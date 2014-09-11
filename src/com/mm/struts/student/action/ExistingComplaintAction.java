
package com.mm.struts.student.action; 
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
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

public class ExistingComplaintAction  extends Action{
	

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session =request.getSession();
		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		//String userid=(String)session.getAttribute("uId");
		//String companyid=(String)session.getAttribute("compId");			
		
		String userid= (request.getParameter("uId") != null) ? request.getParameter("uId") : (String)session.getAttribute("uId");
		String qtype= (request.getParameter("qtype") != null) ? request.getParameter("qtype") : "Personal";
		session.setAttribute("uId",userid);		
		
		String pageid= ( (String)session.getAttribute("pageid")!=null) ? (String)session.getAttribute("pageid") : "";
		
		String lowerSubTag= (request.getParameter("lowerSubTag") != null) ? request.getParameter("lowerSubTag") : "2";
		
		System.out.println("pageid......on exis comp.........." +pageid);
		System.out.println("qtype......on exis comp..........." +qtype);
		System.out.println("lowerSubTag......on exis comp.........." +lowerSubTag);
		request.setAttribute("pageid",pageid); 
		//////System.out.println("countVec.............numCount........... "+numCount);		
		String companyid= (request.getParameter("compId") != null) ? request.getParameter("compId") : (String)session.getAttribute("compId");
		session.setAttribute("compId",companyid);
		System.out.println("company User ID are "+userid+" "+companyid);
		
		
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
	    String creationDate = sttotal.nextToken();
	    String dateToday[] = creationDate.split("-");
	    String YR =dateToday[0];	
	    String DaY = dateToday[2];
	    String MTH =dateToday[1];

		String month= (request.getParameter("Month") != null) ? request.getParameter("Month") : MTH;
		String nYear= (request.getParameter("Year") != null) ? request.getParameter("Year") : YR;	
		
		System.out.println("Month and Year are "+month+" "+nYear);
		
		IndvMaster indvMaster= new IndvMaster();		
		Vector<String> dataVec = new Vector<String>();
		dataVec.add(userid);
		dataVec.add(companyid);		
		dataVec.add(qtype);
		dataVec.add(month);	
		dataVec.add(nYear);	
		System.out.println("Datavector is "+dataVec);
			
