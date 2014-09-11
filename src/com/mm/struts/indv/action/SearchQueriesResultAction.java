package com.mm.struts.indv.action;

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

import com.mm.core.master.*;

public class SearchQueriesResultAction extends Action{
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		IndvMaster indvMaster = new IndvMaster();
		RootMaster rootMaster = new RootMaster();
		HttpSession session =request.getSession();
		String numCount = (String)session.getAttribute("numCount");
		String strComplaints= "<tr><td>No Data</td></tr>";
		
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 20;
		
		Vector<String> paramVec = new Vector<String>();
		
		paramVec.add(String.valueOf(minVal) );
		paramVec.add(String.valueOf(maxVal));
		
		if(session.getAttribute("searchDataVec")!=null)
		{
			System.out.println("dc in if");
			Vector searchVec = (Vector)session.getAttribute("searchDataVec");
			for(int i=0;i<searchVec.size();i++)
				paramVec.add(searchVec.elementAt(i).toString().trim());
			System.out.println("dc in if paramVec.........."+paramVec);
		}		
		System.out.println("size of paramVec......"+paramVec.size());
		
		Vector searchResultVec = new Vector();
		
		int totalRow = 0;
		String strDes = "";
		
		if(paramVec.elementAt(3).equalsIgnoreCase("Student") ){
			
			 searchResultVec = indvMaster.getQuerisResultList(getDataSource(request, "indv"), paramVec);
			  totalRow = indvMaster.getQuerisResultCount(getDataSource(request, "indv"), paramVec);
			  strDes = "Student";
			  
			  
			  	
		}
		else if(paramVec.elementAt(3).equalsIgnoreCase("Corporates") ){
			
			 searchResultVec = indvMaster.getQuerisResultComList(getDataSource(request, "indv"), paramVec);
			 totalRow = indvMaster.getQuerisResultComCount(getDataSource(request, "indv"), paramVec);
			 strDes = "Corporates";
			 
			 	
		}
		else if( paramVec.elementAt(3).equalsIgnoreCase("Consultants")){
				
			 searchResultVec = indvMaster.getQuerisResultList(getDataSource(request, "indv"), paramVec);
			  totalRow = indvMaster.getQuerisResultCount(getDataSource(request, "indv"), paramVec);
			  strDes = "Consultants";
			 
		}
		else if( paramVec.elementAt(3).equalsIgnoreCase("Advertiser")){
			
			 searchResultVec = indvMaster.getQuerisResultComList(getDataSource(request, "indv"), paramVec);
			 totalRow = indvMaster.getQuerisResultComCount(getDataSource(request, "indv"), paramVec);
			 strDes = "Advertiser";
			 
					}
		
		System.out.println("searchResultVec ......"+searchResultVec);
		
		System.out.println("totalRow ......"+totalRow);
		
		
		
		String strPageHtml = getPages(minVal, maxVal, totalRow, numCount);
		
