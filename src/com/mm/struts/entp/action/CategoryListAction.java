/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.entp.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.core.master.IndvMaster;
import com.mm.core.master.EntpMaster;

/** 
 * MyEclipse Struts
 * Creation date: 07-02-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */

public class CategoryListAction extends Action {
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
		IndvMaster indvMaster= new IndvMaster();
		EntpMaster entpMaster = new EntpMaster();
		
		int numUid = (request.getParameter("uid")!=null) ? Integer.parseInt(request.getParameter("uid")) : 0;
		int numCid = 0;
		String allotedCat = entpMaster.getCoreUserCatId(getDataSource(request, "entp"),numUid);
			Vector categoryVec = entpMaster.getCategories(getDataSource(request, "entp"),allotedCat);
			 ////System.out.println("categoryVec>>>>>>>>>>"+categoryVec);
		    String strCat = getCategoryList(numCid, categoryVec);
		    ////System.out.println("Category list "+strCat);
		    response.setContentType("text/html;charset=ISO-8859-1");
		    PrintWriter out = response.getWriter();
		    out.println(strCat);
		    out.flush();
			return null;
		
	}
	
	private String getCategoryList(int numCid, Vector categoryVec)
	{
		String strResult ="<select name=\"category\"  tabindex=\"2\" onchange=\"retrieveBrandURL('brand.do?category='+this.value), setCategory()\">";
		
		for(int i=0; i<categoryVec.size(); i++)
		{
			Vector tempVec = (Vector)categoryVec.elementAt(i);
			int numCatId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			String strCName = tempVec.elementAt(1).toString().trim();
			if(numCid == numCatId)
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