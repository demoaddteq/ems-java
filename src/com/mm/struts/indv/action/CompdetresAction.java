package com.mm.struts.indv.action;

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

public class CompdetresAction extends Action {
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
		
		String userid= (request.getParameter("uId")!=null) ? request.getParameter("uId").trim() : "0";
				
		String strPgType = (request.getParameter("pgtyp")!=null) ? request.getParameter("pgtyp").trim() : "Details";
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "creation_date";
				
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 10;
		IndvMaster indvMaster = new IndvMaster();
		RootMaster rootMaster = new RootMaster();
		
		String strComplaintHtml = "";
		
				
		if(strPgType.equalsIgnoreCase("Details"))
		{
			String numCopmId = (request.getParameter("numCid")!=null) ? request.getParameter("numCid").trim() : "0";
			//System.out.println("numCopmId>>>>>>>>"+numCopmId);
			
			
			String strResult = (request.getParameter("strResult")!=null) ? request.getParameter("strResult").trim() : "";
			
			//System.out.println("strResult>>>>>>>>>>"+strResult);
			if(!numCopmId.equalsIgnoreCase("0"))
			
	    	{
	    		Vector<String> paramVec1 = new Vector<String>();
	    		paramVec1.add(numCopmId);
	    		paramVec1.add(userid);
	    		//-------------------------
		    	//int numArray[] = md.getCompanyMoves(numCopmId, getDataSource(request, "main"), minVal, maxVal, strFShortBy);
		    	
	    		//-------------------------
		    	int totalRow = indvMaster.getComplaintCount(getDataSource(request, "indv"),paramVec1);
				//System.out.println("totalRow Pages "+totalRow);
		    	String strPageHtml = getPages(minVal, maxVal, totalRow, strPgType);
				//System.out.println("Pages "+strValue);
		    	Vector<String> paramVec2 = new Vector<String>();
		    	paramVec2.add(numCopmId);
		    	paramVec2.add(userid);
		    	
		    	
		    	Vector complaintVec = indvMaster.getComplaintDetails(getDataSource(request, "indv"),paramVec2);
		    	//System.out.println("complaintVec>>>>>>>>"+complaintVec);
		    	
		    	/*
		    	 * 
				    support.setFieldVec("int", "complaint_id");			//0
					support.setFieldVec("string", "complaint_title");	//1
					support.setFieldVec("string", "complaint_text");	//2
					support.setFieldVec("string", "relevent_info");		//3		
					support.setFieldVec("string", "category");			//4
					support.setFieldVec("string", "creation_date");		//5
					support.setFieldVec("string", "publish_date");		//6
					support.setFieldVec("string", "creation_time");		//7
					support.setFieldVec("string", "publish_time");		//8
					support.setFieldVec("string", "lastmodify_date");	//9
					support.setFieldVec("string", "lastmodify_time");	//10
					support.setFieldVec("int", "login_id");				//11
					support.setFieldVec("int", "companyid");			//12
					support.setFieldVec("string", "timepart");			//13
					support.setFieldVec("int", "viewcount");			//14
					support.setFieldVec("int", "entp_id");				//15
					support.setFieldVec("int", "cu_id");				//16		
					support.setFieldVec("String", "fcom_id");			//17
					support.setFieldVec("int", "advt_id");				//18
					support.setFieldVec("int", "advtL_id");				//19
					support.setFieldVec("int", "dealer_id");			//20
					support.setFieldVec("int", "tag_id");				//21
					support.setFieldVec("String", "resolve_date");		//22
					support.setFieldVec("String", "closed_date");		//23
					support.setFieldVec("int", "brandFlag");			//24
					support.setFieldVec("int", "publish_flag");			//25
					support.setFieldVec("string", "publish_on");		//26
					support.setFieldVec("string", "other");				//27														
		    	 */
		    	
		    	int advtid = Integer.parseInt(complaintVec.elementAt(18).toString());
		    	String comp_name ="";
		    	if(advtid<=0)
		    	{
		    		comp_name = complaintVec.elementAt(27).toString()+"(Other)";
		    	}
		    	else
		    	{
		    		comp_name = rootMaster.getBrandName(getDataSource(request, "indv"), advtid);
		    	}
			
				
				int dealerid = Integer.parseInt(complaintVec.elementAt(20).toString());
				
				Vector<String> dealerVec1= new Vector<String>(); 
				if(dealerid!=0)
				{	//System.out.println("dealerid>>>"+dealerid);
					dealerVec1 = indvMaster.getDealerDetail(getDataSource(request, "indv"),dealerid);
					int contryId = Integer.parseInt(dealerVec1.elementAt(5).toString());//dealer contryId id
					String dealercontry = rootMaster.getCountryName(getDataSource(request, "indv"),contryId);
					int stateId = Integer.parseInt(dealerVec1.elementAt(6).toString());//dealer stateId id
					String dealerstate = rootMaster.getStateName(getDataSource(request, "indv"),stateId);
					int cityId = Integer.parseInt(dealerVec1.elementAt(8).toString());//dealer cityId id
					String dealercity = rootMaster.getPlaceName(getDataSource(request, "indv"),cityId);
					//System.out.println("dealerVec1>>befor-adding>"+dealerVec1);
					dealerVec1.add(dealercontry); 
					dealerVec1.add(dealerstate); 
					dealerVec1.add(dealercity); 
					
					}
				String catName="";
				int catid=Integer.parseInt(complaintVec.elementAt(4).toString());
				Vector catNamevec = rootMaster.getCategoryName(getDataSource(request, "indv"),catid);
				if(Integer.parseInt(catNamevec.elementAt(1).toString().trim())==0){
					catName=catNamevec.elementAt(0).toString().trim()+"(Other)";
				}else
				{
					catName=catNamevec.elementAt(0).toString().trim();
				}
				int tagid = Integer.parseInt(complaintVec.elementAt(21).toString());
				String tagName = rootMaster.getStrTagName(getDataSource(request, "indv"),tagid);
				Vector <String> infoVec = new Vector<String>();
				infoVec.add(tagName);
				infoVec.add(catName);
				
				//get no of responses for this page by using getCompanyType() method of rootmaster
				//get all company list with the no of responses accorrding to company type. rootmaster.getResponseList()
				Vector<String> respDataVec = new Vector<String>();
				respDataVec.add(complaintVec.elementAt(0).toString().trim());
				respDataVec.add("Consumer");
				Vector responseVec = rootMaster.getResponseList(getDataSource(request, "indv"), respDataVec);
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
				}
				infoVec.add(String.valueOf(totleRsponse));	//totle no of responses
				infoVec.add(String.valueOf(advtResponse));	//Brand Response		
				infoVec.add(String.valueOf(entpResponse));	//Core Response	
				infoVec.add(String.valueOf(indvResponse));	//Consumer response
				//end of response counting
				//System.out.println("responseVec in action>>>>>>>>>"+responseVec);
				//get comment vetor
				Vector commentVec = rootMaster.getCommentsList(getDataSource(request, "indv"), Integer.parseInt(complaintVec.elementAt(0).toString().trim()));
				String strUnnamed = getComplaintUnnamed(request, Integer.parseInt(numCopmId), Integer.parseInt(complaintVec.elementAt(18).toString().trim()), Integer.parseInt(complaintVec.elementAt(4).toString().trim()));
				//System.out.println("dealerVec1>>Beforcalling founction>"+dealerVec1);
				
				strComplaintHtml = getComplaintDetail(strUnnamed, numCopmId,strResult, strPageHtml,  complaintVec, comp_name, dealerVec1, infoVec,strShortBy, responseVec,commentVec,request, remander);
		    	
		    	
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
		
		// TODO Auto-generated method stub
		return null;
	}
	
	 
	private String getComplaintDetail(String strUnnamed, String numCopmId,String strResult,String strPageHtml,Vector complaintVec,String comp_name, Vector dealerVec1, Vector infoVec, String strShortBy, Vector responseVec,Vector commentVec, HttpServletRequest request, int remander){
		String strValue ="";
		
		int z=0;
		RootMaster rootMaster = new RootMaster();  
		
		//StringBuffer url = request.getRequestURL();
		String url = "compdetres.jsp?numCid="+numCopmId;
		if(strResult.length()>0)
		{
			url="compdetres.jsp";
		}
		int respid=(request.getParameter("respid")!=null) ? Integer.parseInt(request.getParameter("respid")) : 0;
		//System.out.println("respid>>>>>>>>"+respid);
		
		
		
		
		
		if(complaintVec.size()==0)
		{
					strValue += "<tr align=\"center\">";
					strValue += "<td height=\"20\" valign=\"center\" colspan=\"2\" class=\"Arial_Narrow_16_orange_normal\"> No Data Found</td>";
					strValue += "</tr>";
					
		}	
		
		    strValue+="<table   width=\"100%\" >";
		    strValue+="<tr width=\"100%\">";
			strValue+="<td height=\"30\"  valign=\"top\" width=\"10%\" align=\"left\" class=\"var_12_bold_orange\">&nbsp;&nbsp;&nbsp;Remarks:</td>";
			strValue+="<td   width=\"90%\"class=\"Arial_12_black_normal\" align=\"left\"  valign=\"top\"  ><div align=\"justify\">Your complaints against partner company is under process and as per the agreement, it will be attended and attempted to resolve <strong>"+complaintVec.elementAt(26).toString().trim()+"</strong>. During this time, your complaint is not published for public view. However, at expiry of committed time frame, with reply or without reply from brand, same complaint will be available for public view on our website.<br></div></td>";
			strValue+="</tr>";
			 
	        strValue += "<tr align=\"left\">";
	        strValue += "<td colspan=\"2\" class=\"formHeadingsBold\"><hr color=\"#ff722b\"></td>";
	        strValue += "</tr>";
		    strValue+="</table>";
		
		  strValue+="<table  height = \"350\" width=\"100%\" valign=\"top\" >";
		  strValue+="<tr>";
		  //////////////////////////// start td for complaint detail
			strValue+="<td width=\"57%\" valign=\"top\"><div style =\" height:350px; overflow-y:scroll; \">";
			strValue+="<table width=\"100%\"   border=\"0\" >";
		  	strValue += "<tr class=\"ms_sans_serif\" >";
	        strValue += "<td >";
	       
	        
	        strValue += "<tr align=\"left\">";
	        strValue += "<td width=\"35%\" align=\"left\"class=\"var_12_bold_orange\" > &nbsp;&nbsp;&nbsp;Complaint Title:</td>";
	        strValue += "<td  width=\"65%\" align=\"left\" ></td>";
	        strValue += "</tr>";
	        strValue += "<tr >";
	        strValue += "<td colspan=\"2\" class=\"Arial_12_black_normal\" align=\"left\"  width=\"100%\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;"+complaintVec.elementAt(1).toString().trim()+" </td>";
	       
	        strValue += "</tr>";
	       
	        strValue += "<tr align=\"left\">";
	        strValue += "<td colspan=\"2\" class=\"formHeadingsBold\"><hr color=\"#ff722b\"></td>";
	        strValue += "</tr>";
	        
	        
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
		  
			
			
				strValue += "<tr align=\"left\">";
		        strValue += "<td  width=\"35%\" align=\"left\" class=\"var_12_bold_orange\" > &nbsp;&nbsp;&nbsp;Complaint Text:</td>";
		        strValue += "<td  align=\"left\" ><span class=\"Arial_12_black_normal\"></span></td>";
		        strValue += "</tr>";
		        strValue += "<tr align=\"left\">";
		        strValue += "<td  colspan=\"2\" ><table width=\"100%\" align=\"center\"><tr><td width=\"5%\"></td><td align=\"left\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+strFMsgcompl+"</td><td width=\"5%\"></td></tr></table></td>";
		        strValue += "</tr>";
		        
		        strValue += "<tr align=\"left\">";
		        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \"><hr color=\"#ff722b\"></td>";
		        strValue += "</tr>";
		        
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
					
					
					
					strValue += "<tr align=\"left\">";
					strValue += "<td  align=\"left\" class=\"var_12_bold_orange\" > &nbsp;&nbsp;&nbsp;Relevent Text:</td>";
			        strValue += "<td  align=\"left\" ><span class=\"Arial_12_black_normal\"></span></td>";
			        strValue += "</tr>";	
			        
			        strValue += "<tr align=\"left\">";
			        strValue += "<td width=\"80%\" colspan=\"2\" class=\"Arial_12_black_normal\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+strFMsgcomp3+"</td>";
			        strValue += "</tr>";		
			        
				
				}
		             
