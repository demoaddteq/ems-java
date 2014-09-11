package com.mm.struts.student.action;

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
import com.mm.core.master.RootMaster;


public class ClassifiedDetailAction  extends Action
{

  
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
    	DataSource ds = getDataSource(request,"student");
    	Vector infoVec = new Vector();
    	HttpSession session= request.getSession();
    	String typ = (request.getParameter("typ")!=null) ? request.getParameter("typ") : "blog";
		int minVal1 = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		String StrCid= (request.getParameter("complainId")!=null) ? request.getParameter("complainId") : (String)session.getAttribute("complainid");
		String minVal = (request.getParameter("minVal")!=null) ? request.getParameter("minVal") : "0";
		//System.out.println("minVal....aaaaaaaaaaaaa..1..."+minVal);
		int complainid=Integer.parseInt(StrCid);
		request.setAttribute("complainid", StrCid);
		
		session.setAttribute("complainid",StrCid);
			
		
		int maxVal = 10;
	
		RootMaster rootMaster = new RootMaster();
        int totalRow = 200;
        String strPageHtml = getPages(minVal1, maxVal, totalRow);
        Vector<String> paramVec = new Vector<String>();
		paramVec.add(String.valueOf(minVal1));
		paramVec.add(String.valueOf(maxVal));	
		
		
		Vector detailVec = rootMaster.getComplaintDetails(ds, complainid);
		System.out.println("detailVec......2..."+detailVec);
		    
			int loginid=Integer.parseInt(detailVec.elementAt(11).toString()) ;
			////////System.out.println("loginid......3..."+loginid);
			Vector userVec = new Vector();
		    userVec=rootMaster.getUserInfo(ds, loginid);
		    ////////System.out.println("userVec....4...."+userVec);
	        String fname= userVec.elementAt(4).toString();
	        String lname= userVec.elementAt(5).toString();	
	        int cityid=Integer.parseInt(userVec.elementAt(6).toString()) ;
	        ////////System.out.println("cityid.....5..."+cityid);
	      //  String city=rootMaster.getPlaceName(ds, cityid);
	        ////////System.out.println("cityname....6...."+city);
	      Vector FacilityVec = new Vector();
	      FacilityVec= rootMaster.getfacilityINFO(ds,loginid);
	      String fac_name=FacilityVec.elementAt(2).toString();
	      String fac_location=FacilityVec.elementAt(3).toString();
	      int fac_city=Integer.parseInt(FacilityVec.elementAt(4).toString());
	      String city=rootMaster.getPlaceName(ds, fac_city);
	      
	        int complainId=Integer.parseInt(detailVec.elementAt(0).toString()) ;
	        
	        int comment=rootMaster.getcommentCount(ds, complainId);
	        
	        Vector commentVec = rootMaster.getCommentsList(ds, complainId);
	        //System.out.println("response....detailVec.elementAt(0).toString()...."+detailVec.elementAt(0).toString());
	        //System.out.println("response....detailVec.elementAt(0).toString()...."+detailVec);
	        ////////System.out.println("commentVec.size()....commentVec.size()...."+commentVec.size());
	        
//	    	for check length of Complaint title
			 String strText1 = "";  
			 String strFMsg1 = "";
			 
				String strMessage1 =  detailVec.elementAt(1).toString().trim();
				////////System.out.println("complaintMessage........."+strMessage.length());
				////////System.out.println("complaintMessage........."+strMessage);
				int numChar1 = (strMessage1.length() > 200) ? 200 : strMessage1.length();  
				for(int k=0; k<numChar1; k++)
				{ 
					
						   strFMsg1 = strFMsg1+strMessage1.charAt(k);
						   
						
				}
				if(strFMsg1.lastIndexOf(strFMsg1)!=-1)
				{
					 strText1= strFMsg1;
					////////System.out.println("...strFMsgTeam......"+strFMsg1.lastIndexOf(strFMsg1));
					
		         
		        }else{
		        	strText1= strFMsg1.substring(0, strFMsg1.lastIndexOf(" "));
		        	////////System.out.println("...strText......");
		        }	
	        
