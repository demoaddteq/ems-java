package com.mm.struts.student.action;

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
import org.omg.CORBA.portable.ValueOutputStream;

import com.mm.core.master.*;

public class SearchQueriesResultAction extends Action{
	
	
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
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		DataSource dataSource = getDataSource(request, "student");
		HttpSession session =request.getSession();
		
		
		
		RootMaster  rootMaster = new RootMaster();
		IndvMaster  indvMaster = new IndvMaster();
		String numCount = (request.getParameter("numCount")!=null)? request.getParameter("numCount") :(String)session.getAttribute("numCount");
		
		int count = Integer.parseInt(numCount);
		int reminder = count%2;
		Vector<String> paramVec = new Vector<String>();
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 10;
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));
		
		String  searchFor ="";
		if(session.getAttribute("searchFor")!=null)
		{
			   searchFor = (String)session.getAttribute("searchFor");
			 ////////System.out.println("searchFor...@@...."+searchFor);
		}
		
		String strComplaints = "";		
		/*
		 * brand = College Name
		 * categor = Course(Streem)
		 */
		String country="", state="", city = "", brand="", category="", firstName="";
		
		String countryName ="", stateName ="", cityName = "",brandName="", categoryName="";
		
		
		Vector userVec = (Vector)session.getAttribute("userVec");
		////////System.out.println("userVec>>>>>>>on search Result Action>>>>>>>>"+userVec);
		//Vector cscVec = (Vector)session.getAttribute("cscVec");
		Vector cscVec = userVec;
		////////System.out.println("cscVec>>>>>>>on search Result Action>>>>>>>>"+cscVec);
		
		
		
		Vector userTempVec = new Vector();
		if(session.getAttribute("userVec")!=null)
		{
				 userTempVec = (Vector)session.getAttribute("userVec");
				for(int i=0;i<userTempVec.size();i++)
				{
					if(userTempVec.elementAt(i).toString().equals("city ="))
					{
						city = userTempVec.elementAt(i+1).toString().trim();
						cityName = rootMaster.getPlaceName(dataSource, Integer.parseInt(city));
						break;
					}
				}
				
		}
		
		Vector searchVec = new Vector();
		
		/*
		 *  searchFor (0) Student.
		 */
		
		if(searchFor.equalsIgnoreCase("0")){
			if(session.getAttribute("searchVec")!=null)
			{
					 searchVec = (Vector)session.getAttribute("searchVec");
					for(int i=0; i<searchVec.size();i++)
					{
						paramVec.add(searchVec.elementAt(i).toString());
						if(searchVec.elementAt(i).toString().equals("course ="))
						{
							category = searchVec.elementAt(i+1).toString().trim();
							Vector categoryNameVec = indvMaster.getCourseName(dataSource, Integer.parseInt(category.trim()));
							categoryName = categoryNameVec.elementAt(0).toString();
						}
						if(searchVec.elementAt(i).toString().equals("first_name like"))
						{	
							firstName = searchVec.elementAt(i+1).toString().trim();
							
							String teamCat1[] = firstName.split("%");
							////////System.out.println("teamCat[]>>>>>>>searchVec>>>>>>>"+teamCat1[1]);
							firstName = teamCat1[1];
							
						}
						if(searchVec.elementAt(i).toString().equals("college_id ="))
						{
							brand = searchVec.elementAt(i+1).toString().trim();
							
							String StrArr[]= brand.split("~");
							int compnyid=Integer.parseInt(StrArr[0]);
							
							
							
							if(compnyid==0)
							{
								 compnyid=Integer.parseInt(userVec.elementAt(27).toString());
								 Vector name=indvMaster.getOtherCollege(dataSource, compnyid);
								 brandName=name.elementAt(0).toString().trim();
							}else if(StrArr[1].equalsIgnoreCase("1"))
							{
								brandName=rootMaster.getCompnyName(dataSource, compnyid);
							 }
							else
							{
								brandName = rootMaster.getCollegeName(dataSource, compnyid);
							}
							//brandName
							
						}
					}
			}
			if(session.getAttribute("userVec")!=null)
			{	
				for(int i=0; i<userVec.size();i++)
				{
					paramVec.add(userVec.elementAt(i).toString());
				}
				
			}
			
			////////System.out.println("searchVec>>>>>>>on search Result Action>>>>>>>>"+searchVec);
			
			////////System.out.println("paramVec>>>>>>>on search Result Action>>>>>>>>"+paramVec);
			
			int totalRow = rootMaster.getSearchResultStudentCount(dataSource, paramVec);
			String strPageHtml = getPages(minVal, maxVal, totalRow, numCount, searchFor);
			Vector complaintVec  = rootMaster.getSearchResultStudentList(dataSource, paramVec);
			int numcount = complaintVec.size();
			
			////////System.out.println("complaintVec>>>>>>>on search Result Action>>>>>>>>"+complaintVec);
			////////System.out.println("totalRow>>>>>>>on search Result Action>>>>>>>>"+totalRow);
			strComplaints = getStudentString(session, searchFor, dataSource,complaintVec,strPageHtml,numcount,minVal,maxVal,totalRow, city, brand,  categoryName, cityName, brandName, reminder, cscVec, firstName);
			
		}
		/*
		 * searchFor (2) Corporates.
		 */
		else if(searchFor.equalsIgnoreCase("2")){
			
		
			if(session.getAttribute("userVec")!=null)
			{
					 userTempVec = (Vector)session.getAttribute("userVec");
					for(int i=0;i<userTempVec.size();i++)
					{
						if(userTempVec.elementAt(i).toString().equals("company_city ="))
						{
							city = userTempVec.elementAt(i+1).toString().trim();
							cityName = rootMaster.getPlaceName(dataSource, Integer.parseInt(city));
							break;
						}
					}
					
			}
			
			if(session.getAttribute("searchVec")!=null)
			{
				searchVec = (Vector)session.getAttribute("searchVec");
			}
			// //////System.out.println("searchVec>>>>>>>searchVec>>>>>>>"+searchVec);
			 
			 for(int i=0; i<searchVec.size();i++)
				{
				 paramVec.add(searchVec.elementAt(i).toString());
				 
					if(searchVec.elementAt(i).toString().equals("company_name like"))
					{
						firstName = searchVec.elementAt(i+1).toString().trim();	
						
						String teamCat1[] = firstName.split("%");
						////////System.out.println("teamCat[]>>>>>>>searchVec>>>>>>>"+teamCat1[1]);
						firstName = teamCat1[1];
					}
					if(searchVec.elementAt(i).toString().equals("category like"))
					{	
						category = searchVec.elementAt(i+1).toString().trim();
						String teamCat[] = category.split(",");
						////////System.out.println("teamCat[]>>>>>>>searchVec>>>>>>>"+teamCat[1]);
						categoryName = rootMaster.getStrCategoryName(dataSource, Integer.parseInt(teamCat[1].trim()));
					}
				 
				}
			 if(session.getAttribute("userVec")!=null)
				{	
					for(int i=0; i<userVec.size();i++)
					{
						paramVec.add(userVec.elementAt(i).toString());
					}
					
				}
				
				////////System.out.println("searchVec>>>>>>>on search Result Action>>>>>>>>"+searchVec);
			// //////System.out.println("paramVec>>>>>>>Corporates>>>>>>>"+paramVec);
			 
			 	int totalRow = rootMaster.getSearchResultCompanyCount(dataSource, paramVec);
				String strPageHtml = getPages(minVal, maxVal, totalRow, numCount, searchFor);
				Vector complaintVec  = rootMaster.getSearchResultCompanyList(dataSource, paramVec);
				int numcount = complaintVec.size();
				strComplaints = getCompanyString( session,searchFor,dataSource,complaintVec,strPageHtml,numcount,minVal,maxVal,totalRow,cscVec, category,categoryName, cityName, firstName);
				
		}
		/*
		 * searchFor (3) College.
		 */
		else if(searchFor.equalsIgnoreCase("3")){
			
			if(session.getAttribute("userVec")!=null)
			{
					 userTempVec = (Vector)session.getAttribute("userVec");
					for(int i=0;i<userTempVec.size();i++)
					{
						if(userTempVec.elementAt(i).toString().equals("company_city ="))
						{
							city = userTempVec.elementAt(i+1).toString().trim();
							cityName = rootMaster.getPlaceName(dataSource, Integer.parseInt(city));
							break;
						}
					}
					
			}
			
			if(session.getAttribute("searchVec")!=null)
			{
				searchVec = (Vector)session.getAttribute("searchVec");
			}
			// //////System.out.println("searchVec>>>>>>>3>>>>>>>"+searchVec);
			 
			 for(int i=0; i<searchVec.size();i++)
				{
				 paramVec.add(searchVec.elementAt(i).toString());
				 
					if(searchVec.elementAt(i).toString().equals("company_name like"))
					{
						firstName = searchVec.elementAt(i+1).toString().trim();	
						
						String teamCat1[] = firstName.split("%");
						////////System.out.println("teamCat[]>>>>>>>searchVec>>>>>>>"+teamCat1[1]);
						firstName = teamCat1[1];
					}
					if(searchVec.elementAt(i).toString().equals("category like"))
					{	
						category = searchVec.elementAt(i+1).toString().trim();
						String teamCat[] = category.split(",");
						////////System.out.println("teamCat[]>>>>>>>searchVec>>>>>>>"+teamCat[1]);
						categoryName = rootMaster.getStrCollegeCategoryName(dataSource, Integer.parseInt(teamCat[1].trim()));
					}
				 
				}
			 if(session.getAttribute("userVec")!=null)
				{	
					for(int i=0; i<userVec.size();i++)
					{
						paramVec.add(userVec.elementAt(i).toString());
					}
					
				}
				
				////////System.out.println("searchVec>>>>>>>3>>>>>>>"+searchVec);
			 ////////System.out.println("paramVec>>>>>>>3>>>>>>>"+paramVec);
			 
			 	int totalRow = rootMaster.getSearchResultCollegeCount(dataSource, paramVec);
				String strPageHtml = getPages(minVal, maxVal, totalRow, numCount, searchFor);
				Vector complaintVec  = rootMaster.getSearchResultCollegeList(dataSource, paramVec);
				int numcount = complaintVec.size();
				strComplaints = getCollegeString(session, searchFor, dataSource,complaintVec,strPageHtml,numcount,minVal,maxVal,totalRow,cscVec, category,categoryName, cityName, firstName);
				
							
		}
		
		
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strComplaints);
	    out.flush();
		return null;
		
	}
	
	private String getCollegeString(HttpSession session, String searchFor, DataSource dataSource,Vector complaintVec , String strPageHtml,int numcount,int minVal,int maxVal,int totalRow, Vector cscVec,String category,String categoryName,String cityName, String firstName)
	{
		String strValue="";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=782 align=center border=0>";
		strValue +="<TBODY>";
		strValue +=" <TR>";
		strValue +=" <TD width=\"782\" bgColor=\"#cccccc\">";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=779 align=center bgColor=#ffffff border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +=" <TD width=\"779\">";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=779 align=center border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +=" <TD width=\"779\">";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=779 border=0>";
		strValue +=" <TBODY>";

		strValue +=" <TR>";
		strValue +=" <TD width=\"584\" height=\"28\" >&nbsp;&nbsp;&nbsp;&nbsp;</TD>";
		strValue +=" <TD width=\"195\" align=\"center\" ></TD></TR>";
		
		
		strValue +=" <TR>";
		strValue +=" <TD colSpan=2>";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"98%\" align=center border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +="    <TD><TABLE  cellSpacing=0 cellPadding=0 width=762 border=0>";
		strValue +="  <TBODY>";

		strValue +="  <TR>";
		strValue +="   <TD width=\"762\">";
		strValue +="   <TABLE cellSpacing=0 cellPadding=0 width=628 bgColor=#000000 border=0>";
		strValue +="    <TBODY>";
		strValue +="   <TR>";
		strValue +="   <TD>";
		strValue +="   <TABLE cellSpacing=0 cellPadding=0 width=626 align=center bgColor=#ffffff border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +="  <TD>";
		strValue +="  <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" align=center border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +="  <TD vAlign=top >";
		strValue +="  <TABLE width=773 border=0 cellPadding=0 cellSpacing=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +="  <TD vAlign=top align=left width=\"12\"></TD>";
		strValue +=" <TD vAlign=top width=\"757\" >";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" border=0>";
		strValue +=" <TBODY>";

		strValue +=" <TR>";
		strValue +="  <TD  vAlign=top>";
		strValue +="  <TABLE width=100% border=0 cellPadding=0 cellSpacing=0 >";
		strValue +=" <TBODY>";
		strValue +="   <TR>";
		strValue +="   <TD  width=628 valign=\"top\">";
		strValue +="   <TABLE width=\"98%\" height=\"636\"   border=0 cellPadding=0 cellSpacing=0  bgcolor=\"#FFFFFF\">";
		strValue +="   <TBODY>";
		strValue +="   <TR>";
		strValue +=" <TD align=\"center\" valign=\"top\"><TABLE width=\"100%\" border=0 align=\"center\" cellPadding=0 cellSpacing=0>";
		strValue +="  <TBODY>";
		
		strValue +=" <tr >";
		strValue +="   <TD width=\"40%\" bgcolor=\"#FFFFFF\" class=\"bold\"><a href=\"searchQueries.jsp?searchFor="+searchFor+"\" class=\"bold\">Click here to New search</a></TD>";

		strValue +=" <td align=\"left\" class=\"bold\">";
		if(numcount != 0)
		{
			strValue += " Total Matches Found: "+totalRow+"";
		}
		
		strValue +=" </td>";
		
		strValue +=" <td align=\"right\" class=\"bold\">";
		
		if(totalRow>maxVal){
		strValue +=" "+strPageHtml+" ";
		}
		strValue +=" </td></TR>";

		strValue +=" <tr >";
		strValue +=" <td colspan=\"3\"><hr color=\"#F0972F\"></td></tr>";
		strValue +=" <TR>";
		strValue +=" <TD height=\"30\" colspan=\"2\" align=\"left\" valign=\"bottom\" class=\"bold\">&nbsp;&nbsp;Search Criteria:&nbsp;&nbsp;";
		if(!category.equalsIgnoreCase(""))
		{
			strValue +=" <span class=\"bold\">Category:</span>&nbsp;<span class=\"bold\">"+categoryName+"</span>&nbsp;&nbsp;";
		}
		if(!cityName.equalsIgnoreCase(""))
		{
			strValue +=" <span class=\"bold\">City:</span>&nbsp;<span class=\"bold\">"+cityName+"</span>&nbsp;&nbsp;";
		}
		if(!firstName.equalsIgnoreCase(""))
		{
			strValue +=" <span class=\"bold\">College Name Like:</span>&nbsp;<span class=\"bold\">"+firstName+"</span>&nbsp;&nbsp;";
		}
		
		
		strValue +=" </TD>";
		strValue +=" </TR>";
		strValue +=" <tr >";
		strValue +=" <td colspan=\"3\"><hr color=\"#F0972F\"></td></tr>";
		strValue +="</TBODY></TABLE>";
		strValue +="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
		

		RootMaster rootMaster = new RootMaster();
		Vector allcid = new Vector();
				
		if(complaintVec.size()>0){
		
		for(int i=0; i<complaintVec.size(); i++){
			
		Vector tempVec = (Vector)complaintVec.elementAt(i);
		
				
		int compId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
		
		
		allcid.add(String.valueOf(compId));
		
		
		String comName =tempVec.elementAt(1).toString().trim();
		String comDisName =tempVec.elementAt(2).toString().trim();
		String comCity =tempVec.elementAt(3).toString().trim();
		String cityname = rootMaster.getPlaceName(dataSource, Integer.parseInt(comCity)) ;
		
		String comState =tempVec.elementAt(4).toString().trim();
		String statename = rootMaster.getStateName(dataSource, Integer.parseInt(comState)) ;
		
		String comCountry =tempVec.elementAt(5).toString().trim();
		String countryname = rootMaster.getCountryName(dataSource, Integer.parseInt(comCountry)) ;
		
		String logoname=rootMaster.getCompanyDetail(dataSource, compId).elementAt(26).toString();
		//String logopath =  "http://www.campusyogi.com:8080/campusyogi/logo/"+logoname;
		
		

strValue +=" <tr align=\"right\" bgcolor=\"#FFFAF6\">";
	strValue +="  <td colspan=\"5\" valign=\"middle\" bgcolor=\"#FFFFFF\">";
			strValue +="<table width=\"100%\">";
				strValue +="<tr>";
								
								/*
								strValue +="<td width=\"20%\">";
								strValue +="<table width=\"100%\"  align=\"center\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">";
								strValue +="<tr  align=\"left\" width=\"80\" height=\"90\" valign=\"bottom\" bgcolor=\"#FFFAF6\"><td align=\"center\" valign=\"middle\"  ><img src =\""+logopath+"\" width=\"80\"  height=\"90\"></img></td>";
								strValue +="</tr>";					
								strValue +="</table>";
								strValue +="</td>";
								*/
								
								strValue +="<td width=\"20%\"></td>";
								
							
							strValue +="<td width=\"80%\">";
							strValue +="<table width=\"100%\" >";
								strValue +=" <tr align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\">";
								strValue +="  <td colspan=\"5\" valign=\"top\"  bgcolor=\"#FFFFFF\" class=\"bold\">&nbsp;";
								strValue +=" <strong><font color=\"#0033FF\"><A href=\"compProfilePre.do?pageid=4&cuid="+compId+"&index="+i+"&detailFor=Advertiser\">"+comDisName+" </A></font></strong></td>";
								strValue +=" </tr>";
								strValue +=" <tr align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\">";
								strValue +="  <td colspan=\"5\" valign=\"top\"  bgcolor=\"#FFFFFF\" class=\"bold\">&nbsp;";
								strValue +=" "+cityname+",&nbsp;&nbsp;"+statename+",&nbsp;&nbsp;"+countryname+".</td>";
								strValue +=" </tr>";
							strValue +="</table>";
					strValue +="</td>";
				strValue +="</tr>";
			strValue +="</table>";
		strValue +=" </td>";
strValue +="  </tr>";

strValue +=" <tr align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\">";
strValue +="  <td colspan=\"5\" bgcolor=\"#FFFFFF\" valign=\"top\" class=\"bold\">&nbsp;";
strValue +=" <hr color=\"#F0972F\"></td>";
strValue +=" </tr>";
		
		}}else{
		strValue +=" <tr bgcolor=\"#FFFAF6\">";


		strValue +="  <td height=\"200\" colspan=\"5\" align=\"center\" valign=\"middle\" bgcolor=\"#FFFFFF\"><span class=\"bold\">No College(s) found. </span></td>";
		strValue +="  </tr>";
		strValue +=" <tr align=\"right\" bgcolor=\"#FFFAF6\">";


		strValue +="  <td colspan=\"5\" valign=\"middle\" bgcolor=\"#FFFFFF\"></td>";
		strValue +="  </tr>";
		}
		 session.setAttribute("allcid", allcid);
		
		strValue +=" <tr >";	
		
		if(totalRow>maxVal){
			
		
		if(minVal==0){
			
			strValue +=" <td colspan=\"3\" class=\"bold\" align=\"left\"><span style=\"cursor:hand\"  onclick=\"retrieveSComplistURL('searchQueriesResult.do?searchFor="+searchFor+"&numMin="+(minVal+maxVal)+"');\" > Next Page</span></td>";
			
		}else if((totalRow - minVal)<= maxVal){
			
			strValue +=" <td colspan=\"3\" class=\"bold\" align=\"left\"><span style=\"cursor:hand\"  onclick=\"retrieveSComplistURL('searchQueriesResult.do?searchFor="+searchFor+"&numMin="+(minVal-maxVal)+"');\" >Previous Page </span></td>";
			
		}
		else{
		
		strValue +=" <td colspan=\"3\" class=\"bold\" align=\"left\"><span style=\"cursor:hand\"  onclick=\"retrieveSComplistURL('searchQueriesResult.do?searchFor="+searchFor+"&numMin="+(minVal-maxVal)+"');\" >Previous Page </span> <span style=\"cursor:hand\"  onclick=\"retrieveSComplistURL('searchQueriesResult.do?searchFor="+searchFor+"&numMin="+(minVal+maxVal)+"');\" >&nbsp;&nbsp;|&nbsp;&nbsp; Next Page </span></td>";
		}	
	}
		strValue +=" </tr>";

		strValue +=" </table></TD>";
		strValue +="    </TR>";

		strValue +="  </TBODY></TABLE></TD>";
		strValue +=" <TD vAlign=top width=123>";
		strValue +="  <TABLE cellSpacing=0 cellPadding=0 width=114 align=right bgColor=#ffffff border=1 >";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +=" <TD width=\"110\">";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=104 align=center border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +=" <TD width=\"104\"><TABLE cellSpacing=0 cellPadding=0 width=\"100%\" border=0>";
		strValue +=" <TBODY>";
		strValue +="  <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif></TD></TR>";
		strValue +="  <TR>";
		strValue +=" <TD><table width=\"98%\" border=\"0\" align=\"center\" cellpadding=\"2\" cellspacing=\"2\" bgcolor=\"#CACACA\">";
		strValue +="  <tr>";
		strValue +="   <td class=\"bold\"><div align=\"center\">Cities</div></td>";
		strValue +="  </tr>";
		strValue +="  </table></TD>";                                                            
		strValue +="  </TR>";
		strValue +="  <TR>";
		strValue +="  <TD background=blogs_files/1x1white.gif>";
		strValue +=" </TD>";
		strValue +=" </TR>";
		strValue +=" <TR>";
		strValue +="  <TD>";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"101%\"    align=center border=0>";
		strValue +=" <TBODY>";
		Vector CityVec = rootMaster.getAllPlace(94, dataSource);
		Vector tempCityVec = new Vector();
		for(int i=1 ; i<CityVec.size()-1;i++)
		{
			tempCityVec.add(CityVec.elementAt(i));
		}
		int tempInt = tempCityVec.size()/10;
		int tempRest = tempCityVec.size()%10;
//		////////System.out.println("tempInt>>>>>>>>>"+tempInt);
//		////////System.out.println("tempRest>>>>>>>>>"+tempRest);
		int y3 = 10;
		if(tempRest!=0)
		{
		tempInt += 1;
		}

		for(int k=0;k<tempInt;k++,y3+=10){
		if(y3>tempCityVec.size())
		{
		if(tempRest!=0)
		{
		y3=y3+tempRest-10;
		}
		else
		{
		y3=tempCityVec.size();
		}
		}
		if(k==0){


		strValue +=" <TR id=\"city"+k+"\" style=\"display:inline\">";
		}else{
		strValue +=" <TR id=\"city"+k+"\" style=\"display:none\">";
		}
		strValue +=" <TD colspan=\"2\"  align=\"left\" valign=\"top\">";
		if(k==0){
		strValue +=" <A href=\"searchQueriesResult.jsp?collCity=&collCategory="+category+"\"><SPAN class=\"bold\">ALL</SPAN></A><BR>";
		}
		for(int j=k*10;j<y3;j++){

		strValue +=" <A href=\"searchQueriesResult.jsp?collCity="+((Vector)tempCityVec.elementAt(j)).elementAt(0).toString().trim()+"&&collCategory="+category+"\"><SPAN class=\"bold\">"+((Vector)tempCityVec.elementAt(j)).elementAt(1).toString().trim()+"</SPAN></A><BR>";

		}
		strValue +=" </TD>";
		strValue +=" </TR>";

		}

		strValue +=" <tr id=\"citynext\" style=\"display:inline\"><td width=\"60\"></td><td  align=\"right\" class=\"bold\" id=\"next\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"city\" style=\"display:none\"><td width=\"60\" align=\"center\" class=\"bold\" id=\"Previous\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Previous&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td class=\"bold\" id=\"next\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"citypre\" style=\"display:none\"><td width=\"60\" colspan=\"2\" align=\"left\" class=\"bold\" id=\"Previous\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Previous</a></td><td></td></tr>";

		strValue +=" </TBODY>";
		strValue +=" </TABLE>";
		strValue +=" </TD>";
		strValue +=" </TR>";
		strValue +="  <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif></TD></TR></TBODY></TABLE>  </TD></TR>";
		strValue +=" <TR><TD>";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" border=0>";
		strValue +=" <TBODY>";
		strValue +="  <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif>";
		strValue +=" </TD></TR>";
		strValue +=" <TR>";
		strValue +=" <TD><table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"2\" cellspacing=\"2\" bgcolor=\"#CACACA\">";
		strValue +="  <tr>";
		strValue +="  <td class=\"bold\"><div align=\"center\">Category</div></td>";
		strValue +="  </tr>";
		strValue +="  </table> </TD>";
		strValue +=" </TR>";
		strValue +="  <TR>";
		strValue +="  <TD background=blogs_files/1x1white.gif></TD></TR>";
		strValue +=" <TR>";
		strValue +=" <TD>";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" align=center border=0>";
		strValue +=" <TBODY>";
	
		
		Vector categoryVec = rootMaster.getCollegeCategories(dataSource);
		Vector tempCategoryVec = new Vector();
		if(categoryVec.size()>0){
			for(int i=1 ; i<categoryVec.size()-1;i++)
			{
				tempCategoryVec.add(categoryVec.elementAt(i));
			}
		int tempInt2 = tempCategoryVec.size()/10;
		int tempRest2 = tempCategoryVec.size()%10;
//		////////System.out.println("tempBrandVec.size()>>>>>>>>>"+tempBrandVec.size());
//		////////System.out.println("tempInt1>>>>>>>>>"+tempInt1);
//		////////System.out.println("tempRest1>>>>>>>>>"+tempRest1);
		int y2 = 10;
		if(tempRest2!=0)
		{
		tempInt2 += 1;
		}

		for(int k2=0;k2<tempInt2;k2++,y2+=10){
		if(y2>tempCategoryVec.size())
		{
		if(tempRest2!=0)
		{
		y2=y2+tempRest2-10;
		}
		else
		{
		y2=tempCategoryVec.size();
		}
		}

		if(k2==0){


		strValue +=" <TR id=\"category"+k2+"\" style=\"display:inline\">";
		}else{
		strValue +=" <TR id=\"category"+k2+"\" style=\"display:none\">";
		}
		strValue +=" <TD colspan=\"2\" align=\"left\" valign=\"top\">";
		if(k2==0){
		strValue +="  <A href=\"searchQueriesResult.jsp?collCity=&collCategory=\"><SPAN class=\"bold\">ALL</SPAN></A><BR>";
		}
		for(int j2=k2*10;j2<y2;j2++){
//		////////System.out.println(k+">>>>>>>>>>>>>>>>>>"+tempCityVec.elementAt(j).toString());




		Vector tempCat = (Vector)tempCategoryVec.elementAt(j2);

		strValue +=" <A href=\"searchQueriesResult.jsp?collCity=&collCategory="+tempCat.elementAt(0).toString()+"\"><SPAN class=\"bold\">"+tempCat.elementAt(1).toString()+"</SPAN></A><BR>";


		}
		strValue +=" </TD>";
		strValue +=" </TR>";



		}
		if(tempCategoryVec.size()>10)
		{


		strValue +=" <tr id=\"categorynext\" style=\"display:inline\"><td width=\"60\"></td><td  align=\"right\" class=\"bold\" id=\"next\" onclick=\"showCategory(this.id,"+(tempInt2-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"category\" style=\"display:none\"><td width=\"60\" align=\"center\" class=\"bold\" id=\"Previous\" onclick=\"showCategory(this.id,"+(tempInt2-1)+")\" style=\"cursor:hand\">Previous</td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td class=\"bold\" id=\"next\" onclick=\"showCategory(this.id,"+(tempInt2-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"categorypre\" style=\"display:none\"><td width=\"60\" align=\"left\" class=\"bold\" id=\"Previous\" onclick=\"showCategory(this.id,"+(tempInt2-1)+")\" style=\"cursor:hand\">Previous</td><td></td></tr>";


		}}else{
		strValue +=" <tr><td >No Category Availlable</A><BR></TD>";
		strValue +=" </TR>";
		}
		strValue +=" </TBODY>";
		strValue +=" </TABLE>";
		strValue +=" </TD>";
		strValue +=" </TR>";
		strValue +=" <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif></TD>";
		strValue +=" </TR>";
		strValue +=" </TBODY>";
		strValue +=" </TABLE>";
		strValue +=" </TD>";
		strValue +=" </TR>";

		strValue +=" <TR>";
		strValue +=" <TD><TABLE cellSpacing=0 cellPadding=0 width=\"100%\" border=0>";
		strValue +=" <TBODY>";
		strValue +="  <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif></TD></TR>";
		strValue +=" <TR>";
		strValue +=" <TD><table width=\"98%\" border=\"0\" align=\"center\" cellpadding=\"2\" cellspacing=\"2\" bgcolor=\"#CACACA\">";
		
		strValue +=" </table></TD>";
		strValue +=" </TR>";
		strValue +=" <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif></TD></TR>";
	
		strValue +=" <TR>";
		strValue +="  <TD height=\"2\" background=blogs_files/1x1white.gif></TD>";
		strValue +="  </TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>";
		strValue +=" <TD width=6></TD>";
		strValue +="  </TR>";
		
		strValue +="  <TR>";
		strValue +=" <TD height=\"2\" colSpan=4></TD>";
		strValue +=" </TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>";
		strValue +=" <TD width=4></TD>";
		strValue +=" </TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>";
		strValue +=" </TBODY></TABLE>                              </TD></TR></TBODY></TABLE></TD></TR>";
		strValue +=" </TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>";
		return strValue;
	}
	
	private String getCompanyString(HttpSession session,String searchFor,DataSource dataSource,Vector complaintVec , String strPageHtml,int numcount,int minVal,int maxVal,int totalRow, Vector cscVec,String category,String categoryName,String cityName, String firstName)
	{
		String strValue="";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=782 align=center border=0>";
		strValue +="<TBODY>";
		strValue +=" <TR>";
		strValue +=" <TD width=\"782\" bgColor=\"#cccccc\">";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=779 align=center bgColor=#ffffff border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +=" <TD width=\"779\">";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=779 align=center border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +=" <TD width=\"779\">";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=779 border=0>";
		strValue +=" <TBODY>";

		strValue +=" <TR>";
		strValue +=" <TD width=\"584\" height=\"28\" >&nbsp;&nbsp;&nbsp;&nbsp;</TD>";
		strValue +=" <TD width=\"195\" align=\"center\" ></TD></TR>";
		
		
		
		strValue +=" <TR>";
		strValue +=" <TD colSpan=2>";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"98%\" align=center border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +="    <TD><TABLE  cellSpacing=0 cellPadding=0 width=762 border=0>";
		strValue +="  <TBODY>";

		strValue +="  <TR>";
		strValue +="   <TD width=\"762\">";
		strValue +="   <TABLE cellSpacing=0 cellPadding=0 width=628 bgColor=#000000 border=0>";
		strValue +="    <TBODY>";
		strValue +="   <TR>";
		strValue +="   <TD>";
		strValue +="   <TABLE cellSpacing=0 cellPadding=0 width=626 align=center bgColor=#ffffff border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +="  <TD>";
		strValue +="  <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" align=center border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +="  <TD vAlign=top >";
		strValue +="  <TABLE width=773 border=0 cellPadding=0 cellSpacing=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +="  <TD vAlign=top align=left width=\"12\"></TD>";
		strValue +=" <TD vAlign=top width=\"757\" >";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" border=0>";
		strValue +=" <TBODY>";

		strValue +=" <TR>";
		strValue +="  <TD  vAlign=top>";
		strValue +="  <TABLE width=100% border=0 cellPadding=0 cellSpacing=0 >";
		strValue +=" <TBODY>";
		strValue +="   <TR>";
		strValue +="   <TD  width=628 valign=\"top\">";
		strValue +="   <TABLE width=\"98%\" height=\"636\"   border=0 cellPadding=0 cellSpacing=0  bgcolor=\"#FFFFFF\">";
		strValue +="   <TBODY>";
		strValue +="   <TR>";
		strValue +=" <TD align=\"center\" valign=\"top\"><TABLE width=\"100%\" border=0 align=\"center\" cellPadding=0 cellSpacing=0>";
		strValue +="  <TBODY>";
		strValue +=" <tr >";
		strValue +="   <TD width=\"40%\" bgcolor=\"#FFFFFF\" class=\"bold\"><a href=\"searchQueries.jsp?searchFor="+searchFor+"\" class=\"bold\">Click here to New search</a></TD>";

		strValue +=" <td align=\"left\" class=\"bold\">";
		if(numcount != 0)
		{
			strValue += " Total Matches Found: "+totalRow+"";
		}
		
		strValue +=" </td>";
		
		strValue +=" <td align=\"right\" class=\"bold\">";
		
		if(totalRow>maxVal){
		strValue +=" "+strPageHtml+" ";
		}
		strValue +=" </td></TR>";

		strValue +=" <tr >";
		strValue +=" <td colspan=\"3\"><hr color=\"#F0972F\"></td></tr>";
		strValue +=" <TR>";
		strValue +=" <TD height=\"30\" colspan=\"2\" align=\"left\" valign=\"bottom\" class=\"bold\">&nbsp;&nbsp;Search Criteria:&nbsp;&nbsp;";
		if(!category.equalsIgnoreCase(""))
		{
			strValue +=" <span class=\"bold\">Category:</span>&nbsp;<span class=\"bold\">"+categoryName+"</span>&nbsp;&nbsp;";
		}
		if(!cityName.equalsIgnoreCase(""))
		{
			strValue +=" <span class=\"bold\">City:</span>&nbsp;<span class=\"bold\">"+cityName+"</span>&nbsp;&nbsp;";
		}
		if(!firstName.equalsIgnoreCase(""))
		{
			strValue +=" <span class=\"bold\">Compamy Name Like:</span>&nbsp;<span class=\"bold\">"+firstName+"</span>&nbsp;&nbsp;";
		}
		
		
		strValue +=" </TD>";
		strValue +=" </TR>";
		strValue +=" <tr >";
		strValue +=" <td colspan=\"3\"><hr color=\"#F0972F\"></td></tr>";
		strValue +="</TBODY></TABLE>";
		strValue +="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
		

		RootMaster rootMaster = new RootMaster();
		Vector allcid = new Vector();
		
		if(complaintVec.size()>0){
		
		for(int i=0; i<complaintVec.size(); i++){
			
		Vector tempVec = (Vector)complaintVec.elementAt(i);
		
		int compId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
		allcid.add(String.valueOf(compId));
		
		String comName =tempVec.elementAt(1).toString().trim();
		String comDisName =tempVec.elementAt(2).toString().trim();
		
		String comCity =tempVec.elementAt(3).toString().trim();
		String cityname = rootMaster.getPlaceName(dataSource, Integer.parseInt(comCity)) ;
		
		String comState =tempVec.elementAt(4).toString().trim();
		String statename = rootMaster.getStateName(dataSource, Integer.parseInt(comState)) ;
		
		String comCountry =tempVec.elementAt(5).toString().trim();
		String countryname = rootMaster.getCountryName(dataSource, Integer.parseInt(comCountry)) ;
		
		String logoname=rootMaster.getCompanyDetail(dataSource, compId).elementAt(26).toString();
		//String logopath =  "http://www.campusyogi.com:8080/campusyogi/logo/"+logoname;
		
		

strValue +=" <tr align=\"right\" bgcolor=\"#FFFAF6\">";
	strValue +="  <td colspan=\"5\" valign=\"middle\" bgcolor=\"#FFFFFF\">";
			strValue +="<table width=\"100%\">";
				strValue +="<tr>";
				
							/*strValue +="<td width=\"20%\">";
							strValue +="<table width=\"100%\"  align=\"center\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">";
							strValue +="<tr  align=\"left\" width=\"80\" height=\"90\" valign=\"bottom\" bgcolor=\"#FFFAF6\"><td align=\"center\" valign=\"middle\"  ><img src =\""+logopath+"\" width=\"80\"  height=\"90\"></img></td>";
							strValue +="</tr>";					
							strValue +="</table>";
							strValue +="</td>";
							*/
							
							strValue +="<td width=\"20%\"></td>";
							
							strValue +="<td width=\"80%\">";
							strValue +="<table width=\"100%\" >";
								strValue +=" <tr align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\">";
								strValue +="  <td colspan=\"5\" valign=\"top\"  bgcolor=\"#FFFFFF\" class=\"bold\">&nbsp;";
								strValue +=" <strong><font color=\"#0033FF\"><A href=\"compProfilePre.do?pageid=3&cuid="+compId+"&index="+i+"&detailFor=Corporates\">"+comDisName+" </A></font></strong></td>";
								strValue +=" </tr>";
								strValue +=" <tr align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\">";
								strValue +="  <td colspan=\"5\" valign=\"top\"  bgcolor=\"#FFFFFF\" class=\"bold\">&nbsp;";
								strValue +=" "+cityname+",&nbsp;&nbsp;"+statename+",&nbsp;&nbsp;"+countryname+".</td>";
								strValue +=" </tr>";
							strValue +="</table>";
					strValue +="</td>";
				strValue +="</tr>";
			strValue +="</table>";
		strValue +=" </td>";
strValue +="  </tr>";

strValue +=" <tr align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\">";
strValue +="  <td colspan=\"5\" bgcolor=\"#FFFFFF\" valign=\"top\" class=\"bold\">&nbsp;";
strValue +=" <hr color=\"#F0972F\"></td>";
strValue +=" </tr>";
		
		}}else{
		strValue +=" <tr bgcolor=\"#FFFAF6\">";


		strValue +="  <td height=\"200\" colspan=\"5\" align=\"center\" valign=\"middle\" bgcolor=\"#FFFFFF\"><span class=\"bold\">No Company(s) found. </span></td>";
		strValue +="  </tr>";
		strValue +=" <tr align=\"right\" bgcolor=\"#FFFAF6\">";


		strValue +="  <td colspan=\"5\" valign=\"middle\" bgcolor=\"#FFFFFF\"></td>";
		strValue +="  </tr>";
		}
		
		session.setAttribute("allcid", allcid);
		
		strValue +=" <tr >";	
		
		if(totalRow>maxVal){
		
		if(minVal==0){
			
			strValue +=" <td colspan=\"3\" class=\"bold\" align=\"left\"><span style=\"cursor:hand\"  onclick=\"retrieveSComplistURL('searchQueriesResult.do?searchFor="+searchFor+"&numMin="+(minVal+maxVal)+"');\" > Next Page</span></td>";
			
		}else if((totalRow - minVal)<= maxVal){
			
			strValue +=" <td colspan=\"3\" class=\"bold\" align=\"left\"><span style=\"cursor:hand\"  onclick=\"retrieveSComplistURL('searchQueriesResult.do?searchFor="+searchFor+"&numMin="+(minVal-maxVal)+"');\" >Previous Page </span></td>";
			
		}
		else{
		
		strValue +=" <td colspan=\"3\" class=\"bold\" align=\"left\"><span style=\"cursor:hand\"  onclick=\"retrieveSComplistURL('searchQueriesResult.do?searchFor="+searchFor+"&numMin="+(minVal-maxVal)+"');\" >Previous Page </span> <span style=\"cursor:hand\"  onclick=\"retrieveSComplistURL('searchQueriesResult.do?searchFor="+searchFor+"&numMin="+(minVal+maxVal)+"');\" >&nbsp;&nbsp;|&nbsp;&nbsp; Next Page </span></td>";
		}
		}
		strValue +=" </tr>";

		strValue +=" </table></TD>";
		strValue +="    </TR>";

		strValue +="  </TBODY></TABLE></TD>";
		strValue +=" <TD vAlign=top width=123>";
		strValue +="  <TABLE cellSpacing=0 cellPadding=0 width=114 align=right bgColor=#ffffff border=1 >";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +=" <TD width=\"110\">";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=104 align=center border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +=" <TD width=\"104\"><TABLE cellSpacing=0 cellPadding=0 width=\"100%\" border=0>";
		strValue +=" <TBODY>";
		strValue +="  <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif></TD></TR>";
		strValue +="  <TR>";
		strValue +=" <TD><table width=\"98%\" border=\"0\" align=\"center\" cellpadding=\"2\" cellspacing=\"2\" bgcolor=\"#CACACA\">";
		strValue +="  <tr>";
		strValue +="   <td class=\"bold\"><div align=\"center\">Cities</div></td>";
		strValue +="  </tr>";
		strValue +="  </table></TD>";                                                            
		strValue +="  </TR>";
		strValue +="  <TR>";
		strValue +="  <TD background=blogs_files/1x1white.gif>";
		strValue +=" </TD>";
		strValue +=" </TR>";
		strValue +=" <TR>";
		strValue +="  <TD>";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"101%\"    align=center border=0>";
		strValue +=" <TBODY>";
		Vector CityVec = rootMaster.getAllPlace(94, dataSource);
		Vector tempCityVec = new Vector();
		for(int i=1 ; i<CityVec.size()-1;i++)
		{
			tempCityVec.add(CityVec.elementAt(i));
		}
		int tempInt = tempCityVec.size()/10;
		int tempRest = tempCityVec.size()%10;
//		////////System.out.println("tempInt>>>>>>>>>"+tempInt);
//		////////System.out.println("tempRest>>>>>>>>>"+tempRest);
		int y3 = 10;
		if(tempRest!=0)
		{
		tempInt += 1;
		}

		for(int k=0;k<tempInt;k++,y3+=10){
		if(y3>tempCityVec.size())
		{
		if(tempRest!=0)
		{
		y3=y3+tempRest-10;
		}
		else
		{
		y3=tempCityVec.size();
		}
		}
		if(k==0){


		strValue +=" <TR id=\"city"+k+"\" style=\"display:inline\">";
		}else{
		strValue +=" <TR id=\"city"+k+"\" style=\"display:none\">";
		}
		strValue +=" <TD colspan=\"2\"  align=\"left\" valign=\"top\">";
		if(k==0){
		strValue +=" <A href=\"searchQueriesResult.jsp?comCity=&comCategory="+category+"\"><SPAN class=\"bold\">ALL</SPAN></A><BR>";
		}
		for(int j=k*10;j<y3;j++){

		strValue +=" <A href=\"searchQueriesResult.jsp?comCity="+((Vector)tempCityVec.elementAt(j)).elementAt(0).toString().trim()+"&&comCategory="+category+"\"><SPAN class=\"bold\">"+((Vector)tempCityVec.elementAt(j)).elementAt(1).toString().trim()+"</SPAN></A><BR>";

		}
		strValue +=" </TD>";
		strValue +=" </TR>";

		}

		strValue +=" <tr id=\"citynext\" style=\"display:inline\"><td width=\"60\"></td><td  align=\"right\" class=\"bold\" id=\"next\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"city\" style=\"display:none\"><td width=\"60\" align=\"center\" class=\"bold\" id=\"Previous\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Previous&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td class=\"bold\" id=\"next\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"citypre\" style=\"display:none\"><td width=\"60\" colspan=\"2\" align=\"left\" class=\"bold\" id=\"Previous\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Previous</a></td><td></td></tr>";

		strValue +=" </TBODY>";
		strValue +=" </TABLE>";
		strValue +=" </TD>";
		strValue +=" </TR>";
		strValue +="  <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif></TD></TR></TBODY></TABLE>  </TD></TR>";
		strValue +=" <TR><TD>";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" border=0>";
		strValue +=" <TBODY>";
		strValue +="  <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif>";
		strValue +=" </TD></TR>";
		strValue +=" <TR>";
		strValue +=" <TD><table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"2\" cellspacing=\"2\" bgcolor=\"#CACACA\">";
		strValue +="  <tr>";
		strValue +="  <td class=\"bold\"><div align=\"center\">Category</div></td>";
		strValue +="  </tr>";
		strValue +="  </table> </TD>";
		strValue +=" </TR>";
		strValue +="  <TR>";
		strValue +="  <TD background=blogs_files/1x1white.gif></TD></TR>";
		strValue +=" <TR>";
		strValue +=" <TD>";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" align=center border=0>";
		strValue +=" <TBODY>";
	
		
		Vector categoryVec = rootMaster.getCategories(dataSource);
		Vector tempCategoryVec = new Vector();
		if(categoryVec.size()>0){
			for(int i=1 ; i<categoryVec.size()-1;i++)
			{
				tempCategoryVec.add(categoryVec.elementAt(i));
			}
		int tempInt2 = tempCategoryVec.size()/10;
		int tempRest2 = tempCategoryVec.size()%10;
//		////////System.out.println("tempBrandVec.size()>>>>>>>>>"+tempBrandVec.size());
//		////////System.out.println("tempInt1>>>>>>>>>"+tempInt1);
//		////////System.out.println("tempRest1>>>>>>>>>"+tempRest1);
		int y2 = 10;
		if(tempRest2!=0)
		{
		tempInt2 += 1;
		}

		for(int k2=0;k2<tempInt2;k2++,y2+=10){
		if(y2>tempCategoryVec.size())
		{
		if(tempRest2!=0)
		{
		y2=y2+tempRest2-10;
		}
		else
		{
		y2=tempCategoryVec.size();
		}
		}

		if(k2==0){


		strValue +=" <TR id=\"category"+k2+"\" style=\"display:inline\">";
		}else{
		strValue +=" <TR id=\"category"+k2+"\" style=\"display:none\">";
		}
		strValue +=" <TD colspan=\"2\" align=\"left\" valign=\"top\">";
		if(k2==0){
		strValue +="  <A href=\"searchQueriesResult.jsp?comCity=&comCategory=\"><SPAN class=\"bold\">ALL</SPAN></A><BR>";
		}
		for(int j2=k2*10;j2<y2;j2++){
//		////////System.out.println(k+">>>>>>>>>>>>>>>>>>"+tempCityVec.elementAt(j).toString());




		Vector tempCat = (Vector)tempCategoryVec.elementAt(j2);

		strValue +=" <A href=\"searchQueriesResult.jsp?comCity=&comCategory="+tempCat.elementAt(0).toString()+"\"><SPAN class=\"bold\">"+tempCat.elementAt(1).toString()+"</SPAN></A><BR>";


		}
		strValue +=" </TD>";
		strValue +=" </TR>";



		}
		if(tempCategoryVec.size()>10)
		{


		strValue +=" <tr id=\"categorynext\" style=\"display:inline\"><td width=\"60\"></td><td  align=\"right\" class=\"bold\" id=\"next\" onclick=\"showCategory(this.id,"+(tempInt2-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"category\" style=\"display:none\"><td width=\"60\" align=\"center\" class=\"bold\" id=\"Previous\" onclick=\"showCategory(this.id,"+(tempInt2-1)+")\" style=\"cursor:hand\">Previous</td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td class=\"bold\" id=\"next\" onclick=\"showCategory(this.id,"+(tempInt2-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"categorypre\" style=\"display:none\"><td width=\"60\" align=\"left\" class=\"bold\" id=\"Previous\" onclick=\"showCategory(this.id,"+(tempInt2-1)+")\" style=\"cursor:hand\">Previous</td><td></td></tr>";


		}}else{
		strValue +=" <tr><td >No Category Availlable</A><BR></TD>";
		strValue +=" </TR>";
		}
		strValue +=" </TBODY>";
		strValue +=" </TABLE>";
		strValue +=" </TD>";
		strValue +=" </TR>";
		strValue +=" <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif></TD>";
		strValue +=" </TR>";
		strValue +=" </TBODY>";
		strValue +=" </TABLE>";
		strValue +=" </TD>";
		strValue +=" </TR>";

		strValue +=" <TR>";
		strValue +=" <TD><TABLE cellSpacing=0 cellPadding=0 width=\"100%\" border=0>";
		strValue +=" <TBODY>";
		strValue +="  <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif></TD></TR>";
		strValue +=" <TR>";
		strValue +=" <TD><table width=\"98%\" border=\"0\" align=\"center\" cellpadding=\"2\" cellspacing=\"2\" bgcolor=\"#CACACA\">";
		
		strValue +=" </table></TD>";
		strValue +=" </TR>";
		strValue +=" <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif></TD></TR>";
	
		strValue +=" <TR>";
		strValue +="  <TD height=\"2\" background=blogs_files/1x1white.gif></TD>";
		strValue +="  </TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>";
		strValue +=" <TD width=6></TD>";
		strValue +="  </TR>";
		
		strValue +="  <TR>";
		strValue +=" <TD height=\"2\" colSpan=4></TD>";
		strValue +=" </TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>";
		strValue +=" <TD width=4></TD>";
		strValue +=" </TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>";
		strValue +=" </TBODY></TABLE>                              </TD></TR></TBODY></TABLE></TD></TR>";
		strValue +=" </TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>";
		return strValue;
	}
	private String getStudentString(HttpSession session,String searchFor,DataSource dataSource,Vector complaintVec , String strPageHtml,int numcount,int minVal,int maxVal,int totalRow,String city,String brand, String category,String cityName,String  brandName,int reminder, Vector cscVec, String firstName)
	{
		
		String strValue="";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=782 align=center border=0>";
		strValue +="<TBODY>";
		strValue +=" <TR>";
		strValue +=" <TD width=\"782\" bgColor=\"#cccccc\">";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=779 align=center bgColor=#ffffff border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +=" <TD width=\"779\">";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=779 align=center border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +=" <TD width=\"779\">";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=779 border=0>";
		strValue +=" <TBODY>";

		strValue +=" <TR>";
		strValue +=" <TD width=\"584\" height=\"28\" >&nbsp;&nbsp;&nbsp;&nbsp;</TD>";
		strValue +=" <TD width=\"195\" align=\"center\" ></TD></TR>";
		
		
		
		strValue +=" <TR>";
		strValue +=" <TD colSpan=2>";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"98%\" align=center border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +="    <TD><TABLE  cellSpacing=0 cellPadding=0 width=762 border=0>";
		strValue +="  <TBODY>";

		strValue +="  <TR>";
		strValue +="   <TD width=\"762\">";
		strValue +="   <TABLE cellSpacing=0 cellPadding=0 width=628 bgColor=#000000 border=0>";
		strValue +="    <TBODY>";
		strValue +="   <TR>";
		strValue +="   <TD>";
		strValue +="   <TABLE cellSpacing=0 cellPadding=0 width=626 align=center bgColor=#ffffff border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +="  <TD>";
		strValue +="  <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" align=center border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +="  <TD vAlign=top >";
		strValue +="  <TABLE width=773 border=0 cellPadding=0 cellSpacing=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +="  <TD vAlign=top align=left width=\"12\"></TD>";
		strValue +=" <TD vAlign=top width=\"757\" >";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" border=0>";
		strValue +=" <TBODY>";

		strValue +=" <TR>";
		strValue +="  <TD  vAlign=top>";
		strValue +="  <TABLE width=100% border=0 cellPadding=0 cellSpacing=0 >";
		strValue +=" <TBODY>";
		strValue +="   <TR>";
		strValue +="   <TD  width=628 valign=\"top\">";
		strValue +="   <TABLE width=\"98%\" height=\"636\"   border=0 cellPadding=0 cellSpacing=0  bgcolor=\"#FFFFFF\">";
		strValue +="   <TBODY>";
		strValue +="   <TR>";
		strValue +=" <TD align=\"center\" valign=\"top\"><TABLE width=\"100%\" border=0 align=\"center\" cellPadding=0 cellSpacing=0>";
		strValue +="  <TBODY>";
		
		strValue +=" <tr >";
		strValue +="   <TD width=\"40%\" bgcolor=\"#FFFFFF\" class=\"bold\"><a href=\"searchQueries.jsp?searchFor="+searchFor+"\" class=\"bold\">Click here to New search</a></TD>";

		strValue +=" <td align=\"left\" class=\"bold\">";
		if(numcount != 0)
		{
			strValue += " Total Matches Found: "+totalRow+"";
		}
		
		strValue +=" </td>";
		
		strValue +=" <td align=\"right\" class=\"bold\">";
		
		if(totalRow>maxVal){
		strValue +=" "+strPageHtml+" ";
		}
		strValue +=" </td></TR>";
		
		strValue +=" <tr >";
		strValue +=" <td colspan=\"3\"><hr color=\"#F0972F\"></td></tr>";
		strValue +=" <TR>";
		strValue +=" <TD height=\"30\" colspan=\"2\" align=\"left\" valign=\"bottom\" class=\"bold\">&nbsp;&nbsp;Search Criteria:&nbsp;&nbsp;";
		if(!category.equalsIgnoreCase(""))
		{
			strValue +=" <span class=\"bold\">Course:</span>&nbsp;<span class=\"bold\">"+category+"</span>&nbsp;&nbsp;";
		}
		if(!cityName.equalsIgnoreCase(""))
		{
			strValue +=" <span class=\"bold\">city:</span>&nbsp;<span class=\"bold\">"+cityName+"</span>&nbsp;&nbsp;";
		}
		if(!firstName.equalsIgnoreCase(""))
		{
			strValue +=" <span class=\"bold\">First Name:</span>&nbsp;<span class=\"bold\">"+firstName+"</span>&nbsp;&nbsp;";
		}
		if(!brandName.equalsIgnoreCase(""))
		{
			strValue +=" <span class=\"bold\">College name:</span>&nbsp;<span class=\"bold\">"+brandName+"</span>&nbsp;&nbsp;";
		}
		
		strValue +=" </TD>";
		strValue +=" </TR>";
		strValue +=" <tr >";
		strValue +=" <td colspan=\"3\"></td></tr>";
		strValue +="</TBODY></TABLE>";
		
		strValue +="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";	

		RootMaster rootMaster = new RootMaster();
		Vector allcid = new Vector();
		
		
		if(complaintVec.size()>0){
		
		for(int i=0; i<complaintVec.size(); i++){
			
		Vector tempVec = (Vector)complaintVec.elementAt(i);
		
		int loginId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
		
		allcid.add(String.valueOf(loginId));
		
		String firstSName =tempVec.elementAt(1).toString().trim();
		String lastName =tempVec.elementAt(2).toString().trim();
		String CId =tempVec.elementAt(5).toString().trim();
		int otherCollId =Integer.parseInt(tempVec.elementAt(6).toString().trim());
		//String photopath =  "http://www.campusyogi.com:8080/campusyogi/photo/"+tempVec.elementAt(7).toString().trim();
		
		
		String StrArr[]= CId.split("~");
		int numCId=Integer.parseInt(StrArr[0]);
		
		
		String cName = "Not Mention.";
		String collCityName = "Not Mention.";
		
		if(StrArr.length==2){
			
			if(StrArr[1].equalsIgnoreCase("1"))
			{
				 cName = rootMaster.getCompnyName(dataSource, numCId);
				 collCityName = rootMaster.getCompnyCityName(dataSource, numCId);
			}
			else{
				
				 cName = rootMaster.getCollegeName(dataSource, numCId);
				 collCityName = rootMaster.getCollegeCityName(dataSource, numCId);
			}
			
		}
		else{
			cName = rootMaster.getCollegeName(dataSource, otherCollId);
			collCityName = rootMaster.getCollegeCityName(dataSource, otherCollId);
		}
		////////System.out.println("cName....."+cName);
		
		strValue +="<tr><td><hr color=\"#F0972F\">";
		strValue +="<table width=\"100%\">";
			strValue +="<tr>";
			
			/*strValue +="<td width=\"20%\">";
			strValue +="<table width=\"100%\"  align=\"center\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">";
			strValue +="<tr height=\"125\" align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\"><td align=\"center\" valign=\"middle\"><img src =\""+photopath+"\" width=\"80\"  height=\"100\"></img></td>";
			strValue +="</tr>";					
			strValue +="</table>";
			strValue +="</td>";
			*/
			
			strValue +="<td width=\"20%\"></td>";
		
				strValue +="<td width=\"80%\">";
					strValue +="<table width=\"100%\" >";
				strValue +=" <tr align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\">";
				strValue +="  <td colspan=\"5\" valign=\"top\"  bgcolor=\"#FFFFFF\" class=\"bold\">&nbsp;";
				strValue +=" <strong><font color=\"#0033FF\"><A href=\"otherResumePre.do?pageid=1&userid="+loginId+"&index="+i+"&detailFor=Student\"> "+firstSName+"   "+lastName+"</A></font></strong>,&nbsp;"+tempVec.elementAt(3).toString().trim()+"</td>";
				strValue +=" </tr>";
				
				IndvMaster indvMaster=new IndvMaster();
				Vector course=indvMaster.getCourseName(dataSource, Integer.parseInt(tempVec.elementAt(4).toString().trim()));
				Vector Stream=indvMaster.getStreamName(dataSource, Integer.parseInt(tempVec.elementAt(8).toString().trim()));
				
				
				strValue +=" <tr align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\">";
				strValue +="  <td colspan=\"5\" valign=\"top\"  bgcolor=\"#FFFFFF\" class=\"bold\">&nbsp;";
				strValue +=" "+course.elementAt(0).toString().trim()+",&nbsp;"+Stream.elementAt(0).toString().trim()+"</td>";
				strValue +=" </tr>";
				
				strValue +=" <tr align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\">";
				strValue +="  <td colspan=\"5\" valign=\"top\"  bgcolor=\"#FFFFFF\" >&nbsp;";
				strValue +=" "+cName+",&nbsp;"+collCityName+"</td>";
				strValue +=" </tr>";
				
								
					strValue +="</table>";
				strValue +="</td>";

				
		strValue +="</tr>";
		strValue +="</table>";
	strValue +="</td></tr>";
		
		
		}}else{
		strValue +=" <tr bgcolor=\"#FFFAF6\">";
		strValue +="  <td height=\"200\" colspan=\"5\" align=\"center\" valign=\"middle\" bgcolor=\"#FFFFFF\"><span class=\"bold\">No Student(s) found. </span></td>";
		strValue +="  </tr>";
		strValue +=" <tr align=\"right\" bgcolor=\"#FFFAF6\">";
		strValue +="  <td colspan=\"5\" valign=\"middle\" bgcolor=\"#FFFFFF\"></td>";
		strValue +="  </tr>";
		}
		
		session.setAttribute("allcid", allcid);
		
		strValue +=" <tr >";		
		
		if(totalRow>maxVal){
			
		
		if(minVal==0){
			
			strValue +=" <td colspan=\"3\" class=\"bold\" align=\"left\"><span style=\"cursor:hand\"  onclick=\"retrieveSComplistURL('searchQueriesResult.do?searchFor="+searchFor+"&numMin="+(minVal+maxVal)+"');\" > Next Page</span></td>";
			
		}else if((totalRow - minVal)<= maxVal){
			
			strValue +=" <td colspan=\"3\" class=\"bold\" align=\"left\"><span style=\"cursor:hand\"  onclick=\"retrieveSComplistURL('searchQueriesResult.do?searchFor="+searchFor+"&numMin="+(minVal-maxVal)+"');\" >Previous Page </span></td>";
			
		}
		else{
		
		strValue +=" <td colspan=\"3\" class=\"bold\" align=\"left\"><span style=\"cursor:hand\"  onclick=\"retrieveSComplistURL('searchQueriesResult.do?searchFor="+searchFor+"&numMin="+(minVal-maxVal)+"');\" >Previous Page </span> <span style=\"cursor:hand\"  onclick=\"retrieveSComplistURL('searchQueriesResult.do?searchFor="+searchFor+"&numMin="+(minVal+maxVal)+"');\" >&nbsp;&nbsp;|&nbsp;&nbsp; Next Page </span></td>";
		}	
		}
		strValue +=" </tr>";

		strValue +=" </table></TD>";
		strValue +="    </TR>";

		strValue +="  </TBODY></TABLE></TD>";
		strValue +=" <TD vAlign=top width=123>";
		strValue +="  <TABLE cellSpacing=0 cellPadding=0 width=114 align=right bgColor=#ffffff border=1 >";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +=" <TD width=\"110\">";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=104 align=center border=0>";
		strValue +=" <TBODY>";
		strValue +=" <TR>";
		strValue +=" <TD width=\"104\"><TABLE cellSpacing=0 cellPadding=0 width=\"100%\" border=0>";
		strValue +=" <TBODY>";
		strValue +="  <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif></TD></TR>";
		strValue +="  <TR>";
		strValue +=" <TD><table width=\"98%\" border=\"0\" align=\"center\" cellpadding=\"2\" cellspacing=\"2\" bgcolor=\"#CACACA\">";
		strValue +="  <tr>";
		strValue +="   <td class=\"bold\"><div align=\"center\">Cities</div></td>";
		strValue +="  </tr>";
		strValue +="  </table></TD>";                                                            
		strValue +="  </TR>";
		strValue +="  <TR>";
		strValue +="  <TD background=blogs_files/1x1white.gif>";
		strValue +=" </TD>";
		strValue +=" </TR>";
		strValue +=" <TR>";
		strValue +="  <TD>";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"101%\"    align=center border=0>";
		strValue +=" <TBODY>";
		Vector CityVec = rootMaster.getAllPlace(94,  dataSource);
		Vector tempCityVec = new Vector();
		for(int i=1 ; i<CityVec.size()-1;i++)
		{
			tempCityVec.add(CityVec.elementAt(i));
		}
		int tempInt = tempCityVec.size()/10;
		int tempRest = tempCityVec.size()%10;
//		////////System.out.println("tempInt>>>>>>>>>"+tempInt);
//		////////System.out.println("tempRest>>>>>>>>>"+tempRest);
		int y3 = 10;
		if(tempRest!=0)
		{
		tempInt += 1;
		}

		for(int k=0;k<tempInt;k++,y3+=10){
		if(y3>tempCityVec.size())
		{
		if(tempRest!=0)
		{
		y3=y3+tempRest-10;
		}
		else
		{
		y3=tempCityVec.size();
		}
		}
		if(k==0){


		strValue +=" <TR id=\"city"+k+"\" style=\"display:inline\">";
		}else{
		strValue +=" <TR id=\"city"+k+"\" style=\"display:none\">";
		}
		strValue +=" <TD colspan=\"2\"  align=\"left\" valign=\"top\">";
		if(k==0){
		strValue +=" <A href=\"searchQueriesResult.jsp?cityStr=&brandStr="+brand+"&categoryStr="+category+"\"><SPAN class=\"bold\">ALL</SPAN></A><BR>";
		}
		for(int j=k*10;j<y3;j++){

		strValue +=" <A href=\"searchQueriesResult.jsp?cityStr="+((Vector)tempCityVec.elementAt(j)).elementAt(0).toString().trim()+"&brandStr="+brand+"&categoryStr="+category+"\"><SPAN class=\"bold\">"+((Vector)tempCityVec.elementAt(j)).elementAt(1).toString().trim()+"</SPAN></A><BR>";

		}
		strValue +=" </TD>";
		strValue +=" </TR>";

		}

		strValue +=" <tr id=\"citynext\" style=\"display:inline\"><td width=\"60\"></td><td  align=\"right\" class=\"bold\" id=\"next\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"city\" style=\"display:none\"><td width=\"60\" align=\"center\" class=\"bold\" id=\"Previous\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Previous&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td class=\"bold\" id=\"next\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"citypre\" style=\"display:none\"><td width=\"60\" colspan=\"2\" align=\"left\" class=\"bold\" id=\"Previous\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Previous</a></td><td></td></tr>";

		strValue +=" </TBODY>";
		strValue +=" </TABLE>";
		strValue +=" </TD>";
		strValue +=" </TR>";
		strValue +="  <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif></TD></TR></TBODY></TABLE>  </TD></TR>";
		strValue +=" <TR><TD>";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" border=0>";
		strValue +=" <TBODY>";
		strValue +="  <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif>";
		strValue +=" </TD></TR>";
		strValue +=" <TR>";
		strValue +=" <TD><table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"2\" cellspacing=\"2\" bgcolor=\"#CACACA\">";
		strValue +="  <tr>";
		strValue +="  <td class=\"bold\"><div align=\"center\">College</div></td>";
		strValue +="  </tr>";
		strValue +="  </table> </TD>";
		strValue +=" </TR>";
		strValue +="  <TR>";
		strValue +="  <TD background=blogs_files/1x1white.gif></TD></TR>";
		strValue +=" <TR>";
		strValue +=" <TD>";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" align=center border=0>";
		strValue +=" <TBODY>";
	
		
		Vector collegeVec = new Vector();
		Vector collegeVecCom = new Vector();
		
		if(cscVec.size()==6){
			
		 collegeVec = rootMaster.getCollege(dataSource,cscVec.elementAt(1).toString(), cscVec.elementAt(3).toString(), cscVec.elementAt(5).toString());
		 collegeVecCom = rootMaster.getCollegeCom(dataSource, cscVec.elementAt(1).toString(), cscVec.elementAt(3).toString(), cscVec.elementAt(5).toString());
		}
		else if(cscVec.size()==4){
			
			collegeVec = rootMaster.getCollegeState(dataSource,cscVec.elementAt(1).toString(), cscVec.elementAt(3).toString());
			 collegeVecCom = rootMaster.getCollegeComState(dataSource, cscVec.elementAt(1).toString(), cscVec.elementAt(3).toString());
						
		}
		else {
			
			collegeVec = rootMaster.getCollegeCountry(dataSource,cscVec.elementAt(1).toString());
			 collegeVecCom = rootMaster.getCollegeComCountry(dataSource, cscVec.elementAt(1).toString());
						
		}
		
		Vector<Vector>brandVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		
		for(int i=0;i<collegeVecCom.size();i++)
		{	
			Vector teamp = (Vector)collegeVecCom.elementAt(i);
			Vector<String>teamp1 = new Vector<String>();
			
			String teampId = teamp.elementAt(0).toString();
			
			teamp1.add(teampId+"~1");
			teamp1.add(teamp.elementAt(1).toString());
			
			brandVec.add(teamp1);
			
		}
		
		for(int i=0;i<collegeVec.size();i++)
		{
			Vector teamp = (Vector)collegeVec.elementAt(i);
			Vector<String>teamp1 = new Vector<String>();
			
			String teampId = teamp.elementAt(0).toString();
			
			teamp1.add(teampId+"~0");
			teamp1.add(teamp.elementAt(1).toString());
			
			brandVec.add(teamp1);
			
			
		}
		
		Vector tempBrandVec = new Vector();
		if(brandVec.size()>0){
			for(int i=0 ; i<brandVec.size();i++)
			{
				tempBrandVec.add(brandVec.elementAt(i));
			}
		int tempInt1 = tempBrandVec.size()/10;
		int tempRest1 = tempBrandVec.size()%10;
//		////////System.out.println("tempBrandVec.size()>>>>>>>>>"+tempBrandVec.size());
//		////////System.out.println("tempInt1>>>>>>>>>"+tempInt1);
//		////////System.out.println("tempRest1>>>>>>>>>"+tempRest1);
		int y1 = 10;
		if(tempRest1!=0)
		{
		tempInt1 += 1;
		}

		for(int k1=0;k1<tempInt1;k1++,y1+=10){
		if(y1>tempBrandVec.size())
		{
			if(tempRest1!=0)
			{
				y1=y1+tempRest1-10;
			}
			else
			{
				y1=tempBrandVec.size();
			}
		}

		if(k1==0){


		strValue +=" <TR id=\"brand"+k1+"\" style=\"display:inline\">";
		}else{
		strValue +=" <TR id=\"brand"+k1+"\" style=\"display:none\">";
		}
		strValue +=" <TD colspan=\"2\"  align=\"left\" valign=\"top\">";
		if(k1==0){
		strValue +=" <A href=\"searchQueriesResult.jsp?cityStr=0&brandStr=&categoryStr="+category+"\"><SPAN class=\"bold\">ALL</SPAN></A><BR>";
		}
		for(int j1=k1*10;j1<y1;j1++){
//		////////System.out.println(k+">>>>>>>>>>>>>>>>>>"+tempCityVec.elementAt(j).toString());




		Vector tempBrandVec1 = (Vector)tempBrandVec.elementAt(j1);

		strValue +=" <A href=\"searchQueriesResult.jsp?cityStr=0&brandStr="+tempBrandVec1.elementAt(0).toString()+"&categoryStr="+category+"\"><SPAN class=\"bold\">"+tempBrandVec1.elementAt(1).toString()+"</SPAN></A><BR>";



		}
		strValue +=" </TD>";
		strValue +=" </TR>";



		}
		if(tempBrandVec.size()>10)
		{


		strValue +=" <tr id=\"brandnext\" style=\"display:inline\"><td width=\"60\"></td><td  align=\"right\" class=\"bold\" id=\"next\" onclick=\"showBrand(this.id,"+(tempInt1-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"brand\" style=\"display:none\"><td width=\"60\" align=\"center\" class=\"bold\" id=\"Previous\" onclick=\"showBrand(this.id,"+(tempInt1-1)+")\" style=\"cursor:hand\">Previous&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td  class=\"bold\" id=\"next\" onclick=\"showBrand(this.id,"+(tempInt1-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"brandpre\" style=\"display:none\"><td width=\"60\" align=\"left\" class=\"bold\" id=\"Previous\" onclick=\"showBrand(this.id,"+(tempInt1-1)+")\" style=\"cursor:hand\">Previous</td><td></td></tr>";

		}}else{
		strValue +=" <tr><td >No College Availlable<BR></TD>";
		strValue +=" </TR>";
		}
		strValue +=" </TBODY>";
		strValue +=" </TABLE>";
		strValue +=" </TD>";
		strValue +=" </TR>";
		strValue +=" <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif></TD>";
		strValue +=" </TR>";
		strValue +=" </TBODY>";
		strValue +=" </TABLE>";
		strValue +=" </TD>";
		strValue +=" </TR>";

		strValue +=" <TR>";
		strValue +=" <TD><TABLE cellSpacing=0 cellPadding=0 width=\"100%\" border=0>";
		strValue +=" <TBODY>";
		strValue +="  <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif></TD></TR>";
		strValue +=" <TR>";
		strValue +=" <TD><table width=\"98%\" border=\"0\" align=\"center\" cellpadding=\"2\" cellspacing=\"2\" bgcolor=\"#CACACA\">";
		strValue +="  <tr>";
		strValue +="    <td class=\"bold\"><div align=\"center\">Stream</div></td>";
		strValue +="  </tr>";
		strValue +=" </table></TD>";
		strValue +=" </TR>";
		strValue +=" <TR>";
		strValue +=" <TD background=blogs_files/1x1white.gif></TD></TR>";
		strValue +=" <TR>";
		strValue +="  <TD>";
		strValue +=" <TABLE cellSpacing=0 cellPadding=0 width=\"95%\" align=center border=0>";
		strValue +=" <TBODY>";
		
		//Vector categoryVec = rootMaster.getCategories(dataSource);
		Vector<Vector>resultVec = new Vector<Vector>();
		
		resultVec = rootMaster.getCourse(dataSource);
		
		/*Vector categoryVec0 =new Vector();
		Vector categoryVec1 =new Vector();
		Vector categoryVec2 =new Vector();
		Vector categoryVec3 =new Vector();
		Vector categoryVec4 =new Vector();
		Vector categoryVec5 =new Vector();
		Vector categoryVec6 =new Vector();
		Vector categoryVec7 =new Vector();
		Vector categoryVec8 =new Vector();
		Vector categoryVec9 =new Vector();
		Vector categoryVec10 =new Vector();
		Vector categoryVec11 =new Vector();
		Vector categoryVec12 =new Vector();
		Vector categoryVec13 =new Vector();
		Vector categoryVec14 =new Vector();
		Vector categoryVec15 =new Vector();
		Vector categoryVec16 =new Vector();
		Vector categoryVec17 =new Vector();
		Vector categoryVec18 =new Vector();
		
		
		categoryVec0.add("All");
		categoryVec0.add("All");
		resultVec.add(categoryVec0);
		categoryVec1.add("B.A");
		categoryVec1.add("B.A");
		resultVec.add(categoryVec1);
		categoryVec2.add("B.Arch");
		categoryVec2.add("B.Arch");
		resultVec.add(categoryVec2);
		categoryVec3.add("B.C.A");
		categoryVec3.add("B.C.A");
		resultVec.add(categoryVec3);
		categoryVec4.add("B.Com");
		categoryVec4.add("B.Com");
		resultVec.add(categoryVec4);
		categoryVec18.add("B.Sc");
		categoryVec18.add("B.Sc");
		resultVec.add(categoryVec18);
		categoryVec5.add("B.Tech/B.E.");
		categoryVec5.add("B.Tech/B.E.");
		resultVec.add(categoryVec5);
		categoryVec6.add("Diploma");
		categoryVec6.add("Diploma");
		resultVec.add(categoryVec6);
		categoryVec7.add("CA");
		categoryVec7.add("CA");
		resultVec.add(categoryVec7);
		categoryVec8.add("CS");
		categoryVec8.add("CS");
		resultVec.add(categoryVec8);
		categoryVec9.add("ICWA");
		categoryVec9.add("ICWA");
		resultVec.add(categoryVec9);
		categoryVec10.add("M.A");
		categoryVec10.add("M.A");
		resultVec.add(categoryVec10);
		categoryVec11.add("M.Arch");
		categoryVec11.add("M.Arch");
		resultVec.add(categoryVec11);
		categoryVec12.add("M.Com");
		categoryVec12.add("M.Com");
		resultVec.add(categoryVec12);
		categoryVec13.add("M.Sc");
		categoryVec13.add("M.Sc");
		resultVec.add(categoryVec13);
		categoryVec14.add("M.Tech");
		categoryVec14.add("M.Tech");
		resultVec.add(categoryVec14);
		categoryVec15.add("MBA/PGDM");
		categoryVec15.add("MBA/PGDM");
		resultVec.add(categoryVec15);
		categoryVec16.add("MCA");
		categoryVec16.add("MCA");
		resultVec.add(categoryVec16);
		categoryVec17.add("PG Diploma");
		categoryVec17.add("PG Diploma");
		resultVec.add(categoryVec17);
		*/
		
		
		Vector tempCategoryVec = new Vector();
		if(resultVec.size()>0){
			for(int i=1 ; i<resultVec.size();i++)
			{
				tempCategoryVec.add(resultVec.elementAt(i));
			}
		int tempInt2 = tempCategoryVec.size()/10;
		int tempRest2 = tempCategoryVec.size()%10;
		int y2 = 10;
		if(tempRest2!=0)
		{
		tempInt2 += 1;
		}

		for(int k2=0;k2<tempInt2;k2++,y2+=10){
		if(y2>tempCategoryVec.size())
		{
		if(tempRest2!=0)
		{
		y2=y2+tempRest2-10;
		}
		else
		{
		y2=tempCategoryVec.size();
		}
		}

		if(k2==0){


		strValue +=" <TR id=\"category"+k2+"\" style=\"display:inline\">";
		}else{
		strValue +=" <TR id=\"category"+k2+"\" style=\"display:none\">";
		}
		strValue +=" <TD colspan=\"2\" align=\"left\" valign=\"top\">";
		if(k2==0){
		strValue +="  <A href=\"searchQueriesResult.jsp?cityStr=0&brandStr="+brand+"&categoryStr=\"><SPAN class=\"bold\">ALL</SPAN></A><BR>";
		}
		for(int j2=k2*10;j2<y2;j2++){
//		//////System.out.println(k+">>>>>>>>>>>>>>>>>>"+tempCityVec.elementAt(j).toString());


		Vector tempCat = (Vector)tempCategoryVec.elementAt(j2);

		strValue +=" <A href=\"searchQueriesResult.jsp?cityStr=0&brandStr="+brand+"&categoryStr="+tempCat.elementAt(0).toString()+"\"><SPAN class=\"bold\">"+tempCat.elementAt(1).toString()+"</SPAN></A><BR>";



		}
		strValue +=" </TD>";
		strValue +=" </TR>";



		}
		if(tempCategoryVec.size()>10)
		{


		strValue +=" <tr id=\"categorynext\" style=\"display:inline\"><td width=\"60\"></td><td  align=\"right\" class=\"bold\" id=\"next\" onclick=\"showCategory(this.id,"+(tempInt2-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"category\" style=\"display:none\"><td width=\"60\" align=\"center\" class=\"bold\" id=\"Previous\" onclick=\"showCategory(this.id,"+(tempInt2-1)+")\" style=\"cursor:hand\">Previous</td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td class=\"bold\" id=\"next\" onclick=\"showCategory(this.id,"+(tempInt2-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"categorypre\" style=\"display:none\"><td width=\"60\" align=\"left\" class=\"bold\" id=\"Previous\" onclick=\"showCategory(this.id,"+(tempInt2-1)+")\" style=\"cursor:hand\">Previous</td><td></td></tr>";


		}}else{
		strValue +=" <tr><td >No Streem Availlable</A><BR></TD>";
		strValue +=" </TR>";
		}
		if(reminder==0)
		{
			strValue +=" <tr><td ><BR></TD>";
			strValue +=" </TR>";
		}
		strValue +=" </TBODY>";
		strValue +=" </TABLE>";
		strValue +=" </TD>";
		strValue +=" </TR>";
		strValue +=" <TR>";
		strValue +="  <TD height=\"2\" background=blogs_files/1x1white.gif></TD>";
		strValue +="  </TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>";
		strValue +=" <TD width=6></TD>";
		strValue +="  </TR>";
		
		strValue +="  <TR>";
		strValue +=" <TD height=\"2\" colSpan=4></TD>";
		strValue +=" </TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>";
		strValue +=" <TD width=4></TD>";
		strValue +=" </TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>";
		strValue +=" </TBODY></TABLE>                              </TD></TR></TBODY></TABLE></TD></TR>";
		strValue +=" </TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>";
		return strValue;
	}
	private String getPages(int minVal, int maxVal, int numSize, String numCount, String searchFor)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveSComplistURL('searchQueriesResult.do?searchFor="+searchFor+"&numCount="+numCount+"&'+this.value);\">";
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
