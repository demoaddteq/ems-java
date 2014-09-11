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

public class BuilderLocationAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		System.out.println("In Builder Facility Location");
		RootMaster rootMaster=new RootMaster();
		
		//String depid = (request.getParameter("depId")!=null) ? (request.getParameter("depId").trim()) : "0";
		System.out.println("Builder Facility");		
		//int numCId=Integer.parseInt(depid);
		
		int cityId = (request.getParameter("desID")!=null) ? Integer.parseInt(request.getParameter("desID").trim()) : 1;
		
		System.out.println("Builder Facility Location-->"+cityId);
		
		Vector streamVec = rootMaster.getBuilderLocationList(getDataSource(request, "main"), cityId);
		
		System.out.println("Builder City Vector=>"+streamVec);
	    String strStream = getCourseStreamList(1, 1, streamVec);
	    
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strStream);
	    out.flush();
		return null;
	}
	private String getCourseStreamList(int numSid, int numCId, Vector streamVec)
	{
		
		String strResult ="<select name=\"designation\" onchange=\"retrieveBuilderLocationURL('setbuilderLocation.do?desId='+this.value)\">";
		System.out.println("strResult--02092009=>"+strResult);
		for(int i=0; i<streamVec.size(); i++)
		{
			Vector tempVec = (Vector)streamVec.elementAt(i);
			int numStateId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			String strSName = tempVec.elementAt(1).toString().trim();
			System.out.println("numStateId "+numStateId);
			System.out.println("strSName "+strSName);
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
