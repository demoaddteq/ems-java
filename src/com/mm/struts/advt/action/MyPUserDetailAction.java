package com.mm.struts.advt.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.struts.advt.form.MyPUserDetailForm;

import com.mm.core.master.*;

import javax.sql.DataSource;
import java.util.*;


public class MyPUserDetailAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		MyPUserDetailForm myPUserDeatil = (MyPUserDetailForm) form;// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();		
		String uId =(String)session.getAttribute("uId");		
		
		Vector <String> datavec = new Vector<String>();		
		RootMaster rootMaster = new RootMaster();
		DataSource ds = getDataSource(request,"advt");
		
		
		///////////////////////
		 String tempCountry[] = myPUserDeatil.getCountry().split("~");
			String comCountry=tempCountry[0];
			String comState = myPUserDeatil.getState();
			String comCity = myPUserDeatil.getCity();
			if(comCountry.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				comCountry=rootMaster.insertCountry(ds,myPUserDeatil.getTxtPPrefix(),myPUserDeatil.getOthercountry().toString().trim());//return country id
				comState=rootMaster.insertState(ds,myPUserDeatil.getOtherstate().toString().trim(),comCountry);
				comCity=rootMaster.insertCity(ds,myPUserDeatil.getOthercity().toString().trim(),comState,comCountry);
			}
			else if(comState.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				comState=rootMaster.insertState(ds,myPUserDeatil.getOtherstate().toString().trim(),comCountry);
				comCity=rootMaster.insertCity(ds,myPUserDeatil.getOthercity().toString().trim(),comState,comCountry);
			}
			else if(comCity.equals("-1"))
			{
				//set sity in data base and add that is in company master.
				comCity=rootMaster.insertCity(ds,myPUserDeatil.getOthercity().toString().trim(),comState,comCountry);
			}
			else
			{
				//nothing 
			}
			////////////
			String phoneNo="";
			if(myPUserDeatil.getTxtPhone().trim().length()>0)
			{
				String Prefix = myPUserDeatil.getTxtPPrefix().trim();
				String areaCode = myPUserDeatil.getTxtUACPrefix().trim();
				String mobile = myPUserDeatil.getTxtPhone().trim();
				phoneNo = Prefix+"~"+areaCode+"~"+mobile ;
			}
			
			String mobileNo="";
			if(myPUserDeatil.getMobile().trim().length()>0)
			{
				
				String mcountry = myPUserDeatil.getTxtMobile().trim();
				String userMobileNo = myPUserDeatil.getMobile().trim();
				mobileNo = mcountry+"~"+userMobileNo;
			}
			
			
			String depart = myPUserDeatil.getDepartment();
			String desig = myPUserDeatil.getDesignation();
			String type="college";
			if(depart.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				depart=rootMaster.insertDepartment(ds,myPUserDeatil.getOtherdepartment().toString().trim(),type);//return country id
				desig=rootMaster.insertDesignation(ds,myPUserDeatil.getOtherdesignation().toString().trim(),type,depart);
				
			}
			else if(desig.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				desig=rootMaster.insertDesignation(ds,myPUserDeatil.getOtherdesignation().toString().trim(),type,depart);
			}
			
			String strMentor = myPUserDeatil.getMcata()+","+myPUserDeatil.getMcatb()+","+myPUserDeatil.getMcatc()+","+myPUserDeatil.getMcatd()+","+"0";
			String strQuet = myPUserDeatil.getTecquer()+","+myPUserDeatil.getPerquer();
		
			datavec.add(uId);		
			datavec.add(myPUserDeatil.getFname().toString().trim());		
			datavec.add(myPUserDeatil.getLname().toString().trim());		
			datavec.add(myPUserDeatil.getAddress().toString().trim());	
			datavec.add(comCountry);	
			datavec.add(comState);	
			datavec.add(comCity);				
			datavec.add(myPUserDeatil.getZip().toString().trim());
			datavec.add(myPUserDeatil.getEmail().toString().trim());
			datavec.add(phoneNo);
			datavec.add(mobileNo);
			datavec.add(myPUserDeatil.getRid().toString());
			datavec.add(myPUserDeatil.getShortPro().toString());
			
			////////System.out.println("datavec....MyPUserDetailAction......."+myPUserDeatil.getRid().toString());
			datavec.add(strMentor);	      								 // 12	
			datavec.add(strQuet);	
			datavec.add(depart);	      								 // 12	
			datavec.add(desig);	  									 // 13
			
			String result = rootMaster.updateUserInfo(ds, datavec);
			
			if(result.equalsIgnoreCase("success")){
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("usermodify", new ActionError("errors.entp.usermodifySuccess"));
				saveErrors(request, errors);
				result = "success";
			}
			
			//////System.out.println("datavec....MyPUserDetailAction......."+result);
			
			if(result.equalsIgnoreCase("success")){
				return mapping.findForward("success");
			}
			else{
				return mapping.findForward("failure");
			}
			
		
	}
}
