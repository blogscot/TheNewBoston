package com.diamond.iain.thenewboston;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity implements View.OnClickListener {

	Button checkCommand;
	ToggleButton passToggle;
	EditText input;
	TextView display;

	private void setUpTextPlay() {
		checkCommand = (Button) findViewById(R.id.bResults);
		passToggle = (ToggleButton) findViewById(R.id.tbPassword);
		input = (EditText) findViewById(R.id.etCommands);
		display = (TextView) findViewById(R.id.tvResults);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);

		setUpTextPlay();
		
		passToggle.setOnClickListener(this);
		checkCommand.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		
		case R.id.bResults:
			String check = input.getText().toString();
			display.setText(check);
			
			switch (check) {
			case "left":
				display.setGravity(Gravity.LEFT);
				break;
			case "right":
				display.setGravity(Gravity.RIGHT);
				break;
			case "center":
				display.setGravity(Gravity.CENTER);
				break;
			case "blue":
				display.setTextColor(Color.BLUE);
				break;
			case "rand":
				Random rand = new Random();
				display.setText("Iain says Hi!");
				display.setTextSize(rand.nextInt(40));
				display.setTextColor(Color.rgb(rand.nextInt(255),
						rand.nextInt(255), rand.nextInt(255)));
				break;
			default:
				display.setText("invalid");
				display.setGravity(Gravity.CENTER);
			}
			break;
		
		case R.id.tbPassword:
			if (passToggle.isChecked()) {
				input.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
			} else {
				input.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			break;
		default:
		}
	}
}

