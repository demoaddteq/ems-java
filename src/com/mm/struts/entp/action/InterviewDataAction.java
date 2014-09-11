package com.mm.struts.entp.action;


import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.core.master.*;
import com.mm.struts.entp.form.InterviewDataForm;


public class InterviewDataAction extends Action {
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		InterviewDataForm corpoDataForm = (InterviewDataForm) form;// TODO Auto-generated method stub
		//SavestuResultForm savestuResultForm = (SavestuResultForm)form;
		
	//	RootMaster rootMaster = new RootMaster();
		HttpSession session= request.getSession();	
		IndvMaster indvMaster = new IndvMaster();
		String uId =(String)session.getAttribute("uId");
		
		DataSource dataSource = getDataSource(request, "entp");
		
		
		//////////////System.out.println("ptype>>>>>>>on action>>>>>>"+corpoDataForm.getPtype());
		if(((String)session.getAttribute("flagform")).equalsIgnoreCase("1"))
		{	
			
			
			
		String strComplResult ="";
			 
		Vector<String> complaintVec =  new Vector<String>();
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
	    String creationDate = sttotal.nextToken();
	    complaintVec.add(uId);					
		
		
			complaintVec.add(corpoDataForm.getTab1().toString().trim());	    //Tab1 Title
			complaintVec.add(corpoDataForm.getTab2().toString().trim());	    //Tab2 Title
			complaintVec.add(corpoDataForm.getTab3().toString().trim());	    //Tab3 Title
			complaintVec.add(corpoDataForm.getTab4().toString().trim());	    //Tab4 Title
			complaintVec.add(corpoDataForm.getTab5().toString().trim());	    //Tab5 Title
			complaintVec.add("1");
			complaintVec.add(creationDate);		
				
			strComplResult = indvMaster.insertInterview(dataSource, complaintVec);
		    if(strComplResult.equalsIgnoreCase("success"))
							{
								ActionErrors errors = new ActionErrors();
								errors.clear();
								errors.add("usermodify", new ActionError("errors.user.modifySuucess"));
								
								saveErrors(request, errors);
								
							}
							else
							{
								ActionErrors errors = new ActionErrors();
								errors.clear();
								errors.add("usermodify", new ActionError("errors.user.modifyFailure"));
								
								saveErrors(request, errors);
								
							}
							////////System.out.println("result1result1>>>>>>>>>>>>in action"+strComplResult);	
		if(strComplResult.equalsIgnoreCase("success")){
			session.setAttribute("flagform","0");
			
		}
		
		return mapping.findForward(strComplResult);
		
	}else{
			
			return mapping.findForward("success");
		}
	}

}
