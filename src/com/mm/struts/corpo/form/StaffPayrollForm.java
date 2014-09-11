package com.mm.struts.corpo.form;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;


public class StaffPayrollForm  extends ValidatorForm{

	

	private String staffName;
	private int facility;
	private int staffCategory;
	private int staffSubCategory;
	private String address;
	private String contactNo;
	private String salary;
	
	
	private int isMandetory;
	private int type;
	private int serviceId;
	private String serviceName;
	private String serviceDesc;
	private int monthlyFee;
	private int taxes;
	private int cess;
	private String facilityName;
	private int facilityId;
	private int consumerId;
	private Date billDate;
	private Date dateTo;
	private Date dateFrom;
	private int billYear;
	private int billMonth;
	private Date dueDate;
	private String dueDateStr;
	
	private int status;
	private int subscribers;
	
	private int createdBy;
	private Timestamp createdOn;
	private int modifiedBy;
	private Timestamp modifiedOn;
	private String operation;
	private String requestId;

	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdOn
	 */
	public Timestamp getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn
	 *            the createdOn to set
	 */
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the modifiedBy
	 */
	public int getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the modifiedOn
	 */
	public Timestamp getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * @param modifiedOn
	 *            the modifiedOn to set
	 */
	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	public int getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(int subscribers) {
		this.subscribers = subscribers;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the dueDateStr
	 */
	public String getDueDateStr() {
		return dueDateStr;
	}

	/**
	 * @param dueDateStr the dueDateStr to set
	 */
	public void setDueDateStr(String dueDateStr) {
		this.dueDateStr = dueDateStr;
	}

	private int dueAmount;
	private String uniqueId;

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the dueAmount
	 */
	public int getDueAmount() {
		return dueAmount;
	}

	/**
	 * @param dueAmount
	 *            the dueAmount to set
	 */
	public void setDueAmount(int dueAmount) {
		this.dueAmount = dueAmount;
	}

	/**
	 * @return the serviceId
	 */
	public int getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId
	 *            the serviceId to set
	 */
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		System.out.println("In Staff Form-->"+this.serviceName);
		return this.serviceName;
	}

	/**
	 * @param serviceName
	 *            the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * @return the serviceDesc
	 */
	public String getServiceDesc() {
		return this.serviceDesc;
	}

	/**
	 * @param serviceDesc
	 *            the serviceDesc to set
	 */
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	/**
	 * @return the monthlyFee
	 */
	public int getMonthlyFee() {
		return monthlyFee;
	}

