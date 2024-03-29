/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.advt.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.core.master.RootMaster;

/** 
 * MyEclipse Struts
 * Creation date: 06-30-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class StateAction extends Action {
	RootMaster rootMaster= new RootMaster();
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
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int numSId = (request.getParameter("state")!=null) ? Integer.parseInt(request.getParameter("state").trim()) : 0;
		String codeStr = (request.getParameter("country")!=null) ? (request.getParameter("country").trim()) : "94~+91";
		String codeStrArr[]= codeStr.split("~");
		int numCId=Integer.parseInt(codeStrArr[0]);
		
		HttpSession session = request.getSession();
		HashMap stateList = (HashMap)session.getAttribute("AllStates");
		String strState = "";
		if(request.getParameter("country")!=null && numCId > 0)
		{
			strState = getStateList(numSId, numCId, stateList);
		}
		else
		{
			strState ="<select name=\"state\"  tabindex=\"10\"  onchange=\"retrieveMyProCityURL('city.do?country="+numCId+"&state='+this.value)\">";
			strState += "<option value=\"0\" Selected>Select</option>";
			strState += "<option value=\"-1\">Others</option>";
			strState += "</select>";
		}
	    
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strState);
	    out.flush();
		return null;
	}
	private String getStateList(int numSid, int numCId, HashMap stateList)
	{
		ArrayList AllStates = (ArrayList)stateList.get(numCId);
		
		String strResult ="<select name=\"state\"  tabindex=\"10\"  onchange=\"retrieveMyProCityURL('city.do?country="+numCId+"&state='+this.value),showOth();\">";
		
		for(int i=0; i<AllStates.size(); i++)
		{
			ArrayList StatesArr = (ArrayList)AllStates.get(i);
			int numStateId = Integer.parseInt(StatesArr.get(0).toString().trim());
			String strSName = StatesArr.get(1).toString().trim();
				if(numSid == numStateId)
			{
				strResult += "<option value=\"" + numStateId + "\" Selected>" + strSName + "</option>";
			}
			else
			{
				strResult += "<option value=\"" + numStateId + "\">" + strSName + "</option>";
			}
		}
		
		strResult += "</select>";
		return strResult;
	}
}