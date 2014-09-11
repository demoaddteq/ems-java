/** Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
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
import com.mm.core.master.RootMaster;

/** 
 * MyEclipse Struts
 * Creation date: 07-31-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class GetOtherComplaintAction extends Action {
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
		HttpSession session = request.getSession();
		
		
		String uId = (request.getParameter("uId")!= null) ? (String)request.getParameter("uId") : (String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
		Vector allRights =(Vector)session.getAttribute("allRights");
		String adminFlag = allRights.elementAt(18).toString();
		int numCount = Integer.parseInt((request.getParameter("numCount")!=null) ? request.getParameter("numCount").toString().trim() : "0") ;
		int reminder = numCount%2;		
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "creation_date";
		String strSOrder = "";
		String strComIdOrder = "asc";
		String strCTitelOrder = "asc";
		String strDateOrder = "asc";
		
		if(request.getParameter("sby")!=null)
		{
			if(request.getParameter("idorder")!=null)
			{
				if(request.getParameter("idorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strComIdOrder = "desc";
				}
				else if (request.getParameter("idorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strComIdOrder = "asc";
				}
			}
			
			if(request.getParameter("ctorder")!=null)
			{
				if(request.getParameter("ctorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strCTitelOrder = "desc";
				}
				else if (request.getParameter("ctorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strCTitelOrder = "asc";
				}
			}
			if(request.getParameter("dateorder")!=null)
			{
				if(request.getParameter("dateorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strDateOrder = "desc";
				}
				else if (request.getParameter("dateorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strDateOrder = "asc";
				}
			}
			
			
		}
		
		String strFShortBy = strShortBy+" "+strSOrder;
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 20;
		EntpMaster entpMaster = new EntpMaster();
		
		
		
		Vector<String> paramVec1 = new Vector<String>();
		Vector<String> paramVec = new Vector<String>();
		
		paramVec1.add(compId);//companyId
		paramVec1.add(uId);//companyId
		
		
			
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));
		paramVec.add(strFShortBy);
		paramVec.add(compId);
		paramVec.add(uId);
				
		int totalRow = entpMaster.getUnallottedComplaintCount(getDataSource(request, "entp"), paramVec1);	
		String strPageHtml = getPages(minVal, maxVal, totalRow,numCount);
		String strCoreUser = getCoreUser(request,compId,uId); 
		String strOtherCoreUser = getOtherCoreUser(request,compId,uId);
		Vector userVec = entpMaster.getUnAllottedComplainsList(getDataSource(request, "entp"), paramVec);
		
		
		
		//////System.out.println("totalRow..........."+totalRow);
		//////System.out.println("userVec..........."+userVec);
		
		
		
		String strComplaintList = getComplaintList(request,strComIdOrder, strCTitelOrder,strDateOrder, minVal, strPageHtml, userVec,strCoreUser,reminder,strOtherCoreUser);
		//////System.out.println("Users "+strComplaintList);
		/**
	     * setContentType - text/html to write String on page.
	     * PrintWriter - This line use to get writer to write on page.
	     * out.println - use to write string. 
	     * 
	     */
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strComplaintList);
	    out.flush();
		
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getComplaintList(HttpServletRequest request ,String strComIdOrder, String strCTitelOrder,String strDateOrder, int minVal, String strPageHtml, Vector userVec,String strCoreUser,int reminder,String strOtherCoreUser)
	{
		RootMaster  rootMaster = new RootMaster();
	
		String strValue = "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
		
		if(userVec.size()==0)
		{
			strValue += "<tr >";
			strValue += "<td colspan=\"5\" align=\"left\" >Select User &nbsp;"+strCoreUser+"</td>";
			strValue += "</tr>";

			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"400\" valign=\"center\" colspan=\"5\" class=\"bold\"> No Data Found</td>";
			strValue += "</tr>";
		}
		else
		{
		
		strValue = strValue +"<form  name=\"otherComp\" method=\"post\"  action=\"othersComplaint.do\">";
		strValue += "<tr >";
		strValue += "<td colspan=\"2\" align=\"left\" >&nbsp;Switch User From &nbsp;"+strCoreUser;
		strValue += "&nbsp;To "+strOtherCoreUser+" </td>";
		strValue += "<td colspan=\"3\" align=\"right\" >Page Number  "+strPageHtml+"</td>";		
		strValue += "</tr>";
		strValue += "<tr>";
        strValue += "<td width=\"100%\" colspan=\"5\">";
	    strValue += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
		
	    strValue += "<tr >";
		strValue += "<td height=\"25\" colspan=\"7\" align=\"left\" >&nbsp;Select <span class=\"bold\" onClick=\"selectAll()\" style=\"cursor:hand\">All</span>,<span class=\"bold\" onClick=\"selectNone()\" style=\"cursor:hand\">none</span></td>";
		strValue += "</tr>";
		strValue += "</tr>";	
	    strValue +="<tr><td colspan=\"7\" align=\"left\" >&nbsp;&nbsp;<input type=\"submit\" id=\"submit1\" name=\"submit1\"  value=\"Switch Complaint\" disabled=\"true\"></input></td></tr>";
	    strValue += "<tr>";	
	    strValue += "<td height=\"5\" colspan=\"7\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";
	   
	    String strComIdSymbol = (strComIdOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveOtherComplaintlistURL('getOtherComplaint.do?sby=fcom_id&numMin="+ minVal+"&idorder="+strComIdOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveOtherComplaintlistURL('getOtherComplaint.do?sby=fcom_id&numMin="+ minVal+"&idorder="+strComIdOrder+"');\">";
	    String strCTitelSymbol = (strCTitelOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveOtherComplaintlistURL('getOtherComplaint.do?sby=complaint_title&numMin="+ minVal+"&ctorder="+strCTitelOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveOtherComplaintlistURL('getOtherComplaint.do?sby=complaint_title&numMin="+ minVal+"&ctorder="+strCTitelOrder+"');\">";
	    String strDateSymbol = (strDateOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveOtherComplaintlistURL('getOtherComplaint.do?sby=creation_date&numMin="+ minVal+"&dateorder="+strDateOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveOtherComplaintlistURL('getOtherComplaint.do?sby=creation_date&numMin="+ minVal+"&dateorder="+strDateOrder+"');\">";
	    
	   	
	    
	    strValue += "<tr height=\"20\" >";	
	    strValue += "<th class=\"bold\">&nbsp;S. No.</th>";
	    strValue += "<th class=\"bold\">Query Id&nbsp;"+strComIdSymbol+"</th>";	    
	    strValue += "<th class=\"bold\">Query Title&nbsp;"+strCTitelSymbol+"</th>";	
	    strValue += "<th class=\"bold\">Date&nbsp;"+strDateSymbol+"</th>";
	    strValue += "<th class=\"bold\">Category</th>";
	    strValue += "<th class=\"bold\">Company Name</th>";
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
		 
		 int comId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
		 String strFid = tempVec.elementAt(1).toString().trim();
		 
//			for check length of Complaint text
		 String strText = "";  
		 String strFMsg = "";
		 String strFMsgTeam = "";
			String strMessage =  tempVec.elementAt(2).toString().trim();
			////System.out.println("complaintMessage........."+strMessage.length());
			////System.out.println("complaintMessage........."+strMessage);
			int numChar = (strMessage.length() > 45) ? 45 : strMessage.length();  
			for(int k=0; k<numChar; k++)
			{ 
				
					   strFMsg = strFMsg+strMessage.charAt(k);
					   
					
			}
			if(strFMsg.lastIndexOf(strFMsg)!=-1)
			{
				 strText= strFMsg;
				//System.out.println("...userVec......"+userVec.size());
				//System.out.println("...userVec......"+userVec);
	         
	        }else{
	        	strText= strFMsg.substring(0, strFMsg.lastIndexOf(" "));
	        	
	        }	
		 
	     String strDate = tempVec.elementAt(3).toString().trim();
	     int advtid = Integer.parseInt(tempVec.elementAt(6).toString());
	     String brand_Name = rootMaster.getBrandName(getDataSource(request, "entp"), advtid);
			
			if(brand_Name.equals("failure"))
			{
				brand_Name = tempVec.elementAt(7).toString()+"(Other)";
			}
		int catid=Integer.parseInt(tempVec.elementAt(5).toString());
		String catName = rootMaster.getStrCategoryName(getDataSource(request, "entp"),catid);
		strValue += "<tr height=\"25\" align=\"center\" bgcolor=\""+strColor+"\">";
	    strValue += "<td height=\"20\" ><input name=\"blogids\" type=\"checkbox\"  id=\"blogids\" value=\""+comId+"\" onClick=\"checkSelection()\"></input>&nbsp;"+j+"</td>";	
	    strValue += "<td align=\"center\" > "+strFid+"</td>";	
	    strValue += "<td align=\"center\" > "+strText+"..</td>";	
	    strValue += "<td align=\"center\" > "+strDate+"</td>";	
	    strValue += "<td align=\"center\" > "+catName+"</td>";
	    strValue += "<td align=\"center\" > "+brand_Name+"</td>";
	    strValue += "<td ><a href=\"allComplaints.jsp?pageid=9&pgtyp=Details&numCopmId="+comId+"\"><img alt=\"Click here to see detail\" src=\"../images/"+strImg.trim()+"\" width=\"58\" height=\"24\" border=\"0\"></img></a></td>";	
	    strValue += "</tr>";	
	   	   
	    
		}
	    strValue += "<tr>";
	    strValue += "<td height=\"20\" colspan=\"7\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";
	    strValue += "<tr >";
	    strValue +="<td colspan=\"2\" align=\"left\" >&nbsp;&nbsp;<input type=\"submit\" id=\"submit\" name=\"submit\"  value=\"Switch Complaint\" disabled=\"true\"></input></td>";
		strValue += "<td colspan=\"5\" align=\"right\" >Page Number  "+strPageHtml+"</td>";		
		strValue += "</tr>";
		strValue += "</table>";
		strValue += "</td>";	
		strValue += "</tr>";
		strValue += "</form>";	
		}
		if(reminder==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"5\" valign=\"center\" colspan=\"5\" class=\"bold\"> </td>";
			strValue += "</tr>";
		}
	    strValue += "</table>";	
	    
		return strValue;
	}
	
	private String getPages(int minVal, int maxVal, int numSize, int numCount)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveOtherComplaintlistURL('getOtherComplaint.do?numCount="+numCount+"&'+this.value);\">";
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
	private String getCoreUser(HttpServletRequest request , String compId,String uId)
	{
		RootMaster  rootMaster = new RootMaster();
		String strResult ="<select name=\"coreUser\" onchange=\"retrieveOtherComplaintlistURL('getOtherComplaint.do?uId='+this.value);\">";
		Vector cureUserVec = rootMaster.getUserList(getDataSource(request, "entp"),Integer.parseInt(compId));
		int adminId = Integer.parseInt(uId);
		for(int i=0; i<cureUserVec.size(); i++)
		{
			Vector tempVec =  (Vector)cureUserVec.elementAt(i);
			int userId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			String uName = tempVec.elementAt(1).toString().trim()+" "+tempVec.elementAt(2).toString().trim();
			if(userId == adminId)
			{
				strResult += "<option value=\""+userId+"\" Selected>" + uName + "</option>";
			}
			else
			{
				strResult += "<option value=\"" + userId+"\">" + uName + "</option>";
			}
				
		}
		strResult += "</select>";
		return strResult;
	}
	
	
	private String getOtherCoreUser(HttpServletRequest request , String compId,String uId)
	{
		RootMaster  rootMaster = new RootMaster();
		String strResult ="<select name=\"otherCoreUser\" >";
		Vector cureUserVec = rootMaster.getUserList(getDataSource(request, "entp"),Integer.parseInt(compId));
		strResult += "<option value=\"0\">Select</option>";
		int coreUid = Integer.parseInt(uId);
		for(int i=0; i<cureUserVec.size(); i++)
		{
			Vector tempVec =  (Vector)cureUserVec.elementAt(i);
			int userId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			String uName = tempVec.elementAt(1).toString().trim()+" "+tempVec.elementAt(2).toString().trim();
			if(coreUid!=userId)
				strResult += "<option value=\"" + userId+"\">" + uName + "</option>";
			
				
		}
		strResult += "</select>";
		return strResult;
	}


}
