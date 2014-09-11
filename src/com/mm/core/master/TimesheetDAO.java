package com.mm.core.master;

import com.mm.struts.corpo.form.TimesheetForm;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.struts.upload.FormFile;

import java.util.Vector;
import java.util.Calendar;
// import java.io.InputStream;
// import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;


import java.util.StringTokenizer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.sql.DataSource;



public class TimesheetDAO {
	
	protected final Log logging = LogFactory.getLog(getClass());
	
	static final byte[] HEX_CHAR_TABLE = {
		    (byte)'0', (byte)'1', (byte)'2', (byte)'3',
		    (byte)'4', (byte)'5', (byte)'6', (byte)'7',
		    (byte)'8', (byte)'9', (byte)'a', (byte)'b',
		    (byte)'c', (byte)'d', (byte)'e', (byte)'f'
	 };    

	public static String getHexString(byte[] raw) 
		    throws UnsupportedEncodingException 
		  {
		    byte[] hex = new byte[2 * raw.length];
		    int index = 0;

		    for (byte b : raw) {
		      int v = b & 0xFF;
		      hex[index++] = HEX_CHAR_TABLE[v >>> 4];
		      hex[index++] = HEX_CHAR_TABLE[v & 0xF];
		    }
		    return new String(hex, "ASCII");
	}
	
	
	public void addSheet(DataSource datasource, TimesheetForm form){
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		
		String query = "INSERT INTO TIMESHEET (WID, BILLABLE_HOURS,PERIOD_START_DATE, PERIOD_END_DATE, EXTENSION) VALUES (?,?,?,?,?)";
		String attachQuery ="INSERT INTO PDFFILE (TS_ID, CONTENT) VALUES (?,?)";
		String getTsIdQuery ="SELECT TS_ID FROM TIMESHEET WHERE WID=? AND BILLABLE_HOURS=? AND PERIOD_START_DATE=? AND PERIOD_END_DATE=?";
		
		int wid=form.getWid();
		int hours = form.getHours();
		FormFile myFile=form.getTheFile();
		int fileSize=myFile.getFileSize();
		logging.info("fileSize-->"+fileSize);
		
		String contentType=null;
		String fileName=null; 
		
		String extension = null;
		
		
		// get file extension
		if(fileSize!=0){
		
			contentType=myFile.getContentType();
			fileName=myFile.getFileName();
			StringTokenizer st0 = new StringTokenizer(fileName, ".");
		
			while(st0.hasMoreTokens()){
			
				extension = st0.nextToken();
			}
		
		}

		StringTokenizer st1 = new StringTokenizer(form.getFromDate(), "/");
		String month1 = st1.nextToken();
		String day1 = st1.nextToken();
		String year1 = st1.nextToken();
		String startDate = year1+"-"+month1+"-"+day1; 
		
		StringTokenizer st2 = new StringTokenizer(form.getToDate(), "/");
		String month2 = st2.nextToken();
		String day2 = st2.nextToken();
		String year2 = st2.nextToken();
		String endDate = year2+"-"+month2+"-"+day2;
		
		try{
			
			con = datasource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, wid);
			ps.setInt(2, hours);
			ps.setString(3, startDate);
			ps.setString(4, endDate);
			ps.setString(5, extension);
			int test = ps.executeUpdate();
			
			logging.info("Insert test-->"+test);
			
			
			// get ts_id from the above transaction for attachment insertion
			
			if(fileSize!=0){
				

				logging.info("contextType-->"+contentType);
				logging.info("fileName-->"+fileName);
				logging.info("file extension-->"+extension);
			
				ps = con.prepareStatement(getTsIdQuery);
				ps.setInt(1, wid);
				ps.setInt(2,hours);
				ps.setString(3,startDate);
				ps.setString(4,endDate);
				rs=ps.executeQuery();
				int tsId=0;
			
				while(rs.next()){ tsId = rs.getInt("ts_id");}
			
				logging.info("ts_id-->"+tsId);
			
				// attach file to the table
				byte [] fileData=myFile.getFileData();
				logging.info("byte[].length-->"+fileData.length);
			
				String hexStream = TimesheetDAO.getHexString(fileData);
				
				int parts = hexStream.length() / 3072;
				logging.info("hexStream.length()-->"+hexStream.length());
				logging.info("hexStream.length() / 3072 -->"+parts);
				
				if (parts==0){
					
					/*
					StringBuffer sb = new StringBuffer();
					for(int x=0; x<fileData.length; x++){
						sb.append(fileData[x]);
					}
					
					*/
					// InputStream stream = new ByteArrayInputStream(fileData);
					// String fileContent = sb.toString();
					ps = con.prepareStatement(attachQuery);
					ps.setInt(1, tsId);
					//ps.setAsciiStream(2, stream, fileData.length);
					ps.setString(2, hexStream);
					
					
					// ps.setString(2,fileContent);
					test = ps.executeUpdate();
					logging.info("Attach File (modulus=0) test-->"+test);
					
				}
				
				else {
									
					for(int x=0; x<parts;x++){
					
						StringBuffer sb = new StringBuffer();

						// byte partFileData[] = new byte[3072];
											
						// int z =0;
						for(int y=x*3072;y<(x+1)*3072;y++){
							 sb.append(hexStream.charAt(y));
							// partFileData[z]=fileData[y];
							// z++;
						}
						// InputStream stream = new ByteArrayInputStream(partFileData);
						String fileContent = sb.toString();
						ps = con.prepareStatement(attachQuery);
						ps.setInt(1, tsId);
						// ps.setAsciiStream(2, stream, partFileData.length);
						ps.setString(2,fileContent);
						test = ps.executeUpdate();
						logging.info("File part no-->"+x);
						logging.info("Attach Part of File. Result-->"+test);
						// sb.delete(0, sb.length());
						
					}
					
					// attach remaining part of file (which is less than 3072) if it exists
					
					if((hexStream.length()%3072)!=0){
						StringBuffer sb = new StringBuffer();
						// byte[] partFileData = new byte[3072];
						// int z =0;
					
						for(int y=3072*parts;y<hexStream.length();y++){
							sb.append(hexStream.charAt(y));
							// partFileData[z]=fileData[y];
							// z++;
						}
					
						// InputStream stream = new ByteArrayInputStream(partFileData);
						String fileContent = sb.toString();
						ps = con.prepareStatement(attachQuery);
						ps.setInt(1, tsId);
						// ps.setAsciiStream(2, stream, partFileData.length);
						ps.setString(2,fileContent);
						test = ps.executeUpdate();
					
						logging.info("Attach Last Part of File. Result-->"+test);
					
					}
					
				}
						
							
			}
			
		}catch (Exception e){
			
			e.printStackTrace();
		}finally { 
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
	
	public String getDate(DataSource datasource, int wid){
		
		logging.info("Inside DAO. wid-->"+wid);
		String endDate = new String();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT MAX(PERIOD_END_DATE) FROM TIMESHEET WHERE WID=?";
		String newConsultant = "SELECT START_DATE FROM WORKORDER WHERE WID=?";
		
		String DATE_FORMAT = "MM/dd/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance(); 
		Calendar c2 = Calendar.getInstance();
		
			try{
			
				con = datasource.getConnection();
				ps = con.prepareStatement(query);
				ps.setInt(1, wid);
				rs = ps.executeQuery();
				
				while(rs.next()){
								
					endDate=rs.getString("max(period_end_date)");
				
					if (endDate==null) { 
						logging.info("No period_end_date found in TIMESHEET");
						ps = con.prepareStatement(newConsultant);
						ps.setInt(1,wid);
						rs = ps.executeQuery();
					
						while(rs.next()){
							endDate = rs.getString("start_date");
							logging.info("workorder start_date-->"+endDate);
						}
					}
					
					else{
						
						logging.info("Found period_end_date in timesheet-->"+endDate);
					}
				}
			
				StringTokenizer st = new StringTokenizer(endDate, "-");
				String year = st.nextToken();
				String month = st.nextToken();
				String day = st.nextToken();
				
				int y = Integer.parseInt(year);
				int m = Integer.parseInt(month);
				int d = Integer.parseInt(day);
				c1.set(y,m-1,d); 
				
				logging.info("Period_End_Date is : " + sdf.format(c1.getTime()));
				c1.add(Calendar.DATE, 1);
				c2.setTime(c1.getTime());
				c2.add(Calendar.DATE, 6);
				logging.info("Period_End_Date + 1 day is : " + sdf.format(c1.getTime()));
				logging.info("Period_End_Date + 7 day is : " + sdf.format(c2.getTime()));
			
		}catch (Exception e){
			
			e.printStackTrace();
		}finally { 
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
		

		
		return sdf.format(c1.getTime())+"##"+sdf.format(c2.getTime());
	}
	
	public Vector getFullNames(DataSource datasource){
		
		Vector vector=new Vector();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT W.WID, C.FIRSTNAME, C.LASTNAME "+
					   "FROM WORKORDER W, CONSULTANT C "+
					   "WHERE W.CID=C.CID AND W.END_DATE IS NULL AND C.CLOSED='N' "+
					   "ORDER BY C.FIRSTNAME ASC";
				
		logging.info("Inside timeDAO. query -->"+query);
		
		
		
		try{
			
			con = datasource.getConnection();
			if (con==null) logging.info("Connection is NULL");
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs==null) logging.info("ResultSet is NULL");
					
			while(rs.next()){
				String aRow = rs.getInt("wid")+","+rs.getString("firstname")+" "+rs.getString("lastname");
				logging.info("Full name-->"+aRow);
				vector.add(aRow);
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}finally { 
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
		
		return vector;
	}

}
