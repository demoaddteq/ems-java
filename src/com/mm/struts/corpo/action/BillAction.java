package com.mm.struts.corpo.action;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.resource.APPConstant;
import com.mm.struts.corpo.form.ServiceForm;
import com.mm.core.master.ServiceDao;
import com.mm.core.resource.LoginHelper;

/**
 * 
 * @author Administrator
 *
 */
public class BillAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Arun in this start");
		HttpSession session = request.getSession();
		DataSource dataSource = getDataSource(request, "corpo");
		ServiceForm serviceForm = (ServiceForm) form;
		ServiceDao serviceDao = new ServiceDao();
		String operation = request.getParameter("operation");
		String forward = "billSummary";
		//int facilityId = getFacilityId(session);
		int facilityId = (session.getAttribute("fId")!=null) ? Integer.parseInt((String)session.getAttribute("fId")) : 0;
		serviceForm.setFacilityId(facilityId);
		System.out.println("Service Form "+serviceForm);
		System.out.println("Operation "+operation);
		if (operation == null) {
			operation = serviceForm.getOperation();
		}
		if (operation == null) {
			operation = "";
		}
		System.out.println("Again Operation Arun "+operation);
		try {
			if (operation.equalsIgnoreCase("forwardToBillGeneratePage")) {
				forward = "billGeneration";
			} else if (operation.equalsIgnoreCase("billGeneration")) {
				System.out.println("Bill Generation");
				int billMonth = serviceForm.getBillMonth();
				int billYear = serviceForm.getBillYear();
				Date billDate = getDate(billMonth,billYear);
				
				String dueDate = request.getParameter("dueDateStr");
				Date date = getDate(dueDate);
				
				serviceDao.getAllConsumerBill(dataSource, billDate, date, facilityId);
				ArrayList compoundDetailList = serviceDao
						.getCompoundBillDetails(dataSource, facilityId, billDate);
				request.setAttribute(APPConstant.COMPOUND_BILL_DETAIL_LIST, compoundDetailList);
				forward="billSummary";
			} else if (operation.equalsIgnoreCase("billSummary")) {
				Date billDate = new Date(System.currentTimeMillis());
				System.out.print("Date "+billDate);
				ArrayList compoundDetailList = serviceDao.getCompoundBillDetails(dataSource, facilityId, billDate);
				request.setAttribute(APPConstant.COMPOUND_BILL_DETAIL_LIST,	compoundDetailList);
			} else if (operation.equalsIgnoreCase("viewDetailedBill")) {  
				
				String consumerId = request.getParameter("consumerId");
				String billNo = request.getParameter("billNo");
				String month = request.getParameter("month");
				String year = request.getParameter("year");
				ArrayList compoundDetailList = serviceDao.getBillDetails(dataSource, Integer.parseInt(consumerId),Integer.parseInt(month),Integer.parseInt(year) );
				
				request.setAttribute(APPConstant.DETAILED_BILL_LIST, compoundDetailList);
				forward = "billDetails";
			}else if (operation.equalsIgnoreCase("viewDetailedBillResident")) {  // resident bills
				
				Date billDate = new Date(System.currentTimeMillis());
				String dateArr[] = getDateArr(billDate);
				String month = request.getParameter("month");
				String year = dateArr[0];
				
				if (month == null || month.trim().length()==0) {
					month = dateArr[1];
				}
				
				
				ArrayList compoundDetailList = serviceDao.getBillDetailsResident(dataSource, month, year, getConsumerId(session));
				request.setAttribute(APPConstant.DETAILED_BILL_LIST,compoundDetailList);
				
				
				
				request.setAttribute("Month",month);
				request.setAttribute("Year",year);
				forward = "billDetailsResident";
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			request.setAttribute(APPConstant.MESSAGE,
					APPConstant.FAILURE_MESSAGE);
		}
		return mapping.findForward(forward);
	}
	
	public String[] getDateArr(Date date) {
		String dateStr = date.toString();
		String dateStrArr[] = dateStr.split("-");
		return dateStrArr;
	}

	
	private Date getDate(String str_date) throws Exception {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date sqlDate = new java.sql.Date(formatter.parse(str_date).getTime());
		return sqlDate;
	}
	private Date getDate(int month,int year) throws Exception {
		String billMonth = "";
		if(month<10){
			billMonth = "0"+month;
		}else{
			billMonth = String.valueOf(month);
		}
		String str_date = "01/"+billMonth+"/"+year;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date sqlDate = new java.sql.Date(formatter.parse(str_date).getTime());
		return sqlDate;
	}
	
	public int getFacilityId(HttpSession session) {
		if(session.getAttribute(APPConstant.USER_INFORMATION) == null){
			return 1;
		}
		LoginHelper helper = (LoginHelper) session
				.getAttribute(APPConstant.USER_INFORMATION);
		return helper.getFacilityId();
	}
	
	public int getConsumerId(HttpSession session) {
		if(session.getAttribute(APPConstant.USER_INFORMATION) == null){
			return -1;
		}
		LoginHelper helper = (LoginHelper) session
				.getAttribute(APPConstant.USER_INFORMATION);
		return helper.getConsumerId();
	}
}
