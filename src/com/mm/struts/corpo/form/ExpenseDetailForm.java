package com.mm.struts.corpo.form;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;


public class ExpenseDetailForm  extends ValidatorForm{

	

	private int facility;
	private int type;
	private int expenseId;
	private String invoicName;
	private String payableTo;
	private String paymentType;
	private Float payAmount;
	private String expenseType;
	private String authorisedBy;
	private String expenseDate;
	private String payStatus;
	private String expenseSummary;
	private String operation;
	private int facilityId;
	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
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
	 * @return the invoicName
	 */
	public String getInvoicName() {
		return invoicName;
	}
	/**
	 * @param invoicName the invoicName to set
	 */
	public void setInvoicName(String invoicName) {
		this.invoicName = invoicName;
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
	 * @return the payAmount
	 */
	public Float getPayAmount() {
		return payAmount;
	}
	/**
	 * @param payAmount the payAmount to set
	 */
	public void setPayAmount(Float payAmount) {
		this.payAmount = payAmount;
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
}
