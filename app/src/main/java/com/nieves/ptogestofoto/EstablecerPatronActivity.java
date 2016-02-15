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

import java.util.List;

import me.zhanghai.android.patternlock.PatternUtils;
import me.zhanghai.android.patternlock.PatternView;

/**
 * Created by Nieves on 08/02/2016.
 * Clase en la que estableceremos lo necesario para poder leer y almacenar un patron
 */
public class EstablecerPatronActivity extends me.zhanghai.android.patternlock.SetPatternActivity {
    @Override
    protected void onSetPattern(List<PatternView.Cell> pattern) {
        //pasamos el pattern a string
        String patternSha = PatternUtils.patternToSha1String(pattern);
        //guardamos el patron en formato string para su posterior carga
        Utility.saveToPreferences(this, patternSha);

        Intent i = new Intent(this, ConfirmarPatronActivity.class);
        startActivity(i);
    }


}
