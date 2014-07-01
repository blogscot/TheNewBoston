package com.diamond.iain.thenewboston;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

	String classes[] = { "MainActivity", "TextPlay", "Email", "Camera", "Data",
			"GFX", "GFXSurface", "SoundStuff", "Slider", "Tabs",
			"SimpleBrowser", "Flipper", "SharedPrefs", "InternalData",
			"ExternalData", "SQLiteExample", "Accelerate" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		String itemClicked = classes[position];

		try {
			Class<?> ourClass = Class.forName("com.diamond.iain.thenewboston."
					+ itemClicked);
			Intent ourIntent = new Intent(this, ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflateMenu = getMenuInflater();
		inflateMenu.inflate(R.menu.cool_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.aboutMe:
			startActivity(new Intent("com.diamond.iain.thenewboston.ABOUT"));
			break;
		case R.id.preferences:
			startActivity(new Intent("com.diamond.iain.thenewboston.PREFS"));
			break;
		case R.id.exit:
			finish();
			break;
		}
		return true;
	}
}
