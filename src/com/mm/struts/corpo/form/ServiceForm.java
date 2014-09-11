package com.mm.struts.corpo.form;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.struts.validator.ValidatorForm;


public class ServiceForm  extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -529079429368128204L;
	private int isMandetory;
	private int type;
	private int serviceId;
	private String serviceName;
	private String serviceDesc;
	private int monthlyFee;
	private String subscription;
	//private int cess;
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
	
//added by Renuka 09102009 start
	private int taxes = 0;
	private int cess = 0;
	
	public int getCess() {
		return cess;
	}

	public void setCess(int cess) {
		this.cess = cess;
	}

	public int getTaxes() {
		return taxes;
	}

	public void setTaxes(int taxes) {
		this.taxes = taxes;
	}
//added by Renuka 09102009 stop
	
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
	//public int getTaxes() {
		//return taxes;
	//}

	/**
	 * @param taxes
	 *            the taxes to set
	 */
	//public void setTaxes(int taxes) {
		//this.taxes = taxes;
	//}

	/**
	 * @return the cess
	 */
	//public int getCess() {
		//return cess;
	//}

	/**
	 * @param cess
	 *            the cess to set
	 */
	//public void setCess(int cess) {
		//this.cess = cess;
	//}

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

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	

}
