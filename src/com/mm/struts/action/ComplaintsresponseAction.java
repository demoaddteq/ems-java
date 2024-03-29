	/*
	 * Generated by MyEclipse Struts
	 * Template path: templates/java/JavaClass.vtl
	 */
	package com.mm.struts.action;

	import java.text.SimpleDateFormat;
	import java.util.StringTokenizer;
	import java.util.Vector;

	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;	
	import org.apache.struts.action.Action;
	import org.apache.struts.action.ActionForm;
	import org.apache.struts.action.ActionForward;
	import org.apache.struts.action.ActionMapping;
	import com.mm.struts.form.ComplaintsresponseForm;
	import javax.servlet.http.HttpSession;
	import com.mm.core.master.*;

	import javax.sql.DataSource;
	import java.util.*;



	/** 
	 * MyEclipse Struts
	 * Creation date: 08-03-2007
	 * 
	 * XDoclet definition:
	 * @struts.action path="/complaintsresponse" name="ComplaintsresponseForm" input="/complaintsresponse.jsp" scope="request" validate="true"
	 * @struts.action-forward name="success" path="/.jsp"
	 * @struts.action-forward name="failure" path="/indvLoginForComments.jsp"
	 */
	public class ComplaintsresponseAction extends Action {
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
			ComplaintsresponseForm complaintsresponseForm = (ComplaintsresponseForm) form;// TODO Auto-generated method stub
			
				
			String result = null;
				
			Vector <String> datavec = new Vector<String>();
			RootMaster rootMaster = new RootMaster();
			DataSource ds = getDataSource(request,"main");	
			
			String complainid = (request.getParameter("complainid")!=null) ? request.getParameter("complainid") : (String)request.getAttribute("complainid");
			request.setAttribute("complainid", complainid);
			////System.out.println("complainid..........ComplaintsresponseAction.........."+complainid);
			
			String strUserId = (request.getParameter("strUserId")!=null) ? request.getParameter("strUserId") : (String)request.getAttribute("strUserId");
			request.setAttribute("strUserId", strUserId);
			////System.out.println("strUserId..........ComplaintsresponseAction........."+strUserId);
			
			String companyId = (request.getParameter("companyId")!=null) ? request.getParameter("companyId") : (String)request.getAttribute("companyId");
			request.setAttribute("companyId", companyId);
			////System.out.println("companyId..........ComplaintsresponseAction........."+companyId);
			HttpSession session = request.getSession();
			if(((String)session.getAttribute("flag1")).equalsIgnoreCase("1"))
			{
				session.setAttribute("flag1","0");
			
			java.util.Date dt = new java.util.Date();
			SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
			String completeRemDate = sform.format(dt);
			StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
			String creationDate = sttotal.nextToken();
			String creationTime = sttotal.nextToken();
			
			datavec.add(complaintsresponseForm.getResponses());//0 commenttext
			datavec.add(creationDate);//1 commentdate
			datavec.add(complainid);//2  complaintid
			datavec.add(strUserId);//3 userid
			datavec.add(creationTime);//4 commentTime 
			datavec.add(companyId);//5 comapanyid
			datavec.add("0");//6 publish_flag
			datavec.add("0");//7 suspend_flag
			
			
			
			result = rootMaster.insertComment(ds,datavec);
			
			if(result.equalsIgnoreCase("success"))
			{
				return mapping.findForward("success");
			}
			return mapping.findForward("failure");
			}
			else{
				
				return mapping.findForward("success");
				}
	
}
}