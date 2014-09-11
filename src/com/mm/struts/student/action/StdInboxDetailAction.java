

package com.mm.struts.student.action;

import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.*;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.core.master.*;

/** 
 * 
 * Creation date: 07-04-2007
 * 
 * 
 * 
 */

public class StdInboxDetailAction extends Action {
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
		
		HttpSession session = request.getSession();

		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		String copid = (request.getParameter("numCid")!=null) ? request.getParameter("numCid").trim() : (String)session.getAttribute("numCid");
		String Ctype = (request.getParameter("Ctype")!=null) ? request.getParameter("Ctype").trim() : "Company";
		int cid=Integer.parseInt(copid);
		DataSource ds = getDataSource(request,"student");
				
		
		RootMaster rootMaster = new RootMaster();
		IndvMaster indvMaster = new IndvMaster();
		Vector Complaintinfo=rootMaster.getComplaintDetails(ds, cid);
		//System.out.println("....Complaintinfo.....Complaintinfo........"+Complaintinfo);
		
		 int respid = Integer.parseInt(Complaintinfo.elementAt(11).toString());
			
		
		 Vector userinfo=indvMaster.getUserInfo(ds, respid);
		 String fname=userinfo.elementAt(4).toString().trim();
		 String lname=userinfo.elementAt(5).toString().trim();
		 String contact=userinfo.elementAt(10).toString().trim();
		
		
		 int cid1 = Integer.parseInt(userinfo.elementAt(0).toString());
		 Vector cinfo=rootMaster.getCompanyDetail(ds, cid1);
		 String cname=cinfo.elementAt(1).toString().trim();
		 String add=cinfo.elementAt(2).toString().trim();
		 String email=cinfo.elementAt(8).toString().trim();
		
