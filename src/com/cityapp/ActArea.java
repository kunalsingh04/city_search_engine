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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.adapter.AdapterArea;
import com.db.TblArea;
import com.model.ModelArea;

public class ActArea extends Activity {
	String cityid, zoneid;
	private ListView list;
	AdapterArea adapter;
	private ArrayList<ModelArea> arrArea;
	private LinearLayout linear;
	private EditText editArea;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_area);
		cityid = getIntent().getStringExtra("cityid");
		zoneid = getIntent().getStringExtra("zoneid");

		list = (ListView) findViewById(R.id.area_list);
		linear = (LinearLayout) findViewById(R.id.area_linear);
		editArea = (EditText) findViewById(R.id.area_edit);
		if (CityApp.isAdmin) {
			linear.setVisibility(View.VISIBLE);
		} else {
			linear.setVisibility(View.GONE);
		}
		findViewById(R.id.area_btnAdd).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						String area = editArea.getText().toString().trim();

						if (area.length() <= 0) {
							Toast.makeText(ActArea.this, "Please Enter Area",
									Toast.LENGTH_SHORT).show();
						} else {
							saveData(area, -1);
							editArea.setText("");
							displayArea();
						}

					}
				});

		displayArea();
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(ActArea.this, ActType.class);
				intent.putExtra("areaid", arrArea.get(arg2).areaId);
				intent.putExtra("cityid", cityid);
				intent.putExtra("zoneid", zoneid);
				startActivity(intent);

			}
		});

		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				if (!CityApp.isAdmin) {
					return true;
				}
				final Dialog dialog = new Dialog(ActArea.this);
				dialog.setContentView(R.layout.dialog_edit);
				final EditText edit = (EditText) dialog
						.findViewById(R.id.dialog_edit);
				edit.setText(arrArea.get(arg2).areaName);
				dialog.findViewById(R.id.dialog_btnOk).setOnClickListener(
						new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								String area = edit.getText().toString().trim();
								if (area.length() <= 0) {
									Toast.makeText(ActArea.this,
											"Please Enter Area",
											Toast.LENGTH_SHORT).show();
								} else {
									saveData(area, Integer.parseInt(arrArea
											.get(arg2).areaId));
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

	protected void saveData(String area, int id) {
		ModelArea model = new ModelArea();
		model.areaName = area;

		model.cityId = cityid;

		model.zoneId = zoneid;
		if (id == -1)
			TblArea.inserArea(model);
		else
			TblArea.updateArea(model, String.valueOf(id) );

	}

	public void displayArea() {
		arrArea = TblArea.selecArea(cityid, zoneid);

		if (arrArea != null) {
			adapter = new AdapterArea(this, arrArea);
			list.setAdapter(adapter);
		}
	}
}
