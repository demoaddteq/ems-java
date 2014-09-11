/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.student.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.addteq.struts.DAO.reportDAO;
import com.addteq.struts.form.ReportForm;
import com.addteq.struts.resource.APPConstant;

/** 
 * MyEclipse Struts
 * Creation date: 04-02-2010
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class FindReportAction extends Action {
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
		// TODO Auto-generated method stub
		
		ReportForm myReportForm=(ReportForm)form;
		String operation = (String)request.getParameter("operation");
		String vid = (String)request.getParameter("vid");
		String startDate = (String)request.getParameter("startDate");
		String endDate = (String)request.getParameter("endDate");
		DataSource myDataSource = getDataSource(request,"corpo");
		reportDAO myReportDAO = new reportDAO();
		Vector invoiceList = new Vector();
		Vector vendorList = new Vector();
		Vector subconList = new Vector();
		
		
		String forward = "viewConsultantForm";
		try
		{
			if(operation.equals("show"))
			{
				vendorList = myReportDAO.getVendorName(myDataSource);
				request.setAttribute(APPConstant.VEN_LIST,vendorList);
				forward = "showInvoiceReport";
			}
			else if(operation.equals("view"))
			{
				 invoiceList = myReportDAO.getInvoiceReport(myDataSource, vid, startDate,endDate);
				 request.setAttribute(APPConstant.INVOICE_LIST,invoiceList);
				 forward = "viewResult";
			}
			else if(operation.equals("home"))
			{
				forward ="viewIndex";
				
			}
			
		}
		catch(Exception e)
		{
			
			System.out.println("we got a problem");
		}
		
		
		return mapping.findForward(forward);
	}
}