package com.mm.struts.student.action;

/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */


import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.core.master.IndvMaster;

/** 
 * MyEclipse Struts
 * Creation date: 07-03-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class DraftComplaintAction  extends Action {
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
		
		String userid=(String)session.getAttribute("uId");
		String companyid=(String)session.getAttribute("compId");
		System.out.println("userid................" +userid);
		System.out.println("companyid................" +companyid);
		
		IndvMaster indvMaster= new IndvMaster();		
		Vector dataVec = new Vector();		
		dataVec.add(userid);
		dataVec.add(companyid);		
			
		Vector countVec = indvMaster.complaintCount(getDataSource(request, "student"), dataVec);		
		session.setAttribute("countVec", countVec);						
		////System.out.println("countVec.............DraftComplaintAction........... "+countVec);
		
		String qtype = (request.getParameter("qtype")!=null) ? request.getParameter("qtype") : "Personal";
		////System.out.println("qtypeqtype.............DraftComplaintAction........... "+qtype);
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "creation_date";
		String strSOrder = "";
		String strDraftId = "asc";
		String strDraftTitle= "asc";
		String strDraftDate= "asc";
		if(request.getParameter("sby")!=null)
		{
			if(request.getParameter("did")!=null)
			{
				if(request.getParameter("did").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strDraftId = "desc";
				}
				else if (request.getParameter("did").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strDraftId = "asc";
				}
			}
			
			if(request.getParameter("dit")!=null)
			{
				if(request.getParameter("dit").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strDraftTitle = "desc";
				}
				else if (request.getParameter("dit").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strDraftTitle = "asc";
				}
			}
			if(request.getParameter("ddate")!=null)
			{
				if(request.getParameter("ddate").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strDraftDate = "desc";
				}
				else if (request.getParameter("ddate").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strDraftDate = "asc";
				}
			}
			
			
		}
		String strFShortBy = strShortBy+" "+strSOrder;
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 10;
		
		Vector<String> paramVec1 = new Vector<String>();
		paramVec1.add(userid);
		paramVec1.add(companyid);
		paramVec1.add(qtype);
		int totalRow = indvMaster.getDraftsCount1(getDataSource(request, "student"), paramVec1);
		System.out.println("Total Draft "+totalRow);
		//int totalRow=500;
		String strPageHtml = getPages(minVal, maxVal, totalRow,numCount);
		Vector <String> paramVec = new Vector<String>();
		paramVec.add(userid);
		paramVec.add(companyid);
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));		
		paramVec.add(strFShortBy);
		paramVec.add(qtype);
		
		
		
		Vector userVec = indvMaster.getDraftsList1(getDataSource(request, "student"), paramVec);
	//	System.out.println("Users vector"+userVec);
		String strUsers = getUserList(strDraftId, strDraftTitle, strDraftDate, minVal, maxVal, totalRow, strPageHtml, userVec, remander);
		System.out.println("Users "+strUsers);
		/**
	     * setContentType - text/html to write String on page.
	     * PrintWriter - This line use to get writer to write on page.
	     * out.println - use to write string. 
	     * 
	     */
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    //out.println(strUsers);
	    out.write(strUsers);
	    out.flush();
	    out.close();
	    return null;
	}
	
	private String getUserList(String strDraftId, String strDraftTitle,String strDraftDate, int minVal, int maxVal, int totalRow, String strPageHtml, Vector userVec, int remander)
	{
		String strValue="";
		strValue += "<div class=\"Fields_Container\">";	
		strValue += "<div class=\"Fields_Container_Inside\">";
		strValue += "<div class=\"Fields_Row_2a\">";
		strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 20%;\" text-align: right;\">Select <span class=\"bold\" onClick=\"selectAll()\" style=\"cursor:hand\">All</span>,<span class=\"bold\" onClick=\"selectNone()\" style=\"cursor:hand\">none</span></div>";
		strValue += "</div></div>";
	
		//	strValue += "<tr >";
		//	 strValue += "<td width=\"100%\" colspan=\"7\">";
		//	 strValue += "<table width=\"100%\" >";
		//	 strValue +="<td  align=\"left\"width=\"10%\" >&nbsp;<input type=\"submit\" id=\"deletebutton1\" name=\"deletebutton1\" class=\"buttonCopy\" value=\"Delete\" disabled=\"true\"></input></td>";
		//	 strValue += "<td width=\"70%\" >";
		//	 strValue += "</td >";
		//	 strValue += "<td  colspan=\"6\" align=\"right\" >Page Number  "+strPageHtml+"</td>";
		//	 strValue += "</table >";
		//	 strValue += "</td >";
		//	 strValue += "</tr>";
			 
			   
			if (totalRow > maxVal)
			   {
			    strValue +="<tr >";			
				strValue +="<td  align=\"left\" >&nbsp;<input type=\"submit\" id=\"deletebutton1\" name=\"deletebutton1\"  value=\"Delete\" disabled=\"true\"></input></td>";
				strValue += "<td colspan=\"6\"  align=\"right\" >Page Number  "+strPageHtml+"</td>";		
				strValue +="</tr>";
			   }
			 
			
			
		String strdraftIdSymbol = (strDraftId.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveIndvDComplistURL('draftComplaint.do?sby=draft_id&numMin="+ minVal+"&did="+strDraftId+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveIndvDComplistURL('draftComplaint.do?sby=draft_id&numMin="+ minVal+"&did="+strDraftId+"');\">";
	    String strdraftTitelSymbol = (strDraftTitle.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveIndvDComplistURL('draftComplaint.do?sby=draft_title&numMin="+ minVal+"&dit="+strDraftTitle+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveIndvDComplistURL('draftComplaint.do?sby=draft_title&numMin="+ minVal+"&dit="+strDraftTitle+"');\">";
	    String strdraftDateSymbol = (strDraftDate.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveIndvDComplistURL('draftComplaint.do?sby=creation_date&numMin="+ minVal+"&ddate="+strDraftDate+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveIndvDComplistURL('draftComplaint.do?sby=creation_date&numMin="+ minVal+"&ddate="+strDraftDate+"');\">";
	    
		strValue += "<div class=\"Fields_Row_2_head\">";
		strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 9%; margin-top:4px;\">S.No.</div>";			
		strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 15%;\">Draft ID"+strdraftIdSymbol+"</div>";
		strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 30%;\">Draft Title"+strdraftTitelSymbol+"</div>";
		strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 15%; margin-top:4px; text-align: left;\">Category</div>";
		strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 12%; text-align: left;\">Date"+strdraftDateSymbol+"</div>";		
		strValue += "</div>";
	
	    	for(int i=0; i<userVec.size(); i++)
			{
				int j = i+1;
		    	int numTotal = j%2;
		    	String strColor = (numTotal==0) ? "#FFFFFF" : "#EDF3F8";
		    	String strImg = (numTotal==0) ? "detail_gray.gif" : "detail_white.gif";
		    	
				Vector tempVec = (Vector)userVec.elementAt(i);
				 
				 int intDId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
				 String strDDate = tempVec.elementAt(3).toString().trim();
			     
//				 	for check length of Complaint text
				 String strText = "";  
				 String strFMsg = "";
				 
					String strMessage = tempVec.elementAt(1).toString().trim();
					int numChar = (strMessage.length() > 50) ? 50 : strMessage.length();  
					for(int k=0; k<numChar; k++)
					{		   strFMsg = strFMsg+strMessage.charAt(k);
					}
					if(strFMsg.lastIndexOf(strFMsg)!=-1)
					{
						 strText= strFMsg;
						////System.out.println("...strFMsgTeam......"+strFMsg.lastIndexOf(strFMsg));
					}else{
			        	strText= strFMsg.substring(0, strFMsg.lastIndexOf(" "));
			        	////System.out.println("...strText......");
			        }	
					strValue += "<div class=\"Fields_Row_2a\">";
					strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 10%; font-weight: normal;\"><input name=\"blogids\" type=\"checkbox\" style=\"margin:0px 0px;\"  id=\"blogids\" value=\""+intDId+"\" onClick=\"checkSelection()\"></input>&nbsp;"+j+"</div>";
					strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 15%; margin-left:2px; font-weight: normal;\">"+intDId+"</div>";
					strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 30%; font-weight: normal;\">"+strText+"</div>";
					strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 15%; font-weight: normal; text-align: left;\">Category</div>";
					strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 12%; font-weight: normal; text-align: left;\">"+strDDate+"</div>";
					strValue += "<span style=\"font-weight: normal; padding:3px 5px; float:left; font-size:11px;\"><A href=\"complaintDetail.jsp?numDraftId="+intDId+"\">Details</A></span>";				
				 strValue += "</div>";
				 
			}
			
		    if (totalRow > maxVal)
			   {
			    strValue +="<tr >";			
				strValue +="<td  align=\"left\" >&nbsp;<input type=\"submit\" id=\"deletebutton1\" name=\"deletebutton1\"  value=\"Delete\" disabled=\"true\"></input></td>";
				strValue += "<td colspan=\"6\"  align=\"right\" >Page Number  "+strPageHtml+"</td>";		
				strValue +="</tr>";
			   }
			
		if(remander==0)
		{
			strValue += "<div>";
			strValue += "</div>";
		}
		strValue += "</div></div>";
		
		return strValue;
	}
	
	private String getPages(int minVal, int maxVal, int numSize,String numCount)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveIndvDComplistURL('draftComplaint.do?numCount="+numCount+"&'+this.value);\">";
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