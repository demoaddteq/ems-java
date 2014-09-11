package com.mm.core.master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.sql.DataSource;

import com.addteq.struts.form.ExpenseReportForm;
import com.addteq.struts.resource.DBConstant;

public class expenseReportDAO {

public Vector getCategoryReport(DataSource datasource , String year) throws Exception {
		
		Connection con=null;
		PreparedStatement pStatement=null;
		ResultSet rs=null;
		Vector categoryList =new Vector();
		
		try{
			con=datasource.getConnection();
			String sql=DBConstant.RETRIEVE_CATEGORY_EXPENSE_LIST;
			pStatement=con.prepareStatement(sql);
			pStatement.setString(1, year);
	
			rs=pStatement.executeQuery();
			ExpenseReportForm bean;
			
			while(rs.next()){
				
				bean=new ExpenseReportForm();
			
				bean.setCategory(rs.getString("category"));
				bean.setAmount(rs.getString(2));
				bean.setType(rs.getInt("type"));
				categoryList.add(bean);
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
		
	return categoryList;
}

public Vector getCategoryDetailReport(DataSource datasource , int categoryID) throws Exception {
	
	Connection con=null;
	PreparedStatement pStatement=null;
	ResultSet rs=null;
	Vector categoryList =new Vector();
	
	try{
		con=datasource.getConnection();
		String sql=DBConstant.RETRIEVE_CATEGORY_EXPENSE_DETAIL_LIST;
		pStatement=con.prepareStatement(sql);
		pStatement.setInt(1, categoryID);

		rs=pStatement.executeQuery();
		ExpenseReportForm bean;
		
		while(rs.next()){
			
			bean=new ExpenseReportForm();
		
			bean.setDate(rs.getString("date"));
			bean.setAmount(rs.getString("amount"));
			bean.setDescription(rs.getString("description"));
			bean.setType(rs.getInt("type"));
			categoryList.add(bean);
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
	
return categoryList;
}

public Vector getSearchResult(DataSource datasource, String n) throws Exception {
	
	Connection con=null;
	PreparedStatement pStatement=null;
	ResultSet rs=null;
	Vector categoryList =new Vector();

	ExpenseReportForm bean; 
	
	try{
		con=datasource.getConnection();
		String sql=DBConstant.RETRIEVE_EXPENSE_SEARCH;
		pStatement=con.prepareStatement(sql);
		pStatement.setString(1, n);

		rs=pStatement.executeQuery();
	
		
		while(rs.next()){
			
			bean = new ExpenseReportForm();
		
			bean.setDate(rs.getString("date"));
			bean.setAmount(rs.getString("amount"));
			bean.setCategory(rs.getString("category"));
			bean.setDescription(rs.getString("description"));
			bean.setType(rs.getInt("type"));
			categoryList.add(bean);
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
	
return categoryList;
}
	
}//end of class
