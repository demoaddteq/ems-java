/*
 
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		int numSId = (request.getParameter("collstate")!=null) ? Integer.parseInt(request.getParameter("collstate").trim()) : 0;
		String codeStr = (request.getParameter("collcountry")!=null) ? (request.getParameter("collcountry").trim()) : "94~+91";
		//System.out.println("codeStr in state>....................."+codeStr);
		String codeStrArr[]= codeStr.split("~");
		int numCId=Integer.parseInt(codeStrArr[0]);
		
		Vector StateVec = rootMaster.getState(numCId, getDataSource(request, "main"));
		System.out.println("States "+StateVec);
	    String strState = getStateList(numSId, numCId, StateVec);
	    //System.out.println("States "+strState);
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strState);
	    out.flush();
		return null;
	}
	private String getStateList(int numSid, int numCId, Vector StateVec)
	{
		String strResult ="<select name=\"collegeState\" tabindex=\"20\" onchange=\"retrievePURL('city.do?collcountry="+numCId+"&collstate='+this.value), showOth();\">";
		
		for(int i=0; i<StateVec.size(); i++)
		{
			Vector tempVec = (Vector)StateVec.elementAt(i);
			int numStateId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			String strSName = tempVec.elementAt(1).toString().trim();
			//System.out.println("numStateId "+numStateId);
			//System.out.println("Ajay  Jha  strSName "+strSName);
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