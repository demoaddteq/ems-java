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

public class CollegecategoryForSearchAction extends Action {
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
		String industryCategory = (request.getParameter("indCategory1")!=null) ? request.getParameter("indCategory1") : " ";
		 ////////System.out.println("industryCategory......categoryAction......"+industryCategory);
		
		
		Vector categoryVec = rootMaster.getCollegeCategories(getDataSource(request, "advt"));
	    String strCat = getCategoryList(industryCategory, categoryVec);
	    //////System.out.println("Category list "+strCat);
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strCat);
	    out.flush();
		return null;
		
		
	}
	
	private String getCategoryList(String industryCategory, Vector categoryVec)
	{
		String strResult ="<select name=\"companyTemplate\"  >";
		
String strTemCat[] = industryCategory.split(", ");
		
		
		for(int i=0; i<categoryVec.size()-1; i++)
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
		return strResult;
	}

}
