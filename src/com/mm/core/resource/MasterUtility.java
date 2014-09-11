/**
 * 
 */ 

package com.mm.core.resource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
/**
 * @author prashant
 *	Date 9/5/2007.
 */
/**
 * This class is use for performing all the data base related 
 * function.This class use data base name for listing, get detail
 * Update ,Add and search.  
 */
public class MasterUtility {
	
	//Resource resource = new Resource();
/**
 * 
 * @param dbName data base name foe getting connection.
 * @param strQuery contains query for selection of Data 
 * @param dataVec contains those fields of table those are required 
 * by user
 * @return a vector that contais list of all field value which user wants.
 */
/**
 * This function is use for getting detail of a perticuler Row
 * according to given parameter.
 */
	public Vector getDetail(DataSource ds, String strQuery, Support support)
	{
		Connection con = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		Vector<String> resultVec = new Vector<String>();
		Vector fieldVec = support.getFieldVec();
		Vector typeVec = support.getTypeFieldVec();
	//	System.out.println("Field Vec "+fieldVec);
	//	System.out.println("Type Vec "+typeVec);
		try
		{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(strQuery);
			if(rs.next())
			{
				for(int i=0;i<fieldVec.size();i++)
				{
					if(typeVec.elementAt(i).toString().trim().equalsIgnoreCase("int"))
					{
						resultVec.add(String.valueOf(rs.getInt(fieldVec.elementAt(i).toString().trim())));
					}
					else
					{
						resultVec.add(rs.getString(fieldVec.elementAt(i).toString().trim()));
					}
				}
			}
			
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
				}
			}
			catch(Exception exp){}
		}
		return resultVec;
	}
/**
 * @param dbName data base name foe getting connection.
 * @param strQuery contains query for selection of list of Data 
 * @param dataVec contains those fields of table those are required 
 * by user
 * @return a vector that contais list of all field value which user wants.
 */