		int CompCount = indvMaster.ComplaintCountAll(getDataSource(request, "student"), dataVec);	
		System.out.println("Count is "+CompCount);
	//	session.setAttribute("countVec", countVec);								
	//	System.out.println("blogs count" +countVec);					
		DataSource dataSource = getDataSource(request, "student");
		
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "creation_date";
		String strSOrder = "";
		String strComplaintFId = "asc";
		String strComplaintTitle = "asc";
		String strComplaintDate = "asc";
		if(request.getParameter("sby")!=null)
		{
			if(request.getParameter("cidorder")!=null)
			{
				if(request.getParameter("cidorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strComplaintFId = "desc";
				}
				else if (request.getParameter("cidorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strComplaintFId = "asc";
				}
			}
			
			if(request.getParameter("ctorder")!=null)
			{
				if(request.getParameter("ctorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strComplaintTitle = "desc";
				}
				else if (request.getParameter("ctorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strComplaintTitle = "asc";
				}
			}
			if(request.getParameter("cdorder")!=null)
			{
				if(request.getParameter("cdorder").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strComplaintDate = "desc";
				}
				else if (request.getParameter("cdorder").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strComplaintDate = "asc";
				}
			}
			
			
			
		}
		String strFShortBy = strShortBy+" "+strSOrder;
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 20;
		
		System.out.println("countVec.............InboxAction......minVal..... "+minVal);
		System.out.println("countVec.............InboxAction......maxVal..... "+maxVal);
	
		Vector<String> paramVec1 = new Vector<String>();
		paramVec1.add(userid);
		paramVec1.add(companyid);
		paramVec1.add(qtype);	
		paramVec1.add(month);	
		paramVec1.add(nYear);	
		
		System.out.println("Vecotr is "+paramVec1);
		int totalRow = indvMaster.ComplaintCountAll(getDataSource(request, "student"), paramVec1);
		System.out.println("countVec.............InboxAction......totalRow..... "+totalRow);
		//int totalRow=200;
		String strPageHtml = getPages(minVal, maxVal, totalRow,userid,companyid,numCount, qtype, pageid);
		Vector<String> paramVec = new Vector<String>();
        paramVec.add(userid);
		paramVec.add(companyid);		
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));		
		paramVec.add(strFShortBy);
		paramVec.add(qtype);
		paramVec.add(month);
		paramVec.add(nYear);
		
		
		Vector userVec = indvMaster.getComplaintListAll(getDataSource(request, "student"), paramVec);
		System.out.println("userVec.............InboxAction......userVec..... "+userVec);
		
		
		String strComplaints = getUserList(  request, strComplaintFId, strComplaintTitle, strComplaintDate, minVal, strPageHtml, userVec, remander,pageid,qtype, maxVal, totalRow,dataSource);
	//	System.out.println("Complaints" +strComplaints);
		/**
	     * setContentType - text/html to write String on page.
	     * PrintWriter - This line use to get writer to write on page.
	     * out.println - use to write string. 
	     * 
	     */
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    //out.println(strComplaints);
	    out.write(strComplaints);
	    out.flush();
	    out.close();
		return null;
	}
	private String getUserList( HttpServletRequest request,String strComplaintFId, String strComplaintTitle,  String strComplaintDate,int minVal, String strPageHtml, Vector comVec, int remander,String pageid,String qtype,int maxVal, int totalRow,DataSource dataSource)
	{
		RootMaster rootMaster = new RootMaster();
		
		String lowerSubTag= (request.getParameter("lowerSubTag") != null) ? request.getParameter("lowerSubTag") : "2";
	
				
		String strValue = "<div class=\"top_panel\">";
		strValue += "<div class=\"Page_Heading\">";	
		strValue += "<div class=\"Page_Heading_Text\" style=\"width: 70%;\">List of Complaints</div>";
		if(totalRow != 0)
		{
		strValue += "<div style=\"float: left;\">";
		strValue += "<a style=\"font-size: 10px; font-weight: bold; color: rgb(0, 0, 0);\">Total matches Found: "+totalRow+"</a></div>";
		}
		strValue += "</div>";

		if(totalRow==0)
		{

			strValue += "<div class=\"Fields_Container\">";	
			strValue += "<div class=\"Fields_Container_Inside\">";
			strValue += "<div class=\"Fields_Row_2a\">";
			strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 90%;\" text-align: center\"> No Complaints found...</div>";
			strValue += "</div></div></div>";
			
		}
		else
		{
			strValue += "<div class=\"Fields_Container\">";	
			strValue += "<div class=\"Fields_Container_Inside\">";
			strValue += "<div class=\"Fields_Row_2_head\">";
			strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 10%;\">S.No.</div>";
			strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 20%;\">Description</div>";
			strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 15%; text-align: right;\">Category</div>";
			strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 10%; text-align: right;\">Start Date</div>";
			strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 10%; text-align: right;\">End Date</div>";
			strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 15%; text-align: right;\">Status</div>";
			strValue += "</div>";
				    
		
			for(int i=0; i<comVec.size(); i++)
			{
				  
				  int j = i+1;
			    	int numTotal = j%2;
			    	//String strColor = (numTotal==0) ? "#ff722b" : "#ffffff";
			    	String strColor = (numTotal==0) ? "#FFFFFF" : "#FFFFFF";
			    	String strImg = "add.png";
			    	////////////System.out.println("strColor "+strColor);
			    	
				 Vector tempVec = (Vector)comVec.elementAt(i);
				 
				
				 int numCid = Integer.parseInt(tempVec.elementAt(0).toString().trim());
				 String statusTAG = "NA";
				 String strCFID = tempVec.elementAt(1).toString().trim();
			     String strCTitle = tempVec.elementAt(2).toString().trim();
			     String strCDate = tempVec.elementAt(3).toString().trim();
			     String categoryname = tempVec.elementAt(12).toString().trim();
			     int tagId = Integer.parseInt(tempVec.elementAt(13).toString().trim());
			     int staffId = Integer.parseInt(tempVec.elementAt(14).toString().trim());
			     statusTAG = tempVec.elementAt(11).toString().trim();
			     String resolve_date = tempVec.elementAt(10).toString().trim();
			     		
			     strColor="#404040";
				 if (tagId==1){ strColor="#990099"; }
				 if (tagId==2){ strColor="#999900"; }
				 if (tagId==3){ strColor="#00CC00"; }
				 if (tagId==4){ strColor="#B00000"; }
			
				 String Staff="NA";
			 if (staffId != 0){
				 Vector StaffVec=rootMaster.getStaffName(dataSource,staffId);
				 Staff = StaffVec.elementAt(0).toString().trim();}
								
			     String Date[]=strCDate.split("-");
			     
//			 	for check length of Complaint text
				 String strText = "";  
				 String strFMsg = "";
				 
					String strMessage =  tempVec.elementAt(2).toString().trim();
					////////////System.out.println("complaintMessage........."+strMessage.length());
					////////////System.out.println("complaintMessage........."+strMessage);
					int numChar = (strMessage.length() > 45) ? 45 : strMessage.length();  
					for(int k=0; k<numChar; k++)
					{ 
						
							   strFMsg = strFMsg+strMessage.charAt(k);
							   
							
					}
					if(strFMsg.lastIndexOf(strFMsg)!=-1)
					{
						 strText= strFMsg;
						//////////System.out.println("...strFMsgTeam......"+strFMsg.lastIndexOf(strFMsg));
						
			         
			        }else{
			        	strText= strFMsg.substring(0, strFMsg.lastIndexOf(" "));
			        	////////System.out.println("...strText......");
			        }	
					strValue += "<div class=\"Fields_Row_2a\">";
					strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 10%; font-weight: normal;\">"+j+"</div>";
					strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 20%; font-weight: normal;\">"+strText+"</div>";
					strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 15%; font-weight: normal; text-align: right;\">"+categoryname+"</div>";
					strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 10%; font-weight: normal; text-align: right;\">"+strCDate+"</div>";
					strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 10%; font-weight: normal; text-align: right;\">"+resolve_date+"</div>";
					strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 15%; font-weight: bold; color: "+strColor+"; text-align: right;\">"+statusTAG+"</div>";
//				    strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 8%;\"  onclick=\"popup('popUpDiv')\"><a href=\"compdetres.jsp?numCid="+numCid+"&pageid1="+pageid+"&lowerSubTag="+lowerSubTag+"\"><img alt=\"Click here to see detail\" src=\"../images/"+strImg.trim()+"\" width=\"16\" height=\"16\" border=\"0\"></img></a></div>";	
					strValue += "<div class=\"Row_2_Fields_head_2\"><img id=\"imgplus"+i+"\" style=\"cursor: pointer\" src=\"../images/add.png\" onclick=\"fnShow('imgplus"+i+"', 'compdet"+i+"', 'imgminus"+i+"');\"><img id=\"imgminus"+i+"\" style=\"display: none; cursor: pointer\" src=\"../images/add.png\" onclick=\"fnHide('imgplus"+i+"', 'compdet"+i+"', 'imgminus"+i+"');\"></div>";					
					strValue += "</div>";
					strValue += "<div class=\"Fields_Row_2a\">";
					strValue +="<span id=\"compdet"+i+"\" style=\"display: none;\">";
					strValue += "<div class=\"top_panel-2\"  style =\"width:600px;\">";
					strValue += "<div class=\"Page_Heading-2\">";	
					strValue += "<div class=\"Page_Heading_Text\" style=\"width: 70%;\">Details</div></div>";
					
					strValue += "<div class=\"Fields_Container-2\"  style =\"width:600px;\">";	
					strValue += "<div class=\"Fields_Container_Inside\">";
					strValue += "<div class=\"Fields_Row_1\">";
					strValue += "<div style=\"float: left; width: 25%; text-align: left; margin-left: 25px; display: inline; font-weight: bold;\">Complaint Title:</div>";
					strValue += "<div style=\"float: left; width: 60%; text-align: left; margin-left: 25px; display: inline; font-weight: normal;\">"+strText+"</div>";
					strValue += "</div>";
					
					strValue += "<div class=\"Fields_Row_1\">";
					strValue += "<div style=\"float: left; width: 25%; text-align: left; margin-left: 25px; display: inline; font-weight: bold;\">To:</div>";
					strValue += "<div style=\"float: left; width: 60%; text-align: left; margin-left: 25px; display: inline; font-weight: normal;\">Facility Manager</div>";
					strValue += "</div>";

					strValue += "<div class=\"Fields_Row_1\">";
					strValue += "<div style=\"float: left; width: 15%; text-align: left; margin-left: 25px; display: inline; font-weight: bold;\">Category:</div>";
					strValue += "<div style=\"float: left; width: 20%; text-align: left; margin-left: 25px; display: inline; font-weight: normal;\">"+categoryname+"</div>";
					strValue += "<div style=\"float: left; width: 20%; text-align: left; margin-left: 25px; display: inline; font-weight: bold;\">Complaint ID:</div>";
					strValue += "<div style=\"float: left; width: 20%; text-align: left; margin-left: 25px; display: inline; font-weight: normal;\">"+strCFID+"</div>";		
					strValue += "</div>";

					strValue += "<div class=\"Fields_Row_1\">";
					strValue += "<div style=\"float: left; width: 15%; text-align: left; margin-left: 25px; display: inline; font-weight: bold;\">Filed on:</div>";
					strValue += "<div style=\"float: left; width: 25%; text-align: left; margin-left: 25px; display: inline; font-weight: normal;\">"+strCDate+"</div>";
					strValue += "<div style=\"float: left; width: 15%; text-align: left; margin-left: 25px; display: inline; font-weight: bold;\">Status:</div>";
					strValue += "<div style=\"float: left; width: 25%; text-align: left; margin-left: 25px; display: inline; font-weight: normal;\">"+statusTAG+"</div>";				
					strValue += "</div>";

					strValue += "<div class=\"Fields_Row_1\">";
					strValue += "<div style=\"float: left; width: 25%; text-align: left; margin-left: 25px; display: inline; font-weight: bold;\">Complaint Details:</div>";
					strValue += "<div style=\"float: left; width: 60%; text-align: left; margin-left: 25px; display: inline; font-weight: normal;\"><p>"+strCTitle+"</p></div>";
					strValue += "</div>";

					strValue += "<div class=\"Fields_Row_1\">";
					strValue += "<div style=\"float: left; width: 25%; text-align: left; margin-left: 25px; display: inline; font-weight: bold;\">Assigned To:</div>";
					strValue += "<div style=\"float: left; width: 60%; text-align: left; margin-left: 25px; display: inline; font-weight: normal;\">"+Staff+"</div>";
					strValue += "</div>";

					strValue += "</div></div>";

					strValue += "</div></span></div>";
//				    	strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 70%;\"><a href=\"complaintDetail.jsp?pageid="+pageid+"&lowerSubTag="+lowerSubTag+"\">"+strText+"</a>";
	//			        strValue += "....<a href=\"compdetres.jsp?numCid="+numCid+"&pageid1="+pageid+"&lowerSubTag="+lowerSubTag+"\"><img src=\"images/read_more.jpg\" width=\"74\" height=\"10\" border=\"0\" /></a></p></div>";
						
					//	strValue += "</div>";
					
					
			}
					if (totalRow > maxVal){
						strValue += "<div class=\"Fields_Row_2a\">";
						strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 20%;\" text-align: right;\">Page Number  "+strPageHtml+"</div>";
						strValue += "</div>";
					}
		}
		if(remander==0)
		{
			strValue += "<div class=\"Fields_Row_2a\">";
			strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 20%;\" text-align: right;\"></div>";
			strValue += "</div>";	
		}
		strValue += "</div></div></div>";
		
		return strValue;
	}
	
	private String getPages(int minVal, int maxVal, int numSize ,String uId,String compId,String numCount,String qtype,String pageid)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveIndvEComplistURL('existingComplaint.do?uId="+uId+"&compId="+compId+"&numCount="+numCount+"&pageid1="+pageid+"&qtype="+qtype+"&'+this.value);\">";
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
