package com.mm.struts.corpo.action;


import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.core.master.RootMaster;


public class LeaveStaffAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		RootMaster rootMaster=new RootMaster();
		HttpSession session = request.getSession();
		int facilityId = (session.getAttribute("fId")!=null) ? Integer.parseInt((String)session.getAttribute("fId")) : 0;
		
		
		String category_Id = (request.getParameter("categoryId")!=null) ? (request.getParameter("categoryId").trim()) : "0";
		
		if(category_Id==""){
			category_Id="0";
		}
		
		int catId=Integer.parseInt(category_Id);
		
		Vector streamVec = rootMaster.getStaffLeaveList(getDataSource(request, "corpo"), catId ,facilityId);
	    String strStream = getCourseStreamList(1, 1, streamVec);
	    
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strStream);
	    out.flush();
		return null;
	}
	private String getCourseStreamList(int numSid, int catId, Vector streamVec)
	{
		
		String strResult ="<select name=\"staffId\">";
		System.out.println("strResult Shift --18092009=>"+strResult);
		for(int i=0; i<streamVec.size(); i++)
		{
			Vector tempVec = (Vector)streamVec.elementAt(i);
			int numStateId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			String strSName = tempVec.elementAt(1).toString().trim();
			//////System.out.println("numStateId "+numStateId);
			//////System.out.println("strSName "+strSName);
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
		System.out.println("Result Returned Category --18092009=>"+strResult);
		return strResult;
	}

}
