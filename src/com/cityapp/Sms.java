package com.cityapp;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sms extends Activity {
	TextView mob;
	EditText sms;
	 String value="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		  value  = extras.getString("new_variable_name");
		}
		mob=(TextView)findViewById(R.id.textView22);
		sms=(EditText)findViewById(R.id.editText11);
		mob.setText(value);
		sms.setText("Wright Your Message");
		
		
		
	}
	@SuppressWarnings("deprecation")
	public void Send(View v) {
		String sms1=sms.getText().toString().trim();
		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(value, null, sms1, null, null);
			Toast.makeText(getApplicationContext(), "SMS Sent!",Toast.LENGTH_LONG).show();
			finish();
		  } catch (Exception e) {
			Toast.makeText(getApplicationContext(),
				"SMS faild, please try again later!",
				Toast.LENGTH_LONG).show();
			e.printStackTrace();
		  }
	}

}
