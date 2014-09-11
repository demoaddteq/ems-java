package com.mm.struts.entp.form;

import org.apache.struts.action.ActionForm;

/** 
 * MyEclipse Struts
 * Creation date: 08-01-2007
 * 
 * XDoclet definition:
 * @struts.form name="manipulategrpForm"
 */

public class ManipulategrpForm extends ActionForm {
	
	/*
	 * Generated fields
	 */

	
	/** grpid property */
	private String grpid;

	/** grpname property */
	private String grpname;

	/** permissions property */
	private String permissions;
	
	/** cmdtyp property */
	private String cmdtyp;
	
	private String tempcount;
	/*
	 * Generated Methods
	 */
	
	/** 
	 * Returns the grpid.
	 * @return String
	 */
	public String getGrpid() {
		return this.grpid;
	}

	/** 
	 * Set the grpid.
	 * @param grpid The grpid to set
	 */
	public void setGrpid(String grpid) {
		this.grpid = grpid;
	}

	/** 
	 * Returns the grpname.
	 * @return String
	 */
	public String getGrpname() {
		return this.grpname;
	}

	/** 
	 * Set the grpname.
	 * @param grpname The grpname to set
	 */
	public void setGrpname(String grpname) {
		this.grpname = grpname;
	}

	/** 
	 * Returns the permissions.
	 * @return String
	 */
	public String getPermissions() {
		return this.permissions;
	}

	/** 
	 * Set the permissions.
	 * @param permissions The permissions to set
	 */
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	//cmdtyp
	/** 
	 * Returns the cmdtyp.
	 * @return String
	 */
	public String getCmdtyp() {
		return this.cmdtyp;
	}

	/** 
	 * Set the cmdtyp.
	 * @param cmdtyp The cmdtyp to set
	 */
	public void setCmdtyp(String cmdtyp) {
		this.cmdtyp = cmdtyp;
	}
	
	//tempcount
	/** 
	 * Returns the tempcount.
	 * @return String
	 */
	public String getTempcount() {
		return this.tempcount;
	}

	/** 
	 * Set the cmdtyp.
	 * @param cmdtyp The cmdtyp to set
	 */
	public void setTempcount(String tempcount) {
		this.tempcount = tempcount;
	}

}
