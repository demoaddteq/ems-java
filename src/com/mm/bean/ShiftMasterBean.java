package com.mm.bean;

import java.sql.Date;

public class ShiftMasterBean extends CommonBean {
	
	private int shiftId;
	private String shiftName;
	private int facilityId;
	private String facilityName;
	private String shiftStartTime;
	private String shiftEndTime;
	private int type;
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
}