		  ///////////////////////////////////////////////////////////////////////////////////////////
			  strValue+="</table>";
			  strValue+="</div></td>";
			  // End td for complaint detail
			  strValue+="<td width=\"2%\"></td>";
		
		  	//System.out.println("INSIDE RIGHT TD");
		  	strValue+="<td width=\"39%\" valign=\"top\"><div style =\" height:350px; overflow-y:scroll; \">";
		    strValue+="<table width=\"100%\" valign=\"top\"  border=\"0\" >";
		  	strValue += "<tr align=\"left\" valign=\"top\" >";
	        strValue += "<td  align=\"left\" valign=\"top\"  colspan=\"2\" class=\"var_12_bold_orange\">Complaint Information</td>";
	        strValue += "</tr>";
	        
	        strValue += "<tr align=\"left\" valign=\"top\">";
	        strValue += "<td  align=\"right\"class=\"Arial_Narrow_12_black_bold\" >Complaint ID:  </td>";
	        strValue += "<td  align=\"left\" ><span class=\"Arial_12_black_normal\">"+complaintVec.elementAt(17).toString().trim()+" </span></td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\"class=\"Arial_Narrow_12_black_bold\" > Date: </td>";
	        strValue += "<td  align=\"left\" ><span class=\"Arial_12_black_normal\">"+complaintVec.elementAt(5).toString().trim()+"</span></td>";
	        strValue += "</tr>";
	        
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\"class=\"Arial_Narrow_12_black_bold\" > Category: </td>";
	        strValue += "<td  align=\"left\" ><span class=\"Arial_12_black_normal\">"+infoVec.elementAt(1).toString().trim()+" </span></td>";
	        strValue += "</tr>";
	        
