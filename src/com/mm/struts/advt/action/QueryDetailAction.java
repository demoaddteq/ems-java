	package com.mm.struts.advt.action;

	import java.io.PrintWriter;
	import java.util.Vector;

	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;

	import org.apache.struts.action.Action;
	import org.apache.struts.action.ActionForm;
	import org.apache.struts.action.ActionForward;
	import org.apache.struts.action.ActionMapping;
	import javax.sql.*;

import com.mm.core.master.*;
	public class QueryDetailAction extends Action {
		
		
		
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception 
		{
			HttpSession session = request.getSession();
			String numCount = (String)session.getAttribute("numCount");
			int count = Integer.parseInt(numCount);
			int remander = count%2;
			DataSource ds = getDataSource(request,"advt");
			String uId =(String)session.getAttribute("uId");
			String compId =(String)session.getAttribute("compId");
			Vector allRights =(Vector)session.getAttribute("allRights");
			String adminFlag = allRights.elementAt(18).toString();
			
			////////////System.out.println("uId..in Action avdt Detail........."+uId);
			////////////System.out.println("compId..in Action avdt ...Detail......"+compId);
			////////////System.out.println("adminFlag..in Action avdt ..Detail......."+adminFlag);
			
			
			
			String strPgType = (request.getParameter("pgtyp")!=null) ? request.getParameter("pgtyp").trim() : "Detail";
			String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "creation_date";
			
				
			int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
			int maxVal = 10;
			//EntpMaster am = new EntpMaster();
			AdvtMaster advtMaster = new AdvtMaster();
			RootMaster rootMaster = new RootMaster();
			IndvMaster indvMaster = new IndvMaster();
			
			
			String strComplaintHtml = "";
			
					
			if(strPgType.equalsIgnoreCase("Detail"))
			{	
				////////////System.out.println("In side for loop............... ");
				String numCompId = (request.getParameter("numCopmId")!=null) ? request.getParameter("numCopmId").trim() : "0";
				session.setAttribute("numCopmId", numCompId);
				//////System.out.println("numCopmId on ComplaintDetail page in  "+numCompId);
				String pageid = (request.getParameter("pageid")!=null) ? request.getParameter("pageid").trim() : "1";
				session.setAttribute("pageid", pageid);
				//////System.out.println("pageid on ComplaintDetail page in  "+pageid);
		    	if(!numCompId.equalsIgnoreCase("0"))
		    	{	
		    		Vector<String> paramVec1 = new Vector<String>();
					
					paramVec1.add(uId);
					paramVec1.add(compId);
					paramVec1.add(adminFlag);	
					paramVec1.add(pageid);	
					
					Vector<String> paramVec2 = new Vector<String>();
		    		paramVec2.add(uId);
		    		paramVec2.add(compId);
		    		paramVec2.add(numCompId);
		    		paramVec2.add(adminFlag);
		//	    	-------------------------
			    	//int numArray[] = md.getCompanyMoves(numCopmId, getDataSource(request, "main"), minVal, maxVal, strFShortBy);
			    	
		//	    	-------------------------
		    		int totalRow = 0;
			    	
		    		//totalRow = advtMaster.getComplaintCount(getDataSource(request, "advt"),paramVec1);
		    		
					////////////System.out.println("totalRow on ComplaintDetail page "+totalRow);
			    	String strPageHtml = getPages(minVal, maxVal, totalRow, strPgType);
					////////////System.out.println("Pages "+strValue);
			    	Vector complaintVec = advtMaster.getQueryDetails(getDataSource(request, "advt"),paramVec2);
			    	//////System.out.println("complaint details Vector.on ComplaintDetail page...."+complaintVec);
			    	
			    	int advt_id = Integer.parseInt(complaintVec.elementAt(12).toString().trim());
			    	//////System.out.println("advt_id, on ComplaintDetail page....."+advt_id);
			    	Vector brandVec = rootMaster.getCompanyDetail(getDataSource(request, "advt"), advt_id);		    	
			    	//////System.out.println("brandVec, .on ComplaintDetail page...."+brandVec);
			    	
			    	int login_id = Integer.parseInt(complaintVec.elementAt(19).toString().trim());
			    	//////System.out.println("login_id, ...on ComplaintDetail page.."+login_id);
			    	Vector complainantVec = rootMaster.getUserInfo(getDataSource(request, "advt"), login_id);		    	
			    	//////System.out.println("complainantVec, ..on ComplaintDetail page..."+complainantVec);
//			    	complainant Information
			    	int consCityId = Integer.parseInt(complainantVec.elementAt(6).toString().trim());
			    	String consCityname = rootMaster.getPlaceName(getDataSource(request, "advt"), consCityId);		    	
			    	int consStateId = Integer.parseInt(complainantVec.elementAt(7).toString().trim());
			    	String consStateName = rootMaster.getStateName(getDataSource(request, "advt"), consStateId);		    	
			    	int consCountryId = Integer.parseInt(complainantVec.elementAt(11).toString().trim());
			    	String consCountryname = rootMaster.getCountryName(getDataSource(request, "advt"), consCountryId);
			    	
			    	int dealer_id = Integer.parseInt(complaintVec.elementAt(20).toString().trim());
			    	////////////System.out.println("dealer_id, ..on ComplaintDetail page..."+dealer_id);
			    	Vector delareVec = new Vector();
			    	
			    	Vector commentVec=rootMaster.getCommentsList(getDataSource(request, "advt"), Integer.parseInt(numCompId));
			    	
			    	if(dealer_id!= 0)
			    	{
			    	delareVec = rootMaster.getDealerDetail(getDataSource(request, "advt"), dealer_id);
			    	int dealerCityId = Integer.parseInt(delareVec.elementAt(6).toString().trim());
			    	String dealerCityname = rootMaster.getPlaceName(getDataSource(request, "advt"), dealerCityId);		    	
			    	int dealerStateId = Integer.parseInt(delareVec.elementAt(4).toString().trim());
			    	String dealerStateName = rootMaster.getStateName(getDataSource(request, "advt"), dealerStateId);		    	
			    	int dealerCountryId = Integer.parseInt(delareVec.elementAt(12).toString().trim());
			    	String dealerCountryname = rootMaster.getCountryName(getDataSource(request, "advt"), dealerCountryId);
			    	
			    	delareVec.add(dealerCityname);//13
			    	delareVec.add(dealerStateName);//14
			    	delareVec.add(dealerCountryname);//15
			    	}
			    	////////////System.out.println("delareVec, ..on ComplaintDetail page..."+delareVec);
			    	
			    	int cat_id = Integer.parseInt(complaintVec.elementAt(4).toString().trim());
			    	int tag_id = Integer.parseInt(complaintVec.elementAt(21).toString().trim());
			    	////////////System.out.println("cat_id, ....."+cat_id);
			    	////////////System.out.println("tag_id, ....."+tag_id);
			    	String catName = "";
			    	
			    	if(cat_id==1)
					{
			    		catName = "Personality";
					}
					else if(cat_id==2)
					{
						catName = "Softskills";
					}
					else if(cat_id==3)
					{
						catName = "Technical";
					}
					else if(cat_id==4)
					{
						catName = "Aptitude";
					}else
					{
						catName = "Not mentioned";
					}
			    	
			    	////////////System.out.println("catName, ....."+catName);
			    	
			    	
			    	String tagName = rootMaster.getStrTagName(getDataSource(request, "advt"), tag_id);
			    	////////////System.out.println("tagName, ....."+tagName);
			    	
			    	//get no of responses for this page by using getCompanyType() method of rootmaster
					//get all company list with the no of responses accorrding to company type. rootmaster.getResponseList()
					Vector<String> respDataVec = new Vector<String>();
					respDataVec.add(numCompId);
					respDataVec.add("Advertiser");
					respDataVec.add("corporates");
					Vector responseVec = rootMaster.getResponseList(getDataSource(request, "advt"), respDataVec);
					////////////System.out.println("responseVec..in Action avdt ..Detail......."+responseVec);
					int totleRsponse = responseVec.size();
					int indvResponse = 0;
					int advtResponse = 0;
					int entpResponse = 0;
					////////////System.out.println("responseVec>>>"+responseVec);
					
					for(int i=0;i<responseVec.size();i++)
					{
						Vector tempVec =(Vector)responseVec.elementAt(i);
						String comp_type = tempVec.elementAt(5).toString().trim();
						///
						if(comp_type.equalsIgnoreCase("Advertiser"))
						{
							advtResponse++;
							
						}
						else if(comp_type.equalsIgnoreCase("Corporates"))
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
					}
			    	//end of response getting code
					
			    	
			    	Vector<String> infoVec = new Vector<String>();
			    	infoVec.add(catName);//0//catName
			    	infoVec.add(tagName);//1//tagName
			    	infoVec.add(consCityname);//2// Consumer City nane
			    	infoVec.add(consStateName);//3// Consumer State nane
			    	infoVec.add(consCountryname);//4// Consumer Country nane
			    	
			    	infoVec.add(String.valueOf(totleRsponse));//5// total no of responses
			    	infoVec.add(String.valueOf(indvResponse));//6// Consumer responses
			    	infoVec.add(String.valueOf(advtResponse));//7// brand responses
			    	infoVec.add(String.valueOf(entpResponse));//8// Core responses
			    	String strUnnamed = getComplaintUnnamed(request, Integer.parseInt(numCompId.trim()), advt_id, cat_id);
			    
			    		
			    		
			    		strComplaintHtml = getComplaintDetail(strUnnamed, request,numCompId, strPageHtml, complaintVec, brandVec, complainantVec, delareVec,responseVec, infoVec,pageid,  strShortBy, remander,commentVec);	
			    	
			    	
		    	}
			}
			
			
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
		    ////////System.out.println("strComplaintHtml in Detail page>>>>>>>>>"+strComplaintHtml);
			// TODO Auto-generated method stub
			return null;
		}
		
		
	
		
////////////////////////////////////////////////////////////////////////////	//
		private String getComplaintDetail(String strUnnamed, HttpServletRequest request, String numCompId,String strPageHtml,Vector complaintVec,Vector brandVec,Vector complainantVec,Vector delareVec ,Vector responseVec, Vector infoVec,String pageid, String strShortBy, int remander,Vector commentVec)
		{
			
			
			int z=0;
			String strResult="success";
			String result = "success";
			RootMaster rootMaster = new RootMaster();
			int tag_id = Integer.parseInt(complaintVec.elementAt(21).toString().trim());
			int publish_flag =  Integer.parseInt(complaintVec.elementAt(24).toString().trim());
			String  publish_on =  complaintVec.elementAt(25).toString().trim();
			int Consumerresponse=0,brandresponse=0,coreresponse=0,totalresponse=0;
			totalresponse = Integer.parseInt(infoVec.elementAt(5).toString().trim()); 
			Consumerresponse = Integer.parseInt(infoVec.elementAt(6).toString().trim()); 
			brandresponse = Integer.parseInt(infoVec.elementAt(7).toString().trim()); 
			coreresponse = Integer.parseInt(infoVec.elementAt(8).toString().trim()); 
	    	String brnadName = complaintVec.elementAt(27).toString().trim();
	    	int respid=(request.getParameter("respid")!=null) ? Integer.parseInt(request.getParameter("respid")) : 0;
	    	if(!brnadName.equals(""))
	    	{
	    		brnadName = brnadName+"(Other)";
	    	}
	    	else
	    	{
	    		brnadName = brandVec.elementAt(1).toString().trim();
	    	}
			String strDetailHtml ="";
			
			
			

			  	strDetailHtml+="<table   width=\"100%\" >";
			  	 strDetailHtml+="<tr >";
				   strDetailHtml+="<td valign=\"top\" width=\"1%\">&nbsp;</td>";
				   strDetailHtml+="<td valign=\"top\" width=\"23%\"><div align=\"right\" class=\"bold\" > To:</div></td>";
				   strDetailHtml+="<td valign=\"top\" width=\"1%\">&nbsp;</td>";
				   strDetailHtml+="<td valign=\"top\" width=\"74%\"><div align=\"justify\"  valign=\"top\">"+complainantVec.elementAt(4).toString().trim()+"&nbsp;"+complainantVec.elementAt(5).toString().trim()+","+infoVec.elementAt(2).toString().trim()+"</div></td>";
				   strDetailHtml+="<td valign=\"top\" width=\"1%\">&nbsp;</td>";
				 strDetailHtml+="</tr>";
				 
				 
				  strDetailHtml+="<tr >";
				   strDetailHtml+="<td valign=\"top\" width=\"1%\">&nbsp;</td>";
				   strDetailHtml+="<td valign=\"top\" width=\"23%\"><div align=\"right\" class=\"bold\" > Query Title:</div></td>";
				   strDetailHtml+="<td valign=\"top\" width=\"1%\">&nbsp;</td>";
				   strDetailHtml+="<td valign=\"top\" width=\"74%\"><div align=\"justify\"  valign=\"top\">"+complaintVec.elementAt(1).toString().trim()+" </div></td>";
				   strDetailHtml+="<td valign=\"top\" width=\"1%\">&nbsp;</td>";
				 strDetailHtml+="</tr>";
				 
		       
		        
				 strDetailHtml+="<tr >";
				   strDetailHtml+="<td valign=\"top\" width=\"1%\">&nbsp;</td>";
				   strDetailHtml+="<td valign=\"top\" width=\"23%\"><div align=\"right\" class=\"bold\" > Query ID:</div></td>";
				   strDetailHtml+="<td valign=\"top\" width=\"1%\">&nbsp;</td>";
				   strDetailHtml+="<td valign=\"top\" width=\"74%\"><div align=\"justify\"  valign=\"top\">"+complaintVec.elementAt(17).toString().trim()+"</div></td>";
				   strDetailHtml+="<td valign=\"top\" width=\"1%\">&nbsp;</td>";
				 strDetailHtml+="</tr>"; 
		      
				 strDetailHtml+="<tr >";
				   strDetailHtml+="<td valign=\"top\" width=\"1%\">&nbsp;</td>";
				   strDetailHtml+="<td valign=\"top\" width=\"23%\"><div align=\"right\" class=\"bold\" > Query Originated Date:</div></td>";
				   strDetailHtml+="<td valign=\"top\" width=\"1%\">&nbsp;</td>";
				   String strDate[]=complaintVec.elementAt(5).toString().trim().split("-");
				   strDetailHtml+="<td valign=\"top\" width=\"74%\"><div align=\"justify\"  valign=\"top\">"+strDate[2]+"-"+strDate[1]+"-"+strDate[0]+"</div></td>";
				   strDetailHtml+="<td valign=\"top\" width=\"1%\">&nbsp;</td>";
				 strDetailHtml+="</tr>";
		        
							  	 
		        strDetailHtml += "<tr align=\"left\">";
		        strDetailHtml += "<td colspan=\"5\" ><hr color=\"#C1C1C1\"></td>";
		        strDetailHtml += "</tr>";
		        
		        
		        String strFMsgcompl = "";
				String strMessagecompl = complaintVec.elementAt(2).toString()!=null ? complaintVec.elementAt(2).toString().trim():"";
				
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
			  
		        strDetailHtml+="<tr height=\"60\">";
				   strDetailHtml+="<td valign=\"top\" width=\"1%\">&nbsp;</td>";
				   strDetailHtml+="<td valign=\"top\" width=\"23%\"><div align=\"right\" class=\"bold\" > Query Text: </div></td>";
				   strDetailHtml+="<td valign=\"top\" width=\"1%\">&nbsp;</td>";
				   strDetailHtml+="<td valign=\"top\" width=\"74%\"><div align=\"justify\"  valign=\"top\">"+strFMsgcompl+"</div></td>";
				   strDetailHtml+="<td valign=\"top\" width=\"1%\">&nbsp;</td>";
				 strDetailHtml+="</tr>";
			        
				 strDetailHtml += "<tr align=\"left\">";
			        strDetailHtml += "<td colspan=\"5\" ><hr color=\"#C1C1C1\"></td>";
			        strDetailHtml += "</tr>";
			        
			   strDetailHtml+="</table>";
				
			        
			        
			        strDetailHtml+="<table   width=\"100%\" valign=\"top\" >";
			        if(!(pageid.equals("5") || pageid.equals("6")))
			        {
			        	
						strDetailHtml +="<tr height=\"\">";
						strDetailHtml +="<td colspan=\"2\"  align=\"left\" class=\"bold\"> Number Of Response By </td>";
						strDetailHtml +="</tr>";
						strDetailHtml +="<tr align=\"left\" valign=\"top\">";
						strDetailHtml +="<td colspan=\"2\" class=\"bold\">";
						strDetailHtml +="Student:&nbsp;<span class=\"bold\">"+Consumerresponse+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						strDetailHtml +="College:&nbsp;<span class=\"bold\">"+brandresponse+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						strDetailHtml +="Total:&nbsp;<span class=\"bold\">"+totalresponse+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						strDetailHtml +="&nbsp;<span class=\"bold\"></span>";
						strDetailHtml +="</td>";
						strDetailHtml +="</tr>";
						
						strDetailHtml +="<tr>";
						if(respid==0)
						{
							if(strDetailHtml.equalsIgnoreCase("failure"))
							{
								strDetailHtml+="<td height=\"20\" colspan=\"2\"></td>";
							}
							else if(strResult.equalsIgnoreCase("success"))
							{
								strDetailHtml+="<td  height=\"20\" colspan=\"2\" ></td>";
							}
							if(result.equalsIgnoreCase("failure"))
							{
								strDetailHtml+="<td height=\"20\"  colspan=\"2\"></td>";
							}
							
						}
						strDetailHtml +="</tr>";
						strDetailHtml += "<tr align=\"left\" >";
				        strDetailHtml += "<td colspan=\"2\" valign=\"top\" align=\"left\" ><img id=\""+z+"\" onclick=\"showAddNew("+z+")\" style=\"cursor:hand\" src=\"../images/giveresponse.gif\" width=\"63\" height=\"19\" border=\"0\" alt=\"Click here to give response\"></img></td>";
				        strDetailHtml += "</tr>";
				        strDetailHtml +="<tr>";
						strDetailHtml +="<td colspan=\"2\" align=\"right\" class=\"bold\"><hr color=\"#C1C1C1\"></td>";
						strDetailHtml +="</tr>";
						strDetailHtml+="<tr id=\"status"+z+"\" style=\"display:inline\" align=\"center\" >";
						if(respid==0)
						{
							if(strDetailHtml.equalsIgnoreCase("failure"))
							{
								strDetailHtml+="<td height=\"20\"  colspan=\"2\"></td>";
							}
							else if(strResult.equalsIgnoreCase("success"))
							{
								strDetailHtml+="<td  height=\"20\" colspan=\"2\" ></td>";
							}
							if(result.equalsIgnoreCase("failure"))
							{
								strDetailHtml+="<td height=\"20\"  colspan=\"2\"></td>";
							}
							
						}
						
						strDetailHtml+="<tr id=\"status1"+z+"\" style=\"display:none\" align=\"center\" >";
						strDetailHtml+="<td height=\"20\"  colspan=\"2\"></td>";
						strDetailHtml+="</tr>";
						strDetailHtml+="<tr id=\"rowid"+z+"\" align=\"center\" style=\"display:none\">";
						strDetailHtml+="<td valign=\"top\" colspan=\"2\"><form id=\""+z+"\" name=\"frmreply\" method=\"post\" action=\"queryresponseSuccess.do\" >";
						strDetailHtml+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
						strDetailHtml+=" <tr>";
						strDetailHtml+=" <td align=\"center\" colspan=\"4\"><textarea id=\"responsetext"+z+"\" name=\"responsetext\" cols=\"65\" rows=\"4\" ></textarea></td>";
						strDetailHtml+=" </tr>";
						strDetailHtml+=" <tr style=\"display:none\">";
						strDetailHtml+=" <td colspan=\"4\" align=\"left\"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						strDetailHtml+=" <input name=\"flag\" type=\"checkbox\" id=\"chkPublic"+z+"\" value=\"1\" onClick=\"validateCheckBox("+z+")\">";
						strDetailHtml+=" Click to make this Response Private</td>";
						strDetailHtml+="  </tr>";
						strDetailHtml+="<input type=\"hidden\" name=\"respType\" value=\"Consumer\">";
						strDetailHtml+="<input type=\"hidden\" name=\"respid\" value=\"0\">";
						strDetailHtml+="<input type=\"hidden\" name=\"compId\" value=\""+numCompId+"\">";
						 
						strDetailHtml+="<tr>";
						strDetailHtml+="<td align=\"center\" colspan=\"4\"><input name=\"Submit\" type=\"submit\"  value=\"Submit\" >&nbsp;&nbsp;&nbsp;";
						strDetailHtml+="<input name=\"cancel\" type=\"button\"  id=\""+z+"\" value=\"Cancel\" onClick=\"hideAddNew(this.id)\"></td>";
						
						strDetailHtml+="</tr>";
						strDetailHtml+=" </table>";
						strDetailHtml+="</form>";
						strDetailHtml+=" </td>";
						strDetailHtml+="</tr>";
						
						
						
			        }
					
			        if(!(pageid.equals("5") || pageid.equals("6")))
			        {
						strDetailHtml+="<tr  align=\"center\" >";
						strDetailHtml+="<td colspan=\"2\" valign=\"top\">";
						strDetailHtml+="<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >";
						if(responseVec.size()>0)
						{
							String bgrow="#CCECFF",bgtab="#F0FAF9";
							for(int i=0;i<responseVec.size();i++)
							{
								////////////System.out.println("responseVec>>>>>>>>>>>>>>"+responseVec);
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
								String cityName = rootMaster.getPlaceName(getDataSource(request, "advt"),cityId);
								int response_flag = Integer.parseInt(tempVec.elementAt(11).toString().trim());
								String strFMsg3 = "";
								
								////////////System.out.println("comp_type>>>>>>>>>>>>>>"+comp_type);
						       
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
								if(comp_type.equalsIgnoreCase("Corporates"))
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
								
								
								
							
								
								
								strDetailHtml+="<tr>";
								strDetailHtml+="<td colspan=\"2\" width=\"100%\" >";
								strDetailHtml+="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
								// titel font color:#CC3300;
								//footer color:#0099CC,arrow02.gif
								
								strDetailHtml+="<tr >";
								strDetailHtml+="<td colspan=\"2\" valign=\"top\" width=\"100%\">";
								strDetailHtml+="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
								strDetailHtml+="<tr bgcolor=\"#EDF3F8\" height=\"20\">";
								strDetailHtml+="<td width=\"1%\" height=\"20\">&nbsp;</td>";
								strDetailHtml+="<td width=\"65%\" align=\"left\" ><font color=\"#CC3300\"><strong>";
								if(comp_type.equalsIgnoreCase("Consumer"))
								{
									strDetailHtml+="&nbsp;Reply By Student";
								}
								else if(comp_type.equalsIgnoreCase("Advertiser"))
								{
									strDetailHtml+="&nbsp;Response By College";
								}
								else if(comp_type.equalsIgnoreCase("Corporates"))
								{
									strDetailHtml+="&nbsp;Response By Company";
								}
								
								strDetailHtml+="</strong></font></td>";
								strDetailHtml+="<td width=\"32%\" ></td>";
								strDetailHtml+="<td width=\"2%\" >&nbsp;</td>";
								strDetailHtml+="</tr>";
								strDetailHtml+="<tr bgcolor=\"#EDF3F8\" height=\"20\">";
								strDetailHtml+="<td width=\"1%\" >&nbsp;</td>";
								strDetailHtml+="<td width=\"97%\" colspan=\"2\" ><div align=\"justify\">"+strFMsg3+"</div></td>";
								strDetailHtml+="<td width=\"2%\" >&nbsp;</td>";
								strDetailHtml+="</tr>";
								
								strDetailHtml+="<tr bgcolor=\"#EDF3F8\" height=\"20\">";
								strDetailHtml+="<td width=\"1%\" >&nbsp;</td>";
								strDetailHtml+="<td width=\"65%\" ></td>";
								strDetailHtml+="<td width=\"32%\" ><div align=\"right\"><font color=\"#CC3300\"><strong>City:&nbsp;"+cityName+"</strong></div></td>";
								strDetailHtml+="<td width=\"2%\" >&nbsp;</td>";
								strDetailHtml+="</tr>";
								
								
								strDetailHtml+="<tr height=\"20\">";
								strDetailHtml+="<td width=\"1%\" >&nbsp;</td>";
								strDetailHtml+=" <td valign=\"top\"><div align=\"left\"><img valign=\"top\"  src=\"../images/arrow02.gif\"></img>&nbsp;&nbsp;&nbsp;<font color=\"#0099CC\"><strong>Posted by &nbsp;" ;
								
								strDetailHtml +=fname+" "+lname;
					        
								strDetailHtml += "&nbsp;on  "+respDate+"/"+tempVec.elementAt(4)+"  /</font><span class=\"student\" >"+comptype+"</span>";
					       
								strDetailHtml += "</div></td>";
								if((!comptype.equalsIgnoreCase("College")) && response_flag == 0)
								{
									
									strDetailHtml+="<td width=\"32%\" ><div align=\"right\"><img src=\"../images/reply.gif\" alt=\"Click here to give reply\"  id=\""+z+"\" onclick=\"showAddNew(this.id)\" style=\"cursor:hand\" ></img></div></td>";
									
								}else
								{
									strDetailHtml+="<td width=\"32%\" >&nbsp;</td>";
								}
						      	
								
					        
					        
					        strDetailHtml+="</tr>";
								
								
							strDetailHtml+="</table>";
							strDetailHtml+="</td>";
							strDetailHtml+="</tr>";
				        		strDetailHtml+="<tr id=\"status1"+z+"\" style=\"display:none\" align=\"center\" >";
				        		strDetailHtml+="<td height=\"20\"  colspan=\"4\"></td>";
				        		strDetailHtml+="</tr>";
				        		strDetailHtml+="<tr id=\"rowid"+z+"\" align=\"center\" style=\"display:none\">";
				        		strDetailHtml+="<td valign=\"top\" colspan=\"4\"><form id=\""+z+"\" name=\"frmreply\" method=\"post\" action=\"queryresponseSuccess.do\" >";
				        		strDetailHtml+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
				        		strDetailHtml+="<tr>";
				        		strDetailHtml+="<td colspan=\"4\" align=\"center\"><textarea id=\"responsetext"+z+"\" name=\"responsetext\" cols=\"65\" rows=\"4\"  ></textarea></td>";
				        		strDetailHtml+="</tr>";
				        		strDetailHtml+=" <tr style=\"display:none\">";
				        		strDetailHtml+=" <td colspan=\"4\" align=\"center\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				        		strDetailHtml+=" <input name=\"flag\" type=\"checkbox\" id=\"chkPublic"+z+"\" value=\"1\"  onClick=\"validateCheckBox("+z+")\">";
				        		strDetailHtml+=" <span class=\"bold\">Click to make this Response Private  </span></td>";
				        		strDetailHtml+="  </tr>";
				        		strDetailHtml+="<input type=\"hidden\" name=\"respType\" value=\""+comptype+"\">";
				        		strDetailHtml+="<input type=\"hidden\" name=\"respid\" value=\""+responseId+"\">";
				        		strDetailHtml+="<input type=\"hidden\" name=\"compId\" value=\""+numCompId+"\">";
				        		strDetailHtml+="</tr>";
				                strDetailHtml+="<tr>";
				        		strDetailHtml+="<td colspan=\"4\" align=\"center\"><input name=\"Submit\" type=\"submit\"  value=\"Submit\" >&nbsp;&nbsp;&nbsp;";
				        		strDetailHtml+="<input name=\"cancel\" type=\"button\"  id=\""+z+"\" value=\"Cancel\" onClick=\"hideAddNew(this.id)\"></td>";
				        		
				        		strDetailHtml+="</table>";
				        		strDetailHtml+="</form>";
				        		strDetailHtml+="  </td>";
								strDetailHtml+="</tr>";
								strDetailHtml+="</table>";
								strDetailHtml+="  </td>";
								strDetailHtml+="</tr>";
							}
							
							DataSource ds = getDataSource(request,"advt");
							IndvMaster indvMaster = new IndvMaster();
							
							 strDetailHtml+="<table cellpading=\"0\" cellspacing=\"0\" width=\"100%\">";
								
							  if(commentVec.size()>0)
								{
									
								  for(int i=0;i<commentVec.size();i++)
									{
										
										
										  Vector tempVec = (Vector)commentVec.elementAt(i);
											String commentText = tempVec.elementAt(0).toString().trim();
											String cdate = tempVec.elementAt(1).toString().trim();
											String ctime = tempVec.elementAt(4).toString().trim();
											String cfname = tempVec.elementAt(6).toString().trim();
											String clname = tempVec.elementAt(7).toString().trim();
											int ccityId = Integer.parseInt(tempVec.elementAt(8).toString().trim());
											String ccityName = rootMaster.getPlaceName(getDataSource(request, "advt"),ccityId);
											
											
											int comp_id = Integer.parseInt(tempVec.elementAt(5).toString().trim());
											String compName = rootMaster.getComp_Type(getDataSource(request, "advt"),comp_id);
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
											
											String strFMsg1 = "";
											String strMessage1 = commentText;
											
											for(int y=0; y<strMessage1.length(); y++)
											{ 
												int num1 = (int)strMessage1.charAt(y);
												if(num1 == 10)
												{
													strFMsg1 = strFMsg1+"<br>"+strMessage1.charAt(y);
												}
												else
												{
													strFMsg1 = strFMsg1+strMessage1.charAt(y);
												}
											}
										 

											strDetailHtml+="<tr>";
											strDetailHtml+="<td colspan=\"2\" width=\"100%\" >";
											strDetailHtml+="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
											// titel font color:#CC3300;
											//footer color:#0099CC,arrow02.gif
											
											strDetailHtml+="<tr >";
											strDetailHtml+="<td colspan=\"2\" valign=\"top\" width=\"100%\">";
											strDetailHtml+="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
											
											strDetailHtml+="<tr bgcolor=\"#F3F3F5\" height=\"20\">";
											strDetailHtml+="<td width=\"1%\" height=\"20\">&nbsp;</td>";
											strDetailHtml+="<td width=\"65%\" align=\"left\" ><font color=\"#CC3300\"><strong>Comment</strong></font></td>";
											strDetailHtml+="<td width=\"32%\" ></td>";
											strDetailHtml+="<td width=\"2%\" >&nbsp;</td>";
											strDetailHtml+="</tr>";
											strDetailHtml+="<tr bgcolor=\"#F3F3F5\" height=\"20\">";
											strDetailHtml+="<td width=\"1%\" >&nbsp;</td>";
											strDetailHtml+="<td width=\"97%\" colspan=\"2\" ><div align=\"justify\">"+strFMsg1+"</div></td>";
											strDetailHtml+="<td width=\"2%\" >&nbsp;</td>";
											strDetailHtml+="</tr>";
											
											strDetailHtml+="<tr bgcolor=\"#F3F3F5\" height=\"20\">";
											strDetailHtml+="<td width=\"1%\" >&nbsp;</td>";
											strDetailHtml+="<td width=\"97%\" colspan=\"2\" ></td>";
											strDetailHtml+="<td width=\"2%\" >&nbsp;</td>";
											strDetailHtml+="</tr>";
											
											
											strDetailHtml+="<tr height=\"20\">";
											strDetailHtml+="<td width=\"1%\" >&nbsp;</td>";
											strDetailHtml+=" <td valign=\"top\"><div align=\"left\"><img valign=\"top\"  src=\"../images/arrow02.gif\"></img>&nbsp;&nbsp;&nbsp;<font color=\"#0099CC\"><strong>Posted by &nbsp;" ;
											strDetailHtml +=cfname+" "+clname;
								        	strDetailHtml += "&nbsp;on  "+cdate+"/"+ctime+"  /</font><span class=\"student\" >"+compName+"</span>";
											strDetailHtml += "</div></td>";
											strDetailHtml+="<td width=\"32%\" >&nbsp;</td>";
											strDetailHtml+="</tr>";
											
											
										strDetailHtml+="</table>";
										strDetailHtml+="</td>";
										strDetailHtml+="</tr>";
															
															
									}
								}
						        
						        else
						        { 	
						        	strDetailHtml += "<tr align=\"center\">";
									strDetailHtml += "<td height=\"20\" valign=\"center\" colspan=\"2\" class=\"bold\">  No Comment </td>";
									strDetailHtml += "</tr>";
						        	
						        }
							  	
							  strDetailHtml+="</table >";
							
						}
						else
							
						{  
							
							
							strDetailHtml+="<tr  align=\"center\"  id=\"noresponse\" style=\"display:inline\" >";
							strDetailHtml+="<td  colspan=\"2\"> No Response</td>";
							strDetailHtml+="</tr>";
							
						}
			        
						strDetailHtml+="</table>";
						strDetailHtml+="  </td>";
						strDetailHtml+="</tr>";
						
						strDetailHtml += "<tr align=\"left\" height=\"5\">";
				        strDetailHtml += "<td colspan=\"2\"  height=\"5\" ><hr color=\"#C1C1C1\"></td>";
				        strDetailHtml += "</tr>";
			        
			        }
			        
			    	
			        
			        strDetailHtml+="</table>";
				
				return strDetailHtml;
				
			
			
			
			
		}
		///////////////////////////////////////////////////////////
		
		private String getPages(int minVal, int maxVal, int numSize, String getPages)
		{
			String strResult ="<select name=\"paging\" onchange=\"retrieveadvtQueryDetailURL('queryDetail.do?'+this.value);\">";
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
		/**
		 * Ajay Kumar Jha
		 * @param numCopmId
		 * @return
		 */
		private String getComplaintUnnamed(HttpServletRequest request, int numCopmId, int numbrandId, int numCatId)
		{
			////////////System.out.println("numCopmId "+numCopmId);
			////////////System.out.println("numbrandId "+numbrandId);
			////////////System.out.println("numCatId "+numCatId);
			IndvMaster im = new IndvMaster();
			AdvtMaster am = new AdvtMaster();
			Vector unnamedVec = am.getComplaintUnnamedMap(getDataSource(request, "advt"), numbrandId, numCatId);
			////////////System.out.println("unnamedVec Size "+unnamedVec.size());
			////////////System.out.println("unnamedVec "+unnamedVec);
			Vector dataVec = im.getComplaintUnnamedData(getDataSource(request, "advt"), numCopmId);
			//////////////System.out.println("Data Vec in Details Arun Size "+dataVec.size());
			//////////////System.out.println("Data Vec in Details Arun "+dataVec);
			String strResult = "";
			if(unnamedVec.size() == 175 && dataVec.size() == 35)
			{
				Vector<String> FinalDataVec = new Vector<String>();
				if(dataVec.size()>10)
				{
					int numCount2=0, numCount3=3, numCount4=4;
					String strFlag1="";
					for(int k=0; k<10; k++)
					{
						String strSubCat1 = dataVec.elementAt(k).toString().trim();
						String strSubCatVal1 = "";
						if(strSubCat1.length()>0)
						{
							char [] chars = strSubCat1.toCharArray();
							String strFlag="";
							for(int j=0; j<chars.length; j++)
							{
								strFlag +=(Character.isDigit(chars[j])) ? "false" : "true";
							}
							if(strFlag.indexOf("true") <= -1)
							{
								if(k==0)
								{
									strSubCatVal1 = im.getValueSubCat1(getDataSource(request, "advt"), Integer.parseInt(strSubCat1.trim()));
								}
								if(k==1)
								{
									strSubCatVal1 = im.getValueSubCat2(getDataSource(request, "advt"), Integer.parseInt(strSubCat1.trim()));
								}
								if(k==2)
								{
									strSubCatVal1 = im.getValueSubCat3(getDataSource(request, "advt"), Integer.parseInt(strSubCat1.trim()));
								}
								if(k==3)
								{
									strSubCatVal1 = im.getValueSubCat4(getDataSource(request, "advt"), Integer.parseInt(strSubCat1.trim()));
								}
								if(k==4)
								{
									strSubCatVal1 = im.getValueSubCat5(getDataSource(request, "advt"), Integer.parseInt(strSubCat1.trim()));
								}
								if(k==5)
								{
									strSubCatVal1 = im.getValueSubCat6(getDataSource(request, "advt"), Integer.parseInt(strSubCat1.trim()));
								}
								if(k==6)
								{
									strSubCatVal1 = im.getValueSubCat7(getDataSource(request, "advt"), Integer.parseInt(strSubCat1.trim()));
								}
								if(k==7)
								{
									strSubCatVal1 = im.getValueSubCat8(getDataSource(request, "advt"), Integer.parseInt(strSubCat1.trim()));
								}
								if(k==8)
								{
									strSubCatVal1 = im.getValueSubCat9(getDataSource(request, "advt"), Integer.parseInt(strSubCat1.trim()));
								}
								if(k==9)
								{
									strSubCatVal1 = im.getValueSubCat10(getDataSource(request, "advt"), Integer.parseInt(strSubCat1.trim()));
								}
								if(strSubCatVal1.equalsIgnoreCase("failure"))
								{
									strSubCatVal1="";
								}
							}
							else
							{
								strSubCatVal1 = strSubCat1;
							}
						}
						String strFName=unnamedVec.elementAt(numCount2).toString().trim();
						//String strType = unnamedVec.elementAt(i+1).toString().trim();
						//String strDetailHtml = unnamedVec.elementAt(i+2).toString().trim();
						int numMandatory = Integer.parseInt(unnamedVec.elementAt(numCount3).toString().trim());
						int numVisible = Integer.parseInt(unnamedVec.elementAt(numCount4).toString().trim());
						if(numMandatory==1)
						{
							strFName=strFName+":";
						}
						else
						{
							strFName = strFName+":";
						}
						strFlag1 +=(numVisible == 1) ? "false" : "true";
						//////////////System.out.println("str Flag1 "+strFlag1);
						if((strFlag1.indexOf("true") <= -1) && (!strFName.equalsIgnoreCase(""))&&(!strSubCatVal1.equalsIgnoreCase("")))
						{
							FinalDataVec.add(strFName+"~"+strSubCatVal1);
						}
						numCount2=numCount2+5;
						numCount3=numCount3+5;
						numCount4=numCount4+5;
					}
					//////////////System.out.println("FinalDataVec "+ FinalDataVec);
				}
				int numCount1=10;
				for(int i=50; i<unnamedVec.size(); i=i+5)
				{
					String strFName=unnamedVec.elementAt(i).toString().trim();
					//String strType = unnamedVec.elementAt(i+1).toString().trim();
					//String strDetailHtml = unnamedVec.elementAt(i+2).toString().trim();
					int numMandatory = Integer.parseInt(unnamedVec.elementAt(i+3).toString().trim());
					int numVisible = Integer.parseInt(unnamedVec.elementAt(i+4).toString().trim());
					if(numMandatory==1)
					{
						strFName=strFName+":";
					}
					else
					{
						strFName = strFName+":";
					}
					if((numVisible==1) && (!strFName.equalsIgnoreCase(""))&&(!dataVec.elementAt(numCount1).toString().trim().equalsIgnoreCase("")))
					{
						FinalDataVec.add(strFName+"~"+dataVec.elementAt(numCount1).toString().trim());
					}
					numCount1++;
					//////////////System.out.println("FinalDataVec "+i+" "+FinalDataVec);
				}
				//////////////System.out.println("Final Data Vec Size "+FinalDataVec.size());
				//////////////System.out.println("Final Data Vec "+FinalDataVec);
				for(int j=0; j<FinalDataVec.size(); j++)
				{
					String strFVal = "", strVal="";
					String strDetailHtml = FinalDataVec.elementAt(j).toString().trim();
					String arrValue[] = strDetailHtml.split("~");
					if(arrValue.length==1)
					{
						strFVal = arrValue[0];
						strVal="";
					}
					else
					{
						strFVal = arrValue[0];
						strVal= arrValue[1];
					}
					strResult +="<tr>";
					strResult +="<td align=\"right\" class=\"bold\">"+strFVal+"</td>";
					strResult +="<td >&nbsp;"+strVal+"</td>";
					strResult +="</tr>";
				}
			}
			
			return strResult;
		}
	}
