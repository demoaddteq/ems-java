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


import com.mm.core.master.*;
public class UserlistAction extends Action {
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
		EntpMaster entpMaster = new EntpMaster();		
	
		HttpSession session = request.getSession();
		
		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		
		////System.out.println("remander..........."+remander);
		String compId =(String)session.getAttribute("compId");
		String uId =(String)session.getAttribute("uId");		
		Vector allRights =(Vector)session.getAttribute("allRights");
		String adminFlag = allRights.elementAt(18).toString();
		
		String changePassword = allRights.elementAt(3).toString();
		String users	 = allRights.elementAt(10).toString();
		String userDeletion = allRights.elementAt(12).toString();			
		String userModification = allRights.elementAt(13).toString();
		
		Vector<String> rigthVec = new Vector<String>();
		rigthVec.add(changePassword);
		rigthVec.add(users);
		rigthVec.add(userDeletion);
		rigthVec.add(userModification);
		
		
		
		Vector<String> infoVec = new Vector<String>();
		infoVec.add(uId);
		infoVec.add(adminFlag);
		
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "loginname";
		String strSOrder = "";
		String strLoginIdOrder = "asc";
		String strUnameOrder = "asc";
		
		if(request.getParameter("sby")!=null)
		{
			if(request.getParameter("liorder")!=null)
			{
				if(request.getParameter("liorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strLoginIdOrder = "desc";
				}
				else if (request.getParameter("liorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strLoginIdOrder = "asc";
				}
			}
			
			if(request.getParameter("unorder")!=null)
			{
				if(request.getParameter("unorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strUnameOrder = "desc";
				}
				else if (request.getParameter("unorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strUnameOrder = "asc";
				}
			}
			
			
		}
		String strFShortBy = strShortBy+" "+strSOrder;
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 20;
		
		
		Vector<String> paramVec1 = new Vector<String>();
		paramVec1.add(compId);
		int totalRow = entpMaster.getUserCount(getDataSource(request, "entp"), paramVec1);
		
		//int totalRow=500;
		String strPageHtml = getPages(minVal, maxVal, totalRow);
		
		Vector<String> paramVec = new Vector<String>();
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));
		paramVec.add(compId);
		paramVec.add(strFShortBy);		
		Vector userVec = entpMaster.getUserList(getDataSource(request, "entp"), paramVec);
		
		
		String strUsers = getUserList(strLoginIdOrder, strUnameOrder, minVal, strPageHtml, userVec, infoVec, rigthVec, remander, uId);
		//System.out.println("Users "+strUsers);
		/**
	     * setContentType - text/html to write String on page.
	     * PrintWriter - This line use to get writer to write on page.
	     * out.println - use to write string. 
	     * 
	     */
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strUsers);
	    out.flush();
		return null;
	}
	
