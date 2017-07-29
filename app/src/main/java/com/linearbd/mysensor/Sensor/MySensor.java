package com.linearbd.mysensor.Sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.List;

/**
 * Created by Genius 03 on 7/25/2017.
 */

public class MySensor {
    private static final String TAG="MySensor";

    private Context context;
    private SensorManager sensorManager;
    private SensorEventListener lightEventListener;
    private SensorEventListener linearAccelerationEventListener;
    private SensorEventListener gyroscopeEventListener;
    private SensorListenerList sensorListenerList;


    public MySensor(Context context) {
        this.context = context;
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.sensorListenerList = new SensorListenerList();
    }

    public List<Sensor> getListofSensor(){
        return sensorManager.getSensorList(Sensor.TYPE_ALL);
    }

    private Sensor getLightSensor(){
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        return lightSensor;
    }

    public void registerLightSensor(){
        if(lightEventListener==null){
            lightEventListener = sensorListenerList.getLightListener();
        }
        sensorManager.registerListener(lightEventListener,getLightSensor(),SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterLightSensor(){
        sensorManager.unregisterListener(lightEventListener);

    }

    //Linear Acceleration Code Start Here

    private Sensor getLinearAccelerationSensor(){
        Sensor linearAccelerationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        Log.d(TAG,linearAccelerationSensor.getMaximumRange()+"");
        return linearAccelerationSensor;
    }

    public void registerLinearAccelerationSensor(){
        if(linearAccelerationEventListener==null){
            linearAccelerationEventListener = sensorListenerList.getLinearAccelerationListener();
        }
        sensorManager.registerListener(linearAccelerationEventListener,getLinearAccelerationSensor(),SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterLinearAccelerationSensor(){
        sensorManager.unregisterListener(linearAccelerationEventListener);

    }

    public void setMyEventListener(MyEventListener myEventListener){
        sensorListenerList.setMyEventListener(myEventListener);
    }

    private Sensor getGyroscopeSensor(){
        Sensor gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        Log.d(TAG,gyroscopeSensor.getMaximumRange()+"");
        return gyroscopeSensor;
    }

    public void registerGyroscopeSensor(){
        if(gyroscopeEventListener==null){
            gyroscopeEventListener = sensorListenerList.getGyroscopeListener();
        }
        sensorManager.registerListener(gyroscopeEventListener,getGyroscopeSensor(),SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterGyroscopeSensor(){
        sensorManager.unregisterListener(gyroscopeEventListener);

    }





}
