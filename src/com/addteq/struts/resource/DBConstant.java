package com.addteq.struts.resource;

public interface DBConstant {
	
	String SELECT_INSERT_CONS_INFO ="INSERT INTO consultant(firstname,lastname,address1,address2,city,state,zip,email,phone,start_date) values(?,?,?,?,?,?,?,?,?,?)";
	String SELECT_MAX_CID ="SELECT max(cid) FROM consultant";
	String INSERT_WORKORDER_INFO ="INSERT INTO workorder(cid,vid,rate,cost,billing_type,subcon,start_date,timesheet_type,invoicing_type) values(?,?,?,?,?,?,?,?,?)";
	String SELECT_CON_LIST ="SELECT * FROM consultant where closed='N'";
	String SELECT_VEN_LIST = "SELECT distinct vid, company FROM vendor_id";
	String DELETE_CON_INFO = "UPDATE consultant  SET Closed='Y' WHERE cid=?";
	String SELECT_CON_INFO = "SELECT * FROM consultant WHERE cid=?";
	String SELECT_CON_INFO_NEXT ="SELECT * FROM workorder WHERE  cid=?";
	String UPDATE_CON_INFO = "UPDATE consultant SET firstName=?,lastName=?, address1=?, address2=?, city=?, state=?, zip=?, email=?, phone=?, start_date=? WHERE Cid=?";
	String SELECT_INSERT_COM_INFO = "INSERT INTO cm_earner(earner_id, wid, table_id, amount, start_date, end_date) values(?,?,?,?,?,?)";
	String SELECT_COM_LIST = "SELECT * FROM cm_earner";
	String DELETE_COM_INFO ="DELETE FROM cm_earner where id=?";
	String SELECT_COM_INFO ="SELECT * FROM cm_earner where id=?";
	String SELECT_SUBCON_LIST = "SELECT scid, company from subcon_id";
	String CONTRIBUTOR = "SELECT A.wid, B.firstNAme,B.lastName FROM workorder A, consultant B WHERE A.cid = B.cid AND A.end_date IS NULL";

	String INSERT_VENDOR="INSERT INTO vendor_id(COMPANY,ADDRESS,ADDRESS2,CITY,STATE,ZIP,timestamp) VALUES(?,?,?,?,?,?,NOW())";
	
	String INSERT_SUBCON="INSERT INTO subcon_id(COMPANY,ADDRESS,ADDRESS2,CITY,STATE,ZIP,EIN,timestamp) VALUES(?,?,?,?,?,?,?,NOW())";

	String RETRIEVE_INVOICE_REPORT ="SELECT firstname, lastname, period_start_date, period_end_date, company, total FROM invoice_view WHERE vid=? AND period_start_date >=? AND period_end_date <=?";
	String RETRIEVE_INVOICE_REPORT2 ="SELECT * FROM invoice_view";
	String RETRIEVE_CATEGORY_EXPENSE_LIST ="select category, sum(amount), date, type From expense_report GROUP BY Category HAVING date LIKE CONCAT(?,'%')";
	String RETRIEVE_CATEGORY_EXPENSE_DETAIL_LIST ="SELECT * FROM expense_report Where type=?";
	String RETRIEVE_EXPENSE_SEARCH ="SELECT * FROM expense_report WHERE description like CONCAT('%', ? ,'%')";
	String SELECT_VENDOR_LIST ="SELECT * FROM Vendor_id";
	
}
