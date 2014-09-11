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

public class BuilderFacilityAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		System.out.println("In Builder Facility Action");
		RootMaster rootMaster=new RootMaster();
		
		//String depid = (request.getParameter("depId")!=null) ? (request.getParameter("depId").trim()) : "0";
		System.out.println("Builder Facility");		
		//int numCId=Integer.parseInt(depid);
		
		int city_Id = (request.getParameter("cityId")!=null) ? Integer.parseInt(request.getParameter("cityId").trim()) : 0;
		int builder_Id=(request.getParameter("builderId")!=null) ? Integer.parseInt(request.getParameter("builderId").trim()) : 0;
		
		System.out.println("Builder ID In Facility Action-->"+builder_Id);
		System.out.println("City ID In Facility Action-->"+city_Id);
		
		Vector streamVec = rootMaster.getBuilderFacilityList(getDataSource(request, "main"), city_Id ,builder_Id);
		
		System.out.println("Builder City Vector=>"+streamVec);
	    String strStream = getCourseStreamList(city_Id, builder_Id, streamVec);
	    
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strStream);
	    out.flush();
		return null;
	}
	private String getCourseStreamList(int numSid, int builder_Id, Vector streamVec)
	{
		
		String strResult ="<select name=\"builderFacility\">";
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
		System.out.println("strResult--07092009 in Facility--3=>"+strResult);
		return strResult;
	}

}
