package com.mm.struts.entp.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.struts.entp.form.CompanyDetailsForm;
import com.mm.core.master.*;

import javax.sql.DataSource;
import java.util.*;
/** 
 * MyEclipse Struts
 * Creation date: 06-20-2007
 * 
 * XDoclet definition:
 * @struts.action path="/companyDetails" name="CompanyDetailsForm" input="/form/companyDetails.jsp" scope="request" 
 * @struts.action-forward name="success" path="companyDetails.jsp"
 */
public class CompanyDetailsAction extends Action {
	/*
	 * Generated Methods
	 */
	RootMaster rootMaster = new RootMaster();
	EntpMaster enptMaster = new EntpMaster();

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		CompanyDetailsForm companyDetailsForm = (CompanyDetailsForm) form;// TODO Auto-generated method stub
		
		Vector <String> datavec = new Vector<String>();		
		DataSource ds = getDataSource(request,"entp");
		String result = null;
		
		String companyId = companyDetailsForm.getCompanyId();
		String task = companyDetailsForm.getTask();
		
		Vector <String> dataVec1 = new Vector<String>();	
		
		if(task.equalsIgnoreCase("Block")){			
			dataVec1.add("0");
			dataVec1.add(companyId);			
			result = enptMaster.updateComActiveFlag(ds,dataVec1);
			if(result.equalsIgnoreCase("success")){
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("compBlock", new ActionError("errors.entp.compBlockSuccess"));
				saveErrors(request, errors);
				result = "success";
			}
				
		}
		else if(task.equalsIgnoreCase("Active")){			
			dataVec1.add("1");
			dataVec1.add(companyId);			
			result = enptMaster.updateComActiveFlag(ds,dataVec1);	
			
			if(result.equalsIgnoreCase("success")){
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("compActive", new ActionError("errors.entp.compActiveSuccess"));
				saveErrors(request, errors);
				result = "success";
			}
				
		}
		else {		
		
		 String tempCountry[] = companyDetailsForm.getCountry().split("~");
			String comCountry=tempCountry[0];
			String comState = companyDetailsForm.getState();
			String comCity = companyDetailsForm.getCity();
			if(comCountry.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				comCountry=rootMaster.insertCountry(ds,companyDetailsForm.getTxtCPPrefix(),companyDetailsForm.getOtherCountry());//return country id
				comState=rootMaster.insertState(ds,companyDetailsForm.getOtherState(),comCountry);
				comCity=rootMaster.insertCity(ds,companyDetailsForm.getOtherCity(),comState,comCountry);
			}
			else if(comState.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				comState=rootMaster.insertState(ds,companyDetailsForm.getOtherState(),comCountry);
				comCity=rootMaster.insertCity(ds,companyDetailsForm.getOtherCity(),comState,comCountry);
			}
			else if(comCity.equals("-1"))
			{
				//set sity in data base and add that is in company master.
				comCity=rootMaster.insertCity(ds,companyDetailsForm.getOtherCity(),comState,comCountry);
			}
			else
			{
				//nothing 
			}
			////////////
			String phoneNo="";
			if(companyDetailsForm.getCphone().trim().length()>0)
			{
				String Prefix = companyDetailsForm.getTxtCPPrefix().trim();
				String areaCode = companyDetailsForm.getTxtCACPrefix().trim();
				String mobile = companyDetailsForm.getCphone().trim();
				phoneNo = Prefix+"~"+areaCode+"~"+mobile ;
			}
			///////////////			
		
			String tempcat="0,";
			String companyTemplate[] = companyDetailsForm.getCompanyTemplate();
			for(int i=0; i<companyTemplate.length;i++)
			{
				String tempCatval = companyTemplate[i];
				if(tempCatval.equals("-1"))
				{
					//set category in category table and add that id in company master table
					//tempCatval=rootMaster.insertCategory(dataSource,firstCreationVo.getOthercompanyTemplate());
				}
				if(i<companyTemplate.length)
				  tempcat = tempcat+tempCatval+", ";
				
			}
			
			
		datavec.add(companyId);//0	
		datavec.add(companyDetailsForm.getCompanyName());//1
		datavec.add(companyDetailsForm.getCompanyAddress1());//2		
		datavec.add(companyDetailsForm.getCompanyAddress2());//3
		datavec.add(comCity);//4
		datavec.add(comState);//5	
		datavec.add(comCountry);//6
		datavec.add(companyDetailsForm.getCompanyPostalCode());//7
		datavec.add(companyDetailsForm.getCompanyEMail());//8	
		datavec.add(phoneNo);//10		
		datavec.add(tempcat);//11
		
		result = enptMaster.updateCompanyInfo(ds, datavec);
		if(result.equalsIgnoreCase("success")){
			ActionErrors errors = new ActionErrors();
           	errors.clear();
          	errors.add("compmodify", new ActionError("errors.entp.compmodifySuccess"));
			saveErrors(request, errors);
			result = "success";
		}
		
		}
		
		if(result.equalsIgnoreCase("success"))
		{
			return mapping.findForward("success");
		}
		else{
			
		return mapping.findForward("failure");
		}
	}
}