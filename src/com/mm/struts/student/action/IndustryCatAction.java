

/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.student.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.core.master.RootMaster;

/** 
 * MyEclipse Struts
 * Creation date: 07-02-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class IndustryCatAction extends Action {
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
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		RootMaster rootMaster= new RootMaster();
		String industryCategory = (request.getParameter("indCategory1")!=null) ? request.getParameter("indCategory1") : "1";
		 ////System.out.println("industryCategory.....in std.categoryAction......"+industryCategory);
		
		
		Vector categoryVec = rootMaster.getCategories(getDataSource(request, "student"));
	    String strCat = getCategoryList(industryCategory, categoryVec);
	    ////////System.out.println("Category list "+strCat);
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strCat);
	    out.flush();
		return null;
		
	}
	
	private String getCategoryList(String industryCategory, Vector categoryVec)
	{
		String strResult ="<select name=\"icat\"   onchange=\"doTaskCat(+this.value);\" >";
		
String strTemCat[] = industryCategory.split(", ");
		
		
		for(int i=0; i<categoryVec.size(); i++)
		{	
			int flag = 0;
			Vector tempVec = (Vector)categoryVec.elementAt(i);
			String numCatId = tempVec.elementAt(0).toString().trim();
			String strCName = tempVec.elementAt(1).toString().trim();
			
			for(int j =0; j<strTemCat.length; j++){
				
				if(numCatId.equalsIgnoreCase(strTemCat[j])){
					flag = 1;	
					
				}
				
			}
			
			if(flag==1)
			{
				strResult += "<option value=\"" + numCatId + "\" Selected>" + strCName + "</option>";
				
			}
			
			else
			{
				strResult += "<option value=\"" + numCatId + "\">" + strCName + "</option>";
				
			}
			
		}
		strResult += "</select>";
		//System.out.println("strResult.....in std.categoryAction......"+strResult);
		return strResult;
	}
}