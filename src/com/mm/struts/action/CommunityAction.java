/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.sql.DataSource;

import com.mm.core.master.RootMaster;

/** 
 * MyEclipse Struts
 * Creation date: 06-30-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class CommunityAction extends Action {
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
		String StrCId = ((String)request.getParameter("commuId")!=null) ? (String)request.getParameter("commuId") : "0";
		
		
		Vector communityVec = rootMaster.getCommunity(getDataSource(request, "main"));
		////System.out.println("strCommunitystrCommunity>>>>>>>>>>>>>"+communityVec);
		String strCommunity = getCommunityList(StrCId, communityVec);
		////System.out.println("strCommunitystrCommunity>>>>>>>>>"+strCommunity);
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strCommunity);
	    out.flush();
		return null;
	}
	
	private String getCommunityList(String StrCId, Vector communityVec)
	{
		String strResult ="<select name=\"community\"  tabindex=\"19\" >";
		
		for(int i=0; i<communityVec.size(); i++)
		{
			Vector tempVec = (Vector)communityVec.elementAt(i);
			String CommunityId = tempVec.elementAt(0).toString().trim();			
			////System.out.println("strCommunitysttempVec>>>>>>>>>>"+tempVec);
			String strCName = tempVec.elementAt(1).toString().trim();
		
			
			if(StrCId.equalsIgnoreCase(CommunityId))
			{
				strResult += "<option value=\"" + CommunityId + "\" Selected>" + strCName + "</option>";
				
			}
			else
			{
				strResult += "<option value=\"" + CommunityId + "\">" + strCName + "</option>";
			}
		}
		strResult += "</select>";
		return strResult;
	}
}