package com.diamond.iain.thenewboston;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Accelerate extends Activity implements SensorEventListener {
	
	SensorManager manager;
	MySurfaceView ourSurfaceView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(new MySurfaceView(this));
		manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor sensor = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
		
		ourSurfaceView = new MySurfaceView(this);
		ourSurfaceView.resume();
		setContentView(ourSurfaceView);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		ourSurfaceView.pause();
		manager.unregisterListener(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ourSurfaceView.setSensorX(event.values[0]);
		ourSurfaceView.setSensorY(event.values[1]);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
