package com.mm.struts.entp.action;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.RootMaster;

/** 
 * MyEclipse Struts
 * Creation date: 07-31-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */

public class CountrystatecityFirstCreationAction extends Action {
	
	/*
	 * Generated Methods
	 */
	RootMaster rootMaster= new RootMaster();
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{	
		HttpSession session = request.getSession();
		String strSIdOth = (request.getParameter("strSIdOth")!=null) ? request.getParameter("strSIdOth").trim() : "";
		String strPIdOth = (request.getParameter("strPIdOth")!=null) ? request.getParameter("strPIdOth").trim() : "";
		String strCIdOth = (request.getParameter("strCIdOth")!=null) ? request.getParameter("strCIdOth").trim() : "";
		
		
		int numTempCount = (request.getParameter("numCount") != null) ? Integer.parseInt(request.getParameter("numCount").trim()) : 0;
		String codeStr = (request.getParameter("country")!=null) ? (request.getParameter("country").trim()) : "0~0";
		//System.out.println("codeStr in country>>>>>>>"+codeStr);
		int numSId = (request.getParameter("state")!=null) ? Integer.parseInt(request.getParameter("state").trim()) : 0;
		int numPId = (request.getParameter("place")!=null) ? Integer.parseInt(request.getParameter("place").trim()) : 0;
		//System.out.println("State id "+numSId);
		//System.out.println("Place id "+numPId);
		
		int tabindex = (request.getParameter("tabindex")!=null) ? Integer.parseInt(request.getParameter("tabindex").trim()) : 1;
		
		
		String codeStrArr[]= codeStr.split("~");
		////System.out.println("codeStrArr[0]>>>>>>>"+codeStrArr[0]);
		String strCountries="";
		String strState = "";
		String strPlace = "";
		
		if(!codeStrArr[0].equalsIgnoreCase("-1"))
		{
			int numCId=Integer.parseInt(codeStrArr[0]);
			
			/**
			 * Country Start Here
			 */
			ArrayList<ArrayList> FinalList = new ArrayList<ArrayList>();
			ArrayList<String> tempList = new ArrayList<String>();
			tempList.add("0");
			tempList.add("+0");
			tempList.add("Select");
			ArrayList<String> tempList1 = new ArrayList<String>();
			tempList1.add("-1");
			tempList1.add("+0");
			tempList1.add("Others");
			FinalList.add(tempList);
			FinalList.add(tempList1);
			ArrayList countryList = (session.getAttribute("AllCountry")!=null) ? (ArrayList)session.getAttribute("AllCountry") : FinalList;
			strCountries = getCountriesList(numCId, countryList, numSId, numPId, tabindex, strCIdOth);
			if(numCId==0)
			{
				strState=getOtherState("0", tabindex, strSIdOth);
				strPlace=getOtherPlace("0", tabindex, strPIdOth);
			}
			else
			{
				if(numSId==-1)
		    	{
		    		strState=getOtherState(String.valueOf(numSId), tabindex, strSIdOth);
		    		strPlace=getOtherPlace("0", tabindex, strPIdOth);
		    	}
				else
				{
					/**
				     * State Start Here
				     */
					HashMap stateList = (HashMap)session.getAttribute("AllStates");
				    strState = getStateList(numSId, numCId, stateList, numPId, tabindex, strSIdOth);
				    if(numSId==0)
				    {
				    	strPlace=getOtherPlace("0", tabindex, strPIdOth);
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
								strPlace=getOtherPlace(String.valueOf(numPId), tabindex, strPIdOth);
							}
							else
							{
								HashMap citiesList = (HashMap)session.getAttribute("AllCities");
								strPlace = getPlaceList(numPId, citiesList, numSId, numCId, tabindex, strPIdOth);
							}
								
						}
				    }
				}
			}
		}
		else
		{
			strCountries=getOtherCountry("-1~0", tabindex, strCIdOth);
			strState=getOtherState("-1", tabindex, strSIdOth);
			strPlace=getOtherPlace("-1", tabindex, strPIdOth);
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
		String strResult="<table width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">";
		strResult+="<tr height=\"20\">";
		strResult+="<td width=\"10%\">&nbsp;</td>";
		strResult+="<td width=\"25%\" align=\"right\" class=\"bold\">* Country&nbsp;</td>";
		strResult+="<td width=\"40%\">&nbsp;"+strCountries+"</td>";
		if(strCountries.indexOf("<input type=\"text\" ")<= -1)
		{
			strResult+="<td width=\"25%\">&nbsp;</td>";
		}
		else
		{
			strResult+="<td  width=\"25%\" class=\"bold\" style=\"cursor:hand\" onclick=\"retrieveCountryStateCity('countrystatecityFirstCreation.do?place=0&state=0&country=94~+91');\"><b><u><font color=\"blue\">Switch Back To List Mode</font></u></b></td>";
		}		
		strResult+="</tr>";
		strResult+="<tr height=\"20\">";
		strResult+="<td>&nbsp;</td>";
		strResult+="<td align=\"right\" class=\"bold\">* State&nbsp;</td>";
		strResult+="<td>&nbsp;"+strState+"</td>";
		if(strState.indexOf("<input type=\"text\" ")<= -1)
		{
			strResult+="<td >&nbsp;</td>";
		}
		else
		{
			strResult+="<td  class=\"bold\" style=\"cursor:hand\" onclick=\"retrieveCountryStateCity('countrystatecityFirstCreation.do?place=0&state=0&country=94~+91');\"><b><u><font color=\"blue\">Switch Back To List Mode</font></u></b></td>";
		}
		strResult+="</tr>";
		strResult+="<tr height=\"20\">";
		strResult+="<td>&nbsp;</td>";
		strResult+="<td align=\"right\" class=\"bold\">* City&nbsp;</td>";
		strResult+="<td>&nbsp;"+strPlace+"</td>";
		strResult+="<td>&nbsp;</td>";
		
		strResult+="</tr>";
		strResult+="</table>";
		numTempCount = numTempCount+1;
		int numtotal = numTempCount%2;
		if(numtotal !=0)
		{
		
		}
		return strResult;
	}
	private String getCountriesList(int numCid, ArrayList CountryList, int numSId, int numPId, int tabindex, String strCIdOth)
	{
		String strResult="";
		if(CountryList.size()>2)
		{
			strResult ="<select name=\"country\"  tabindex=\""+tabindex+"\" onchange=\"retrieveCountryStateCity('countrystatecityFirstCreation.do?place="+numPId+"&state="+numSId+"&country='+this.value), setCountry()\">";
			for(int i=0; i<CountryList.size(); i++)
			{
				ArrayList tempList = (ArrayList)CountryList.get(i);
				int numCIdTemp=Integer.parseInt(tempList.get(0).toString().trim());
				String strCCode = tempList.get(1).toString().trim();
				String strCName = tempList.get(2).toString().trim();
				String strValue = numCIdTemp+"~"+strCCode;
				////System.out.println("strValue in country>>>>>>"+strValue);
				////System.out.println("numCountryId "+numCountryId);
				////System.out.println("strCName "+strCName);
				////System.out.println("numCIdTemp>>>>>>>>>>>"+numCIdTemp);
				////System.out.println("numCid>>>>>>>>>>>"+numCid);
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
			strResult = getOtherCountry(String.valueOf(numCid), tabindex, strCIdOth);
		}
		return strResult;
	}
	private String getOtherCountry(String strCVal,int  tabindex, String strCIdOth)
	{
		String strResult = "<input type=\"text\" name=\"otherCountry\" id=\"otherCountry\" tabindex=\""+tabindex+"\" value=\""+strCIdOth+"\" ><input type=\"hidden\" name=\"country\" id=\"country\"  value=\"-1\">";
		return strResult;
	}
	private String getStateList(int numSid, int numCId, HashMap stateList, int numPId, int tabindex,String strSIdOth)
	{
		ArrayList AllStates = (ArrayList)stateList.get(numCId);
		String strResult = "";
		if(AllStates.size()>2)
		{
			strResult ="<select name=\"state\"  tabindex=\""+tabindex+"\" onchange=\"retrieveCountryStateCity('countrystatecityFirstCreation.do?country="+numCId+"&place="+numPId+"&state='+this.value);\">";
			
			for(int i=0; i<AllStates.size(); i++)
			{
				ArrayList StatesArr = (ArrayList)AllStates.get(i);
				int numStateId = Integer.parseInt(StatesArr.get(0).toString().trim());
				String strSName = StatesArr.get(1).toString().trim();
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
			strResult=getOtherState(String.valueOf(numSid), tabindex, strSIdOth);
		}
		return strResult;
	}
	private String getOtherState(String strSVal, int tabindex, String strSIdOth)
	{
		String strResult = "<input type=\"text\" name=\"otherState\" id=\"otherState\" tabindex=\""+tabindex+"\" value=\""+strSIdOth+"\" ><input type=\"hidden\" name=\"state\" id=\"state\"  value=\"-1\">";
		return strResult;
	}
	private String getPlaceList(int numPId, HashMap citiesList, int numSId, int numCId, int tabindex, String strPIdOth)
	{
		ArrayList AllCities = (ArrayList)citiesList.get(numSId);
		String strResult ="";
		if(AllCities.size()>2)
		{
			strResult ="<select name=\"city\"   tabindex=\""+tabindex+"\"  onchange=\"retrieveCountryStateCity('countrystatecityFirstCreation.do?country="+numCId+"&state="+numSId+"&place='+this.value);\">";
			
			for(int i=0; i<AllCities.size(); i++)
			{
				ArrayList CitiesArr = (ArrayList)AllCities.get(i);
				int numPlaceId = Integer.parseInt(CitiesArr.get(0).toString().trim());
				String strPName = CitiesArr.get(1).toString().trim();
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
			strResult = getOtherPlace(String.valueOf(numPId), tabindex, strPIdOth);
		}
		return strResult;
	}
	private String getOtherPlace(String strPVal, int tabindex, String strPIdOth)
	{
		String strResult = "<input type=\"text\" name=\"otherCity\" id=\"otherCity\" tabindex=\""+tabindex+"\" value=\""+strPIdOth+"\" ><input type=\"hidden\" name=\"city\" id=\"city\"  value=\"-1\">";
		return strResult;
	}

}
