package com.mm.bean;

import java.sql.Date;

public class ConsumerBillDetailBean extends CommonBean {
	private int consumerId;
	private int month;
	private int year;
	private int dueAmount;
	
	private Date duedate;
	private ConsumerBean consumerDetail;
	private int billNo;
	
	private int monthlyFee;
	private float tax;
	private float cess;
	private float gross;
	
	private int billId;
	private int facilityId;
	private String facilityName;
	private int serviceId;
	private String serviceName;
	private int residentId;
	private String residentName;
	private float prevMeterReading;
	private float currentMeterReading;
	private String billStartDate;
	private String billEndDate;
	
	
	
	public int getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(int monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public float getCess() {
		return cess;
	}

	public void setCess(float cess) {
		this.cess = cess;
	}

	public float getGross() {
		return gross;
	}

	public void setGross(float gross) {
		this.gross = gross;
	}

	/**
	 * @return the billNo
	 */
	public int getBillNo() {
		return billNo;
	}

	/**
	 * @param billNo the billNo to set
	 */
	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	/**
	 * @return the duedate
	 */
	public Date getDuedate() {
		return duedate;
	}

	/**
	 * @param duedate
	 *            the duedate to set
	 */
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
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
	 * @return the month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
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
	 * @return the consumerDetail
	 */
	public ConsumerBean getConsumerDetail() {
		return consumerDetail;
	}

	/**
	 * @param consumerDetail
	 *            the consumerDetail to set
	 */
	public void setConsumerDetail(ConsumerBean consumerDetail) {
		this.consumerDetail = consumerDetail;
	}

	/**
	 * @return the billEndDate
	 */
	public String getBillEndDate() {
		return billEndDate;
	}

	/**
	 * @param billEndDate the billEndDate to set
	 */
	public void setBillEndDate(String billEndDate) {
		this.billEndDate = billEndDate;
	}

	/**
	 * @return the billId
	 */
	public int getBillId() {
		return billId;
	}

	/**
	 * @param billId the billId to set
	 */
	public void setBillId(int billId) {
		this.billId = billId;
	}

	/**
	 * @return the billStartDate
	 */
	public String getBillStartDate() {
		return billStartDate;
	}

	/**
	 * @param billStartDate the billStartDate to set
	 */
	public void setBillStartDate(String billStartDate) {
		this.billStartDate = billStartDate;
	}

	/**
	 * @return the currentMeterReading
	 */
	public float getCurrentMeterReading() {
		return currentMeterReading;
	}

	/**
	 * @param currentMeterReading the currentMeterReading to set
	 */
	public void setCurrentMeterReading(float currentMeterReading) {
		this.currentMeterReading = currentMeterReading;
	}

	/**
	 * @return the facilityId
	 */
	public int getFacilityId() {
		return facilityId;
	}

	/**
	 * @param facilityId the facilityId to set
	 */
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * @param facilityName the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	/**
	 * @return the prevMeterReading
	 */
	public float getPrevMeterReading() {
		return prevMeterReading;
	}

	/**
	 * @param prevMeterReading the prevMeterReading to set
	 */
	public void setPrevMeterReading(float prevMeterReading) {
		this.prevMeterReading = prevMeterReading;
	}

	/**
	 * @return the residentId
	 */
	public int getResidentId() {
		return residentId;
	}

	/**
	 * @param residentId the residentId to set
	 */
	public void setResidentId(int residentId) {
		this.residentId = residentId;
	}

	/**
	 * @return the residentName
	 */
	public String getResidentName() {
		System.out.println("getResidentName-->"+residentName);
		return residentName;
	}

	/**
	 * @param residentName the residentName to set
	 */
	public void setResidentName(String residentName) {
		System.out.println("setResidentName-->"+residentName);
		this.residentName = residentName;
	}

	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}
