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

public class ExistingComplaintAction  extends Action{
	

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session =request.getSession();
		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		//String userid=(String)session.getAttribute("uId");
		//String companyid=(String)session.getAttribute("compId");
		
		String userid= (request.getParameter("uId") != null) ? request.getParameter("uId") : (String)session.getAttribute("uId");
		session.setAttribute("uId",userid);
				
		String companyid= (request.getParameter("compId") != null) ? request.getParameter("compId") : (String)session.getAttribute("compId");
		session.setAttribute("compId",companyid);
		System.out.println("userid................" +userid);
		System.out.println("companyid................" +companyid);
		
		IndvMaster indvMaster= new IndvMaster();		
		Vector dataVec = new Vector();		
		dataVec.add(userid);
		dataVec.add(companyid);		
			
		Vector countVec = indvMaster.complaintCount(getDataSource(request, "indv"), dataVec);		
		session.setAttribute("countVec", countVec);						
		System.out.println("countVec.............InboxAction........... "+countVec);
		
		
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "creation_date";
		String strSOrder = "";
		String strComplaintFId = "asc";
		String strComplaintTitle = "asc";
		String strComplaintDate = "asc";
		if(request.getParameter("sby")!=null)
		{
			if(request.getParameter("cidorder")!=null)
			{
				if(request.getParameter("cidorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strComplaintFId = "desc";
				}
				else if (request.getParameter("cidorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strComplaintFId = "asc";
				}
			}
			
			if(request.getParameter("ctorder")!=null)
			{
				if(request.getParameter("ctorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strComplaintTitle = "desc";
				}
				else if (request.getParameter("ctorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strComplaintTitle = "asc";
				}
			}
			if(request.getParameter("cdorder")!=null)
			{
				if(request.getParameter("cdorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strComplaintDate = "desc";
				}
				else if (request.getParameter("cdorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strComplaintDate = "asc";
				}
			}
			
			
			
		}
		String strFShortBy = strShortBy+" "+strSOrder;
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 20;
		
		
		Vector<String> paramVec1 = new Vector<String>();
		paramVec1.add(userid);
		paramVec1.add(companyid);
		
		int totalRow = indvMaster.getComplaintCount(getDataSource(request, "indv"), paramVec1);
		//int totalRow=200;
		String strPageHtml = getPages(minVal, maxVal, totalRow,userid,companyid,numCount);
		Vector<String> paramVec = new Vector<String>();
        paramVec.add(userid);
		paramVec.add(companyid);		
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));		
		paramVec.add(strFShortBy);
		
		Vector userVec = indvMaster.getComplaintList(getDataSource(request, "indv"), paramVec);
		
		/*Vector<Vector> comVec = new Vector<Vector>();
		Vector<String> comVec1 = new Vector<String>();
		Vector<String> comVec2 = new Vector<String>();		
		comVec1.add("1");
		comVec1.add("22");
		comVec1.add("For over billing");
		comVec1.add("12-12-07");		
		comVec2.add("2");
		comVec2.add("23");
		comVec2.add("For over billing");
		comVec2.add("12-12-07");		
		comVec.add(comVec1);
		comVec.add(comVec2);*/
		
		String strComplaints = getUserList(  strComplaintFId, strComplaintTitle, strComplaintDate, minVal, strPageHtml, userVec, remander);
		System.out.println("Complaints" +strComplaints);
		/**
	     * setContentType - text/html to write String on page.
	     * PrintWriter - This line use to get writer to write on page.
	     * out.println - use to write string. 
	     * 
	     */
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    //out.println(strComplaints);
	    out.write(strComplaints);
	    out.flush();
	    out.close();
		return null;
	}
	private String getUserList(String strComplaintFId, String strComplaintTitle,  String strComplaintDate,int minVal, String strPageHtml, Vector comVec, int remander)
	{
		
		
		String strValue = "<table width=\"100%\"   align=\"center\" cellpadding=\"0\" cellspacing=\"0\"  >";
		if(comVec.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"400\" valign=\"center\" colspan=\"7\" class=\"Arial_Narrow_16_orange_normal\"> No Data Found</td>";
			strValue += "</tr>";
		
		}
		else
		{
			
					
			        strValue += "<tr >";
					strValue += "<td colspan=\"7\" align=\"right\" style=\"Arial_12_black_normal\">Page Number  "+strPageHtml+"</td>";		
					strValue += "</tr>";
					
								
					strValue += "<tr>";	
				    strValue += "<td height=\"5\" colspan=\"7\" align=\"right\"><hr color=\"#ff722b\"></td>";	
				    strValue += "</tr>";
									
					String strComplaintFIdSymbol = (strComplaintFId.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\"   onClick=\"retrieveIndvEComplistURL('existingComplaint.do?sby=fcom_id&numMin="+ minVal+"&cidorder="+strComplaintFId+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\"  onClick=\"retrieveIndvEComplistURL('existingComplaint.do?sby=fcom_id&numMin="+ minVal+"&cidorder="+strComplaintFId+"');\">";
				    String strComplaintTitleSymbol = (strComplaintTitle.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\"  onClick=\"retrieveIndvEComplistURL('existingComplaint.do?sby=complaint_title&numMin="+ minVal+"&ctorder="+strComplaintTitle+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\"   onClick=\"retrieveIndvEComplistURL('existingComplaint.do?sby=complaint_title&numMin="+ minVal+"&ctorder="+strComplaintTitle+"');\">";
				    String strComplaintDateSymbol = (strComplaintDate.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\"  onClick=\"retrieveIndvEComplistURL('existingComplaint.do?sby=creation_date&numMin="+ minVal+"&cdorder="+strComplaintDate+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\"  onClick=\"retrieveIndvEComplistURL('existingComplaint.do?sby=creation_date&numMin="+ minVal+"&cdorder="+strComplaintDate+"');\">";
				 	
				    
				    strValue += "<tr height=\"20\" class=\"formBoldOutPage\">";	
				    strValue += "<th class=\"Arial_Narrow_12_black_bold\">&nbsp;S. No.</th>";
				    strValue += "<th class=\"Arial_Narrow_12_black_bold\">Query Id&nbsp;"+strComplaintFIdSymbol+"</th>";	    
				    strValue += "<th class=\"Arial_Narrow_12_black_bold\">Query Title&nbsp;"+strComplaintTitleSymbol+"</th>";	
				    strValue += "<th class=\"Arial_Narrow_12_black_bold\">Date&nbsp;"+strComplaintDateSymbol+"</th>";
				    strValue += "<th class=\"Arial_Narrow_12_black_bold\">&nbsp;</th>";
				    strValue += "</tr>";	
				    strValue += "<tr>";				    
				    strValue += "<td height=\"5\" colspan=\"5\" align=\"right\"><hr color=\"#ff722b\"></td>";	
				    strValue += "</tr>";	
				    
				//	strValue += "<th  height=\"20\" class=\"var_11_bold_white\" style=\"cursor:hand\"   onClick=\"retrieveIndvEComplistURL('existingComplaint.do?sby=fcom_id&numMin="+ minVal+"&cidorder="+strComplaintFId+"');\" onMouseOver=\"style.background='#c0c0c0';\" onMouseOut=\"style.background='#ff722b';\">Complaint Id </th>";
				//	strValue += "<th  height=\"20\" class=\"var_11_bold_white\" style=\"cursor:hand\"  onClick=\"retrieveIndvEComplistURL('existingComplaint.do?sby=complaint_title&numMin="+ minVal+"&ctorder="+strComplaintTitle+"');\" onMouseOver=\"style.background='#c0c0c0';\" onMouseOut=\"style.background='#ff722b';\">Complaint Title</th>";
				//	strValue += "<th  height=\"20\" class=\"var_11_bold_white\" style=\"cursor:hand\"  onClick=\"retrieveIndvEComplistURL('existingComplaint.do?sby=creation_date&numMin="+ minVal+"&cdorder="+strComplaintDate+"');\" onMouseOver=\"style.background='#c0c0c0';\" onMouseOut=\"style.background='#ff722b';\">Complaint Date</th>";
		    	//	strValue += "<th colspan=\"3\"  height=\"20\" class=\"var_11_bold_white\">&nbsp;</th>";
				//	strValue += "</tr>"; 
			    //	strValue += "<tr height=\"5\">";
				//	strValue += "<td colspan=\"7\"></td>";
				//	strValue += "</tr>";
		          
		
			for(int i=0; i<comVec.size(); i++)
			{
				  
				  int j = i+1;
			    	int numTotal = j%2;
			    	//String strColor = (numTotal==0) ? "#ff722b" : "#ffffff";
			    	String strColor = (numTotal==0) ? "#E6E6E6" : "#F0F0F0";
			    	String strImg = (numTotal==0) ? "detail_gray.gif" : "detail_white.gif";
			    	//System.out.println("strColor "+strColor);
			    	
				 Vector tempVec = (Vector)comVec.elementAt(i);
				 
				
				 int numCid = Integer.parseInt(tempVec.elementAt(0).toString().trim());
				 
				 String strCFID = tempVec.elementAt(1).toString().trim();
			     String strCTitle = tempVec.elementAt(2).toString().trim();
			     String strCDate = tempVec.elementAt(3).toString().trim();
			     
//			 	for check length of Complaint text
				 String strText = "";  
				 String strFMsg = "";
				 
					String strMessage =  tempVec.elementAt(2).toString().trim();
					//System.out.println("complaintMessage........."+strMessage.length());
					//System.out.println("complaintMessage........."+strMessage);
					int numChar = (strMessage.length() > 45) ? 45 : strMessage.length();  
					for(int k=0; k<numChar; k++)
					{ 
						
							   strFMsg = strFMsg+strMessage.charAt(k);
							   
							
					}
					if(strFMsg.lastIndexOf(strFMsg)!=-1)
					{
						 strText= strFMsg;
						System.out.println("...strFMsgTeam......"+strFMsg.lastIndexOf(strFMsg));
						
			         
			        }else{
			        	strText= strFMsg.substring(0, strFMsg.lastIndexOf(" "));
			        	System.out.println("...strText......");
			        }	
				
					
			        strValue += "<tr height=\"25\" align=\"center\" bgcolor=\""+strColor+"\">";	
				    strValue += "<td class=\"Arial_12_black_normal\">&nbsp;"+j+"</td>";	
				    strValue += "<td align=\"center\" class=\"Arial_12_black_normal\"> "+strCFID+"</td>";	
				    strValue += "<td align=\"center\" class=\"Arial_12_black_normal\"> "+strText+"..</td>";	
				    strValue += "<td align=\"center\" class=\"Arial_12_black_normal\"> "+strCDate+"</td>";	
				    strValue += "<td ><a href=\"compdetres.jsp?numCid="+numCid+"&pageid=2\"><img alt=\"Click here to see detail\" src=\"../images/"+strImg.trim()+"\" width=\"58\" height=\"24\" border=\"0\"></img></a></td>";	
				    strValue += "</tr>";   
				    
				
			//		strValue+="<tr height=\"20\" align=\"center\"  bgcolor=\""+strColor+"\">";
			//		strValue+="<td align=\"center\" height=\"20\" class=\"Arial_12_black_normal\">"+j+"</td>";
			//		strValue+="<td align=\"center\"  height=\"20\" class=\"Arial_12_black_normal\">"+strCFID+"</td>";
			//		strValue+="<td align=\"center\"  height=\"20\" class=\"Arial_12_black_normal\">"+strCTitle+"</td>";
			//		strValue+="<td align=\"center\" class=\"Arial_12_black_normal\">"+strCDate+"</td>";
			//		strValue+="<td colspan=\"3\"  width=\"11%\" align=\"center\" nowrap class=\"Arial_Narrow_12_black_bold\">";
			//		strValue += "<td ><a href=\"compdetres.jsp?numCid="+numCid+"\"><img alt=\"Click here to see detail\" src=\"../images/"+strImg.trim()+"\" width=\"58\" height=\"24\" border=\"0\"></img></a></td>";	
	        //	    strValue+="<a href=\"compdetres.jsp?pageid=2&numCid="+numCid+"\"><img alt=\"Click here to see detail\" src=\"../images/detail.gif\" width=\"58\" height=\"24\" border=\"0\"></img></a>";
		    //		strValue+="</td>";
		    //		strValue+="</tr>";
					
					
					
					
			}
					strValue += "<tr>";
					strValue +="<td  colspan=\"7\" align=\"right\" class=\"errormessages\"><hr color=\"#ff722b\"></td>";
					strValue +="</tr>";
					strValue += "<tr >";
					strValue += "<td colspan=\"7\"  align=\"right\" class=\"Arial_12_black_normal\">Page Number  "+strPageHtml+"</td>";		
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
	
	private String getPages(int minVal, int maxVal, int numSize ,String uId,String compId,String numCount)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveIndvEComplistURL('existingComplaint.do?uId="+uId+"&compId="+compId+"&numCount="+numCount+"&'+this.value);\">";
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
