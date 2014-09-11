package com.mm.struts.entp.action;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.*;
import com.mm.struts.entp.form.MgtInfoForm;


public class MgtInfoPreAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		MgtInfoForm mgtInfoForm = new MgtInfoForm();// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();		
		String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");		
		int intUserId = Integer.parseInt(uId);
		int intCompId = Integer.parseInt(compId);
		
		//System.out.println("intUserId..........."+intUserId);
		//System.out.println("intCompId..........."+intCompId);
		
	
		EntpMaster entpMaster = new EntpMaster();
		
		DataSource ds = getDataSource(request,"entp");
		
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
		String strcurrDate = sttotal.nextToken();
		//String strcurrTime = sttotal.nextToken();
		
		//this function use for getting no of complaints received daily
		int compDaily = entpMaster.getComplaints(ds, strcurrDate);
		//System.out.println("compDaily......"+compDaily);
		//this function use for getting total number of companits online published
		int	pubComp = entpMaster.getPublishedComplaints(ds);
		//System.out.println("pubComp......"+pubComp);
		//this function use for getting no. of complaints responded
		int	resComp = entpMaster.getRespondedComplaints(ds);
		//System.out.println("resComp......"+resComp);
		//this function use for getting total number of complaints responded daily
		int	respCompDaily = entpMaster.getRespondedComplaintsDaily(ds, strcurrDate);
		//System.out.println("respCompDaily......"+respCompDaily);
		//this function use for getting no. of brands against whom complaints are filed 
		int	brands = entpMaster.getBrandCount(ds);
		//System.out.println("brands......"+brands);		
		//this function use for getting no. of brands against whom complaints are pending
		int	brandsPending = entpMaster.getBrandPending(ds);
		//System.out.println("brandsPending......"+brandsPending);	
		/*this function use for getting no. of brands who are registered or Not registered  according to coming parameter
				active =0 for Not Registered Brand
				active =1 for registered Brand*/
		int	regBrand = entpMaster.getRegisteredBrand(ds, 1);
		//System.out.println("regBrand......"+regBrand);
		int	notRegBrand = entpMaster.getRegisteredBrand(ds, 0);
		//System.out.println("notRegBrand......"+notRegBrand);
		
		String strData = "";
		
		strData = strData +compDaily +","+pubComp+","+resComp+","+respCompDaily+","+brands+","+brandsPending+","+regBrand+","+notRegBrand;
		
		
		request.setAttribute("strData", strData);
		session.setAttribute("strData", strData);
		mgtInfoForm.setStrInfo(strData);
		//System.out.println("strData......"+strData);
		
		
		request.setAttribute("entpMgtInfoForm", mgtInfoForm);
		
		return mapping.findForward("success");
	}

}
