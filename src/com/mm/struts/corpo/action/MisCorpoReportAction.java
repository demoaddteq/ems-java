/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */

package com.mm.struts.corpo.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.AdvtMaster;
import com.mm.core.master.RootMaster;
import java.util.Date;

/** 
 * MyEclipse Struts
 * Creation date: 08-20-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class MisCorpoReportAction extends Action {
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
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
		String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
		
		AdvtMaster advtMaster = new AdvtMaster();
		DataSource ds = getDataSource(request, "corpo");
		Vector dataVec = new Vector();
		
		dataVec.add(compId);
		
		Vector totalStudent = advtMaster.getTestNameAndStu(getDataSource(request, "corpo"), dataVec);
		//System.out.println("totalStudent......"+totalStudent);		
				
		int totalTraReq = advtMaster.gettotalMentoringCount(ds, compId);
		
		int totalMenReqT = advtMaster.gettotalQueriesCount(ds, compId, "Mrequest");
		
		Vector totalM_ace = advtMaster.getMenAcseptRejectCount(ds, compId, "Accept");
		int inttotalM_ace = totalM_ace.size();
		
		
		Vector totalM_rej= advtMaster.getMenAcseptRejectCount(ds, compId, "Reject");
		int inttotalM_rej = totalM_rej.size();
		
		
		int totalQueReqT = advtMaster.gettotalQueriesCount(ds, compId, "Technical");
		
		Vector totalQueAneT = advtMaster.gettotalQueriesAneCount(ds, compId, "Technical");
		int inttotalQueAneT = totalQueAneT.size();
		
		////System.out.println("totalQueReqT......"+totalQueReqT);	
		////System.out.println("inttotalQueAneT......"+inttotalQueAneT);	
		//Personal
		
		int totalQueReqP = advtMaster.gettotalQueriesCount(ds, compId, "Personal");
		
		Vector totalQueAneP = advtMaster.gettotalQueriesAneCount(ds, compId, "Personal");
		int inttotalQueAneP = totalQueAneP.size();
		
		////System.out.println("totalQueReqP......"+totalQueReqP);	
		////System.out.println("inttotalQueAneP......"+inttotalQueAneP);	
		
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd");
		String toDate = sform.format(dt);
		
		int numTempCount = (request.getParameter("numCount") != null) ? Integer.parseInt(request.getParameter("numCount").trim()) : 0;
		
		
		
		
		String strTbl = getTableRow(ds, advtMaster, numTempCount, totalStudent, totalTraReq, totalQueReqT ,inttotalQueAneT, totalQueReqP, inttotalQueAneP, totalMenReqT, inttotalM_ace, inttotalM_rej);
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strTbl);
	    out.flush();
		return null;
	}
	
	private String getTableRow(DataSource ds,AdvtMaster advtMaster, int numTempCount, Vector totalStudent, int totalTraReq,int totalQueReqT ,int inttotalQueAneT,int totalQueReqP,int inttotalQueAneP, int totalMenReqT, int inttotalM_ace, int inttotalM_rej)
	{
		String strResult="<table width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">";
			
		strResult+="<tr>";
		strResult+="<td  align=\"left\" >";
		strResult+="<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > </td>";				
		strResult+="</tr>";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"left\"  valign=\"top\" >1. Total No. of students have taken tests uploaded by the company. </td>";				
		strResult+="</tr>";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > </td>";				
		strResult+="</tr>";
		
		strResult+="<tr align=\"center\" valign=\"middle\">";
		strResult+="<td width=\"25%\" align=\"center\" class=\"bold\">Test Name </td>";
		strResult+="<td width=\"25%\" align=\"center\" class=\"bold\">Test Category</td>";
		strResult+="<td width=\"25%%\" align=\"center\" class=\"bold\">Type</td>";
		strResult+="<td width=\"25%\" align=\"center\" class=\"bold\">NO of Student</td>";		
		strResult+="</tr>";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > </td>";				
		strResult+="</tr>";
		
		for(int i=0; i< totalStudent.size(); i++)
		{
			Vector teampVec = (Vector)totalStudent.elementAt(i);
			String testid = teampVec.elementAt(0).toString();
			String testname = teampVec.elementAt(1).toString();
			String testCat = teampVec.elementAt(3).toString();
			String testTpe = teampVec.elementAt(2).toString();
			int totalNo = advtMaster.gettotalCount(ds, testid);
			
			strResult+="<tr align=\"center\" valign=\"middle\">";
			strResult+="<td  align=\"center\"  >"+testname+"</td>";
			strResult+="<td  align=\"center\" >"+testCat+"</td>";
			strResult+="<td  align=\"center\" >"+testTpe+"</td>";
			strResult+="<td  align=\"center\" >"+totalNo+"</td>";		
			strResult+="</tr>";
		}
		
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > </td>";				
		strResult+="</tr>";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > <hr></td>";				
		strResult+="</tr>";
		
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > </td>";				
		strResult+="</tr>";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"left\"  valign=\"top\" >2.  Total No. of mentoring requests to the company.  -  ("+totalMenReqT+") </td>";				
		strResult+="</tr>";
		
		
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > </td>";				
		strResult+="</tr>";
		
		strResult+="<tr align=\"center\" valign=\"middle\">";
		strResult+="<td  align=\"center\"  ></td>";
		strResult+="<td  align=\"right\" class=\"bold\">Accepted</td>";
		strResult+="<td  align=\"center\" >"+inttotalM_ace+"</td>";
		strResult+="<td  align=\"center\" ></td>";		
		strResult+="</tr>";
		
		strResult+="<tr align=\"center\" valign=\"middle\">";
		strResult+="<td  align=\"center\"  ></td>";
		strResult+="<td  align=\"right\" class=\"bold\">Rejected</td>";
		strResult+="<td  align=\"center\" >"+inttotalM_rej+"</td>";
		strResult+="<td  align=\"center\" ></td>";		
		strResult+="</tr>";
		
		strResult+="<tr align=\"center\" valign=\"middle\">";
		strResult+="<td  align=\"center\"  ></td>";
		strResult+="<td  align=\"right\" class=\"bold\">Pending</td>";
		strResult+="<td  align=\"center\" >"+(totalMenReqT-(inttotalM_rej+inttotalM_ace))+"</td>";
		strResult+="<td  align=\"center\" ></td>";		
		strResult+="</tr>";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > </td>";				
		strResult+="</tr>";
		
		
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > <hr></td>";				
		strResult+="</tr>";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"left\"  valign=\"top\" >3.  Total No. of training requests to the company. </td>";				
		strResult+="</tr>";
		
		strResult+="<tr align=\"center\" valign=\"middle\">";
		strResult+="<td  align=\"center\"  ></td>";
		strResult+="<td  align=\"center\" ></td>";
		strResult+="<td  align=\"center\" >"+totalTraReq+"</td>";
		strResult+="<td  align=\"center\" ></td>";		
		strResult+="</tr>";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > </td>";				
		strResult+="</tr>";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > <hr></td>";				
		strResult+="</tr>";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > </td>";				
		strResult+="</tr>";
		
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"left\"  valign=\"top\" >4.   Total No. of queries to the company. -  ("+(totalQueReqT+totalQueReqP)+")</td>";				
		strResult+="</tr>";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > </td>";				
		strResult+="</tr>";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"left\" class=\"bold\" valign=\"top\" > <u>Technical</u> </td>";				
		strResult+="</tr>";
		
		strResult+="<tr align=\"center\" valign=\"middle\">";
		strResult+="<td  align=\"center\"  ></td>";
		strResult+="<td  align=\"center\" class=\"bold\">Tolat</td>";
		strResult+="<td  align=\"center\" class=\"bold\">Answered</td>";
		strResult+="<td  align=\"center\" class=\"bold\">Pending</td>";		
		strResult+="</tr>";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > </td>";				
		strResult+="</tr>";
		
		
		strResult+="<tr align=\"center\" valign=\"middle\">";
		strResult+="<td  align=\"center\"  ></td>";
		strResult+="<td  align=\"center\" >"+totalQueReqT+"</td>";
		strResult+="<td  align=\"center\" >"+inttotalQueAneT+"</td>";
		strResult+="<td  align=\"center\" >"+(totalQueReqT-inttotalQueAneT)+"</td>";		
		strResult+="</tr>";
		
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > </td>";				
		strResult+="</tr>";
		
		
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"left\" class=\"bold\" valign=\"top\" > <u>Personal</u> </td>";				
		strResult+="</tr>";
		
		strResult+="<tr align=\"center\" valign=\"middle\">";
		strResult+="<td  align=\"center\"  ></td>";
		strResult+="<td  align=\"center\" class=\"bold\">Tolat</td>";
		strResult+="<td  align=\"center\" class=\"bold\">Answered</td>";
		strResult+="<td  align=\"center\" class=\"bold\">Pending</td>";		
		strResult+="</tr>";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > </td>";				
		strResult+="</tr>";
		
		
		strResult+="<tr align=\"center\" valign=\"middle\">";
		strResult+="<td  align=\"center\"  ></td>";
		strResult+="<td  align=\"center\" >"+totalQueReqP+"</td>";
		strResult+="<td  align=\"center\" >"+inttotalQueAneP+"</td>";
		strResult+="<td  align=\"center\" >"+(totalQueReqP-inttotalQueAneP)+"</td>";		
		strResult+="</tr>";
				
				
			numTempCount = numTempCount+1;
			int numtotal = numTempCount%2; 
			if(numtotal !=0)
			{
				strResult+="<tr height=\"20\" style=\"display:none\">";
					strResult+="<td align=\"right\" >&nbsp;</td>";
				strResult+="</tr>";
			}
		strResult+="</table>";
		strResult+="</td>";
		strResult+="</tr>";
		
		strResult+="<tr>";
		strResult+="<td align=\"right\" ></td>";
		strResult+="</tr>";
		
		strResult+="<tr height=\"20\" >";
		strResult+="<td width=\"10%\" colspan=\"4\" align=\"center\" class=\"bold\" valign=\"top\" > </td>";				
		strResult+="</tr>";
		
		strResult+="</table>";
		
		
		return strResult;
	}
	
	
	
}