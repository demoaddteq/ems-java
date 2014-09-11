package com.mm.struts.indv.action;

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


public class InboxAction extends Action {

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
		String companyid=(String)session.getAttribute("compId");
		Vector allRights =(Vector)session.getAttribute("allRights");
		String adminFlag = allRights.elementAt(18).toString();
		
		IndvMaster indvMaster= new IndvMaster();		
		Vector dataVec = new Vector();		
		dataVec.add(uId);
		dataVec.add(companyid);		
			
		Vector countVec = indvMaster.complaintCount(getDataSource(request, "indv"), dataVec);		
		session.setAttribute("countVec", countVec);						
		////System.out.println("countVec.............InboxAction........... "+countVec);
		
		
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "responsedate";
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
		
		//////System.out.println("...............Inbox...............");
		
    	Vector<String> paramVec1 = new Vector<String>();
		paramVec1.add(uId);//UserId
		paramVec1.add(adminFlag);//adminFlag
		
			paramVec1.add("Enterprise");//typeOfResCompany	
		
		int totalRow = indvMaster.getInboxCount(getDataSource(request, "indv"), paramVec1);
		//////System.out.println("result totalRow.Inbox......"+totalRow);
		//int totalRow=500;
		
		String strPageHtml = getPages(minVal, maxVal, totalRow , uId,companyid,numCount);
		
