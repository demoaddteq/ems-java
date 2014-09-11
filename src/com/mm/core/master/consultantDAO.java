package com.mm.core.master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
//import java.sql.CallableStatement;

import javax.sql.DataSource;

import com.mm.core.resource.DBConstant;
import com.mm.struts.corpo.form.ConsultantForm;




public class consultantDAO {
	public void insertConsultantInfo(DataSource datasource,ConsultantForm consultantForm)
	{
		
		Connection con=null;
		PreparedStatement pStatement=null;
		ResultSet rs=null;
		int rows =0;
		int consultantID=0;
		//CallableStatement cStatement = null;
		
		try{
			con=datasource.getConnection();
			String sql=DBConstant.SELECT_INSERT_CONS_INFO;
			pStatement=con.prepareStatement(sql);
			pStatement.setString(1,consultantForm.getFirstName());
			pStatement.setString(2,consultantForm.getLastName());
			pStatement.setString(3,consultantForm.getAddress1());
			pStatement.setString(4,consultantForm.getAddress2());
			pStatement.setString(5,consultantForm.getCity());
			pStatement.setString(6,consultantForm.getState());
			pStatement.setInt(7,consultantForm.getZip());
			pStatement.setString(8,consultantForm.getEmail());
			pStatement.setString(9,consultantForm.getPhone());
			pStatement.setString(10,consultantForm.getStart_date());
			
			rows = pStatement.executeUpdate();
			pStatement.close();
			
			System.out.println("value of rows ==>"+rows);
			if(rows ==1 )
			{
				sql = DBConstant.SELECT_MAX_CID;
				pStatement = con.prepareStatement(sql);
				rs=pStatement.executeQuery();
				
				while(rs.next()){
				consultantID = Integer.parseInt(rs.getString(1));
				}
				pStatement.close();
				
				System.out.println("value of consultantID==>"+consultantID);
				sql= DBConstant.INSERT_WORKORDER_INFO;
				pStatement = con.prepareStatement(sql);
				pStatement.setInt(1,consultantID);
				pStatement.setInt(2,consultantForm.getVid());
				pStatement.setFloat(3,consultantForm.getRate());
				pStatement.setFloat(4,consultantForm.getCost());
				pStatement.setString(5,consultantForm.getBilling_type());
				pStatement.setInt(6,consultantForm.getSubcon());
				pStatement.setString(7,consultantForm.getStart_date());
				pStatement.setString(8,consultantForm.getTimesheet_type());
				pStatement.setString(9,consultantForm.getInvoicing_type());
				pStatement.executeUpdate();
				
			}
			
			//cStatement = con.prepareCall("{call INSERT_CONSULTANT}");
		
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
	
public Vector getConsultantList(DataSource datasource) throws Exception {
		
		Connection con=null;
		PreparedStatement pStatement=null;
		ResultSet rs=null;
		Vector consultantList =new Vector();
		
		try{
			con=datasource.getConnection();
			String sql=DBConstant.SELECT_CON_LIST;
			pStatement=con.prepareStatement(sql);
			rs=pStatement.executeQuery();
			ConsultantForm bean;
			
			while(rs.next()){
				
				bean=new ConsultantForm();
				
				
				bean.setLastName(rs.getString("lastName"));
				bean.setFirstName(rs.getString("firstName"));
				bean.setAddress1(rs.getString("address1"));
				bean.setAddress2(rs.getString("address2"));
				bean.setCid(rs.getInt("cid"));
				bean.setCity(rs.getString("city"));
				bean.setClosed(rs.getString("closed"));
				bean.setEmail(rs.getString("email"));
				bean.setPhone(rs.getString("phone"));
				bean.setStart_date(rs.getString("start_date"));
				bean.setState(rs.getString("state"));
				bean.setZip(rs.getInt("zip"));
				
				consultantList.add(bean);
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
		
	return consultantList;
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

public Vector getSubconName(DataSource datasource)throws Exception
{
	Connection con=null;
	PreparedStatement pStatement=null;
	ResultSet rs=null;
	Vector subconList =new Vector();
	
	try{
		con=datasource.getConnection();
		String sql=DBConstant.SELECT_SUBCON_LIST;
		pStatement=con.prepareStatement(sql);
		rs=pStatement.executeQuery();
		objectVendor bean;
		
		while(rs.next()){
			
			bean=new objectVendor();
			bean.setVid(rs.getInt("scid"));
			bean.setName(rs.getString("company"));
			
			
			subconList.add(bean);
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
	
return subconList;
	
	
	

}


public void deleteConsultant(DataSource datasource, int custID)
{
	Connection con=null;
	PreparedStatement pStatement=null;
	ResultSet rs=null;
	
	try{
		con=datasource.getConnection();
		String sql=DBConstant.DELETE_CON_INFO;
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

public ConsultantForm getConsultantInfo(DataSource datasource, int custID)
{
	ConsultantForm v = new ConsultantForm();
	Connection con=null;
	PreparedStatement pStatement=null;
	ResultSet rs=null;
	
	try{
		con=datasource.getConnection();
		String sql=DBConstant.SELECT_CON_INFO;
		pStatement=con.prepareStatement(sql);
		pStatement.setInt(1,custID);
		rs=pStatement.executeQuery();
	
		
		while(rs.next()){
			
			
			
			v.setLastName(rs.getString("lastName"));
			v.setFirstName(rs.getString("firstName"));
			v.setAddress1(rs.getString("address1"));
			v.setAddress2(rs.getString("address2"));
			v.setCid(rs.getInt("cid"));
			v.setCity(rs.getString("city"));
			v.setClosed(rs.getString("closed"));
			v.setEmail(rs.getString("email"));
			v.setPhone(rs.getString("phone"));
			v.setStart_date(rs.getString("start_date"));
			v.setState(rs.getString("state"));
			v.setZip(rs.getInt("zip"));
			
			
			
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


public void getConsultantUpdated(DataSource datasource, ConsultantForm consultantForm)
{
	Connection con=null;
	PreparedStatement pStatement=null;
	ResultSet rs=null;
	
	try{
		con=datasource.getConnection();
		String sql=DBConstant.UPDATE_CON_INFO;
		pStatement=con.prepareStatement(sql);
		
		pStatement.setString(1,consultantForm.getFirstName());
		pStatement.setString(2,consultantForm.getLastName());
		pStatement.setString(3,consultantForm.getAddress1());
		pStatement.setString(4,consultantForm.getAddress2());
		pStatement.setString(5,consultantForm.getCity());
		pStatement.setString(6,consultantForm.getState());
		pStatement.setInt(7,consultantForm.getZip());
		pStatement.setString(8,consultantForm.getEmail());
		pStatement.setString(9,consultantForm.getPhone());
		pStatement.setString(10,consultantForm.getStart_date());
		pStatement.setInt(11,consultantForm.getCid());
	
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

}//end of class