	        strValue += "<tr align=\"left\">";
	        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \"><hr color=\"#ff722b\"></td>";
	        strValue += "</tr>";
	        
	        
	        strValue += "<tr align=\"left\" >";
	        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \" align=\"left\" >";
	        strValue += "<table align=\"left\">";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td valign=\"top\"width=\"57%\"  align=\"left\">";
	        strValue += "<table width=\"100%\" align=\"left\">";
	        strValue += "<tr >";
	        strValue += "<td  align=\"left\" width=\"35%\" class=\"Arial_Narrow_12_black_bold\"  > Status: </td>";
	        strValue += " <td align=\"left\" ><span class=\"Arial_12_black_normal\">"+infoVec.elementAt(0).toString().trim()+"</span></td>";
	        strValue += "</tr>";
	       
	        strValue += "</table>";
	        strValue += "</td>";
	      
	        strValue += "<td  valign=\"top\">";
	        strValue += "<table width=\"100%\">";
	        strValue += "<tr >";
	        strValue += "<td  align=\"left\" class=\"Arial_Narrow_12_black_bold\" > Publish: </td>";
	                
		    if((complaintVec.elementAt(26).toString()).equalsIgnoreCase("1@Week"))
			{
		    strValue += " <td  align=\"left\"><span class=\"Arial_12_black_normal\">1 Week</span></td>";
			}
		    else if((complaintVec.elementAt(26).toString()).equalsIgnoreCase("2@Week"))
			{
		    strValue += " <td   align=\"left\"><span class=\"Arial_12_black_normal\">2 Week</span></td>";
			}
		    else if((complaintVec.elementAt(26).toString()).equalsIgnoreCase("4@Week"))
			{
		    strValue += " <td   align=\"left\"><span class=\"Arial_12_black_normal\">4 Week</span></td>";
			}
		    else if((complaintVec.elementAt(26).toString()).equalsIgnoreCase("6@Week"))
			{
		    strValue += " <td   align=\"left\"><span class=\"Arial_12_black_normal\">6 Week</span></td>";
			}
		    else{
		    strValue += " <td   align=\"left\"><span class=\"Arial_12_black_normal\">Immediate</span></td>";	   
		    }
	        
	        
	     