		 Vector<String> respDataVec = new Vector<String>();
			respDataVec.add(Complaintinfo.elementAt(0).toString().trim());
			respDataVec.add("Consumer");
			Vector responseVec = rootMaster.getResponseListNew(getDataSource(request, "student"), respDataVec);
			Vector <String> infoVec = new Vector<String>();
			int totleRsponse = responseVec.size();
			int indvResponse = 0;
			int advtResponse = 0;
			int entpResponse = 0;
			
			
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
				else if(comp_type.equalsIgnoreCase("Corporates"))
				{
					advtResponse++;
				}
			}
			infoVec.add(String.valueOf(totleRsponse));	//totle no of responses
			infoVec.add(String.valueOf(advtResponse));	//Brand Response		
			infoVec.add(String.valueOf(entpResponse));	//Core Response	
			infoVec.add(String.valueOf(indvResponse));	//Consumer response
		
		
			int contryId = Integer.parseInt(cinfo.elementAt(6).toString().trim());//dealer contryId id
			
			String contry = rootMaster.getCountryName(getDataSource(request, "student"),contryId);
			int stateId = Integer.parseInt(cinfo.elementAt(5).toString().trim());//dealer stateId id
			
			String state = rootMaster.getStateName(getDataSource(request, "student"),stateId);
			
			int cityId = Integer.parseInt(cinfo.elementAt(4).toString().trim());//dealer cityId id
		
			String city = rootMaster.getPlaceName(getDataSource(request, "student"),cityId);
			
			Vector commentVec=indvMaster.getCommentList(ds, copid);
			
		
		 Vector inputVec=new Vector();
		inputVec.add(cname);
		inputVec.add(add);
		inputVec.add(contry);
		inputVec.add(state);
		inputVec.add(city);
		inputVec.add(email);
		inputVec.add(contact);
		inputVec.add(fname);
		inputVec.add(lname);
		inputVec.add(Ctype);
		String strComplaintHtml = "";
		
				
		
		strComplaintHtml = getMentorDetail(request, inputVec, commentVec,  remander,Complaintinfo,responseVec,cid,infoVec);
		 //////System.out.println("....strComplaintHtml............."+strComplaintHtml);
		
		/**
	     * setContentType - text/html to write String on page.
	     * PrintWriter - This line use to get writer to write on page.
	     * out.println - use to write string. 
	     * 
	     */
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strComplaintHtml);
	    out.flush();
		
		// TODO Auto-generated method stub
		return null;
	}
	
	 
	private String getMentorDetail( HttpServletRequest request, Vector inputVec,Vector commentVec, int remander,Vector Complaintinfo,Vector responseVec,int cid,Vector infoVec){
		String strValue ="";
		
		DataSource ds = getDataSource(request,"student");
		IndvMaster indvMaster = new IndvMaster();
		 RootMaster rootMaster=new RootMaster();
		
		if(Complaintinfo.size()==0)
		{
					strValue += "<tr align=\"center\">";
					strValue += "<td height=\"20\" valign=\"center\" colspan=\"2\" > No Data Found</td>";
					strValue += "</tr>";
					
		}
		
		
		////////////////////////////////////////////////////////////////////
 strValue+="<table  height = \"350\" width=\"100%\" >";
		 
 
	strValue += "<tr align=\"center\">";
	strValue += "<td height=\"20\" valign=\"center\" colspan=\"2\">";
	strValue+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
	 strValue+="<tr>";
	   strValue+="<td>&nbsp;</td>";
	   strValue+="<td width=\"23%\"><div align=\"right\" class=\"bold\" > TO: </div></td>";
	   strValue+="<td width=\"1%\">&nbsp;</td>";
	   strValue+="<td width=\"74%\"><div align=\"left\" >"+inputVec.elementAt(0).toString().trim()+" , "+inputVec.elementAt(7).toString().trim()+"&nbsp;"+inputVec.elementAt(8).toString().trim()+"</div></td>";
	   strValue+="<td>&nbsp;</td>";
	 strValue+="</tr>";
	 strValue+="<tr>";
	   strValue+="<td>&nbsp;</td>";
	   strValue+="<td><div align=\"right\" class=\"bold\"> Query Title: </div></td>";
	   strValue+="<td>&nbsp;</td>";
	   strValue+="<td><div align=\"left\" >"+Complaintinfo.elementAt(1).toString().trim()+" </div></td>";
	   strValue+="<td>&nbsp;</td>";
	 strValue+="</tr>";
	 strValue+="<tr>";
	   strValue+="<td>&nbsp;</td>";
	   strValue+="<td><div align=\"right\" class=\"bold\"> Query ID: </div></td>";
	   strValue+="<td>&nbsp;</td>";
	   strValue+="<td><div align=\"left\" >"+Complaintinfo.elementAt(17).toString().trim()+"</div></td>";
	   strValue+="<td>&nbsp;</td>";
	 strValue+="</tr>";
	 strValue+="<tr>";
	   strValue+="<td>&nbsp;</td>";
	   strValue+="<td><div align=\"right\" class=\"bold\"> Query Originated Date: </div></td>";
	   strValue+="<td>&nbsp;</td>";
	   
	   String newdate[]=Complaintinfo.elementAt(5).toString().split("-");
	   
	   strValue+="<td><div align=\"left\" >"+newdate[2]+"-"+newdate[1]+"-"+newdate[0]+"</div></td>";
	   strValue+="<td>&nbsp;</td>";
	 strValue+="</tr>";
	 
	 strValue+="<tr>";
	  strValue+="<td colspan=\"5\"><hr color=\"#ff722b\"></td>";
	 strValue+="</tr>";
	 
	  String strFMsgcompl = "";
		String strMessagecompl = Complaintinfo.elementAt(2).toString()!=null ? Complaintinfo.elementAt(2).toString().trim():"";
		
		for(int x=0; x<strMessagecompl.length(); x++)
		{ 
			int numcompl = (int)strMessagecompl.charAt(x);
			if(numcompl == 10)
			{
				strFMsgcompl = strFMsgcompl+"<br><br>";
			}
			else
			{
				strFMsgcompl = strFMsgcompl+strMessagecompl.charAt(x);
			}
		}
	 strValue+="<tr height=\"60\">";
	   strValue+="<td valign=\"top\">&nbsp;</td>";
	   strValue+="<td valign=\"top\"><div align=\"right\" class=\"bold\" > Query Text: </div></td>";
	   strValue+="<td valign=\"top\">&nbsp;</td>";
	   strValue+="<td valign=\"top\"><div align=\"justify\"  valign=\"top\">"+strFMsgcompl+"</div></td>";
	   strValue+="<td valign=\"top\">&nbsp;</td>";
	 strValue+="</tr>";
	 strValue+="<tr>";
	   strValue+="<td>&nbsp;</td>";
	   strValue+="<td><div align=\"right\"></div></td>";
	   strValue+="<td>&nbsp;</td>";
	   strValue+="<td><div align=\"left\"></div></td>";
	   strValue+="<td>&nbsp;</td>";
	 strValue+="</tr>";
	 
	 strValue+="</table>";
	strValue += "</td>";
	strValue += "</tr>";
	if(remander==0)
	{
		strValue += "<tr style=\"display:none\" align=\"center\">";
		strValue += "<td height=\"50\" colspan=\"2\"></td>";
		strValue += "</tr>";
	}
	
	 int z=0;
 

		     
				     
	      
			
			
	        
	      
			 strValue += "<tr align=\"left\" width=\"100%\">";
		        strValue += "<td colspan=\"2\"  width=\"100%\"><hr color=\"#ff722b\"></td></tr>";
				
		        	
			        
			    	strValue += "<tr align=\"left\" width=\"100%\">";
			        strValue += "<td colspan=\"2\" width=\"100%\">";
			        strValue+="<table cellpading=\"0\" cellspacing=\"0\" width=\"100%\">";
			        
			        strValue +="<tr height=\"\" id=\"resp\" >";
					strValue +="<td colspan=\"2\"  align=\"left\" > Number Of Response By </td>";
					strValue +="</tr>";
					strValue +="<tr align=\"left\" valign=\"top\" id=\"resp\" >";
					strValue +="<td colspan=\"2\" class=\"bold\">";
					strValue +="Student:&nbsp;<span class=\"bold\">"+infoVec.elementAt(3).toString().trim()+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					strValue +=""+inputVec.elementAt(9).toString().trim()+":&nbsp;<span class=\"bold\">"+infoVec.elementAt(1).toString().trim()+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					strValue +="Total:&nbsp;<span class=\"bold\">"+infoVec.elementAt(0).toString().trim()+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					strValue +="&nbsp;<span class=\"bold\"></span>";
					strValue +="</td>";
					strValue +="</tr>";
					
					
					
					strValue += "<tr align=\"left\"  >";
			        strValue += "<td colspan=\"2\" valign=\"top\" align=\"left\" ><img  onclick=\"showRes()\" style=\"cursor:hand\" src=\"../images/giveresponse.gif\" width=\"63\" height=\"19\" border=\"0\" alt=\"Click here to give response\"></img></td>";
			        strValue += "</tr>";
			      
					
			        strValue+="<tr>";
			  	  strValue+="<td colspan=\"2\"><hr color=\"#ff722b\"></td>";
			  	 strValue+="</tr>";
		
				
				
				
				 
						
						//System.out.println("ist>>>>>>>>>>>ZZZZZZZZZZZZ"+z);
						strValue+="<tr id=\"response\" align=\"center\" style=\"display:none\">";
						//System.out.println("2nd>>>>>>>>>>>ZZZZZZZZZZZZ"+z);
						strValue+="<td valign=\"top\" colspan=\"3\"><form id=\""+z+"\" name=\"frmreply\" method=\"post\" action=\"queryResponseSuccess.do\" >";
						strValue+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
						strValue+=" <tr>";
						strValue+=" <td align=\"center\" colspan=\"4\"><textarea id=\"responsetext"+z+"\" name=\"responsetext\" cols=\"105\" rows=\"5\" ></textarea></td>";
						strValue+=" </tr>";
						strValue+=" <tr style=\"display:none\" >";
						strValue+=" <td colspan=\"4\" align=\"left\"  >";
						strValue+=" <input type=\"hidden\" name=\"flag\" id=\"flag\" value=\"\" >";
						strValue+=" </td>";
						strValue+="  </tr>";
						strValue+="<input type=\"hidden\" name=\"respType\" value=\"Consumer\">";
						strValue+="<input type=\"hidden\" name=\"respid\" value=\"0\">";
						strValue+="<input type=\"hidden\" name=\"compId\" value=\""+cid+"\">";
						
						
						strValue+="<tr>";
						 
			        		strValue+="<td colspan=\"4\" align=\"center\"><input name=\"Submit\" type=\"submit\"  value=\"Submit\" >&nbsp;&nbsp;&nbsp;";
			        		strValue+="<input name=\"cancel\" type=\"button\"   value=\"Cancel\" onClick=\"reshide()\"></td>";
			        		
			        		strValue+="  </tr>";	
						
						
						strValue+=" </table>";
						strValue+="</form>";
						strValue+=" </td>";
						strValue+="</tr>";
				String bgrow="#CCECFF",bgtab="#F0FAF9";
				if(responseVec.size()>0)
				{
				for(int i=0;i<responseVec.size();i++)
				{
					z=z+1;
					Vector tempVec =(Vector)responseVec.elementAt(i);
					int responseId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
					String resptext = tempVec.elementAt(1).toString().trim();
					String respDate = tempVec.elementAt(2).toString().trim();
					String comp_type = tempVec.elementAt(5).toString().trim();
					String fname = tempVec.elementAt(6).toString().trim();
					String lname = tempVec.elementAt(7).toString().trim();
					int cityId = Integer.parseInt(tempVec.elementAt(8).toString().trim());
					String companyName = tempVec.elementAt(9).toString().trim();
					String cityName = rootMaster.getPlaceName(getDataSource(request, "student"),cityId);
					int resposne_flag = Integer.parseInt(tempVec.elementAt(10).toString().trim());
					////////System.out.println("resposne_flagresposne_flag=>>>>>>>"+resposne_flag);
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
					if(comp_type.equalsIgnoreCase("Corporetes"))
					{
						bgrow="#CCECFF";
						bgtab="#F0FAF9";
						comptype = "Company";
						
					}
					else if(comp_type.equalsIgnoreCase("Advertiser"))
					{
						bgrow="#FFEBFF";
						bgtab="#FFF7FF";
						comptype = "College";
					}
					else if(comp_type.equalsIgnoreCase("Consumer"))
					{
						bgrow="#F9E1CC";
					    bgtab="#FFFAF6";
					    comptype = "Student";
					}
					strValue+="<tr>";
					strValue+="<td colspan=\"2\" width=\"100%\" bgcolor=\""+bgtab+"\">";
					strValue+="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
					strValue+="<tr bgcolor=\""+bgrow+"\">";
					strValue+="<td  height=\"22\" width=\"100%\" colspan=\"2\" align=\"left\" bgcolor=\""+bgrow+"\" class=\"bold\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr>"; 
					strValue+="<td width=\"39%\" align=\"left\" >";
					
					
					
					if(comptype.equalsIgnoreCase("Student"))
					{
						strValue+="&nbsp;Reply By "+comptype+":";
					}
					else
					{
						strValue+="&nbsp;Response By "+comptype+":";
					}
					strValue+="<span >";
					
					if((comptype.equalsIgnoreCase("Company"))||(comptype.equalsIgnoreCase("College")))
					{
						strValue+=companyName;
					}
					else
					{
						strValue+=fname+" "+lname;
					}
					strValue+="</span></td>";
					strValue+="<td align=\"right\" width=\"15%\" class=\"bold\">&nbsp;</td>";
					strValue+="<td   align=\"left\" ></td>";
					strValue+="<td align=\"left\" width=\"45%\" class=\"bold\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date:<span  width=\"15%\" align=\"left\">"+respDate+"</span></td>";
					
					strValue += " </tr></table></td></tr>";
					
					strValue += " <tr>";
					strValue += " <td colspan=\"2\"  align=\"left\" valign=\"top\" class=\"bold\" width=\"100%\" height=\"45\"> <table height=\"100%\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td  width=\"15%\"  valign=\"top\">&nbsp;Response Text: </td>";
					strValue += " <td align=\"left\" valign=\"top\"><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td align=\"left\" valign=\"top\" width=\"100%\"  >"+strFMsg3+"</td>";
					strValue += " </tr></table></td></tr></table></td></tr>";
					
					
					if((!comptype.equalsIgnoreCase("Student")) && resposne_flag == 0)
					{
						strValue+="<tr>";
						strValue+="<td  height=\"20\" align=\"right\" width=\"90%\"  class=\"bold\"></td>";
						strValue+="<td  width=\"10%\" align=\"left\"  class=\"bold\"><img src=\"../images/reply.gif\" alt=\"Click here to give reply\" width=\"53\" height=\"19\" border=\"0\" id=\""+z+"\" onclick=\"showAddNew(this.id)\" style=\"cursor:hand\" ></img></td>";
						strValue+="</tr>";
					}
					//////System.out.println("Value For ZZZ in Response>>>>>>>>>>>>>"+z);
					strValue+="<tr  id=\"status"+z+"\" style=\"display:inline\" align=\"center\">";
					strValue+="<td width=\"100%\" colspan=\"2\" height=\"20\"></td>";
					
					strValue+="</tr>";
					
					strValue+="<tr id=\"status1"+z+"\" style=\"display:none\" align=\"center\">";
					strValue+="<td height=\"20\"  colspan=\"2\"></td>";
					strValue+="</tr>";
					strValue+="<tr id=\"rowid"+z+"\" align=\"center\" style=\"display:none\">";
					//System.out.println("zzzzzzzzzzzzzzz on detail"+z);
					strValue+="<td colspan=\"2\" valign=\"top\" ><a name=\"<%=z%>\">";
					strValue+="<form id=\""+z+"\" name=\"frmreply\" method=\"post\" action=\"queryResponseSuccess.do\">";
					strValue+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
					strValue+="<tr>";
					strValue+="<td height=\"22\" width=\"100%\"  colspan=\"2\" align=\"left\"><strong class=\"bold\">&nbsp;&nbsp;&nbsp;Write  reply to "+inputVec.elementAt(9).toString().trim()+"</strong></td>";
					strValue+="</tr>";
					strValue+="<tr>";
					strValue+="<td align=\"center\" width=\"100%\"  colspan=\"2\"><textarea id=\"responsetext"+z+"\" name=\"responsetext\" cols=\"105\" rows=\"5\" ></textarea></td>";
					strValue+="</tr>";
					strValue+="<tr>";
					strValue+="<td align=\"center\" width=\"100%\"  colspan=\"2\"><input name=\"Submit\" type=\"submit\"  value=\"Submit\" >";
					strValue+="&nbsp;&nbsp;&nbsp;<input name=\"cancel\" type=\"button\"  id=\""+z+"\" value=\"Cancel\" onClick=\"hideAddNew(this.id)\"></td>";
					strValue+="<input type=\"hidden\" name=\"respid\" value=\""+responseId+"\">";
					strValue+="<input type=\"hidden\" name=\"compId\" value=\""+cid+"\">";
					strValue+="</tr>";
					strValue+="</table>";
					
					strValue+="</form></a></td>";
					strValue+="</tr>";
					strValue+="</table>";
					strValue+="</td></tr>";
				}
		
	}else
	{
		strValue += "<tr align=\"center\">";
		strValue += "<td height=\"20\" valign=\"center\" colspan=\"2\" >  No Response Yet. </td>";
		strValue += "</tr>";
	}
			strValue+="<table cellpading=\"0\" cellspacing=\"0\" width=\"100%\">";
			
			  if(commentVec.size()>0)
				{
							
		        	strValue+="<tr>";
			        strValue += "<tr align=\"left\" >";
			        strValue += "<td width=\"35%\" align=\"left\" >&nbsp; Comments:</td>";
			        strValue += "<td  width=\"65%\" align=\"left\" ></td>";
			        strValue += "</tr>";
					for(int i=0;i<commentVec.size();i++)
					{
						
						
						Vector tempVec =(Vector)commentVec.elementAt(i);
						int responseId = Integer.parseInt(tempVec.elementAt(5).toString().trim());
						String resptext = tempVec.elementAt(0).toString().trim();
						String respDate = tempVec.elementAt(1).toString().trim();
						int cuser_id = Integer.parseInt(tempVec.elementAt(3).toString().trim());
							Vector userinfo=indvMaster.getUserInfo(ds, cuser_id);
							String fname=userinfo.elementAt(4).toString().trim();
							String lname=userinfo.elementAt(5).toString().trim();
							
						
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
						
						strValue += "<tr align=\"left\" bgcolor=\"#BBF3E4\" height=\"25\" >";
						strValue+="<td width=\"39%\" align=\"left\" class=\"bold\" >&nbsp;Comment By  :<span >"+fname+" "+lname+"</span></td>";
						strValue+="<td align=\"center\" width=\"60%\" class=\"bold\">&nbsp;Date:<span >"+respDate+"</span></td>";
				        strValue += "</tr>";
				        
				        strValue += "<tr align=\"left\" bgcolor=\"#FFFAF6\" height=\"20\" >";
						strValue+="<td  align=\"left\" colspan=\"2\" class=\"bold\" valign=\"top\" >";
						strValue+="<table valign=\"top\" height=\"45\">";
						strValue+="<tr>";
						strValue+="<td  width=\"13%\"class=\"bold\" valign=\"top\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Text:";
						strValue+="</td>";
						strValue+="<td  valign=\"top\">"+strFMsg3+"</td>";
						strValue+="</tr>";
						strValue+="</table>";
						strValue+="</td>";
				        strValue += "</tr>";
					}
				}
		        
		        else
		        { 	
		        	strValue += "<tr align=\"left\">";
		 	        strValue += "<td colspan=\"2\" ><hr color=\"#ff722b\"></td></tr>";
		 			strValue+="<tr>";
		        	strValue += "<tr align=\"center\">";
					strValue += "<td height=\"20\" valign=\"center\" colspan=\"2\" >  No Comment </td>";
					strValue += "</tr>";
		        	
		        }
			  	
			  strValue+="</table >";
				

	        strValue += "<tr align=\"left\">";
	        strValue += "<td colspan=\"2\" ><hr color=\"#ff722b\"></td></tr>";
			strValue+="<tr>";
			strValue+="<td height=\"22\" colspan=\"2\" align=\"center\" valign=\"top\"><input name=\"btn_modify\" type=\"button\"  value=\"Back\" onclick=\"goBack()\"></td>";
			strValue+="</tr>";
		    
		
		return strValue;
	}
	
}
