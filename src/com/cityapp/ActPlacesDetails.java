package com.cityapp;

import com.db.TblPlaces;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActPlacesDetails extends Activity implements OnClickListener {

	TextView txtName, txtPhone, txtAddress;
	Button btnDelete, btnEdit;
	private String name;
	private String typeid;
	private String tdid;
	private String address;
	private String phone;
	private String email;
	private TextView txtEmail;
	
	private String tel="";
	private String id=""; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_placesdetail);
		txtName = (TextView) findViewById(R.id.txtName);
		txtPhone = (TextView) findViewById(R.id.txtPhone);
		txtEmail= (TextView) findViewById(R.id.txtEmail);
		txtAddress = (TextView) findViewById(R.id.txtAddress);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnEdit = (Button) findViewById(R.id.btnEdit);
		btnEdit.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
		if(CityApp.isAdmin){
			btnEdit.setVisibility(View.VISIBLE);
			btnDelete.setVisibility(View.VISIBLE);
		}
		Intent intent = getIntent();
		tdid = intent.getStringExtra("tdid");
		typeid = intent.getStringExtra("typeid");
		name = intent.getStringExtra("name");
		phone = intent.getStringExtra("phone");
		tel=phone;
		address = intent.getStringExtra("address");
		email = intent.getStringExtra("email");
		id=email;
	
		txtName.setText(name);
		txtPhone.setText(phone);
		txtEmail.setText(email);
		txtAddress.setText(address);
	}

	@Override
	public void onClick(View arg0) {
		if (arg0 == btnDelete) {
			TblPlaces.deletePlaces(tdid);
			finish();
		} else if (arg0 == btnEdit) {
			Intent intent = new Intent(ActPlacesDetails.this,
					ActAddPlaces.class);
			intent.putExtra("update", true);
			intent.putExtra("tdid", tdid);
			intent.putExtra("typeid", typeid);
			intent.putExtra("name", name);
			intent.putExtra("phone", phone);
			intent.putExtra("address", address);
			intent.putExtra("email", email);
			startActivity(intent);
			finish();
		}
	}
	public void Call(View v) {
		
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		//String t="tel:"+tel;
		callIntent.setData(Uri.parse("tel:"+tel));
		startActivity(callIntent);
		
	}
	public void sms(View v) {
		Intent callIntent = new Intent(getApplicationContext(), Sms.class);
		callIntent.putExtra("new_variable_name",tel);
		startActivity(callIntent);
		
	}
	public void Mail(View v) {
		
		Intent callIntent = new Intent(getApplicationContext(), Mail.class);
		callIntent.putExtra("new_variable_name",id);
		startActivity(callIntent);
		
		
	}
}
