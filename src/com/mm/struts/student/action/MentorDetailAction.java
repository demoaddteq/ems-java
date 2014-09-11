

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

public class MentorDetailAction extends Action {
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
		String copid = (request.getParameter("numCid")!=null) ? request.getParameter("numCid").trim() : "";
		String aType = (request.getParameter("aType")!=null) ? request.getParameter("aType").trim() : (String)session.getAttribute("aType");
		//////////System.out.println("....Complaintinfo.....cidddddddd........"+copid);
		int cid=Integer.parseInt(copid);
		DataSource ds = getDataSource(request,"student");
				
		
		RootMaster rootMaster = new RootMaster();
		IndvMaster indvMaster = new IndvMaster();
		Vector Complaintinfo=rootMaster.getComplaintDetails(ds, cid);
		//////////System.out.println("....Complaintinfo............."+Complaintinfo.size());
		//////////System.out.println("....Complaintinfo............."+Complaintinfo);
		//////////System.out.println("....Complaintinfo............."+Complaintinfo.elementAt(19).toString());
		/*Vector commentVec=indvMaster.getProcessedList(ds, cid);
		int comid = Integer.parseInt(commentVec.elementAt(3).toString());
		
		 Vector Complaintinfo=rootMaster.getComplaintDetails(ds, comid);
		//////////System.out.println("....Complaintinfo............."+Complaintinfo);*/
		
		 int respid = Integer.parseInt(Complaintinfo.elementAt(19).toString());
			
		 Vector userinfo=indvMaster.getUserInfo(ds, respid);
		 String fname=userinfo.elementAt(4).toString().trim();
		 String lname=userinfo.elementAt(5).toString().trim();
		 String contact=userinfo.elementAt(10).toString().trim();
		 String Mcat=Complaintinfo.elementAt(4).toString();
		 String cat="";
		
			
		 
		 if(Mcat.equalsIgnoreCase("1"))
			{
				cat="Personality";
			} else if(Mcat.equalsIgnoreCase("2"))
			{
				cat="Softskills";
			}else  if(Mcat.equalsIgnoreCase("3"))
			{
				cat="Technical";
			}else 
			{
				cat="Aptitute";
			}
		
		
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
			infoVec.add(cat);	//Consumer response
		
		
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
		inputVec.add(aType);
		
		String strComplaintHtml = "";
		
				
		
		strComplaintHtml = getMentorDetail(request, inputVec, commentVec,  remander,Complaintinfo,responseVec,cid,infoVec);
		 //////////System.out.println("....strComplaintHtml............."+strComplaintHtml);
		
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
		
		
		if(Complaintinfo.size()==0)
		{
					strValue += "<tr align=\"center\">";
					strValue += "<td height=\"20\" valign=\"center\" colspan=\"2\" class=\"bold\"> No Data Found</td>";
					strValue += "</tr>";
					
		}
		
		
		////////////////////////////////////////////////////////////////////
		
		
		

