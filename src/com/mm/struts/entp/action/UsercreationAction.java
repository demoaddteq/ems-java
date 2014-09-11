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
import com.mm.struts.entp.form.UsercreationForm;
import com.mm.core.master.*;
import javax.sql.DataSource;

import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * MyEclipse Struts
 * Creation date: 06-20-2007
 * 
 * XDoclet definition:
 * @struts.action path="/usercreation" name="UsercreationForm" input="/form/usercreation.jsp" scope="request" 
 * @struts.action-forward name="success" path="usercreation.jsp"
 */
public class UsercreationAction extends Action {
	/*
	 * Generated Methods
	 */
	EntpMaster entpMaster = new EntpMaster(); 
	RootMaster rootMaster = new RootMaster(); 

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		UsercreationForm usercreationForm = (UsercreationForm) form;// TODO Auto-generated method stub
		//System.out.println("UserCreation.........in   Action.....");
		
		HttpSession session = request.getSession();
		
		
		String rightId = "1";
		rightId = usercreationForm.getRdbgroup();
		//System.out.println("rightId......ff........"+rightId);
				
		String compId =(String)session.getAttribute("compId");
		
		Vector <String> userVec = new Vector<String>();		
		DataSource ds = getDataSource(request,"entp");	
		String creationDate ="";		
		String creationTime = "";
		
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
	    creationDate = sttotal.nextToken();
	    creationTime = sttotal.nextToken();
	    
	    String strVerifyResult=rootMaster.checkLoginId(ds,usercreationForm.getLoginName(),usercreationForm.getEmail());
		if(strVerifyResult.equals("loginId"))
		{	
			//System.out.println("strVerifyResult..............."+strVerifyResult);
			
			ActionErrors errors = new ActionErrors();
			errors.add("duplicate", new ActionError("errors.user.duplicate"));
			
				saveErrors(request, errors);
			
			return mapping.findForward("failure");
			
		}
		
		String result = null;
		
		
		
		 String tempCountry[] = usercreationForm.getCountry().split("~");
		 
			String comUserCountry=tempCountry[0];
			String comUserState = usercreationForm.getState();
			String comUserCity = usercreationForm.getCity();
			if(comUserCountry.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				comUserCountry=rootMaster.insertCountry(ds,usercreationForm.getTxtPPrefix(),usercreationForm.getOtherCountry());//return country id
				comUserState=rootMaster.insertState(ds,usercreationForm.getOtherState(),comUserCountry);
				comUserCity=rootMaster.insertCity(ds,usercreationForm.getOtherCity(),comUserState,comUserCountry);
			}
			else if(comUserState.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				comUserState=rootMaster.insertState(ds,usercreationForm.getOtherState(),comUserCountry);
				comUserCity=rootMaster.insertCity(ds,usercreationForm.getOtherCity(),comUserState,comUserCountry);
			}
			else if(comUserCity.equals("-1"))
			{
				//set sity in data base and add that is in company master.
				comUserCity=rootMaster.insertCity(ds,usercreationForm.getOtherCity(),comUserState,comUserCountry);
			}
			else
			{
				//nothing 
			}
			////////////
			String phoneNo="";
			if(usercreationForm.getTxtPhone().trim().length()>0)
			{
				String Prefix = usercreationForm.getTxtPPrefix().trim();
				String areaCode = usercreationForm.getTxtAreacode().trim();
				String mobile = usercreationForm.getTxtPhone().trim();
				phoneNo = Prefix+"~"+areaCode+"~"+mobile ;
			}
			
			String mobilNo="";
			if(usercreationForm.getTxtMobile().trim().length()>0)
			{
				String countryCode = usercreationForm.getTxtPrefix().trim();				
				String mobile = usercreationForm.getTxtMobile().trim();
				
				mobilNo = countryCode+"~"+mobile;
			}
		
		
		
		
		userVec.add(compId);
		userVec.add(rightId);
		userVec.add(usercreationForm.getLoginName());
		userVec.add(usercreationForm.getPassword());
		userVec.add(usercreationForm.getFname());
		userVec.add(usercreationForm.getLname());
		userVec.add(usercreationForm.getEmail());
		userVec.add(phoneNo);
		userVec.add(mobilNo);
		userVec.add(usercreationForm.getAddress());
		userVec.add(comUserCountry);
		userVec.add(comUserState);
		userVec.add(comUserCity);
		userVec.add(usercreationForm.getZip());
		userVec.add("nophoto.jpg");
		userVec.add("IN");
		userVec.add(creationDate);
		userVec.add(creationTime);
		userVec.add("0000-00-00");
		userVec.add("00:00:00");
		userVec.add("0");
		userVec.add("20");
		userVec.add("0");
		userVec.add("0");
		userVec.add("0");
		userVec.add("0");
		userVec.add("0");
		userVec.add("0");
		userVec.add("0");
		userVec.add("0");
		userVec.add("0");
		userVec.add("0");
		userVec.add("Male");
		
		//System.out.println("userVec.............."+userVec);
		
		result=rootMaster.insertUserInfo(ds,userVec);
		
		//System.out.println("result.............."+result);
		
		
		
		
		if(result.equalsIgnoreCase("success"))
		{
			return mapping.findForward("success");
		}
		else{
			
		return mapping.findForward("failure");
		}
		
	}
}