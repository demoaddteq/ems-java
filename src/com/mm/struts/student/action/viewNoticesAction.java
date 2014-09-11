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

public class viewNoticesAction  extends Action{
	

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session =request.getSession();
		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
	    String creationDate = sttotal.nextToken();
	    String dateToday[] = creationDate.split("-");
	    String YR =dateToday[0];	
	    String DaY = dateToday[2];
	    String MTH =dateToday[1];

		
		String userid= (request.getParameter("uId") != null) ? request.getParameter("uId") : (String)session.getAttribute("uId");
		String FacilityID= (request.getParameter("fId") != null) ? request.getParameter("fId") : (String)session.getAttribute("fId");
		session.setAttribute("uId",userid);
		
		
		String pageid= ( (String)session.getAttribute("pageid")!=null) ? (String)session.getAttribute("pageid") : "";
		
		String lowerSubTag= (request.getParameter("lowerSubTag") != null) ? request.getParameter("lowerSubTag") : "2";
		request.setAttribute("pageid",pageid); 
		
		String companyid= (request.getParameter("compId") != null) ? request.getParameter("compId") : (String)session.getAttribute("compId");
		session.setAttribute("compId",companyid);
	
		String month= (request.getParameter("Month") != null) ? request.getParameter("Month") : MTH;
		String nYear= (request.getParameter("Year") != null) ? request.getParameter("Year") : YR;	
		
		System.out.println("Month and Year are "+month+" "+nYear);
	
		IndvMaster indvMaster= new IndvMaster();		
		RootMaster rootMaster = new RootMaster();
		
		Vector<String> dataVec = new Vector<String>();
		dataVec.add(userid);
		dataVec.add(FacilityID);	
		System.out.println("Datavector is "+dataVec);
			
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 10;
		//////System.out.println("countVec.............InboxAction......minVal..... "+minVal);
		//////System.out.println("countVec.............InboxAction......maxVal..... "+maxVal);
	
		Vector<String> paramVec1 = new Vector<String>();
		paramVec1.add(userid);
		paramVec1.add(FacilityID);	
		paramVec1.add(month);
		paramVec1.add(nYear);
		
		System.out.println("paravector is "+paramVec1);
		
		int totalRow = indvMaster.getNoticesCount(getDataSource(request, "student"), paramVec1);
		System.out.println("countVec.............InboxAction......totalRow..... "+totalRow);
		//int totalRow=200;
		String strPageHtml = getPages(minVal, maxVal, totalRow,userid,companyid,numCount, FacilityID, pageid);
		Vector<String> paramVec = new Vector<String>();
        paramVec.add(userid);	
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));		
		paramVec.add(FacilityID);
		paramVec.add(month);
		paramVec.add(nYear);		
		
		Vector facilityDetails = rootMaster.getResfacID(getDataSource(request, "student"),userid);
		System.out.println("Facility Vector is "+facilityDetails);
		Vector userVec = indvMaster.viewNoticesList(getDataSource(request, "student"), paramVec);
		//System.out.println("userVec.............InboxAction......userVec..... "+userVec);
		/*Vector<Vector> comVec = new Vector<Vector>();
		Vector<String> comVec1 = new Vector<String>();
		Vector<String> comVec2 = new Vector<String>();		
		comVec1.add("1");
		comVec1.add("22");
		comVec1.add("For over billing");
		comVec1.add("12-12-07");		
		comVec2.add("2");
		comVec2.add("23");
		comVec2.add("For over billing");
		comVec2.add("12-12-07");		
		comVec.add(comVec1);
		comVec.add(comVec2);*/
		
		String strComplaints = getUserList(request, minVal, strPageHtml, userVec, remander,pageid,FacilityID, maxVal, totalRow, facilityDetails);
		//////////System.out.println("Complaints" +strComplaints);
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
	private String getUserList( HttpServletRequest request,int minVal, String strPageHtml, Vector comVec, int remander,String pageid,String qtype,int maxVal, int totalRow, Vector FVector)
	{
		
		String lowerSubTag= (request.getParameter("lowerSubTag") != null) ? request.getParameter("lowerSubTag") : "2";
		
		System.out.println("Facility Vector is "+FVector);
		String strValue = "<div class=\"top_panel\">";
		strValue += "<div class=\"Page_Heading\">";	
		strValue += "<div class=\"Page_Heading_Text\" style=\"width: 70%;\">List of Notices by Facility Manager (" + FVector.elementAt(1).toString().trim()+" "+ FVector.elementAt(2).toString().trim()+")";
		strValue +=	"</div>";
	
		if(comVec.size() != 0)
		{
		strValue += "<div style=\"float: left;\">";
		strValue += "<a style=\"font-size: 10px; font-weight: bold; color: rgb(0, 0, 0);\">Total matches Found: "+comVec.size()+"</a></div>";
		strValue += "</div>";}
		
		
		if(comVec.size()==0)
		{
			strValue += "</div>";
			strValue += "<div class=\"Fields_Container\">";	
			strValue += "<div class=\"Fields_Container_Inside\">";			
			strValue +="<div class=\"Fields_Row_2_head\">";
			strValue +="<div class=\"Fields_Col_1a\">No Notices Found.";
			strValue += "</div></div></div></div>";
			
		}
		else
		{
								
			if (totalRow > maxVal){
				strValue +="<div class=\"Fields_Col_1a\">Page Number  "+strPageHtml+"\"div>";				    
				}
			 	
					strValue += "<div class=\"Fields_Container\">";	
					strValue += "<div class=\"Fields_Container_Inside\">";
					
			for(int i=0; i<comVec.size(); i++)
			{
				  
				  int j = i+1;
			    	int numTotal = j%2;
			    	//String strColor = (numTotal==0) ? "#ff722b" : "#ffffff";
			    	String strColor = (numTotal==0) ? "#FFFFFF" : "#FFFFFF";
			    	String strImg = (numTotal==0) ? "detail_white.gif" : "detail_white.gif";
			    	////////////System.out.println("strColor "+strColor);
			    	
				 Vector tempVec = (Vector)comVec.elementAt(i);
				 
				
				 int numCid = Integer.parseInt(tempVec.elementAt(0).toString().trim());
				 
				 String strNTitle = tempVec.elementAt(1).toString().trim();
			     String strCTitle = tempVec.elementAt(2).toString().trim();
			     String strCDate = tempVec.elementAt(3).toString().trim();
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
					strValue += "<div class=\"Fields_Row_2_head\">";
					strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 65%;\">Subject: "+strNTitle+"</div>";
					strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 25%;\">   Dated:"+Date[2]+"-"+Date[1]+"-"+Date[0]+"</div>";
					strValue += "</div>";
						strValue += "<div class=\"Fields_Row_1\">";
						
				        strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 90%;\">"+strMessage+"</div>";								
						strValue += "</div>";
													
			}
			strValue += "</div></div>";
			if (totalRow > maxVal){
				strValue += "<div class=\"Fields_Row_1\">";
				strValue += "<div class=\"Row_2_Fields_Col_2\" align=\"right\"  style=\"width: 30%;\">Page Number  "+strPageHtml+"</div></div>";
					
		}
		}
		if(remander==0)
		{
			strValue += "<div class=\"Fields_Row_1\">";
			strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"display:none\" align=\"center\"></div></div>";
		}
		strValue += "</div>";
		
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
