package com.mm.struts.entp.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.EntpMaster;

/** 
 * MyEclipse Struts
 * Creation date: 08-01-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class CommunityUsergroupdetailAction extends Action {
	
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
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		//System.out.println(" ....................... User Group Detail Action >> ");
		
		int numCompId = 0; 
		String strType = (request.getParameter("typ")!=null) ? request.getParameter("typ").trim() : "Add New";
		int numUrId = (request.getParameter("grpid")!=null) ? Integer.parseInt(request.getParameter("grpid").trim()) : 0;
		int numTempCount = (request.getParameter("numCount") != null) ? Integer.parseInt(request.getParameter("numCount").trim()) : 0;
		//System.out.println("Temp Count in User Group Detail Action >> "+numTempCount);
		EntpMaster entpMaster = new EntpMaster();
		
		Vector listVec= entpMaster.getCommunityRightsGroupDetails(getDataSource(request, "entp"), numCompId, numUrId, strType);
		Vector completeVec = getAllRightsList(listVec);
		
		//System.out.println("listVec in User Group Detail Action >> "+listVec);
		//System.out.println("completeVec in User Group Detail Action >> "+completeVec);
		
		String strUGroup = getUserGroup(completeVec, strType, numUrId, numTempCount);
		
		//System.out.println("strUGroup in User Group Detail Action >> "+strUGroup);
		
		//System.out.println(strUGroup);
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    //out.println(strtblSubCategory);
	    out.write(strUGroup);
	    out.flush();
		return null;
	}
	
	private String getUserGroup(Vector completeVec, String strType, int numUrId, int numTempCount)
	{	
		//System.out.println("in.................... >> ");
		Vector tempVec1 = (Vector)completeVec.elementAt(0);
		String strTemp = "";
		if(strType.trim().equalsIgnoreCase("Delete") || strType.trim().equalsIgnoreCase("Details"))
		{
			strTemp = " disabled=\"true\"";
		}
		
		
         
		String strResult = "<table width=\"100%\"  border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">";
		
		
		
		strResult += "<tr height=\"30\">";
		strResult += "<td align=\"right\" class=\"bold\">Rights Group Name&nbsp;&nbsp;</td>";
		strResult += "<td colspan=\"2\" align=\"left\">";
		strResult += "<input name=\"grpname\" type=\"text\" id=\"grpname\" value=\""+tempVec1.elementAt(1).toString().trim()+"\""+strTemp+">";
		strResult += "</td>";
		strResult += "</tr>";
		strResult += "<tr height=\"30\">";
		strResult += "<td align=\"right\" class=\"bold\">Rights&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";
		strResult += "<td></td>";
		strResult += "<td align=\"left\" class=\"bold\">&nbsp;&nbsp;&nbsp;Rights In Group </td>";
		strResult += "</tr>";
		strResult += "<tr>";
		strResult += "<td align=\"right\" >";
		strResult += "<select name=\"permissionList\" size=\"10\"  id=\"permissionList\" style=\"width=150\""+strTemp+">";
		for(int i=1; i<completeVec.size(); i++)
		{
			Vector tempVec = (Vector)completeVec.elementAt(i);
			if(Integer.parseInt(tempVec.elementAt(0).toString().trim()) == 0)
			{
				strResult += "<option value=\""+tempVec.elementAt(1).toString().trim()+"\">"+tempVec.elementAt(2).toString().trim()+"</option>";
			}
		}
		strResult += "</select>";
		strResult += "</td>";
        strResult += "<td align=\"center\" valign=\"middle\"><input type=\"button\"  name=\"Button\" value=\"&gt;&gt;\" onClick=\"addPermission()\""+strTemp+">";
		strResult += "&nbsp;<BR><BR><BR>";
        strResult += "<input type=\"button\"  name=\"Submit2\" value=\"&lt;&lt;\" onClick=\"removePermission()\""+strTemp+"></td>";
        strResult += "<td align=\"left\">";
        strResult += "<select name=\"allotedPermissionList\" size=\"10\"  id=\"allotedPermissionList\" style=\"width=150\""+strTemp+">";
        for(int i=1; i<completeVec.size(); i++)
		{
			Vector tempVec = (Vector)completeVec.elementAt(i);
			if(Integer.parseInt(tempVec.elementAt(0).toString().trim()) == 1)
			{
				strResult += "<option value=\""+tempVec.elementAt(1).toString().trim()+"\">"+tempVec.elementAt(2).toString().trim()+"</option>";
			}
		}
        strResult += "</select>";
        strResult += "</td>";
        strResult += "</tr>";
        
        numTempCount = numTempCount+1;
                
        strResult += "<tr height=\"30\">";
        strResult += "<td align=\"right\"><input name=\"grpid\" type=\"hidden\" id=\"grpid\" value=\""+numUrId+"\"><input type=\"hidden\" name=\"tempcount\" value=\""+numTempCount+"\"></td>";
        strResult += "<td><input name=\"cmdtyp\" type=\"hidden\" id=\"cmdtyp\" value=\""+strType+"\"><input type=\"hidden\" name=\"permissions\" value=\"\"></td>";
        strResult += "<td></td>";
        strResult += "</tr>";
	    strResult += "</table>";
		
	    //System.out.println("out.................... >> "+strResult);
		return strResult;
	}
	
	private Vector getAllRightsList(Vector listVec)
	{
		Vector<Vector> resultVec = new Vector<Vector>();
		Vector<String> dataVec = new Vector<String>();
		dataVec.add(listVec.elementAt(0).toString().trim());
		dataVec.add(listVec.elementAt(1).toString().trim());
		resultVec.add(dataVec);
		
		Vector<String> tempVec = new Vector<String>();
		tempVec.add(listVec.elementAt(2).toString().trim());
		tempVec.add("accmanagement");
		tempVec.add("Account Management");
		resultVec.add(tempVec);
		
		Vector<String> tempVec1 = new Vector<String>();
		tempVec1.add(listVec.elementAt(3).toString().trim());
		tempVec1.add("users");
		tempVec1.add("Users Detail Of Others");
		resultVec.add(tempVec1);
		
		Vector<String> tempVec2 = new Vector<String>();
		tempVec2.add(listVec.elementAt(4).toString().trim());
		tempVec2.add("usercreation");
		tempVec2.add("User Creation");
		resultVec.add(tempVec2);
		
		Vector<String> tempVec3 = new Vector<String>();
		tempVec3.add(listVec.elementAt(5).toString().trim());
		tempVec3.add("userdeletion");
		tempVec3.add("User Deletion");
		resultVec.add(tempVec3);
		
		Vector<String> tempVec4 = new Vector<String>();
		tempVec4.add(listVec.elementAt(6).toString().trim());
		tempVec4.add("usermodification");
		tempVec4.add("User Modification");
		resultVec.add(tempVec4);
		
		Vector<String> tempVec5 = new Vector<String>();
		tempVec5.add(listVec.elementAt(7).toString().trim());
		tempVec5.add("change_password");
		tempVec5.add("Change Password Of Others");
		resultVec.add(tempVec5);
		
		Vector<String> tempVec6 = new Vector<String>();
		tempVec6.add(listVec.elementAt(8).toString().trim());
		tempVec6.add("complaintmanagement");
		tempVec6.add("Complaint Allotment");
		resultVec.add(tempVec6);
		
			
		Vector<String> tempVec10 = new Vector<String>();
		tempVec10.add(listVec.elementAt(9).toString().trim());
		tempVec10.add("complaints");
		tempVec10.add("Complaints");
		resultVec.add(tempVec10);
		
		
		return resultVec;
	}

}