	/**
	 * @param monthlyFee
	 *            the monthlyFee to set
	 */
	public void setMonthlyFee(int monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	/**
	 * @return the taxes
	 */
	public int getTaxes() {
		return taxes;
	}

	/**
	 * @param taxes
	 *            the taxes to set
	 */
	public void setTaxes(int taxes) {
		this.taxes = taxes;
	}

	/**
	 * @return the cess
	 */
	public int getCess() {
		return cess;
	}

	/**
	 * @param cess
	 *            the cess to set
	 */
	public void setCess(int cess) {
		this.cess = cess;
	}

	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * @param facilityName
	 *            the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	/**
	 * @return the facilityId
	 */
	public int getFacilityId() {
		return facilityId;
	}

	/**
	 * @param facilityId
	 *            the facilityId to set
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	/**
	 * @return the consumerId
	 */
	public int getConsumerId() {
		return consumerId;
	}

	/**
	 * @param consumerId
	 *            the consumerId to set
	 */
	public void setConsumerId(int consumerId) {
		this.consumerId = consumerId;
	}

	/**
	 * @return the billDate
	 */
	public Date getBillDate() {
		return billDate;
	}

	/**
	 * @param billDate
	 *            the billDate to set
	 */
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	/**
	 * @return the dateTo
	 */
	public Date getDateTo() {
		return dateTo;
	}

	/**
	 * @param dateTo
	 *            the dateTo to set
	 */
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * @return the dateFrom
	 */
	public Date getDateFrom() {
		return dateFrom;
	}

	/**
	 * @param dateFrom
	 *            the dateFrom to set
	 */
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @return the billYear
	 */
	public int getBillYear() {
		return billYear;
	}

	/**
	 * @param billYear
	 *            the billYear to set
	 */
	public void setBillYear(int billYear) {
		this.billYear = billYear;
	}

	/**
	 * @return the billMonth
	 */
	public int getBillMonth() {
		return billMonth;
	}

	/**
	 * @param billMonth
	 *            the billMonth to set
	 */
	public void setBillMonth(int billMonth) {
		this.billMonth = billMonth;
	}

	/**
	 * @return the uniqueId
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/**
	 * @param uniqueId the uniqueId to set
	 */
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getIsMandetory() {
		return isMandetory;
	}

	public void setIsMandetory(int isMandetory) {
		this.isMandetory = isMandetory;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	/*Setter Getter To Be Addedd For Schedule Form*/
	
	private int scheduleId;
	private String scheduleStartTime;
	private String scheduleEndTime;
	private String electricianStaff;
	private String pumpOperatorStaff;
	private String plumberStaff;
	private String securityStaff;
	
	private int category;
	private int subCategory;
	private int staffId;
	private int paymentYear;
	private String paymentMonth;
	private int noOfLeaves;
	private float salaryDeduction;
	private float paidAmount;
	private String paymentMode;
	private String paymentDate;
	
	private String startTime;
	private String endTime;
	
	public String getStartTime() {
		return startTime;
	}	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public int getStaffId() {
		return staffId;
	}	
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	
	
	public int getCategory() {
		return category;
	}	
	public void setCategory(int category) {
		this.category = category;
	}
	
	public int getSubCategory() {
		System.out.println("subCategory=>"+subCategory);
		return subCategory;
	}	
	public void setSubCategory(int subCategory) {
		this.subCategory = subCategory;
	}
	
	public int getScheduleId() {
		return scheduleId;
	}	
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	
	public String getScheduleStartTime() {
		return scheduleStartTime;
	}

	public void setScheduleStartTime(String scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime;
	}
	
	
	public String getScheduleEndTime() {
		return scheduleEndTime;
	}
	
	public void setScheduleEndTime(String scheduleEndTime) {
		this.scheduleEndTime = scheduleEndTime;
	}
	
	public String getElectricianStaff() {
		return electricianStaff;
	}
	
	public void setElectricianStaff(String electricianStaff) {
		this.electricianStaff = electricianStaff;
	}
	
	public String getPumpOperatorStaff() {
		return pumpOperatorStaff;
	}
	
	public void setPumpOperatorStaff(String pumpOperatorStaff) {
		this.pumpOperatorStaff = pumpOperatorStaff;
	}
	
	public String getPlumberStaff() {
		return plumberStaff;
	}
	
	public void setPlumberStaff(String plumberStaff) {
		this.plumberStaff = plumberStaff;
	}
	
	public String getSecurityStaff() {
		return securityStaff;
	}
	public void setSecurityStaff(String securityStaff) {
		this.securityStaff = securityStaff;
	}

	/*Added For Staff Master*/
	/**
	 * @return the address
	 */
	public String getAddress() {
		System.out.println("address in Staff Master Form==>"+address);
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the facility
	 */
	public int getFacility() {
		return facility;
	}

	/**
	 * @param facility the facility to set
	 */
	public void setFacility(int facility) {
		this.facility = facility;
	}

	/**
	 * @return the salary
	 */
	public String getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}

	/**
	 * @return the staffCategory
	 */
	public int getStaffCategory() {
		return staffCategory;
	}

	/**
	 * @param staffCategory the staffCategory to set
	 */
	public void setStaffCategory(int staffCategory) {
		this.staffCategory = staffCategory;
	}

	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * @return the staffSubCategory
	 */
	public int getStaffSubCategory() {
		return staffSubCategory;
	}

	/**
	 * @param staffSubCategory the staffSubCategory to set
	 */
	public void setStaffSubCategory(int staffSubCategory) {
		this.staffSubCategory = staffSubCategory;
	}

	/**
	 * @return the noOfLeaves
	 */
	public int getNoOfLeaves() {
		return noOfLeaves;
	}

	/**
	 * @param noOfLeaves the noOfLeaves to set
	 */
	public void setNoOfLeaves(int noOfLeaves) {
		this.noOfLeaves = noOfLeaves;
	}

	/**
	 * @return the paidAmount
	 */
	public float getPaidAmount() {
		return paidAmount;
	}

	/**
	 * @param paidAmount the paidAmount to set
	 */
	public void setPaidAmount(float paidAmount) {
		this.paidAmount = paidAmount;
	}

	/**
	 * @return the paymentDate
	 */
	public String getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate the paymentDate to set
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the paymentMonth
	 */
	public String getPaymentMonth() {
		return paymentMonth;
	}

	/**
	 * @param paymentMonth the paymentMonth to set
	 */
	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}

	/**
	 * @return the paymentYear
	 */
	public int getPaymentYear() {
		return paymentYear;
	}

	/**
	 * @param paymentYear the paymentYear to set
	 */
	public void setPaymentYear(int paymentYear) {
		this.paymentYear = paymentYear;
	}

	/**
	 * @return the salaryDeduction
	 */
	public float getSalaryDeduction() {
		System.out.println("salaryDeduction");
		return salaryDeduction;
	}

	/**
	 * @param salaryDeduction the salaryDeduction to set
	 */
	public void setSalaryDeduction(float salaryDeduction) {
		this.salaryDeduction = salaryDeduction;
	}
}
