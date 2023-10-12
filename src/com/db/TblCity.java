package com.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cityapp.CityApp;
import com.model.ModelCity;

public class TblCity {
	public static String TABLE = "city";
	public static String cityId = "cityId";
	public static String cityName = "cityName";

	public static ArrayList<ModelCity> selecAllCity() {
		SQLiteDatabase db = CityApp.database;
		String query = "select * from " + TABLE;
		ArrayList<ModelCity> arrCity = null;
		Cursor c = db.rawQuery(query, null);
		if (c != null && c.moveToFirst()) {
			arrCity = new ArrayList<ModelCity>();
			do {
				ModelCity model = new ModelCity();
				model.cityId = c.getInt(c.getColumnIndex(cityId)) + "";
				model.cityName = c.getString(c.getColumnIndex(cityName));
				arrCity.add(model);
			} while (c.moveToNext());
			c.close();
		}
		return arrCity;
	}

	public static long inserCity(String city) {
		SQLiteDatabase db = CityApp.database;
		ContentValues values = new ContentValues();
		values.put(cityName, city);
		return db.insertOrThrow(TABLE, null, values);
	}

	public static void deleteCity(String id) {
		SQLiteDatabase db = CityApp.database;
		db.delete(TABLE, cityId + "=" + id, null);
	}

	public static long updateCity(String city, String id) {
		SQLiteDatabase db = CityApp.database;
		ContentValues values = new ContentValues();
		values.put(cityName, city);
		return db.update(TABLE, values, cityId+"="+id, null);
	}
}
