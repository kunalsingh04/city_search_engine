package com.cityapp;

import java.util.ArrayList;

import com.adapter.AdapterType;
import com.db.TblArea;
import com.db.TblType;
import com.model.ModelArea;
import com.model.ModelType;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class ActType extends Activity {
	ListView list;
	String areaId;
	private LinearLayout linear;
	private EditText editType;
	private String cityId;
	private String zoneId;
	private ArrayList<ModelType> arrTypes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_type);
		list = (ListView) findViewById(R.id.type_list);
		areaId = getIntent().getStringExtra("areaid");
		cityId = getIntent().getStringExtra("cityid");
		zoneId= getIntent().getStringExtra("zoneid");
		linear = (LinearLayout) findViewById(R.id.type_linear);
		editType = (EditText) findViewById(R.id.type_edit);
		if (CityApp.isAdmin) {
			linear.setVisibility(View.VISIBLE);
		} else {
			linear.setVisibility(View.GONE);
		}
		findViewById(R.id.type_btnAdd).setOnClickListener(
				new OnClickListener() {
					public void onClick(View arg0) {
						String type = editType.getText().toString().trim();
						if (type.length() <= 0) {
							Toast.makeText(ActType.this, "Please Enter Type",
									Toast.LENGTH_SHORT).show();
						} else {
							saveData(type, -1);
							editType.setText("");
							displayType();
						}
					}
				});

		displayType();
		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(ActType.this, ActPlaces.class);
				intent.putExtra("areaid", areaId);
				intent.putExtra("typeid", arrTypes.get(arg2).typeId);
				startActivity(intent);
			}
		});
		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				if (!CityApp.isAdmin) {
					return true;
				}
				final Dialog dialog = new Dialog(ActType.this);
				dialog.setContentView(R.layout.dialog_edit);
				final EditText edit = (EditText) dialog
						.findViewById(R.id.dialog_edit);
				edit.setText(arrTypes.get(arg2).typeName);
				dialog.findViewById(R.id.dialog_btnOk).setOnClickListener(
						new OnClickListener() {

							public void onClick(View arg0) {
								String type = edit.getText().toString().trim();
								if (type.length() <= 0) {
									Toast.makeText(ActType.this,
											"Please Enter Type",
											Toast.LENGTH_SHORT).show();
								} else {
									saveData(type, Integer.parseInt(arrTypes.get(arg2).typeId));
									dialog.dismiss();
								}
							}
						});
				dialog.findViewById(R.id.dialog_btnCancel).setOnClickListener(
						new OnClickListener() {

							public void onClick(View arg0) {
								dialog.dismiss();
							}
						});
				dialog.show();
				return true;
			}
		});
	}

	
	protected void saveData(String type, int id) {
		ModelType model = new ModelType();
		model.typeName = type;
		model.areaId = areaId;
		model.cityId = cityId;
		model.zoneId = zoneId;
		
		if (id == -1)
			TblType.inserType(model);
		else
			TblType.updateArea(model, String.valueOf(id) );

	}


	public void displayType() {
		arrTypes = TblType.selecTypes(areaId);
		if (arrTypes != null) {
			AdapterType adapter = new AdapterType(this, arrTypes);
			list.setAdapter(adapter);
		}
	}
}
