package com.mm.struts.student.form;

import org.apache.struts.action.ActionForm;

public class CreateInvoiceForm extends ActionForm{
	
	private int wid;
	private int tsId;
	private String vendorName;
	private String consultantName;
	private String periodStartDate;
	private String periodEndDate;
	private int hours;
	

	public int getTsId() {
		return tsId;
	}
	public void setTsId(int tsId) {
		this.tsId = tsId;
	}
	public String getConsultantName() {
		return consultantName;
	}
	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public String getPeriodEndDate() {
		return periodEndDate;
	}
	public void setPeriodEndDate(String periodEndDate) {
		this.periodEndDate = periodEndDate;
	}
	public String getPeriodStartDate() {
		return periodStartDate;
	}
	public void setPeriodStartDate(String periodStartDate) {
		this.periodStartDate = periodStartDate;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	
	

}
