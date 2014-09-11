
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
 * @struts.entp.form name="UsercreationForm"
 */
public class UserdetailsForm extends ValidatorForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** text property */
	
	
	
	private String loginName = "";
	private String state = "";
	private String password = "";
	private String city = "";	
	private String repassword = "";
	private String fname = "";
	private String email = "";
	private String lname = "";
	private String txtPPrefix = "";
	private String txtAreacode = "";
	private String txtPhone = "";
	private String address = "";
	private String txtPrefix = "";
	private String txtMobile = "";
	private String country = "";
	private String zip = "";
	private String userid = ""; //hidden
	private String task = ""; //hidden
	
private String rdbgroup="0";
	
//	private String rdbgroup;
	/** 
	 * Returns the rdbgroup.
	 * @return String
	 */
	public String getRdbgroup() {
		return rdbgroup;
	}
	/** 
	 * Set the rdbgroup.
	 * @param rdbgroup The rdbgroup to set
	 */
	public void setRdbgroup(String rdbgroup) {
		this.rdbgroup = rdbgroup;

	}
	
	/** 
	 * Returns the userid.
	 * @return String
	 */
	public String getUserid() {
		return this.userid;
	}
	/** 
	 * Set the userid.
	 * @param userid The userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	/** 
	 * Returns the task.
	 * @return String
	 */
	public String getTask() {
		return this.task;
	}
	/** 
	 * Set the task.
	 * @param task The task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}
	
	/** text property */
	private String otherCountry = "";
	
	/** 
	 * Returns the othComcountry.
	 * @return String
	 */
	public String getOtherCountry() {
		return this.otherCountry;
	}
	/** 
	 * Set the othComcountry.
	 * @param othComcountry The othComcountry to set
	 */
	public void setOtherCountry(String otherCountry) {
		this.otherCountry = otherCountry;
	}
	
	
	
	/** text property */
	private String otherState = "";
	
	/** 
	 * Returns the othComState.
	 * @return String
	 */
	public String getOtherState() {
		return this.otherState;
	}
	/** 
	 * Set the othComState.
	 * @param othComState The othComState to set
	 */
	public void setOtherState(String otherState) {
		this.otherState = otherState;
	}
	
	
	
	/** text property */
	private String otherCity = "";
	
	/** 
	 * Returns the othComCity.
	 * @return String
	 */
	public String getOtherCity() {
		return this.otherCity;
	}
	/** 
	 * Set the othComCity.
	 * @param othComCity The othComCity to set
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
	 * Returns the password.
	 * @return String
	 */
	public String getPassword() {
		return this.password;
	}
	/** 
	 * Set the password.
	 * @param password The password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * Returns the repassword.
	 * @return String
	 */
	public String getRepassword() {
		return this.repassword;
	}
	/** 
	 * Set the repassword.
	 * @param repassword The repassword to set
	 */
	public void setRepassword(String repassword) {
		this.repassword = repassword;
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
	 * Returns the txtAreacode.
	 * @return String
	 */
	public String getTxtAreacode() {
		return this.txtAreacode;
	}
	/** 
	 * Set the txtAreacode.
	 * @param txtAreacode The txtAreacode to set
	 */
	public void setTxtAreacode(String txtAreacode) {
		this.txtAreacode = txtAreacode;
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
	 * Returns the txtPrefix.
	 * @return String
	 */
	public String getTxtPrefix() {
		return this.txtPrefix;
	}
	/** 
	 * Set the txtPrefix.
	 * @param txtPrefix The txtPrefix to set
	 */
	public void setTxtPrefix(String txtPrefix) {
		this.txtPrefix = txtPrefix;
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
	 * Returns the country.
	 * @return String
	 */
	public String getCountry() {
		return this.country;
	}
	/** 
	 * Set the country.
	 * @param country The country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip() {
		return this.zip;
	}
	/** 
	 * Set the pincode.
	 * @param pincode The pincode to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
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
			if(!(this.txtPPrefix.equalsIgnoreCase(this.txtPrefix)))
			{
				errors.add("entpUserdetailsForm",new ActionError("errors.resource.match","phone prefix" , "mobile prefix"));
			}
		}
		if(this.txtPPrefix.equals("-1") || this.txtPrefix.equals("-1"))
		{
			errors.add("entpUserdetailsForm",new ActionError("errors.resource.valid","phone or mobile prefix"));
		}
		return errors;
	}
	
	
	
}