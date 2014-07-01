package com.diamond.iain.thenewboston;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity implements View.OnClickListener {

	ImageButton takePic;
	Button setWall;
	ImageView returnedPic;
	Intent intent;
	final int cameraData = 0;
	Bitmap bmp;
	InputStream inStream;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		initialize();
		inStream = getResources().openRawResource(R.drawable.ic_launcher);
		bmp = BitmapFactory.decodeStream(inStream);
	}

	private void initialize() {
		returnedPic = (ImageView) findViewById(R.id.ivReturnedPicture);
		takePic = (ImageButton) findViewById(R.id.ibTakePicture);
		setWall = (Button) findViewById(R.id.bSetWallpaper);
		takePic.setOnClickListener(this);
		setWall.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSetWallpaper:
			try {
				//WallpaperManager.getInstance(getApplicationContext()).setStream(inStream);
				getApplicationContext().setWallpaper(bmp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.ibTakePicture:
			intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent, cameraData);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			returnedPic.setImageBitmap(bmp);
		}
	}

}
