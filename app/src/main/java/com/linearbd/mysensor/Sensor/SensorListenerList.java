package com.linearbd.mysensor.Sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

/**
 * Created by Genius 03 on 7/29/2017.
 */

public class SensorListenerList {
    private MyEventListener myEventListener;

    public SensorListenerList() {
    }

    public SensorEventListener getLightListener(){
        return new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                Log.d("TTTT",event.values[0]+"");

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    public void setMyEventListener(MyEventListener myEventListener){
        this.myEventListener = myEventListener;
    }

    public SensorEventListener getLinearAccelerationListener(){
        return new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(myEventListener!=null){
                    myEventListener.getEvent(event);
                }
/*
                Log.d("TTTT",event.values[0]+"");
                Log.d("TTTT",event.values[0]+"");
                Log.d("TTTT",event.values[0]+"");*/

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    public SensorEventListener getGyroscopeListener(){
        return new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(myEventListener!=null){
                    myEventListener.getEvent(event);
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }


}
