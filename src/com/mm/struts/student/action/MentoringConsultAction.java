package com.mm.struts.student.action;

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

public class MentoringConsultAction extends Action {
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
		RootMaster rootMaster= new RootMaster();
		HttpSession session = request.getSession();
		String mcat = (request.getParameter("mcat")!=null) ? request.getParameter("mcat").trim() : "Selecte";
		String Qtype = (request.getParameter("Qtype")!=null) ? request.getParameter("Qtype") : "Companies";
		
		int CampusyogiId = (request.getParameter("CampusyogiId")!=null) ? Integer.parseInt(request.getParameter("CampusyogiId").trim()) : Integer.parseInt((String)session.getAttribute("CampusyogiId"));
			int numcomId = (request.getParameter("comid")!=null) ? Integer.parseInt(request.getParameter("comid").trim()) : CampusyogiId;
		////System.out.println("mcat...ConsultAction........."+mcat);
		////System.out.println("numcomId....company...ConsultAction....."+numcomId);
		
		int mcatInt = -1;
		
		if(mcat.equalsIgnoreCase("Personality"))
		{
			mcatInt = 1;
		}
		else if(mcat.equalsIgnoreCase("Softskills"))
		{
			mcatInt = 2;
		}
		else if(mcat.equalsIgnoreCase("Technical"))
		{
			mcatInt = 3;
		}
		else if(mcat.equalsIgnoreCase("Aptitude"))
		{
			mcatInt = 4;
		}
		
		//System.out.println("numbrandId>>>>>>>>>>>"+mcatInt);
		Vector conVec=new Vector();
		if (Qtype.equalsIgnoreCase("Advertiser"))
		{
			conVec = rootMaster.getCollege(getDataSource(request, "student"), mcatInt);
		}else
		{
			conVec = rootMaster.getCorpoList(getDataSource(request, "student"), mcatInt);
		}
		 
		
		////////System.out.println("brandVec>>>>>>>>>>>"+brandVec);
		String strBrand = getCorpoList(numcomId, conVec ,mcatInt);
		
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strBrand);
	    out.flush();
		return null;
	}
	
	private String getCorpoList(int brandId, Vector conVec,int mcatInt)
	{
		String strResult ="<select name=\"brand\"  tabindex=\"3\" onchange=\"retrieveCorpoUserListURL('mentoringUser.do?mcat="+mcatInt+"&cuid='+this.value)\">";
		for(int i=0; i<conVec.size(); i++)
		{
			Vector tempVec = (Vector)conVec.elementAt(i);
			int numBrandId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			String strfName = tempVec.elementAt(1).toString().trim();
			
			////////System.out.println("numPlaceId "+numPlaceId);
			////////System.out.println("strPName "+strPName);
			if(brandId == numBrandId)
			{
				strResult += "<option value=\"" + numBrandId + "\" Selected>" + strfName + "</option>";
			}
			else
			{
				strResult += "<option value=\"" + numBrandId + "\" >" + strfName + "</option>";
			}
		}
		strResult += "</select>";
		return strResult;
	}
}