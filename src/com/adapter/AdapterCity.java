package com.adapter;

import java.util.ArrayList;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.cityapp.ActCity;
import com.cityapp.CityApp;
import com.cityapp.R;
import com.db.TblCity;
import com.model.ModelCity;

public class AdapterCity extends BaseAdapter {
	ActCity actCity;
	ArrayList<ModelCity> arrCity;

	public AdapterCity(ActCity actCity, ArrayList<ModelCity> arrCity) {
		this.arrCity = arrCity;
		this.actCity = actCity;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrCity.size();
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
		convertView=actCity.getLayoutInflater().inflate(R.layout.row,parent, false);
		TextView text=(TextView) convertView.findViewById(R.id.row_text);
		Button btnDelete=(Button)convertView.findViewById(R.id.row_btnDelete);
		text.setText(arrCity.get(position).cityName);
		if (!CityApp.isAdmin) {
			btnDelete.setVisibility(View.GONE);
		}
		btnDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				TblCity.deleteCity(arrCity.get(position).cityId);
				actCity.displayCity();
			}
		});
		return convertView;
	}

}
