package com.mm.struts.indv.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.struts.indv.form.SearchQueriesForm;

import com.mm.core.master.RootMaster;

public class BrandForCatAction extends Action {
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
		
				
		int numCatId = (request.getParameter("category")!=null) ? Integer.parseInt(request.getParameter("category").trim()) : 0;
		System.out.println("numCatId.......BrandForCatAction............"+numCatId);
		int numbrandId = (request.getParameter("brand")!=null) ? Integer.parseInt(request.getParameter("brand").trim()) : 0;
		Vector brandVec = rootMaster.getBrandByCat( getDataSource(request, "indv"), numCatId);
		String strBrand = getBrnadList(numCatId,numbrandId, brandVec);
		
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strBrand);
	    out.flush();
		return null;
	}
	
	private String getBrnadList(int numCatId,int brandId, Vector brandVec)
	{
		String strResult ="<select name=\"brand\" class=\"input_multi_txt_box\" tabindex=\"6\" , setBrand()\">";
		for(int i=0; i<brandVec.size(); i++)
		{
			Vector tempVec = (Vector)brandVec.elementAt(i);
			int numBrandId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			String strBrandName = tempVec.elementAt(1).toString().trim();
			//System.out.println("numPlaceId "+numPlaceId);
			//System.out.println("strPName "+strPName);
			if(brandId == numBrandId)
			{
				strResult += "<option value=\"" + numBrandId + "\" Selected>" + strBrandName + "</option>";
			}
			else
			{
				strResult += "<option value=\"" + numBrandId + "\">" + strBrandName + "</option>";
			}
		}
		strResult += "</select>";
		return strResult;
	}

}