		strComplaints = getresultList(getDataSource(request, "indv"), strDes, searchResultVec,  strPageHtml, totalRow, minVal, maxVal);
		
		
		
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strComplaints);
	    out.flush();
		return null;	
		
	}
	
	private String getresultList(DataSource ds, String strDes, Vector searchResultVec, String strPageHtml, int totalRow, int minVal, int maxVal)
	{
		
		RootMaster rootMaster = new RootMaster();
		int minVal1 = minVal+1;
		int maxVal1 = maxVal;
		
		if(maxVal>totalRow)
		{
			maxVal1 = totalRow;
		}
		
		String strValue = "<table width=\"100%\"   align=\"center\" cellpadding=\"0\" cellspacing=\"0\"  >";
		if(searchResultVec.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"400\" valign=\"center\" colspan=\"5\" class=\"Arial_Narrow_16_orange_normal\"> No Data Found</td>";
			strValue += "</tr>";
		}
		else
		{
			strValue += "<tr >";
			strValue += "<td colspan=\"2\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">Results <span class=\"Arial_12_black_normal\"> "+minVal1+"</span> - <span class=\"Arial_12_black_normal\">"+maxVal1+"</span> of about <span class=\"Arial_12_black_normal\">"+totalRow+"</span></td>";	
			strValue += "<td colspan=\"3\" align=\"right\" class=\"Arial_12_black_normal\">Page Number  "+strPageHtml+"</td>";		
			strValue += "</tr>";
			
			strValue += "<tr>";	
		    strValue += "<td height=\"5\" colspan=\"5\" align=\"right\"><hr color=\"#ff722b\"></td>";	
		    strValue += "</tr>";
		    
		    if(strDes.equalsIgnoreCase("Student")){
		    	
		    	for(int i=0; i< searchResultVec.size(); i++){
		    		
		    		int j = i+1;
		    		Vector tempVec = (Vector)searchResultVec.elementAt(i);
		    		
		    		int contryId = Integer.parseInt(tempVec.elementAt(7).toString());//dealer contryId id
					String contryStr = rootMaster.getCountryName(ds,contryId);
					int stateId = Integer.parseInt(tempVec.elementAt(8).toString());//dealer stateId id
					String stateStr = rootMaster.getStateName(ds,stateId);
					int cityId = Integer.parseInt(tempVec.elementAt(9).toString());//dealer cityId id
					String cityStr = rootMaster.getPlaceName(ds,cityId);
					
					String phone = tempVec.elementAt(4).toString().replace("~", "-");
					String mobile = tempVec.elementAt(5).toString().replace("~", "-");
					
					Vector extraVec = new Vector();
					
					extraVec.add(contryStr);
					extraVec.add(stateStr);
					extraVec.add(cityStr);
					extraVec.add(phone);
					extraVec.add(mobile);
					
		    		
	    		strValue += "<tr height=\"25\" align=\"center\" bgcolor=\"#E6E6E6\">";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+j+"</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
				strValue+="</td>";
				strValue+="</tr>";
		    	
		    	strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Student Name:  </td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;<A href=\"detailAll.jsp?cuid="+tempVec.elementAt(0).toString()+"&detailFor=Student\">"+tempVec.elementAt(1).toString()+"&nbsp; "+tempVec.elementAt(2).toString()+"</A></td>";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				
				/*strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Gender:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(16).toString()+" &nbsp;&nbsp;"+tempVec.elementAt(11).toString()+"</td>";				
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td width=\"30%\" class=\"Arial_Narrow_12_black_bold\">";
				strValue+="<td align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="</td>";
				strValue+="</tr>";
				
				strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">E-mail Id:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(3).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				
				strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Address:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(6).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				
				strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Country:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(0).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"Arial_Narrow_12_black_bold\">State:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(1).toString()+"</td>";
				strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				
				strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">City:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(2).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"Arial_Narrow_12_black_bold\">Pin No:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(10).toString()+"</td>";
				strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				
				strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Phone No:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(3).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"Arial_Narrow_12_black_bold\">Mobile No:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(4).toString()+"</td>";
				strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				*/
		    	}
		    	
		    }
		    else if(strDes.equalsIgnoreCase("Consultants"))
		    {
		    	for(int i=0; i< searchResultVec.size(); i++){
		    		
		    		int j = i+1;
		    		Vector tempVec = (Vector)searchResultVec.elementAt(i);
		    		
		    		int contryId = Integer.parseInt(tempVec.elementAt(7).toString());//dealer contryId id
					String contryStr = rootMaster.getCountryName(ds,contryId);
					int stateId = Integer.parseInt(tempVec.elementAt(8).toString());//dealer stateId id
					String stateStr = rootMaster.getStateName(ds,stateId);
					int cityId = Integer.parseInt(tempVec.elementAt(9).toString());//dealer cityId id
					String cityStr = rootMaster.getPlaceName(ds,cityId);
					
					String phone = tempVec.elementAt(4).toString().replace("~", "-");
					String mobile = tempVec.elementAt(5).toString().replace("~", "-");
					
					Vector extraVec = new Vector();
					
					extraVec.add(contryStr);
					extraVec.add(stateStr);
					extraVec.add(cityStr);
					extraVec.add(phone);
					extraVec.add(mobile);
		    		
	    		strValue += "<tr height=\"25\" align=\"center\" bgcolor=\"#E6E6E6\">";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+j+"</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
				strValue+="</td>";
				strValue+="</tr>";
		    	
		    	strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Consultants Name:  </td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;<A href=\"detailAll.jsp?cuid="+tempVec.elementAt(0).toString()+"&detailFor=Consultants\">"+tempVec.elementAt(1).toString()+"&nbsp; "+tempVec.elementAt(2).toString()+"</A></td>";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				
				/*strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Gender:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(16).toString()+" &nbsp;&nbsp;"+tempVec.elementAt(11).toString()+"</td>";				
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td width=\"30%\" class=\"Arial_Narrow_12_black_bold\">";
				strValue+="<td align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="</td>";
				strValue+="</tr>";
				
				strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">E-mail Id:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(3).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				
				strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Address:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(6).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
				strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				
				strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Country:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(0).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"Arial_Narrow_12_black_bold\">State:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(1).toString()+"</td>";
				strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				
				strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">City:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(2).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"Arial_Narrow_12_black_bold\">Pin No:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(10).toString()+"</td>";
				strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				
				strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Phone No:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(3).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"Arial_Narrow_12_black_bold\">Mobile No:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(4).toString()+"</td>";
				strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				*/
		    	}
		    	
		    	
		    }
		    else if(strDes.equalsIgnoreCase("Corporates"))
		    {
		    	for(int i=0; i< searchResultVec.size(); i++){
		    		
		    		int j = i+1;
		    		Vector tempVec = (Vector)searchResultVec.elementAt(i);
		    		
		    		int contryId = Integer.parseInt(tempVec.elementAt(7).toString());//dealer contryId id
					String contryStr = rootMaster.getCountryName(ds,contryId);
					int stateId = Integer.parseInt(tempVec.elementAt(6).toString());//dealer stateId id
					String stateStr = rootMaster.getStateName(ds,stateId);
					int cityId = Integer.parseInt(tempVec.elementAt(5).toString());//dealer cityId id
					String cityStr = rootMaster.getPlaceName(ds,cityId);
					
					String phone = tempVec.elementAt(10).toString().replace("~", "-");
					String Category = tempVec.elementAt(11).toString();
					System.out.println("Category.............."+Category);
					
					String strtemCat[] = Category.split(", ");
					int k = strtemCat.length;
					System.out.println("industryCategory...length........"+strtemCat.length);
					Vector<String> industryCatName = new Vector<String>();		
					
					for (int m=0; m<k ; m++)
						
					{ 	int a = Integer.parseInt(strtemCat[m]);
						
						String catName = rootMaster.getStrCategoryName(ds, a);
						industryCatName.add(catName);
						
					}
					
					System.out.println("industryCatName..........."+industryCatName);
					
					Vector extraVec = new Vector();
					
					extraVec.add(contryStr);
					extraVec.add(stateStr);
					extraVec.add(cityStr);
					extraVec.add(phone);
					
					strValue += "<tr height=\"25\" align=\"center\" bgcolor=\"#E6E6E6\">";
					strValue+="<td width=\"20%\" align=\"left\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+j+"</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
					strValue+="</td>";
					strValue+="</tr>";
			    	
			    	strValue += "<tr height=\"25\" align=\"center\">";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Company Name:  </td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;<A href=\"detailAll.jsp?cuid="+tempVec.elementAt(0).toString()+"&detailFor=Corporates\">"+tempVec.elementAt(2).toString()+"&nbsp;</A> </td>";
					strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
					strValue+="</td>";
					strValue+="</tr>";
					
										
					/*strValue += "<tr height=\"25\" align=\"center\">";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">E-mail Id:</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(9).toString()+"</td>";
					strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
					strValue+="</td>";
					strValue+="</tr>";
					
					strValue += "<tr height=\"25\" align=\"center\">";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Address:</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(3).toString()+"</td>";
					if(tempVec.elementAt(4).toString()!=null){
						strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(4).toString()+"</td>";
						
					}else{
						strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;</td>";
						
					}
					
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
					strValue+="</td>";
					strValue+="</tr>";
					
					strValue += "<tr height=\"25\" align=\"center\">";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Country:</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(0).toString()+"</td>";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"Arial_Narrow_12_black_bold\">State:</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(1).toString()+"</td>";
					strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
					strValue+="</td>";
					strValue+="</tr>";
					
					strValue += "<tr height=\"25\" align=\"center\">";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">City:</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(2).toString()+"</td>";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"Arial_Narrow_12_black_bold\">Pin No:</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(8).toString()+"</td>";
					strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
					strValue+="</td>";
					strValue+="</tr>";
					
					strValue += "<tr height=\"25\" align=\"center\">";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Phone No:</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(3).toString()+"</td>";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"Arial_Narrow_12_black_bold\"></td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;</td>";
					strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
					strValue+="</td>";
					strValue+="</tr>";
		    		*/		    		
					
			    	}
		    	
		    }
		    else if(strDes.equalsIgnoreCase("Advertiser"))
		    {
		    	for(int i=0; i< searchResultVec.size(); i++){
		    		
		    		int j = i+1;
		    		Vector tempVec = (Vector)searchResultVec.elementAt(i);
		    		
		    		int contryId = Integer.parseInt(tempVec.elementAt(7).toString());//dealer contryId id
					String contryStr = rootMaster.getCountryName(ds,contryId);
					int stateId = Integer.parseInt(tempVec.elementAt(6).toString());//dealer stateId id
					String stateStr = rootMaster.getStateName(ds,stateId);
					int cityId = Integer.parseInt(tempVec.elementAt(5).toString());//dealer cityId id
					String cityStr = rootMaster.getPlaceName(ds,cityId);
					
					String phone = tempVec.elementAt(10).toString().replace("~", "-");
					
					
					Vector extraVec = new Vector();
					
					extraVec.add(contryStr);
					extraVec.add(stateStr);
					extraVec.add(cityStr);
					extraVec.add(phone);
		    		
					strValue += "<tr height=\"25\" align=\"center\" bgcolor=\"#E6E6E6\">";
					strValue+="<td width=\"20%\" align=\"left\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+j+"</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
					strValue+="</td>";
					strValue+="</tr>";
			    	
			    	strValue += "<tr height=\"25\" align=\"center\">";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">College Name:  </td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;<A href=\"detailAll.jsp?cuid="+tempVec.elementAt(0).toString()+"&detailFor=Student\">"+tempVec.elementAt(2).toString()+"&nbsp; </A></td>";
					strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
					strValue+="</td>";
					strValue+="</tr>";
					
										
					/*strValue += "<tr height=\"25\" align=\"center\">";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">E-mail Id:</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(9).toString()+"</td>";
					strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
					strValue+="</td>";
					strValue+="</tr>";
					
					strValue += "<tr height=\"25\" align=\"center\">";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Address:</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(3).toString()+"</td>";
					strValue+="<td width=\"20%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(4).toString()+"</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\"></td>";
					strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
					strValue+="</td>";
					strValue+="</tr>";
					
					strValue += "<tr height=\"25\" align=\"center\">";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Country:</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(0).toString()+"</td>";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"Arial_Narrow_12_black_bold\">State:</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(1).toString()+"</td>";
					strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
					strValue+="</td>";
					strValue+="</tr>";
					
					strValue += "<tr height=\"25\" align=\"center\">";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">City:</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(2).toString()+"</td>";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"Arial_Narrow_12_black_bold\">Pin No:</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+tempVec.elementAt(8).toString()+"</td>";
					strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
					strValue+="</td>";
					strValue+="</tr>";
					
					strValue += "<tr height=\"25\" align=\"center\">";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"Arial_Narrow_12_black_bold\">Phone No:</td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(3).toString()+"</td>";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"Arial_Narrow_12_black_bold\"></td>";
					strValue+="<td width=\"30%\" align=\"left\" height=\"20\"class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;</td>";
					strValue+="<td class=\"Arial_Narrow_12_black_bold\">";
					strValue+="</td>";
					strValue+="</tr>";
					*/
			    	}
		    	
		    }
		    
		    
		    
		    strValue += "<tr>";	
		    strValue += "<td height=\"5\" colspan=\"5\" align=\"right\"><hr color=\"#ff722b\"></td>";	
		    strValue += "</tr>";
			strValue += "<tr >";
			strValue += "<td colspan=\"5\"  align=\"right\" style=\"Arial_12_black_normal\">Page Number  "+strPageHtml+"</td>";		
			strValue += "</tr>";
			
		}
		
		strValue += "</table>";
		return strValue;
	}
	
	private String getPages(int minVal, int maxVal, int numSize,String numCount)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveQueriesResultURL('searchQueriesResult.do?numCount="+numCount+"&'+this.value);\">";
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
