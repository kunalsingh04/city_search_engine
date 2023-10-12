package com.cityapp;

import com.db.DbHelper;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class CityApp extends Application {
	public static SQLiteDatabase database;
	public static boolean isAdmin=false;
	DbHelper dbHelper;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		dbHelper=new DbHelper(this, "cityapp.sqlite");
		database=dbHelper.openDataBase();
	}

}
