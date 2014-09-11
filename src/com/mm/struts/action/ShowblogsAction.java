// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 7/20/2007 11:53:25 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ShowblogsAction.java
package com.mm.struts.action;


import com.mm.core.master.RootMaster;
import com.mm.struts.form.SearchForm;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.*;

public class ShowblogsAction extends Action
{

  
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
    	
    	HttpSession session = request.getSession();
		String numCount = (request.getParameter("numCount")!=null)?request.getParameter("numCount"):(String)session.getAttribute("numCount");
		String typ = (request.getParameter("typ")!=null) ? request.getParameter("typ") : "blog";
		////System.out.println("typtyptyp>>>>>>>>>>"+typ);
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		
    	DataSource ds = getDataSource(request,"main");
    	String pageid = (request.getParameter("pageid")!=null)? request.getParameter("pageid").trim() : "1";
		String aType = (request.getParameter("aType")!=null)? request.getParameter("aType").trim() : "0";
		String lowerSubTag = (request.getParameter("lowerSubTag")!=null)? request.getParameter("lowerSubTag").trim() : "1";
		String categoryid = (request.getParameter("categoryid")!=null)? request.getParameter("categoryid").trim() : "0";
		
    	
    	// Shorting function is not working now 
    	String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "creation_date";
		String strSOrder = "";
		String strComFIdOrder = "asc";
		String strCTitelOrder = "asc";
		String strDateOrder = "asc";
		
