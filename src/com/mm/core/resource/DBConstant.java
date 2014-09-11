package com.mm.core.resource;

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
	String SELECT_SUBCONTRACTOR_LIST ="SELECT * FROM subcon_id";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	String SELECT_ALL_SCHEDULES_SQL = "select A.sheduleid,A.shift_id,B.shift_name,A.schedule_date,A.staff_id,A.facilityId,C.facility_name,A.category_id,D.cat_name,case A.category_id when 1 THEN F.staffname else '--' END as electrician,case A.category_id when 2 THEN F.staffname else '--' END as pumpoperator,case A.category_id when 3 THEN F.staffname else '--' END as plumber,case A.category_id when 4 THEN F.staffname else '--' END as security from staff_schedule A,staff_shift_master B, facility C, staff_category D,staff_master F Where A.shift_id=B.shift_id AND A.facilityId=C.facility_Id AND A.category_id=D.cat_id AND A.staff_id=F.staff_id AND A.facilityId=? order by A.schedule_date,A.shift_id;";
	String INSERT_SHIFT_SCHEDULE_SQL="insert into staff_schedule(facilityId,schedule_date,shift_id,staff_id,category_id,cat_subID,enteredby,enteredon) values(?,?,?,?,?,?,?,CURDATE()) ON DUPLICATE KEY UPDATE modifiedby=?,modifiedon=CURDATE();";

	String SELECT_ALL_MEMBER_SQL = "select * from loginmaster;";
	String SELECT_MEMBER_UPDATE_SQL="select * from loginmaster where userid=?;";
	String UPDATE_MEMBER_SQL="update loginmaster set first_name=?,last_name=?,email=?,phone=?,mobile=?,address=?,zip=?,birthday=?,birthplace=?,maritalstatus=?,profession=? where userid=?;";
	
	String INACTIVE_LEAVEREQUEST_SQL = "update staff_leave_request set isActive=0 where leaveId=?";
	String SELECT_ALL_LEAVE_SQL = "select A.leaveId,A.staffId,B.staffname,A.leavetype,A.leavefromdate,A.leavetodate,A.no_ofdays from staff_leave_request A,staff_master B  WHERE A.staffId=B.staff_Id AND A.facilityId=? AND A.isActive=1;";
	String INSERT_STAFF_LEAVE_SQL="insert into staff_leave_request (staffId,facilityId,leavetype,leavefromdate,leavetodate,no_ofdays,eneteredby,enteredon) values (?,?,?,?,?,?,1,CURDATE());";
	String UPDATE_LEAVEREQUEST_SQL = "update staff_leave_request set leavetype=?,leavefromdate=?,leavetodate=?,no_ofdays=?, modifiedby=?,modifiedon=CURDATE() where leaveId=?;";

	String INSERT_SHIFT_MASTER_SQL="insert into staff_shift_master(shift_name,facility_id,shiftstarttime,shiftendtime,enteredby,enteredon)values(?,?,?,?,'Saneev',CURDATE());";
	String SELECT_ALL_SHIFT_SQL = "select A.shift_id,A.shift_name,A.facility_id,B.facility_name,A.shiftstarttime,A.shiftendtime from staff_shift_master A,facility B where A.facility_id=B.facility_id AND A.facility_Id=? AND A.isvalid=1 ;";


	String SELECT_ALL_STAFF_SQL = "select A.staff_id,A.staffname,A.facility_id,B.facility_name,A.salary,A.category,C.cat_name,A.subcategory ,D.subcat_name ,A.joining_date, A.address,A.contact_no,A.agencyname from staff_master A LEFT JOIN  (staff_category C,staff_subcategory D) ON (A.category=C.cat_id AND A.subcategory=D.subcat_id),facility B where A.facility_id=B.facility_id AND A.active='Y' AND A.facility_id=? order by A.staffname;";

	String SELECT_LEAVE_UPDATE_SQL="select A.leaveId,A.staffId,B.staffname,A.facilityId,A.leavetype,A.leavefromdate,A.leavetodate,A.no_ofdays from staff_leave_request A,staff_master B where A.staffid=B.staff_id AND A.leaveId=?;";


	String INSERT_STAFF_MASTER_SQL="insert into staff_master(staffname,facility_id,category,subcategory,joining_date,jobtype,salary,active,address,contact_no,agencyname,created_by,created_on) values(?,?,?,?,?,?,?,?,?,?,?,'1',CURDATE())";




	
	
	String SELECT_ALL_EXPENSE_SQL = "select expenseid,invoice_name,payable_to,payment_type,amount,expense_type,authorised_by,expense_date,expense_summary,status from expensedetail where facilityId=? AND isvalid=1;";
	String SELECT_EXPENSE_UPDATE_SQL = "select * from expensedetail where expenseid=?;";
	String INSERT_EXPENSE_DETAIL_SQL="insert into expensedetail (facilityId,invoice_name,payable_to,payment_type,amount,expense_type,authorised_by,expense_date,expense_summary,status,entered_by,entered_on) values(?,?,?,?,?,?,?,?,?,?,?,CURDATE());";
	String UPDATE_EXPENSES_SQL = "update expensedetail set invoice_name=?,payable_to=?,payment_type=?,amount=?,expense_type=?,authorised_by=?,expense_date=?,expense_summary=?,status=? where expenseid=?;";
	String INACTIVE_EXPENSE_SQL = "update expensedetail set isvalid=0 where expenseid=?";
	String GENERATE_RESIDENT_BILL="insert into consumerbilldetail(consumerid,serviceid,month,year,duedate,dueamount,createdby,createdon,modifiedby,modifiedon,uid) values (?,?,?,?,?,?,?,CURDATE(),?,CURDATE(),?);";
	String INSERT_RESIDENT_BILL_DETAIL_SQL="insert into consumer_bills (facilityid,serviceid,residentid,previousmeterreading,currentmeterreading,billstartdate,billenddate,createdby,createdon)  values(?,?,?,?,?,?,?,?,CURDATE());";
	String SELECT_RESIDENT_BILLGENERATE_SQL = "select A.billid,A.facilityid,B.facility_name,A.serviceid,C.servicename,C.monthlyfee,A.residentid,UPPER(CONCAT_WS('  ',D.first_name,D.last_name)) AS residentname,A.previousmeterreading,A.currentmeterreading,A.billstartdate,A.billenddate from consumer_bills A,facility B,service C,loginmaster D where A.facilityid=B.facility_id  AND A.serviceid=C.serviceid AND A.residentid=D.userid AND A.facilityid=1 AND A.residentid=? ;";
	
	String SELECT_STAFF_UPDATE_SQL = "select A.staff_id,A.staffname,A.facility_id,B.facility_name,A.category,C.cat_name,A.subcategory,D.subcat_name,A.joining_date,A.jobtype,A.salary,A.address,A.contact_no,A.agencyname from staff_master A LEFT JOIN  (staff_category C,staff_subcategory D) ON (A.category=C.cat_id AND A.subcategory=D.subcat_id) ,facility B Where  A.facility_id=B.facility_id AND staff_id=?";
	String UPDATE_STAFF_SQL = "update staff_master set staffname=?,category=?,subcategory=?,joining_date=?,jobtype=?,salary=?,address=?,contact_no=?,agencyname=? where staff_id=?;";
	String UPDATE_STAFF_PAYROLL_SQL = "insert into staff_payroll (staffid,year,month,leaves,salary_deducted,salary_paid,paymentmode,dateOfPayment,salary_status) values(?,?,?,?,?,?,?,?,?)";
	
	String SELECT_CONSUMER_BILL_DETAIL="select A.billid,A.facilityid,B.facility_name,A.serviceid,C.servicename,A.residentid,UPPER(CONCAT_WS('  ',D.first_name,D.last_name)) AS residentname,A.previousmeterreading,A.currentmeterreading,A.billstartdate,A.billenddate from consumer_bills A,facility B,service C,loginmaster D where A.facilityid=B.facility_id  AND A.serviceid=C.serviceid AND A.residentid=D.userid AND A.residentid NOT IN (select consumerid from consumerbilldetail where month='09' AND year=2009) AND A.facilityid=?;";

	String SELECT_PARKING_EDITALLOTMENT_SQL="select A.userid,CONCAT_WS('  ',A.first_name,A.last_name) as resident_name,A.status as residenttype,'Occupied' As resident_status,A.creationDate as membersince,B.vehicle,C.car_number,C.sticker_no,parking_no from loginmaster A LEFT JOIN  residentparkingmapping C ON (A.userid=C.residentid) ,resident_details B where A.userid=B.user_id AND A.B_IsValidated=1 AND A.B_IsSuspended=0 AND A.userid=?;";
	
	String SELECT_PARKING_ALLOTMENT_SQL="select A.userid,CONCAT_WS('  ',A.first_name,A.last_name) as resident_name,CONCAT_WS('  ',B.building,B.flat,B.tower) as address,A.status as residenttype,'Occupied' As resident_status,A.creationDate as membersince,B.vehicle,C.car_number,C.sticker_no,C.parking_no from loginmaster A LEFT JOIN  residentparkingmapping C ON (A.userid=C.residentid) ,resident_details B where A.userid=B.user_id  AND A.B_IsValidated=1 AND A.B_IsSuspended=0 AND A.college_id=?;";

	String SELECT_SERVICEFOR_ALLOTMENT_SQL = "select A.mappingid,A.serviceid,CONCAT_WS('  ',B.first_name,B.last_name) AS NAME,C.tower,C.flat,D.servicename,A.status from consumerservicemapping A , loginmaster B,resident_details C,service D Where A.consumerid=B.userid AND A.consumerid=C.user_id AND A.serviceid=D.serviceid  AND A.status=1;";
	String SELECT_SERVICE_ALLOTMENT_SQL="select A.mappingid,A.serviceid,A.consumerid,CONCAT_WS('  ',B.first_name,B.last_name) AS NAME,C.tower,C.flat,D.servicename,A.status from consumerservicemapping A , loginmaster B,resident_details C,service D Where A.consumerid=B.userid AND A.consumerid=C.user_id AND A.serviceid=D.serviceid  AND A.status=1 AND A.mappingid=? AND A.serviceid=?";
	String UPDATE_CONSUMER_SERVICE_MAPPING_ALLOTMENT_SQL="update consumerservicemapping set status=?,modifiedby=?,modifiedon=CURDATE(),remarks=? where mappingid=? AND consumerId=?";
	
	String UPDATE_CONSUMER_PARKING_MAPPING_ALLOTMENT_SQL="insert into residentparkingmapping(residentid,facilityid,car_number,sticker_no,parking_no,enteredby,enteredon) values(?,?,?,?,?,?,CURDATE()) ON DUPLICATE KEY UPDATE car_number=?, sticker_no=?,parking_no=?,modifiedby=?,modifiedon=CURDATE();";
	
	
	
									
	
	//String SELECT_ALL_SCHEDULES_SQL = "SELECT * from staff_schedule where isactive=1;";
	
	String SELECT_PAYROLL_SQL="select A.staff_id,A.staffname,A.facility_id,A.category,A.subcategory,A.jobtype,A.salary,A.joining_date,A.active,A.address,A.contact_no, '0' as noofleave,A.agencyname  from staff_master AS A where A.staff_id=?;";

	//String SELECT_SERVICE_SQL = "select * from service where serviceid=?";
	

	String SELECT_SHIFT_UPDATE_SQL = "select * from staff_shift_master where shift_id=?";
	
	
	
	
		
	String UPDATE_SHIFT_SQL = "update staff_shift_master set shift_name=?,shiftstarttime=?,shiftendtime=?,modifiedby=?,modifiedon=CURDATE() where shift_id=?;";

	//Added by Renuka start
	String INSERT_FACILITY_MAP = "INSERT into facilitymap(N_FacilityId, S_FlatType, S_NoOfRooms, S_TowerName, S_TowerNo, N_NoOfUnits, S_Area, DT_CreatedBy, N_CreatedBy, DT_ModifiedDate, N_ModifyBy, B_IsValid) VALUES(?, ?, ?, ?, ?, ?, ?, curDate(), ?, curDate(), ?, ?)";
	String CHECK_FACILITY_MAP_ISEXIST = "select count(*) as total from facilitymap where N_FacilityId = ? and S_FlatType = ? and S_NoOfRooms = ? and S_TowerNo = ?";
	String CHECK_FACILITY_MAP_EXIST_GIVEN_FACILITYID = "select count(*) as total from facilitymap where N_FacilityId = ?";
	String GET_FLATTYPE_GIVEN_FID = "select  distinct S_FlatType from facilitymap where N_FacilityId = ?";
	//String GET_ALL_NoOfROOM_GIVEN_FID_n_FLATTYPE = "select distinct S_NoOfRooms from facilitymap where N_FacilityId = ? and S_FlatType = ?";
	String GET_DATA_GIVEN_FID_n_FLATTYPE = "select S_NoOfRooms, S_TowerNo, N_NoOfUnits  from facilitymap where N_FacilityId = ? and S_FlatType = ?";
	
	String CHECK_FACILITY_ISEXIST ="select count(*) as total from facility where builder_id = ? and facility_name = ?";
	String INSERT_FACILITY = "insert into facility (facility_uid, builder_id, facility_name, facility_location, facility_city, facility_state, facility_country, facility_zip, company_id, active, no_flats, communityrights, date_of_creation, created_by) values (0, ?, ?, ?, ?, ?, ?, ?, 10, 1, 0, 1, curDate(), 1)";
	
	String INACTIVE_SHIFT_SQL = "update staff_shift_master set isvalid=0 where shift_id=?";
	String INACTIVE_STAFF_SQL = "update staff_master set active='N' where staff_id=? ";
	
	//String SELECT_ALL_SERVICE_SQL = "select s.*, getServiceConsumer(s.serviceid) as subsCount from service s where s.facilityid=? and s.active='y'";
	
	String SELECT_ALL_SERVICE_SQL = "select s.* from service s where s.facilityid=? and s.active='y'";

	String SELECT_SERVICE_SQL = "select * from service where serviceid=?";

	//String SELECT_CUSTOMER_SERVICES_SUBSCRIBED = "SELECT * FROM `service` where serviceid in(select serviceid from consumerservicemapping where consumerid= ?)";// " select * from consumermappingservice where customerId = ?";
	
	
	String SELECT_CUSTOMER_SERVICES_SUBSCRIBED = "Select s.*, csm.status  from consumer co , consumerservicemapping csm, service s "
												 + "where co.consumerid = csm.consumerid "
												 + " and csm.serviceid = s.serviceid "
												 + " and s.facilityid = co.facilityid "
												 + " and co.facilityid = ? "
												 + " and co.consumerid = ? ";
	
	String SEARCH_SERVICE_ASSIGNED = "Select s.*, csm.status  from consumer co , consumerservicemapping csm, service s "
		 + "where co.consumerid = csm.consumerid "
		 + " and csm.serviceid = s.serviceid "
		 + " and s.facilityid = co.facilityid "
		 + " and co.facilityid = ? "
		 + " and co.consumerid = ? ";
	
	String SELECT_RESIDENT_SERVICE_SQL = "SELECT * FROM service s ,consumerservicemapping m "
			+ "where s.serviceid=m.serviceid 	" + "and   m.consumerid=?";

	//Updated by Renuka 091092009 start
	String INSERT_SERVICE_SQL = "insert into service(servicename,servicedesc,monthlyfee,subscription,"
			+ "facilityname,facilityid,createdby,createdon,modifiedby,is_mendetory,taxes,cess) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?)";
	//Updated by Renuka 091092009 stop
	String UPDATE_SERVICE_SQL = "update service set servicename=?,servicedesc=?,monthlyfee=?,subscription=?,"
			+ "facilityname=?,facilityid=?,modifiedby=?,is_mendetory=? where serviceid=?";

	String DELETE_SERVICE_SQL = "delete from service where serviceid=? ";

	String INACTIVE_SERVICE_SQL = "update service set active='n' where serviceid=? ";

	String INSERT_CONSUMER_SERVICE_MAPPING_SQL = "insert into consumerservicemapping(consumerid,serviceid,datefrom,dateto,"
			+ "createdby,createdon,modifiedby, status) " + "values (?,?,?,?,?,?,?,?)";

	String DELETE_CONSUMER_SERVICE_MAPPING_SQL = "delete from consumerservicemapping where consumerid=?";

	String UPDATE_CONSUMER_SERVICE_MAPPING_SQL = "update consumerservicemapping set dateto=?,"
			+ "modifiedby=?,modifiedon=? where consumerid=? and serviceid=?";

	String INSERT_CONSUMER_BILL_DETAIL_SQL = "insert into consumerbilldetail(consumerid,serviceid,month,year,duedate,dueamount,"
			+ "createdby,createdon,modifiedby,modifiedon,uid) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?)";
	String INSERT_CONSUMER_BILL_SQL = "insert into consumerbills(consumerid,month,year,payableamt,paidamt,duedate,remarks,"
			+ "paiddate,facilityid) "
			+ "values (?,?,?,?,?,?,?,?,?)";
	String INSERT_CONSUMER_COMPOUND_BILL_DETAIL_SQL = "insert into consumercompoundbill(consumerid,month,year,totalamount,paidamount,paymentmode,"
			+ "createdby,createdon,modifiedby,modifiedon,uid) "
			+ "values (?,?,?,?,?,?,?,?,?,?,?)";
	String UPDATE_CONSUMER_BILL_DETAIL_SQL = "update consumercompoundbill"
			+ " set paidamount =? ,paymentmode = ?,"
			+ "where consumerid= ? and month=? and year = ?";

	String GENERATE_CONSUMER_BILL_SQL = "select * from ("
			+ "select s.serviceid,s.servicename,s.servicedesc,s.monthlyfee,s.taxes,s.cess, "
			+ "m.dateto,m.datefrom,m.consumerid "
			+ "from service s, "
			+ "consumerservicemapping m where "
			+ " m.serviceid=s.serviceid and m.dateto is null "
			+ " union "
			+ "select s.serviceid,s.servicename,s.servicedesc,s.monthlyfee,s.taxes,s.cess,"
			+ "m.dateto,m.datefrom,m.consumerid " + " from service s, "
			+ "consumerservicemapping m where "
			+ " m.serviceid=s.serviceid and "
			+ "MONTH(m.dateto)=? and  YEAR(m.dateto)=?"
			+ ") as c order by consumerid";

	String GENERATE_CONSUMER_BILL_SQL_2 = "select * from ("
			+ "select s.serviceid,s.servicename,s.servicedesc,s.monthlyfee,s.taxes,s.taxes,"
			+ "m.dateto,m.datefrom,m.consumerid,Day(m.datefrom) as fromday,"
			+ "Month(m.datefrom) as frommonth,year(m.datefrom) as fromyear,Day(m.dateto) as datetoday,"
			+ "Month(m.dateto) as datetomonth,year(m.dateto) as datetoyear from service s, "
			+ "consumerservicemapping m where "
			+ " m.serviceid=s.serviceid and m.dateto is null"
			+ "union "
			+ "select s.serviceid,s.servicename,s.servicedesc,s.monthlyfee,s.taxes,s.taxes,"
			+ "m.dateto,m.datefrom,m.consumerid,Day(m.datefrom) as fromday,"
			+ "Month(m.datefrom) as frommonth,year(m.datefrom) as fromyear,Day(m.dateto) as datetoday,"
			+ "Month(m.dateto) as datetomonth,year(m.dateto) as datetoyear from service s, "
			+ "consumerservicemapping m where "
			+ " m.serviceid=s.serviceid and "
			+ "MONTH(m.dateto)=? and  YEAR(m.dateto)=?"
			+ ") c order by consumerid";

	String SELECT_ALL_CONSUMER_SQL = "select a.*,GROUP_CONCAT(servicename )as service from consumer a,consumerservicemapping b,service c where a.facilityid=? and a.consumerid=b.consumerid and  c.serviceid=b.serviceid and a.active='y' group by a.consumerid";

	String SELECT_CONSUMER_SQL = "select * from consumer where consumerid=?";

	String INSERT_CONSUMER_SQL = "insert into consumer (firstname ,lastname,dateofbirth,address,phone,gender,"
			+ "emailid,facilityid,createdby ,createdon ,modifiedby )VALUES "
			+ "(?,?,?,?,?,?,?,?,?,?,?)";

	String REGISTER_USER_SQL_BackUp = "insert into consumer(firstname ,lastname,address,gender,facilityid)VALUES "
			+ "(?,?,?,?,?)";
	String REGISTER_USER_SQL = "insert into consumer(username,firstname ,lastname,address,gender,marriagestatus,facilityid,createdon)VALUES "
			+ "(?,?,?,?,?,?,?,current_timestamp())";

	String UPDATE_CONSUMER_SQL = "update consumer set firstname=? ,lastname=?,dateofbirth=?,address=?,phone=?,gender=?,"
			+ "emailid=?,modifiedby=?  where consumerid=?";

	String DELETE_CONSUMER_SQL = "delete from consumer where consumerid=?";

	String INACTIVE_CONSUMER_SQL = "update consumer set active='n' where consumerid=?";

	String SEARCH_SERVICE_UNASSIGNED = "select * from service where facilityid=? and active='y' and serviceid not "
			+ "in(select serviceid from consumerservicemapping where  consumerid=?)";
	
