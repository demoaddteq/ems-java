package com.mm.core.master;

import com.mm.struts.corpo.form.ExpenseForm;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.struts.upload.FormFile;

import javax.sql.DataSource;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Vector;
import java.util.StringTokenizer;

public class ExpenseDAO {
	
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

	
	public Vector getExpenses(DataSource datasource, String str, String str2){
		
		Vector vector = new Vector();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String qDate = "SELECT MAX(DATE) FROM YEARLY_EXPENSE";
		String qDateFirst = "SELECT MIN(DATE) FROM YEARLY_EXPENSE";
		String query = "SELECT DATE, CATEGORY, DESCRIPTION, AMOUNT FROM EXPENSE_REPORT WHERE YEAR(DATE)=? AND MONTH(DATE)=? ORDER BY DATE ASC";
		StringTokenizer st;
		String y=null;
		String m=null;
		
		try{
			con = datasource.getConnection();
			
			if (str.equals("last")){
				
				ps = con.prepareStatement(qDate);
				rs = ps.executeQuery();
					while (rs.next()) {
							st = new StringTokenizer(rs.getString("max(date)"),"-");
							y = st.nextToken();
							m = st.nextToken();
					}
			}
			
			else if (str.equals("first")){
				
				logging.info("Inside DAO; inside the else if for 'first'");
				ps = con.prepareStatement(qDateFirst);
				rs = ps.executeQuery();
					while (rs.next()) {
							st = new StringTokenizer(rs.getString("min(date)"),"-");
							y = st.nextToken();
							m = st.nextToken();
					}
					logging.info("y/m-->"+y+"/"+m);
			} 
				
			else if (str.equals("previous") || str.equals("next") || str.equals("current")){

				StringTokenizer sToken2 = new StringTokenizer(str2, "/");
				
				m = sToken2.nextToken();
				String d = sToken2.nextToken();
				y = sToken2.nextToken();
				logging.info("m/y-->"+m+"/"+y);
			}
			
			ps = con.prepareStatement(query);
			ps.setString(1, y);
			ps.setString(2, m);
			
			rs = ps.executeQuery();
			while(rs.next()){
				
				ExpenseForm form = new ExpenseForm();
				
				StringTokenizer tokenizer = new StringTokenizer(rs.getString("date"), "-");
				String yr = tokenizer.nextToken();
				String mn = tokenizer.nextToken();
				String dy = tokenizer.nextToken();
				
				form.setExpDate(mn+"/"+dy+"/"+yr);
				form.setCategory(rs.getString("category"));
				form.setDescription(rs.getString("description"));
				form.setAmount(rs.getFloat("amount"));
				
				vector.add(form);
				
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

	public Vector getCategory(DataSource datasource){
		
		Vector vector = new Vector();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM EXPENSE_TYPE ORDER BY CATEGORY ASC";
		StringTokenizer st;
		
		try{
			con = datasource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				String aRow = rs.getInt("type")+"##"+rs.getString("category");
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
	
	public void addExpense(DataSource datasource, ExpenseForm form){
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "INSERT INTO YEARLY_EXPENSE (DATE, AMOUNT, DESCRIPTION, TYPE, EXTENSION, UID) VALUES (?,?,?,?,?,?)";
		String getId = "SELECT ID FROM YEARLY_EXPENSE WHERE DATE=? AND AMOUNT=? AND DESCRIPTION=? AND TYPE=?";
		String uploadFile = "INSERT INTO PDFFILE (TS_ID, MODE, CONTENT) VALUES (?,?,?)";
		StringTokenizer st;
		FormFile theFile=form.getTheFile();
		
		String contentType=theFile.getContentType();
		String fileName=theFile.getFileName();
		int fileSize=theFile.getFileSize();
		String extension = null;
		String uid = new String("akjha");
		
		if(fileSize!=0){
			StringTokenizer st0 = new StringTokenizer(fileName, ".");
		
			while(st0.hasMoreTokens()){
			
				extension = st0.nextToken();
			}
		
		}
		
		logging.info("contextType-->"+contentType);
		logging.info("fileName-->"+fileName);
		logging.info("fileSize-->"+fileSize);
		logging.info("file Extension-->"+extension);
		
		try{
			StringTokenizer tokenizer = new StringTokenizer(form.getExpDate(), "/");
			String m = tokenizer.nextToken();
			String d = tokenizer.nextToken();
			String y = tokenizer.nextToken();
			
			con = datasource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, y+"-"+m+"-"+d);
			ps.setFloat(2, form.getAmount());
			ps.setString(3, form.getDescription());
			ps.setInt(4, form.getType());
			ps.setString(5, extension);
			ps.setString(6, uid);
			
			int test = ps.executeUpdate();
			logging.info("Insert Expense Operation. Result-->"+test);
			
			// get id from above transaction and insert file in PDFFILE
			if(fileSize!=0){

				ps = con.prepareStatement(getId);
				ps.setString(1, y+"-"+m+"-"+d);
				ps.setFloat(2,form.getAmount());
				ps.setString(3,form.getDescription());
				ps.setInt(4,form.getType());
				rs=ps.executeQuery();
				int id=0;
			
				while(rs.next()){ id = rs.getInt("id");}
			
				logging.info("Expense id-->"+id);
			
				// attach file to the table
				byte [] fileData=theFile.getFileData();
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
					ps = con.prepareStatement(uploadFile);
					ps.setInt(1, id);
					ps.setString(2, "E");
					ps.setString(3,hexStream);
					//ps.setAsciiStream(2, stream, fileData.length);
					
					// ps.setString(2,fileContent);
					test = ps.executeUpdate();
					logging.info("Attach File (modulus=0) test-->"+test);
					
				}
				
				else {
									
					for(int x=0; x<parts;x++){
					
						StringBuffer sb = new StringBuffer();

						// byte partFileData[] = new byte[3072];
											
						// int z =0;
						for(int z=x*3072;z<(x+1)*3072;z++){
							 sb.append(hexStream.charAt(z));
							// partFileData[z]=fileData[y];
							// z++;
						}
						// InputStream stream = new ByteArrayInputStream(partFileData);
						String fileContent = sb.toString();
						ps = con.prepareStatement(uploadFile);
						ps.setInt(1, id);
						ps.setString(2, "E");
						// ps.setAsciiStream(2, stream, partFileData.length);
						ps.setString(3,fileContent);
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
					
						for(int z=3072*parts;z<hexStream.length();z++){
							sb.append(hexStream.charAt(z));
							// partFileData[z]=fileData[z];
							// z++;
						}
					
						// InputStream stream = new ByteArrayInputStream(partFileData);
						String fileContent = sb.toString();
						ps = con.prepareStatement(uploadFile);
						ps.setInt(1, id);
						ps.setString(2, "E");
						// ps.setAsciiStream(2, stream, partFileData.length);
						ps.setString(3,fileContent);
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
}
