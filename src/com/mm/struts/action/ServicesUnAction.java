/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class ServicesUnAction extends Action {
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
		HttpSession session =request.getSession();
		String facilityId = (String)session.getAttribute("fId");
		String userId = (String)session.getAttribute("uId");
		System.out.println("User Type is "+userId);
		
		
		RootMaster rootMaster= new RootMaster();
	 System.out.println("industryCategory......categoryAction......"+facilityId);
		
		
		Vector categoryVec = rootMaster.getUnServices(getDataSource(request, "student"),userId);
		System.out.println("Facility vector is"+categoryVec);
	String strCat = getCategoryList(facilityId, categoryVec);
	    System.out.println("Category list "+strCat);
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	   out.println(strCat);
	    out.flush();
		return null;
		
	}
	
	private String getCategoryList(String industryCategory, Vector categoryVec)
	{
		String strResult ="<select name=\"companyTemplate\"   tabindex=\"15\" onchange=\"outputSelected(this.form.companyTemplate)\">";
				
String strTemCat[] = industryCategory.split(", ");
		
		
		for(int i=0; i<categoryVec.size(); i++)
		{	
			int flag = 0;
			Vector tempVec = (Vector)categoryVec.elementAt(i);
			String numCatId = tempVec.elementAt(0).toString().trim();
			String strCName = tempVec.elementAt(1).toString().trim();
			System.out.println("Length is "+strTemCat.length);
			
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