//	String SEARCH_SERVICE_ASSIGNED = "select * from service where facilityid=? and active='y' and serviceid "
//			+ "in(select serviceid from consumerservicemapping where  consumerid=?)";

	String DELETE_SERVICE_ASSIGNED = "delete from consumerservicemapping where  consumerid=?";

//	String SELECT_COMPOUND_BILL = "SELECT cb.*,c.firstname,c.lastname FROM consumercompoundbill cb ,"
//			+ "consumer c WHERE cb.consumerid in(select consumerid from consumer "
//			+ "where facilityid=?) and cb.year=? and cb.month=? and "
//			+ "cb.consumerid=c.consumerid";
	
	String SELECT_COMPOUND_BILL = "Select cb.billid, cb.consumerid, cb.month, cb.year, cb.payableamt, cb.paidamt, cb.duedate, cb.remarks, cb.paiddate, cb.facilityid, c.first_name, c.last_name "
	+ " from consumerbills cb, resident_details r, loginmaster c"
	+ " where cb.consumerid = r.user_id and r.user_id=c.userid"
	+ " and cb.facilityid=?"
	+ " and cb.year=?"
	+ " and cb.month=?";

	String SELECT_DETAILED_BILL = "SELECT c.*,cc.firstname,cc.lastname,cc.address,cc.phone,s.servicename "
			+ "from consumerbilldetail c,consumer cc ,service s where c.uid=? and "
			+ "c.consumerid=cc.consumerid and c.serviceid=s.serviceid";