		if(request.getParameter("sby")!=null)
		{
			if(request.getParameter("fidorder")!=null)
			{
				if(request.getParameter("fidorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strComFIdOrder = "desc";
				}
				else if (request.getParameter("fidorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strComFIdOrder = "asc";
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
		int maxVal =10;
		
		RootMaster rootMaster = new RootMaster();
		Vector<String> countVec = new Vector<String>();
		countVec.add(typ);
		countVec.add(categoryid);
		int totalRow =rootMaster.getBlogsCount(ds, countVec);
		System.out.println("Total Row "+totalRow);
		
        String strPageHtml = getPages(minVal, maxVal, totalRow,numCount,typ);
        Vector<String> paramVec = new Vector<String>();
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));	
		paramVec.add(typ);
		paramVec.add(categoryid);
		System.out.println("ParamVEc is show blogs action "+paramVec);
		Vector comVec = rootMaster.getBlogsList(getDataSource(request, "main"), paramVec);
		
		System.out.println("comVec is show blogs action "+comVec);
		String strComplaintList = getBlogsList1(strComFIdOrder, ds, strCTitelOrder,strDateOrder, minVal, maxVal ,totalRow, strPageHtml, comVec,ds,remander,pageid,lowerSubTag,typ,numCount);
        
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        out.println(strComplaintList);
        out.flush();
       
        return null;
    }
    

    private String getBlogsList1(String strComFIdOrder, DataSource dataSource, String strCTitelOrder,String strDateOrder, int minVal,int maxVal,int totalRow, String strPageHtml, Vector comVec,DataSource ds,int remander,String pageid,String lowerSubTag,String typ, String numCount)
	{
    	RootMaster rootMaster = new RootMaster();
    	
    	String strValue = "<div class=\"main_frame_mid_inside-comment\">";  
    	strValue += "<div class=\"Page_Title\">Existing Classifieds";		
		if(totalRow != 0)		{
		strValue += "<div style=\"float: right;\">";
		strValue += "<a style=\"font-size: 10px; font-weight: bold; color: rgb(0, 0, 0);\">Total matches Found:  "+totalRow+"</a></div>";
		}
		strValue += "</div>";
		
		if(comVec.size()==0){
		if(typ.equalsIgnoreCase("blog")){	
		strValue +="<div>No Classifieds Available...";
		strValue += "</div>";
		}else{	
			strValue +="<div>No Reviews Available...";
			strValue += "</div>";
			}			
		}
		else
		{
			
			if (totalRow > maxVal){
				strValue += "<div style=\"float:left; text-align:right; margin:3px; width:97%; font-size:11px;\">Page Number  "+strPageHtml+"</div>";
	
			}
			
			for(int i=0; i<comVec.size(); i++)
			{
				Vector compdataVec = (Vector)comVec.elementAt(i);
			    int loginid=Integer.parseInt(compdataVec.elementAt(9).toString()) ;
				Vector userVec = new Vector();
			    userVec=rootMaster.getUserInfo(ds, loginid);
		        String fname= userVec.elementAt(4).toString();
		        String lname= userVec.elementAt(5).toString();	
		        int cityid=Integer.parseInt(userVec.elementAt(6).toString()) ;
		        String city=rootMaster.getPlaceName(ds, cityid);
		    
		        int complainId=Integer.parseInt(compdataVec.elementAt(0).toString()) ;
		        int comment=rootMaster.getcommentCount(ds, complainId);
		       	 String strText = "";  
				 String strFMsg = "";
				 
					String strMessage =  compdataVec.elementAt(2).toString().trim();
					int numChar = (strMessage.length() > 200) ? 200 : strMessage.length();  
					for(int k=0; k<numChar; k++)
					{ 
						
							   strFMsg = strFMsg+strMessage.charAt(k);
							   
							
					}
					if(strFMsg.lastIndexOf(strFMsg)!=-1)
					{
						 strText= strFMsg;
					
			         
			        }else{
			        	strText= strFMsg.substring(0, strFMsg.lastIndexOf(" "));
			        }	
					
					
					
				//	for check length of Complaint title
					 String strText1 = "";  
					 String strFMsg1 = "";
					 
						String strMessage1 =  compdataVec.elementAt(1).toString().trim();
						int numChar1 = (strMessage1.length() > 35) ? 35 : strMessage1.length();  
						for(int k=0; k<numChar1; k++)
						{ 
							
								   strFMsg1 = strFMsg1+strMessage1.charAt(k);
								   
								
						}
						if(strFMsg1.lastIndexOf(strFMsg1)!=-1)
						{
							 strText1= strFMsg1;
							////////////System.out.println("...strFMsgTeam......"+strFMsg1.lastIndexOf(strFMsg1));
							
				         
				        }else{
				        	strText1= strFMsg1.substring(0, strFMsg1.lastIndexOf(" "));
				        	////#E4F1FC////////System.out.println("...strText......");
				        }	
						int j = i+1;
				    	int numTotal = j%2;
				    
				 int respCount=rootMaster.getResponse_Count(ds, complainId);   	
				int compid=Integer.parseInt(compdataVec.elementAt(8).toString()) ;
				Vector compVec = new Vector();
				compVec=rootMaster.getCompanyDetail(ds, compid);
		        //////////System.out.println("loginid........."+loginid);
		        String cname= compVec.elementAt(1).toString();
		        String ComName = "";
		        if (cname == "Student"){
		        	
		        	ComName = "Resident";
		        	}
		        System.out.println("C Name is "+ComName);
		        
		    	strValue += "<div style=\"float:left; margin:5px 0px;\">";	
				strValue += "<div class=\"commentbox\">";
				strValue += "<h3>"+strText1+"</h3>";
				strValue += "<p>"+strText+"</p>";
				
		
				strValue +="<ul style=\"float:right; margin:5px 5px; font-size:11px;\">";
				strValue +="<li style=\"float:left; padding:3px 2px;\">Response: <span>"+respCount+"</span></li>";
				strValue +="<li style=\"float:left; padding:3px 2px;\">|</li>";
				strValue +="<li style=\"float:left; padding:3px 2px;\">Comment: <span>"+comment+"</span></li>";
				strValue +="</ul>";
				strValue +="<ul style=\"float:left; margin:5px 5px; font-size:11px;\">";
				strValue +="<li style=\"float:left; padding:3px 2px;\"><a href=\"complaintsDetail.jsp?complainId="+complainId+"&minVal="+minVal+"\"> read more</a></li>";
				strValue +="</ul>";
					strValue +="</div>";
						strValue +="<div class=\"commentfooter\">Posted by "+fname+" "+lname+" on "+compdataVec.elementAt(4)+"/"+compdataVec.elementAt(5)+" /<span class=\"student\" >"+ComName+"</span></div>";
						strValue +="</div>";	
			//strValue += "<div class=\"Row_2_Fields_Col_3\" style=\"width: 70%;\"><a href=\"complaintsDetail.jsp?complainId="+complainId+"&minVal="+minVal+"\"> "+strText1+"</a></div></div>";		    	
		    //strValue += "<div class=\"Row_2_Fields_Col_3\" p style=\"text-align:justify\">"+strText+"... <a href=\"complaintsDetail.jsp?complainId="+complainId+"&minVal="+minVal+"&typ="+typ+"\"><img src=\"images/read_more.jpg\" width=\"74\" height=\"10\" border=\"0\" /></a></p></div></div>";
		
			}
			
			if (totalRow > maxVal){				
				strValue += "<div>Page Number  "+strPageHtml+"</div>";				
			}


		}
		strValue += "</div>";		
		if(remander==0)
		{
			strValue += "<label  class=\"required\" > </label><label  class=\"required\" > </label><label  class=\"required\" ></label>";			
		}
    
		
	    strValue += "<div class=\"right_div\">";	
		strValue +="<div class=\"SideNav_frame-2\">";
		strValue +="<div class=\"Page_Title-2\">Search Criteria</div></div>";
		
	    strValue +="<div class=\"SideNav_frame-2\">";	
		strValue +="<div class=\"Search_Criteria\">Category</div>";		
		strValue +="<ul class=\"Search_Criteria-ul\">";
		
		
		Vector CatVec = rootMaster.getAllBlogCategory(dataSource);
		Vector tempCatVec = new Vector();
		for(int i=1 ; i<CatVec.size()-1;i++)
		{
			tempCatVec.add(CatVec.elementAt(i));
		}
		int tempInt = tempCatVec.size()/10;
		int tempRest = tempCatVec.size()%10;
//		////////System.out.println("tempInt>>>>>>>>>"+tempInt);
//		////////System.out.println("tempRest>>>>>>>>>"+tempRest);
		int y3 = 10;
		if(tempRest!=0)
		{
		tempInt += 1;
		}

		for(int k=0;k<tempInt;k++,y3+=10){
		if(y3>tempCatVec.size())
		{
		if(tempRest!=0)
		{
		y3=y3+tempRest-10;
		}
		else
		{
		y3=tempCatVec.size();
		}
		}

		System.out.println("temp Vec at ShowBlogs Action"+tempCatVec);
/*		if(k==0){
		strValue +=" <TR id=\"city"+k+"\" style=\"display:inline\">";
		}else{
		strValue +=" <TR id=\"city"+k+"\" style=\"display:none\">";
		}
		strValue +=" <TD colspan=\"2\"  align=\"left\" valign=\"top\">";*/
		if(k==0){
		//strValue +=" <A href=\"searchresult.jsp?cityStr=&brandStr="+brand+"&categoryStr="+category+"\">ALL/A><BR>";
		strValue +="<li><A onclick=\"retrieveRootsearchURL('showblogs.do?numMin=0&numCount="+numCount+"&typ=blog');\" href=\"#\">ALL</A></li>";
		}
		for(int j=k*10;j<y3;j++){

		strValue +=" <li><A onclick=\"retrieveRootsearchURL('showblogs.do?numMin=0&numCount="+numCount+"&typ=blogs&categoryid="+((Vector)tempCatVec.elementAt(j)).elementAt(0).toString().trim()+"&numCount=6');\" href=\"#\">"+((Vector)tempCatVec.elementAt(j)).elementAt(1).toString().trim()+"</A></li>";

		}
		}
		 strValue +="</ul>";
			strValue +="<span style=\"float:left; margin-left:5px; font-size:11px;\">Prev</span>";
			strValue +="<span style=\"float:right; margin-right:5px; font-size:11px;\">Next</span>";
			
	/*	strValue +=" <tr id=\"citynext\" style=\"display:inline\"><td width=\"60\"></td><td  align=\"right\"  id=\"next\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"city\" style=\"display:none\"><td width=\"60\" align=\"center\" id=\"Previous\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Previous&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td class=\"bold\"id=\"next\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Next</td></tr>";
		strValue +=" <tr id=\"citypre\" style=\"display:none\"><td width=\"60\" colspan=\"2\" align=\"left\"  id=\"Previous\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Previous</a></td><td></td></tr>";
*/
		strValue +="    </div></div></div>";
		return strValue;
    }

    

private String getPages(int minVal, int maxVal, int numSize,String numCount,String typ)
{ 
	String strResult ="<select name=\"paging\" style=\"font-size:11px; padding:2px;\" onchange=\"retrieveRootsearchURL('showblogs.do?numCount="+numCount+"&typ="+typ+"&'+this.value);\">";
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