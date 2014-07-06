package com.diamond.iain.thenewboston;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Accelerate extends Activity implements SensorEventListener {

	private final long UPDATE_THRESHOLD = 200;
	private final float mAlpha = 0.8f;

	private float[] mGravity = new float[2];
	
	private SensorManager manager;
	private Sensor sensor;
	private MySurfaceView ourSurfaceView;
	private long mLastUpdate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(new MySurfaceView(this));
		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		
		if (null == (sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER))) {
			finish();
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
				
				mGravity[0] = lowPass(event.values[0], mGravity[0]);
				mGravity[1] = lowPass(event.values[1], mGravity[1]);

				ourSurfaceView.setSensorX(mGravity[0]);
				ourSurfaceView.setSensorY(mGravity[1]);
			}
		}
	}

	// Uses a running average to create a low pass filter
	private float lowPass(float current, float average) {
		return average * mAlpha + current * (1 - mAlpha);
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
}
