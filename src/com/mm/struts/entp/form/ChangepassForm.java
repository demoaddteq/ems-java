package com.mm.struts.entp.form;

import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import javax.servlet.http.*;


/** 
 * MyEclipse Struts
 * Creation date: 06-19-2007
 * 
 * XDoclet definition:
 * @struts.entp.form name="ChangepassForm"
 */
public class ChangepassForm extends ValidatorForm{
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** text property */
	private String currentPass = "";
	private String passWord = "";
	private String conPassWord = "";
	private String userId = "";
	
	/** 
	 * Returns the currentPass.
	 * @return String
	 */
	public String getCurrentPass() {
		return this.currentPass;
	}
	
	/** 
	 * Set the currentPass.
	 * @param currentPass The currentPass to set
	 */

	public void setCurrentPass(String currentPass) {
		this.currentPass = currentPass;
	}
	
	/** 
	 * Returns the passWord.
	 * @return String
	 */
	public String getPassWord() {
		return this.passWord;
	}
	
	/** 
	 * Set the passWord.
	 * @param passWord The passWord to set
	 */

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/** 
	 * Returns the userId.
	 * @return String
	 */

	public String getUserId() {
		return this.userId;
	}

	/** 
	 * Set the conPassWord.
	 * @param conPassWord The conPassWord to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/** 
	 * Returns the conPassWord.
	 * @return String
	 */

	public String getConPassWord() {
		return this.conPassWord;
	}

	/** 
	 * Set the conPassWord.
	 * @param conPassWord The conPassWord to set
	 */
	public void setConPassWord(String conPassWord) {
		this.conPassWord = conPassWord;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{
		ActionErrors errors = super.validate(mapping,req);
		
		if(!(this.passWord.equalsIgnoreCase(this.conPassWord)))
		{
			errors.add("entpChangepassForm",new ActionError("errors.resource.match","password" , "retype password"));
		}
		
		return errors;
	}
	
	
}
