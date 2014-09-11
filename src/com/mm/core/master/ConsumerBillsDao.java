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
import com.mm.bean.ConsumerBillDetailBean;
import com.mm.bean.StaffBean;
import com.mm.core.resource.DBConstant;
import com.mm.struts.corpo.form.ConsumerBillsForm;

public class ConsumerBillsDao {

	public Vector getConsumerBillList(DataSource  datasource, int facilityId) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			Vector conBillList = new Vector();
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_CONSUMER_BILL_DETAIL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, facilityId);
				rs = pStatement.executeQuery();
				while (rs.next()) {
					ConsumerBillDetailBean bean = new ConsumerBillDetailBean();
					bean.setBillId(rs.getInt("billid"));
					bean.setFacilityName(rs.getString("facility_name"));
					bean.setResidentId(rs.getInt("residentid"));
					bean.setServiceName(rs.getString("servicename"));
					bean.setPrevMeterReading(rs.getFloat("previousmeterreading"));
					bean.setCurrentMeterReading(rs.getFloat("currentmeterreading"));
					bean.setResidentName(rs.getString("residentname"));
					bean.setBillStartDate(rs.getString("billstartdate"));
					bean.setBillEndDate(rs.getString("billenddate"));
					conBillList.add(bean);
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
			return conBillList;
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

		public ConsumerBillDetailBean getStaffData(DataSource  datasource, int staffId) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			ConsumerBillDetailBean bean = new ConsumerBillDetailBean();
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_STAFF_UPDATE_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, staffId);
				
				//select A.staff_id,A.staffname,A.facility_id,B.facility_name,A.category,C.cat_name,A.subcategory,D.subcat_name,
				//A.joining_date,A.jobtype,A.salary,A.address,A.contact_no from staff_master A ,facility B,staff_category C,staff_subcategory D Where  A.facility_id=B.facility_id AND A.category=C.cat_id AND A.subcategory=D.subcat_id
				
				rs = pStatement.executeQuery();
				if (rs.next()) {
					/*
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
					*/
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

public ConsumerBillDetailBean getResidentsForBillGeneration(DataSource  datasource, int residentId) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			ResultSet rs = null;
			ConsumerBillDetailBean bean = new ConsumerBillDetailBean();
			try {
				con = datasource.getConnection();
				String sql = DBConstant.SELECT_RESIDENT_BILLGENERATE_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, residentId);
				
				rs = pStatement.executeQuery();
				if (rs.next()) {
					bean.setBillId(rs.getInt("billid"));
					bean.setFacilityName(rs.getString("facility_name"));
					bean.setResidentId(rs.getInt("residentid"));
					bean.setServiceName(rs.getString("servicename"));
					bean.setPrevMeterReading(rs.getFloat("previousmeterreading"));
					bean.setCurrentMeterReading(rs.getFloat("currentmeterreading"));
					bean.setResidentName(rs.getString("residentname"));
					bean.setBillStartDate(rs.getString("billstartdate"));
					bean.setBillEndDate(rs.getString("billenddate"));
					bean.setMonthlyFee(rs.getInt("monthlyfee"));
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

		
		public void insertDetail(DataSource  datasource, ConsumerBillsForm resBillForm) throws Exception {
			Connection con = null;
			PreparedStatement pStatement= null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.INSERT_RESIDENT_BILL_DETAIL_SQL;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, resBillForm.getFacilityId());
				pStatement.setInt(2, resBillForm.getServiceId());
				pStatement.setInt(3, resBillForm.getResidentId());
				pStatement.setFloat(4, resBillForm.getPreMeterReading());
				pStatement.setFloat(5, resBillForm.getCurMeterReading());
				pStatement.setString(6, resBillForm.getBillStartDate());
				pStatement.setString(7, resBillForm.getBillEndDate());
				pStatement.setInt(8, resBillForm.getCreatedBy());
				
				int result = pStatement.executeUpdate();
				// Default Assign the new Service to all the members of this Facility.
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

		public void updateStaffDetail(DataSource  datasource, ConsumerBillsForm resBillForm) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.UPDATE_STAFF_SQL;
				
				//update staff_master set staffname=?,category=?,subcategory=?,joining_date=?,jobtype=?,salary=?,address=?,contact_no=?,agencyname=? where staff_id=?
				pStatement = con.prepareStatement(sql);

				pStatement.setString(1, handleNullForString(resBillForm.getStaffName()));
				pStatement.setInt(2, resBillForm.getStaffCategory());
				pStatement.setInt(3, resBillForm.getStaffSubCategory());
				pStatement.setString(4, resBillForm.getJoiningDate());
				pStatement.setString(5, resBillForm.getJobType());
				pStatement.setString(6, resBillForm.getSalary());
				pStatement.setString(7, resBillForm.getAddress());
				pStatement.setString(8, resBillForm.getContactNo());
				pStatement.setString(9, resBillForm.getAgencyName());
				pStatement.setInt(10, resBillForm.getStaffId());

				int result = pStatement.executeUpdate();
				System.out.println(result+"++++++++++++++++++++++++++++++++++");
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

		
public void generateBill(DataSource  datasource, ConsumerBillsForm resBillForm) throws Exception {
			Connection con = null;
			PreparedStatement pStatement = null;
			try {
				con = datasource.getConnection();
				String sql = DBConstant.GENERATE_RESIDENT_BILL;
				
				//update staff_master set staffname=?,category=?,subcategory=?,joining_date=?,jobtype=?,salary=?,address=?,contact_no=?,agencyname=? where staff_id=?
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1, resBillForm.getConsumerId());
				pStatement.setInt(2, resBillForm.getServiceId());
				pStatement.setInt(3, resBillForm.getBillMonth());
				pStatement.setInt(4, resBillForm.getBillYear());
				pStatement.setString(5, resBillForm.getDueDate());
				pStatement.setInt(6, resBillForm.getDueAmount());
				pStatement.setInt(7, resBillForm.getCreatedBy());
				pStatement.setInt(8, resBillForm.getModifiedBy());
				pStatement.setString(9, resBillForm.getUniqueId());

				int result = pStatement.executeUpdate();
				System.out.println(result+"++++++++++++++++++++++++++++++++++");
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
				System.out.println("Services inserted to consumer : "
						+ result.length);
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
