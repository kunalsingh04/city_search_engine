package com.ketan.adapter;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ketan.cityapp.ActArea;
import com.ketan.cityapp.ActPlaces;
import com.ketan.model.ModelArea;
import com.ketan.model.ModelPlaces;

public class AdapterPlaces extends BaseAdapter {
	ActPlaces actPlaces;
	ArrayList<ModelPlaces> arrPlaces;

	public AdapterPlaces(ActPlaces actPlaces, ArrayList<ModelPlaces> arrPlaces) {
		this.arrPlaces = arrPlaces;
		this.actPlaces = actPlaces;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrPlaces.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		TextView text=new TextView(actPlaces);
		text.setPadding(5, 5, 5, 5);
		text.setText(arrPlaces.get(arg0).typeName);
		return text;
	}

}
