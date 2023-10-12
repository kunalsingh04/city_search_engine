package com.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cityapp.CityApp;
import com.model.ModelType;

public class TblType {
	public static String TABLE = "type";
	public static String typeId = "typeId";
	public static String areaId = "areaId";
	public static String typeName = "typeName";
	public static String cityId = "cityId";
	public static String zoneId = "zoneId";

	public static ArrayList<ModelType> selecTypes(String areaid) {
		SQLiteDatabase db = CityApp.database;
		String query = "select * from " + TABLE + " where areaId=" + areaid;
		ArrayList<ModelType> arrArea = null;
		Cursor c = db.rawQuery(query, null);
		if (c != null && c.moveToFirst()) {
			arrArea = new ArrayList<ModelType>();
			do {
				ModelType model = new ModelType();
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

	public static long inserType(ModelType model) {
		SQLiteDatabase db = CityApp.database;
		ContentValues values = new ContentValues();
		values.put(typeName, model.typeName);
		values.put(areaId, model.areaId);
		values.put(cityId, model.cityId);
		values.put(zoneId, model.zoneId);
		return db.insertOrThrow(TABLE, null, values);

	}

	public static long updateArea(ModelType model, String id) {
		SQLiteDatabase db = CityApp.database;
		ContentValues values = new ContentValues();
		values.put(typeName, model.typeName);
		values.put(areaId, model.areaId);
		values.put(cityId, model.cityId);
		values.put(zoneId, model.zoneId);
		return db.update(TABLE,values, typeId+"="+id, null);
		
	}

	public static void deleteType(String id) {
		SQLiteDatabase db=CityApp.database;
		db.delete(TABLE,typeId + "=" + id, null);
	}
}
