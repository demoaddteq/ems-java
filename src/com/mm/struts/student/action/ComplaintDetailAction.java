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
		    	int totalRow = indvMaster.getDraftsCount(getDataSource(request, "student"),paramVec1);
				System.out.println("totalRow Pages "+totalRow);
		    	String strPageHtml = getPages(minVal, maxVal, totalRow, strPgType);
				//////System.out.println("Pages "+strValue);
		    	Vector<String> paramVec2 = new Vector<String>();
		    	paramVec2.add(numDraftId);
		    	paramVec2.add(userid);
		    	
		    	
		    	Vector draftVec = indvMaster.getDraftsDetails(getDataSource(request, "student"),paramVec2);
		    	
		    	System.out.println("advt id "+draftVec.size());
		    	////System.out.println("advt id "+draftVec.elementAt(14).toString());
		    	System.out.println("advt id "+draftVec);
		    	
		    	int advtid = Integer.parseInt(draftVec.elementAt(14).toString());
		    	Vector comp_Vec =new Vector();
		    	String comp_name = "";
		    	String comp_type = "";
		    	
				if(advtid<=0)
				{
					comp_name = draftVec.elementAt(13).toString()+"(Other)";
				}
				else
				{
					comp_Vec = rootMaster.getCompanyDetail(getDataSource(request, "student"), advtid);
					comp_name=comp_Vec.elementAt(1).toString();
				}
		//		comp_type=comp_Vec.elementAt(15).toString();
					
				Vector catNamevec = new Vector(); 
				String catName="";
				int catid=Integer.parseInt(draftVec.elementAt(4).toString());
				if(comp_type.equalsIgnoreCase("Advertiser"))
				{
					catName=rootMaster.getStrCollegeCategoryName(getDataSource(request, "student"), catid);
				}else
				{
					if (Integer.parseInt(draftVec.elementAt(4).toString())!=0){
						////System.out.println("catidcatidcatid in  side ist if comdeteres Action"+catid);
					catNamevec  = rootMaster.getCategoryName(getDataSource(request, "student"),catid);
					////System.out.println("catNameveccatNameveccatNamevec in comdeteres Action"+catNamevec);
					if(Integer.parseInt(catNamevec.elementAt(1).toString().trim())==0){
						catName=catNamevec.elementAt(0).toString().trim()+"(Other)";
						////System.out.println("catidcatidcatid if in  side ist if comdeteres Action"+catid);
					}else
					{
						catName=catNamevec.elementAt(0).toString().trim();
					}
					}
					else
					{
						catName="Consultant";
						////System.out.println("catidcatidcatid  in  side ist else comdeteres Action"+catName);
					}
					
				}
				
				
				//////System.out.println("cat Name "+catName);    							
		    	int dealerid = Integer.parseInt(draftVec.elementAt(12).toString());
		    	Vector dealerVec1 = new Vector();
		    	if(dealerid!=0)
				{	
					dealerVec1 = indvMaster.getDealerDetail(getDataSource(request, "student"),dealerid);
					int contryId = Integer.parseInt(dealerVec1.elementAt(5).toString());//dealer contryId id
					String dealercontry = rootMaster.getCountryName(getDataSource(request, "student"),contryId);
					int stateId = Integer.parseInt(dealerVec1.elementAt(6).toString());//dealer stateId id
					String dealerstate = rootMaster.getStateName(getDataSource(request, "student"),stateId);
					int cityId = Integer.parseInt(dealerVec1.elementAt(8).toString());//dealer cityId id
					String dealercity = rootMaster.getPlaceName(getDataSource(request, "student"),cityId);
							
					dealerVec1.add(dealercontry); 
					dealerVec1.add(dealerstate); 
					dealerVec1.add(dealercity); 
				
				}
		    	////System.out.println("dealerVec1>>>>>>>>>>>>>>>"+dealerVec1);
				Vector <String> infoVec = new Vector<String>();
				infoVec.add(catName);//0//category name
				infoVec.add(comp_name);//1//Brand name
				infoVec.add(comp_type);//1//Brand type
				//////System.out.println("infoVec "+infoVec);
				
				//////System.out.println("infoVec..............."+infoVec);
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
		String strValue = "<div class=\"top_panel\">";
		strValue += "<div class=\"Page_Heading\">";	
		strValue += "<div class=\"Page_Heading_Text\" style=\"width: 70%;\">Draft Details</div></div>";
	
		if(draftVec.size()==0)
		{
			strValue += "<div class=\"Fields_Container\">";	
			strValue += "<div class=\"Fields_Container_Inside\">";
			strValue += "<div class=\"Fields_Row_2a\">";
			strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 20%;\" text-align: right;\"> No Complaints found...</div>";
			strValue += "<div class=\"Row_2_Fields_Col_2\" style=\"width: 20%;\" text-align: right;\" </div></div></div></div>";
				
		}
		strValue += "<div class=\"Fields_Container\">";	
		strValue += "<div class=\"Fields_Container_Inside\">";

		strValue += "<div class=\"Fields_Row_1\">";
		strValue += "<div class=\"Fields_Col_1a\">Draft Title:</div>";
		strValue += "<div class=\"Fields_Col_1b\">"+draftVec.elementAt(1).toString().trim()+"</div></div>";
	        
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
					
					
				}
		        
		        strValue += "<tr align=\"left\">";
		        String ctype="";
		        if(infoVec.elementAt(2).toString().equalsIgnoreCase("Advertiser"))
		        {
		        	ctype="College";
		        }else
		        {
		        	ctype="Company";
		        }
		        
		        strValue += "<div class=\"Fields_Row_1\">";
				strValue += "<div class=\"Fields_Col_1a\">Draft Text:</div>";
				strValue += "<div class=\"Fields_Col_1b\">"+strFMsgcompl+"</div></div>";
			
			    strValue += "<div class=\"Fields_Row_1\">";
				strValue += "<div class=\"Fields_Col_1a\">Category:</div>";
				strValue += "<div class=\"Fields_Col_1b\">"+infoVec.elementAt(0).toString().trim()+"</div></div>";
			     
			    strValue += "<div class=\"Fields_Row_1\">";
				strValue += "<div class=\"Fields_Col_1a\"></div>";
				strValue += "<div class=\"Fields_Col_1b\">"+" "+"</div></div>";
		        
		        strValue+=strUnnamed;
		
		if(remander==0)
		{
			strValue += "<tr style=\"display:none\" align=\"center\">";
			strValue += "<td height=\"50\" colspan=\"2\"></td>";
			strValue += "</tr>";
		}
		
	strValue+="</div></div>";
		  // End td for additional detail
			strValue+="<tr>";
			strValue+="<td height=\"22\" align=\"center\" valign=\"top\"><input name=\"btn_modify\" type=\"button\"  value=\"Modify\" onclick=\"goModify()\">";
			strValue+="<input name=\"btn_modify\" type=\"button\"  value=\"Back\" onclick=\"goBack()\"></td>";	
			strValue+="</tr>";
			strValue+="</div>";	  
		return strValue;
		
	}
	private String getComplaintUnnamed(HttpServletRequest request, int numDraftId, int numbrandId, int numCatId)
	{
		IndvMaster im = new IndvMaster();
		AdvtMaster am = new AdvtMaster();
		Vector unnamedVec = am.getComplaintUnnamedMap(getDataSource(request, "student"), numbrandId, numCatId);
		//////System.out.println("unnamedVec Size "+unnamedVec.size());
		//////System.out.println("unnamedVec "+unnamedVec);
		Vector dataVec = im.getDraftUnnamedData(getDataSource(request, "student"), numDraftId);
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
				if((numVisible==1) && (!strFName.equalsIgnoreCase("")) && (!dataVec.elementAt(numCount1).toString().trim().equalsIgnoreCase("")))
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
				strResult+="<td width=\"3%\" align=\"right\" valign=\"middle\" height=\"20\"></td>";
				strResult+="<td width=\"19%\" height=\"30\" align=\"right\" valign=\"middle\"class=\"bold\">"+strFVal+"&nbsp;</td>";
				strResult+="<td height=\"30\"  align=\"left\" valign=\"middle\" >"+strVal+"</td>"; 
				strResult+="<td width=\"19%\" height=\"30\" align=\"right\" valign=\"middle\"class=\"bold\">"+strFVal1+"&nbsp;</td>";
				strResult+="<td height=\"30\"  align=\"left\" valign=\"middle\" >"+strVal1+"</td>";
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

	