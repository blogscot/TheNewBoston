package com.diamond.iain.thenewboston;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class MyBringBack extends View {
	
	Bitmap gBall;
	float posY;
	Typeface myFont;
	Rect middleRect = new Rect();
	Paint ourBlue = new Paint();	
	Paint textPaint = new Paint();
	
	public MyBringBack(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		gBall = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
		posY = 0;
		myFont = Typeface.createFromAsset(context.getAssets(), "G-Unit.TTF");
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		
		textPaint.setARGB(50, 255, 100, 150);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(60);
		textPaint.setTypeface(myFont);
		canvas.drawText("mybringback", canvas.getWidth()/2, 200, textPaint);
		
		canvas.drawBitmap(gBall, (canvas.getWidth()/2)-gBall.getWidth()/2, posY, null);
		if (posY < canvas.getHeight()) {
			posY += 10;
		} else {
			posY = 0;
		}

		middleRect.set(0, 400, canvas.getWidth(), 500);
		ourBlue.setColor(Color.BLUE);
		canvas.drawRect(middleRect, ourBlue);
		invalidate();
	}

}
