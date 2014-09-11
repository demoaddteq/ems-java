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
 * @struts.entp.form name="AllotcomplaintForm"
 */
public class AllotcomplaintForm extends ValidatorForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** text property */
	
	
	
	private String cmbUser= "";
	private String permissionList = "";
	private String allotedPermissionList = "";
	private String permissions = "";
	/** 
	 * Returns the permissions.
	 * @return String
	 */
	public String getPermissions() {
		return this.permissions;
	}
	/** 
	 * Set the cmbUser.
	 * @param cmbUser The cmbUser to set
	 */
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	/** 
	 * Returns the cmbUser.
	 * @return String
	 */
	public String getCmbUser() {
		return this.cmbUser;
	}
	/** 
	 * Set the cmbUser.
	 * @param cmbUser The cmbUser to set
	 */
	public void setCmbUser(String cmbUser) {
		this.cmbUser = cmbUser;
	}
	/** 
	 * Returns the permissionList.
	 * @return String
	 */
	public String getPermissionList() {
		return this.permissionList;
	}
	/** 
	 * Set the permissionList.
	 * @param permissionList The permissionList to set
	 */
	public void setPermissionList(String permissionList) {
		this.permissionList = permissionList;
	}
	/** 
	 * Returns the allotedPermissionList.
	 * @return String
	 */
	public String getAllotedPermissionList() {
		return this.allotedPermissionList;
	}
	/** 
	 * Set the allotedPermissionList.
	 * @param allotedPermissionList The allotedPermissionList to set
	 */
	public void setAllotedPermissionList(String allotedPermissionList) {
		this.allotedPermissionList = allotedPermissionList;
	}
	
	/** 
	 * Returns the title.
	 * @return String
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if(this.permissions.length()== 0)
		{
			errors.add("entpAllotcomplaintForm",new ActionError("errors.resource.allot","Category"));
		}
		
		return errors;
	}
	
}