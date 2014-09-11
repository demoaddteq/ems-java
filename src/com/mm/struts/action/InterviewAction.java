// Decompiled by DJ v3.4.4.74 Copyright 2003 Atanas Neshkov  Date: 7/20/2007 11:53:25 AM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ShowblogsAction.java
package com.mm.struts.action;


import com.mm.core.master.*;
import com.mm.struts.form.SearchForm;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.*;

public class InterviewAction extends Action
{

  
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
    	
    	HttpSession session = request.getSession();
		String numCount = (request.getParameter("numCount")!=null)?request.getParameter("numCount"):(String)session.getAttribute("numCount");
		String interId = (request.getParameter("InterId")!=null)?request.getParameter("InterId"):"-1";
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		
    	DataSource ds = getDataSource(request,"main");
    	
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
		int maxVal =5;
		
		RootMaster rootMaster = new RootMaster();
		IndvMaster indvMaster = new IndvMaster();
		Vector<String> countVec = new Vector<String>();
		
		int totalRow =indvMaster.getICount(ds);
		
        String strPageHtml = getPages(minVal, maxVal, totalRow,numCount);
        Vector<String> paramVec = new Vector<String>();
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));	
		Vector comVec = indvMaster.getinterview(getDataSource(request, "main"), paramVec);
		
		
		String strComplaintList = getComplaintList(strComFIdOrder, strCTitelOrder,strDateOrder, minVal, maxVal ,totalRow, strPageHtml, comVec,ds,remander,interId,numCount);
        
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        out.println(strComplaintList);
        out.flush();
        
        
        
        
       
        return null;
    }

    private String getComplaintList(String strComFIdOrder, String strCTitelOrder,String strDateOrder, int minVal,int maxVal,int totalRow, String strPageHtml, Vector comVec,DataSource ds,int remander,String interId,String numCount)
	{
    	RootMaster rootMaster = new RootMaster();
    	
    	
		////////////////System.out.println("table1........."+comVec.size());
    	String strvalue = "";
		if(comVec.size()==0)
		{
			strvalue += "<br><br><br><br><br><br><br><br>";
			strvalue += "<div align=\"center\"><h3><span class=\"Student\" align=\"center\">No Blog Available..</span></h3></div>";
			strvalue += "<br><br><br><br><br><br><br><br><br>";
			
		}
		else
		{
			
			if (totalRow > maxVal)
			{
				if(minVal==0){
					
					strvalue +=" <label   class=\"bold\" ><span style=\"cursor:hand\"  onclick=\"retrieveInterviewURL('interview.do?numCount="+numCount+"&numMin="+(minVal+maxVal)+"');\" > Next Page</span></lable>";
					
				}else if((totalRow - minVal)<= maxVal)
				{
					strvalue +=" <label  class=\"bold\"><span style=\"cursor:hand\"  onclick=\"retrieveInterviewURL('interview.do?numCount="+numCount+"&numMin="+(minVal-maxVal)+"');\" >Previous Page </span></lable>";
					
				}
				else
				{
					strvalue +=" <label   class=\"bold\" ><span style=\"cursor:hand\"   onclick=\"retrieveInterviewURL('interview.do?numCount="+numCount+"&numMin="+(minVal-maxVal)+"');\" >Previous Page </span> <span style=\"cursor:hand\"   onclick=\"retrieveInterviewURL('interview.do?numCount="+numCount+"&numMin="+(minVal+maxVal)+"');\" >&nbsp;&nbsp;|&nbsp;&nbsp; Next Page </span></lable>";
				}	
				//strvalue +=" <p id=\"citynext\" style=\"display:inline\"><label  class=\"required\"   id=\"next\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Next</label>></p>";
				//strvalue +=" <p id=\"city\" style=\"display:none\"><label  class=\"required\" id=\"Previous\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Previous&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><label  class=\"required\" id=\"next\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Next</lable></p>";
				//strvalue +=" <p id=\"citypre\" style=\"display:none\"><label  class=\"required\" colspan=\"2\" align=\"left\"  id=\"Previous\" onclick=\"showCity(this.id,"+(tempInt-1)+")\" style=\"cursor:hand\">Previous</a></label></p>";

			}
				strvalue += "<br>";
				//////System.out.println("compdataVin Vec"+comVec.lastElement());
			Vector detailVec=new Vector();
				if(interId.equalsIgnoreCase("-1"))
				{////////System.out.println("dfdsfdsfdsfdsfdsfdsfdsfds");
					 detailVec =(Vector)comVec.firstElement();
					 //////System.out.println("compdataVin last index Vec"+detailVec);
				}else
				{//////System.out.println("compdataVin Vec"+detailVec);
					detailVec=(Vector)comVec.elementAt(Integer.parseInt(interId));
				}
				String photopath =  "http://localhost:8080/campusyogi/photo/"+detailVec.elementAt(3).toString().trim();
				  strvalue += "<div  >";
				  strvalue += "<p class=\"blogsTitle\" style=\"background-color:#EDF3F8\">"+detailVec.elementAt(1)+"<br style=\"background-color:#EDF3F8\" >"+detailVec.elementAt(0)+"<br ></p>";
				  strvalue += "<p style=\"text-align:justify\" class=\"student\">";
				  strvalue += "<img src =\""+photopath+"\" width=\"60\"  height=\"80\"></img>";
				  strvalue += ""+detailVec.elementAt(2)+"</p><br>";
				  
				  strvalue += "</div>";
				  strvalue += "<div>"+detailVec.elementAt(4)+"</div><br><br><br>";
			for(int i=0; i<comVec.size(); i++)
			{
				Vector compdataVec = (Vector)comVec.elementAt(i);
				////System.out.println("compdataVeccompdataVec"+i+""+compdataVec);
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
					
					
					
					int j = i+1;
				    	int numTotal = j%2;
				    	String strColor = (numTotal==0) ? "#EDF3F8" : "#F3F3F5";
				    
				    	String photopath1 =  "http://localhost:8080/campusyogi/photo/"+compdataVec.elementAt(3).toString().trim();
		        strvalue += "<div class=\"commentbox\" style=\"background-color:"+strColor+"\">";
		        strvalue += "<p class=\"blogsTitle\"> "+compdataVec.elementAt(0)+"</p>";
				strvalue += "<p style=\"text-align:justify\" valign=\"top\">" ;
				 strvalue += "<img src =\""+photopath1+"\" width=\"60\"  height=\"80\"></img>";
				 
				strvalue += ""+strText+"... <a href=\"interview.jsp?InterId="+i+"&numCount="+numCount+"&minVal="+minVal+"\"><img src=\"images/read_more.jpg\" width=\"74\" height=\"10\" border=\"0\" /></a></p>";
				//strvalue += "<p class=\"blogsVisit\">Response : "+respCount+"&nbsp;&nbsp; &nbsp;Comment : "+comment+"</p>";
				strvalue += "</div>";
				strvalue += "<div class=\"commentfooter\">Posted by "+compdataVec.elementAt(1)+" on "+compdataVec.elementAt(6)+" <span class=\"student\" ></span></div>";

				
				
			}
			Vector tempBrandVec = new Vector();
			if(comVec.size()>0){
				for(int i=0 ; i<comVec.size();i++)
				{
					tempBrandVec.add(comVec.elementAt(i));
				}
				
			int tempInt1 = tempBrandVec.size()/5;
			int tempRest1 = tempBrandVec.size()%5;
			
//			
			if (totalRow > maxVal)
			{
				if(minVal==0){
					
					strvalue +=" <label    class=\"bold\" ><span style=\"cursor:hand\"  onclick=\"retrieveInterviewURL('interview.do?numCount="+numCount+"&numMin="+(minVal+maxVal)+"');\" > Next Page</span></lable>";
					
				}else if((totalRow - minVal)<= maxVal){
					
					strvalue +=" <label   class=\"bold\"><span style=\"cursor:hand\"  onclick=\"retrieveInterviewURL('interview.do?numCount="+numCount+"&numMin="+(minVal-maxVal)+"');\" >Previous Page </span></lable>";
					
				}
				else{
				
					strvalue +=" <label   class=\"bold\" ><span style=\"cursor:hand\"   onclick=\"retrieveInterviewURL('interview.do?numCount="+numCount+"&numMin="+(minVal-maxVal)+"');\" >Previous Page </span> <span style=\"cursor:hand\"   onclick=\"retrieveInterviewURL('interview.do?numCount="+numCount+"&numMin="+(minVal+maxVal)+"');\" >&nbsp;&nbsp;|&nbsp;&nbsp; Next Page </span></lable>";
				}	
				
			}
				strvalue += "<br>";
			}
			
		}
		
		if(remander==0)
		{
			strvalue += "<label  class=\"required\" > </label><label  class=\"required\" > </label><label  class=\"required\" ></lable>";
			strvalue += "<br>";
		}
        return strvalue;
    }

    

private String getPages(int minVal, int maxVal, int numSize,String numCount)
{ 
	String strResult ="<p >";
	int numMin = 0;
	int numPage = 1;
	for(int i=0; i<numSize; i=i+maxVal)
	{
		numMin = i;
		
		if(minVal == numMin)
		{
			strResult += "<label  class=\"required\" onclick=\"retrieveInterviewURL('interview.do?numCount="+numCount+"&numMin="+numMin+"');\">"+numPage+",</lable>";
		}
		else
		{
			strResult += "<label  class=\"required\" onclick=\"retrieveInterviewURL('interview.do?numCount="+numCount+"&numMin="+numMin+"');\">"+numPage+",</lable>";
		}
			numPage++;
	}
	strResult += "</p>";
	return strResult;
}
}