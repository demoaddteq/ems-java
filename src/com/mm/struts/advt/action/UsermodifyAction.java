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
import com.mm.struts.advt.form.UsermodifyForm;

import com.mm.core.master.*;


import javax.sql.DataSource;
import java.util.*;


public class UsermodifyAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		UsermodifyForm usermodifyForm = (UsermodifyForm) form;// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String userId =(String)session.getAttribute("uId");    // Self Id
		
		
		RootMaster rootMaster = new RootMaster();
		DataSource ds = getDataSource(request,"advt");
		
		String userRightId = usermodifyForm.getRdbgroup();
		//System.out.println("userRightId........."+userRightId);
		if(userRightId.equalsIgnoreCase("0"))
		{
			////System.out.println("NO User Reight group exist.........");
			ActionErrors errors = new ActionErrors();
			errors.add("groupNotExist", new ActionError("errors.rgroup.groupNotExist"));			
				saveErrors(request, errors);
          	
			return mapping.findForward("success");
		}	
	
		////System.out.println("...no group exist.........");
		
		
		int rId = rootMaster.getrightsId(ds,Integer.parseInt(userId) );
			
		String strUId = (request.getParameter("userid")!=null) ? request.getParameter("userid") : "0";
		String actionTask = (request.getParameter("task")!=null) ? request.getParameter("task") : "Modify";
		
		
		String result = "success";
		Vector <String> datavec = new Vector<String>();
		Vector <String> datavec1 = new Vector<String>();
		
		
		
		////System.out.println("strUId....strUId......."+strUId);
		////System.out.println("datavec....UsermodifyAction......."+result);		
				
				
		if(actionTask.equalsIgnoreCase("Delete")){
			
			datavec1.add("1");
			datavec1.add(strUId);
			
			result = rootMaster.updateUserActiveFlag(ds,datavec1 );
			if(result.equalsIgnoreCase("success")){
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("userdelete", new ActionError("errors.entp.userdeleteSuccess"));
				saveErrors(request, errors);
				result = "success";
			}
		}
		else{
		
		
		 String tempCountry[] = usermodifyForm.getCountry().split("~");
			String country=tempCountry[0];
			String state = usermodifyForm.getState();
			String city = usermodifyForm.getCity();
			if(country.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				country=rootMaster.insertCountry(ds,usermodifyForm.getTxtPPrefix(),usermodifyForm.getOthercountry().toString().trim());//return country id
				state=rootMaster.insertState(ds,usermodifyForm.getOtherstate().toString().trim(),country);
				city=rootMaster.insertCity(ds,usermodifyForm.getOthercity().toString().trim(),state,country);
			}
			else if(state.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				state=rootMaster.insertState(ds,usermodifyForm.getOtherstate().toString().trim(),country);
				city=rootMaster.insertCity(ds,usermodifyForm.getOthercity().toString().trim(),state,country);
			}
			else if(city.equals("-1"))
			{
				//set sity in data base and add that is in company master.
				city=rootMaster.insertCity(ds,usermodifyForm.getOthercity().toString(),state,country);
			}
			else
			{
				//nothing 
			}
			////////////
			String phoneNo="";
			if(usermodifyForm.getTxtPhone().trim().length()>0)
			{
				String Prefix = usermodifyForm.getTxtPPrefix().trim();
				String areaCode = usermodifyForm.getTxtAreacode().trim();
				String mobile = usermodifyForm.getTxtPhone().trim();
				phoneNo = Prefix+"~"+areaCode+"~"+mobile ;
			}
			
			String mobileNo="";
			if(usermodifyForm.getTxtMobile().trim().length()>0)
			{
				
				String mcountry = usermodifyForm.getTxtPrefix().trim();
				String userMobileNo = usermodifyForm.getTxtMobile().trim();
				mobileNo = mcountry+"~"+userMobileNo;
			}
			
			String depart = usermodifyForm.getDepartment();
			String desig = usermodifyForm.getDesignation();
			String type="college";
			if(depart.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				depart=rootMaster.insertDepartment(ds,usermodifyForm.getOtherdepartment().toString().trim(),type);//return country id
				desig=rootMaster.insertDesignation(ds,usermodifyForm.getOtherdesignation().toString().trim(),type,depart);
				
			}
			else if(desig.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				desig=rootMaster.insertDesignation(ds,usermodifyForm.getOtherdesignation().toString().trim(),type,depart);
			}
			
			String strMentor = usermodifyForm.getMcata()+","+usermodifyForm.getMcatb()+","+usermodifyForm.getMcatc()+","+usermodifyForm.getMcatd();
			
			String strQuet = usermodifyForm.getTecquer()+","+usermodifyForm.getPerquer();
			
			
			datavec.add(strUId);										 //0 userid
			datavec.add(usermodifyForm.getFname().toString().trim());	 //1 Fname	
			datavec.add(usermodifyForm.getLname().toString().trim());	 //2 Lname	
			datavec.add(usermodifyForm.getAddress().toString().trim());	 //3 Address
			datavec.add(country);	                                     //4 country
			datavec.add(state);											 //5 state
			datavec.add(city);											 //6 city	
			datavec.add(usermodifyForm.getZip().toString().trim());		 //7 Zip
			datavec.add(usermodifyForm.getEmail().toString().trim());	 //8 Email
			datavec.add(phoneNo);										 //8 phoneNo
			datavec.add(mobileNo);									     //10 mobileNo
			
			if(userId.equalsIgnoreCase(strUId)){				
				datavec.add(String.valueOf(rId));
			}else{
			datavec.add(userRightId);									 //11 RightId
			}
			
			
			datavec.add(strMentor);	      								 // 12	
			datavec.add(strQuet);	  									 // 13
			datavec.add(depart);	      								 // 12	
			datavec.add(desig);	  									 // 13
			
			//System.out.println("datavec......"+datavec);
			
			result =  rootMaster.updateUserInfoCorpo(ds, datavec);
			
			if(result.equalsIgnoreCase("success")){
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("otherusermodify", new ActionError("errors.entp.otherusermodifySuccess"));
				saveErrors(request, errors);
				result = "success";
			}
			
			if(result.equalsIgnoreCase("success"))
			{
				
				String strCompId = (String)session.getAttribute("compId");
				AdvtMaster am = new AdvtMaster();
				
				Vector<String> usrCatVec = new Vector<String>();
				usrCatVec.add(strCompId);
				usrCatVec.add(strUId);
				usrCatVec.add(usermodifyForm.getCmbcat());
				usrCatVec.add(usermodifyForm.getChkGroup1());
				usrCatVec.add(usermodifyForm.getChkGroup2());
				usrCatVec.add(usermodifyForm.getChkGroup3());
				usrCatVec.add(usermodifyForm.getChkGroup4());
				usrCatVec.add(usermodifyForm.getChkGroup5());
				usrCatVec.add(usermodifyForm.getChkGroup6());
				usrCatVec.add(usermodifyForm.getChkGroup7());
				usrCatVec.add(usermodifyForm.getChkGroup8());
				usrCatVec.add(usermodifyForm.getChkGroup9());
				usrCatVec.add(usermodifyForm.getChkGroup10());
				//////System.out.println("User Category Vec "+usrCatVec);
				int numStatus = am.getUserCatStatus(getDataSource(request, "advt"), Integer.parseInt(strCompId.trim()), Integer.parseInt(strUId.trim()));
				if(numStatus>0)
				{
					result= am.editUserCategory(ds, usrCatVec);
				}
				else
				{
					result= am.setUserCategory(ds, usrCatVec);
				}
				//////System.out.println("Result "+result);
			}
			
		}
			if(result.equalsIgnoreCase("success")){
				return mapping.findForward("success");
			}
			else{
				return mapping.findForward("failuer");
			}
			
		
	}
}