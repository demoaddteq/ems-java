package com.addteq.struts.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import com.addteq.struts.DAO.TimesheetDAO;
import com.addteq.struts.form.TimesheetForm;
import com.addteq.struts.resource.APPConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import java.util.Vector;
import java.io.PrintWriter;

public class AddTimesheetAction extends Action{
	
	protected final Log logger=LogFactory.getLog(getClass());
	
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
								 throws Exception{
		
		String operation = null;
			
		operation = request.getParameter("operation");
		logger.info("Inside AddTimesheetAction. Operation -->"+operation);
		DataSource datasource=getDataSource(request,"corpo"); 
		TimesheetForm timesheetForm = (TimesheetForm)form;
		
		
		TimesheetDAO timeDao = new TimesheetDAO();
		
		if (operation.equals("ajax")){
			
			String conId = (String) request.getParameter("letrs");
			int wid = Integer.parseInt(conId);
			logger.info("Inside Action class. id-->"+wid);
			String dates = timeDao.getDate(datasource, wid);
			
			logger.info("Inside AJAX part. Dates -->"+dates);
								
			// response.setContentType("text/xml");
			// response.getWriter().write("<endDate>"+endDate+"</endDate>");
			
			response.setContentType("text/html");
			response.getWriter().write(dates);
			
			// PrintWriter out = response.getWriter();
		    // out.println(dates);
		    // out.flush();


			// return mapping.findForward("showForm");
			return null;
		}
		
		else if (operation.equals("add")) {
			
			request.setAttribute(APPConstant.START_DATE, timesheetForm.getFromDate());
			request.setAttribute(APPConstant.END_DATE, timesheetForm.getToDate());
			
			logger.info("form.wid-->"+timesheetForm.getWid());
			logger.info("form.fromDate-->"+timesheetForm.getFromDate());
			logger.info("form.toDate-->"+timesheetForm.getToDate());
			logger.info("form.hours-->"+timesheetForm.getHours());
			logger.info("APPConstant.START_DATE: "+timesheetForm.getFromDate());
			logger.info("APPConstant.END_DATE: "+timesheetForm.getToDate());
			
			logger.info("Inside Add Operation. Before calling timeDAO.");
			timeDao.addSheet(datasource, timesheetForm);
			
			Vector conList = timeDao.getFullNames(datasource);
			request.setAttribute(APPConstant.CONSULTANT_LIST,conList);
						
			return mapping.findForward("showForm");
		}
	
		else {
			
			Vector conList = timeDao.getFullNames(datasource);
			logger.info("After receiving consultant list from the DAO-->"+conList);
			request.setAttribute(APPConstant.CONSULTANT_LIST,conList);
			logger.info("Consultant list from APPConstant-->"+APPConstant.CONSULTANT_LIST);
			
			return mapping.findForward("showForm");}
		
	}
	
	

}
