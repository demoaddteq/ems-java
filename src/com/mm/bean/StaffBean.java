package com.mm.bean;

import java.sql.Date;

public class StaffBean extends CommonBean {

	private int isMandetory;
	private int type;
	private int serviceId;
	private String serviceName;
	private String serviceDesc;
	private int monthlyFee;
	private int taxes;
	private int cess;
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
		System.out.println("serviceName IN staff Bean-->"+serviceName);
		return serviceName;
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
		return serviceDesc;
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
	
	
	/*Setter Getter For Staff Scheduling Display*/
	private int scheduleId;
	private int shiftId;
	private String shiftName;
	private String shiftDate;
	private String scheuduleDate;
	private int staffId;
	private int facilityId;
	private String facilityName;
	private int categoryId;
	private String categoryName;
	private int subCategoryId;
	private String subCategoryName;
	private String electricianStaff;
	private String pumpOperatorStaff;
	private String plumberStaff;
	private String securityStaff;
	
	public int getScheduleId() {
		return scheduleId;
	}	
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
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

	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the shiftId
	 */
	public int getShiftId() {
		return shiftId;
	}

	/**
	 * @param shiftId the shiftId to set
	 */
	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	/**
	 * @return the shiftName
	 */
	public String getShiftName() {
		return shiftName;
	}

	/**
	 * @param shiftName the shiftName to set
	 */
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	/**
	 * @return the staffId
	 */
	public int getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the subCategoryId
	 */
	public int getSubCategoryId() {
		return subCategoryId;
	}

	/**
	 * @param subCategoryId the subCategoryId to set
	 */
	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	/**
	 * @return the subCategoryName
	 */
	public String getSubCategoryName() {
		return subCategoryName;
	}

	/**
	 * @param subCategoryName the subCategoryName to set
	 */
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	/**
	 * @return the shiftDate
	 */
	public String getShiftDate() {
		return shiftDate;
	}

	/**
	 * @param shiftDate the shiftDate to set
	 */
	public void setShiftDate(String shiftDate) {
		this.shiftDate = shiftDate;
	}

	/**
	 * @return the scheuduleDate
	 */
	public String getScheuduleDate() {
		return scheuduleDate;
	}

	/**
	 * @param scheuduleDate the scheuduleDate to set
	 */
	public void setScheuduleDate(String scheuduleDate) {
		this.scheuduleDate = scheuduleDate;
	}

	

	
	

}
