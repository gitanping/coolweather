package com.coolweather.app.util;

import android.text.TextUtils;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

public class Utility {
	/**
	 * 解析和处理服务器返回的省级数据
	 */
	public synchronized static boolean handleProvincesResponse(
			CoolWeatherDB coolWeatherDB, String response) {

		if (!TextUtils.isEmpty(response)) {
			String[] allProvices = response.split(",");
			if (allProvices != null && allProvices.length > 0) {
				for (String p : allProvices) {
					String[] array = p.split("\\|");
					Province provice = new Province();
					provice.setProviceCode(array[0]);
					provice.setProviceName(array[1]);
					coolWeatherDB.saveProvince(provice);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 解析和处理服务器返回的市级数据
	 */
	public synchronized static boolean handleCitiesResponse(
			CoolWeatherDB coolWeatherDB, String response, int provincedId){
		if(!TextUtils.isEmpty(response)){
			String[] allCities = response.split(",");
			if(allCities != null && allCities.length > 0){
				for(String c : allCities){
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProviceId(provincedId);
					coolWeatherDB.saveCities(city);
				}
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 解析和处理服务器返回的县级数据
	 */
	public synchronized static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,
			String response, int cityId){
		if(!TextUtils.isEmpty(response)){
			String[] counties = response.split(",");
			if(counties != null && counties.length > 0){
				for(String c : counties){
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}
