package com.ketan.cityapp;

import java.util.ArrayList;

import com.ketan.adapter.AdapterPlaces;
import com.ketan.db.TblPlaces;
import com.ketan.model.ModelPlaces;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ActPlaces extends Activity {
	ListView list;
	String areaId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_places);
		list = (ListView) findViewById(R.id.places_list);

		ArrayList<ModelPlaces> arrPlaces = TblPlaces.selecPlaces(areaId);
		if (arrPlaces != null) {
			AdapterPlaces adapter = new AdapterPlaces(this, arrPlaces);
			list.setAdapter(adapter);
		}
	}
}
