package com.mm.struts.corpo.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.FacilityMapDao;
import com.mm.core.master.IndvMaster;
import com.mm.core.master.RootMaster;
import com.mm.core.resource.LoginHelper;
import com.mm.resource.APPConstant;

public class GetFMCountGivenFId extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		////////System.out.println(".............DetailAllAction...................");
		
		//getFacilityMapForFacilityID
		int intTemp = Integer.parseInt(request.getParameter("temp"));
		String strTemp = "<table>";
		for(int count=0; count<intTemp ; count++)
		{
			strTemp += "<tr><td>"+count+"</td></tr>";
		}
		strTemp += "</table>";
		
		System.out.println("\n\n GetFMCountGivenFId");
		HttpSession session = request.getSession();
		int intFacilityId = Integer.parseInt(session.getAttribute("fId").toString());//getFacilityId(session);
		 
		System.out.println("line32:"+intFacilityId);
		DataSource dataSource = getDataSource(request, "corpo");
		System.out.println("line34:"+intFacilityId);
		FacilityMapDao fm = new FacilityMapDao();
		
		System.out.println("line number 49:::49:");
		ArrayList arrBuilderDetails = fm.getBuilderDetailGivenFID(dataSource, intFacilityId);
		System.out.println("line number 51:");
		session.setAttribute("ArrayBuilderDetails", arrBuilderDetails);
		System.out.println("arrBuilderDetails:"+arrBuilderDetails);
		String strFMTable ="---";;
		for(int count =0 ; count< arrBuilderDetails.size() ; count++)
		{
			if(count==0)
				strFMTable = arrBuilderDetails.get(0).toString();
			else
				strFMTable += "~$~"+arrBuilderDetails.get(count).toString();
		}
		strFMTable += "~~~~~" ;;
		
		
		
		int intCount =  fm.getFacilityMapForFacilityID(dataSource, intFacilityId);
		//int intCount = Integer.parseInt(strCount);
		
		String strFMTable1 = "No Facility Map is available";
		if(intCount > 0)
		{
			strFMTable1 = getExistedFacilityMapDetails(dataSource , intFacilityId);
		}
		
		strFMTable1 += "<input type=\"hidden\" name=\"hdFMCount\" id=\"hdFMCount\" value="+intCount+">";
		
		strFMTable += strFMTable1;
		System.out.println("strCount:"+intCount);
		
		System.out.println("\n\n strFMTable: "+strFMTable+" \n\n");
	
	response.setContentType("text/html;charset=ISO-8859-1");
    PrintWriter out = response.getWriter();
    out.println(strFMTable);
    out.flush();
	return null;	
	
	}
	public int getFacilityId(HttpSession session) {
		if(session.getAttribute(APPConstant.USER_INFORMATION) == null){
			return 1;
		}
		LoginHelper helper = (LoginHelper) session
				.getAttribute(APPConstant.USER_INFORMATION);
		return helper.getFacilityId();
	}
	
	public String getExistedFacilityMapDetails(DataSource dataSource , int intFacilityId) 
	{
		String strReturnTable = "<table cellpadding=\"0\" cellspacing=\"0\" width=\"63%\" border=\"0\" align=\"left\">";
		try
		{
			FacilityMapDao fmDAO = new FacilityMapDao(); 
			ArrayList arrFlatType = fmDAO.getFlatTypeGivenFID(dataSource, intFacilityId);
			if((arrFlatType != null) && (arrFlatType.size() > 0))
			{
				for(int i=0 ; i<arrFlatType.size() ; i++)
				{
					String strFlatType = (String) arrFlatType.get(i);
					strReturnTable += "<tr class=\"Fields_Row_5a\"><td class=\"Row_2_Fields_Col_2\" width=\"75%\" colspan='2'><b><u>Type&nbsp;:&nbsp;"+strFlatType+"</u></b></td></tr>";
					
					strReturnTable += "<tr>"
						+"<td colspan=\"2\">"
							+"<table cellpadding=\"0\" cellspacing=\"0\" width=\"84%\" border=\"0\" align=\"left\">"
								+"<tr>"
									+"<div class=\"Row_2_Fields_Col_2\" width=\"7%\">&nbsp;</div>"
									+"<div class=\"Row_2_Fields_Col_2\"><b><u>No. Of Rooms</u></b></div>"
									+"<div class=\"Row_2_Fields_Col_2\"><b><u>Tower No.</u></b></div>"
									+"<div class=\"Row_2_Fields_Col_2\"><b><u>Units</u></b></div>"
								+"</tr>"; 
					
					ArrayList arrDataGivenFIDnFlatType = fmDAO.getDataGivenFIDnFlatType(dataSource, intFacilityId, strFlatType);
					if((arrDataGivenFIDnFlatType != null) && (arrDataGivenFIDnFlatType.size() > 0))
					{
						for(int j=0 ; j<arrDataGivenFIDnFlatType.size() ; j++)
						{
							ArrayList arrDetails = (ArrayList) arrDataGivenFIDnFlatType.get(j);
							strReturnTable += "<tr>"
									+"<div class=\"Row_2_Fields_Col_2\">&nbsp;</div>"
									+"<div class=\"Row_2_Fields_Col_2\">"+arrDetails.get(0)+"</div>"
									+"<div class=\"Row_2_Fields_Col_2\">"+arrDetails.get(1)+"</div>"
									+"<div class=\"Row_2_Fields_Col_2\">"+arrDetails.get(2)+"</div>"
								+"</tr>";
						}
						 
					}
					
					strReturnTable +=  "</table> </td></tr>";
					strReturnTable +=  "<tr><td>&nbsp;</td></tr>";
				}
				 
			}
		}
		catch(Exception ex)
		{
			
		}
		strReturnTable += "</table>";
		return strReturnTable;
	}
}