/**
 * This function is use for getting list according to given parameter.
 */
	public Vector getList(DataSource ds, String strQuery, Support support)
	{
		Connection con = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		Vector<Vector> resultVec = new Vector<Vector>();
		Vector<String> fieldVec = (Vector)support.getFieldVec();
		Vector<String> typeVec = (Vector)support.getTypeFieldVec();
		System.out.println("\n\nfieldvec:"+fieldVec);
		System.out.println("\n\ntypevec:"+typeVec);
		System.out.println("\n\nsupport:"+support);
		try
		{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(strQuery);
			while(rs.next())
			{
				Vector<String> tempVec = new Vector<String>();
				for(int i=0;i<fieldVec.size();i++)
				{
					if(typeVec.elementAt(i).toString().trim().equalsIgnoreCase("int"))
					{
						tempVec.add(String.valueOf(rs.getInt(fieldVec.elementAt(i).toString().trim())));
					}
					else
					{
						tempVec.add(rs.getString(fieldVec.elementAt(i).toString().trim()));
					}
				}
				
				
				
				resultVec.add(tempVec);
				
			}
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
				}
			}
			catch(Exception exp){}
		}
		return resultVec;
	}
	
	public ArrayList getArrayList(DataSource ds, String strQuery, Support support)
	{
		Connection con = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<ArrayList> resultVec = new ArrayList<ArrayList>();
		Vector<String> fieldVec = (Vector)support.getFieldVec();
		Vector<String> typeVec = (Vector)support.getTypeFieldVec();
		//System.out.println("\n\nsupport:"+support);
		try
		{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(strQuery);
			while(rs.next())
			{
				ArrayList<String> tempVec = new ArrayList<String>();
				for(int i=0;i<fieldVec.size();i++)
				{
					if(typeVec.elementAt(i).toString().trim().equalsIgnoreCase("int"))
					{
						tempVec.add(String.valueOf(rs.getInt(fieldVec.elementAt(i).toString().trim())));
					}
					else
					{
						tempVec.add(rs.getString(fieldVec.elementAt(i).toString().trim()));
					}
				}
				
				
				
				resultVec.add(tempVec);
				
			}
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
				}
			}
			catch(Exception exp){}
		}
		return resultVec;
	}
	
	
	public ArrayList getComplaintList(DataSource ds, Vector Data)
	{
		Connection con = null;
		Statement  stmt = null;
		ResultSet rs = null;
		
		ArrayList subCompArr = new ArrayList();
		ArrayList subOfSubCompArr = new ArrayList();
		ArrayList ComplaintArr = new ArrayList();
		String strQuery ="";
		try
		{
			if(Data.elementAt(0).toString().equalsIgnoreCase("Student"))
			{
			strQuery= "SELECT * from complaints com, loginmaster log, places pl where com.login_id= "+Data.elementAt(1).toString()+" and com.companyid="+Data.elementAt(2).toString()+"  and  q_type='"+Data.elementAt(3).toString()+"' and com.advtL_id=log.userid and log.city = pl.placeId ";
			}
			
			
			//System.out.println("strQuery in Volunteer List "+strQuery);
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(strQuery);
			
			int count=1;
			while(rs.next())
			{	
				
					subOfSubCompArr=new ArrayList();
					subOfSubCompArr.add(rs.getInt("com.complaint_id"));
					subOfSubCompArr.add(rs.getInt("com.fcom_id"));
					subOfSubCompArr.add(rs.getString("com.complaint_title"));
					subOfSubCompArr.add(rs.getString("com.creation_date"));
					subOfSubCompArr.add(rs.getString("com.creation_time"));
					subOfSubCompArr.add(rs.getInt("com.login_id"));
					subOfSubCompArr.add(rs.getString("log.first_name"));
					subOfSubCompArr.add(rs.getString("log.last_name"));
					subOfSubCompArr.add(rs.getString("pl.PlaceName"));
					subOfSubCompArr.add(rs.getString("com.q_type"));
					subCompArr.add(subOfSubCompArr);
					
					count++;
					
					
					if(count==21)
					{
						count=1;
						
							ComplaintArr.add(subCompArr);
							//System.out.println("Size of subOfSubCompArr in if"+subOfSubCompArr.size());
							//System.out.println("Size of subCompArr in if"+subCompArr.size());
							//System.out.println("Size of ComplaintArr in if"+ComplaintArr.size());
							
							
							subCompArr=new ArrayList();
							
					}
				
			}
			
			if(count<20&&count>1)
			{
								
					ComplaintArr.add(subCompArr);
					
					subCompArr=new ArrayList();
					
			}
					
			//System.out.println("strQuery in Volunteer Listin final "+ComplaintArr.size());
			//System.out.println("strQuery in Volunteer Listin final "+ComplaintArr);
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
				}
			}
			catch(Exception exp){}
		}
		return ComplaintArr;
	}
	
	
	
	public ArrayList getDraftList(DataSource ds, Vector Data)
	{
		Connection con = null;
		Statement  stmt = null;
		ResultSet rs = null;
		
		ArrayList subCompArr = new ArrayList();
		ArrayList subOfSubCompArr = new ArrayList();
		ArrayList ComplaintArr = new ArrayList();
		String strQuery ="";
		try
		{
			if(Data.size()<2)
			{
				 strQuery = "select * from draft_queries where "+Data.elementAt(0).toString()+"='"+Data.elementAt(1).toString()+"' ";
			}
			else
			{
				strQuery = "select * from draft_queries where "+Data.elementAt(0).toString()+"="+Data.elementAt(1).toString()+" and "+Data.elementAt(2).toString()+"='"+Data.elementAt(3).toString()+"' ";
			}
			
			
			//System.out.println("strQuery in Volunteer List "+strQuery);
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(strQuery);
			
			int count=1;
			while(rs.next())
			{	
				
					subOfSubCompArr=new ArrayList();
					
					subOfSubCompArr.add(rs.getInt("draft_id"));
					subOfSubCompArr.add(rs.getString("draft_title"));
					subOfSubCompArr.add(rs.getString("draft_text"));
					subOfSubCompArr.add(rs.getString("creation_date"));
					subOfSubCompArr.add(rs.getString("creation_time"));
					subOfSubCompArr.add(rs.getInt("com_id"));
					subOfSubCompArr.add(rs.getInt("com_companyid"));
					subOfSubCompArr.add(rs.getInt("login_id"));
					subOfSubCompArr.add(rs.getInt("companyid"));
					subOfSubCompArr.add(rs.getInt("publish_flag"));
					subOfSubCompArr.add(rs.getString("query_type"));
					subOfSubCompArr.add(rs.getString("lastmodify_date"));
					subOfSubCompArr.add(rs.getString("lastmodify_time"));
					
					subCompArr.add(subOfSubCompArr);
					
					count++;
					
					
					if(count==21)
					{
						count=1;
						
							ComplaintArr.add(subCompArr);
							////System.out.println("Size of subOfSubCompArr in if"+subOfSubCompArr.size());
							////System.out.println("Size of subCompArr in if"+subCompArr.size());
							////System.out.println("Size of ComplaintArr in if"+ComplaintArr.size());
							
							
							subCompArr=new ArrayList();
							
					}
				
			}
			
			if(count<20&&count>1)
			{
								
					ComplaintArr.add(subCompArr);
					
					subCompArr=new ArrayList();
					
			}
					
			//System.out.println("strQuery in Volunteer Listin final "+ComplaintArr.size());
			////System.out.println("strQuery in Volunteer Listin final "+ComplaintArr);
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
				}
			}
			catch(Exception exp){}
		}
		return ComplaintArr;
	}
	
	
