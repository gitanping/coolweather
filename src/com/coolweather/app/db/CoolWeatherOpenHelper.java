package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Provice,City,County���ű�Ľ���
 * @author xu
 *
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
	/**
	 * Provice�������
	 */
	public static final String CREATE_PROVICE = "create table Provice("
			+"id integer primary key autoincrement,"
			+"provice_name text,"
			+"provice_code text)";
	/**
	 * City�������
	 */
	public static final String CREATE_CITY = "create table City("
			+"id integer primary key autoincrement,"
			+"city_name text,"
			+"city_code text,"
			+"provice_id integer)";
	/**
	 * County�������
	 */
	public static final String CREATE_COUNTY = "create table County("
			+"id integer primary key autoincrement,"
			+"county_name text,"
			+"county_code text,"
			+"city_id integer)";
	
	public CoolWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVICE);  //����Province��
		db.execSQL(CREATE_CITY);   //����City��
		db.execSQL(CREATE_COUNTY); //����County��
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
