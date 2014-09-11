/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.action;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.Vector;

import com.mm.core.master.FacilityMapDao;
import com.mm.core.master.RootMaster;
import com.mm.resource.APPConstant;
import com.mm.struts.corpo.form.FacilityMapForm;
import com.mm.struts.form.AddFacilityForm;

/** 
 * MyEclipse Struts
 * Creation date: 06-30-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class SaveFacilityAction extends Action {
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
	 * onchange=\"retrieveStuStateURL('studentState.do
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("\n\n in IN   SaveFacilityAction");
		String forward = "Failure";
		try
		{
			HttpSession session = request.getSession();
			DataSource dataSource = getDataSource(request, "main");
			AddFacilityForm addFacilityForm = (AddFacilityForm) form;
			
			System.out.println("62::62:::::");
			
			//int facilityId = getFacilityId(session);
			//facilityMapForm.setFacilityId(facilityId);
			FacilityMapDao facilityMapDao = new FacilityMapDao();
			//String strResult= facilityMapDao.insertFacilityMap(dataSource, facilityMapForm);  
			//insert into facility (facility_uid, builder_id, facility_name, facility_location, facility_city, facility_state, facility_country, facility_zip, company_id, active, no_flats, communityrights, date_of_creation, created_by) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
			
			System.out.println("68::68:::");
			
			String strResult = facilityMapDao.saveFacility(dataSource, addFacilityForm);
			System.out.println("line73::73:::73:::"+strResult+":");
			ActionErrors errors = new ActionErrors();
           	errors.clear();
			if(strResult.equalsIgnoreCase("SuccessfullyAdded"))
			{
				errors.add("insertFacilitySuccess", new ActionError("addFacilityForm.insertFacilitySuccess"));
				saveErrors(request, errors);
				forward = "Success";					
			}
/*
			else if(strResult.equalsIgnoreCase("UnableToAdded"))
			{
				System.out.println("line 60");
				errors.add("insertFacilityMapFailure", new ActionError("facilityMap.insertFacilityMapFailure"));
				saveErrors(request, errors);
				forward = "Failure";					
			}
*/			
			else if(strResult.equalsIgnoreCase("AlreadyExist"))
			{
				errors.add("insertFacilityAlreadyExist", new ActionError("addFacilityForm.insertFacilityAlreadyExist"));
				saveErrors(request, errors);
				forward = "Failure";					
			}
			else
			{
				errors.add("insertFacilityFailure", new ActionError("addFacilityForm.insertFacilityFailure"));
				saveErrors(request, errors);
				forward = "Failure";					
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			request.setAttribute(APPConstant.MESSAGE,
					APPConstant.FAILURE_MESSAGE);
		}
		return mapping.findForward(forward);
			
		
		
		
		
		
		
		
		
	

		
		
/*		
		String StrFacilityId = (request.getParameter("facilityId")!=null) ? (request.getParameter("facilityId").trim()) : "0";
		
		RootMaster rootMaster= new RootMaster();
		String facility_name = (request.getParameter("indCategory1")!=null) ? request.getParameter("indCategory1") : " ";
		 //////System.out.println("industryCategory......categoryAction......"+industryCategory);
		
		int numFacilityId=Integer.parseInt(StrFacilityId);
		
		Vector facilityVec = rootMaster.getFacility(getDataSource(request, "main"));
	    String strCat = getCategoryList(facility_name,facilityVec);
	    System.out.println("facility list "+strCat);
		
	    ////System.out.println("Category list "+strCat);
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strCat);
	    out.flush();
		return null;
*/
	}
	
	private String getCategoryList(String industryCategory, Vector categoryVec)
	{
		String strResult ="<select name=\"facility\"  tabindex=\"15\" onchange=\"outputSelected(this.form.companyTemplate)\">";
		
String strTemCat[] = industryCategory.split(", ");
		
		
		for(int i=0; i<categoryVec.size(); i++)
		{	
			int flag = 0;
			Vector tempVec = (Vector)categoryVec.elementAt(i);
			String numCatId = tempVec.elementAt(0).toString().trim();
			String strCName = tempVec.elementAt(1).toString().trim();
			
			for(int j =0; j<strTemCat.length; j++){
				
				if(numCatId.equalsIgnoreCase(strTemCat[j])){
					flag = 1;	
					
				}
				
			}
			
			if(flag==1)
			{
				strResult += "<option value=\"" + numCatId + "\" Selected>" + strCName + "</option>";
				
			}
			
			else
			{
				strResult += "<option value=\"" + numCatId + "\">" + strCName + "</option>";
				
			}
			
		}
		strResult += "</select>";
		return strResult;
	}
}