		strValue+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
		 strValue+="<tr>";
		   strValue+="<td>&nbsp;</td>";
		   strValue+="<td width=\"23%\"><div align=\"right\" class=\"bold\" > To: </div></td>";
		   strValue+="<td width=\"1%\">&nbsp;</td>";
		   strValue+="<td width=\"74%\"><div align=\"left\" >"+inputVec.elementAt(0).toString().trim()+" , ("+inputVec.elementAt(7).toString().trim()+"&nbsp;"+inputVec.elementAt(8).toString().trim()+")</div></td>";
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
		   strValue+="<td>&nbsp;</td>";
		   strValue+="<td><div align=\"right\" class=\"bold\"> Category: </div></td>";
		   strValue+="<td>&nbsp;</td>";
		   strValue+="<td><div align=\"left\" >"+infoVec.elementAt(4).toString().trim()+"</div></td>";
		   strValue+="<td>&nbsp;</td>";
		 strValue+="</tr>";
		 strValue+="<tr>";
		   strValue+="<td>&nbsp;</td>";
		   strValue+="<td><div align=\"right\" class=\"bold\"> Status: </div></td>";
		   strValue+="<td>&nbsp;</td>";
		   strValue+="<td><div align=\"left\" >"+inputVec.elementAt(9).toString().trim()+"</div></td>";
		   strValue+="<td>&nbsp;</td>";
		 strValue+="</tr>";
		 strValue+="<tr>";
		  strValue+="<td colspan=\"5\"><hr color=\"#C1C1C1\"></td>";
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
		 strValue+="<tr>";
		 strValue+="<td colspan=\"5\"><hr color=\"#C1C1C1\"></td>";
		 strValue+="</tr>";
		 strValue+="</table>";
		
		
/* strValue+="<table  height = \"350\" width=\"100%\" >";
		  strValue+="<tr>";
		  //////////////////////////// start td for complaint detail
			strValue+="<td width=\"60%\" valign=\"top\"><div style =\" height:350px; overflow-y:scroll; \">";
			 strValue+="<table width=\"100%\" valign=\"top\"  border=\"0\" cellpading=\"0\" cellspacing=\"0\" >";
			 
			 	
			 strValue += "<tr align=\"left\">";
		        strValue += "<td width=\"35%\" align=\"left\"class=\"bold\" > &nbsp;&nbsp;&nbsp;Request Title:</td>";
		        strValue += "<td  width=\"65%\" align=\"left\" ></td>";
		        strValue += "</tr>";
		        strValue += "<tr >";
		        strValue += "<td colspan=\"2\"  align=\"left\"  width=\"100%\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;"+Complaintinfo.elementAt(1).toString().trim()+" </td>";
		       
		        strValue += "</tr>";
			 
			
		        strValue += "<tr align=\"left\">";
		        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \"><hr color=\"#C1C1C1\"></td>";
				strValue+="<tr>";
			    
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
			  
				
				
					strValue += "<tr align=\"left\">";
			        strValue += "<td  align=\"left\" class=\"bold\" > &nbsp;&nbsp;&nbsp;Request Text:</td>";
			        strValue += "<td  align=\"left\" ><span ></span></td>";
			        strValue += "</tr>";
			        strValue += "<tr align=\"left\">";
			        strValue += "<td  colspan=\"2\" ><table width=\"100%\" align=\"center\"><tr><td width=\"5%\"></td><td align=\"left\"><div align=\"justify\"> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+strFMsgcompl+"</div></td><td width=\"5%\"></td></tr></table></td>";
			        strValue += "</tr>";
				
		       
		        strValue += "<tr align=\"left\" style=\"display:none\">";
		        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \"><hr color=\"#C1C1C1\"></td>";
		        strValue += "</tr>";
		        
		        if((Complaintinfo.elementAt(3).toString()).length()!=0){
					String strFMsgcomp3 = "";
					String strMessagecomp3 = Complaintinfo.elementAt(3).toString()!=null ? Complaintinfo.elementAt(3).toString().trim():"";
					
					for(int x=0; x<strMessagecomp3.length(); x++)
					{ 
						int numcomp3 = (int)strMessagecomp3.charAt(x);
						if(numcomp3 == 10)
						{
							strFMsgcomp3 = strFMsgcomp3+"<br><br>";
						}
						else
						{
							strFMsgcomp3 = strFMsgcomp3+strMessagecomp3.charAt(x);
						}
					}
					
					strValue += "<tr align=\"left\" style=\"display:none\">";
			        strValue += "<td  align=\"left\" class=\"bold\" > &nbsp;&nbsp;&nbsp;Relevent Text:</td>";
			        strValue += "<td  align=\"left\" ><span ></span></td>";
			        strValue += "</tr>";
			        strValue += "<tr align=\"left\" style=\"display:none\">";
			        strValue += "<td  colspan=\"2\" ><table width=\"100%\" align=\"center\"><tr><td width=\"5%\"></td><td align=\"left\"> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+strFMsgcomp3+"</td><td width=\"5%\"></td></tr></table></td>";
			        strValue += "</tr>";
					
		        }
		        
		        strValue += "<tr align=\"left\" style=\"display:none\">";
		        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \"><hr color=\"#C1C1C1\"></td>";
		        strValue += "</tr>";
			 
	       
	        
	       
	        strValue+="</table>";  
	           
		      
		        strValue+="</div></td>";
				  // End td for complaint detail
		        strValue+="<td width=\"1%\"></td>";
		        
		        
		        strValue+="<td width=\"38%\" valign=\"top\" ><div style =\" height:350px; overflow-y:scroll; \">";
			    strValue+="<table width=\"100%\" valign=\"top\"  border=\"0\" >";
			    
			    
			    
		        strValue += "<tr align=\"left\">";
		        strValue += "<td width=\"35%\" align=\"left\"class=\"bold\" > &nbsp;&nbsp;&nbsp;Company detail:</td>";
		        strValue += "<td  width=\"65%\" align=\"left\" ></td>";
		        strValue += "</tr>";
		        strValue += "<tr align=\"left\">";
		        strValue += "<td  align=\"right\"class=\"bold\" width=\"50%\" > Company Name: </td>";
		        strValue += "<td  align=\"left\" ><span >"+inputVec.elementAt(0).toString().trim()+" </span></td>";
		        strValue += "</tr>";
		        strValue += "<tr align=\"left\">";
		        strValue += "<td  align=\"right\"class=\"bold\" width=\"50%\" > Company Address: </td>";
		        strValue += "<td  align=\"left\" ><span >"+inputVec.elementAt(1).toString().trim()+" </span></td>";
		        strValue += "</tr>";
		        strValue += "<tr align=\"left\">";
		        strValue += "<td  align=\"right\"class=\"bold\" width=\"50%\" > Country: </td>";
		        strValue += "<td  align=\"left\" ><span >"+inputVec.elementAt(2).toString().trim()+" </span></td>";
		        strValue += "</tr>";
		        strValue += "<tr align=\"left\">";
		        strValue += "<td  align=\"right\"class=\"bold\" width=\"50%\" > State: </td>";
		        strValue += "<td  align=\"left\" ><span >"+inputVec.elementAt(3).toString().trim()+" </span></td>";
		        strValue += "</tr>";
		        strValue += "<tr align=\"left\">";
		        strValue += "<td  align=\"right\"class=\"bold\" width=\"50%\" > City: </td>";
		        strValue += "<td  align=\"left\" ><span >"+inputVec.elementAt(4).toString().trim()+" </span></td>";
		        strValue += "</tr>";
		        strValue += "<tr align=\"left\" style=\"display:none\">";
		        strValue += "<td  align=\"right\"class=\"bold\" width=\"50%\" > E-mail: </td>";
		        strValue += "<td  align=\"left\" ><span >"+inputVec.elementAt(5).toString().trim()+" </span></td>";
		        strValue += "</tr>";
		        strValue += "<tr align=\"left\">";
		        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \"><hr color=\"#C1C1C1\"></td>";
				strValue+="<tr>";
			  	strValue += "<tr align=\"left\" valign=\"top\" >";
		        strValue += "<td  align=\"left\" valign=\"top\"  colspan=\"2\" class=\"bold\">Mentors Information:</td>";
		        strValue += "</tr>";
		         
		        strValue += "<tr align=\"left\">";
		        strValue += "<td  align=\"right\"class=\"bold\" width=\"50%\" > Name: </td>";
		        strValue += "<td  align=\"left\" ><span >"+inputVec.elementAt(7).toString().trim()+""+inputVec.elementAt(8).toString().trim()+"  </span></td>";
		        strValue += "</tr>";
		        
		        
		        String temPno1="Not mention";
				if(inputVec.elementAt(6).toString().trim().length()!=0){
					////////System.out.println("insite....................."); 
					String temPno=inputVec.elementAt(6).toString().trim();
					temPno1 = temPno.replace("~", "-");
					
				
					
						strValue += "<tr align=\"left\" style=\"display:none\">";
				        strValue += "<td  align=\"right\"class=\"bold\" width=\"50%\" > Contact: </td>";
				        strValue += "<td  align=\"left\" ><span >"+temPno1+"  </span></td>";
				        strValue += "</tr>";
			    }
		       
		        
		        
		       
	        
	       
		strValue+="</td>";
		strValue+="</tr>";
		
		   
		strValue+="</table>";
		strValue+="</div></td>";
		// End td for additional detail
		strValue+="<td width=\"1%\"></td>";
		strValue+="</tr>";
		  strValue+="</table>";*/
		  RootMaster rootMaster=new RootMaster();
		 
