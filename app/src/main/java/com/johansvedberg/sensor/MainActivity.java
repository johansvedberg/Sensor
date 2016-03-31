package com.johansvedberg.sensor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.afollestad.materialcamera.MaterialCamera;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private final static int CAMERA_RQ = 6969;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void openAcc(View view){
        Intent intent = new Intent(this, Accelerometer.class);
        startActivity(intent);
    }
    public void openComp(View view){
        Intent intent = new Intent(this, Compass.class);
        startActivity(intent);
    }

    public void openCam(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);



    }
}
