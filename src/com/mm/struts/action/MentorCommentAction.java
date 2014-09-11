package com.mm.struts.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
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
import org.apache.struts.actions.LookupDispatchAction;

import com.mm.core.master.*;
import com.mm.struts.form.LoginCommentsForm;
import com.mm.struts.form.MentorCommentForm;

/** 
 * MyEclipse Struts
 * Creation date: 08-02-2007
 * 
 */

public class MentorCommentAction extends LookupDispatchAction {
	
	protected Map getKeyMethodMap()
	{
		Map<String , String> map = new HashMap<String , String> ();
		map.put("preview.loginsubmit", "loginsubmit");
		map.put("preview.textsubmit", "textsubmit");
		
		return map;
	}
	
	public ActionForward loginsubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		MentorCommentForm mentorCommentForm = (MentorCommentForm) form;

		
				
		HttpSession session= request.getSession();
		
		////System.out.println("session..........."+session);
		String complainid = (mentorCommentForm.getComplaintId() != null) ? mentorCommentForm.getComplaintId():(String)request.getAttribute("complainid");
		//String Stype = (request.getParameter("Stype")!=null) ? request.getParameter("Stype") : (String)request.getAttribute("Stype");
		session.setAttribute("complainid", complainid);
		
		
			
		String result = "failure";
		
		RootMaster rootMaster= new RootMaster();
		
		
		DataSource ds = getDataSource(request,"main");	
		
		
		Vector <String> dataVec = new Vector<String>();	
		String type = "indv";
		
		dataVec.add(mentorCommentForm.getLoginId().trim());
		dataVec.add(mentorCommentForm.getPasword().trim());
		dataVec.add(type);
		
		//System.out.println("dataVec.getLoginId().trim()..........."+dataVec.elementAt(0).length());
		//System.out.println("dataVec.getLoginId().trim()..........."+dataVec.elementAt(1).length());
			ActionErrors errors = new ActionErrors();
			if((mentorCommentForm.getLoginId().trim().length()==0)&&(mentorCommentForm.getLoginId().trim().length()==0))
			{
				
				
				//System.out.println("mentorCommentForm.getLoginId().trim()...in if........"+mentorCommentForm.getLoginId().trim());
				errors.add("cLoginid", new ActionError("errors.user.cLoginid"));
				errors.add("cPassword", new ActionError("errors.user.cPassword"));
				if(!errors.isEmpty())
				{
					saveErrors(request, errors);
				}
				return mapping.findForward("failure");
				
			}else if(mentorCommentForm.getLoginId().trim().length()==0)
					{
						errors.add("cLoginid", new ActionError("errors.user.cLoginid"));
						if(!errors.isEmpty())
						{
							saveErrors(request, errors);
						}
						return mapping.findForward("failure");
						
					}
			else if(mentorCommentForm.getPasword().trim().length()==0)
					{
						errors.add("cPassword", new ActionError("errors.user.cPassword"));
						if(!errors.isEmpty())
						{
							saveErrors(request, errors);
						}
						return mapping.findForward("failure");
					}
			
		//change type to full company type
		Vector userVec = rootMaster.verifyUserforcomment(ds,dataVec);
		System.out.println("userVec..........Action..for errormessage......."+userVec);
		if(userVec.size()==1)
		{	
			
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
			String strCid =  userVec.elementAt(2).toString();
			String strUname =  userVec.elementAt(4).toString();
			
			//System.out.println("strUserId......in login sucess....."+strUserId);
			//System.out.println("strCid......in login sucess....."+strCid);
			//System.out.println("strUname......in login sucess....."+strUname);
			
			
			session.setAttribute("strUserId", strUserId);
			session.setAttribute("strCid", strCid);
			session.setAttribute("strUname", strUname);
			session.setAttribute("stype", "writetext");
			
			
			
			result = "loginsuccess";
		}
		
		
		return mapping.findForward(result);
		
		
		
	}
	
	public ActionForward textsubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		MentorCommentForm mentorCommentForm = (MentorCommentForm) form;

		////System.out.println("in sied textsubmit");
				
		HttpSession session= request.getSession();
		
		////System.out.println("session..........."+session);
		String complainid = (mentorCommentForm.getComplaintId() != null) ? mentorCommentForm.getComplaintId():(String)session.getAttribute("complainid");
		//String Stype = (request.getParameter("Stype")!=null) ? request.getParameter("Stype") : (String)request.getAttribute("Stype");
		request.setAttribute("complainid", complainid);
		////System.out.println("complaintId..........Action...2......"+complainid);
		
			
		String result = "failure";
		
		RootMaster rootMaster= new RootMaster();
		
		
		DataSource ds = getDataSource(request,"main");	
		Vector datavec=new Vector();
		int compId=Integer.parseInt(complainid);
		Vector compVec=rootMaster.getComplaintDetails(ds, compId);
		String loginid=compVec.elementAt(11).toString().trim();
		////System.out.println("loginid..........loginid........."+loginid);
		String strUserId = (mentorCommentForm.getUserId() != null) ? mentorCommentForm.getUserId():(String)session.getAttribute("userId");
		
		////System.out.println("strUserId..........strUserId........."+strUserId);
		Vector compVec1=rootMaster.getUserInfo(ds, Integer.parseInt(strUserId));
		String strcompId=compVec1.elementAt(0).toString().trim();
		
		
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
		String creationDate = sttotal.nextToken();
		String creationTime = sttotal.nextToken();
		ActionErrors errors = new ActionErrors();
		
		
		if(mentorCommentForm.getComtext().trim().length()==0)
		{
			errors.add("comtext", new ActionError("errors.user.comtext"));
			if(!errors.isEmpty())
			{
				saveErrors(request, errors);
			}
			return mapping.findForward("failure");
		}
		datavec.add(mentorCommentForm.getComtext());//0 commenttext
		datavec.add(creationDate);//1 commentdate
		datavec.add(complainid);//2  complaintid
		datavec.add(strUserId);//3 userid
		datavec.add(creationTime);//4 commentTime 
		datavec.add(strcompId);//5 comapanyid
		datavec.add("1");//6 publish_flag
		datavec.add("0");//7 suspend_flag
		datavec.add(loginid);//8 mentor id
		
		
		
		result = rootMaster.insertCommentMentor(ds,datavec);
		
		if(result.equalsIgnoreCase("success"))
		{	
			session.setAttribute("stype", "login");
			return mapping.findForward("textsuccess");
		}
		return mapping.findForward("failure");
	}
	
	
}
