package com.example.responsi_map;

import static android.content.Context.SENSOR_SERVICE;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class CompassFragment extends Fragment {

    private ImageView compass;

    private SensorManager mSensorManager;
    private Sensor mSensorAccelerator;
    private Sensor mSensorMagneticField;

    private float[] floatGravity = new float[3];
    private float[] floatGeoMagnetic = new float[3];

    private float[] floatOrientation = new float[3];
    private float[] floatRotationMatrix = new float[9];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View compassView = inflater.inflate(R.layout.fragment_compass, container, false);

        compass = compassView.findViewById(R.id.compassSign);

        mSensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);

        mSensorAccelerator = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorMagneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


        return compassView;
    }
    SensorEventListener sensorEventListenerAccelerator = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            floatGravity = event.values;

            SensorManager.getRotationMatrix(floatRotationMatrix, null, floatGravity, floatGeoMagnetic);
            SensorManager.getOrientation(floatRotationMatrix, floatOrientation);

            compass.setRotation((float) (-floatOrientation[0]*180/3.14159));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    SensorEventListener sensorEventListenerMagneticField = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            floatGeoMagnetic = event.values;

            SensorManager.getRotationMatrix(floatRotationMatrix, null, floatGravity, floatGeoMagnetic);
            SensorManager.getOrientation(floatRotationMatrix, floatOrientation);

            compass.setRotation((float) (-floatOrientation[0]*180/3.14159));
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    public void onResume() {
        super.onResume();

        mSensorManager.registerListener(sensorEventListenerAccelerator, mSensorAccelerator, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(sensorEventListenerMagneticField, mSensorMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(sensorEventListenerAccelerator, mSensorAccelerator);
        mSensorManager.unregisterListener(sensorEventListenerMagneticField, mSensorMagneticField);
    }

}