/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.corpo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;
import com.mm.core.master.AdvtMaster;
import java.util.Vector;
import com.mm.struts.corpo.form.InboxDeletionForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-19-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class InboxDeletionAction extends Action {
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
		
		
		////////System.out.println("dc in action >>>>>");
				
		AdvtMaster advtMaster = new AdvtMaster();
		String status = "failure";
		InboxDeletionForm inboxDeletionForm = (InboxDeletionForm)form;
		String[] inboxIds = inboxDeletionForm.getInboxids();
		////////System.out.println("dc in action inboxIds >>>>>"+inboxIds);
		String inboxs = "";
		for(int i=0; i<inboxIds.length; i++)
		{
			////////System.out.println("manoj in action "+i+">>>>>"+inboxIds[i]);
			if(i!=(inboxIds.length-1))
				inboxs = inboxs+inboxIds[i]+",";
			else
			{
				inboxs = inboxs+inboxIds[i];
			}
		}
		Vector<String> paramVec = new Vector<String>();
		String result = "failure";
		////////System.out.println("inboxs>>>>>>>>>>>"+inboxs);
		if(inboxs.length()>0)
		{
			paramVec.add(inboxs);
			result = advtMaster.updateInbox(getDataSource(request, "corpo"), paramVec);
		}
		if(result.equalsIgnoreCase("success"))
		{
			
			ActionErrors errors = new ActionErrors();
			errors.clear();
			errors.add("inboxdeleteadvt", new ActionMessage("advt.inbox.DeleteSuccess"));
			
			saveErrors(request, errors);
			
			
			status="success";
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			errors.clear();
			errors.add("inboxdeleteadvt", new ActionMessage("advt.inbox.DeleteFailure"));
			
			saveErrors(request, errors);
			
			status ="failure";
		}
		////////System.out.println("result>>>>>>>>>>>"+result);
		Vector dataVec = new Vector();		
		dataVec.add(uId);
		dataVec.add(compId);
		dataVec.add(adminFlag);
			
		Vector advtCountVec = advtMaster.complaintCountAdvt(getDataSource(request, "corpo"), dataVec);		
		session.setAttribute("advtCountVec", advtCountVec);						
		//////System.out.println("advtCountVec.............InboxDeletionAction........... "+advtCountVec);
		
		return mapping.findForward(status);
	}
}