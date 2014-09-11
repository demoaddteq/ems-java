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

public class BuilderCityAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		System.out.println("In Builder City Action");
		RootMaster rootMaster=new RootMaster();
		
		//String depid = (request.getParameter("depId")!=null) ? (request.getParameter("depId").trim()) : "0";
		System.out.println("Builder City---->"+request.getParameter("builderId"));		
		//int numCId=Integer.parseInt(depid);
		
		int builderId = (request.getParameter("builderId")!=null) ? Integer.parseInt(request.getParameter("builderId").trim()) : 0;
		
		System.out.println("Builder Facility Action Builder ID 1-->"+builderId);
		
		Vector streamVec = rootMaster.getBuilderCityList(getDataSource(request, "main"), builderId);
		
		System.out.println("Builder City Vector=>"+streamVec);
	    String strStream = getCourseStreamList(builderId, 1, streamVec);
	    
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strStream);
	    out.flush();
		return null;
	}
	private String getCourseStreamList(int numSid, int numCId, Vector streamVec)
	{
		
		System.out.println("Builder Facility Action Builder ID 2-->"+numSid);
		String strResult ="<select name=\"builderCity\" onchange=\"retrieveBuilderFacilityURL('setbuilderFacility.do?builderId="+numSid+"&cityId='+this.value)\">";
		//onchange=\"retrieveCollegeURL('collegeList.do?collcountry="+numCId+"&collstate="+numSId+"&collcity='+this.value), showOth();\">";
		for(int i=0; i<streamVec.size(); i++)
		{
			Vector tempVec = (Vector)streamVec.elementAt(i);
			int numStateId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			String strSName = tempVec.elementAt(1).toString().trim();
			System.out.println("numStateId 2309209-1"+numStateId);
			System.out.println("strSName23092009-2 "+strSName);
			if(numSid == numStateId)
			{
				strResult += "<option value=\"" + numStateId + "\" Selected>" + strSName + "</option>";
			}
			else
			{
				strResult += "<option value=\"" + numStateId + "\">" + strSName + "</option>";
			}
		}
		System.out.println("strResult--23092009 2 In City Action New=>"+strResult);
		strResult += "</select>";
		return strResult;
	}

}