	        strValue += "</tr>";
	        strValue += "</table>";
	        strValue += "</td>";
	        
	        strValue += "</tr>";
	        strValue += "</table>";
	        strValue += "</td>";
	        strValue += "</tr>";
	        
	        
	        
	        
	       
	        strValue += "<tr align=\"left\">";
	        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \"><hr color=\"#ff722b\"></td>";
	        strValue += "</tr>";
	        
	        
	        //System.out.println("InfoVector`````in additional``detail````11111111111"+complaintVec);
		  
	        strValue += " <tr align=\"left\">";
	        strValue += "<td colspan=\"2\" class=\"var_12_bold_orange\">Brand Information</td>";
	        strValue += " </tr>";
	        
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\"class=\"Arial_Narrow_12_black_bold\" > Brand Name: </td>";
	        strValue += "<td  align=\"left\" ><span class=\"Arial_12_black_normal\">"+comp_name+" </span></td>";
	        strValue += "</tr>";
	        
	        
	        
	        
	        
	       
	        
	       strValue += strUnnamed;
	     
	        strValue += " <tr align=\"left\">";
	        strValue += " <td colspan=\"2\" class=\"formHeadingsBold style5\"><hr color=\"#ff722b\"></td>";
	        strValue += " </tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += " <td colspan=\"2\" class=\"var_12_bold_orange\">Dealer Information</td>";
	        strValue += "</tr>";
	        if(dealerVec1.size()!=0)
			{
	        	//System.out.println("DEALERVECTOR-size----------->>>>>>>>>>>>>>>>>>"+dealerVec1.size());
	        	//System.out.println("DEALERVECTOR-size----------->>>>>>>>>>>>>>>>>>"+dealerVec1);
	        strValue += "<tr align=\"left\">";	
	        strValue += "<td colspan=\"2\">";
	        strValue += "<table align=\"center\" width=\"90%\">";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"Arial_Narrow_12_black_bold\" width=\"50%\"> Dealer Name: </td>";
	        strValue += "<td  align=\"left\" class=\"Arial_12_black_normal\"  width=\"50%\">"+dealerVec1.elementAt(1).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"Arial_Narrow_12_black_bold\" > Dealer Address: </td>";
	        strValue += "<td  align=\"left\" class=\"Arial_12_black_normal\">"+dealerVec1.elementAt(4).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"Arial_Narrow_12_black_bold\" > Dealer Country: </td>";
	        strValue += "<td  align=\"left\"  class=\"Arial_12_black_normal\">"+dealerVec1.elementAt(13).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"Arial_Narrow_12_black_bold\" > Dealer State: </td>";
	        strValue += "<td  align=\"left\"  class=\"Arial_12_black_normal\">"+dealerVec1.elementAt(14).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"Arial_Narrow_12_black_bold\" > Dealer City: </td>";
	        strValue += "<td  align=\"left\"  class=\"Arial_12_black_normal\">"+dealerVec1.elementAt(15).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"Arial_Narrow_12_black_bold\" > Dealer District: </td>";
	        strValue += "<td  align=\"left\"  class=\"Arial_12_black_normal\">"+dealerVec1.elementAt(15).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"Arial_Narrow_12_black_bold\" > Contact Person: </td>";
	        strValue += "<td  align=\"left\" class=\"Arial_12_black_normal\">"+dealerVec1.elementAt(2).toString().trim()+" </td>";
	        strValue += "</tr>";
	       // //System.out.println("DEALERVECTOR-size-------temMno---->>>>>>>>>>>>>>>>>>"+dealerVec1);
	        String temMno="N/A";
		    if((dealerVec1.elementAt(10).toString().trim().length()!=0)||(dealerVec1.elementAt(11).toString().trim().length()!=0)){
				System.out.println("insite");
				if(dealerVec1.elementAt(10).toString().trim().length()!=0){
				String temPno=dealerVec1.elementAt(10).toString().trim();
				
				temMno = temPno.replace("~", "-");
				}else
				{
					String temPno=dealerVec1.elementAt(11).toString().trim();
					
					temMno = temPno.replace("~", "-");
				}
			}
			
		  //System.out.println("Mob No./Ph No.: >> "+dealerVec1.elementAt(10).toString().trim().length());
		    
		    
		    strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"Arial_Narrow_12_black_bold\" > Mob No./Ph No.: </td>";
	        strValue += "<td  align=\"left\"  class=\"Arial_12_black_normal\">"+temMno+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"Arial_Narrow_12_black_bold\" > EMail Id: </td>";
	        strValue += "<td  align=\"left\"  class=\"Arial_12_black_normal\">"+dealerVec1.elementAt(9).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "</table>";
	        strValue += "</td>";
	        strValue += "</tr>";
		    
			}else{
		    strValue += " <tr align=\"left\">";
	        strValue += " <td height=\"35\" colspan=\"2\" align=\"center\" class=\"Arial_Narrow_12_black_bold\">Dealer Information is not provided.</td>";
	        strValue += " </tr>";
			}
	        
	       
	          	        
