
package com.mm.struts.entp.form;

import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 06-20-2007
 * 
 * XDoclet definition:
 * @struts.entp.form name="CommentsDetailForm"
 */
public class CommentsDetailForm extends ValidatorForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** text property */
	private String commnetId = "";
	private String txtComment = "";
	
	/** 
	 * Returns the txtcid.
	 * @return String
	 */
	public String getCommnetId() {
		return commnetId;
	}

	/** 
	 * Set the txtcid.
	 * @param txtcid The txtcid to set
	 */
	public void setCommnetId(String commnetId) {
		this.commnetId = commnetId;
	}
	
	/** 
	 * Returns the txtComment.
	 * @return String
	 */
	public String getTxtComment() {
		return txtComment;
	}

	/** 
	 * Set the txtComment.
	 * @param txtComment The txtComment to set
	 */
	public void setTxtComment(String txtComment) {
		this.txtComment = txtComment;
	}
	
}