/**
 * 
 * @param dbName Data base name 
 * @param strQuery prepared query statement for inserting Data ina paertculer Field.
 * @param dataVec contains list of filds those will insert in Data Base.
 * @return Status of the query of insrting filed in Data base.
 */
	
	
	
	
	public ArrayList getListCountries(DataSource ds)
	{
		Connection con = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<ArrayList> resultArr = new ArrayList<ArrayList>();
		ArrayList<String> tempArr = null;
		
		try
		{
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from countries where active=1 order by country_name");
			ArrayList<String> tempVec = new ArrayList<String>();
			tempVec.add("0");
			tempVec.add("+0");
		    tempVec.add("Select");
		    resultArr.add(tempVec);
			while(rs.next())
			{
				tempArr = new ArrayList<String>();
				tempArr.add(String.valueOf(rs.getInt("id")));
				tempArr.add(rs.getString("country_code"));
				tempArr.add(rs.getString("country_name"));
				resultArr.add(tempArr);
			}
			ArrayList<String> tempVec1 = new ArrayList<String>();
		    tempVec1.add("-1");
		    tempVec1.add("+0");
		    tempVec1.add("Others");
		    resultArr.add(tempVec1);
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
				}
			}
			catch(Exception exp){}
		}
		return resultArr;
	}
	
	public HashMap getListState(DataSource ds)
	{
		Connection con = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmt1 = null;
		ResultSet rs1 = null;
		HashMap<Integer, ArrayList> resultMap = new HashMap<Integer, ArrayList>();
		
		try
		{
			
			con = ds.getConnection();
			stmt1 = con.createStatement();
			rs1 = stmt1.executeQuery("select * from countries where active=1");
			while(rs1.next())
			{
				ArrayList<ArrayList> arrTemp = new ArrayList<ArrayList>();
				ArrayList<String> arrTemp2 = new ArrayList<String>();
				arrTemp2.add("0");
				arrTemp2.add("Select");
				ArrayList<String> arrTemp3 = new ArrayList<String>();
				arrTemp3.add("-1");
				arrTemp3.add("Others");
				arrTemp.add(arrTemp2);
				int numCid = rs1.getInt("id");
				stmt = con.createStatement();
				rs = stmt.executeQuery("select * from states where active=1 and countryid= "+numCid+" order by statename");
				while(rs.next())
				{
					ArrayList<String> arrTemp1 = new ArrayList<String>();
					int numValue = rs.getInt("stateid");
					String strValue = rs.getString("statename");
					arrTemp1.add(String.valueOf(numValue));
					arrTemp1.add(strValue);
					arrTemp.add(arrTemp1);
					
				}
				arrTemp.add(arrTemp3);
				resultMap.put(numCid, arrTemp);
				
				rs.close();
				stmt.close();
			}
			
			rs1.close();
			stmt1.close();
			
			con.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt1 != null)
				{
					stmt1.close();
				}
				if(rs1 != null)
				{
					rs1.close();
				}
				if(stmt != null)
				{
					stmt.close();
				}
				if(rs != null)
				{
					rs.close();
				}
				if(con != null)
				{
					con.close();
				}
			}
			catch(Exception exp){}
		}
		return resultMap;
	}
	public HashMap getListCities(DataSource ds)
	{
		Connection con = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmt1 = null;
		ResultSet rs1 = null;
		HashMap<Integer, ArrayList> resultMap = new HashMap<Integer, ArrayList>();
		try
		{
			con = ds.getConnection();
			stmt1 = con.createStatement();
			rs1 = stmt1.executeQuery("select * from states where active=1");
			while(rs1.next())
			{
				ArrayList<ArrayList> arrTemp = new ArrayList<ArrayList>();
				ArrayList<String> arrTemp2 = new ArrayList<String>();
				arrTemp2.add("0");
				arrTemp2.add("Select");
				ArrayList<String> arrTemp3 = new ArrayList<String>();
				arrTemp3.add("-1");
				arrTemp3.add("Others");
				arrTemp.add(arrTemp2);
				int numSid = rs1.getInt("stateid");
				stmt = con.createStatement();
				rs = stmt.executeQuery("select * from places where active=1 and StateID="+numSid+" order by PlaceName");
				while(rs.next())
				{
					ArrayList<String> arrTemp1 = new ArrayList<String>();
					int numValue = rs.getInt("PlaceID");
					String strValue = rs.getString("Place");
					arrTemp1.add(String.valueOf(numValue));
					arrTemp1.add(strValue);
					arrTemp.add(arrTemp1);
				}
				arrTemp.add(arrTemp3);
				resultMap.put(numSid, arrTemp);
				
				rs.close();
				stmt.close();
			}
			rs1.close();
			stmt1.close();
			
			con.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt1 != null)
				{
					stmt1.close();
				}
				if(rs1 != null)
				{
					rs1.close();
				}
				if(stmt != null)
				{
					stmt.close();
				}
				if(rs != null)
				{
					rs.close();
				}
				if(con != null)
				{
					con.close();
				}
			}
			catch(Exception exp){}
		}
		return resultMap;
	}
		
	
	