		  strValue+="</table>";
		  strValue+="</div></td>";
		  // End td for additional detail
		  	strValue+="<td width=\"1%\"></td>";
		    strValue+="</tr>";
		   
		    strValue+="</table>";
		    
		    strValue += " <tr align=\"left\">";
	        strValue += " <td colspan=\"3\" class=\"formHeadingsBold style5\"><hr color=\"#ff722b\"></td>";
	        strValue += " </tr>";
		   
	        

		    strValue+="<table   width=\"100%\" >";
		    
		    
	        strValue +="<tr height=\"20\">";
			strValue +="<td colspan=\"2\"  align=\"left\" class=\"var_12_bold_orange\" width=\"100%\" > Number Of Response By : </td>";
			strValue +="</tr>";
			strValue +="<tr align=\"left\" valign=\"top\">";
			strValue +="<td colspan=\"2\" class=\"Arial_Narrow_12_black_bold\"width=\"100%\" >";
			strValue +=" &nbsp;Consumer:&nbsp;<span class=\"Arial_Narrow_12_blue_bold\">"+infoVec.elementAt(5).toString().trim()+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			strValue +="Core:&nbsp;<span class=\"Arial_Narrow_12_blue_bold\">"+infoVec.elementAt(4).toString().trim()+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			strValue +="Brand:&nbsp;<span class=\"Arial_Narrow_12_blue_bold\">"+infoVec.elementAt(3).toString().trim()+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			strValue +="Total:&nbsp;<span class=\"Arial_Narrow_12_blue_bold\">"+infoVec.elementAt(2).toString().trim()+"</span>";
			strValue +="</td>";
			strValue +="</tr>";
	        
			strValue += "<tr align=\"left\" class=\"Arial_12_black_normal\">";
	        strValue += " <td colspan=\"2\" valign=\"top\" class=\"Arial_12_black_normal\"><HR color=\"#ff722b\"></td>";
	        strValue += "</tr>";
	        
		    int brandresponse = Integer.parseInt(infoVec.elementAt(3).toString().trim());
			int coreresponse = Integer.parseInt(infoVec.elementAt(4).toString().trim());
			if(brandresponse == 0 && coreresponse ==0)
			{
			strValue+="<tr>";
			strValue+="<td height=\"\" colspan=\"2\" align=\"left\" valign=\"top\">";
			strValue+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
			strValue+="<tr>";
			strValue+="<td width=\"3%\">&nbsp;</td>";
			strValue+="<td width=\"43%\"  align=\"left\" valign=\"top\" ><img src=\"../images/sendarequesttocore.gif\" width=\"155\"  border=\"0\" alt=\"Click here to send a request to core\" onClick=\"showAddNew("+z+")\" style=\"cursor:hand\"></img></td>";
			strValue+="<td width=\"20%\">&nbsp;</td>";
			strValue+="<td width=\"27%\"  align=\"right\" valign=\"top\" class=\"Arial_Narrow_14_orange_normal\"> <img src=\"../images/commentsoncomplaints.gif\" width=\"167\" height=\"35\" border=\"0\" alt=\"Click here to see all comments\" onclick=\"showAddNew1()\" style=\"cursor:hand\"></img></td>";
			strValue+="<td width=\"7%\" align=\"left\" valign=\"middle\" class=\"Arial_Narrow_14_orange_normal\">#&nbsp;"+commentVec.size()+"</td>";
			strValue+="</tr>";
			strValue+="</table>";
			strValue+="</td>";
			strValue+="</tr>";
			strValue+="<tr>";
			strValue+="<td  colspan=\"2\" align=\"center\" valign=\"top\" class=\"Arial_Narrow_16_orange_normal\">No Response";
			strValue+="</td>";
			strValue+="</tr>";
			}
			else
			{
				strValue+="<tr>";
				strValue+="<td  colspan=\"2\" align=\"left\" valign=\"top\">";
				strValue+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
				strValue+="<tr>";
				strValue+="<td width=\"3%\">&nbsp;</td>";
				strValue+="<td width=\"43%\"  align=\"left\" valign=\"top\"></td>";
				strValue+="<td width=\"20%\">&nbsp;</td>";
				strValue+="<td width=\"27%\"  align=\"right\" valign=\"top\" class=\"Arial_Narrow_14_orange_normal\"> <img src=\"../images/commentsoncomplaints.gif\" width=\"167\" height=\"35\" border=\"0\" alt=\"Click here to see all comments\" onclick=\"showAddNew1()\" style=\"cursor:hand\" ></img></td>";
				strValue+="<td width=\"7%\" align=\"left\" valign=\"middle\" class=\"Arial_Narrow_14_orange_normal\">#&nbsp;"+commentVec.size()+"</td>";
				strValue+="</tr>";
				strValue+="</table>";
				strValue+="</td>";
				strValue+="</tr>";
			}
			strValue+="<tr>";
			strValue+="<td height=\"\" colspan=\"2\" align=\"center\" valign=\"top\"></td>";
			strValue+="</tr>";
			strValue+="<tr id=\"status"+z+"\" style=\"display:inline\" align=\"center\" >";
			if(respid==0)
			{
				if(strResult.equalsIgnoreCase("failure"))
				{
					strValue+="<td height=\"20\" class=\"errormessages\" colspan=\"5\"></td>";
				}
				else if(strResult.equalsIgnoreCase("success"))
				{
					strValue+="<td  height=\"20\" colspan=\"2\" class=\"successmessages\"></td>";
				}
				
			}
			strValue+="</tr>";
			strValue+="<tr id=\"status1"+z+"\" style=\"display:none\" align=\"center\">";
			strValue+="<td height=\"20\"  colspan=\"2\"></td>";
			strValue+="</tr>";
			
