package com.mm.struts.advt.action;

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
import javax.sql.DataSource;

public class StudentListAction extends Action {
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
		String cid =(String)session.getAttribute("compId");	
		Vector allRights =(Vector)session.getAttribute("allRights");
		String adminFlag = allRights.elementAt(18).toString();
		
		
		
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "first_name";
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
		AdvtMaster advtMaster = new AdvtMaster();
		////////System.out.println("...............Inbox...............");
		DataSource ds=getDataSource(request, "advt");
		int totalRow = advtMaster.getStudentCount(getDataSource(request, "advt"), Integer.parseInt(cid));
		////////System.out.println("result totalRow.Inbox......"+totalRow);
		//int totalRow=500;
		
		String strPageHtml = getPages(minVal, maxVal, totalRow,numCount);
		
		Vector<String> paramVec = new Vector<String>();	
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));
		paramVec.add(strFShortBy);
		paramVec.add(cid);//brandUserId
				
		Vector userVec = advtMaster.getStudentList(getDataSource(request, "advt"), paramVec);
		////////System.out.println("result userVec.Inbox......"+userVec);
		
		
		
		String strComplaintList = getComplaintList(request,strComIdOrder, strCTitelOrder,strDateOrder, minVal, maxVal, totalRow, strPageHtml, userVec, remander, ds);
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
	
	private String getComplaintList(HttpServletRequest request,String strComIdOrder, String strCTitelOrder,String strDateOrder, int minVal, int maxVal, int totalRow, String strPageHtml, Vector userVec, int remander, DataSource ds)
	{
		
		
		  EntpMaster entpMaster =  new EntpMaster();
		  
		String strValue = "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
		strValue = strValue +"<form  name=\"frminbox\" method=\"post\"  action=\"studentSelect.do\" onSubmit=\"return validate()\">";
		
		if(userVec.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"300\" valign=\"center\" colspan=\"5\" > No Data Found</td>";
			strValue += "</tr>";
		}
		else
		{
			
			strValue += "<tr  id=\"list\" style=\"display:inline\">";
			strValue += "<td height=\"25\" colspan=\"6\" width=\"100%\">";
			strValue += "<table width=\"100%\">";
			strValue += "<tr  >";
			strValue += "<td height=\"25\" colspan=\"6\" align=\"left\" >&nbsp;Select <span class=\"bold\" onClick=\"selectAll()\" style=\"cursor:hand\">All</span>,<span class=\"bold\" onClick=\"selectNone()\" style=\"cursor:hand\">none</span></td>";
			strValue += "</tr>";
			strValue += "<tr >";
			 strValue += "<td width=\"100%\" colspan=\"6\">";
			 strValue += "<table width=\"100%\" >";
			 strValue +="<td  align=\"left\"width=\"10%\" >&nbsp;<input type=\"button\" id=\"deletebutton1\"  name=\"deletebutton1\"  value=\"Write Query\"  disabled=\"true\" onclick=\"hideall()\"></input></td>";
			 strValue += "<td width=\"70%\" >";
			 strValue += "</td >";
			 if (totalRow > maxVal)
				{
			 strValue += "<td  width=\"20%\" align=\"right\" >Page Number  "+strPageHtml+"</td>";
				}else
				{
					 strValue += "<td  width=\"20%\" align=\"right\" ></td>";
				}
			 strValue += "</table >";
			 strValue += "</td >";
			 strValue += "</tr>";
		strValue += "<tr>";
        strValue += "<td width=\"100%\" colspan=\"6\">";
	    strValue += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
	    
	    strValue += "<tr>";	
	    strValue += "<td height=\"5\" colspan=\"6\" align=\"right\"><hr color=\"#C1C1C1\"></td>";	
	    strValue += "</tr>";
	   
	    String strComIdSymbol = (strComIdOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveStdlistURL('studentList.do?sby=first_name&numMin="+ minVal+"&idorder="+strComIdOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveStdlistURL('studentList.do?sby=first_name&numMin="+ minVal+"&idorder="+strComIdOrder+"');\">";
	    String strCTitelSymbol = (strCTitelOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveStdlistURL('studentList.do?sby=course&numMin="+ minVal+"&ctorder="+strCTitelOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveStdlistURL('studentList.do?sby=course&numMin="+ minVal+"&ctorder="+strCTitelOrder+"');\">";
	    String strDateSymbol = (strDateOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveStdlistURL('studentList.do?sby=city&numMin="+ minVal+"&dateorder="+strDateOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveStdlistURL('studentList.do?sby=city&numMin="+ minVal+"&dateorder="+strDateOrder+"');\">";
	    
	    strValue += "<tr height=\"20\" >";	
	    strValue += "<th  align=\"left\" class=\"bold\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;S. No.</th>";
	    strValue += "<th class=\"bold\">Name&nbsp;"+strComIdSymbol+"</th>";	    
	    strValue += "<th class=\"bold\">Stream&nbsp;"+strCTitelSymbol+"</th>";	
	    strValue += "<th class=\"bold\">city&nbsp;"+strDateSymbol+"</th>";
	    strValue += "<th class=\"bold\">&nbsp;</th>";
	    strValue += "</tr>";	
	    strValue += "<tr>";	
	    strValue += "<td height=\"5\" colspan=\"6\" align=\"right\"><hr color=\"#C1C1C1\"></td>";	
	    strValue += "</tr>";
	    
	    
	   
	    
	    for(int i=0; i<userVec.size(); i++)
		{
	    	int j = i+1;
	    	int numTotal = j%2;
	    	
	    	String strColor = (numTotal==0) ? "#E6E6E6" : "#F0F0F0";
	    	String strImg = (numTotal==0) ? "detail_gray.gif" : "detail_white.gif";
		 Vector tempVec = (Vector)userVec.elementAt(i);
		 
		 int respid = Integer.parseInt(tempVec.elementAt(0).toString().trim());
		 RootMaster rm=new RootMaster();
		 IndvMaster Im=new IndvMaster();
		String cName=rm.getPlaceName(ds, Integer.parseInt(tempVec.elementAt(3).toString()));
		String Steram=Im.getCourseName(ds, Integer.parseInt(tempVec.elementAt(4).toString())).elementAt(0).toString();
		

		    
	     strValue += "<tr height=\"25\" align=\"center\" bgcolor=\""+strColor+"\">";
		    
		    strValue += "<td width=\"5%\" align=\"left\" height=\"20\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name=\"inboxids\" type=\"checkbox\"  id=\"inboxids\" value=\""+respid+"\" onClick=\"checkSelection()\"></input>&nbsp;"+j+"</td>";	
		   strValue += "<td align=\"center\" > "+tempVec.elementAt(1).toString().trim()+"&nbsp;"+tempVec.elementAt(2).toString().trim()+"</td>";	
	    strValue += "<td align=\"center\" >"+Steram+"</td>";	
	    strValue += "<td align=\"center\" > "+cName+"</td>";	
	    
	    	strValue += "<td ></td>";
	    
	    strValue += "</tr>";	
	      
	    
		}
	    
	    strValue += "<tr>";	
	    strValue += "<td height=\"20\" colspan=\"6\" align=\"right\"><hr color=\"#C1C1C1\"></td>";	
	    strValue += "</tr>";
	    strValue += "<tr >";
	    strValue +="<td  align=\"left\" >&nbsp;<input type=\"button\" id=\"deletebutton\" name=\"deletebutton\"  value=\"Write Query\" disabled=\"true\" onclick=\"hideall()\"></input></td>";
		
	    if (totalRow > maxVal)
		{
	    	strValue += "<td colspan=\"5\"  width=\"20%\" align=\"right\" >Page Number  "+strPageHtml+"</td>";
		}else
		{
			strValue += "<td colspan=\"5\"  width=\"20%\" align=\"right\" ></td>";
		}
	   		
		strValue += "</tr>";
		
		strValue += "</table>";
		strValue += "</td>";	
		strValue += "</tr>";
		
			strValue += "</table>";
			strValue += "</td>";
			strValue += "</tr>";
			
			strValue += "<tr  id=\"inPage\" style=\"display:none\" width=\"100%\">";
			strValue += "<td height=\"25\" colspan=\"6\" width=\"100%\">";
			strValue += "<table width=\"100%\" height=\"250\">";
			
			strValue += "<tr>";	
		    strValue += "<td height=\"30\" colspan=\"3\" align=\"right\"></td>";	
		    strValue += "</tr>";
			
			strValue += "<tr  >";
			strValue += "<td width=\"30%\" align=\"right\" class=\"bold\">*Query Title";
			strValue += "</td>";
			strValue += "<td width=\"1%\">";
			strValue += "</td>";
			strValue += "<td width=\"69%\" align=\"left\" ><input type=\"text\" maxlength=\"70\" tabindex=\"1\" style name=\"titel\"></input>";
			strValue += "</td>";
			strValue += "</tr>";
			
			strValue += "<tr  >";
			strValue += "<td width=\"30%\" align=\"right\" class=\"bold\" valign=\"top\">*Query Text";
			strValue += "</td>";
			strValue += "<td width=\"1%\" valign=\"top\">";
			strValue += "</td>";
			strValue += "<td width=\"69%\" valign=\"top\" align=\"left\" ><textarea  name=\"rtext\" cols=\"60\" rows=\"8\"></textarea>";
			strValue += "</td>";
			strValue += "</tr>";
			
			strValue += "<tr>";	
			strValue += "<td height=\"30\" width=\"100%\" colspan=\"3\" align=\"right\"><hr color=\"#C1C1C1\"></td>";	
			strValue += "</tr>";
			
			strValue += "<tr  >";
			strValue += "<td width=\"100%\" align=\"center\" class=\"bold\" colspan=\"3\">";
			strValue += "<input name=\"Submit\" type=\"submit\"  value=\"Submit\" ></input>";
			strValue += "</td>";
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
	
	private String getPages(int minVal, int maxVal, int numSize,String numCount)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveStdlistURL('studentList.do?numCount="+numCount+"&'+this.value);\">";
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
