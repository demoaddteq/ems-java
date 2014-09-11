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
import com.mm.bean.StaffPayrollBean;
import com.mm.core.resource.DBConstant;
import com.mm.struts.corpo.form.StaffMasterForm;
import com.mm.struts.corpo.form.StaffPayrollForm;

public class StaffPayrollDao {

	public Vector getStaffForPayroll(DataSource  datasource, int facilityId) throws Exception {
		System.out.println("In Staff PayRoll Dao getStaffForPayroll 16092009 Method facilityId-->"+facilityId);
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
					bean.setActive(rs.getString("active"));
					bean.setAdrresss(rs.getString("address"));
					bean.setContactNo(rs.getString("contact_no"));
					bean.setSalaryStauts(rs.getString("salary_status"));
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
			/*try {
				con = datasource.getConnection();
				//String sql = DBConstant.SELECT_RESIDENT_SERVICE_SQL;
				String sql = DBConstant.SEARCH_SERVICE_ASSIGNED;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, facilityId);
				pStatement.setInt(2, consumerId);

				rs = pStatement.executeQuery();
				while (rs.next()) {
					StaffPayrollBean bean = new StaffPayrollBean();
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
			}*/
			return staffList;
			
		}

		public Vector searchServices(DataSource  datasource, String serviceName) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector staffList = new Vector();
		/*	try {
				con = datasource.getConnection();
				String sql = "select * from service where active='y' and servicename like '%"
						+ serviceName + "%'";
				pStatement = con.prepareStatement(sql);

				rs = pStatement.executeQuery();
				while (rs.next()) {
					StaffPayrollBean bean = new StaffPayrollBean();
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
			}*/
			return staffList;
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

