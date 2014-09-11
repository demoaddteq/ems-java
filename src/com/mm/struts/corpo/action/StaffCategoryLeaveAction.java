package com.mm.struts.corpo.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.core.master.RootMaster;
public class StaffCategoryLeaveAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		System.out.println("In StaffCategoryLeaveAction Action");
		RootMaster rootMaster=new RootMaster();
		
		//String depid = (request.getParameter("depId")!=null) ? (request.getParameter("depId").trim()) : "0";
	//	System.out.println("Builder");		
		//int numCId=Integer.parseInt(depid);
		
		//int numdesId = (request.getParameter("desId")!=null) ? Integer.parseInt(request.getParameter("desId").trim()) : 0;
		
		Vector streamVec = rootMaster.getStaffCategoryLeaveList(getDataSource(request, "corpo"), 1);
		
		//Vector streamVec = rootMaster.getBuilderList(getDataSource(request, "main"), 1);
		
		System.out.println("Builder Vector=>"+streamVec);
	    String strStream = getCourseStreamList(1, 1, streamVec);
	    
	//    System.out.println("strStream=>"+strStream);
	//    System.out.println("streamVec=>"+streamVec);
	    
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strStream);
	    out.flush();
		return null;
	}
	private String getCourseStreamList(int numSid, int numCId, Vector streamVec)
	{
		
		String strResult ="<select name=\"category\" onchange=\"retrieveStaffForLeaveURL('setStaffForLeave.do?categoryId='+this.value)\">";
		System.out.println("strResult Shift --15102009=>"+strResult);
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