	private String getUserList(String strLoginIdOrder, String strUnameOrder, int minVal, String strPageHtml, Vector userVec, Vector infoVec, Vector rigthVec, int remander, String uId)
	{
		
		
		String strValue = "<table width=\"100%\">";
		if(userVec.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"400\" valign=\"center\" colspan=\"7\" class=\"bold\"> No Data Found</td>";
			strValue += "</tr>";
		}
		else
		{
		strValue += "<tr >";
		strValue += "<td colspan=\"7\" align=\"right\">Page Number  "+strPageHtml+"</td>";		
		strValue += "</tr>";
		strValue += "<tr>";
        strValue += "<td width=\"100%\">";
	    strValue += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
		
		 	strValue += "<tr>";	
		    strValue += "<td height=\"5\" colspan=\"7\" align=\"right\"><hr color=\"#ff722b\"></td>";	
		    strValue += "</tr>";
		    //strComIdOrder
		    //strCTitelOrder
		    //strDateOrder
		    //asc, desc
		    String strLoginIdSymbol = (strLoginIdOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveEntpUlistURL('userlist.do?sby=loginname&numMin="+ minVal+"&liorder="+strLoginIdOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveEntpUlistURL('userlist.do?sby=loginname&numMin="+ minVal+"&liorder="+strLoginIdOrder+"');\">";
		    String strUnameSymbol = (strUnameOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveEntpUlistURL('userlist.do?sby=first_name&numMin="+ minVal+"&unorder="+strUnameOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveEntpUlistURL('userlist.do?sby=first_name&numMin="+ minVal+"&unorder="+strUnameOrder+"');\">";
		    
		    
		    strValue += "<tr height=\"20\" >";	
		    strValue += "<th class=\"bold\">&nbsp;S. No.</th>";
		    strValue += "<th class=\"bold\">Login ID&nbsp;"+strLoginIdSymbol+"</th>";	    
		    strValue += "<th class=\"bold\">User Name&nbsp;"+strUnameSymbol+"</th>";	
		    strValue += "<th class=\"bold\">&nbsp;</th>";
		    strValue += "<th class=\"bold\">&nbsp;</th>";
		    strValue += "<th class=\"bold\">&nbsp;</th>";
		    strValue += "<th class=\"bold\">&nbsp;</th>";
		    strValue += "</tr>";	
		    strValue += "<tr>";	
		    strValue += "<td height=\"5\" colspan=\"7\" align=\"right\"><hr color=\"#ff722b\"></td>";	
		    strValue += "</tr>";	
		
		
			for(int i=0; i<userVec.size(); i++)
			{
				int j = i+1;
		    	int numTotal = j%2;
		    	String strColor = (numTotal==0) ? "#E6E6E6" : "#F0F0F0";
		    	String strImg = (numTotal==0) ? "detail_gray.gif" : "detail_white.gif";
				 Vector tempVec = (Vector)userVec.elementAt(i);
				 
				 int numUCompanyId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
				 String strLIdName = tempVec.elementAt(1).toString().trim();
			     String strUName = tempVec.elementAt(2).toString().trim();
			     String strUid = tempVec.elementAt(3).toString().trim();
			     int numUid = Integer.parseInt(strUid);
				 
			     strValue += "<tr height=\"25\" align=\"center\" bgcolor=\""+strColor+"\">";	
				strValue += "<td width=\"10%\"> "+j+" </td>"; 
				strValue += "<td width=\"15%\"  nowrap> "+strLIdName+" </td>";
				strValue += "<td width=\"15%\"  nowrap> "+strUName+" </td>";
				strValue += "<td width=\"15%\"  valign=\"middle\" nowrap class=\"bold\">"; 
				strValue += "<a href=\"coreUserdetail.jsp?task=Details&userid="+numUid+"\"class=\"bold\" >Details</a></td>";		
				strValue += "<td width=\"15%\"  height=\"20\" nowrap class=\"bold\">";
				if((infoVec.elementAt(0).toString().equalsIgnoreCase(strUid))||rigthVec.elementAt(0).toString().equalsIgnoreCase("1")){
				strValue += "<a href=\"changepass.jsp?userid="+numUid+"\"class=\"bold\" >Change Password</a></td>";	
				}else{
				strValue += "Change Password</td>";	
				}
				strValue += "<td width=\"15%\"   align=\"center\" valign=\"middle\" nowrap class=\"bold\">";				
				if((!infoVec.elementAt(0).toString().equalsIgnoreCase(strUid))&&rigthVec.elementAt(2).toString().equalsIgnoreCase("1")){
				strValue += "<a href=\"userGetdetails.do?task=Delete&userid="+numUid+"\" class=\"bold\" >Delete</a></td>";
				}else{
				strValue += "Delete</td>";
				}
				
				strValue += "<td width=\"15%\"   nowrap class=\"bold\">";
				
				////System.out.println("uId.............."+uId);
				////System.out.println("strUid..........."+strUid);
				
				if((!uId.equalsIgnoreCase(strUid))){
				if((infoVec.elementAt(0).toString().equalsIgnoreCase(strUid))||rigthVec.elementAt(3).toString().equalsIgnoreCase("1")){
				strValue += "<a href=\"userGetdetails.do?task=Modify&userid="+numUid+"\"class=\"bold\" >Modify</a></td>";
				
				////System.out.println("...........IF............");
				}else{
				strValue += "Modify</td>";
				////System.out.println("...........else...........");
				}
				}else{
					strValue += "Modify</td>";					
				}
					
				strValue += "</tr>";				
				
			}
			strValue += "<tr>";	
		    strValue += "<td height=\"20\" colspan=\"7\" align=\"right\"><hr color=\"#ff722b\"></td>";	
		    strValue += "</tr>";
		    strValue += "<tr >";
			strValue += "<td colspan=\"7\" align=\"right\">Page Number  "+strPageHtml+"</td>";		
			strValue += "</tr>";
			strValue += "</table>";
			strValue += "</td>";	
			strValue += "</tr>";
		}
		if(remander==0)
		{
			strValue += "<tr style=\"display:none\" align=\"center\">";
			strValue += "<td height=\"50\" colspan=\"7\"></td>";
			strValue += "</tr>";
		}
		strValue += "</table>";
		
		return strValue;
	}
	
	private String getPages(int minVal, int maxVal, int numSize)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveEntpUlistURL('userlist.do?'+this.value);\">";
		int numMin = 0;
		int numPage = 1;
		for(int i=0; i<numSize; i=i+maxVal)
		{
			numMin = i;
			
			if(minVal == numMin)
			{
				strResult += "<option value=\"numMin=" + numMin+"\" Selected>Page" + numPage + "</option>";
			}
			else
			{
				strResult += "<option value=\"numMin=" + numMin+"\">Page" + numPage + "</option>";
			}
				numPage++;
		}
		strResult += "</select>";
		return strResult;
	}

}
