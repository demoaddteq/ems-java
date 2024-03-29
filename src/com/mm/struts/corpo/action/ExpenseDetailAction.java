/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.corpo.action;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.dbcp.DataSourceConnectionFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.resource.APPConstant;
import com.mm.struts.corpo.form.ExpenseDetailForm;
import com.mm.bean.ExpenseDetailBean;
import com.mm.bean.StaffMasterBean;
import com.mm.core.master.ExpenseDetailDao;
import com.mm.core.resource.LoginHelper;

/** 
 * MyEclipse Struts
 * Creation date: 10-09-2009
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ExpenseDetailAction extends Action {
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
		ExpenseDetailForm expenseDetailForm = (ExpenseDetailForm) form;
		ExpenseDetailDao expDetailDao = new ExpenseDetailDao();
		DataSource dataSource = getDataSource(request, "corpo");
		String operation = request.getParameter("operation");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		
		
		String forward = "showExpense";

		int facilityId = (session.getAttribute("fId")!=null) ? Integer.parseInt((String)session.getAttribute("fId")) : 0;
		expenseDetailForm.setFacilityId(facilityId);
		
		
		request.setAttribute("facilityId", facilityId);
		if (operation == null) {
			operation = expenseDetailForm.getOperation();
		}
		if (operation == null) {
			operation = "";
		}
		
		
		
		try {
			if (operation.equals("addnew")) {
				String results = validateServiceForm(expenseDetailForm);
				if (results != null) {
					request.setAttribute(APPConstant.MESSAGE, results);
					forward = "addStaffFailure";
				}
				else {
					expDetailDao.insertDetail(dataSource, expenseDetailForm);
					Vector consumerList = expDetailDao.getExpenseList(dataSource, facilityId);
					request.setAttribute(APPConstant.EXPENSE_LIST, consumerList);
					request.setAttribute(APPConstant.MESSAGE, APPConstant.INSERT_SUCCESS);
					forward = "showExpense";
				}
			} else if (operation.equals("edit")) {
				expDetailDao.updateExpenses(dataSource, expenseDetailForm);
				Vector consumerList = expDetailDao.getExpenseList(dataSource, facilityId);
				request.setAttribute(APPConstant.EXPENSE_LIST, consumerList);
				request.setAttribute(APPConstant.MESSAGE, APPConstant.EDIT_SUCCESS);
				forward = "showExpense"; 
			}else if (operation.equals("forwardToAddPage")) {
				forward = "addExpense";
			} else if (operation.equals("forwardToEditPage")) {
				setDataForEditPage(request);
				forward = "editExpense";
			} else if (operation.equals("delete")) {
				deleteExpenses(request, facilityId);
			} else if (operation.equals("showExpense")) {
				Vector consumerList = expDetailDao.getExpenseList(dataSource, facilityId);
				
				request.setAttribute(APPConstant.EXPENSE_LIST, consumerList);
			}else if (operation.equals("searchService")){
				String serviceName=request.getParameter("serviceName"); 
				Vector consumerList = expDetailDao.searchServices(dataSource, serviceName);
				request.setAttribute(APPConstant.EXPENSE_LIST, consumerList);
			}else if (operation.equals("showServiceResident")){
				int consumerId = getConsumerId(session);
				//int facilityId = getFacilityId(session);
				String serviceName=request.getParameter("serviceName"); 
				Vector consumerList = expDetailDao.getServiceListResident(dataSource, consumerId, facilityId);
				request.setAttribute(APPConstant.EXPENSE_LIST, consumerList);
				forward = "showServiceResident";
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			request.setAttribute(APPConstant.MESSAGE,APPConstant.FAILURE_MESSAGE);
		}
		return mapping.findForward(forward);
	}


	private void deleteExpenses(HttpServletRequest request, int facilityId)
			throws Exception {
		DataSource dataSource = getDataSource(request, "corpo");
		String expId = request.getParameter("expenseIds");
		String staffIdsArr[] = expId.split(",");
		
		ExpenseDetailDao expDetaiolDao = new ExpenseDetailDao();
		expDetaiolDao.inactiveExpenses(dataSource, staffIdsArr);
		Vector staffList = expDetaiolDao.getExpenseList(dataSource, facilityId);
		request.setAttribute(APPConstant.EXPENSE_LIST, staffList);
		request.setAttribute(APPConstant.MESSAGE, APPConstant.DELETE_SUCCESS);
	}

	private void setDataForEditPage(HttpServletRequest request)
			throws Exception {
		ExpenseDetailDao expenseDao = new ExpenseDetailDao();
		DataSource dataSource = getDataSource(request, "corpo");
		int expId = Integer.parseInt((String)request.getParameter("expenseId"));
		ExpenseDetailBean bean = expenseDao.getExpenseData(dataSource, expId);
		request.setAttribute(APPConstant.EXPENSEDATA, bean);
	}
	
	private String validateServiceForm(ExpenseDetailForm expenseDetailForm ) {
		
		if (expenseDetailForm == null) {
			return "Please fill New Staff form";
		}
		
		return null;
		
	}
	
	
	/**
	 * date help function
	 * @param str_date
	 * @return
	 * @throws Exception
	 */
	private Date getDate(String str_date) throws Exception {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date sqlDate = new java.sql.Date(formatter.parse(str_date).getTime());
		return sqlDate;
	}
	
	/**
	 * Date Help function
	 * @param month
	 * @param year
	 * @return
	 * @throws Exception
	 */
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
	
	public String getLoginName(HttpSession session) {
		if(session.getAttribute(APPConstant.USER_INFORMATION) == null){
			return "";
		}
		LoginHelper helper = (LoginHelper) session
				.getAttribute(APPConstant.USER_INFORMATION);
		return helper.getUserName();
	}
}
