package com.voidmain.progaurd.entity;

public class Event {

	private int eId;
	private String name;
	private String description;
	private String company;
	private float price;
	
	public int geteId() {
		return eId;
	}
	public void seteId(int eId) {
		this.eId = eId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price =price;
	}
	
	public void setPrice(String price) {
		this.price =Float.parseFloat(price);
	}
}
