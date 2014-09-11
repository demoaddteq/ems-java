package com.mm.struts.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.struts.form.ForgotpasswordForm;
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
public class ForgotpasswordAction extends Action {
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
		ForgotpasswordForm forgotpasswordForm = (ForgotpasswordForm) form;// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String type = session.getAttribute("type").toString().trim();
		RootMaster rootMaster = new RootMaster();
		Vector <String> datavec = new Vector<String>();		
		DataSource DataSource = getDataSource(request,"main");			
		String result = null;
		datavec.add(forgotpasswordForm.getFuName());//0
		datavec.add(forgotpasswordForm.getFemailadd());//1
		datavec.add(type);//2
		System.out.println("type in forget action>>"+type);
		Vector resultVec =new Vector();
		if(type.equalsIgnoreCase("outer"))
		{
			 resultVec = rootMaster.forgetPasswordBlog(DataSource,datavec);
			
		}else
		{
			 resultVec = rootMaster.forgetPassword(DataSource,datavec);
		}
			
		////System.out.println("resultVec in forget action>>"+resultVec);
		if(!resultVec.elementAt(0).toString().trim().equalsIgnoreCase("failure"))
		{
			Resource resr = new Resource();
			MailText mt = new MailText();
			String strMailText = "";
			strMailText = mt.getETextForFogetPass(forgotpasswordForm.getFuName(),resultVec);
			result = resr.sendMail(strMailText, Constant.Email_Sender,forgotpasswordForm.getFemailadd() , Constant.Email_Title_For_Forget);
			
			
			////System.out.println("result>>>>>>>>>"+result);
		}
		else 
		{
			ActionErrors errors = new ActionErrors();
			errors.add("wrong", new ActionError("errors.forget.wrong"));
			if(!errors.isEmpty())
			{
				saveErrors(request, errors);
			}
			result="failure";
			
		}
		request.setAttribute("uName", forgotpasswordForm.getFuName());
		return mapping.findForward(result);
	}
}