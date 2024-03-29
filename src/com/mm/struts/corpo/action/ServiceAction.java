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


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.resource.APPConstant;
import com.mm.struts.corpo.form.ServiceForm;
import com.mm.bean.ServiceBean;
import com.mm.core.master.AdvtMaster;
import com.mm.core.master.ServiceDao;
import com.mm.core.resource.LoginHelper;

/** 
 * MyEclipse Struts
 * Creation date: 09-03-2009
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ServiceAction extends Action {
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
		ServiceForm serviceForm = (ServiceForm) form;
		ServiceDao serviceDao = new ServiceDao();
		DataSource dataSource = getDataSource(request, "corpo");
		String operation = request.getParameter("operation");
		
		//String month = request.getParameter("month");
		//String year = request.getParameter("year");
		
		
		String forward = "showService";
		//int facilityId = getFacilityId(session);
		int facilityId = (session.getAttribute("fId")!=null) ? Integer.parseInt((String)session.getAttribute("fId")) : 0;
		serviceForm.setFacilityId(facilityId);
		
		System.out.println(facilityId);
		
		request.setAttribute("facilityId", facilityId);
		if (operation == null) {
			operation = serviceForm.getOperation();
		}
		if (operation == null) {
			operation = "";
		}
		
		
		
		try {
			if (operation.equals("addnew")) {
				String results = validateServiceForm(serviceForm);
				if (results != null) {
					request.setAttribute(APPConstant.MESSAGE, APPConstant.SERVICE_INSERT_FAILURE);
					forward = "addServiceFailure";
				}
				else {
					serviceDao.insertService(dataSource, serviceForm);
					Vector consumerList = serviceDao.getServiceList(dataSource, facilityId);
					request.setAttribute(APPConstant.SERVICE_LIST, consumerList);
					request.setAttribute(APPConstant.MESSAGE, APPConstant.INSERT_SUCCESS);
					forward = "showService";
				}
				
			} else if (operation.equals("edit")) {
				serviceDao.updateService(dataSource, serviceForm);
				Vector consumerList = serviceDao.getServiceList(dataSource, facilityId);
				request.setAttribute(APPConstant.SERVICE_LIST, consumerList);
				request.setAttribute(APPConstant.MESSAGE, APPConstant.EDIT_SUCCESS);
				forward = "showService"; 
			}else if (operation.equals("billGeneration")) {

				int billMonth = serviceForm.getBillMonth();
				int billYear = serviceForm.getBillYear();
				Date billDate = getDate(billMonth,billYear);
				
				String dueDate = request.getParameter("dueDateStr");
				Date date = getDate(dueDate);
				System.out.println("Billmonth & year"+ "="+billMonth+" & "+ billYear );
				if (billMonth !=0  && billYear!=0 ){
					serviceDao.generateConsumerBills(dataSource, facilityId, billMonth, billYear, date);
					forward = "showService";  // need to change the follow-up page
				}
				else {
					request.setAttribute(APPConstant.MESSAGE, "Month/Year cannot be BLANK!");
					forward = "showService";  // need to change the follow-up page
				}
				}
			
				
			else if (operation.equals("forwardToAddPage")) {
				forward = "addService";
			} else if (operation.equals("forwardToEditPage")) {
				setDataForEditPage(request);
				forward = "editService";
			} else if (operation.equals("delete")) {
				deleteConsumer(request, facilityId);
				forward = "showService";
			} else if (operation.equals("showService")) {
				Vector consumerList = serviceDao.getServiceList(dataSource, facilityId);
				request.setAttribute(APPConstant.SERVICE_LIST, consumerList);
			}else if (operation.equals("searchService")){
				String serviceName=request.getParameter("serviceName"); 
				Vector consumerList = serviceDao.searchServices(dataSource, serviceName);
				request.setAttribute(APPConstant.SERVICE_LIST, consumerList);
			}else if (operation.equals("showServiceResident")){
				int consumerId = getConsumerId(session);
				//int facilityId = getFacilityId(session);
				String serviceName=request.getParameter("serviceName"); 
				Vector consumerList = serviceDao.getServiceListResident(dataSource, consumerId, facilityId);
				request.setAttribute(APPConstant.SERVICE_LIST, consumerList);
				forward = "showServiceResident";
			}
			AdvtMaster advtMaster = new AdvtMaster();
			Vector servicesVec = advtMaster.getServices(getDataSource(request, "corpo"), facilityId); 
			session.setAttribute("ServiceList", servicesVec);
		} catch (Exception exception) {
			exception.printStackTrace();
			request.setAttribute(APPConstant.MESSAGE,APPConstant.FAILURE_MESSAGE);
		}
		return mapping.findForward(forward);
	}


	private void deleteConsumer(HttpServletRequest request, int facilityId)
			throws Exception {
		DataSource dataSource = getDataSource(request, "corpo");
		String serviceIds = request.getParameter("serviceIds");
		System.out.println("Service Ids by Ajay Kumar Jha "+serviceIds);
		String serviceIdsArr[] = serviceIds.split(",");
		System.out.println("Service Ids Split by Ajay Kumar Jha "+serviceIdsArr[0]);
		ServiceDao serviceDao = new ServiceDao();
		serviceDao.inactiveService(dataSource, serviceIdsArr);
		Vector consumerList = serviceDao.getServiceList(dataSource, facilityId);
		request.setAttribute(APPConstant.SERVICE_LIST, consumerList);
		request.setAttribute(APPConstant.MESSAGE, APPConstant.DELETE_SUCCESS);
	}

	private void setDataForEditPage(HttpServletRequest request)
			throws Exception {
		ServiceDao serviceDao = new ServiceDao();
		DataSource dataSource = getDataSource(request, "corpo");
		int serviceId = Integer.parseInt((String)request.getParameter("serviceId"));
		ServiceBean bean = serviceDao.getServiceData(dataSource, serviceId);
		request.setAttribute(APPConstant.SERVICEDATA, bean);
	}
	
	private String validateServiceForm(ServiceForm serviceForm ) {
		
		if (serviceForm == null) {
			return "Please fill New Service form";
		}
		else if (serviceForm.getServiceName() == null ||  serviceForm.getServiceName().trim().length() <= 0) {
				return "Service Name cannot be empty";
			}
		else if(serviceForm.getServiceDesc()==null || serviceForm.getServiceDesc().trim().length()<=0){
				return "Service Description can not be empty";
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
