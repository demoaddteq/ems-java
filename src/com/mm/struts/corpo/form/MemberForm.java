package com.mm.struts.corpo.form;

import java.sql.Date;
import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;


public class MemberForm  extends ValidatorForm{

	

	private String staffName;
	private int facility;
	private int facilityId;
	private String facilityName;
	private int type;
	
	private int userId;
	private int companyId;
	private String loginName;
	private String password;
	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNo;
	private String mobileNo;
	private String address;
	private int country;
	private int state;
	private int city;
	private String zip;
	private String birthDay;
	private String birthPlace;
	private String maritalStatus;
	private String profession;	
	private int createdBy;
	private Timestamp createdOn;
	private int modifiedBy;
	private Timestamp modifiedOn;
	private String operation;
	private String requestId;
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the birthDay
	 */
	public String getBirthDay() {
		return birthDay;
	}
	/**
	 * @param birthDay the birthDay to set
	 */
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	/**
	 * @return the birthPlace
	 */
	public String getBirthPlace() {
		return birthPlace;
	}
	/**
	 * @param birthPlace the birthPlace to set
	 */
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	/**
	 * @return the city
	 */
	public int getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(int city) {
		this.city = city;
	}
	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the country
	 */
	public int getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(int country) {
		this.country = country;
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
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}
	/**
	 * @param maritalStatus the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}
	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}
	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}
	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
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
	 * @return the state
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
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
	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
}
