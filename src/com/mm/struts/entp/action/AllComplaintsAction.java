 
package com.mm.struts.entp.action; 


import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class AllComplaintsAction extends Action {
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
		//////System.out.println("remander..........."+remander);
		
		String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
		Vector allRights =(Vector)session.getAttribute("allRights");
		String adminFlag = allRights.elementAt(18).toString();
		
		
		
		
		String pageid = (request.getParameter("pageid")!=null)? request.getParameter("pageid").trim() : "1";
		String qtp = (request.getParameter("qtp")!=null) ? request.getParameter("qtp").trim() : "technical" ;
		session.setAttribute("pageid", pageid);
		//////System.out.println("pageid..........."+pageid);
		
		String strPgType = (request.getParameter("pgtyp")!=null) ? request.getParameter("pgtyp").trim() : "Details";
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "creation_date";
		
		
		
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 10;
		EntpMaster entpMaster = new EntpMaster();
		RootMaster rootMaster = new RootMaster();
		
		
		
				
		
		String strComplaintHtml = "";
		
				
		if(strPgType.equalsIgnoreCase("Details"))
		{
			String userResult = (request.getParameter("userResult")!=null) ? request.getParameter("userResult").trim() : "";
			String strResult = (request.getParameter("strResult")!=null) ? request.getParameter("strResult").trim() : "";
			String numCopmId = (request.getParameter("numCopmId")!=null) ? request.getParameter("numCopmId").trim() : "0";
			session.setAttribute("numCopmId", numCopmId);
			session.setAttribute("pgtyp",strPgType);
			if(!numCopmId.equalsIgnoreCase("0"))
	    	{
	    		Vector<String> paramVec2 = new Vector<String>();
	    		paramVec2.add(numCopmId);
	    		if(pageid.equalsIgnoreCase("8"))
	    		{
	    			paramVec2.add("0");
	    		}
	    		else
	    		{
	    			paramVec2.add(uId);
	    		}
	    		paramVec2.add(adminFlag);
	    		//////System.out.println("paramVec2 "+paramVec2);
	    		////System.out.println("paramVec1 "+paramVec1);
	//	    	-------------------------
		    	//int numArray[] = md.getCompanyMoves(numCopmId, getDataSource(request, "main"), minVal, maxVal, strFShortBy);
		    	
	//	    	-------------------------
	    	
		    
		    	Vector complaintVec = entpMaster.getComplaintDetails(getDataSource(request, "entp"),paramVec2);
		    	////System.out.println("complaint details Vector >>>>>>>>>>>"+complaintVec);
	    	/**
	    	 * support.setFieldVec("int", "complaint_id");            	   //0
			support.setFieldVec("string", "complaint_title");              //1
			support.setFieldVec("string", "complaint_text");               //2
			support.setFieldVec("string", "relevent_info");                //3	
			support.setFieldVec("int", "category");                        //4			
			support.setFieldVec("string", "creation_date");                //5
			support.setFieldVec("string", "publish_date");                 //6
			support.setFieldVec("string", "creation_time");  			   //7
			support.setFieldVec("string", "publish_time");  			   //8
			support.setFieldVec("string", "lastmodify_date"); 			   //9
			support.setFieldVec("string", "lastmodify_time"); 			   //10
			support.setFieldVec("int", "login_id"); 					   //11
			support.setFieldVec("int", "companyid"); 					   //12
			support.setFieldVec("string", "timepart");					   //13
			support.setFieldVec("int", "viewcount");                       //14
			support.setFieldVec("int", "entp_id");                         //15
			support.setFieldVec("int", "cu_id");                           //16	
			support.setFieldVec("String", "fcom_id");                      //17
			support.setFieldVec("int", "advt_id");                         //18
			support.setFieldVec("int", "advtL_id");                        //19
			support.setFieldVec("int", "dealer_id");                       //20
			support.setFieldVec("int", "tag_id");                          //21
			support.setFieldVec("String", "resolve_date");                 //22
			support.setFieldVec("String", "closed_date");                  //23
			support.setFieldVec("int", "brandFlag");                       //24
			support.setFieldVec("int", "publish_flag");                    //25
			support.setFieldVec("string", "publish_on");                   //26
			support.setFieldVec("string", "other");                        //27
		    	 */
		    	int advt_id = Integer.parseInt(complaintVec.elementAt(18).toString().trim());
		    	////System.out.println("advt_id, ....."+advt_id);
		    	Vector brandVec = rootMaster.getCompanyDetail(getDataSource(request, "entp"), advt_id);		    	
		    	////System.out.println("brandVec, ....."+brandVec);
		    	int advtl_id =  Integer.parseInt(complaintVec.elementAt(19).toString().trim());
		    	Vector brandUserVec = new Vector();
		    	//Vector brandUserVec = entpMaster.getAllUserList(getDataSource(request, "entp"), advt_id);
		    	
		    	int login_id = Integer.parseInt(complaintVec.elementAt(11).toString().trim());
		    	////System.out.println("login_id, ....."+login_id);
		    	Vector complainantVec = rootMaster.getUserInfo(getDataSource(request, "entp"), login_id);		    	
		    	////System.out.println("complainantVec, ....."+complainantVec);
		    	//complainant Information
		    	int consCityId = Integer.parseInt(complainantVec.elementAt(6).toString().trim());
		    	String consCityname = rootMaster.getPlaceName(getDataSource(request, "entp"), consCityId);		    	
		    	int consStateId = Integer.parseInt(complainantVec.elementAt(7).toString().trim());
		    	String consStateName = rootMaster.getStateName(getDataSource(request, "entp"), consStateId);		    	
		    	int consCountryId = Integer.parseInt(complainantVec.elementAt(11).toString().trim());
		    	String consCountryname = rootMaster.getCountryName(getDataSource(request, "entp"), consCountryId);
		    	//////System.out.println("complainantVec, ...>>>>>>>>>>..");
		    	int dealer_id = Integer.parseInt(complaintVec.elementAt(20).toString().trim());
		    	//////System.out.println("dealer_id, ....."+dealer_id);
		    	Vector delareVec = new Vector();
		    	
		    	if(dealer_id!= 0){
		    		delareVec = entpMaster.getDealerDetail(getDataSource(request, "entp"), dealer_id);
		    		//delare Information
		    		////System.out.println("delareVec, ...>>>>>>>>>>.."+delareVec);
		    		int dealerCityId = Integer.parseInt(delareVec.elementAt(6).toString().trim());
		    		String dealerCityname = rootMaster.getPlaceName(getDataSource(request, "entp"), dealerCityId);		    	
		    		int dealerStateId = Integer.parseInt(delareVec.elementAt(4).toString().trim());
		    		String dealerStateName = rootMaster.getStateName(getDataSource(request, "entp"), dealerStateId);		    	
		    		int dealerCountryId = Integer.parseInt(delareVec.elementAt(12).toString().trim());
		    		String dealerCountryname = rootMaster.getCountryName(getDataSource(request, "entp"), dealerCountryId);
		    		//////System.out.println("delareVec, ...>>>>111111111>>..");
		    		delareVec.add(dealerCityname);//13
		    		delareVec.add(dealerStateName);//14
		    		delareVec.add(dealerCountryname);//15
		    		////System.out.println("delareVec,after ...>>>>>>.."+delareVec);
		    	}
		    	//////System.out.println("delareVec, ....."+delareVec);
		    	
		    	String catName="";
		    	int cat_id = Integer.parseInt(complaintVec.elementAt(4).toString().trim());
				Vector catNamevec = rootMaster.getCategoryName(getDataSource(request, "entp"),cat_id);
				if(Integer.parseInt(catNamevec.elementAt(1).toString().trim())==0){
					catName=catNamevec.elementAt(0).toString().trim()+"(Other)";
				}else
				{
					catName=catNamevec.elementAt(0).toString().trim();
				}
		    	
		    	
		    	int tag_id = Integer.parseInt(complaintVec.elementAt(21).toString().trim());
		    		
		    
		    	String tagName = rootMaster.getStrTagName(getDataSource(request, "entp"), tag_id);		    	
		    	//////System.out.println("tagName, ....."+tagName);
//		    	get all company list with the no of responses accorrding to company type. rootmaster.getResponseList()
				Vector<String> respDataVec = new Vector<String>();
				respDataVec.add(numCopmId);
				respDataVec.add("Enterprise");
				Vector responseVec = rootMaster.getResponseList(getDataSource(request, "entp"), respDataVec);
				////System.out.println("responseVec>>>>>>>>>>>>"+responseVec);
				int totleRsponse = responseVec.size();
				int indvResponse = 0;
				int advtResponse = 0;
				int entpResponse = 0;
				//////System.out.println("responseVec>>>"+responseVec);
				
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
				}
		    	//end of response getting code
		    		    	
		    	Vector<String> infoVec = new Vector<String>();
		    	infoVec.add(catName);//0// category name
		    	infoVec.add(tagName);//1// status name
		    	infoVec.add(consCityname);//2// Consumer City nane
		    	infoVec.add(consStateName);//3// Consumer State nane
		    	infoVec.add(consCountryname);//4// Consumer Country nane
		    	
		    	infoVec.add(String.valueOf(totleRsponse));//5// total no of responses
		    	infoVec.add(String.valueOf(indvResponse));//6// Consumer responses
		    	infoVec.add(String.valueOf(advtResponse));//7// brand responses
		    	infoVec.add(String.valueOf(entpResponse));//8// Core responses
		    	infoVec.add(qtp);//9// Query Type
		    	
		    	String strUnnamed = getComplaintUnnamed(request, Integer.parseInt(numCopmId.trim()), advt_id, cat_id);
		    	//////System.out.println("strUnnamed "+strUnnamed);
		    	//////System.out.println("infoVec, ....."+infoVec);
		    	strComplaintHtml = getComplaintDetail(strUnnamed,userResult,request,strResult,numCopmId, complaintVec, brandVec,advtl_id,brandUserVec,  complainantVec, delareVec,responseVec, infoVec,pageid, strShortBy, remander);
	    	}
		}
		////System.out.println(strComplaintHtml);
		
		
		
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
	
	
	private String getComplaintDetail(String strUnnamed,String userResult,HttpServletRequest request,String strResult,String numCopmId, Vector complaintVec,Vector brandVec,int advtl_id,Vector brandUserVec,Vector complainantVec,Vector delareVec ,Vector responseVec ,Vector infoVec,String pageid, String strShortBy, int remander){
		
		int z=0;
		RootMaster rootMaster = new RootMaster();
		HttpSession session = request.getSession();
		String result = (session.getAttribute("result")!= null)? (String)session.getAttribute("result") : "";
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
		
		
		/////////////////////new////////////////////////////////////////////////
		
		 strDetailHtml+="<table  height = \"350\" width=\"100%\" >";
		  strDetailHtml+="<tr>";
		  //////////////////////////// start td for complaint detail
			strDetailHtml+="<td width=\"57%\" valign=\"top\"><div style =\" height:350px; overflow-y:scroll; \">";
			strDetailHtml+="<table width=\"100%\"   border=\"0\" >";
			
			if(userResult.length()>0)
			{
				strDetailHtml += "<tr  >";
				if(userResult.equals("failure"))
				{
					strDetailHtml += "<td height=\"30\"  align=\"center\" valign=\"middle\" colspan=\"4\"  >User Creation "+userResult+"</td>";
				}
				else
				{
					strDetailHtml += "<td height=\"30\"  align=\"center\" valign=\"middle\" colspan=\"4\"  >User Creation "+userResult+"</td>";
				}
					strDetailHtml += "</tr>";
			}
			
			
			   
			  strDetailHtml += "<tr align=\"left\">";
		        strDetailHtml += "<td width=\"35%\" align=\"left\"class=\"bold\" > &nbsp;&nbsp;&nbsp;Query Title:</td>";
		        strDetailHtml += "<td  width=\"65%\" align=\"left\" ></td>";
		        strDetailHtml += "</tr>";
		        strDetailHtml += "<tr >";
		        strDetailHtml += "<td colspan=\"2\"  align=\"left\"  width=\"100%\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;"+complaintVec.elementAt(1).toString().trim()+" </td>";
		       
		        strDetailHtml += "</tr>";
		           
			
		  
	       
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td colspan=\"2\" ><hr color=\"#ff722b\"></td>";
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
		  
			
			
				strDetailHtml += "<tr align=\"left\">";
		        strDetailHtml += "<td  align=\"left\" class=\"bold\" > &nbsp;&nbsp;&nbsp;Query Text:</td>";
		        strDetailHtml += "<td  align=\"left\" ><span ></span></td>";
		        strDetailHtml += "</tr>";
		        strDetailHtml += "<tr align=\"left\">";
		        strDetailHtml += "<td  colspan=\"2\" ><table width=\"100%\" align=\"center\"><tr><td width=\"5%\"></td><td align=\"left\"> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+strFMsgcompl+"</td><td width=\"5%\"></td></tr></table></td>";
		        strDetailHtml += "</tr>";
		        strDetailHtml += "<tr align=\"left\">";
		        strDetailHtml += "<td colspan=\"2\" ><hr color=\"#ff722b\"></td>";
		        strDetailHtml += "</tr>";
		        
		        if((complaintVec.elementAt(3).toString()).length()!=0){
					String strFMsgcomp3 = "";
					String strMessagecomp3 = complaintVec.elementAt(3).toString()!=null ? complaintVec.elementAt(3).toString().trim():"";
					
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
					
					
					strDetailHtml += "<tr align=\"left\">";
					strDetailHtml += "<td  align=\"left\" class=\"bold\" > &nbsp;&nbsp;&nbsp;Relevent Text:</td>";
			        strDetailHtml += "<td  align=\"left\" ><span ></span></td>";
			        strDetailHtml += "</tr>";	
			        
			        strDetailHtml += "<tr align=\"left\">";
			        strDetailHtml += "<td width=\"80%\" colspan=\"2\" >&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+strFMsgcomp3+"</td>";
			        strDetailHtml += "</tr>";	
			       
			        
				
				}else{
					strDetailHtml += " <tr align=\"left\">";
			        strDetailHtml += " <td height=\"35\" colspan=\"2\" align=\"center\" class=\"bold\">Relevent Information is not provided.</td>";
			        strDetailHtml += " </tr>";
					
				}
		        
		     
		
		  strDetailHtml+="</table>";
		  strDetailHtml+="</div></td>";
		  // End td for complaint detail
		  strDetailHtml+="<td width=\"1%\"></td>";
		
		  // start td for additional detail
		  
		    strDetailHtml+="<td width=\"41%\" valign=\"top\"><div style =\" height:350px; overflow-y:scroll; \">";
		    strDetailHtml+="<table width=\"100%\" valign=\"top\"  border=\"0\" >";
		  	strDetailHtml += "<tr align=\"left\" valign=\"top\" >";
	        strDetailHtml += "<td  align=\"left\" valign=\"top\"  colspan=\"2\" class=\"bold\"></td>";
	        strDetailHtml += "</tr>";
	        
	        strDetailHtml += "<tr align=\"left\" valign=\"top\">";
	        strDetailHtml += "<td  align=\"right\"class=\"bold\" >Query ID:  </td>";
	        strDetailHtml += "<td  align=\"left\" ><span >"+complaintVec.elementAt(17).toString().trim()+" </span></td>";
	        strDetailHtml += "</tr>";
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\"class=\"bold\" > Date: </td>";
	        strDetailHtml += "<td  align=\"left\" ><span >"+complaintVec.elementAt(5).toString().trim()+"</span></td>";
	        strDetailHtml += "</tr>";
	        
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\"class=\"bold\" > Category: </td>";
	        strDetailHtml += "<td  align=\"left\" ><span >"+infoVec.elementAt(0).toString().trim()+" </span></td>";
	        strDetailHtml += "</tr>";
	        
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td colspan=\"2\" ><hr color=\"#ff722b\"></td>";
	        strDetailHtml += "</tr>";
	        
	         
	        
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td colspan=\"2\" >";
	        strDetailHtml += "<table>";
	        strDetailHtml += "<tr>";
	        strDetailHtml += "<td valign=\"top\" >";
	        strDetailHtml += "<table >";
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"left\" class=\"bold\" >&nbsp;&nbsp;&nbsp;&nbsp; Status: </td>";
	        strDetailHtml += " <td align=\"left\" valign=\"middle\" ><span >"+infoVec.elementAt(1).toString().trim()+"</span></td>";
	        strDetailHtml += "</tr>";
	       
	        strDetailHtml += "</table>";
	        strDetailHtml += "</td>";
	      
	        strDetailHtml += "<td  valign=\"top\">";
	        strDetailHtml += "<table>";
	        strDetailHtml += "<tr align=\"center\">";
	        strDetailHtml += "<td  align=\"center\"class=\"bold\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Publish: </td>";
	                
		    if((complaintVec.elementAt(26).toString()).equalsIgnoreCase("1@Week"))
			{
		    strDetailHtml += " <td  align=\"left\"><span >1 Week</span></td>";
			}
		    else if((complaintVec.elementAt(26).toString()).equalsIgnoreCase("2@Week"))
			{
		    strDetailHtml += " <td   align=\"left\"><span >2 Week</span></td>";
			}
		    else if((complaintVec.elementAt(26).toString()).equalsIgnoreCase("4@Week"))
			{
		    strDetailHtml += " <td   align=\"left\"><span >4 Week</span></td>";
			}
		    else if((complaintVec.elementAt(26).toString()).equalsIgnoreCase("6@Week"))
			{
		    strDetailHtml += " <td   align=\"left\"><span >6 Week</span></td>";
			}
		    else{
		    strDetailHtml += " <td   align=\"left\"><span >Immediate</span></td>";	   
		    }
	        
	        
	     
	        strDetailHtml += "</tr>";
	        strDetailHtml += "</table>";
	        strDetailHtml += "</td>";
	        
	        strDetailHtml += "</tr>";
	        strDetailHtml += "</table>";
	        strDetailHtml += "</td>";
	        strDetailHtml += "</tr>";
	        
	       
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td colspan=\"2\" ><hr color=\"#ff722b\"></td>";
	        strDetailHtml += "</tr>";
	         
	        
		    strDetailHtml += " <tr align=\"left\">";
	        strDetailHtml += "<td colspan=\"2\" class=\"bold\">company Information</td>";
	        strDetailHtml += " </tr>";
	        
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\"class=\"bold\" > company Name: </td>";
	        strDetailHtml += "<td  align=\"left\" ><span >"+brnadName+" </span></td>";
	        strDetailHtml += "</tr>";
	        
	       
	        
	        strDetailHtml += strUnnamed;
	        for(int i=0;i<brandUserVec.size();i++)
	        {
	        	Vector tempVec = (Vector)brandUserVec.elementAt(i);
	        
	        	strDetailHtml += "<tr align=\"left\">";
	  	        strDetailHtml += "<td  align=\"right\"class=\"bold\" > Contact Person: </td>";
	  	        strDetailHtml += "<td  align=\"left\" ><span >"+tempVec.elementAt(1).toString()+" "+tempVec.elementAt(2).toString()+" </span></td>";
	  	        strDetailHtml += "</tr>";
	  	        strDetailHtml += "<tr align=\"left\">";
		        strDetailHtml += "<td  align=\"right\"class=\"bold\" > Designation:</td>";
		        strDetailHtml += "<td  align=\"left\" ><span >N/A </span></td>";
		        strDetailHtml += "</tr>";
		        strDetailHtml += "<tr align=\"left\">";
		        strDetailHtml += "<td  align=\"right\"class=\"bold\" > EMail Id: </td>";
		        strDetailHtml += "<td  align=\"left\" ><span >"+tempVec.elementAt(3).toString()+" </span></td>";
		        strDetailHtml += "</tr>";
		                
			    
	        }
	        strDetailHtml += " <tr align=\"left\">";
	        strDetailHtml += " <td colspan=\"2\" ><hr color=\"#ff722b\"></td>";
	        strDetailHtml += " </tr>";
	         
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += " <td colspan=\"2\" class=\"bold\">Dealer Information</td>";
	        strDetailHtml += "</tr>";
	        if(delareVec.size()!=0)
			{
	        strDetailHtml += "<tr align=\"left\">";	
	        strDetailHtml += "<td colspan=\"2\">";
	        strDetailHtml += "<table align=\"center\" width=\"90%\">";
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\" class=\"bold\" width=\"50%\"> Dealer Name: </td>";
	        strDetailHtml += "<td  align=\"left\"   width=\"50%\">"+delareVec.elementAt(1).toString().trim()+" </td>";
	        strDetailHtml += "</tr>";
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\" class=\"bold\" > Dealer Address: </td>";
	        strDetailHtml += "<td  align=\"left\" >"+delareVec.elementAt(11).toString().trim()+" </td>";
	        strDetailHtml += "</tr>";
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\" class=\"bold\" > Dealer Country: </td>";
	        strDetailHtml += "<td  align=\"left\"  >"+delareVec.elementAt(15).toString().trim()+" </td>";
	        strDetailHtml += "</tr>";
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\" class=\"bold\" > Dealer State: </td>";
	        strDetailHtml += "<td  align=\"left\"  >"+delareVec.elementAt(14).toString().trim()+" </td>";
	        strDetailHtml += "</tr>";
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\" class=\"bold\" > Dealer City: </td>";
	        strDetailHtml += "<td  align=\"left\"  >"+delareVec.elementAt(13).toString().trim()+" </td>";
	        strDetailHtml += "</tr>";
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\" class=\"bold\" > Dealer District: </td>";
	        strDetailHtml += "<td  align=\"left\"  >"+delareVec.elementAt(13).toString().trim()+" </td>";
	        strDetailHtml += "</tr>";
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\" class=\"bold\" > Contact Person: </td>";
	        strDetailHtml += "<td  align=\"left\" >"+delareVec.elementAt(2).toString().trim()+" </td>";
	        strDetailHtml += "</tr>";
	        //System.out.println("dealer----mobileno---"+delareVec.elementAt(8).toString().trim());
	        //System.out.println("dealer----mobileno---"+delareVec);
	        	String temMno="N/A";
			    if((delareVec.elementAt(8).toString().trim().length()!=0)||(delareVec.elementAt(9).toString().trim().length()!=0)){
					//System.out.println("insite");
					if(delareVec.elementAt(8).toString().trim().length()!=0){
					String temPno=delareVec.elementAt(8).toString().trim();
					
					temMno = temPno.replace("~", "-");
					}else
					{
						String temPno=delareVec.elementAt(9).toString().trim();
						
						temMno = temPno.replace("~", "-");
					}
				}
			
		    
		    
		    
		    strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\" class=\"bold\" > Mob No./Ph No.: </td>";
	        strDetailHtml += "<td  align=\"left\"  >"+temMno+" </td>";
	        strDetailHtml += "</tr>";
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\" class=\"bold\" > EMail Id: </td>";
	        strDetailHtml += "<td  align=\"left\"  >"+delareVec.elementAt(7).toString().trim()+" </td>";
	        strDetailHtml += "</tr>";
	        strDetailHtml += "</table>";
	        strDetailHtml += "</td>";
	        strDetailHtml += "</tr>";
		    
			}else{
		    strDetailHtml += " <tr align=\"left\">";
	        strDetailHtml += " <td height=\"35\" colspan=\"2\" align=\"center\" class=\"bold\">Dealer Information is not provided.</td>";
	        strDetailHtml += " </tr>";
			}
	        
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td colspan=\"2\" ><hr color=\"#ff722b\"></td>";
	        strDetailHtml += "</tr>";
	        
	        
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"left\" colspan=\"2\" class=\"bold\">Query Detail</td>";
	        strDetailHtml += "</tr>";
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\"class=\"bold\" > Name:</td>";
	        strDetailHtml += "<td  align=\"left\" ><span >"+complainantVec.elementAt(4).toString().trim()+" "+complainantVec.elementAt(5).toString().trim()+"</span></td>";
	        strDetailHtml += "</tr>";
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\"class=\"bold\" > Address:</td>";
	        strDetailHtml += "<td  align=\"left\" ><span >"+complainantVec.elementAt(9).toString().trim()+"</span></td>";
	        strDetailHtml += "</tr>";
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\"class=\"bold\" > City:</td>";
	        strDetailHtml += "<td  align=\"left\" ><span >"+infoVec.elementAt(2).toString().trim()+"</span></td>";
	        strDetailHtml += "</tr>";
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\"class=\"bold\" > State:</td>";
	        strDetailHtml += "<td  align=\"left\" ><span >"+infoVec.elementAt(3).toString().trim()+"</span></td>";
	        strDetailHtml += "</tr>";
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\"class=\"bold\" > Email Id:</td>";
	        strDetailHtml += "<td  align=\"left\" ><span >"+complainantVec.elementAt(8).toString().trim()+" </span></td>";
	        strDetailHtml += "</tr>";
	        String temMno1="Not mention";
			if(complainantVec.elementAt(10).toString().trim().length()!=0){
				//System.out.println("insite"); 
				String temPno=complainantVec.elementAt(10).toString().trim().trim();
				temMno1 = temPno.replace("~", "-");
				
			}
	        strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td  align=\"right\"class=\"bold\" > Mobile No.: </td>";
	        strDetailHtml += "<td  align=\"left\" ><span >"+temMno1+" </span></td>";
	        strDetailHtml += "</tr>";
	       
	        
		    
	        
	       
	       
	        
		  strDetailHtml+="</table>";
		  strDetailHtml+="</div></td>";
		  // End td for additional detail
		  strDetailHtml+="<td width=\"1%\" width=\"100%\"></td>";
		  strDetailHtml+="</tr>";
		  
		  
		  	strDetailHtml += "<tr align=\"left\">";
	        strDetailHtml += "<td colspan=\"3\" ><hr color=\"#ff722b\"></td>";
	        strDetailHtml += "</tr>";
	        
	        
	        ////////////for new detail new tr start from here////////////////////////
	        strDetailHtml +="<tr width=\"100%\">";
	        strDetailHtml +="<td width=\"100%\" colspan=\"3\">";
	        strDetailHtml +="<table width=\"100%\">";
	         
	        
	        if(pageid.equals("1") || pageid.equals("7") )
	        { 
	        	
        	strDetailHtml += " <tr align=\"left\" width=\"100%\">";
            strDetailHtml += " <td height=\"20\" colspan=\"5\" align=\"center\" class=\"bold\">";
        	strDetailHtml += " <table width=\"100%\"   border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
        	strDetailHtml += " <tr class=\"bold\">";
        	strDetailHtml += " <td width=\"50%\" align=\"center\" id=\""+z+"\" onclick=\"showAddNew("+z+")\" style=\"cursor:hand\"><img src=\"../images/comunicatewithconsumer.gif\" width=\"179\" height=\"26\" border=\"0\" alt=\"Click here to send response to student\"></img></td>";
        	strDetailHtml += " <td align=\"right\" colspan=\"2\"  width=\"39%\" onclick=\"showAddNew1()\" style=\"cursor:hand\"> <img src=\"../images/viewallresponses.gif\" width=\"131\" height=\"27\" border=\"0\" alt=\"Click here to view all responses\"></img></td>";
        	strDetailHtml += " <td width=\"11%\" align=\"left\" valign=\"middle\" class=\"bold\">-&nbsp;"+infoVec.elementAt(5).toString()+"</td>";
        	strDetailHtml += " </tr>";
        	strDetailHtml += " </table>";
        	strDetailHtml += "</td>";
            strDetailHtml += " </tr>";
            
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
					strDetailHtml+="<td height=\"20\"  colspan=\"2\">Please give reposnse text with in 1500 letters.</td>";
				}
				
			}
			strDetailHtml+="<tr id=\"status1"+z+"\" style=\"display:none\" align=\"center\" >";
			strDetailHtml+="<td height=\"20\" colspan=\"2\"></td>";
			strDetailHtml+="</tr>";
			strDetailHtml+="<tr id=\"rowid"+z+"\" align=\"center\" style=\"display:none\">";
			strDetailHtml+="<td valign=\"top\" colspan=\"2\"><form id=\""+z+"\" name=\"frmreply\" method=\"post\" action=\"responseSuccess.do\" >";
			strDetailHtml+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
			strDetailHtml+=" <tr>";
			strDetailHtml+=" <td align=\"center\" colspan=\"2\"><textarea id=\"responsetext"+z+"\" name=\"responsetext\" cols=\"95\" rows=\"5\"></textarea></td>";
			strDetailHtml+=" </tr>";
			strDetailHtml+=" <tr>";
			strDetailHtml+=" <td colspan=\"2\" align=\"left\"  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			
			strDetailHtml+="  </tr>";
			strDetailHtml+="<input type=\"hidden\" name=\"respType\" value=\"Consumer\">";
			strDetailHtml+="<input type=\"hidden\" name=\"respid\" value=\"0\">";
			strDetailHtml+="<input type=\"hidden\" name=\"compId\" value=\""+numCopmId+"\">";
			 
			strDetailHtml+="<tr>";
			strDetailHtml+="<td align=\"center\" colspan=\"2\"><input name=\"Submit\" type=\"submit\" value=\"Submit\" >&nbsp;&nbsp;&nbsp;";
			strDetailHtml+="<input name=\"cancel\" type=\"button\" id=\""+z+"\" value=\"Cancel\" onClick=\"hideAddNew(this.id)\"></td>";
			
			strDetailHtml+="</tr>";
			strDetailHtml+=" </table>";
			strDetailHtml+="</form>";
			strDetailHtml+=" </td>";
			strDetailHtml+="</tr>";
			
			
				strDetailHtml+="<tr id=\"response\" style=\"display:none\" align=\"center\" >";
				strDetailHtml+="<td colspan=\"5\"><a name=\"response\">";
				strDetailHtml+="<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
				if(responseVec.size()>0)
				{
					String bgrow="",bgtab="";
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
						String cityName = rootMaster.getPlaceName(getDataSource(request, "entp"),cityId);
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
						if(comp_type.equalsIgnoreCase("Advertiser"))
						{
							bgrow="#CCECFF";
							bgtab="#F0FAF9";
							comptype = "Brand";
							
						}
						else if(comp_type.equalsIgnoreCase("Enterprise"))
						{
							bgrow="#FFEBFF";
							bgtab="#FFF7FF";
							comptype = "Core";
						}
						else if(comp_type.equalsIgnoreCase("Consumer"))
						{
							bgrow="#F9E1CC";
						    bgtab="#FFFAF6";
						    comptype = "Consumer";
						}
						
						
						
						strDetailHtml += " <tr align=\"left\" >";
						strDetailHtml += " <td bgcolor=\""+bgtab+"\" colspan=\"2\" valign=\"top\" ><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"2\">";
						strDetailHtml += " <tr bgcolor=\""+bgrow+"\" height=\"20\" >";
						strDetailHtml += " <td  width=\"40%\" bgcolor=\""+bgrow+"\" class=\"bold\">&nbsp; Response by "+comptype+":"; 
						strDetailHtml += "  <span >";
						if(comptype.equalsIgnoreCase("Brand")){
							strDetailHtml += companyName;
						}else{
							strDetailHtml +=fname+" "+lname;
				        }
						strDetailHtml += " </span></td>";
						strDetailHtml += " <td width=\"30%\"  align=\"left\"  class=\"bold\">&nbsp;Date:<span >"+respDate+"</span> </td>";
						strDetailHtml += " <td width=\"30%\" align=\"left\"  class=\"bold\">&nbsp;City:<span >"+cityName+"</span> </td>";
						strDetailHtml += " </tr>";
						strDetailHtml += " </tr>";
						strDetailHtml += " <tr>";
						strDetailHtml += " <td colspan=\"2\" ><table width=\"100%\" >";
						strDetailHtml += " <td width=\"20%\"  align=\"left\" valign=\"top\" class=\"bold\">&nbsp;Response Text: </td>";
						strDetailHtml += " <td height=\"31\"  align=\"left\" valign=\"top\" >"+strFMsg3+"</td>";
						strDetailHtml += " </tr>";
						strDetailHtml += "</table> </td>";
						
						
						
						
						
						
						
						
		                if(!(comptype.equalsIgnoreCase("Core"))){
		                	strDetailHtml += " <td width=\"17%\" align=\"center\" valign=\"top\"  id=\""+z+"\" onclick=\"showAddNew("+z+")\">";
		                	strDetailHtml += " <img src=\"../images/reply.gif\" alt=\"Click here to give reply\" width=\"68\" height=\"25\" border=\"0\"></img>";
		                 }
		                else
		                {
		                	strDetailHtml += " <td width=\"17%\" align=\"center\" valign=\"top\" >";
		                }
		                strDetailHtml += "</td>";
		                strDetailHtml += "</tr>";
		                strDetailHtml+="<tr id=\"status"+z+"\" style=\"display:inline\" align=\"center\" >";
		        		if(respid == responseId)
		        		{
		        			if(strDetailHtml.equalsIgnoreCase("failure"))
		        			{
		        				strDetailHtml+="<td height=\"20\"  colspan=\"2\">Response failure</td>";
		        			}
		        			else if(strResult.equalsIgnoreCase("success"))
		        			{
		        				strDetailHtml+="<td  height=\"20\" colspan=\"2\" >Response success</td>";
		        			}
		        			if(result.equalsIgnoreCase("failure"))
		        			{
		        				strDetailHtml+="<td height=\"20\"  colspan=\"2\">Please give reposnse text with in 1500 letters.</td>";
		        			}
		        			
		        			
		        		}
		        		strDetailHtml+="</tr>";
		        		strDetailHtml+="<tr id=\"status1"+z+"\" style=\"display:none\" align=\"center\" >";
		        		strDetailHtml+="<td height=\"20\"  colspan=\"2\"></td>";
		        		strDetailHtml+="</tr>";
		        		strDetailHtml+="<tr id=\"rowid"+z+"\" align=\"center\" style=\"display:none\">";
		        		strDetailHtml+="<td valign=\"top\" colspan=\"2\"><form id=\""+z+"\" name=\"frmreply\" method=\"post\" action=\"responseSuccess.do\" >";
		        		strDetailHtml+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
		        		strDetailHtml+="<tr>";
		        		strDetailHtml+="<td colspan=\"2\" align=\"center\"><textarea id=\"responsetext"+z+"\" name=\"responsetext\" cols=\"95\" rows=\"5\" ></textarea></td>";
		        		strDetailHtml+="</tr>";
		                strDetailHtml+="<tr>";
		        		strDetailHtml+="<td colspan=\"2\" align=\"center\"><input name=\"Submit\" type=\"submit\" value=\"Submit\" >&nbsp;&nbsp;&nbsp;";
		        		strDetailHtml+="<input name=\"cancel\" type=\"button\" id=\""+z+"\" value=\"Cancel\" onClick=\"hideAddNew(this.id)\"></td>";
		        		strDetailHtml+="<input type=\"hidden\" name=\"respid\" value=\""+responseId+"\">";
		        		strDetailHtml+="<input type=\"hidden\" name=\"compId\" value=\""+numCopmId+"\">";
		        		strDetailHtml+="<input type=\"hidden\" name=\"flag\" value=\"2\">";
		        		strDetailHtml+="</tr>";
		        		strDetailHtml+="</table>";
		        		strDetailHtml+="</form>";
		        		strDetailHtml+="  </td>";
						strDetailHtml+="</tr>";
						strDetailHtml+="</table>";
						strDetailHtml+="  </td>";
						strDetailHtml+="</tr>";
					}
					
				}
				else
				{
					strDetailHtml+="<tr align=\"center\"  id=\"noresponse\" style=\"display:inline\" >";
					strDetailHtml+="<td colspan=\"5\" bgcolor=\"#CCFFFF\" class=\"bold\"> No Response</td>";
					strDetailHtml+="</tr>";
				}
				strDetailHtml+="</table></a>";
		        strDetailHtml+="</td>";
				strDetailHtml+="</tr>";
				
				 strDetailHtml += "<tr align=\"left\">";
			        strDetailHtml += "<td colspan=\"5\" ><hr color=\"#ff722b\" width =\"100%\"></td>";
			        strDetailHtml += "</tr>";
	        }
	        
							
	       
	        
	        strDetailHtml +="</table>";
	        strDetailHtml +="</td>";
	        strDetailHtml +="</tr>";
	        ////////////////end/////////////////
	    	
	        
	        strDetailHtml +="<tr height=\"20\" >";
	        strDetailHtml +="<td  colspan=\"3\"  align=\"center\" >";
	        if(pageid.equals("1") || pageid.equals("7") )
	        { strDetailHtml+="<input type=\"hidden\" name=\"Qtype\" id=\"Qtype\" value=\""+infoVec.elementAt(9)+"\">";
	        	strDetailHtml +="<input name=\"reject\" type=\"button\"   value=\"Reject\" onclick=\"reject()\"/>&nbsp;&nbsp;&nbsp;&nbsp;";
		        if(!complaintVec.elementAt(27).toString().trim().equals(""))
		        {
		        strDetailHtml +="<input name=\"create\" type=\"button\"   value=\"Create Brand\" onclick=\"createBrand()\"/>&nbsp;&nbsp;&nbsp;&nbsp;";
		        }
		        else
		        {
		        strDetailHtml +="<input name=\"modify\" type=\"button\"   value=\"Accept\" onclick=\"submit()\"/>&nbsp;&nbsp;&nbsp;&nbsp;";
		        }
	        }
	       
	        strDetailHtml +="<input name=\"Back\" type=\"button\"   value=\"Back\" onclick=\"back()\"/>";
	        strDetailHtml +="</td>";       
	        strDetailHtml +="</tr>";
	        
	        
	        
	        
	        
	        
		  strDetailHtml+="</table>";
		
		
		  if(remander==0)
			{
	        	strDetailHtml += "<tr style=\"display:none\" align=\"center\">";
	        	strDetailHtml += "<td height=\"50\" colspan=\"5\"></td>";
	        	strDetailHtml += "</tr>";
			}
		  
		return strDetailHtml;
		
	}
		
		
		
		
	private String getComplaintUnnamed(HttpServletRequest request, int numCopmId, int numbrandId, int numCatId)
	{
		////System.out.println("numCopmId "+numCopmId);
		////System.out.println("numbrandId "+numbrandId);
		////System.out.println("numCatId "+numCatId);
		IndvMaster im = new IndvMaster();
		AdvtMaster am = new AdvtMaster();
		Vector unnamedVec = am.getComplaintUnnamedMap(getDataSource(request, "entp"), numbrandId, numCatId);
		////System.out.println("unnamedVec Size "+unnamedVec.size());
		////System.out.println("unnamedVec "+unnamedVec);
		Vector dataVec = im.getComplaintUnnamedData(getDataSource(request, "entp"), numCopmId);
		//////System.out.println("Data Vec in Details Arun Size "+dataVec.size());
		//////System.out.println("Data Vec in Details Arun "+dataVec);
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
								strSubCatVal1 = im.getValueSubCat1(getDataSource(request, "entp"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==1)
							{
								strSubCatVal1 = im.getValueSubCat2(getDataSource(request, "entp"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==2)
							{
								strSubCatVal1 = im.getValueSubCat3(getDataSource(request, "entp"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==3)
							{
								strSubCatVal1 = im.getValueSubCat4(getDataSource(request, "entp"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==4)
							{
								strSubCatVal1 = im.getValueSubCat5(getDataSource(request, "entp"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==5)
							{
								strSubCatVal1 = im.getValueSubCat6(getDataSource(request, "entp"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==6)
							{
								strSubCatVal1 = im.getValueSubCat7(getDataSource(request, "entp"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==7)
							{
								strSubCatVal1 = im.getValueSubCat8(getDataSource(request, "entp"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==8)
							{
								strSubCatVal1 = im.getValueSubCat9(getDataSource(request, "entp"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==9)
							{
								strSubCatVal1 = im.getValueSubCat10(getDataSource(request, "entp"), Integer.parseInt(strSubCat1.trim()));
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
					//String strValue = unnamedVec.elementAt(i+2).toString().trim();
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
					//////System.out.println("str Flag1 "+strFlag1);
					if((strFlag1.indexOf("true") <= -1) && (!strFName.equalsIgnoreCase(""))&&(!strSubCatVal1.equalsIgnoreCase("")))
					{
						FinalDataVec.add(strFName+"~"+strSubCatVal1);
					}
					numCount2=numCount2+5;
					numCount3=numCount3+5;
					numCount4=numCount4+5;
				}
				//////System.out.println("FinalDataVec "+ FinalDataVec);
			}
			int numCount1=10;
			for(int i=50; i<unnamedVec.size(); i=i+5)
			{
				String strFName=unnamedVec.elementAt(i).toString().trim();
				//String strType = unnamedVec.elementAt(i+1).toString().trim();
				//String strValue = unnamedVec.elementAt(i+2).toString().trim();
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
				//////System.out.println("FinalDataVec "+i+" "+FinalDataVec);
			}
			//////System.out.println("Final Data Vec Size "+FinalDataVec.size());
			//////System.out.println("Final Data Vec "+FinalDataVec);
			for(int j=0; j<FinalDataVec.size(); j=j+2)
			{
				String strFVal = "", strVal="";
				String strFVal1 = "", strVal1="";
				String strValue = FinalDataVec.elementAt(j).toString().trim();
				String arrValue[] = strValue.split("~");
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
				//////System.out.println("Arr Value >> "+arrValue.length);
				String strValue1 = " ~ ";
				if(j+1 < FinalDataVec.size())
				{
					strValue1 = FinalDataVec.elementAt(j+1).toString().trim();
				}
				String arrValue1[] = strValue1.split("~");
				//////System.out.println("Arr Value 1>> "+arrValue1.length);
				if(arrValue1.length==1)
				{
					strFVal1 = arrValue1[0];
					strVal1="";
				}
				else
				{
					strFVal1 = arrValue1[0];
					strVal1= arrValue1[1];
				}
				
				strResult += " <tr >";
				strResult += "<td  height=\"25\" align=\"left\" colspan=\"2\" class=\"bold\">"+strFVal+"&nbsp;<span >"+strVal+"</span></td>";
				strResult += "<td  height=\"25\" align=\"left\" colspan=\"2\" class=\"bold\">"+strFVal1+"&nbsp;<span >"+strVal1+"</span></td>";
				strResult += " </tr>";
			}
		}
		
		return strResult;
	}
	

}
