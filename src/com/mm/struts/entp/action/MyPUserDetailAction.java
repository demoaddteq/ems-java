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
import com.mm.struts.entp.form.MyPUserDetailForm;

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
		DataSource ds = getDataSource(request,"entp");
		
		
		///////////////////////
		 String tempCountry[] = myPUserDeatil.getCountry().split("~");
			String comCountry=tempCountry[0];
			String comState = myPUserDeatil.getState();
			String comCity = myPUserDeatil.getCity();
			if(comCountry.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				comCountry=rootMaster.insertCountry(ds,myPUserDeatil.getTxtPPrefix(),myPUserDeatil.getOtherCountry());//return country id
				comState=rootMaster.insertState(ds,myPUserDeatil.getOtherState(),comCountry);
				comCity=rootMaster.insertCity(ds,myPUserDeatil.getOtherCity(),comState,comCountry);
			}
			else if(comState.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				comState=rootMaster.insertState(ds,myPUserDeatil.getOtherState(),comCountry);
				comCity=rootMaster.insertCity(ds,myPUserDeatil.getOtherCity(),comState,comCountry);
			}
			else if(comCity.equals("-1"))
			{
				//set sity in data base and add that is in company master.
				comCity=rootMaster.insertCity(ds,myPUserDeatil.getOtherCity(),comState,comCountry);
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
			datavec.add(myPUserDeatil.getRid());
			
			//System.out.println("datavec....MyPUserDetailAction......."+datavec);
			
			String result = rootMaster.updateUserInfo(ds, datavec);
			
			//System.out.println("datavec....MyPUserDetailAction......."+result);
			
			if(result.equalsIgnoreCase("success")){
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("usermodify", new ActionError("errors.entp.usermodifySuccess"));
				saveErrors(request, errors);
				result = "success";
			}
			
						
			if(result.equalsIgnoreCase("success")){
				return mapping.findForward("success");
			}
			else{
				return mapping.findForward("failuer");
			}
			
		
	}
}