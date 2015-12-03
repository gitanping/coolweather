package com.coolweather.app.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

public class CoolWeatherDB {
	/**
	 * 数据库名
	 */
	public static final String DB_NAME = "cool_weather";
	/**
	 * 数据库版本
	 */
	public static final int VERSION = 1;

	private static CoolWeatherDB coolWeatherDB;

	private SQLiteDatabase db;

	/**
	 * 将构造方法私有化
	 * 
	 * @param context
	 */
	private CoolWeatherDB(Context context) {
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,
				DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}
	
	public synchronized static CoolWeatherDB getInstance(Context context){
		if(coolWeatherDB == null){
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}
	/**
	 * 将Provice实例存储到数据库
	 * @param provice
	 */
	public void saveProvince(Province provice){
		if(provice != null){
			ContentValues values = new ContentValues();
			values.put("provice_name", provice.getProviceName());
			values.put("provice_code", provice.getProviceCode());
			db.insert("Provice", null, values);
		}
	}
	
	/**
	 * 从数据库读取全国所有的省份信息
	 */
	public List<Province> loadProvice(){
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query("Provice", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Province provice = new Province();
				provice.setId(cursor.getInt(cursor.getColumnIndex("id")));
				provice.setProviceName(cursor.getString(cursor.getColumnIndex("provice_name")));
				provice.setProviceCode(cursor.getString(cursor.getColumnIndex("provice_code")));
				list.add(provice);
				
			}while(cursor.moveToNext());
		}
		return list;
	}
	
	/**
	 * 将City实例存储到数据库
	 */
	public void saveCities(City city){
		if(city != null){
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("provice_id", city.getProviceId());
			db.insert("City", null, values);
		}
	}
	/**
	 * 从数据库读取某省下所有的城市信息
	 */
	public List<City> loadCity(int proviceId){
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City", null, "provice_id = ?", new String[]{String.valueOf(proviceId)}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setProviceId(proviceId);
				list.add(city);
			}while(cursor.moveToNext());
		}
		return list;
	}
	
	/**
	 * 将Country实例存储到数据库
	 */
	public void saveCounty(County county){
		if(county != null){
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert("County", null, values);
		}
	}
	
	/**
	 * 从数据库读取某城市下所有的县信息
	 */
	public List<County> loadCounties(int cityId){
		List<County> list = new ArrayList<County>();
		Cursor cursor = db.query("County", null, "city_id = ?", new String[]{String.valueOf(cityId)}, null, null, null);
		if(cursor.moveToFirst()){
			do{
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
				county.setCityId(cityId);
				list.add(county);
			}while(cursor.moveToNext());
		}
		return list;
	}
	
	
	
}
