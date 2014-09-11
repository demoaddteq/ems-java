package com.mm.struts.corpo.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import com.mm.struts.corpo.form.AddMemberForm;

public class VendorForm extends ValidatorForm{
	
	
	private static final long serialVersionUID = 1L;
	private String company;
	private String address;
	private String address2;
	private String city;
	private String state;
	private int zip;
	private int ein;
	private int vid;
	
	
	
	
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	
	public int getEin() {
		return ein;
	}
	public void setEin(int ein) {
		this.ein = ein;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String add1) {
		this.address = add1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String add2) {
		this.address2 = add2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	
	
	
	
	
	

}
