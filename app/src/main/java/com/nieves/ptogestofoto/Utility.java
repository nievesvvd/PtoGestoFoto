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


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nieves on 08/02/2016.
 * Clase en la que establecemos los metodos para almacenar y cargar el patron establecido
 */
public class Utility {
    private static final String PREFERENCES_LOCK_PATTERN = "PreferencesLockPattern";
    private static final String KEY_LOCK_PATTERN = "KeyLockPattern";
    private static boolean almacenado;

    /**
     * Metodo al que, dado el contexto y un patron convertido en string, lo almacenamos para
     * su posterior uso
     *
     * @param context
     * @param lockPattern
     */
    public static void saveToPreferences(Context context, String lockPattern) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_LOCK_PATTERN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_LOCK_PATTERN, lockPattern);
        editor.commit();
    }

    /**
     * Metodo al que, dado el contexto, nos carga el patron almacenado previmente
     *
     * @param context
     * @return
     */
    public static String loadFromPreferences(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_LOCK_PATTERN, Context.MODE_PRIVATE);
        return sp.getString(KEY_LOCK_PATTERN, null);
    }

    public static boolean isPatternSetted(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_LOCK_PATTERN, Context.MODE_PRIVATE);
        return sp != null;
    }
}
