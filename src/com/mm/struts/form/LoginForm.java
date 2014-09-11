/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.form; 

import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 06-06-2007
 * 
 * XDoclet definition:
 * @struts.form name="loginForm"
 */
public class LoginForm extends ValidatorForm {
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
	private String type= "";

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
	 * Returns the type.
	 * @return String
	 */
	public String gettype() {
		return this.type;
	}

	/** 
	 * Set the type.
	 * @param type to set
	 */
	public void settype(String type) {
		this.type = type;
	}
	
	
}