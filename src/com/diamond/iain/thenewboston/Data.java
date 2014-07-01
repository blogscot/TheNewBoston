package com.diamond.iain.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements View.OnClickListener {

	Button startAct, startActForResult;
	EditText sendData;
	TextView getData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		startAct = (Button) findViewById(R.id.bSA);
		startActForResult = (Button) findViewById(R.id.bSAFR);
		sendData = (EditText) findViewById(R.id.etSend);
		getData = (TextView) findViewById(R.id.tvGot);
		startAct.setOnClickListener(this);
		startActForResult.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, OpenedClass.class);

		switch (v.getId()){
		case R.id.bSA:
			String userString = sendData.getText().toString();
			Bundle basket = new Bundle();
			basket.putString("stringKey", userString);
			intent.putExtras(basket);
			startActivity(intent);
			break;
		case R.id.bSAFR:
			startActivityForResult(intent, 0);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK) {
			Bundle basket = data.getExtras();
			String str = basket.getString("answerKey");
			getData.setText(str);
		}
	}
}
