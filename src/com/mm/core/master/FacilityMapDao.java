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
import com.mm.bean.ServiceBean;
import com.mm.core.resource.DBConstant;
import com.mm.struts.corpo.form.FacilityMapForm;
import com.mm.struts.corpo.form.ServiceForm;
import com.mm.struts.form.AddFacilityForm;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class FacilityMapDao 
{
	public String insertFacilityMap(DataSource  datasource, FacilityMapForm facilityMapBean) throws Exception 
	{
		System.out.println("\n\n\n In FacilityMapDao");
		Connection con = null;
		PreparedStatement pStatement= null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//int intFacilityMapId = 0;
		String strReturnResult = "~";
		try 
		{
			con = datasource.getConnection();
			
			int facilityId = facilityMapBean.getFacilityId();
			String sqlChkValidFacilityMapEntry = DBConstant.CHECK_FACILITY_MAP_ISEXIST;
			//"select count(*) as total from facilitymap where N_FacilityId = ? and S_FlatType = ? and S_NoOfRooms = ? and S_TowerNo = ?";
			pStatement = con.prepareStatement(sqlChkValidFacilityMapEntry);
			pStatement.setInt(1, facilityId);
			pStatement.setString(2, facilityMapBean.getFlatType());
			pStatement.setString(3, facilityMapBean.getNoOfRooms());
			pStatement.setString(4, facilityMapBean.getTowerNo());
			rs = pStatement.executeQuery();
			int intFacilityMapCount = 0;
			if (rs.next()) 
			{
				intFacilityMapCount = rs.getInt(1);
			}
			if(intFacilityMapCount == 0)
			{
				String sqlInsert = DBConstant.INSERT_FACILITY_MAP;
				pStatement = con.prepareStatement(sqlInsert);
				
				pStatement.setInt(1, facilityMapBean.getFacilityId());
				pStatement.setString(2, handleNullForString(facilityMapBean.getFlatType()));
				pStatement.setString(3, handleNullForString(facilityMapBean.getNoOfRooms()));
				pStatement.setString(4, handleNullForString(facilityMapBean.getTowerName()));
				pStatement.setString(5, handleNullForString(facilityMapBean.getTowerNo()));
				pStatement.setInt(6, Integer.parseInt(facilityMapBean.getnoOfUnits()));
				pStatement.setInt(7, Integer.parseInt(facilityMapBean.getArea()));
				
				pStatement.setInt(8, facilityMapBean.getCreatedBy());
				//pStatement.setTimestamp(10, facilityMapBean.getModifiedOn());
				pStatement.setInt(9, facilityMapBean.getModifiedBy());
				pStatement.setInt(10, facilityMapBean.getIsValid());

				int result = pStatement.executeUpdate();
					
				if(result == 1)
				{
					strReturnResult = "SuccessfullyAdded";
				}
				else
					strReturnResult = "UnableToAdded";
				
			}
			else if(intFacilityMapCount>0)
			{
				strReturnResult = "AlreadyExist";
			}
		}
		catch(Exception ex )
		{
			//intFacilityMapId = -1;
			strReturnResult = "InsertError";
			System.out.println("ex:"+ex);
		}
		finally 
		{
			try 
			{
				if (pStatement != null) 
				{
					pStatement.close();
					pStatement = null;
				}
				if (con != null) 
				{
					con.close();
					con = null;
				}
			}
			catch (SQLException exception2) 
			{
			}
		}
		return strReturnResult;
	}
	
	public String handleNullForString(Object obj) 
	{
		if (obj == null) 
		{
			return "";
			// return "";
		} 
		else{
			return obj.toString();
		}
	}

	public int getFacilityMapForFacilityID(DataSource  datasource, int intFacilityId) throws Exception
	{
		String strReturnResult="0";
		int intReturnResult = 0;
		Connection con = null;
		PreparedStatement pStatement= null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try
		{
			con = datasource.getConnection();
			System.out.println("\n\n\n In getFacilityMapForFacilityID line142");
	
			int facilityId = intFacilityId;
			String sqlChkValidFacilityMapEntry = DBConstant.CHECK_FACILITY_MAP_EXIST_GIVEN_FACILITYID;
			System.out.println("sqlChkValidFacilityMapEntry|:"+sqlChkValidFacilityMapEntry);
			//"select count(*) as total from facilitymap where N_FacilityId = ? and S_FlatType = ? and S_NoOfRooms = ? and S_TowerNo = ?";
			pStatement = con.prepareStatement(sqlChkValidFacilityMapEntry);
			System.out.println("line   149:");
			System.out.println("line   150:"+facilityId);
			pStatement.setInt(1, facilityId);
			System.out.println("line 149:");
			rs = pStatement.executeQuery();
			int intFacilityMapCount = 0;
			if (rs.next()) 
			{
				strReturnResult = rs.getString(1);
				intReturnResult = Integer.parseInt(rs.getString(1));
			}
			System.out.println("line 154:"+strReturnResult);
		}
		catch(Exception ex )
		{
			//intFacilityMapId = -1;
			strReturnResult = "Error";
			intReturnResult = -1;
			System.out.println("ex:"+ex.getCause());
		}
		finally 
		{
			try 
			{
				if (pStatement != null) 
				{
					pStatement.close();
					pStatement = null;
				}
				if (con != null) 
				{
					con.close();
					con = null;
				}
			}
			catch (SQLException exception2) 
			{
			}
		}
		return intReturnResult;
	}

	public ArrayList getFlatTypeGivenFID(DataSource  datasource, int intFacilityId) throws Exception
	{
		ArrayList arrFlatType = new ArrayList();
		Connection con = null;
		PreparedStatement pStatement= null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			con = datasource.getConnection();
			System.out.println("\n\n\n In getFlatTypeGivenFID line199");
	
			String sqlGetFlatTypeGivenFID = DBConstant.GET_FLATTYPE_GIVEN_FID;
			
			System.out.println("sqlChkValidFacilityMapEntry|:"+sqlGetFlatTypeGivenFID);
			//"select count(*) as total from facilitymap where N_FacilityId = ? and S_FlatType = ? and S_NoOfRooms = ? and S_TowerNo = ?";
			pStatement = con.prepareStatement(sqlGetFlatTypeGivenFID);
			System.out.println("line   147:");
			pStatement.setInt(1, intFacilityId);
			System.out.println("line 149:");
			rs = pStatement.executeQuery();
			int intFacilityMapCount = 0;
			while (rs.next()) 
			{
				arrFlatType.add(rs.getString("S_FlatType"));
			}
			System.out.println("line 154:"+arrFlatType);
		}
		catch(Exception ex )
		{
			System.out.println("ex:"+ex.getCause());
		}
		finally 
		{
			try 
			{
				if (pStatement != null) 
				{
					pStatement.close();
					pStatement = null;
				}
				if (con != null) 
				{
					con.close();
					con = null;
				}
			}
			catch (SQLException exception2) 
			{
			}
		}
		return arrFlatType;
	}

	public ArrayList getDataGivenFIDnFlatType(DataSource  datasource, int intFacilityId, String strFlatType) throws Exception
	{
		ArrayList arrDataGivenFIDnFlatType = new ArrayList();
		//ArrayList arrNoOfRooms = new ArrayList();
		Connection con = null;
		PreparedStatement pStatement= null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			con = datasource.getConnection();
			System.out.println("\n\n\n In getFlatTypeGivenFIDb  line 255"); 
	
			String sqlAllNoOfRoomsGivenFID = DBConstant.GET_DATA_GIVEN_FID_n_FLATTYPE;
			
			System.out.println("sqlAllNoOfRoomsGivenFID:"+sqlAllNoOfRoomsGivenFID);
			//"select count(*) as total from facilitymap where N_FacilityId = ? and S_FlatType = ? and S_NoOfRooms = ? and S_TowerNo = ?";
			pStatement = con.prepareStatement(sqlAllNoOfRoomsGivenFID);
			System.out.println("line   147:");
			System.out.println("intFacilityId:line263:"+intFacilityId);
			System.out.println("strFlatType:line263:"+strFlatType);
			pStatement.setInt(1, intFacilityId);
			pStatement.setString(2, handleNullForString(strFlatType));
			
			System.out.println("line 149:");
			rs = pStatement.executeQuery();
			int intFacilityMapCount = 0;
			while (rs.next()) 
			{
				ArrayList arrNoOfRooms = new ArrayList(); 
				arrNoOfRooms.add(rs.getString("S_NoOfRooms"));
				arrNoOfRooms.add(rs.getString("S_TowerNo"));
				arrNoOfRooms.add(rs.getString("N_NoOfUnits"));
				
				System.out.println("\n\n line 275:"+arrNoOfRooms);
				System.out.println("line 276:"+arrNoOfRooms);
				arrDataGivenFIDnFlatType.add(arrNoOfRooms);
			}
			System.out.println("line 279:"+arrDataGivenFIDnFlatType);
		}
		catch(Exception ex )
		{
			System.out.println("ex:"+ex.getCause());
		}
		finally 
		{
			try 
			{
				if (pStatement != null) 
				{
					pStatement.close();
					pStatement = null;
				}
				if (con != null) 
				{
					con.close();
					con = null;
				}
			}
			catch (SQLException exception2) 
			{
			}
		}
		return arrDataGivenFIDnFlatType; 
	}

