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
import com.mm.bean.StaffLeaveBean;
import com.mm.bean.StaffBean;
import com.mm.bean.StaffLeaveBean;
import com.mm.core.resource.DBConstant;
import com.mm.struts.corpo.form.StaffLeaveForm;;

public class StaffLeaveDao {

	public Vector getLeaveList(DataSource  datasource, int facilityId) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector staffLeaveList = new Vector();
			try {
				System.out.println("facilityId For Leave==>"+facilityId);
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_ALL_LEAVE_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, facilityId);
				rs = pStatement.executeQuery();
				while (rs.next()) {
					StaffLeaveBean bean = new StaffLeaveBean();
					bean.setLeaveId(rs.getInt("leaveId"));
					bean.setStaffId(rs.getInt("staffId"));
					bean.setStaffName(rs.getString("staffname"));
					bean.setLeaveType(rs.getString("leavetype"));
					bean.setLeaveFromDate(rs.getString("leavefromdate"));
					bean.setLeaveToDate(rs.getString("leavetodate"));
					bean.setNoofdays(rs.getInt("no_ofdays"));
					staffLeaveList.add(bean);
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
			return staffLeaveList;
		}

		public Vector getServiceListResident(DataSource  datasource, int consumerId, int facilityId) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector staffLeaveList = new Vector();
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
					staffLeaveList.add(bean);
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
			return staffLeaveList;
		}

		public Vector searchServices(DataSource  datasource, String serviceName) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector staffLeaveList = new Vector();
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
					staffLeaveList.add(bean);
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
			return staffLeaveList;
		}

		public ArrayList getCompoundBillDetails(DataSource  datasource, int facilityId, Date billDate)
				throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			String dateArr[] = getDateArr(billDate);
			int month = Integer.parseInt(dateArr[1]);
			int year = Integer.parseInt(dateArr[0]);

			ArrayList compoundDetailList = new ArrayList();
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_COMPOUND_BILL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, facilityId);
				pStatement.setInt(2, year);
				pStatement.setInt(3, month);
				rs = pStatement.executeQuery();
				while (rs.next()) {
					CompoundBillDetailBean bean = new CompoundBillDetailBean();
					bean.setCompoundBillId(rs.getInt("billid"));
					bean.setConsumerId(rs.getInt("consumerid"));
					bean.setMonth(rs.getInt("month"));
					bean.setYear(rs.getInt("year"));
					bean.setTotalAmount(rs.getInt("payableamt"));
					bean.setPaidAmount(rs.getInt("paidamt"));
					//bean.setPaymentMode(rs.getString("paymentmode"));
					//bean.setUniqueId(rs.getString("uid"));
					ConsumerBean consumerBean = new ConsumerBean();
					consumerBean.setFirstName(rs.getString("firstName"));
					consumerBean.setLastName(rs.getString("lastName"));
					bean.setConsumerBean(consumerBean);
					compoundDetailList.add(bean);
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
			return compoundDetailList;
		}

		public ArrayList getBillDetails(DataSource  datasource, int consumerId, int month, int year)
				throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;

			ArrayList billDetailList = new ArrayList();
			try {
				con = datasource.getConnection();
				//String sql = DBConstant.SELECT_DETAILED_BILL;
				String sql = DBConstant.SELECT_DETAILED_BILL_2;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, month);
				pStatement.setInt(2, year);
				pStatement.setInt(3, consumerId);
				rs = pStatement.executeQuery();
				while (rs.next()) {
					ConsumerBillDetailBean bean = new ConsumerBillDetailBean();
					//bean.setBillNo(billNo);
					bean.setConsumerId(rs.getInt("consumerid"));
					bean.setMonth(rs.getInt("month"));
					bean.setYear(rs.getInt("year"));
					bean.setServiceId(rs.getInt("serviceid"));
					bean.setDueAmount(rs.getInt("gross"));
					//bean.setDuedate(rs.getDate("duedate"));
					ConsumerBean consumerBean = new ConsumerBean();
					consumerBean.setFirstName(rs.getString("firstname"));
					consumerBean.setLastName(rs.getString("lastname"));
					consumerBean.setAddress(rs.getString("address"));
					consumerBean.setPhone(rs.getString("phone"));
					consumerBean.setServiceName(rs.getString("servicename"));
					consumerBean.setConsumerId(rs.getInt("consumerid"));
					bean.setConsumerDetail(consumerBean);
					billDetailList.add(bean);
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
			return billDetailList;
		}

		public ArrayList getBillDetailsResident(DataSource  datasource, String smonth, String syear, int consumerid)
				throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;

			ArrayList billDetailList = new ArrayList();
			//String dateArr[] = getDateArr(billDate);
			int month = Integer.parseInt(smonth);
			int year = Integer.parseInt(syear);
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_DETAILED_BILL_2;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, month);
				pStatement.setInt(2, year);
				pStatement.setInt(3, consumerid);
				rs = pStatement.executeQuery();
				
				while (rs.next()) {
					ConsumerBillDetailBean bean = new ConsumerBillDetailBean();
					bean.setConsumerId(rs.getInt("consumerid"));
					bean.setMonth(rs.getInt("month"));
					bean.setYear(rs.getInt("year"));
					bean.setServiceId(rs.getInt("serviceid"));
					bean.setMonthlyFee(rs.getInt("monthlyfee"));
					bean.setTax(rs.getFloat("tax"));
					bean.setCess(rs.getFloat("cess"));
					bean.setGross(rs.getFloat("gross"));
					
					//bean.setDueAmount(rs.getInt("dueamount"));
					//bean.setDuedate(rs.getDate("duedate"));
					
					ConsumerBean consumerBean = new ConsumerBean();
					consumerBean.setFirstName(rs.getString("firstname"));
					consumerBean.setLastName(rs.getString("lastname"));
					consumerBean.setAddress(rs.getString("address"));
					consumerBean.setPhone(rs.getString("phone"));
					consumerBean.setServiceName(rs.getString("servicename"));
					bean.setConsumerDetail(consumerBean);
					billDetailList.add(bean);
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
			return billDetailList;
		}

		public StaffLeaveBean getLeaveData(DataSource  datasource, int leaveId) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			StaffLeaveBean bean = new StaffLeaveBean();
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_LEAVE_UPDATE_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1,leaveId);
				rs = pStatement.executeQuery();
				if (rs.next()) {
					bean.setLeaveId(rs.getInt("leaveId"));
					bean.setStaffId(rs.getInt("staffId"));
					bean.setStaffName(rs.getString("staffname"));
					bean.setFacilityId(rs.getInt("facilityId"));
					bean.setLeaveType(rs.getString("leavetype"));
					bean.setLeaveFromDate(rs.getString("leavefromdate"));
					bean.setLeaveToDate(rs.getString("leavetodate"));
					bean.setNoofdays(rs.getInt("no_ofdays"));
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

		public void insertDetail(DataSource  datasource, StaffLeaveForm leaveForm) throws Exception {
			Connection con = null;
			PreparedStatement pStatement= null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.INSERT_STAFF_LEAVE_SQL;
				pStatement = con.prepareStatement(sql);
				//insert into staff_leave_request (staffId,facilityId,leavetype,leavefromdate,leavetodate,no_ofdays,eneteredby,enteredon) values (?,?,?,?,?,?,1,CURDATE());
				System.out.println("04102009-sql==>"+sql);
				System.out.println("03102009-leaveForm.getFacilityId()==>"+leaveForm.getFacilityId());
				
				pStatement.setInt(1, leaveForm.getStaffId());
				pStatement.setInt(2, leaveForm.getFacilityId());
				pStatement.setString(3, leaveForm.getLeaveType());
				pStatement.setString(4, leaveForm.getLeaveFrom());
				pStatement.setString(5, leaveForm.getLeaveTo());
				pStatement.setInt(6, leaveForm.getNoOfDays());
				
				int result = pStatement.executeUpdate();
				System.out.println("04102009-result==>"+result);
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

		public void updateLeaveRequest(DataSource  datasource, StaffLeaveForm shiftForm) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.UPDATE_LEAVEREQUEST_SQL;
				pStatement = con.prepareStatement(sql);
				//update staff_leave_request set leavefromdate=?,leavetodate=?,no_ofdays=? modifiedby=?,modifiedon=CURDATE() where leaveId=?;";
				pStatement.setString(1, handleNullForString(shiftForm.getLeaveType()));
				pStatement.setString(2, handleNullForString(shiftForm.getLeaveFrom()));
				pStatement.setString(3, handleNullForString(shiftForm.getLeaveTo()));
				pStatement.setInt(4, shiftForm.getNoOfDays());
				pStatement.setInt(5, shiftForm.getModifiedBy());
				pStatement.setInt(6, shiftForm.getLeaveId());
				int result = pStatement.executeUpdate();
				System.out.println("result For Leave Update=>"+result);
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

		public void inactiveLeaveRequest(DataSource  datasource, String leavesArr[]) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.INACTIVE_LEAVEREQUEST_SQL;
				pStatement = con.prepareStatement(sql);
				for (int i = 0; i < leavesArr.length; i++) {
					int leaveId = Integer.parseInt(leavesArr[i]);
						pStatement.setInt(1, leaveId);
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

		public void processMails(Vector mailMessageDetails) {
			int size = mailMessageDetails.size();
			for (int i = 0; i < size; i++) {
				Vector consumerDetails = (Vector) mailMessageDetails.get(i);
				int innerSize = consumerDetails.size();
				for (int j = 0; j < innerSize; j++) {
					StaffBean bean = (StaffBean) mailMessageDetails.get(i);
					String mailMsg = getMailMsg(bean);
				}
			}

		}

		public String getMailMsg(StaffBean bean) {
			String mailMsg = "Dear " + bean.getConsumerId()
					+ ", Please find the bill details for  " + bean.getBillDate()
					+ " : " + "";
			return mailMsg;
		}
		
		public static java.sql.Timestamp getCurrentJavaSqlTimestamp() {
		    java.util.Date date = new java.util.Date();
		    return new java.sql.Timestamp(date.getTime());
	}

}
