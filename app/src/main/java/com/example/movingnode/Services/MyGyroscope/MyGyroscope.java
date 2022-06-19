package com.example.movingnode.Services.MyGyroscope;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MyGyroscope {

    public interface Listener{
        public void onRotation(float x, float y, float z);
    }

    Context context;
    Sensor sensor;
    SensorManager sensorManager;
    SensorEventListener sensorEventListener;
    Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public MyGyroscope(Context context){
        this.context = context;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(listener != null){
                    float[] values = sensorEvent.values;
                    listener.onRotation(values[0], values[1], values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }

    public void register(){
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregister(){
        sensorManager.unregisterListener(sensorEventListener);
    }
}
