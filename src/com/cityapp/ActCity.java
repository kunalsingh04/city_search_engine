package com.cityapp;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.adapter.AdapterCity;
import com.db.TblArea;
import com.db.TblCity;
import com.model.ModelArea;
import com.model.ModelCity;

public class ActCity extends Activity {

	private ListView listCity;
	private ArrayList<ModelCity> arrCity;
	ArrayList<String> cityName;
	LinearLayout linear;
	EditText editCity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_city);
		listCity = (ListView) findViewById(R.id.city_listview);
		linear = (LinearLayout) findViewById(R.id.city_linear);
		editCity = (EditText) findViewById(R.id.city_edit);
		if (CityApp.isAdmin) {
			linear.setVisibility(View.VISIBLE);
		} else {
			linear.setVisibility(View.GONE);
		}
		findViewById(R.id.city_btnAdd).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						
					

						String city = editCity.getText().toString().trim();
						if (city.length() <= 0) {
							Toast.makeText(ActCity.this, "Please Enter City",
									Toast.LENGTH_SHORT).show();
						} else {
							saveData(city, -1);
							
							editCity.setText("");
							displayCity();
						}
					}
				});
		findViewById(R.id.city_btn).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

			}
		});
		displayCity();
		listCity.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(ActCity.this, ActZone.class);
				intent.putExtra("cityid", arrCity.get(arg2).cityId);
				startActivity(intent);
			}
		});
		
		listCity.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				if (!CityApp.isAdmin) {
					return true;
				} 
				final Dialog dialog = new Dialog(ActCity.this);
				dialog.setContentView(R.layout.dialog_edit);
				final EditText edit = (EditText) dialog
						.findViewById(R.id.dialog_edit);
				edit.setText(arrCity.get(arg2).cityName);
				dialog.findViewById(R.id.dialog_btnOk).setOnClickListener(
						new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								String area = edit.getText().toString().trim();
								if (area.length() <= 0) {
									Toast.makeText(ActCity.this,
											"Please Enter City",
											Toast.LENGTH_SHORT).show();
								} else {
									saveData(area, Integer.parseInt(arrCity
											.get(arg2).cityId));
									dialog.dismiss();
								}
							}
						});
				dialog.findViewById(R.id.dialog_btnCancel).setOnClickListener(
						new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								dialog.dismiss();
							}
						});
				dialog.show();
				return true;
			}
		});
	}

	protected void saveData(String city, int id) {
		
		if (id == -1)
			TblCity.inserCity(city);
		else
			TblCity.updateCity(city, String.valueOf(id) );

	}

	public void displayCity() {
		arrCity = TblCity.selecAllCity();
		if (arrCity != null) {
			cityName = new ArrayList<String>();
			for (ModelCity model : arrCity) {
				cityName.add(model.cityName);
			}

			listCity.setAdapter(new AdapterCity(ActCity.this, arrCity));

		}
	}
}
