
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
 * @struts.entp.form name="CompanyDetailsForm"
 */
public class CompanyDetailsForm extends ValidatorForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** text property */
	
	
	
	private String companySName = "";
	private String country = "";
	private String companyName = "";
	private String companyPostalCode = "";	
	private String companyAddress1 = "";
	private String companyEMail = "";
	private String companyAddress2 = "";
	
	private String txtCPPrefix = "";
	private String txtCACPrefix = "";
	private String cphone = "";
	
	private String city = "";
	private String state = "";	
	
	private String companyId = "";  //hidden Field
	private String industryCategory = "";  //hidden Field
	private String task = "";  //hidden Field
	
	/** companyTemplate property */	
	private String[] companyTemplate;
	
	/** companyTemplate property */	
	private String othercompanyTemplate="";	
	
	/** 
	 * Returns the companyId.
	 * @return String
	 */
	
	
	public String getCompanyId() {
		return this.companyId;
	}
	/** 
	 * Set the companyId.
	 * @param companyId The companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

	/** 
	 * Returns the industryCategory.
	 * @return String
	 */
	public String getIndustryCategory() {
		return this.industryCategory;
	}
	/** 
	 * Set the industryCategory.
	 * @param industryCategory The industryCategory to set
	 */
	public void setIndustryCategory(String industryCategory) {
		this.industryCategory = industryCategory;
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
	 * Returns the companyTemplate.
	 * @return String
	 */
	public String[] getCompanyTemplate() {
		return companyTemplate;
	}

	/** 
	 * Set the companyTemplate.
	 * @param companyTemplate The companyTemplate to set
	 */
	public void setCompanyTemplate(String companyTemplate[]) {
		this.companyTemplate = companyTemplate;
	}
	
	/** 
	 * Returns the companyTemplate.
	 * @return String
	 */
	public String getOthercompanyTemplate() {
		return othercompanyTemplate;
	}

	/** 
	 * Set the companyTemplate.
	 * @param companyTemplate The companyTemplate to set
	 */
	public void setOthercompanyTemplate(String othercompanyTemplate) {
		this.othercompanyTemplate = othercompanyTemplate;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{
		ActionErrors errors = super.validate(mapping,req);
		
		
		if(this.country.equals("-1~+0") && this.otherCountry.length()<=0)
		{
			errors.add("entpCompanyDetailsForm",new ActionError("errors.resource.other","country"));
			if(this.otherState.length()<=0)
			{
				errors.add("entpCompanyDetailsForm",new ActionError("errors.resource.other","state"));
				if(this.otherCity.length()<=0)
				{
					errors.add("entpCompanyDetailsForm",new ActionError("errors.resource.other","city"));
				}
			}
		}
		else
		{
			if(this.state.equals("-1") && this.otherState.length()<=0)
			{
				errors.add("entpCompanyDetailsForm",new ActionError("errors.resource.other","state"));
				if(this.otherCity.length()<=0)
				{
					errors.add("entpCompanyDetailsForm",new ActionError("errors.resource.other","city"));
				}
			}
			else
			{
				if(this.city.equals("-1") && this.otherCity.length()<=0)
				{
					errors.add("entpCompanyDetailsForm",new ActionError("errors.resource.other","city"));
				}
			}
			
		}
		
		return errors;
	}
	
}