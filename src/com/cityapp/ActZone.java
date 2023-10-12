package com.cityapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class ActZone extends Activity {
	String cityId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_zone);
		cityId=getIntent().getStringExtra("cityid");

	}

	public void onButtonClick(View view) {
		Intent intent=new Intent(ActZone.this, ActArea.class);
		switch (view.getId()) {
		case R.id.zone_btnEast:
			intent.putExtra("zoneid", String.valueOf(1));
			break;
		case R.id.zone_btnWest:
			intent.putExtra("zoneid", String.valueOf(2));
			break;
		case R.id.zone_btnNorth:
			intent.putExtra("zoneid", String.valueOf(3));
			break;
		case R.id.zone_btnSouth:
			intent.putExtra("zoneid", String.valueOf(4));
			break;

		default:
			intent.putExtra("zoneid", String.valueOf(1));
			break;
		}
		intent.putExtra("cityid", cityId);
		startActivity(intent);

	}

}
