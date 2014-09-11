package com.mm.struts.advt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.RootMaster;
import com.mm.core.master.IndvMaster;
import com.mm.struts.advt.form.UploadLogoForm;

public class ConsumerLogoAction extends Action {
	
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
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session =request.getSession();
	
		String compId =(session.getAttribute("compId") != null) ? (String)session.getAttribute("compId") : "0";
		RootMaster rm = new RootMaster();
		String logoPath = rm.getLogoPath(getDataSource(request, "advt"), Integer.parseInt(compId.trim()));
		
		UploadLogoForm CollegeUploadLogoForm = new UploadLogoForm();
		    			    		
		CollegeUploadLogoForm.setLogoPath(logoPath);
		   
           request.setAttribute("collegeUploadLogoForm",CollegeUploadLogoForm);
       	       
			
			return mapping.findForward("success");
		
	}

}
