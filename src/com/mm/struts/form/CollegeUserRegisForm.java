package com.mm.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class CollegeUserRegisForm extends ValidatorForm {
	
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
	
	/** companyCity property */	
	private String companyCity="";
	
	/** companyState property */	
	private String companyState="";
	
	/** compCountry property */	
	private String compCountry="";
	
	/** companyCity property */	
	private String othercompanyCity="";
	
	/** companyState property */	
	private String othercompanyState="";
	
	/** compCountry property */	
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
	
	
	private String department= "";
	private String otherdepartment= "";
	private String designation= "";
	private String otherdesignation= "";
	
	
	/** 
	 * Returns the department.
	 * @return String
	 */
	public String getDepartment() {
		return department;
	}
	/** 
	 * Set the department.
	 * @param department The department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/** 
	 * Returns the getOtherdepartment.
	 * @return String
	 */
	public String getOtherdepartment() {
		return otherdepartment;
	}
	/** 
	 * Set the getOtherdepartment.
	 * @param getOtherdepartment The getOtherdepartment to set
	 */
	public void setOtherdepartment(String otherdepartment) {
		this.otherdepartment = otherdepartment;
	}
	
	/** 
	 * Returns the getDesignation.
	 * @return String
	 */
	public String getDesignation() {
		return designation;
	}
	/** 
	 * Set the getDesignation.
	 * @param getDesignation The getDesignation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	/** 
	 * Returns the getOtherdesignation.
	 * @return String
	 */
	public String getOtherdesignation() {
		return otherdesignation;
	}
	/** 
	 * Set the getOtherdesignation.
	 * @param getOtherdesignation The getOtherdesignation to set
	 */
	public void setOtherdesignation(String otherdesignation) {
		this.otherdesignation = otherdesignation;
	}
	
	
	
	
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
	 * Returns the companyCity.
	 * @return String
	 */
	public String getCompanyCity() {
		return companyCity;
	}

	/** 
	 * Set the companyCity.
	 * @param companyCity The companyCity to set
	 */
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}
	
	
	
	
	
	/** 
	 * Returns the companyState.
	 * @return String
	 */
	public String getCompanyState() {
		return companyState;
	}

	/** 
	 * Set the companyState.
	 * @param companyState The companyState to set
	 */
	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}
	
	
	
	
	
	/** 
	 * Returns the compCountry.
	 * @return String
	 */
	public String getCompCountry() {
		return compCountry;
	}

	/** 
	 * Set the compCountry.
	 * @param cCountry The compCountry to set
	 */
	public void setCompCountry(String compCountry) {
		this.compCountry = compCountry;
	}
	
	
	
	/** 
	 * Returns the companyCity.
	 * @return String
	 */
	public String getOthercompanyCity() {
		return othercompanyCity;
	}

	/** 
	 * Set the companyCity.
	 * @param companyCity The companyCity to set
	 */
	public void setOthercompanyCity(String othercompanyCity) {
		this.othercompanyCity = othercompanyCity;
	}
	
	
	
	
	
	/** 
	 * Returns the companyState.
	 * @return String
	 */
	public String getOthercompanyState() {
		return othercompanyState;
	}

	/** 
	 * Set the companyState.
	 * @param companyState The companyState to set
	 */
	public void setOthercompanyState(String othercompanyState) {
		this.othercompanyState = othercompanyState;
	}
	
	
	
	
	
	/** 
	 * Returns the compCountry.
	 * @return String
	 */
	public String getOthercompCountry() {
		return othercompCountry;
	}

	/** 
	 * Set the compCountry.
	 * @param cCountry The compCountry to set
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
		
		String cid = this.compCountry;
		req.setAttribute("cid", cid);
		//System.out.println("cid....collegeUserRegisForm......"+cid);
		if(cid.equalsIgnoreCase("-1"))
		{
			String othercompCountry = this.othercompCountry;
			req.setAttribute("othercompCountry", othercompCountry);
			//System.out.println("othercompCountry....collegeUserRegisForm......"+othercompCountry);
		}
		
		String sid = this.companyState;
		req.setAttribute("sid", sid);
		//System.out.println("sid....FirstCreationForm......"+sid);
		if(sid.equalsIgnoreCase("-1"))
		{
			String othercompanyState = this.othercompanyState;
			req.setAttribute("othercompanyState", othercompanyState);
			//System.out.println("othercompanyState....collegeUserRegisForm......"+othercompanyState);
		}
			
		String pid = this.companyCity;
		req.setAttribute("pid", pid);
		//System.out.println("pid....FirstCreationForm......"+pid);
		if(pid.equalsIgnoreCase("-1"))
		{
			String othercompanyCity = this.othercompanyCity;
			req.setAttribute("othercompanyCity", othercompanyCity);
			//System.out.println("othercompanyCity....collegeUserRegisForm......"+othercompanyCity);
		}
		
		ActionErrors errors = super.validate(mapping,req);
		
		if(!(this.userPassword.equalsIgnoreCase(this.userRePassword)))
		{
			errors.add("collegeUserRegisForm",new ActionError("errors.resource.match","password" , "retype password"));
		}
		if(this.compCountry.equals("-1~+0") && this.othercompCountry.length()<=0)
		{
			errors.add("collegeUserRegisForm",new ActionError("errors.resource.other","country"));
			if(this.othercompanyState.length()<=0)
			{
				errors.add("collegeUserRegisForm",new ActionError("errors.resource.other","state"));
				if(this.othercompanyCity.length()<=0)
				{
					errors.add("collegeUserRegisForm",new ActionError("errors.resource.other","city"));
				}
			}
		}
		else
		{
			if(this.companyState.equals("-1") && this.othercompanyState.length()<=0)
			{
				errors.add("collegeUserRegisForm",new ActionError("errors.resource.other","state"));
				if(this.othercompanyCity.length()<=0)
				{
					errors.add("collegeUserRegisForm",new ActionError("errors.resource.other","city"));
				}
			}
			else
			{
				if(this.companyCity.equals("-1") && this.othercompanyCity.length()<=0)
				{
					errors.add("collegeUserRegisForm",new ActionError("errors.resource.other","city"));
				}
			}
			
		}
		if(this.txtPPrefix.length()>0)
		{
			if(!(this.txtPPrefix.equalsIgnoreCase(this.txtPrefix)))
			{
				errors.add("collegeUserRegisForm",new ActionError("errors.resource.match","phone prefix" , "mobile prefix"));
			}
		}
		if(this.txtPPrefix.equals("-1") || this.txtPrefix.equals("-1"))
		{
			errors.add("collegeUserRegisForm",new ActionError("errors.resource.valid","phone or mobile prefix"));
		}
		return errors;
	}

}
