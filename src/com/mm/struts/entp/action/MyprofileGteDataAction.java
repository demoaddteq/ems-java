package com.mm.struts.entp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.struts.entp.form.MyprofileForm;
import com.mm.core.master.*;

import javax.sql.DataSource;
import java.util.*;


public class MyprofileGteDataAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		MyprofileForm myprofileGetDataForm = new MyprofileForm();// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
		
		int intUserId = Integer.parseInt(uId);
		int intCompId = Integer.parseInt(compId);
		//System.out.println("intUserId....My Profile......."+intUserId);
		//System.out.println("intCompId....My Profile......."+intCompId);
		
	
		RootMaster rootMaster = new RootMaster();
		DataSource ds = getDataSource(request,"entp");
		
			
		Vector companyData = rootMaster.getCompanyDetail(ds, intCompId);
		//System.out.println("companyData....My Profile......."+companyData);
		
		
		
		String CserPhoneNo = companyData.elementAt(9).toString().trim();
		StringTokenizer stC = new StringTokenizer(CserPhoneNo,"~");		
		String CCuntryCode = stC.nextToken();
		String CAreaCode = stC.nextToken();
		String CPhoneNo = stC.nextToken();
		
		myprofileGetDataForm.setCompanySName(companyData.elementAt(0).toString());
		myprofileGetDataForm.setCompanyName(companyData.elementAt(1).toString());
		myprofileGetDataForm.setCompanyAddress1(companyData.elementAt(2).toString());
		myprofileGetDataForm.setCompanyAddress2(companyData.elementAt(3).toString());
		//myprofileGetDataForm.setCompanyCity(companyData.elementAt(4).toString());
		//myprofileGetDataForm.setCompanyState(companyData.elementAt(5).toString());
		//myprofileGetDataForm.setCcountry(companyData.elementAt(6).toString());
		myprofileGetDataForm.setCompanyPostalCode(companyData.elementAt(7).toString());
		myprofileGetDataForm.setCompanyEMail(companyData.elementAt(8).toString());		
		myprofileGetDataForm.setTxtCPPrefix(CCuntryCode);
		myprofileGetDataForm.setTxtCACPrefix(CAreaCode);
		myprofileGetDataForm.setCphone(CPhoneNo);
		
		
		session.setAttribute("pid",companyData.elementAt(4).toString());
		session.setAttribute("sid",companyData.elementAt(5).toString());
		session.setAttribute("cid",companyData.elementAt(6).toString());
		
		
		
		request.setAttribute("entpMyprofileForm", myprofileGetDataForm);
		
		return mapping.findForward("success");
	}

}
