package com.johansvedberg.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class Compass extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mComp;
    private Sensor mComp1;
    private Sensor vector;

    private TextView textView;
    private TextView textViewInfo;
    private ImageView rose;
  /*  private float[] inR = new float[16];
    private float[] I = new float[16];
    private float[] gravity = new float[3];
    private float[] geomag = new float[3];
    private float[] orientVals = new float[3];*/

    private float[] gravity;
    private float[] geomagnetic;

    private float[] rotationMatrix;
    private float[] rotationMatrixResult;
    //private float[] rotationMatrixNewResult;

    private int azimuth = 0;
    private int currentDegree;
    //Rose rose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        // rose = new Rose(this);
        //setContentView(rose);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mComp = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mComp1 = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        vector = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        rotationMatrix = new float[9];
        rotationMatrixResult = new float[3];
        //rotationMatrixNewResult = new float[9];


        textView = (TextView) findViewById(R.id.degree);
        textViewInfo = (TextView) findViewById(R.id.info);
        rose = (ImageView) findViewById(R.id.rose);


    }


    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public final void onSensorChanged(SensorEvent event) {

 /*
         if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
           gravity = event.values.clone();
        }

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            geomagnetic = event.values.clone();
            textViewInfo.setText(String.valueOf(event.values[0]));
        }


        if (gravity != null && geomagnetic != null) {
            boolean success = SensorManager.getRotationMatrix(rotationMatrix, null, gravity, geomagnetic);

            if (success) {
                SensorManager.getOrientation(rotationMatrix, rotationMatrixResult);
               // SensorManager.remapCoordinateSystem(rotationMatrixResult, 2, 129, rotationMatrixNewResult);
                azimuth = Math.toDegrees(rotationMatrixResult[0]);


            }
        }*/

        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {

            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);

            azimuth = (int) (Math.toDegrees(SensorManager.getOrientation(rotationMatrix, rotationMatrixResult)[0]) + 360) % 360;
        }

        if (azimuth > 340 || azimuth < 20) {
            textView.setText(String.valueOf(azimuth) + "° NORTH");

        } else if (azimuth > 285 && azimuth < 340) {

            textView.setText(String.valueOf(azimuth) + "° NORTHWEST");

        } else if (azimuth > 255 && azimuth < 285) {
            textView.setText(String.valueOf(azimuth) + "° WEST");


        } else if (azimuth > 195 && azimuth < 255) {
            textView.setText(String.valueOf(azimuth) + "° SOUTHWEST");


        } else if (azimuth > 165 && azimuth < 195) {
            textView.setText(String.valueOf(azimuth) + "° SOUTH");


        } else if (azimuth > 105 && azimuth < 165) {
            textView.setText(String.valueOf(azimuth) + "° SOUTHEAST");


        } else if (azimuth > 77 && azimuth < 105) {
            textView.setText(String.valueOf(azimuth) + "° EAST");


        } else if (azimuth > 20 && azimuth < 75) {
            textView.setText(String.valueOf(azimuth) + "° NORTHEAST");

        }

        RotateAnimation ra = new RotateAnimation(currentDegree, -azimuth, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        ra.setDuration(210);

        ra.setFillAfter(true);

        rose.startAnimation(ra);

        currentDegree = -azimuth;
        // rose.setDirection(azimuth.intValue());

        /*
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                gravity = event.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                geomag = event.values.clone();
                break;
        }


        if (gravity != null && geomag != null) {


            boolean success = SensorManager.getRotationMatrix(inR, I,
                    gravity, geomag);
            if (success) {
                SensorManager.getOrientation(inR, orientVals);
                azimuth = Math.toDegrees(orientVals[0]);

            }
        }

        long angle = Math.round(azimuth);

        if (angle > 0 && angle < 90){
            textView.setText(String.valueOf(angle) + " NORTH");
        } else if(angle > 90 && angle < 180){
            textView.setText(String.valueOf(angle) + " EAST");

        } else if(angle < 0 && angle > -90){
            textView.setText(String.valueOf(angle) + " WEST");
        } else{
            textView.setText(String.valueOf(angle) + " SOUTH");
        }*/


    }

    @Override
    protected void onResume() {
        super.onResume();
        //mSensorManager.registerListener(this, mComp, SensorManager.SENSOR_DELAY_UI);
        // mSensorManager.registerListener(this, mComp1, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(this, vector, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


}
