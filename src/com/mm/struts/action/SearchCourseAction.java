/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Vector;
import com.mm.core.master.RootMaster;

/** 
 * MyEclipse Struts
 * Creation date: 06-30-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class SearchCourseAction extends Action {
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
		// TODO Auto-generated method stub
		String StrCourseId = "0";
		
				
		int numCourseId=Integer.parseInt(StrCourseId);
		Vector CourseVec = rootMaster.getCourse(getDataSource(request,"main"));
		System.out.println("CourseVec....SearchCourseAction........ "+CourseVec);
	    String strCourse = getCourseList(numCourseId, CourseVec);
	   
	    
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strCourse);
	    out.flush();
		return null;
	}
	
	private String getCourseList(int numCid, Vector CountryVec)
	{
		String strResult ="<select name=\"txtCourse\" >";
		
		for(int i=0; i<CountryVec.size()-1; i++)
		{
			Vector tempVec = (Vector)CountryVec.elementAt(i);
			
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
		System.out.println("strResult............ "+strResult);
		
		return strResult;
	}
	
}