//Added by Renuka on 26.09.2009
	public String saveFacility(DataSource  datasource, AddFacilityForm addFacilityBean) throws Exception 
	{
		System.out.println("\n\n\n In saveFacilityDAO");
		Connection con = null;
		PreparedStatement pStatement= null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String strReturnResult = "~";
		try 
		{
			con = datasource.getConnection();
			
			//int facilityId = facilityMapBean.getFacilityId();

			String sqlChkValidFacilityEntry = DBConstant.CHECK_FACILITY_ISEXIST;
			//"select count(*) as total from facilitymap where N_FacilityId = ? and S_FlatType = ? and S_NoOfRooms = ? and S_TowerNo = ?";
			pStatement = con.prepareStatement(sqlChkValidFacilityEntry);
			pStatement.setInt(1, Integer.parseInt(addFacilityBean.getBuilder()));
			pStatement.setString(2, handleNullForString(addFacilityBean.getFacilityName()));
			rs = pStatement.executeQuery();
			
			int intFacilityCount = 0;

			if (rs.next()) 
			{
				intFacilityCount = rs.getInt(1);
			}
			
			if(intFacilityCount == 0)
			{
				String sqlInsert = DBConstant.INSERT_FACILITY;
				pStatement = con.prepareStatement(sqlInsert);
				System.out.println("line 343:"+sqlInsert);
				//builder_id 1, facility_name 2 , facility_location 3, facility_city 4, facility_state 5 , 
				//facility_country 6, facility_zip 7
				pStatement.setInt(1, Integer.parseInt(addFacilityBean.getBuilder()));
				pStatement.setString(2, handleNullForString(addFacilityBean.getFacilityName()));
				pStatement.setString(3, handleNullForString(addFacilityBean.getAddress()));
				pStatement.setString(4, handleNullForString(addFacilityBean.getCollegeCity()));
				pStatement.setString(5, handleNullForString(addFacilityBean.getCollegeState()));
				pStatement.setInt(6, Integer.parseInt(addFacilityBean.getCollegeCountry()));
				pStatement.setInt(7, Integer.parseInt(addFacilityBean.getZipcode()));
				
				System.out.println("line 354:");
				int result = pStatement.executeUpdate();
				System.out.println("line 356:");	
				if(result == 1)
				{
					strReturnResult = "SuccessfullyAdded";
				}
				else
					strReturnResult = "UnableToAdded";
				
			}
			else if(intFacilityCount>0)
			{
				strReturnResult = "AlreadyExist";
			}
		}
		catch(Exception ex )
		{
			//intFacilityMapId = -1;
			strReturnResult = "InsertError";
			System.out.println("ex:"+ex);
		}
		finally 
		{
			try 
			{
				if (pStatement != null) 
				{
					pStatement.close();
					pStatement = null;
				}
				if (con != null) 
				{
					con.close();
					con = null;
				}
			}
			catch (SQLException exception2) 
			{
			}
		}
		System.out.println("line395:"+strReturnResult+":");
		return strReturnResult;
	}
	
	
	
	
	
	public ArrayList getBuilderName(DataSource ds) 
	{
		ArrayList arrBuilder = new ArrayList();
		Connection con = null;
		PreparedStatement pStatement= null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			con = ds.getConnection();
			System.out.println("\n\n\n In getFlatTypeGivenFID line412");
	
			String sqlGetFlatTypeGivenFID = "select builder_id, builder_name from builder order by builder_name";
			
			System.out.println("sqlChkValidFacilityMapEntry|:"+sqlGetFlatTypeGivenFID);
			//"select count(*) as total from facilitymap where N_FacilityId = ? and S_FlatType = ? and S_NoOfRooms = ? and S_TowerNo = ?";
			pStatement = con.prepareStatement(sqlGetFlatTypeGivenFID);
			System.out.println("line   147:");
			
			System.out.println("line 149:");
			rs = pStatement.executeQuery();
			int intFacilityMapCount = 0;
			while (rs.next()) 
			{
				ArrayList arrBuilderID_Name = new ArrayList();
				arrBuilderID_Name.add(rs.getString("builder_id"));
				arrBuilderID_Name.add(rs.getString("builder_name"));
				
				arrBuilder.add(arrBuilderID_Name);
			}
			System.out.println("line 154:"+arrBuilder);
		}
		catch(Exception ex )
		{
			System.out.println("ex:"+ex.getCause());
		}
		finally 
		{
			try 
			{
				if (pStatement != null) 
				{
					pStatement.close();
					pStatement = null;
				}
				if (con != null) 
				{
					con.close();
					con = null;
				}
			}
			catch (SQLException exception2) 
			{
			}
		}
		return arrBuilder;
		
		
	}

	public ArrayList getCountry(DataSource ds)
	{
		ArrayList arrCountry = new ArrayList();
		Connection con = null;
		PreparedStatement pStatement= null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			con = ds.getConnection();
			System.out.println("\n\n\n In getFlatTypeGivenFID line472");
	
			String sqlCountry = "select id, country_name from countries order by country_name;";
			
			System.out.println("sqlChkValidFacilityMapEntry|:"+sqlCountry);
			//"select count(*) as total from facilitymap where N_FacilityId = ? and S_FlatType = ? and S_NoOfRooms = ? and S_TowerNo = ?";
			pStatement = con.prepareStatement(sqlCountry);
			System.out.println("line   147:");
			System.out.println("line 149:");
			rs = pStatement.executeQuery();
			int intFacilityMapCount = 0;
			while (rs.next()) 
			{
				ArrayList arrCountryID_Name = new ArrayList();
				arrCountryID_Name.add(rs.getString("id"));
				arrCountryID_Name.add(rs.getString("country_name"));
				
				arrCountry.add(arrCountryID_Name);
			}
			System.out.println("line 154:"+arrCountry);
		}
		catch(Exception ex )
		{
			System.out.println("ex:"+ex.getCause());
		}
		finally 
		{
			try 
			{
				if (pStatement != null) 
				{
					pStatement.close();
					pStatement = null;
				}
				if (con != null) 
				{
					con.close();
					con = null;
				}
			}
			catch (SQLException exception2) 
			{
			}
		}
		return arrCountry;
		
		
	}

