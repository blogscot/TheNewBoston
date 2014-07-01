package com.diamond.iain.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends Activity implements View.OnClickListener {

	EditText emailAddress, emailTitle, personsName, emailBody, companyDisclaimer, legalStuff;
	String address, title, person, body, disclaimer, legal;
	Button sendEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		initializeVars();
		sendEmail.setOnClickListener(this);
	}

	private void initializeVars() {
		emailAddress = (EditText) findViewById(R.id.et_emailAddress);
		emailTitle = (EditText) findViewById(R.id.et_emailTitle);
		personsName = (EditText) findViewById(R.id.et_personsName);
		emailBody = (EditText) findViewById(R.id.et_emailBody);
		companyDisclaimer = (EditText) findViewById(R.id.et_companyDisclaimer);
		legalStuff = (EditText) findViewById(R.id.et_legalStuff);
		sendEmail = (Button) findViewById(R.id.bSendEmail);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		convertEditTextVars();
		String emailAdd[] = { address };
		String message = "Hello " 
						 + person
						 + "\nI am writing to you to say "
						 + body
						 + "\nOur company says: "
						 + disclaimer
						 + "\nSome mumbo jumbo " +
						 legal;
		
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailAdd);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
		startActivity(emailIntent);
	}

	private void convertEditTextVars() {
		address = emailAddress.getText().toString();
		title = emailTitle.getText().toString();
		person = personsName.getText().toString();
		body = emailBody.getText().toString();
		disclaimer = companyDisclaimer.getText().toString();
		legal = legalStuff.getText().toString();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}
