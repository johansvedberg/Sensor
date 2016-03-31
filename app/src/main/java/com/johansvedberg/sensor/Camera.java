package com.johansvedberg.sensor;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Camera extends AppCompatActivity {

    private String backfacingID;
    private boolean hasbackfacing = false;
    private boolean hasfrontfacing = false;
    private String frontfacingID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        CameraManager mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        TextView textView = (TextView) findViewById(R.id.camera);
        try {
            for (String cameraId : mCameraManager.getCameraIdList()) {
                CameraCharacteristics cameraCharacteristics =
                        mCameraManager.getCameraCharacteristics(cameraId);
                if (cameraCharacteristics.get(cameraCharacteristics.LENS_FACING) ==
                        CameraCharacteristics.LENS_FACING_BACK) {
                    backfacingID = cameraId;
                    hasbackfacing = true;
                } else if (cameraCharacteristics.get(cameraCharacteristics.LENS_FACING) ==
                        CameraCharacteristics.LENS_FACING_FRONT) {
                    hasfrontfacing = true;
                    frontfacingID = cameraId;

                }
            }

        } catch (CameraAccessException e) {

        }

        if (hasbackfacing) {
            textView.append("This phone has a backfacing camera!");
        }

        if (hasfrontfacing){
            textView.append(" This phone has a frontfacing camera");
        }


    }
}
