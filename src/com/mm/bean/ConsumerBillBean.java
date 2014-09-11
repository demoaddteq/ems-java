package com.mm.bean;

import java.sql.Date;

public class ConsumerBillBean 
{
	private int consumerId;
	private int month;
	private int year;
	private float payableAmount;
	private float paidAmount;
	private Date dueDate;
	private Date paidDate;
	private String remarks;
	private int facilityId;
	public int getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(int consumerId) {
		this.consumerId = consumerId;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public int getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public float getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(float paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	public float getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(float payableAmount) {
		this.payableAmount = payableAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
}
