package com.diamond.iain.thenewboston;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements Runnable {

	SurfaceHolder ourHolder;
	Thread ourThread = null;
	boolean isRunning = false;
	
	float x, y, sensorX, sensorY;
	Bitmap ball;
	
	public MySurfaceView(Context context) {
		super(context);
		ourHolder = getHolder();
		
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
		x = y = sensorX = sensorY = 0;		
	}
	
	public void setSensorX(float sensorX) {
		this.sensorX = sensorX;
	}

	public void setSensorY(float sensorY) {
		this.sensorY = sensorY;
	}

	public void pause() {
		isRunning = false;
		while (true) {
			try {
				ourThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		ourThread = null;
	}

	public void resume() {
		isRunning = true;
		ourThread = new Thread(this);
		ourThread.start();
	}

	@Override
	public void run() {
		while (isRunning) {
			if (!ourHolder.getSurface().isValid())
				continue;

			Canvas canvas = ourHolder.lockCanvas();
			canvas.drawRGB(2, 2, 150);
			float centerX = canvas.getWidth()/2;
			float centerY = canvas.getHeight()/2;
			canvas.drawBitmap(ball, centerX + sensorX*50, centerY - sensorY*90, null);
			ourHolder.unlockCanvasAndPost(canvas);
		}
	}
}