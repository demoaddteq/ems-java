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
import com.mm.struts.entp.form.CompanyDetailsForm;


public class CompanyGetDataAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		CompanyDetailsForm companyDetailsForm = new CompanyDetailsForm();// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String companyId = (request.getParameter("companyId")!=null)? request.getParameter("companyId").trim() : "0";
		String task = (request.getParameter("task")!=null)? request.getParameter("task").trim() : "Modify";
		
		
		int intCompId = Integer.parseInt(companyId);		
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
		
		String industryCategory = companyData.elementAt(10).toString().trim();
		System.out.println("industryCategory..........."+industryCategory);
		
		
		
		
		
		companyDetailsForm.setCompanySName(companyData.elementAt(0).toString());
		companyDetailsForm.setCompanyName(companyData.elementAt(1).toString());
		companyDetailsForm.setCompanyAddress1(companyData.elementAt(2).toString());
		companyDetailsForm.setCompanyAddress2(companyData.elementAt(3).toString());
		
		companyDetailsForm.setCompanyPostalCode(companyData.elementAt(7).toString());
		companyDetailsForm.setCompanyEMail(companyData.elementAt(8).toString());		
		companyDetailsForm.setTxtCPPrefix(CCuntryCode);
		companyDetailsForm.setTxtCACPrefix(CAreaCode);
		companyDetailsForm.setCphone(CPhoneNo);
		
		companyDetailsForm.setCompanyId(companyId);
		companyDetailsForm.setIndustryCategory(industryCategory);
		companyDetailsForm.setTask(task);
		
		session.setAttribute("pid",companyData.elementAt(4).toString());
		session.setAttribute("sid",companyData.elementAt(5).toString());
		session.setAttribute("cid",companyData.elementAt(6).toString());
		
		request.setAttribute("category", industryCategory);
		
		request.setAttribute("entpCompanyDetailsForm", companyDetailsForm);
		
		return mapping.findForward("success");
	}

}
