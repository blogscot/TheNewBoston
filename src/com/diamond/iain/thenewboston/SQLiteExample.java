package com.diamond.iain.thenewboston;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener {
	
	Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
	EditText sqlName, sqlHotness, sqlRow;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteexample);
		sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
		sqlView = (Button) findViewById(R.id.bSQLView);
		sqlModify = (Button) findViewById(R.id.bModifyEntry);
		sqlGetInfo = (Button) findViewById(R.id.bGetInfo);
		sqlDelete = (Button) findViewById(R.id.bDeleteEntry);
		sqlHotness = (EditText) findViewById(R.id.etSQLHotness);
		sqlName = (EditText) findViewById(R.id.etSQLName);
		sqlRow = (EditText) findViewById(R.id.etSQLRowInfo);
		
		sqlView.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);
		sqlModify.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		
		String rowText = "";
		long row = 0;
		HotOrNot person;
		
		switch(v.getId()){
		case R.id.bSQLUpdate:
			
			boolean success = true;
			try {
			String name = sqlName.getText().toString();
			String hotness = sqlHotness.getText().toString();
			
			HotOrNot entry = new HotOrNot(this);
			entry.open();
			entry.createEntry(name, hotness);
			entry.close();
			} catch (Exception e) {
				success = false;
			} finally {
				if(success) {
					Dialog dialog = new Dialog(this);
					dialog.setTitle("Updating SQL");
					TextView tv = new TextView(this);
					tv.setText("Success");
					dialog.setContentView(tv);
					dialog.show();
				}
			}
			break;
		case R.id.bSQLView:
			Intent intent = new Intent("com.diamond.iain.thenewboston.SQLVIEW");
			startActivity(intent);
			break;
		case R.id.bGetInfo:
			rowText = sqlRow.getText().toString();
			row = Long.parseLong(rowText);
			person = new HotOrNot(this);
			person.open();
			String name = person.getName(row);
			String hotness = person.getHotness(row);
			person.close();
			sqlName.setText(name);
			sqlHotness.setText(hotness);
			break;
		case R.id.bModifyEntry:
			String editName = sqlName.getText().toString();
			String editHotness = sqlHotness.getText().toString();
			rowText = sqlRow.getText().toString();
			row = Long.parseLong(rowText);
			person = new HotOrNot(this);
			person.open();
			person.update(row, editName, editHotness);
			person.close();
			break;
		case R.id.bDeleteEntry:
			rowText = sqlRow.getText().toString();
			row = Long.parseLong(rowText);
			person = new HotOrNot(this);
			person.open();
			person.delete(row);
			person.close();			
			break;
		}
	}
}
