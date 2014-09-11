
package com.mm.struts.entp.form;

import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 06-20-2007
 * 
 * XDoclet definition:
 * @struts.entp.form name="CommunitycreationForm"
 */
public class CommunitycreationForm extends ValidatorForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** text property */
	
	
	
	private String communityName = "";
	
	/** 
	 * Returns the communityName.
	 * @return String
	 */
	public String getCommunityName() {
		return communityName;
	}
	/** 
	 * Set the communityName.
	 * @param communityName The communityName to set
	 */
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	
private String rdbgroup = "";
	
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
	
	
	
	
}