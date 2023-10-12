package com.adapter;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.cityapp.ActArea;
import com.cityapp.ActPlaces;
import com.cityapp.CityApp;
import com.cityapp.R;
import com.db.TblPlaces;
import com.db.TblType;
import com.model.ModelArea;
import com.model.ModelPlaces;

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
	public View getView(final int position, View convertView, ViewGroup parent) {
		convertView=actPlaces.getLayoutInflater().inflate(R.layout.row,parent, false);
		TextView text=(TextView) convertView.findViewById(R.id.row_text);
		Button btnDelete=(Button)convertView.findViewById(R.id.row_btnDelete);
		text.setText(arrPlaces.get(position).name);
		if (!CityApp.isAdmin) {
			btnDelete.setVisibility(View.GONE);
		}
		btnDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				TblPlaces.deletePlaces(arrPlaces.get(position).tdId);
				actPlaces.displayPlaces();
			}
		});
		return convertView;
	}

}
