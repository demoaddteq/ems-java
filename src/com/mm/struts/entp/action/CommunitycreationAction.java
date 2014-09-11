package com.mm.struts.entp.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.struts.entp.form.CommunitycreationForm;

import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.Vector;
import com.mm.core.master.*;

import javax.sql.DataSource;
import java.util.*;

/** 
 * MyEclipse Struts
 * Creation date: 07-30-2007
 * 
 * XDoclet definition:
 * @struts.action path="/communitycreation" name="CommunitycreationForm" input="/form/communitycreation.jsp" scope="request" 
 * @struts.action-forward name="success" path="communitycreation.jsp"
 */
public class CommunitycreationAction extends Action {
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
		CommunitycreationForm communitycreationForm = (CommunitycreationForm) form;// TODO Auto-generated method stub
		
		EntpMaster entpMaster = new EntpMaster();
		RootMaster rootMaster = new RootMaster();
		String strStatus="failure";
		//System.out.println(" in action Communitycreation>>>>>!!");
		
		DataSource ds = getDataSource(request,"entp");
		
		String strResult = "";
		
		 strResult=entpMaster.companyVerify(ds,communitycreationForm.getCommunityName());
		 //System.out.println("strResult in action Communitycreation>>>>>!!"+strResult);
		 
		if(!strResult.equals("success"))
		{
			if(strResult.equals("sname"))
			{
				ActionErrors errors = new ActionErrors();
				errors.add("duplicate", new ActionError("errors.company.duplicate"));
				
				if(!errors.isEmpty())
				{
					saveErrors(request, errors);
				}
				strStatus="failure";
				return mapping.findForward("failure");
			}
		}
		else{
			strStatus="success";
		}
		
		
		//System.out.println("strStatus in action Communitycreation>>>>>"+strStatus);
		
		if(strStatus.equalsIgnoreCase("success")){
			
		
		Vector <String> companyVec = new Vector<String>();
		Vector <String> userVec = new Vector<String>();
		
		String creationDate ="";
		String creationTime ="";
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
	    creationDate = sttotal.nextToken();
	    creationTime = sttotal.nextToken();
	    
		
		companyVec.add(communitycreationForm.getCommunityName());  //Company short name
		companyVec.add(communitycreationForm.getCommunityName());	//Company name
		companyVec.add("Logix Park");
		companyVec.add("Logix Park");
		companyVec.add("5115");
		companyVec.add("94");
		companyVec.add("94");
		companyVec.add("201301");
		companyVec.add(communitycreationForm.getCommunityName()+"@mobilemantra.com");
		companyVec.add("+91~0568~258047");
		companyVec.add("0");
		companyVec.add("10");
		companyVec.add("0");
		companyVec.add("1");
		companyVec.add("0000-00-00");
		companyVec.add("0");
		companyVec.add("1");
		companyVec.add("Consumer");
		companyVec.add("NotRequired");
		companyVec.add("2");
		companyVec.add(communitycreationForm.getRdbgroup()); // RIGHTS ID
		companyVec.add(creationDate);
		companyVec.add(creationTime);
		companyVec.add("0");
		companyVec.add("1");
		//System.out.println("communitycreationForm.getRdbgroup() in action Communitycreation>>>^^^^^^^>>"+communitycreationForm.getRdbgroup());
		strResult=rootMaster.insertCompanyDetail(ds,companyVec);
		
		/*
		Vector companyDetailVec=rootMaster.getCompanyDetail(ds,communitycreationForm.getCommunityName());
		
			if(strResult.equalsIgnoreCase("success"))
			{
				
				userVec.add(companyDetailVec.elementAt(0).toString());
				userVec.add("1");
				userVec.add(communitycreationForm.getCommunityName());
				userVec.add(communitycreationForm.getCommunityName());
				userVec.add(communitycreationForm.getCommunityName());
				userVec.add(communitycreationForm.getCommunityName());
				userVec.add(communitycreationForm.getCommunityName()+"@mobilemantra.com");
				userVec.add("+911202517690");
				userVec.add("+911202517690");
				userVec.add("Logix Park");
				userVec.add("India");
				userVec.add("UP");
				userVec.add("NOIDA");
				userVec.add("201301");
				userVec.add("nophoto.jpg");
				userVec.add("IN");
				userVec.add(creationDate);
				userVec.add(creationTime);
				userVec.add("0000-00-00");
				userVec.add("00:00:00");
				userVec.add("0");
				userVec.add("20");
				userVec.add("0");
				userVec.add("0");
				userVec.add("0");
				userVec.add("0");
				userVec.add("0");
				userVec.add("0");
				userVec.add("0");
				userVec.add("0");
				userVec.add("0");
				userVec.add("0");
				userVec.add("Male");
				//System.out.println("userVec in action Communitycreation>>>>>"+userVec);
				strResult=rootMaster.insertUserInfo(ds,userVec);
				
			}
			*/
		}
		
		
		return mapping.findForward(strResult);
	}
}