package com.addteq.struts.DAO;

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
import com.mm.bean.ExpenseDetailBean;
import com.mm.bean.StaffBean;
import com.mm.core.resource.DBConstant;
import com.mm.struts.corpo.form.ExpenseDetailForm;
import com.mm.struts.corpo.form.StaffMasterForm;

public class ExpenseDetailDao {

	public Vector getExpenseList(DataSource  datasource, int facilityId) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector expenseList = new Vector();
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_ALL_EXPENSE_SQL;
				//select expenseid,invoice_name,payable_to,payment_type,amount,expense_type,authorised_by,expense_date,expense_summary,status from expensedetail;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, facilityId);
				rs = pStatement.executeQuery();
				while (rs.next()) {
					ExpenseDetailBean bean = new ExpenseDetailBean();
					
					bean.setExpenseId(rs.getInt("expenseid"));
					bean.setInvoiceName(rs.getString("invoice_name"));
					bean.setPayableTo(rs.getString("payable_to"));
					bean.setPaymentType(rs.getString("payment_type"));
					bean.setAmount(rs.getFloat("amount"));
					bean.setExpenseType(rs.getString("expense_type"));
					bean.setAuthorisedBy(rs.getString("authorised_by"));
					bean.setExpenseDate(rs.getString("expense_date"));
					bean.setExpenseSummary(rs.getString("expense_summary"));
					bean.setPayStatus(rs.getString("status"));
					
					expenseList.add(bean);
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
			return expenseList;
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

		public ExpenseDetailBean getExpenseData(DataSource  datasource, int expenseId) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			ExpenseDetailBean bean = new ExpenseDetailBean();
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_EXPENSE_UPDATE_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, expenseId);
				rs = pStatement.executeQuery();
				if (rs.next()) {
					//bean.setStaffId(rs.getInt("staff_id"));
					
					bean.setExpenseId(rs.getInt("expenseid"));
					bean.setInvoiceName(rs.getString("invoice_name"));
					bean.setPayableTo(rs.getString("payable_to"));
					bean.setPaymentType(rs.getString("payment_type"));
					bean.setAmount(rs.getFloat("amount"));
					bean.setExpenseType(rs.getString("expense_type"));
					bean.setAuthorisedBy(rs.getString("authorised_by"));
					bean.setExpenseDate(rs.getString("expense_date"));
					bean.setExpenseSummary(rs.getString("expense_summary"));
					bean.setPayStatus(rs.getString("status"));

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

		public void insertDetail(DataSource  datasource, ExpenseDetailForm expenseForm) throws Exception {
			Connection con = null;
			PreparedStatement pStatement= null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.INSERT_EXPENSE_DETAIL_SQL;
				pStatement = con.prepareStatement(sql);
				//insert into expensedetail (invoice_name,payable_to,payment_type,amount,expense_type,authorised_by,expense_date,expense_summary,status,entered_by,entered_on) values(?,?,?,?,?,?,?,?,?,?,CURDATE())
				
				pStatement.setInt(1, expenseForm.getFacilityId());
				pStatement.setString(2, expenseForm.getInvoicName());
				pStatement.setString(3, expenseForm.getPayableTo());
				pStatement.setString(4, expenseForm.getPaymentType());
				pStatement.setFloat(5, expenseForm.getPayAmount());
				pStatement.setString(6, expenseForm.getExpenseType());
				pStatement.setString(7, expenseForm.getAuthorisedBy());
				pStatement.setString(8, expenseForm.getExpenseDate());
				pStatement.setString(9, expenseForm.getExpenseSummary());
				pStatement.setString(10, expenseForm.getPayStatus());
				pStatement.setInt(11,0);
				
				
				int result = pStatement.executeUpdate();
				
				
				// Default Assign the new Service to all the members of this Facility.
				//int facilityId = staffForm.getFacilityId();
				
				// Step 0: Retrieve the newly added service assuming that there can be only one service with a given name within a Facility.
				//sql = "Select s.serviceid from service s where s.facilityid=? and s.servicename=?";
				//pStatement = con.prepareStatement(sql);
				//pStatement.setInt(1, facilityId);
				//pStatement.setString(2, staffForm.getServiceName());
				
				//rs = pStatement.executeQuery();
				//int serviceId = 0;
				
				//if (rs.next()) {
				//	serviceId = rs.getInt("serviceid");
				//}
				
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

		public void updateExpenses(DataSource  datasource, ExpenseDetailForm expForm) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.UPDATE_EXPENSES_SQL;
				pStatement = con.prepareStatement(sql);
				//select expenseid,invoice_name,payable_to,payment_type,amount,expense_type,authorised_by,expense_date,expense_summary,status from expensedetail	
				pStatement.setString(1, handleNullForString(expForm.getInvoicName()));
				pStatement.setString(2, handleNullForString(expForm.getPayableTo()));
				pStatement.setString(3, expForm.getPaymentType());
				pStatement.setFloat(4, expForm.getPayAmount());
				pStatement.setString(5, expForm.getExpenseType());
				pStatement.setString(6,handleNullForString(expForm.getAuthorisedBy()));
				pStatement.setString(7, expForm.getExpenseDate());
				pStatement.setString(8, expForm.getExpenseSummary());
				pStatement.setString(9, expForm.getPayStatus());
				pStatement.setInt(10, expForm.getExpenseId());
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

		public void inactiveExpenses(DataSource  datasource, String expIdsArr[]) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.INACTIVE_EXPENSE_SQL;
				pStatement = con.prepareStatement(sql);
				
				for (int i = 0; i < expIdsArr.length; i++) {
					int staffId = Integer.parseInt(expIdsArr[i]);
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

		public void insertConsumerServicemapping(DataSource  datasource, 
				ConsumerSrvciceMappingBean staffBean) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.INSERT_CONSUMER_SERVICE_MAPPING_SQL;
				pStatement = con.prepareStatement(sql);

				pStatement.setInt(1, staffBean.getConsumerId());
				pStatement.setInt(2, staffBean.getServiceId());
				pStatement.setDate(3, staffBean.getDateFrom());
				pStatement.setDate(4, staffBean.getDateTo());
				pStatement.setInt(5, staffBean.getCreatedBy());
				pStatement.setTimestamp(6, staffBean.getCreatedOn());
				pStatement.setInt(7, staffBean.getModifiedBy());
				pStatement.setTimestamp(8, staffBean.getModifiedOn());

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
				ConsumerSrvciceMappingBean staffBean, Connection con,
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
					pStatement.setDate(4, staffBean.getDateTo());
					pStatement.setInt(5, staffBean.getCreatedBy());
					pStatement.setTimestamp(6, staffBean.getCreatedOn());
					pStatement.setInt(7, staffBean.getModifiedBy());
					pStatement.addBatch();
				}
				int result[] = pStatement.executeBatch();
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
				ConsumerSrvciceMappingBean staffBean) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.UPDATE_CONSUMER_SERVICE_MAPPING_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setDate(1, staffBean.getDateTo());
				pStatement.setInt(2, staffBean.getModifiedBy());
				pStatement.setTimestamp(3, staffBean.getModifiedOn());
				pStatement.setInt(4, staffBean.getConsumerId());
				pStatement.setInt(5, staffBean.getServiceId());
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

		public void insertConsumerBillDetail(Vector dataList, Connection con)
				throws Exception {
			PreparedStatement pStatement = null;
			try {
				String sql = DBConstant.INSERT_CONSUMER_BILL_DETAIL_SQL;
				pStatement = con.prepareStatement(sql);

				for (int i = 0; i < dataList.size(); i++) {
					StaffBean staffBean = (StaffBean) dataList.get(i);
					pStatement.setInt(1, staffBean.getConsumerId());
					pStatement.setInt(2, staffBean.getServiceId());
					pStatement.setInt(3, staffBean.getBillMonth());
					pStatement.setInt(4, staffBean.getBillYear());
					pStatement.setDate(5, staffBean.getDueDate());
					pStatement.setInt(6, staffBean.getDueAmount());
					pStatement.setInt(7, staffBean.getCreatedBy());
					pStatement.setTimestamp(8, staffBean.getCreatedOn());
					pStatement.setInt(9, staffBean.getModifiedBy());
					pStatement.setTimestamp(10, staffBean.getModifiedOn());
					pStatement.setString(11, staffBean.getUniqueId());

					pStatement.addBatch();
				}
				int result[] = pStatement.executeBatch();
				System.out.println(result.length);
				System.out.println(result);
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

		public void insertCompoundBillDetail(DataSource  datasource, CompoundBillDetailBean bean)
				throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			try {
				con = datasource.getConnection();
				con.setAutoCommit(false);
				insertConsumerBillDetail(bean.getServiceBeanList(), con);
				String sql = DBConstant.INSERT_CONSUMER_COMPOUND_BILL_DETAIL_SQL;
				pStatement = con.prepareStatement(sql);

				pStatement.setInt(1, bean.getConsumerId());
				pStatement.setInt(2, bean.getMonth());
				pStatement.setInt(3, bean.getYear());
				pStatement.setInt(4, bean.getTotalAmount());
				pStatement.setInt(5, bean.getPaidAmount());
				pStatement.setString(6, handleNullForString(bean.getPaymentMode()));
				pStatement.setInt(7, bean.getCreatedBy());
				pStatement.setTimestamp(8, bean.getCreatedOn());
				pStatement.setInt(9, bean.getModifiedBy());
				pStatement.setTimestamp(10, bean.getModifiedOn());
				pStatement.setString(11, bean.getUniqueId());

				int result = pStatement.executeUpdate();
				System.out.println(result);
			} catch (Exception exception) {
				try {
					con.rollback();
				} catch (SQLException exception2) {
				}
				exception.printStackTrace();
				throw exception;
			} finally {
				try {
					if (pStatement != null) {
						pStatement.close();
						pStatement = null;
					}
				} catch (SQLException exception2) {
				}
			}
			con.commit();
		}

		public void updatePaymentDetails(DataSource  datasource, CompoundBillDetailBean bean)
				throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			try {
				con = datasource.getConnection();
				con.setAutoCommit(false);
				insertConsumerBillDetail(bean.getServiceBeanList(), con);
				String sql = DBConstant.UPDATE_CONSUMER_BILL_DETAIL_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, bean.getPaidAmount());
				pStatement.setString(2, handleNullForString(bean.getPaymentMode()));
				pStatement.setInt(3, bean.getConsumerId());
				pStatement.setInt(4, bean.getMonth());
				pStatement.setInt(5, bean.getYear());
				int result = pStatement.executeUpdate();
				System.out.println(result);
			} catch (Exception exception) {
				try {
					con.rollback();
				} catch (SQLException exception2) {
				}
				exception.printStackTrace();
				throw exception;
			} finally {
				try {
					if (pStatement != null) {
						pStatement.close();
						pStatement = null;
					}
				} catch (SQLException exception2) {
				}
			}
			con.commit();
		}

