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
import com.mm.struts.entp.form.MyPUserDetailForm;


public class MyPUserGetDataAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		MyPUserDetailForm myPUserDeatil = new MyPUserDetailForm();// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String uId =(String)session.getAttribute("uId");		
		
		int intUserId = Integer.parseInt(uId);
	
		RootMaster rootMaster = new RootMaster();
		DataSource ds = getDataSource(request,"entp");
		
		Vector UserData = rootMaster.getUserInfo(ds, intUserId);
		//System.out.println("UserData.....My Profile......"+UserData);		
		
		String UserPhoneNo  = UserData.elementAt(12).toString().trim();
		String uCuntryCode = "";
		String uAreaCode = "";
		String uPhoneNo = "";
		
		if(UserPhoneNo.length()!=0){
		
		StringTokenizer st = new StringTokenizer(UserPhoneNo,"~");		
		 uCuntryCode = st.nextToken();
		 uAreaCode = st.nextToken();
		 uPhoneNo = st.nextToken();
		}
		
		
		String UserMobileNo = UserData.elementAt(10).toString().trim();
		StringTokenizer st1 = new StringTokenizer(UserMobileNo,"~");		
		String umCuntryCode = st1.nextToken();
		String umNo = st1.nextToken();
		
		
		myPUserDeatil.setLoginName(UserData.elementAt(2).toString());
		myPUserDeatil.setFname(UserData.elementAt(4).toString());
		myPUserDeatil.setLname(UserData.elementAt(5).toString());
		myPUserDeatil.setAddress(UserData.elementAt(9).toString());
		
		myPUserDeatil.setZip(UserData.elementAt(13).toString());
		myPUserDeatil.setEmail(UserData.elementAt(8).toString());
		
		if(uCuntryCode.length()==0)
		{
			myPUserDeatil.setTxtPPrefix(umCuntryCode);
		}else{
			myPUserDeatil.setTxtPPrefix(uCuntryCode);
		}
		myPUserDeatil.setTxtUACPrefix(uAreaCode);
		myPUserDeatil.setTxtPhone(uPhoneNo);
		myPUserDeatil.setTxtMobile(umCuntryCode);
		myPUserDeatil.setMobile(umNo);
		myPUserDeatil.setRid(UserData.elementAt(1).toString());
		
		myPUserDeatil.setPhotoPath(UserData.elementAt(14).toString());
		//System.out.println("setPhotoPath.....mPUser My Profile......"+UserData.elementAt(14).toString());
		
		session.setAttribute("pid",UserData.elementAt(6).toString());
		session.setAttribute("sid",UserData.elementAt(7).toString());
		session.setAttribute("cid",UserData.elementAt(11).toString());
		
		
		
		
		
		request.setAttribute("entpMyPUserDetailForm", myPUserDeatil);
		
		return mapping.findForward("success");
	}

}
