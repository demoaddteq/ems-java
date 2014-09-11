


package com.mm.struts.student.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.sql.DataSource;
import com.mm.core.master.*;


public class MentorListAction extends Action {

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
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		DataSource ds = getDataSource(request,"student");
		HttpSession session = request.getSession();
		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		
		String uId =(String)session.getAttribute("uId");
		int userid=Integer.parseInt(uId);
		String companyid=(String)session.getAttribute("compId");
		
		
		IndvMaster indvMaster= new IndvMaster();	
		AdvtMaster advtMaster= new AdvtMaster();	
		Vector dataVec = new Vector();		
			
			
		int mcount = indvMaster.getMentorCount(getDataSource(request, "student"), userid);		
		session.setAttribute("mcount", mcount);						
		//////System.out.println("countVec.............InboxAction........... "+mcount);
		
		String pageid = (request.getParameter("pageid") != null) ? request.getParameter("pageid") :(String)session.getAttribute("pageid") ;	
		String 	qtype = (request.getParameter("qtype") != null) ? request.getParameter("qtype") : "uProcess";
		System.out.println("pageid......on Mlist.........." +pageid);
		System.out.println("qtype......on Mlist..........." +qtype);
		//System.out.println("lowerSubTag......on exis Req..........." +lowerSubTag);
		
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "creation_date";
		String strSOrder = "";
		String strName = "asc";
		String strCname = "asc";
		String strDateOrder = "asc";
		
