package com.diamond.iain.thenewboston;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;

import com.diamond.iain.thenewboston.R.id;

public class Flipper extends Activity implements OnClickListener {

	ViewFlipper flipper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flipper);
		flipper = (ViewFlipper) findViewById(id.viewFlipper1);
		flipper.setOnClickListener(this);
		flipper.setFlipInterval(1000);
		flipper.startFlipping();
	}

	@Override
	public void onClick(View v) {
		flipper.showNext();
	}
	
	

}
