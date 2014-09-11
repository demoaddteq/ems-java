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
		String pageid = (request.getAttribute("pageid")!=null) ? request.getAttribute("pageid").toString() : (String)session.getAttribute("pageid") ;		
		
		System.out.println("pageid......on exis CompdetresAction........." +pageid);
		
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
			//////System.out.println("numCopmId>>>>>>>>"+numCopmId);
			
			
			String strResult = (request.getParameter("strResult")!=null) ? request.getParameter("strResult").trim() : "";
			
			//////System.out.println("strResult>>>>>>>>>>"+strResult);
			if(!numCopmId.equalsIgnoreCase("0"))
			
	    	{
	    		Vector<String> paramVec1 = new Vector<String>();
	    		paramVec1.add(numCopmId);
	    		paramVec1.add(userid);
	    		//-------------------------
		    	//int numArray[] = md.getCompanyMoves(numCopmId, getDataSource(request, "main"), minVal, maxVal, strFShortBy);
		    	
	    		//-------------------------
		    	int totalRow = indvMaster.getComplaintCount(getDataSource(request, "student"),paramVec1);
				//////System.out.println("totalRow Pages "+totalRow);
		    	String strPageHtml = getPages(minVal, maxVal, totalRow, strPgType);
				//////System.out.println("Pages "+strValue);
		    	Vector<String> paramVec2 = new Vector<String>();
		    	paramVec2.add(numCopmId);
		    	paramVec2.add(userid);
		    	
		    	
		    	Vector complaintVec = indvMaster.getComplaintDetails(getDataSource(request, "student"),paramVec2);
		    	//////System.out.println("complaintVec>>>>>>>>"+complaintVec);
		    	
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
		    	int advtlid=Integer.parseInt(complaintVec.elementAt(19).toString());
		    	Vector UserVec=rootMaster.getUserInfo(getDataSource(request, "student"), advtlid);
		    	
		    	String Name=""+UserVec.elementAt(4)+"&nbsp;"+UserVec.elementAt(5)+"";
		    	Vector additionalVec=new Vector();
		    	additionalVec.add(Name);
		    	
		    	String compType= rootMaster.getCompanyDetail(getDataSource(request, "student"), advtid).elementAt(17).toString();
		    	
		    	if (compType.equalsIgnoreCase("Corporates"))
		    	{
		    		compType="Company";
		    	}else if(compType.equalsIgnoreCase("Advertiser"))
		    	{
		    		compType="College";
		    	}else
		    	{
		    		compType="Consultant";
		    	}
		    	String comp_name ="";
		    	if(advtid<=0)
		    	{
		    		comp_name = complaintVec.elementAt(27).toString()+"(Other)";
		    	}
		    	else
		    	{
		    		comp_name = rootMaster.getBrandName(getDataSource(request, "student"), advtid);
		    	}
			
				
				int dealerid = Integer.parseInt(complaintVec.elementAt(20).toString());
				
				Vector<String> dealerVec1= new Vector<String>(); 
				if(dealerid!=0)
				{	//////System.out.println("dealerid>>>"+dealerid);
					dealerVec1 = indvMaster.getDealerDetail(getDataSource(request, "student"),dealerid);
					int contryId = Integer.parseInt(dealerVec1.elementAt(5).toString());//dealer contryId id
					String dealercontry = rootMaster.getCountryName(getDataSource(request, "student"),contryId);
					int stateId = Integer.parseInt(dealerVec1.elementAt(6).toString());//dealer stateId id
					String dealerstate = rootMaster.getStateName(getDataSource(request, "student"),stateId);
					int cityId = Integer.parseInt(dealerVec1.elementAt(8).toString());//dealer cityId id
					String dealercity = rootMaster.getPlaceName(getDataSource(request, "student"),cityId);
					//////System.out.println("dealerVec1>>befor-adding>"+dealerVec1);
					dealerVec1.add(dealercontry); 
					dealerVec1.add(dealerstate); 
					dealerVec1.add(dealercity); 
					
					}
				String catName="";
				String collegecatName="";
				
				int catid=Integer.parseInt(complaintVec.elementAt(4).toString());
				
				
				Vector catNamevec = new Vector(); 
				if (Integer.parseInt(complaintVec.elementAt(4).toString())!=0){
					
					
					if(compType.equalsIgnoreCase("Company")){
						
						catNamevec  = rootMaster.getCategoryName(getDataSource(request, "student"),catid);
						collegecatName = catNamevec.elementAt(0).toString();
						
					}else{
						
						collegecatName  = rootMaster.getStrCollegeCategoryName(getDataSource(request, "student"),catid);
					}
				
				}
				else
				{
					collegecatName="Consultant";
					
				}
				int tagid = Integer.parseInt(complaintVec.elementAt(21).toString());
				////System.out.println("complaintVec.elementAt(21).toString() comdeteres Action"+complaintVec.elementAt(21).toString());
				String tagName = rootMaster.getStrTagName(getDataSource(request, "student"),tagid);
				Vector <String> infoVec = new Vector<String>();
				infoVec.add(tagName);
				infoVec.add(collegecatName);
				
				//get no of responses for this page by using getCompanyType() method of rootmaster
				//get all company list with the no of responses accorrding to company type. rootmaster.getResponseList()
				Vector<String> respDataVec = new Vector<String>();
				respDataVec.add(complaintVec.elementAt(0).toString().trim());
				respDataVec.add("Consumer");
				Vector responseVec = rootMaster.getResponseListNew(getDataSource(request, "student"), respDataVec);
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
				infoVec.add(String.valueOf(Name));			//for Company user Name
				
				
				//end of response counting
				//////System.out.println("responseVec in action>>>>>>>>>"+responseVec);
				//get comment vetor
				Vector commentVec = rootMaster.getCommentsList(getDataSource(request, "student"), Integer.parseInt(complaintVec.elementAt(0).toString().trim()));
				String strUnnamed = getComplaintUnnamed(request, Integer.parseInt(numCopmId), Integer.parseInt(complaintVec.elementAt(18).toString().trim()), Integer.parseInt(complaintVec.elementAt(4).toString().trim()));
				//////System.out.println("dealerVec1>>Beforcalling founction>"+dealerVec1);
				
				strComplaintHtml = getComplaintDetail(strUnnamed, numCopmId,strResult, strPageHtml,  complaintVec, comp_name, dealerVec1, infoVec,strShortBy, responseVec,commentVec,request, remander,compType);
		    	
		    	
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
	
	 
	private String getComplaintDetail(String strUnnamed, String numCopmId,String strResult,String strPageHtml,Vector complaintVec,String comp_name, Vector dealerVec1, Vector infoVec, String strShortBy, Vector responseVec,Vector commentVec, HttpServletRequest request, int remander,String compType){
		
		int z=0;
		RootMaster rootMaster = new RootMaster();  
		
		//StringBuffer url = request.getRequestURL();
		String url = "compdetres.jsp?numCid="+numCopmId;
		if(strResult.length()>0)
		{
			url="compdetres.jsp";
		}
		int respid=(request.getParameter("respid")!=null) ? Integer.parseInt(request.getParameter("respid")) : 0;
		//////System.out.println("respid>>>>>>>>"+respid);
		
		String strValue = "<div class=\"Page_Title\">Complaint Details</div>";
		strValue += "<div class=\"top_panel\">";
		strValue +="<div class=\"Page_Heading\">";
		strValue +="<div class=\"Page_Heading_Text\" style=\"width: 70%;\"></div></div>";
	
		
		if(complaintVec.size()==0)
		{

			strValue +="<div class=\"Fields_Container\">";
			strValue +="<div class=\"Fields_Container_Inside\">";
					strValue += "<div class=\"Fields_Row_2a\">";
					strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 20%;\" text-align: right;\" No Data Found...</div>";
					strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 20%;\" text-align: right;\" </div></div>";
					strValue += "</div</div>";				
		}	
		strValue +="<div class=\"Fields_Container\">";
		strValue +="<div class=\"Fields_Container_Inside\">";
	
			strValue += "<div class=\"Fields_Row_1\">";
	    	strValue += "<div class=\"Row_2_Fields_Col_2\"> TO: </div>";
	    	strValue += "<div class=\"Row_2_Fields_Col_2\">"+comp_name+" , ("+infoVec.elementAt(6).toString().trim()+")</div></div>";
			 
	    	strValue += "<div class=\"Fields_Row_1\">";
	    	strValue += "<div class=\"Row_2_Fields_Col_2\"> Complaint Title: </div>";
	    	strValue += "<div class=\"Row_2_Fields_Col_2\">"+complaintVec.elementAt(1).toString().trim()+" </div></div>";
			   strValue+="<td><div align=\"right\" class=\"bold\"> Query ID: </div></td>";
			   strValue+="<td>&nbsp;</td>";
			   strValue+="<td><div align=\"left\" >"+complaintVec.elementAt(17).toString().trim()+"</div></td>";
			   strValue+="<td>&nbsp;</td>";
			 strValue+="</tr>";
			 strValue+="<tr>";
			   strValue+="<td>&nbsp;</td>";
			   strValue+="<td><div align=\"right\" class=\"bold\"> Query Originated Date: </div></td>";
			   strValue+="<td>&nbsp;</td>";
			   
			   String newdate[]=complaintVec.elementAt(5).toString().split("-");
			   
			   strValue+="<td><div align=\"left\" >"+newdate[2]+"-"+newdate[1]+"-"+newdate[0]+"</div></td>";
			   strValue+="<td>&nbsp;</td>";
			 strValue+="</tr>";
			 strValue+="<tr>";
			   strValue+="<td>&nbsp;</td>";
			   strValue+="<td><div align=\"right\" class=\"bold\"> Category: </div></td>";
			   strValue+="<td>&nbsp;</td>";
			   strValue+="<td><div align=\"left\" >"+infoVec.elementAt(1).toString().trim()+"</div></td>";
			   strValue+="<td>&nbsp;</td>";
			 strValue+="</tr>";
			 strValue+="<tr>";
			  strValue+="<td colspan=\"5\"><hr color=\"#C1C1C1\"></td>";
			 strValue+="</tr>";
			 
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
		
		/*  strValue+="<table  height = \"350\" width=\"100%\" valign=\"top\" >";
		  strValue+="<tr>";
		  //////////////////////////// start td for complaint detail
			strValue+="<td width=\"57%\" valign=\"top\"><div style =\" height:350px; overflow-y:scroll; \">";
			strValue+="<table width=\"100%\"   border=\"0\" >";
		  	strValue += "<tr class=\"ms_sans_serif\" >";
	        strValue += "<td >";
	       
	        
	        strValue += "<tr align=\"left\">";
	        strValue += "<td width=\"35%\" align=\"left\"class=\"bold\" > &nbsp;&nbsp;&nbsp;Query Title:</td>";
	        strValue += "<td  width=\"65%\" align=\"left\" ></td>";
	        strValue += "</tr>";
	        strValue += "<tr >";
	        strValue += "<td colspan=\"2\"  align=\"left\"  width=\"100%\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;"+complaintVec.elementAt(1).toString().trim()+" </td>";
	       
	        strValue += "</tr>";
	       
	        strValue += "<tr align=\"left\">";
	        strValue += "<td colspan=\"2\" class=\"formHeadingsBold\"><hr color=\"#C1C1C1\"></td>";
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
		        strValue += "<td  width=\"35%\" align=\"left\" class=\"bold\" > &nbsp;&nbsp;&nbsp;Query Text:</td>";
		        strValue += "<td  align=\"left\" ><span ></span></td>";
		        strValue += "</tr>";
		        strValue += "<tr align=\"left\">";
		        strValue += "<td  colspan=\"2\" ><table width=\"100%\" align=\"center\"><tr><td width=\"5%\"></td><td align=\"left\"><div align=\"justify\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+strFMsgcompl+"</div></td><td width=\"5%\"></td></tr></table></td>";
		        strValue += "</tr>";
		        
		        strValue += "<tr align=\"left\" style=\"display:none\">";
		        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \"><hr color=\"#C1C1C1\"></td>";
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
					
					
					
					strValue += "<tr align=\"left\" style=\"display:none\">";
					strValue += "<td  align=\"left\" class=\"bold\" > &nbsp;&nbsp;&nbsp;Relevent Text:</td>";
			        strValue += "<td  align=\"left\" ><span ></span></td>";
			        strValue += "</tr>";	
			        
			        strValue += "<tr align=\"left\" style=\"display:none\">";
			        strValue += "<td width=\"80%\" colspan=\"2\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+strFMsgcomp3+"</td>";
			        strValue += "</tr>";		
			        
				
				}
		             
		  ///////////////////////////////////////////////////////////////////////////////////////////
			  strValue+="</table>";
			  strValue+="</div></td>";
			  // End td for complaint detail
			  strValue+="<td width=\"2%\"></td>";
		
		  	//////System.out.println("INSIDE RIGHT TD");
		  	strValue+="<td width=\"39%\" valign=\"top\"><div style =\" height:350px; overflow-y:scroll; \">";
		    strValue+="<table width=\"100%\" valign=\"top\"  border=\"0\" >";
		  	strValue += "<tr align=\"left\" valign=\"top\" >";
	        strValue += "<td  align=\"left\" valign=\"top\"  colspan=\"2\" class=\"bold\">Query Information</td>";
	        strValue += "</tr>";
	        
	        strValue += "</tr>";
	        strValue += "<td>";
	        strValue += "&nbsp";
	        strValue += "</td>";
	        strValue += "</tr>";
	        
	        strValue += "<tr align=\"left\" >";
	        strValue += "<td  align=\"right\"class=\"bold\" >Query ID:  </td>";
	        strValue += "<td  align=\"left\" ><span >"+complaintVec.elementAt(17).toString().trim()+" </span></td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\"class=\"bold\" > Date: </td>";
	        strValue += "<td  align=\"left\" ><span >"+complaintVec.elementAt(5).toString().trim()+"</span></td>";
	        strValue += "</tr>";
	        
	        
	        
	        
	        
	        
	      
	        strValue += "<tr align=\"left\"style=\"display:none\">";
	        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \"><hr color=\"#C1C1C1\"></td>";
	        strValue += "</tr>";
	        
	        strValue += "</tr>";
	        strValue += "<td>";
	        strValue += "&nbsp";
	        strValue += "</td>";
	        strValue += "</tr>";
	        
	        
	        strValue += "<tr align=\"left\" style=\"display:none\">";
	        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \" align=\"left\" >";
	        strValue += "<table align=\"left\">";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td valign=\"top\"width=\"57%\"  align=\"left\">";
	        strValue += "<table width=\"100%\" align=\"left\">";
	        strValue += "<tr >";
	        strValue += "<td  align=\"left\" width=\"35%\" class=\"bold\"  > Status: </td>";
	        strValue += " <td align=\"left\" ><span >"+infoVec.elementAt(0).toString().trim()+"</span></td>";
	        strValue += "</tr>";
	       
	        strValue += "</table>";
	        strValue += "</td>";
	      
	        strValue += "<td  valign=\"top\" style=\"display:none\">";
	        strValue += "<table width=\"100%\">";
	        strValue += "<tr >";
	        strValue += "<td  align=\"left\" class=\"bold\" > Publish: </td>";
	                
		    if((complaintVec.elementAt(26).toString()).equalsIgnoreCase("1@Week"))
			{
		    strValue += " <td  align=\"left\"><span >1 Week</span></td>";
			}
		    else if((complaintVec.elementAt(26).toString()).equalsIgnoreCase("2@Week"))
			{
		    strValue += " <td   align=\"left\"><span >2 Week</span></td>";
			}
		    else if((complaintVec.elementAt(26).toString()).equalsIgnoreCase("4@Week"))
			{
		    strValue += " <td   align=\"left\"><span >4 Week</span></td>";
			}
		    else if((complaintVec.elementAt(26).toString()).equalsIgnoreCase("6@Week"))
			{
		    strValue += " <td   align=\"left\"><span >6 Week</span></td>";
			}
		    else{
		    strValue += " <td   align=\"left\"><span >Immediate</span></td>";	   
		    }
	        
	        
	     
	        strValue += "</tr>";
	        strValue += "</table>";
	        strValue += "</td>";
	        
	        strValue += "</tr>";
	        strValue += "</table>";
	        strValue += "</td>";
	        strValue += "</tr>";
	        
	        
	        
	        
	       
	        strValue += "<tr align=\"left\">";
	        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \"><hr color=\"#C1C1C1\"></td>";
	        strValue += "</tr>";
	        
	        strValue += "</tr>";
	        strValue += "<td>";
	        strValue += "&nbsp";
	        strValue += "</td>";
	        strValue += "</tr>";
	        
	        
	        //////System.out.println("InfoVector`````in additional``detail````11111111111"+complaintVec);
		  
	        strValue += " <tr align=\"left\">";
	        strValue += "<td colspan=\"2\" class=\"bold\">"+compType+" Information</td>";
	        strValue += " </tr>";
	        
	        strValue += "</tr>";
	        strValue += "<td>";
	        strValue += "&nbsp";
	        strValue += "</td>";
	        strValue += "</tr>";
	        
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\"class=\"bold\" > "+compType+" Name: </td>";
	        strValue += "<td  align=\"left\" ><span >"+comp_name+" </span></td>";
	        strValue += "</tr>";
	        
	        
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\"class=\"bold\" > Category: </td>";
	        strValue += "<td  align=\"left\" ><span >"+infoVec.elementAt(1).toString().trim()+" </span></td>";
	        strValue += "</tr>";
	        
	        
	        
	        
	       
	        
	      //important line do not delete it// strValue += strUnnamed;
     
	        strValue += " <tr align=\"left\" style=\"display:none\">";
	        strValue += " <td colspan=\"2\" class=\"formHeadingsBold style5\"><hr color=\"#C1C1C1\"></td>";
	        strValue += " </tr>";
	        strValue += "<tr align=\"left\" style=\"display:none\">";
	        strValue += " <td colspan=\"2\" class=\"bold\">Dealer Information</td>";
	        strValue += "</tr>";
	        if(dealerVec1.size()!=0)
			{
	        	//////System.out.println("DEALERVECTOR-size----------->>>>>>>>>>>>>>>>>>"+dealerVec1.size());
	        	//////System.out.println("DEALERVECTOR-size----------->>>>>>>>>>>>>>>>>>"+dealerVec1);
	        strValue += "<tr align=\"left\" style=\"display:none\">";	
	        strValue += "<td colspan=\"2\">";
	        strValue += "<table align=\"center\" width=\"90%\">";
	        strValue += "<tr align=\"left\" style=\"display:none\">";
	        strValue += "<td  align=\"right\" class=\"bold\" width=\"50%\"> Dealer Name: </td>";
	        strValue += "<td  align=\"left\"   width=\"50%\">"+dealerVec1.elementAt(1).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"bold\" > Dealer Address: </td>";
	        strValue += "<td  align=\"left\" >"+dealerVec1.elementAt(4).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\" style=\"display:none\">";
	        strValue += "<td  align=\"right\" class=\"bold\" > Dealer Country: </td>";
	        strValue += "<td  align=\"left\"  >"+dealerVec1.elementAt(13).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\" style=\"display:none\">";
	        strValue += "<td  align=\"right\" class=\"bold\" > Dealer State: </td>";
	        strValue += "<td  align=\"left\"  >"+dealerVec1.elementAt(14).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\" style=\"display:none\">";
	        strValue += "<td  align=\"right\" class=\"bold\" > Dealer City: </td>";
	        strValue += "<td  align=\"left\"  >"+dealerVec1.elementAt(15).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\" style=\"display:none\">";
	        strValue += "<td  align=\"right\" class=\"bold\" > Dealer District: </td>";
	        strValue += "<td  align=\"left\"  >"+dealerVec1.elementAt(15).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\" style=\"display:none\">";
	        strValue += "<td  align=\"right\" class=\"bold\" > Contact Person: </td>";
	        strValue += "<td  align=\"left\" >"+dealerVec1.elementAt(2).toString().trim()+" </td>";
	        strValue += "</tr>";
	       // //////System.out.println("DEALERVECTOR-size-------temMno---->>>>>>>>>>>>>>>>>>"+dealerVec1);
	          	        
		  strValue+="</table>";
		  strValue+="</div></td>";
		  // End td for additional detail
		  	strValue+="<td width=\"1%\"></td>";
		    strValue+="</tr>";
		   
		    strValue+="</table>";*/
		    
		     
	        

		    strValue+="<table   width=\"100%\" >";
		    
		    
	        strValue +="<tr height=\"20\">";
			strValue +="<td colspan=\"2\"  align=\"left\" class=\"bold\" width=\"100%\" > Number Of Response By : </td>";
			strValue +="</tr>";
			strValue +="<tr align=\"left\" valign=\"top\">";
			strValue +="<td colspan=\"2\" class=\"bold\"width=\"100%\" >";
			strValue +=" &nbsp;Student:&nbsp;<span class=\"bold\">"+infoVec.elementAt(5).toString().trim()+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			//strValue +="Core:&nbsp;<span class=\"bold\">"+infoVec.elementAt(4).toString().trim()+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			strValue +=""+compType+":&nbsp;<span class=\"bold\">"+infoVec.elementAt(3).toString().trim()+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			strValue +="Total:&nbsp;<span class=\"bold\">"+infoVec.elementAt(2).toString().trim()+"</span>";
			strValue +="</td>";
			strValue +="</tr>";
			
			strValue +="</tr>";
			strValue += "<tr align=\"left\"  >";
	        strValue += "<td colspan=\"2\" valign=\"top\" align=\"left\" ><img  onclick=\"showRes()\" style=\"cursor:hand\" src=\"../images/giveresponse.gif\" width=\"63\" height=\"19\" border=\"0\" alt=\"Click here to give response\"></img></td>";
	        strValue += "</tr>";
	        strValue +="<tr  >";
			strValue +="<td colspan=\"2\" align=\"right\" class=\"bold\"><hr color=\"#C1C1C1\"></td>";
			strValue +="</tr>";
			
			//System.out.println("ist>>>>>>>>>>>ZZZZZZZZZZZZ"+z);
			strValue+="<tr id=\"response\" align=\"center\" style=\"display:none\">";
			//System.out.println("2nd>>>>>>>>>>>ZZZZZZZZZZZZ"+z);
			strValue+="<td valign=\"top\" colspan=\"2\"><form id=\""+z+"\" name=\"frmreply\" method=\"post\" action=\"responseSuccess.do\" >";
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
			strValue+="<input type=\"hidden\" name=\"compId\" value=\""+numCopmId+"\">";
			
			
			strValue+="<tr>";
			 
        		strValue+="<td colspan=\"4\" align=\"center\"><input name=\"Submit\" type=\"submit\"  value=\"Submit\" >&nbsp;&nbsp;&nbsp;";
        		strValue+="<input name=\"cancel\" type=\"button\"   value=\"Cancel\" onClick=\"reshide()\"></td>";
        		
        		strValue+="  </tr>";	
			
			
			strValue+=" </table>";
			strValue+="</form>";
			strValue+=" </td>";
			strValue+="</tr>";
			
	        
			
		    int brandresponse = Integer.parseInt(infoVec.elementAt(3).toString().trim());
			int coreresponse = Integer.parseInt(infoVec.elementAt(4).toString().trim());
			if(brandresponse == 0 && coreresponse ==0)
			{
			strValue+="<tr>";
			strValue+="<td height=\"\" colspan=\"2\" align=\"left\" valign=\"top\">";
			strValue+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
			strValue+="<tr>";
			strValue+="<td width=\"3%\">&nbsp;</td>";
			strValue+="<td width=\"43%\"  align=\"left\" valign=\"top\" ></td>";
			strValue+="<td width=\"20%\">&nbsp;</td>";
			strValue+="<td width=\"27%\"  align=\"right\" valign=\"top\" class=\"bold\"> <img src=\"../images/commentsoncomplaints.gif\" width=\"150\" height=\"19\"  border=\"0\" alt=\"Click here to see all comments\" onclick=\"showAddNew1()\" style=\"cursor:hand\"></img></td>";
			strValue+="<td width=\"7%\" align=\"left\" valign=\"middle\" class=\"bold\">#&nbsp;"+commentVec.size()+"</td>";
			strValue+="</tr>";
			strValue+="</table>";
			strValue+="</td>";
			strValue+="</tr>";
			strValue+="<tr>";
			strValue+="<td  colspan=\"2\" align=\"center\" valign=\"top\" class=\"bold\">No Response";
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
				strValue+="<td width=\"27%\"  align=\"right\" valign=\"top\" class=\"bold\"> <img src=\"../images/commentsoncomplaints.gif\" width=\"150\" height=\"19\"  border=\"0\" alt=\"Click here to see all comments\" onclick=\"showAddNew1()\" style=\"cursor:hand\" ></img></td>";
				strValue+="<td width=\"7%\" align=\"left\" valign=\"middle\" class=\"bold\">#&nbsp;"+commentVec.size()+"</td>";
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
					strValue+="<td height=\"20\"  colspan=\"5\"></td>";
				}
				else if(strResult.equalsIgnoreCase("success"))
				{
					strValue+="<td  height=\"20\" colspan=\"2\" ></td>";
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
			strValue+="<td height=\"22\"><strong class=\"bold\">&nbsp;&nbsp;&nbsp;&nbsp;Please Write a request to Core";
			strValue+=":</strong></td>";
			strValue+="</tr>";
			strValue+="<tr>";
			strValue+="<td align=\"center\"><textarea id=\"responsetext"+z+"\" name=\"responsetext\" cols=\"65\" rows=\"4\"  ></textarea></td>";
			strValue+="</tr>";
			strValue+="<tr>";
			strValue+="<td align=\"center\"><input name=\"Submit\" type=\"submit\"  value=\"Submit\">";
			strValue+="&nbsp;&nbsp;&nbsp;<input name=\"cancel\" id=\""+z+"\" type=\"button\"  value=\"Cancel\" onClick=\"hideAddNew(this.id)\"></td>";
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
					String cityName = rootMaster.getPlaceName(getDataSource(request, "student"),cityId);
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
					String compName ="";
					if(comp_type.equalsIgnoreCase("Advertiser"))
					{
						bgrow="#CCECFF";
						bgtab="#F0FAF9";
						comptype = "College";
						compName =comp_name;
						
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
					    compName ="Resident";
					}else if(comp_type.equalsIgnoreCase("Corporates"))
					{
						bgrow="#CCECFF";
						bgtab="#F0FAF9";
						comptype = "Company";
						compName =comp_name;
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
						strValue+="&nbsp;Reply By Resident";
					}
					else if(comp_type.equalsIgnoreCase("Advertiser"))
					{
						strValue+="&nbsp;Response By Builder";
					}
					else if(comp_type.equalsIgnoreCase("Corporates"))
					{
						strValue+="&nbsp;Response By Facility Manager";
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
		        
					strValue += "&nbsp;on  "+respDate+"/"+tempVec.elementAt(4)+"  /</font><span class=\"student\" >"+compName+"</span>";
		       
					strValue += "</div></td>";
					if((!comptype.equalsIgnoreCase("Consumer")) && resposne_flag == 0)
					{
						
						strValue+="<td width=\"32%\" ><div align=\"right\"><img src=\"../images/reply.gif\" alt=\"Click here to give reply\"  id=\""+z+"\" onclick=\"showAddNew(this.id)\" style=\"cursor:hand\" ></img></div></td>";
						
					}else
					{
						strValue+="<td width=\"32%\" >&nbsp;</td>";
					}
			      	
					
		        
		        
		        strValue+="</tr>";
					
					
				strValue+="</table>";
				strValue+="</td>";
				strValue+="</tr>";
					
					
					
					
					
					strValue+="<tr  id=\"status"+z+"\" style=\"display:inline\" align=\"center\">";
					strValue+="<td width=\"100%\" colspan=\"2\" height=\"20\"></td>";
					
					strValue+="</tr>";
					
					strValue+="<tr id=\"status1"+z+"\" style=\"display:none\" align=\"center\">";
					strValue+="<td height=\"20\"  colspan=\"2\"></td>";
					strValue+="</tr>";
					strValue+="<tr id=\"rowid"+z+"\" align=\"center\" style=\"display:none\">";
					strValue+="<td colspan=\"2\" valign=\"top\" ><a name=\"<%=z%>\">";
					strValue+="<form id=\""+z+"\" name=\"frmreply\" method=\"post\" action=\"responseSuccess.do\">";
					strValue+="<table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
					strValue+="<tr>";
					strValue+="<td height=\"22\" width=\"100%\"  colspan=\"2\" align=\"left\"><strong class=\"bold\">&nbsp;&nbsp;&nbsp;Write  reply to Company</strong></td>";
					strValue+="</tr>";
					strValue+="<tr>";
					strValue+="<td align=\"center\" width=\"100%\"  colspan=\"2\"><textarea id=\"responsetext"+z+"\" name=\"responsetext\" cols=\"65\" rows=\"4\" ></textarea></td>";
					strValue+="</tr>";
					strValue+="<tr>";
					strValue+="<td align=\"center\" width=\"100%\"  colspan=\"2\"><input name=\"Submit\" type=\"submit\" value=\"Submit\" >";
					strValue+="&nbsp;&nbsp;&nbsp;<input name=\"cancel\" type=\"button\"  id=\""+z+"\" value=\"Cancel\" onClick=\"hideAddNew(this.id)\"></td>";
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
				strValue+="<td colspan=\"2\" bgcolor=\"#ffffff\" >&nbsp;</td>";
				strValue+="</tr>";
			}
			strValue+="<tr id=\"comment\" style=\"display:none\" align=\"center\" >";
			strValue+="<td colspan=\"2\"><a name=\"ccomments\">";
			strValue+="<table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >";
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
					String ccityName = rootMaster.getPlaceName(getDataSource(request, "student"),ccityId);
					
					
					int comp_id = Integer.parseInt(tempVec.elementAt(5).toString().trim());
					String compName = rootMaster.getComp_Type(getDataSource(request, "student"),comp_id);
					if(compName.equalsIgnoreCase("Corporates"))
					{
						compName="Facility Manager";
					}else if(compName.equalsIgnoreCase("Advertiser"))
					{
						compName="Builder";
					}else
					{
						compName="Resident";
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
					strValue+="<td width=\"97%\" colspan=\"2\" ><div align=\"justify\">"+strFMsg1+"</div></td>";
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
					strValue +=cfname+" "+cfname;
		        	strValue += "&nbsp;on  "+cdate+"/"+ctime+"  /</font><span class=\"student\" >"+compName+"</span>";
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
			strValue+="<tr  align=\"center\" >";
			strValue+="<td colspan=\"2\" class=\"bold\">No Comment </td>";
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
		Vector unnamedVec = am.getComplaintUnnamedMap(getDataSource(request, "student"), numbrandId, numCatId);
		//////System.out.println("unnamedVec Size "+unnamedVec.size());
		//////System.out.println("unnamedVec "+unnamedVec);
		Vector dataVec = im.getComplaintUnnamedData(getDataSource(request, "student"), numCopmId);
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
								strSubCatVal1 = im.getValueSubCat1(getDataSource(request, "student"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==1)
							{
								strSubCatVal1 = im.getValueSubCat2(getDataSource(request, "student"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==2)
							{
								strSubCatVal1 = im.getValueSubCat3(getDataSource(request, "student"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==3)
							{
								strSubCatVal1 = im.getValueSubCat4(getDataSource(request, "student"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==4)
							{
								strSubCatVal1 = im.getValueSubCat5(getDataSource(request, "student"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==5)
							{
								strSubCatVal1 = im.getValueSubCat6(getDataSource(request, "student"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==6)
							{
								strSubCatVal1 = im.getValueSubCat7(getDataSource(request, "student"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==7)
							{
								strSubCatVal1 = im.getValueSubCat8(getDataSource(request, "student"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==8)
							{
								strSubCatVal1 = im.getValueSubCat9(getDataSource(request, "student"), Integer.parseInt(strSubCat1.trim()));
							}
							if(k==9)
							{
								strSubCatVal1 = im.getValueSubCat10(getDataSource(request, "student"), Integer.parseInt(strSubCat1.trim()));
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
				strResult+="<tr>";
				strResult+="<td align=\"right\" valign=\"middle\" height=\"20\"></td>";
				strResult+="<td height=\"30\" align=\"right\" valign=\"middle\" class=\"bold\">"+strFVal+"&nbsp </td>";
				strResult+="<td width=\"24%\" height=\"30\" align=\"left\" valign=\"middle\" >"+strVal+"</td>"; 
				strResult+="<td width=\"19%\" height=\"30\" align=\"right\" valign=\"middle\" class=\"bold\">"+strFVal1+"&nbsp;</td>";
				strResult+="<td height=\"30\" align=\"left\" valign=\"middle\" >"+strVal1+"</td>";
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
