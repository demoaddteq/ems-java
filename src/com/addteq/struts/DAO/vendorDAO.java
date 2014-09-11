package com.addteq.struts.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.sql.DataSource;

import com.addteq.struts.form.ConsultantForm;
import com.addteq.struts.form.VendorForm;
import com.addteq.struts.resource.DBConstant;

public class vendorDAO {

	public void insert_vendor(DataSource datasource, VendorForm vendorform) throws Exception {
		 Connection conn=null;
		 PreparedStatement Pstmt =null;
		 ResultSet rs=null;
		 	try {
		 		conn=datasource.getConnection();
		 		String sql=DBConstant.INSERT_VENDOR;
		 	    System.out.println("student.getFirstName()-->"+vendorform.getCompany());
		 	//	System.out.println("student last name-->"+studentForm.getStudentLastName());
		 		System.out.println("11Querry=>"+sql);
		 		
		 		
		 		Pstmt=conn.prepareStatement(sql);
		 		
		 		
		 		Pstmt.setString(1,vendorform.getCompany());
		 		Pstmt.setString(2,vendorform.getAddress());
		 		Pstmt.setString(3,vendorform.getAddress2());
		 		Pstmt.setString(4,vendorform.getCity());
		 		Pstmt.setString(5,vendorform.getState());
		 		Pstmt.setInt(6,vendorform.getZip());
		 		
		 				 		
		 		int result=Pstmt.executeUpdate();
		 		
		 		System.out.println("After Insert State-->"+result);
		 		
		 	}finally{
		 		try{
		 			if(Pstmt!=null){
		 				Pstmt.close();
		 				Pstmt=null;
		 			}
		 			if(conn!=null){
		 				conn.close();
		 				conn=null;
		 			}
		 		}catch(SQLException exception2){
		 			
		 		}
		 	}
		 
	}
	
	
	public void insert_subcontractor(DataSource datasource, VendorForm vendorform) throws Exception {
		 Connection conn=null;
		 PreparedStatement Pstmt =null;
		 ResultSet rs=null;
		 	try {
		 		conn=datasource.getConnection();
		 		String sql=DBConstant.INSERT_SUBCON;
		 	    System.out.println("student.getFirstName()-->"+vendorform.getCompany());
		 	//	System.out.println("student last name-->"+studentForm.getStudentLastName());
		 		System.out.println("11Querry=>"+sql);
		 		
		 		
		 		Pstmt=conn.prepareStatement(sql);
		 		
		 		
		 		Pstmt.setString(1,vendorform.getCompany());
		 		Pstmt.setString(2,vendorform.getAddress());
		 		Pstmt.setString(3,vendorform.getAddress2());
		 		Pstmt.setString(4,vendorform.getCity());
		 		Pstmt.setString(5,vendorform.getState());
		 		Pstmt.setInt(6,vendorform.getZip());
		 		Pstmt.setInt(7,vendorform.getEin());
		 		
		 				 		
		 		int result=Pstmt.executeUpdate();
		 		
		 		System.out.println("After Insert State-->"+result);
		 		
		 	}finally{
		 		try{
		 			if(Pstmt!=null){
		 				Pstmt.close();
		 				Pstmt=null;
		 			}
		 			if(conn!=null){
		 				conn.close();
		 				conn=null;
		 			}
		 		}catch(SQLException exception2){
		 			
		 		}
		 	}
		 
	}
	
public Vector getVendorList(DataSource datasource) throws Exception {
		
		
		Connection con=null;
		PreparedStatement pStatement=null;
		ResultSet rs=null;
		Vector vendorList =new Vector();
		System.out.println("Querry=Here>");
		try{
			System.out.println("Querry=Here>");
			con=datasource.getConnection();
			String sql=DBConstant.SELECT_VENDOR_LIST;
			System.out.println("Querry=>"+sql);
			pStatement=con.prepareStatement(sql);
			rs=pStatement.executeQuery();
			VendorForm bean;
			
			while(rs.next()){
				
				bean=new VendorForm();
				
				
				bean.setCompany(rs.getString("company"));
				bean.setAddress(rs.getString("address"));
				bean.setAddress2(rs.getString("address2"));
				bean.setCity(rs.getString("city"));
				bean.setState(rs.getString("state"));
				bean.setZip(rs.getInt("zip"));
				
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
	

}