			strValue+="<tr id=\"rowid"+z+"\" align=\"center\" style=\"display:none\">";
			strValue+="<td colspan=\"2\" align=\"left\" valign=\"top\"><a name=\""+z+"\">";
			strValue+="<form id=\""+z+"\" name=\"frmreply\" method=\"post\" action=\"responseSuccess.do\">";
			strValue+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
			strValue+="<tr>";
			strValue+="<td height=\"22\"><strong class=\"Arial_Narrow_12_black_bold\">&nbsp;&nbsp;&nbsp;&nbsp;Please Write a request to Core";
			strValue+=":</strong></td>";
			strValue+="</tr>";
			strValue+="<tr>";
			strValue+="<td align=\"center\"><textarea id=\"responsetext"+z+"\" name=\"responsetext\" cols=\"105\" rows=\"5\" class=\"Arial_12_black_normal\" ></textarea></td>";
			strValue+="</tr>";
			strValue+="<tr>";
			strValue+="<td align=\"center\"><input name=\"Submit\" type=\"submit\" class=\"buttonCopy\" value=\"Submit\">";
			strValue+="&nbsp;&nbsp;&nbsp;<input name=\"cancel\" id=\""+z+"\" type=\"button\" class=\"buttonCopy\" value=\"Cancel\" onClick=\"hideAddNew(this.id)\"></td>";
			strValue+="</tr>";
			strValue+="</table>";
			strValue+="<input type=\"hidden\" name=\"respid\" value=\"0\">";
			strValue+="<input type=\"hidden\" name=\"compId\" value=\""+numCopmId+"\">";
			
			
			strValue+="</form> </a></td>";
			strValue+="</tr>";
			
			
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
					String cityName = rootMaster.getPlaceName(getDataSource(request, "indv"),cityId);
					int resposne_flag = Integer.parseInt(tempVec.elementAt(10).toString().trim());
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
					strValue+="<tr>";
					strValue+="<td colspan=\"2\" width=\"100%\" bgcolor=\""+bgtab+"\">";
					strValue+="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
					strValue+="<tr bgcolor=\""+bgrow+"\">";
					strValue+="<td  height=\"22\" width=\"100%\" colspan=\"2\" align=\"left\" bgcolor=\""+bgrow+"\" class=\"Arial_Narrow_12_black_bold\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr>"; 
					strValue+="<td width=\"39%\" align=\"left\" >";
					
					
					
					if(comptype.equalsIgnoreCase("Consumer"))
					{
						strValue+="&nbsp;Reply By "+comptype+":";
					}
					else
					{
						strValue+="&nbsp;Response By "+comptype+":";
					}
					strValue+="<span class=\"Arial_12_black_normal\">";
					
					if(comptype.equalsIgnoreCase("Brand"))
					{
						strValue+=companyName;
					}
					else
					{
						strValue+=fname+" "+lname;
					}
					strValue+="</span></td>";
					strValue+="<td align=\"right\" width=\"15%\" class=\"Arial_Narrow_12_black_bold\">&nbsp;Date:</td>";
					strValue+="<td class=\"Arial_12_black_normal\" width=\"15%\" align=\"left\" >"+respDate+"</td>";
					strValue+="<td align=\"left\" width=\"30%\" class=\"Arial_Narrow_12_black_bold\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;City:<span class=\"Arial_12_black_normal\" width=\"15%\" align=\"left\">"+cityName+"</span></td>";
					
					strValue += " </tr></table></td></tr>";
					
