/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.corpo.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.AdvtMaster;

/** 
 * MyEclipse Struts
 * Creation date: 07-03-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
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
		HttpSession session = request.getSession();
		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		
		String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
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
		AdvtMaster am = new AdvtMaster();
		
		
		Vector<String> paramVec1 = new Vector<String>();
		paramVec1.add(compId);
		
		
		int totalRow = am.getUserCount(getDataSource(request, "corpo"), paramVec1);
		//////////System.out.println("totalRow...getUserCount...."+totalRow);
		//int totalRow=500;
		String strPageHtml = getPages(minVal, maxVal, totalRow,compId,numCount);
		//////////System.out.println("strPageHtml......."+strPageHtml);
		
		Vector<String> paramVec = new Vector<String>();
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));
		paramVec.add(compId);
		paramVec.add(strFShortBy);
		
		//////////System.out.println("paramVec......."+paramVec);
		Vector userVec = new Vector();		
	    userVec = am.getUserList(getDataSource(request, "corpo"), paramVec);
	   ////////System.out.println("result userVec......."+userVec);
		
		
		
		String strUsers = getUserList(strLoginIdOrder, strUnameOrder, minVal, maxVal, totalRow, strPageHtml, userVec, infoVec, rigthVec, remander, uId);
		//////////System.out.println("Users "+strUsers);
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
	
	private String getUserList(String strLoginIdOrder, String strUnameOrder, int minVal, int maxVal, int totalRow, String strPageHtml, Vector userVec, Vector infoVec, Vector rigthVec, int remander, String uId)
	{
		
		
		String strValue="<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >";
		if(userVec.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td  height=\"50\" valign=\"center\" colspan=\"7\" class=\"bold\"> No Data Found</td>";
			strValue += "</tr>";
		}
		else
		{
			String strLorderImg = (strLoginIdOrder.equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveUlistURL('userlist.do?sby=loginname&numMin="+ minVal+"&liorder="+strLoginIdOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveUlistURL('userlist.do?sby=loginname&numMin="+ minVal+"&liorder="+strLoginIdOrder+"');\">";
			String strUNOrderImg = (strUnameOrder.equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveUlistURL('userlist.do?sby=first_name&numMin="+ minVal+"&unorder="+strUnameOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveUlistURL('userlist.do?sby=first_name&numMin="+ minVal+"&unorder="+strUnameOrder+"');\">";
			
			
			if (totalRow > maxVal)
			{
	        strValue += "<tr >";
			strValue += "<td colspan=\"7\" align=\"right\" >Page Number  "+strPageHtml+"</td>";		
			strValue += "</tr>";
			}
			
			strValue += "<td height=\"5\" colspan=\"7\" align=\"right\"><hr color=\"#C1C1C1\"></td>";	
		    strValue += "</tr>";
			strValue += "<tr height=\"20\">";
			strValue += "<th width=\"12%\" class=\"bold\">S. No.</th>";
			strValue += "<th width=\"20%\" class=\"bold\">Login ID&nbsp;"+strLorderImg+"</th>";
			strValue += "<th width=\"20%\" class=\"bold\">User Name&nbsp;"+strUNOrderImg+"</th>";
			strValue += "<th width=\"12%\" class=\"bold\">&nbsp;</th>";
			strValue += "<th width=\"12%\" class=\"bold\">&nbsp;</th>";
			strValue += "<th width=\"12%\" class=\"bold\">&nbsp;</th>";
			strValue += "<th width=\"12%\" class=\"bold\">&nbsp;</th>";
			strValue += "</tr>";
			strValue += "<tr>";	
		    strValue += "<td height=\"5\" colspan=\"7\" align=\"right\"><hr color=\"#C1C1C1\"></td>";	
		    strValue += "</tr>";
			
				for(int i=0; i<userVec.size(); i++)
				{
					int j = i+1;
					 Vector tempVec = (Vector)userVec.elementAt(i);
					 int numTotal = j%2;
					 String strColor = (numTotal==0) ? "#FFFFFF" : "#EDF3F8";
					 int numCid = Integer.parseInt(tempVec.elementAt(0).toString().trim());
					 String strLIdName = tempVec.elementAt(1).toString().trim();
				     String strUName = tempVec.elementAt(2).toString().trim();
				     String strUid = tempVec.elementAt(3).toString().trim();
				     String rid = tempVec.elementAt(5).toString().trim();
				     
				     if(rid.equalsIgnoreCase("0")){
				    	 
				    	 strLIdName += "(disable)";
				     }
				     
				     int numUid = Integer.parseInt(strUid);		     
				     
					 
				     strValue += "<tr height=\"23\" align=\"center\" valign=\"middle\" bgcolor=\""+strColor+"\">";
						strValue += "<td > "+j+" </td>"; 
						strValue += "<td nowrap > "+strLIdName+" </td>";
						strValue += "<td nowrap > "+strUName+" </td>";
						strValue += "<td align=\"center\" valign=\"middle\" nowrap class=\"bold\">"; 
						strValue += "<a href=\"advtUserdetail.jsp?task=Details&userid="+numUid+"\"><span class=\"bold\" >Details</span></a></td>";		
						strValue += "<td height=\"20\" align=\"center\" valign=\"middle\" nowrap class=\"bold\">";
						if((infoVec.elementAt(0).toString().equalsIgnoreCase(strUid))||(rigthVec.elementAt(0).toString().equalsIgnoreCase("1"))){
						strValue += "<a href=\"changepass.jsp?userid="+numUid+"\"><span class=\"bold\" >Change Password</span></a></td>";	
						}else{
						strValue += "Change Password</td>";	
						}
						strValue += "<td align=\"center\" valign=\"middle\" nowrap class=\"bold\">";				
						if((!infoVec.elementAt(0).toString().equalsIgnoreCase(strUid))&&(rigthVec.elementAt(2).toString().equalsIgnoreCase("1"))){
						strValue += "<a href=\"usergetdata.do?task=Delete&userid="+numUid+"\"><span class=\"bold\" >Delete</span></a></td>";
						}else{
						strValue += "Delete</td>";
						}
						
						strValue += "<td align=\"center\" valign=\"middle\" nowrap class=\"bold\">";
						
						if((!uId.equalsIgnoreCase(strUid))){
						if((infoVec.elementAt(0).toString().equalsIgnoreCase(strUid))||(rigthVec.elementAt(3).toString().equalsIgnoreCase("1"))){
						strValue += "<a href=\"usergetdata.do?task=Modify&userid="+numUid+"\"><span class=\"bold\" >Modify</span></a></td>";
						}else{
						strValue += "Modify</td>";
						}
						}else{
							strValue += "Modify</td>";					
						}
							
						strValue += "</tr>";			
					
				}
				strValue += "<tr>";	
			    strValue += "<td height=\"5\" colspan=\"7\" valign=\"middle\" align=\"right\"><hr color=\"#C1C1C1\"></td>";	
			    strValue += "</tr>";
				strValue += "</td>";	
				strValue += "</tr>";
			
				if (totalRow > maxVal)
				{
		        strValue += "<tr >";
				strValue += "<td colspan=\"7\" align=\"right\" >Page Number  "+strPageHtml+"</td>";		
				strValue += "</tr>";
				}
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
	
	private String getPages(int minVal, int maxVal, int numSize,String CompId,String numTempCount)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveUlistURL('userlist.do?compId="+CompId+"&numCount="+numTempCount+"&'+this.value);\">";
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