		Vector<String> paramVec = new Vector<String>();	
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));
		paramVec.add(strFShortBy);
		paramVec.add(uId);//UserId
		paramVec.add(adminFlag);//adminFlag
		
				paramVec.add("Enterprise");//typeOfResCompany	
			
		Vector userVec = indvMaster.getInboxList(getDataSource(request, "indv"), paramVec);
		//////System.out.println("result userVec.Inbox......"+userVec);
		
		
		
		String strComplaintList = getComplaintList(request,strComIdOrder, strCTitelOrder,strDateOrder, minVal, strPageHtml, userVec, remander);
		////System.out.println("Users "+strComplaintList);
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
	
	private String getComplaintList(HttpServletRequest request,String strComIdOrder, String strCTitelOrder,String strDateOrder, int minVal, String strPageHtml, Vector userVec, int remander)
	{
		
		
	//  EntpMaster entpMaster =  new EntpMaster();
		String strValue = "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >";
		strValue = strValue +"<form  name=\"frminbox\" method=\"post\"  action=\"inboxDeletion.do\" onSubmit=\"return validate()\">";
		
		if(userVec.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"400\" valign=\"center\" colspan=\"7\" class=\"Arial_Narrow_16_orange_normal\"> No Data Found</td>";
			strValue += "</tr>";
		}
		else
			{strValue += "<tr class=\"formBoldOutPage\">";
			strValue += "<td height=\"25\" colspan=\"6\" align=\"left\" class=\"Arial_12_black_normal\">&nbsp;Select <span class=\"Arial_Narrow_12_blue_bold\" onClick=\"selectAll()\" style=\"cursor:hand\">All</span>,<span class=\"Arial_Narrow_12_blue_bold\" onClick=\"selectNone()\" style=\"cursor:hand\">none</span></td>";
			strValue += "</tr>";
			strValue += "<tr >";
			 strValue += "<td width=\"100%\" colspan=\"6\">";
			 strValue += "<table width=\"100%\" >";
			 strValue +="<td  align=\"left\"width=\"10%\" >&nbsp;<input type=\"submit\" id=\"deletebutton1\" class=\"buttonCopy\" name=\"deletebutton1\"  value=\"Delete\" disabled=\"true\"></input></td>";
			 strValue += "<td width=\"70%\" >";
			 strValue += "</td >";
			 strValue += "<td  width=\"20%\" align=\"right\" style=\"Arial_12_black_normal\">Page Number  "+strPageHtml+"</td>";
			 strValue += "</table >";
			 strValue += "</td >";
			 strValue += "</tr>";		
			
			strValue += "<tr>";
	        strValue += "<td width=\"100%\">";
		    strValue += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
		    strValue += "<tr>";	
		    strValue += "<td height=\"5\" colspan=\"6\" align=\"right\"><hr color=\"#ff722b\"></td>";	
		    strValue += "</tr>";
		    ////System.out.println("minVal....1....."+minVal);
	    String strComIdSymbol = (strComIdOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\"   onClick=\"retrieveEntpInlistURL('inbox.do?sby=fcom_id&&numMin="+ minVal+"&idorder="+strComIdOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\"  onClick=\"retrieveEntpInlistURL('inbox.do?sby=fcom_id&&numMin="+ minVal+"&idorder="+strComIdOrder+"');\">";
	    String strRTitelSymbol = (strCTitelOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\"   onClick=\"retrieveEntpInlistURL('inbox.do?sby=responsetext&&numMin="+ minVal+"&ctorder="+strCTitelOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\"  onClick=\"retrieveEntpInlistURL('inbox.do?sby=responsetext&&numMin="+ minVal+"&ctorder="+strCTitelOrder+"');\">";
	    String strDateSymbol = (strDateOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\"  onClick=\"retrieveEntpInlistURL('inbox.do?sby=responsedate&&numMin="+ minVal+"&dateorder="+strDateOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\"  onClick=\"retrieveEntpInlistURL('inbox.do?sby=responsedate&&numMin="+ minVal+"&dateorder="+strDateOrder+"');\">";
	    	    
	    strValue += "<th  align=\"left\" class=\"Arial_Narrow_12_black_bold\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;S. No.</th>";
	    strValue += "<th class=\"Arial_Narrow_12_black_bold\">Complaint Id&nbsp;"+strComIdSymbol+"</th>";	    
	    strValue += "<th class=\"Arial_Narrow_12_black_bold\">Request Title&nbsp;"+strRTitelSymbol+"</th>";	
	    strValue += "<th class=\"Arial_Narrow_12_black_bold\">Date&nbsp;"+strDateSymbol+"</th>";
	    strValue += "<th class=\"Arial_Narrow_12_black_bold\">&nbsp;</th>";
	    strValue += "<th class=\"Arial_Narrow_12_black_bold\">&nbsp;</th>";
	    strValue += "</tr>";	
	    

	 
	   
	   	 
	    strValue += "<tr>";	
	    strValue += "<td height=\"5\" colspan=\"6\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";	
	    
	    for(int i=0; i<userVec.size(); i++)
		{
	    	int j = i+1;
	    	int numTotal = j%2;
	    	//String strColor = (numTotal==0) ? "#ff722b" : "#ffffff";
	    	String strColor = (numTotal==0) ? "#E6E6E6" : "#F0F0F0";
	    	String strImg = (numTotal==0) ? "detail_gray.gif" : "detail_white.gif";
	    	//////System.out.println("strColor "+strColor);
		 Vector tempVec = (Vector)userVec.elementAt(i);
		 
		 int respid = Integer.parseInt(tempVec.elementAt(0).toString().trim());
		 String strFid = tempVec.elementAt(6).toString().trim();
		 String strComplaintId = tempVec.elementAt(3).toString().trim();
	     String strDate = tempVec.elementAt(4).toString().trim();
	     
	     
	     //for check length of Complaint text
	     	String strText=  "";
	        String strFMsg = "";
			String strMessage = tempVec.elementAt(2).toString().trim();
			////System.out.println("complaintMessage........."+strMessage.length());
			////System.out.println("comtext........."+strMessage);
			
			
			int numChar = (strMessage.length() > 40) ? 40 : strMessage.length();  
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
			
	        
	    
	    strValue += "<tr align=\"center\" bgcolor=\""+strColor+"\">";
	    strValue += "<td width=\"5%\" align=\"left\" height=\"20\" class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name=\"inboxids\" type=\"checkbox\" class=\"ms_sans_serif\" id=\"inboxids\" value=\""+respid+"\" onClick=\"checkSelection()\"></input>&nbsp;"+j+"</td>";
	    
	    strValue += "<td  width=\"20%\" align=\"center\" class=\"Arial_12_black_normal\"> "+strFid+"</td>";	
	    strValue += "<td width=\"47%\" align=\"center\" class=\"Arial_12_black_normal\"> "+strText+"...</td>";	
	    strValue += "<td width=\"15%\" align=\"center\" class=\"Arial_12_black_normal\"> "+strDate+"</td>";	
	   
	    strValue += "<td width=\"10%\" colspan=\"2\" ><a href=\"compdetres.jsp?pageid=5&strPgType=Detail&numCid="+strComplaintId+"&inboxRespid="+respid+"\"> <img alt=\"Click here to see detail\" src=\"../images/"+strImg.trim()+"\" width=\"58\" height=\"24\" border=\"0\"></img></a></td>";
	     
	    strValue += "</tr>";	
	    	   
	    
		}
	    
	    strValue += "<tr>";
	    strValue += "<td height=\"5\" colspan=\"6\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";
	    strValue += "<tr >";
	    strValue +="<td  align=\"left\" >&nbsp;<input type=\"submit\" id=\"deletebutton\" name=\"deletebutton\" class=\"buttonCopy\" value=\"Delete\" disabled=\"true\"></input></td>";
		strValue += "<td colspan=\"5\" align=\"right\" style=\"Arial_12_black_normal\">Page Number  "+strPageHtml+"&nbsp;&nbsp;</td>";		
		strValue += "</tr>";
		
		strValue += "</table>";
		strValue += "</td>";	
		strValue += "</tr>";
		}
		strValue += "</form>";
		if(remander==0)
		{
			strValue += "<tr style=\"display:none\" align=\"center\">";
			strValue += "<td height=\"50\" colspan=\"5\"></td>";
			strValue += "</tr>";
		}
	    strValue += "</table>";	
	    ////System.out.println("minVal........."+minVal);
	   
		return strValue;
	}
	  
	private String getPages(int minVal, int maxVal, int numSize, String uId,String compId,String numCount)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveEntpInlistURL('inbox.do?uId="+uId+"&compId="+compId+"&numCount="+numCount+"&'+this.value);\">";
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
