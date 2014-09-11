package com.mm.struts.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.FacilityMapDao;
import com.mm.core.master.RootMaster;
public class AddFacilityAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		System.out.println("\n\n\n In AddFacilityAction");
		//RootMaster rootMaster=new RootMaster();
		FacilityMapDao fmDAO = new FacilityMapDao();
		ArrayList arrBuilder = fmDAO.getBuilderName(getDataSource(request, "main"));
		String strDDLBuilder = "<select name=\"builder\">";
		if((arrBuilder != null) && (arrBuilder.size()>0))
		{
			strDDLBuilder += "<option value='0'>--Select--</option>";
			for(int i=0 ; i<arrBuilder.size() ; i++)
			{
				ArrayList arrBuilder_Id_Name = (ArrayList)arrBuilder.get(i);
				String strBuilderId = (String) arrBuilder_Id_Name.get(0);
				String strBuilderName = (String) arrBuilder_Id_Name.get(1);
				strDDLBuilder += "<option value=\""+strBuilderId+"\">"+strBuilderName+"</option>";
			}
		}
		strDDLBuilder += "</select>";
		
		
		ArrayList arrCountry = fmDAO.getCountry(getDataSource(request, "main"));
		//<select name=\"collegeCountry\" tabindex=\"19\" onchange=\"retrieveSURL('state.do?collcountry='+this.value), showOth();\">";
		String strDDLCountry = "<select name=\"collegeCountry\" onchange=\"retrieveSURL('state.do?collcountry='+this.value), showOth();\" >";
		if((arrCountry != null) && (arrCountry.size()>0))
		{
			strDDLCountry += "<option value='0'>--Select--</option>";
			for(int i=0 ; i<arrCountry.size() ; i++)
			{
				ArrayList arrCountry_Id_Name = (ArrayList)arrCountry.get(i);
				String strCountryId = (String) arrCountry_Id_Name.get(0);
				String strCountryName = (String) arrCountry_Id_Name.get(1);
				strDDLCountry += "<option value=\""+strCountryId+"\">"+strCountryName+"</option>";
			}
		}
		strDDLCountry += "</select>";
		
		
		String strReturnResult = strDDLBuilder +"~"+ strDDLCountry; 
		System.out.println("strReturnResult::::=>"+strReturnResult);
	    //String strStream = getBuilderNameList(strDDLBuilder);
	    
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strReturnResult);
	    out.flush();
		return null;
	}
	
	private String getBuilderNameList(Vector streamVec)
	{
		String strBuilderDDL = "<select name=\"builder\">";
		try
		{
			for(int i=0; i<streamVec.size(); i++)
			{
				Vector tempVec = (Vector)streamVec.elementAt(i);
				int numStateId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
				String strSName = tempVec.elementAt(1).toString().trim();
				strBuilderDDL += "<option value=\"" + numStateId + "\">" + numStateId +"-"+ strSName + "</option>";
			}
		}
		catch(Exception ex)
		{
			System.out.println("line57:"+ex);
		}
		strBuilderDDL = "</select>";
		return strBuilderDDL;
	}
}
