package com.mm.bean;

import java.sql.Date;

public class ParkingAllotmentBean extends CommonBean {

	//select A.userid,CONCAT_WS('  ',A.first_name,A.last_name) as resident_name,A.status as type,'Occupied' As resident_status,
	//A.creationDate from loginmaster A,resident_details B where A.userid=B.user_id AND B.vehicle!='';
	
	
	private int type;
	private String facilityName;
	private int facilityId;
	private Date billDate;
	private int billYear;
	private int billMonth;
	
	private String address;
	
	private int userId;
	private String residentName;
	private String resType;
	private String resStatus;
	private String memberSince;
	
	private String vehicleName;
	private String carNumber;
	private String stickerNumber;
	private String parkingNumber;
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
	 * @return the parkingNumber
	 */
	public String getParkingNumber() {
		return parkingNumber;
	}
	/**
	 * @param parkingNumber the parkingNumber to set
	 */
	public void setParkingNumber(String parkingNumber) {
		this.parkingNumber = parkingNumber;
	}
	/**
	 * @return the stickerNumber
	 */
	public String getStickerNumber() {
		return stickerNumber;
	}
	/**
	 * @param stickerNumber the stickerNumber to set
	 */
	public void setStickerNumber(String stickerNumber) {
		this.stickerNumber = stickerNumber;
	}
	/**
	 * @return the vehicleName
	 */
	public String getVehicleName() {
		return vehicleName;
	}
	/**
	 * @param vehicleName the vehicleName to set
	 */
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	/**
	 * @return the billDate
	 */
	public Date getBillDate() {
		return billDate;
	}
	/**
	 * @param billDate the billDate to set
	 */
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	/**
	 * @return the billMonth
	 */
	public int getBillMonth() {
		return billMonth;
	}
	/**
	 * @param billMonth the billMonth to set
	 */
	public void setBillMonth(int billMonth) {
		this.billMonth = billMonth;
	}
	/**
	 * @return the billYear
	 */
	public int getBillYear() {
		return billYear;
	}
	/**
	 * @param billYear the billYear to set
	 */
	public void setBillYear(int billYear) {
		this.billYear = billYear;
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
	 * @return the memberSince
	 */
	public String getMemberSince() {
		return memberSince;
	}
	/**
	 * @param memberSince the memberSince to set
	 */
	public void setMemberSince(String memberSince) {
		this.memberSince = memberSince;
	}
	/**
	 * @return the residentName
	 */
	public String getResidentName() {
		return residentName;
	}
	/**
	 * @param residentName the residentName to set
	 */
	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}
	/**
	 * @return the resStatus
	 */
	public String getResStatus() {
		return resStatus;
	}
	/**
	 * @param resStatus the resStatus to set
	 */
	public void setResStatus(String resStatus) {
		this.resStatus = resStatus;
	}
	/**
	 * @return the resType
	 */
	public String getResType() {
		return resType;
	}
	/**
	 * @param resType the resType to set
	 */
	public void setResType(String resType) {
		this.resType = resType;
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
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

	

}
