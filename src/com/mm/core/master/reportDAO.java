package com.mm.core.master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.sql.DataSource;

import com.mm.core.resource.DBConstant;
import com.mm.struts.corpo.form.ReportForm;

public class reportDAO {
	
	public Vector getVendorName(DataSource datasource)throws Exception
	{
		Connection con=null;
		PreparedStatement pStatement=null;
		ResultSet rs=null;
		Vector vendorList =new Vector();
		
		try{
			con=datasource.getConnection();
			String sql=DBConstant.SELECT_VEN_LIST;
			pStatement=con.prepareStatement(sql);
			rs=pStatement.executeQuery();
			objectVendor bean;
			
			while(rs.next()){
				
				bean=new objectVendor();
				bean.setVid(rs.getInt("vid"));
				bean.setName(rs.getString("company"));
				
				
				vendorList.add(bean);
			}
			
		}catch(Exception exception){
			exception.printStackTrace();
		}finally{
			try { 
				if(rs!=null){
					rs.close();
					rs=null;
				}
				if(pStatement!=null){
					pStatement.close();
					pStatement=null;
				}
				if(con!=null){
					con.close();
					con=null;
				}
			}catch(SQLException exception2){
				
			}
		}
		
	return vendorList;
		
		
		

	}


public Vector getInvoiceReport(DataSource datasource, String vid, String startDate, String endDate) throws Exception {
		
		Connection con=null;
		PreparedStatement pStatement=null;
		ResultSet rs=null;
		Vector invoiceList =new Vector();
		int myVid = Integer.parseInt(vid);
		System.out.println("vid ==>"+myVid);
		System.out.println("start Date ==>"+startDate);
		System.out.println("end Date ==>"+endDate);
		
		try{
			con=datasource.getConnection();
			String sql=DBConstant.RETRIEVE_INVOICE_REPORT;
			String sql2=DBConstant.RETRIEVE_INVOICE_REPORT2;
			if((endDate=="") || (startDate=="")|| (startDate=="null"))
			{
				pStatement=con.prepareStatement(sql2);
				System.out.println("I'm here");
			}
			else
			{
				
				pStatement=con.prepareStatement(sql);
				pStatement.setInt(1,myVid);
				pStatement.setString(2,startDate);
				pStatement.setString(3,endDate);
			}
		
			rs=pStatement.executeQuery();
			ReportForm bean;
			
			while(rs.next()){
				
				bean=new ReportForm();
				
				
				bean.setFullName(rs.getString("firstname")+" "+rs.getString("lastname"));
				bean.setVendor(rs.getString("company"));
				bean.setTotal(rs.getFloat("total"));
				bean.setBalance(rs.getFloat("total"));
				
				
				invoiceList.add(bean);
			}
			
		}catch(Exception exception){
			exception.printStackTrace();
		}finally{
			try { 
				if(rs!=null){
					rs.close();
					rs=null;
				}
				if(pStatement!=null){
					pStatement.close();
					pStatement=null;
				}
				if(con!=null){
					con.close();
					con=null;
				}
			}catch(SQLException exception2){
				
			}
		}
		
	return invoiceList;
}
	
}//end of class
