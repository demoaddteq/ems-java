package com.mm.struts.corpo.action;

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

import com.mm.core.master.IndvMaster;
import com.mm.core.master.RootMaster;

public class SearchStuDetailAction extends Action{
	
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
		DataSource dataSource = getDataSource(request, "corpo");
		HttpSession session =request.getSession();
		
		
		
		RootMaster  rootMaster = new RootMaster();
		IndvMaster  indvMaster = new IndvMaster();
		String numCount = (request.getParameter("numCount")!=null)? request.getParameter("numCount") :(String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int reminder = count%2;
		
		String uId = (request.getParameter("uId")!=null)? request.getParameter("uId").trim() : "0";	
		//System.out.println("uId ...Action......"+uId);
		
		String compId =(String)session.getAttribute("compId");
				
		////System.out.println("compId..in Action avdt ........."+compId);
		int regFlag= rootMaster.getRegisteredFlag(getDataSource(request, "corpo"), compId);
		////System.out.println("regFlag..in Action avdt ........."+regFlag);
		
		String searchResultId = (request.getParameter("searchResultId")!=null)? request.getParameter("searchResultId") :"0";
		
				
		String searchType = rootMaster.getSearchType(dataSource, Integer.parseInt(searchResultId.trim()));
		
		
		
		String studentsId = rootMaster.getStudentsId(dataSource, Integer.parseInt(searchResultId.trim()));
		
		Vector dataVec = new Vector();
		String strComplaints = "";	
		
		
		
			
		if(searchType.equalsIgnoreCase("Student")){
		dataVec = rootMaster.getStudentList(dataSource, studentsId);
		
		strComplaints = getStudentString(dataSource, dataVec, reminder, regFlag ,searchResultId);
		
		}else if(searchType.equalsIgnoreCase("College")){
			
			dataVec = rootMaster.getCollegeList(dataSource, studentsId);
			
			strComplaints = getCollegeString(dataSource, dataVec, reminder, regFlag);
		}
		
		//String teamStuId[]= studentsId.split(",");
		
		////System.out.println("studentsId ........."+studentsId);
		////System.out.println("teamStuId.length ........."+teamStuId.length);
		//System.out.println("dataVec ........."+dataVec);
		//System.out.println("dataVec.size() ........."+dataVec.size());
		
		
		
		
		Vector<String> paramVec = new Vector<String>();
		
		
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strComplaints);
	    out.flush();
		return null;
		
	}
	
	private String getStudentString(DataSource dataSource, Vector complaintVec, int reminder, int regFlag,String searchResultId)
	{
		
		String strValue="";
		
		strValue +="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";	
		//strValue = strValue +"<form  name=\"frmStudentId\" method=\"post\"  action=\"savestuResult.do\">";
		strValue +="<tr><td colspan=\"5\"><hr color=\"#C1C1C1\"></td></tr>";
		strValue +=" <tr align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\">";
		strValue +="  <td colspan=\"5\" bgcolor=\"#FFFFFF\" valign=\"top\" ><table width=\"100%\">";
		strValue+="<tr align=\"center\"><td bgcolor=\"#9BCFDD\" width=\"30%\"><a href=\"writequery.jsp?pageid=5&pagetype=Sresult&srid="+searchResultId+"\" class=\"bold\"> click here for Write Blogs.</a></td>	<td colspan=\"2\" width=\"35%\"></td><td colspan=\"2\" width=\"35%\"></td></tr>";
		strValue +="</table></td>";
		strValue +=" </tr>";
		
		RootMaster rootMaster = new RootMaster();
		if(complaintVec.size()>0){
		
		for(int i=0; i<complaintVec.size(); i++){
			
		Vector tempVec = (Vector)complaintVec.elementAt(i);
		
		int loginId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
		String firstSName =tempVec.elementAt(1).toString().trim();
		String lastName =tempVec.elementAt(2).toString().trim();
		String CId =tempVec.elementAt(5).toString().trim();
		int otherCollId =Integer.parseInt(tempVec.elementAt(6).toString().trim());
		String photopath =  "http://www.campusyogi.com:8080/campusyogi/photo/"+tempVec.elementAt(7).toString().trim();
		//System.out.println("photopath....."+photopath);
		////System.out.println("otherCollId....."+otherCollId);
		
		String StrArr[]= CId.split("~");
		int numCId=Integer.parseInt(StrArr[0]);
		
		
		String cName = "Not Mention.";
		if(StrArr.length==2){
			
			if(StrArr[1].equalsIgnoreCase("1"))
			{
				 cName = rootMaster.getCompnyName(dataSource, numCId);
			}
			else{
				
				 cName = rootMaster.getCollegeName(dataSource, numCId);
			}
			
		}
		else{
			cName = rootMaster.getCollegeName(dataSource, otherCollId);
		}
		////System.out.println("cName....."+cName);
		int j = i+1;
		strValue +="<tr><td colspan=\"5\"><hr color=\"#C1C1C1\">";
		strValue +="<table width=\"100%\">";
			strValue +="<tr>";
				strValue +="<td width=\"80%\">";
				strValue +="<table width=\"100%\" >";				
					
				strValue +=" <tr align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\">";
				strValue +="  <td colspan=\"5\" valign=\"top\"  bgcolor=\"#FFFFFF\" >&nbsp;";
				if(regFlag!=0){
				//strValue +=""+j+"&nbsp;<input name=\"studentsds\" type=\"checkbox\" class=\"ms_sans_serif\" id=\"studentsds\" value=\""+loginId+"\" onClick=\"checkSelection()\"></input>&nbsp;";
				strValue +=" <strong>Student Name:-</strong> <strong><font color=\"#0033FF\"><A href=\"otherResumePre.do?pageid=1&userid="+loginId+"&detailFor=Student\"> "+firstSName+"   "+lastName+"</A></font></strong></td>";
				}else{
					strValue +=" <strong>Student Name:-</strong> <strong><font color=\"#0033FF\"><A onclick=\"showalert()\";> "+firstSName+"   "+lastName+"</A></font></strong></td>";
					
				}
				strValue +=" </tr>";
				
				strValue +=" <tr align=\"left\" valign=\"top\"  bgcolor=\"#FFFAF6\">";
				strValue +="  <td  width=\"10%\" valign=\"top\"  align=\"right\" bgcolor=\"#FFFFFF\" >&nbsp;</td>";	
				strValue +="  <td  width=\"30%\" valign=\"top\"  align=\"right\" bgcolor=\"#FFFFFF\" >&nbsp;College Name:</td>";		
				strValue +="  <td  width=\"40%\" valign=\"top\"  align=\"left\" bgcolor=\"#FFFFFF\" >&nbsp;"+cName+"</td>";		
				strValue +="  <td colspan=\"2\" valign=\"top\"  bgcolor=\"#FFFFFF\" >&nbsp;</td>";		
				strValue +=" </tr>";
				
				strValue +=" <tr align=\"left\" valign=\"top\"  bgcolor=\"#FFFAF6\">";
				strValue +="  <td  width=\"10%\" valign=\"top\"  align=\"right\" bgcolor=\"#FFFFFF\" >&nbsp;</td>";	
				strValue +="  <td  width=\"30%\" valign=\"top\"  align=\"right\" bgcolor=\"#FFFFFF\" >&nbsp;Stream:</td>";
				
				IndvMaster indvMaster=new IndvMaster();
				Vector Stream=indvMaster.getStreamName(dataSource, Integer.parseInt(tempVec.elementAt(4).toString().trim()));
				strValue +="  <td  width=\"40%\" valign=\"top\"  align=\"left\" bgcolor=\"#FFFFFF\" >&nbsp;"+Stream.elementAt(0).toString().trim()+"</td>";		
				strValue +="  <td colspan=\"2\" valign=\"top\"  bgcolor=\"#FFFFFF\" >&nbsp;</td>";		
				strValue +=" </tr>";
					
				strValue +=" <tr align=\"left\" valign=\"top\" bgcolor=\"#FFFAF6\">";
				strValue +="  <td  width=\"10%\" valign=\"top\"  align=\"right\" bgcolor=\"#FFFFFF\" >&nbsp;</td>";	
				strValue +="  <td  width=\"30%\" valign=\"top\"  align=\"right\" bgcolor=\"#FFFFFF\" >&nbsp;Status:</td>";		
				strValue +="  <td  width=\"40%\" valign=\"top\" align=\"left\" bgcolor=\"#FFFFFF\" >&nbsp;"+tempVec.elementAt(3).toString().trim()+"</td>";		
				strValue +="  <td colspan=\"2\" bgcolor=\"#FFFFFF\" >&nbsp;</td>";		
				strValue +=" </tr>";					
					strValue +="</table>";
				strValue +="</td>";

				strValue +="<td width=\"20%\">";
					strValue +="<table width=\"100%\"  align=\"center\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">";
					strValue +="<tr height=\"125\" align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\"><td align=\"center\" valign=\"middle\"><img src =\""+photopath+"\" width=\"80\"  height=\"100\"></img></td>";
					strValue +="</tr>";					
					strValue +="</table>";
				strValue +="</td>";
		strValue +="</tr>";
		
		
		strValue +="</table>";
		
		
		
		strValue +="</td>";
		strValue +="</tr>";	
		
		}
		}	
		strValue +="<tr><td colspan=\"5\"><hr color=\"#C1C1C1\"></td></tr>";
		strValue +=" <tr align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\">";
		strValue +="  <td colspan=\"5\" bgcolor=\"#FFFFFF\" valign=\"top\" ><table width=\"100%\">";
		strValue+="<tr align=\"center\"><td bgcolor=\"#9BCFDD\" width=\"30%\"><a href=\"writequery.jsp?pageid=5&pagetype=Sresult&srid="+searchResultId+"\" class=\"bold\"> click here for Write Blogs.</a></td>	<td colspan=\"2\" width=\"35%\"></td><td colspan=\"2\" width=\"35%\"></td></tr>";
		strValue +="</table></td>";
		strValue +=" </tr>";
		
		strValue +="</table>";
		
		
		return strValue;
	
		}
	
	private String getCollegeString(DataSource dataSource, Vector complaintVec, int reminder, int regFlag)
	{
		
		String strValue="";
		
		strValue +="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";	
		//strValue = strValue +"<form  name=\"frmStudentId\" method=\"post\"  action=\"savestuResult.do\">";

		RootMaster rootMaster = new RootMaster();
		if(complaintVec.size()>0){
		
		for(int i=0; i<complaintVec.size(); i++){
			
		Vector tempVec = (Vector)complaintVec.elementAt(i);
		
		int compId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
		int total_sut = rootMaster.getStudentInCollCount(dataSource, compId);
		
		String comName =tempVec.elementAt(1).toString().trim();
		String comDisName =tempVec.elementAt(2).toString().trim();
		String comCity =tempVec.elementAt(3).toString().trim();
		String cityname = rootMaster.getPlaceName(dataSource, Integer.parseInt(comCity)) ;
		
		String comState =tempVec.elementAt(4).toString().trim();
		String statename = rootMaster.getStateName(dataSource, Integer.parseInt(comState)) ;
		
		String comCountry =tempVec.elementAt(5).toString().trim();
		String countryname = rootMaster.getCountryName(dataSource, Integer.parseInt(comCountry)) ;
		
		strValue +=" <tr align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\">";
		strValue +="  <td colspan=\"5\" bgcolor=\"#FFFFFF\" >&nbsp;";
		
		 if(regFlag!=0){
				
		strValue +=" <strong>College Name:-</strong> <strong><font color=\"#0033FF\"><A href=\"detailAll.jsp?pageid=4&cuid="+compId+"&detailFor=Advertiser\">"+comName+" </A></font></strong>";
		 }else{
			 strValue +=" <strong>College Name:-</strong> <strong><font color=\"#0033FF\" ><A onclick=\"showalert()\";>"+comName+"</A></font></strong>";
				 
		 }
		strValue +=" </td></tr>";
		
		strValue +="   <tr align=\"left\">";
		strValue +="   <td  width=\"10%\" align=\"right\" valign=\"top\" class=\"bold\">&nbsp;</td>";
		strValue +="   <td width=\"25%\" align=\"right\" valign=\"top\" colspan=\"1\" >&nbsp;</td>";
		strValue +="   <td width=\"60%\" align=\"left\" valign=\"top\" colspan=\"1\" >&nbsp;</td>";
		strValue +="   <td colspan=\"2\" valign=\"top\" class=\"bold\"></td>";
		
		strValue +=" </tr>";
		
		strValue +="   <tr align=\"left\">";
		strValue +="   <td  width=\"10%\" align=\"right\" valign=\"top\" class=\"bold\">&nbsp;</td>";
		strValue +="   <td width=\"25%\" align=\"right\" valign=\"top\" colspan=\"1\" class=\"bold\">&nbsp;Country:</td>";
		strValue +="   <td width=\"60%\" align=\"left\" colspan=\"1\" >&nbsp;"+countryname+".</td>";
		strValue +="   <td colspan=\"2\" valign=\"top\" class=\"bold\"></td>";
		
		strValue +=" </tr>";
		strValue +="   <tr align=\"left\">";
		strValue +="   <td  width=\"10%\" align=\"right\" valign=\"top\" class=\"bold\">&nbsp;</td>";
		strValue +="   <td width=\"25%\" align=\"right\" valign=\"top\" colspan=\"1\" class=\"bold\">&nbsp;State:</td>";
		strValue +="   <td width=\"60%\" align=\"left\" colspan=\"1\" >&nbsp;"+statename+".</td>";
		strValue +="   <td colspan=\"2\" valign=\"top\" class=\"bold\"></td>";
		
		strValue +=" </tr>";
		strValue +="   <tr align=\"left\">";
		strValue +="   <td  width=\"10%\" align=\"right\" valign=\"top\" class=\"bold\">&nbsp;</td>";
		strValue +="   <td width=\"25%\" align=\"right\" valign=\"top\" colspan=\"1\" class=\"bold\">&nbsp;City:</td>";
		strValue +="   <td width=\"60%\" align=\"left\" colspan=\"1\" >&nbsp;"+cityname+".</td>";
		strValue +="   <td colspan=\"2\" valign=\"top\" class=\"bold\"></td>";
		
		strValue +=" </tr>";
		
		strValue +="   <tr align=\"left\">";
		strValue +="   <td  width=\"10%\" align=\"right\" valign=\"top\" class=\"bold\">&nbsp;</td>";
		strValue +="   <td width=\"25%\" align=\"right\" valign=\"top\" colspan=\"1\" class=\"bold\">&nbsp;Total Student Registerd:</td>";
		strValue +="   <td width=\"60%\" align=\"left\" colspan=\"1\" >&nbsp;&nbsp;<A href=\"showsudent.do?collegeId="+compId+"\">&nbsp;"+total_sut+"&nbsp;</A></td>";
		strValue +="   <td colspan=\"2\" valign=\"top\" class=\"bold\"></td>";
		
		strValue +=" </tr>";
		
		strValue +=" <tr align=\"left\" valign=\"bottom\" bgcolor=\"#FFFAF6\">";
		strValue +="  <td colspan=\"5\" bgcolor=\"#FFFFFF\" valign=\"top\" >&nbsp;";
		strValue +=" <hr color=\"#C1C1C1\"></td>";
		strValue +=" </tr>";
		
		
		
		}
		
		strValue +=" <tr id=\"collrow3\" style=\"display:none\" valign=\"top\"  bgcolor=\"#FFFAF6\">";
		strValue +="  <td  colspan=\"2\" width=\"30%\" valign=\"top\"  align=\"right\" bgcolor=\"#FFFFFF\" class=\"bold\">&nbsp;Search Title:</td>";		
		strValue +="  <td  colspan=\"3\" valign=\"top\"  align=\"left\" bgcolor=\"#FFFFFF\" >&nbsp;<input type=\"text\" id=\"titelname\" name=\"titelname\"   value=\"\"></input></td>";		
		strValue +=" </tr>";
		
		strValue +=" <tr height=\"20\"  align=\"right\">";
		strValue +="  <td colspan=\"5\" valign=\"middle\" align=\"center\" ></td>";
		strValue +="  </tr>";
		
		strValue +=" <tr id=\"collrow4\" style=\"display:none\" valign=\"top\"  bgcolor=\"#FFFAF6\">";
		strValue +="  <td  colspan=\"2\" width=\"30%\" valign=\"top\"  align=\"right\" bgcolor=\"#FFFFFF\" class=\"bold\">&nbsp;Access Type:</td>";		
		strValue +="  <td  colspan=\"3\" valign=\"top\"  align=\"left\" bgcolor=\"#FFFFFF\" >&nbsp;<input type=\"radio\" name=\"acceess\"  value=\"0\" checked=\"true\">Personal</input>&nbsp;<input type=\"radio\" name=\"acceess\"  value=\"1\">Company</input></td>";		
		strValue +=" </tr>";
		
				
		strValue +=" <tr height=\"25\"  align=\"right\">";
		strValue +="  <td colspan=\"5\" valign=\"middle\" align=\"center\" ></td>";
		strValue +="  </tr>";
		
				
		
		strValue +="</table>";
		
		
		
		strValue +="</td>";
		strValue +="</tr>";	
		
		}
			
		
		
		
		return strValue;
	
		}
	
	

}
