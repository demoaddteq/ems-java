/*
 * Generated by MyEclipse Struts
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
/** 
 * MyEclipse Struts
 * Creation date: 07-03-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */

public class CommunitycustomizeAction extends Action {
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
		//System.out.println("remander..........."+remander);
		
		
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "company_sname";
		String strSOrder = "";
		String strCommunityName = "asc";
		
		
		if(request.getParameter("sby")!=null)
		{
			if(request.getParameter("communityname")!=null)
			{
				if(request.getParameter("communityname").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strCommunityName = "desc";
				}
				else if (request.getParameter("communityname").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strCommunityName = "asc";
				}
			}
			
				
			
		}
		String strFShortBy = strShortBy+" "+strSOrder;
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 20;
		EntpMaster entpMaster = new EntpMaster();
		
		int totalRow = entpMaster.getCommunityCount(getDataSource(request, "entp"));
		//int totalRow=200;
		String strPageHtml = getPages(minVal, maxVal, totalRow,count);
		
		
		Vector CommunityVec = new Vector();
		CommunityVec = entpMaster.getCommunityList(getDataSource(request, "entp"), strFShortBy ,minVal,maxVal);
		
		
		
		
		String strUsers = getCommunityList(strCommunityName,  minVal, strPageHtml, CommunityVec, remander);
		//System.out.println("Users "+strUsers);
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
	
	private String getCommunityList(String strCommunityName,  int minVal, String strPageHtml, Vector CommunityVec, int remander)
	{
		
		
		String strValue = "<table width=\"100%\">";
		if(CommunityVec.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"400\" valign=\"center\" colspan=\"3\" class=\"bold\"> No Data Found</td>";
			strValue += "</tr>";
		}
		else
		{
			strValue += "<tr >";
			strValue += "<td colspan=\"3\" align=\"right\" >Page Number  "+strPageHtml+"</td>";		
			strValue += "</tr>";
		strValue += "<tr >";
		strValue += "<td colspan=\"3\">";
		strValue += "<table width=\"100%\" border=\"0\"  align=\"center\" cellpadding=\"0\" cellspacing=\"0\"   >";
		
		strValue += "<tr>";	
	    strValue += "<td height=\"5\" colspan=\"3\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";
	    
	    String strCommunityNameSymbol = (strCommunityName.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveEntpCcustlistURL('communitycustomize.do?sby=company_sname&numMin="+ minVal+"&communityname="+strCommunityName+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveEntpCcustlistURL('communitycustomize.do?sby=company_sname&numMin="+ minVal+"&communityname="+strCommunityName+"');\">";
	    
	    
	    
	    strValue += "<tr height=\"20\" >";	
	    strValue += "<th class=\"bold\">&nbsp;S. No.</th>";
	    strValue += "<th class=\"bold\">Community Name&nbsp;"+strCommunityNameSymbol+"</th>";	    
	    strValue += "<th class=\"bold\">&nbsp;</th>";
	    strValue += "</tr>";	
	    strValue += "<tr>";	
	    strValue += "<td height=\"5\" colspan=\"3\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";
		
		
		
		
			for(int i=0; i<CommunityVec.size(); i++)
			{
				int j = i+1;
		    	int numTotal = j%2;
		    	String strColor = (numTotal==0) ? "#E6E6E6" : "#F0F0F0";
		    	String strImg = (numTotal==0) ? "detail_gray.gif" : "detail_white.gif";
				 Vector tempVec = (Vector)CommunityVec.elementAt(i);
				 
				 int CompanyId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
				 String CommunityName = tempVec.elementAt(1).toString().trim();
			     
				 
				 strValue += "<tr height=\"25\" align=\"center\" bgcolor=\""+strColor+"\">";
				strValue += "<td  width=\"10%\"height=\"10%\" > "+j+" </td>"; 
				strValue += "<td width=\"30%\" height=\"25\" nowrap > "+CommunityName+" </td>";
				strValue += "<td  height=\"20\" valign=\"middle\" nowrap class=\"bold\">"; 
				strValue +="<A href=\"communitcustPre.do?CompId="+CompanyId+"\" class=\"bold\" >Customize</A>";				
				strValue += "</tr>";				
				
			}
		
		
		strValue += "</table >";
		strValue += "</td >";
		strValue += "</tr >";
		strValue += "<tr>";	
	    strValue += "<td height=\"20\" colspan=\"3\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";
		strValue += "<tr >";
		strValue += "<td colspan=\"3\"  align=\"right\" >Page Number "+strPageHtml+"</td>";		
		strValue += "</tr>";
		}
		if(remander==0)
		{
			strValue += "<tr style=\"display:none\" align=\"center\">";
			strValue += "<td height=\"50\" valign=\"center\" colspan=\"3\" class=\"bold\"></td>";
			strValue += "</tr>";
		}
		strValue += "</table>";
		
		
		return strValue;
	}
	
	private String getPages(int minVal, int maxVal, int numSize ,int numCount)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveEntpCcustlistURL('communitycustomize.do?count="+numCount+"&'+this.value);\">";
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