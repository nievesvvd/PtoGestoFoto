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
