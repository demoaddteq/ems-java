package com.mm.struts.entp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.io.PrintWriter;
import java.util.*;

import com.mm.core.master.*;

public class CoreCompanyDetailAction extends Action {
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		////System.out.println("remander..........."+remander);
		
		String task = request.getParameter("task").trim();
		int companyId = Integer.parseInt(request.getParameter("companyId").trim().toString());
		////System.out.println("task..........."+task);
		////System.out.println("Companyid..........."+companyId);
		
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 100;
		
		EntpMaster am = new EntpMaster();	
		RootMaster ob = new RootMaster();
		
		Vector<String> paramVec1 = new Vector<String>();
		paramVec1.add("Advertiser");
		
		int totalRow = am.getCompanyCount(getDataSource(request, "entp"), paramVec1);
		
		////System.out.println("totalRow..........."+totalRow);
		String strPageHtml = getPages(minVal, maxVal, totalRow);
		
		Vector userVec = ob.getCompanyDetail(getDataSource(request, "entp"), companyId);
		////System.out.println("userVec..........."+userVec);
		int ComCityId = Integer.parseInt(userVec.elementAt(4).toString().trim());
    	String ComCityname = ob.getPlaceName(getDataSource(request, "entp"), ComCityId);		    	
    	int ComStateId = Integer.parseInt(userVec.elementAt(5).toString().trim());
    	String ComStateName = ob.getStateName(getDataSource(request, "entp"), ComStateId);		    	
    	int ComCountryId = Integer.parseInt(userVec.elementAt(6).toString().trim());
    	String ComCountryname = ob.getCountryName(getDataSource(request, "entp"), ComCountryId);
    	
    	Vector<String> infoVec = new Vector<String>();    	
    	infoVec.add(ComCityname);//0// Company City nane
    	infoVec.add(ComStateName);//1// Company State nane
    	infoVec.add(ComCountryname);//2// Company Country nane
    	
		String industryCategory = userVec.elementAt(10).toString().trim();
		////System.out.println("industryCategory..........."+industryCategory);
		
		String strtemCat[] = industryCategory.split(",");
		int i = strtemCat.length;
		System.out.println("industryCategory...length........"+strtemCat.length);
		Vector<String> industryCatName = new Vector<String>();		
		
		for (int j=1; j<i ; j++)
			
		{ 	System.out.println("industryCategory..........."+strtemCat[j].trim());
			int a = Integer.parseInt(strtemCat[j].trim());
			////System.out.println("industry Category..id ........"+a);
			String catName = ob.getStrCategoryName(getDataSource(request, "entp"), a);
			industryCatName.add(catName);
			////System.out.println("catName..........."+catName);
		}
		
		
		
		
		String strCompanyDetail = getCompanyDetail(userVec,infoVec,industryCatName,  minVal, strPageHtml, remander);
		////System.out.println("Users............................... "+strCompanyDetail);
		/**
	     * setContentType - text/html to write String on page.
	     * PrintWriter - This line use to get writer to write on page.
	     * out.println - use to write string. 
	     * 
	     */
		response.setContentType("text/html;charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
	    out.println(strCompanyDetail);
	    out.flush();	
		return null;
	}
	
