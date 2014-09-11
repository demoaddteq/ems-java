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
import javax.sql.*;
import com.mm.core.master.*;

/** 
 * MyEclipse Struts
 * Creation date: 07-03-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class StdInboxAction extends Action {
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
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
		int numTempCount = (session.getAttribute("numCount") != null) ? Integer.parseInt((String)session.getAttribute("numCount")) : 0;
		numTempCount = numTempCount+1;
		
		RootMaster rootMaster = new RootMaster();
		
		session.setAttribute("numCount", String.valueOf(numTempCount));
		int remander = numTempCount%2;
		//System.out.println("remander..in Action corpo ........."+remander);
		
		String uId = (request.getParameter("uId")!=null)? request.getParameter("uId").trim() : (String)session.getAttribute("uId");	
		//System.out.println("uId ...Action......"+uId);
		
		String compId =(String)session.getAttribute("compId");
		Vector allRights =(Vector)session.getAttribute("allRights");
		String adminFlag = allRights.elementAt(18).toString();
		
//		System.out.println("compId..in Action avdt ........."+compId);
		int regFlag= rootMaster.getRegisteredFlag(getDataSource(request, "student"), compId);
		//System.out.println("regFlag..in Action avdt ........."+regFlag);
		
		DataSource ds=getDataSource(request, "student");
		
		String pageid = (request.getParameter("pageid")!=null)? request.getParameter("pageid").trim() : "7";
		String aType = (request.getParameter("aType")!=null)? request.getParameter("aType").trim() : "0";
		String lowerSubTag = (request.getParameter("pageid")!=null)? request.getParameter("pageid").trim() : "1";
		//System.out.println("pageid..in Action avdt ........."+pageid);
		////System.out.println("aType..in Action avdt ........."+aType);
		////System.out.println("aType................on page."+aType);
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "creation_date";
		String strSOrder = "";
		String strComFIdOrder = "asc";
		String strCTitelOrder = "asc";
		String strDateOrder = "asc";
		
		if(request.getParameter("sby")!=null)
		{
			if(request.getParameter("fidorder")!=null)
			{
				if(request.getParameter("fidorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strComFIdOrder = "desc";
				}
				else if (request.getParameter("fidorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strComFIdOrder = "asc";
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
		
		AdvtMaster advtMaster = new AdvtMaster();
		
		Vector<String> paramVec1 = new Vector<String>();
		String pagetype="student";
		paramVec1.add(uId);
		paramVec1.add(compId);
		paramVec1.add(pagetype);
		
		
		Vector<String> paramVec = new Vector<String>();
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));		
		paramVec.add(strFShortBy);
		paramVec.add(uId);
		paramVec.add(compId);
		paramVec.add(pagetype);
		
		
		
		
		int Cid = Integer.parseInt(compId);
		int Uid = Integer.parseInt(uId);
		int aFid = Integer.parseInt(adminFlag);
		int totalRow = 0;
		Vector userVec = new Vector();
		
				totalRow = advtMaster.getQueryCount(getDataSource(request, "student"), paramVec1);
				//System.out.println("totalRow..........."+totalRow);
				userVec= advtMaster.getQueryList(getDataSource(request, "student"), paramVec);
				///System.out.println("userVec..........."+userVec);
			
			
			String strPageHtml = getPages(minVal, maxVal, totalRow,lowerSubTag,pageid,remander);
		
		
		////System.out.println("result userVec......."+userVec);
		
		////////////////////////////////////////////////////////////////////////////////////
		String strComplaintList = "";
		 strComplaintList = getComplaintList(strComFIdOrder, strCTitelOrder,strDateOrder, minVal, maxVal, totalRow, strPageHtml, userVec, pageid,lowerSubTag, remander,aType, regFlag,ds);
		
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strComplaintList);
	    out.flush();
		
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getComplaintList(String strComFIdOrder, String strCTitelOrder,String strDateOrder, int minVal, int maxVal, int totalRow, String strPageHtml, Vector userVec, String pageid,String lowerSubTag, int remander,String aType,int regFlag,DataSource ds)
	{
		
		
		     
		String strValue = "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
		
		if(userVec.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"400\" valign=\"center\" colspan=\"5\" > Currently, there is no data in your inbox.</td>";
			strValue += "</tr>";
		}
		else
		{
			if (totalRow > maxVal)
			{
	        strValue += "<tr >";
			strValue += "<td colspan=\"7\" align=\"right\" >Page Number  "+strPageHtml+"</td>";		
			strValue += "</tr>";
			}
			
			strValue += "<tr>";
        strValue += "<td width=\"100%\">";
           
	    strValue += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
	    
	    
	    strValue += "<tr>";	
	    strValue += "<td height=\"5\" colspan=\"7\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";
	    
	    
	    String strLoginIdSymbol = (strComFIdOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveStdInboxtURL('stdInbox.do?sby=fcom_id&pageid="+pageid+"&numMin="+ minVal+"&fidorder="+strComFIdOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveStdInboxtURL('stdInbox.do?sby=fcom_id&pageid="+pageid+"&numMin="+ minVal+"&fidorder="+strComFIdOrder+"');\">";
	    String strUnameSymbol = (strCTitelOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveStdInboxtURL('stdInbox.do?sby=complaint_title&pageid="+pageid+"&numMin="+ minVal+"&ctorder="+strCTitelOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveStdInboxtURL('stdInbox.do?sby=complaint_title&pageid="+pageid+"&numMin="+ minVal+"&ctorder="+strCTitelOrder+"');\">";
	    String strDateSymbol = (strDateOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveStdInboxtURL('stdInbox.do?sby=creation_date&pageid="+pageid+"&numMin="+ minVal+"&dateorder="+strDateOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveStdInboxtURL('stdInbox.do?sby=creation_date&pageid="+pageid+"&numMin="+ minVal+"&dateorder="+strDateOrder+"');\">";
		
	    strValue += "<tr height=\"20\" >";	
	    strValue += "<th class=\"bold\">&nbsp;S. No.</th>";
	    strValue += "<th class=\"bold\">Query By&nbsp;"+strLoginIdSymbol+"</th>";	    
	    strValue += "<th class=\"bold\">Query Title&nbsp;"+strUnameSymbol+"</th>";	
	    strValue += "<th class=\"bold\">Date&nbsp;"+strDateSymbol+"</th>";
	    strValue += "<th class=\"bold\">&nbsp;</th>";
	    
	    strValue += "</tr>";	
	    strValue += "<tr>";	
	    strValue += "<td height=\"5\" colspan=\"5\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";	
	
	   
	    
	    for(int i=0; i<userVec.size(); i++)
		{
	    	int j = i+1;
	    	int numTotal = j%2;
	    	String strColor = (numTotal==0) ? "#E6E6E6" : "#F0F0F0";
	    	String strImg = (numTotal==0) ? "detail_gray.gif" : "detail_white.gif";
		 Vector tempVec = (Vector)userVec.elementAt(i);
		 
		 int comId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
		 int userid = Integer.parseInt(tempVec.elementAt(5).toString().trim());
		 String strFid = tempVec.elementAt(5).toString().trim();
		 RootMaster rootMaster=new RootMaster();
		 Vector userInfo=rootMaster.getUserInfo(ds, userid);
		 String Companytype=userInfo.elementAt(18).toString();
		 if(Companytype.equalsIgnoreCase("Advertiser"))
		 {
			 Companytype="College" ;
		 }else
		 {
			 Companytype="Company" ;
		 }
		 String Name=userInfo.elementAt(4).toString()+ "&nbsp;"+userInfo.elementAt(5).toString()+","+Companytype+"";
//		 	for check length of Complaint text
		 String strText = "";  
		 String strFMsg = "";
		 
			String strMessage =  tempVec.elementAt(2).toString().trim();
			//////System.out.println("complaintMessage........."+strMessage.length());
			//////System.out.println("complaintMessage........."+strMessage);
			int numChar = (strMessage.length() > 45) ? 45 : strMessage.length();  
			for(int k=0; k<numChar; k++)
			{ 
				
					   strFMsg = strFMsg+strMessage.charAt(k);
					   
					
			}
			if(strFMsg.lastIndexOf(strFMsg)!=-1)
			{
				 strText= strFMsg;
				////System.out.println("...strFMsgTeam......"+strFMsg.lastIndexOf(strFMsg));
				
	         
	        }else{
	        	strText= strFMsg.substring(0, strFMsg.lastIndexOf(" "));
	        	////System.out.println("...strText......");
	        }
			
	     String strDate[] = tempVec.elementAt(3).toString().split("-");
	     strValue += "<tr height=\"25\" align=\"center\" bgcolor=\""+strColor+"\">";
	    strValue += "<td height=\"20\" >&nbsp;"+j+"</td>";	
	    strValue += "<td align=\"center\" > "+Name+"</td>";	
	    strValue += "<td align=\"center\" > "+strText+"..</td>";	
	    strValue += "<td align=\"center\" > "+strDate[2]+"-"+strDate[1]+"-"+strDate[0]+"</td>";	
	    if(regFlag!=0){
		    strValue += "<td ><a href=\"stdinboxDetail.jsp?Ctype="+Companytype+"&pageid="+pageid+"&numCompId="+comId+"\"><img alt=\"Click here to see detail\" src=\"../images/"+strImg.trim()+"\" width=\"47\" height=\"19\" border=\"0\"></img></a></td>";	
		    }else{
		    	strValue += "<td ><img alt=\"Click here to see detail\" src=\"../images/"+strImg.trim()+"\" width=\"47\" height=\"19\" border=\"0\" onclick=\"showalert()\";></img></td>";	
		 	  }
	    strValue += "</tr>";	
	   	   
	    strValue += "</td>";	
	    strValue += "</tr>";
		}
	    
	    strValue += "<tr>";	
	    strValue += "<td height=\"20\" colspan=\"5\" align=\"right\"><hr color=\"#ff722b\"></td>";	
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
			strValue += "<td height=\"50\" colspan=\"5\"></td>";
			strValue += "</tr>";
		}
	    strValue += "</table>";	
	    
		return strValue;
	}
	
	
	
	private String getPages(int minVal, int maxVal, int numSize ,String lowerSubTag,String pageid ,int numCount)
	{
		String strResult ="<select name=\"paging\" onClick=\"retrieveStdInboxtURL('stdInbox.do?pageid="+pageid+"&numCount="+numCount+"&'+this.value);\">";
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