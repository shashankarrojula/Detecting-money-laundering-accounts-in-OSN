package com.voidmain.progaurd.entity;

public class Service {

	private int sId;
	private String name;
	private String description;
	private float price;
	
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price =Float.parseFloat(price);
	}
}
