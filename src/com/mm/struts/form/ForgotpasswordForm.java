package com.mm.struts.form;



import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 06-19-2007
 * 
 * XDoclet definition:
 * @struts.entp.form name="ChangepassForm"
 */
public class ForgotpasswordForm extends ValidatorForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** text property */
	private String fuName = "";
	private String femailadd = "";
	
	/** 
	 * Returns the fuName.
	 * @return String
	 */
	public String getFuName() {
		return this.fuName;
	}
	
	/** 
	 * Set the fuName.
	 * @param fuName The fuName to set
	 */
	public void setFuName(String fuName) {
		this.fuName = fuName;
	}
	
	/** 
	 * Returns the femailadd.
	 * @return String
	 */
	public String getFemailadd() {
		return this.femailadd;
	}
	
	/** 
	 * Set the femailadd.
	 * @param femailadd The femailadd to set
	 */
	public void setFemailadd(String femailadd) {
		this.femailadd = femailadd;
	}

}