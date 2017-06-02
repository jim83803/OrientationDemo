package com.s10576019.cyel.orientationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.s10576019.cyel.orientationdemo.sensors.OrientationManager;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView azimuthTextView, pitchTextView, rollTextView;
    private OrientationManager orientationManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orientationManager = new OrientationManager(this);

        azimuthTextView = (TextView) findViewById(R.id.azimut);
        pitchTextView = (TextView) findViewById(R.id.pitch);
        rollTextView = (TextView) findViewById(R.id.roll);
    }

    protected void onResume() {
        super.onResume();

        if (orientationManager.getState() == OrientationManager.STATE_IDLE) {
            orientationManager.start(new OrientationManager.OrientationListener() {
                @Override
                public void onOrientationChanged(float azimuth, float pitch, float roll) {
                    azimuthTextView.setText(String.format(Locale.getDefault(), "%.2f π", azimuth/Math.PI));
                    pitchTextView.setText(String.format(Locale.getDefault(), "%.2f π", pitch/Math.PI));
                    rollTextView.setText(String.format(Locale.getDefault(), "%.2f π", roll/Math.PI));
                }
            });
        }
    }

    protected void onPause() {
        if (orientationManager.getState() == OrientationManager.STATE_RUNNING) {
            orientationManager.stop();
        }
        super.onPause();
    }

}