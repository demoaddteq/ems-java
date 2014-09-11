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

public class ComplaintDetailAction extends Action {
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
		

		

		HttpSession session =request.getSession();
		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		
		String userid=(String)session.getAttribute("uId");
		String companyid=(String)session.getAttribute("compId");
		
		
		String strPgType = (request.getParameter("pgtyp")!=null) ? request.getParameter("pgtyp").trim() : "Details";
		String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "creation_date";
				
		int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
		int maxVal = 10;
		IndvMaster indvMaster = new IndvMaster();
		RootMaster rootMaster = new RootMaster();
	
		String strComplaintHtml = "";
						
		if(strPgType.equalsIgnoreCase("Details"))
		{
			String numDraftId = (request.getParameter("numDraftId")!=null) ? request.getParameter("numDraftId").trim() : "0";
			
			if(!numDraftId.equalsIgnoreCase("0") )
	    	{
	    		Vector<String> paramVec1 = new Vector<String>();
	    		paramVec1.add(numDraftId);
	    		paramVec1.add(userid);
	//	    	-------------------------
		    	//int numArray[] = md.getCompanyMoves(numCopmId, getDataSource(request, "main"), minVal, maxVal, strFShortBy);
		    	
	//	    	-------------------------
		    	int totalRow = indvMaster.getDraftsCount(getDataSource(request, "indv"),paramVec1);
				//System.out.println("totalRow Pages "+totalRow);
		    	String strPageHtml = getPages(minVal, maxVal, totalRow, strPgType);
				//System.out.println("Pages "+strValue);
		    	Vector<String> paramVec2 = new Vector<String>();
		    	paramVec2.add(numDraftId);
		    	paramVec2.add(userid);
		    	
		    	
		    	Vector draftVec = indvMaster.getDraftsDetails(getDataSource(request, "indv"),paramVec2);
		    	System.out.println("advt id "+draftVec.elementAt(14).toString());
		    	int advtid = Integer.parseInt(draftVec.elementAt(14).toString());
		    	String comp_name = "";
				if(advtid<=0)
				{
					comp_name = draftVec.elementAt(13).toString()+"(Other)";
				}
				else
				{
					comp_name = rootMaster.getBrandName(getDataSource(request, "indv"), advtid);
				}
		    	
					
				
				String catName="";
				int catid=Integer.parseInt(draftVec.elementAt(4).toString());
				Vector catNamevec = rootMaster.getCategoryName(getDataSource(request, "indv"),catid);
				if(Integer.parseInt(catNamevec.elementAt(1).toString().trim())==0){
					catName=catNamevec.elementAt(0).toString().trim()+"(Other)";
				}else
				{
					catName=catNamevec.elementAt(0).toString().trim();
				}
		    	
		    	
				
				//System.out.println("cat Name "+catName);    							
		    	int dealerid = Integer.parseInt(draftVec.elementAt(12).toString());
		    	Vector dealerVec1 = new Vector();
		    	if(dealerid!=0)
				{	
					dealerVec1 = indvMaster.getDealerDetail(getDataSource(request, "indv"),dealerid);
					int contryId = Integer.parseInt(dealerVec1.elementAt(5).toString());//dealer contryId id
					String dealercontry = rootMaster.getCountryName(getDataSource(request, "indv"),contryId);
					int stateId = Integer.parseInt(dealerVec1.elementAt(6).toString());//dealer stateId id
					String dealerstate = rootMaster.getStateName(getDataSource(request, "indv"),stateId);
					int cityId = Integer.parseInt(dealerVec1.elementAt(8).toString());//dealer cityId id
					String dealercity = rootMaster.getPlaceName(getDataSource(request, "indv"),cityId);
							
					dealerVec1.add(dealercontry); 
					dealerVec1.add(dealerstate); 
					dealerVec1.add(dealercity); 
				
				}
		    	//System.out.println("dealerVec1>>>>>>>>>>>>>>>"+dealerVec1);
				Vector <String> infoVec = new Vector<String>();
				infoVec.add(catName);//0//category name
				infoVec.add(comp_name);//1//Brand name
				//System.out.println("infoVec "+infoVec);
				
				//System.out.println("infoVec..............."+infoVec);
				String strUnnamed = getComplaintUnnamed(request, Integer.parseInt(numDraftId.trim()), advtid, Integer.parseInt(draftVec.elementAt(4).toString().trim()));
		    	strComplaintHtml = getDraftDetail(strUnnamed, numDraftId, strPageHtml,  draftVec,  dealerVec1, infoVec, strShortBy, remander);
		    	
		    	
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
	private String getDraftDetail(String strUnnamed, String numCopmId,String strPageHtml,Vector draftVec, Vector dealerVec1, Vector infoVec, String strShortBy, int remander){
		String strValue ="";
		
		
		
		if(draftVec.size()==0)
		{
					strValue += "<tr align=\"center\">";
					strValue += "<td height=\"20\" valign=\"center\" colspan=\"2\" class=\"Arial_Narrow_16_orange_normal\"> No Data Found</td>";
					strValue += "</tr>";
					
		}
		
		  strValue+="<table  height = \"350\" width=\"100%\" >";
		  strValue+="<tr>";
		  //////////////////////////// start td for complaint detail
			strValue+="<td width=\"60%\" valign=\"top\"><div style =\" height:350px; overflow-y:scroll; \">";
			strValue+="<table width=\"100%\"   border=\"0\" >";
		  	strValue += "<tr class=\"ms_sans_serif\" >";
	        strValue += "<td >";
	       
	        
	        
	        
	        strValue += "<tr align=\"left\">";
	        strValue += "<td width=\"35%\" align=\"left\"class=\"var_12_bold_orange\" > &nbsp;&nbsp;&nbsp;Complaint Title:</td>";
	        strValue += "<td  width=\"65%\" align=\"left\" ></td>";
	        strValue += "</tr>";
	        strValue += "<tr >";
	        strValue += "<td colspan=\"2\" class=\"Arial_12_black_normal\" align=\"left\"  width=\"100%\" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;"+draftVec.elementAt(1).toString().trim()+"</td>";
	       
	        strValue += "</tr>";
	       
	        strValue += "<tr align=\"left\">";
	        strValue += "<td colspan=\"2\" class=\"formHeadingsBold\"><hr color=\"#ff722b\"></td>";
	        strValue += "</tr>";
	        
	        
	        String strFMsgcompl = "";
			String strMessagecompl = draftVec.elementAt(2).toString().trim()!=null ? draftVec.elementAt(2).toString().trim():"";
			
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
		        strValue += "<td  align=\"left\" class=\"var_12_bold_orange\" > &nbsp;&nbsp;&nbsp;Complaint Text:</td>";
		        strValue += "<td  align=\"left\" ><span class=\"Arial_12_black_normal\"></span></td>";
		        strValue += "</tr>";
		        strValue += "<tr align=\"left\">";
		        strValue += "<td  colspan=\"2\" ><table width=\"100%\" align=\"center\"><tr><td width=\"5%\"></td><td align=\"left\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+strFMsgcompl+"</td><td width=\"5%\"></td></tr></table></td>";
		        strValue += "</tr>";
		        
		        strValue += "<tr align=\"left\">";
		        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \"><hr color=\"#ff722b\"></td>";
		        strValue += "</tr>";
		        
		        if((draftVec.elementAt(3).toString()).length()!=0){
					String strFMsgcomp3 = "";
					String strMessagecomp3 = draftVec.elementAt(3).toString()!=null ? draftVec.elementAt(3).toString().trim():"";
					
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
		        
		        strValue+="</table>";
		        strValue+="</div></td>";
				  // End td for complaint detail
		        strValue+="<td width=\"1%\"></td>";
		        
		        
		        strValue+="<td width=\"38%\" valign=\"top\" ><div style =\" height:350px; overflow-y:scroll; \">";
			    strValue+="<table width=\"100%\" valign=\"top\"  border=\"0\" >";
			  	strValue += "<tr align=\"left\" valign=\"top\" >";
		        strValue += "<td  align=\"left\" valign=\"top\"  colspan=\"2\" class=\"var_12_bold_orange\">Complaint Information:</td>";
		        strValue += "</tr>";
		         
		        strValue += "<tr align=\"left\">";
		        strValue += "<td  align=\"right\"class=\"Arial_Narrow_12_black_bold\" width=\"50%\" > Category: </td>";
		        strValue += "<td  align=\"left\" ><span class=\"Arial_12_black_normal\">"+infoVec.elementAt(0).toString().trim()+" </span></td>";
		        strValue += "</tr>";
		        
		        strValue += "<tr align=\"left\">";
		        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \"><hr color=\"#ff722b\"></td>";
		        strValue += "</tr>";
		        
		        strValue += " <tr align=\"left\">";
		        strValue += "<td colspan=\"2\" class=\"var_12_bold_orange\">Brand Information</td>";
		        strValue += " </tr>";
			        
		        strValue += "<tr align=\"left\">";
		        strValue += "<td  align=\"right\"class=\"Arial_Narrow_12_black_bold\" width=\"50%\"> Brand Name: </td>";
		        strValue += "<td  align=\"left\" ><span class=\"Arial_12_black_normal\">"+infoVec.elementAt(1).toString().trim()+"  </span></td>";
		        strValue += "</tr>";
		        
		        strValue += "<tr align=\"left\">";
		        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \"><hr color=\"#ff722b\"></td>";
		        strValue += "</tr>";
		        strValue += " <tr align=\"left\">";
		        strValue += "<td colspan=\"2\" class=\"var_12_bold_orange\">Dealer Information</td>";
		        strValue += " </tr>";
		        
		        strValue+=strUnnamed;
		
		  if(dealerVec1.size()!=0)
			{
	        strValue += "<tr align=\"left\">";	
	        strValue += "<td colspan=\"2\" valign=\"top\">";
	        strValue += "<table align=\"center\" width=\"90%\" valign=\"top\">";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"Arial_Narrow_12_black_bold\" width=\"50%\" valign=\"top\"> Dealer Name: </td>";
	        strValue += "<td  align=\"left\" class=\"Arial_12_black_normal\"  width=\"50%\" valign=\"top\">"+dealerVec1.elementAt(1).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"Arial_Narrow_12_black_bold\" valign=\"top\" > Dealer Address: </td>";
	        strValue += "<td  align=\"left\" class=\"Arial_12_black_normal\" valign=\"top\">"+dealerVec1.elementAt(4).toString().trim()+"</td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"Arial_Narrow_12_black_bold\" valign=\"top\" > Dealer Country: </td>";
	        strValue += "<td  align=\"left\"  class=\"Arial_12_black_normal\" valign=\"top\">"+dealerVec1.elementAt(13).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"Arial_Narrow_12_black_bold\" valign=\"top\" > Dealer State: </td>";
	        strValue += "<td  align=\"left\"  class=\"Arial_12_black_normal\" valign=\"top\">"+dealerVec1.elementAt(14).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"Arial_Narrow_12_black_bold\" valign=\"top\"> Dealer City: </td>";
	        strValue += "<td  align=\"left\"  class=\"Arial_12_black_normal\" valign=\"top\">"+dealerVec1.elementAt(15).toString().trim()+" </td>";
	        strValue += "</tr>";
	        strValue += "<tr align=\"left\">";
	        strValue += "<td  align=\"right\" class=\"Arial_Narrow_12_black_bold\" valign=\"top\"> Pincode: </td>";
	        strValue += "<td  align=\"left\" class=\"Arial_12_black_normal\" valign=\"top\">"+dealerVec1.elementAt(12).toString().trim()+" </td>";
	        strValue += "</tr>";
	        
	        	
	        strValue += "</table>";
	        strValue += "</td>";
	        strValue += "</tr>";
		    
			}else{
		    strValue += " <tr align=\"left\">";
	        strValue += " <td height=\"35\" colspan=\"2\" align=\"center\" class=\"Arial_Narrow_12_black_bold\">Dealer Information is not provided.</td>";
	        strValue += " </tr>";
			}
	        
	       
		strValue+="</td>";
		strValue+="</tr>";
		if(remander==0)
		{
			strValue += "<tr style=\"display:none\" align=\"center\">";
			strValue += "<td height=\"50\" colspan=\"2\"></td>";
			strValue += "</tr>";
		}
		

		   
		strValue+="</table>";
		strValue+="</div></td>";
		  // End td for additional detail
		strValue+="<td width=\"1%\"></td>";
		strValue+="</tr>";
		  strValue+="</table>";

	        strValue += "<tr align=\"left\">";
	        strValue += "<td colspan=\"2\" class=\"formHeadingsBold \"><hr color=\"#ff722b\"></td>";
			strValue+="<tr>";
			strValue+="<td height=\"22\" colspan=\"2\" align=\"center\" valign=\"top\"><input name=\"btn_modify\" type=\"button\" class=\"buttonCopy\" value=\"Modify\" onclick=\"goModify()\">&nbsp;&nbsp;";
			strValue+="<input name=\"btn_modify\" type=\"button\" class=\"buttonCopy\" value=\"Back\" onclick=\"goBack()\"></td>";	
			strValue+="</tr>";
		  
		return strValue;
		
	}
	private String getComplaintUnnamed(HttpServletRequest request, int numDraftId, int numbrandId, int numCatId)
	{
		IndvMaster im = new IndvMaster();
		AdvtMaster am = new AdvtMaster();
		Vector unnamedVec = am.getComplaintUnnamedMap(getDataSource(request, "indv"), numbrandId, numCatId);
		//System.out.println("unnamedVec Size "+unnamedVec.size());
		//System.out.println("unnamedVec "+unnamedVec);
		Vector dataVec = im.getDraftUnnamedData(getDataSource(request, "indv"), numDraftId);
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
				if((numVisible==1) && (!strFName.equalsIgnoreCase("")) && (!dataVec.elementAt(numCount1).toString().trim().equalsIgnoreCase("")))
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
				strResult+="<td width=\"3%\" align=\"right\" valign=\"middle\" height=\"20\"></td>";
				strResult+="<td width=\"19%\" height=\"30\" align=\"right\" valign=\"middle\"class=\"Arial_Narrow_12_black_bold\">"+strFVal+"&nbsp;</td>";
				strResult+="<td height=\"30\"  align=\"left\" valign=\"middle\" class=\"Arial_12_black_normal\">"+strVal+"</td>"; 
				strResult+="<td width=\"19%\" height=\"30\" align=\"right\" valign=\"middle\"class=\"Arial_Narrow_12_black_bold\">"+strFVal1+"&nbsp;</td>";
				strResult+="<td height=\"30\"  align=\"left\" valign=\"middle\" class=\"Arial_12_black_normal\">"+strVal1+"</td>";
				strResult+="</tr>";
			}
		}
		
		return strResult;
	}
	private String getPages(int minVal, int maxVal, int numSize, String getPages)
	{
		String strResult ="<select name=\"paging\" onchange=\"retrieveIndvDraftDetailURL('complaintDetail.do?'+this.value);\">";
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

	