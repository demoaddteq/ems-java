package com.mm.struts.advt.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.core.master.RootMaster;

public class DepartmentAction extends Action {
	
	
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String StrCourseId = (request.getParameter("depId")!=null) ? (request.getParameter("depId").trim()) : "0";
		System.out.println("StrCourseId ..dc..................."+StrCourseId);	
		
		String type = (request.getParameter("type")!=null) ? request.getParameter("type") : "college";
		RootMaster rootMaster= new RootMaster();
		int numdepId=Integer.parseInt(StrCourseId);
		Vector CourseVec = rootMaster.getdepCategory(getDataSource(request, "advt"), type);
		///System.out.println("CourseVec............ "+CourseVec);
	    String strCourse = getDepList(numdepId, CourseVec);
	    System.out.println("strCourse............ "+strCourse);
	    ///String strResult ="<select name=\"department\" onchange=\"retrieveDesignationURL('designation.do?depId='+this.value)\">";
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strCourse);
	    out.flush();
		return null;
	}
	
	private String getDepList(int numCid, Vector CountryVec)
	{
		String strResult ="<select name=\"department\" onchange=\"retrieveDesignationURL('designation.do?depId='+this.value),showOth()\">";
		
		for(int i=0; i<CountryVec.size(); i++)
		{
			Vector tempVec = (Vector)CountryVec.elementAt(i);
			//System.out.println("CourseVec....list........ "+tempVec.elementAt(0).toString().trim());
			int numCIdTemp=Integer.parseInt(tempVec.elementAt(0).toString().trim());			
			String strCName = tempVec.elementAt(1).toString().trim();
			
			if(numCid == numCIdTemp)
			{
				strResult += "<option value=\"" + numCIdTemp + "\" Selected>" + strCName + "</option>";
			}
			else
			{
				strResult += "<option value=\"" + numCIdTemp + "\">" + strCName + "</option>";
			}
		}
		strResult += "</select>";
		//System.out.println("strResult............ "+strResult);
		
		return strResult;
	}
	
	
	

}
