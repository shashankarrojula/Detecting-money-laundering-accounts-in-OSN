package com.voidmain.progaurd.entity;

import java.util.Date;

public class Friends {

	private int fid;
	private String requestedFrom;
	private String requestTo;
	private Date date;
	private String status="waiting";
	
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getRequestTo() {
		return requestTo;
	}
	public void setRequestTo(String requestTo) {
		this.requestTo = requestTo;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRequestedFrom() {
		return requestedFrom;
	}
	public void setRequestedFrom(String requestedFrom) {
		this.requestedFrom = requestedFrom;
	}
}