/**
 * This function is use for inserting ,Updating and deleting data
 * in a perticular table according to given data base name ,table
 * name and prepared Statement.
 */
	public String setData(DataSource ds, String strQuery, Support support)
	{
		Connection con = null;
		PreparedStatement prepinsert = null;
		
		String strResult = "failure";
		Vector dataVec = support.getDataVec();
		Vector typeVec = support.getTypeDataVec();
		try
		{
			con = ds.getConnection();
			prepinsert = con.prepareStatement(strQuery);
			//System.out.println("in master utility>>>"+dataVec);
			for(int i=0;i<dataVec.size();i++)
			{
				if(typeVec.elementAt(i).toString().trim().equalsIgnoreCase("int"))
				{
					//System.out.println("in if i>>"+i);
					prepinsert.setInt(i+1, Integer.parseInt(dataVec.elementAt(i).toString().trim()));
				}
				else
				{
					//System.out.println("in else i>>"+i);
					prepinsert.setString(i+1,dataVec.elementAt(i).toString().trim());
				}
				
			}
			int row = prepinsert.executeUpdate();
			strResult = (row==0) ? "failure" : "success";
			
			prepinsert.close();
			con.close();
			
		}
		catch(SQLException sqle) {
			strResult = "failure";
			sqle.printStackTrace();
		}
		catch(Exception e) {
			strResult = "failure";
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
				}
			}
			catch(Exception exp){}
		}
		return strResult;
	}
	/**
	 * Added Ajay Kumar Jha
	 * Date - 21 Sept 2009
	 * To Update and Modify using Statement
	 * @param ds
	 * @param strQuery
	 * @return
	 */
	public String setData(DataSource ds, String strQuery)
	{
		Connection con = null;
		Statement prepinsert = null;
		
		String strResult = "failure";
		
		try
		{
			con = ds.getConnection();
			prepinsert = con.createStatement();
						
			int row = prepinsert.executeUpdate(strQuery);
			strResult = (row<=0) ? "failure" : "success";
			
			prepinsert.close();
			con.close();
			
		}
		catch(SQLException sqle) {
			strResult = "failure";
			sqle.printStackTrace();
		}
		catch(Exception e) {
			strResult = "failure";
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
				}
			}
			catch(Exception exp){}
		}
		return strResult;
	}
	/**
	 * This function is use for  deleting data
	 * in a perticular table according to given data base name ,table
	 * name and prepared Statement.
	 */
		public String deleteData(DataSource ds, String strQuery)
		{
			Connection con = null;
			
			Statement stmt = null;
			
			String strResult = "failure";
			
			try
			{
				con = ds.getConnection();
				stmt = con.createStatement();
				
				int row = stmt.executeUpdate(strQuery);
				strResult = (row==0) ? "failure" : "success";
				
				stmt.close();
				con.close();
				
			}
			catch(SQLException sqle) {
				strResult = "failure";
				sqle.printStackTrace();
			}
			catch(Exception e) {
				strResult = "failure";
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(con != null)
					{
						con.close();
					}
				}
				catch(Exception exp){}
			}
			return strResult;
		}
