package com.mm.struts.corpo.action;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

import com.mm.resource.APPConstant;
import com.mm.struts.corpo.form.FacilityMapForm;
import com.mm.struts.corpo.form.ServiceForm;
import com.mm.core.master.FacilityMapDao;
import com.mm.core.master.ServiceDao;
import com.mm.core.resource.LoginHelper;

public class FacilityMapAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("\n\n in IN   FacilityMapAction");
		String forward = "Failure";
		try
		{
			HttpSession session = request.getSession();
			DataSource dataSource = getDataSource(request, "corpo");
			FacilityMapForm facilityMapForm = (FacilityMapForm) form;
			int facilityId = Integer.parseInt(session.getAttribute("fId").toString());//getFacilityId(session);
			
			facilityMapForm.setFacilityId(facilityId);
			FacilityMapDao facilityMapDao = new FacilityMapDao();
			String strResult= facilityMapDao.insertFacilityMap(dataSource, facilityMapForm);

			ActionErrors errors = new ActionErrors();
           	errors.clear();
			if(strResult.equalsIgnoreCase("SuccessfullyAdded"))
			{
				errors.add("insertFacilityMapSuccess", new ActionError("facilityMap.insertFacilityMapSuccess"));
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
				errors.add("insertFacilityMapAlreadyExist", new ActionError("facilityMap.insertFacilityMapAlreadyExist"));
				saveErrors(request, errors);
				forward = "Failure";					
			}
			else
			{
				errors.add("insertFacilityMapFailure", new ActionError("facilityMap.insertFacilityMapFailure"));
				saveErrors(request, errors);
				forward = "Failure";					
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
			request.setAttribute(APPConstant.MESSAGE,
					APPConstant.FAILURE_MESSAGE);
		}
		return mapping.findForward(forward);
	}
	
	public int getFacilityId(HttpSession session) {
		if(session.getAttribute(APPConstant.USER_INFORMATION) == null){
			return 1;
		}
		LoginHelper helper = (LoginHelper) session
				.getAttribute(APPConstant.USER_INFORMATION);
		return helper.getFacilityId();
	}
	
}
