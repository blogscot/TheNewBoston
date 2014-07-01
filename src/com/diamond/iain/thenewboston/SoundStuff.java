package com.diamond.iain.thenewboston;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class SoundStuff extends Activity implements OnClickListener,
		OnLongClickListener {

	SoundPool sp;
	final int maxStreams = 5;
	int explosion = 0;
	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = new View(this);
		view.setOnClickListener(this);
		view.setOnLongClickListener(this);
		setContentView(view);
		mp = MediaPlayer.create(this, R.raw.backgroundmusic);
	}

	@Override
	public void onClick(View v) {
		if (explosion != 0)
			sp.play(explosion, 1, 1, 0, 0, 1);
	}

	@Override
	protected void onResume() {
		super.onResume();
		sp = new SoundPool(maxStreams, AudioManager.STREAM_MUSIC, 0);
		explosion = sp.load(this, R.raw.explosion, 1);
	}

	@Override
	protected void onPause() {
		super.onPause();
		sp.release();
		mp.release();
	}

	@Override
	public boolean onLongClick(View v) {
		mp.start();
		return false;
	}

}
