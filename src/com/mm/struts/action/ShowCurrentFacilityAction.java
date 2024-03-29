/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.RootMaster;

/** 
 * MyEclipse Struts
 * Creation date: 10-04-2009
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ShowCurrentFacilityAction extends Action {
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
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String strFacility = "";
		RootMaster rm = new RootMaster();
		ArrayList arrFacility = rm.getCurrentFacility(getDataSource(request, "main"));
		
		for(int i=0; i<arrFacility.size(); i++)
		{
			ArrayList arrTemp = (ArrayList)arrFacility.get(i);
			
			//int numCompId = Integer.parseInt(arrTemp.get(0).toString().trim());
			String strCompTTL = arrTemp.get(1).toString().trim();
			strFacility += "<div class=\"updateBox\">";
			strFacility += "<div class=\"fltLft-Padding53\"><img src=\"images/dlf.jpg\" width=\"51\" height=\"36\" /></div>";
			strFacility += "<div class=\"updateBoxTXT\"><p>"+strCompTTL+"</p></div>";
			//strFacility += "<div class=\"fltRht\">";
			//strFacility += "<img class=\"del-x\" alt=\"\" src=\"images/close-1.gif\"/>";
			//strFacility += "</div>";
			strFacility += "</div>";
		}
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strFacility);
	    out.flush();
		return null;
	}
}