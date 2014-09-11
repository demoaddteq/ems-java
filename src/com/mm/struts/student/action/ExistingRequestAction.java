
package com.mm.struts.student.action; 
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.core.master.*;

public class ExistingRequestAction  extends Action{
	

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session =request.getSession();
		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		
		String pageid = (request.getParameter("pageid")!=null) ? request.getParameter("pageid").trim() :(String)session.getAttribute("pageid");
		String lowerSubTag = (request.getParameter("lowerSubTag")!=null) ? request.getParameter("lowerSubTag").trim() :(String)session.getAttribute("lowerSubTag");
	
		String userid= (request.getParameter("uId") != null) ? request.getParameter("uId") : (String)session.getAttribute("uId");
		String qtype= (request.getParameter("qtype") != null) ? request.getParameter("qtype") : "Personal";
		session.setAttribute("uId",userid);
		
		String facId= (request.getParameter("fId") != null) ? request.getParameter("fId") : (String)session.getAttribute("fId");
		session.setAttribute("fId",facId);
		
		String companyid= (request.getParameter("compId") != null) ? request.getParameter("compId") : (String)session.getAttribute("compId");
		session.setAttribute("compId",companyid);
		System.out.println("pageid......on exis Req.........." +pageid);
		System.out.println("user ID......on exis Req..........." +userid);
		System.out.println("facility Id........." +facId);
		
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
		Vector dataVec = new Vector();		
		dataVec.add(userid);
		dataVec.add(companyid);		
		dataVec.add(qtype);	
			
		Vector countVec = indvMaster.complaintCount(getDataSource(request, "student"), dataVec);		
		session.setAttribute("countVec", countVec);						
		//////System.out.println("countVec.............InboxAction........... "+countVec);
		
		
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
		String status="2";
		
		Vector<String> paramVec = new Vector<String>();
		paramVec.add(userid);
		paramVec.add(companyid);
		paramVec.add(status);	
		System.out.println("here in request action 9");
		String statusP="1";
		
		Vector<String> paramVecP = new Vector<String>();
		paramVecP.add(userid);
		paramVecP.add(companyid);
		paramVecP.add(statusP);	
		
		Vector userVec = indvMaster.getServiceList(getDataSource(request, "student"), paramVec,month,nYear);
		System.out.println("here in request action 0"+userVec);
		Vector userVec1 = indvMaster.getServicePendingList(getDataSource(request, "student"), paramVecP,month,nYear);
		System.out.println("Complaints" +userVec1);
		
		/**
	     * setContentType - text/html to write String on page.
	     * PrintWriter - This line use to get writer to write on page.
	     * out.println - use to write string. 
	     * 
	     */
		
