/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.entp.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.EntpMaster;
import com.mm.core.master.RootMaster;
import com.mm.core.resource.MailText;

/** 
 * MyEclipse Struts
 * Creation date: 07-27-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */

public class ChangeTagAction extends Action {
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
		
		HttpSession session = request.getSession();
				
		String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
		Vector allRights =(Vector)session.getAttribute("allRights");
		String adminFlag = allRights.elementAt(18).toString();
		
		EntpMaster entpMaster = new EntpMaster();
		String complanitId = request.getParameter("complainId");
		String tag = request.getParameter("tag");
		System.out.println("complanitId in change publish action>>>>>>>>>>>>>"+complanitId);
		System.out.println("tag in change publish action>>>>>>>>>>>>>"+tag);
		Vector<String> dataVec = new Vector<String>();
		dataVec.add(complanitId);
		dataVec.add(tag);
		String status = entpMaster.changetag(getDataSource(request, "entp"), dataVec);
		
		Vector dataVec3 = new Vector();		
		dataVec3.add(uId);
		dataVec3.add(compId);	
		dataVec3.add(adminFlag);
		Vector entpCountVec = entpMaster.complaintCountEntp(getDataSource(request, "entp"), dataVec3);		
		session.setAttribute("entpCountVec", entpCountVec);						
		System.out.println("entpCountVec.............ChangeTagAction........... "+entpCountVec);
		
		if(status.equalsIgnoreCase("success"))
		{
			RootMaster rootMaster = new RootMaster();
			Vector complaintVec = entpMaster.getComplaintDetails(getDataSource(request, "entp"), Integer.parseInt(complanitId));
			System.out.println("complaintVec>>>>>>>>>>>"+complaintVec);
			/*
			 * support.setFieldVec("string", "complaint_title");	// 0
				support.setFieldVec("string", "complaint_text");	// 1
				support.setFieldVec("string", "relevent_info");		// 2
				support.setFieldVec("int", "login_id");				// 3
				support.setFieldVec("int", "companyid");			// 4
				support.setFieldVec("int", "entp_id");				// 5
				support.setFieldVec("int", "cu_id");				// 6
				support.setFieldVec("String", "fcom_id");			// 7
				support.setFieldVec("int", "advt_id");				// 8
				support.setFieldVec("int", "advtL_id");				// 9
				support.setFieldVec("int", "dealer_id");			// 10
			 */
			String finalCompId = complaintVec.elementAt(7).toString().trim();
			String strComplaintText = complaintVec.elementAt(1).toString().trim();
			String strReleventText = complaintVec.elementAt(2).toString().trim();
			int advtl_id = Integer.parseInt(complaintVec.elementAt(9).toString().trim());
			Vector brandUserVec = rootMaster.getUserInfo(getDataSource(request, "entp"), advtl_id);
			String uname = brandUserVec.elementAt(4).toString().trim()+" "+brandUserVec.elementAt(5).toString().trim();
			Vector<Vector> mailVec = new Vector<Vector>();
			Vector<String> tempMailVec = new Vector<String>();
			tempMailVec.add(String.valueOf(advtl_id));
			tempMailVec.add(brandUserVec.elementAt(8).toString().trim());
			tempMailVec.add(uname);
			mailVec.add(tempMailVec);
			MailText mailText = new MailText();
			String strReminder = mailText.getMailtextReminder(uname,finalCompId,tag);
			String strMailText = mailText.getMailtextMail(finalCompId,tag);
			String strReminderSub = mailText.getMailtextReminderSubject(tag, finalCompId);
			request.setAttribute("mailVec", mailVec);
			request.setAttribute("strReminder", strReminder);
			request.setAttribute("strReminderSub", strReminderSub);
			request.setAttribute("strComplaintText", strComplaintText);
			request.setAttribute("strReleventText", strReleventText);
			request.setAttribute("CopmId", complanitId);
			request.setAttribute("fCopmId", finalCompId);
			request.setAttribute("tagId", tag);
			request.setAttribute("strMailText",strMailText);
			
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			errors.clear();
			errors.add("publish", new ActionError("errors.tag.failure"));
			
				saveErrors(request, errors);
		}
		
		return mapping.findForward(status);
	}
}