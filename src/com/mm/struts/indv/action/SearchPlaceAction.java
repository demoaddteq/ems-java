/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.indv.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.RootMaster;

/** 
 * MyEclipse Struts
 * Creation date: 08-02-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class SearchPlaceAction extends Action {
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
		RootMaster rootMaster= new RootMaster();
		int numTempCount = (request.getParameter("numCount") != null) ? Integer.parseInt(request.getParameter("numCount").trim()) : 0;
		String codeStr = (request.getParameter("country")!=null) ? (request.getParameter("country").trim()) : "0~0";
		System.out.println("codeStr in country>>>>>>>"+codeStr);
		int numSId = (request.getParameter("state")!=null) ? Integer.parseInt(request.getParameter("state").trim()) : 0;
		int numPId = (request.getParameter("place")!=null) ? Integer.parseInt(request.getParameter("place").trim()) : 0;
		System.out.println("State id "+numSId);
		System.out.println("Place id "+numPId);
		String codeStrArr[]= codeStr.split("~");
		//System.out.println("codeStrArr[0]>>>>>>>"+codeStrArr[0]);
		String strCountries="";
		String strState = "";
		String strPlace = "";
		
		if(!codeStrArr[0].equalsIgnoreCase("-1"))
		{
			int numCId=Integer.parseInt(codeStrArr[0]);
			
			/**
			 * Country Start Here
			 */
			Vector CountryVec = rootMaster.getCountries(getDataSource(request,"indv"));
			//System.out.println("Countries "+CountryVec);
			strCountries = getCountriesList(numCId, CountryVec, numSId, numPId);
			if(numCId==0)
			{
				strState=getOtherState("0");
				strPlace=getOtherPlace("0");
			}
			else
			{
				if(numSId==-1)
		    	{
		    		strState=getOtherState(String.valueOf(numSId));
		    		strPlace=getOtherPlace("0");
		    	}
				else
				{
					/**
				     * State Start Here
				     */
				    Vector StateVec = rootMaster.getState(numCId, getDataSource(request, "indv"));
				    strState = getStateList(numSId, numCId, StateVec, numPId);
				    if(numSId==0)
				    {
				    	strPlace=getOtherPlace("0");
				    }
				    else
				    {
				    	/**
					     * Places/City Start here
					     */
					    
						if(numSId>0)
						{				
							if(numPId==-1)
							{
								strPlace=getOtherPlace("0");
							}
							else
							{
								Vector placeVec = rootMaster.getPlace(numCId, numSId, getDataSource(request, "indv"));
								strPlace = getPlaceList(numPId, placeVec, numSId, numCId);
							}
								
						}
				    }
				}
			}
		}
		else
		{
			strCountries=getOtherCountry("-1~0");
			strState=getOtherState("-1");
			strPlace=getOtherPlace("-1");
		}
		
		String strTbl = getTableRow(strCountries, strState, strPlace, numTempCount);
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strTbl);
	    out.flush();
		return null;
	}
	private String getTableRow(String strCountries, String strState, String strPlace, int numTempCount)
	{
		String strResult="<TR>";
		strResult  += "<TD vAlign=\"top\" bgColor=\"#ffffff\" height=\"31\">";
		strResult  += "<TABLE borderColor=\"#fefbfb\" width=\"778\" bgColor=\"#ffffff\">";
		strResult  += "<TBODY>";
		strResult  += "<TR>";
		strResult  += "<TD width=\"181\" bgColor=\"#ffffff\" height=\"30\">&nbsp;</TD>";
		strResult  += "<TD width=\"400\" bgColor=\"#fefbfb\" height=\"30\">";
		strResult  += "<TABLE cellSpacing=\"0\" cellPadding=\"0\" width=\"398\" align=\"center\" bgColor=\"#fefbfb\" border=\"0\">";
		strResult  += "<TBODY>";
		strResult  += "<TR>";
		strResult  += "<TD width=\"396\">";
		strResult  += "<TABLE cellSpacing=\"0\" cellPadding=\"0\" width=\"396\" align=\"center\" bgColor=\"#ff9966\" border=\"0\">";
		strResult  += "<TBODY>";
		strResult  += "<TR>";
		strResult  += "<TD background=\"\"></TD></TR>";
		strResult  += "<TR>";
		strResult  += "<TD>";
		strResult  += "<TABLE cellSpacing=\"0\" cellPadding=\"0\" width=\"100%\" border=\"0\">";
		strResult  += " <TBODY>";
		strResult  += "<TR>";
		strResult  += "<TD width=\"5%\" bgColor=\"#ffffff\">";
		strResult  += "<DIV align=\"left\"></DIV></TD>";
		strResult  += "<TD class=\"ms_sans_serif\" width=\"26%\" bgColor=\"#ffffff\" height=\"30\">";
		strResult  += "<DIV class=\"Arial_Narrow_12_black_bold\" align=\"right\">Country</DIV></TD>";
		strResult  += "<TD width=\"3%\" bgColor=\"#ffffff\">&nbsp;</TD>";
		strResult  += "<TD vAlign=\"middle\" align=\"left\" width=\"66%\"  bgColor=\"#ffffff\" height=\"25\">"+strCountries+"</TD></TR></TBODY></TABLE></TD></TR>";
		strResult  += "<TR>";
		strResult  += "<TD background=\"\"></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>";
		strResult  += "<TD width=\"181\" bgColor=\"#ffffff\" height=\"25\">&nbsp;</TD></TR></TBODY></TABLE></TD></TR>";
		
		
		
		strResult  += "<TR>";
		strResult  += "<TD vAlign=\"top\" bgColor=\"#ffffff\" height=\"31\">";
		strResult  += "<TABLE borderColor=\"#fefbfb\" width=\"778\" bgColor=\"#ffffff\">";
		strResult  += "<TBODY>";
		strResult  += "<TR>";
		strResult  += "<TD width=\"181\" bgColor=\"#ffffff\" height=\"30\">&nbsp;</TD>";
		strResult  += "<TD width=\"400\" bgColor=\"#fefbfb\" height=\"30\">";
		strResult  += "<TABLE cellSpacing=\"0\" cellPadding=\"0\" width=\"398\" align=\"center\" bgColor=\"#fefbfb\" border=\"0\">";
		strResult  += "<TBODY>";
		strResult  += "<TR>";
		strResult  += "<TD width=\"396\">";
		strResult  += "<TABLE cellSpacing=\"0\" cellPadding=\"0\" width=\"396\" align=\"center\" bgColor=\"#ff9966\" border=\"0\">";
		strResult  += " <TBODY>";
		strResult  += "<TR>";
		strResult  += "<TD background=\"\"></TD></TR>";
		strResult  += "<TR>";
		strResult  += "<TD>";
		strResult  += "<TABLE cellSpacing=\"0\" cellPadding=\"0\" width=\"100%\" border=\"0\">";
		strResult  += " <TBODY>";
		strResult  += " <TR>";
		strResult  += "<TD width=\"5%\" bgColor=\"#ffffff\">";
		strResult  += "<DIV align=\"left\"></DIV></TD>";
		strResult  += "<TD class=\"ms_sans_serif\" width=\"26%\" bgColor=\"#ffffff\" height=\"30\">";
		strResult  += "<DIV class=\"Arial_Narrow_12_black_bold\" align=\"right\">State</DIV></TD>";
		strResult  += "<TD width=\"3%\" bgColor=\"#ffffff\">&nbsp;</TD>";
		strResult  += "<TD vAlign=\"middle\" align=\"left\" width=\"66%\"  bgColor=\"#ffffff\" height=\"25\">"+strState+"</TD></TR></TBODY></TABLE></TD></TR>";
		strResult  += "<TR>";
		strResult  += "<TD background=\"\"></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>";
		strResult  += "<TD width=\"181\" bgColor=\"#ffffff\" height=\"25\">&nbsp;</TD></TR></TBODY></TABLE></TD></TR>";
		
		
		strResult  += "<TR>";
		strResult  += "<TD vAlign=\"top\" bgColor=\"#ffffff\" height=\"31\">";
		strResult  += "<TABLE borderColor=\"#fefbfb\" width=\"778\" bgColor=\"#ffffff\">";
		strResult  += "<TBODY>";
		strResult  += "<TR>";
		strResult  += "<TD width=\"181\" bgColor=\"#ffffff\" height=\"30\">&nbsp;</TD>";
		strResult  += "<TD width=\"400\" bgColor=\"#fefbfb\" height=\"30\">";
		strResult  += "<TABLE cellSpacing=\"0\" cellPadding=\"0\" width=\"398\" align=\"center\" bgColor=\"#fefbfb\" border=\"0\">";
		strResult  += "<TBODY>";
		strResult  += "<TR>";
		strResult  += "<TD width=\"396\">";
		strResult  += "<TABLE cellSpacing=\"0\" cellPadding=\"0\" width=\"396\" align=\"center\" bgColor=\"#ff9966\" border=\"0\">";
		strResult  += "<TBODY>";
		strResult  += "<TR>";
		strResult  += "<TD background=\"\"></TD></TR>";
		strResult  += "<TR>";
		strResult  += "<TD>";
		strResult  += "<TABLE cellSpacing=\"0\" cellPadding=\"0\" width=\"100%\" border=\"0\">";
		strResult  += " <TBODY>";
		strResult  += "<TR>";
		strResult  += "<TD width=\"5%\" bgColor=\"#ffffff\">";
		strResult  += "<DIV align=\"left\"></DIV></TD>";
		strResult  += "<TD class=\"ms_sans_serif\" width=\"26%\" bgColor=\"#ffffff\" height=\"30\">";
		strResult  += "<DIV class=\"Arial_Narrow_12_black_bold\" align=\"right\">City</DIV></TD>";
		strResult  += "<TD width=\"3%\" bgColor=\"#ffffff\">&nbsp;</TD>";
		strResult  += "<TD vAlign=\"middle\" align=\"left\" width=\"66%\"  bgColor=\"#ffffff\" height=\"25\">"+strPlace+"</TD></TR></TBODY></TABLE></TD></TR>";
		strResult  += "<TR>";
		strResult  += "<TD background=\"\"></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD>";
		strResult  += "<TD width=\"181\" bgColor=\"#ffffff\" height=\"25\">&nbsp;</TD></TR></TBODY></TABLE></TD></TR>";
		numTempCount = numTempCount+1;
		int numtotal = numTempCount%2;
		if(numtotal !=0)
		{
		
		}
		return strResult;
	}
	private String getCountriesList(int numCid, Vector CountryVec, int numSId, int numPId)
	{
		String strResult="";
		if(CountryVec.size()>2)
		{
			strResult ="<select name=\"country\" class=\"input_multi_txt_box\"  onchange=\"retrieveCountryStateCity('searchPlace.do?place="+numPId+"&state="+numSId+"&country='+this.value), setCountryCode()\">";
			for(int i=0; i<CountryVec.size()-1; i++)
			{
				Vector tempVec = (Vector)CountryVec.elementAt(i);
				//String strCountryId = tempVec.elementAt(0).toString().trim();
				//String codeStrArr[]= strCountryId.split("~");
				int numCIdTemp=Integer.parseInt(tempVec.elementAt(0).toString().trim());
				String strCCode = tempVec.elementAt(1).toString().trim();
				String strCName = tempVec.elementAt(2).toString().trim();
				String strValue = numCIdTemp+"~"+strCCode;
				//System.out.println("strValue in country>>>>>>"+strValue);
				//System.out.println("numCountryId "+numCountryId);
				//System.out.println("strCName "+strCName);
				//System.out.println("numCIdTemp>>>>>>>>>>>"+numCIdTemp);
				//System.out.println("numCid>>>>>>>>>>>"+numCid);
				if(numCid == numCIdTemp)
				{
					strResult += "<option value=\"" + strValue + "\" Selected>" + strCName + "</option>";
				}
				else
				{
					strResult += "<option value=\"" + strValue + "\">" + strCName + "</option>";
				}
			}
			strResult += "</select>";
		}
		else
		{
			strResult = getOtherCountry(String.valueOf(numCid));
		}
		return strResult;
	}
	private String getOtherCountry(String strCVal)
	{
		String  strResult ="<select name=\"country\" class=\"input_multi_txt_box\" >";
		strResult += "<option value=\"0\" Selected>Select</option>";
		strResult += "</select>";
		return strResult;
	}
	private String getStateList(int numSid, int numCId, Vector StateVec, int numPId)
	{
		String strResult = "";
		if(StateVec.size()>2)
		{
			strResult ="<select name=\"state\" class=\"input_multi_txt_box\"  onchange=\"retrieveCountryStateCity('searchPlace.do?country="+numCId+"&place="+numPId+"&state='+this.value);\">";
			
			for(int i=0; i<StateVec.size()-1; i++)
			{
				Vector tempVec = (Vector)StateVec.elementAt(i);
				int numStateId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
				String strSName = tempVec.elementAt(1).toString().trim();
				//System.out.println("numStateId "+numStateId);
				//System.out.println("strSName "+strSName);
				if(numSid == numStateId)
				{
					strResult += "<option value=\"" + numStateId + "\" Selected>" + strSName + "</option>";
				}
				else
				{
					strResult += "<option value=\"" + numStateId + "\">" + strSName + "</option>";
				}
			}
			strResult += "</select>";
		}
		else
		{
			strResult=getOtherState(String.valueOf(numSid));
		}
		return strResult;
	}
	private String getOtherState(String strSVal)
	{
		String  strResult ="<select name=\"state\" class=\"input_multi_txt_box\" >";
		strResult += "<option value=\"0\" Selected>Select</option>";
		strResult += "</select>";
		return strResult;
	}
	private String getPlaceList(int numPId, Vector placeVec, int numSId, int numCId)
	{
		String strResult ="";
		if(placeVec.size()>2)
		{
			strResult ="<select name=\"city\" class=\"input_multi_txt_box\" onchange=\"retrieveCountryStateCity('searchPlace.do?country="+numCId+"&state="+numSId+"&place='+this.value);\">";
			
			for(int i=0; i<placeVec.size()-1; i++)
			{
				Vector tempVec = (Vector)placeVec.elementAt(i);
				int numPlaceId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
				String strPName = tempVec.elementAt(1).toString().trim();
				//System.out.println("numPlaceId "+numPlaceId);
				//System.out.println("strPName "+strPName);
				if(numPId == numPlaceId)
				{
					strResult += "<option value=\"" + numPlaceId + "\" Selected>" + strPName + "</option>";
				}
				else
				{
					strResult += "<option value=\"" + numPlaceId + "\">" + strPName + "</option>";
				}
			}
			strResult += "</select>";
		}
		else
		{
			strResult = getOtherPlace(String.valueOf(numPId));
		}
		return strResult;
	}
	private String getOtherPlace(String strPVal)
	{
		String  strResult ="<select name=\"city\" class=\"input_multi_txt_box\" >";
		strResult += "<option value=\"0\" Selected>Select</option>";
		strResult += "</select>";
		return strResult;
	}

}