		String strComplaints = getUserList(userVec, userVec1);
		
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strComplaints);
	    out.flush();
	    out.close();
		return null;
	}

	private String getUserList(Vector comVec, Vector comVec1)
	{
		String strValue = "";
		strValue += "<div class=\"top_panel\">";
		if((comVec.size()==0) && (comVec1.size()==0))
		{
			strValue += "<div class=\"Fields_Row_2a\">";
			strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 20%;\" text-align: right;\" No Data found...</div>";
			strValue +="<div style=\"float: left;\">";
			strValue += "<a style=\"text-decoration: underline; font-size: 10px; font-weight: bold; color: rgb(0, 0, 0);\" href=\"writeMentorRequest.jsp\">Request for Add/Delete Service</a></div></div>";
		
			strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 20%;\" text-align: right;\" </div></div>";
					
		}
		else
		{
			if (comVec.size()!=0){
				strValue += "<div class=\"Page_Heading\">";	
				strValue += "<div class=\"Page_Heading_Text\" style=\"width: 70%;\">List of services subcribed by you</div>";
				strValue +="<div style=\"float: left;\">";
				strValue += "<a style=\"text-decoration: underline; font-size: 10px; font-weight: bold; color: rgb(0, 0, 0);\" href=\"writeMentorRequest.jsp\">Request for Add/Delete Service</a></div></div>";
				
			strValue += "<div class=\"Fields_Container\">";	
			strValue += "<div class=\"Fields_Container_Inside\">";
			strValue += "<div class=\"Fields_Row_2_head\">";
			
			strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 30%;\">Services Name</div>";
			strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 20%;\" text-align: right;\">Charges (Monthly)</div>";
			strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 20%;\" text-align: right;\">Taxes</div>";
			strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 20%;\" text-align: right;\">Cess</div></div>";
		
		
			for(int i=0; i<comVec.size(); i++)
			{
				  
				  int j = i+1;
			    	int numTotal = j%2;
			    	
				 Vector tempVec = (Vector)comVec.elementAt(i);
				 							
				 String SName = tempVec.elementAt(1).toString().trim();
				 String Charges = tempVec.elementAt(2).toString().trim();
				 String Taxes = tempVec.elementAt(3).toString().trim();
				 String Cess = tempVec.elementAt(4).toString().trim();
			     
			    	strValue += "<div class=\"Fields_Row_2a\">";
					
					strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 30%;\">"+SName+"</div>";
					strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 20%;\" text-align: right;\">"+"Rs. "+Charges+"/-"+"</div>";
					strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 20%;\" text-align: right;\">"+Taxes+"%</div>";
					strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 20%;\" text-align: right;\">"+Cess+"%</div></div>";
			}
								
					}
			if (comVec1.size()!=0){
				strValue += "<div class=\"Page_Heading\">";	
				strValue += "<div class=\"Page_Heading_Text\" style=\"width: 70%;\">List of services Sent for Approval</div>";
				if (comVec.size()==0){
				strValue +="<div style=\"float: left;\">";
				strValue += "<a style=\"text-decoration: underline; font-size: 10px; font-weight: bold; color: rgb(0, 0, 0);\" href=\"writeMentorRequest.jsp\">Request for Add/Delete Service</a></div>";
				}
				strValue += "</div>";
				strValue += "<div class=\"Fields_Container\">";	
				strValue += "<div class=\"Fields_Container_Inside\">";
				strValue += "<div class=\"Fields_Row_2_head\">";
				
				strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 30%;\">Services Name</div>";
				strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 20%;\" text-align: right;\">Charges (Monthly)</div>";
				strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 20%;\" text-align: right;\">Taxes</div>";
				strValue += "<div class=\"Row_2_Fields_head_2\" style=\"width: 20%;\" text-align: right;\">Cess</div></div>";
				for(int s=0; s<comVec1.size(); s++)
				{
					  
					  int t = s+1;
				    	int numTotalP = t%2;
				    	
					 Vector tempVecP = (Vector)comVec1.elementAt(s);
					 							
					 String SNameP = tempVecP.elementAt(1).toString().trim();
					 String ChargesP = tempVecP.elementAt(2).toString().trim();
					 String TaxesP = tempVecP.elementAt(3).toString().trim();
					 String CessP = tempVecP.elementAt(4).toString().trim();
				     
				    	strValue += "<div class=\"Fields_Row_2a\">";
						
						strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 30%;\">"+SNameP+"</div>";
						strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 20%;\" text-align: right;\">"+"Rs. "+ChargesP+"/-"+"</div>";
						strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 20%;\" text-align: right;\">"+TaxesP+"%</div>";
						strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 20%;\" text-align: right;\">"+CessP+"%</div></div>";
			
				}		
					
			}
							strValue += "</div></div>";
		}
		return strValue;
	}
	
	private String getPages(int minVal, int maxVal, int numSize ,String uId,String compId,String numCount,String qtype,String pageid)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveIndvERequestURL('existingRequest.do?uId="+uId+"&compId="+compId+"&numCount="+numCount+"&qtype="+qtype+"&pageid="+pageid+"&'+this.value);\">";
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
