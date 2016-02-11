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
