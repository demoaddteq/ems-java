package com.mm.struts.entp.action;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.EntpMaster;

/** 
 * MyEclipse Struts
 * Creation date: 08-01-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */


public class UserrightsgroupAction extends Action {
	
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
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		int numCompId = (request.getParameter("compId")!=null) ? Integer.parseInt(request.getParameter("compId").trim()) : 0;
		int numTempCount = (request.getParameter("numCount") != null) ? Integer.parseInt(request.getParameter("numCount").trim()) : 0;
		int numPageState = (request.getParameter("pgstate") != null) ? Integer.parseInt(request.getParameter("pgstate").trim()) : 0;
		
		int numGroupId = (request.getParameter("grpid") != null) ? Integer.parseInt(request.getParameter("grpid").trim()) : 0;
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "groupname";
		String strgnameOrder = "asc";
		String strSOrder = "";
		
			if(request.getParameter("sby")!=null)
		{
			if(request.getParameter("grporder")!=null)
			{
				if(request.getParameter("grporder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strgnameOrder = "desc";
				}
				else if (request.getParameter("grporder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strgnameOrder = "asc";
				}
			}
			
		}
		
		
		String strFShortBy = strShortBy+" "+strSOrder;
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 20;
		EntpMaster entpMaster = new EntpMaster(); 
		int totalRow = entpMaster.getUserGroupCount(getDataSource(request, "entp"), numCompId);
		String strPageHtml = getPages(minVal, maxVal, totalRow , numCompId,numTempCount);
		//System.out.println("totalRow............."+totalRow);
		//System.out.println("strPageHtml............."+strPageHtml);
		
		HttpSession session = request.getSession();	
		Vector allRightsVec = (Vector)session.getAttribute("allRights");
		int adminFlag = Integer.parseInt(allRightsVec.elementAt(18).toString().trim());
		Vector<String> paramVec = new Vector<String>();
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));
		paramVec.add(String.valueOf(numCompId));
		paramVec.add(strFShortBy);		
		
		
		Vector listVec= entpMaster.getUserGroupList(getDataSource(request, "entp"), paramVec);
		
