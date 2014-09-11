package com.mm.struts.entp.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.struts.entp.form.MyprofileForm;
import com.mm.core.master.*;

import javax.sql.DataSource;
import java.util.*;

/** 
 * MyEclipse Struts
 * Creation date: 06-20-2007
 * 
 * XDoclet definition:
 * @struts.action path="/myprofile" name="MyprofileForm" input="/form/myprofile.jsp" scope="request" 
 * @struts.action-forward name="success" path="myprofile.jsp"
 */
public class MyprofileAction extends Action {
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
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		MyprofileForm myprofileForm = (MyprofileForm) form;// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String compId =(String)session.getAttribute("compId");
		
		
		
		Vector <String> datavec = new Vector<String>();
		EntpMaster entpMaster = new EntpMaster();
		RootMaster rootMaster = new RootMaster();
		DataSource ds = getDataSource(request,"entp");
		
		
		///////////////////////
		 String tempCountry[] = myprofileForm.getCountry().split("~");
			String comCountry=tempCountry[0];
			String comState = myprofileForm.getState();
			String comCity = myprofileForm.getCity();
			if(comCountry.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				comCountry=rootMaster.insertCountry(ds,myprofileForm.getTxtCPPrefix(),myprofileForm.getOtherCountry());//return country id
				comState=rootMaster.insertState(ds,myprofileForm.getOtherState(),comCountry);
				comCity=rootMaster.insertCity(ds,myprofileForm.getOtherCity(),comState,comCountry);
			}
			else if(comState.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				comState=rootMaster.insertState(ds,myprofileForm.getOtherState(),comCountry);
				comCity=rootMaster.insertCity(ds,myprofileForm.getOtherCity(),comState,comCountry);
			}
			else if(comCity.equals("-1"))
			{
				//set sity in data base and add that is in company master.
				comCity=rootMaster.insertCity(ds,myprofileForm.getOtherCity(),comState,comCountry);
			}
			else
			{
				//nothing 
			}
			////////////
			String phoneNo="";
			if(myprofileForm.getCphone().trim().length()>0)
			{
				String Prefix = myprofileForm.getTxtCPPrefix().trim();
				String areaCode = myprofileForm.getTxtCACPrefix().trim();
				String mobile = myprofileForm.getCphone().trim();
				phoneNo = Prefix+"~"+areaCode+"~"+mobile ;
			}
		/////////////////////////
		datavec.add(compId);		
		datavec.add(myprofileForm.getCompanyName().toString().trim());		
		datavec.add(myprofileForm.getCompanyAddress1().toString().trim());		
		datavec.add(myprofileForm.getCompanyAddress2().toString().trim());	
		datavec.add(comCity);	
		datavec.add(comState);	
		datavec.add(comCountry);	
		datavec.add(myprofileForm.getCompanyPostalCode().toString().trim());
		datavec.add(myprofileForm.getCompanyEMail().toString().trim());		
		
		datavec.add(phoneNo);
		datavec.add("");
		
		//System.out.println("datavec....MyProfileAction......."+datavec);
		
		String result = entpMaster.updateCompanyInfo(ds, datavec);
		
		if(result.equalsIgnoreCase("success")){
			ActionErrors errors = new ActionErrors();
           	errors.clear();
          	errors.add("compmodify", new ActionError("errors.entp.compmodifySuccess"));
			saveErrors(request, errors);
			result = "success";
		}
		
		//System.out.println("datavec....MyProfileAction......."+result);
		
		if(result.equalsIgnoreCase("success")){
			return mapping.findForward("success");
		}
		else{
			return mapping.findForward("failuer");
		}
	}
}