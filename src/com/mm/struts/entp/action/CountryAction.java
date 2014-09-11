package com.mm.struts.entp.action;


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
 * Creation date: 07-15-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */

public class CountryAction extends Action {
	RootMaster rootMaster= new RootMaster();
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
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String codeStr = (request.getParameter("country")!=null) ? (request.getParameter("country").trim()) : "0~0";
		//System.out.println("codeStr in country>>>>>>>"+codeStr);
		
		String codeStrArr[]= codeStr.split("~");
		//System.out.println("codeStrArr[0]>>>>>>>"+codeStrArr[0]);
		int numCId=Integer.parseInt(codeStrArr[0]);
		Vector CountryVec = rootMaster.getCountries(getDataSource(request,"entp"));
		//System.out.println("Countries "+CountryVec);
	    String strCountries = getCountriesList(numCId, CountryVec);
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strCountries);
	    out.flush();
		return null;
	}
	
	private String getCountriesList(int numCid, Vector CountryVec)
	{
		String strResult ="<select name=\"country\"  tabindex=\"9\" onchange=\"retrieveSURL('state.do?country='+this.value), setCountry()\">";
		
		for(int i=0; i<CountryVec.size(); i++)
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
		return strResult;
	}
}