		if(request.getParameter("sby")!=null)
		{
			if(request.getParameter("name")!=null)
			{
				if(request.getParameter("name").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strName = "desc";
				}
				else if (request.getParameter("name").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strName = "asc";
				}
			}
			
			if(request.getParameter("cname")!=null)
			{
				if(request.getParameter("cname").trim().equalsIgnoreCase("asc"))
				{
					strSOrder = "desc";
					strCname = "desc";
				}
				else if (request.getParameter("cname").trim().equalsIgnoreCase("desc"))
				{
					strSOrder = "asc";
					strCname = "asc";
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
		int maxVal = 20;
		
		int totalRow = indvMaster.getMentorCount(getDataSource(request, "student"), userid);
		//////System.out.println("totalRow.............InboxAction........... "+totalRow);
		
		String strPageHtml = getPages(minVal, maxVal, totalRow , uId,numCount);
		
		Vector<String> paramVec = new Vector<String>();
		paramVec.add(uId);//UserId
		paramVec.add(strFShortBy);
		paramVec.add(String.valueOf(minVal));
		paramVec.add(String.valueOf(maxVal));
		
		System.out.println("qtype userVec..in if ..."+qtype);
		Vector userVec=new Vector();	
//		complaint_id not in ("+ComList+")";
		Vector	temp= new Vector();
		Vector idVec=new Vector();
		Vector userVec1=new Vector();
		
		if(qtype.equalsIgnoreCase("Accept"))
			{
			
				userVec = indvMaster.getMentordata(getDataSource(request, "student"), paramVec);
				
				if(userVec.size()!=0){
				System.out.println("Accept userVec....."+userVec);
				for(int i=0; i<userVec.size();i++)
					{
					Vector	comListVec = (Vector)userVec.elementAt(i);					
					String tempComId = comListVec.elementAt(0).toString().trim();
					 idVec = indvMaster.getNewMentorList(ds, tempComId);
					Vector idVec1 = indvMaster.getNewMentorList(ds, tempComId);
					 if(idVec.size()!=0)
					 	{
						 
						 temp.add(idVec.elementAt(0));
						}
					 
					 else{
						 Vector temp1=new Vector();
						 temp1.add("0");
						 temp1.add("0");
						 temp1.add("0");
						 temp1.add(tempComId);
						 temp1.add(comListVec.elementAt(1).toString().trim());
						 temp1.add("Underprocess");
						 
						 temp.add(temp1);
						 //////System.out.println("result userVec....."+userVec);
						 	
					 	}
					}
				}
			}
			else if(qtype.equalsIgnoreCase("uProcess"))
			{
					userVec = indvMaster.getMentordata(getDataSource(request, "student"), paramVec);
					System.out.println("result userVec..in if ..."+userVec);
					if(userVec.size()!=0)
						{
							System.out.println("result userVec..in if ..."+userVec);
						for(int i=0; i<userVec.size();i++)
							{
								Vector	comListVec = (Vector)userVec.elementAt(i);					
								String tempComId = comListVec.elementAt(0).toString().trim();
								 idVec = indvMaster.getMentorAccept(ds, tempComId);
								Vector idVec1 = indvMaster.getNewMentorList(ds, tempComId);
								 if(idVec.size()!=0)
								 {
									 
									 temp.add(idVec.elementAt(0));
			
			
								 }
								 System.out.println("temp userVec..in if ..."+temp);
															 
								}
						}
			}
		
		
		
		 //////System.out.println("temptemptempin ... "+temp);
	
		
		String strComplaintList = getComplaintList(request,strName, strCname,  strDateOrder, minVal, strPageHtml,  remander,temp,pageid, maxVal, totalRow);
		//////////System.out.println("Users "+strComplaintList);
		/**
	     * setContentType - text/html to write String on page.
	     * PrintWriter - This line use to get writer to write on page.
	     * out.println - use to write string. 
	     * 
	     */
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strComplaintList);
	    out.flush();
		
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getComplaintList(HttpServletRequest request,String strName, String strCname,String strDateOrder, int minVal, String strPageHtml,  int remander, Vector temp,String pageid,int maxVal, int totalRow)
	{
		
		
	  IndvMaster indvMaster =  new IndvMaster();
	  RootMaster rootMaster =  new RootMaster();
	  DataSource ds = getDataSource(request,"student");
	  
		String strValue = "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >";
		
		
		if(temp.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"400\" valign=\"center\" colspan=\"7\" class=\"bold\"> No Data Found</td>";
			strValue += "</tr>";
		}
		else
			{
			if (totalRow > maxVal){
			strValue += "<tr >";
		    strValue += "<td colspan=\"7\" align=\"right\" >Page Number  "+strPageHtml+"&nbsp;&nbsp;</td>";		
			strValue += "</tr>";
			}
			strValue += "<tr>";
	        strValue += "<td width=\"100%\">";
		    strValue += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
		    strValue += "<tr>";	
		    strValue += "<td height=\"5\" colspan=\"6\" align=\"right\"><hr color=\"#C1C1C1\"></td>";	
		    strValue += "</tr>";
		   
	      
		strValue += "<tr>";	    
	    strValue += "<td  align=\"left\" class=\"bold\">&nbsp;S.No.</td>";
	    strValue += "<td class=\"bold\">Mentor`s Name &nbsp;</td>";	    
	    strValue += "<td class=\"bold\">Mentor`s Company&nbsp;</td>";	
	    strValue += "<td class=\"bold\">Status</td>";	
	    strValue += "<td class=\"bold\">&nbsp;</td>";
	    strValue += "<td class=\"bold\">&nbsp;</td>";
	    strValue += "</tr>";	
	    

	 
	   
	   	 
	    strValue += "<tr>";	
	    strValue += "<td height=\"5\" colspan=\"6\" align=\"right\"><hr color=\"#C1C1C1\"></td>";	
	    strValue += "</tr>";	
	   
	    for(int i=0; i<temp.size(); i++)
		{
	    	int j = i+1;
	    	int numTotal = j%2;
	    	//String strColor = (numTotal==0) ? "#ff722b" : "#ffffff";
	    	String strColor = (numTotal==0) ? "#FFFFFF" : "#EDF3F8";
	    	String strImg = (numTotal==0) ? "detail_gray.gif" : "detail_white.gif";
	    	//////System.out.println("strColor "+strColor);
	    	
	    	/*support.setFieldVec("int", "respid");//0
			support.setFieldVec("string", "responsetext");//1
			support.setFieldVec("string", "responsedate");//2
			support.setFieldVec("int", "complaintid");//3
			support.setFieldVec("int", "userid");//4
			support.setFieldVec("int", "privateflag");//5*/
			
	    	
		 
		 Vector tempVec = (Vector)temp.elementAt(i);
		 //////System.out.println("temptemptempin ... "+tempVec.elementAt(0));
		 //////System.out.println("temptemptempin ... "+tempVec.elementAt(1));
		 //////System.out.println("temptemptempin ... "+tempVec.elementAt(2));
		 //////System.out.println("temptemptempin ... "+tempVec.elementAt(3));
		 //////System.out.println("temptemptempin ... "+tempVec.elementAt(4));
		 //////System.out.println("temptemptempin ... "+tempVec.elementAt(5));
		 //////System.out.println("------------------"+i+"-------------------------");
		 int respid = Integer.parseInt(tempVec.elementAt(4).toString().trim());
		 
		 Vector userinfo=indvMaster.getUserInfo(ds, respid);
		 String fname=userinfo.elementAt(4).toString().trim();
		 String lname=userinfo.elementAt(5).toString().trim();
		 
		 int cid = Integer.parseInt(userinfo.elementAt(0).toString().trim());
		 Vector cinfo=rootMaster.getCompanyDetail(ds, cid);
		 String cname=cinfo.elementAt(1).toString().trim();
		/*
		 String Status="";
		//System.out.println("temptemptempin ... "+tempVec.elementAt(5));
		 if((( tempVec.elementAt(5).toString().equalsIgnoreCase("0")))||(( tempVec.elementAt(5).toString().equalsIgnoreCase("1"))))
		 {
			 if(tempVec.elementAt(5).toString().equalsIgnoreCase("0"))
			 {
				 Status="Reject"; 
			 }else
			 {
				 Status="Accept"; 
			 }
		 }else
		 {
			 Status="UnderProcess"; 
		 }
		*/
	     
	     
	    
	        
	    
	    strValue += "<tr align=\"center\" bgcolor=\""+strColor+"\">";
	    strValue += "<td width=\"5%\" align=\"left\" height=\"20\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+j+"</td>";
	    
	    strValue += "<td  width=\"30%\" align=\"center\" > "+fname+"&nbsp;"+lname+"</td>";	
	    strValue += "<td width=\"30%\" align=\"center\" > "+cname+"</td>";	
	    strValue += "<td width=\"25%\" align=\"center\" > "+tempVec.elementAt(5).toString().trim()+"</td>";	
	   
	    strValue += "<td width=\"10%\" colspan=\"2\" ><a href=\"mentorDetail.jsp?pageid="+pageid+"&mCid="+tempVec.elementAt(3).toString()+"&aType="+tempVec.elementAt(5).toString()+"\"> <img alt=\"Click here to see detail\" src=\"../images/"+strImg.trim()+"\" width=\"47\" height=\"19\" border=\"0\"></img></a></td>";
	     
	    strValue += "</tr>";	
		
	    
		}
	   
	    strValue += "<tr>";
	    strValue += "<td height=\"5\" colspan=\"6\" align=\"right\"><hr color=\"#C1C1C1\"></td>";	
	    strValue += "</tr>";
	    if (totalRow > maxVal){
		strValue += "<tr >";
	    strValue += "<td colspan=\"7\" align=\"right\" >Page Number  "+strPageHtml+"&nbsp;&nbsp;</td>";		
		strValue += "</tr>";
		}
		
		strValue += "</table>";
		strValue += "</td>";	
		strValue += "</tr>";
		}
		
		if(remander==0)
		{
			strValue += "<tr style=\"display:none\" align=\"center\">";
			strValue += "<td height=\"50\" colspan=\"5\"></td>";
			strValue += "</tr>";
		}
	    strValue += "</table>";	
	    //////////System.out.println("minVal........."+minVal);
	   
		return strValue;
	}
	  
	private String getPages(int minVal, int maxVal, int numSize, String uId,String numCount)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveStdMentorlistURL('mentorList.do?uId="+uId+"&numCount="+numCount+"&'+this.value);\">";
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
