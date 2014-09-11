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
import java.util.ListIterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.mm.bean.CompoundBillDetailBean;
import com.mm.bean.ConsumerBean;
import com.mm.bean.ConsumerBillDetailBean;
import com.mm.bean.ConsumerSrvciceMappingBean;
import com.mm.bean.StaffBean;
import com.mm.core.resource.DBConstant;
import com.mm.struts.corpo.form.StaffForm;
/**
 * @
 * @author Ajay
 *
 */
public class StaffDao {

	public Vector getScheduleList(DataSource  datasource, int facilityId) throws Exception {

			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector staffList = new Vector();
			System.out.println("facilityId-03102009->"+facilityId);
			
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_ALL_SCHEDULES_SQL;
				System.out.println("facilityId-03102009-qqqq>"+sql);
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, facilityId);
				rs = pStatement.executeQuery();
				while (rs.next()) {
					int scheduleId=rs.getInt("sheduleid");
					int shift_id=rs.getInt("shift_id");
					StaffBean bean = new StaffBean();
					bean.setShiftName(rs.getString("shift_name"));
					bean.setScheuduleDate(rs.getString("schedule_date"));
					bean.setElectricianStaff(rs.getString("electrician"));
					bean.setPumpOperatorStaff(rs.getString("pumpoperator"));
					bean.setPlumberStaff(rs.getString("plumber"));
					bean.setSecurityStaff(rs.getString("security"));
					staffList.add(bean);
				}
				System.out.println("staffList-2->"+staffList);
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
			System.out.println("In Staff Dao getServiceListResident Method facilityId-->"+facilityId);
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
			System.out.println("In Staff Dao searchServices Method facilityId-->"+serviceName);
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

	
		public StaffBean getServiceData(DataSource  datasource, int serviceId) throws Exception {
			System.out.println("In Staff Dao getServiceData Method facilityId-->"+serviceId);
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			StaffBean bean = new StaffBean();
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_SERVICE_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, serviceId);
				rs = pStatement.executeQuery();
				if (rs.next()) {
					bean.setServiceName(rs.getString("serviceName"));
					bean.setServiceDesc(rs.getString("serviceDesc"));
					bean.setMonthlyFee(rs.getInt("monthlyFee"));
					bean.setTaxes(rs.getInt("taxes"));
					bean.setCess(rs.getInt("cess"));
					bean.setServiceId(rs.getInt("serviceid"));
					bean.setFacilityId(rs.getInt("facilityid"));
					bean.setIsMandetory(rs.getInt("is_mendetory"));
					bean.setType(rs.getInt("service_type"));
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

		public void insertScedule(DataSource  datasource, StaffForm staffBean) throws Exception {
			System.out.println("In Staff Dao insertScedule Method facilityId-->"+staffBean);
			Connection con = null;
			PreparedStatement pStatement= null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.INSERT_SHIFT_SCHEDULE_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, staffBean.getFacilityId());
				pStatement.setString(2, staffBean.getScheduleDate());
				pStatement.setInt(3, staffBean.getShiftId());
				pStatement.setInt(4, staffBean.getStaffId());
				pStatement.setInt(5, staffBean.getCategory());
				pStatement.setInt(6, staffBean.getSubCategory());
				pStatement.setInt(7,staffBean.getCreatedBy());
				pStatement.setInt(8,staffBean.getModifiedBy());

				int result = pStatement.executeUpdate();
				
				System.out.println("result in Staff Dao For Insert-->"+result);
				
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

public void updateService(DataSource  datasource, StaffForm staffBean) throws Exception {
			System.out.println("In Staff Dao updateService Method facilityId-->"+staffBean);
			Connection con = null;
			PreparedStatement pStatement = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.UPDATE_SERVICE_SQL;
				pStatement = con.prepareStatement(sql);

				pStatement.setString(1, handleNullForString(staffBean.getServiceName()));
				pStatement.setString(2, handleNullForString(staffBean.getServiceDesc()));
				pStatement.setInt(3, staffBean.getMonthlyFee());
				pStatement.setInt(4, staffBean.getTaxes());
				pStatement.setInt(5, staffBean.getCess());
				pStatement.setString(6, handleNullForString(staffBean.getFacilityName()));
				pStatement.setInt(7, staffBean.getFacilityId());
				pStatement.setInt(8, staffBean.getModifiedBy());
				pStatement.setInt(9, staffBean.getIsMandetory());
				pStatement.setInt(10, staffBean.getType());
				pStatement.setInt(11, staffBean.getServiceId());

				int result = pStatement.executeUpdate();
				System.out.println(result);
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

		public void inactiveService(DataSource  datasource, String serviceIdsArr[]) throws Exception {
			System.out.println("In Staff Dao inactiveService Method facilityId-->"+serviceIdsArr[0]);
			Connection con = null;
			PreparedStatement pStatement = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.INACTIVE_SERVICE_SQL;
				pStatement = con.prepareStatement(sql);
				
				for (int i = 0; i < serviceIdsArr.length; i++) {
					int serviceId = Integer.parseInt(serviceIdsArr[i]);
						pStatement.setInt(1, serviceId);
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
		
		//Added by Renuka 12102009
		public Vector getDistinctScheduleDtGivenFId(DataSource  datasource, int facilityId) throws Exception {

			Connection con = null; 
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector scheduleDtList = new Vector();
			
			System.out.println("FacilityId:::->"+facilityId);
			
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_ALL_SCHEDULEDATE_GIVEN_FID;
				System.out.println("facilityId-03102009-qqqq>"+sql);
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, facilityId);
				rs = pStatement.executeQuery();
				while (rs.next()) {
					StaffBean bean = new StaffBean();
					bean.setScheuduleDate(rs.getString("schedule_date"));
					scheduleDtList.add(bean);
				}
				System.out.println("staffList-2->"+scheduleDtList);
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
			return scheduleDtList;
		}
		
		public Vector getShiftListGivenScheduleId_FId(DataSource  datasource, int facilityId, String strScheduleDate) throws Exception {

			Connection con = null; 
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector vecShiftList = new Vector();
			
			System.out.println("FacilityId:::line 628->"+facilityId);
			System.out.println("strScheduleDate:::line 629->"+strScheduleDate);
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_ALL_SHIFTS_GIVEN_FID_n_SCHEDULEID;
				System.out.println("sql line 633::>"+sql);
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, facilityId);
				pStatement.setString(2, strScheduleDate);
				rs = pStatement.executeQuery();
				while (rs.next()) {
					StaffBean bean = new StaffBean();
					bean.setShiftId(Integer.parseInt(rs.getString("shift_id")));
					bean.setShiftName(rs.getString("shift_name"));
					vecShiftList.add(bean);
				}
				System.out.println("vecShiftList-2->"+vecShiftList); 
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
			return vecShiftList; 
		}

		public Vector getCategory(DataSource  datasource) throws Exception 
		{
			Connection con = null; 
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector vecCategoryList = new Vector();
			
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_ALL_CATEGORY;
				System.out.println("sql line 633::>"+sql);
				pStatement = con.prepareStatement(sql);
				rs = pStatement.executeQuery();
				while (rs.next()) {
					StaffBean bean = new StaffBean();
					bean.setCategoryId(Integer.parseInt(rs.getString("cat_id")));
					bean.setCategoryName(rs.getString("cat_name"));
					vecCategoryList.add(bean);
				}
				System.out.println("vecCategoryList-->"+vecCategoryList);  
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
			return vecCategoryList; 
		}
		
		
		public Vector getStaffListGivenShiftId_ScheduleDate_FId(DataSource  datasource, int facilityId, String strScheduleDate, int intShiftId, int intCategoryId) throws Exception  
		{
			Connection con = null; 
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector vecStaffList = new Vector();
			
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_ALL_SHIFTS_GIVEN_FID_n_SCHEDULEDATE_SHIFTID_CATID;
				System.out.println("sql line 717::>"+sql);
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, facilityId);
				pStatement.setString(2, strScheduleDate);
				pStatement.setInt(3, intShiftId);
				pStatement.setInt(4, intCategoryId);
				rs = pStatement.executeQuery();
				while (rs.next()) {
					ArrayList arrStaff = new ArrayList();
					arrStaff.add(0, rs.getInt(("schedule_id")));
					arrStaff.add(1, rs.getString("staff_name"));
					
					vecStaffList.add(arrStaff);
				}
				System.out.println("vecStaffList--->"+vecStaffList); 
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
			return vecStaffList; 
		}
		
		
	}
