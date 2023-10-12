package com.ketan.db;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ketan.cityapp.CityApp;
import com.ketan.model.ModelPlaces;

public class TblPlaces {
	public static String TABLE = "type";
	public static String typeId = "typeId";
	public static String areaId = "areaId";
	public static String typeName = "typeName";
	public static String cityId = "cityId";
	public static String zoneId = "zoneId";

	public static ArrayList<ModelPlaces> selecPlaces(String areaId) {
		SQLiteDatabase db = CityApp.database;
		String query = "select * from " + TABLE+" where areaId="+areaId;
		ArrayList<ModelPlaces> arrArea = null;
		Cursor c = db.rawQuery(query, null);
		if (c != null && c.moveToFirst()) {
			arrArea = new ArrayList<ModelPlaces>();
			do {
				ModelPlaces model = new ModelPlaces();
				model.typeId = c.getInt(c.getColumnIndex(typeId)) + "";
				model.cityId = c.getString(c.getColumnIndex(cityId));
				model.areaId = c.getString(c.getColumnIndex(areaId));
				model.zoneId = c.getString(c.getColumnIndex(zoneId));
				model.typeName = c.getString(c.getColumnIndex(typeName));
				arrArea.add(model);
			} while (c.moveToNext());
			c.close();
		}
		return arrArea;
	}
}
