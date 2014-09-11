
/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.entp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 06-27-2007
 * 
 * XDoclet definition:
 * @struts.form name="firstCreationForm"
 */
public class FirstusercreationForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** UserID property */
	private String userID;
	
	/** userPassword property */	
	private String userPassword;
	
	/** userRePassword property */	
	private String userRePassword;
	
	/** userFirstName property */	
	private String userFirstName;
	
	/** userLastName property */	
	private String userLastName;
	
	/** userAddress property */	
	private String userAddress;
	
	/** city property */	
	private String city="";
	
	/** state property */	
	private String state="";
	
	/** country property */	
	private String country="";
	
	/** city property */	
	private String othercompanyCity="";
	
	/** state property */	
	private String othercompanyState="";
	
	/** country property */	
	private String othercompCountry="";
	
	/** userPostalCode property */	
	private String userPostalCode;
	
	/** userEMail property */	
	private String userEMail;
	
	/** txtPPrefix property */
	private String txtPPrefix;
	
	/** txtAreacode property */
	private String txtAreacode;
	
	/** txtPhone property */
	private String txtPhone;
	
	/** txtPrefix property */
	private String txtPrefix;
	
	/** txtMobile property */	
	private String txtMobile;
	
	/** 
	 * Returns the userID.
	 * @return String
	 */
	public String getUserID() {
		return userID;
	}

	/** 
	 * Set the userID.
	 * @param userID The userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	/** 
	 * Returns the userPassword.
	 * @return String
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/** 
	 * Set the userPassword.
	 * @param userPassword The userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	/** 
	 * Returns the userRePassword.
	 * @return String
	 */
	public String getUserRePassword() {
		return userRePassword;
	}

	/** 
	 * Set the userRePassword.
	 * @param userRePassword The userRePassword to set
	 */
	public void setUserRePassword(String userRePassword) {
		this.userRePassword = userRePassword;
	}
	
	/** 
	 * Returns the userFirstName.
	 * @return String
	 */
	public String getUserFirstName() {
		return userFirstName;
	}

	/** 
	 * Set the userFirstName.
	 * @param userFirstName The userFirstName to set
	 */
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	
	
	/** 
	 * Returns the userLastName.
	 * @return String
	 */
	public String getUserLastName() {
		return userLastName;
	}

	/** 
	 * Set the userLastName.
	 * @param userLastName The userLastName to set
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	
	/** 
	 * Returns the userAddress.
	 * @return String
	 */
	public String getUserAddress() {
		return userAddress;
	}

	/** 
	 * Set the companySName.
	 * @param userAddress The userAddress to set
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	
	/** 
	 * Returns the city.
	 * @return String
	 */
	public String getCity() {
		return city;
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
		return state;
	}

	/** 
	 * Set the state.
	 * @param state The state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
	
	/** 
	 * Returns the country.
	 * @return String
	 */
	public String getCountry() {
		return country;
	}

	/** 
	 * Set the country.
	 * @param cCountry The country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	/** 
	 * Returns the city.
	 * @return String
	 */
	public String getOthercompanyCity() {
		return othercompanyCity;
	}

	/** 
	 * Set the city.
	 * @param city The city to set
	 */
	public void setOthercompanyCity(String othercompanyCity) {
		this.othercompanyCity = othercompanyCity;
	}
	
	
	
	
	
	/** 
	 * Returns the state.
	 * @return String
	 */
	public String getOthercompanyState() {
		return othercompanyState;
	}

	/** 
	 * Set the state.
	 * @param state The state to set
	 */
	public void setOthercompanyState(String othercompanyState) {
		this.othercompanyState = othercompanyState;
	}
	
	
	
	
	
	/** 
	 * Returns the country.
	 * @return String
	 */
	public String getOthercompCountry() {
		return othercompCountry;
	}

	/** 
	 * Set the country.
	 * @param cCountry The country to set
	 */
	public void setOthercompCountry(String othercompCountry) {
		this.othercompCountry = othercompCountry;
	}
	
	
	/** 
	 * Returns the userPostalCode.
	 * @return String
	 */
	public String getUserPostalCode() {
		return userPostalCode;
	}

	/** 
	 * Set the userPostalCode.
	 * @param userPostalCode The userPostalCode to set
	 */
	public void setUserPostalCode(String userPostalCode) {
		this.userPostalCode = userPostalCode;
	}

	/** 
	 * Returns the userEMail.
	 * @return String
	 */
	public String getUserEMail() {
		return userEMail;
	}

	/** 
	 * Set the userEMail.
	 * @param userEMail The userEMail to set
	 */
	public void setUserEMail(String userEMail) {
		this.userEMail = userEMail;
	}
	

	/** 
	 * Returns the txtPPrefix.
	 * @return String
	 */
	public String getTxtPPrefix() {
		return txtPPrefix;
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
		return txtAreacode;
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
		return txtPhone;
	}

	/** 
	 * Set the txtCAreacode.
	 * @param txtCAreacode The txtCAreacode to set
	 */
	public void setTxtPhone(String txtPhone) {
		this.txtPhone = txtPhone;
	}
	

	/** 
	 * Returns the txtPrefix.
	 * @return String
	 */
	public String getTxtPrefix() {
		return txtPrefix;
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
		return txtMobile;
	}

	/** 
	 * Set the txtCAreacode.
	 * @param txtCAreacode The txtCAreacode to set
	 */
	public void setTxtMobile(String txtMobile) {
		this.txtMobile = txtMobile;
	}
	
	//Validation for this form
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{
		
		String cid = this.country;
		req.setAttribute("cid", cid);
		//System.out.println("cid....FirstusercreationForm......"+cid);
		if(cid.equalsIgnoreCase("-1"))
		{
			String othercompCountry = this.othercompCountry;
			req.setAttribute("othercompCountry", othercompCountry);
			//System.out.println("othercompCountry....FirstusercreationForm...1111..."+othercompCountry);
		}
		
		String sid = this.state;
		req.setAttribute("sid", sid);
		//System.out.println("sid....FirstusercreationForm......"+sid);
		if(sid.equalsIgnoreCase("-1"))
		{
			String othercompanyState = this.othercompanyState;
			req.setAttribute("othercompanyState", othercompanyState);
			//System.out.println("othercompanyState....FirstusercreationForm...1111..."+othercompanyState);
		}
			
		String pid = this.city;
		req.setAttribute("pid", pid);
		//System.out.println("pid....FirstusercreationForm......"+pid);
		if(pid.equalsIgnoreCase("-1"))
		{
			String othercompanyCity = this.othercompanyCity;
			req.setAttribute("othercompanyCity", othercompanyCity);
			//System.out.println("othercompanyCity....FirstusercreationForm...1111..."+othercompanyCity);
		}
		ActionErrors errors = super.validate(mapping,req);
		
		if(!(this.userPassword.equalsIgnoreCase(this.userRePassword)))
		{
			errors.add("firstusercreationForm",new ActionError("errors.resource.match","password" , "retype password"));
		}
		if(this.country.equals("-1~+0") && this.othercompCountry.length()<=0)
		{
			errors.add("firstusercreationForm",new ActionError("errors.resource.other","country"));
			if(this.othercompanyState.length()<=0)
			{
				errors.add("firstusercreationForm",new ActionError("errors.resource.other","state"));
				if(this.othercompanyCity.length()<=0)
				{
					errors.add("firstusercreationForm",new ActionError("errors.resource.other","city"));
				}
			}
		}
		else
		{
			if(this.state.equals("-1") && this.othercompanyState.length()<=0)
			{
				errors.add("firstusercreationForm",new ActionError("errors.resource.other","state"));
				if(this.othercompanyCity.length()<=0)
				{
					errors.add("firstusercreationForm",new ActionError("errors.resource.other","city"));
				}
			}
			else
			{
				if(this.city.equals("-1") && this.othercompanyCity.length()<=0)
				{
					errors.add("firstusercreationForm",new ActionError("errors.resource.other","city"));
				}
			}
			
		}
		if(this.txtPPrefix.length()>0)
		{
			if(!(this.txtPPrefix.equalsIgnoreCase(this.txtPrefix)))
			{
				errors.add("firstusercreationForm",new ActionError("errors.resource.match","phone prefix" , "mobile prefix"));
			}
		}
		if(this.txtPPrefix.equals("-1") || this.txtPrefix.equals("-1"))
		{
			errors.add("firstusercreationForm",new ActionError("errors.resource.valid","phone or mobile prefix"));
		}
		return errors;
	}
}