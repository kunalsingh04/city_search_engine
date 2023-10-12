package com.cityapp;

import com.db.TblPlaces;
import com.model.ModelPlaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActAddPlaces extends Activity {

	EditText editName, editPhone, editAddress,editEmail;
	boolean isUpdate = false;
	private String address;
	private String phone;
	private String name;
	private String typeid;
	private String tdid;
	Button btnSave;
	private String email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_addplaces);
		editName = (EditText) findViewById(R.id.editName);
		editPhone = (EditText) findViewById(R.id.editPhone);
		editAddress = (EditText) findViewById(R.id.editAddress);
		editEmail=(EditText)findViewById(R.id.editEmail);
		btnSave=(Button)findViewById(R.id.btnSave);
		
		Intent intent = getIntent();
		isUpdate = intent.getBooleanExtra("update", false);
		typeid = intent.getStringExtra("typeid");
		if (isUpdate) {
			tdid = intent.getStringExtra("tdid");
			typeid = intent.getStringExtra("typeid");
			name = intent.getStringExtra("name");
			phone = intent.getStringExtra("phone");
			address = intent.getStringExtra("address");
			email=intent.getStringExtra("email");

			editName.setText(name);
			editPhone.setText(phone);
			editEmail.setText(email);
			editAddress.setText(address);
		}
		btnSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				name = editName.getText().toString().trim();
				phone = editPhone.getText().toString().trim();
				address = editAddress.getText().toString().trim();
				email=editEmail.getText().toString().trim();
				if (name.length() <= 0) {
					Toast.makeText(ActAddPlaces.this, "Please Enter name",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (phone.length() <= 0) {
					Toast.makeText(ActAddPlaces.this, "Please Enter phone number",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (email.length() <= 0) {
					Toast.makeText(ActAddPlaces.this, "Please Enter Email",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if (address.length() <= 0) {
					Toast.makeText(ActAddPlaces.this, "Please Enter address",
							Toast.LENGTH_SHORT).show();
					return;
				}
				saveData();
				finish();
			}
		});
		
	}

	private void saveData() {
		ModelPlaces model = new ModelPlaces();
		model.tdId = tdid;
		model.typeId = typeid;
		model.name = name;
		model.phone = phone;
		model.email=email;
		model.address = address;
		if (isUpdate) {
			TblPlaces.update(model, tdid);
		} else {
			TblPlaces.insert(model);
			
		}
	}
}
