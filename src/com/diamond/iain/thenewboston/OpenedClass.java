package com.diamond.iain.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity implements OnClickListener, OnCheckedChangeListener{

	TextView question, test;
	Button returnData;
	RadioGroup selectionList;
	String userString, setData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		
		question = (TextView) findViewById(R.id.tvQuestion);
		test = (TextView) findViewById(R.id.tvText);
		returnData = (Button) findViewById(R.id.bReturnData);
		returnData.setOnClickListener(this);
		selectionList = (RadioGroup) findViewById(R.id.rgAnswers);
		selectionList.setOnCheckedChangeListener(this);
		
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String pQueston = getPrefs.getString("nameKey", "Iain is ...");
		String values = getPrefs.getString("listKey", "4");
		if (values.contentEquals("1")){
			question.setText(pQueston);
		}
		
		//Bundle basket = getIntent().getExtras();
		//userString = basket.getString("stringKey");
		//question.setText(userString);
	}

	@Override
	public void onClick(View v) {
		Intent person = new Intent();
		Bundle basket = new Bundle();
		basket.putString("answerKey", setData);
		person.putExtras(basket);
		setResult(RESULT_OK, person);
		finish();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch(checkedId) {
		case R.id.rGreat:
			setData = "Definitely right!";
			break;
		case R.id.rLike:
			setData = "Probably right!";
			break;
		case R.id.rMeh:
			setData = "Yeah, right.";
			break;
		}
		test.setText(setData);
	}

}