		String strUGroup = "";
		if(numPageState==1)
		{
			strUGroup = getGroupsOption( numGroupId, numPageState, listVec, numTempCount, adminFlag);
		}
		else
		{
			strUGroup = getGroups(strgnameOrder,minVal,strPageHtml,listVec, numTempCount,numCompId);
		}
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    //out.println(strtblSubCategory);
	    out.write(strUGroup);
	    out.flush();
		return null;
	}
	private String getGroups( String strgnameOrder, int minVal, String strPageHtml,Vector lstVec, int numTempCount,int numCompId)
	{
		String strResult="";
		strResult += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
		if(lstVec.size()<=0)
		{
			
			strResult += "<tr align=\"center\">";
			strResult += "<td height=\"400\" valign=\"center\" colspan=\"7\" class=\"bold\"> User Rights Group Not Available</td>";
			strResult += "</tr>";
		}
		else
		{
			strResult += "<tr >";
			strResult += "<td colspan=\"7\" align=\"right\" >Page Number  "+strPageHtml+"</td>";		
			strResult += "</tr>";
			strResult += "<tr>";	
		    strResult += "<td height=\"5\" colspan=\"7\" align=\"right\"><hr color=\"#ff722b\"></td>";	
		    strResult += "</tr>";
		    
		    
		    String strgnameSymbol = (strgnameOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveUserGrp('userrightsgroup.do?sby=groupname&numMin="+ minVal+"&grporder="+strgnameOrder+"&compId="+numCompId+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveUserGrp('userrightsgroup.do?sby=groupname&numMin="+ minVal+"&grporder="+strgnameOrder+"&compId="+numCompId+"');\">";
		    strResult += "<tr height=\"20\" >";	
		    strResult += "<th class=\"bold\" align=\"center\">&nbsp;S. No.</th>";
		    strResult += "<th  width=\"25%\"class=\"bold\" align=\"center\">Group Name&nbsp;"+strgnameSymbol+"</th>";	    
		    strResult += "<th  colspan=\"5\" class=\"bold\" align=\"center\">&nbsp;</th>";
		   
		    strResult += "</tr>";	
		    strResult += "<tr>";	
		    strResult += "<td height=\"5\" colspan=\"7\" align=\"right\"><hr color=\"#ff722b\"></td>";	
		    strResult += "</tr>";	
		
			
			
			
			int numCount = 1;
			for(int i=0; i<lstVec.size(); i++)
			{
				int j = i+1;
		    	int numTotal = j%2;
		    	String strColor = (numTotal==0) ? "#E6E6E6" : "#F0F0F0";
		    	String strImg = (numTotal==0) ? "detail_gray.gif" : "detail_white.gif";
				Vector tempVec = (Vector)lstVec.elementAt(i);
				int numGrId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
				String numGrName = tempVec.elementAt(1).toString().trim();
				
				
				
				
				strResult += "<tr border=\"0\" height=\"25\" align=\"center\" bgcolor=\""+strColor+"\">";	
				strResult += "<td  width=\"10%\"  > "+j+" </td>"; 
				strResult += "<td width=\"15%\"   > "+numGrName+" </td>";
				
				strResult+="<td  align=\"center\"  style=\"cursor:hand\" onclick=\"javascript:location='manipulategrp.jsp?typ=Details&grpid="+numGrId+"'\" class=\"bold\">Details</td>";
				strResult+="<td align=\"center\"   style=\"cursor:hand\" onclick=\"javascript:location='manipulategrp.jsp?typ=Modify&grpid="+numGrId+"'\" class=\"bold\">Modify</td>";
				
				strResult+="<td  align=\"center\"   style=\"cursor:hand\" onclick=\"javascript:location='manipulategrp.jsp?typ=Delete&grpid="+numGrId+"'\" class=\"bold\">Delete</td>";
				strResult+="<td  colspan=\"2\"  align=\"center\"  ></td>";
				
				 
				
				strResult+="</tr>";
				numCount++;
			}
		}

		strResult += "<tr>";	
	    strResult += "<td height=\"5\" colspan=\"7\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strResult += "</tr>";
	    strResult += "<tr >";
				
		strResult += "</tr>";
		strResult+="<tr>";
		strResult+="<td align=\"center\" >&nbsp;&nbsp;&nbsp;<input type=\"button\" name=\"add\" value=\"Add New\"  onclick=\"javascript:location='manipulategrp.jsp?typ=Add New'\"></td>";
		strResult += "<td colspan=\"6\" align=\"right\" >Page Number  "+strPageHtml+"</td>";
		strResult+="</tr>";
		int numTotal = numTempCount%2;
		//System.out.println("Num Total "+numTotal);
		if(numTotal!=0)
		{
			strResult+="<tr>";
			strResult+="<td align=\"center\" colspan=\"5\">&nbsp;</td>";
			strResult+="</tr>";
		}
		strResult+="</table>";
		return strResult;
	}
	private String getGroupsOption(int numGroupId, int numPageState, Vector lstVec, int numTempCount, int adminFlag)
	{
		
		
		int numTotal = numTempCount%2;
		String strResult = "<table width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">";
		if(numTotal!=0)
		{
			strResult += "<tr>";
			strResult += "<td colspan=\"6\" class=\"bold\">&nbsp;</td>";
			strResult += "<td>&nbsp;</td>";
			strResult += "</tr>";
		}
		strResult += "<tr>";
		if(adminFlag == 1){
			strResult += "<td colspan=\"6\" class=\"bold\">&nbsp;USER RIGHTS</td>";
			strResult += "<td  align=\"right\" style=\"cursor:hand\" onclick=\"javascript:location='manipulategrp.jsp?typ=Add New'\"><font color=\"blue\"><u><b>Add More Rights Group</b></u></font></td>";
			strResult += "</tr>";
		}
		else
		{
			strResult += "<td colspan=\"6\" class=\"bold\">&nbsp;USER RIGHTS</td>";
			strResult += "<td >&nbsp;</td>";
			strResult += "</tr>";
		}
		strResult += "<tr>";
		strResult += "<td width=\"10%\" class=\"bold\">&nbsp;</td>";
		strResult += "<td width=\"25%\" align=\"right\">&nbsp;</td>";
		strResult += "<td width=\"40%\">&nbsp;</td>";
		strResult += "<td colspan=\"4\" width=\"25%\">&nbsp;</td>";
		strResult += "</tr>";
		
		for(int i=0; i<lstVec.size(); i++)
		{
			Vector tempVec = (Vector)lstVec.elementAt(i);
			int numGrId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			String numGrName = tempVec.elementAt(1).toString().trim();
			strResult += "<tr>";
			strResult += "<td>&nbsp;</td>";
			if(numGroupId == numGrId)
			{
				strResult += "<td align=\"right\"><input name=\"rdbgroup\" type=\"radio\" value=\""+numGrId+"\" checked=\"true\">&nbsp;</td>";
			}
			else
			{
				strResult += "<td align=\"right\"><input name=\"rdbgroup\" type=\"radio\" value=\""+numGrId+"\">&nbsp;</td>";
			}
			strResult += "<td >&nbsp;"+numGrName+"</td>";
			strResult += "<td>&nbsp;</td>";
			strResult += "</tr>";
		}
		strResult += "<tr>";
		strResult += "<td colspan=\"4\" class=\"bold\"></td>";
		strResult += "</tr>";
		
		
		strResult += "</table>";
		
		return strResult;
	}
	
	
	private String getPages(int minVal, int maxVal, int numSize,int compId,int numTempCount)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveUserGrp('userrightsgroup.do?compId="+compId+"&numCount="+numTempCount+"&'+this.value);\">";
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
