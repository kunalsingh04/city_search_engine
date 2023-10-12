package com.cityapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActHome extends Activity {
	RadioGroup rgroup;
	String password;
	SharedPreferences pref;
	SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_home);

		pref = PreferenceManager.getDefaultSharedPreferences(this);
		editor = pref.edit();
		rgroup = (RadioGroup) findViewById(R.id.home_rgroup);
		final EditText editPass = (EditText) findViewById(R.id.home_editPass);

		findViewById(R.id.home_btnNext).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						switch (rgroup.getCheckedRadioButtonId()) {
						case R.id.home_rbtnAdmin:
							CityApp.isAdmin = true;
							password = pref.getString("adminpass", "admin1234");
							break;
						case R.id.home_rbtnUser:
							CityApp.isAdmin = false;
							password = pref.getString("userpass", "user123");
							break;
						}
						String enteredPass = editPass.getText().toString();
						if (enteredPass.equals(password)) {
							startActivity(new Intent(ActHome.this,
									ActCity.class));
						} else {
							Toast.makeText(ActHome.this, "Invalid Password",
									Toast.LENGTH_LONG).show();
							editPass.setText("");
						}
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.act_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==R.id.menu_changepass){
			changePassword();
		}
		return super.onOptionsItemSelected(item);
	}

	private void changePassword() {
		final Dialog dialog = new Dialog(ActHome.this);
		dialog.setTitle("Change Password");
		dialog.setContentView(R.layout.dialog_changepass);
		final EditText editCurrent = (EditText) dialog
				.findViewById(R.id.dialog_pass_editPassCurrent);
		final EditText editNew = (EditText) dialog
				.findViewById(R.id.dialog_pass_editPassNew);
		final Button btnOk = (Button) dialog
				.findViewById(R.id.dialog_pass_btnOk);
		final Button btnCancel = (Button) dialog
				.findViewById(R.id.dialog_pass_btnCancel);
		btnOk.setOnClickListener(new OnClickListener() {
			
		

			@Override
			public void onClick(View arg0) {
				String savedPass = "";
				String currentPass = "";
				String newPass = "";
				switch (rgroup.getCheckedRadioButtonId()) {
				case R.id.home_rbtnAdmin:

					savedPass = pref.getString("adminpass", "admin123");
					break;
				case R.id.home_rbtnUser:
					CityApp.isAdmin = false;
					savedPass = pref.getString("userpass", "user123");
					break;
				}
				newPass = editNew.getText().toString().trim();
				currentPass = editCurrent.getText().toString().trim();
				if (currentPass.equals(savedPass)) {
					if (newPass.length() > 0) {
						if (CityApp.isAdmin) {
							editor.putString("adminpass", newPass);
							editor.commit();
						} else {
							editor.putString("userpass", newPass);
							editor.commit();
						}
						dialog.dismiss();
					} else {
						Toast.makeText(ActHome.this,
								"Please enter New Password", Toast.LENGTH_LONG)
								.show();

					}
				} else {
					Toast.makeText(ActHome.this, "Invalid Current Password",
							Toast.LENGTH_LONG).show();
					editCurrent.setText("");

				}
				
			}
		});
		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}
}