		public void getAllConsumerBill(DataSource  datasource, Date billDate, Date dueDate)
				throws Exception {
			Date todayDate = new Date(System.currentTimeMillis());
			String dateStrArr[] = getDateArr(billDate);
			int month = Integer.parseInt(dateStrArr[1]);
			int year = Integer.parseInt(dateStrArr[0]);
			int outerConsumerId = 0;
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector billDetails = new Vector();
			Vector mailMessageDetails = new Vector();
			try {
				con = datasource.getConnection();
				String sql = DBConstant.GENERATE_CONSUMER_BILL_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, month);
				pStatement.setInt(2, year);

				rs = pStatement.executeQuery();
				while (rs.next()) {
					int innerConsumerId = rs.getInt("consumerid");
					if (outerConsumerId == 0 || innerConsumerId == outerConsumerId) {
						outerConsumerId = innerConsumerId;
					} else {
						try {
							processBillDetailForConsumer(datasource, billDetails, todayDate,
									dueDate);
							mailMessageDetails.add(billDetails);
						} catch (Exception exception) {
							exception.printStackTrace();
						}
						billDetails = new Vector();
						outerConsumerId = innerConsumerId;
					}
					Date dateTo = rs.getDate("dateto");
					Date dateFrom = rs.getDate("datefrom");

					StaffBean bean = new StaffBean();
					bean.setServiceId(rs.getInt("serviceid"));
					bean.setServiceName(rs.getString("servicename"));
					bean.setServiceDesc(rs.getString("servicedesc"));
					bean.setMonthlyFee(rs.getInt("monthlyfee"));
					bean.setTaxes(rs.getInt("taxes"));
					bean.setCess(rs.getInt("cess"));
					bean.setBillDate(billDate);
					bean.setDateFrom(dateFrom);
					bean.setDateTo(dateTo);
					bean.setConsumerId(innerConsumerId);
					bean.setBillYear(year);
					bean.setBillMonth(month);
					billDetails.add(bean);
				}
				try {
					processBillDetailForConsumer(datasource, billDetails, todayDate, dueDate);
					mailMessageDetails.add(billDetails);
				} catch (Exception exception) {
					exception.printStackTrace();
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
		}

		public void processBillDetailForConsumer(DataSource dataSource, Vector detailVec, Date todayDate,
				Date dueDate) throws Exception {
			CompoundBillDetailBean compoundBillDetailBean = new CompoundBillDetailBean();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			int size = detailVec.size();
			int totalAmount = 0;
			int consumerId = 0;
			int year = 0;
			int month = 0;
			UID uniqueId = new UID();
			for (int i = 0; i < size; i++) {
				StaffBean bean = (StaffBean) detailVec.get(i);
				Date dateTo = bean.getDateTo();
				Date dateFrom = bean.getDateFrom();
				Date billDate = bean.getBillDate();
				year = bean.getBillYear();
				month = bean.getBillMonth();
				int tempAmount = 0;

				if (dateTo == null) {
					if (isCurrentMonthAndCurrentYear(dateFrom, billDate)) {
						tempAmount = (int) calculateServiceAmout(
								getDaysOfServiceUsedForSErviceStartMonth(dateFrom),
								month, year, bean.getMonthlyFee());
					} else {
						tempAmount = (int) calculateServiceAmout(
								getTotalDaysInMonth(month), month, year, bean
										.getMonthlyFee());
					}
				} else {
					if (isCurrentMonthAndCurrentYear(dateFrom, billDate)
							&& isCurrentMonthAndCurrentYear(dateTo, billDate)) {

						tempAmount = (int) calculateServiceAmout(
								getDaysOfServiceUsed(dateFrom, dateTo), month,
								year, bean.getMonthlyFee());

					} else if (!isCurrentMonthAndCurrentYear(dateFrom, billDate)
							&& isCurrentMonthAndCurrentYear(dateTo, billDate)) {

						int day = Integer.parseInt(getDateArr(dateTo)[2]);
						tempAmount = (int) calculateServiceAmout(day, month, year,
								bean.getMonthlyFee());
					}
				}

				consumerId = bean.getConsumerId();
				int monthlyFee = bean.getMonthlyFee();
				int taxes = bean.getTaxes();
				int cess = bean.getCess();

				totalAmount = totalAmount + tempAmount;// + monthlyFee + taxes +//
				// cess;
				bean.setDueAmount(tempAmount);
				bean.setCreatedOn(timestamp);
				bean.setModifiedOn(timestamp);
				bean.setUniqueId(uniqueId.toString());
				bean.setDueDate(dueDate);
			}
			compoundBillDetailBean.setTotalAmount(totalAmount);
			compoundBillDetailBean.setPaidAmount(0);
			compoundBillDetailBean.setConsumerId(consumerId);
			compoundBillDetailBean.setYear(year);
			compoundBillDetailBean.setMonth(month);
			compoundBillDetailBean.setCreatedOn(timestamp);
			compoundBillDetailBean.setModifiedOn(timestamp);
			compoundBillDetailBean.setUniqueId(uniqueId.toString());
			compoundBillDetailBean.setServiceBeanList(detailVec);
			insertCompoundBillDetail(dataSource,compoundBillDetailBean);

		}

		/*
		 * public Vector getConsumerBill(int consumerId, Date billDate) { int month
		 * = billDate.getMonth(); int year = billDate.getYear(); Connection con =
		 * null; PreparedStatement pStatement = null; ResultSet rs = null; Vector
		 * resultVector = new Vector(); try { String sql = "select * from (" +
		 * "select s.*,m.consumerid from service s, consumerservicemapping m where m.consumerid=? "
		 * + "and m.serviceid=s.serviceid and m.datefrom is null" + "union " +
		 * "select s.*,m.consumerid from service s, consumerservicemapping m where m.consumerid=? "
		 * + "and m.serviceid=s.serviceid and " +
		 * "DAYOFMONTH(m.dateto)=? and  YEAR(m.dateto)=?" +
		 * ") c order by consumerid";
		 * 
		 * pStatement = con.prepareStatement(sql); pStatement.setInt(1, consumerId);
		 * pStatement.setInt(2, consumerId); pStatement.setInt(3, month);
		 * pStatement.setInt(4, year);
		 * 
		 * rs = pStatement.executeQuery(); while (rs.next()) { StaffBean bean =
		 * new StaffBean(); bean.setServiceId(rs.getInt("serviceid"));
		 * bean.setServiceName(rs.getString("servicename"));
		 * bean.setServiceDesc(rs.getString("servicedesc"));
		 * bean.setMonthlyFee(rs.getInt("monthlyfee"));
		 * bean.setTaxes(rs.getInt("taxes")); bean.setCess(rs.getInt("cess"));
		 * bean.setConsumerId(rs.getInt("consumerid")); resultVector.add(bean); } }
		 * catch (Exception exception) { exception.printStackTrace(); } finally {
		 * try { if (rs != null) { rs.close(); rs = null; } if (pStatement != null)
		 * { pStatement.close(); pStatement = null; } if (con != null) {
		 * con.close(); con = null; } } catch (SQLException exception2) {
		 * 
		 * } return resultVector; } }
		 */
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
