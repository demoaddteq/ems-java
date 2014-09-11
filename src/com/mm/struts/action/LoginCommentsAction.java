package com.mm.struts.action;

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

import com.mm.core.master.RootMaster;
import com.mm.struts.form.LoginCommentsForm;

/** 
 * MyEclipse Struts
 * Creation date: 08-02-2007
 * 
 * XDoclet definition:
 * @struts.action path="/loginComments" name="LoginCommentsForm" input="/indvLoginForComments.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/.jsp"
 * @struts.action-forward name="failure" path="/indvLoginForComments.jsp"
 */

public class LoginCommentsAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		LoginCommentsForm loginCommentsForm = (LoginCommentsForm) form;// TODO Auto-generated method stub
		
				
		HttpSession session= request.getSession();
		
		////System.out.println("session..........."+session);
		String complainid = (loginCommentsForm.getComplaintId() != null) ? loginCommentsForm.getComplaintId():(String)request.getAttribute("complainid");
		request.setAttribute("complainid", complainid);
		////System.out.println("complaintId..........Action...1......."+complainid);
		
			
		String result = "failure";
		
		RootMaster rootMaster= new RootMaster();
		
		DataSource dataSource = getDataSource(request,"main");	
		Vector <String> dataVec = new Vector<String>();	
		String type = "indv";
		
		
		
		dataVec.add(loginCommentsForm.getLoginId().trim());
		dataVec.add(loginCommentsForm.getPasword().trim());
		dataVec.add(type);
		//change type to full company type
		Vector userVec = rootMaster.verifyUser(dataSource,dataVec);
		
		if(userVec.size()==1)
		{	
			ActionErrors errors = new ActionErrors();
			if(userVec.elementAt(0).toString().equalsIgnoreCase("loginFailure"))
			{
				errors.add("notFound", new ActionError("errors.user.notFound"));
				if(!errors.isEmpty())
				{
					saveErrors(request, errors);
				}
				return mapping.findForward("failure");
			}
			if(userVec.elementAt(0).toString().equalsIgnoreCase("companyFailure"))
			{
				errors.add("notFound", new ActionError("errors.company.notExist"));
				if(!errors.isEmpty())
				{
					saveErrors(request, errors);
				}
				return mapping.findForward("failure");
			}
			if(userVec.elementAt(0).toString().equalsIgnoreCase("activeFailure"))
			{
				if(type.equalsIgnoreCase("indv"))
				{
					
					return mapping.findForward("activeFailure");
				}
				else
				{
					errors.add("notFound", new ActionError("errors.user.acivate"));
					if(!errors.isEmpty())
					{
						saveErrors(request, errors);
					}
					return mapping.findForward("failure");
				}
			}
			
		}
		else
		{	
			
			String strUserId =  userVec.elementAt(1).toString();
			request.setAttribute("strUserId", strUserId);
			
			
			////System.out.println("complaintId..........Action...2......."+complainid);
			////System.out.println("strUserId..........Action.....2....."+strUserId);
			
			
			
			result = "success";
		}
		
		return mapping.findForward(result);
	}

}
