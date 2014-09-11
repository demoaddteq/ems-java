/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.entp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 07-31-2007
 * 
 * XDoclet definition:
 * @struts.form name="unAllotedComplaintForm"
 */
public class UnAllotedComplaintForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String blogids[] = null;
	private String coreUser = "0";
	public void setBlogids(String[] blogids)
	{
		this.blogids = blogids;
	}
	public String[] getBlogids()
	{
		return this.blogids;
	}
	
	public void setCoreUser(String coreUser)
	{
		this.coreUser = coreUser;
	}
	public String getCoreUser()
	{
		return this.coreUser;
	}
	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if(this.blogids.length == 0)
		{
			errors.add("entpunAllotedComplaintForm",new ActionError("errors.resource.allot","Complaint"));
		}
		if(this.coreUser.equals("0"))
		{
			errors.add("entpunAllotedComplaintForm",new ActionError("errors.resource.allot","Core User"));
		}
		return errors;
	}

}