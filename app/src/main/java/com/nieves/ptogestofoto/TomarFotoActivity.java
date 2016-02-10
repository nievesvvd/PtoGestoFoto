/*
 *  Copyright (C) 2015, 2016 - Samuel Peregrina Morillas <gaedr@correo.ugr.es>, Nieves V. Velásquez Díaz <nievesvvd@correo.ugr.es>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.nieves.ptogestofoto;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TomarFotoActivity extends AppCompatActivity {
    private ImageView img;
    private Intent camera = new Intent();
    private int count = 3;
    private File image;
    private static final int PETICION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomar_foto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        img = (ImageView) this.findViewById(R.id.imageView1);

        tomarFoto();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo desde el que llamamos a la camara para tomar la foto
     */
    public void tomarFoto() {
        camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        //creamos una carpeta donde guardar las fotos hechas por esta app
        File imagesFolder = new File(
                Environment.getExternalStorageDirectory(), "GestoFotos");
        imagesFolder.mkdirs();

        //establecemos el nombre de la imagen y le decimos donde se guardara
        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        image = new File(imagesFolder, "IMG_" + name + ".jpg");
        Uri uriImage = Uri.fromFile(image);

        //almacenamos la imagen en la memoria interna
        camera.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
        if (camera.resolveActivity(getPackageManager()) != null) {
            //lanzamos la aplicacion de la camara
            startActivityForResult(camera, PETICION);
        }
    }

    /**
     * Metodo con el que comprobamos que se haya realizado la foto, y de ser asi, la almacenamos en
     * la memoria y, finalmente la mostramos por pantalla
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap newBM;
        if (requestCode == PETICION && resultCode == Activity.RESULT_OK) {
            Bitmap btmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()
                    + "/GestoFotos/" + image.getName());

            newBM = scale(btmap);
            img.setImageBitmap(newBM);
        }
    }

    /**
     * Metodo con el que escalamos la foto tomada para su posterior muestra
     *
     * @param bt
     * @return
     */
    protected Bitmap scale(Bitmap bt) {
        Bitmap btmap = Bitmap.createScaledBitmap(bt, 390, 650, false);
        int ancho = btmap.getWidth();
        int alto = btmap.getHeight();
        int newAncho = 390;
        int newAlto = 650;

        //calculamos la escala
        float escalaAncho = ((float) newAncho) / ancho;
        float escalaAlto = ((float) newAlto) / alto;

        //creamos una matriz para la conversion de la escala y reescalamos
        Matrix matriz = new Matrix();
        matriz.postScale(escalaAlto, escalaAncho);
        Bitmap reescalarBitMap = Bitmap.createBitmap(btmap, 0, 0, ancho, alto, matriz, true);

        return reescalarBitMap;
    }
//    /**
//     * Metodo que se encarga de, tras tres segundos, tomar una foto
//     */
//    public void temporizador() {
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//
//                tomarFoto();
//                Log.d(TomarFotoActivity.class.getSimpleName(), "realizando foto");
//            }
//        };
//        ScheduledThreadPoolExecutor temper = new ScheduledThreadPoolExecutor(1);
//        temper.schedule(r, 3, TimeUnit.SECONDS);
//    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, ConfirmarPatronActivity.class);
        startActivity(i);
    }
}
