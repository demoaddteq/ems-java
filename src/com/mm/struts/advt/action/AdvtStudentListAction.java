package com.mm.struts.advt.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.*;

import javax.sql.DataSource;

public class AdvtStudentListAction extends Action {
	
	RootMaster rootMaster= new RootMaster();
	IndvMaster indvMaster=new IndvMaster();
	
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
		// TODO Auto-generated method stub
		String StrCourseId = (request.getParameter("college_id")!=null) ? (request.getParameter("college_id").trim()) : "0";
		String stream_id = (request.getParameter("stream_id")!=null) ? (request.getParameter("stream_id").trim()) : "0";
		String uid = (request.getParameter("uid")!=null) ? (request.getParameter("uid").trim()) : "0";
		System.out.println("StrCourseId............ "+StrCourseId);
		
		
		int tabindex = 0;
		
		int numCourseId=Integer.parseInt(StrCourseId);
		
		
		Vector CourseVec = indvMaster.getStudentList(getDataSource(request,"advt"),StrCourseId,stream_id);
		System.out.println("CourseVec............ "+CourseVec);
		String strCourse ="";
		
	    strCourse = getCourseList(Integer.parseInt(uid), CourseVec,numCourseId,stream_id);
	   
		
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strCourse);
	    out.flush();
		return null;
	}
	
	private String getCourseList(int numCid, Vector CountryVec,int numCourseId,String stream_id)
	{
		String strResult ="<table width=\"100%\"><tr><td width=\"25%\" align=\"right\"><span class=\"bold\" >Student Name</span></td><td  width=\"75%\"><select name=\"student\" onchange=\"retrieveStudentURL('stdList.do?college_id="+numCourseId+"&stream_id="+stream_id+"&uid='+this.value)\"  >";
		
		
		for(int i=0; i<CountryVec.size()-1; i++)
		{
			Vector tempVec = (Vector)CountryVec.elementAt(i);
			
			int numCIdTemp=Integer.parseInt(tempVec.elementAt(0).toString().trim());			
			String strFName = tempVec.elementAt(1).toString().trim();
			String strLName = tempVec.elementAt(2).toString().trim();
			
			if(numCid == numCIdTemp)
			{
				strResult += "<option value=\"" + numCIdTemp + "\" Selected>" + strFName + "&nbsp" + strLName + "</option>";
			}
			else
			{
				strResult += "<option value=\"" + numCIdTemp + "\">" + strFName + "&nbsp" + strLName + "</option>";
			}
		}
		
			
		
		strResult += "</select></td></tr>";
		if(CountryVec.size()==2)
		{
			strResult +="<tr><td width=\"25%\" align=\"right\"><span class=\"bold\" ></span></td><td  width=\"75%\"  align=\"left\">";
			strResult+="<span class=\"bold\" > &nbsp;&nbsp;No Student register under this College.<br>&nbsp;&nbsp;plz Select another College to write Your Reviews.</span></td></tr></table>";
		
		}
		System.out.println("CountryVec............ "+CountryVec.size());
		
		return strResult;
	}
}
