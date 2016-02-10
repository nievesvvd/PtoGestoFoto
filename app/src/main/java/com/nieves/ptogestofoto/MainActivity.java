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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    /**
     * Metodo con el que vemos cual de las dos views vamos a ejecutar
     * si la que establece el patron (EstablecerPatronActivity) o la que, una
     * vez que tiene el patron, lo pide de nuevo y si es correcto lanza la
     * camara(ConfirmarPatronActivity)
     *
     * @param savedInstanceState
     */
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Utility.isPatternSetted(this)) {
            Intent i = new Intent(this, ConfirmarPatronActivity.class);
            startActivity(i);
        } else {
            Intent i = new Intent(this, EstablecerPatronActivity.class);
            startActivity(i);
        }

    }


    /**
     * Metodo para reiniciar la aplicacion en caso de que se haya suspendido
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Metodo con el que suspendemos la aplicacion
     */
    @Override
    protected void onPause() {
        super.onPause();
    }
}
