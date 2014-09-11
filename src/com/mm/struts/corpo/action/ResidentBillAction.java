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

public class ResidentBillAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		System.out.println("In ResidentBillAction-1");
		RootMaster rootMaster=new RootMaster();
		
		//String depid = (request.getParameter("depId")!=null) ? (request.getParameter("depId").trim()) : "0";
		System.out.println("ResidentBillAction-2");		
		//int numCId=Integer.parseInt(depid);
		
		int city_Id = (request.getParameter("cityId")!=null) ? Integer.parseInt(request.getParameter("cityId").trim()) : 0;
		int builder_Id=(request.getParameter("builderID")!=null) ? Integer.parseInt(request.getParameter("builderID").trim()) : 0;
		
		System.out.println("ResidentBillAction-3-->"+builder_Id);
		System.out.println("ResidentBillAction-4-->"+city_Id);
		
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
		
		String strResult ="<select name=\"builderFacility\" onchange=\"outputSelected(this.form.builderFacility)\">";
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
