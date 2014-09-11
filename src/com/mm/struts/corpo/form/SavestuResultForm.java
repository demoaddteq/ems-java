package com.mm.struts.corpo.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SavestuResultForm extends ActionForm {
	
	/*
	 * Generated Methods
	 */
	private String studentsds[] = null;
	public void setStudentsds(String[] studentsds)
	{
		this.studentsds = studentsds;
	}
	public String[] getStudentsds()
	{
		return this.studentsds;
	}
	
	
	private String titelname = null;
	public void setTitelname(String titelname)
	{
		this.titelname = titelname;
	}
	public String getTitelname()
	{
		return this.titelname;
	}
	
	private String acceess = null;
	public void setAcceess(String acceess)
	{
		this.acceess = acceess;
	}
	public String getAcceess()
	{
		return this.acceess;
	}
	
	
	private String titelname1 = null;
	public void setTitelname1(String titelname1)
	{
		this.titelname1 = titelname1;
	}
	public String getTitelname1()
	{
		return this.titelname1;
	}
	
	private String acceess1 = null;
	public void setAcceess1(String acceess1)
	{
		this.acceess1 = acceess1;
	}
	public String getAcceess1()
	{
		return this.acceess1;
	}
	
	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	
}
