/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

import com.mm.core.master.RootMaster;

/** 
 * MyEclipse Struts
 * Creation date: 06-30-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class CollRegisCountryAction extends Action {
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
		String codeStr = (request.getParameter("collcountry")!=null) ? (request.getParameter("collcountry").trim()) : "94~+91";
		HttpSession session = request.getSession();
		int tabindex = 5;
		
		System.out.println("CollRegisCountryAction....");
			
		String codeStrArr[]= codeStr.split("~");
		int numCId=Integer.parseInt(codeStrArr[0]);
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
		
		 String strCountries = getCountriesList(numCId, countryList, tabindex);
	    
	    
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strCountries);
	    out.flush();
		return null;
	}
	
	
	private String getCountriesList(int numCid, ArrayList CountryList, int tabindex)
	{       //String strResult="";
			//strResult ="<select name=\"country\" class=\"input_multi_txt_box\" tabindex=\""+tabindex+"\" onchange=\"retrieveCollStateFURL('collRegisState.do?collcountry='+this.value), showOth();\">";
			String strResult ="<select name=\"compCountry\"  tabindex=\""+tabindex+"\" onchange=\"retrieveCollStateFURL('collRegisState.do?collcountry='+this.value), showOth();\">";
			System.out.println("strResult=>On 03092009 For Test Purpose=>"+strResult);
			for(int i=0; i<CountryList.size(); i++)
			{
				ArrayList tempList = (ArrayList)CountryList.get(i);
				int numCIdTemp=Integer.parseInt(tempList.get(0).toString().trim());
				String strCCode = tempList.get(1).toString().trim();
				String strCName = tempList.get(2).toString().trim();
				String strValue = numCIdTemp+"~"+strCCode;
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