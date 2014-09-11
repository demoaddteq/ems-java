package com.mm.core.master;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.sql.DataSource;

import com.mm.core.resource.DBConstant;
import com.mm.struts.corpo.form.CommissionForm;

public class commissionDAO {

	
	public void insertCommissionInfo(DataSource datasource,CommissionForm commissionForm)
	{
		
		Connection con=null;
		PreparedStatement pStatement=null;
		ResultSet rs=null;
		
		
		try{
			con=datasource.getConnection();
			String sql=DBConstant.SELECT_INSERT_COM_INFO;
			pStatement=con.prepareStatement(sql);
			pStatement.setInt(1,commissionForm.getEarner_id());
			pStatement.setInt(2,commissionForm.getWid());
			pStatement.setInt(3,commissionForm.getTable_id());
			pStatement.setFloat(4,commissionForm.getAmount());
			pStatement.setString(5,commissionForm.getStart_date());
			pStatement.setString(6,commissionForm.getEnd_date());
			
			pStatement.executeUpdate();
			
					
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
		
	
		
	}
	
public Vector getCommissionList(DataSource datasource) throws Exception {
		
		Connection con=null;
		PreparedStatement pStatement=null;
		ResultSet rs=null;
		Vector commissionList =new Vector();
		
		try{
			con=datasource.getConnection();
			String sql=DBConstant.SELECT_COM_LIST;
			pStatement=con.prepareStatement(sql);
			rs=pStatement.executeQuery();
			CommissionForm bean;
			
			while(rs.next()){
				
				bean=new CommissionForm();
				bean.setId(rs.getInt("id"));
				bean.setEarner_id(rs.getInt("earner_id"));
				bean.setAmount(rs.getFloat("amount"));
				bean.setEnd_date(rs.getString("end_date"));
				bean.setStart_date(rs.getString("start_date"));
				bean.setTable_id(rs.getInt("table_id"));
				bean.setWid(rs.getInt("wid"));
				
				
				commissionList.add(bean);
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
		
	return commissionList;
}

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

public Vector getContributorName(DataSource datasource)throws Exception
{
	Connection con=null;
	PreparedStatement pStatement=null;
	ResultSet rs=null;
	Vector contributorList =new Vector();
	
	try{
		con=datasource.getConnection();
		String sql=DBConstant.CONTRIBUTOR;
		pStatement=con.prepareStatement(sql);
		rs=pStatement.executeQuery();
		objectVendor bean;
		
		while(rs.next()){
			
			bean=new objectVendor();
			bean.setVid(rs.getInt(1));
			bean.setName(rs.getString(2)+ " "+ rs.getString(3));
			
			
			contributorList.add(bean);
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
	
return contributorList;
	
	
	

}


public void deleteCommission(DataSource datasource, int custID)
{
	Connection con=null;
	PreparedStatement pStatement=null;
	ResultSet rs=null;
	
	try{
		con=datasource.getConnection();
		String sql=DBConstant.DELETE_COM_INFO;
		pStatement=con.prepareStatement(sql);
		pStatement.setInt(1,custID);
		
		pStatement.executeUpdate();
	
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
	
}

public CommissionForm getCommissionInfo(DataSource datasource, int custID)
{
	CommissionForm v = new CommissionForm();
	Connection con=null;
	PreparedStatement pStatement=null;
	ResultSet rs=null;
	
	try{
		con=datasource.getConnection();
		String sql=DBConstant.SELECT_COM_INFO;
		pStatement=con.prepareStatement(sql);
		pStatement.setInt(1,custID);
		rs=pStatement.executeQuery();
	
		
		while(rs.next()){
			
			
			v.setId(rs.getInt("id"));
			v.setEarner_id(rs.getInt("earner_id"));
			v.setAmount(rs.getFloat("amount"));
			v.setEnd_date(rs.getString("end_date"));
			v.setStart_date(rs.getString("start_date"));
			v.setTable_id(rs.getInt("table_id"));
			v.setWid(rs.getInt("wid"));
			
			
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
	
	
	return v;
}




	
	
}//end of class
