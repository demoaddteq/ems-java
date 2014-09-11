package com.mm.struts.corpo.form;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;


public class FacilityMapForm  extends ValidatorForm{

	private int facilityMapId;
	private int facilityId;
	private int createdBy;
	private Timestamp createdOn;
	private int modifiedBy;
	private Timestamp modifiedOn;
	private int isValid;
	
	private String flatType;
	private String noOfRooms;
	private String towerName;
	private String towerNo;
	private String noOfUnits;
	private String area;
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public int getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	public int getFacilityMapId() {
		return facilityMapId;
	}
	public void setFacilityMapId(int facilityMapId) {
		this.facilityMapId = facilityMapId;
	}
	public String getFlatType() {
		return flatType;
	}
	public void setFlatType(String flatType) {
		this.flatType = flatType;
	}
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Timestamp getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public String getNoOfRooms() {
		return noOfRooms;
	}
	public void setNoOfRooms(String noOfRooms) {
		this.noOfRooms = noOfRooms;
	}
	public String getnoOfUnits() {
		return noOfUnits;
	}
	public void setnoOfUnits(String noOfUnits) {
		this.noOfUnits = noOfUnits;
	}
	public String getTowerName() {
		return towerName;
	}
	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}
	public String getTowerNo() {
		return towerNo;
	}
	public void setTowerNo(String towerNo) {
		this.towerNo = towerNo;
	}
	

	

}
