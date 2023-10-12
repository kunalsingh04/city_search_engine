package com.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cityapp.CityApp;
import com.model.ModelArea;
import com.model.ModelCity;

public class TblArea {
	public static String TABLE = "area";
	public static String areaId = "areaId";
	public static String areaName = "areaName";
	public static String cityId = "cityId";
	public static String zoneId = "zoneId";

	public static ArrayList<ModelArea> selecArea(String cityid, String zoneid) {
		SQLiteDatabase db = CityApp.database;
		String query = "select * from " + TABLE + " where cityId=" + cityid
				+ " and zoneId=" + zoneid;
		ArrayList<ModelArea> arrArea = null;
		Cursor c = db.rawQuery(query, null);
		if (c != null && c.moveToFirst()) {
			arrArea = new ArrayList<ModelArea>();
			do {
				ModelArea model = new ModelArea();
				model.areaId = c.getInt(c.getColumnIndex(areaId)) + "";
				model.cityId = c.getString(c.getColumnIndex(cityId));
				model.areaName = c.getString(c.getColumnIndex(areaName));
				model.zoneId = c.getString(c.getColumnIndex(zoneId));
				arrArea.add(model);
			} while (c.moveToNext());
			c.close();
		}
		return arrArea;
	}

	public static long inserArea(ModelArea model) {
		SQLiteDatabase db = CityApp.database;
		ContentValues values = new ContentValues();
		values.put(areaName, model.areaName);
		values.put(cityId, model.cityId);
		values.put(zoneId, model.zoneId);
		return db.insertOrThrow(TABLE, null, values);
	}

	public static long updateArea(ModelArea model,String id) {
		SQLiteDatabase db = CityApp.database;
		ContentValues values = new ContentValues();
		values.put(areaName, model.areaName);
		values.put(cityId, model.cityId);
		values.put(zoneId, model.zoneId);
		 
		return db.update(TABLE, values, areaId+"="+id, null);
	}

	public static void deleteArea(String id) {
		SQLiteDatabase db=CityApp.database;
		db.delete(TABLE,areaId + "=" + id, null);
	}
}
