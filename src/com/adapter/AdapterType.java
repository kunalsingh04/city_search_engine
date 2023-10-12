package com.adapter;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.cityapp.ActArea;
import com.cityapp.ActType;
import com.cityapp.CityApp;
import com.cityapp.R;
import com.db.TblArea;
import com.db.TblType;
import com.model.ModelArea;
import com.model.ModelType;

public class AdapterType extends BaseAdapter {
	ActType actType;
	ArrayList<ModelType> arrType;

	public AdapterType(ActType actType, ArrayList<ModelType> arrPlaces) {
		this.arrType = arrPlaces;
		this.actType = actType;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrType.size();
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
		convertView=actType.getLayoutInflater().inflate(R.layout.row,parent, false);
		TextView text=(TextView) convertView.findViewById(R.id.row_text);
		Button btnDelete=(Button)convertView.findViewById(R.id.row_btnDelete);
		text.setText(arrType.get(position).typeName);
		if (!CityApp.isAdmin) {
			btnDelete.setVisibility(View.GONE);
		}
		btnDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				TblType.deleteType(arrType.get(position).typeId);
				actType.displayType();
			}
		});
		return convertView;
	}

}
