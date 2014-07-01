package com.diamond.iain.thenewboston;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnItemSelectedListener,
		OnClickListener {

	TextView canWrite, canRead;
	EditText saveFile;
	Button confirm, save;
	String state;
	boolean writable, readable;
	Spinner spinner;
	String[] paths = { "Music", "Pictures", "Movies", "Podcasts" };
	File dir = Environment
			.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
	File filename = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);
		canWrite = (TextView) findViewById(R.id.tvCanWrite);
		canRead = (TextView) findViewById(R.id.tvCanRead);
		saveFile = (EditText) findViewById(R.id.etSaveAs);
		confirm = (Button) findViewById(R.id.bConfirmSave);
		save = (Button) findViewById(R.id.bSaveFile);
		confirm.setOnClickListener(this);
		save.setOnClickListener(this);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, paths);

		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
	}

	private void checkState() {
		state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			canWrite.setText("true");
			canRead.setText("true");
			writable = readable = true;
		} else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			canWrite.setText("false");
			canRead.setText("true");
			writable = false;
			readable = true;

		} else {
			canWrite.setText("false");
			canRead.setText("false");
			writable = false;
			readable = false;
		}
	}

	private boolean isWritable() {
		return writable;
	}

	private boolean isReadable() {
		return readable;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0:
			dir = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1:
			dir = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:
			dir = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
			break;
		case 3:
			dir = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS);
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bSaveFile:

			String name = saveFile.getText().toString();
			if (name.equals(""))
				name = "defaultFilename";

			if (name.endsWith(".png")) {
				filename = new File(dir, name);
			} else {
				filename = new File(dir, name + ".png");
			}
			checkState();

			if (isWritable() && isReadable()) {

				dir.mkdirs();

				try {
					InputStream is = getResources().openRawResource(
							R.drawable.greenball);
					OutputStream os = new FileOutputStream(filename);
					byte[] data = new byte[is.available()];
					is.read(data);
					os.write(data);
					is.close();
					os.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/*
				 * Toast toast = Toast.makeText(this, "File has been saved.",
				 * Toast.LENGTH_LONG); toast.show();
				 */

				MediaScannerConnection.scanFile(this,
						new String[] { filename.toString() }, null,
						new MediaScannerConnection.OnScanCompletedListener() {

							@Override
							public void onScanCompleted(String path, Uri uri) {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										Toast.makeText(ExternalData.this,
												"Media scan completed.",
												Toast.LENGTH_SHORT).show();
									}
								});
							}
						});

			}

			break;
		case R.id.bConfirmSave:
			save.setVisibility(View.VISIBLE);
			break;
		}
	}
}
