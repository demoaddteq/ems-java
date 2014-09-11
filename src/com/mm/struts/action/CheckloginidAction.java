/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.RootMaster;

/** 
 * MyEclipse Struts
 * Creation date: 11-02-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class CheckloginidAction extends Action {
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
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String strLid = (request.getParameter("Lid")!= null) ? request.getParameter("Lid").trim() : "";
		//System.out.println("Login Id is "+strLid);
		RootMaster rm = new RootMaster();
		String strCountries = "";
		//System.out.println("length of Lid "+strLid.length());
		if(strLid.trim().length()>=6 && strLid.trim().length() <= 25)
		{
			int numAvialable = rm.checkAvailability(getDataSource(request,"main"), strLid);
			if(numAvialable > 0)
			{
				strCountries = "<center><font class=\"bold\">User Id Already Exist.</font></center>";
			}
			else
			{
				strCountries = "<center><font class=\"bold\">User Id Available.</font></center>";
			}
		}
		else if (strLid.trim().length()< 6)
		{
			strCountries = "<center><font class=\"bold\">User Id too short. Not acceptable.</font></center>";
		}
		else if (strLid.trim().length() > 25)
		{
			strCountries = "<center><font class=\"bold\">User Id too long. Please create in between 6 to 25 characters.</font></center>";
		}
		else
		{
			strCountries = "<center><font class=\"bold\">Unknown</font></center>";
		}
		
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strCountries);
	    out.flush();
		return null;
	}
}