//added by Renuka 06.10.2009
	public ArrayList getBuilderDetailGivenFID(DataSource  datasource, int intFacilityId) throws Exception
	{
		ArrayList arrFlatType = new ArrayList();
		Connection con = null;
		PreparedStatement pStatement= null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			con = datasource.getConnection();
			System.out.println("\n\n\n In getBuilderDetailGivenFID line 534");
	
			String sqlGetFlatTypeGivenFID = DBConstant.GET_BUILDERDETAILS_GIVEN_FID;
			
			System.out.println("sqlChkValidFacilityMapEntry|:"+sqlGetFlatTypeGivenFID);
			//"select count(*) as total from facilitymap where N_FacilityId = ? and S_FlatType = ? and S_NoOfRooms = ? and S_TowerNo = ?";
			pStatement = con.prepareStatement(sqlGetFlatTypeGivenFID);
			System.out.println("line   147:");
			pStatement.setInt(1, intFacilityId);
			System.out.println("line 149:");
			rs = pStatement.executeQuery();
			int intFacilityMapCount = 0;
			while (rs.next()) 
			{
				arrFlatType.add(rs.getString("first_name"));//0
				arrFlatType.add(rs.getString("last_name"));//1
				arrFlatType.add(rs.getString("builder_name"));//2
				arrFlatType.add(rs.getString("facility_name"));//3
				arrFlatType.add(rs.getString("facility_location"));//4
			}
			System.out.println("line 154:"+arrFlatType);
		}
		catch(Exception ex )
		{
			System.out.println("ex:"+ex.getCause());
		}
		finally 
		{
			try 
			{
				if (pStatement != null) 
				{
					pStatement.close();
					pStatement = null;
				}
				if (con != null) 
				{
					con.close();
					con = null;
				}
			}
			catch (SQLException exception2) 
			{
			}
		}
		return arrFlatType;
	}
	
}
