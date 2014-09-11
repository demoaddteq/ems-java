package com.mm.struts.form;

import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 08-02-2007
 * 
 * XDoclet definition:
 * @struts.form name="LoginCommentsForm"
 */


public class LoginCommentsForm extends ValidatorForm {

	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** loginId property */
	private String loginId= "";

	/** pasword property */
	private String pasword= "";
	
	
	/** complaintId property */
	private String complaintId= "";

	/*
	 * Generated Methods
	 */

	/** 
	 * Returns the loginId.
	 * @return String
	 */
	public String getLoginId() {
		return this.loginId;
	}

	/** 
	 * Set the loginId.
	 * @param loginId The loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/** 
	 * Returns the pasword.
	 * @return String
	 */
	public String getPasword() {
		return this.pasword;
	}

	/** 
	 * Set the pasword.
	 * @param pasword The pasword to set
	 */
	public void setPasword(String pasword) {
		this.pasword = pasword;
	}
	
	/** 
	 * Returns the complaintId.
	 * @return String
	 */
	public String getComplaintId() {
		return this.complaintId;
	}

	/** 
	 * Set the complaintId.
	 * @param complaintId The complaintId to set
	 */
	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}
	
	
	
}
