package com.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cityapp.CityApp;
import com.model.ModelArea;
import com.model.ModelPlaces;

public class TblPlaces {
	public static String TABLE = "typeDetail";
	public static String tdID = "tdID";
	public static String typeId = "typeId";
	public static String phone = "phone";
	public static String name = "name";
	public static String email = "email";
	public static String address = "address";

	public static ArrayList<ModelPlaces> selecPlaces(String typeid) {
		SQLiteDatabase db = CityApp.database;
		String query = "select * from " + TABLE + " where typeId=" + typeid;
		ArrayList<ModelPlaces> arrArea = null;
		Cursor c = db.rawQuery(query, null);
		if (c != null && c.moveToFirst()) {
			arrArea = new ArrayList<ModelPlaces>();
			do {
				ModelPlaces model = new ModelPlaces();
				model.tdId = c.getInt(c.getColumnIndex(tdID)) + "";
				model.typeId = c.getString(c.getColumnIndex(typeId));
				model.address = c.getString(c.getColumnIndex(address));
				model.phone = c.getString(c.getColumnIndex(phone));
				model.name = c.getString(c.getColumnIndex(name));
				model.email = c.getString(c.getColumnIndex(email));
				arrArea.add(model);
			} while (c.moveToNext());
			c.close();
		}
		return arrArea;
	}

	public static long insert(ModelPlaces model) {

		SQLiteDatabase db = CityApp.database;
		ContentValues values = new ContentValues();
		values.put(typeId, model.typeId);
		values.put(name, model.name);
		values.put(phone, model.phone);
		values.put(email, model.email);
		values.put(address, model.address);
		return db.insertOrThrow(TABLE, null, values);
	}
	
	public static long update(ModelPlaces model,String id) {
		SQLiteDatabase db = CityApp.database;
		ContentValues values = new ContentValues();
		values.put(typeId, model.typeId);
		values.put(name, model.name);
		values.put(phone, model.phone);
		values.put(email, model.email);
		values.put(address, model.address);
		 
		return db.update(TABLE, values, tdID+"="+id, null);
	}

	public static void deletePlaces(String id) {
		SQLiteDatabase db=CityApp.database;
		db.delete(TABLE,tdID + "=" + id, null);
	}
}