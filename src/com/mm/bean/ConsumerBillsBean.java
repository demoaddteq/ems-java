package com.mm.bean;

import java.sql.Date;

public class ConsumerBillsBean extends CommonBean {
	
	private int staffId;
	private String staffName;
	
	private int facilityId;
	private String facilityName;
	
	private float salary;
	
	private int category;
	private String categoryName;
	
	private int subCategory;
	private String subCategoryName;
	
	private String joiningDate;
	private String active;
	
	private String adrresss;
	private String contactNo;
	private String salaryStauts;
	private String agencyName;
	private String jobType;
	
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
	 * @return the salaryStauts
	 */
	public String getSalaryStauts() {
		return salaryStauts;
	}
	/**
	 * @param salaryStauts the salaryStauts to set
	 */
	public void setSalaryStauts(String salaryStauts) {
		this.salaryStauts = salaryStauts;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getAdrresss() {
		return adrresss;
	}
	public void setAdrresss(String adrresss) {
		this.adrresss = adrresss;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public int getFacilityId() {
		return facilityId;
	}
	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public int getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(int subCategory) {
		this.subCategory = subCategory;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	/**
	 * @return the agencyName
	 */
	public String getAgencyName() {
		return agencyName;
	}
	/**
	 * @param agencyName the agencyName to set
	 */
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	/**
	 * @return the jobType
	 */
	public String getJobType() {
		return jobType;
	}
	/**
	 * @param jobType the jobType to set
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
}
