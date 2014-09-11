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
import com.mm.bean.ShiftMasterBean;
import com.mm.bean.StaffBean;
import com.mm.core.resource.DBConstant;
import com.mm.struts.corpo.form.ShiftMasterForm;

public class ShiftMasterDao {

	public Vector getShiftList(DataSource  datasource, int facilityId) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector staffShiftList = new Vector();
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_ALL_SHIFT_SQL;
				System.out.println("sql For Shift Master List=>"+sql);
				System.out.println("sql For Shift Master List=>"+facilityId);
				//select A.shift_id,A.shift_name,A.facility_id,B.facility_name,A.shiftstarttime,A.shiftendtime from staff_shift_master A,facility B where A.facility_id=B.facility_id AND A.facility_id=1?;"
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, facilityId);
				rs = pStatement.executeQuery();
				while (rs.next()) {
					ShiftMasterBean bean = new ShiftMasterBean();
					bean.setShiftId(rs.getInt("shift_id"));
					bean.setShiftName(rs.getString("shift_name"));
					bean.setFacilityId(rs.getInt("facility_id"));
					bean.setFacilityName(rs.getString("facility_name"));
					bean.setShiftStartTime(rs.getString("shiftstarttime"));
					bean.setShiftEndTime(rs.getString("shiftendtime"));
					staffShiftList.add(bean);
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
			return staffShiftList;
		}

		public Vector getServiceListResident(DataSource  datasource, int consumerId, int facilityId) throws Exception {
			System.out.println("In Staff Dao getServiceListResident Method facilityId-->"+facilityId);
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector staffShiftList = new Vector();
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
					staffShiftList.add(bean);
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
			return staffShiftList;
		}

		public Vector searchServices(DataSource  datasource, String serviceName) throws Exception {
			System.out.println("In Staff Dao searchServices Method facilityId-->"+serviceName);
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector staffShiftList = new Vector();
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
					staffShiftList.add(bean);
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
			return staffShiftList;
		}

		public ArrayList getCompoundBillDetails(DataSource  datasource, int facilityId, Date billDate)
				throws Exception {
			System.out.println("In Staff Dao getCompoundBillDetails Method facilityId-->"+facilityId);
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
			System.out.println("In Staff Dao getBillDetails Method facilityId-->"+consumerId);
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
			System.out.println("In Staff Dao getBillDetailsResident Method facilityId-->"+consumerid);
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

		public ShiftMasterBean getShiftData(DataSource  datasource, int shiftId) throws Exception {
			System.out.println("In Shift Dao getServiceData Method facilityId-->"+shiftId);
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			ShiftMasterBean bean = new ShiftMasterBean();
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_SHIFT_UPDATE_SQL;
				System.out.println("In Shift Dao getServiceData Method facilityId-->"+shiftId);
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, shiftId);
				rs = pStatement.executeQuery();
				if (rs.next()) {
					bean.setShiftId(rs.getInt("shift_id"));
					bean.setShiftName(rs.getString("shift_name"));
					bean.setFacilityId(rs.getInt("facility_id"));
					bean.setShiftStartTime(rs.getString("shiftstarttime"));
					bean.setShiftEndTime(rs.getString("shiftendtime"));
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

		public void insertDetail(DataSource  datasource, ShiftMasterForm shiftForm) throws Exception {
			System.out.println("In Shift Dao insertDetail Method facilityId-->"+shiftForm);
			Connection con = null;
			PreparedStatement pStatement= null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.INSERT_SHIFT_MASTER_SQL;
				String startHr=shiftForm.getStartHour();
				String startMn=shiftForm.getStartMinuts();
				String startAmPm=shiftForm.getStartAmPm();
				String startTime=startHr+':'+startMn+' '+startAmPm;
				String endHr=shiftForm.getEndHour();
				String endMn=shiftForm.getEndMinuts();
				String endAmPm=shiftForm.getEndAmPm();
				String endTime=endHr+':'+endMn+' '+endAmPm;
				
				//:(shiftForm.getStartMinuts()):(shiftForm.getStartAmPm()));
				pStatement = con.prepareStatement(sql);
				//insert into staff_master(staffname,facility_id,category,subcategory,joining_date,jobtype,salary,active,address,contact_no,created_by,created_on) values(?,?,?,?,?,?,?,?,?,?,'Sanjeev',CURDATE())
				System.out.println("sql in Shift Dao For Insert New1-->"+sql);
				System.out.println("getShiftName()-->"+shiftForm.getShiftName());
				System.out.println("getFacilityId-->"+shiftForm.getFacilityId());
				System.out.println("getShiftStartTime-->"+startTime);
				System.out.println("getShiftEndTime-->"+endTime);
				//pStatement.setString(1, handleNullForString(serviceBean.getServiceName()));
				
				pStatement.setString(1, handleNullForString(shiftForm.getShiftName()));
				pStatement.setInt(2, shiftForm.getFacilityId());
				pStatement.setString(3, handleNullForString(startTime));
				pStatement.setString(4, (endTime));
				
				int result = pStatement.executeUpdate();
				System.out.println("result in Shift Dao For Insert-->"+result);
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

		public void updateShift(DataSource  datasource, ShiftMasterForm shiftForm) throws Exception {
			System.out.println("In Shift Dao updateShift Method facilityId-->"+shiftForm);
			Connection con = null;
			PreparedStatement pStatement = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.UPDATE_SHIFT_SQL;
				pStatement = con.prepareStatement(sql);

				String startHr=shiftForm.getStartHour();
				String startMn=shiftForm.getStartMinuts();
				String startAmPm=shiftForm.getStartAmPm();
				String startTime=startHr+':'+startMn+' '+startAmPm;
				String endHr=shiftForm.getEndHour();
				String endMn=shiftForm.getEndMinuts();
				String endAmPm=shiftForm.getEndAmPm();
				String endTime=endHr+':'+endMn+' '+endAmPm;

				System.out.println("sql For Shift Update=>"+sql);
				System.out.println("sql For Shift Update=>"+shiftForm.getShiftName());
				System.out.println("sql For Shift Update=>"+startTime);
				System.out.println("sql For Shift getModifiedBy=>"+endTime);
				System.out.println("sql For Shift getModifiedBy=>"+shiftForm.getModifiedBy());
				System.out.println("sql For Shift getShiftId=>"+shiftForm.getShiftId());
				//update staff_shift_master set shift_name='Night Shift',shiftstarttime='18:00:00',shiftendtime='02:00:00',modifiedby='Ajay',modifiedon=CURDATE() where shift_id=2
				pStatement.setString(1, handleNullForString(shiftForm.getShiftName()));
				pStatement.setString(2, handleNullForString(startTime));
				pStatement.setString(3, handleNullForString(endTime));
				pStatement.setInt(4, shiftForm.getModifiedBy());
				pStatement.setInt(5, shiftForm.getShiftId());
				int result = pStatement.executeUpdate();
				System.out.println("result For Shift Update=>"+result);
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
			System.out.println("In Staff Dao deleteService Method facilityId-->"+serviceId);
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

		public void inactiveShift(DataSource  datasource, String shiftIdsArr[]) throws Exception {
			System.out.println("In Staff Dao inactiveShift Method facilityId-->"+shiftIdsArr[0]);
			Connection con = null;
			PreparedStatement pStatement = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.INACTIVE_SHIFT_SQL;
				pStatement = con.prepareStatement(sql);
				
				for (int i = 0; i < shiftIdsArr.length; i++) {
					int shiftId = Integer.parseInt(shiftIdsArr[i]);
						pStatement.setInt(1, shiftId);
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
