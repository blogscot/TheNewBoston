package com.diamond.iain.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity {
	
	MediaPlayer splashSong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		splashSong = MediaPlayer.create(this, R.raw.tubular_bells);
		
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean playMusic = getPrefs.getBoolean("checkbox", true);
		if(playMusic) splashSong.start();
		
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					Intent openMainActivity = new Intent(
							"com.diamond.iain.thenewboston.MENU");
					startActivity(openMainActivity);
				}
			}
		};

		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		splashSong.release();
		finish();
	}

}