					strValue += " <tr>";
					strValue += " <td colspan=\"2\"  align=\"left\" valign=\"top\" class=\"Arial_Narrow_12_black_bold\" width=\"100%\" height=\"45\"> <table height=\"100%\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td height=\"100%\" width=\"13%\"  valign=\"top\">&nbsp;Response Text: </td>";
					strValue += " <td align=\"left\" valign=\"top\"><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td align=\"left\" valign=\"top\" width=\"100%\"  class=\"Arial_12_black_normal\">"+strFMsg3+"</td>";
					strValue += " </tr></table></td></tr></table></td></tr>";
					
					
					if((!comptype.equalsIgnoreCase("Consumer")) && resposne_flag == 0)
					{
						strValue+="<tr>";
						strValue+="<td  height=\"20\" align=\"right\" width=\"90%\"  class=\"Arial_Narrow_12_black_bold\">If you are not satisfied with this response. Please</td>";
						strValue+="<td  width=\"10%\" align=\"left\"  class=\"Arial_Narrow_12_blue_bold\"><img src=\"../images/reply.gif\" alt=\"Click here to give reply\" width=\"68\" height=\"25\" border=\"0\" id=\""+z+"\" onclick=\"showAddNew(this.id)\" style=\"cursor:hand\" ></img></td>";
						strValue+="</tr>";
					}
					
					strValue+="<tr  id=\"status"+z+"\" style=\"display:inline\" align=\"center\">";
					strValue+="<td width=\"100%\" colspan=\"2\" height=\"20\"></td>";
					
					strValue+="</tr>";
					