	private String getCompanyDetail( Vector userVec,Vector infoVec,Vector industryCatName, int minVal, String strPageHtml, int remander)
	{
		
		

		String strValue = "<table width=\"640\">";
		if(userVec.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"50\" valign=\"center\" colspan=\"7\" class=\"bold\"> No Data Found</td>";
			strValue += "</tr>";
		}
		else
		{	////System.out.println("insite.....1................"); 
			strValue += "<table width=\"100%\" align=\"center\"  >";
			strValue += "<tr height=\"20\">";
			strValue += "<td width=\"70%\" valign=\"top\" >";
			strValue += "<table width=\"100%\" align=\"center\"  >";
			strValue += "<tr>";
			strValue += "<td colspan=\"2\" align=\"left\" valign=\"top\" class=\"bold\">";
			strValue += "Company Information</td>";
			strValue += " </tr>";
			strValue +="<tr>";
			strValue +="<td colspan=\"2\" align=\"left\" class=\"bold\"><hr color=\"#ff722b\"></td>";
			strValue +="</tr>";
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td  align=\"right\" class=\"bold\">Company Short Name:</td>";
			strValue += "  <td  align=\"left\"><span >"+userVec.elementAt(0).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\"> Company Name:</td>";
			String password = userVec.elementAt(3).toString().trim();
			String strPass ="";
			for(int i=0; i < password.length(); i++){
				strPass = strPass +"*";
			}
			strValue += "  <td align=\"left\"><span >"+userVec.elementAt(1).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\">Address1:</td>";
			strValue += "  <td align=\"left\"><span >"+userVec.elementAt(2).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			if(userVec.elementAt(3).toString().trim().length()!=0){
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\">Address2:</td>";
			strValue += "  <td align=\"left\"><span >"+userVec.elementAt(3).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			}
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\"> Country:</td>";
			strValue += " <td align=\"left\"><span >"+infoVec.elementAt(2).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\">State:</td>";
			strValue += "  <td align=\"left\">";
			strValue += "	<span >"+infoVec.elementAt(1).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\">City:</td>";
			strValue += "  <td align=\"left\">";
			strValue += "	<span >"+infoVec.elementAt(0).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\"> Pin Code:</td>";
			strValue += " <td align=\"left\"><span >"+userVec.elementAt(7).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\"> E-Mail Id:</td>";
			strValue += "  <td align=\"left\"><span >"+userVec.elementAt(8).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			String temPno1="Not mentioned";
			if(userVec.elementAt(9).toString().trim().length()!=0){
				////System.out.println("insite....................."); 
				String temPno=userVec.elementAt(9).toString().trim();
				temPno1 = temPno.replace("~", "-");
				
			
				
				strValue += " <tr height=\"20\">";
				strValue += "  <td align=\"right\" class=\"bold\"> Phone No:</td>";
				strValue += "  <td align=\"left\">";
				strValue += "	<span >"+temPno1+"</span> </td> ";
				strValue += " </tr>";
		    }
			////System.out.println("industryCatName................"+industryCatName); 
			if(industryCatName.elementAt(0)!="failure"){
			strValue += " <tr height=\"20\">";			
			strValue += "  <td align=\"right\" class=\"bold\"> *Company Template:</td>";
			strValue += "  <td  align=\"left\">";
			for(int k=0 ; k < industryCatName.size(); k++)
			{
				int l = k+1;
			strValue += "<span  >"+industryCatName.elementAt(k).toString().trim()+"</span>";
			if((l)<industryCatName.size())
			strValue +=",&nbsp;";
			}
			strValue += "</td> ";
			strValue += " </tr>";
			}
			strValue += " </table>";
			strValue += "</td>";
			strValue += " <td width=\"0%\" valign=\"top\" bgcolor=\"#F0F0F0\">&nbsp;</td>";
			strValue += " <td width=\"30%\" valign=\"top\">";
			strValue += "<table width=\"100%\" align=\"center\"  >";
			strValue += "<tr>";
			strValue += "<td colspan=\"2\" align=\"left\" valign=\"top\" class=\"bold\">";
			strValue += "Company Rights Information</td>";
			strValue += " </tr>";
			strValue +="<tr>";
			strValue +="<td colspan=\"2\" align=\"left\" class=\"bold\"><hr color=\"#ff722b\"></td>";
			strValue +="</tr>";
			strValue += "</td>";
			strValue += "</tr>";
			
			
			
			
			
			strValue += " </table>";
			strValue += "</td>";
			strValue += " </tr>";
			strValue +="<tr>";
			strValue +="<td colspan=\"3\" align=\"left\" class=\"bold\"><hr color=\"#ff722b\"></td>";
			strValue +="</tr>";
			strValue += " </table>";
			
			
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
		String strResult ="<select name=\"paging\" onchange=\"retrieveEntpCompanyDetailURL('coreCompanyDetail.do?'+this.value);\">";
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
