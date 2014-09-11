/*
 * 
 * For the time being, this code generates 1 invoice for each entry in TIMESHEET table
 * Later, it has to fixed according to different invoicing cycles of the vendors/clients
 *  
 * 
 */


package com.mm.core.master;

import com.addteq.struts.form.CreateInvoiceForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import java.util.Calendar;
import java.util.Vector;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

public class CreateInvoiceDAO {
	
	protected final Log logging = LogFactory.getLog(getClass());
	
	public Vector showPending(DataSource datasource){
		
		logging.info("Inside DAO. Inside showPending.");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector vector = new Vector();
		String query = "SELECT C.FIRSTNAME, C.LASTNAME, V.COMPANY, T.BILLABLE_HOURS, T.PERIOD_START_DATE, T.PERIOD_END_DATE, T.WID, T.TS_ID "+
					   "FROM TIMESHEET T, WORKORDER W, VENDOR_ID V, CONSULTANT C "+
					   "WHERE T.INVOICE_ID IS NULL AND T.WID=W.WID AND V.VID=W.VID AND W.CID=C.CID";
		
		try{
			con = datasource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				CreateInvoiceForm invForm = new CreateInvoiceForm();
				invForm.setConsultantName(rs.getString("firstname")+" "+rs.getString("lastname"));
				invForm.setVendorName(rs.getString("company"));
				invForm.setHours(rs.getInt("billable_hours"));
				invForm.setPeriodStartDate(rs.getString("period_start_date"));
				invForm.setPeriodEndDate(rs.getString("period_end_date"));
				invForm.setWid(rs.getInt("wid"));
				invForm.setTsId(rs.getInt("ts_id"));
				logging.info(invForm.toString());
				vector.add(invForm);

			}
			
			
		}catch (Exception e){
			
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					 rs.close();
					 rs=null;
				 }
				 if(ps!=null){
					 ps.close();
					 ps=null;
				 }
				 if(con!=null){
					 con.close();
					 con=null;
				 }
			} catch (SQLException e2){
				e2.printStackTrace();
			}
			
		}
		
		logging.info("Vector size-->"+vector.size());
		return vector;
	}
	
	public void createInvoice(DataSource datasource, int wid, int tsId){
		
		logging.info("Inside DAO. Inside createInvoce()");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int invoiceId = 0;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String createInv = "INSERT INTO INVOICE (WID, INVOICE_DATE) VALUES (?,?)";
		String getInv = "SELECT MAX(INVOICE_ID) FROM INVOICE WHERE WID=?";
		String updateTS = "UPDATE TIMESHEET SET INVOICE_ID=? WHERE TS_ID=?";
		
		try{
			con = datasource.getConnection();
			
			// create invoice
			ps = con.prepareStatement(createInv);
			ps.setInt(1, wid);
			ps.setString(2, sdf.format(cal.getTime()));
			int test = ps.executeUpdate();
			logging.info("create invoice test-->"+test);
			
			// get the generated invoice_id
			ps = con.prepareStatement(getInv);
			ps.setInt(1, wid);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				invoiceId = rs.getInt("max(invoice_id)");
			}
			
			logging.info("invoiceId-->"+invoiceId);
			
			// update TIMESHEET with the generated invoice_id
			ps = con.prepareStatement(updateTS);
			ps.setInt(1, invoiceId);
			ps.setInt(2, tsId);
			test = ps.executeUpdate();
			logging.info("update TIMESHEET test-->"+test);
			
						
		}catch (Exception e){
			
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					 rs.close();
					 rs=null;
				 }
				 if(ps!=null){
					 ps.close();
					 ps=null;
				 }
				 if(con!=null){
					 con.close();
					 con=null;
				 }
			} catch (SQLException e2){
				e2.printStackTrace();
			}
			
		}

		
	}

}
