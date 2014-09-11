package com.mm.struts.corpo.action; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.io.PrintWriter;
import java.util.Vector;

import com.mm.core.master.*;
import com.mm.struts.indv.action.ExistingComplaintAction;

public class AdvtUserdetailAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		////////System.out.println("remander..........."+remander);
		
		String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
		//////System.out.println("uId.....Advt Section......"+uId);
		
		String task = request.getParameter("task").trim();
		int userid = Integer.parseInt(request.getParameter("userid").trim());
		//////System.out.println("task..........."+task);
		//////System.out.println("userid..........."+userid);
		
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 100;
		
		AdvtMaster am = new AdvtMaster();	
		RootMaster ob = new RootMaster();
		Vector<String> paramVec1 = new Vector<String>();
		paramVec1.add(compId);
		int totalRow = am.getUserCount(getDataSource(request, "corpo"), paramVec1);	
		//////System.out.println("totalRow..........."+totalRow);
		String strPageHtml = getPages(minVal, maxVal, totalRow);
		
		Vector userVec = ob.getUserInfo(getDataSource(request, "corpo"), userid);
		//////System.out.println("userVec..........."+userVec);
		int UserCityId = Integer.parseInt(userVec.elementAt(6).toString().trim());
    	String UserCityname = ob.getPlaceName(getDataSource(request, "corpo"), UserCityId);		    	
    	int UserStateId = Integer.parseInt(userVec.elementAt(7).toString().trim());
    	String UserStateName = ob.getStateName(getDataSource(request, "corpo"), UserStateId);		    	
    	int UserCountryId = Integer.parseInt(userVec.elementAt(11).toString().trim());
    	String UserCountryname = ob.getCountryName(getDataSource(request, "corpo"), UserCountryId);
    	int numRId = Integer.parseInt(userVec.elementAt(1).toString().trim());
    	Vector userRightVec = am.getUserGroupDetails(getDataSource(request, "corpo"), Integer.parseInt(compId.trim()), numRId, "Details");
    	
    	int dep_Id = Integer.parseInt(userVec.elementAt(19).toString());
		int des_Id = Integer.parseInt(userVec.elementAt(20).toString());
		String desStr = ob.getDesName(getDataSource(request, "corpo"),des_Id);	
		String depStr = ob.getDepName(getDataSource(request, "corpo"),dep_Id);
    	
    	
    	//////System.out.println("userRightVec......................... "+userRightVec);
    	////////System.out.println("compId "+compId);
    	////////System.out.println("numRId "+numRId);
    	////////System.out.println("userid "+userid);
    	Vector userCategoryVec = am.getUserCategoryDetails(getDataSource(request, "corpo"), Integer.parseInt(compId.trim()), userid);
    	//////System.out.println("userCategoryVec..................... "+userCategoryVec);
    	Vector<String> userCatVec = new Vector<String>();
    	for(int i=0; i<userCategoryVec.size(); i++)
    	{
    		////////System.out.println("userCategoryVec.elementAt(i).toString().trim() "+userCategoryVec.elementAt(i).toString().trim());
    		if(Integer.parseInt(userCategoryVec.elementAt(i).toString().trim())==0)
    		{
    			////////System.out.println("userCategoryVec.elementAt(i).toString().trim() "+userCategoryVec.elementAt(i).toString().trim());
    			String strCatVal = "";
    			if(i>0){
    			 strCatVal = am.getSubCatDetail(getDataSource(request, "corpo"), Integer.parseInt(compId.trim()), Integer.parseInt(userCategoryVec.elementAt(i-1).toString().trim()), i-1);
    			}
    			userCatVec.add(strCatVal);
    			String strCatVal1 = "";
    			String strAdminName  = "";
    			if(i>1){
    			 strCatVal1 = am.getSubCatDetail(getDataSource(request, "corpo"), Integer.parseInt(compId.trim()), Integer.parseInt(userCategoryVec.elementAt(i-2).toString().trim()), i-2);
    			int numCatVal = Integer.parseInt(userCategoryVec.elementAt(i-2).toString().trim());
    			
    			userCatVec.add(strCatVal1);
    			 strAdminName = am.getAdminName(getDataSource(request, "corpo"), Integer.parseInt(compId.trim()), numCatVal, i-2);
    			}
    			userCatVec.add(strAdminName);
    			break;
    		}
    	}
    	//////System.out.println("userCatVec....................... "+userCatVec);
    	Vector<String> infoVec = new Vector<String>();    	
    	infoVec.add(UserCityname);//0// Consumer City nane
    	infoVec.add(UserStateName);//1// Consumer State nane
    	infoVec.add(UserCountryname);//2// Consumer Country nane
    	infoVec.add(depStr);//3// Department name
    	infoVec.add(desStr);//4// Designation
		
		String strUsers = getUserDetail(userVec,infoVec, minVal, strPageHtml, remander, userRightVec, userCatVec);
		//////System.out.println("Users................1................... ");
		/**
	     * setContentType - text/html to write String on page.
	     * PrintWriter - This line use to get writer to write on page.
	     * out.println - use to write string. 
	     * 
	     */
		response.setContentType("text/html;charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
	    out.println(strUsers);
	    out.flush();	
		return null;
	}
	
	private String getUserDetail( Vector userVec,Vector infoVec, int minVal, String strPageHtml, int remander, Vector userRightVec, Vector userCatVec)
	{
		
		
		String strValue = "<table width=\"100%\">";
		if(userVec.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"50\" valign=\"center\" colspan=\"7\" class=\"Arial_Narrow_16_orange_normal\"> No Data Found</td>";
			strValue += "</tr>";
		}
		else
		{	
			strValue += "<table width=\"100%\" align=\"center\"  class=\"headingsBlack\">";
			strValue += "<tr height=\"20\">";
			strValue += "<td width=\"60%\" valign=\"top\" class=\"Arial_12_black_normal\">";
			strValue += "<table width=\"100%\" align=\"center\"  class=\"headingsBlack\">";
			strValue += "<tr>";
			strValue += "<td colspan=\"2\" align=\"center\" valign=\"top\" class=\"Arial_Narrow_12_black_bold\">";
			strValue += "User Details</td>";
			strValue += " </tr>";
			strValue +="<tr>";
			strValue +="<td colspan=\"2\" align=\"left\" class=\"var_12_bold_orange\"><hr color=\"#C1C1C1\"></td>";
			strValue +="</tr>";
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td  align=\"right\" class=\"Arial_Narrow_12_black_bold\">Login Name:</td>";
			strValue += "  <td  align=\"left\"><span class=\"Arial_12_black_normal\">"+userVec.elementAt(2).toString().trim()+"</span></td>";
			strValue += "  </tr>";
	/*	
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"Arial_Narrow_12_black_bold\"> Password:</td>";
			String password = userVec.elementAt(3).toString().trim();
			String strPass ="";
			for(int i=0; i < password.length(); i++){
				strPass = strPass +"*";
	
			}
			strValue += "  <td align=\"left\"><span class=\"Arial_12_black_normal\">"+strPass+"</span></td>";
			strValue += "  </tr>";
	*/		
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"Arial_Narrow_12_black_bold\">First Name:</td>";
			strValue += "  <td align=\"left\"><span class=\"Arial_12_black_normal\">"+userVec.elementAt(4).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"Arial_Narrow_12_black_bold\">Last Name:</td>";
			strValue += "  <td align=\"left\"><span class=\"Arial_12_black_normal\">"+userVec.elementAt(5).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\">Department:</td>";
			strValue += "  <td align=\"left\"><span >"+infoVec.elementAt(3).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\">Designation:</td>";
			strValue += "  <td align=\"left\"><span >"+infoVec.elementAt(4).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			
			strValue += " <tr height=\"20\">";
			strValue += " <td align=\"right\" class=\"Arial_Narrow_12_black_bold\">Home Address:</td>";
			strValue += "  <td align=\"left\"><span class=\"Arial_12_black_normal\">"+userVec.elementAt(9).toString().trim()+"</span></td>";
			strValue += "  </tr>";
		/*	
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"Arial_Narrow_12_black_bold\"> Country:</td>";
			strValue += " <td align=\"left\"><span class=\"Arial_12_black_normal\">"+infoVec.elementAt(2).toString().trim()+"</span></td>";
			strValue += "  </tr>";
	*/		
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"Arial_Narrow_12_black_bold\">City:</td>";
			strValue += "  <td align=\"left\">";
			strValue += "	<span class=\"Arial_12_black_normal\">"+infoVec.elementAt(0).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			
			
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"Arial_Narrow_12_black_bold\">State:</td>";
			strValue += "  <td align=\"left\">";
			strValue += "	<span class=\"Arial_12_black_normal\">"+infoVec.elementAt(1).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"Arial_Narrow_12_black_bold\"> Country:</td>";
			strValue += " <td align=\"left\"><span class=\"Arial_12_black_normal\">"+infoVec.elementAt(2).toString().trim()+"</span></td>";
			strValue += "  </tr>";
		
	/*		
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"Arial_Narrow_12_black_bold\">City:</td>";
			strValue += "  <td align=\"left\">";
			strValue += "	<span class=\"Arial_12_black_normal\">"+infoVec.elementAt(0).toString().trim()+"</span></td>";
			strValue += "  </tr>";
	*/		
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"Arial_Narrow_12_black_bold\"> Pin Code:</td>";
			strValue += " <td align=\"left\"><span class=\"Arial_12_black_normal\">"+userVec.elementAt(13).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"Arial_Narrow_12_black_bold\"> E-Mail Id:</td>";
			strValue += "  <td align=\"left\"><span class=\"Arial_12_black_normal\">"+userVec.elementAt(8).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			String temPno1="Not mention";
		    if(userVec.elementAt(12).toString().trim().length()!=0)
		    {
				////////System.out.println("insite"); 
				String temPno=userVec.elementAt(12).toString().trim().trim();
				temPno1 = temPno.replace("~", "-");
				strValue += " <tr height=\"20\">";
				strValue += "  <td align=\"right\" class=\"Arial_Narrow_12_black_bold\"> Phone No:</td>";
				strValue += "  <td align=\"left\">";
				strValue += "	<span class=\"Arial_12_black_normal\">"+temPno1+"</span> </td> ";
				strValue += " </tr>";
		    }
			strValue += " <tr height=\"20\">";
			strValue += " <td align=\"right\" class=\"Arial_Narrow_12_black_bold\">Mobile No:</td>";
			String temMno1="Not mention";
		    if(userVec.elementAt(10).toString().trim().length()!=0){
				////////System.out.println("insite"); 
				String temPno=userVec.elementAt(10).toString().trim();
				temMno1 = temPno.replace("~", "-");
				
			}
			strValue += "  <td align=\"left\"><span class=\"Arial_12_black_normal\">"+temMno1+"</span> </td>";         
			strValue += "  </tr>";
			
			String strMentoring[] = userVec.elementAt(16).toString().trim().split(",");			
			String strQue[]  = userVec.elementAt(17).toString().trim().split(",");
			
			String strmen1 = "NO";
			String strque1 = "NO";
			
			if(strMentoring.length>=4){
				
				strmen1 = "";
				
					if(!strMentoring[0].equalsIgnoreCase("0")){				
						strmen1 += "  Personality,";
						}
					if(!strMentoring[1].equalsIgnoreCase("0")){				
						strmen1 += "  Soft skills,";
						}
					if(!strMentoring[2].equalsIgnoreCase("0")){				
						strmen1 += "  Technical,";
						}
					if(!strMentoring[3].equalsIgnoreCase("0")){				
						strmen1 += "  Aptitude,";
						}
					
					if(strmen1.length()>2){
						strmen1 = strmen1.substring(0, strmen1.length()-1);
						}
			}
			if(strQue.length==2){
				
				strque1 = "";
				
					if(!strQue[0].equalsIgnoreCase("0")){				
						strque1 += "Technical,";
						}
					if(!strQue[1].equalsIgnoreCase("0")){				
						strque1 += " Personal,";
						}
					
					strque1 = strque1.substring(0, strque1.length()-1);
			}
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"Arial_Narrow_12_black_bold\"> Mentoring In:</td>";
			strValue += " <td align=\"left\"><span class=\"Arial_12_black_normal\">"+strmen1+"</span></td>";
			strValue += "  </tr>";
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"Arial_Narrow_12_black_bold\"> Queries In:</td>";
			strValue += " <td align=\"left\"><span class=\"Arial_12_black_normal\">"+strque1+"</span></td>";
			strValue += "  </tr>";
			
			strValue += " </table>";
			strValue += "</td>";
			strValue += " <td width=\"0%\" valign=\"top\" bgcolor=\"#F0F0F0\">&nbsp;</td>";
			strValue += " <td width=\"40%\" valign=\"top\">";
			strValue += "<table width=\"100%\" align=\"center\"  class=\"headingsBlack\">";
			strValue += "<td colspan=\"2\" align=\"center\" valign=\"top\" class=\"Arial_Narrow_12_black_bold\">";
			strValue += "User Rights</td>";
			strValue += "</tr>";
			strValue +="<tr>";
			strValue +="<td colspan=\"2\" align=\"left\" class=\"var_12_bold_orange\"><hr color=\"#C1C1C1\"></td>";
			strValue +="</tr>";
			//userRightVec, userCategoryVec
			
		if(userRightVec.size()==0){
				
				strValue += "<tr>";
				strValue += "<td colspan=\"2\" align=\"left\" class=\"Arial_Narrow_12_black_bold\">&nbsp;&nbsp;<span class=\"Arial_Narrow_12_black_bold\"><u>You Have All Rights</u></span></td>";
				strValue += "</tr>";
				
				strValue += "<tr>";
				strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
				strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Account Management</span></td>";
				strValue += "</tr>";
				
				strValue += "<tr>";
				strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
				strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Users</span></td>";
				strValue += "</tr>";
				
				strValue += "<tr>";
				strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
				strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Create A User</span></td>";
				strValue += "</tr>";
				
				strValue += "<tr>";
				strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
				strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Delete A User</span></td>";
				strValue += "</tr>";
				
				strValue += "<tr>";
				strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
				strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Modify A User Detail</span></td>";
				strValue += "</tr>";
				
				strValue += "<tr>";
				strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
				strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Change Other User Password</span></td>";
				strValue += "</tr>";
				
				strValue += "<tr>";
				strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
				strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Test Creation</span></td>";
				strValue += "</tr>";
				
				strValue += "<tr>";
				strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
				strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Mentoring</span></td>";
				strValue += "</tr>";
				
				strValue += "<tr>";
				strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
				strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Queries Allotment</span></td>";
				strValue += "</tr>";
				
				strValue += "<tr>";
				strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
				strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;MIS</span></td>";
				strValue += "</tr>";
				
				strValue += "<tr>";
				strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
				strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Queries Management</span></td>";
				strValue += "</tr>";
				
			}
			
			if(userRightVec.size()>=13)
			{
				/*strValue += "<tr>";
				strValue += "<td colspan=\"2\" align=\"left\" class=\"Arial_Narrow_12_black_bold\">User Group Name:&nbsp;&nbsp;<span class=\"Arial_12_black_normal\">&nbsp;"+userRightVec.elementAt(1).toString().trim()+"</span></td>";
				strValue += "</tr>";
				strValue += "<tr>";
				strValue += "<td colspan=\"2\" align=\"left\" class=\"Arial_Narrow_12_black_bold\"><u>Group Detail</u>&nbsp;</td>";
				strValue += "</tr>";
				*/
				if(Integer.parseInt(userRightVec.elementAt(2).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Account Management</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(3).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Users</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(4).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Create A User</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(5).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Delete A User</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(6).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Modify A User Detail</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(7).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Change Other User Password</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(8).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Queries Allotment</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(9).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;MIS(Management Information System)</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(10).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Queries Management</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(11).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Test Creation</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(12).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;Mentoring</span></td>";
					strValue += "</tr>";
				}
			}
			if(userCatVec.size()>=3)
			{
				strValue +="<tr>";
				strValue +="<td colspan=\"2\" align=\"left\" class=\"var_12_bold_orange\"><hr color=\"#C1C1C1\"></td>";
				strValue +="</tr>";
				strValue +="<tr>";
				strValue +="<td colspan=\"2\" align=\"left\" class=\"var_12_bold_orange\">";
				strValue += "<table width=\"100%\" align=\"center\"  class=\"headingsBlack\">";				
				strValue += "<tr>";
				strValue += "<td colspan=\"2\" align=\"left\" class=\"Arial_Narrow_12_black_bold\"><u>Work Category</u>&nbsp;</td>";
				strValue += "</tr>";
				//userCatVec
				strValue += "<tr>";
				strValue += "<td width=\"40%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">Admin Category&nbsp;</td>";
				strValue += "<td width=\"60%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;"+userCatVec.elementAt(1).toString().trim()+"</span></td>";
				strValue += "</tr>";
				if(!userCatVec.elementAt(2).toString().trim().equalsIgnoreCase(""))
				{
					strValue += "<tr>";
					strValue += "<td width=\"40%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">Admin Name&nbsp;</td>";
					strValue += "<td width=\"60%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;"+userCatVec.elementAt(2).toString().trim()+"</span></td>";
					strValue += "</tr>";
				}
				strValue += "<tr>";
				strValue += "<td width=\"40%\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">Your Category&nbsp;</td>";
				strValue += "<td width=\"60%\" align=\"left\"><span class=\"Arial_12_black_normal\">&nbsp;"+userCatVec.elementAt(0).toString().trim()+"</span></td>";
				strValue += "</tr>";
				strValue += " </table>";
				strValue +="</td></tr>";
				
			}
			
			strValue += " </table>";
			strValue += "</td>";
			strValue += " </tr>";
			strValue +="<tr>";
			strValue +="<td colspan=\"3\" align=\"left\" class=\"var_12_bold_orange\"><hr color=\"#C1C1C1\"></td>";
			strValue +="</tr>";
			strValue += " </table>";
			
			
		}
		if(remander==0)
		{
			strValue += "<tr style=\"display:none\" align=\"center\">";
			strValue += "<td height=\"50\" colspan=\"5\"></td>";
			strValue += "</tr>";
		}
		strValue += "</table>";
		
		return strValue;
	}
	
	
	private String getPages(int minVal, int maxVal, int numSize)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveAdvtUserDetailURL('advtUserdetail.do?'+this.value);\">";
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
