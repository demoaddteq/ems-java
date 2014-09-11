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
public class SearchResultAction extends Action {
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
		HttpSession session =request.getSession();
		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		//////System.out.println("remander..........."+remander);
		
		String userid=(String)session.getAttribute("uId");
		Vector rights=(Vector)session.getAttribute("allRights");
		String adminFlag = rights.elementAt(18).toString();
		////System.out.println("userid................" +userid);
		////System.out.println("admin flag................" +adminFlag);
		
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
		paramVec1.add(String.valueOf(minVal));
		paramVec1.add(String.valueOf(maxVal));
		paramVec1.add(strFShortBy);
		paramVec1.add(userid);//coreUserId
		paramVec1.add(adminFlag);//Admin Flag
		if(session.getAttribute("searchVec")!=null)
		{
			//////System.out.println("manoj in if");
			Vector searchVec = (Vector)session.getAttribute("searchVec");
			for(int i=0;i<searchVec.size();i++)
				paramVec1.add(searchVec.elementAt(i).toString().trim());
		}
		////System.out.println("paramVec>>>>>>>>>>>"+paramVec1);
		int totalRow = entpMaster.getSearchComplainsCount(getDataSource(request, "entp"), paramVec1);
		//int totalRow=500;
		////System.out.println("totalRow>>>>>>>>>>>"+totalRow);
		
		String strPageHtml = getPages(minVal, maxVal, totalRow);
		////System.out.println("strPageHtml>>>>>>>>>>>"+strPageHtml);
		Vector<String> paramVec = new Vector<String>();	
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));
		paramVec.add(strFShortBy);
		paramVec.add(userid);//coreUserId
		paramVec.add(adminFlag);//Admin Flag
		if(session.getAttribute("searchVec")!=null)
		{
			////System.out.println("manoj in if");
			Vector searchVec = (Vector)session.getAttribute("searchVec");
			for(int i=0;i<searchVec.size();i++)
				paramVec.add(searchVec.elementAt(i).toString().trim());
		}
		////System.out.println("paramVec>>>>>>>>>>>"+paramVec);
		Vector userVec = entpMaster.getSearchComplainsList(getDataSource(request, "entp"), paramVec);
		
		
		String strComplaintList = getComplaintList(strComIdOrder, strCTitelOrder,strDateOrder, minVal, strPageHtml, userVec, remander);
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
	
	private String getComplaintList(String strComIdOrder, String strCTitelOrder,String strDateOrder, int minVal, String strPageHtml, Vector userVec, int remander)
	{
		
		
		     
		String strValue = "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
		
		if(userVec.size()==0)
		{

			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"400\" valign=\"center\" colspan=\"5\" > No Data Found</td>";
			strValue += "</tr>";
		}
		else
		{
			strValue += "<tr >";
			strValue += "<td colspan=\"5\" align=\"right\" >Page Number  "+strPageHtml+"</td>";		
			strValue += "</tr>";
		strValue += "<tr>";
        strValue += "<td width=\"100%\">";
	    strValue += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
	    
	    
	    
	    strValue += "<tr>";	
	    strValue += "<td height=\"5\" colspan=\"5\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";
	   
	    String strComIdSymbol = (strComIdOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveEntpSRlistURL('searchResult.do?sby=fcom_id&numMin="+ minVal+"&idorder="+strComIdOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveEntpSRlistURL('searchResult.do?sby=fcom_id&numMin="+ minVal+"&idorder="+strComIdOrder+"');\">";
	    String strCTitelSymbol = (strCTitelOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveEntpSRlistURL('searchResult.do?sby=complaint_title&numMin="+ minVal+"&ctorder="+strCTitelOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveEntpSRlistURL('searchResult.do?sby=complaint_title&numMin="+ minVal+"&ctorder="+strCTitelOrder+"');\">";
	    String strDateSymbol = (strDateOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveEntpSRlistURL('searchResult.do?sby=creation_date&numMin="+ minVal+"&dateorder="+strDateOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveEntpSRlistURL('searchResult.do?sby=creation_date&numMin="+ minVal+"&dateorder="+strDateOrder+"');\">";
	    
	    strValue += "<tr height=\"20\" class=\"formBoldOutPage\">";	
	    strValue += "<th class=\"bold\">&nbsp;S. No.</th>";
	    strValue += "<th class=\"bold\">Query Id&nbsp;"+strComIdSymbol+"</th>";	    
	    strValue += "<th class=\"bold\">Query Title&nbsp;"+strCTitelSymbol+"</th>";	
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
		 
		 String strFid = tempVec.elementAt(1).toString().trim();
		    
	     String strDate = tempVec.elementAt(3).toString().trim();
		 
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
				//System.out.println("...strFMsgTeam......"+strFMsg.lastIndexOf(strFMsg));
				
	         
	        }else{
	        	strText= strFMsg.substring(0, strFMsg.lastIndexOf(" "));
	        	//System.out.println("...strText......");
	        }	
	     
	     strValue += "<tr height=\"25\" align=\"center\" bgcolor=\""+strColor+"\">";	
	    strValue += "<td height=\"20\" >&nbsp;"+j+"</td>";	
	    strValue += "<td align=\"center\" > "+strFid+"</td>";	
	    strValue += "<td align=\"center\" > "+strText+"..</td>";	
	    strValue += "<td align=\"center\" > "+strDate+"</td>";	
	    if(tempVec.elementAt(5).toString().trim().equals("0"))
	    {
	    	strValue += "<td ><a href=\"allComplaints.jsp?pageid=7&pgtyp=Details&numCopmId="+comId+"\"><img alt=\"Click here to see detail\" src=\"../images/"+strImg.trim()+"\" width=\"58\" height=\"24\" border=\"0\"></img></a></td>";	
	    }
	    else
	    {
	    	strValue += "<td ><a href=\"complaintResponseComments.jsp?pageid=7&pgtyp=Details&numCopmId="+comId+"\"><img alt=\"Click here to see detail\" src=\"../images/"+strImg.trim()+"\" width=\"58\" height=\"24\" border=\"0\"></img></a></td>";
	    }
	    strValue += "</tr>";	
	    	   
	    
		}
	    
	    strValue += "<tr>";
	    strValue += "<td height=\"20\" colspan=\"5\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";
	    strValue += "<tr >";
		strValue += "<td colspan=\"5\" align=\"right\" >Page Number  "+strPageHtml+"</td>";		
		strValue += "</tr>";
		 strValue += "</table>";	
		strValue += "</td>";	
	    strValue += "</tr>";
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
	
	private String getPages(int minVal, int maxVal, int numSize)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveEntpSRlistURL('searchResult.do?'+this.value);\">";
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
