

package com.mm.struts.action;

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

import com.mm.core.master.RootMaster;
import com.mm.struts.form.CollegeRegisForm;
import com.mm.struts.form.CollegeRegisVo;

public class CollegeRegisAction extends Action {
	
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
		RootMaster rootMaster = new RootMaster();
		String strStatus="failure";
		String strtypeOfCompan="Advertiser";
		
		DataSource dataSource = getDataSource(request,"main");
		CollegeRegisForm collegeRegisForm = (CollegeRegisForm) form;// TODO Auto-generated method stub
		String phoneNo = collegeRegisForm.getTxtCPPrefix()+"~"+collegeRegisForm.getTxtCAreacode()+"~"+collegeRegisForm.getTxtCPhone();
		
		String strResult=rootMaster.comCollVerify(dataSource,collegeRegisForm.getCompanySName(),collegeRegisForm.getCompanyEMail(),phoneNo, strtypeOfCompan);
		
		if(!strResult.equals("success"))
		{
			if(strResult.equals("sname"))
			{
				ActionErrors errors = new ActionErrors();
				errors.add("duplicate", new ActionError("errors.company.duplicate"));
				
				if(!errors.isEmpty())
				{
					saveErrors(request, errors);
				}
				strStatus="failure";
			}
			
			if(strResult.equals("mailid"))
			{
				ActionErrors errors = new ActionErrors();
				errors.add("duplicate", new ActionError("errors.company.mailId"));
				
				if(!errors.isEmpty())
				{
					saveErrors(request, errors);
				}
				strStatus="failure";
			}
			
			if(strResult.equals("phone"))
			{
				ActionErrors errors = new ActionErrors();
				errors.add("duplicate", new ActionError("errors.company.phone"));
				
				if(!errors.isEmpty())
				{
					saveErrors(request, errors);
				}
				strStatus="failure";
			}
		}
		else
		{
		//end company phone no check
		
			CollegeRegisVo collegeRegisVo = collegeRegisForm.getCollegeRegisVo();
		HttpSession  session= request.getSession();
		session.setAttribute("companyData", collegeRegisVo);
		strStatus="success";
		}
		return mapping.findForward(strStatus);
	}

}
