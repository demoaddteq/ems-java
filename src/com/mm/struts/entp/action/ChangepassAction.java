package com.mm.struts.entp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.struts.entp.form.ChangepassForm;
import com.mm.core.master.*;
import com.mm.core.resource.Constant;
import com.mm.core.resource.MailText;
import com.mm.core.resource.Resource;

import javax.sql.DataSource;
import java.util.*;

/** 
 * MyEclipse Struts
 * Creation date: 06-11-2007
 * 
 * XDoclet definition:
 * @struts.action path="/changepass" name="ChangepassForm" input="/form/Changepass.jsp" scope="request" 
 * @struts.action-forward name="success" path="changepass.jsp"
 */

public class ChangepassAction extends Action {
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
		ChangepassForm changepassForm = (ChangepassForm) form;// TODO Auto-generated method stub
		
		Vector <String> datavec = new Vector<String>();		
		
		DataSource ds = getDataSource(request,"entp");
		RootMaster rootMaster = new RootMaster();
		String currentPassword = "";
		String result = "result";
		String userId = (request.getParameter("userId")!=null)? request.getParameter("userId").trim() : (String)request.getAttribute("userId"); 
		request.setAttribute("userId", userId);
		int intUserId = Integer.parseInt(userId);
		//System.out.println("userId....on ....ChangepassAction....."+userId);
		
		currentPassword = rootMaster.getPassWord(ds,intUserId);
		if(!currentPassword.equalsIgnoreCase(changepassForm.getCurrentPass().toString()))
		{
			ActionErrors errors = new ActionErrors();
			errors.add("notExistPassword", new ActionError("errors.user.notExistPassword"));
			
				saveErrors(request, errors);
			
			return mapping.findForward("failuer");
			
		}
		
		if(currentPassword.equals(changepassForm.getCurrentPass().toString())){
		
		datavec.add(userId);//0				
		datavec.add(changepassForm.getPassWord());//1
		
		result = rootMaster.changPassWord(ds,datavec);
			if(result.equalsIgnoreCase("success")){
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("changPassWord", new ActionError("errors.entp.changPassWordSuccess"));
				saveErrors(request, errors);
				result = "success";
			}
		
		}
		
		
		Vector<String> paramVec = new Vector<String>();
		IndvMaster indvMaster = new IndvMaster();
		
		if(!result.equalsIgnoreCase("failure"))
		{
			paramVec.add(userId);			
			Vector emailVec = indvMaster.getEmailList(ds, paramVec);
			Vector UserVec = (Vector)emailVec.elementAt(0);
			String userName =UserVec.elementAt(0).toString()+" "+UserVec.elementAt(1).toString();
			String usermail = UserVec.elementAt(2).toString().trim();
			String subject = "Your Password is changed";
			
			Vector userVec = rootMaster.getUserInfo(ds, Integer.parseInt(userId));
			String loginName = userVec.elementAt(2).toString();
			
			Resource resr = new Resource();
			MailText mt = new MailText();
			String strMailText = "";
			strMailText = mt.getChangedPassWord(userName, changepassForm.getPassWord(), loginName);
			result = resr.sendMail(strMailText, Constant.Email_Sender,usermail , subject);
			//System.out.println("result>>>mail>>entp>>>"+result);
		}
		
			
	
		if(result.equalsIgnoreCase("success"))
		{
			return mapping.findForward("success");
		}
		else{
			
		return mapping.findForward("failure");
		}
	}
}
