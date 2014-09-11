
package com.mm.struts.indv.action;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import com.mm.core.master.IndvMaster;

public class SearchComplaintResultAction  extends Action{
	


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		HttpSession session =request.getSession();
		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		
		String userid=(String)session.getAttribute("uId");
		String companyid=(String)session.getAttribute("compId");
		System.out.println("userid................" +userid);
		System.out.println("companyid................" +companyid);
		
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "complaint_title";
		String strSOrder = "";
		String strSComplaintId = "asc";
		String strSComplaintTitle = "asc";
		String strSComplaintdate= "asc";
		
		if(request.getParameter("sby")!=null)
		{
			if(request.getParameter("scidorder")!=null)
			{
				if(request.getParameter("scidorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strSComplaintId = "desc";
				}
				else if (request.getParameter("scidorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strSComplaintId = "asc";
				}
			}
			
			if(request.getParameter("sctorder")!=null)
			{
				if(request.getParameter("sctorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strSComplaintTitle = "desc";
				}
				else if (request.getParameter("sctorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strSComplaintTitle = "asc";
				}
			}
			if(request.getParameter("scdorder")!=null)
			{
				if(request.getParameter("scdorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strSComplaintdate = "desc";
				}
				else if (request.getParameter("scdorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strSComplaintdate = "asc";
				}
			}
			
			
		}
		
		String strFShortBy = strShortBy+" "+strSOrder;
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 20;
		IndvMaster indvMaster = new IndvMaster();
		Vector<String> paramVec = new Vector<String>();
		paramVec.add(userid);
		paramVec.add(companyid);
		if(session.getAttribute("searchVec")!=null)
		{
			System.out.println("manoj in if");
			Vector searchVec = (Vector)session.getAttribute("searchVec");
			for(int i=0;i<searchVec.size();i++)
				paramVec.add(searchVec.elementAt(i).toString().trim());
		}
		System.out.println("paramVec>>>>>>>>>>>"+paramVec);
		int totalRow = indvMaster.getComplaintCount(getDataSource(request, "indv"), paramVec);
		
		//int totalRow=200;
		String strPageHtml = getPages(minVal, maxVal, totalRow,numCount);
		Vector<String> paramVec1 = new Vector<String>();
		paramVec1.add(userid);
		paramVec1.add(companyid);
		paramVec1.add(String.valueOf(minVal) );
		paramVec1.add(String.valueOf(maxVal));
		paramVec1.add(strFShortBy);
		if(session.getAttribute("searchVec")!=null)
		{
			Vector searchVec1 = (Vector)session.getAttribute("searchVec");
			for(int i=0;i<searchVec1.size();i++)
				paramVec1.add(searchVec1.elementAt(i).toString().trim());
		}
		System.out.println("paramVec1>>>>>>>>>>>"+paramVec1);
		Vector userVec = indvMaster.getComplaintList(getDataSource(request, "indv"), paramVec1);
		
		/*Vector<Vector> comVec = new Vector<Vector>();
		Vector<String> comVec1 = new Vector<String>();
		Vector<String> comVec2 = new Vector<String>();
		
		comVec1.add("1");
		comVec1.add("22");
		comVec1.add("For over billing");
		comVec1.add("12-12-07");
		
		comVec2.add("2");
		comVec2.add("23");
		comVec2.add("Complaint against");
		comVec2.add("12-12-07");
		
		comVec.add(comVec1);
		comVec.add(comVec2);*/
		String strComplaints = getUserList(  strSComplaintId, strSComplaintTitle,strSComplaintdate,  minVal, strPageHtml, userVec, remander);
		System.out.println("Complaints" +strComplaints);
		/**
	     * setContentType - text/html to write String on page.
	     * PrintWriter - This line use to get writer to write on page.
	     * out.println - use to write string. 
	     * 
	     */
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strComplaints);
	    out.flush();
		return null;
	}
	private String getUserList(String strSComplaintId, String strSComplaintTitle,String strSComplaintdate,  int minVal, String strPageHtml, Vector userVec, int remander)
	{
		
		
		String strValue = "<table width=\"100%\"   align=\"center\" cellpadding=\"0\" cellspacing=\"0\"  >";
		if(userVec.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"400\" valign=\"center\" colspan=\"5\" class=\"Arial_Narrow_16_orange_normal\"> No Data Found</td>";
			strValue += "</tr>";
		}
		else
		{
			
          
			        strValue += "<tr >";
					strValue += "<td colspan=\"7\" align=\"right\" class=\"Arial_12_black_normal\">Page Number  "+strPageHtml+"</td>";		
					strValue += "</tr>";
					
					strValue += "<tr>";	
				    strValue += "<td height=\"5\" colspan=\"5\" align=\"right\"><hr color=\"#ff722b\"></td>";	
				    strValue += "</tr>";
				    //strComIdOrder
				    //strCTitelOrder
				    //strDateOrder
				    //asc, desc
				    String strSComplaintIdSymbol = (strSComplaintId.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveIndvSComplistURL('searchComplaintResult.do?sby=fcom_id&numMin="+ minVal+"&scidorder="+strSComplaintId+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveIndvSComplistURL('searchComplaintResult.do?sby=fcom_id&numMin="+ minVal+"&scidorder="+strSComplaintId+"');\">";
				    String strSComplaintTitleSymbol = (strSComplaintTitle.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveIndvSComplistURL('searchComplaintResult.do?sby=complaint_title&numMin="+ minVal+"&sctorder="+strSComplaintTitle+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveIndvSComplistURL('searchComplaintResult.do?sby=complaint_title&numMin="+ minVal+"&sctorder="+strSComplaintTitle+"');\">";
				    String strSComplaintdateSymbol = (strSComplaintdate.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveIndvSComplistURL('searchComplaintResult.do?sby=creation_date&numMin="+ minVal+"&scdorder="+strSComplaintdate+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveIndvSComplistURL('searchComplaintResult.do?sby=creation_date&numMin="+ minVal+"&scdorder="+strSComplaintdate+"');\">";
				    
				    
				    strValue += "<tr height=\"20\" class=\"formBoldOutPage\">";	
				    strValue += "<th class=\"Arial_Narrow_12_black_bold\">&nbsp;S. No.</th>";
				    strValue += "<th class=\"Arial_Narrow_12_black_bold\">Complaint Id&nbsp;"+strSComplaintIdSymbol+"</th>";	    
				    strValue += "<th class=\"Arial_Narrow_12_black_bold\">Complaint Title&nbsp;"+strSComplaintTitleSymbol+"</th>";	
				    strValue += "<th class=\"Arial_Narrow_12_black_bold\">Date&nbsp;"+strSComplaintdateSymbol+"</th>";	
				    strValue += "<th class=\"Arial_Narrow_12_black_bold\">&nbsp;</th>";
				   
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
				 
				 int numCid = Integer.parseInt(tempVec.elementAt(0).toString().trim());
				 String strSCID = tempVec.elementAt(1).toString().trim();
			     
			     String strSCDate = tempVec.elementAt(3).toString().trim();
			     
//				 	for check length of Complaint text
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
					strValue+="<td align=\"center\" height=\"20\" class=\"Arial_12_black_normal\">"+j+"</td>";
					strValue+="<td align=\"center\" height=\"20\"class=\"Arial_12_black_normal\">"+strSCID+"</td>";
					strValue+="<td align=\"center\"  height=\"20\"class=\"Arial_12_black_normal\">"+strText+"</td>";
					strValue+="<td align=\"center\" height=\"20\"class=\"Arial_12_black_normal\">"+strSCDate+"</td>";
					strValue+="<td  class=\"Arial_Narrow_12_black_bold\">";
					strValue+="<a href=\"compdetres.jsp?numCid="+numCid+"&pageid=3\"><img alt=\"Click here to see detail\" src=\"../images/detail.gif\" width=\"58\" height=\"24\" border=\"0\"></img></a></td>";
					strValue+="</tr>";
					
					
					
					
			}
					strValue += "<tr>";	
				    strValue += "<td height=\"5\" colspan=\"5\" align=\"right\"><hr color=\"#ff722b\"></td>";	
				    strValue += "</tr>";
					strValue += "<tr >";
					strValue += "<td colspan=\"5\"  align=\"right\" style=\"Arial_12_black_normal\">Page Number  "+strPageHtml+"</td>";		
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
	
	private String getPages(int minVal, int maxVal, int numSize,String numCount)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveIndvSComplistURL('searchComplaintResult.do?numCount="+numCount+"&'+this.value);\">";
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
