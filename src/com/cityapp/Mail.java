package com.cityapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
 
public class Mail extends Activity {
 
	Button buttonSend;
	EditText textTo;
	EditText textSubject;
	EditText textMessage;
	String value="";
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mail);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		  value  = extras.getString("new_variable_name");
		}
		
 
		buttonSend = (Button) findViewById(R.id.buttonSend);
		textTo = (EditText) findViewById(R.id.editTextTo);
		textSubject = (EditText) findViewById(R.id.editTextSubject);
		textMessage = (EditText) findViewById(R.id.editTextMessage);
        textTo.setText(value);
		buttonSend.setOnClickListener(new OnClickListener() {
 
			public void onClick(View v) {
 
			  String to = textTo.getText().toString();
			  String subject = textSubject.getText().toString();
			  String message = textMessage.getText().toString();
 
			  Intent email = new Intent(Intent.ACTION_SEND);
			  email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
			  
			  email.putExtra(Intent.EXTRA_SUBJECT, subject);
			  email.putExtra(Intent.EXTRA_TEXT, message);
 
			  //need this to prompts email client only
			  email.setType("message/rfc822");
 
			  startActivity(Intent.createChooser(email, "Choose an Email client :"));
			  
 
			}
		});
	}
}