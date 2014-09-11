package com.mm.struts.form;

import org.apache.struts.validator.ValidatorForm;

public class MentormeForm extends ValidatorForm {
	
	
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

}
