package com.mm.struts.corpo.form;

import org.apache.struts.action.ActionForm;

public class DeletStuResultForm extends ActionForm {
	
	/*
	 * Generated Methods
	 */
	private String studentsIds[] = null;
	public void setStudentsIds(String[] studentsIds)
	{
		this.studentsIds = studentsIds;
	}
	public String[] getStudentsIds()
	{
		return this.studentsIds;
	}
	
	// strtaskId
	
	private String strtaskId = "";
	public void setStrtaskId(String strtaskId)
	{
		this.strtaskId = strtaskId;
	}
	public String getStrtaskId()
	{
		return this.strtaskId;
	}

}
