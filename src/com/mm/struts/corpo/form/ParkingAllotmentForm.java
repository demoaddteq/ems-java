package com.mm.struts.corpo.form;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;


public class ParkingAllotmentForm  extends ValidatorForm{

	private int type;
	private int status;
	private String operation;
	
	private int residentId;
	private String facilityName;
	private int facilityId;
	private String carNumber;
	private String stickerNo;
	private String parkingNo;
	private int createdBy;
	private Timestamp createdOn;
	private int modifiedBy;
	private Timestamp modifiedOn;
	/**
	 * @return the carNumber
	 */
	public String getCarNumber() {
		return carNumber;
	}
	/**
	 * @param carNumber the carNumber to set
	 */
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
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
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
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
	 * @return the modifiedOn
	 */
	public Timestamp getModifiedOn() {
		return modifiedOn;
	}
	/**
	 * @param modifiedOn the modifiedOn to set
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
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	/**
	 * @return the parkingNo
	 */
	public String getParkingNo() {
		return parkingNo;
	}
	/**
	 * @param parkingNo the parkingNo to set
	 */
	public void setParkingNo(String parkingNo) {
		this.parkingNo = parkingNo;
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
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the stickerNo
	 */
	public String getStickerNo() {
		return stickerNo;
	}
	/**
	 * @param stickerNo the stickerNo to set
	 */
	public void setStickerNo(String stickerNo) {
		this.stickerNo = stickerNo;
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
