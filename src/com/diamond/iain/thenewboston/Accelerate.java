package com.diamond.iain.thenewboston;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Accelerate extends Activity implements SensorEventListener {

	private final static long UPDATE_THRESHOLD = 200;

	SensorManager manager;
	Sensor sensor;
	MySurfaceView ourSurfaceView;
	long mLastUpdate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(new MySurfaceView(this));
		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			sensor = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
		}

		ourSurfaceView = new MySurfaceView(this);
		ourSurfaceView.resume();
		setContentView(ourSurfaceView);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
		mLastUpdate = System.currentTimeMillis();

	}

	@Override
	protected void onPause() {
		ourSurfaceView.pause();
		manager.unregisterListener(this);
		super.onPause();
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

			long actualTime = System.currentTimeMillis();

			if (actualTime - mLastUpdate > UPDATE_THRESHOLD) {

				mLastUpdate = actualTime;

				ourSurfaceView.setSensorX(event.values[0]);
				ourSurfaceView.setSensorY(event.values[1]);
			}
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
}
