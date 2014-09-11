package com.mm.bean;

import java.sql.Date;

public class ComplaintBean extends CommonBean {
	private int complaintId;
	
	private int searchComplaintId;
	private String title;
	private String type;
	private String desc;
	private String name;
	private String status;
	private String address;
	private int facilityid;
	
	/**
	 * @return the complaintType
	 */
	public String getComplaintType() {
		return complaintType;
	}

	/**
	 * @param complaintType the complaintType to set
	 */
	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}

	private Date date;
	private String dateStr;

	
	private String complaintStatus;
	private String complaintDate;
	private String complaintNotes;
	private String complaintAssignedTo;
	private String complaintType;
	private String complainentBy;

	/**
	 * @return the complaintId
	 */
	public int getComplaintId() {
		return complaintId;
	}

	/**
	 * @param complaintId
	 *            the complaintId to set
	 */
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the facilityid
	 */
	public int getFacilityid() {
		return facilityid;
	}

	/**
	 * @param facilityid
	 *            the facilityid to set
	 */
	public void setFacilityid(int facilityid) {
		this.facilityid = facilityid;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the dateStr
	 */
	public String getDateStr() {
		return dateStr;
	}

	/**
	 * @param dateStr
	 *            the dateStr to set
	 */
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	

	/**
	 * @return the complaintStatus
	 */
	public String getComplaintStatus() {
		return complaintStatus;
	}

	/**
	 * @param complaintStatus
	 *            the complaintStatus to set
	 */
	public void setComplaintStatus(String complaintStatus) {
		this.complaintStatus = complaintStatus;
	}

	/**
	 * @return the complaintDate
	 */
	public String getComplaintDate() {
		return complaintDate;
	}

	/**
	 * @param complaintDate
	 *            the complaintDate to set
	 */
	public void setComplaintDate(String complaintDate) {
		this.complaintDate = complaintDate;
	}

	/**
	 * @return the complaintNotes
	 */
	public String getComplaintNotes() {
		return complaintNotes;
	}

	/**
	 * @param complaintNotes
	 *            the complaintNotes to set
	 */
	public void setComplaintNotes(String complaintNotes) {
		this.complaintNotes = complaintNotes;
	}

	/**
	 * @return the complaintAssignedTo
	 */
	public String getComplaintAssignedTo() {
		return complaintAssignedTo;
	}

	/**
	 * @param complaintAssignedTo
	 *            the complaintAssignedTo to set
	 */
	public void setComplaintAssignedTo(String complaintAssignedTo) {
		this.complaintAssignedTo = complaintAssignedTo;
	}

	/**
	 * @return the complainentBy
	 */
	public String getComplainentBy() {
		return complainentBy;
	}

	/**
	 * @param complainentBy the complainentBy to set
	 */
	public void setComplainentBy(String complainentBy) {
		this.complainentBy = complainentBy;
	}

	/**
	 * @return the searchComplaintId
	 */
	public int getSearchComplaintId() {
		return searchComplaintId;
	}

	/**
	 * @param searchComplaintId the searchComplaintId to set
	 */
	public void setSearchComplaintId(int searchComplaintId) {
		this.searchComplaintId = searchComplaintId;
	}

}
