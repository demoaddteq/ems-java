
package com.mm.struts.entp.action;

import java.util.Vector;

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
import com.mm.struts.entp.form.UserdetailsForm;
import com.mm.core.master.*;

import javax.sql.DataSource;
import java.util.*;

/** 
 * MyEclipse Struts
 * Creation date: 06-20-2007
 * 
 * XDoclet definition:
 * @struts.action path="/usercreation" name="UsercreationForm" input="/form/usercreation.jsp" scope="request" 
 * @struts.action-forward name="success" path="usercreation.jsp"
 */
public class UserdetailsAction extends Action {
	/*
	 * Generated Methods
	 */

	EntpMaster objectStr = new EntpMaster();
	
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		UserdetailsForm userdetailsForm = (UserdetailsForm) form;// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String userId =(String)session.getAttribute("uId");    // Self Id
		
		String result = null;
		DataSource ds = getDataSource(request,"entp");	
		RootMaster rootMaster = new RootMaster();
		
		int rId = rootMaster.getrightsId(ds,Integer.parseInt(userId) );		
		
		Vector <String> datavec = new Vector<String>();	
		Vector <String> datavec1 = new Vector<String>();	
		
		String strUId = userdetailsForm.getUserid();
		String actionTask = userdetailsForm.getTask();
		
		//System.out.println("actionTask...3...."+actionTask);
		
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
		////System.out.println("strUId.....*......"+strUId);
		
		 String tempCountry[] = userdetailsForm.getCountry().split("~");
			String userCountry=tempCountry[0];
			String userState = userdetailsForm.getState();
			String userCity = userdetailsForm.getCity();
			if(userCountry.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				userCountry=rootMaster.insertCountry(ds,userdetailsForm.getTxtPPrefix(),userdetailsForm.getOtherCountry());//return country id
				userState=rootMaster.insertState(ds,userdetailsForm.getOtherState(),userCountry);
				userCity=rootMaster.insertCity(ds,userdetailsForm.getOtherCity(),userState,userCountry);
			}
			else if(userState.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				userState=rootMaster.insertState(ds,userdetailsForm.getOtherState(),userCountry);
				userCity=rootMaster.insertCity(ds,userdetailsForm.getOtherCity(),userState,userCountry);
			}
			else if(userCity.equals("-1"))
			{
				//set sity in data base and add that is in company master.
				userCity=rootMaster.insertCity(ds,userdetailsForm.getOtherCity(),userState,userCountry);
			}
			else
			{
				//nothing 
			}
			////////////
			String phoneNo="";
			if(userdetailsForm.getTxtPhone().trim().length()>0)
			{
				String Prefix = userdetailsForm.getTxtPPrefix().trim();
				String areaCode = userdetailsForm.getTxtAreacode().trim();
				String num = userdetailsForm.getTxtPhone().trim();
				phoneNo = Prefix+"~"+areaCode+"~"+num ;
			}
			
			String mobileNo="";
			if(userdetailsForm.getTxtMobile().trim().length()>0)
			{
				
				String mcountry = userdetailsForm.getTxtPrefix().trim();
				String userMobileNo = userdetailsForm.getTxtMobile().trim();
				mobileNo = mcountry+"~"+userMobileNo;
			}
			
			String userRightId = userdetailsForm.getRdbgroup();	
		
		
		datavec.add(strUId);//0	
		datavec.add(userdetailsForm.getFname());//1		
		datavec.add(userdetailsForm.getLname());//2
		datavec.add(userdetailsForm.getAddress());//3		
		datavec.add(userCountry);//4	
		datavec.add(userState);	//5
		datavec.add(userCity);	//6
		datavec.add(userdetailsForm.getZip());//7
		datavec.add(userdetailsForm.getEmail());//8
		datavec.add(phoneNo);//9
		datavec.add(mobileNo);//10	
		if(userId.equalsIgnoreCase(strUId)){				
			datavec.add(String.valueOf(rId));
		}else{
		datavec.add(userRightId);									 //11 RightId
		}
		
		
		////System.out.println("datavec.....*......"+datavec);
		
		result = rootMaster.updateUserInfo(ds, datavec);
		if(result.equalsIgnoreCase("success")){
			ActionErrors errors = new ActionErrors();
           	errors.clear();
          	errors.add("otherusermodify", new ActionError("errors.entp.otherusermodifySuccess"));
			saveErrors(request, errors);
			result = "success";
		}
				
		
		
		}
		////System.out.println("result.....*......"+result);
		if(result.equalsIgnoreCase("success")){
			return mapping.findForward("success");
		}
		else{
			return mapping.findForward("failuer");
		}
		
	}
}