//	String SELECT_DETAILED_BILL_2 = "SELECT c.*,cc.firstname,cc.lastname,cc.address,cc.phone,s.servicename 	"
//			+ "from consumerbilldetail c,consumer cc ,service s 	"
//			+ "where c.month=? 	and c.year=? 	and c.consumerid=? 	"
//			+ "and c.consumerid=cc.consumerid 	and c.serviceid=s.serviceid";
	
	String SELECT_DETAILED_BILL_2 = " Select bd.consumerid, bd.serviceid, bd.month, bd.year, bd.dueamount, bd.dueDate, se.servicename, co.first_name, co.last_name, co.address, co.phone " 
	+ " from consumerbilldetail bd, loginmaster co, service se"
	+ " where bd.month = ? "
	+ " and bd.year = ? "
	+ " and bd.consumerid = ? "
	+ " and bd.consumerid = co.userid"
	+ " and bd.serviceid = se.serviceid";

	String INSERT_LOGIN_SQL = "insert into login(username ,password,usertype)VALUES "
			+ "(?,?,?)";

	String VERIFY_LOGIN_SQL = "select l.usertype,c.* "
			+ "from login l,consumer c 	"
			+ "where l.username=? and l.password=? and l.usertype=?  "
			+ "and l.username=c.username";

	
	String ALL_CONSUMER_BY_FACILITY = "Select c.consumerid from consumer c, login l " 
										+ " where facilityId = ? "
										+ " and c.username = l.username "
										+ " and l.usertype = 'Resident' ";	

