package com.mm.struts.entp.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.IndvMaster;
import com.mm.struts.entp.form.UploadPhotoForm;

public class ConsumerPhotoAction extends Action {
	
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
		String userid=(String)session.getAttribute("uId");
		////System.out.println("userid................" +userid);
		int uid = Integer.parseInt(userid);	
		
		////System.out.println("Companyid................" +companyid);
		
		IndvMaster indvMaster = new IndvMaster();
		
			
		/*
		String country = indvConsumerMyProfileModifyForm.getCountry().toString().trim();
		String countryArr[] = country.split("~");
		String otherCountry = indvConsumerMyProfileModifyForm.getOthercountry().toString().trim();
		String state = indvConsumerMyProfileModifyForm.getState().toString().trim();
		String otherState = indvConsumerMyProfileModifyForm.getOtherstate().toString().trim();
		String city = indvConsumerMyProfileModifyForm.getCity().toString().trim();
		String otherCity = indvConsumerMyProfileModifyForm.getOthercity().toString().trim();
		*/
		String photoPath = indvMaster.getUserPhotoPath(getDataSource(request, "entp"),uid);   		
		UploadPhotoForm entpUploadPhotoForm = new UploadPhotoForm();
		    			    		
		entpUploadPhotoForm.setPhotoPath(photoPath);
		   
           request.setAttribute("entpUploadPhotoForm",entpUploadPhotoForm);
       	       
			
			return mapping.findForward("success");
		
	}

}
