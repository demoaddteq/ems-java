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

public class MentoringUserAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		RootMaster rootMaster= new RootMaster();
		String cuid = (request.getParameter("cuid").length()!=0) ? request.getParameter("cuid").trim() : request.getParameter("CampusyogiId").trim();
		String Qtype = (request.getParameter("Qtype")!=null) ? request.getParameter("Qtype").trim() : "";
		String mentor_cat = (request.getParameter("mcat")!=null) ? request.getParameter("mcat").trim() : "";
		int numconId = (request.getParameter("contactper")!=null) ? Integer.parseInt(request.getParameter("contactper").trim()) : 0;
		
		
		String selcetType="1";
		if(Qtype.length()==0)
		{
			selcetType="-1";
		}
		
		int intCuid = Integer.parseInt(cuid);
		////System.out.println("Qtype>>>>>>>>>>>"+mentor_cat);
		Vector conVec = rootMaster.getCorpoUserListforAll(getDataSource(request, "student"), intCuid,selcetType,Qtype.split(","),mentor_cat);
		//////////////System.out.println("brandVec>>>>>>>>>>>"+brandVec);
		String strBrand = getCorpoUserList(numconId, conVec,mentor_cat);
		
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strBrand);
	    out.flush();
		return null;
	}
	
	private String getCorpoUserList(int brandId, Vector conVec,String mentor_cat)
	{
		String strResult ="<select name=\"cons\"  tabindex=\"3\"  onchange=\" retrieveUserShortProURL('shortPro.do?mcat="+mentor_cat+"&compid='+this.value+'&pType=user'),hidebutton()\" >";
		for(int i=0; i<conVec.size(); i++)
		{
			Vector tempVec = (Vector)conVec.elementAt(i);
			int numBrandId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			String strfName = tempVec.elementAt(1).toString().trim();
			String strlName = tempVec.elementAt(2).toString().trim();
			
			////////////////System.out.println("numPlaceId "+numPlaceId);
			////////////System.out.println("strPName "+strPName);
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
