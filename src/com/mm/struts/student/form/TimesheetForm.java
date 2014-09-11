package com.mm.struts.student.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class TimesheetForm extends ActionForm{
	
	private String fullName;
	private String fromDate;
	private String toDate;
	private int hours;
	private int wid;
	private FormFile theFile;
	
	
	public FormFile getTheFile() {
		return theFile;
	}
	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	

}
