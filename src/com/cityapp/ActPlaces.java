package com.cityapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.adapter.AdapterPlaces;
import com.db.TblPlaces;
import com.model.ModelPlaces;

public class ActPlaces extends Activity {
	ListView list;
	String areaId;
	private String typeId;
	Button btnAdd;
	ArrayList<ModelPlaces> arrPlaces;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_places);
		list = (ListView) findViewById(R.id.places_list);
		btnAdd=(Button)findViewById(R.id.btnAdd);
		if(CityApp.isAdmin){
			btnAdd.setVisibility(View.VISIBLE);
		}
		areaId = getIntent().getStringExtra("areaid");
		typeId = getIntent().getStringExtra("typeid");
		
		
		btnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ActPlaces.this,
						ActAddPlaces.class);
				intent.putExtra("typeid", typeId);
				intent.putExtra("update", false);
				startActivity(intent);
			}
		});
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(ActPlaces.this,
						ActPlacesDetails.class);
				intent.putExtra("tdid", arrPlaces.get(arg2).tdId);
				intent.putExtra("typeid", arrPlaces.get(arg2).typeId);
				intent.putExtra("name", arrPlaces.get(arg2).name);
				intent.putExtra("phone", arrPlaces.get(arg2).phone);
				intent.putExtra("address", arrPlaces.get(arg2).address);
				intent.putExtra("email", arrPlaces.get(arg2).email);
				startActivity(intent);
			}
		});
		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (!CityApp.isAdmin) {
					return true;
				}
				Intent intent = new Intent(ActPlaces.this,
						ActAddPlaces.class);
				intent.putExtra("update", true);
				intent.putExtra("tdid", arrPlaces.get(arg2).tdId);
				intent.putExtra("typeid", arrPlaces.get(arg2).typeId);
				intent.putExtra("name", arrPlaces.get(arg2).name);
				intent.putExtra("phone", arrPlaces.get(arg2).phone);
				intent.putExtra("address", arrPlaces.get(arg2).address);
				intent.putExtra("email", arrPlaces.get(arg2).email);
				startActivity(intent);
				return true;
			}
		});
	}
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	displayPlaces();
}
	public void displayPlaces() {
		arrPlaces = TblPlaces.selecPlaces(typeId);
		if (arrPlaces != null) {
			AdapterPlaces adapter = new AdapterPlaces(this, arrPlaces);
			list.setAdapter(adapter);
		}
	}
}
