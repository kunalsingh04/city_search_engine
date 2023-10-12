package com.adapter;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.cityapp.ActArea;
import com.cityapp.CityApp;
import com.cityapp.R;
import com.db.TblArea;
import com.db.TblCity;
import com.model.ModelArea;

public class AdapterArea extends BaseAdapter {
	ActArea actArea;
	ArrayList<ModelArea> arrArea;

	public AdapterArea(ActArea actArea, ArrayList<ModelArea> arrArea) {
		this.arrArea = arrArea;
		this.actArea = actArea;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrArea.size();
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		convertView=actArea.getLayoutInflater().inflate(R.layout.row,parent, false);
		TextView text=(TextView) convertView.findViewById(R.id.row_text);
		Button btnDelete=(Button)convertView.findViewById(R.id.row_btnDelete);
		text.setText(arrArea.get(position).areaName);
		if (!CityApp.isAdmin) {
			btnDelete.setVisibility(View.GONE);
		}
		btnDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				TblArea.deleteArea(arrArea.get(position).areaId);
				actArea.displayArea();
			}
		});
		return convertView;
	}

}
