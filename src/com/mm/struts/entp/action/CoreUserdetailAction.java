package com.mm.struts.entp.action;  

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

public class CoreUserdetailAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		
		String compId =(String)session.getAttribute("compId");
		
		String task = request.getParameter("task").trim();
		int userid = Integer.parseInt(request.getParameter("userid").trim());
		//System.out.println("task..........."+task);
		//System.out.println("userid..........."+userid);
		
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 100;
		
		EntpMaster entpMaster = new EntpMaster();	
		RootMaster ob = new RootMaster();
		Vector<String> paramVec1 = new Vector<String>();
		paramVec1.add(compId);
		int totalRow = entpMaster.getUserCount(getDataSource(request, "entp"), paramVec1);	
		//System.out.println("totalRow..........."+totalRow);
		String strPageHtml = getPages(minVal, maxVal, totalRow);
		
		Vector userVec = ob.getUserInfo(getDataSource(request, "entp"), userid);
		//System.out.println("userVec..........."+userVec);
		//User Information
    	int UserCityId = Integer.parseInt(userVec.elementAt(6).toString().trim());
    	String UserCityname = ob.getPlaceName(getDataSource(request, "entp"), UserCityId);		    	
    	int UserStateId = Integer.parseInt(userVec.elementAt(7).toString().trim());
    	String UserStateName = ob.getStateName(getDataSource(request, "entp"), UserStateId);		    	
    	int UserCountryId = Integer.parseInt(userVec.elementAt(11).toString().trim());
    	String UserCountryname = ob.getCountryName(getDataSource(request, "entp"), UserCountryId);
    	
    	int numRId = Integer.parseInt(userVec.elementAt(1).toString().trim());
    	Vector userRightVec = entpMaster.getUserGroupDetails(getDataSource(request, "entp"), Integer.parseInt(compId.trim()), numRId, "Details");
    	
    	//System.out.println("userRightVec "+userVec.elementAt(1).toString().trim());
    	//System.out.println("userRightVec...........size............. "+userRightVec.size());
    	//System.out.println("compId "+compId);
    	//System.out.println("numRId "+numRId);
    	//System.out.println("userid "+userid);
    	Vector<String> infoVec = new Vector<String>();    	
    	infoVec.add(UserCityname);//0// Consumer City nane
    	infoVec.add(UserStateName);//1// Consumer State nane
    	infoVec.add(UserCountryname);//2// Consumer Country nane
    	
		String strUsers = getUserDetail(userVec,infoVec, minVal, strPageHtml, remander,userRightVec);
		//System.out.println("Users "+strUsers);
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
	
	private String getUserDetail( Vector userVec,Vector infoVec, int minVal, String strPageHtml, int remander,Vector userRightVec)
	
	{
		
		
		String strValue = "<table width=\"640\">";
		if(userVec.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"50\" valign=\"center\" colspan=\"7\" class=\"bold\"> No Data Found</td>";
			strValue += "</tr>";
		}
		else
		{
			strValue += "<table width=\"100%\" align=\"center\"  >";
			strValue += "<tr height=\"20\">";
			strValue += "<td width=\"60%\" valign=\"top\" >";
			strValue += "<table width=\"100%\" align=\"center\" >";
			strValue += "<tr>";
			strValue += "<td colspan=\"2\" align=\"left\" valign=\"top\" class=\"bold\">";
			strValue += "User Information</td>";
			strValue += " </tr>";
			strValue +="<tr>";
			strValue +="<td colspan=\"2\" align=\"left\" class=\"bold\"><hr color=\"#ff722b\"></td>";
			strValue +="</tr>";
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td width=\"200\" align=\"right\" class=\"bold\">Login Name:</td>";
			strValue += "  <td width=\"125\" align=\"left\"><span >"+userVec.elementAt(2).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\"> Password:</td>";
			String password = userVec.elementAt(3).toString().trim();
			String strPass ="";
			for(int i=0; i < password.length(); i++){
				strPass = strPass +"*";
			}
			strValue += "  <td align=\"left\"><span >"+strPass+"</span></td>";
			strValue += "  </tr>";
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\"> First Name:</td>";
			strValue += "  <td align=\"left\"><span >"+userVec.elementAt(4).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\"> Last Name:</td>";
			strValue += "  <td align=\"left\"><span >"+userVec.elementAt(5).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			
			strValue += " <tr height=\"20\">";
			strValue += " <td align=\"right\" class=\"bold\"> Home Address:</td>";
			strValue += "  <td align=\"left\"><span >"+userVec.elementAt(9).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\"> Country:</td>";
			strValue += " <td align=\"left\"><span >"+infoVec.elementAt(2).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\"> State:</td>";
			strValue += "  <td align=\"left\">";
			strValue += "	<span >"+infoVec.elementAt(1).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\"> City:</td>";
			strValue += "  <td align=\"left\">";
			strValue += "	<span >"+infoVec.elementAt(0).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\"> Pin Code:</td>";
			strValue += " <td align=\"left\"><span >"+userVec.elementAt(13).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\"> E-Mail Id:</td>";
			strValue += "  <td align=\"left\"><span >"+userVec.elementAt(8).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			String temPno1="Not mention";
		    if(userVec.elementAt(12).toString().trim().length()!=0)
		    {
				////System.out.println("insite"); 
				String temPno=userVec.elementAt(12).toString().trim().trim();
				temPno1 = temPno.replace("~", "-");
				strValue += " <tr height=\"20\">";
				strValue += "  <td align=\"right\" class=\"bold\"> Phone No:</td>";
				strValue += "  <td align=\"left\">";
				strValue += "	<span >"+temPno1+"</span> </td> ";
				strValue += " </tr>";
		    }
			strValue += " <tr height=\"20\">";
			strValue += " <td align=\"right\" class=\"bold\"> Mobile No:</td>";
			String temMno1="Not mention";
		    if(userVec.elementAt(10).toString().trim().length()!=0){
				//System.out.println("insite"); 
				String temPno=userVec.elementAt(10).toString().trim().trim();
				temMno1 = temPno.replace("~", "-");
				
			}
			strValue += "  <td align=\"left\"><span >"+temMno1+"</span> </td>";         
			strValue += "  </tr>";
			strValue += " </table>";
			strValue += "</td>";
			strValue += " <td width=\"0%\" valign=\"top\" bgcolor=\"#F0F0F0\">&nbsp;</td>";
			strValue += " <td width=\"40%\" valign=\"top\">";
			strValue += "<table width=\"100%\" align=\"center\"  >";
			strValue += "<td colspan=\"2\" align=\"left\" valign=\"top\" class=\"bold\">";
			strValue += "User Rights</td>";
			strValue += "</tr>";
			strValue +="<tr>";
			strValue +="<td colspan=\"2\" align=\"left\" class=\"bold\"><hr color=\"#ff722b\"></td>";
			strValue +="</tr>";
			
			if(userRightVec.size()==0){
				strValue += "<tr>";
				strValue += "<td colspan=\"2\" align=\"left\" class=\"bold\">&nbsp;&nbsp;<span >&nbsp;You Have All Rights</span></td>";
				strValue += "</tr>";
				
			}
				
			else if(userRightVec.size()>=11)
			{
				strValue += "<tr>";
				strValue += "<td colspan=\"2\" align=\"left\" class=\"bold\">User Group Name:&nbsp;&nbsp;<span >&nbsp;"+userRightVec.elementAt(1).toString().trim()+"</span></td>";
				strValue += "</tr>";
				strValue += "<tr>";
				strValue += "<td colspan=\"2\" align=\"left\" class=\"bold\"><u>Group Detail</u>&nbsp;</td>";
				strValue += "</tr>";
				if(Integer.parseInt(userRightVec.elementAt(2).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span >&nbsp;Account Management</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(3).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span >&nbsp;Users</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(4).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span >&nbsp;Create A User</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(5).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span >&nbsp;Delete A User</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(6).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span >&nbsp;Modify A User Detail</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(7).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span >&nbsp;Change Other User Password</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(8).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span >&nbsp;Query Management</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(9).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span >&nbsp;MIS(Management Information System)</span></td>";
					strValue += "</tr>";
				}
				if(Integer.parseInt(userRightVec.elementAt(10).toString().trim())==1)
				{
					strValue += "<tr>";
					strValue += "<td width=\"20%\" align=\"right\" class=\"bold\">&nbsp;</td>";
					strValue += "<td width=\"80%\" align=\"left\"><span >&nbsp;Query Management</span></td>";
					strValue += "</tr>";
				}
			}
			
			
			strValue += " </table>";
			strValue += "</td>";
			strValue += " </tr>";
			strValue +="<tr>";
			strValue +="<td colspan=\"3\" align=\"left\" class=\"bold\"><hr color=\"#ff722b\"></td>";
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

	
	
	
	
	
	
	
	
	
	
	
	/*{
		
		
		String strValue = "<table width=\"640\">";
		if(userVec.size()==0)
		{
			strValue += "<tr align=\"center\">";
			strValue += "<td height=\"50\" valign=\"center\" colspan=\"7\" class=\"bold\"> No Data Found</td>";
			strValue += "</tr>";
		}
		else
		{


			strValue += "<table width=\"100%\" align=\"center\"  class=\"headingsBlack\">";
			strValue += "<tr height=\"20\">";
			strValue += "<td height=\"20\" colspan=\"2\" valign=\"top\" ><span class=\"bold\"></span><br>";
			strValue += " Fields marked with an asterisk (*) are mandatory</td>";
			strValue += " <td colspan=\"3\" valign=\"top\"></td>";
			strValue += " </tr>";
			strValue += "<tr height=\"20\">";
			strValue += "<td height=\"20\" colspan=\"2\">&nbsp;&nbsp;</td>";			
			strValue += " <td colspan=\"3\" valign=\"top\"></td>";
			strValue += " </tr>";
			strValue += " <tr height=\"20\">";
			strValue += "  <td width=\"200\" align=\"right\" class=\"bold\">*Login Name:</td>";
			strValue += "  <td width=\"125\" align=\"left\"><span >"+userVec.elementAt(2).toString().trim()+"</span></td>";
			strValue += "  <td width=\"125\" align=\"right\" class=\"bold\">*State:</td>";
			strValue += "  <td colspan=\"2\" align=\"left\">";
			strValue += "	<span >"+infoVec.elementAt(1).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\">* Password:</td>";
			String password = userVec.elementAt(3).toString().trim();
			String strPass ="";
			for(int i=0; i < password.length(); i++){
				strPass = strPass +"*";
			}  
				
			strValue += "  <td align=\"left\"><span ><html:password >"+strPass+"</html:password></span></td>";
			strValue += "  <td align=\"right\" class=\"bold\">*City:</td>";
			strValue += "  <td colspan=\"2\" align=\"left\">";
			strValue += "	<span >"+infoVec.elementAt(0).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\">*First Name:</td>";
			strValue += "  <td align=\"left\"><span >"+userVec.elementAt(4).toString().trim()+"</span></td>";
			strValue += "  <td align=\"right\" class=\"bold\">* Pin Code:</td>";
			strValue += " <td colspan=\"2\" align=\"left\"><span >"+userVec.elementAt(13).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\">*Last Name:</td>";
			strValue += "  <td align=\"left\"><span >"+userVec.elementAt(5).toString().trim()+"</span></td>";			
			strValue += "  <td align=\"right\" class=\"bold\">* E-Mail Id:</td>";
			strValue += "  <td colspan=\"2\" align=\"left\"><span >"+userVec.elementAt(8).toString().trim()+"</span></td>";
			strValue += "  </tr>";
			strValue += " <tr height=\"20\">";
			strValue += " <td align=\"right\" class=\"bold\">*Home Address:</td>";
			strValue += "  <td align=\"left\"><span >"+userVec.elementAt(9).toString().trim()+"</span></td>";
			strValue += "  <td align=\"right\" class=\"bold\"> Phone No:</td>";
			strValue += "  <td colspan=\"2\" align=\"left\">";
			String temPno1="Not mention";
			if(userVec.elementAt(12).toString().trim().length()!=0){
				//System.out.println("insite"); 
				String temPno=userVec.elementAt(12).toString().trim();
				  temPno1 = temPno.replace("~", "-");
				//System.out.println(temPno1); 
			}
			strValue += "	<span >"+temPno1+"</span> </td> ";
			strValue += " </tr>";
			strValue += " <tr height=\"20\">";
			strValue += "  <td align=\"right\" class=\"bold\">* Country:</td>";
			strValue += " <td align=\"left\"><span >"+infoVec.elementAt(2).toString().trim()+"</span></td>";
			strValue += " <td align=\"right\" class=\"bold\">*Mobile No:</td>";
			String temMno1="Not mention";
			if(userVec.elementAt(10).toString().trim().length()!=0){
				//System.out.println("insite"); 
				String temPno=userVec.elementAt(10).toString().trim();
				temMno1 = temPno.replace("~", "-");
				//System.out.println(temPno1); 
			}
			strValue += "  <td colspan=\"2\" align=\"left\"><span >"+temMno1+"</span> </td>";         
			strValue += "  </tr>";
			
			strValue += "<tr height=\"20\" align=\"center\">";
			strValue += " <td colspan=\"5\" height=\"20\">";
			strValue += "  </td>";
			strValue += " </tr>";
			
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
	*/
	
	private String getPages(int minVal, int maxVal, int numSize)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveEntpUserDetailURL('coreUserdetail.do?'+this.value);\">";
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
