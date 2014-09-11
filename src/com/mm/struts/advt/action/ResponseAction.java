package com.mm.struts.advt.action;


import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.*;

import com.mm.core.resource.Constant;
import com.mm.core.resource.MailText;
import com.mm.core.resource.Resource;
import com.mm.struts.advt.form.ResponseForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-27-2007
 * 
 * XDoclet definition:
 * @struts.action path="/changepass" name="ChangepassForm" input="/form/Changepass.jsp" scope="request" 
 * @struts.action-forward name="success" path="changepass.jsp"
 */
public class ResponseAction extends Action {
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
		ResponseForm responseForm = (ResponseForm) form;// TODO Auto-generated method stub
		RootMaster rootMaster = new RootMaster();
		AdvtMaster advtMaster = new AdvtMaster();
		HttpSession session = request.getSession();
		String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
		Vector allRights =(Vector)session.getAttribute("allRights");
		String adminFlag = allRights.elementAt(18).toString();
		if(((String)session.getAttribute("flag1")).equalsIgnoreCase("1"))
		{
			
		
		
		Vector<String> dataVec = new Vector<String>();
		String responseTextt = responseForm.getResponsetext();
		String respId = request.getParameter("respid");
		String complaintId = request.getParameter("compId");
		String recpType = (request.getParameter("respType")!=null) ? request.getParameter("respType") : "";
		String flag = "";
		if(request.getParameter("flag")!= null)
		{
			flag = request.getParameter("flag").trim();
		}
		else
		{
			flag = "0";
		}
		String uid = (String)session.getAttribute("uId");
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
	    String creationDate = sttotal.nextToken();
	    String creationTime = sttotal.nextToken();
	    
		dataVec.add(responseTextt);
		dataVec.add(creationDate);
		dataVec.add(complaintId);
		dataVec.add(uid);
		dataVec.add(creationTime);
		dataVec.add(respId);
		dataVec.add(flag);
		dataVec.add("0"); 
		dataVec.add("1");
		dataVec.add("0");
				
		String result = rootMaster.insertResponse(getDataSource(request,"advt"),dataVec);
		
		if((result.equalsIgnoreCase("success"))&&(!respId.equalsIgnoreCase("0"))){
			ActionErrors errors = new ActionErrors();
           	errors.clear();
          	errors.add("reply", new ActionError("errors.entp.replySuccess"));
			saveErrors(request, errors);
			result = "success";
		}else if (result.equalsIgnoreCase("success")){
			ActionErrors errors = new ActionErrors();
           	errors.clear();
          	errors.add("response", new ActionError("errors.entp.responseSuccess"));
			saveErrors(request, errors);
			result = "success";
		}
		
		if(result.equalsIgnoreCase("success")){
		if(!respId.equalsIgnoreCase("0"))
		  {
		   Vector<String> respVec = new Vector<String>();
		   respVec.add(respId);
		   respVec.add("advt");
		   //update `entp_rflag` for communication table.
		   result = rootMaster.updateResponseFlag(getDataSource(request,"advt"), respVec);
		  }
		}
		
		//////////////for mail///////////////
		if(result.equalsIgnoreCase("success"))
		{
			
			EntpMaster entpMaster = new EntpMaster();
			IndvMaster indvMaster = new IndvMaster();
			Vector<String> complaintVec = new Vector<String>();
			int cid = Integer.parseInt(complaintId);
			complaintVec = entpMaster.getComplaintDetails(getDataSource(request,"advt"), cid);
			String login_id = complaintVec.elementAt(3).toString();
			String cu_id = complaintVec.elementAt(6).toString();
			String fcomId = complaintVec.elementAt(7).toString();
			
			Vector<String> paramVec = new Vector<String>();
			
			////System.out.println("to Enterprise.............mail");
			paramVec.add(uid);
			paramVec.add(login_id);
			if(!cu_id.equalsIgnoreCase("0")){
				paramVec.add(cu_id);
				//////System.out.println(" to Enterprise..in if...........mail");
				}
			
			Vector emailVec = indvMaster.getEmailList(getDataSource(request,"advt"), paramVec);
			Vector<String> tempMailParam = new Vector<String>();
			tempMailParam.add("Advertiser");//sender company type Advertiser
			tempMailParam.add("Student");//receipient company type Enterprise
			if(respId.equalsIgnoreCase("0")){
				////System.out.println("Response if status in brand");
				tempMailParam.add("Response");//mail description
				}else{
					////System.out.println("Reply else status in brand");
					tempMailParam.add("Reply");//mail description
				}
				
			//get mail text alert fot sending mail to core user and admin core user
			String mailTextLAlert = rootMaster.getMailText(getDataSource(request,"advt"), tempMailParam);
			Vector brandVec = (Vector)emailVec.elementAt(0);
			String brandName =brandVec.elementAt(0).toString()+" "+brandVec.elementAt(1).toString();
			String brandmail = brandVec.elementAt(2).toString().trim();
			String subject = "";
			if(respId.equalsIgnoreCase("0")){
				
				 subject = "Response on Querie Id ("+ fcomId + ") By "+brandName+".";
				}else{
					
					 subject = "Reply to your mail on Querie Id ("+ fcomId + ") By "+brandName+".";
				}
			
			
			for(int i=1; i<emailVec.size();i++)
			{
				MailText mt = new MailText();
				Resource resr = new Resource();
				Vector coreMailVec = (Vector)emailVec.elementAt(i);
				String strMailText = mt.getReplyByBrand(coreMailVec,fcomId,mailTextLAlert,brandName);
				
				String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender,((Vector)emailVec.elementAt(i)).elementAt(2).toString() , subject);
				////System.out.println("str Email status in brand"+strEmailStatus);
			}
			
			
		}
		/////////////////end mail////////////
		
		session.setAttribute("numCid", complaintId);	
		session.setAttribute("strResult", result);
		
		Vector dataVec1 = new Vector();		
		dataVec1.add(uId);
		dataVec1.add(compId);
		dataVec1.add(adminFlag);			
		Vector advtCountVec = advtMaster.complaintCountAdvt(getDataSource(request, "advt"), dataVec1);		
		session.setAttribute("advtCountVec", advtCountVec);						
		////System.out.println("advtCountVec.............ResponseAction........... "+advtCountVec);
		if(result.equalsIgnoreCase("success")){
			session.setAttribute("flag1","0");
			
		}
		return mapping.findForward(result);
		}else{
			
			return mapping.findForward("success");
	}
	
}	
}