/**
 * 
 * @param dbName Data Base Name 
 * @param strQuery SQL query for fatching no of rows frm particuler Table.
 * @return
 */
	
	public int getCount(DataSource ds, String strQuery)
	{
		Connection con = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		int count=0;
		try
		{
			
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(strQuery);
			if(rs.next())
			{
				count = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
				}
			}
			catch(Exception exp){}
		}
		return count;
	}
/**
 * This function use for getting a ID for a perticuler table.
 */

	public int getId(DataSource ds,String strQuery,String idName)
	{
		Connection con = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		int resultId = 0;
		
		try
		{
			
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(strQuery);
			if(rs.next())
			{
				resultId=rs.getInt(idName);
				
			}
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
				}
			}
			catch(Exception exp){}
		}
		return resultId;
	}
	
	/**
	 * This function is use for getting name of a perticuler Row
	 * according to given parameter.
	 */
		public String getValue(DataSource ds, String strQuery, Support support)
		{
			Connection con = null;
			
			Statement stmt = null;
			ResultSet rs = null;
			String strResult ="failure";
			Vector fieldVec = support.getFieldVec();
			Vector typeVec = support.getTypeFieldVec();
			try
			{
				con = ds.getConnection();
				stmt = con.createStatement();
				rs = stmt.executeQuery(strQuery);
				if(rs.next())
				{
					for(int i=0;i<fieldVec.size();i++)
					{
						if(typeVec.elementAt(i).toString().trim().equalsIgnoreCase("int"))
						{
							strResult=String.valueOf(rs.getInt(fieldVec.elementAt(i).toString().trim()));
						}
						else
						{
							strResult=rs.getString(fieldVec.elementAt(i).toString().trim());
						}
					}
				}
				
				
				rs.close();
				stmt.close();
				con.close();
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(con != null)
					{
						con.close();
					}
				}
				catch(Exception exp){}
			}
			return strResult;
		}
		/**
		 * This function is used to get no of ids against a primary key value.
		 * To get these values please type query as
		 * Select id from xyztable where ys=?
		 * Here
		 * id - column name(has numaric values)
		 * xyztable - table name
		 * ys - condition for query
		 * @param ds
		 * @param strQuery
		 * @return
		 */
		public Vector getIds(DataSource ds, String strQuery)
		{
			Connection con = null;
			
			Statement stmt = null;
			ResultSet rs = null;
			Vector<String> resultVec = new Vector<String>();
			try
			{
				int i=0;
				con = ds.getConnection();
				stmt = con.createStatement();
				rs = stmt.executeQuery(strQuery);
				while(rs.next())
				{
					resultVec.add(String.valueOf(rs.getInt(1)));
				}
				rs.close();
				stmt.close();
				con.close();
			}
			catch(SQLException sqle) {
				sqle.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if(con != null)
					{
						con.close();
					}
				}
				catch(Exception exp){}
			}
			return resultVec;
		}


