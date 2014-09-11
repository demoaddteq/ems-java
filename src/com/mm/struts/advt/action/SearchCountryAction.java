/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.advt.action;

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
public class SearchCountryAction extends Action {
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
	 * onchange=\"retrieveSearchStateURL('searchState.do?country='+this.value), setCountryCode();\">";
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String codeStr = (request.getParameter("country")!=null) ? (request.getParameter("country").trim()) : "0~0";
		////System.out.println("codeStr in country>>>>>>>"+codeStr);
		
		String codeStrArr[]= codeStr.split("~");
		////System.out.println("codeStrArr[0]>>>>>>>"+codeStrArr[0]);
		int numCId=Integer.parseInt(codeStrArr[0]);
		HttpSession session = request.getSession();
		ArrayList<ArrayList> FinalList = new ArrayList<ArrayList>();
		ArrayList<String> tempList = new ArrayList<String>();
		tempList.add("0");
		tempList.add("+0");
		tempList.add("Select");
		//ArrayList<String> tempList1 = new ArrayList<String>();
		//tempList1.add("-1");
		//tempList1.add("+0");
		//tempList1.add("Others");
		FinalList.add(tempList);
		//FinalList.add(tempList1);
		ArrayList countryList = (session.getAttribute("AllCountry")!=null) ? (ArrayList)session.getAttribute("AllCountry") : FinalList;
	    String strCountries = getCountriesList(numCId, countryList);
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strCountries);
	    out.flush();
		return null;
	}
	
	private String getCountriesList(int numCid, ArrayList CountryList)
	{
		String strResult ="<select name=\"country\"  tabindex=\"5\" onchange=\"retrieveSearchStateURL('searchState.do?country='+this.value), setCountryCode();\">";
		
		for(int i=0; i<CountryList.size()-1; i++)
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