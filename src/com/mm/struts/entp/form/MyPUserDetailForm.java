package com.mm.struts.entp.form;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
/** 
 * MyEclipse Struts
 * Creation date: 06-20-2007
 * 
 * XDoclet definition:
 * @struts.entp.form name="MyprofileForm"
 */
public class MyPUserDetailForm extends ValidatorForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** text property */
	
	private String loginName = "";	
	private String fname = "";	
	private String lname = "";	
	private String address = "";
	private String city = "";	
	private String state = "";	
	private String country = "";	
	private String zip = "";	
	private String email = "";	
	private String txtPPrefix = "";
	private String txtUACPrefix = "";
	private String txtPhone = "";	
	private String txtMobile = "";
	private String mobile = "";
	
	private String rid = "";
	
	public String getRid() {
		return this.rid;
	}
	/** 
	 * Set the rid.
	 * @param rid The rid to set
	 */
	public void setRid(String rid) {
		this.rid = rid;
	}
	
	
	
	private String photoPath ="";
	
	
	public String getPhotoPath() {
		return this.photoPath;
	}
	/** 
	 * Set the userid.
	 * @param userid The userid to set
	 */
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	
	
	/** text property */
	private String otherCountry = "";
	/** 
	 * Returns the otherCountry.
	 * @return String
	 */
	public String getOtherCountry() {
		return this.otherCountry;
	}
	/** 
	 * Set the otherCountry.
	 * @param otherCountry The otherCountry to set
	 */
	public void setOtherCountry(String otherCountry) {
		this.otherCountry = otherCountry;
	}
	
	
	/** text property */
	private String otherState = "";
	
	/** 
	 * Returns the otherState.
	 * @return String
	 */
	public String getOtherState() {
		return this.otherState;
	}
	/** 
	 * Set the otherState.
	 * @param otherState The otherState to set
	 */
	public void setOtherState(String otherState) {
		this.otherState = otherState;
	}
	
	
	
	/** text property */
	private String otherCity = "";
	
	/** 
	 * Returns the otherCity.
	 * @return String
	 */
	public String getOtherCity() {
		return this.otherCity;
	}
	/** 
	 * Set the otherCity.
	 * @param otherCity The otherCity to set
	 */
	public void setOtherCity(String otherCity) {
		this.otherCity = otherCity;
	}
	
	
	/** 
	 * Returns the loginName.
	 * @return String
	 */
	public String getLoginName() {
		return this.loginName;
	}
	/** 
	 * Set the loginName.
	 * @param loginName The loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	/** 
	 * Returns the fname.
	 * @return String
	 */
	public String getFname() {
		return this.fname;
	}
	/** 
	 * Set the fname.
	 * @param fname The fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	/** 
	 * Returns the lname.
	 * @return String
	 */
	public String getLname() {
		return this.lname;
	}
	/** 
	 * Set the lname.
	 * @param lname The lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	/** 
	 * Returns the address.
	 * @return String
	 */
	public String getAddress() {
		return this.address;
	}
	/** 
	 * Set the address.
	 * @param address The address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/** 
	 * Returns the city.
	 * @return String
	 */
	public String getCity() {
		return this.city;
	}
	/** 
	 * Set the city.
	 * @param city The city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/** 
	 * Returns the state.
	 * @return String
	 */
	public String getState() {
		return this.state;
	}
	/** 
	 * Set the state.
	 * @param state The state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/** 
	 * Returns the ucountry.
	 * @return String
	 */
	public String getCountry() {
		return this.country;
	}
	/** 
	 * Set the ucountry.
	 * @param ucountry The ucountry to set
	 */
	public void setCountry(String ucountry) {
		this.country = ucountry;
	}
	
	/** 
	 * Returns the zip.
	 * @return String
	 */
	public String getZip() {
		return this.zip;
	}
	/** 
	 * Set the zip.
	 * @param zip The zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	/** 
	 * Returns the email.
	 * @return String
	 */
	public String getEmail() {
		return this.email;
	}
	/** 
	 * Set the email.
	 * @param email The email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	/** 
	 * Returns the txtPPrefix.
	 * @return String
	 */
	public String getTxtPPrefix() {
		return this.txtPPrefix;
	}
	/** 
	 * Set the txtPPrefix.
	 * @param txtPPrefix The txtPPrefix to set
	 */
	public void setTxtPPrefix(String txtPPrefix) {
		this.txtPPrefix = txtPPrefix;
	}
	/** 
	 * Returns the txtUACPrefix.
	 * @return String
	 */
	public String getTxtUACPrefix() {
		return this.txtUACPrefix;
	}
	/** 
	 * Set the txtUACPrefix.
	 * @param txtUACPrefix The txtUACPrefix to set
	 */
	public void setTxtUACPrefix(String txtUACPrefix) {
		this.txtUACPrefix = txtUACPrefix;
	}
	/** 
	 * Returns the txtPhone.
	 * @return String
	 */
	public String getTxtPhone() {
		return this.txtPhone;
	}
	/** 
	 * Set the txtPhone.
	 * @param txtPhone The txtPhone to set
	 */
	public void setTxtPhone(String txtPhone) {
		this.txtPhone = txtPhone;
	}
	
	/** 
	 * Returns the txtMobile.
	 * @return String
	 */
	public String getTxtMobile() {
		return this.txtMobile;
	}
	/** 
	 * Set the txtMobile.
	 * @param txtMobile The txtMobile to set
	 */
	public void setTxtMobile(String txtMobile) {
		this.txtMobile = txtMobile;
	}
	/** 
	 * Returns the mobile.
	 * @return String
	 */
	public String getMobile() {
		return this.mobile;
	}
	/** 
	 * Set the mobile.
	 * @param mobile The mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{
		ActionErrors errors = super.validate(mapping,req);
		
		
		if(this.country.equals("-1~+0") && this.otherCountry.length()<=0)
		{
			errors.add("entpUserdetailsForm",new ActionError("errors.resource.other","country"));
			if(this.otherState.length()<=0)
			{
				errors.add("entpUserdetailsForm",new ActionError("errors.resource.other","state"));
				if(this.otherCity.length()<=0)
				{
					errors.add("entpUserdetailsForm",new ActionError("errors.resource.other","city"));
				}
			}
		}
		else
		{
			if(this.state.equals("-1") && this.otherState.length()<=0)
			{
				errors.add("entpUserdetailsForm",new ActionError("errors.resource.other","state"));
				if(this.otherCity.length()<=0)
				{
					errors.add("entpUserdetailsForm",new ActionError("errors.resource.other","city"));
				}
			}
			else
			{
				if(this.city.equals("-1") && this.otherCity.length()<=0)
				{
					errors.add("entpUserdetailsForm",new ActionError("errors.resource.other","city"));
				}
			}
			
		}
		if(this.txtPPrefix.length()>0)
		{
			if(!(this.txtPPrefix.equalsIgnoreCase(this.txtMobile)))
			{
				errors.add("entpUserdetailsForm",new ActionError("errors.resource.match","phone prefix" , "mobile prefix"));
			}
		}
		if(this.txtPPrefix.equals("-1") || this.txtMobile.equals("-1"))
		{
			errors.add("entpUserdetailsForm",new ActionError("errors.resource.valid","phone or mobile prefix"));
		}
		return errors;
	}
	
}