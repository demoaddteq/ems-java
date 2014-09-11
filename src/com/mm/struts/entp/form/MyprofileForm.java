
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
public class MyprofileForm extends ValidatorForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** text property */
	private String companySName = "";	
	private String companyName = "";	
	private String companyAddress1 = "";	
	private String companyAddress2 = "";	
	private String city = "";	
	private String state = "";	
	private String country = "";	
	private String companyPostalCode = "";	
	private String companyEMail = "";	
	private String txtCPPrefix = "";
	private String txtCACPrefix = "";
	private String cphone = "";
	
	
	
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
	 * Returns the companySName.
	 * @return String
	 */
	public String getCompanySName() {
		return this.companySName;
	}
	/** 
	 * Set the companySName.
	 * @param companySName The companySName to set
	 */
	public void setCompanySName(String companySName) {
		this.companySName = companySName;
	}
	
	/** 
	 * Returns the companyName.
	 * @return String
	 */
	public String getCompanyName() {
		return this.companyName;
	}
	/** 
	 * Set the companyName.
	 * @param companyName The companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	/** 
	 * Returns the companyAddress1.
	 * @return String
	 */
	public String getCompanyAddress1() {
		return this.companyAddress1;
	}
	/** 
	 * Set the companyAddress1.
	 * @param companyAddress1 The companyAddress1 to set
	 */
	public void setCompanyAddress1(String companyAddress1) {
		this.companyAddress1 = companyAddress1;
	}
	
	/** 
	 * Returns the companyAddress2.
	 * @return String
	 */
	public String getCompanyAddress2() {
		return this.companyAddress2;
	}
	/** 
	 * Set the companyAddress2.
	 * @param companyAddress2 The companyAddress2 to set
	 */
	public void setCompanyAddress2(String companyAddress2) {
		this.companyAddress2 = companyAddress2;
	}
	
	/** 
	 * Returns the companyCity.
	 * @return String
	 */
	public String getCity() {
		return this.city;
	}
	/** 
	 * Set the companyCity.
	 * @param companyCity The companyCity to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/** 
	 * Returns the companyState.
	 * @return String
	 */
	public String getState() {
		return this.state;
	}
	/** 
	 * Set the companyState.
	 * @param companyState The companyState to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/** 
	 * Returns the ccountry.
	 * @return String
	 */
	public String getCountry() {
		return this.country;
	}
	/** 
	 * Set the ccountry.
	 * @param ccountry The ccountry to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/** 
	 * Returns the companyPostalCode.
	 * @return String
	 */
	public String getCompanyPostalCode() {
		return this.companyPostalCode;
	}
	/** 
	 * Set the companyPostalCode.
	 * @param companyPostalCode The companyPostalCode to set
	 */
	public void setCompanyPostalCode(String companyPostalCode) {
		this.companyPostalCode = companyPostalCode;
	}
	
	/** 
	 * Returns the companyEMail.
	 * @return String
	 */
	public String getCompanyEMail() {
		return this.companyEMail;
	}
	/** 
	 * Set the companyEMail.
	 * @param companyEMail The companyEMail to set
	 */
	public void setCompanyEMail(String companyEMail) {
		this.companyEMail = companyEMail;
	}
	
	/** 
	 * Returns the txtCPPrefix.
	 * @return String
	 */
	public String getTxtCPPrefix() {
		return this.txtCPPrefix;
	}
	/** 
	 * Set the txtCPPrefix.
	 * @param txtCPPrefix The txtCPPrefix to set
	 */
	public void setTxtCPPrefix(String txtCPPrefix) {
		this.txtCPPrefix = txtCPPrefix;
	}
	
	/** 
	 * Returns the txtCACPrefix.
	 * @return String
	 */
	public String getTxtCACPrefix() {
		return this.txtCACPrefix;
	}
	/** 
	 * Set the txtCACPrefix.
	 * @param txtCACPrefix The txtCACPrefix to set
	 */
	public void setTxtCACPrefix(String txtCACPrefix) {
		this.txtCACPrefix = txtCACPrefix;
	}
	/** 
	 * Returns the cphone.
	 * @return String
	 */
	public String getCphone() {
		return this.cphone;
	}
	/** 
	 * Set the cphone.
	 * @param cphone The cphone to set
	 */
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{
		ActionErrors errors = super.validate(mapping,req);
		
		
		if(this.country.equals("-1~+0") && this.otherCountry.length()<=0)
		{
			errors.add("entpMyprofileForm",new ActionError("errors.resource.other","country"));
			if(this.otherState.length()<=0)
			{
				errors.add("entpMyprofileForm",new ActionError("errors.resource.other","state"));
				if(this.otherCity.length()<=0)
				{
					errors.add("entpMyprofileForm",new ActionError("errors.resource.other","city"));
				}
			}
		}
		else
		{
			if(this.state.equals("-1") && this.otherState.length()<=0)
			{
				errors.add("entpMyprofileForm",new ActionError("errors.resource.other","state"));
				if(this.otherCity.length()<=0)
				{
					errors.add("entpMyprofileForm",new ActionError("errors.resource.other","city"));
				}
			}
			else
			{
				if(this.city.equals("-1") && this.otherCity.length()<=0)
				{
					errors.add("entpMyprofileForm",new ActionError("errors.resource.other","city"));
				}
			}
			
		}
		
		return errors;
	}
	
	
}