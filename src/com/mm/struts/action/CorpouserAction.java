
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

public class CorpouserAction extends Action {

	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		RootMaster rootMaster= new RootMaster();
		String cuid = (request.getParameter("cuid")!=null) ? request.getParameter("cuid").trim() : "";
		int numconId = (request.getParameter("contactper")!=null) ? Integer.parseInt(request.getParameter("contactper").trim()) : 0;
		//System.out.println("cuid....companId.... contactAction......"+cuid);
		//System.out.println("numconId....contactpartion...contactAction......."+numconId);
		
		int intCuid = Integer.parseInt(cuid);
				
		//////System.out.println("numbrandId>>>>>>>>>>>"+numbrandId);
		Vector conVec = rootMaster.getCorpoUserList(getDataSource(request, "main"), intCuid);
		//////System.out.println("brandVec>>>>>>>>>>>"+brandVec);
		String strBrand = getCorpoUserList(numconId, conVec);
		
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strBrand);
	    out.flush();
		return null;
	}
	
	private String getCorpoUserList(int brandId, Vector conVec)
	{
		String strResult ="<select name=\"cons\"  tabindex=\"3\" >";
		for(int i=0; i<conVec.size(); i++)
		{
			Vector tempVec = (Vector)conVec.elementAt(i);
			int numBrandId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			String strfName = tempVec.elementAt(1).toString().trim();
			String strlName = tempVec.elementAt(2).toString().trim();
			
			//////System.out.println("numPlaceId "+numPlaceId);
			//////System.out.println("strPName "+strPName);
			if(brandId == numBrandId)
			{
				strResult += "<option value=\"" + numBrandId + "\" Selected>" + strfName + " " + strlName + "</option>";
			}
			else
			{
				strResult += "<option value=\"" + numBrandId + "\" >" + strfName + " " + strlName + "</option>";
			}
		}
		strResult += "</select>";
		return strResult;
	}
}
