package com.diamond.iain.thenewboston;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class GFX extends Activity {

	MyBringBack ourView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		this.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		super.onCreate(savedInstanceState);
		ourView = new MyBringBack(this);
		setContentView(ourView);
	}

}
