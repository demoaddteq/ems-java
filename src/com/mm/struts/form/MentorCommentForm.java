
package com.mm.struts.form;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 08-02-2007
 * 
 * XDoclet definition:
 * @struts.form name="LoginCommentsForm"
 */


public class MentorCommentForm extends ValidatorForm {

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

	private String comtext= "";
	private String userId="";
	
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
	
	/** 
	 * Returns the comtext.
	 * @return String
	 */
	public String getComtext() {
		return this.comtext;
	}

	/** 
	 * Set the comtext.
	 * @param comtext The comtext to set
	 */
	public void setComtext(String comtext) {
		this.comtext = comtext;
	}
	
	/** 
	 * Returns the comtext.
	 * @return String
	 */
	public String getUserId() {
		return this.userId;
	}

	/** 
	 * Set the comtext.
	 * @param comtext The comtext to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/*public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{
		
		
		ActionErrors errors = super.validate(mapping,req);
		
		if(this.comtext.equals("-1"))
		{
			errors.add("mentorCommentForm",new ActionError("errors.resource.valid"," CommentText "));
		}
			
		return errors;
	}*/

}

	
	
	

