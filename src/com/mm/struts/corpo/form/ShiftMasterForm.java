package com.mm.struts.corpo.form;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;


public class ShiftMasterForm  extends ValidatorForm{
	private int shiftId;
	private String shiftName;
	private int facilityId;
	
	private String startHour;
	private String startMinuts;
	private String startAmPm;
	
	private String endHour;
	private String endMinuts;
	private String endAmPm;
	
	private String shiftStartTime;
	private String shiftEndTime;
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
	public String getShiftEndTime() {
		return shiftEndTime;
	}
	/**
	 * @param shiftEndTime the shiftEndTime to set
	 */
	public void setShiftEndTime(String shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}
	/**
	 * @return the shiftId
	 */
	public int getShiftId() {
		System.out.println("ShiftId in Shift Master Form=> "+shiftId);
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
	 * @return the shiftStartTime
	 */
	public String getShiftStartTime() {
		return shiftStartTime;
	}
	/**
	 * @param shiftStartTime the shiftStartTime to set
	 */
	public void setShiftStartTime(String shiftStartTime) {
		this.shiftStartTime = shiftStartTime;
	}
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
	 * @return the endAmPm
	 */
	public String getEndAmPm() {
		return endAmPm;
	}
	/**
	 * @param endAmPm the endAmPm to set
	 */
	public void setEndAmPm(String endAmPm) {
		this.endAmPm = endAmPm;
	}
	/**
	 * @return the endHour
	 */
	public String getEndHour() {
		return endHour;
	}
	/**
	 * @param endHour the endHour to set
	 */
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	/**
	 * @return the endMinuts
	 */
	public String getEndMinuts() {
		return endMinuts;
	}
	/**
	 * @param endMinuts the endMinuts to set
	 */
	public void setEndMinuts(String endMinuts) {
		this.endMinuts = endMinuts;
	}
	/**
	 * @return the startAmPm
	 */
	public String getStartAmPm() {
		return startAmPm;
	}
	/**
	 * @param startAmPm the startAmPm to set
	 */
	public void setStartAmPm(String startAmPm) {
		this.startAmPm = startAmPm;
	}
	/**
	 * @return the startHour
	 */
	public String getStartHour() {
		return startHour;
	}
	/**
	 * @param startHour the startHour to set
	 */
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	/**
	 * @return the startMinuts
	 */
	public String getStartMinuts() {
		return startMinuts;
	}
	/**
	 * @param startMinuts the startMinuts to set
	 */
	public void setStartMinuts(String startMinuts) {
		this.startMinuts = startMinuts;
	}

}
