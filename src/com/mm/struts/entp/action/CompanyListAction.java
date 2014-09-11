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

public class CompanyListAction extends Action {
	
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
		////System.out.println("remander..........."+remander);
		
		//String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");		
		Vector allRights = (Vector)session.getAttribute("allRights");
		String compProfileModify = allRights.elementAt(4).toString();	
		String companydeletion = allRights.elementAt(21).toString();
		Vector<String> rigthVec = new Vector<String>();
		rigthVec.add(compProfileModify);
		rigthVec.add(companydeletion);
		
		
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "company_sname";
		String strSOrder = "";
		String strComNameOrder = "asc";
		String strTypeComIdOrder = "asc";
		
		
		if(request.getParameter("sby")!=null)
		{
			if(request.getParameter("idorder")!=null)
			{
				if(request.getParameter("idorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strComNameOrder = "desc";
				}
				else if (request.getParameter("idorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strComNameOrder = "asc";
				}
			}
			
			if(request.getParameter("tofcdorder")!=null)
			{
				if(request.getParameter("tofcdorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strTypeComIdOrder = "desc";
				}
				else if (request.getParameter("tofcdorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strTypeComIdOrder = "asc";
				}
			}
			
			
			
			
			
		}
		String strFShortBy = strShortBy+" "+strSOrder;
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 20;
		EntpMaster entpMaster = new EntpMaster();
		
		Vector<String> paramVec1 = new Vector<String>();
		paramVec1.add("Advertiser");
		
		int totalRow = entpMaster.getCompanyCount(getDataSource(request, "entp"), paramVec1);
		//int totalRow=500;
		String strPageHtml = getPages(minVal, maxVal, totalRow);
		
		Vector<String> paramVec = new Vector<String>();	
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));
		paramVec.add(strFShortBy);		
		Vector userVec = entpMaster.getCompanyList(getDataSource(request, "entp"), paramVec);
		
		
		
		String strCompanyList = getCompanyyList(strComNameOrder, strTypeComIdOrder, minVal, strPageHtml, userVec, rigthVec, remander, compId);
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
	
	private String getCompanyyList(String strComIdOrder,String strTypeComIdOrder,  int minVal, String strPageHtml, Vector userVec, Vector rigthVec, int remander, String compId)
	{
		
		
		     
		String strValue = "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
		
		if(userVec.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"400\" valign=\"center\" colspan=\"7\" class=\"bold\"> No Data Found</td>";
			strValue += "</tr>";
		}
		else
		{
			strValue += "<tr >";
			strValue += "<td colspan=\"\" align=\"right\" >Page Number  "+strPageHtml+"</td>";		
			strValue += "</tr>";
		strValue += "<tr>";
        strValue += "<td width=\"100%\">";
	    strValue += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
	    strValue += "<tr>";	
	    strValue += "<td height=\"5\" colspan=\"7\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";
	    //strComIdOrder
	    //strCTitelOrder
	    //strDateOrder
	    //asc, desc
	    String strComIdSymbol = (strComIdOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveEntpCompanylistURL('companyList.do?sby=company_sname&numMin="+ minVal+"&idorder="+strComIdOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveEntpCompanylistURL('companyList.do?sby=company_sname&numMin="+ minVal+"&idorder="+strComIdOrder+"');\">";
	    String strCTitelSymbol = (strTypeComIdOrder.trim().equalsIgnoreCase("asc")) ? "<img style=\"cursor:hand\" src=\"../images/downarrow.gif\" onClick=\"retrieveEntpCompanylistURL('companyList.do?sby=typeofcompany&numMin="+ minVal+"&tofcdorder="+strTypeComIdOrder+"');\">" : "<img style=\"cursor:hand\" src=\"../images/uparrow.gif\" onClick=\"retrieveEntpCompanylistURL('companyList.do?sby=typeofcompany&numMin="+ minVal+"&tofcdorder="+strTypeComIdOrder+"');\">";
	   
	    strValue += "<tr height=\"20\" >";	
	    strValue += "<th class=\"bold\">&nbsp;S. No.</th>";
	    strValue += "<th class=\"bold\">Company Short Name&nbsp;"+strComIdSymbol+"</th>";	    
	    strValue += "<th class=\"bold\">Type of Company&nbsp;"+strCTitelSymbol+"</th>";	
	    strValue += "<th class=\"bold\">&nbsp;</th>";
	    strValue += "<th class=\"bold\">&nbsp;</th>";
	    strValue += "<th class=\"bold\">&nbsp;</th>";
	   
	    strValue += "</tr>";	
	    strValue += "<tr>";	
	    strValue += "<td height=\"5\" colspan=\"7\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";
	    
	    
	    
	  
	    for(int i=0; i<userVec.size(); i++)
		{
	    	int j = i+1;
	    	int numTotal = j%2;
	    	
	    	String strColor = (numTotal==0) ? "#E6E6E6" : "#F0F0F0";
	    	String strImg = (numTotal==0) ? "detail_gray.gif" : "detail_white.gif";
	    
		 Vector tempVec = (Vector)userVec.elementAt(i);
		 
		 String strCompanyId= tempVec.elementAt(0).toString().trim();
		 int comId = Integer.parseInt(strCompanyId);
		 
		 String strCname = tempVec.elementAt(1).toString().trim();
		 String strTypeOfComp = tempVec.elementAt(3).toString().trim();
		 String active = tempVec.elementAt(4).toString().trim();
		 
		 strValue += "<tr height=\"25\" align=\"center\" bgcolor=\""+strColor+"\">";		
	    strValue += "<td  height=\"20\" >&nbsp;"+j+"</td>";	
	    strValue += "<td  align=\"center\" > "+strCname+"</td>";
	    strValue += "<td  align=\"center\" > "+strTypeOfComp+"</td>";
	    
        strValue += "<td nowrap class=\"bold\"><a href=\"coreCompanyDetail.jsp?task=Details&companyId="+comId+"\" class=\"bold\">Details</a> </td>";
        
        if(!compId.equalsIgnoreCase(strCompanyId)){
        	
       
		        if(!rigthVec.elementAt(0).toString().equalsIgnoreCase("1")){
		        strValue += "<td  nowrap class=\"bold\">Modify</a></td>"; 
		        }
		        else{
		        	 strValue += "<td nowrap class=\"bold\" ><a href=\"companyGetData.do?task=Modify&companyId="+comId+"\" class=\"bold\">Modify	</td>"; 
		        	
		        }
		        strValue += "<td  height=\"25\" align=\"center\" valign=\"middle\" nowrap class=\"bold\" >Delete</td>";   
		        
		        
		        if(rigthVec.elementAt(0).toString().equalsIgnoreCase("1"))
		        {
		        strValue += "<td nowrap class=\"bold\"  height=\"25\" align=\"center\" valign=\"middle\" >";         
			        if(active.equalsIgnoreCase("1"))
			        strValue += "<a href=\"companyGetData.do?task=Block&companyId="+comId+"\" class=\"bold\">Block</a>"; 
			        else
			        strValue += "<a href=\"companyGetData.do?task=Active&companyId="+comId+"\" class=\"bold\">Active</a>"; 
			    strValue += "</td>"; 		
			       
				}else
				{
				strValue += "<td  height=\"25\" align=\"center\" valign=\"middle\" class=\"bold\">";         
			        if(active.equalsIgnoreCase("1"))
			        strValue += "Block"; 
			        else
			        strValue += "Active"; 
			    strValue += "</td>"; 
				}
        
        }
        else {
        	 strValue += "<td  nowrap class=\"bold\">Modify</a></td>"; 
        	 strValue += "<td  height=\"25\" align=\"center\" valign=\"middle\" nowrap class=\"bold\" >Delete</td>";  
        	 strValue += "<td  height=\"25\" align=\"center\" valign=\"middle\" class=\"bold\">";         
		        if(active.equalsIgnoreCase("1"))
		        strValue += "Block"; 
		        else
		        strValue += "Active"; 
		    strValue += "</td>"; 
        	
        }
        }//for loop
	    
	    	
	    
	    strValue += "</table>";
	    strValue += "<tr>";	
	    strValue += "<td height=\"20\" colspan=\"7\" align=\"right\"><hr color=\"#ff722b\"></td>";	
	    strValue += "</tr>";
	    
	    strValue += "<tr >";
		strValue += "<td colspan=\"7\" align=\"right\" >Page Number  "+strPageHtml+"</td>";		
		strValue += "</tr>";
		strValue += "</table>";
		strValue += "</td>";	
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
	
	private String getPages(int minVal, int maxVal, int numSize)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveEntpCompanylistURL('companyList.do?'+this.value);\">";
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
