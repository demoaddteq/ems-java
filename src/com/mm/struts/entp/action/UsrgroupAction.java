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

import com.mm.core.master.EntpMaster;
import com.mm.core.master.RootMaster;
import com.mm.struts.entp.form.UsrgroupForm;

public class UsrgroupAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		UsrgroupForm usrgroupForm = (UsrgroupForm) form;// TODO Auto-generated method stub
		
		 
		RootMaster rootMaster = new RootMaster(); 
		Vector <String> rightVec = new Vector<String>();
		DataSource ds = getDataSource(request,"entp");	
		
		HttpSession session = request.getSession();	
		Vector allRightsVec = (Vector)session.getAttribute("allRights");
		int adminFlag = Integer.parseInt(allRightsVec.elementAt(18).toString().trim());
		//String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
		//Vector allRights =(Vector)session.getAttribute("allRights"); 
		//String adminFlag = allRights.elementAt(18).toString();
		String groupname = usrgroupForm.getGroupName();
		String strVerifyResult=rootMaster.checkGroupName(ds, compId, groupname);
		////System.out.println("out side if ........1....");
		if(!strVerifyResult.equals("failure"))
		{
			ActionErrors errors = new ActionErrors();
			errors.add("duplicategroup", new ActionError("errors.group.duplicategroup"));
			
				saveErrors(request, errors);
				//System.out.println("in side if ............");
			
			return mapping.findForward("failure");
			
		}
		//System.out.println("out side if ........1...."+usrgroupForm.getAccmanagement());
		
		String accmanagement = "0";             //0
		String accountinformation = "0";       //1
		String billing_information = "0";		//2
		String change_password = "0";			//3
		String company_profile_modify = "0";	//4
		String senderid = "0";					//5
		String senderid_default = "0";			//6
		String senderid_deletion = "0";			//7
		String senderid_creation = "0";			//8
		String smsallotment = "0";				//9
		String users = "0";					//10
		String usercreation = "0";				//11
		String userdeletion = "0";				//12
		String usermodification = "0";			//13
		String order_more = "0";				//14
		String pay_now = "0";					//15
		String controlpanel = "0";				//16 
		String communitycreation = "0";			//17
		String companydeletion = "0";			//18
		String existingcommunity = "0";			//19
		String packagemanagement = "0";			//20
		String smscallotment = "0";				//21
		String complaintmanagement = "0";		//22 
		String comments = "0";					//23
		String company_management = "0";	//24
		String category = "0";					//25
		String complaints = "0";			//26
		String mis = "0";					//27
		
		
		
					 accmanagement = usrgroupForm.getAccmanagement();             //0
					 
					 if(accmanagement.equalsIgnoreCase("on")){			 
						 accmanagement = "1";
					 }
					 else{
						 accmanagement = "0";
					 }
					 
					 
					  accountinformation = usrgroupForm.getAccountinformation();       //1		 
					 if(accountinformation.equalsIgnoreCase("on")){			 
						 accountinformation = "1";
					 }
					 else{
						 accountinformation = "0";
					 }
					 
					  billing_information = usrgroupForm.getBilling_information();		//2
					 if(billing_information.equalsIgnoreCase("on")){			 
						 billing_information = "1";
					 }
					 else{
						 billing_information = "0";
					 }
					 
					 
					  change_password = usrgroupForm.getChange_password();			//3
					 if(change_password.equalsIgnoreCase("on")){			 
						 change_password = "1";
					 }
					 else{
						 change_password = "0";
					 }
					 
					 
					  company_profile_modify = usrgroupForm.getCompany_profile_modify();	//4
					 if(company_profile_modify.equalsIgnoreCase("on")){			 
						 company_profile_modify = "1";
					 }
					 else{
						 company_profile_modify = "0";
					 }
					 
					 
					  senderid = usrgroupForm.getSenderid();					//5
					 if(senderid.equalsIgnoreCase("on")){			 
						 senderid = "1";
					 }
					 else{
						 senderid = "0";
					 }
					 
					 
					  senderid_default = usrgroupForm.getSenderid_default();			//6
					 if(senderid_default.equalsIgnoreCase("on")){			 
						 senderid_default = "1";
					 }
					 else{
						 senderid_default = "0";
					 }
					 
					 
					  senderid_deletion = usrgroupForm.getSenderid_deletion();			//7
					 if(senderid_deletion.equalsIgnoreCase("on")){			 
						 senderid_deletion = "1";
					 }
					 else{
						 senderid_deletion = "0";
					 }
					 
					 
					  senderid_creation = usrgroupForm.getSenderid_creation();			//8
					 if(senderid_creation.equalsIgnoreCase("on")){			 
						 senderid_creation = "1";
					 }
					 else{
						 senderid_creation = "0";
					 }
					 
					 
					  smsallotment = usrgroupForm.getSmsallotment();				//9
					 if(smsallotment.equalsIgnoreCase("on")){			 
						 smsallotment = "1";
					 }
					 else{
						 smsallotment = "0";
					 }
					 
					 
					  users = usrgroupForm.getUsers();						//10
					 if(users.equalsIgnoreCase("on")){			 
						 users = "1";
					 }
					 else{
						 users = "0";
					 }
					 
					 
					  usercreation = usrgroupForm.getUsercreation();				//11
					 if(usercreation.equalsIgnoreCase("on")){			 
						 usercreation = "1";
					 }
					 else{
						 usercreation = "0";
					 }
					 
					 
					  userdeletion = usrgroupForm.getUserdeletion();				//12
					 if(userdeletion.equalsIgnoreCase("on")){			 
						 userdeletion = "1";
					 }
					 else{
						 userdeletion = "0";
					 }
					 
					 
					  usermodification = usrgroupForm.getUsermodification();			 //13
					 if(usermodification.equalsIgnoreCase("on")){			 
						 usermodification = "1";
					 }
					 else{
						 usermodification = "0";
					 }
					 
					 
					  order_more = usrgroupForm.getOrder_more();				//14
					 if(order_more.equalsIgnoreCase("on")){			 
						 order_more = "1";
					 }
					 else{
						 order_more = "0";
					 }
					 
					 
					  pay_now = usrgroupForm.getPay_now();					//15
					 if(pay_now.equalsIgnoreCase("on")){			 
						 pay_now = "1";
					 }
					 else{
						 pay_now = "0";
					 }
					 
					 
					  controlpanel = usrgroupForm.getControlpanel();				//16 
					 if(controlpanel.equalsIgnoreCase("on")){			 
						 controlpanel = "1";
					 }
					 else{
						 controlpanel = "0";
					 }
					 
					 
					  communitycreation = usrgroupForm.getCommunitycreation();			 //17
					 if(communitycreation.equalsIgnoreCase("on")){			 
						 communitycreation = "1";
					 }
					 else{
						 communitycreation = "0";
					 }
					 
					 
					  companydeletion = usrgroupForm.getCompanydeletion();			//18
					 if(companydeletion.equalsIgnoreCase("on")){			 
						 companydeletion = "1";
					 }
					 else{
						 companydeletion = "0";
					 }
					 
					 
					  existingcommunity = usrgroupForm.getExistingcommunity();			//19
					 if(existingcommunity.equalsIgnoreCase("on")){			 
						 existingcommunity = "1";
					 }
					 else{
						 existingcommunity = "0";
					 }
					 
					 
					  packagemanagement = usrgroupForm.getPackagemanagement();			 //20
					 if(packagemanagement.equalsIgnoreCase("on")){			 
						 packagemanagement = "1";
					 }
					 else{
						 packagemanagement = "0";
					 }
					 
					 
					  smscallotment = usrgroupForm.getSmscallotment();				//21
					 if(smscallotment.equalsIgnoreCase("on")){			 
						 smscallotment = "1";
					 }
					 else{
						 smscallotment = "0";
					 }
					 
					 
					  complaintmanagement = usrgroupForm.getComplaintmanagement();		//22 
					 if(complaintmanagement.equalsIgnoreCase("on")){			 
						 complaintmanagement = "1";
					 }
					 else{
						 complaintmanagement = "0";
					 }
					 
					 
					  comments = usrgroupForm.getComments(); 					//23
					 if(comments.equalsIgnoreCase("on")){			 
						 comments = "1";
					 }
					 else{
						 comments = "0";
					 }
					 
					 
					  company_management = usrgroupForm.getCompany_management();		//24
					 if(company_management.equalsIgnoreCase("on")){			 
						 company_management = "1";
					 }
					 else{
						 company_management = "0";
					 }
					 
					 
					  category = usrgroupForm.getCategory();					 //25
					 if(category.equalsIgnoreCase("on")){			 
						 category = "1";
					 }
					 else{
						 category = "0";
					 }
					 
					 
					  complaints = usrgroupForm.getComplaints();				//26
					 if(complaints.equalsIgnoreCase("on")){			 
						 complaints = "1";
					 }
					 else{
						 complaints = "0";
					 }
					 
					 
					  mis = usrgroupForm.getMis();						//27
					 if(mis.equalsIgnoreCase("on")){			 
						 mis = "1";
					 }
					 else{
						 mis = "0";
					 }
		 
		 
		// }
		 

		  
		 	 rightVec.add(accmanagement);             //0
			 rightVec.add(accountinformation);        //1
			 rightVec.add(billing_information);		//2
			 rightVec.add(change_password);			//3
			 rightVec.add(company_profile_modify);	//4
			 rightVec.add(senderid);					//5
			 rightVec.add(senderid_default);			//6
			 rightVec.add(senderid_deletion);			//7
			 rightVec.add(senderid_creation);			//8
			 rightVec.add(smsallotment);				//9
			 rightVec.add(users);						//10
			 rightVec.add(usercreation);				//11
			 rightVec.add(userdeletion);				//12
			 rightVec.add(usermodification);			//13
			 rightVec.add(order_more);				//14
			 rightVec.add(pay_now);					//15
			 rightVec.add(controlpanel);				//16 
			 rightVec.add(communitycreation);			//17
			 rightVec.add(companydeletion);			//18
			 rightVec.add(existingcommunity);			//19
			 rightVec.add(packagemanagement);			//20
			 rightVec.add(smscallotment);				//21
			 rightVec.add(complaintmanagement);		//22 
			 rightVec.add(comments); 					//23
			 rightVec.add(company_management);		//24
			 rightVec.add(category);					//25
			 rightVec.add(complaints);				//26
			 rightVec.add(mis);						//27
			 
			 rightVec.add(compId);                                      //28
		 	 rightVec.add(usrgroupForm.getGroupName());				 	//29

		  
		  //System.out.println("rightVec,rightVec ...>>>>>>.."+rightVec);
		  
		  
		  
		String result= rootMaster.insertGroupRights(ds, rightVec);
		
		//System.out.println("result,result ...>>>>>>.."+result);
		
		
		if(result.equalsIgnoreCase("success"))
		{
			return mapping.findForward("success");
		}
		else{
			
		return mapping.findForward("failure");
		}
		
	}

}