/**
 * This function is use for inserting ,Updating and deleting data
 * in a perticular table according to given data source and query vector
 */
	public String setBatchData(DataSource ds, Vector quesryVec)
	{
		Connection con = null;
		Statement  stmt = null;
		
		String strResult = "failure";
		
		try
		{
			con = ds.getConnection();
			stmt = con.createStatement();
			con.setAutoCommit(false);
			
			for(int i=0;i<quesryVec.size();i++)
			{
				
				stmt.addBatch(quesryVec.elementAt(i).toString().trim());
			}

			int[] row = stmt.executeBatch();
			for(int j=0;j<row.length;j++)
			{
				strResult = (row[j]==0) ? "failure" : "success";
				if(row[j]==0)
					break;
			}
			
			if(strResult.equals("success"))
				con.commit();
			else
				con.rollback();
			
			stmt.close();
			con.close();
			
		}
		catch(SQLException sqle) {
			strResult = "failure";
			sqle.printStackTrace();
		}
		catch(Exception e) {
			strResult = "failure";
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(con != null)
				{
					con.close();
				}
			}
			catch(Exception exp){}
		}
		return strResult;
	}
	
	public HashMap getTestCategory(DataSource ds)
	{
		Connection con = null;
		
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmt1 = null;
		ResultSet rs1 = null;
		HashMap<String, ArrayList> resultMap = new HashMap<String, ArrayList>();
		try
		{
			con = ds.getConnection();
			stmt1 = con.createStatement();
			rs1 = stmt1.executeQuery("select tc_cat from testcategory group by tc_cat");
			while(rs1.next())
			{
				ArrayList<ArrayList> arrTemp = new ArrayList<ArrayList>();
				ArrayList<String> arrTemp2 = new ArrayList<String>();
				arrTemp2.add("0");
				arrTemp2.add("Select");
				ArrayList<String> arrTemp3 = new ArrayList<String>();
				arrTemp3.add("-1");
				arrTemp3.add("Others");
				arrTemp.add(arrTemp2);
				String strTC_Cat = rs1.getString("tc_cat");
				stmt = con.createStatement();
				rs = stmt.executeQuery("select * from testcategory where tc_cat='"+strTC_Cat.trim()+"'");
				while(rs.next())
				{
					ArrayList<String> arrTemp1 = new ArrayList<String>();
					int numValue = rs.getInt("tc_id");
					String strValue = rs.getString("tc_name");
					arrTemp1.add(String.valueOf(numValue));
					arrTemp1.add(strValue);
					arrTemp.add(arrTemp1);
				}
				arrTemp.add(arrTemp3);
				resultMap.put(strTC_Cat, arrTemp);
				rs.close();
				stmt.close();
			}
			rs1.close();
			stmt1.close();
			
			con.close();
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt1 != null)
				{
					stmt1.close();
				}
				if(rs1 != null)
				{
					rs1.close();
				}
				if(stmt != null)
				{
					stmt.close();
				}
				if(rs != null)
				{
					rs.close();
				}
				if(con != null)
				{
					con.close();
				}
			}
			catch(Exception exp){}
		}
		return resultMap;
	}
}