	        String strFMsg = "";
			String strMessage = detailVec.elementAt(2).toString().trim();
			int numChar = (strMessage.length() > 200) ? 200 : strMessage.length();  
			for(int k=0; k<numChar; k++)
			{ 
				int num = (int)strMessage.charAt(k);
					
					   strFMsg = strFMsg+strMessage.charAt(k);
					
			}
	        String comtext = strText1;
	        String strcat = "";
	        String cname ="";
	        String Ctype="";
	        System.out.println("...strText...advtid..."+detailVec.elementAt(18));
	        if(Integer.parseInt(detailVec.elementAt(18).toString())!=0){
			int compid=Integer.parseInt(detailVec.elementAt(18).toString()) ;
			
			Vector compVec = new Vector();
			compVec=rootMaster.getCompanyDetail(ds, compid);
	        
	         cname= compVec.elementAt(1).toString();
	         System.out.println("...strText...Ctype..."+compVec.elementAt(17).toString().trim());
	         Ctype= compVec.elementAt(17).toString().trim();
	         
	         int catid= Integer.parseInt( detailVec.elementAt(4).toString());
	        	        
	        }else{
	        		cname = detailVec.elementAt(27).toString().trim();
	        	 //////System.out.println("cname....cname...."+detailVec.elementAt(27).toString());
	        	 cname+="(Other)";
	        	
	 	     }
	        int catid= Integer.parseInt( detailVec.elementAt(4).toString());
	        //////System.out.println("catid....catid...."+detailVec.elementAt(4).toString());
	        Vector catvec=rootMaster.getClassifiedCategoryName(ds, catid);
	       
	        if(Integer.parseInt(catvec.elementAt(1).toString().trim())==0)
	        {
	        	strcat=catvec.elementAt(0).toString().trim();
	        	strcat+="(Other)";
	        	//////System.out.println("catmane....catmane...."+catvec.elementAt(0).toString().trim());
	        }
	        else
	        {
	        	strcat=catvec.elementAt(0).toString().trim();
	        }
	 	      
	        
	        
//	      get all company list with the no of responses accorrding to company type. rootmaster.getResponseList()
			Vector<String> respDataVec = new Vector<String>();
			respDataVec.add(detailVec.elementAt(0).toString());// complaint Id
			respDataVec.add("main");
			//System.out.println("respDataVec>>>"+respDataVec);
			Vector responseVec = rootMaster.getResponseList(getDataSource(request, "student"), respDataVec);
			
			int totleRsponse = responseVec.size();
			int indvResponse = 0;
			int advtResponse = 0;
			int entpResponse = 0;
			System.out.println("responseVec ist time on detail>>>"+responseVec);
			
			for(int i=0;i<responseVec.size();i++)
			{
				Vector tempVec =(Vector)responseVec.elementAt(i);
				String comp_type = tempVec.elementAt(5).toString().trim();
				if(comp_type.equalsIgnoreCase("Advertiser"))
				{
					advtResponse++;
					
				}
				else if(comp_type.equalsIgnoreCase("Enterprise"))
				{
					entpResponse++;
				}
				else if(comp_type.equalsIgnoreCase("Consumer"))
				{
					indvResponse++;
				}
			}
	    	//end of response getting code
			
	        
	        infoVec.add(fname);//0
	        infoVec.add(lname);//1
	        infoVec.add(city);//2
	        infoVec.add(comment);//3
	        infoVec.add(cname);//4
	        infoVec.add(strcat);//5
	      
	        infoVec.add(String.valueOf(totleRsponse));//6// total no of responses
	    	infoVec.add(String.valueOf(indvResponse));//7// Consumer responses
	    	infoVec.add(String.valueOf(advtResponse));//8// brand responses
	    	infoVec.add(String.valueOf(entpResponse));//9// Core responses 
	    	infoVec.add(Ctype);//10// Company TYPE 
	        infoVec.add(fac_name);//11
	        infoVec.add(fac_location);//12
	    	
	       
	    	System.out.println("infoVec>>>"+infoVec);
	    	////////System.out.println("comment>>>"+comment);
	    	int compid1=Integer.parseInt(detailVec.elementAt(18).toString()) ;	
	        
