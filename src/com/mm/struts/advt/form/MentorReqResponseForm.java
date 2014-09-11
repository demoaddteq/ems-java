package com.mm.struts.advt.form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 * Creation date: 07-27-2007
 * 
 * XDoclet definition:
 * @struts.form name="ChangepassForm"
 */
public class MentorReqResponseForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** text property */
	private String responsetext= "";
	
	/** 
	 * Returns the currentPass.
	 * @return String
	 */
	public String getResponsetext() {
		return this.responsetext;
	}
	/** 
	 * Set the currentPass.
	 * @param currentPass The currentPass to set
	 */
	public void setResponsetext(String responsetext) {
		this.responsetext = responsetext;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{
		ActionErrors errors = new ActionErrors();
		HttpSession session = req.getSession();
		//System.out.println("dc in form");
		if(this.responsetext.length()<=0)
		{
			this.responsetext="Your mentoring request has been accepted/denied.";
		}
		if(this.responsetext.length()<=0)
		{
			//System.out.println("dc  in form if");
			errors.add("advtMentorReqResponseForm",new ActionError("errors.required","Response"));
			session.setAttribute("result", "failure");
		}
		else if(this.responsetext.length()>1500)
		{
			//System.out.println("dc  in form else if");
			errors.add("advtMentorReqResponseForm",new ActionError("errors.resource.length","Response","1500"));
			session.setAttribute("result", "failure");
		}
		else
		{
			//System.out.println("dc  in form else");
			session.setAttribute("result", "success");
		}
		//System.out.println("errors  in form else"+errors);
		return errors;
	}
	
	
}