					strValue+="<tr id=\"status1"+z+"\" style=\"display:none\" align=\"center\">";
					strValue+="<td height=\"20\" class=\"var_13_Success\" colspan=\"2\"></td>";
					strValue+="</tr>";
					strValue+="<tr id=\"rowid"+z+"\" align=\"center\" style=\"display:none\">";
					strValue+="<td colspan=\"2\" valign=\"top\" class=\"successmessages\"><a name=\"<%=z%>\">";
					strValue+="<form id=\""+z+"\" name=\"frmreply\" method=\"post\" action=\"responseSuccess.do\">";
					strValue+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
					strValue+="<tr>";
					strValue+="<td height=\"22\" width=\"100%\"  colspan=\"2\" align=\"left\"><strong class=\"Arial_Narrow_12_black_bold\">&nbsp;&nbsp;&nbsp;Write  reply to "+comptype+"</strong></td>";
					strValue+="</tr>";
					strValue+="<tr>";
					strValue+="<td align=\"center\" width=\"100%\"  colspan=\"2\"><textarea id=\"responsetext"+z+"\" name=\"responsetext\" cols=\"105\" rows=\"5\" class=\"Arial_12_black_normal\"></textarea></td>";
					strValue+="</tr>";
					strValue+="<tr>";
					strValue+="<td align=\"center\" width=\"100%\"  colspan=\"2\"><input name=\"Submit\" type=\"submit\" class=\"buttonCopy\" value=\"Submit\" >";
					strValue+="&nbsp;&nbsp;&nbsp;<input name=\"cancel\" type=\"button\" class=\"buttonCopy\" id=\""+z+"\" value=\"Cancel\" onClick=\"hideAddNew(this.id)\"></td>";
					strValue+="<input type=\"hidden\" name=\"respid\" value=\""+responseId+"\">";
					strValue+="<input type=\"hidden\" name=\"compId\" value=\""+numCopmId+"\">";
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
				strValue+="<tr align=\"center\">";
				strValue+="<td colspan=\"2\" bgcolor=\"#ffffff\" class=\"arial_14\">&nbsp;</td>";
				strValue+="</tr>";
			}
			strValue+="<tr id=\"comment\" style=\"display:none\" align=\"center\" >";
			strValue+="<td colspan=\"2\"><a name=\"ccomments\">";
			strValue+="<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#EDF9F8\">";
			if(commentVec.size()>0)
			{
				for(int i=0; i<commentVec.size(); i++)
				{
					Vector tempVec = (Vector)commentVec.elementAt(i);
					String commentText = tempVec.elementAt(0).toString().trim();
					String cdate = tempVec.elementAt(1).toString().trim();
					String ctime = tempVec.elementAt(4).toString().trim();
					String cfname = tempVec.elementAt(6).toString().trim();
					String clname = tempVec.elementAt(7).toString().trim();
					int ccityId = Integer.parseInt(tempVec.elementAt(8).toString().trim());
					String ccityName = rootMaster.getPlaceName(getDataSource(request, "indv"),ccityId);
					strValue+="<tr >";
					
					strValue+="<td  colspan=\"4\" height=\"22\" align=\"left\"  bgcolor=\"#BBF3E4\" class=\"Arial_Narrow_12_black_bold\">";
					strValue+="<table width=\"100%\" align=\"left\">";
					strValue+="<tr align=\"left\">";
					strValue+="<td width=\"39%\" align=\"left\" >Commented by :<span class=\"Arial_12_black_normal\" >"+cfname+" "+clname+"</span></td>";
					
					strValue+="<td align=\"right\" width=\"15%\" class=\"Arial_Narrow_12_black_bold\">&nbsp;Date:</td>";
					strValue+="<td><span class=\"Arial_12_black_normal\" width=\"15%\" align=\"left\" >"+cdate+"</span></td>";
					strValue+="<td align=\"left\" width=\"30%\" class=\"Arial_Narrow_12_black_bold\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;City:<span class=\"Arial_12_black_normal\" width=\"15%\" align=\"left\">"+ccityName+"</span></td>";
					
					strValue+="</tr>";
					strValue+="</table>";
					strValue+="</td>";
					strValue+="</tr>";
					
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
				 
					
					strValue += " <tr>";
					strValue += " <td colspan=\"2\"  align=\"left\" valign=\"top\" class=\"Arial_Narrow_12_black_bold\" width=\"100%\"> <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" height=\"40\"><tr><td width=\"13%\"  valign=\"top\">&nbsp;Comment Text: </td>";
					strValue += " <td align=\"left\" valign=\"top\"><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td align=\"left\" valign=\"top\" width=\"100%\"  class=\"Arial_12_black_normal\">"+strFMsg1+"</td>";
					strValue += " </tr></table></td></tr></table></td></tr>";
					
					
					
					
				
				}
				
			}
			else
			{
			strValue+="<tr  align=\"center\" >";
			strValue+="<td colspan=\"2\" class=\"Arial_Narrow_16_orange_normal\">No Comment </td>";
			strValue+="</tr>";
			}
			
			strValue+="</table>"; 
			strValue+="</a></td></tr>";
			 
	       
		    strValue+="</table>";
		
		return strValue;
		
	}
		  
		  
		
		
	private String getComplaintUnnamed(HttpServletRequest request, int numCopmId, int numbrandId, int numCatId)
	{
		IndvMaster im = new IndvMaster();
		AdvtMaster am = new AdvtMaster();
		Vector unnamedVec = am.getComplaintUnnamedMap(getDataSource(request, "indv"), numbrandId, numCatId);
		//System.out.println("unnamedVec Size "+unnamedVec.size());
		//System.out.println("unnamedVec "+unnamedVec);
		Vector dataVec = im.getComplaintUnnamedData(getDataSource(request, "indv"), numCopmId);
		//System.out.println("Data Vec in Details Arun Size "+dataVec.size());
		//System.out.println("Data Vec in Details Arun "+dataVec);
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
								strSubCatVal1 = im.getValueSubCat1(getDataSource(request, "indv"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==1)
							{
								strSubCatVal1 = im.getValueSubCat2(getDataSource(request, "indv"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==2)
							{
								strSubCatVal1 = im.getValueSubCat3(getDataSource(request, "indv"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==3)
							{
								strSubCatVal1 = im.getValueSubCat4(getDataSource(request, "indv"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==4)
							{
								strSubCatVal1 = im.getValueSubCat5(getDataSource(request, "indv"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==5)
							{
								strSubCatVal1 = im.getValueSubCat6(getDataSource(request, "indv"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==6)
							{
								strSubCatVal1 = im.getValueSubCat7(getDataSource(request, "indv"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==7)
							{
								strSubCatVal1 = im.getValueSubCat8(getDataSource(request, "indv"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==8)
							{
								strSubCatVal1 = im.getValueSubCat9(getDataSource(request, "indv"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==9)
							{
								strSubCatVal1 = im.getValueSubCat10(getDataSource(request, "indv"), Integer.parseInt(strSubCat1.trim()));
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
						strFName="*&nbsp;"+strFName+":";
					}
					else
					{
						strFName = strFName+":";
					}
					strFlag1 +=(numVisible == 1) ? "false" : "true";
					//System.out.println("str Flag1 "+strFlag1);
					if((strFlag1.indexOf("true") <= -1) && (!strFName.equalsIgnoreCase(""))&&(!strSubCatVal1.equalsIgnoreCase("")))
					{
						FinalDataVec.add(strFName+"~"+strSubCatVal1);
					}
					numCount2=numCount2+5;
					numCount3=numCount3+5;
					numCount4=numCount4+5;
				}
				//System.out.println("FinalDataVec "+ FinalDataVec);
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
					strFName="*&nbsp;"+strFName+":";
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
				//System.out.println("FinalDataVec "+i+" "+FinalDataVec);
			}
			//System.out.println("Final Data Vec Size "+FinalDataVec.size());
			//System.out.println("Final Data Vec "+FinalDataVec);
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
				//System.out.println("Arr Value >> "+arrValue.length);
				String strValue1 = " ~ ";
				if(j+1 < FinalDataVec.size())
				{
					strValue1 = FinalDataVec.elementAt(j+1).toString().trim();
				}
				String arrValue1[] = strValue1.split("~");
				//System.out.println("Arr Value 1>> "+arrValue1.length);
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
				strResult+="<tr>";
				strResult+="<td align=\"right\" valign=\"middle\" height=\"20\"></td>";
				strResult+="<td height=\"30\" align=\"right\" valign=\"middle\" class=\"Arial_Narrow_12_black_bold\">"+strFVal+"&nbsp </td>";
				strResult+="<td width=\"24%\" height=\"30\" align=\"left\" valign=\"middle\" class=\"Arial_12_black_normal\">"+strVal+"</td>"; 
				strResult+="<td width=\"19%\" height=\"30\" align=\"right\" valign=\"middle\" class=\"Arial_Narrow_12_black_bold\">"+strFVal1+"&nbsp;</td>";
				strResult+="<td height=\"30\" align=\"left\" valign=\"middle\" class=\"Arial_12_black_normal\">"+strVal1+"</td>";
				strResult+="</tr>";
			}
		}
		
		return strResult;
	}
	private String getPages(int minVal, int maxVal, int numSize, String getPages)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveIndvComplaintDetailURL('compdetres.do?'+this.value);\">";
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
