package com.diamond.iain.thenewboston;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener {

	EditText sharedData;
	TextView dataResults;
	FileOutputStream fos;
	public final String filename = "InternalDataStore12345678901234567890";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedpreferences);

		Button save = (Button) findViewById(R.id.bSave);
		Button load = (Button) findViewById(R.id.bLoad);
		sharedData = (EditText) findViewById(R.id.etSharedPrefs);
		dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
		save.setOnClickListener(this);
		load.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSave:
			String data = sharedData.getText().toString();
			try {
				fos = openFileOutput(filename, Context.MODE_PRIVATE);
				fos.write(data.getBytes());
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.bLoad:
			new backgroundLoad().execute(filename);
			break;
		}
	}

	public class backgroundLoad extends AsyncTask<String, Integer, String> {

		ProgressDialog dialog;
		
		protected void onPreExecute() {
			dialog = new ProgressDialog(InternalData.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
		};

		@Override
		protected String doInBackground(String... params) {
			
			for(int i = 0; i<20;i++) {
				publishProgress(5);
				try {
					Thread.sleep(88);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			String str = null;
			FileInputStream fis = null;
			try {
				fis = openFileInput(filename);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			byte[] dataArray = new byte[filename.length()];

			try {
				if (fis != null && fis.available() > 0) {
					fis.read(dataArray);
					str = new String(dataArray);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return str;
		}

		protected void onProgressUpdate(Integer... progress) {
			dialog.incrementProgressBy(progress[0]);
		}
		
		protected void onPostExecute(String result) {
			dataResults.setText(result);
			dialog.dismiss();
		}
	}
}
