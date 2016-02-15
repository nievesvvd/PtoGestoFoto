/*
 *   Copyright (C) 2015, 2016 - Samuel Peregrina Morillas <gaedr@correo.ugr.es>, Nieves V. Velásquez Díaz <nievesvvd@correo.ugr.es>
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.nieves.ptogestofoto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.commonsware.cwac.cam2.CameraActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int PATTERN_CONFIRMED = 123;
    private static final int REQUEST_PORTRAIT_FFC = 222;
    private File image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Utility.isPatternSetted(this)) {
            Intent i = new Intent(this, ConfirmarPatronActivity.class);
            startActivityForResult(i, PATTERN_CONFIRMED);
        } else {
            Intent i = new Intent(this, EstablecerPatronActivity.class);
            startActivity(i);
        }
    }

    /**
     * Metodo desde el que una vez se ha confirmado el patron, se realiza la foto
     *
     * @param requestCode codigo con el que se llama al metodo
     * @param resultCode  codigo que se espera como resultado tras la llamada
     * @param data        datos con los que trabajar desde el onActivityResult
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PATTERN_CONFIRMED && resultCode == RESULT_OK) {
            File saveFolder = new File(Environment.getExternalStorageDirectory(), "Autophoto");
            saveFolder.mkdirs();

            String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            image = new File(saveFolder, "IMG_" + name + ".jpg");
            Uri uriImage = Uri.fromFile(image);

            Intent i = new CameraActivity.IntentBuilder(this)
                    .skipConfirm()
                    .facing(CameraActivity.Facing.BACK)
                    .to(uriImage)
                    .debug()
                    .build();
            startActivityForResult(i, REQUEST_PORTRAIT_FFC);


        } else if (requestCode == REQUEST_PORTRAIT_FFC && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Foto realizada.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConfirmarPatronActivity.class);
            startActivity(intent);
        }
    }
}
