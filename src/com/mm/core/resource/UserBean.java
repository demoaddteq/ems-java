/**
 * 
 */
package com.mm.core.resource;

/**
 * @author arun
 *
 */
public class UserBean 
{
	private String cname="";
	
	private String userName="";
	
	private String pass="";
	
	private String contactUs="";
	
	/* Returns the strCName.
	 * @return String
	 */
	public String getCNname() {
		return cname;
	}
	/** 
	 * Set the strCName.
	 * @param strCName The strCName to set
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	/* Returns the strUserName.
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}
	/** 
	 * Set the strUserName.
	 * @param strUserName The strUserName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/* Returns the strPass.
	 * @return String
	 */
	public String getPass() {
		return pass;
	}
	/** 
	 * Set the strPass.
	 * @param strPass The strPass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	/* Returns the strContactUS.
	 * @return String
	 */
	public String getContactUs() {
		return contactUs;
	}
	/** 
	 * Set the strContactUS.
	 * @param strContactUS The strContactUS to set
	 */
	public void setContactUs(String contactUs) {
		this.contactUs = contactUs;
	}
}
