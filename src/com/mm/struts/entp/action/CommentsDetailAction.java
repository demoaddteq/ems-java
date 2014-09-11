package com.mm.struts.entp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.struts.entp.form.CommentsDetailForm;
import com.mm.core.master.*;

import javax.sql.DataSource;
import java.util.*;
/** 
 * MyEclipse Struts
 * Creation date: 06-20-2007
 * 
 * XDoclet definition:
 * @struts.action path="/commentsDetail" name="CommentsDetailForm" input="/form/commentsDetail.jsp" scope="request" 
 * @struts.action-forward name="success" path="commentsDetail.jsp"
 */
public class CommentsDetailAction extends LookupDispatchAction {
	/*
	 * Generated Methods
	 */
	protected Map getKeyMethodMap()
	{
		Map<String , String> map = new HashMap<String , String> ();
		map.put("comment.publish", "publish");
		map.put("comment.suspend", "suspend");
		map.put("comment.reject","reject");
		map.put("comment.cancel","cancel");
		return map;
	}
	

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward publish(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		CommentsDetailForm commentsDetailForm = (CommentsDetailForm) form;// TODO Auto-generated method stub
		String status = "failure";
		
		DataSource dataSource = getDataSource(request,"entp");
		EntpMaster entpMaster = new EntpMaster();
		String strCText = commentsDetailForm.getTxtComment();
		String numCId = commentsDetailForm.getCommnetId();
		int numFlag=1;
		status = entpMaster.updateComments(dataSource, numCId, strCText, numFlag);
		if(status.equalsIgnoreCase("success"))
		{
			ActionErrors errors = new ActionErrors();
			errors.clear();
			errors.add("commnet", new ActionError("errors.commnet.publish"));
			
				saveErrors(request, errors);
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			errors.clear();
			errors.add("commnet", new ActionError("errors.commnet.failure"));
			
				saveErrors(request, errors);
		}
		return mapping.findForward(status);
	}
	
	
	public ActionForward suspend(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		CommentsDetailForm commentsDetailForm = (CommentsDetailForm) form;// TODO Auto-generated method stub
		String status = "failure";
		
		DataSource dataSource = getDataSource(request,"entp");
		EntpMaster entpMaster = new EntpMaster();
		String strCText = commentsDetailForm.getTxtComment();
		String numCId = commentsDetailForm.getCommnetId();
		int numFlag=2;
		status = entpMaster.updateComments(dataSource, numCId, strCText, numFlag);
		if(status.equalsIgnoreCase("success"))
		{
			ActionErrors errors = new ActionErrors();
			errors.clear();
			errors.add("commnet", new ActionError("errors.commnet.suspend"));
			
				saveErrors(request, errors);
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			errors.clear();
			errors.add("commnet", new ActionError("errors.commnet.failure"));
			
				saveErrors(request, errors);
		}
		return mapping.findForward(status);
	}
	
	
	public ActionForward reject(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		CommentsDetailForm commentsDetailForm = (CommentsDetailForm) form;// TODO Auto-generated method stub
		String status = "failure";
		
		DataSource dataSource = getDataSource(request,"entp");
		EntpMaster entpMaster = new EntpMaster();
		String strCText = commentsDetailForm.getTxtComment();
		String numCId = commentsDetailForm.getCommnetId();
		int numFlag=3;
		status = entpMaster.updateComments(dataSource, numCId, strCText, numFlag);
		if(status.equalsIgnoreCase("success"))
		{
			ActionErrors errors = new ActionErrors();
			errors.clear();
			errors.add("commnet", new ActionError("errors.commnet.reject"));
			
				saveErrors(request, errors);
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			errors.clear();
			errors.add("commnet", new ActionError("errors.commnet.failure"));
			
				saveErrors(request, errors);
		}
		return mapping.findForward(status);
	}
	
	
	public ActionForward cancel(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		
		return mapping.findForward("cancel");
	}
}