	    String strComplaintDetail = getComplaintsDetail( request, minVal1, strPageHtml, detailVec, infoVec, responseVec, commentVec, ds, loginid, compid1,minVal,typ);
	    //System.out.println("Classified Details>>>"+strComplaintDetail);
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        out.println(strComplaintDetail);
        out.flush();
         
		
			
		return  null;
		
        
    }

    private String getComplaintsDetail( HttpServletRequest request ,int minVal1, String strPageHtml, Vector detailVec,Vector infoVec ,Vector responseVec, Vector commentVec, DataSource ds, int loginid, int compid , String minVal,String typ)
	{   
    	String complainid = (request.getParameter("complainId")!=null) ? request.getParameter("complainId") : (String)request.getAttribute("complainid");
    	String strUserId =(request.getParameter("strUserId")!=null) ? request.getParameter("strUserId") : (String)request.getAttribute("strUserId");
    	
    	String Stype = (request.getParameter("Stype")!=null) ? request.getParameter("Stype") : (String)request.getAttribute("Stype");
    	request.setAttribute("Stype", Stype);
		String strvalue="";
		//bgcolor=\"#EDF3F8\"
		//bgcolor=\"#F3F3F5\"
		
		strvalue+="<div class=\"classified_DIV\">";	
		
		if(detailVec.size()!=0){
			
			
			//	for check length of Complaint title
				 String strText1 = "";  
				 String strFMsg1 = "";
				 
					String strMessage1 =  detailVec.elementAt(1).toString().trim();
					////////System.out.println("complaintMessage........."+strMessage.length());
					////////System.out.println("complaintMessage........."+strMessage);
					int numChar1 = (strMessage1.length() > 45) ? 45 : strMessage1.length();  
					for(int k=0; k<numChar1; k++)
					{ 
						
							   strFMsg1 = strFMsg1+strMessage1.charAt(k);
							   
							
					}
					if(strFMsg1.lastIndexOf(strFMsg1)!=-1)
					{
						 strText1= strFMsg1;
						////////System.out.println("...strFMsgTeam......"+strFMsg1.lastIndexOf(strFMsg1));
						
			         
			        }else{
			        	strText1= strFMsg1.substring(0, strFMsg1.lastIndexOf(" "));
			        	////////System.out.println("...strText......");
			        }	
					strvalue+="<div class=\"classified_Details\">";
					strvalue+="<div class=\"classified_DetailsLFT\">Blog Title:</div><div class=\"classified_DetailsRHT\">"+strText1+"</div>";
					strvalue+="<div class=\"classified_DetailsLFT\">Date/Time:</div><div class=\"classified_DetailsRHT\">"+detailVec.elementAt(6).toString().trim()+"/&nbsp;"+detailVec.elementAt(7).toString().trim()+"</div>";
					strvalue+="<div class=\"classified_DetailsLFT\">Resident:</div><div class=\"classified_DetailsRHT\">"+infoVec.elementAt(0).toString().trim()+"&nbsp;"+infoVec.elementAt(1).toString().trim()+"</div>";
					strvalue+="<div class=\"classified_DetailsLFT\">City:</div><div class=\"classified_DetailsRHT\">"+infoVec.elementAt(2).toString().trim()+"</div>";
					strvalue+="</div>";

					strvalue+="<div class=\"classified_Details\">";
					strvalue+="<div class=\"classified_DetailsLFT\">Building:</div><div class=\"classified_DetailsRHT\">"+infoVec.elementAt(11).toString().trim()+"</div>";
					strvalue+="<div class=\"classified_DetailsLFT\">Location:</div><div class=\"classified_DetailsRHT\">"+infoVec.elementAt(12).toString().trim()+"</div>";
					strvalue+="<div class=\"classified_DetailsLFT\">Category:</div><div class=\"classified_DetailsRHT\">"+infoVec.elementAt(5).toString().trim()+"</div>";
					strvalue+="<div class=\"classified_DetailsLFT\">No. of comments:</div><div class=\"classified_DetailsRHT\">"+infoVec.elementAt(3).toString().trim()+"</div>";
					strvalue+="</div>";

		String strFMsg = "";
		String strMessage = detailVec.elementAt(2).toString().trim();
		////////System.out.println(strMessage);
		int enterFlag = 0;
		for(int k=0; k<strMessage.length(); k++)
		{
			int num = (int)strMessage.charAt(k);
			if(num == 10)
			{
				enterFlag++;
				//////////System.out.println(k);
				if((int)strMessage.charAt(k)==10 && (int)strMessage.charAt(k+1) ==10)
				{
					strFMsg = strFMsg+"<br><br><br>"+strMessage.charAt(k);
					enterFlag=0;
				}
				else
				{
					strFMsg = strFMsg+"<br>"+strMessage.charAt(k);
					
				}
				
			}
			else
			{
				strFMsg = strFMsg+strMessage.charAt(k);
			}
		}
		
		strvalue+="<div class=\"classified_Details-p\">";		
		strvalue+="<p>"+strFMsg+"</p>";
	
		
		
		int z=0;
		String strResult="success";
		String result = "success";
		RootMaster rootMaster = new RootMaster();
		int respid=(request.getParameter("respid")!=null) ? Integer.parseInt(request.getParameter("respid")) : 0;
    	
		
		if(typ.equalsIgnoreCase("blog"))
		{
					if(responseVec.size()>0)
					{	
						//System.out.println("responseVec.size()>>>>>>>>>>>>>>"+responseVec.size());
					
						
						for(int i=0;i<responseVec.size();i++)
						{
							
							z=z+1;
							Vector tempVec =(Vector)responseVec.elementAt(i);
							//////////System.out.println("tempVec>>>>>>>>>>>>>>"+tempVec);
							//int responseId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
							String resptext = tempVec.elementAt(1).toString().trim();
							String respDate = tempVec.elementAt(2).toString().trim();
							String comp_type = tempVec.elementAt(5).toString().trim();
							String fname = tempVec.elementAt(6).toString().trim();
							String lname = tempVec.elementAt(7).toString().trim();
							int cityId = Integer.parseInt(tempVec.elementAt(8).toString().trim());
							String companyName = tempVec.elementAt(9).toString().trim();
							String cityName = rootMaster.getPlaceName(getDataSource(request, "main"),cityId);
							//////////System.out.println("cityName>>>>>>>>>>>>>>"+cityName);
							//int response_flag = Integer.parseInt(tempVec.elementAt(12).toString().trim());
							String strFMsg3 = "";
					        
							String strMessage3 = resptext;
							
							for(int k=0; k<strMessage3.length(); k++)
							{ 
								int num = (int)strMessage3.charAt(k);
								if(num == 10)
								{
									strFMsg3 = strFMsg3+"<br>"+strMessage3.charAt(k);
								}
								else
								{
									strFMsg3 = strFMsg3+strMessage3.charAt(k);
								}
							}
							String comptype ="";
							if(comp_type.equalsIgnoreCase("Corporates"))
							{
								comptype = "Facility Manager";
								
							}
							else if(comp_type.equalsIgnoreCase("Enterprise"))
							{
								comptype = "Airtel";
							}
							else if(comp_type.equalsIgnoreCase("Consumer"))
							{
								  comptype = "Resident";
							}else if(comp_type.equalsIgnoreCase("Advertiser"))
							{
								comptype = "Builder";
							}
							
							strvalue+="<tr><td colspan=\"4\" >"; 
							strvalue+="<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" >";
							strvalue+="<tr>";
							
							strvalue += "<div class=\"commentbox\" style=\"background-color:#EDF3F8\">";
							strvalue += "<p class=\"blogsTitle\">Response</p>";
							strvalue += "<p style=\"text-align:justify\">"+strFMsg3+"</p>";	
							strvalue += "<p class=\"blogsVisit\">City:&nbsp;"+cityName+"</p>";
							strvalue += "</div>";
						
							strvalue += "<div class=\"commentfooter\">Posted by &nbsp;";
								if(comptype.equalsIgnoreCase("Brand")){
			
									strvalue += companyName;
			 
							}else{
								strvalue +=fname+" "+lname;
					        }
					        strvalue += "&nbsp;on  "+respDate+"/"+tempVec.elementAt(4)+"  /<span class=\"student\" >"+comptype+"</span> </div>";
							strvalue+="</tr>";
							strvalue+="</table>";
							
							strvalue+="</td>";
							strvalue+="</tr>";
					}
						
					}
					else
					{
						strvalue+="<tr align=\"center\" id=\"noresponse\" style=\"display:inline\" >";
						strvalue+="<td colspan=\"4\"  class=\"required\"> No Response(s)...   </td>";
						strvalue+="</tr>";
					}
					
					////////System.out.println("commentVec>>>>>>in sied>>>>>>>>"+commentVec);
					if(commentVec.size()!=0){
						////////System.out.println("commentVec....commentVec...."+commentVec.size());
						
					strvalue+="<tr><td colspan=\"4\">"; 
					for(int j=0; j<commentVec.size(); j++){	
						Vector tempVec = (Vector)commentVec.elementAt(j);
						 ////////System.out.println("commentVec....commentVec...."+j);
						 
					       //strvalue += "<label  class=\"required\" > "+compdataVec.elementAt(4)+"/"+compdataVec.elementAt(5)+"</label>";
							//strvalue += "<BR>";
							
							
						int cityId = Integer.parseInt(tempVec.elementAt(8).toString().trim());
						
						String cityName = rootMaster.getPlaceName(getDataSource(request, "main"),cityId);
						
			
					
					strvalue+="<table width=\"100%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" >";
					strvalue+="<tr>";
					
					strvalue += "<div class=\"commentbox\" style=\"background-color:#F3F3F5\">";
					strvalue += "<p class=\"blogsTitle\">Comment</p>";
					strvalue += "<p style=\"text-align:justify\">"+tempVec.elementAt(0).toString().trim()+"</p>";	
					strvalue += "<p class=\"blogsVisit\">City:&nbsp;"+cityName+"</p>";
					strvalue += "</div>";
				
					strvalue += "<div class=\"commentfooter\">Posted by &nbsp;"+tempVec.elementAt(6)+"&nbsp;"+tempVec.elementAt(7)+" on "+tempVec.elementAt(1)+"/"+tempVec.elementAt(4)+" </div>";
					strvalue+="</tr>";
					strvalue+="</table>";
					}
					
					strvalue+="</td></tr>";
					}else{
					strvalue+="<tr  >";
					strvalue+="<td colspan=\"4\" align=\"center\" valign=\"middle\" class=\"required\">No Comments Available.</td>";
					strvalue+="</tr>";
					}
		}
		strvalue+="</table>";
		strvalue+="<br>";
		
		}else{
					
		strvalue+="<table width=\"100%\" >";
		strvalue+="<tr>";
		strvalue+="<td height=\"150\" align=\"center\"  class=\"required\"><h3>Complaint does not exist for given reference.</h3> </td>";
		strvalue+="</tr>";
		strvalue+="</table>";
		}
		strvalue+="<br></td>";
		strvalue+="</tr>";
		
		
		strvalue+="<tr align=\"center\"  >";
		strvalue+="<td   class=\"bold\" align=\"right\"> </td>";
		strvalue+="</tr>";
		
		System.out.println("StypeStype>>>>>>>>>>....................."+Stype);
		
		if(Stype.equalsIgnoreCase("first")||(Stype.length()==0))
		{
			//System.out.println("StypeStype>>>>>in condition>>>>>"+Stype);
			
			strvalue+="<tr align=\"center\" >";
			strvalue+="<td ><table width=\"100%\" border=\"0\" align=\"center\">";
			strvalue+="<tr align=\"center\" id=\"login1\" style=\"display:inline\">";
			if(typ.equalsIgnoreCase("blog")){
			
			strvalue+="<td><img  src=\"images/back1.gif\" height=\"19\" width=\"53\" border=\"0\" style=\"cursor:hand\" onclick=\" back()\"align=\"absbottom\" />&nbsp;&nbsp;&nbsp;&nbsp;<img src=\"images/giveresponse.gif\"  onclick=\"show1()\" style=\"cursor:hand\" name=\"image1\" width=\"63\" height=\"19\" border=\"0\" align=\"absbottom\" onMouseOver=\"MM_showMenu(window.mm_menu_1228163906_0,0,17,null,'image1')\" onMouseOut=\"MM_startTimeout();\"/>&nbsp;&nbsp;&nbsp;&nbsp;<A href=\"showblogs.jsp?numMin="+minVal+"\" style=\"cursor:hand\" ><img  src=\"images/blogslist.gif\" height=\"19\" width=\"150\" border=\"0\" style=\"cursor:hand\" align=\"absbottom\" /></A></td>";
			}else
			{
				strvalue+="<td><img  src=\"images/back1.gif\" height=\"19\" width=\"53\" border=\"0\" style=\"cursor:hand\" onclick=\" back()\"align=\"absbottom\" /></td>";
			}
			strvalue+="</tr>";
			
			strvalue+="<tr align=\"center\" id=\"login\" style=\"display:none\">";
			strvalue+="<td>" ;
			//////////////////////////for login///////////////////////////////////////////
			
		
	                strvalue+="<TABLE cellSpacing=3 cellPadding=0 width=\"80%\"  align=center border=0>";
	                    strvalue+="<TBODY>";
	                        
	                              
	                        strvalue+="<TR align=\"center\">";
	                        strvalue+="<TD colSpan=2 class=\"bold\">";
	                        
	                        strvalue+="</TD>";
	                        strvalue+="</TR>";
	                        strvalue+="<TR>";
	                        strvalue+="<TD colSpan=2  align=\"center\" class=\"required\">Note : You should be a registered user(i.e student, college or company) to post your reply.</TD></TR>";
	                        strvalue+="<TR>";
	                        strvalue+="<TD colSpan=2>&nbsp;</TD></TR>";
	                        strvalue+="<TR>";
	                        strvalue+="<TD width=\"40%\" align=right class=\"required\">Login-Id";
	                        strvalue+="</TD>";
	                        strvalue+="<TD width=\"60%\" align=\"left\"><input type=\"text\" name=\"loginId\" ></TD></TR>";
	                        strvalue+="<TR>";
	                        strvalue+="<TD align=right class=\"required\">Password";
	                        strvalue+="</TD>";
	                        strvalue+="<TD align=\"left\"><input type=\"password\" name=\"pasword\"/></TD></TR>";
	                        
	                        strvalue+="<TR>";
	                        strvalue+="<TD width=\"40%\" align=right>";
	                        strvalue+="</TD>";
	                        strvalue+="<TD width=\"60%\"><input type=\"hidden\" name=\"complaintId\" value=\""+complainid+"\"></html:hidden></TD></TR>";
	                        
	                        strvalue+="<TR>";
	                		
	                        //strvalue+="<TD colSpan=2> <DIV align=center> <input type=\"submit\"  property=\"method\" class=\"buttonCopy\"  ><bean:message key=\"preview.loginsubmit\"/>" +
	                        		//"&nbsp;&nbsp;<input name=\"cancel\" type=\"button\" class=\"buttonCopy\"  value=\"Cancel\" onClick=\"hideAddNew(this.id)\"></DIV></TD>";
							strvalue+="</TR>";
	                        strvalue+="<TR>";
	                        strvalue+="<TD colSpan=2>&nbsp;</TD>";
							strvalue+="</TR>";
	                        strvalue+="<TR>";
	                        strvalue+="<TD class=\"required\" align=\"right\" colspan=\"2\">";
	                        strvalue+="<A   href=\"forgotpassword.jsp\">Forgot Password?</A> ";
	                        strvalue+="</TD>";
							strvalue+="</TR>";
	                        strvalue+="<TD ><br><TD  align=right>&nbsp; </TD>";
	                strvalue+="</TBODY>";
			strvalue+="</TABLE>";

			//////////////////////////for login///////////////////////////////////////////
			
			strvalue+="</td>";
			strvalue+="</tr>";
			strvalue+="</table></td>";
			strvalue+="</tr>";
			
		}
		
		else if(Stype.equalsIgnoreCase("login"))
		{	
			
			strvalue+="<div class=\"classified_Details-LINKS\">";
			strvalue+="<tr align=\"center\" >";
			strvalue+="<td ><table width=\"100%\" border=\"0\" align=\"center\">";
			strvalue+="<tr align=\"center\" id=\"login1\" style=\"display:inline\">";
				strvalue+="<a href=\"#\" style=\"cursor:hand\" onclick=\"javascript:back()\">Back</a>";
			//	strvalue+="<a href=\"#\" style=\"cursor:hand\" onclick=\"javascript:show1()\">Post Reply</a>";
			//	strvalue+="<A href=\"showblogs.jsp?numMin="+minVal+"\" style=\"cursor:hand\">Back to Blog(s) List</a>";

					
			strvalue+="<tr align=\"center\" >";
			strvalue+="<td ><table><tr><td align=\"top\"><A href=\"complaintsDetail.jsp?stype=writetext\"><img src=\"images/giveresponse.gif\"  style=\"cursor:hand\" width=\"63\" height=\"19\" border=\"0\" align=\"absbottom\"/><A> &nbsp;&nbsp;&nbsp;&nbsp;<A href=\"showblogs.jsp?numMin="+minVal+"\" style=\"cursor:hand\" ><img  src=\"images/blogslist.gif\" height=\"19\" width=\"150\" border=\"0\" style=\"cursor:hand\" align=\"absbottom\" /></A></td>";
			strvalue+="</tr>";
			strvalue+="</table>";
			strvalue+="</td>";
			strvalue+="</tr>";
			
		}
		else
		{	
			HttpSession session= request.getSession();
			 strUserId = (request.getParameter("strUserId")!=null) ? request.getParameter("strUserId") : (String)session.getAttribute("strUserId");
			
			 //////System.out.println("strUserId. in complaint Action.strUserId....2..."+strUserId);
		
			strvalue+="<tr align=\"center\" >";
			strvalue+="<td ><input type=\"hidden\" name=\"complaintId\" value=\""+complainid+"\"></html:hidden>" +
					"<input type=\"hidden\" name=\"userId\" value=\""+strUserId+"\"></html:hidden><table><tr><td valign=\"top\" align=\"left\" width=\"18%\"><span ><strong>Comment Text:</strong></span></td><td valign=\"top\" align=\"left\"><span ><textarea  name=\"comtext\" cols=\"60\" rows=\"7\"  ></textarea></span></td></td>";
			strvalue+="</tr>";
			strvalue+="</table>";
			strvalue+="</td>";
			strvalue+="</tr>";
			
		}
		
		strvalue+="</table></td>";
		strvalue+="</tr>";
		strvalue+="</table></td>";
		strvalue+="</tr>";
		strvalue+="</table></td>";
		strvalue+="</tr>";
		strvalue+="</table>";
		
        return strvalue;
    }

    

private String getPages(int minVal1, int maxVal, int numSize)
{
	String strResult ="<select name=\"paging\" onchange=\"retrieveComplaintsDetailURL('comlpaintsDetail.do?'+this.value);\">";
	int numMin = 0;
	int numPage = 1;
	for(int i=0; i<numSize; i=i+maxVal)
	{
		numMin = i;
		
		if(minVal1 == numMin)
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