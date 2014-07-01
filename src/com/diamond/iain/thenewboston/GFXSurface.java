package com.diamond.iain.thenewboston;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener {

	MyBringBackSurface ourSurfaceView;
	float posX, posY, startX, startY, finishX, finishY;
	float dX, dY, aniX, aniY, scaledX, scaledY;
	Bitmap bmpBall, bmpPlus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurfaceView = new MyBringBackSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		posX = posY = startX = startY = finishX = finishY = 0;
		dX = dY = aniX = aniY = scaledX = scaledY = 0;
		bmpBall = BitmapFactory.decodeResource(getResources(),
				R.drawable.greenball);
		bmpPlus = BitmapFactory.decodeResource(getResources(), R.drawable.plus);
		setContentView(ourSurfaceView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		posX = event.getX();
		posY = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			startY = event.getY();
			finishX = finishY = dX = dY = aniX = aniY = scaledX = scaledY = 0;
			break;
		case MotionEvent.ACTION_UP:
			finishX = event.getX();
			finishY = event.getY();
			dX = finishX - startX;
			dY = finishY - startY;
			scaledX = dX / 30;
			scaledY = dY / 30;
			posX = posY = 0;
			break;
		}
		return true;
	}

	public class MyBringBackSurface extends SurfaceView implements Runnable {

		SurfaceHolder ourHolder;
		Thread ourThread = null;
		boolean isRunning = false;

		public MyBringBackSurface(Context context) {
			super(context);
			ourHolder = getHolder();
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

			// TODO Auto-generated method stub
			while (isRunning) {
				if (!ourHolder.getSurface().isValid())
					continue;

				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(2, 2, 150);
				if (posX != 0 || posY != 0) {
					canvas.drawBitmap(bmpBall, posX - bmpBall.getWidth() / 2,
							posY - bmpBall.getHeight() / 2, null);
				}
				if (startX != 0 || startY != 0) {
					canvas.drawBitmap(bmpPlus, startX - bmpPlus.getWidth() / 2,
							startY - bmpPlus.getHeight() / 2, null);
				}
				if (finishX != 0 || finishY != 0) {
					canvas.drawBitmap(bmpBall, finishX - (bmpBall.getWidth() / 2)
							- aniX, finishY - (bmpBall.getHeight() / 2) - aniY,
							null);
					canvas.drawBitmap(bmpPlus,
							finishX - bmpPlus.getWidth() / 2,
							finishY - bmpPlus.getHeight() / 2, null);
				}
				aniX += scaledX;
				aniY += scaledY;
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
}
