package com.mm.struts.entp.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.EntpMaster;

public class BrandmgtInfoAction extends Action { 
	
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
		
		String brand = (request.getParameter("brand")!=null)? request.getParameter("brand").trim() : "1";
		
		
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "company_sname";
		String strSOrder = "";
		String strComShortNameOrder = "asc";
		String strComNameOrder = "asc";
		
		
		if(request.getParameter("sby")!=null)
		{
			if(request.getParameter("idorder")!=null)
			{
				if(request.getParameter("idorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strComShortNameOrder = "desc";
				}
				else if (request.getParameter("idorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strComShortNameOrder = "asc";
				}
			}
			
			
				if(request.getParameter("cndorder")!=null)
				{
					if(request.getParameter("cndorder").trim().equalsIgnoreCase("asc"))
					{
						strSOrder = "desc";
						strComNameOrder = "desc";
					}
					else if (request.getParameter("cndorder").trim().equalsIgnoreCase("desc"))
					{
						strSOrder = "asc";
						strComNameOrder = "asc";
					}
				}
			
			
			
		}
		
		String strFShortBy = strShortBy+" "+strSOrder;
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 100;
		
		EntpMaster entpMaster = new EntpMaster();
		DataSource ds = getDataSource(request,"entp");
		
		
		int totalRow = 0;
		Vector userVec = new Vector();
		
		if(brand.equalsIgnoreCase("1")){
			 totalRow = entpMaster.getBrandCount(ds);
			 userVec = entpMaster.getBrandList(ds, strFShortBy);
			
			}
		else if(brand.equalsIgnoreCase("2")){
			 totalRow = entpMaster.getBrandPending(ds);
			 userVec = entpMaster.getBrandPendingList(ds, strFShortBy);
		
		}
		else if(brand.equalsIgnoreCase("3")){
			 totalRow = entpMaster.getRegisteredBrand(ds, 1);
			 userVec = entpMaster.getRegisteredBrandList(ds,1, strFShortBy);

			}
		else if(brand.equalsIgnoreCase("4")){
			 totalRow = entpMaster.getRegisteredBrand(ds, 0);
			 userVec = entpMaster.getRegisteredBrandList(ds,0, strFShortBy);
			
		}
		
		//int totalRow=500;
		String strPageHtml = getPages(minVal, maxVal, totalRow);
		
		
		
		String strCompanyList = getCompanyyList(strComShortNameOrder, strComNameOrder, minVal, strPageHtml, userVec, brand);
		//System.out.println("Users "+strCompanyList);
		/**
	     * setContentType - text/html to write String on page.
	     * PrintWriter - This line use to get writer to write on page.
	     * out.println - use to write string. 
	     * 
	     */
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strCompanyList);
	    out.flush();
		
		// TODO Auto-generated method stub
		return null;
		
	}
	
	private String getCompanyyList(String strComShortNameOrder, String strComNameOrder,  int minVal, String strPageHtml, Vector userVec, String brand)
	{
		
		
		     
		String strValue = "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
		
		if(userVec.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"50\" valign=\"center\" colspan=\"6\" class=\"bold\"> No Data Found</td>";
			strValue += "</tr>";
		}
		else
		{
			strValue += "<tr >";
			strValue += "<td colspan=\"6\" align=\"right\" style=\"Arial_12_black_normal\">Page Number  "+strPageHtml+"</td>";		
			strValue += "</tr>";
		strValue += "<tr>";
        strValue += "<td width=\"100%\">";
	    strValue += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
	    
	    strValue += "<tr>";	
	    strValue += "<td height=\"5\" colspan=\"6\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";
	    //strComIdOrder
	    //strCTitelOrder
	    //strDateOrder
	    //asc, desc
	    String strComSnSymbol = (strComShortNameOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveEntpbrandmgtURL('brandmgtInfo.do?sby=company_sname&numMin="+ minVal+"&brand="+brand+"&idorder="+strComShortNameOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveEntpbrandmgtURL('brandmgtInfo.do?sby=company_sname&numMin="+ minVal+"&brand="+brand+"&idorder="+strComShortNameOrder+"');\">";
	    String strComNSymbol = (strComNameOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveEntpbrandmgtURL('brandmgtInfo.do?sby=company_name&numMin="+ minVal+"&brand="+brand+"&cndorder="+strComNameOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveEntpbrandmgtURL('brandmgtInfo.do?sby=company_name&numMin="+ minVal+"&brand="+brand+"&cndorder="+strComNameOrder+"');\">";
	   
	    strValue += "<tr height=\"20\"   >";	
	    strValue += "<th  width=\"10%\"  >&nbsp;S. No.</th>";
	    strValue += "<th  width=\"30%\" >Company Short Name&nbsp;"+strComSnSymbol+"</th>";	    
	    strValue += "<th  width=\"30%\" > Company Name&nbsp;"+strComNSymbol+"</th>";	
	    strValue += "<th  width=\"10%\" >&nbsp;</th>";
	    strValue += "<th  width=\"10%\" >&nbsp;</th>";
	    strValue += "<th  width=\"10%\" >&nbsp;</th>";
	   
	    strValue += "</tr>";	
	    strValue += "<tr>";	
	    strValue += "<td height=\"5\" colspan=\"6\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";
	    
	    
	    
	   
		
		
	    
	    for(int i=0; i<userVec.size(); i++)
		{
	    	int j = i+1;
	    	int numTotal = j%2;
	    	
	    	String strColor = (numTotal==0) ? "#E6E6E6" : "#F0F0F0";
	    	String strImg = (numTotal==0) ? "detail_gray.gif" : "detail_white.gif";
		 Vector tempVec = (Vector)userVec.elementAt(i);
		 
		 int  company_id = Integer.parseInt(tempVec.elementAt(0).toString().trim());
		 String company_sname = tempVec.elementAt(1).toString().trim();		 
		 String company_name = tempVec.elementAt(2).toString().trim();
		 int  active = Integer.parseInt(tempVec.elementAt(3).toString().trim());		 
		 String typeofcompany = tempVec.elementAt(4).toString().trim();
		 
		 String compStatas = "";
		 if(active==1)
			 compStatas = "Active";
		 else
			 compStatas = "Block";
		 
			String activeclass ="successmessages";
			if(active==0)
			{
			
				activeclass ="errormessages";
			}
			 
		 
			strValue += "<tr height=\"25\" align=\"center\" bgcolor=\""+strColor+"\">";		
	    strValue += "<td height=\"20\" >&nbsp;"+j+"</td>";	
	    strValue += "<td  align=\"center\" > "+company_sname+"</td>";
        strValue += "<td > "+company_name+" </td>";
        strValue += "<td class="+activeclass+" >"+compStatas+"</td>";                   
        strValue += "<td colspan=\"2\" height=\"25\" align=\"center\" valign=\"middle\" >";        
        strValue += "<a href=\"coreCompanyDetail.jsp?task=Details&companyId="+company_id+"\" >Detail</a>";
        
        strValue += "</td>";
		}
	    strValue += "</table>";
	    strValue += "<tr>";	
	    strValue += "<td height=\"20\" colspan=\"6\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";
	    strValue += "<tr >";
		strValue += "<td colspan=\"6\" align=\"right\" style=\"Arial_12_black_normal\">Page Number  "+strPageHtml+"</td>";		
		strValue += "</tr>";
		strValue += "</table>";
		strValue += "</td>";	
		strValue += "</tr>";
		}
	    strValue += "</table>";	
		
		return strValue;
	}
	
	private String getPages(int minVal, int maxVal, int numSize)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveEntpbrandmgtURL('brandmgtInfo.do?'+this.value);\">";
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
