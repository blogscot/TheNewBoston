package com.diamond.iain.thenewboston;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener {

	TabHost tabHost;
	TextView showResults;
	long start, stop = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		tabHost = (TabHost) findViewById(R.id.tabhost);

		Button startWatch = (Button) findViewById(R.id.bStartWatch);
		Button stopWatch = (Button) findViewById(R.id.bStopWatch);
		Button addTab = (Button) findViewById(R.id.bAddTab);
		showResults = (TextView) findViewById(R.id.tvShowResults);

		addTab.setOnClickListener(this);
		startWatch.setOnClickListener(this);
		stopWatch.setOnClickListener(this);

		tabHost.setup();

		TabSpec spec1 = tabHost.newTabSpec("tag1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("Stop Watch");
		tabHost.addTab(spec1);

		TabSpec spec2 = tabHost.newTabSpec("tag2");
		spec2.setContent(R.id.tab2);
		spec2.setIndicator("Tab Two");
		tabHost.addTab(spec2);

		TabSpec spec3 = tabHost.newTabSpec("tag3");
		spec3.setContent(R.id.tab3);
		spec3.setIndicator("Tab Three");
		tabHost.addTab(spec3);
		start = 0;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bStartWatch:
			start = System.currentTimeMillis();
			break;
		case R.id.bStopWatch:
			if (start != 0) {
				stop = System.currentTimeMillis();
				long result = stop - start;
				int millis = (int) result % 1000;
				int seconds = (int) (result / 1000);
				int minutes = seconds / 60;
				seconds = seconds % 60;
				showResults.setText(String.format("%d:%02d:%03d", minutes, seconds, millis));
			}
			break;
		case R.id.bAddTab:
			TabSpec ourSpec = tabHost.newTabSpec("newTag");
			ourSpec.setContent(new TabHost.TabContentFactory() {

				@Override
				public View createTabContent(String tag) {
					TextView text = new TextView(Tabs.this);
					text.setText("You've created a new Tab!");
					return text;
				}
			});
			ourSpec.setIndicator("New Tab");
			tabHost.addTab(ourSpec);
			break;

		}
	}

}
