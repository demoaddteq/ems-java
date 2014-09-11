package com.mm.bean;

import java.sql.Date;

public class StaffLeaveBean extends CommonBean {
	
	private int type;
	private int leaveId;
	private int staffId;
	private String staffName;
	private String leaveType;
	private String leaveFromDate;
	private String leaveToDate;
	private int noofdays;
	private int facilityId;
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
	 * @return the leaveFromDate
	 */
	public String getLeaveFromDate() {
		return leaveFromDate;
	}
	/**
	 * @param leaveFromDate the leaveFromDate to set
	 */
	public void setLeaveFromDate(String leaveFromDate) {
		this.leaveFromDate = leaveFromDate;
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
	/**
	 * @return the leaveToDate
	 */
	public String getLeaveToDate() {
		return leaveToDate;
	}
	/**
	 * @param leaveToDate the leaveToDate to set
	 */
	public void setLeaveToDate(String leaveToDate) {
		this.leaveToDate = leaveToDate;
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
	 * @return the noofdays
	 */
	public int getNoofdays() {
		return noofdays;
	}
	/**
	 * @param noofdays the noofdays to set
	 */
	public void setNoofdays(int noofdays) {
		this.noofdays = noofdays;
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
