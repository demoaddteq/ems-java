
/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.action;

import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import javax.sql.DataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.core.master.RootMaster;


/** 
 * MyEclipse Struts
 * Creation date: 07-10-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ActivateAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		RootMaster rootMaster = new RootMaster();
		String uName = (request.getAttribute("uName")!=null) ?  request.getAttribute("uName").toString().trim() : "";
		DataSource dataSource = getDataSource(request,"main");	
		
		System.out.println("ActivateAction......");
			
		String modifyDate ="";		
		String modifyTime = "";
		
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
		modifyDate = sttotal.nextToken();
		modifyTime = sttotal.nextToken();
	    sttotal.nextToken();
		
	    
		
		String result = rootMaster.activateUser(dataSource, uName, modifyDate, modifyTime);
		
		
		
		if(result.equalsIgnoreCase("success"))
		{
//			To write a blog file.
			rootMaster.CreateBlogsFile(dataSource);
			ActionErrors errors = new ActionErrors();
			errors.add("active", new ActionError("errors.user.activate"));
			if(!errors.isEmpty())
			{
				saveErrors(request, errors);
			}
		}
		return mapping.findForward(result);
	}
}