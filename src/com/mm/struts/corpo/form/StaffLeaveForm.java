package com.mm.struts.corpo.form;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;


public class StaffLeaveForm  extends ValidatorForm{
	private int leaveId;
	private int staffId;
	private String leaveType;
	private int facilityId;
	private String leaveFrom;
	private String leaveTo;
	private int noOfDays;
	private String operation;
	private int modifiedBy;
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
	 * @return the shiftEndTime
	 */
		/**
	 * @return the modifiedBy
	 */
	public int getModifiedBy() {
		return modifiedBy;
	}
	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	/**
	 * @return the leaveFrom
	 */
	public String getLeaveFrom() {
		return leaveFrom;
	}
	/**
	 * @param leaveFrom the leaveFrom to set
	 */
	public void setLeaveFrom(String leaveFrom) {
		this.leaveFrom = leaveFrom;
	}
	/**
	 * @return the leaveTo
	 */
	public String getLeaveTo() {
		return leaveTo;
	}
	/**
	 * @param leaveTo the leaveTo to set
	 */
	public void setLeaveTo(String leaveTo) {
		this.leaveTo = leaveTo;
	}
	/**
	 * @return the leaveType
	 */
	public String getLeaveType() {
		return leaveType;
	}
	/**
	 * @param leaveType the leaveType to set
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
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
	 * @return the noOfDays
	 */
	public int getNoOfDays() {
		return noOfDays;
	}
	/**
	 * @param noOfDays the noOfDays to set
	 */
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}
	/**
	 * @return the leaveId
	 */
	public int getLeaveId() {
		return leaveId;
	}
	/**
	 * @param leaveId the leaveId to set
	 */
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

}
