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

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nieves.camera2basics.Camera2BasicFragment;

import java.util.Timer;
import java.util.TimerTask;


public class TomarFotoFragment extends Camera2BasicFragment {
    Timer timer;
    private static final String TAG = Camera2BasicFragment.class.getSimpleName();

    public TomarFotoFragment() {
        super();
    }

    public static TomarFotoFragment newInstance() {

        Bundle args = new Bundle();

        TomarFotoFragment fragment = new TomarFotoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showToast("Foto!!!!!");

        Button b = (Button) getActivity().findViewById(R.id.picture);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "Llamo a la cámara");
                takePicture();
            }
        };
        timer.schedule(timerTask, 3);
    }
}