//	aDDED BY renuka 06.10.2009
	String GET_BUILDERDETAILS_GIVEN_FID = "select f.facility_id,  lm.first_name first_name, lm.last_name last_name, b.builder_name builder_name, f.facility_name facility_name, f.facility_location facility_location "
		+" from loginmaster lm, builder b, facility f" 
	    +" where lm.login_type = 'Corporates' "
	    +" and lm.companyid = b.builder_id"
	    +" and lm.college_id = f.facility_id"
	    +" and f.facility_id = ?";	
	
	//Added by Renuka 12102009 start
	String SELECT_ALL_SCHEDULEDATE_GIVEN_FID = "select distinct schedule_date from staff_schedule where facilityId = ? and isvalid=1 order by (str_to_date(schedule_date, '%d/%m/%Y'))";
	String SELECT_ALL_SHIFTS_GIVEN_FID_n_SCHEDULEID = "select distinct ss.shift_id shift_id, ssm.shift_name shift_name from staff_schedule ss inner join staff_shift_master ssm on ssm.shift_id = ss.shift_id  where ss.facilityId = ? and ss.schedule_date = ? and ss.isvalid=1 and ssm.isvalid=1 order by shift_name";
	String SELECT_ALL_CATEGORY = "select distinct cat_id, cat_name from staff_category order by cat_id"; 
	String SELECT_ALL_SHIFTS_GIVEN_FID_n_SCHEDULEDATE_SHIFTID_CATID = "select distinct ss.sheduleid schedule_id, sm.staffname staff_name from staff_schedule ss inner join staff_master sm on sm.staff_id = ss.staff_id where facilityId = ? and schedule_date = ? and shift_id=? and sm.category=? and ss.isvalid=1 and sm.active='Y' order by staff_name";
	
	//Added by Re Renuka 21102009
	String CHANGE_STATUS_CONSUMERSERVICEMAPPING_SQL = "update consumerservicemapping set STATUS = 3 where serviceid = ? ";
}
