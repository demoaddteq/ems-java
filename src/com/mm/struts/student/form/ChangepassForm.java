
/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */

package com.mm.struts.student.form;

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
 * @struts.form name="ChangepassForm"
 */
public class ChangepassForm extends ValidatorForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** text property */
	private String currentPass= "";
	private String passWord= "";
	private String conPassWord= "";
	/** 
	 * Returns the currentPass.
	 * @return String
	 */
	public String getCurrentPass() {
		return currentPass;
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
		return passWord;
	}
	/** 
	 * Set the passWord.
	 * @param passWord The passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/** 
	 * Returns the conPassWord.
	 * @return String
	 */
	public String getConPassWord() {
		return conPassWord;
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
		
		if(!(this.passWord.equals(this.conPassWord)))
		{
			errors.add("entpChangepassForm",new ActionError("errors.resource.match","password" , "retype password"));
		}
		
		return errors;
	}
	
}