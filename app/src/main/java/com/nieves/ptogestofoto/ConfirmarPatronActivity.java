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
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import me.zhanghai.android.patternlock.PatternUtils;
import me.zhanghai.android.patternlock.PatternView;

/**
 * Created by Nieves on 08/02/2016.
 * Clase con la que comprobamos que el patron establecido sea correcto
 */
public class ConfirmarPatronActivity extends me.zhanghai.android.patternlock.ConfirmPatternActivity {

    private static final String TAG = ConfirmarPatronActivity.class.getSimpleName();

    @Override
    protected void onStart() {
        super.onStart();
        if (!Utility.isPatternSetted(this)) {
            Intent i = new Intent(this, EstablecerPatronActivity.class);
            startActivity(i);
        } else {
            Log.d(TAG, "Patrón ya seteado");
        }
    }

    /**
     * Metodo en el que comprobamos que el patron introducido por el usuario es correcto
     *
     * @param pattern recibe el patron establecido por el usuario
     * @return devolvera true si el patron coincide con el almacenado y false en caso contrario
     */
    @Override
    protected boolean isPatternCorrect(List<PatternView.Cell> pattern) {
        String patternSha1 = Utility.loadFromPreferences(this);
        return TextUtils.equals(PatternUtils.patternToSha1String(pattern), patternSha1);
    }

    /**
     * Metodo con el que podemos proceder a cambiar el patron previamente establecido
     */
    @Override
    protected void onForgotPassword() {
        super.onForgotPassword();
        Intent i = new Intent(this, EstablecerPatronActivity.class);
        startActivity(i);
    }

    /**
     * Metodo con el que le indicamos qué hacer en caso de pulsar el boton de "atras"
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
