package com.mm.struts.action;

import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.RootMaster;
import com.mm.core.resource.Constant;
import com.mm.core.resource.MailText;
import com.mm.core.resource.Resource;
import com.mm.struts.form.FeedbackForm;

public class FeedbackAction extends Action {
	
	
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
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		DataSource dataSource = getDataSource(request,"main");
		RootMaster rootMaster= new RootMaster();
		HttpSession session = request.getSession();
		
		String result ="";
		
		String flag = (String)session.getAttribute("feedbackFlag");
		
		FeedbackForm feedbackForm = (FeedbackForm) form;// TODO Auto-generated method stub
		
		if(flag.equalsIgnoreCase("1")){
		
		 String name = feedbackForm.getNamec().toString().trim();
		 String userid= feedbackForm.getUserid().toString().trim();
		 String emailid= feedbackForm.getEmailid().toString().trim();
		 String feedbacktype= feedbackForm.getFeedbacktype().toString().trim();
		 String subject= feedbackForm.getSubject().toString().trim();
		 String text= feedbackForm.getText().toString().trim(); 
		 String creationDate = "";
		 String creationTime = "";
		 
		 
		 	java.util.Date dt = new java.util.Date();
			SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
			String completeRemDate = sform.format(dt);
			StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
		    creationDate = sttotal.nextToken();
		    creationTime = sttotal.nextToken();
		    sttotal.nextToken();
		    
		    Vector<String>dataVec= new Vector<String>();
		    
		    dataVec.add(name);
		    dataVec.add(userid);
		    dataVec.add(emailid);
		    dataVec.add(feedbacktype);
		    dataVec.add(subject);
		    dataVec.add(text);
		    dataVec.add(creationDate);
		    dataVec.add(creationTime);
		    
		    result = rootMaster.insertFeedback(dataSource, dataVec);
		    
		    
		    Resource resr = new Resource();
			MailText mt = new MailText();
			String strMailText = "";
		    
		    strMailText = mt.getEmailTextFeedBack(name, emailid, feedbacktype, subject, text );
						
			String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender, "feedback@campusyogi.com", "feedback");
			////////////System.out.println("str Email Status"+strEmailStatus);
			
		    
		    System.out.println("strMailText.......FeedbackAction.........."+strMailText);
		    System.out.println("strEmailStatus.......FeedbackAction.........."+strEmailStatus);
		    
		}else{
			
			result = "success";
		}
		
		
		return mapping.findForward(result);
	}

}
