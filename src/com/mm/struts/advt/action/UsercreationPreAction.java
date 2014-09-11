package com.mm.struts.advt.action;

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

import com.mm.core.master.EntpMaster;
import com.mm.core.master.RootMaster;
import com.mm.struts.advt.form.UsercreationForm;

public class UsercreationPreAction extends Action {
	
	RootMaster rootMaster = new RootMaster(); 
	EntpMaster entpMaster = new EntpMaster(); 
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		//UsercreationForm usercreationForm = (UsercreationForm) form;// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String compId =(String)session.getAttribute("compId");
		//////System.out.println("compId............UsercreationPreAction.............."+compId);
		
		int intCompId = Integer.parseInt(compId);
				
		DataSource ds = getDataSource(request,"advt");	
		
		Vector <String> userVec = new Vector<String>();	
		String result = "";
		
		String totatUser = "";
		int noOfUser = 0;
		
		userVec.add(compId);
		noOfUser = entpMaster.getUserCount(ds, userVec);	
		//////System.out.println("noOfUser............UsercreationPreAction.............."+noOfUser);
		
		totatUser = rootMaster.getTotalUser(ds, intCompId);
		//////System.out.println("totatUser............UsercreationPreAction.............."+totatUser);
		
		int intTotalUser = Integer.parseInt(totatUser);
		
		Vector companyData = rootMaster.getCompanyDetail(ds, intCompId);
		////////////System.out.println("categor....Corpo......."+companyData.elementAt(25).toString());
		String mcat = companyData.elementAt(25).toString();
		//System.out.println("categor....Corpo......."+mcat);		
				
		session.setAttribute("compMentorReq", mcat);
		
		//////System.out.println("no Of User"+noOfUser);
		//////System.out.println("totatUserr from Company Master"+totatUser);
		
		if(noOfUser >= intTotalUser){			
			result="failure";			
		}
		else
		{
			result="success";	
		}
	    
	    
		if(result.equalsIgnoreCase("failure"))
		{
			ActionErrors errors = new ActionErrors();
			errors.add("limit", new ActionError("errors.totaluser.limit"));			
				saveErrors(request, errors);
				request.setAttribute("license", "failure");	
				session.setAttribute("license", "failure");
				
				
			return mapping.findForward("failure");			
		}
		else
		{
		request.setAttribute("license", "success");
		session.setAttribute("license", "success");
		}
		
		
		/**
		 * For Right Group
		 */
		Vector  rightListVec = new Vector();		
		
		rightListVec = rootMaster.getGroupRightList(ds, intCompId);
		
		if(rightListVec.size()==0)
		{		
				
			ActionErrors errors = new ActionErrors();
			errors.add("groupNotExist", new ActionError("errors.rgroup.groupNotExist"));			
				saveErrors(request, errors);
				request.setAttribute("license", "failure");	
				session.setAttribute("license", "failure");
					
			return mapping.findForward("failure");	
		}
		
		if(result.equalsIgnoreCase("success"))
		{
			return mapping.findForward("success");
		}
		else{
			
		return mapping.findForward("failure");
		}
		
	}

}
