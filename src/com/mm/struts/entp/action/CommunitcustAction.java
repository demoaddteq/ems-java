package com.mm.struts.entp.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.EntpMaster;
import com.mm.struts.entp.form.CommunitcustForm;
import java.util.*;

public class CommunitcustAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
			
	{		
		
		CommunitcustForm cf = (CommunitcustForm) form;
		String target = new String("failuer");
		////System.out.println("///////////////////////////////CommunitcustAction/////");
		String tempId = cf.getCompId();
		////System.out.println("tempId//////numCompId////////"+tempId);
		
		
		EntpMaster entpMaster= new EntpMaster();
		String CompId = (request.getParameter("compId")!=null) ? request.getParameter("compId") : (String)request.getAttribute("CompId");
		request.setAttribute("CompId", CompId);
		
		
		
		int numCompId = Integer.parseInt(CompId);
		////System.out.println("CommunitcustAction//////numCompId////////"+numCompId);
		
		target = entpMaster.setCommunityUnnamedMap(cf, getDataSource(request, "entp"), numCompId);
		
		if(target.equalsIgnoreCase("success"))
		{
			ActionErrors errors = new ActionErrors();
			errors.clear();
			errors.add("communitCust", new ActionError("errors.communitCust.publish"));
			
				saveErrors(request, errors);
		}
		else
		{
			ActionErrors errors = new ActionErrors();
			errors.clear();
			errors.add("communitCust", new ActionError("errors.communitCust.failure"));
			
				saveErrors(request, errors);
		}
		
		return (mapping.findForward(target));
	}

}