		public StaffPayrollBean getPayrollData(DataSource  datasource, int staffId) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			StaffPayrollBean bean = new StaffPayrollBean();
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_PAYROLL_SQL;
				System.out.println("sqliiiiiiiiiiii=>"+sql);
				System.out.println("staffId=>"+staffId);
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, staffId);
				rs = pStatement.executeQuery();
				//System.out.println("rs.getString=>"+rs.getString("staffname"));
				if (rs.next()) {
					//StaffMasterBean bean = new StaffMasterBean();
					System.out.println("rs.getString=>"+rs.getString("staffname"));
					bean.setStaffId(rs.getInt("staff_id"));
					bean.setStaffName(rs.getString("staffname"));
					bean.setFacilityId(rs.getInt("facility_id"));
					bean.setSalary(rs.getFloat("salary"));
					bean.setCategory(rs.getInt("category"));
					bean.setSubCategory(rs.getInt("subcategory"));
					bean.setJoiningDate(rs.getString("joining_date"));
					bean.setActive(rs.getString("active"));
					bean.setAdrresss(rs.getString("address"));
					bean.setContactNo(rs.getString("contact_no"));
					bean.setNoOfLeave(rs.getInt("noofleave"));
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
				pStatement.setInt(2, staffForm.getFacility());
				pStatement.setInt(3, staffForm.getStaffCategory());
				pStatement.setInt(4, staffForm.getStaffSubCategory());
				pStatement.setString(5, staffForm.getJoiningDate());
				pStatement.setString(6, "P");
				pStatement.setString(7, staffForm.getSalary());
				pStatement.setString(8, "Y");
				pStatement.setString(9, staffForm.getAddress());
				pStatement.setString(10, staffForm.getContactNo());
				
				
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

		public void updateStaffPayroll(DataSource  datasource, StaffPayrollForm payrollForm) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.UPDATE_STAFF_PAYROLL_SQL;
				
				//insert into staff_payroll (staffid,year,month,leaves,salary_deducted,salary_paid,paymentmode,dateOfPayment) values(?,?,?,?,?,?,?,?)
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, payrollForm.getStaffId());
				pStatement.setInt(2, payrollForm.getPaymentYear());
				pStatement.setString(3, payrollForm.getPaymentMonth());
				pStatement.setInt(4, payrollForm.getNoOfLeaves());
				pStatement.setFloat(5, payrollForm.getSalaryDeduction());
				pStatement.setFloat(6, payrollForm.getPaidAmount());
				pStatement.setString(7, payrollForm.getPaymentMode());
				pStatement.setString(8, payrollForm.getPaymentDate());
				pStatement.setString(9, "Paid");
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

		public void insertConsumerServicemapping(DataSource  datasource, 
				ConsumerSrvciceMappingBean staffPayrollBean) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.INSERT_CONSUMER_SERVICE_MAPPING_SQL;
				pStatement = con.prepareStatement(sql);

				pStatement.setInt(1, staffPayrollBean.getConsumerId());
				pStatement.setInt(2, staffPayrollBean.getServiceId());
				pStatement.setDate(3, staffPayrollBean.getDateFrom());
				pStatement.setDate(4, staffPayrollBean.getDateTo());
				pStatement.setInt(5, staffPayrollBean.getCreatedBy());
				pStatement.setTimestamp(6, staffPayrollBean.getCreatedOn());
				pStatement.setInt(7, staffPayrollBean.getModifiedBy());
				pStatement.setTimestamp(8, staffPayrollBean.getModifiedOn());

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

		public void insertConsumerServicemapping(DataSource  datasource, 
				ConsumerSrvciceMappingBean staffPayrollBean, Connection con,
				int consumerId, String assignedServicesArr[]) throws Exception {
			PreparedStatement pStatement = null;
			try {
				String sql = DBConstant.INSERT_CONSUMER_SERVICE_MAPPING_SQL;
				pStatement = con.prepareStatement(sql);
				Date date = new Date(System.currentTimeMillis());
				for (int i = 0; i < assignedServicesArr.length; i++) {
					String serviceIdStr = assignedServicesArr[i];
					int serviceIdInt = Integer.parseInt(serviceIdStr);

					pStatement.setInt(1, consumerId);
					pStatement.setInt(2, serviceIdInt);
					pStatement.setDate(3, date);
					pStatement.setDate(4, staffPayrollBean.getDateTo());
					pStatement.setInt(5, staffPayrollBean.getCreatedBy());
					pStatement.setTimestamp(6, staffPayrollBean.getCreatedOn());
					pStatement.setInt(7, staffPayrollBean.getModifiedBy());
					pStatement.addBatch();
				}
				int result[] = pStatement.executeBatch();
				System.out.println("Services inserted to consumer : "+ result.length);
			} finally {
				try {
					if (pStatement != null) {
						pStatement.close();
						pStatement = null;
					}
				} catch (SQLException exception2) {

				}
			}

		}

		public void deleteConsumerServicemapping(DataSource  datasource, int consumerId, Connection con)
				throws Exception {
			PreparedStatement pStatement = null;
			try {
				String sql = DBConstant.DELETE_CONSUMER_SERVICE_MAPPING_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, consumerId);
				int result = pStatement.executeUpdate();
			} finally {
				try {
					if (pStatement != null) {
						pStatement.close();
						pStatement = null;
					}
				} catch (SQLException exception2) {
				}
			}
		}

		public void updateConsumerServicemapping(DataSource  datasource, 
				ConsumerSrvciceMappingBean staffPayrollBean) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.UPDATE_CONSUMER_SERVICE_MAPPING_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setDate(1, staffPayrollBean.getDateTo());
				pStatement.setInt(2, staffPayrollBean.getModifiedBy());
				pStatement.setTimestamp(3, staffPayrollBean.getModifiedOn());
				pStatement.setInt(4, staffPayrollBean.getConsumerId());
				pStatement.setInt(5, staffPayrollBean.getServiceId());
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

		/*public static void main(String args[]) {

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			timestamp.setDate(timestamp.getDate() - 2);
			Date billDate = new Date(timestamp.getTime());
			ServiceDao service = new ServiceDao();
			try {
				service.getAllConsumerBill(billDate, billDate);
			} catch (Exception exception) {

			}
		}
*/
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
					StaffPayrollBean bean = (StaffPayrollBean) mailMessageDetails.get(i);
					String mailMsg = getMailMsg(bean);
				}
			}

		}

		public String getMailMsg(StaffPayrollBean bean) {
			/*String mailMsg = "Dear " + bean.getConsumerId()
					+ ", Please find the bill details for  " + bean.getBillDate()
					+ " : " + "";*/
			return "MailMsg";
		}
		
		public static java.sql.Timestamp getCurrentJavaSqlTimestamp() {
		    java.util.Date date = new java.util.Date();
		    return new java.sql.Timestamp(date.getTime());
		  }

		
		public void generateConsumerBills(DataSource  datasource, int facilityId, int month, int year, Date dueDate) throws Exception {
			
			if (facilityId == 0 || month==0 || year==0){
				throw new Exception("Invalid Data Provided");
			}
			String sqlFetch = "select s.serviceid, c.consumerid, s.monthlyfee, s.taxes,	(s.monthlyfee *  s.taxes/100) as taxamt, "
				+" s.cess, (((s.monthlyfee *  s.taxes/100)) * s.cess/100) as cessamt, s.monthlyfee + (s.monthlyfee *  s.taxes/100) + (((s.monthlyfee *  s.taxes/100)) * s.cess/100) as gross "
				+ " from service s, consumer c, consumerservicemapping csm "
				+ " where s.serviceid = csm.serviceid and csm.consumerid = c.consumerid and csm.status = 2 and s.active = 'y' "
				+ " and s.facilityid =? and ? between csm.datefrom and csm.dateto "
				+ " order by c.consumerid";
			
			String sqlInsertBillDetails = "insert into billsdetails(month, year, consumerid,serviceid,facilityid,monthlyfee,taxpercent,tax,cessPercent, cess, gross) " 
				+ "values (?,?,?,?,?,?,?,?,?,?,?)";
			
			String sqlInsertConsumerBills = "insert into consumerbills(month, year, consumerid,payableamt,facilityid,duedate) " 
				+ "values (?,?,?,?,?,?)";
			
			String sqlUpdateConsumerBills = "update consumerbills set payableamt=payableamt + ? " 
					+ " where month=? and year=? and facilityid=? and consumerid=? ";
			
			
			String sqlSelectConsumerBills = "Select count(*) from consumerbills" 
											+ "  where month=? "
											+ "  and year=? "
											+ "  and facilityid=?";
			
			Connection con = null;
			PreparedStatement pStatement = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ResultSet rsInsert = null;
			
			try {
				con = datasource.getConnection();
				String date = String.valueOf(year) + "-" + String.valueOf(month) + "-01";
				
				// Check if for this facility, the bills have been generated already or not.
				pStatement = con.prepareStatement(sqlSelectConsumerBills);
				pStatement.setInt(1, month);
				pStatement.setInt(2,year);
				pStatement.setInt(3, facilityId);
				
				rs = pStatement.executeQuery();
				boolean isGenerate = false;
				
				if (rs.next()) {
					int count = rs.getInt(1);
					if (count > 0) {
						isGenerate = true;
					}
				}
				
				
				if (!isGenerate) {
				
					pStatement = con.prepareStatement(sqlFetch);
					pStatement.setInt(1, facilityId);
					pStatement.setString(2,date);   //2009-8-24
		
					rs = pStatement.executeQuery();
					int consumerOld = 0;
					
					Calendar cal = Calendar.getInstance();
				    java.sql.Date jsqlD = new java.sql.Date( cal.getTime().getTime() );
				    
				    
				    
				    
				    
					while (rs.next()) {
						
						int consumerId = rs.getInt("consumerid");
						int serviceId = rs.getInt("serviceid");
						int monthlFee = rs.getInt("monthlyfee");
						int taxes =  rs.getInt("taxes");
						float taxAmt = rs.getFloat("taxamt");
						int cess =  rs.getInt("cess");
						float cessAmt = rs.getFloat("cessamt");
						float gross = rs.getFloat("gross");
						
						// check if the bills for this consumer are already generated?
						
						
						
						
		
					
						//  insert each entry into billsdetails table for a given consumer and service for the month selected
						pstmt = con.prepareStatement(sqlInsertBillDetails);
						pstmt.setInt(1, month);
						pstmt.setInt(2, year);
						pstmt.setInt(3, consumerId);
						pstmt.setInt(4, serviceId);
						pstmt.setInt(5, facilityId);
						pstmt.setInt(6, monthlFee);
						pstmt.setInt(7, taxes);
						pstmt.setFloat(8, taxAmt);
						pstmt.setInt(9, cess);
						pstmt.setFloat(10, cessAmt);
						pstmt.setFloat(11, gross);
		
						pstmt.executeUpdate();
						
						if ( consumerId != consumerOld) {
							pstmt = con.prepareStatement(sqlInsertConsumerBills);
							pstmt.setInt(1, month);
							pstmt.setInt(2, year);
							pstmt.setInt(3, consumerId);
							pstmt.setFloat(4, gross);
							pstmt.setInt(5, facilityId);
							pstmt.setDate(6, dueDate);
		
							pstmt.executeUpdate();
							
						}
						else {
							pstmt = con.prepareStatement(sqlUpdateConsumerBills);
							pstmt.setFloat(1, gross);
							pstmt.setInt(2, month);
							pstmt.setInt(3, year);
							pstmt.setInt(4, facilityId);
							pstmt.setInt(5, consumerId);
							
							pstmt.executeUpdate();
							
						}
						
		
						consumerOld = consumerId;	
					
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				
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
			
		}
		
	}
