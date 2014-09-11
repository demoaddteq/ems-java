package com.mm.struts.corpo.action;

import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
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
import com.mm.core.master.IndvMaster;
import com.mm.core.master.RootMaster;
import com.mm.core.resource.Constant;
import com.mm.core.resource.MailText;
import com.mm.core.resource.Resource;
import com.mm.struts.student.form.MentoringRequestForm;

public class MentoringRequestAction extends Action {
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		MentoringRequestForm writeRequestForm = (MentoringRequestForm) form;// TODO Auto-generated method stub
		RootMaster rootMaster = new RootMaster();
		HttpSession session= request.getSession();	
		IndvMaster indvMaster = new IndvMaster();
		
		String result = "failure";
		if(((String)session.getAttribute("flagform")).equalsIgnoreCase("1"))
		{
			
		String categoryName="",brandName="",countryName="", stateName="",cityName="";
		//set all form data in a vector and transfer this vector to the complaint preview page.
		//and set status success for that action.
		DataSource dataSource = getDataSource(request, "student");
		
		
		String uId =(String)session.getAttribute("uId");
			
		//Vector userlogindata = (Vector)session.getAttribute("userlogindata");
		//////System.out.println("userlogindata >>>>>>>>>>>>"+userlogindata);
		
		
		
		String qcat = writeRequestForm.getQcat().toString().trim();	
		String brand = writeRequestForm.getBrand().toString().trim();
		
		/*
		 * qcat    
		 * brand   			`advt_id`
		 * cons				`advtL_id`
		 * blogTitle		`complaint_title`
		 * blogtext			`complaint_text`
		 * qtype			`q_type`
		 */
	
	
		
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
	    String creationDate = sttotal.nextToken();
	    String creationTime = sttotal.nextToken();
	    String timePart = sttotal.nextToken();
	    String entpId = String.valueOf(indvMaster.getCompanyId(dataSource,"Enterprise","mm"));
	    String fcu_id = "1";
	   
	    
	    String compId = String.valueOf(indvMaster.getCompanyId(dataSource,Integer.parseInt(uId)));
	    
	    String publishDate = "0000-00-00";
	    String publishTime = "00:00:00";
	    String publishFlag = "1";
	    String publishAt = "0@Week";
		
		Vector<String> complaintVec =  new Vector<String>();
		complaintVec.add(writeRequestForm.getBlogTitle().toString().trim());	//blog Title
		complaintVec.add(writeRequestForm.getBlogtext().toString().trim());	//blog Text
		complaintVec.add("N/A");	//Relevent Text
		complaintVec.add(qcat);						//Category
		complaintVec.add(creationDate);						//Creation Date
		complaintVec.add(publishDate);						//Publish Date
		complaintVec.add(creationTime);						//Creation Time
		complaintVec.add(publishTime);						//Publish Time
		complaintVec.add("0000-00-00");						//Last Modify Date
		complaintVec.add("00:00:00");						//Last Modify Time
		complaintVec.add(uId);								//User (Complainent id) LoginId()uId
		complaintVec.add(compId);							//User Company Id
		complaintVec.add(timePart);							//Time Part
		complaintVec.add("0");								//View Count
		complaintVec.add(entpId);							//Entp Id(Complaint handler company Id)
		complaintVec.add(fcu_id);							//cu_Id(Complaint handler User Id)
		complaintVec.add(brand);							//advtId(Company Id of brand) 
		complaintVec.add(writeRequestForm.getCons().toString().trim());							//advtlId(Company user Id of brand) 
		complaintVec.add("0");							//Dealer Id
		complaintVec.add("2");							    //Tag Id
		complaintVec.add("0000-00-00");						//Resolved Date
		complaintVec.add("0000-00-00");						//Closed Date
		complaintVec.add("0");								//Brand Flag
		complaintVec.add(publishFlag);						//Publish Flag
		complaintVec.add(publishAt);						//Publish On 
		complaintVec.add("0");						//otherBranddataVec.elementAt(17).toString()
		complaintVec.add(writeRequestForm.getQtype().toString().trim());	//Query type
		//////System.out.println("complaintVec.............SubmitComplaintAction........... "+complaintVec);
		
		
		String strComplResult = indvMaster.insertNewComplaints(dataSource, complaintVec);
		
		//////System.out.println("strComplResult.............SubmitComplaintAction........... "+strComplResult);
		String fcomId = "";
		String result1 = "";
		
		if(strComplResult.equalsIgnoreCase("success"))
		{
			//get complaint Id of this new complaint and set fcom Id for this complaint
			fcomId = indvMaster.getFinalComplainId(dataSource, Integer.parseInt(uId), writeRequestForm.getBlogTitle().toString().trim(), creationTime);
			String fcomId1 = indvMaster.getComplainId(dataSource, Integer.parseInt(uId), writeRequestForm.getBlogTitle().toString().trim(), creationTime);
			String fcomarr[] = fcomId1.split("/");
			Vector<String> tempCompVec = new Vector<String>();
			tempCompVec.add(fcomId);//final compaint id
			tempCompVec.add(fcomarr[0]);//complaint id
			result1 = indvMaster.setFinalId(dataSource, tempCompVec);
		}
		
		if(result1.equalsIgnoreCase("success"))
		{
			
			EntpMaster entpMaster = new EntpMaster();
			
			
			Vector<String> paramVec = new Vector<String>();
			
			////////System.out.println(" to Enterprise.............mail");
			paramVec.add(uId);
			paramVec.add(writeRequestForm.getCons().toString().trim());
			
			Vector emailVec = indvMaster.getEmailList(getDataSource(request,"student"), paramVec);
			//////System.out.println(" emailVec..........."+emailVec);
			
			Vector<String> tempMailParam = new Vector<String>();
			tempMailParam.add("Student");//sender company type Consumer
			tempMailParam.add("Corporates");//receipient company type Enterprise
			tempMailParam.add("Mentoring");//mail description
			
			//get mail text alert fot sending mail to core user and admin core user
			String mailTextLAlert = rootMaster.getMailText(getDataSource(request,"student"), tempMailParam);
			Vector consumerdVec = (Vector)emailVec.elementAt(0);
			String consumerName =consumerdVec.elementAt(0).toString()+" "+consumerdVec.elementAt(1).toString();
			String consumermail = consumerdVec.elementAt(2).toString().trim();
			//String subject = "Request for mentoring, Request Id ("+ fcomId + ") By "+ consumerName + ".";
			String subject = "Mentoring request from "+ consumerName + ".";
			for(int i=1; i<emailVec.size();i++)
			{
				MailText mt = new MailText();
				Resource resr = new Resource();
				Vector coreMailVec = (Vector)emailVec.elementAt(i);
				String strMailText = mt.getMentoringReqByStudent(coreMailVec,fcomId,mailTextLAlert,consumerName, writeRequestForm.getBlogTitle().toString().trim(),writeRequestForm.getBlogtext().toString().trim(),compId);
				//////System.out.println("strMailText..............."+strMailText);
				
				String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender,((Vector)emailVec.elementAt(i)).elementAt(2).toString() , subject);
				//////System.out.println("str Email status of consumer"+strEmailStatus);
			}
		}

			
				 if(result1.equalsIgnoreCase("success"))
							{
								ActionErrors msg = new ActionErrors();
								msg.clear();
								msg.add("delete", new ActionError("std.MentoringRequest.submitSuccess"));
								
								saveErrors(request, msg);
								
							}
							else
							{
								ActionErrors msg = new ActionErrors();
								msg.clear();
								msg.add("delete", new ActionError("std.MentoringRequest.submitFailure"));
								
								saveErrors(request, msg);
								
							}
								
		if(result1.equalsIgnoreCase("success")){
			session.setAttribute("flagform","0");
			
		}
		
		return mapping.findForward(result1);
		
	}else{
			
			return mapping.findForward("success");
		}
	}

}
