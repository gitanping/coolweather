package com.coolweather.app.model;

public class City {
	private int id;
	private String cityName;
	private String cityCode;
	private int proviceId;
	public int getId() {
		return id;
	}
	public String getCityName() {
		return cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public int getProviceId() {
		return proviceId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public void setProviceId(int proviceId) {
		this.proviceId = proviceId;
	}
	
}
