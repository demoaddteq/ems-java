package com.mm.bean;

import java.sql.Date;

public class ExpenseDetailBean extends CommonBean {
	
	private int facilityId;
	private String facilityName;
	private int type;
	
	
	private int expenseId;
	private String invoiceName;
	private String payableTo;
	private String paymentType;
	private Float amount;
	private String expenseType;
	private String authorisedBy;
	private String expenseDate;
	private String expenseSummary;
	private String payStatus;
	/**
	 * @return the amount
	 */
	public Float getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	/**
	 * @return the authorisedBy
	 */
	public String getAuthorisedBy() {
		return authorisedBy;
	}
	/**
	 * @param authorisedBy the authorisedBy to set
	 */
	public void setAuthorisedBy(String authorisedBy) {
		this.authorisedBy = authorisedBy;
	}
	/**
	 * @return the expenseDate
	 */
	public String getExpenseDate() {
		return expenseDate;
	}
	/**
	 * @param expenseDate the expenseDate to set
	 */
	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}
	/**
	 * @return the expenseId
	 */
	public int getExpenseId() {
		return expenseId;
	}
	/**
	 * @param expenseId the expenseId to set
	 */
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	/**
	 * @return the expenseSummary
	 */
	public String getExpenseSummary() {
		return expenseSummary;
	}
	/**
	 * @param expenseSummary the expenseSummary to set
	 */
	public void setExpenseSummary(String expenseSummary) {
		this.expenseSummary = expenseSummary;
	}
	/**
	 * @return the expenseType
	 */
	public String getExpenseType() {
		return expenseType;
	}
	/**
	 * @param expenseType the expenseType to set
	 */
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
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
	 * @return the invoiceName
	 */
	public String getInvoiceName() {
		return invoiceName;
	}
	/**
	 * @param invoiceName the invoiceName to set
	 */
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	/**
	 * @return the payableTo
	 */
	public String getPayableTo() {
		return payableTo;
	}
	/**
	 * @param payableTo the payableTo to set
	 */
	public void setPayableTo(String payableTo) {
		this.payableTo = payableTo;
	}
	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * @return the payStatus
	 */
	public String getPayStatus() {
		return payStatus;
	}
	/**
	 * @param payStatus the payStatus to set
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
}