		if(remander==0)
		{
			strValue += "<tr style=\"display:none\" align=\"center\">";
			strValue += "<td height=\"50\" colspan=\"2\"></td>";
			strValue += "</tr>";
		}
		
		 int z=0;
	        strValue+="</Table>";
	        
			strValue+="</td></tr>";
		     
				     
	      
			
			
	        
	      
		 
		  
			if(responseVec.size()>0)
			{
				
				
				  	
				        
				    	strValue += "<tr align=\"left\" width=\"100%\">";
				        strValue += "<td colspan=\"2\" width=\"100%\">";
				        strValue+="<table cellpading=\"0\" cellspacing=\"0\" width=\"100%\">";
				        
				        strValue +="<tr height=\"\" id=\"resp\" >";
						strValue +="<td colspan=\"2\"  align=\"left\" class=\"bold\"> Number Of Response By </td>";
						strValue +="</tr>";
						strValue +="<tr align=\"left\" valign=\"top\" id=\"resp\" >";
						strValue +="<td colspan=\"2\" class=\"bold\">";
						strValue +="Student:&nbsp;<span class=\"Arial_Narrow_12_blue_bold\">"+infoVec.elementAt(3).toString().trim()+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						strValue +="Company:&nbsp;<span class=\"Arial_Narrow_12_blue_bold\">"+infoVec.elementAt(1).toString().trim()+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						strValue +="Total:&nbsp;<span class=\"Arial_Narrow_12_blue_bold\">"+infoVec.elementAt(0).toString().trim()+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						strValue +="&nbsp;<span class=\"Arial_Narrow_12_blue_bold\"></span>";
						strValue +="</td>";
						strValue +="</tr>";
						
						
						strValue +="</tr>";
						strValue += "<tr align=\"left\"  >";
						if(!inputVec.elementAt(9).toString().trim().equalsIgnoreCase("Reject"))
						{
							 strValue += "<td colspan=\"2\" valign=\"top\" align=\"left\" ><img  onclick=\"showRes()\" style=\"cursor:hand\" src=\"../images/giveresponse.gif\" width=\"63\" height=\"19\" border=\"0\" alt=\"Click here to give response\"></img></td>";
						}else
						{
							 strValue += "<td colspan=\"2\" valign=\"top\" align=\"center\" class=\"bold\" height=\"25\">Your Request is Rejected so no further Communication Possible.</td>";
						}
				       
				        strValue += "</tr>";
				        strValue +="<tr  >";
						strValue +="<td colspan=\"2\" align=\"right\" class=\"bold\"><hr color=\"#C1C1C1\"></td>";
						strValue +="</tr>";
						
						//////System.out.println("ist>>>>>>>>>>>ZZZZZZZZZZZZ"+z);
						strValue+="<tr id=\"response\" align=\"center\" style=\"display:none\">";
						//////System.out.println("2nd>>>>>>>>>>>ZZZZZZZZZZZZ"+z);
						strValue+="<td valign=\"top\" colspan=\"3\"><form id=\""+z+"\" name=\"frmreply\" method=\"post\" action=\"menResponseSuccess.do\" >";
						strValue+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
						strValue+=" <tr>";
						strValue+=" <td align=\"center\" colspan=\"4\"><textarea id=\"responsetext"+z+"\" name=\"responsetext\" cols=\"65\" rows=\"4\" ></textarea></td>";
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
						 
			        		strValue+="<td colspan=\"4\" align=\"center\"><input name=\"Submit\" type=\"submit\" class=\"buttonCopy\" value=\"Submit\" >&nbsp;&nbsp;&nbsp;";
			        		strValue+="<input name=\"cancel\" type=\"button\" class=\"buttonCopy\"  value=\"Cancel\" onClick=\"reshide()\"></td>";
			        		
			        		strValue+="  </tr>";	
						
						
						strValue+=" </table>";
						strValue+="</form>";
						strValue+=" </td>";
						strValue+="</tr>";
				String bgrow="#CCECFF",bgtab="#F0FAF9";
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
					////////////System.out.println("resposne_flagresposne_flag=>>>>>>>"+resposne_flag);
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
					
					if((comp_type.equalsIgnoreCase("Corporates"))||(comp_type.equalsIgnoreCase("Advertiser")))
					{
						bgrow="#CCECFF";
						bgtab="#F0FAF9";
						if(comp_type.equalsIgnoreCase("Corporates"))
						{
							comptype = inputVec.elementAt(0).toString().trim();
						}else
						{
							comptype = "College";
						}
						
					}
					else if(comp_type.equalsIgnoreCase("Enterprise"))
					{
						bgrow="#FFEBFF";
						bgtab="#FFF7FF";
						comptype = "Campusyogi";
					}
					else if(comp_type.equalsIgnoreCase("Consumer"))
					{
						bgrow="#F9E1CC";
					    bgtab="#FFFAF6";
					    comptype = "Student";
					}
					strValue+="<tr>";
					strValue+="<td colspan=\"2\" width=\"100%\" >";
					strValue+="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
					// titel font color:#CC3300;
					//footer color:#0099CC,arrow02.gif
					
					strValue+="<tr >";
					strValue+="<td colspan=\"2\" valign=\"top\" width=\"100%\">";
					strValue+="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
					
					strValue+="<tr bgcolor=\"#EDF3F8\" height=\"20\">";
					strValue+="<td width=\"1%\" height=\"20\">&nbsp;</td>";
					strValue+="<td width=\"65%\" align=\"left\" ><font color=\"#CC3300\"><strong>";
					if(comp_type.equalsIgnoreCase("Consumer"))
					{
						strValue+="Reply By Student";
					}
					else if(comp_type.equalsIgnoreCase("Advertiser"))
					{
						strValue+="Response By College";
					}
					else if(comp_type.equalsIgnoreCase("Corporates"))
					{
						strValue+="Response By Company";
					}
					
					strValue+="</strong></font></td>";
					strValue+="<td width=\"32%\" ></td>";
					strValue+="<td width=\"2%\" >&nbsp;</td>";
					strValue+="</tr>";
					strValue+="<tr bgcolor=\"#EDF3F8\" height=\"20\">";
					strValue+="<td width=\"1%\" >&nbsp;</td>";
					strValue+="<td width=\"97%\" colspan=\"2\" ><div align=\"justify\">"+strFMsg3+"</div></td>";
					strValue+="<td width=\"2%\" >&nbsp;</td>";
					strValue+="</tr>";
					
					strValue+="<tr bgcolor=\"#EDF3F8\" height=\"20\">";
					strValue+="<td width=\"1%\" >&nbsp;</td>";
					strValue+="<td width=\"65%\" ></td>";
					strValue+="<td width=\"32%\" ><div align=\"right\"><font color=\"#CC3300\"><strong>City:&nbsp;"+cityName+"</strong></div></td>";
					strValue+="<td width=\"2%\" >&nbsp;</td>";
					strValue+="</tr>";
					
					
					strValue+="<tr height=\"20\">";
					strValue+="<td width=\"1%\" >&nbsp;</td>";
					strValue+=" <td valign=\"top\"><div align=\"left\"><img valign=\"top\"  src=\"../images/arrow02.gif\"></img>&nbsp;&nbsp;&nbsp;<font color=\"#0099CC\"><strong>Posted by &nbsp;" ;
					
					strValue +=fname+" "+lname;
		        
					strValue += "&nbsp;on  "+respDate+"/"+tempVec.elementAt(4)+"  /</font><span class=\"student\" >"+comptype+"</span>";
		       
					strValue += "</div></td>";
					if((!comptype.equalsIgnoreCase("Student")) && resposne_flag == 0)
					{
					if(!inputVec.elementAt(9).toString().trim().equalsIgnoreCase("Reject"))
					{	
						strValue+="<td width=\"32%\" ><div align=\"right\"><img src=\"../images/reply.gif\" alt=\"Click here to give reply\"  id=\""+z+"\" onclick=\"showAddNew(this.id)\" style=\"cursor:hand\" ></img></div></td>";
						
					}else
					{
						strValue+="<td width=\"32%\" >&nbsp;</td>";
					}
			      	
					}
		        
		        
		        strValue+="</tr>";
					
					
				strValue+="</table>";
				strValue+="</td>";
				strValue+="</tr>";
					
					//////////System.out.println("Value For ZZZ in Response>>>>>>>>>>>>>"+z);
					strValue+="<tr  id=\"status"+z+"\" style=\"display:inline\" align=\"center\">";
					strValue+="<td width=\"100%\" colspan=\"2\" height=\"20\"></td>";
					
					strValue+="</tr>";
					
					strValue+="<tr id=\"status1"+z+"\" style=\"display:none\" align=\"center\">";
					strValue+="<td height=\"20\" class=\"var_13_Success\" colspan=\"2\"></td>";
					strValue+="</tr>";
					strValue+="<tr id=\"rowid"+z+"\" align=\"center\" style=\"display:none\">";
					////System.out.println("zzzzzzzzzzzzzzz on detail"+z);
					strValue+="<td colspan=\"2\" valign=\"top\" class=\"successmessages\"><a name=\"<%=z%>\">";
					strValue+="<form id=\""+z+"\" name=\"frmreply\" method=\"post\" action=\"menResponseSuccess.do\">";
					strValue+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
					strValue+="<tr>";
					strValue+="<td height=\"22\" width=\"100%\"  colspan=\"2\" align=\"left\"><strong class=\"bold\">&nbsp;&nbsp;&nbsp;Write  reply to Company</strong></td>";
					strValue+="</tr>";
					strValue+="<tr>";
					strValue+="<td align=\"center\" width=\"100%\"  colspan=\"2\"><textarea id=\"responsetext"+z+"\" name=\"responsetext\" cols=\"65\" rows=\"4\" ></textarea></td>";
					strValue+="</tr>";
					strValue+="<tr>";
					strValue+="<td align=\"center\" width=\"100%\"  colspan=\"2\"><input name=\"Submit\" type=\"submit\" class=\"buttonCopy\" value=\"Submit\" >";
					strValue+="&nbsp;&nbsp;&nbsp;<input name=\"cancel\" type=\"button\" class=\"buttonCopy\" id=\""+z+"\" value=\"Cancel\" onClick=\"hideAddNew(this.id)\"></td>";
					strValue+="<input type=\"hidden\" name=\"respid\" value=\""+responseId+"\">";
					strValue+="<input type=\"hidden\" name=\"compId\" value=\""+cid+"\">";
					strValue+="</tr>";
					strValue+="</table>";
					
					strValue+="</form></a></td>";
					strValue+="</tr>";
					strValue+="</table>";
					strValue+="</td></tr>";
				}
			}
			else
			{
				
			}
			strValue+="<table cellpading=\"0\" cellspacing=\"0\" width=\"100%\">";
			
			  if(commentVec.size()>0)
				{
							
		        	
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
							System.out.println("Value For ZZZ in Response>>>>>"+i+">>>>>>>>"+tempVec);
							int comp_id = Integer.parseInt(tempVec.elementAt(5).toString().trim());
							String compName = rootMaster.getComp_Type(getDataSource(request, "student"),comp_id);
							if(compName.equalsIgnoreCase("Corporates"))
							{
								compName="Company";
							}else if(compName.equalsIgnoreCase("Advertiser"))
							{
								compName="College";
							}else
							{
								compName="Student";
							}
							
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
						
						
						
						
						
						strValue+="<tr>";
						strValue+="<td colspan=\"2\" width=\"100%\" >";
						strValue+="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
						// titel font color:#CC3300;
						//footer color:#0099CC,arrow02.gif
						
						strValue+="<tr >";
						strValue+="<td colspan=\"2\" valign=\"top\" width=\"100%\">";
						strValue+="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
						
						strValue+="<tr bgcolor=\"#F3F3F5\" height=\"20\">";
						strValue+="<td width=\"1%\" height=\"20\">&nbsp;</td>";
						strValue+="<td width=\"65%\" align=\"left\" ><font color=\"#CC3300\"><strong>Comment</strong></font></td>";
						strValue+="<td width=\"32%\" ></td>";
						strValue+="<td width=\"2%\" >&nbsp;</td>";
						strValue+="</tr>";
						strValue+="<tr bgcolor=\"#F3F3F5\" height=\"20\">";
						strValue+="<td width=\"1%\" >&nbsp;</td>";
						strValue+="<td width=\"97%\" colspan=\"2\" ><div align=\"justify\">"+strFMsg3+"</div></td>";
						strValue+="<td width=\"2%\" >&nbsp;</td>";
						strValue+="</tr>";
						
						strValue+="<tr bgcolor=\"#F3F3F5\" height=\"20\">";
						strValue+="<td width=\"1%\" >&nbsp;</td>";
						strValue+="<td width=\"97%\" colspan=\"2\" ></td>";
						strValue+="<td width=\"2%\" >&nbsp;</td>";
						strValue+="</tr>";
						
						
						strValue+="<tr height=\"20\">";
						strValue+="<td width=\"1%\" >&nbsp;</td>";
						strValue+=" <td valign=\"top\"><div align=\"left\"><img valign=\"top\"  src=\"../images/arrow02.gif\"></img>&nbsp;&nbsp;&nbsp;<font color=\"#0099CC\"><strong>Posted by &nbsp;" ;
						strValue +=fname+" "+lname;
			        	strValue += "&nbsp;on  "+respDate+"/"+tempVec.elementAt(4)+"  /</font><span class=\"student\" >"+compName+"</span>";
						strValue += "</div></td>";
						strValue+="<td width=\"32%\" >&nbsp;</td>";
						strValue+="</tr>";
						
						
					strValue+="</table>";
					strValue+="</td>";
					strValue+="</tr>";
						
						
					}
				}
		        
		        else
		        { 	
		        	if(inputVec.elementAt(9).toString().trim().equalsIgnoreCase("Underprocess"))
					{
		        		strValue += "<tr align=\"center\">";
						strValue += "<td height=\"20\" valign=\"center\" colspan=\"2\" class=\"bold\">  No Response Yet. </td>";
						strValue += "</tr>"; 
					}
		        	
		        	
		        	strValue += "<tr align=\"center\">";
					strValue += "<td height=\"20\" valign=\"center\" colspan=\"2\" class=\"bold\">  No Comment </td>";
					strValue += "</tr>";
		        	
		        }
			  	
			  strValue+="</table >";
				

	        strValue += "<tr align=\"left\">";
	        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \"><hr color=\"#C1C1C1\"></td></tr>";
			strValue+="<tr>";
			strValue+="<td height=\"22\" colspan=\"2\" align=\"center\" valign=\"top\"><input name=\"btn_modify\" type=\"button\"  value=\"Back\" onclick=\"goBack()\"></td>";
			strValue+="</tr>";
		    
		
		return strValue;
	}
	
}
