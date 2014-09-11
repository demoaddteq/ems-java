package com.mm.struts.entp.action;

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
import com.mm.struts.entp.form.UserdetailsForm;
public class UserGetdetailsAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserdetailsForm userDetailForm = new UserdetailsForm();// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String userId = (request.getParameter("userid")!=null)? request.getParameter("userid").trim() : "0";
		int intUserId = Integer.parseInt(userId);
		
		String task = (request.getParameter("task")!=null)? request.getParameter("task").trim() : "Modify";
		//System.out.println("actionTask...1...."+task);
		
		RootMaster rootMaster = new RootMaster();
		DataSource ds = getDataSource(request,"entp");
		
		Vector UserData = rootMaster.getUserInfo(ds, intUserId);
		
		
		
		String UserPhoneNo = UserData.elementAt(12).toString().trim();
		String CuntryCode = "";
		String AreaCode = "";
		String PhoneNo = "";
		////System.out.println("UserPhoneNo.length()....My Profile......."+UserPhoneNo.length());
		
		if(UserPhoneNo.length()!=0){		 
		StringTokenizer st1 = new StringTokenizer(UserPhoneNo,"~");		
		 CuntryCode = st1.nextToken();
		 AreaCode = st1.nextToken();
		 PhoneNo = st1.nextToken();
		}
		
		String mobileNo = UserData.elementAt(10).toString().trim();
		StringTokenizer st = new StringTokenizer(mobileNo,"~");		
		String Code = st.nextToken();
		String no = st.nextToken();
		
		String password = UserData.elementAt(3).toString().trim();
		String strPass ="";
		for(int i=0; i < password.length(); i++){
			strPass = strPass +"*";
		}
			
		userDetailForm.setLoginName(UserData.elementAt(2).toString());
		userDetailForm.setPassword(strPass);
		userDetailForm.setFname(UserData.elementAt(4).toString());
		userDetailForm.setLname(UserData.elementAt(5).toString());
		userDetailForm.setEmail(UserData.elementAt(8).toString());
		userDetailForm.setAddress(UserData.elementAt(9).toString());		
		userDetailForm.setTxtPPrefix(CuntryCode);
		userDetailForm.setTxtAreacode(AreaCode);
		userDetailForm.setTxtPhone(PhoneNo);		
		userDetailForm.setTxtPrefix(Code);
		userDetailForm.setTxtMobile(no);		
		userDetailForm.setZip(UserData.elementAt(13).toString());
		userDetailForm.setUserid(userId);
		userDetailForm.setTask(task);
		
		String strUserGroup = UserData.elementAt(1).toString().trim();
		
		session.setAttribute("pid",UserData.elementAt(6).toString());
		session.setAttribute("sid",UserData.elementAt(7).toString());
		session.setAttribute("cid",UserData.elementAt(11).toString());
		session.setAttribute("usergrp", strUserGroup);
		//System.out.println("strUserGroup...userGetdetailAction......"+strUserGroup);
		
		
		
		request.setAttribute("entpUserdetailsForm", userDetailForm);
		
		return mapping.findForward("success");
	}

}
