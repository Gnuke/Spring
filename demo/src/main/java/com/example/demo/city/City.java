package com.example.demo.city;

public class City {
	private int id;
	private String name;
	private String countryCode;
	private String district;
	private int population;
	
	public City() {
		// TODO Auto-generated constructor stub
	}
	
	public City(int id, String name, String countryCode, String district,  int population) {
		this.countryCode = countryCode;
		this.district = district;
		this.id = id;
		this.name = name;
		this.population = population;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "City [countryCode=" + countryCode + ", district=" + district + ", id=" + id + ", name=" + name
				+ ", population=" + population + "]";
	}
	
}
