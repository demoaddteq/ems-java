package com.mm.core.master;

import java.rmi.server.UID;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.mm.bean.CompoundBillDetailBean;
import com.mm.bean.ConsumerBean;
import com.mm.bean.ConsumerBillDetailBean;
import com.mm.bean.ConsumerSrvciceMappingBean;
import com.mm.bean.StaffMasterBean;
import com.mm.bean.StaffBean;
import com.mm.core.resource.DBConstant;
import com.mm.struts.corpo.form.StaffMasterForm;

/* Created By Ajay Kumar
 * 03/10/2009
 */

public class StaffMasterDao {

	public Vector getStaffList(DataSource  datasource, int facilityId) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector staffList = new Vector();
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_ALL_STAFF_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, facilityId);
				rs = pStatement.executeQuery();
				while (rs.next()) {
					StaffMasterBean bean = new StaffMasterBean();
					bean.setStaffId(rs.getInt("staff_id"));
					bean.setStaffName(rs.getString("staffname"));
					bean.setFacilityId(rs.getInt("facility_id"));
					bean.setFacilityName(rs.getString("facility_name"));
					bean.setSalary(rs.getFloat("salary"));
					bean.setCategory(rs.getInt("category"));
					bean.setCategoryName(rs.getString("cat_name"));
					bean.setSubCategory(rs.getInt("subcategory"));
					bean.setSubCategoryName(rs.getString("subcat_name"));
					bean.setJoiningDate(rs.getString("joining_date"));
					bean.setAdrresss(rs.getString("address"));
					bean.setContactNo(rs.getString("contact_no"));
					
					staffList.add(bean);
				}
			} finally {
				try {
					if (rs != null) {
						rs.close();
						rs = null;
					}
					if (pStatement != null) {
						pStatement.close();
						pStatement = null;
					}
					if (con != null) {
						con.close();
						con = null;
					}
				} catch (SQLException exception2) {

				}
			}
			return staffList;
		}

		public Vector getServiceListResident(DataSource  datasource, int consumerId, int facilityId) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector staffList = new Vector();
			try {
				con = datasource.getConnection();
				//String sql = DBConstant.SELECT_RESIDENT_SERVICE_SQL;
				String sql = DBConstant.SEARCH_SERVICE_ASSIGNED;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, facilityId);
				pStatement.setInt(2, consumerId);

				rs = pStatement.executeQuery();
				while (rs.next()) {
					StaffBean bean = new StaffBean();
					bean.setServiceId(rs.getInt("serviceId"));
					bean.setServiceName(rs.getString("serviceName"));
					bean.setServiceDesc(rs.getString("serviceDesc"));
					bean.setMonthlyFee(rs.getInt("monthlyFee"));
					bean.setTaxes(rs.getInt("taxes"));
					bean.setCess(rs.getInt("cess"));
					bean.setFacilityName(rs.getString("facilityName"));
					bean.setStatus(rs.getInt("status"));
					staffList.add(bean);
				}
			} finally {
				try {
					if (rs != null) {
						rs.close();
						rs = null;
					}
					if (pStatement != null) {
						pStatement.close();
						pStatement = null;
					}
					if (con != null) {
						con.close();
						con = null;
					}
				} catch (SQLException exception2) {

				}
			}
			return staffList;
		}

		public Vector searchServices(DataSource  datasource, String serviceName) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector staffList = new Vector();
			try {
				con = datasource.getConnection();
				String sql = "select * from service where active='y' and servicename like '%"
						+ serviceName + "%'";
				pStatement = con.prepareStatement(sql);

				rs = pStatement.executeQuery();
				while (rs.next()) {
					StaffBean bean = new StaffBean();
					bean.setServiceId(rs.getInt("serviceId"));
					bean.setServiceName(rs.getString("serviceName"));
					bean.setServiceDesc(rs.getString("serviceDesc"));
					bean.setMonthlyFee(rs.getInt("monthlyFee"));
					bean.setTaxes(rs.getInt("taxes"));
					bean.setCess(rs.getInt("cess"));
					bean.setFacilityName(rs.getString("facilityName"));
					bean.setServiceId(rs.getInt("serviceId"));
					staffList.add(bean);
				}
			} finally {
				try {
					if (rs != null) {
						rs.close();
						rs = null;
					}
					if (pStatement != null) {
						pStatement.close();
						pStatement = null;
					}
					if (con != null) {
						con.close();
						con = null;
					}
				} catch (SQLException exception2) {

				}
			}
			return staffList;
		}
		
		public StaffMasterBean getStaffData(DataSource  datasource, int staffId) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			StaffMasterBean bean = new StaffMasterBean();
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_STAFF_UPDATE_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, staffId);
				
				//select A.staff_id,A.staffname,A.facility_id,B.facility_name,A.category,C.cat_name,A.subcategory,D.subcat_name,
				//A.joining_date,A.jobtype,A.salary,A.address,A.contact_no from staff_master A ,facility B,staff_category C,staff_subcategory D Where  A.facility_id=B.facility_id AND A.category=C.cat_id AND A.subcategory=D.subcat_id
				
				rs = pStatement.executeQuery();
				if (rs.next()) {
					
					bean.setStaffId(rs.getInt("staff_id"));
					bean.setStaffName(rs.getString("staffname"));
					bean.setFacilityId(rs.getInt("facility_id"));
					bean.setFacilityName(rs.getString("facility_name"));
					bean.setCategory(rs.getInt("category"));
					bean.setCategoryName(rs.getString("cat_name"));
					bean.setSubCategory(rs.getInt("subcategory"));
					bean.setSubCategoryName(rs.getString("subcat_name"));
					bean.setJoiningDate(rs.getString("joining_date"));
					bean.setJobType(rs.getString("jobtype"));
					bean.setSalary(rs.getFloat("salary"));
					bean.setAdrresss(rs.getString("address"));
					bean.setContactNo(rs.getString("contact_no"));
					bean.setAgencyName(rs.getString("agencyname"));
					
				}
			} finally {
				try {
					if (rs != null) {
						rs.close();
						rs = null;
					}
					if (pStatement != null) {
						pStatement.close();
						pStatement = null;
					}
					if (con != null) {
						con.close();
						con = null;
					}
				} catch (SQLException exception2) {
				}
			}
			return bean;
		}

		public void insertDetail(DataSource  datasource, StaffMasterForm staffForm) throws Exception {
			Connection con = null;
			PreparedStatement pStatement= null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.INSERT_STAFF_MASTER_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setString(1, staffForm.getStaffName());
				pStatement.setInt(2, staffForm.getFacilityId());
				pStatement.setInt(3, staffForm.getStaffCategory());
				pStatement.setInt(4, staffForm.getStaffSubCategory());
				pStatement.setString(5, staffForm.getJoiningDate());
				pStatement.setString(6, staffForm.getJobType());
				pStatement.setString(7, handleNullForString(staffForm.getSalary()));
				pStatement.setString(8, "Y");
				pStatement.setString(9, staffForm.getAddress());
				pStatement.setString(10, staffForm.getContactNo());
				pStatement.setString(11, staffForm.getAgencyName());
				
				
				int result = pStatement.executeUpdate();
				// Default Assign the new Service to all the members of this Facility.
				int facilityId = staffForm.getFacilityId();
				
				// Step 0: Retrieve the newly added service assuming that there can be only one service with a given name within a Facility.
				sql = "Select s.serviceid from service s where s.facilityid=? and s.servicename=?";
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, facilityId);
				pStatement.setString(2, staffForm.getServiceName());
				
				rs = pStatement.executeQuery();
				int serviceId = 0;
				
				if (rs.next()) {
					serviceId = rs.getInt("serviceid");
				}
				
				// Step 1: Get all the active consumers of the facility.
				/*sql = DBConstant.ALL_CONSUMER_BY_FACILITY;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, facilityId);
				rs = pStatement.executeQuery();
				
				if (rs != null) {
					
					String sqlInsert = DBConstant.INSERT_CONSUMER_SERVICE_MAPPING_SQL;
					stmt = con.prepareStatement(sqlInsert);
					
//					"insert into consumerservicemapping(consumerid,serviceid,datefrom,dateto,"
//					+ "createdby,createdon,modifiedby) " + "values (?,?,?,?,?,?,?)";
					
					Calendar cal = Calendar.getInstance();
				    java.sql.Date jsqlD = new java.sql.Date( cal.getTime().getTime() );

					
					
					// Step 2:  Iterate over all the active members and subscribe them to new service by default.
					while (rs.next()){
						int consumerId = rs.getInt(1);
						
						stmt.setInt(1, consumerId);
						stmt.setInt(2, serviceId);
						stmt.setDate(3, jsqlD);
						stmt.setDate(4, jsqlD);
						stmt.setInt(5, 0);
						stmt.setTimestamp(6, getCurrentJavaSqlTimestamp());
						stmt.setInt(7, 0);
						stmt.setInt(8,2);  
						
						stmt.executeUpdate();
						
					}
				}*/
				
			} finally {
				try {
					if (pStatement != null) {
						pStatement.close();
						pStatement = null;
					}
					if (con != null) {
						con.close();
						con = null;
					}
				} catch (SQLException exception2) {

				}
			}
		}

		public void updateStaffDetail(DataSource  datasource, StaffMasterForm staffForm) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.UPDATE_STAFF_SQL;
				
				//update staff_master set staffname=?,category=?,subcategory=?,joining_date=?,jobtype=?,salary=?,address=?,contact_no=?,agencyname=? where staff_id=?
				pStatement = con.prepareStatement(sql);

				pStatement.setString(1, handleNullForString(staffForm.getStaffName()));
				pStatement.setInt(2, staffForm.getStaffCategory());
				pStatement.setInt(3, staffForm.getStaffSubCategory());
				pStatement.setString(4, staffForm.getJoiningDate());
				pStatement.setString(5, staffForm.getJobType());
				pStatement.setString(6, staffForm.getSalary());
				pStatement.setString(7, staffForm.getAddress());
				pStatement.setString(8, staffForm.getContactNo());
				pStatement.setString(9, staffForm.getAgencyName());
				pStatement.setInt(10, staffForm.getStaffId());

				int result = pStatement.executeUpdate();
			} finally {
				try {
					if (pStatement != null) {
						pStatement.close();
						pStatement = null;
					}
					if (con != null) {
						con.close();
						con = null;
					}
				} catch (SQLException exception2) {

				}
			}
		}

		public void deleteService(DataSource  datasource, int serviceId) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.DELETE_SERVICE_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, serviceId);
				int result = pStatement.executeUpdate();
			} finally {
				try {
					if (pStatement != null) {
						pStatement.close();
						pStatement = null;
					}
					if (con != null) {
						con.close();
						con = null;
					}
				} catch (SQLException exception2) {

				}
			}
		}

		public void inactiveStaff(DataSource  datasource, String staffIdsArr[]) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.INACTIVE_STAFF_SQL;
				pStatement = con.prepareStatement(sql);
				
				for (int i = 0; i < staffIdsArr.length; i++) {
					int staffId = Integer.parseInt(staffIdsArr[i]);
						pStatement.setInt(1, staffId);
						pStatement.addBatch();
				}
				int result[] = pStatement.executeBatch();
			} finally {
				try {
					if (pStatement != null) {
						pStatement.close();
						pStatement = null;
					}
					if (con != null) {
						con.close();
						con = null;
					}
				} catch (SQLException exception2) {

				}
			}
		}

				public String handleNullForString(Object obj) {
			if (obj == null) {
				return "";
				// return "";
			} else {
				return obj.toString();
			}
		}

		public long calculateServiceAmout(int serviceUsedInDays, int month,
				int year, int chargesPerMonth) {
			int daysInMonth = getTotalDaysInMonth(month);
			if (month == 2) {
				if (isLeapYear(year)) {
					daysInMonth++;
				}
			}
			double daysInMonthDoubleVal = daysInMonth;
			double chargesInToDays = chargesPerMonth * serviceUsedInDays;
			double amount = chargesInToDays / daysInMonthDoubleVal;
			long val = Math.round(amount);
			return val;
		}

		public int getDaysOfServiceUsedForSErviceStartMonth(Date dateFrom) {
			String dateFromArr[] = getDateArr(dateFrom);
			int serviceStartDay = Integer.parseInt(dateFromArr[2]);
			int month = Integer.parseInt(dateFromArr[1]);
			int daysInMonth = getTotalDaysInMonth(month);
			int daysOfServiceUsed = daysInMonth - serviceStartDay;
			return daysOfServiceUsed++;
		}

		public int getDaysOfServiceUsed(Date dateFrom, Date dateTo) {
			String dateFromArr[] = getDateArr(dateFrom);
			String dateToArr[] = getDateArr(dateTo);
			int serviceStartDay = Integer.parseInt(dateFromArr[2]);
			int serviceEndDay = Integer.parseInt(dateToArr[2]);
			int days = serviceEndDay - serviceStartDay;
			days++;
			return days;
		}

		public int getTotalDaysInMonth(int month) {
			int monthArr[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
			return monthArr[month];
		}

		public boolean isLeapYear(int year) {
			if ((year % 100) == 0) {
				return (year % 400) == 0;
			} else {
				return (year % 4) == 0;
			}

		}

		public boolean isCurrentMonthAndCurrentYear(Date sqldate, Date billDate) {
			boolean currentMonthAndCurrentYear = false;

			String sqlDateArr[] = getDateArr(sqldate);
			String billDateArr[] = getDateArr(billDate);

			int sqlMonth = Integer.parseInt(sqlDateArr[1]);
			int sqlYear = Integer.parseInt(sqlDateArr[0]);

			int billMonth = Integer.parseInt(billDateArr[1]);
			int billYear = Integer.parseInt(billDateArr[0]);

			if (sqlYear == billYear) {
				if (sqlMonth == billMonth) {
					currentMonthAndCurrentYear = true;
				}
			}

			return currentMonthAndCurrentYear;
		}

			public String[] getDateArr(Date date) {
			String dateStr = date.toString();
			String dateStrArr[] = dateStr.split("-");
			return dateStrArr;
		}
			
		public static java.sql.Timestamp getCurrentJavaSqlTimestamp() {
		    java.util.Date date = new java.util.Date();
		    return new java.sql.Timestamp(date.getTime());
	  }
}
