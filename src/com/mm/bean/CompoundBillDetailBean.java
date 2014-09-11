package com.mm.bean;

import java.util.Vector;

public class CompoundBillDetailBean extends CommonBean {
	private int consumerId;
	private int month;
	private int year;
	private int totalAmount;
	private int paidAmount;
	private String paymentMode;
	private Vector ServiceBeanList;
	private int compoundBillId;
	private String uniqueId;
	private ConsumerBean consumerBean;

	/**
	 * @return the consumerBean
	 */
	public ConsumerBean getConsumerBean() {
		return consumerBean;
	}

	/**
	 * @param consumerBean the consumerBean to set
	 */
	public void setConsumerBean(ConsumerBean consumerBean) {
		this.consumerBean = consumerBean;
	}

	/**
	 * @return the serviceBeanList
	 */
	public Vector getServiceBeanList() {
		return ServiceBeanList;
	}

	/**
	 * @param serviceBeanList
	 *            the serviceBeanList to set
	 */
	public void setServiceBeanList(Vector serviceBeanList) {
		ServiceBeanList = serviceBeanList;
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
	 * @return the totalAmount
	 */
	public int getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount
	 *            the totalAmount to set
	 */
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the paidAmount
	 */
	public int getPaidAmount() {
		return paidAmount;
	}

	/**
	 * @param paidAmount
	 *            the paidAmount to set
	 */
	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode
	 *            the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the compoundBillId
	 */
	public int getCompoundBillId() {
		return compoundBillId;
	}

	/**
	 * @param compoundBillId the compoundBillId to set
	 */
	public void setCompoundBillId(int compoundBillId) {
		this.compoundBillId = compoundBillId;
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

	

}
