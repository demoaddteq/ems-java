package com.mm.bean;

import java.sql.Date;

public class ServiceAllotmentBean extends CommonBean {

	//select A.mappingid,A.serviceid,CONCAT_WS('  ',B.first_name,B.last_name) AS NAME,C.tower,C.flat,D.servicename,A.status from consumerservicemapping A , loginmaster B,resident_details C,service D Where A.consumerid=B.userid AND A.consumerid=C.user_id AND A.serviceid=D.serviceid  AND A.status=1
	
	private int mappingId;
	private int serviceId;
	private String serviceName;
	private String consumerName;
	private String towerName;
	private String flatNo;
	
	private String dateFrom;
	private String dateTo;

	private int status;

	private int isMandetory;
	private int type;
	
	
	private String serviceDesc;
	private int monthlyFee;
	private int taxes;
	private int cess;
	private String facilityName;
	private int facilityId;
	private int consumerId;
	private Date billDate;
	
	
	private int billYear;
	private int billMonth;
	private Date dueDate;
	private String dueDateStr;
	
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

	/**
	 * @return the dateFrom
	 */
	public String getDateFrom() {
		return dateFrom;
	}

	/**
	 * @param dateFrom the dateFrom to set
	 */
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @return the dateTo
	 */
	public String getDateTo() {
		return dateTo;
	}

	/**
	 * @param dateTo the dateTo to set
	 */
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * @return the mappingId
	 */
	public int getMappingId() {
		return mappingId;
	}

	/**
	 * @param mappingId the mappingId to set
	 */
	public void setMappingId(int mappingId) {
		this.mappingId = mappingId;
	}

	/**
	 * @return the consumerName
	 */
	public String getConsumerName() {
		return consumerName;
	}

	/**
	 * @param consumerName the consumerName to set
	 */
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	/**
	 * @return the flatNo
	 */
	public String getFlatNo() {
		return flatNo;
	}

	/**
	 * @param flatNo the flatNo to set
	 */
	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	/**
	 * @return the towerName
	 */
	public String getTowerName() {
		return towerName;
	}

	/**
	 * @param towerName the towerName to set
	 */
	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}

	

}
