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
public class ScheduleSubCategoryAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		System.out.println("In ScheduleSubCategoryAction Action");
		RootMaster rootMaster=new RootMaster();
		String category_Id = (request.getParameter("categoryId")!=null) ? (request.getParameter("categoryId").trim()) : "0";
		int catId=Integer.parseInt(category_Id);
		Vector streamVec = rootMaster.getSubCategoryList(getDataSource(request, "corpo"), catId);
	    String strStream = getCourseStreamList(1, catId, streamVec);
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strStream);
	    out.flush();
		return null;
	}
	private String getCourseStreamList(int numSid, int catId, Vector streamVec)
	{
		
		String strResult ="<select name=\"subCategory\" onchange=\"retrieveStaffForSceduleURL('setStaffForSchedule.do?categoryId="+catId+"&suCategoryId='+this.value)\">";
		for(int i=0; i<streamVec.size(); i++)
		{
			Vector tempVec = (Vector)streamVec.elementAt(i);
			int numStateId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			String strSName = tempVec.elementAt(1).toString().trim();
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
