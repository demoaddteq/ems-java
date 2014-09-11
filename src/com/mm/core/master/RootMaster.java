/**
 * 
 */
package com.mm.core.master;
import java.util.Vector;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.TimeZone;
import java.util.Calendar;

import com.mm.core.resource.*;

/**
 * @author Manoj Kumar Jha Date 9/5/2007  
 */
/**
 * This class is use for performing all function of ROOT. it creats a
 * MasterUtility class object for performing basic operation upon the Data Base.
 */
public class RootMaster {

	
	MasterUtility masterUtil = new MasterUtility();

	public int checkAvailability(DataSource ds, String strLId)
	{
		int numResult = 0;
		String strQuery="Select * from loginmaster where loginname='"+strLId+"'";
		numResult = masterUtil.getCount(ds, strQuery);		
		return numResult;
	}
	/**
	 * Activate user for new user 
	 */
	public String activateUser(DataSource ds, String uName, String modifyDate, String modifyTime)
	{
		String result = "";
		Support support=new Support();
		
		
		
		String strQuery="Select userid from loginmaster where loginname='"+uName+"'";
		support.setFieldVec("string", "userid");
		Vector tempresultVec = (Vector) masterUtil.getDetail(ds, strQuery, support);
		
		
		
		if(tempresultVec.size() == 0)
		{
			result="failure";
		}
		else
		{
			strQuery = "Update loginmaster set logindeletion=?, activDate=?, activTime=? where loginname=?";
			support.setDataVec("int", "0");			
			support.setDataVec("string", modifyDate);
			support.setDataVec("string", modifyTime);
			support.setDataVec("string", uName);
			result = masterUtil.setData(ds, strQuery, support);
			
			  ////System.out.println("result.result....."+result);
		}
		return result;
	}

	/**
	 * Complaint related function written here.
	 */
	/**
	 * @param compId
	 *            defines perticauler complaint Id .
	 * @return Detail vector of complaint.
	 */
	/**
	 * this function is use for verify user login id and password
	 */
	
	public Vector verifyUser(DataSource ds, Vector dataVec)
	{
		Support support=new Support();
		Vector<String> resultVec = new Vector<String>();
		
		int companyStatus = 1;
		String strQuery="";
		String compType = "";
		String loginId= dataVec.elementAt(0).toString();
		String password = dataVec.elementAt(1).toString();
		String strCompType = dataVec.elementAt(2).toString();
		if(strCompType.equalsIgnoreCase("indv"))
		{
			strCompType="Consumer";
		}
		else if(strCompType.equalsIgnoreCase("advt"))
		{
			strCompType="Advertiser";
		}
		else if(strCompType.equalsIgnoreCase("corpo"))
		{
			strCompType="Corporates";
		}
		else
		{
			strCompType="Enterprise";
		}
		strQuery = "select userid ,companyid, password from loginmaster where loginname='"+loginId+"' and password='"+password+"'";
		support.setFieldVec("string", "userid");
		support.setFieldVec("string", "companyid");
		support.setFieldVec("string", "password");
		
		Vector tempVec1 = (Vector) masterUtil.getDetail(ds, strQuery, support);
		Vector tempresultVec = new Vector();
		
		
		if(tempVec1.size() != 0)
		{
			if(password.equals(tempVec1.elementAt(2))){
				
				tempresultVec = tempVec1;
				
			}
			
		}
	
		
		if(tempresultVec.size() == 0)
		{
			resultVec.add("loginFailure");
		}
		else
		{
			int intUserId=Integer.parseInt(tempresultVec.elementAt(0).toString().trim());
			Support support1=new Support();
			strQuery = "select companyid from loginmaster where userid="+intUserId+" and logindeletion=0";
			support1.setFieldVec("string", "companyid");
			Vector tempresultVec1 = (Vector) masterUtil.getDetail(ds, strQuery, support1);
			////////////////////////System.out.println("tempresultVec1>>>>>>"+tempresultVec1);
			if(tempresultVec1.size() == 0)
			{
				resultVec.add("activeFailure");
			}
			else
			{
				Vector tempcompanyDetailVec=(Vector)getCompanyDetail(ds,Integer.parseInt(tempresultVec.elementAt(1).toString().trim()),companyStatus);
				
				if(tempcompanyDetailVec.size()>0)
				{
					compType=tempcompanyDetailVec.elementAt(0).toString();
					if(strCompType.equalsIgnoreCase(compType))
					{
						
						resultVec.add("success");
						resultVec.add(tempresultVec.elementAt(0).toString());
					}
					else
					{
						
						resultVec.add("sectionFailure");
					}
				}
				else
				{
					
					resultVec.add("companyFailure");
				}
			}
		}
		return resultVec;
	}
	

	

	public String insertUserResidentInfo(DataSource ds, Vector dataVec ,int userID) {
		String strQuery_res;
		String results;
		String loginName=dataVec.elementAt(2).toString();
		Support support=new Support();
		String StrQuery = "select userid from loginmaster where loginname ='" + loginName+"'";
		
		//String strQuery = "select rid from loginrights where groupname = "+groupName+" and companyid = "+companyId;
		
		//result = String.valueOf(masterUtil.getId(ds, strQuery, "other_cid"));
		results =String.valueOf(masterUtil.getId(ds, StrQuery, "userid"));
		int userId = getUserId(ds, loginName);
		System.out.println("0509-1 nu=>"+userID);
		System.out.println("0509-2= nu>"+dataVec.elementAt(11).toString().trim());
		//System.out.println("2508-3= nu>"+dataVec.elementAt(40).toString().trim());
			
		strQuery_res="INSERT INTO resident_details (user_id,spouse_name,spouse_dob,child1_name,child1_dob,child2_name,child2_dob,child3_name,child3_dob,facility_id,building,tower,flat,vehicle,vehicle_name,vehicle_make,vehicle_mfd) values ("+userID+",'"+dataVec.elementAt(40).toString().trim()+"','"+dataVec.elementAt(41).toString().trim()+"','"+dataVec.elementAt(42).toString().trim()+"','"+dataVec.elementAt(43).toString().trim()+"','"+dataVec.elementAt(44).toString().trim()+"','"+dataVec.elementAt(45).toString().trim()+"','"+dataVec.elementAt(46).toString().trim()+"','"+dataVec.elementAt(47).toString().trim()+"',"+dataVec.elementAt(11).toString().trim()+",'"+dataVec.elementAt(54).toString().trim()+"','"+dataVec.elementAt(53).toString().trim()+"','"+dataVec.elementAt(52).toString().trim()+"','"+dataVec.elementAt(48).toString().trim()+"','"+dataVec.elementAt(49).toString().trim()+"','"+dataVec.elementAt(50).toString().trim()+"','"+dataVec.elementAt(51).toString().trim()+"')";
		//strQuery_res="INSERT INTO RESIDENT_DETAILS (USER_ID,FACILITY_ID) values ("+userID+",1)";
		//support.setDataVec("int", results);
		//support.setDataVec("int", dataVec.elementAt(11).toString().trim());
		
		System.out.println("0509-3=>"+strQuery_res);
		return masterUtil.setData(ds, strQuery_res, support);
	}
	
	public String insertCosumerServiceMap(DataSource ds, Vector dataVec ,int userID) {
		String strQueryMap;
		String results;
		Support support=new Support();
		String loginName=dataVec.elementAt(2).toString();
		String StrQuery = "select userid from loginmaster where loginname ='" + loginName+"'";
		results =String.valueOf(masterUtil.getId(ds, StrQuery, "userid"));
		int userId = getUserId(ds, loginName);
		System.out.println("0509-1 nu=>"+userID);
		int Status=2;
		
		for(int i=0;i<50;i++){
			System.out.println("05092009-1=>---"+i+"Th Position--"+dataVec.elementAt(i).toString().trim());
		}
		//strQueryMap="INSERT INTO RESIDENT_DETAILS (USER_ID,SPOUSE_NAME,SPOUSE_DOB,CHILD1_NAME,CHILD1_DOB,CHILD2_NAME,CHILD2_DOB,CHILD3_NAME,CHILD3_DOB,FACILITY_ID,BUILDING,TOWER,FLAT,VEHICLE,VEHICLE_NAME,VEHICLE_MAKE,VEHICLE_MFD) values ("+userID+",'"+dataVec.elementAt(40).toString().trim()+"','"+dataVec.elementAt(41).toString().trim()+"','"+dataVec.elementAt(42).toString().trim()+"','"+dataVec.elementAt(43).toString().trim()+"','"+dataVec.elementAt(44).toString().trim()+"','"+dataVec.elementAt(45).toString().trim()+"','"+dataVec.elementAt(46).toString().trim()+"','"+dataVec.elementAt(47).toString().trim()+"',"+dataVec.elementAt(11).toString().trim()+",'"+dataVec.elementAt(54).toString().trim()+"','"+dataVec.elementAt(53).toString().trim()+"','"+dataVec.elementAt(52).toString().trim()+"','"+dataVec.elementAt(48).toString().trim()+"','"+dataVec.elementAt(49).toString().trim()+"','"+dataVec.elementAt(50).toString().trim()+"','"+dataVec.elementAt(51).toString().trim()+"')";
		strQueryMap="insert into consumerservicemapping (consumerid,serviceid,datefrom,createdby,createdon,status) select "+userID+",serviceid,current_date(),"+userID+",current_date(),"+Status+" from service where facilityid='" +dataVec.elementAt(11).toString().trim()+"' and is_mendetory='1'";
		System.out.println("07092009-1=>"+strQueryMap);
		return masterUtil.setData(ds, strQueryMap, support);
	}
	
	/**
	 * Added by Ajay
	 */
	public Vector getStaffLeaveList(DataSource ds,int catId,int facilityId)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		strQuery = "select staff_id,staffname from staff_master where category="+catId+" AND active='Y' AND facility_id="+facilityId+"";
		support.setFieldVec("int", "staff_id");
		support.setFieldVec("String", "staffname");
		System.out.println("strQuery In RootMaster 15102009-1-->"+strQuery);
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		return resultVec;
}
	
	public Vector getCategoryList(DataSource ds,int dep_id)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		System.out.println("Category List 18092009-1-->"+dep_id);
		System.out.println("Category List 18092009-2-->"+tempVec);
		//strQuery = "select * from designation  order by des_name";
		strQuery = "select cat_id,cat_name from staff_category";
		System.out.println("Category List 18092009-2-->"+strQuery);
		support.setFieldVec("int", "cat_id");
		support.setFieldVec("String", "cat_name");
		
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
		System.out.println("Category List 19092009-1-->"+resultVec);
		return resultVec;
}

	public Vector getShiftList(DataSource ds,int facilityId)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		System.out.println("facilityId RootMasterrrrrrrrrr=>"+facilityId);
		strQuery = "select shift_id,shift_name from staff_shift_master where isvalid=1 and facility_id="+facilityId+"";
		//strQuery = "select staff_id,staffname from staff_master where category="+catId+" AND subcategory="+subCatId+"";
		System.out.println("facilityId=>"+strQuery);
		support.setFieldVec("int", "shift_id");
		support.setFieldVec("String", "shift_name");
		
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
		System.out.println("Builder List 07092009-1-->"+resultVec);
		return resultVec;
	}
	
	public Vector getStaffList(DataSource ds,int catId ,int facilityId)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		strQuery = "select staff_id,staffname from staff_master where category="+catId+" and active='Y' and facility_id="+facilityId+"";
		support.setFieldVec("int", "staff_id");
		support.setFieldVec("String", "staffname");
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		return resultVec;
}
	
	public Vector getSubCategoryList(DataSource ds,int catId)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		System.out.println("catId List 19092009-1-->"+catId);
		System.out.println("catId List 19092009-2-->"+tempVec);
		//strQuery = "select * from designation  order by des_name";
		strQuery = "select subcat_id,subcat_name from staff_subcategory where cat_id="+catId+"" ;
		System.out.println("Category List 18092009-2-->"+strQuery);
		support.setFieldVec("int", "subcat_id");
		support.setFieldVec("String", "subcat_name");
		
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
		System.out.println("Category List 19092009-1-->"+resultVec);
		return resultVec;
}
	
	public Vector getSubCategoryLeaveList(DataSource ds,int catId)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		System.out.println("catId List 19092009-1-->"+catId);
		System.out.println("catId List 19092009-2-->"+tempVec);
		//strQuery = "select * from designation  order by des_name";
		strQuery = "select subcat_id,subcat_name from staff_subcategory where cat_id="+catId+"" ;
		System.out.println("Category List 18092009-2-->"+strQuery);
		support.setFieldVec("int", "subcat_id");
		support.setFieldVec("String", "subcat_name");
		
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
		System.out.println("Category List 19092009-1-->"+resultVec);
		return resultVec;
}
	
	public Vector getStaffCategoryLeaveList(DataSource ds,int dep_id)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		System.out.println("Category List 18092009-1-->"+dep_id);
		System.out.println("Categogory List 18092009-2-->"+tempVec);
		//strQuery = "select * from designation  order by des_name";
		strQuery = "select cat_id,cat_name from staff_category";
		System.out.println("Category List 18092009-2-->"+strQuery);
		support.setFieldVec("int", "cat_id");
		support.setFieldVec("String", "cat_name");
		
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
		System.out.println("Category List 19092009-1-->"+resultVec);
		return resultVec;
}

public Vector getfacility(DataSource ds, String user_id) {
	
	
   Vector vec=new Vector();
  
	String strQuery_res;
	String strQuery="";
	String strResult="";
	Support support=new Support();
	//String uid=dataVec.elementAt(2).toString();
	//System.out.println("Hi Ajay==>"+uid);
	//Support support=new Support();
	strQuery ="SELECT facility_id, facility_uid,facility_name FROM facility WHERE facility_id= (select college_id from loginmaster where userid="+user_id+")";
	//System.out.println(strQuery);
	support.setFieldVec("int", "facility_id");
	support.setFieldVec("int", "facility_uid");
	support.setFieldVec("string", "facility_name");
	//strResult = masterUtil.getValue(ds, strQuery, support);
	//return strResult;//masterUtil.setData(ds, strQuery_res, support);
	vec=(Vector)masterUtil.getDetail(ds, strQuery,support);
//	req.setAttribute("facility",vec );
	
	return vec;
}

public Vector getfacilitydetails(DataSource ds, String user_id) {
	
	
	   Vector vec=new Vector();
	//   System.out.println("Hi Anu Querry facility details==>"+user_id);
		String strQuery="";
		Support support=new Support();
		//String uid=dataVec.elementAt(2).toString();
		//System.out.println("Hi Ajay==>"+uid);
		//Support support=new Support();
		strQuery ="SELECT companyid,first_name,last_name FROM loginmaster WHERE userid="+user_id;
		System.out.println(strQuery);
		support.setFieldVec("int", "companyid");		
		support.setFieldVec("string", "first_name");
		support.setFieldVec("string", "last_name");
		//strResult = masterUtil.getValue(ds, strQuery, support);
		//return strResult;//masterUtil.setData(ds, strQuery_res, support);
		vec=masterUtil.getDetail(ds, strQuery,support);
//		req.setAttribute("facility",vec );
		
		return vec;
	}


public Vector getfacilityinfo(DataSource ds, String user_id) {
	
	
	   Vector vec=new Vector();
	   String strQuery="";
		Support support=new Support();
		//String uid=dataVec.elementAt(2).toString();
		//System.out.println("Hi Ajay==>"+uid);
		//Support support=new Support();
		strQuery ="SELECT builder_id, facility_id,facility_name FROM facility WHERE facility_id= (select college_id from loginmaster where userid ="+user_id+")";
		System.out.println(strQuery);
		support.setFieldVec("int", "builder_id");
		support.setFieldVec("int", "facility_id");
		support.setFieldVec("string", "facility_name");
		//strResult = masterUtil.getValue(ds, strQuery, support);
		//return strResult;//masterUtil.setData(ds, strQuery_res, support);
		vec=masterUtil.getDetail(ds, strQuery,support);
//		req.setAttribute("facility",vec );
		System.out.println("Vec is "+vec);
		return vec;
	}

public Vector getfacID(DataSource ds, String user_id) {
	
	
	   Vector vec=new Vector();
	   String strQuery="";
		Support support=new Support();

		strQuery ="select college_id from loginmaster where userid ="+user_id;
		System.out.println(strQuery);
		support.setFieldVec("int", "college_id");
		//strResult = masterUtil.getValue(ds, strQuery, support);
		//return strResult;//masterUtil.setData(ds, strQuery_res, support);
		vec=masterUtil.getDetail(ds, strQuery,support);
//		req.setAttribute("facility",vec );
		System.out.println("Vec is "+vec);
		return vec;
	}

public Vector getResfacID(DataSource ds, String user_id) {
	
	
	   Vector vec=new Vector();
	   String strQuery="";
		Support support=new Support();
		strQuery ="select * from loginmaster where college_id = (select college_id from loginmaster where userid ="+user_id+") and login_type='Corporates'";
	//	strQuery ="select college_id from loginmaster where userid ="+user_id;
		System.out.println(strQuery);
		support.setFieldVec("int", "userid");
		support.setFieldVec("String", "first_name");
		support.setFieldVec("String", "last_name");
		//strResult = masterUtil.getValue(ds, strQuery, support);
		//return strResult;//masterUtil.setData(ds, strQuery_res, support);
		vec=masterUtil.getDetail(ds, strQuery,support);
//		req.setAttribute("facility",vec );
		System.out.println("Vec is "+vec);
		return vec;
	}

public Vector getfacilityINFO(DataSource ds, int user_id) {
	
	
	   Vector vec=new Vector();
	   String strQuery="";
		Support support=new Support();
		//String uid=dataVec.elementAt(2).toString();
		//System.out.println("Hi Ajay==>"+uid);
		//Support support=new Support();
		strQuery ="SELECT * FROM facility WHERE facility_id= (select facility_id from resident_details where user_id="+user_id+")";
		System.out.println(strQuery);
		support.setFieldVec("int", "builder_id");
		support.setFieldVec("int", "facility_id");
		support.setFieldVec("string", "facility_name");
		support.setFieldVec("string", "facility_location");
		support.setFieldVec("int", "facility_city");
		//strResult = masterUtil.getValue(ds, strQuery, support);
		//return strResult;//masterUtil.setData(ds, strQuery_res, support);
		vec=masterUtil.getDetail(ds, strQuery,support);
//		req.setAttribute("facility",vec );
		System.out.println("Vec is "+vec);
		return vec;
	}

/* this is to get Facility Details for a facility Manager added 28.09.09*/

public Vector getFacilityDetails(DataSource ds, String fid) {
	
	
	   Vector vec=new Vector();
	   String strQuery="";
		Support support=new Support();
		strQuery ="SELECT * FROM facility WHERE facility_id= "+ fid;
		System.out.println(strQuery);
		support.setFieldVec("string", "facility_name"); //0
		support.setFieldVec("string", "facility_location"); //1
		support.setFieldVec("int", "facility_city"); //2
		support.setFieldVec("int", "facility_state");  //3
		support.setFieldVec("int", "facility_country"); //4
		support.setFieldVec("int", "facility_zip"); //5
		//strResult = masterUtil.getValue(ds, strQuery, support);
		//return strResult;//masterUtil.setData(ds, strQuery_res, support);
		vec=masterUtil.getDetail(ds, strQuery,support);
//		req.setAttribute("facility",vec );
		System.out.println("Vec is "+vec);
		return vec;
	}


/**
	 * this function is use for verify user login id and password verifyUserforcomment
	 */
	
	public Vector verifyUserforcomment(DataSource ds, Vector dataVec)
	{
		Support support=new Support();
		Vector<String> resultVec = new Vector<String>();
		
		int companyStatus = 1;
		String strQuery="";
		String compType = "";
		String loginId= dataVec.elementAt(0).toString();
		String password = dataVec.elementAt(1).toString();
		String strCompType = dataVec.elementAt(2).toString();
		if(strCompType.equalsIgnoreCase("indv"))
		{
			strCompType="Consumer";
		}
		else if(strCompType.equalsIgnoreCase("advt"))
		{
			strCompType="Advertiser";
		}
		else if(strCompType.equalsIgnoreCase("corpo"))
		{
			strCompType="Corporates";
		}
		else
		{
			strCompType="Enterprise";
		}
		strQuery = "select userid , companyid, password, first_name, last_name from loginmaster where loginname='"+loginId+"' and password='"+password+"'";
		support.setFieldVec("string", "userid");
		support.setFieldVec("string", "companyid");
		support.setFieldVec("string", "password");
		support.setFieldVec("string", "first_name");
		support.setFieldVec("string", "last_name");
		
		Vector tempVec1 = (Vector) masterUtil.getDetail(ds, strQuery, support);
		Vector tempresultVec = new Vector();
		
		////////System.out.println("strQuery>>>>>>>> >>>>.."+strQuery);
		if(tempVec1.size() != 0)
		{
			if(password.equals(tempVec1.elementAt(2))){
				
				tempresultVec = tempVec1;
				
			}
			
		}
	
		
		if(tempresultVec.size() == 0)
		{
			resultVec.add("loginFailure");
		}
		else
		{
			int intUserId=Integer.parseInt(tempresultVec.elementAt(0).toString().trim());
			Support support1=new Support();
			strQuery = "select companyid from loginmaster where userid="+intUserId+" and logindeletion=0";
			support1.setFieldVec("string", "companyid");
			Vector tempresultVec1 = (Vector) masterUtil.getDetail(ds, strQuery, support1);
			////////////////////////System.out.println("tempresultVec1>>>>>>"+tempresultVec1);
			if(tempresultVec1.size() == 0)
			{
				resultVec.add("activeFailure");
			}
			else
			{
					
						resultVec.add("success");
						resultVec.add(tempresultVec.elementAt(0).toString());
						resultVec.add(tempresultVec.elementAt(1).toString());
						resultVec.add(tempresultVec.elementAt(2).toString());
						resultVec.add(tempresultVec.elementAt(3).toString());
						resultVec.add(tempresultVec.elementAt(4).toString());
				
			}
		}
		return resultVec;
	}
	/**
	 * This function is use for getting all complaint detail of a particuler
	 * complaint.
	 */
	public Vector getComplaintDetails(DataSource ds, int compId) {
		Support support=new Support();
		//////////////////////System.out.println("masterUtil.compId(ds, compId, support);"+compId);
		String strQuery = "select * from complaints where complaint_id = " + compId;
				
		//////////////////////System.out.println("masterUtil.strQuery(ds, strQuery, support);"+strQuery);
		/*
		 * set all the fileds type and name for blogs table by using Support
		 * class object.
		 */
		support.setFieldVec("int", "complaint_id");// 0
		support.setFieldVec("string", "complaint_title");// 1
		support.setFieldVec("string", "complaint_text");// 2
		support.setFieldVec("string", "relevent_info");// 3
		support.setFieldVec("string", "category");// 4
		support.setFieldVec("string", "creation_date");// 5
		support.setFieldVec("string", "publish_date");// 6
		support.setFieldVec("string", "creation_time");// 7
		support.setFieldVec("string", "publish_time");// 8
		support.setFieldVec("string", "lastmodify_date");// 9
		support.setFieldVec("string", "lastmodify_time");// 10
		support.setFieldVec("int", "login_id");// 11
		support.setFieldVec("int", "companyid");// 12
		support.setFieldVec("string", "timepart");// 13
		support.setFieldVec("int", "viewcount");// 14
		support.setFieldVec("int", "entp_id");// 15
		support.setFieldVec("int", "cu_id");// 16
		support.setFieldVec("String", "fcom_id");// 17
		support.setFieldVec("int", "advt_id");// 18
		support.setFieldVec("int", "advtL_id");// 19
		support.setFieldVec("int", "dealer_id");// 20
		support.setFieldVec("int", "tag_id");// 21
		support.setFieldVec("String", "resolve_date");// 22
		support.setFieldVec("String", "closed_date");// 23
		support.setFieldVec("int", "brandFlag");// 24
		support.setFieldVec("int", "publish_flag");// 25
		support.setFieldVec("string", "publish_on");// 26
		support.setFieldVec("string", "other");// 27

		return masterUtil.getDetail(ds, strQuery, support);
	}

	/**
	 * 
	 * @param paramVec
	 *            takes parameter like Minimu and maximum value for fatching the
	 *            list of complaint.
	 * @return
	 */
	
	/**
	 * This function is use for getting list of published complaint. It returns
	 * a list within limit which is given by min and max variable. it also use
	 * for searching complaint.
	 */
	public Vector getComplaintList(DataSource ds, Vector paramVec) {
		int vectorSize = paramVec.size();
		int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
		int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
		String typ=paramVec.elementAt(2).toString().trim();
		
		System.out.println("checkpoint at Complaintss list");
		
		Support support=new Support();
		String strQuery="";
		if (vectorSize == 4) {
			strQuery = "SELECT * FROM complaints where publish_flag =1 and "
					+ paramVec.elementAt(2).toString().trim()
					+ " ='"
					+ paramVec.elementAt(3).toString().trim()
					+ "' order by creation_date DESC, creation_time DESC limit "
					+ min + ", " + max;
		} else if (vectorSize == 6) {
			strQuery = "SELECT * FROM complaints where publish_flag =1 and "
					+ paramVec.elementAt(2).toString().trim()
					+ " ='"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " ='"
					+ paramVec.elementAt(5).toString().trim()
					+ "' order by creation_date DESC, creation_time DESC limit "
					+ min + ", " + max;
		} else if (vectorSize == 8) {
			strQuery = "SELECT * FROM complaints where publish_flag =1 and "
					+ paramVec.elementAt(2).toString().trim()
					+ "='"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " ='"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " ='"
					+ paramVec.elementAt(7).toString().trim()
					+ "' order by creation_date DESC, creation_time DESC limit "
					+ min + ", " + max;
		} else if (vectorSize == 10) {
			strQuery = "SELECT * FROM complaints where publish_flag =1 and "
					+ paramVec.elementAt(2).toString().trim()
					+ "='"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " ='"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " ='"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " ='"
					+ paramVec.elementAt(9).toString().trim()
					+ "'order by creation_date DESC, creation_time DESC  limit "
					+ min + ", " + max;
		} else {
			if(typ.equalsIgnoreCase("blog"))
			{
				strQuery = "select * from complaints where publish_flag=1 and q_type='blog' order by creation_date DESC, creation_time DESC limit "
					+ min + ", " + max;
			}else{
			strQuery = "select * from complaints where publish_flag=1 and q_type!='blog'order by creation_date DESC, creation_time DESC limit "
					+ min + ", " + max;
			}
		}
		
		/*
		 * set all the fileds type and name for complaints table by using
		 * Support class object.
		 */
		support.setFieldVec("int", "complaint_id");
		support.setFieldVec("string", "complaint_title");
		support.setFieldVec("string", "complaint_text");
		//support.setFieldVec("string", "city");
		support.setFieldVec("string", "category");
		/*support.setFieldVec("string", "subcategory1");
		support.setFieldVec("string", "subcategory2");
		support.setFieldVec("string", "subcategory3");
		support.setFieldVec("string", "subcategory4");*/
		support.setFieldVec("string", "creation_date");
		support.setFieldVec("string", "creation_time");
		support.setFieldVec("int", "viewcount");
		support.setFieldVec("String", "fcom_id");
		support.setFieldVec("int", "companyid");
		support.setFieldVec("int", "login_id");
			////System.out.println("strQuerystrQuery>>>"+strQuery);
		return masterUtil.getList(ds, strQuery, support);
	}
	
	/**
	 * This function is use for getting list of all the classifieds. It returns
	 * a list within limit which is given by min and max variable. it also use
	 * for search Blogs.
	 */
	public Vector getBlogsList(DataSource ds, Vector paramVec) {
		int vectorSize = paramVec.size();
		int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
		int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
		String typ=paramVec.elementAt(2).toString().trim();
		String category  =paramVec.elementAt(3).toString().trim();
		System.out.println("category "+category);
		
		Support support=new Support();
		String strQuery="";
		if (Integer.parseInt(paramVec.elementAt(3).toString().trim()) == 0){
			strQuery = "select * from complaints where publish_flag=1 and q_type='blog' order by creation_date DESC, creation_time DESC limit " + min + ", " + max; 
		}
		else
			{
			strQuery = "select * from complaints where publish_flag=1 and q_type='blog' and category="+category+" order by creation_date DESC, creation_time DESC limit "
			
					+ min + ", " + max;
			}
		/*
		 * set all the fileds type and name for complaints table by using
		 * Support class object.
		 */
		support.setFieldVec("int", "complaint_id");
		support.setFieldVec("string", "complaint_title");
		support.setFieldVec("string", "complaint_text");
		//support.setFieldVec("string", "city");
		support.setFieldVec("string", "category");
		support.setFieldVec("string", "creation_date");
		support.setFieldVec("string", "creation_time");
		support.setFieldVec("int", "viewcount");
		support.setFieldVec("String", "fcom_id");
		support.setFieldVec("int", "companyid");
		support.setFieldVec("int", "login_id");
		System.out.println("strQuerystrQuery>>>"+strQuery);
		return masterUtil.getList(ds, strQuery, support);
	}
	
	
	/**
	 * This function is use for getting Search Result for student Search. 
	 */
	public Vector getSearchResultStudentList(DataSource ds, Vector paramVec) {
		int vectorSize = paramVec.size();
		int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
		int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
		Support support=new Support();
		String strQuery="";
		if (vectorSize == 4) {
			strQuery = "SELECT * FROM loginmaster where logindeletion =0 and login_type ='Student' and "
					+ paramVec.elementAt(2).toString().trim()
					+ " '"
					+ paramVec.elementAt(3).toString().trim()
					+ "' order by first_name , last_name  limit "
					+ min + ", " + max;
		} else if (vectorSize == 6) {
			strQuery = "SELECT * FROM loginmaster where logindeletion =0 and login_type ='Student' and "
					+ paramVec.elementAt(2).toString().trim()
					+ " '"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' order by first_name , last_name  limit "
					+ min + ", " + max;
		} else if (vectorSize == 8) {
			strQuery = "SELECT * FROM loginmaster where logindeletion =0 and login_type ='Student' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' order by first_name , last_name  limit "
					+ min + ", " + max;
		} else if (vectorSize == 10) {
			strQuery = "SELECT * FROM loginmaster where logindeletion =0 and login_type ='Student' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()
					+ "'order by first_name , last_name   limit "
					+ min + ", " + max;
		}
		else if (vectorSize == 12) {
			strQuery = "SELECT * FROM loginmaster where logindeletion =0 and login_type ='Student' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()
					+ "' and "
					+ paramVec.elementAt(10).toString().trim()
					+ " '"
					+ paramVec.elementAt(11).toString().trim()
					+ "'order by first_name , last_name   limit "
					+ min + ", " + max;
		}
		else if (vectorSize == 14) {
			strQuery = "SELECT * FROM loginmaster where logindeletion =0 and login_type ='Student' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()
					+ "' and "
					+ paramVec.elementAt(10).toString().trim()
					+ " '"
					+ paramVec.elementAt(11).toString().trim()
					+ "' and "
					+ paramVec.elementAt(12).toString().trim()
					+ " '"
					+ paramVec.elementAt(13).toString().trim()
					+ "'order by first_name , last_name   limit "
					+ min + ", " + max;
		}		
		else {
			strQuery = "select * from loginmaster where logindeletion =0 and login_type ='Student' order by first_name DESC, last_name DESC limit "
					+ min + ", " + max;
		}
		
		/*
		 * set all the fileds type and name for complaints table by using
		 * Support class object.
		 */
		support.setFieldVec("int", "userid");//0
		support.setFieldVec("string", "first_name");//1
		support.setFieldVec("string", "last_name");//2
		support.setFieldVec("string", "status");//3
		support.setFieldVec("string", "course");//4
		support.setFieldVec("string", "college_id");//5
		support.setFieldVec("string", "other_coll_id");//6
		support.setFieldVec("string", "photopath");//7
		support.setFieldVec("string", "major_sub");//8
		
		//support.setFieldVec("string", "creation_date");
		//support.setFieldVec("string", "creation_time");
		//support.setFieldVec("int", "viewcount");
		//support.setFieldVec("String", "fcom_id");
		//support.setFieldVec("int", "companyid");
		//support.setFieldVec("int", "login_id");
		
		////////////////////System.out.println("strQuery>>>>>>..."+strQuery);

		return masterUtil.getList(ds, strQuery, support);
	}
	
	/**
	 * This function use for getting count Search Result for student Search.
	 */
	public int getSearchResultStudentCount(DataSource ds, Vector paramVec) {
		
		String strQuery="";
		
		int vectorSize = paramVec.size();

		if (vectorSize == 4) {
			strQuery = "SELECT  count(*) count FROM loginmaster where logindeletion =0 and  login_type ='Student' and "
					+ paramVec.elementAt(2).toString().trim()
					+ " '"
					+ paramVec.elementAt(3).toString().trim()+"'";
		} else if (vectorSize == 6) {
			strQuery = "SELECT  count(*) count FROM loginmaster where logindeletion =0 and login_type ='Student' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()+"'";
		} else if (vectorSize == 8) {
			strQuery = "SELECT  count(*) count FROM loginmaster where logindeletion =0 and login_type ='Student' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()+"'";
		} else if (vectorSize == 10) {
			strQuery = "SELECT  count(*) count FROM loginmaster where logindeletion =0 and login_type ='Student' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()+"'";
					
		}
		else if (vectorSize == 12) {
			strQuery = "SELECT  count(*) count FROM loginmaster where logindeletion =0 and login_type ='Student' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()
					+ "' and "
					+ paramVec.elementAt(10).toString().trim()
					+ " '"
					+ paramVec.elementAt(11).toString().trim()+"'";
					
		}
		else if (vectorSize == 14) {
			strQuery = "SELECT  count(*) count FROM loginmaster where logindeletion =0 and login_type ='Student' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()
					+ "' and "
					+ paramVec.elementAt(10).toString().trim()
					+ " '"
					+ paramVec.elementAt(11).toString().trim()
					+ "' and "
					+ paramVec.elementAt(12).toString().trim()
					+ " '"
					+ paramVec.elementAt(13).toString().trim()+"'";
					
		}
		else {

			strQuery = "select  count(*) count from loginmaster where logindeletion=0 and login_type ='Student' ";
					
		}


		//////////////////////System.out.println("strQuery>>count>>>>..."+strQuery);
		return masterUtil.getCount(ds, strQuery);
	}
	public Vector getState(int cid, DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		Vector resultVec = new Vector <Vector>();
		Vector tempVec = new Vector <String>();
	  	 tempVec.add("0");
	  	 tempVec.add("Select");
	  	 resultVec.add(tempVec);
	  	 System.out.println("Tempvec is "+tempVec);
		 if(cid>0)
		 {
			 strQuery="SELECT FACILITY_ID,FACILITY_NAME FROM facility";
			 System.out.println("Querry=>"+strQuery);
			 support.setFieldVec("int", "FACILITY_ID");
			 support.setFieldVec("String", "FACILITY_NAME");
			 Vector <Vector>tempResultVec  = new Vector<Vector>();
			 tempResultVec=(Vector)masterUtil.getList(ds, strQuery, support);
			 for(int i=0; i<tempResultVec.size();i++)
			 {
				 resultVec.add(tempResultVec.elementAt(i));
			 }
		 }
		
	  	 System.out.println("Tempvec_result is "+resultVec);
		 return resultVec;
	}




public String FaciltyName(DataSource ds,int userId)
	{
		String strResult="";
		Support support=new Support();
	    	String strQuery="";
	    	
	    	strQuery= "SELECT FACILITY_NAME FROM facility  WHERE FACILITY_ID=(SELECT STATE FROM loginmaster WHERE USERID="+userId+")";
		support.setFieldVec("string", "FACILITY_NAME");	
		strResult = masterUtil.getValue(ds, strQuery, support);
		return strResult;
		
	}
	

	/**
	 * This function is use for getting Search Result for student Search. 
	 */
	public Vector getSearchResultCompanyList(DataSource ds, Vector paramVec) {
		int vectorSize = paramVec.size();
		int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
		int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
		Support support=new Support();
		String strQuery="";
		if (vectorSize == 4) {
			strQuery = "SELECT * FROM companymaster where active =1 and typeofcompany ='Corporates' and "
					+ paramVec.elementAt(2).toString().trim()
					+ " '"
					+ paramVec.elementAt(3).toString().trim()
					+ "' order by company_name  limit "
					+ min + ", " + max;
		} else if (vectorSize == 6) {
			strQuery = "SELECT * FROM companymaster where active =1 and typeofcompany ='Corporates' and "
					+ paramVec.elementAt(2).toString().trim()
					+ " '"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' order by company_name  limit "
					+ min + ", " + max;
		} else if (vectorSize == 8) {
			strQuery = "SELECT * FROM companymaster where active =1 and typeofcompany ='Corporates' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' order by company_name  limit "
					+ min + ", " + max;
		} else if (vectorSize == 10) {
			strQuery = "SELECT * FROM companymaster where active =1 and typeofcompany ='Corporates' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()
					+ "'order by company_name   limit "
					+ min + ", " + max;
		}
		else if (vectorSize == 12) {
			strQuery = "SELECT * FROM companymaster where active =1 and typeofcompany ='Corporates' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()
					+ "' and "
					+ paramVec.elementAt(10).toString().trim()
					+ " '"
					+ paramVec.elementAt(11).toString().trim()
					+ "'order by company_name   limit "
					+ min + ", " + max;
		}
		else if (vectorSize == 14) {
			strQuery = "SELECT * FROM companymaster where active =1 and typeofcompany ='Corporates' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()
					+ "' and "
					+ paramVec.elementAt(10).toString().trim()
					+ " '"
					+ paramVec.elementAt(11).toString().trim()
					+ "' and "
					+ paramVec.elementAt(12).toString().trim()
					+ " '"
					+ paramVec.elementAt(13).toString().trim()
					+ "'order by company_name   limit "
					+ min + ", " + max;
		}		
		else {
			strQuery = "select * from companymaster where active =1 and typeofcompany ='Corporates' order by company_name "
					+ min + ", " + max;
		}
		
		/*
		 * set all the fileds type and name for complaints table by using
		 * Support class object.
		 */
		support.setFieldVec("int", "company_id");//0
		support.setFieldVec("string", "company_sname");//1
		support.setFieldVec("string", "company_name");//2
		support.setFieldVec("string", "company_city");//3
		support.setFieldVec("string", "company_state");//4
		support.setFieldVec("string", "company_country");//5
		
		
		//////////////////////System.out.println("strQuery>>>>>>..."+strQuery);

		return masterUtil.getList(ds, strQuery, support);
	}
	
	/**
	 * This function use for getting count Search Result for student Search.
	 */
	public int getSearchResultCompanyCount(DataSource ds, Vector paramVec) {
		
		String strQuery="";
		
		int vectorSize = paramVec.size();

		if (vectorSize == 4) {
			strQuery = "SELECT  count(*) count FROM  companymaster where active =1 and typeofcompany ='Corporates' and "
					+ paramVec.elementAt(2).toString().trim()
					+ " '"
					+ paramVec.elementAt(3).toString().trim()+"'";
		} else if (vectorSize == 6) {
			strQuery = "SELECT  count(*) count FROM companymaster where active =1 and typeofcompany ='Corporates' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()+"'";
		} else if (vectorSize == 8) {
			strQuery = "SELECT  count(*) count FROM companymaster where active =1 and typeofcompany ='Corporates' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()+"'";
		} else if (vectorSize == 10) {
			strQuery = "SELECT  count(*) count FROM companymaster where active =1 and typeofcompany ='Corporates' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()+"'";
					
		}
		else if (vectorSize == 12) {
			strQuery = "SELECT  count(*) count FROM companymaster where active =1 and typeofcompany ='Corporates' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()
					+ "' and "
					+ paramVec.elementAt(10).toString().trim()
					+ " '"
					+ paramVec.elementAt(11).toString().trim()+"'";
					
		}
		else if (vectorSize == 14) {
			strQuery = "SELECT  count(*) count FROM companymaster where active =1 and typeofcompany ='Corporates' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()
					+ "' and "
					+ paramVec.elementAt(10).toString().trim()
					+ " '"
					+ paramVec.elementAt(11).toString().trim()
					+ "' and "
					+ paramVec.elementAt(12).toString().trim()
					+ " '"
					+ paramVec.elementAt(13).toString().trim()+"'";
					
		}
		else {

			strQuery = "select  count(*) count from companymaster where active =1 and typeofcompany ='Corporates' ";
					
		}


		//////////////////////System.out.println("strQuery>>count>>>>..."+strQuery);
		return masterUtil.getCount(ds, strQuery);
	}
	
	/**
	 * This function is use for getting Search Result for student Search. 
	 */
	public Vector getSearchResultCollegeList(DataSource ds, Vector paramVec) {
		int vectorSize = paramVec.size();
		int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
		int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
		Support support=new Support();
		String strQuery="";
		if (vectorSize == 4) {
			strQuery = "SELECT * FROM companymaster where active =1 and typeofcompany ='Advertiser' and "
					+ paramVec.elementAt(2).toString().trim()
					+ " '"
					+ paramVec.elementAt(3).toString().trim()
					+ "' order by company_name  limit "
					+ min + ", " + max;
		} else if (vectorSize == 6) {
			strQuery = "SELECT * FROM companymaster where active =1 and typeofcompany ='Advertiser' and "
					+ paramVec.elementAt(2).toString().trim()
					+ " '"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' order by company_name  limit "
					+ min + ", " + max;
		} else if (vectorSize == 8) {
			strQuery = "SELECT * FROM companymaster where active =1 and typeofcompany ='Advertiser' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' order by company_name  limit "
					+ min + ", " + max;
		} else if (vectorSize == 10) {
			strQuery = "SELECT * FROM companymaster where active =1 and typeofcompany ='Advertiser' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()
					+ "'order by company_name   limit "
					+ min + ", " + max;
		}
		else if (vectorSize == 12) {
			strQuery = "SELECT * FROM companymaster where active =1 and typeofcompany ='Advertiser' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()
					+ "' and "
					+ paramVec.elementAt(10).toString().trim()
					+ " '"
					+ paramVec.elementAt(11).toString().trim()
					+ "'order by company_name   limit "
					+ min + ", " + max;
		}
		else if (vectorSize == 14) {
			strQuery = "SELECT * FROM companymaster where active =1 and typeofcompany ='Advertiser' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()
					+ "' and "
					+ paramVec.elementAt(10).toString().trim()
					+ " '"
					+ paramVec.elementAt(11).toString().trim()
					+ "' and "
					+ paramVec.elementAt(12).toString().trim()
					+ " '"
					+ paramVec.elementAt(13).toString().trim()
					+ "'order by company_name   limit "
					+ min + ", " + max;
		}		
		else {
			strQuery = "select * from companymaster where active =1 and typeofcompany ='Advertiser' order by company_name limit "
					+ min + ", " + max;
		}
		
		/*
		 * set all the fileds type and name for complaints table by using
		 * Support class object.
		 */
		support.setFieldVec("int", "company_id");
		support.setFieldVec("string", "company_sname");
		support.setFieldVec("string", "company_name");
		support.setFieldVec("string", "company_city");//3
		support.setFieldVec("string", "company_state");//4
		support.setFieldVec("string", "company_country");//5
		
		
		//////////////////////System.out.println("strQuery>>>>>>..."+strQuery);

		return masterUtil.getList(ds, strQuery, support);
	}
	
	/**
	 * This function use for getting count Search Result for student Search.
	 */
	public int getSearchResultCollegeCount(DataSource ds, Vector paramVec) {
		
		String strQuery="";
		
		int vectorSize = paramVec.size();

		if (vectorSize == 4) {
			strQuery = "SELECT  count(*) count FROM  companymaster where active =1 and typeofcompany ='Advertiser' and "
					+ paramVec.elementAt(2).toString().trim()
					+ " '"
					+ paramVec.elementAt(3).toString().trim()+"'";
		} else if (vectorSize == 6) {
			strQuery = "SELECT  count(*) count FROM companymaster where active =1 and typeofcompany ='Advertiser' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()+"'";
		} else if (vectorSize == 8) {
			strQuery = "SELECT  count(*) count FROM companymaster where active =1 and typeofcompany ='Advertiser' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()+"'";
		} else if (vectorSize == 10) {
			strQuery = "SELECT  count(*) count FROM companymaster where active =1 and typeofcompany ='Advertiser' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()+"'";
					
		}
		else if (vectorSize == 12) {
			strQuery = "SELECT  count(*) count FROM companymaster where active =1 and typeofcompany ='Advertiser' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()
					+ "' and "
					+ paramVec.elementAt(10).toString().trim()
					+ " '"
					+ paramVec.elementAt(11).toString().trim()+"'";
					
		}
		else if (vectorSize == 14) {
			strQuery = "SELECT  count(*) count FROM companymaster where active =1 and typeofcompany ='Advertiser' and "
					+ paramVec.elementAt(2).toString().trim()
					+ "'"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " '"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " '"
					+ paramVec.elementAt(7).toString().trim()
					+ "' and "
					+ paramVec.elementAt(8).toString().trim()
					+ " '"
					+ paramVec.elementAt(9).toString().trim()
					+ "' and "
					+ paramVec.elementAt(10).toString().trim()
					+ " '"
					+ paramVec.elementAt(11).toString().trim()
					+ "' and "
					+ paramVec.elementAt(12).toString().trim()
					+ " '"
					+ paramVec.elementAt(13).toString().trim()+"'";
					
		}
		else {

			strQuery = "select  count(*) count from companymaster where active =1 and typeofcompany ='Advertiser' ";
					
		}


		//////////////////////System.out.println("strQuery>>count>>>>..."+strQuery);
		return masterUtil.getCount(ds, strQuery);
	}
	
	
	/**
	 * This function is use for getting list of user ids. it also use
	 * for searching complaint.
	 */
	public Vector getUserIds(DataSource ds, Vector paramVec) {
		int vectorSize = paramVec.size();
		Support support=new Support();
		String strQuery="";
		if (vectorSize == 2) {
			strQuery = "SELECT l.userid userid FROM loginmaster l, companymaster c where l.logindeletion =0 and c.typeofcompany='Consumer' and l.companyid= c.company_id and "
					+ paramVec.elementAt(0).toString().trim()
					+ " ="
					+ paramVec.elementAt(1).toString().trim();
					
		} else if (vectorSize == 4) {
			strQuery = "SELECT userid userid FROM loginmaster l, companymaster c where l.logindeletion =0 and c.typeofcompany='Consumer' and l.companyid= c.company_id and "
				+ paramVec.elementAt(0).toString().trim()
				+ " ="
				+ paramVec.elementAt(1).toString().trim()
				+ " and "
				+ paramVec.elementAt(2).toString().trim()
				+ " ="
				+ paramVec.elementAt(3).toString().trim();
				
		} else if (vectorSize == 6) {
			strQuery = "SELECT userid userid FROM loginmaster l, companymaster c where l.logindeletion =0 and c.typeofcompany='Consumer' and l.companyid= c.company_id and "
				+ paramVec.elementAt(0).toString().trim()
				+ " ="
				+ paramVec.elementAt(1).toString().trim()
				+ " and "
				+ paramVec.elementAt(2).toString().trim()
				+ " ="
				+ paramVec.elementAt(3).toString().trim()
				+ " and "
				+ paramVec.elementAt(4).toString().trim()
				+ " ="
				+ paramVec.elementAt(5).toString().trim();
				
		} 
		
		/*
		 * set all the fileds type and name for complaints table by using
		 * Support class object.
		 */
		support.setFieldVec("int", "userid");
		
		return masterUtil.getList(ds, strQuery, support);
	}
	
	/**
	 * This function is use for getting list of published complaint. It returns
	 * a list within limit which is given by min and max variable. it also use
	 * for searching complaint.
	 */
	public Vector getComplaintList(DataSource ds, Vector paramVec,String userIds) {
		int vectorSize = paramVec.size();
		int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
		int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
		String comdition = "";
		if(userIds.length()>0)
		{
			comdition += " and login_id in ("+userIds+")";
		}
		else
		{
			comdition += " and login_id =0";
		}
		Support support=new Support();
		String strQuery="";
		if (vectorSize == 4) {
			strQuery = "SELECT * FROM complaints where publish_flag =1 "+comdition+" and "
					+ paramVec.elementAt(2).toString().trim()
					+ " ='"
					+ paramVec.elementAt(3).toString().trim()
					+ "' order by creation_date DESC, creation_time DESC limit "
					+ min + ", " + max;
		} else if (vectorSize == 6) {
			strQuery = "SELECT * FROM complaints where publish_flag =1 "+comdition+" and "
					+ paramVec.elementAt(2).toString().trim()
					+ " ='"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " ='"
					+ paramVec.elementAt(5).toString().trim()
					+ "' order by creation_date DESC, creation_time DESC limit "
					+ min + ", " + max;
		} else if (vectorSize == 8) {
			strQuery = "SELECT * FROM complaints where publish_flag =1 "+comdition+" and "
					+ paramVec.elementAt(2).toString().trim()
					+ "='"
					+ paramVec.elementAt(3).toString().trim()
					+ "' and "
					+ paramVec.elementAt(4).toString().trim()
					+ " ='"
					+ paramVec.elementAt(5).toString().trim()
					+ "' and "
					+ paramVec.elementAt(6).toString().trim()
					+ " ='"
					+ paramVec.elementAt(7).toString().trim()
					+ "' order by creation_date DESC, creation_time DESC limit "
					+ min + ", " + max;
		
		} else {
			strQuery = "select * from complaints where publish_flag=1 "+comdition+" order by creation_date DESC, creation_time DESC limit "
					+ min + ", " + max;
		}
		
		
		/*
		 * set all the fileds type and name for complaints table by using
		 * Support class object.
		 */
		support.setFieldVec("int", "complaint_id");
		support.setFieldVec("string", "complaint_title");
		support.setFieldVec("string", "complaint_text");
		support.setFieldVec("string", "category");
		support.setFieldVec("string", "creation_date");
		support.setFieldVec("string", "creation_time");
		support.setFieldVec("int", "viewcount");
		support.setFieldVec("String", "fcom_id");
		support.setFieldVec("int", "login_id");
		support.setFieldVec("int", "advt_id");
		
		//////////////////////System.out.println("strQuery for search Result .....  "+strQuery);

		return masterUtil.getList(ds, strQuery, support);
	}
	/**
	 * This function use for getting total no. of published complaint.
	 */
	public int getComplaintCount(DataSource ds, Vector dataVec,String userIds) {
		
		String strQuery="";
		
		int vectorSize = dataVec.size();
		if (vectorSize == 4)
			strQuery = "SELECT count(*) count FROM complaints where publish_flag =1 and "
					+ dataVec.elementAt(2).toString().trim()
					+ "='"
					+ dataVec.elementAt(3).toString().trim() + "'";

		else if (vectorSize == 6)
			strQuery = "SELECT count(*) count FROM complaints where publish_flag =1 and "
					+ dataVec.elementAt(2).toString().trim()
					+ "='"
					+ dataVec.elementAt(3).toString().trim()
					+ "' and "
					+ dataVec.elementAt(4).toString().trim()
					+ " ='"
					+ dataVec.elementAt(5).toString().trim() + "'";

		else if (vectorSize == 8)
			strQuery = "SELECT count(*) count FROM complaints where publish_flag =1 and "
					+ dataVec.elementAt(2).toString().trim()
					+ "='"
					+ dataVec.elementAt(3).toString().trim()
					+ "' and "
					+ dataVec.elementAt(4).toString().trim()
					+ " ='"
					+ dataVec.elementAt(5).toString().trim()
					+ "' and "
					+ dataVec.elementAt(6).toString().trim()
					+ "='"
					+ dataVec.elementAt(7).toString().trim() + "'";
		else
			strQuery = "SELECT count(*) count FROM complaints where publish_flag =1";
		
		if(userIds.length()>0)
		{
			strQuery += " and login_id in ("+userIds+")";
		}
		else
		{
			strQuery += " and login_id =0";
		}

		return masterUtil.getCount(ds, strQuery);
	}

	/**
	 * 
	 * @return no of blished complaint
	 */
	/**
	 * This function use for getting total no. of published complaint.
	 */
	public int getComplaintCount(DataSource ds, Vector dataVec) {
		
		String strQuery="";
		
		int vectorSize = dataVec.size();
		if (vectorSize == 4)
			strQuery = "SELECT count(*) count FROM complaints where publish_flag =1 and "
					+ dataVec.elementAt(2).toString().trim()
					+ "='"
					+ dataVec.elementAt(3).toString().trim() + "'";

		else if (vectorSize == 6)
			strQuery = "SELECT count(*) count FROM complaints where publish_flag =1 and "
					+ dataVec.elementAt(2).toString().trim()
					+ "='"
					+ dataVec.elementAt(3).toString().trim()
					+ "' and "
					+ dataVec.elementAt(4).toString().trim()
					+ " ='"
					+ dataVec.elementAt(5).toString().trim() + "'";

		else if (vectorSize == 8)
			strQuery = "SELECT count(*) count FROM complaints where publish_flag =1 and "
					+ dataVec.elementAt(2).toString().trim()
					+ "='"
					+ dataVec.elementAt(3).toString().trim()
					+ "' and "
					+ dataVec.elementAt(4).toString().trim()
					+ " ='"
					+ dataVec.elementAt(5).toString().trim()
					+ "' and "
					+ dataVec.elementAt(6).toString().trim()
					+ "='"
					+ dataVec.elementAt(7).toString().trim() + "'";

		else if (vectorSize == 10)
			strQuery = "SELECT count(*) count FROM complaints where publish_flag =1 and "
					+ dataVec.elementAt(2).toString().trim()
					+ "='"
					+ dataVec.elementAt(3).toString().trim()
					+ "' and "
					+ dataVec.elementAt(4).toString().trim()
					+ " ='"
					+ dataVec.elementAt(5).toString().trim()
					+ "' and "
					+ dataVec.elementAt(6).toString().trim()
					+ "='"
					+ dataVec.elementAt(7).toString().trim()
					+ "' and "
					+ dataVec.elementAt(8).toString().trim()
					+ "='"
					+ dataVec.elementAt(9).toString().trim() + "'";

		else{
			
			if(dataVec.elementAt(0).toString().equalsIgnoreCase("blog")){
			strQuery = "SELECT count(*) count FROM complaints where publish_flag =1 and q_type='blog'";
			}else
			{
				strQuery = "SELECT count(*) count FROM complaints where publish_flag =1 and q_type!='blog'";
			}
			}
		return masterUtil.getCount(ds, strQuery);
	}

	/**
	 * 
	 * @return no of blished complaint
	 */
	/**
	 * This function use for getting total no. of published complaint.
	 */
	public int getBlogsCount(DataSource ds, Vector dataVec) {
		
		String strQuery="";
		
		int vectorSize = dataVec.size();
		String category = dataVec.elementAt(1).toString().trim();
		if (Integer.parseInt(dataVec.elementAt(1).toString().trim()) == 0){
			strQuery = "SELECT count(*) count FROM  complaints where publish_flag=1 and q_type='blog'"; 
		}
		else
			{
			strQuery = "SELECT count(*) count FROM  complaints where publish_flag=1 and q_type='blog' and category="+category;
			}
		
		return masterUtil.getCount(ds, strQuery);
	}

	/**
	 * Dealer related function as follows.
	 */
	/*
	 * This function get dealer information .
	 */
	public Vector getDealerDetail(DataSource ds, int dealerId) {
		Support support=new Support();
		String strQuery="";
		strQuery = "select * from dealer where dealerid = " + dealerId;

		support.setFieldVec("int", "dealerid");//0
		support.setFieldVec("string", "dealername");//1
		support.setFieldVec("string", "contactperson");//2
		support.setFieldVec("int", "companyid");//3
		support.setFieldVec("string", "state");//4
		support.setFieldVec("string", "district");//5
		support.setFieldVec("string", "city");//6
		support.setFieldVec("string", "email");//7
		support.setFieldVec("string", "phonenumber");//8
		support.setFieldVec("string", "mobileno");//9
		support.setFieldVec("int", "pincode");//10
		support.setFieldVec("string", "address");//11
		support.setFieldVec("string", "country");//12

		return masterUtil.getDetail(ds, strQuery, support);
	}

	/**
	 * Company inforamtion related function .
	 */
	/**
	 * This funciton is use for getting company datail.This function is
	 * overLoaded.
	 */
	public Vector getCompanyDetail(DataSource ds, int companyId) {
		Support support=new Support();
		String strQuery="";
		strQuery = "select * from companymaster where company_id ="+companyId;
				

		support.setFieldVec("string", "company_sname"); //0
		support.setFieldVec("string", "company_name");//1
		support.setFieldVec("string", "company_address1");//2
		support.setFieldVec("string", "company_address2");//3
		support.setFieldVec("string", "company_city");//4
		support.setFieldVec("string", "company_state");//5
		support.setFieldVec("string", "company_country");//6
		support.setFieldVec("string", "company_zip");//7
		support.setFieldVec("string", "company_email");//8
		support.setFieldVec("string", "company_phone");//9
		support.setFieldVec("string", "category");//10
		support.setFieldVec("int", "total_sms");//11
		support.setFieldVec("int", "used_sms");//12
		support.setFieldVec("int", "total_user");//13
		support.setFieldVec("string", "sms_expiry_date");//14
		support.setFieldVec("int", "smsc_id");//15
		support.setFieldVec("int", "active");//16
		support.setFieldVec("string", "typeofcompany");//17
		support.setFieldVec("string", "senderID");//18
		support.setFieldVec("int", "total_senderid");//19
		support.setFieldVec("string", "communityrights");//20
		support.setFieldVec("string", "date_of_creation");//21
		support.setFieldVec("string", "license_expiry_date");//22
		support.setFieldVec("int", "hidecommunity");//23		
		support.setFieldVec("int", "registered");//24
		support.setFieldVec("string", "mtype");//25
		support.setFieldVec("string", "logopath");//26
		support.setFieldVec("string", "short_pro");//27

		return masterUtil.getDetail(ds, strQuery, support);
	}

	public Vector getCompanyDetail(DataSource ds, int companyId ,int active) {
		Support support=new Support();
		String strQuery="";
		strQuery = "select typeofcompany from companymaster where company_id="+ companyId +"  and active="+active;
		support.setFieldVec("string", "typeofcompany");
		return masterUtil.getDetail(ds, strQuery, support);
	}
	
	
	public Vector getCompanyDetail(DataSource ds, String comp_sname) {
		Support support=new Support();
		String strQuery="";
		strQuery = "select company_id, typeofcompany, company_name, company_email from companymaster where company_sname='"+ comp_sname+"'";

		support.setFieldVec("int", "company_id");
		support.setFieldVec("string", "typeofcompany");
		support.setFieldVec("string", "company_name");
		support.setFieldVec("string", "company_email");
		
		return masterUtil.getDetail(ds, strQuery, support);
	}
	public String companyVerify(DataSource ds, String comp_sname, String comp_email , String comp_phone)
	{
		Support support=new Support();
		String strResult="";
		String strQuery="";
		strQuery = "select company_id from companymaster where company_sname='"+ comp_sname+"'";
		support.setFieldVec("int", "company_id");
		Vector firstVec = masterUtil.getDetail(ds, strQuery, support);
		if(firstVec.size()>0)
		{
			strResult="sname";
			
		}
		else
		{
			strQuery = "select company_id from companymaster where company_email='"+ comp_email+"'";
			support.setFieldVec("int", "company_id");
			Vector secondVec = masterUtil.getDetail(ds, strQuery, support);
			if(secondVec.size()>0)
			{
				strResult="mailid";
				
			}
			else
			{
				strQuery = "select company_id from companymaster where company_phone='"+ comp_phone+"'";
				support.setFieldVec("int", "company_id");
				Vector thirdVec = masterUtil.getDetail(ds, strQuery, support);
				if(thirdVec.size()>0)
				{
					strResult="phone";
					
				}
				else
				{
					strResult="success";
					
				}
				
			}
			
		}
		
		return strResult;
	}
	public String comCollVerify(DataSource ds, String comp_sname, String comp_email , String comp_phone,String typeOfCompan)
	{
		Support support=new Support();
		String strResult="";
		String strQuery="";
		strQuery = "select company_id from companymaster where company_sname='"+ comp_sname+"' and typeofcompany='"+ typeOfCompan+"'";
		support.setFieldVec("int", "company_id");
		Vector firstVec = masterUtil.getDetail(ds, strQuery, support);
		if(firstVec.size()>0)
		{
			strResult="sname";
			
		}
		else
		{
			strQuery = "select company_id from companymaster where company_email='"+ comp_email+"' and typeofcompany='"+ typeOfCompan+"'";
			support.setFieldVec("int", "company_id");
			Vector secondVec = masterUtil.getDetail(ds, strQuery, support);
			if(secondVec.size()>0)
			{
				strResult="mailid";
				
			}
			else
			{
				strQuery = "select company_id from companymaster where company_phone='"+ comp_phone+"' and typeofcompany='"+ typeOfCompan+"'";
				support.setFieldVec("int", "company_id");
				Vector thirdVec = masterUtil.getDetail(ds, strQuery, support);
				if(thirdVec.size()>0)
				{
					strResult="phone";
					
				}
				else
				{
					strResult="success";
					
				}
				
			}
			
		}
		
		return strResult;
	}

	public Vector getCompanyDetail(DataSource ds, String comp_sname,
			String comp_email) {
		Support support=new Support();
		String strQuery="";
		strQuery = "select * from companymaster where where company_sname="
				+ comp_sname + " OR company_email=" + comp_email;

		support.setFieldVec("int", "company_id");
		support.setFieldVec("string", "typeofcompany");
		support.setFieldVec("string", "company_name");
		support.setFieldVec("string", "company_email");
		support.setFieldVec("int", "active");
		support.setFieldVec("string", "license_expiry_date");

		return masterUtil.getDetail(ds, strQuery, support);
	}
	/**
	 * This functin is use for getting conpany type by useing user ID
	 */
	public String getCompanyType(DataSource ds, int userId)
	{
		Support support=new Support();
		String status = "failure";
		String strQuery="";
		strQuery = "select companyid from loginmaster where userid="+userId;
		support.setFieldVec("int", "company_id");
		
		String strResult = masterUtil.getValue(ds, strQuery, support);
		if(!strResult.equalsIgnoreCase("failure"))
		{
			int compId = Integer.parseInt(strResult);
			Support support1 = new Support();
			strQuery = "select typeofcompany from companymaster where company_id = "+ compId;
			support.setFieldVec("string", "typeofcompany");
			status = masterUtil.getValue(ds, strQuery, support1);
		
		}
		return status;
	}
	public String getComp_Type(DataSource ds, int userId)
	{
		Support support=new Support();
		String strQuery="";
		strQuery = "select typeofcompany from companymaster where company_id = "+ userId;
		////System.out.println("Value For ZZZ in Response>>>>>>>>>>>>>"+strQuery);
		support.setFieldVec("string", "typeofcompany");
		String strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
	}


	/**
	 * Comment related function.
	 */
	/**
	 * This function use for insertng Data of comment.It returns status of
	 * insertion.
	 */
	public String insertComment(DataSource ds, Vector dataVec) {
		Support support=new Support();
		String strQuery="";
		strQuery = "Insert into commentsoncomplaints (commenttext, commentdate, complaintid, userid, commentTime, comapanyid, publish_flag, suspend_flag) values(?, ?, ?, ?, ?, ?, ?, ?)";

		support.setDataVec("string", dataVec.elementAt(0).toString().trim());
		support.setDataVec("string", dataVec.elementAt(1).toString().trim());
		support.setDataVec("int", dataVec.elementAt(2).toString().trim());
		support.setDataVec("int", dataVec.elementAt(3).toString().trim());
		support.setDataVec("string", dataVec.elementAt(4).toString().trim());
		support.setDataVec("int", dataVec.elementAt(5).toString().trim());
		support.setDataVec("int", dataVec.elementAt(6).toString().trim());
		support.setDataVec("int", dataVec.elementAt(7).toString().trim());

		return masterUtil.setData(ds, strQuery, support);
	}
	/**
	 * This function use for insertng Data of comment for Mentor.It returns status of
	 * insertion.
	 */
	public String insertCommentMentor(DataSource ds, Vector dataVec) {
		Support support=new Support();
		String strQuery="";
		strQuery = "Insert into commentsoncomplaints (commenttext, commentdate, complaintid, userid, commentTime, comapanyid, publish_flag, suspend_flag,mentored_id) values(?,?, ?, ?, ?, ?, ?, ?, ?)";

		support.setDataVec("string", dataVec.elementAt(0).toString().trim());
		support.setDataVec("string", dataVec.elementAt(1).toString().trim());
		support.setDataVec("int", dataVec.elementAt(2).toString().trim());
		support.setDataVec("int", dataVec.elementAt(3).toString().trim());
		support.setDataVec("string", dataVec.elementAt(4).toString().trim());
		support.setDataVec("int", dataVec.elementAt(5).toString().trim());
		support.setDataVec("int", dataVec.elementAt(6).toString().trim());
		support.setDataVec("int", dataVec.elementAt(7).toString().trim());
		support.setDataVec("int", dataVec.elementAt(8).toString().trim());

		return masterUtil.setData(ds, strQuery, support);
	}


	/**
	 * @param complaintId
	 *            for getting comment on this compaint.
	 * @return
	 */
	/**
	 * This function is use for getting comments on this perticuler complaint
	 * Id.
	 */
	public Vector getCommnetDetail(DataSource ds, int complaintId) {
		Support support=new Support();
		String strQuery="";
		strQuery = "select * from commentsoncomplaints where complaintid = "
				+ complaintId
				+ " and publish_flag=1 order by commentdate, commentTime";

		support.setFieldVec("string", "commenttext");
		support.setFieldVec("string", "commentdate");
		support.setFieldVec("int", "complaintid");
		support.setFieldVec("int", "userid");
		support.setFieldVec("string", "commentTime");
		support.setFieldVec("int", "comapanyid");
		support.setFieldVec("int", "publish_flag");
		support.setFieldVec("int", "suspend_flag");

		return masterUtil.getDetail(ds, strQuery, support);
	}

	/**
	 * This function is use for getting list of comments on complaint from
	 * commentoncomplaints table. This function takes complainId as argument and
	 * return the list of comments list which is given by user for that
	 * perticuler complaint of the perticuler user.
	 */
	public Vector getCommentsList(DataSource ds, int complainId) {
		Support support=new Support();
		String strQuery="";
		Vector resultVec = new Vector();

		strQuery = "select c.commenttext commenttext,c.commentdate commentdate,c.complaintid complaintid, " +
		"c.userid userid, c.commentTime commentTime, c.comapanyid comapanyid, l.first_name first_name, l.last_name last_name, l.city city  from commentsoncomplaints c, loginmaster l " +
		"where c.userid = l.userid and complaintid = "
		+ complainId
		+ " and publish_flag=1 group by c.commentid order by commentdate, commentTime";

		support.setFieldVec("string", "commenttext");	//0
		support.setFieldVec("string", "commentdate");	//1
		support.setFieldVec("int", "complaintid");		//2
		support.setFieldVec("int", "userid");			//3
		support.setFieldVec("string", "commentTime");	//4
		support.setFieldVec("int", "comapanyid");		//5
		support.setFieldVec("string", "first_name");	//6
		support.setFieldVec("string", "last_name");		//7
		support.setFieldVec("int", "city");				//8
		

		resultVec = masterUtil.getList(ds, strQuery, support);
		return resultVec;

	}

	/**
	 * This function is use for getting total no of comments.
	 */
	public int getcommentCount(DataSource ds, int complaintId) {
		
		String strQuery="";
		strQuery = "Select count(commentid) from commentsoncomplaints where  publish_flag=1 and complaintid ="
				+ complaintId;
		////////System.out.println("strQuery>>>>>>>comment>>>>>>>>"+strQuery);
		////////System.out.println("strQuery>>>>>>>comment>>>return>>>>>"+masterUtil.getCount(ds, strQuery));
		return masterUtil.getCount(ds, strQuery);
	}
	
	/**
	 * This function is use for getting total no of comments.
	 */
	public int getResponse_Count(DataSource ds, int complaintId) {
		
		String strQuery="";
		strQuery = "Select count(respid) from communication where  complaintid ="
			+ complaintId;////////System.out.println("strQuery>>>>>>>comment>>>>>>>>"+strQuery);
		////////System.out.println("strQuery>>>>>>>comment>>>return>>>>>"+masterUtil.getCount(ds, strQuery));
		return masterUtil.getCount(ds, strQuery);
	}


	/**
	 * Response related function .
	 */
	/**
	 * This function is use for inserting response corresponding to perticuler
	 * complaint.
	 */
	public String insertResponse(DataSource ds, Vector dataVec) {
		Support support=new Support();
		String strQuery="";
		strQuery = "Insert into communication (responsetext, responsedate, complaintid, userid, responseTime, con_resp_id, privateflag, indv_rflag, advt_rflag, entp_rflag) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		support.setDataVec("string", dataVec.elementAt(0).toString().trim());
		support.setDataVec("string", dataVec.elementAt(1).toString().trim());
		support.setDataVec("int", dataVec.elementAt(2).toString().trim());
		support.setDataVec("int", dataVec.elementAt(3).toString().trim());
		support.setDataVec("string", dataVec.elementAt(4).toString().trim());
		support.setDataVec("int", dataVec.elementAt(5).toString().trim());
		support.setDataVec("int", dataVec.elementAt(6).toString().trim());
		support.setDataVec("int", dataVec.elementAt(7).toString().trim());
		support.setDataVec("int", dataVec.elementAt(8).toString().trim());
		support.setDataVec("int", dataVec.elementAt(9).toString().trim());

		return masterUtil.setData(ds, strQuery, support);
	}
	
	/**
	 * This function is use for inserting response corresponding to perticuler
	 * complaint.
	 */
	public String insertMenResponse(DataSource ds, Vector dataVec) {
		Support support=new Support();
		String strQuery="";
		strQuery = "Insert into communication (responsetext, responsedate, complaintid, userid, responseTime, con_resp_id, privateflag, indv_rflag, advt_rflag, entp_rflag, a_type,com_flag) values(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		support.setDataVec("string", dataVec.elementAt(0).toString().trim());
		support.setDataVec("string", dataVec.elementAt(1).toString().trim());
		support.setDataVec("int", dataVec.elementAt(2).toString().trim());
		support.setDataVec("int", dataVec.elementAt(3).toString().trim());
		support.setDataVec("string", dataVec.elementAt(4).toString().trim());
		support.setDataVec("int", dataVec.elementAt(5).toString().trim());
		support.setDataVec("int", dataVec.elementAt(6).toString().trim());
		support.setDataVec("int", dataVec.elementAt(7).toString().trim());
		support.setDataVec("int", dataVec.elementAt(8).toString().trim());
		support.setDataVec("int", dataVec.elementAt(9).toString().trim());
		support.setDataVec("string", dataVec.elementAt(10).toString().trim());
		support.setDataVec("string", dataVec.elementAt(11).toString().trim());

		return masterUtil.setData(ds, strQuery, support);
	}


	/**
	 * This function is use for getting total no of communication.
	 */
	public int getResponseCount(DataSource ds, int complaintId) {
		
		String strQuery="";
		strQuery = "Select count(respid) from communication where privateflag=0 and complaintid ="
				+ complaintId;

		return masterUtil.getCount(ds, strQuery);
	}

	/**
	 * This function is use for getting response(communication) list of a
	 * perticuler complaint.
	 */
	public Vector getResponseList(DataSource ds, Vector dataVec) {
		Support support=new Support();
		String strQuery="";
		int complainId = Integer.parseInt(dataVec.elementAt(0).toString());
		
		String tempSqlEntp = "select c.respid respid, c.responsetext responsetext,c.responsedate responsedate, "+
		"c.userid userid, c.responseTime responseTime, cm.typeofcompany typeofcompany, "+ 
		"l.first_name first_name, l.last_name last_name, l.city city, cm.company_name company_name, c.indv_rflag indv_rflag, c.advt_rflag advt_rflag, c.entp_rflag entp_rflag "+
		"from communication c, loginmaster l, companymaster cm "+
		"where c.userid = l.userid and l.companyid = cm.company_id "+
		"and c.complaintid ="+complainId +" group by c.respid order by responsedate , responseTime";
		
		String tempSqlAdvt = "select c.respid respid, c.responsetext responsetext,c.responsedate responsedate, "+
		"c.userid userid, c.responseTime responseTime, cm.typeofcompany typeofcompany, "+ 
		"l.first_name first_name, l.last_name last_name, l.city city, cm.company_name company_name, c.indv_rflag indv_rflag, c.advt_rflag advt_rflag, c.entp_rflag entp_rflag "+
		"from communication c, loginmaster l, companymaster cm "+
		"where c.userid = l.userid and l.companyid = cm.company_id "+
		"and c.complaintid ="+complainId +" and privateflag=3 and cm.typeofcompany = 'Enterprise' "+
		"or c.userid = l.userid and l.companyid = cm.company_id and  c.complaintid ="+complainId +" and privateflag=1 and cm.typeofcompany = 'Advertiser' "+
		"or c.userid = l.userid and l.companyid = cm.company_id and  c.complaintid ="+complainId +" and privateflag=0  group by c.respid  order by responsedate , responseTime";
		
		String tempSqlIndv = "select c.respid respid, c.responsetext responsetext,c.responsedate responsedate, "+
		"c.userid userid, c.responseTime responseTime, cm.typeofcompany typeofcompany, "+ 
		"l.first_name first_name, l.last_name last_name, l.city city, cm.company_name company_name, c.indv_rflag indv_rflag, c.advt_rflag advt_rflag, c.entp_rflag entp_rflag "+
		"from communication c, loginmaster l, companymaster cm "+
		"where c.userid = l.userid and l.companyid = cm.company_id "+
		"and c.complaintid ="+complainId +" and privateflag=2 and cm.typeofcompany = 'Enterprise' "+
		"or  c.userid = l.userid and l.companyid = cm.company_id and c.complaintid ="+complainId +" and privateflag=1 and cm.typeofcompany = 'Consumer' "+
		"or  c.userid = l.userid and l.companyid = cm.company_id and c.complaintid ="+complainId +" and privateflag=0  group by c.respid  order by responsedate , responseTime";
		
		String tempSqlOuter = "select c.respid respid, c.responsetext responsetext,c.responsedate responsedate, "+
		"c.userid userid, c.responseTime responseTime, cm.typeofcompany typeofcompany, "+ 
		"l.first_name first_name, l.last_name last_name, l.city city, cm.company_name company_name, c.indv_rflag indv_rflag, c.advt_rflag advt_rflag, c.entp_rflag entp_rflag "+
		"from communication c, loginmaster l, companymaster cm "+
		"where c.userid = l.userid and l.companyid = cm.company_id "+
		"and c.complaintid ="+complainId +" and (privateflag=0 or privateflag=1) group by c.respid  order by responsedate , responseTime";

		String tempSqlCrpo = "select c.respid respid, c.responsetext responsetext,c.responsedate responsedate, "+
		"c.userid userid, c.responseTime responseTime, cm.typeofcompany typeofcompany, "+ 
		"l.first_name first_name, l.last_name last_name, l.city city, cm.company_name company_name, c.indv_rflag indv_rflag, c.advt_rflag advt_rflag, c.entp_rflag entp_rflag "+
		"from communication c, loginmaster l, companymaster cm "+
		"where c.userid = l.userid and l.companyid = cm.company_id "+
		"and c.complaintid ="+complainId +"   group by c.respid  order by responsedate , responseTime";
		////////////////////System.out.println("dataVec.elementAt(2).toString()>>>>>>>>>>>>"+dataVec.size());
		////////////////////System.out.println("dataVec.elementAt(2).toString()>>>>>>>>>>>>"+dataVec.elementAt(0).toString());
		////////////////////System.out.println("dataVec.elementAt(2).toString()>>>>>>>>>>>>"+dataVec.elementAt(1).toString());
		
		String type_of_company ="";
		 if(dataVec.size()>2)
		 {
			 type_of_company = dataVec.elementAt(2).toString();
		 }
		 else
		 {
			 type_of_company = dataVec.elementAt(1).toString();
		 }
		if (type_of_company.equalsIgnoreCase("Enterprise")) {
			strQuery = tempSqlEntp;
		} else if (type_of_company.equalsIgnoreCase("Advertiser")) {
			strQuery = tempSqlAdvt;
		} else if (type_of_company.equalsIgnoreCase("Consumer")) {
			strQuery = tempSqlIndv;
		} else if(type_of_company.equalsIgnoreCase("Corporates")) {
			strQuery = tempSqlCrpo;
		}else
		{
			strQuery = tempSqlOuter;
			
		}
		////////////////////System.out.println("complainIdcomplainId in root master for response"+complainId);
		////////////////////System.out.println("type_of_companytype_of_companytype_of_company in root master for response"+type_of_company);
		////////////////////System.out.println("strQuerystrQuerystrQuery in root master for response"+strQuery);
		support.setFieldVec("int", "respid");			//0
		support.setFieldVec("string", "responsetext");	//1
		support.setFieldVec("string", "responsedate");	//2
		support.setFieldVec("int", "userid");			//3
		support.setFieldVec("string", "responseTime");	//4
		support.setFieldVec("string", "typeofcompany");	//5
		support.setFieldVec("string", "first_name");	//6
		support.setFieldVec("string", "last_name");		//7
		support.setFieldVec("int", "city");				//8
		support.setFieldVec("string", "company_name");	//9
		support.setFieldVec("int", "indv_rflag");		//10
		support.setFieldVec("int", "advt_rflag");		//11
		support.setFieldVec("int", "entp_rflag");		//12
		
		Vector res=new Vector();
		res= masterUtil.getList(ds, strQuery, support);
		////////////////////System.out.println("dataVec.elementAt(2).toString()>>>>>>>>res>>>>>>>>>"+res);
		return res;
	}
	

	/**
	 * This function is use for getting response(communication) list of a
	 * perticuler complaint.
	 */
	public Vector getResponseListNew(DataSource ds, Vector dataVec) {
		Support support=new Support();
		String strQuery="";
		int complainId = Integer.parseInt(dataVec.elementAt(0).toString());
		
		
		String tempSqlIndv = "select c.respid respid, c.responsetext responsetext,c.responsedate responsedate, "+
		"c.userid userid, c.responseTime responseTime, cm.typeofcompany typeofcompany, "+ 
		"l.first_name first_name, l.last_name last_name, l.city city, cm.company_name company_name, c.indv_rflag indv_rflag, c.advt_rflag advt_rflag, c.entp_rflag entp_rflag "+
		"from communication c, loginmaster l, companymaster cm "+
		"where c.userid = l.userid and l.companyid = cm.company_id "+
		"and c.complaintid ="+complainId +"  "+
		"or  c.userid = l.userid and l.companyid = cm.company_id and c.complaintid ="+complainId +"  and cm.typeofcompany = 'Consumer' "+
		"or  c.userid = l.userid and l.companyid = cm.company_id and c.complaintid ="+complainId +"  group by c.respid  order by responsedate , responseTime";
		
		
		String type_of_company ="";
		 
			strQuery = tempSqlIndv;
			
		////////////////////System.out.println("complainIdcomplainId in root master for response"+complainId);
		////////////////////System.out.println("type_of_companytype_of_companytype_of_company in root master for response"+type_of_company);
		////////////////////System.out.println("strQuerystrQuerystrQuery in root master for response"+strQuery);
		support.setFieldVec("int", "respid");			//0
		support.setFieldVec("string", "responsetext");	//1
		support.setFieldVec("string", "responsedate");	//2
		support.setFieldVec("int", "userid");			//3
		support.setFieldVec("string", "responseTime");	//4
		support.setFieldVec("string", "typeofcompany");	//5
		support.setFieldVec("string", "first_name");	//6
		support.setFieldVec("string", "last_name");		//7
		support.setFieldVec("int", "city");				//8
		support.setFieldVec("string", "company_name");	//9
		support.setFieldVec("int", "indv_rflag");		//10
		support.setFieldVec("int", "advt_rflag");		//11
		support.setFieldVec("int", "entp_rflag");		//12
		
		Vector res=new Vector();
		res= masterUtil.getList(ds, strQuery, support);
		////////////////////System.out.println("dataVec.elementAt(2).toString()>>>>>>>>res>>>>>>>>>"+res);
		return res;
	}

	/**
	 * This function is use for getting mail test according to condition of
	 * complanit state and prosedure.
	 */
	public String getMailText(DataSource ds, Vector dataVec) {
		Support support=new Support();
		String strQuery="";
		String sendercompany = dataVec.elementAt(0).toString().trim();
		String recipienttype = dataVec.elementAt(1).toString().trim();
		String maildescription = dataVec.elementAt(2).toString().trim();
		strQuery = "select mailtext from mailtext where sendercompany = '"+ sendercompany + "' and recipienttype = '" + recipienttype+ "' and maildescription = '" + maildescription+"'";
		support.setFieldVec("string", "mailtext");

		return masterUtil.getValue(ds, strQuery, support);
	}

	/**
	* This function is use for getting user information in indv.
	*/
	public Vector getUserInfo(DataSource ds, int userid) {
	Support support=new Support();
	String strQuery="";
	
	strQuery = "select * from loginmaster where userid = " + userid;
	System.out.println("strQuery=>"+strQuery);
	System.out.println("Here In Indvaster=>");
	
	support.setFieldVec("int", "companyid");//0
	support.setFieldVec("string", "rid");//1
	support.setFieldVec("string", "loginname");//2
	support.setFieldVec("string", "password");//3
	support.setFieldVec("string", "first_name");//4
	support.setFieldVec("string", "last_name");//5
	support.setFieldVec("string", "city");//6
	support.setFieldVec("string", "state");//7
	support.setFieldVec("string", "email");//8
	support.setFieldVec("string", "address");//9
	support.setFieldVec("string", "mobile");//10
	support.setFieldVec("string", "country");//11
	support.setFieldVec("string", "phone");//12
	support.setFieldVec("string", "zip");//13
	support.setFieldVec("string", "gender");//14
	support.setFieldVec("string", "Birthday");//15
	support.setFieldVec("string", "maritalstatus");//16
	support.setFieldVec("string", "profession");//17
	support.setFieldVec("string", "annualincome");//18
	support.setFieldVec("string", "aboutus");//19
	support.setFieldVec("string", "education");//20
	support.setFieldVec("string", "photopath");//21
	support.setFieldVec("string", "college_id");//22
	support.setFieldVec("string", "course");//23
	support.setFieldVec("string", "major_sub");//24
	support.setFieldVec("string", "addmi_no");//25
	support.setFieldVec("int", "userid");//26
	support.setFieldVec("String", "other_coll_id");//27
	support.setFieldVec("String", "status");//28
	support.setFieldVec("String", "qure_cat");//29
	support.setFieldVec("String", "mentor_cat");//30
	
	support.setFieldVec("String", "pro_enable");//31
	support.setFieldVec("String", "pd_enable");//32
	
	
	
	return masterUtil.getDetail(ds, strQuery, support);
	}
	/**
	 * This function is use for getting user information.
	 */
	public Vector getUserInfo(DataSource ds, String loginName) {
		Support support=new Support();
		String strQuery="";

		strQuery = "select userid,companyid from loginmaster where loginname ='" + loginName+"'";

		support.setFieldVec("int", "userid");//0
		support.setFieldVec("int", "companyid");//1
		
		return masterUtil.getDetail(ds, strQuery, support);
	}
	/**
	 *  This function Update the User Information in the loginmaster table.
	 */
	public String updateUserInfo(DataSource ds,Vector paramVec){
		
		String strResult = "";	
		Support support=new Support();
	    String strQuery="";
	    System.out.println("paramVecparamVecparamVec- th Position-->"+paramVec.size());
	    
	    for(int i=0;i<17;i++){	
	    		System.out.println("paramVecparamVecparamVec--"+i+" th Position-->"+paramVec.elementAt(i).toString().trim());
	    }		
	    if(paramVec.size()<13)
	    {
	    	strQuery = "Update loginmaster set first_name=?, last_name=?, address=?, country=?,"+
			"state=?, city=?, zip=?, email=?, phone=?, mobile=?, rid=?  where userid=?";
	    	support.setDataVec("string", paramVec.elementAt(1).toString().trim());
			support.setDataVec("string", paramVec.elementAt(2).toString().trim());
			support.setDataVec("string", paramVec.elementAt(3).toString().trim());
			support.setDataVec("string", paramVec.elementAt(4).toString().trim());
			support.setDataVec("string", paramVec.elementAt(5).toString().trim());
			support.setDataVec("string", paramVec.elementAt(6).toString().trim());
			support.setDataVec("string", paramVec.elementAt(7).toString().trim());
			support.setDataVec("string", paramVec.elementAt(8).toString().trim());
			support.setDataVec("string", paramVec.elementAt(9).toString().trim());
			support.setDataVec("string", paramVec.elementAt(10).toString().trim());
			support.setDataVec("int", paramVec.elementAt(11).toString().trim());
			//support.setDataVec("String", paramVec.elementAt(12).toString().trim());
			
			support.setDataVec("int", paramVec.elementAt(0).toString().trim());
			
			
	    }else if(paramVec.size()<14)
	    {
	    	strQuery = "Update loginmaster set first_name=?, last_name=?, address=?, country=?,"+
			"state=?, city=?, zip=?, email=?, phone=?, mobile=?, rid=?,short_pro=? where userid=?";
			
	    	support.setDataVec("string", paramVec.elementAt(1).toString().trim());
			support.setDataVec("string", paramVec.elementAt(2).toString().trim());
			support.setDataVec("string", paramVec.elementAt(3).toString().trim());
			support.setDataVec("string", paramVec.elementAt(4).toString().trim());
			support.setDataVec("string", paramVec.elementAt(5).toString().trim());
			support.setDataVec("string", paramVec.elementAt(6).toString().trim());
			support.setDataVec("string", paramVec.elementAt(7).toString().trim());
			support.setDataVec("string", paramVec.elementAt(8).toString().trim());
			support.setDataVec("string", paramVec.elementAt(9).toString().trim());
			support.setDataVec("string", paramVec.elementAt(10).toString().trim());
			support.setDataVec("int", paramVec.elementAt(11).toString().trim());
			support.setDataVec("String", paramVec.elementAt(12).toString().trim());
			
			support.setDataVec("int", paramVec.elementAt(0).toString().trim());
			
	    }else
	    {
	    	strQuery = "Update loginmaster set first_name=?, last_name=?, address=?, country=?,"+
			"state=?, city=?, zip=?, email=?, phone=?, mobile=?, rid=?,short_pro=?, mentor_cat=?, qure_cat=? ,dep_id=?, des_id=? where userid=?";
			
	    	support.setDataVec("string", paramVec.elementAt(1).toString().trim());
			support.setDataVec("string", paramVec.elementAt(2).toString().trim());
			support.setDataVec("string", paramVec.elementAt(3).toString().trim());
			support.setDataVec("string", paramVec.elementAt(4).toString().trim());
			support.setDataVec("string", paramVec.elementAt(5).toString().trim());
			support.setDataVec("string", paramVec.elementAt(6).toString().trim());
			support.setDataVec("string", paramVec.elementAt(7).toString().trim());
			support.setDataVec("string", paramVec.elementAt(8).toString().trim());
			support.setDataVec("string", paramVec.elementAt(9).toString().trim());
			support.setDataVec("string", paramVec.elementAt(10).toString().trim());
			support.setDataVec("int", paramVec.elementAt(11).toString().trim());
			support.setDataVec("String", paramVec.elementAt(12).toString().trim());
			support.setDataVec("String", paramVec.elementAt(13).toString().trim());
			support.setDataVec("String", paramVec.elementAt(14).toString().trim());
			support.setDataVec("int", paramVec.elementAt(15).toString().trim());
			support.setDataVec("int", paramVec.elementAt(16).toString().trim());
			support.setDataVec("int", paramVec.elementAt(0).toString().trim());
	    	
	    }
		
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;	
				
	}



	/**
	 * This function is use for getting user rights id.
	 * 
	 */
	public int getrightsId(DataSource ds, int userid) {
		
		String strQuery="";
		int result = 0;

		strQuery = "select rid from loginmaster where userid ="+userid;

		result = masterUtil.getId(ds, strQuery, "rid");

		return result;

	}

	/**
	 * This function is use for getting user rights.
	 */
	public Vector getUserRights(DataSource ds, int rid) {

		Support support=new Support();
		String strQuery = "select * from loginrights where rid = "+rid;

		support.setFieldVec("int", "accmanagement"); 			//0
		support.setFieldVec("int", "accountinformation");		//1	
		support.setFieldVec("int", "billing_information");		//2
		support.setFieldVec("int", "change_password");			//3
		support.setFieldVec("int", "company_profile_modify");	//4
		support.setFieldVec("int", "senderid");					//5
		support.setFieldVec("int", "senderid_default");			//6
		support.setFieldVec("int", "senderid_deletion");		//7
		support.setFieldVec("int", "senderid_creation");		//8
		support.setFieldVec("int", "smsallotment");				//9
		support.setFieldVec("int", "users");					//10
		support.setFieldVec("int", "usercreation");				//11
		support.setFieldVec("int", "userdeletion");				//12
		support.setFieldVec("int", "usermodification");			//13
		support.setFieldVec("int", "order_more");				//14
		support.setFieldVec("int", "logindeletion");			//15
		support.setFieldVec("int", "consumer_flag");			//16
		support.setFieldVec("int", "pay_now");					//17
		support.setFieldVec("int", "admin_flag");				//18
		support.setFieldVec("int", "controlpanel");				//19
		support.setFieldVec("int", "communitycreation");		//20
		support.setFieldVec("int", "companydeletion");			//21
		support.setFieldVec("int", "existingcommunity");		//22
		support.setFieldVec("int", "packagemanagement");		//23
		support.setFieldVec("int", "smscallotment");			//24
		support.setFieldVec("int", "complaintmanagement");		//25
		support.setFieldVec("int", "comments");					//26
		support.setFieldVec("int", "company_management");		//27
		support.setFieldVec("int", "category");					//28
		support.setFieldVec("int", "complaints");				//29
		support.setFieldVec("int", "mis");						//30
		support.setFieldVec("int", "test_creation");						//31
		support.setFieldVec("int", "mentoring");						//32

		return masterUtil.getDetail(ds, strQuery, support);
	}

	/**
	 * This function is use for inserting Company Detail into companymaster
	 * table.
	 * 
	 */
	public String insertCompanyDetail(DataSource ds, Vector dataVec) {
		
		Support support=new Support();
		String 	strQuery = "Insert into companymaster (company_sname, company_name, company_address1, company_address2,"
				+ "company_city, company_state, company_country, company_zip, company_email, company_phone, category,"
				+ "total_sms, used_sms, total_user, sms_expiry_date, smsc_id, active, typeofcompany, senderID,"
				+ "total_senderid, communityrights, date_of_creation, license_expiry_date, hidecommunity,"
				+ "registered) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		support.setDataVec("string", dataVec.elementAt(0).toString().trim());
		support.setDataVec("string", dataVec.elementAt(1).toString().trim());
		support.setDataVec("string", dataVec.elementAt(2).toString().trim());
		support.setDataVec("string", dataVec.elementAt(3).toString().trim());
		support.setDataVec("string", dataVec.elementAt(4).toString().trim());
		support.setDataVec("string", dataVec.elementAt(5).toString().trim());
		support.setDataVec("string", dataVec.elementAt(6).toString().trim());
		support.setDataVec("string", dataVec.elementAt(7).toString().trim());
		support.setDataVec("string", dataVec.elementAt(8).toString().trim());
		support.setDataVec("string", dataVec.elementAt(9).toString().trim());
		support.setDataVec("string", dataVec.elementAt(10).toString().trim());
		support.setDataVec("int", dataVec.elementAt(11).toString().trim());
		support.setDataVec("int", dataVec.elementAt(12).toString().trim());
		support.setDataVec("int", dataVec.elementAt(13).toString().trim());
		support.setDataVec("string", dataVec.elementAt(14).toString().trim());
		support.setDataVec("int", dataVec.elementAt(15).toString().trim());
		support.setDataVec("int", dataVec.elementAt(16).toString().trim());
		support.setDataVec("string", dataVec.elementAt(17).toString().trim());
		support.setDataVec("string", dataVec.elementAt(18).toString().trim());
		support.setDataVec("int", dataVec.elementAt(19).toString().trim());
		support.setDataVec("string", dataVec.elementAt(20).toString().trim());
		support.setDataVec("string", dataVec.elementAt(21).toString().trim());
		support.setDataVec("string", dataVec.elementAt(22).toString().trim());
		support.setDataVec("int", dataVec.elementAt(23).toString().trim());
		support.setDataVec("int", dataVec.elementAt(24).toString().trim());

		return masterUtil.setData(ds, strQuery, support);
	}
	
	/**
	 * This function is use for inserting Company Detail into companymaster
	 * table.
	 * 
	 */
	public String insertCorpoDetail(DataSource ds, Vector dataVec) {
		
		Support support=new Support();
		String 	strQuery = "Insert into companymaster (company_sname, company_name, company_address1, company_address2,"
				+ "company_city, company_state, company_country, company_zip, company_email, company_phone, category,"
				+ "total_sms, used_sms, total_user, sms_expiry_date, smsc_id, active, typeofcompany, senderID,"
				+ "total_senderid, communityrights, date_of_creation, license_expiry_date, hidecommunity,"
				+ "registered, mtype) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		support.setDataVec("string", dataVec.elementAt(0).toString().trim());
		support.setDataVec("string", dataVec.elementAt(1).toString().trim());
		support.setDataVec("string", dataVec.elementAt(2).toString().trim());
		support.setDataVec("string", dataVec.elementAt(3).toString().trim());
		support.setDataVec("string", dataVec.elementAt(4).toString().trim());
		support.setDataVec("string", dataVec.elementAt(5).toString().trim());
		support.setDataVec("string", dataVec.elementAt(6).toString().trim());
		support.setDataVec("string", dataVec.elementAt(7).toString().trim());
		support.setDataVec("string", dataVec.elementAt(8).toString().trim());
		support.setDataVec("string", dataVec.elementAt(9).toString().trim());
		support.setDataVec("string", dataVec.elementAt(10).toString().trim());
		support.setDataVec("int", dataVec.elementAt(11).toString().trim());
		support.setDataVec("int", dataVec.elementAt(12).toString().trim());
		support.setDataVec("int", dataVec.elementAt(13).toString().trim());
		support.setDataVec("string", dataVec.elementAt(14).toString().trim());
		support.setDataVec("int", dataVec.elementAt(15).toString().trim());
		support.setDataVec("int", dataVec.elementAt(16).toString().trim());
		support.setDataVec("string", dataVec.elementAt(17).toString().trim());
		support.setDataVec("string", dataVec.elementAt(18).toString().trim());
		support.setDataVec("int", dataVec.elementAt(19).toString().trim());
		support.setDataVec("string", dataVec.elementAt(20).toString().trim());
		support.setDataVec("string", dataVec.elementAt(21).toString().trim());
		support.setDataVec("string", dataVec.elementAt(22).toString().trim());
		support.setDataVec("int", dataVec.elementAt(23).toString().trim());
		support.setDataVec("int", dataVec.elementAt(24).toString().trim());
		support.setDataVec("string", dataVec.elementAt(25).toString().trim());

		return masterUtil.setData(ds, strQuery, support);
	}

	/**
	 * This function is use for inserting User Detail into loginmaster table.
	 * 
	 */
	public String insertUserInfo(DataSource ds, Vector dataVec) {
	///	System.out.println("Address=>"+dataVec.elementAt(52).toString().trim()+" ,"+dataVec.elementAt(53).toString().trim()+", "+dataVec.elementAt(54).toString().trim());
		String Address_FM = "Delhi, India";
		Support support=new Support();
		String strQuery="";
		strQuery = "Insert into loginmaster (companyid, rid, loginname, password, first_name, last_name, email, phone,"
				+ "mobile, address, country, state, city, zip, photopath, country_shortname, creationDate, creationTime,"
				+ "lastmodifiedDate, lastmodifiedTime, logindeletion, allocatedsms, aboutus, age, annualincome, Birthday,"
				+ "BirthPlace, education, maritalstatus, profession, ringtone_received,"
				+ "usedsms, gender, login_type, college_id, course, major_sub, addmi_no, other_coll_id, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		support.setDataVec("int", dataVec.elementAt(0).toString().trim());
		support.setDataVec("int", dataVec.elementAt(1).toString().trim());
		support.setDataVec("string", dataVec.elementAt(2).toString().trim());
		support.setDataVec("string", dataVec.elementAt(3).toString().trim());
		support.setDataVec("string", dataVec.elementAt(4).toString().trim());
		support.setDataVec("string", dataVec.elementAt(5).toString().trim());
		support.setDataVec("string", dataVec.elementAt(6).toString().trim());
		support.setDataVec("string", dataVec.elementAt(7).toString().trim());
		support.setDataVec("string", dataVec.elementAt(8).toString().trim());
		support.setDataVec("string", Address_FM);
		support.setDataVec("string", dataVec.elementAt(10).toString().trim());
		support.setDataVec("string", dataVec.elementAt(11).toString().trim());
		support.setDataVec("string", dataVec.elementAt(12).toString().trim());
		support.setDataVec("string", dataVec.elementAt(13).toString().trim());
		support.setDataVec("string", dataVec.elementAt(14).toString().trim());
		support.setDataVec("string", dataVec.elementAt(15).toString().trim());
		support.setDataVec("string", dataVec.elementAt(16).toString().trim());
		support.setDataVec("string", dataVec.elementAt(17).toString().trim());
		support.setDataVec("string", dataVec.elementAt(18).toString().trim());
		support.setDataVec("string", dataVec.elementAt(19).toString().trim());
		support.setDataVec("int", dataVec.elementAt(20).toString().trim());
		support.setDataVec("int", dataVec.elementAt(21).toString().trim());
		support.setDataVec("string", dataVec.elementAt(22).toString().trim());
		support.setDataVec("string", dataVec.elementAt(23).toString().trim());
		support.setDataVec("string", dataVec.elementAt(24).toString().trim());
		support.setDataVec("string", dataVec.elementAt(25).toString().trim());
		support.setDataVec("string", dataVec.elementAt(26).toString().trim());
		support.setDataVec("string", dataVec.elementAt(27).toString().trim());
		support.setDataVec("string", dataVec.elementAt(28).toString().trim());
		support.setDataVec("string", dataVec.elementAt(29).toString().trim());
		support.setDataVec("int", dataVec.elementAt(30).toString().trim());
		support.setDataVec("int", dataVec.elementAt(31).toString().trim());
		support.setDataVec("string", dataVec.elementAt(32).toString().trim());
		support.setDataVec("string", dataVec.elementAt(33).toString().trim());
		support.setDataVec("string", dataVec.elementAt(34).toString().trim());
		support.setDataVec("string", dataVec.elementAt(35).toString().trim());
		support.setDataVec("string", dataVec.elementAt(36).toString().trim());
		support.setDataVec("string", dataVec.elementAt(37).toString().trim());
		support.setDataVec("string", dataVec.elementAt(38).toString().trim());
		support.setDataVec("string", dataVec.elementAt(39).toString().trim());
		

		return masterUtil.setData(ds, strQuery, support);
	}
	
	
	/**
	 * This function is use for inserting User Detail into loginmaster table.
	 * 
	 */
	public String insertUserInfo1(DataSource ds, Vector dataVec) {
		Support support=new Support();
		String strQuery="";
		strQuery = "Insert into loginmaster (companyid, rid, loginname, password, first_name, last_name, email, phone,"
				+ "mobile, address, country, state, city, zip, photopath, country_shortname, creationDate, creationTime,"
				+ "lastmodifiedDate, lastmodifiedTime, logindeletion, allocatedsms, aboutus, age, annualincome, Birthday,"
				+ "BirthPlace, education, maritalstatus, profession, ringtone_received,"
				+ "usedsms, gender, login_type, college_id, course, major_sub, addmi_no) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		support.setDataVec("int", dataVec.elementAt(0).toString().trim());
		support.setDataVec("int", dataVec.elementAt(1).toString().trim());
		support.setDataVec("string", dataVec.elementAt(2).toString().trim());
		support.setDataVec("string", dataVec.elementAt(3).toString().trim());
		support.setDataVec("string", dataVec.elementAt(4).toString().trim());
		support.setDataVec("string", dataVec.elementAt(5).toString().trim());
		support.setDataVec("string", dataVec.elementAt(6).toString().trim());
		support.setDataVec("string", dataVec.elementAt(7).toString().trim());
		support.setDataVec("string", dataVec.elementAt(8).toString().trim());
		support.setDataVec("string", dataVec.elementAt(9).toString().trim());
		support.setDataVec("string", dataVec.elementAt(10).toString().trim());
		support.setDataVec("string", dataVec.elementAt(11).toString().trim());
		support.setDataVec("string", dataVec.elementAt(12).toString().trim());
		support.setDataVec("string", dataVec.elementAt(13).toString().trim());
		support.setDataVec("string", dataVec.elementAt(14).toString().trim());
		support.setDataVec("string", dataVec.elementAt(15).toString().trim());
		support.setDataVec("string", dataVec.elementAt(16).toString().trim());
		support.setDataVec("string", dataVec.elementAt(17).toString().trim());
		support.setDataVec("string", dataVec.elementAt(18).toString().trim());
		support.setDataVec("string", dataVec.elementAt(19).toString().trim());
		support.setDataVec("int", dataVec.elementAt(20).toString().trim());
		support.setDataVec("int", dataVec.elementAt(21).toString().trim());
		support.setDataVec("string", dataVec.elementAt(22).toString().trim());
		support.setDataVec("string", dataVec.elementAt(23).toString().trim());
		support.setDataVec("string", dataVec.elementAt(24).toString().trim());
		support.setDataVec("string", dataVec.elementAt(25).toString().trim());
		support.setDataVec("string", dataVec.elementAt(26).toString().trim());
		support.setDataVec("string", dataVec.elementAt(27).toString().trim());
		support.setDataVec("string", dataVec.elementAt(28).toString().trim());
		support.setDataVec("string", dataVec.elementAt(29).toString().trim());
		support.setDataVec("int", dataVec.elementAt(30).toString().trim());
		support.setDataVec("int", dataVec.elementAt(31).toString().trim());
		support.setDataVec("string", dataVec.elementAt(32).toString().trim());
		support.setDataVec("string", dataVec.elementAt(33).toString().trim());
		support.setDataVec("string", dataVec.elementAt(34).toString().trim());
		support.setDataVec("string", dataVec.elementAt(35).toString().trim());
		support.setDataVec("string", dataVec.elementAt(36).toString().trim());
		support.setDataVec("string", dataVec.elementAt(37).toString().trim());
		
		////////////////////System.out.println("strQuery.........."+strQuery);
		////////////////////System.out.println("dataVec.........."+dataVec);

		return masterUtil.setData(ds, strQuery, support);
		
		
	}
	
	/**
	 * This function is use for inserting User Detail into loginmaster table.
	 * 
	 */
	public String insertUserInfoCorpo(DataSource ds, Vector dataVec) {
		Support support=new Support();
		String strQuery="";
		strQuery = "Insert into loginmaster (companyid, rid, loginname, password, first_name, last_name, email, phone,"
				+ "mobile, address, country, state, city, zip, photopath, country_shortname, creationDate, creationTime,"
				+ "lastmodifiedDate, lastmodifiedTime, logindeletion, allocatedsms, aboutus, age, annualincome, Birthday,"
				+ "BirthPlace, education, maritalstatus, profession, ringtone_received,"
				+ "usedsms, gender, login_type, college_id, course, major_sub, addmi_no, mentor_cat, qure_cat,dep_id,des_id) values(?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		support.setDataVec("int", dataVec.elementAt(0).toString().trim());
		support.setDataVec("int", dataVec.elementAt(1).toString().trim());
		support.setDataVec("string", dataVec.elementAt(2).toString().trim());
		support.setDataVec("string", dataVec.elementAt(3).toString().trim());
		support.setDataVec("string", dataVec.elementAt(4).toString().trim());
		support.setDataVec("string", dataVec.elementAt(5).toString().trim());
		support.setDataVec("string", dataVec.elementAt(6).toString().trim());
		support.setDataVec("string", dataVec.elementAt(7).toString().trim());
		support.setDataVec("string", dataVec.elementAt(8).toString().trim());
		support.setDataVec("string", dataVec.elementAt(9).toString().trim());
		support.setDataVec("string", dataVec.elementAt(10).toString().trim());
		support.setDataVec("string", dataVec.elementAt(11).toString().trim());
		support.setDataVec("string", dataVec.elementAt(12).toString().trim());
		support.setDataVec("string", dataVec.elementAt(13).toString().trim());
		support.setDataVec("string", dataVec.elementAt(14).toString().trim());
		support.setDataVec("string", dataVec.elementAt(15).toString().trim());
		support.setDataVec("string", dataVec.elementAt(16).toString().trim());
		support.setDataVec("string", dataVec.elementAt(17).toString().trim());
		support.setDataVec("string", dataVec.elementAt(18).toString().trim());
		support.setDataVec("string", dataVec.elementAt(19).toString().trim());
		support.setDataVec("int", dataVec.elementAt(20).toString().trim());
		support.setDataVec("int", dataVec.elementAt(21).toString().trim());
		support.setDataVec("string", dataVec.elementAt(22).toString().trim());
		support.setDataVec("string", dataVec.elementAt(23).toString().trim());
		support.setDataVec("string", dataVec.elementAt(24).toString().trim());
		support.setDataVec("string", dataVec.elementAt(25).toString().trim());
		support.setDataVec("string", dataVec.elementAt(26).toString().trim());
		support.setDataVec("string", dataVec.elementAt(27).toString().trim());
		support.setDataVec("string", dataVec.elementAt(28).toString().trim());
		support.setDataVec("string", dataVec.elementAt(29).toString().trim());
		support.setDataVec("int", dataVec.elementAt(30).toString().trim());
		support.setDataVec("int", dataVec.elementAt(31).toString().trim());
		support.setDataVec("string", dataVec.elementAt(32).toString().trim());
		support.setDataVec("string", dataVec.elementAt(33).toString().trim());
		support.setDataVec("string", dataVec.elementAt(34).toString().trim());
		support.setDataVec("string", dataVec.elementAt(35).toString().trim());
		support.setDataVec("string", dataVec.elementAt(36).toString().trim());
		support.setDataVec("string", dataVec.elementAt(37).toString().trim());
		support.setDataVec("string", dataVec.elementAt(38).toString().trim());
		support.setDataVec("string", dataVec.elementAt(39).toString().trim());
		support.setDataVec("int", dataVec.elementAt(40).toString().trim());
		support.setDataVec("int", dataVec.elementAt(41).toString().trim());
		
		////////////////////System.out.println("strQuery.........."+strQuery);
		////////////////////System.out.println("dataVec.........."+dataVec);

		return masterUtil.setData(ds, strQuery, support);
		
		
	}
	
	/**
	 *  This function Update the User Information in the loginmaster table.
	 */
	public String updateUserInfoCorpo(DataSource ds,Vector paramVec){
		
		String strResult = "";	
		Support support=new Support();
	    String strQuery="";
	  ////////System.out.println("paramVec>>>>>>>in root master>>>>,,/.,.,"+paramVec.size());
	    
	    	strQuery = "Update loginmaster set first_name=?, last_name=?, address=?, country=?,"+
			"state=?, city=?, zip=?, email=?, phone=?, mobile=?, rid=?, mentor_cat=?, qure_cat=? ,dep_id=?, des_id=? where userid=?";
	    	support.setDataVec("string", paramVec.elementAt(1).toString().trim());
			support.setDataVec("string", paramVec.elementAt(2).toString().trim());
			support.setDataVec("string", paramVec.elementAt(3).toString().trim());
			support.setDataVec("string", paramVec.elementAt(4).toString().trim());
			support.setDataVec("string", paramVec.elementAt(5).toString().trim());
			support.setDataVec("string", paramVec.elementAt(6).toString().trim());
			support.setDataVec("string", paramVec.elementAt(7).toString().trim());
			support.setDataVec("string", paramVec.elementAt(8).toString().trim());
			support.setDataVec("string", paramVec.elementAt(9).toString().trim());
			support.setDataVec("string", paramVec.elementAt(10).toString().trim());
			support.setDataVec("int", paramVec.elementAt(11).toString().trim());
			support.setDataVec("string", paramVec.elementAt(12).toString().trim());
			support.setDataVec("string", paramVec.elementAt(13).toString().trim());	
			support.setDataVec("int", paramVec.elementAt(14).toString().trim());
			support.setDataVec("int", paramVec.elementAt(15).toString().trim());
			support.setDataVec("int", paramVec.elementAt(0).toString().trim());
			
	  		
		
		strResult = masterUtil.setData(ds, strQuery, support);
		
		 ////System.out.println("strResult>>>>>>>in root master>>>>.....,"+strResult);		

		 
		return strResult;
		
	}
	
	
	
public String UpdatepopupFlag(DataSource ds,String userid, String flag){
		
		String strResult = "";	
		Support support=new Support();
	    String strQuery="";
	  ////////System.out.println("paramVec>>>>>>>in root master>>>>,,/.,.,"+paramVec.size());
	    
	    	strQuery = "Update loginmaster set info_flag=? where userid=?";
	    	support.setDataVec("int", flag);
			support.setDataVec("int", userid);
			
	  		
		
		strResult = masterUtil.setData(ds, strQuery, support);
		
		 ////////System.out.println("strResult>>>>>>>in root master>>>>.....,"+strResult);		

		 
		return strResult;
		
	}


	/**
	 * This function is use for getting user rights.
	 */
	public String insertUserRights(DataSource ds, Vector dataVec) {
		Support support=new Support();
		String strQuery="";

		strQuery = "Insert into loginrights (accmanagement, accountinformation, billing_information, change_password,"
				+ "company_profile_modify, senderid, senderid_default, senderid_deletion,senderid_creation, smsallotment, users, usercreation, userdeletion,"
				+ "usermodification, order_more, logindeletion, consumer_flag, pay_now, admin_flag, controlpanel, communitycreation,"
				+ "companydeletion, existingcommunity, packagemanagement, smscallotment, complaintmanagement, comments,"
				+ "company_management, category ,complaints, mis) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		support.setDataVec("int", dataVec.elementAt(0).toString().trim());
		support.setDataVec("int", dataVec.elementAt(1).toString().trim());
		support.setDataVec("int", dataVec.elementAt(2).toString().trim());
		support.setDataVec("int", dataVec.elementAt(3).toString().trim());
		support.setDataVec("int", dataVec.elementAt(4).toString().trim());
		support.setDataVec("int", dataVec.elementAt(5).toString().trim());
		support.setDataVec("int", dataVec.elementAt(6).toString().trim());
		support.setDataVec("int", dataVec.elementAt(7).toString().trim());
		support.setDataVec("int", dataVec.elementAt(8).toString().trim());
		support.setDataVec("int", dataVec.elementAt(9).toString().trim());
		support.setDataVec("int", dataVec.elementAt(10).toString().trim());
		support.setDataVec("int", dataVec.elementAt(11).toString().trim());
		support.setDataVec("int", dataVec.elementAt(12).toString().trim());
		support.setDataVec("int", dataVec.elementAt(13).toString().trim());
		support.setDataVec("int", dataVec.elementAt(14).toString().trim());
		support.setDataVec("int", dataVec.elementAt(15).toString().trim());
		support.setDataVec("int", dataVec.elementAt(16).toString().trim());
		support.setDataVec("int", dataVec.elementAt(17).toString().trim());
		support.setDataVec("int", dataVec.elementAt(18).toString().trim());
		support.setDataVec("int", dataVec.elementAt(19).toString().trim());
		support.setDataVec("int", dataVec.elementAt(20).toString().trim());
		support.setDataVec("int", dataVec.elementAt(21).toString().trim());
		support.setDataVec("int", dataVec.elementAt(22).toString().trim());
		support.setDataVec("int", dataVec.elementAt(23).toString().trim());
		support.setDataVec("int", dataVec.elementAt(24).toString().trim());
		support.setDataVec("int", dataVec.elementAt(25).toString().trim());
		support.setDataVec("int", dataVec.elementAt(26).toString().trim());
		support.setDataVec("int", dataVec.elementAt(27).toString().trim());
		support.setDataVec("int", dataVec.elementAt(28).toString().trim());
		support.setDataVec("int", dataVec.elementAt(29).toString().trim());
		support.setDataVec("int", dataVec.elementAt(30).toString().trim());

		return masterUtil.setData(ds, strQuery, support);
	}
	/**
	 * This function is use for rights for a Group.
	 */
	public Vector getGroupRightsId(DataSource ds, Vector dataVec) {
		
		String groupName = dataVec.elementAt(0).toString();
		int companyId = Integer.parseInt(dataVec.elementAt(1).toString());

		Support support=new Support();
		String strQuery = "select rid from loginrights where groupname = "+groupName+" and companyid = "+companyId;

		support.setFieldVec("int", "rid"); 			//0
		

		return masterUtil.getDetail(ds, strQuery, support);
	}
	/**
	 * This function use for getting total no. of Group for a companys .
	 * @return
	 */	
		public int getGroupCount(DataSource ds,int compId){
			
			int result = 0;
			
									
			   String strQuery="";
			  
			
				strQuery = "Select count(*) rowCount from loginrights where companyid= "+compId;
			
			result = masterUtil.getCount(ds, strQuery);
			return result;
		}
		
		
		/**
		 * This function is use for getting list of Rights Group from `loginrights`.
		 * It returns a list within limit which is given by min and max variable.
		 */
			public Vector getGroupRightList(DataSource ds,int companyid){
				
				Vector resultVec = new Vector();
				
				Support support=new Support();
				   String strQuery="";
				
					strQuery = "select * from loginrights where companyid="+companyid;
				
				
				/**
				 * set all the fileds type and name for loginmaster table by using
				 * Support class object.
				 */
				support.setFieldVec("int", "companyid");
				support.setFieldVec("int", "rid");								
				support.setFieldVec("String", "groupname");
				
				resultVec = masterUtil.getList(ds, strQuery, support);				
				
				return resultVec;
			}
	/**
	 * This function is use for getting Group rights.
	 */
	public String insertGroupRights(DataSource ds, Vector dataVec) {
		Support support=new Support();
		String strQuery="";
		
		
		strQuery = "Insert into loginrights (accmanagement, accountinformation, billing_information, change_password,"
				+ "company_profile_modify, senderid, senderid_default, senderid_deletion,senderid_creation, smsallotment, users, usercreation, userdeletion,"
				+ "usermodification, order_more,   pay_now,  controlpanel, communitycreation,"
				+ "companydeletion, existingcommunity, packagemanagement, smscallotment, complaintmanagement, comments,"
				+ "company_management, category ,complaints, mis, companyid, groupname) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		support.setDataVec("int", dataVec.elementAt(0).toString().trim());
		support.setDataVec("int", dataVec.elementAt(1).toString().trim());
		support.setDataVec("int", dataVec.elementAt(2).toString().trim());
		support.setDataVec("int", dataVec.elementAt(3).toString().trim());
		support.setDataVec("int", dataVec.elementAt(4).toString().trim());
		support.setDataVec("int", dataVec.elementAt(5).toString().trim());
		support.setDataVec("int", dataVec.elementAt(6).toString().trim());
		support.setDataVec("int", dataVec.elementAt(7).toString().trim());
		support.setDataVec("int", dataVec.elementAt(8).toString().trim());
		support.setDataVec("int", dataVec.elementAt(9).toString().trim());
		support.setDataVec("int", dataVec.elementAt(10).toString().trim());
		support.setDataVec("int", dataVec.elementAt(11).toString().trim());
		support.setDataVec("int", dataVec.elementAt(12).toString().trim());
		support.setDataVec("int", dataVec.elementAt(13).toString().trim());
		support.setDataVec("int", dataVec.elementAt(14).toString().trim());
		support.setDataVec("int", dataVec.elementAt(15).toString().trim());
		support.setDataVec("int", dataVec.elementAt(16).toString().trim());
		support.setDataVec("int", dataVec.elementAt(17).toString().trim());
		support.setDataVec("int", dataVec.elementAt(18).toString().trim());
		support.setDataVec("int", dataVec.elementAt(19).toString().trim());
		support.setDataVec("int", dataVec.elementAt(20).toString().trim());
		support.setDataVec("int", dataVec.elementAt(21).toString().trim());
		support.setDataVec("int", dataVec.elementAt(22).toString().trim());
		support.setDataVec("int", dataVec.elementAt(23).toString().trim());
		support.setDataVec("int", dataVec.elementAt(24).toString().trim());
		support.setDataVec("int", dataVec.elementAt(25).toString().trim());
		support.setDataVec("int", dataVec.elementAt(26).toString().trim());
		support.setDataVec("int", dataVec.elementAt(27).toString().trim());
		support.setDataVec("int", dataVec.elementAt(28).toString().trim());
		support.setDataVec("String", dataVec.elementAt(29).toString().trim());
		
		
		

		return masterUtil.setData(ds, strQuery, support);
	}
	/** get country list for
	 * 
	 */
	public Vector getCountries(DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		//////////////////////System.out.println("Start in country");
		
		 strQuery="select * from countries where active=1 order by country_name";
		 
		 support.setFieldVec("int", "id");
		 support.setFieldVec("String", "country_code");
		 support.setFieldVec("String", "country_name");
		 //Vector <Vector>tempResultVec = new Vector<Vector>();
		 //tempResultVec=(Vector)masterUtil.getList(ds, strQuery, support);
		 Vector resultVec=(Vector)masterUtil.getList(ds, strQuery, support);
		 Vector<String> tempVec = new Vector<String>();
		 tempVec.add("0");
		 tempVec.add("+0");
	     tempVec.add("Select");
		 resultVec.add(0, tempVec);
		 Vector<String>tempVec1 = new Vector<String>();
			tempVec1.add("-1");
			tempVec1.add("+0");
			tempVec1.add("Others");
		resultVec.add(tempVec1);
		 //	////////////////////System.out.println("tempResultVec>>>>>>>"+resultVec);
		 /*for(int i=0;i<tempResultVec.size();i++)
			{
			 	
			 	Vector <Vector>tempManVec=new Vector<Vector>();
			 	tempManVec=(Vector)tempResultVec.elementAt(i);
			 	////////////////////System.out.println("tempManVec>>>>>>>"+tempManVec);
			 	////////////////////System.out.println("tempManVec.elementAt(0).toString()>>>>>>>"+tempManVec.elementAt(0).toString());
				 ////////////////////System.out.println("tempManVec.elementAt(1).toString()>>>>>>>"+tempManVec.elementAt(1).toString());
			 	String tempCodestr = tempManVec.elementAt(0).toString()+"~"+tempManVec.elementAt(1).toString();
			 	Vector<String> tempVec1= new Vector<String>();
			 	tempVec1.add(tempCodestr);
			 	tempVec1.add(tempManVec.elementAt(2).toString());
			 	resultVec.add(tempVec1);
			}*/
		 //return resultVec;
		 return resultVec;
	}
	
	/** get state
	 * 
	 * 
	 */
	/**
	 * get All places
	 */
	public Vector getAllPlace(int cid, DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		
				 strQuery = "select * from places where active=1 and CountryID="+cid+" order by PlaceName";
				 ////////////////////System.out.println("..."+strQuery);
			 //}
			 
			 support.setFieldVec("int", "PlaceID");
			 support.setFieldVec("String", "Place");
			 Vector <Vector>tempResultVec  = new Vector<Vector>();
			 tempResultVec=(Vector)masterUtil.getList(ds, strQuery, support);
			 for(int i=0; i<tempResultVec.size();i++)
			 {
				 resultVec.add(tempResultVec.elementAt(i));
			 }
					
		
		 Vector<String>tempVec2 = new Vector<String>();
		 tempVec2.add("-1");
		 tempVec2.add("Others");
		 resultVec.add(tempVec2);
		 return resultVec;
	}
	
	public Vector getAllCategory(DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		
				 strQuery = "select * from category where cat_enabled=1 order by cat_name";
				 ////////////////////System.out.println("..."+strQuery);
			 //}
			 
			 support.setFieldVec("int", "cat_id");
			 support.setFieldVec("String", "cat_name");
			 Vector <Vector>tempResultVec  = new Vector<Vector>();
			 tempResultVec=(Vector) masterUtil.getList(ds, strQuery, support);
			 for(int i=0; i<tempResultVec.size();i++)
			 {
				 resultVec.add(tempResultVec.elementAt(i));
			 }
					
		
		 Vector<String>tempVec2 = new Vector<String>();
		 tempVec2.add("-1");
		 tempVec2.add("Others");
		 resultVec.add(tempVec2);
		 return resultVec;
	}
	
	public Vector getAllBlogCategory(DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		
				 strQuery = "select * from classifiedcategory where cat_enabled=1 order by cat_name";
				 ////////////////////System.out.println("..."+strQuery);
			 //}
			 
			 support.setFieldVec("int", "cat_id");
			 support.setFieldVec("String", "cat_name");
			 Vector <Vector>tempResultVec  = new Vector<Vector>();
			 tempResultVec=(Vector) masterUtil.getList(ds, strQuery, support);
			 for(int i=0; i<tempResultVec.size();i++)
			 {
				 resultVec.add(tempResultVec.elementAt(i));
			 }
					
		
		 Vector<String>tempVec2 = new Vector<String>();
		 tempVec2.add("-1");
		 tempVec2.add("Others");
		 resultVec.add(tempVec2);
		 return resultVec;
	}
	

	public Vector getTagList(DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		tempVec.add("0");
		tempVec.add("Select Status");
		resultVec.add(tempVec);
		
				 strQuery = "select * from tages";
				 ////////////////////System.out.println("..."+strQuery);
			 //}
			 
			 support.setFieldVec("int", "tagid");
			 support.setFieldVec("String", "tagname");
			 Vector <Vector>tempResultVec  = new Vector<Vector>();
			 tempResultVec=(Vector) masterUtil.getList(ds, strQuery, support);
			 for(int i=0; i<tempResultVec.size();i++)
			 {
				 resultVec.add(tempResultVec.elementAt(i));
			 }
		
		 return resultVec;
	}

	/**
	 * get places
	 */
	public Vector getPlace(int cid, int sid, DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		
		//////System.out.println("sid........... .........."+sid);
		
		 if(cid>0 && sid >0)
		 {
			
			 if(sid==10)
			 {
				 strQuery = "select * from places where active=1 and CountryID="+cid+" and otherState="+sid+" order by PlaceName";
					
			 }
			 else
			 {
				 strQuery = "select * from places where active=1 and CountryID="+cid+" and StateID="+sid+" order by PlaceName";
				
			}
			 
			 support.setFieldVec("int", "PlaceID");
			 support.setFieldVec("String", "Place");
			 Vector <Vector>tempResultVec  = new Vector<Vector>();
			 tempResultVec=(Vector)masterUtil.getList(ds, strQuery, support);
			 for(int i=0; i<tempResultVec.size();i++)
			 {
				 resultVec.add(tempResultVec.elementAt(i));
			 }
					
		 }
		 Vector<String>tempVec2 = new Vector<String>();
		 tempVec2.add("-1");
		 tempVec2.add("Others");
		 resultVec.add(tempVec2);
		 return resultVec;
	}
	
//	get brand list
	public Vector getBrand(int catid, DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		//Consumer cr = null; 
		
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		
		strQuery = "select company_id, company_name  from companymaster where active=1 and typeofcompany='Corporates'";
		support.setFieldVec("int", "company_id");
		support.setFieldVec("String", "company_name");
		resultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		
		 
	 return resultVec;
	}
	
	
	
//	get brand list
	public Vector getBrand(DataSource ds,int catid)
	{
		Support support=new Support();
		String strQuery="";
		//Consumer cr = null; 
		
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		
		Vector<String>tempVec = new Vector<String>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		strQuery = "select company_id, company_name  from companymaster where active=1 and typeofcompany='Corporates' and category like '%"+catid+"%'";
		support.setFieldVec("int", "company_id");
		support.setFieldVec("String", "company_name");
		////////////////////System.out.println("strQuery>>>>>>>>>>>>>"+strQuery);
		resultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		
		 
	 return resultVec;
	}
	
//	get brand list
	public Vector getBrandByCat(DataSource ds,int catid)
	{
		Support support=new Support();
		String strQuery="";
		//Consumer cr = null; 
		
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		
		strQuery = "select company_id, company_name  from companymaster where active=1 and typeofcompany='Corporates' and category like '%"+catid+"%'";
		support.setFieldVec("int", "company_id");
		support.setFieldVec("String", "company_name");
		////////////////////System.out.println("strQuery>>>>>>>>>>>>>"+strQuery);
		resultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		
		 
	 return resultVec;
	}
	
	public Vector getCommunity(DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		//Consumer cr = null; 
		
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		
		//tempVec.add("0");
		//tempVec.add("Select");
		//resultVec.add(tempVec);
				
			strQuery = "select * from companymaster where hidecommunity=0 and typeofcompany=\"Consumer\"";
			support.setFieldVec("int", "company_id");
			support.setFieldVec("String", "company_name");
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			for(int i=0;i<tempResultVec.size();i++)
			{
				resultVec.add((Vector)tempResultVec.elementAt(i));
			}
			
		 
		 return resultVec;
	}
	
	/*
	 * Search all college list on the base of Country, State and City.
	 */
	
	public Vector getCollege(DataSource ds, String ccid, String csid, String cctiyid)
	{
		Support support=new Support();
		String strQuery="";
		//Consumer cr = null; 
		
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		
			
		//"select * from companymaster where typeofcompany=\"Advertiser\"'
			strQuery = "select * from othercollegeid where coll_city='"+cctiyid+"' and cat_enable= 1";
			support.setFieldVec("int", "other_cid");
			support.setFieldVec("String", "cat_name");
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			
			
		 
		 return tempResultVec;
	}
	
	public Vector getCollegeCom(DataSource ds,String StrArr,String collstate,String  collcity)
	{
		Support support=new Support();
		String strQuery="";
		//Consumer cr = null; 
		
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		
			
		    strQuery = "select * from companymaster where typeofcompany=\"Advertiser\" and company_city='"+collcity+"' and registered= 1";
			//strQuery = "select * from othercollegeid where coll_contry='"+ccid+"' and coll_state='"+csid+"' and coll_city='"+cctiyid+"' and cat_enable= 1";
			support.setFieldVec("int", "company_id");
			support.setFieldVec("String", "company_sname");
			support.setFieldVec("String", "company_name");
			
			
			
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			
			
			
		 
		 return tempResultVec;
	}
	
	/*
	 * Search all college list on the base of Country and  State.
	 */
	
	public Vector getCollegeState(DataSource ds, String ccid, String csid)
	{
		Support support=new Support();
		String strQuery="";
		//Consumer cr = null; 
		
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		
			
		//"select * from companymaster where typeofcompany=\"Advertiser\"'
			strQuery = "select * from othercollegeid where coll_contry='"+ccid+"' and coll_state='"+csid+"' and cat_enable= 1";
			support.setFieldVec("int", "other_cid");
			support.setFieldVec("String", "cat_name");
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			
			
		 
		 return tempResultVec;
	}
	
	public Vector getCollegeComState(DataSource ds,String StrArr,String collstate)
	{
		Support support=new Support();
		String strQuery="";
		//Consumer cr = null; 
		
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		
			
		    strQuery = "select * from companymaster where typeofcompany=\"Advertiser\" and company_country='"+StrArr+"' and company_state='"+collstate+"'  and registered= 1";
			//strQuery = "select * from othercollegeid where coll_contry='"+ccid+"' and coll_state='"+csid+"' and coll_city='"+cctiyid+"' and cat_enable= 1";
			support.setFieldVec("int", "company_id");
			support.setFieldVec("String", "company_sname");
			
			
			
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			
			
			
		 
		 return tempResultVec;
	}
	
	/*
	 * Search all college list on the base of Country. 
	 */
	
	public Vector getCollegeCountry(DataSource ds, String ccid)
	{
		Support support=new Support();
		String strQuery="";
		//Consumer cr = null; 
		
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		
			
		//"select * from companymaster where typeofcompany=\"Advertiser\"'
			strQuery = "select * from othercollegeid where coll_contry='"+ccid+"'  and cat_enable= 1";
			support.setFieldVec("int", "other_cid");
			support.setFieldVec("String", "cat_name");
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			
			
		 
		 return tempResultVec;
	}
	
	public Vector getCollegeComCountry(DataSource ds,String StrArr)
	{
		Support support=new Support();
		String strQuery="";
		//Consumer cr = null; 
		
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		
			
		    strQuery = "select * from companymaster where typeofcompany=\"Advertiser\" and company_country='"+StrArr+"'  and registered= 1";
			//strQuery = "select * from othercollegeid where coll_contry='"+ccid+"' and coll_state='"+csid+"' and coll_city='"+cctiyid+"' and cat_enable= 1";
			support.setFieldVec("int", "company_id");
			support.setFieldVec("String", "company_sname");
			
			
			
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			
			
			
		 
		 return tempResultVec;
	}
	
	
	public Vector getCorpoList(DataSource ds, int mcat)
	{
		Support support=new Support();
		String strQuery="";
		//Consumer cr = null; 
		
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);		
		strQuery = "select distinct C.company_id, C.company_name  from companymaster C,loginmaster L where C.company_id=L.companyid and L.rid!=0 and L.logindeletion=0 and C.typeofcompany=\"Corporates\" and C.mtype like '%"+mcat+"%' and L.Mentor_cat like '%"+mcat+"%'order by company_name";
		///System.out.println("strQuerystrQuery>>>>>>>>>Cate>>>>>>>>"+strQuery);	
		support.setFieldVec("int", "company_id");
			support.setFieldVec("String", "company_name");
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			for(int i=0;i<tempResultVec.size();i++)
			{
				resultVec.add((Vector)tempResultVec.elementAt(i));
			}
			
		 
		 return resultVec;
	}
	
	
	public Vector getCollege(DataSource ds, int mcat)
	{
		Support support=new Support();
		String strQuery="";
		//Consumer cr = null; 
		
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);		
			strQuery = "select distinct C.company_id, C.company_name  from companymaster C,loginmaster L where C.company_id=L.companyid and L.rid!=0 and L.logindeletion=0 and C.typeofcompany=\"Advertiser\" and C.mtype like '%"+mcat+"%' and L.Mentor_cat like '%"+mcat+"%'order by company_name";
			support.setFieldVec("int", "company_id");
			support.setFieldVec("String", "company_name");
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			for(int i=0;i<tempResultVec.size();i++)
			{
				resultVec.add((Vector)tempResultVec.elementAt(i));
			}
			
		 
		 return resultVec;
	}
	
	
	public Vector getCorpoUserList(DataSource ds, int mcat)
	{
		Support support=new Support();
		String strQuery="";
		//Consumer cr = null; 
		
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		tempVec.add(" ");
		resultVec.add(tempVec);
				
			strQuery = "select * from loginmaster where companyid="+mcat;
			support.setFieldVec("int", "userid");
			support.setFieldVec("String", "first_name");
			support.setFieldVec("String", "last_name");
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			for(int i=0;i<tempResultVec.size();i++)
			{
				resultVec.add((Vector)tempResultVec.elementAt(i));
			}
			
		 
		 return resultVec;
	}
	
	
	public Vector getCorpoUserListforAll(DataSource ds, int mcat,String selectType,String qtype[],String Mentor_cat)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		tempVec.add(" ");
		resultVec.add(tempVec);
		////////System.out.println("selectTypeselectTypeselectType"+selectType);
		////////System.out.println("mcatmcatmcatmcatmcatmcatmcatmcatmcat"+mcat);
		
		
			if(!selectType.equalsIgnoreCase("-1"))
		{
				if (qtype[1].equalsIgnoreCase("Technical")||qtype[1].equalsIgnoreCase("Personal"))
				{
					strQuery = "select * from loginmaster where  rid!=0 and logindeletion=0 and  companyid="+mcat+" and qure_cat like '%"+qtype[0]+"%'";
				}else
				{
					strQuery = "select * from loginmaster where  rid!=0 and logindeletion=0 and companyid="+mcat;
				}
		
		}else
		{
			if (Mentor_cat!=null)
			{
			strQuery = "select * from loginmaster where  rid!=0 and logindeletion=0 and companyid="+mcat+" and mentor_cat like '%"+Mentor_cat+"%'";
			}else
			{
				strQuery = "select * from loginmaster where  rid!=0 and logindeletion=0 and companyid="+mcat;
			}
		}	
			
			support.setFieldVec("int", "userid");
			support.setFieldVec("String", "first_name");
			support.setFieldVec("String", "last_name");
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			for(int i=0;i<tempResultVec.size();i++)
			{
				resultVec.add((Vector)tempResultVec.elementAt(i));
			}
			
			//////////System.out.println("strQuery in rootMaster"+strQuery);
		 return resultVec;
	}
	
	
	
	public Vector getCategories(DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select Category");
		resultVec.add(tempVec);
		 
		strQuery = "select * from staff_category where cat_enabled=1 order by cat_name";
		support.setFieldVec("int", "cat_id");
		support.setFieldVec("String", "cat_name");
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
		return resultVec;
	}

	////  Get categories for Classifieds
	
	public Vector getBlogCategories(DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select Classified Category");
		resultVec.add(tempVec);
		 
		strQuery = "select * from classifiedcategory where cat_enabled=1 order by cat_name";
		support.setFieldVec("int", "cat_id");
		support.setFieldVec("String", "cat_name");
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		Vector<String>tempVec1 = new Vector<String>();
		tempVec1.add("-1");
		tempVec1.add("Others");
		resultVec.add(tempVec1);
		 
		return resultVec;
	}
	

	
	////Select Services for Subscription for resident
	
	
	public Vector getServices(DataSource ds,String fID, String userID)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select Service");
		resultVec.add(tempVec);
		 
		strQuery = "select * from service where serviceid NOT IN(select serviceid from consumerservicemapping where consumerid="+userID+") AND facilityid="+fID;
		System.out.println("String Query is "+strQuery);
		support.setFieldVec("String", "serviceid");
		support.setFieldVec("String", "servicename");
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
		System.out.println("Temp Vec Size is"+tempResultVec.size());
		System.out.println("Support vector for Services"+resultVec); 
		return resultVec;
	}
	
	
	
	/// Subscription function end

	////Select Services for Un-Subscription for resident
	
	
	public Vector getUnServices(DataSource ds,String usID)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select Service");
		resultVec.add(tempVec);
			
		strQuery= "select cm.serviceid, s.servicename from service s, consumerservicemapping cm where s.serviceid=cm.serviceid and cm.consumerid="+usID+" and cm.status=2";
		 
//		strQuery = "select * from service where facilityid="+fID+" order by servicename";
		System.out.println("String Query is "+strQuery);
		support.setFieldVec("String", "serviceid");
		support.setFieldVec("String", "servicename");
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
		System.out.println("Temp Vec Size is"+tempResultVec.size());
	//	resultVec.add(tempVec1);
		System.out.println("Support vector for Services"+resultVec); 
		return resultVec;
	}
	
	
	
	/// Un-Subscription function end

	
	
	
	public Vector getFacility(DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		 
		strQuery = "select * from facility order by facility_name";
		support.setFieldVec("int", "facility_id");
		support.setFieldVec("String", "facility_name");
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		Vector<String>tempVec1 = new Vector<String>();
		tempVec1.add("-1");
		resultVec.add(tempVec1);
		 
		return resultVec;
	}
	
	
	public Vector getBuilderList(DataSource ds,int dep_id)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		 
		//strQuery = "select * from designation  order by des_name";
		strQuery = "select * from builder order by builder_id";
		support.setFieldVec("int", "builder_id");
		support.setFieldVec("String", "builder_name");
		
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
		System.out.println("Builder List 07092009-1-->"+resultVec);
		return resultVec;
	}
	
	

	public Vector getBuilderCityList(DataSource ds,int builderID)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		
		System.out.println("resultVec For Builder City builderID-->"+builderID);
		
		//strQuery = "select * from designation  order by des_name";
		//select Distinct(A.facility_city),B.PlaceName from facility A ,places B where A.builderID='"+builderID+"' and A.facility_city=B.placeid order by A.facility_city
		strQuery = "select Distinct(A.facility_city),B.PlaceName from facility A ,places B where A.builder_id='"+builderID+"' and A.facility_city=B.placeid order by A.facility_city";
		//strQuery = "select * from facility where builder_id='"+builderID+"' order by builder_id";
		//strQuery = "select * from builder order by builder_id";
		support.setFieldVec("String", "facility_city");
		support.setFieldVec("String", "PlaceName");
		
		System.out.println("Querry For City--03092009-->"+strQuery);
		
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
		System.out.println("resultVec For Builder City resultVec;;;;;;;;;;;"+resultVec);
		return resultVec;
	}
	

	public Vector getBuilderFacilityList(DataSource ds,int cityID,int builderId)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		
		System.out.println("Builder ID in RoomMaster-->"+builderId);
		System.out.println("City ID In Root master-->"+cityID);
		
		//strQuery = "select * from designation  order by des_name";
		strQuery = "select * from facility where builder_id='"+builderId+"' and facility_city='"+cityID+"' and facility_id not in (select companyid from loginmaster) order by builder_id";
		//select * from facility where builder_id=3 and facility_city=33 order by facility_name
		System.out.println("strQuery- New->"+strQuery);
		//strQuery = "select * from builder order by builder_id";
		support.setFieldVec("String", "facility_id");
		support.setFieldVec("String", "facility_name");
		
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
		System.out.println("resultVec For Builder Faciity 02092009 resultVec;;;;;;;;;;;"+resultVec);
		return resultVec;
	}



	public Vector getBuilderLocationList(DataSource ds,int cityID)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		
		System.out.println("resultVec For Builder City builderID-->"+cityID);
		
		//strQuery = "select * from designation  order by des_name";
		strQuery = "select * from facility where builder_id='"+cityID+"' order by builder_id";
		//strQuery = "select * from builder order by builder_id";
		support.setFieldVec("String", "builder_id");
		support.setFieldVec("String", "facility_location");
		
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
		System.out.println("resultVec For Builder Faciity 02092009 resultVec;;;;;;;;;;;"+resultVec);
		return resultVec;
	}



	public Vector getCollegeCategories(DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		 
		strQuery = "select * from facility order by facility_name";
		support.setFieldVec("int", "facility_id");
		support.setFieldVec("String", "facility_name");
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		Vector<String>tempVec1 = new Vector<String>();
		tempVec1.add("-1");
		tempVec1.add("Others");
		resultVec.add(tempVec1);
		 
		return resultVec;
	}
	
	public String getStrCollegeCategoryName(DataSource ds,int cat_id)
	{
		String resultVec = "";
		Support support=new Support();
	    String strQuery="";
		strQuery= "select coll_catname from collegecategory where college_id="+cat_id;
			
		support.setFieldVec("string", "coll_catname");	
		
		resultVec = masterUtil.getValue(ds, strQuery, support);
		
		return resultVec;
		
	}
	
//	insert country detail in data base
	public String insertOtherCollege(DataSource ds, String collegeName, String countryId, String stateId, String cityId) {
		Support support=new Support();
		
		//////////////////////System.out.print("countryId..$$..."+countryId);
		//////////////////////System.out.print("stateId..$$..."+stateId);
		//////////////////////System.out.print("cityId...$$...."+cityId);
		
		String strQuery="";
		String result ="";
		strQuery = "Insert into othercollegeid (cat_name,coll_contry,coll_state,coll_city) values(?, ?, ?, ?)";
		support.setDataVec("String", collegeName);
		support.setDataVec("String", countryId);
		support.setDataVec("String", stateId);
		support.setDataVec("String", cityId);
	
		result = masterUtil.setData(ds, strQuery, support);
		if(result.equalsIgnoreCase("success"))
		{
			strQuery = "select max(other_cid) other_cid from othercollegeid where  cat_name='"+collegeName+"' and coll_city='"+cityId+"'";
			result = String.valueOf(masterUtil.getId(ds, strQuery, "other_cid"));
		}
		
		Resource resr = new Resource();
		MailText mt = new MailText();
		String strMailText = "";
		String addfield = "College";
	    
	    strMailText = mt.getEmailAddNew(addfield, collegeName, result );
					
		String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender, "feedback@campusyogi.com", "Add new College Id "+result+"");
		
		
		return 	result;
	}
	
	//insert country detail in data base
	public String insertCountry(DataSource ds,String countryCode,String countryName) {
		Support support=new Support();
		String strQuery="";
		String result ="";
		strQuery = "Insert into countries (country_code,country_name) values(?,?)";
		support.setDataVec("String", countryCode);
		support.setDataVec("String", countryName);
		result = masterUtil.setData(ds, strQuery, support);
		if(result.equalsIgnoreCase("success"))
		{
			strQuery = "select max(id) id from countries where country_code='"+countryCode+"' and country_name='"+countryName+"'";
			result = String.valueOf(masterUtil.getId(ds, strQuery, "id"));
		}
		
		Resource resr = new Resource();
		MailText mt = new MailText();
		String strMailText = "";
		String addfield = "Country";
	    
	    strMailText = mt.getEmailAddNew(addfield, countryName, result );
					
		String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender, "feedback@campusyogi.com", "Add new country Id: "+result+"");
		
		
		
		return 	result;
	}
	//end inserting country
	
	//insert state detail in data base
	public String insertState(DataSource ds,String stateName,String countryId) {
		Support support=new Support();
		String strQuery="";
		String result ="";
		strQuery = "Insert into states (countryid,statename) values(?,?)";
		support.setDataVec("int", countryId);
		support.setDataVec("String", stateName);
		result = masterUtil.setData(ds, strQuery, support);
		if(result.equalsIgnoreCase("success"))
		{
			strQuery = "select max(stateid) stateid from states where countryid='"+countryId+"' and statename='"+stateName+"'";
			result = String.valueOf(masterUtil.getId(ds, strQuery, "stateid"));
		}
		
		Resource resr = new Resource();
		MailText mt = new MailText();
		String strMailText = "";
		String addfield = "State";
	    
	    strMailText = mt.getEmailAddNew(addfield, stateName, result );
					
		String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender, "feedback@campusyogi.com", "Add new state Id: "+result+"");
			
		
		return 	result;
	}
	//end inserting state
	//insert city detail in data base
	public String insertCity(DataSource ds, String cityName, String stateId,String countryId) {
		Support support=new Support();
		String strQuery="";
		String result ="";
		strQuery = "Insert into places (CountryID,StateID,Place,PlaceName) values(?,?,?,?)";
		support.setDataVec("int", countryId);
		support.setDataVec("String", stateId);
		support.setDataVec("String", cityName);
		support.setDataVec("String", cityName);
		result = masterUtil.setData(ds, strQuery, support);
		
		//////////////////////System.out.println("result ................"+result);
		
		
		if(result.equalsIgnoreCase("success"))
		{
			String tempstrQuery = "select max(PlaceID) PlaceID from places where CountryID='"+countryId+"' and StateID='"+stateId+"' and Place='"+cityName+"'";
			result = String.valueOf(masterUtil.getId(ds, tempstrQuery, "PlaceID"));
			//////////////////////System.out.println("result ....if............."+result);
			//////////////////////System.out.println("tempstrQuery ....if............."+tempstrQuery);
		}
		
		Resource resr = new Resource();
		MailText mt = new MailText();
		String strMailText = "";
		String addfield = "City";
	    
	    strMailText = mt.getEmailAddNew(addfield, cityName, result );
					
		String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender, "feedback@campusyogi.com", "Add new city Id: "+result+"");
				
		return 	result;
	}
	//end inserting city
	
	//insert category detail in data base
	public String insertCategory(DataSource ds, String catName) {
		Support support=new Support();
		String strQuery="";
		String result ="failure";
		strQuery = "select cat_id from category where cat_name='"+catName+"'";
		result = String.valueOf(masterUtil.getId(ds, strQuery, "cat_id"));
		
		System.out.println("category here in master Utils"+result);
		if(result.equals("0"))
		{
			strQuery = "Insert into category (cat_name) values(?)";
			support.setDataVec("String", catName);
			
			result = masterUtil.setData(ds, strQuery, support);
			if(result.equalsIgnoreCase("success"))
			{
				strQuery = "select max(cat_id) cat_id from category where cat_name='"+catName+"'";
				result = String.valueOf(masterUtil.getId(ds, strQuery, "cat_id"));
			}
		}
		
		Resource resr = new Resource();
		MailText mt = new MailText();
		String strMailText = "";
		String addfield = "Category";
	    
	    strMailText = mt.getEmailAddNew(addfield, catName, result );
					
		String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender, "feedback@campusyogi.com", "Add new Category Id: "+result+"");
		
		
		return 	result;
	}
	
	//	insert category detail in data base
	public String insertCollegeCategory(DataSource ds, String catName) {
		
		//////////////////////System.out.println("catName..database.."+catName);
		Support support=new Support();
		String strQuery="";
		String result ="failure";
		strQuery = "select college_id from collegecategory where coll_catname='"+catName+"'";
		result = String.valueOf(masterUtil.getId(ds, strQuery, "college_id"));
		
		if(result.equals("0"))
		{
			strQuery = "Insert into collegecategory (coll_catname) values(?)";
			support.setDataVec("String", catName);
			
			result = masterUtil.setData(ds, strQuery, support);
			if(result.equalsIgnoreCase("success"))
			{
				strQuery = "select max(college_id) college_id from collegecategory where coll_catname='"+catName+"'";
				result = String.valueOf(masterUtil.getId(ds, strQuery, "college_id"));
			}
		}
		
		Resource resr = new Resource();
		MailText mt = new MailText();
		String strMailText = "";
		String addfield = "Category For College";
	    
	    strMailText = mt.getEmailAddNew(addfield, catName, result );
					
		String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender, "feedback@campusyogi.com", "Add new Category For College Id: "+result+"");
		
		
		
		//////////////////////System.out.println("result..database.."+result);
		return 	result;
	}
		
	//check ligin name and company short name exist
	public String checkLoginId(DataSource ds, String loginId, String email) 
	{
		Support support=new Support();
		String strResult="";
		String strQuery="";
		strQuery = "select userid from loginmaster where loginname='"+ loginId+"'";
		support.setFieldVec("string", "userid");
		Vector firstVec = masterUtil.getDetail(ds, strQuery, support);
		if(firstVec.size()>0)
		{
			strResult="loginId";
		}
		else
		{
			strQuery = "select userid from loginmaster where email='"+ email+"'";
			support.setFieldVec("string", "userid");
			Vector secondVec = masterUtil.getDetail(ds, strQuery, support);
			if(secondVec.size()>0)
			{
				strResult="mailId";
			}
			else
			{
				strResult="success";
			}
		}
		return strResult;
	}
	//END CHECK LOGIN
	public String checkLoginId(DataSource ds, String loginId, String email, String strCompType) 
	{
		Support support=new Support();
		String strResult="";
		String strQuery="";
		//strQuery = "select userid from loginmaster where loginname='"+ loginId+"'";
		strQuery = "select l.userid from loginmaster l, companymaster cm where l.companyid = cm.company_id and l.loginname='"+loginId.trim()+"' and cm.typeofcompany='"+strCompType.trim()+"'";
		support.setFieldVec("string", "userid");
		Vector firstVec = masterUtil.getDetail(ds, strQuery, support);
		if(firstVec.size()>0)
		{
			strResult="loginId";
		}
		else
		{
			//strQuery = "select userid from loginmaster where email='"+ email+"'";
			strQuery = "select l.userid from loginmaster l, companymaster cm where l.companyid = cm.company_id and l.email='"+email.trim()+"' and cm.typeofcompany='"+strCompType.trim()+"'";
			support.setFieldVec("string", "userid");
			Vector secondVec = masterUtil.getDetail(ds, strQuery, support);
			if(secondVec.size()>0)
			{
				strResult="mailId";
			}
			else
			{
				strResult="success";
			}
		}
		return strResult;
	}
	//forget password and return fname , lname and pasword in vector
	public Vector forgetPassword(DataSource ds, Vector dataVec)
	{
		Vector<String> resultVec = new Vector<String>();
		Support support=new Support();
		String strResult="failure";
		String loginId = dataVec.elementAt(0).toString().trim();
		String email = dataVec.elementAt(1).toString().trim();
		String type = dataVec.elementAt(2).toString().trim();
		String strQuery = "select userid, companyid from loginmaster where loginname='"+ loginId+"' and email='"+ email+"'";
		support.setFieldVec("string", "userid");
		support.setFieldVec("string", "companyid");
		Vector firstVec = masterUtil.getDetail(ds, strQuery, support);
		if(firstVec.size()>0)
		{
			strResult="success";
		}
		////////////////////System.out.println("strResult>>>>forgetPassword>>>>"+strResult);
		if(strResult.equalsIgnoreCase("success"))
		{
			//get company type
			String compId = firstVec.elementAt(1).toString().trim();
			Support support1=new Support();
			String strQuery1="select typeofcompany from companymaster where company_id='"+ compId+"'";
			support1.setFieldVec("string", "typeofcompany");
			Vector secondVec = masterUtil.getDetail(ds, strQuery1, support1);
			if(secondVec.size()>0)
			{
				String compType = secondVec.elementAt(0).toString().trim();
				
				if((type.equalsIgnoreCase("indv") && compType.equalsIgnoreCase("Consumer")) || (type.equalsIgnoreCase("advt") && compType.equalsIgnoreCase("Advertiser")) || (type.equalsIgnoreCase("corpo") && compType.equalsIgnoreCase("Corporates"))|| (type.equalsIgnoreCase("entp") && compType.equalsIgnoreCase("Enterprise")))
				{
//					get first name , last name , password
					Support support2=new Support();
					String strQuery2="select first_name, last_name, password from loginmaster where loginname='"+ loginId+"'";
					support2.setFieldVec("string", "first_name");
					support2.setFieldVec("string", "last_name");
					support2.setFieldVec("string", "password");
					resultVec = masterUtil.getDetail(ds, strQuery2, support2);
					////////////////////////System.out.println("resultVec>>>>"+resultVec);
				}
				else
				{
					resultVec.add("failure");
				}
			}
			else
			{
				resultVec.add("failure");
			}
			
		}
		else
		{
			resultVec.add(strResult);
		}
		
		return resultVec;
	}
	//end forget password code
	
//	forget password for blogs Comment and return fname , lname and pasword in vector
	
	public Vector forgetPasswordBlog(DataSource ds, Vector dataVec)
	{
		Vector<String> resultVec = new Vector<String>();
		Support support=new Support();
		String strResult="failure";
		String loginId = dataVec.elementAt(0).toString().trim();
		String email = dataVec.elementAt(1).toString().trim();
		String type = dataVec.elementAt(2).toString().trim();
		String strQuery = "select userid, companyid from loginmaster where loginname='"+ loginId+"' and email='"+ email+"'";
		support.setFieldVec("string", "userid");
		support.setFieldVec("string", "companyid");
		Vector firstVec = masterUtil.getDetail(ds, strQuery, support);
		if(firstVec.size()>0)
		{
			strResult="success";
		}
		////////////////////////System.out.println("strResult>>>>>>>>"+strResult);
		if(strResult.equalsIgnoreCase("success"))
		{
			//get company type
			String compId = firstVec.elementAt(1).toString().trim();
			Support support1=new Support();
			String strQuery1="select typeofcompany from companymaster where company_id='"+ compId+"'";
			support1.setFieldVec("string", "typeofcompany");
			Vector secondVec = masterUtil.getDetail(ds, strQuery1, support1);
			////////////////////System.out.println("secondVec>>>>"+secondVec);
			if(secondVec.size()>0)
			{
				String compType = secondVec.elementAt(0).toString().trim();
				
				
//					get first name , last name , password
					Support support2=new Support();
					String strQuery2="select first_name, last_name, password from loginmaster where loginname='"+ loginId+"'";
					support2.setFieldVec("string", "first_name");
					support2.setFieldVec("string", "last_name");
					support2.setFieldVec("string", "password");
					resultVec = masterUtil.getDetail(ds, strQuery2, support2);
					////////////////////System.out.println("resultVec>>>>"+resultVec);
				
			}
			else
			{
				resultVec.add("failure");
			}
			
		}
		else
		{
			resultVec.add(strResult);
		}
		////////////////////System.out.println("resultVec>>>>"+resultVec);
		
		return resultVec;
	}
	
//	end forget password code
	
	
	//function for getting loginRights id according to given company(community) Id
	public String getRightId(DataSource ds, String companyId)
	{
		
		String strQuery="";
		strQuery = "select communityrights from companymaster where company_id='"+ companyId+"'";
		return String.valueOf(masterUtil.getId(ds, strQuery, "communityrights"));
	}
	//return expiry date
	public String returnFinalDate(String strIncrementOf, int numVal)
	{
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		for (int i=0;i<numVal; i++)
		{
			if(strIncrementOf.equalsIgnoreCase("Days"))
			{
				cal.add(Calendar.DATE, 1);
			}
			if(strIncrementOf.equalsIgnoreCase("Months"))
			{
				cal.add(Calendar.MONTH, 1);
			}
			if(strIncrementOf.equalsIgnoreCase("Years"))
			{
				cal.add(Calendar.YEAR, 1);
			}

		}
        String DATE_FORMAT = "yyyy-MM-dd";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        String currentDate = sdf.format(cal.getTime()).toString();
		return currentDate;
	}
	//end expiry date code
	
	/*****************************************************************************************************************
	 This function Get the cat_name by using the cat_id from the lassifiedcategory Table.
*****************************************************************************************************************/
	public Vector getClassifiedCategoryName(DataSource ds,int cat_id)
	{
		Vector resultVec = new Vector();
		Support support=new Support();
	    String strQuery="";
		strQuery= "select cat_name,cat_enabled from classifiedcategory where cat_id="+cat_id;
			
		support.setFieldVec("string", "cat_name");	
		support.setFieldVec("int", "cat_enabled");	
		
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		
		return resultVec;
		
	}
	/*****************************************************************************************************************
	 This function Get the cat_name by using the cat_id from the category Table.
*****************************************************************************************************************/
	public Vector getCategoryName(DataSource ds,int cat_id)
	{
		Vector resultVec = new Vector();
		Support support=new Support();
	    String strQuery="";
		strQuery= "select cat_name,cat_enabled from category where cat_id="+cat_id;
			
		support.setFieldVec("string", "cat_name");	
		support.setFieldVec("int", "cat_enabled");	
		
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		
		return resultVec;
		
	}
	/*****************************************************************************************************************
	 This function Get the tag Name by using the tag_Id from the tags Table.
*****************************************************************************************************************/
	public Vector getTagName(DataSource ds,int tagid)
	{
		Vector resultVec = new Vector();
		Support support=new Support();
	    String strQuery="";
		strQuery= "select tagname from tages where tagid="+tagid;
			
		support.setFieldVec("string", "tagname");	
		
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		
		return resultVec;
		
	}

	/*****************************************************************************************************************
	 This function Get the Staff Name by using the staff_Id from the Staff Master Table.
*****************************************************************************************************************/
	public Vector getStaffName(DataSource ds,int staffid)
	{
		Vector resultVec = new Vector();
		Support support=new Support();
	    String strQuery="";
		strQuery= "select staffname from staff_master where staff_id="+staffid;
			
		support.setFieldVec("string", "staffname");	
	//	System.out.println("Query is "+strQuery);
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		
		return resultVec;
		
	}
	
	/*****************************************************************************************************************
	 This function Get the cat_name by using the cat_id from the category Table.
*****************************************************************************************************************/
	public String getStrCategoryName(DataSource ds,int cat_id)
	{
		String resultVec = "";
		Support support=new Support();
	    String strQuery="";
		strQuery= "select cat_name from category where cat_id="+cat_id;
			
		support.setFieldVec("string", "cat_name");	
		
		resultVec = masterUtil.getValue(ds, strQuery, support);
		
		return resultVec;
		
	}
	/*****************************************************************************************************************
	 This function Get the tag Name by using the tag_Id from the tags Table.
*****************************************************************************************************************/
	public String getStrTagName(DataSource ds,int tagid)
	{
		String resultVec = "";
		Support support=new Support();
	    String strQuery="";
		strQuery= "select tagname from tages where tagid="+tagid;
			
		support.setFieldVec("string", "tagname");	
		
		resultVec = masterUtil.getValue(ds, strQuery, support);
		
		return resultVec;
		
	}
	
	/*****************************************************************************************************************
	 This function Get the brand Name by using the brnad id from the company master  Table.
*****************************************************************************************************************/
	public String getBrandName(DataSource ds,int brandId)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
		strQuery= "select company_name  from companymaster where company_id="+brandId;
			
		support.setFieldVec("string", "company_name");	
		
		strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
		
	}
	/*****************************************************************************************************************
	 This function Get the contact person name by using the uid from the loginmaster Table.
*****************************************************************************************************************/
	public String getContactPersonName(DataSource ds,int cont_id)
	{
		String strResult="";
		Vector resultVec = new Vector();
		Support support=new Support();
	    String strQuery="";
		strQuery= "select first_name,last_name from loginmaster where userid="+cont_id;
			
		support.setFieldVec("string", "first_name");	
		support.setFieldVec("string", "last_name");	
		
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		
		strResult=resultVec.elementAt(0).toString()+" "+resultVec.elementAt(1).toString();
		return strResult;
		
	}
	/*****************************************************************************************************************
	 This function Get the country name by using the id from the countries Table.
*****************************************************************************************************************/
	public String getCountryName(DataSource ds,int countryId)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
		strQuery= "select country_name from countries where id="+countryId;
			
		support.setFieldVec("string", "country_name");	
		
		strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
		
	}
	/*****************************************************************************************************************
	 This function Get the state name by using the stateid from the countries Table.
*****************************************************************************************************************/

	public String getStateName(DataSource ds,int stateid)
		{
			String strResult="";
			Support support=new Support();
		  	 System.out.println("Tempvec is ");

		    	String strQuery="";
			//strQuery= "select statename from states where stateid="+stateid;
			strQuery="SELECT FACILITY_NAME FROM facility where FACILITY_ID="+stateid;
			support.setFieldVec("string", "FACILITY_NAME");	
			
			strResult = masterUtil.getValue(ds, strQuery, support);
			
			return strResult;
			
		}
	
	/*****************************************************************************************************************
	 This function Get the city name by using the cityId from the places Table.
*****************************************************************************************************************/
	public String getPlaceName(DataSource ds,int placeID)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
		strQuery= "select Place from places where PlaceID="+placeID;
			
		support.setFieldVec("string", "Place");	
		
		strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
		
	}
	
	/*****************************************************************************************************************
	 This function Get  Company Add. by using the compnyid from the companymaster Table.
*****************************************************************************************************************/
	public Vector getCompanyAdd(DataSource ds,int compnyid)
	{
		Vector resultVec = new Vector();
		Support support=new Support();
	    String strQuery="";
	    strQuery= "select * from companymaster where company_id="+compnyid;
			
		support.setFieldVec("string", "company_address1");	
		support.setFieldVec("string", "company_address2");	
		support.setFieldVec("string", "company_city");	
		support.setFieldVec("string", "company_state");	
		support.setFieldVec("string", "company_country");	
		support.setFieldVec("string", "company_zip");	
		support.setFieldVec("string", "company_phone");	
		
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		
		return resultVec;
		
	}
	
	/*****************************************************************************************************************
	 This function Get the company_sname  by using the id from the companymaster Table.
*****************************************************************************************************************/
	public String getCompnyName(DataSource ds,int compnyid)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
	    
		strQuery= "select company_name from companymaster where company_id="+compnyid;
			
		support.setFieldVec("string", "company_name");	
		
		strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
		
	}
	/*****************************************************************************************************************
	 This function Get the Department name by using the id from the Department Table.
*****************************************************************************************************************/
	public String getDepName(DataSource ds,int countryId)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
		strQuery= "select dep_name from department where dep_id="+countryId;
			
		support.setFieldVec("string", "dep_name");	
		
		strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
		
	}
	/*****************************************************************************************************************
	 This function Get the Designation name by using the id from the Designation Table.
*****************************************************************************************************************/
	public String getDesName(DataSource ds,int des_id)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
		strQuery= "select des_name from designation where des_id="+des_id;
			
		support.setFieldVec("string", "des_name");	
		
		strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
		
	}
	public String getCompanyshortpro(DataSource ds,int compnyid)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
	    
		strQuery= "select short_pro from companymaster where company_id="+compnyid;
			
		support.setFieldVec("string", "short_pro");	
		
		strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
		
	}
	public String getUsershortpro(DataSource ds,int uid)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
	    
		strQuery= "select short_pro from loginmaster where userid="+uid;
			
		support.setFieldVec("string", "short_pro");	
		
		strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
		
	}
	
	
	public String getResult(DataSource ds,int uid,int advtid)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
	     	
		strQuery= "select distinct c.advtL_id from  complaints c,communication cn where c.login_id="+uid+" and c.advtL_id="+advtid+" and c.advtL_id=cn.userid  and com_flag='1' and	cn.a_type='Reject'";		
		support.setFieldVec("string", "c.advtL_id");	
		//////System.out.println("useriduseriduseriduserid>>>>>>>>>>>>>"+strQuery);
		strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
		
	}
	
	public String getCollegeName(DataSource ds,int compnyid)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
		strQuery= "select cat_name from othercollegeid where other_cid="+compnyid;
			
		support.setFieldVec("string", "cat_name");	
		
		strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
		
	}
	
	public String getCompnyCityName(DataSource ds,int compnyid)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
		strQuery= "select p.PlaceName from companymaster c, places p where c.company_city=p.PlaceID and  c.company_id="+compnyid;
			
		support.setFieldVec("string", "p.PlaceName");	
		
		strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
		
	}
	
	public String getCollegeCityName(DataSource ds,int compnyid)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
		strQuery= "select p.PlaceName from othercollegeid o, places p  where o.coll_city=p.PlaceID and  o.other_cid="+compnyid;
			
		support.setFieldVec("string", "p.PlaceName");	
		
		strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
		
	}
	
	
	
	
	
	/**
	 *  This function Update the User PassWord in the loginmaster table.
	 */
	public String changPassWord(DataSource ds,Vector paramVec){
		
		String strResult = "";	
		Support support=new Support();
	    String strQuery="";
		strQuery = "Update loginmaster set password=? where userid=?";
		
		support.setDataVec("string", paramVec.elementAt(1).toString().trim());			
		support.setDataVec("int", paramVec.elementAt(0).toString().trim());	
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;	
				
	}
	/**
	 * This function Get User PassWord form loginmaster table.
	 */
	public String getPassWord(DataSource ds,int  userid)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
	   
		strQuery= "select password from loginmaster where userid="+userid;
			
		support.setFieldVec("string", "password");			
		strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
		
	}
	
	/************************************************************************************************************
    This function Update the Active Field value in the companymaster table.     
**************************************************************************************************************/
	public String updateUserActiveFlag(DataSource ds,Vector paramVec){
		
		String strResult = "";	
		Support support=new Support();
	    String strQuery="";
		strQuery = "Update loginmaster set logindeletion=? where userid=?";
		
		
		support.setDataVec("int", paramVec.elementAt(0).toString().trim());
		support.setDataVec("int", paramVec.elementAt(1).toString().trim());
		
		
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;	
				
	}
	
	/************************************************************************************************************
    This function Update the reponse flag value in the communication table.     
**************************************************************************************************************/
	public String updateResponseFlag(DataSource ds,Vector paramVec){
		
		String strResult = "failure";	
		Support support=new Support();
	    String strQuery="";
	    String con_resp_id = paramVec.elementAt(0).toString().trim();
	    String compType = paramVec.elementAt(1).toString().trim();
	    if(compType.equalsIgnoreCase("indv"))
	    {
	    	strQuery = "Update communication set indv_rflag = 1 where respid=?";
	    }
	    else if(compType.equalsIgnoreCase("advt"))
	    {
	    	strQuery = "Update communication set advt_rflag = 1 where respid=?";
	    }
	    else if(compType.equalsIgnoreCase("entp"))
	    {
	    	strQuery = "Update communication set entp_rflag = 1 where respid=?";
	    }
	    else
	    {
	    	//nothing happened
	    }
	    if(strQuery.length()>0)
	    {
	    	support.setDataVec("int", con_resp_id);
			strResult = masterUtil.setData(ds, strQuery, support);
	    }
		return strResult;	
				
	}
	
	/**
	 * This function use for getting total no. of User license for a Company in the companymaster table .
	 * @return
	 */	
		public String getTotalUser(DataSource ds,int companyid){
			
			
			//////////////////////////System.out.println("In siet Advt Master getUserCount");
			
			
			String strResult="";
			Support support=new Support();					
			   String strQuery="";
			
				strQuery = "Select total_user from companymaster where  company_id="+companyid;
				
				support.setFieldVec("int", "total_user");
				
				
				
				strResult = masterUtil.getValue(ds, strQuery, support);
				
				////////////////////////System.out.println("In  strQuery...."+strQuery);
				////////////////////////System.out.println("In  result...."+strResult);
				
				return strResult;
		}
		
		/**
		 * This function is use for getting list of  User from `loginmaster`.
		 * It returns a list within limit which is given by min and max variable.
		 */
			public Vector getUserList(DataSource ds,int companyid){
				
				//////////////////////////System.out.println("In siet Entp Master getUserList");
				
				Vector resultVec = new Vector();
								
				Support support=new Support();
				   String strQuery="";
				
					strQuery = "select * from loginmaster where companyid="+companyid+"  and logindeletion=0 ";
				
				
				/**
				 * set all the fileds type and name for loginmaster table by using
				 * Support class object.
				 */
				support.setFieldVec("int", "userid");
				support.setFieldVec("string", "first_name");
				support.setFieldVec("string", "last_name");
			
				resultVec = masterUtil.getList(ds, strQuery, support);
				
				//////////////////////////System.out.println("In siet Advt Master resultVec"+resultVec);
				return resultVec;
			}
//		check Group name is exist for comp
		public String checkGroupName(DataSource ds, String compId, String groupname) 
		{
			Support support=new Support();
			String strResult="";
			String strQuery="";
			strQuery = "select rid from loginrights where companyid='"+ compId+"' and groupname='"+groupname+"'";
			support.setFieldVec("int", "rid");
			strResult = masterUtil.getValue(ds, strQuery, support);
			////////////////////////System.out.println("strResult............"+strResult);
			
			return strResult;
		}
		
		// get user id .
		public int getUserId(DataSource ds, String userLoginId)
		{
			int result = 0;
			
			String strQuery="";
			
			strQuery= "select userid from loginmaster where loginname = '"+userLoginId+"'" ;
			
			
			result = masterUtil.getId(ds, strQuery, "userid");	
			
			return result;
			
		}
		
		/**
		 * This function is use for inserting User Detail into consumer_data table.
		 * 
		 */
		public String insertUserUnNameInfo(DataSource ds, Vector dataVec) {
			Support support=new Support();
			String strQuery="";
			strQuery = "Insert into consumer_data (contactid, companyid, field1, field2, field3, field4, field5, field6,"
					+ "field7, field8, field9, field10, field11, field12, field13, field14, field15,"
					+ "field16, field17, field18, field19, field20, field21, field22, field23,"
					+ "field24, field25, field26, field27, field28,"
					+ "field29, field30, field31, field32, field33, field34, field35) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			support.setDataVec("int", dataVec.elementAt(0).toString().trim());
			support.setDataVec("int", dataVec.elementAt(1).toString().trim());
			support.setDataVec("string", dataVec.elementAt(2).toString().trim());
			support.setDataVec("string", dataVec.elementAt(3).toString().trim());
			support.setDataVec("string", dataVec.elementAt(4).toString().trim());
			support.setDataVec("string", dataVec.elementAt(5).toString().trim());
			support.setDataVec("string", dataVec.elementAt(6).toString().trim());
			support.setDataVec("string", dataVec.elementAt(7).toString().trim());
			support.setDataVec("string", dataVec.elementAt(8).toString().trim());
			support.setDataVec("string", dataVec.elementAt(9).toString().trim());
			support.setDataVec("string", dataVec.elementAt(10).toString().trim());
			support.setDataVec("string", dataVec.elementAt(11).toString().trim());
			support.setDataVec("string", dataVec.elementAt(12).toString().trim());
			support.setDataVec("string", dataVec.elementAt(13).toString().trim());
			support.setDataVec("string", dataVec.elementAt(14).toString().trim());
			support.setDataVec("string", dataVec.elementAt(15).toString().trim());
			support.setDataVec("string", dataVec.elementAt(16).toString().trim());
			support.setDataVec("string", dataVec.elementAt(17).toString().trim());
			support.setDataVec("string", dataVec.elementAt(18).toString().trim());
			support.setDataVec("string", dataVec.elementAt(19).toString().trim());
			support.setDataVec("string", dataVec.elementAt(20).toString().trim());
			support.setDataVec("string", dataVec.elementAt(21).toString().trim());
			support.setDataVec("string", dataVec.elementAt(22).toString().trim());
			support.setDataVec("string", dataVec.elementAt(23).toString().trim());
			support.setDataVec("string", dataVec.elementAt(24).toString().trim());
			support.setDataVec("string", dataVec.elementAt(25).toString().trim());
			support.setDataVec("string", dataVec.elementAt(26).toString().trim());
			support.setDataVec("string", dataVec.elementAt(27).toString().trim());
			support.setDataVec("string", dataVec.elementAt(28).toString().trim());
			support.setDataVec("string", dataVec.elementAt(29).toString().trim());
			support.setDataVec("string", dataVec.elementAt(30).toString().trim());
			support.setDataVec("string", dataVec.elementAt(31).toString().trim());
			support.setDataVec("string", dataVec.elementAt(32).toString().trim());
			support.setDataVec("string", dataVec.elementAt(33).toString().trim());
			support.setDataVec("string", dataVec.elementAt(34).toString().trim());
			support.setDataVec("string", dataVec.elementAt(35).toString().trim());
			support.setDataVec("string", dataVec.elementAt(36).toString().trim());

			return masterUtil.setData(ds, strQuery, support);
		}
		
		/**
		 * This function is use for update User Detail into consumer_data table.
		 * 
		 */
		public String updateUserUnNameInfo(DataSource ds, Vector dataVec) {
			Support support=new Support();
			String strQuery="";
			strQuery = "Update consumer_data set field1=?, field2=?, field3=?, field4=?, field5=?, field6=?,"
					+ "field7=?, field8=?, field9=?, field10=?, field11=?, field12=?, field13=?, field14=?, field15=?,"
					+ "field16=?, field17=?, field18=?, field19=?, field20=?, field21=?, field22=?, field23=?,"
					+ "field24=?, field25=?, field26=?, field27=?, field28=?,"
					+ "field29=?, field30=?, field31=?, field32=?, field33=?, field34=?, field35=? where contactid=? and companyid=?";

			
			support.setDataVec("string", dataVec.elementAt(2).toString().trim());
			support.setDataVec("string", dataVec.elementAt(3).toString().trim());
			support.setDataVec("string", dataVec.elementAt(4).toString().trim());
			support.setDataVec("string", dataVec.elementAt(5).toString().trim());
			support.setDataVec("string", dataVec.elementAt(6).toString().trim());
			support.setDataVec("string", dataVec.elementAt(7).toString().trim());
			support.setDataVec("string", dataVec.elementAt(8).toString().trim());
			support.setDataVec("string", dataVec.elementAt(9).toString().trim());
			support.setDataVec("string", dataVec.elementAt(10).toString().trim());
			support.setDataVec("string", dataVec.elementAt(11).toString().trim());
			support.setDataVec("string", dataVec.elementAt(12).toString().trim());
			support.setDataVec("string", dataVec.elementAt(13).toString().trim());
			support.setDataVec("string", dataVec.elementAt(14).toString().trim());
			support.setDataVec("string", dataVec.elementAt(15).toString().trim());
			support.setDataVec("string", dataVec.elementAt(16).toString().trim());
			support.setDataVec("string", dataVec.elementAt(17).toString().trim());
			support.setDataVec("string", dataVec.elementAt(18).toString().trim());
			support.setDataVec("string", dataVec.elementAt(19).toString().trim());
			support.setDataVec("string", dataVec.elementAt(20).toString().trim());
			support.setDataVec("string", dataVec.elementAt(21).toString().trim());
			support.setDataVec("string", dataVec.elementAt(22).toString().trim());
			support.setDataVec("string", dataVec.elementAt(23).toString().trim());
			support.setDataVec("string", dataVec.elementAt(24).toString().trim());
			support.setDataVec("string", dataVec.elementAt(25).toString().trim());
			support.setDataVec("string", dataVec.elementAt(26).toString().trim());
			support.setDataVec("string", dataVec.elementAt(27).toString().trim());
			support.setDataVec("string", dataVec.elementAt(28).toString().trim());
			support.setDataVec("string", dataVec.elementAt(29).toString().trim());
			support.setDataVec("string", dataVec.elementAt(30).toString().trim());
			support.setDataVec("string", dataVec.elementAt(31).toString().trim());
			support.setDataVec("string", dataVec.elementAt(32).toString().trim());
			support.setDataVec("string", dataVec.elementAt(33).toString().trim());
			support.setDataVec("string", dataVec.elementAt(34).toString().trim());
			support.setDataVec("string", dataVec.elementAt(35).toString().trim());
			support.setDataVec("string", dataVec.elementAt(36).toString().trim());
			support.setDataVec("int", dataVec.elementAt(0).toString().trim());
			support.setDataVec("int", dataVec.elementAt(1).toString().trim());

			return masterUtil.setData(ds, strQuery, support);
		}
		
		public Vector getUserUnNameInfo(DataSource ds,int userId, int compId)
		{
			Vector resultVec = new Vector();
			Support support=new Support();
		    String strQuery="";
		    
			strQuery= "select * from consumer_data where contactid="+userId+" and companyid="+compId;
				
			support.setFieldVec("string", "field1");
			support.setFieldVec("string", "field2");
			support.setFieldVec("string", "field3");
			support.setFieldVec("string", "field4");
			support.setFieldVec("string", "field5");
			support.setFieldVec("string", "field6");
			support.setFieldVec("string", "field7");
			support.setFieldVec("string", "field8");
			support.setFieldVec("string", "field9");
			support.setFieldVec("string", "field10");
			support.setFieldVec("string", "field11");
			support.setFieldVec("string", "field12");
			support.setFieldVec("string", "field13");
			support.setFieldVec("string", "field14");
			support.setFieldVec("string", "field15");
			support.setFieldVec("string", "field16");
			support.setFieldVec("string", "field17");
			support.setFieldVec("string", "field18");
			support.setFieldVec("string", "field19");
			support.setFieldVec("string", "field20");
			support.setFieldVec("string", "field21");
			support.setFieldVec("string", "field22");
			support.setFieldVec("string", "field23");
			support.setFieldVec("string", "field24");
			support.setFieldVec("string", "field25");
			support.setFieldVec("string", "field26");
			support.setFieldVec("string", "field27");
			support.setFieldVec("string", "field28");
			support.setFieldVec("string", "field29");
			support.setFieldVec("string", "field30");
			support.setFieldVec("string", "field31");
			support.setFieldVec("string", "field32");
			support.setFieldVec("string", "field33");
			support.setFieldVec("string", "field34");
			support.setFieldVec("string", "field35");
			
			resultVec = masterUtil.getDetail(ds, strQuery, support);
			
			return resultVec;
			
		}
		
		

		public Vector getResidentsForBillGeneration(DataSource ds,int facilityid)
		{
			Support support=new Support();
			String strQuery="";
			Vector<Vector>resultVec = new Vector<Vector>();
			Vector<String>tempVec = new Vector<String>();
			Vector<Vector> tempResultVec= new Vector<Vector>();
			tempVec.add("0");
			tempVec.add("Select");
			resultVec.add(tempVec);
			strQuery = "select userid,UPPER(CONCAT_WS('  ',first_name,last_name)) AS residentname from loginmaster where companyid=26 AND login_type='Student' AND B_IsSuspended=0 AND B_IsValidated=1 AND college_id="+facilityid+"  ORDER BY first_name;";

			System.out.println("residentname List 29092009-2-->"+strQuery);
			
			support.setFieldVec("int", "userid");
			support.setFieldVec("String", "residentname");
			
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			
			for(int i=0;i<tempResultVec.size();i++)
			{
				resultVec.add((Vector)tempResultVec.elementAt(i));
			}
			return resultVec;
	}


	public Vector getResidentsServices(DataSource ds,int facilityid)
		{
			Support support=new Support();
			String strQuery="";
			Vector<Vector>resultVec = new Vector<Vector>();
			Vector<String>tempVec = new Vector<String>();
			Vector<Vector> tempResultVec= new Vector<Vector>();
			tempVec.add("0");
			tempVec.add("Select");
			resultVec.add(tempVec);
			strQuery = "select serviceid,servicename from service where Subscription='Per Unit' ORDER BY servicename;";
			System.out.println("strQuery=21102009>"+strQuery);
			support.setFieldVec("int", "serviceid");
			support.setFieldVec("String", "servicename");
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			for(int i=0;i<tempResultVec.size();i++)
			{
				resultVec.add((Vector)tempResultVec.elementAt(i));
			}
			
			System.out.println("residentname List 29092009-1-->"+resultVec);
			return resultVec;
	}

		/**
		 * This function is use for inserting EducationInfo into `studenteducation` table.
		 * 
		 */
		public String insertEducationInfo(DataSource ds, Vector dataVec, String userId) {
			Support support=new Support();
			String strQuery="";
			
			
			
			strQuery = "Insert into studenteducation (sid, course_name, coll_name, major_s, pssout_year, admision_no, cgpa, oth_name) values(?, ?, ?, ?, ?, ?, ?, ?)";

			support.setDataVec("string", userId);
			support.setDataVec("string", dataVec.elementAt(0).toString().trim());
			support.setDataVec("string", dataVec.elementAt(1).toString().trim());
			support.setDataVec("string", dataVec.elementAt(2).toString().trim());
			support.setDataVec("string", dataVec.elementAt(3).toString().trim());
			support.setDataVec("string", dataVec.elementAt(4).toString().trim());
			support.setDataVec("string", dataVec.elementAt(5).toString().trim());
			support.setDataVec("string", dataVec.elementAt(6).toString().trim());
			

			return masterUtil.setData(ds, strQuery, support);

			
		}
		
		/**
		*  This function Update the Student EducationInfo in the studenteducation table.
		*/
		public String updateEducationInfo(DataSource ds,Vector paramVec){
		
		String strResult = "";	
		Support support=new Support();
	    String strQuery="";
		strQuery = "Update studenteducation set course_name=?, coll_name=?, major_s=?, pssout_year=?,"+
		"admision_no=?, cgpa=?, oth_name=? where eid=?";
				
		support.setDataVec("string", paramVec.elementAt(1).toString().trim());
		support.setDataVec("string", paramVec.elementAt(2).toString().trim());
		support.setDataVec("string", paramVec.elementAt(3).toString().trim());
		support.setDataVec("string", paramVec.elementAt(4).toString().trim());
		support.setDataVec("string", paramVec.elementAt(5).toString().trim());
		support.setDataVec("string", paramVec.elementAt(6).toString().trim());	
		support.setDataVec("string", paramVec.elementAt(7).toString().trim());
		support.setDataVec("int", paramVec.elementAt(0).toString().trim());
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;	
				
	}
		
		//	get EducationInfo list for a Student
		public Vector getEducationInfo(String userId, DataSource ds)
		{
			Support support=new Support();
			String strQuery="";
			
			Vector<Vector>resultVec = new Vector<Vector>();
			
			
			strQuery = "select eid, sid, course_name, coll_name, major_s, pssout_year, admision_no, cgpa, oth_name from studenteducation where sid ='"+userId+"'";
			support.setFieldVec("int", "eid");
			support.setFieldVec("String", "sid");
			support.setFieldVec("String", "course_name");
			support.setFieldVec("String", "coll_name");
			support.setFieldVec("String", "major_s");
			support.setFieldVec("String", "pssout_year");
			support.setFieldVec("String", "admision_no");
			support.setFieldVec("String", "cgpa");
			support.setFieldVec("String", "oth_name");

			resultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			
			 
		 return resultVec;
		}
		
		
//		 Activ Flag .
		
		public int getActivFlagPlace(DataSource ds, int PlaceID)
		{
			int result = 0;
			
			String strQuery="";
			
			strQuery= "select active from places where PlaceID = "+PlaceID;
			
			
			result = masterUtil.getId(ds, strQuery, "active");	
			
			return result;
			
		}
		
		public int getActivFlagState(DataSource ds, int states)
		{
			int result = 0;
			
			String strQuery="";
			
			strQuery= "select active from states where stateid = "+states;
			
			
			result = masterUtil.getId(ds, strQuery, "active");	
			
			return result;
			
		}
		
		public int getActivFlagCountry(DataSource ds, int PlaceID)
		{
			int result = 0;
			
			String strQuery="";
			
			strQuery= "select active from countries where id = "+PlaceID;
			
			
			result = masterUtil.getId(ds, strQuery, "active");	
			
			return result;
			
		}
		
		public int getEnableFlagDep(DataSource ds, int depID)
		{
			int result = 0;
			
			String strQuery="";
			
			strQuery= "select dep_enable from department where dep_id = "+depID;
			
			
			result = masterUtil.getId(ds, strQuery, "dep_enable");	
			
			return result;
			
		}
		
		public int getEnableFlagDes(DataSource ds, int desID)
		{
			int result = 0;
			
			String strQuery="";
			
			strQuery= "select enable from designation where des_id = "+desID;
			
			
			result = masterUtil.getId(ds, strQuery, "enable");	
			
			return result;
			
		}
		
		public Vector getCourse(DataSource ds)
		{
			Support support=new Support();
			String strQuery="";
			Vector<Vector>resultVec = new Vector<Vector>();
			Vector<String>tempVec = new Vector<String>();
		  	 tempVec.add("0");
		  	 tempVec.add("Select");
		  	 resultVec.add(tempVec);
			 	 strQuery="select * from courses where cou_enabled=1 order by cou_name";
				 support.setFieldVec("int", "cou_id");
				 support.setFieldVec("String", "cou_name");
				 Vector <Vector>tempResultVec  = new Vector<Vector>();
				 tempResultVec=(Vector)masterUtil.getList(ds, strQuery, support);
				 for(int i=0; i<tempResultVec.size();i++)
				 {
					 resultVec.add(tempResultVec.elementAt(i));
				 }
			
			Vector<String>tempVec2 = new Vector<String>();
			tempVec2.add("-1");
			tempVec2.add("Others");
			resultVec.add(tempVec2);
				 
			 
			 return resultVec;
		}
		
		public Vector getStream(int cId, DataSource ds)
		{
			Support support=new Support();
			String strQuery="";
			Vector<Vector>resultVec = new Vector<Vector>();
			Vector<String>tempVec = new Vector<String>();
		  	 tempVec.add("0");
		  	 tempVec.add("Select");
		  	 resultVec.add(tempVec);
			 	 strQuery="select * from course_stream where coustr_enabled=1 and cou_id="+cId+" order by coustr_name";
				 support.setFieldVec("int", "coustr_id");
				 support.setFieldVec("String", "coustr_name");
				 Vector <Vector>tempResultVec  = new Vector<Vector>();
				 tempResultVec=(Vector)masterUtil.getList(ds, strQuery, support);
				 for(int i=0; i<tempResultVec.size();i++)
				 {
					 resultVec.add(tempResultVec.elementAt(i));
				 }
			
			Vector<String>tempVec2 = new Vector<String>();
			tempVec2.add("-1");
			tempVec2.add("Others");
			resultVec.add(tempVec2);
				 
			 
			 return resultVec;
		}
		
		//insert courses detail in data base
		public String insertCourse(DataSource ds,String CourseName) {
			Support support=new Support();
			String strQuery="";
			String result ="";
			strQuery = "Insert into courses (cou_name) values(?)";
			
			support.setDataVec("String", CourseName);
			result = masterUtil.setData(ds, strQuery, support);
			if(result.equalsIgnoreCase("success"))
			{
				strQuery = "select max(cou_id) cou_id from courses where  cou_name='"+CourseName+"'";
				result = String.valueOf(masterUtil.getId(ds, strQuery, "cou_id"));
			}
			
			Resource resr = new Resource();
			MailText mt = new MailText();
			String strMailText = "";
			String addfield = "Course";
		    
		    strMailText = mt.getEmailAddNew(addfield, CourseName, result );
						
			String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender, "feedback@campusyogi.com", "Add new Course: "+result+"");
			

			return 	result;
		}
		//end inserting courses
		
		//insert Stream detail in data base
		public String insertStream(DataSource ds,String CourseId, String StreamName) {
			Support support=new Support();
			String strQuery="";
			String result ="";
			strQuery = "Insert into course_stream (coustr_name,cou_id) values(?,?)";
			////////////////////System.out.println("strQuery......."+strQuery);
			
			support.setDataVec("String", StreamName);
			support.setDataVec("int", CourseId);
			result = masterUtil.setData(ds, strQuery, support);
			////////////////////System.out.println("result......."+result);
			if(result.equalsIgnoreCase("success"))
			{
				strQuery = "select max(coustr_id) coustr_id from course_stream where  coustr_name='"+StreamName+"' and cou_id='"+CourseId+"'";
				////////////////////System.out.println("strQuery......."+strQuery);
				result = String.valueOf(masterUtil.getId(ds, strQuery, "coustr_id"));
				////////////////////System.out.println("result......."+result);
			}
			
			Resource resr = new Resource();
			MailText mt = new MailText();
			String strMailText = "";
			String addfield = "Course Stream ";
		    
		    strMailText = mt.getEmailAddNew(addfield, StreamName, result );
						
			String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender, "feedback@campusyogi.com", "Add new course stream : "+result+"");
			
			return 	result;
		}
		//end inserting Stream
		/**
		 * To create 
		 */
	/*	public void CreateBlogsFile(DataSource ds)
		{
			//////////////////////System.out.println("Called Arun");
			Support support = new Support();
			String strQuery = "Select l.userid userid, l.first_name first_name, l.last_name last_name, p.PlaceName city, l.login_type login_type, l.companyid companyid, cm.company_name company_name  from loginmaster l, places p, companymaster cm  where l.logindeletion = 0 and l.city=p.PlaceID and cm.company_id=l.companyid order by l.creationDate desc , l.creationTime desc limit 10";
			
			support.setFieldVec("int", "userid");           //0
			support.setFieldVec("String", "first_name");	//1
			support.setFieldVec("String", "last_name");		//2
			support.setFieldVec("String", "city");			//3
			support.setFieldVec("String", "login_type");	//4
			support.setFieldVec("int", "companyid");		//5 
			support.setFieldVec("String", "company_name");		//6 
			
			Vector complaintVec = masterUtil.getList(ds, strQuery, support);
			//////////////////////System.out.println("complaintVec Arun "+complaintVec);`companyid`
			if(complaintVec.size()>0)
			{
				String strToWrite = "";
				for(int i=0; i<complaintVec.size(); i++)
				{
					Vector tempVec = (Vector)complaintVec.elementAt(i);
					String login_type = tempVec.elementAt(4).toString();
					String companyid = tempVec.elementAt(5).toString();
					
					if(login_type.equalsIgnoreCase("Student")){
						
						if(strToWrite.equalsIgnoreCase(""))
						{
							strToWrite = "document.write('<a class=\"Arial_Narrow_11_black_normal\" href=\"http://www.campusyogi.com/campusyogi/studentResumePre.do?pageid=1&detailFor=Student&userid="+tempVec.elementAt(0).toString().trim()+"&minVal=0\">"+tempVec.elementAt(1).toString().trim()+"  "+tempVec.elementAt(2).toString().trim()+", "+tempVec.elementAt(3).toString().trim()+"</a>');";
						}
						else
						{
							strToWrite = strToWrite+"\ndocument.write('<br><br><a class=\"Arial_Narrow_11_black_normal\" href=\"http://www.campusyogi.com/campusyogi/studentResumePre.do?pageid=1&detailFor=Student&userid="+tempVec.elementAt(0).toString().trim()+"&minVal=0\">"+tempVec.elementAt(1).toString().trim()+"  "+tempVec.elementAt(2).toString().trim()+", "+tempVec.elementAt(3).toString().trim()+"</a>');";
						}
						
					}
					else if(login_type.equalsIgnoreCase("Corporates")){
						
						if(strToWrite.equalsIgnoreCase(""))
						{
							strToWrite = "document.write('<a class=\"Arial_Narrow_11_black_normal\" href=\"http://www.campusyogi.com/campusyogi/compProfilePre.do?pageid=1&detailFor=Corporates&cuid="+companyid+"&minVal=0\">"+tempVec.elementAt(6).toString().trim()+" , "+tempVec.elementAt(3).toString().trim()+"</a>');";
						}
						else
						{
							strToWrite = strToWrite+"\ndocument.write('<br><br><a class=\"Arial_Narrow_11_black_normal\" href=\"http://www.campusyogi.com/campusyogi/compProfilePre.do?pageid=1&detailFor=Corporates&cuid="+companyid+"&minVal=0\">"+tempVec.elementAt(6).toString().trim()+"  , "+tempVec.elementAt(3).toString().trim()+"</a>');";
						}
						
					}
					else if(login_type.equalsIgnoreCase("Advertiser")){
						
						if(strToWrite.equalsIgnoreCase(""))
						{
							strToWrite = "document.write('<a class=\"Arial_Narrow_11_black_normal\" href=\"http://www.campusyogi.com/campusyogi/compProfilePre.do?pageid=1&detailFor=Advertiser&cuid="+companyid+"&minVal=0\">"+tempVec.elementAt(6).toString().trim()+"  , "+tempVec.elementAt(3).toString().trim()+"</a>');";
						}
						else
						{
							strToWrite = strToWrite+"\ndocument.write('<br><br><a class=\"Arial_Narrow_11_black_normal\" href=\"http://www.campusyogi.com/campusyogi/compProfilePre.do?pageid=1&detailFor=Advertiser&cuid="+companyid+"&minVal=0\">"+tempVec.elementAt(6).toString().trim()+" , "+tempVec.elementAt(3).toString().trim()+"</a>');";
						}						
					} 
				}
				String strPath = Constant.Blog_File;
				//////////////////////System.out.println("Arun Create file Address is "+strPath);
				Resource res = new Resource();
				boolean blnResult = res.writeFile(strToWrite, strPath);
				if(blnResult==true){}
			}
		}
		*/
		
		/*
		 	public void CreateBlogsFile(DataSource ds)
		{
			//////////////////////System.out.println("Called Arun");
			Support support = new Support();
			String strQuery = "Select c.complaint_id cid, c.complaint_title Complaint, l.first_name Name, p.Place City from complaints c, loginmaster l, places p where c.publish_flag=1 and c.login_id = l.userid and l.city=p.PlaceID order by creation_date desc , creation_time desc limit 10";
			support.setFieldVec("int", "cid");
			support.setFieldVec("String", "Complaint");
			support.setFieldVec("String", "Name");
			support.setFieldVec("String", "City");
			Vector complaintVec = masterUtil.getList(ds, strQuery, support);
			//////////////////////System.out.println("complaintVec Arun "+complaintVec);
			if(complaintVec.size()>0)
			{
				String strToWrite = "";
				for(int i=0; i<complaintVec.size(); i++)
				{
					Vector tempVec = (Vector)complaintVec.elementAt(i);
					if(strToWrite.equalsIgnoreCase(""))
					{
						strToWrite = "document.write('<a class=\"Arial_Narrow_11_black_normal\" href=\"http://www.campusyogi.com/campusyogi/complaintsDetail.jsp?complainId="+tempVec.elementAt(0).toString().trim()+"&minVal=0\">"+tempVec.elementAt(1).toString().trim()+" by "+tempVec.elementAt(2).toString().trim()+", "+tempVec.elementAt(3).toString().trim()+"</a>');";
					}
					else
					{
						strToWrite = strToWrite+"\ndocument.write('<br><br><a class=\"Arial_Narrow_11_black_normal\" href=\"http://www.campusyogi.com/campusyogi/complaintsDetail.jsp?complainId="+tempVec.elementAt(0).toString().trim()+"&minVal=0\">"+tempVec.elementAt(1).toString().trim()+" by "+tempVec.elementAt(2).toString().trim()+", "+tempVec.elementAt(3).toString().trim()+"</a>');";
					}
				}
				String strPath = Constant.Blog_File;
				//////////////////////System.out.println("Arun Create file Address is "+strPath);
				Resource res = new Resource();
				boolean blnResult = res.writeFile(strToWrite, strPath);
				if(blnResult==true){}
			}
		}
		
		 */
		
		


		public void CreateBlogsFile(DataSource ds)
			{
				
				Support support = new Support();
				Support support1 = new Support();

				String strQuery = "Select l.userid userid12, l.first_name first_name, l.last_name last_name, p.PlaceName city, l.login_type login_type, l.companyid companyid from loginmaster l, places p where l.logindeletion = 0 and  l.login_type='Student' and l.city=p.PlaceID order by l.activDate desc , l.activTime desc limit 4";
				
				support.setFieldVec("int", "userid12");         //0
				support.setFieldVec("String", "first_name");	//1
				support.setFieldVec("String", "last_name");		//2
				support.setFieldVec("String", "city");			//3
				support.setFieldVec("String", "login_type");	//4
				support.setFieldVec("int", "companyid");		//5 	
				
				Vector complaintVec = masterUtil.getList(ds, strQuery, support);
				

				String strQuery1 = "Select c.company_id company_id, c.company_name, c.typeofcompany typeofcompany,  p.PlaceName city from companymaster c, places p where c.company_city=p.PlaceID and c.active = 1 order by c.date_of_creation desc  limit 5";
				
				support1.setFieldVec("int", "company_id");           //0
				support1.setFieldVec("String", "company_name");		//1			
				support1.setFieldVec("String", "city");				//2
				support1.setFieldVec("String", "typeofcompany");		//3

				Vector complaintVec1 = masterUtil.getList(ds, strQuery1, support1);
				
				

				String strToWrite = "";

				if(complaintVec.size()>0)
				{
					
					for(int i=0; i<complaintVec.size(); i++)
					{
						Vector tempVec = (Vector)complaintVec.elementAt(i);
											
							
							if(strToWrite.equalsIgnoreCase(""))
							{
								strToWrite = "document.write('<p><img src=\"images/blogIndex_icon.gif\" width=\"13\" height=\"13\" /><a href=\"http://www.campusyogi.com/campusyogi/studentResumePre.do?pageid=1&detailFor=Student&userid="+tempVec.elementAt(0).toString().trim()+"&minVal=0\">"+tempVec.elementAt(1).toString().trim()+"  "+tempVec.elementAt(2).toString().trim()+", "+tempVec.elementAt(3).toString().trim()+"</a><br /></p>');";
							}
							else
							{
								strToWrite = strToWrite+"\ndocument.write('<p><img src=\"images/blogIndex_icon.gif\" width=\"13\" height=\"13\" /><a  href=\"http://www.campusyogi.com/campusyogi/studentResumePre.do?pageid=1&detailFor=Student&userid="+tempVec.elementAt(0).toString().trim()+"&minVal=0\">"+tempVec.elementAt(1).toString().trim()+"  "+tempVec.elementAt(2).toString().trim()+", "+tempVec.elementAt(3).toString().trim()+"</a><br /></p>');";
							}	
							
							
							
					}
					
				}

				if(complaintVec1.size()>0)
				{
					
					for(int i=0; i<complaintVec1.size(); i++)
					{
						Vector tempVec = (Vector)complaintVec1.elementAt(i);
						String typeofcompany = tempVec.elementAt(3).toString();
									
						
						if(typeofcompany.equalsIgnoreCase("Corporates")){

							if(strToWrite.equalsIgnoreCase(""))
							{
								strToWrite = "document.write('<p><img src=\"images/blogIndex_icon.gif\" width=\"13\" height=\"13\" /><a class=\"Arial_Narrow_11_black_normal\" href=\"http://www.campusyogi.com/campusyogi/compProfilePre.do?pageid=1&detailFor=Corporates&cuid="+tempVec.elementAt(0).toString().trim()+"&minVal=0\">"+tempVec.elementAt(1).toString().trim()+" , "+tempVec.elementAt(2).toString().trim()+"</a><br /></p>');";
							}
							else
							{
								strToWrite = strToWrite+"\ndocument.write('<p><img src=\"images/blogIndex_icon.gif\" width=\"13\" height=\"13\" /><a class=\"Arial_Narrow_11_black_normal\" href=\"http://www.campusyogi.com/campusyogi/compProfilePre.do?pageid=1&detailFor=Corporates&cuid="+tempVec.elementAt(0).toString().trim()+"&minVal=0\">"+tempVec.elementAt(1).toString().trim()+"  , "+tempVec.elementAt(2).toString().trim()+"</a><br /></p>');";
							}					
						}else if(typeofcompany.equalsIgnoreCase("Advertiser")){

							
							if(strToWrite.equalsIgnoreCase(""))
							{
								strToWrite = "document.write('<p><img src=\"images/blogIndex_icon.gif\" width=\"13\" height=\"13\" /><a class=\"Arial_Narrow_11_black_normal\" href=\"http://www.campusyogi.com/campusyogi/compProfilePre.do?pageid=1&detailFor=Advertiser&cuid="+tempVec.elementAt(0).toString().trim()+"&minVal=0\">"+tempVec.elementAt(1).toString().trim()+" , "+tempVec.elementAt(2).toString().trim()+"</a><br /></p>');";
							}
							else
							{
								strToWrite = strToWrite+"\ndocument.write('<p><img src=\"images/blogIndex_icon.gif\" width=\"13\" height=\"13\" /><a class=\"Arial_Narrow_11_black_normal\" href=\"http://www.campusyogi.com/campusyogi/compProfilePre.do?pageid=1&detailFor=Advertiser&cuid="+tempVec.elementAt(0).toString().trim()+"&minVal=0\">"+tempVec.elementAt(1).toString().trim()+"  , "+tempVec.elementAt(2).toString().trim()+"</a><br /></p>');";
							}	

						}
						
						
							String strPath = Constant.Blog_File;
							//////////////System.out.println("Arun Create file Address is "+strPath);
							Resource res = new Resource();
							boolean blnResult = res.writeFile(strToWrite, strPath);
							if(blnResult==true){}
				
				}
			}

			
		}
			
		
		
		
		
		
		//get compan/college `registered` flag.
		public int getRegisteredFlag(DataSource ds, String companyId)
		{
			int result = 0;
			
			String strQuery="";
			
			strQuery= "select registered from companymaster where company_id = '"+companyId+"'" ;
			
			
			result = masterUtil.getId(ds, strQuery, "registered");	
			
			return result;
			
		}
		
		public int getStudentInCollCount(DataSource ds, int collId){
			int result = 0;
			
			
			String login_type = "Student";
		
			
			String strQuery="";
			strQuery= "Select count(*) count from loginmaster where college_id= "+collId+" and login_type='"+login_type+"' and logindeletion=0";	
			
			
			result = masterUtil.getCount(ds, strQuery);
			return result;
		}
		
		public String insertSearchStuResult(DataSource ds, Vector dataVec) {
			Support support=new Support();
			String strQuery="";
			
			
			
			strQuery = "Insert into searchresult (user_id, company_id, sr_titel, sr_date, assess_type, studentsId, searchtype) values(?, ?, ?, ?, ?, ?, ?)";

			
			support.setDataVec("int", dataVec.elementAt(0).toString().trim());
			support.setDataVec("int", dataVec.elementAt(1).toString().trim());
			support.setDataVec("string", dataVec.elementAt(2).toString().trim());
			support.setDataVec("string", dataVec.elementAt(3).toString().trim());
			support.setDataVec("int", dataVec.elementAt(4).toString().trim());
			support.setDataVec("string", dataVec.elementAt(5).toString().trim());
			support.setDataVec("string", dataVec.elementAt(6).toString().trim());
			

			return masterUtil.setData(ds, strQuery, support);

			
		}
		
		
		public Vector getSearchStuResultList(DataSource ds, Vector dataVec){
			Vector resultVec = new Vector();
			
			int userId = Integer.parseInt(dataVec.elementAt(0).toString().trim());
			int companyId = Integer.parseInt(dataVec.elementAt(1).toString().trim());
			

			Support support=new Support();
			String strQuery="";
			strQuery = "SELECT * from searchresult  where user_id="+userId+" and company_id="+companyId+"";
			
			support.setFieldVec("int", "sr_id");
			support.setFieldVec("int", "user_id");
			support.setFieldVec("int", "company_id");
			support.setFieldVec("String", "sr_titel");
			support.setFieldVec("String", "sr_date");
			support.setFieldVec("int", "assess_type");
			support.setFieldVec("String", "studentsId");
			
			////////////////////////System.out.println("resultVec in advt master.."+strQuery);
			resultVec = masterUtil.getList(ds, strQuery, support);
			
			return resultVec;
		}
		
		public Vector getSearchStuCGlobalList(DataSource ds, Vector dataVec){
			Vector resultVec = new Vector();
			
			int userId = Integer.parseInt(dataVec.elementAt(0).toString().trim());
			int companyId = Integer.parseInt(dataVec.elementAt(1).toString().trim());
			

			Support support=new Support();
			String strQuery="";
			strQuery = "SELECT * from searchresult  where user_id!="+userId+" and company_id="+companyId+" and assess_type=1";
			
			support.setFieldVec("int", "sr_id");
			support.setFieldVec("int", "user_id");
			support.setFieldVec("int", "company_id");
			support.setFieldVec("String", "sr_titel");
			support.setFieldVec("String", "sr_date");
			support.setFieldVec("int", "assess_type");
			support.setFieldVec("String", "studentsId");
			
			////////////////////////System.out.println("resultVec in advt master.."+strQuery);
			resultVec = masterUtil.getList(ds, strQuery, support);
			
			
			return resultVec;
		}
		
		public String deleteStuResult(DataSource ds,Vector dataVec)
		{	
			
			
		    String strQuery="";
			String strResult="";
				  
			strQuery = "delete from searchresult where sr_id in ("+dataVec.elementAt(0).toString().trim()+")";
			
			strResult = masterUtil.deleteData(ds, strQuery);
			
			return strResult;
		}
		
		public String deleteSearchCriteria(DataSource ds,Vector dataVec)
		{	
			
			
		    String strQuery="";
			String strResult="";
				  
			strQuery = "delete from searchc_cry where scry_id in ("+dataVec.elementAt(0).toString().trim()+")";
			////////////////////System.out.println("strQuery.in root......"+strQuery);
			
			strResult = masterUtil.deleteData(ds, strQuery);
			
			return strResult;
		}
		
		
		
			public String getStudentsId(DataSource ds,int sr_id){
			
			
			//////////////////////////System.out.println("In siet Advt Master getUserCount");
			
			
			String strResult="";
			Support support=new Support();					
			   String strQuery="";
			
				strQuery = "Select studentsId from searchresult where  sr_id="+sr_id;
				
				support.setFieldVec("String", "studentsId");
				
				
				
				strResult = masterUtil.getValue(ds, strQuery, support);
				
				////////////////////////System.out.println("In  strQuery...."+strQuery);
				////////////////////////System.out.println("In  result...."+strResult);
				
				return strResult;
		}
			
			public String getSearchType(DataSource ds,int sr_id){
				
				
				//////////////////////////System.out.println("In siet Advt Master getUserCount");
				
				
				String strResult="";
				Support support=new Support();					
				   String strQuery="";
				
					strQuery = "Select searchtype from searchresult where  sr_id="+sr_id;
					
					support.setFieldVec("String", "searchtype");
					
					
					
					strResult = masterUtil.getValue(ds, strQuery, support);
					
					////////////////////////System.out.println("In  strQuery...."+strQuery);
					////////////////////////System.out.println("In  result...."+strResult);
					
					return strResult;
			}
				
			
			
			public Vector getStudentList(DataSource ds, String dataStr){
				Vector resultVec = new Vector();
				
				Support support=new Support();
				String strQuery="";
				
				strQuery = "Select * from loginmaster where userid in ("+dataStr+")";
				
				support.setFieldVec("int", "userid");//0
				support.setFieldVec("string", "first_name");//1
				support.setFieldVec("string", "last_name");//2
				support.setFieldVec("string", "status");//3
				support.setFieldVec("string", "course");//4
				support.setFieldVec("string", "college_id");//5
				support.setFieldVec("string", "other_coll_id");//6
				support.setFieldVec("string", "photopath");//7
				
				////////////////////////System.out.println("resultVec in advt master.."+strQuery);
				resultVec = masterUtil.getList(ds, strQuery, support);
				
				
				return resultVec;
			}
			
			public Vector getCollegeList(DataSource ds, String dataStr){
				Vector resultVec = new Vector();
				
				Support support=new Support();
				String strQuery="";
				
				strQuery = "Select * from companymaster where company_id in ("+dataStr+")";
				
				support.setFieldVec("int", "company_id");//0
				support.setFieldVec("string", "company_sname");//1
				support.setFieldVec("string", "company_name");//2
				support.setFieldVec("string", "company_city");//3
				support.setFieldVec("string", "company_state");//4
				support.setFieldVec("string", "company_country");//5
				
				//////////////////////System.out.println("resultVec in advt master.."+strQuery);
				resultVec = masterUtil.getList(ds, strQuery, support);
				
				
				return resultVec;
			}
			
			
			public String insertSearchCriteria(DataSource ds, Vector dataVec) {
				Support support=new Support();
				String strQuery="";
				
				
				
				strQuery = "Insert into searchc_cry (user_id, comp_id, titel, user_vec, search_vec, search_for, par_gob, date_sry) values(?, ?, ?, ?, ?, ?, ?, ?)";

				
				support.setDataVec("int", dataVec.elementAt(0).toString().trim());
				support.setDataVec("int", dataVec.elementAt(1).toString().trim());
				support.setDataVec("string", dataVec.elementAt(2).toString().trim());
				support.setDataVec("string", dataVec.elementAt(3).toString().trim());
				support.setDataVec("string", dataVec.elementAt(4).toString().trim());
				support.setDataVec("string", dataVec.elementAt(5).toString().trim());
				support.setDataVec("int", dataVec.elementAt(6).toString().trim());
				support.setDataVec("string", dataVec.elementAt(7).toString().trim());
				

				return masterUtil.setData(ds, strQuery, support);

				
			}
			
			
			public Vector getSearchCriteriaList(DataSource ds, Vector dataVec){
				Vector resultVec = new Vector();
				
				int userId = Integer.parseInt(dataVec.elementAt(0).toString().trim());
				int companyId = Integer.parseInt(dataVec.elementAt(1).toString().trim());
				

				Support support=new Support();
				String strQuery="";
				strQuery = "SELECT * from searchc_cry  where user_id="+userId+" and comp_id="+companyId+"";
				
				support.setFieldVec("int", "scry_id");
				support.setFieldVec("int", "user_id");
				support.setFieldVec("int", "comp_id");
				support.setFieldVec("String", "titel");
				support.setFieldVec("String", "date_sry");
				support.setFieldVec("int", "par_gob");
				support.setFieldVec("String", "search_for");
				support.setFieldVec("String", "user_vec");
				support.setFieldVec("String", "search_vec");
				
				////////////////////////System.out.println("resultVec in advt master.."+strQuery);
				resultVec = masterUtil.getList(ds, strQuery, support);
				
				
				return resultVec;
	}
			
			public Vector getSearchCriteriaGlobalList(DataSource ds, Vector dataVec){
				Vector resultVec = new Vector();
				
				int userId = Integer.parseInt(dataVec.elementAt(0).toString().trim());
				int companyId = Integer.parseInt(dataVec.elementAt(1).toString().trim());
				

				Support support=new Support();
				String strQuery="";
				strQuery = "SELECT * from searchc_cry  where user_id!="+userId+" and comp_id="+companyId+" and par_gob=1";
				
				support.setFieldVec("int", "scry_id");
				support.setFieldVec("int", "user_id");
				support.setFieldVec("int", "comp_id");
				support.setFieldVec("String", "titel");
				support.setFieldVec("String", "date_sry");
				support.setFieldVec("int", "par_gob");
				support.setFieldVec("String", "search_for");
				support.setFieldVec("String", "user_vec");
				support.setFieldVec("String", "search_vec");
				
				////////////////////////System.out.println("resultVec in advt master.."+strQuery);
				resultVec = masterUtil.getList(ds, strQuery, support);
				
				
				return resultVec;
			}
			
			public Vector getSearchCriteria(DataSource ds, int scry_id) {
				Support support=new Support();
				String strQuery="";
				strQuery = "select * from searchc_cry where scry_id ="+scry_id;
						

				support.setFieldVec("int", "scry_id");
				support.setFieldVec("int", "user_id");
				support.setFieldVec("int", "comp_id");
				support.setFieldVec("String", "titel");
				support.setFieldVec("String", "date_sry");
				support.setFieldVec("int", "par_gob");
				support.setFieldVec("String", "search_for");
				support.setFieldVec("String", "user_vec");
				support.setFieldVec("String", "search_vec");

				return masterUtil.getDetail(ds, strQuery, support);
			}
		
		public Vector getTestQuestions(DataSource ds, int numTestId)
		{
			Support support=new Support();
			String strQuery = "Select * From test_mapping where sno="+numTestId;
			support.setFieldVec("String", "ques1");
			support.setFieldVec("String", "ques2");
			support.setFieldVec("String", "ques3");
			support.setFieldVec("String", "ques4");
			support.setFieldVec("String", "ques5");
			support.setFieldVec("String", "ques6");
			support.setFieldVec("String", "ques7");
			support.setFieldVec("String", "ques8");
			support.setFieldVec("String", "ques9");
			support.setFieldVec("String", "ques10");
			support.setFieldVec("String", "ques11");
			support.setFieldVec("String", "ques12");
			support.setFieldVec("String", "ques13");
			support.setFieldVec("String", "ques14");
			support.setFieldVec("String", "ques15");
			support.setFieldVec("String", "ques16");
			support.setFieldVec("String", "ques17");
			support.setFieldVec("String", "ques18");
			support.setFieldVec("String", "ques19");
			support.setFieldVec("String", "ques20");
			support.setFieldVec("String", "ques21");
			support.setFieldVec("String", "ques22");
			support.setFieldVec("String", "ques23");
			support.setFieldVec("String", "ques24");
			support.setFieldVec("String", "ques25");
			support.setFieldVec("String", "ques26");
			support.setFieldVec("String", "ques27");
			support.setFieldVec("String", "ques28");
			support.setFieldVec("String", "ques29");
			support.setFieldVec("String", "ques30");
			support.setFieldVec("String", "ques31");
			support.setFieldVec("String", "ques32");
			support.setFieldVec("String", "ques33");
			support.setFieldVec("String", "ques34");
			support.setFieldVec("String", "ques35");			
			support.setFieldVec("String", "ques1_type");
			support.setFieldVec("String", "ques2_type");
			support.setFieldVec("String", "ques3_type");
			support.setFieldVec("String", "ques4_type");
			support.setFieldVec("String", "ques5_type");
			support.setFieldVec("String", "ques6_type");
			support.setFieldVec("String", "ques7_type");
			support.setFieldVec("String", "ques8_type");
			support.setFieldVec("String", "ques9_type");
			support.setFieldVec("String", "ques10_type");
			support.setFieldVec("String", "ques11_type");
			support.setFieldVec("String", "ques12_type");
			support.setFieldVec("String", "ques13_type");
			support.setFieldVec("String", "ques14_type");
			support.setFieldVec("String", "ques15_type");
			support.setFieldVec("String", "ques16_type");
			support.setFieldVec("String", "ques17_type");
			support.setFieldVec("String", "ques18_type");
			support.setFieldVec("String", "ques19_type");
			support.setFieldVec("String", "ques20_type");
			support.setFieldVec("String", "ques21_type");
			support.setFieldVec("String", "ques22_type");
			support.setFieldVec("String", "ques23_type");
			support.setFieldVec("String", "ques24_type");
			support.setFieldVec("String", "ques25_type");
			support.setFieldVec("String", "ques26_type");
			support.setFieldVec("String", "ques27_type");
			support.setFieldVec("String", "ques28_type");
			support.setFieldVec("String", "ques29_type");
			support.setFieldVec("String", "ques30_type");
			support.setFieldVec("String", "ques31_type");
			support.setFieldVec("String", "ques32_type");
			support.setFieldVec("String", "ques33_type");
			support.setFieldVec("String", "ques34_type");
			support.setFieldVec("String", "ques35_type");
			support.setFieldVec("String", "ques1_check");
			support.setFieldVec("String", "ques2_check");
			support.setFieldVec("String", "ques3_check");
			support.setFieldVec("String", "ques4_check");
			support.setFieldVec("String", "ques5_check");
			support.setFieldVec("String", "ques6_check");
			support.setFieldVec("String", "ques7_check");
			support.setFieldVec("String", "ques8_check");
			support.setFieldVec("String", "ques9_check");
			support.setFieldVec("String", "ques10_check");
			support.setFieldVec("String", "ques11_check");
			support.setFieldVec("String", "ques12_check");
			support.setFieldVec("String", "ques13_check");
			support.setFieldVec("String", "ques14_check");
			support.setFieldVec("String", "ques15_check");
			support.setFieldVec("String", "ques16_check");
			support.setFieldVec("String", "ques17_check");
			support.setFieldVec("String", "ques18_check");
			support.setFieldVec("String", "ques19_check");
			support.setFieldVec("String", "ques20_check");
			support.setFieldVec("String", "ques21_check");
			support.setFieldVec("String", "ques22_check");
			support.setFieldVec("String", "ques23_check");
			support.setFieldVec("String", "ques24_check");
			support.setFieldVec("String", "ques25_check");
			support.setFieldVec("String", "ques26_check");
			support.setFieldVec("String", "ques27_check");
			support.setFieldVec("String", "ques28_check");
			support.setFieldVec("String", "ques29_check");
			support.setFieldVec("String", "ques30_check");
			support.setFieldVec("String", "ques31_check");
			support.setFieldVec("String", "ques32_check");
			support.setFieldVec("String", "ques33_check");
			support.setFieldVec("String", "ques34_check");
			support.setFieldVec("String", "ques35_check");
			support.setFieldVec("String", "ques1_visible");
			support.setFieldVec("String", "ques2_visible");
			support.setFieldVec("String", "ques3_visible");
			support.setFieldVec("String", "ques4_visible");
			support.setFieldVec("String", "ques5_visible");
			support.setFieldVec("String", "ques6_visible");
			support.setFieldVec("String", "ques7_visible");
			support.setFieldVec("String", "ques8_visible");
			support.setFieldVec("String", "ques9_visible");
			support.setFieldVec("String", "ques10_visible");
			support.setFieldVec("String", "ques11_visible");
			support.setFieldVec("String", "ques12_visible");
			support.setFieldVec("String", "ques13_visible");
			support.setFieldVec("String", "ques14_visible");
			support.setFieldVec("String", "ques15_visible");
			support.setFieldVec("String", "ques16_visible");
			support.setFieldVec("String", "ques17_visible");
			support.setFieldVec("String", "ques18_visible");
			support.setFieldVec("String", "ques19_visible");
			support.setFieldVec("String", "ques20_visible");
			support.setFieldVec("String", "ques21_visible");
			support.setFieldVec("String", "ques22_visible");
			support.setFieldVec("String", "ques23_visible");
			support.setFieldVec("String", "ques24_visible");
			support.setFieldVec("String", "ques25_visible");
			support.setFieldVec("String", "ques26_visible");
			support.setFieldVec("String", "ques27_visible");
			support.setFieldVec("String", "ques28_visible");
			support.setFieldVec("String", "ques29_visible");
			support.setFieldVec("String", "ques30_visible");
			support.setFieldVec("String", "ques31_visible");
			support.setFieldVec("String", "ques32_visible");
			support.setFieldVec("String", "ques33_visible");
			support.setFieldVec("String", "ques34_visible");
			support.setFieldVec("String", "ques35_visible");
			support.setFieldVec("String", "testname");
			support.setFieldVec("int", "totalmarks");
			support.setFieldVec("String", "testremarks");
			support.setFieldVec("String", "testtype");
			support.setFieldVec("String", "testcategory");
			support.setFieldVec("String", "maxtime");
			return masterUtil.getDetail(ds, strQuery, support);
		}
		
		public int getTestId(DataSource ds, int numCompId, String testName, String testtyp, String testCat)
		{
			String strQuery = "Select sno From test_mapping where companyid="+numCompId+" and testname='"+testName+"' and testtype='"+testtyp+"' and testcategory='"+testCat+"'";
			return masterUtil.getId(ds, strQuery, "sno");
		}
		public String setTest(DataSource ds, Vector dataVec)
		{
			Support support=new Support();
			String strQuery = "Insert into test_mapping(companyid, testname, totalmarks, ";
			strQuery += "ques1, ques1_type, ques1_check, ques1_visible, ";
			strQuery += "ques2, ques2_type, ques2_check, ques2_visible, ";
			strQuery += "ques3, ques3_type, ques3_check, ques3_visible, ";
			strQuery += "ques4, ques4_type, ques4_check, ques4_visible, ";
			strQuery += "ques5, ques5_type, ques5_check, ques5_visible, ";
			strQuery += "ques6, ques6_type, ques6_check, ques6_visible, ";
			strQuery += "ques7, ques7_type, ques7_check, ques7_visible, ";
			strQuery += "ques8, ques8_type, ques8_check, ques8_visible, ";
			strQuery += "ques9, ques9_type, ques9_check, ques9_visible, ";
			strQuery += "ques10, ques10_type, ques10_check, ques10_visible, ";
			strQuery += "ques11, ques11_type, ques11_check, ques11_visible, ";
			strQuery += "ques12, ques12_type, ques12_check, ques12_visible, ";
			strQuery += "ques13, ques13_type, ques13_check, ques13_visible, ";
			strQuery += "ques14, ques14_type, ques14_check, ques14_visible, ";
			strQuery += "ques15, ques15_type, ques15_check, ques15_visible, ";
			strQuery += "ques16, ques16_type, ques16_check, ques16_visible, ";
			strQuery += "ques17, ques17_type, ques17_check, ques17_visible, ";
			strQuery += "ques18, ques18_type, ques18_check, ques18_visible, ";
			strQuery += "ques19, ques19_type, ques19_check, ques19_visible, ";
			strQuery += "ques20, ques20_type, ques20_check, ques20_visible, ";
			strQuery += "ques21, ques21_type, ques21_check, ques21_visible, ";
			strQuery += "ques22, ques22_type, ques22_check, ques22_visible, ";
			strQuery += "ques23, ques23_type, ques23_check, ques23_visible, ";
			strQuery += "ques24, ques24_type, ques24_check, ques24_visible, ";
			strQuery += "ques25, ques25_type, ques25_check, ques25_visible, ";
			strQuery += "ques26, ques26_type, ques26_check, ques26_visible, ";
			strQuery += "ques27, ques27_type, ques27_check, ques27_visible, ";
			strQuery += "ques28, ques28_type, ques28_check, ques28_visible, ";
			strQuery += "ques29, ques29_type, ques29_check, ques29_visible, ";
			strQuery += "ques30, ques30_type, ques30_check, ques30_visible, ";
			strQuery += "ques31, ques31_type, ques31_check, ques31_visible, ";
			strQuery += "ques32, ques32_type, ques32_check, ques32_visible, ";
			strQuery += "ques33, ques33_type, ques33_check, ques33_visible, ";
			strQuery += "ques34, ques34_type, ques34_check, ques34_visible, ";
			strQuery += "ques35, ques35_type, ques35_check, ques35_visible, ";
			strQuery += "testtype, testremarks, testcategory, maxtime, tc_id) values (";
			for(int i=0; i<=147; i++)
			{
				if(i==147)
				{
					strQuery += "?";
				}
				else
				{
					strQuery += "?, ";
				}
			}
			strQuery += ")";
			////////////////////System.out.println("Test Query "+strQuery);
			support.setDataVec("int", dataVec.elementAt(0).toString().trim());
			support.setDataVec("String", dataVec.elementAt(1).toString().trim());
			support.setDataVec("int", dataVec.elementAt(2).toString().trim());
			support.setDataVec("String", dataVec.elementAt(3).toString().trim());
			support.setDataVec("String", dataVec.elementAt(4).toString().trim());
			support.setDataVec("String", dataVec.elementAt(5).toString().trim());
			support.setDataVec("String", dataVec.elementAt(6).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(7).toString().trim());
			support.setDataVec("String", dataVec.elementAt(8).toString().trim());
			support.setDataVec("String", dataVec.elementAt(9).toString().trim());
			support.setDataVec("String", dataVec.elementAt(10).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(11).toString().trim());
			support.setDataVec("String", dataVec.elementAt(12).toString().trim());
			support.setDataVec("String", dataVec.elementAt(13).toString().trim());
			support.setDataVec("String", dataVec.elementAt(14).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(15).toString().trim());
			support.setDataVec("String", dataVec.elementAt(16).toString().trim());
			support.setDataVec("String", dataVec.elementAt(17).toString().trim());
			support.setDataVec("String", dataVec.elementAt(18).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(19).toString().trim());
			support.setDataVec("String", dataVec.elementAt(20).toString().trim());
			support.setDataVec("String", dataVec.elementAt(21).toString().trim());
			support.setDataVec("String", dataVec.elementAt(22).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(23).toString().trim());
			support.setDataVec("String", dataVec.elementAt(24).toString().trim());
			support.setDataVec("String", dataVec.elementAt(25).toString().trim());
			support.setDataVec("String", dataVec.elementAt(26).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(27).toString().trim());
			support.setDataVec("String", dataVec.elementAt(28).toString().trim());
			support.setDataVec("String", dataVec.elementAt(29).toString().trim());
			support.setDataVec("String", dataVec.elementAt(30).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(31).toString().trim());
			support.setDataVec("String", dataVec.elementAt(32).toString().trim());
			support.setDataVec("String", dataVec.elementAt(33).toString().trim());
			support.setDataVec("String", dataVec.elementAt(34).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(35).toString().trim());
			support.setDataVec("String", dataVec.elementAt(36).toString().trim());
			support.setDataVec("String", dataVec.elementAt(37).toString().trim());
			support.setDataVec("String", dataVec.elementAt(38).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(39).toString().trim());
			support.setDataVec("String", dataVec.elementAt(40).toString().trim());
			support.setDataVec("String", dataVec.elementAt(41).toString().trim());
			support.setDataVec("String", dataVec.elementAt(42).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(43).toString().trim());
			support.setDataVec("String", dataVec.elementAt(44).toString().trim());
			support.setDataVec("String", dataVec.elementAt(45).toString().trim());
			support.setDataVec("String", dataVec.elementAt(46).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(47).toString().trim());
			support.setDataVec("String", dataVec.elementAt(48).toString().trim());
			support.setDataVec("String", dataVec.elementAt(49).toString().trim());
			support.setDataVec("String", dataVec.elementAt(50).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(51).toString().trim());
			support.setDataVec("String", dataVec.elementAt(52).toString().trim());
			support.setDataVec("String", dataVec.elementAt(53).toString().trim());
			support.setDataVec("String", dataVec.elementAt(54).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(55).toString().trim());
			support.setDataVec("String", dataVec.elementAt(56).toString().trim());
			support.setDataVec("String", dataVec.elementAt(57).toString().trim());
			support.setDataVec("String", dataVec.elementAt(58).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(59).toString().trim());
			support.setDataVec("String", dataVec.elementAt(60).toString().trim());
			support.setDataVec("String", dataVec.elementAt(61).toString().trim());
			support.setDataVec("String", dataVec.elementAt(62).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(63).toString().trim());
			support.setDataVec("String", dataVec.elementAt(64).toString().trim());
			support.setDataVec("String", dataVec.elementAt(65).toString().trim());
			support.setDataVec("String", dataVec.elementAt(66).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(67).toString().trim());
			support.setDataVec("String", dataVec.elementAt(68).toString().trim());
			support.setDataVec("String", dataVec.elementAt(69).toString().trim());
			support.setDataVec("String", dataVec.elementAt(70).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(71).toString().trim());
			support.setDataVec("String", dataVec.elementAt(72).toString().trim());
			support.setDataVec("String", dataVec.elementAt(73).toString().trim());
			support.setDataVec("String", dataVec.elementAt(74).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(75).toString().trim());
			support.setDataVec("String", dataVec.elementAt(76).toString().trim());
			support.setDataVec("String", dataVec.elementAt(77).toString().trim());
			support.setDataVec("String", dataVec.elementAt(78).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(79).toString().trim());
			support.setDataVec("String", dataVec.elementAt(80).toString().trim());
			support.setDataVec("String", dataVec.elementAt(81).toString().trim());
			support.setDataVec("String", dataVec.elementAt(82).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(83).toString().trim());
			support.setDataVec("String", dataVec.elementAt(84).toString().trim());
			support.setDataVec("String", dataVec.elementAt(85).toString().trim());
			support.setDataVec("String", dataVec.elementAt(86).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(87).toString().trim());
			support.setDataVec("String", dataVec.elementAt(88).toString().trim());
			support.setDataVec("String", dataVec.elementAt(89).toString().trim());
			support.setDataVec("String", dataVec.elementAt(90).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(91).toString().trim());
			support.setDataVec("String", dataVec.elementAt(92).toString().trim());
			support.setDataVec("String", dataVec.elementAt(93).toString().trim());
			support.setDataVec("String", dataVec.elementAt(94).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(95).toString().trim());
			support.setDataVec("String", dataVec.elementAt(96).toString().trim());
			support.setDataVec("String", dataVec.elementAt(97).toString().trim());
			support.setDataVec("String", dataVec.elementAt(98).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(99).toString().trim());
			support.setDataVec("String", dataVec.elementAt(100).toString().trim());
			support.setDataVec("String", dataVec.elementAt(101).toString().trim());
			support.setDataVec("String", dataVec.elementAt(102).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(103).toString().trim());
			support.setDataVec("String", dataVec.elementAt(104).toString().trim());
			support.setDataVec("String", dataVec.elementAt(105).toString().trim());
			support.setDataVec("String", dataVec.elementAt(106).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(107).toString().trim());
			support.setDataVec("String", dataVec.elementAt(108).toString().trim());
			support.setDataVec("String", dataVec.elementAt(109).toString().trim());
			support.setDataVec("String", dataVec.elementAt(110).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(111).toString().trim());
			support.setDataVec("String", dataVec.elementAt(112).toString().trim());
			support.setDataVec("String", dataVec.elementAt(113).toString().trim());
			support.setDataVec("String", dataVec.elementAt(114).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(115).toString().trim());
			support.setDataVec("String", dataVec.elementAt(116).toString().trim());
			support.setDataVec("String", dataVec.elementAt(117).toString().trim());
			support.setDataVec("String", dataVec.elementAt(118).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(119).toString().trim());
			support.setDataVec("String", dataVec.elementAt(120).toString().trim());
			support.setDataVec("String", dataVec.elementAt(121).toString().trim());
			support.setDataVec("String", dataVec.elementAt(122).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(123).toString().trim());
			support.setDataVec("String", dataVec.elementAt(124).toString().trim());
			support.setDataVec("String", dataVec.elementAt(125).toString().trim());
			support.setDataVec("String", dataVec.elementAt(126).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(127).toString().trim());
			support.setDataVec("String", dataVec.elementAt(128).toString().trim());
			support.setDataVec("String", dataVec.elementAt(129).toString().trim());
			support.setDataVec("String", dataVec.elementAt(130).toString().trim());
						
			support.setDataVec("String", dataVec.elementAt(131).toString().trim());
			support.setDataVec("String", dataVec.elementAt(132).toString().trim());
			support.setDataVec("String", dataVec.elementAt(133).toString().trim());
			support.setDataVec("String", dataVec.elementAt(134).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(135).toString().trim());
			support.setDataVec("String", dataVec.elementAt(136).toString().trim());
			support.setDataVec("String", dataVec.elementAt(137).toString().trim());
			support.setDataVec("String", dataVec.elementAt(138).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(139).toString().trim());			
			support.setDataVec("String", dataVec.elementAt(140).toString().trim());
			support.setDataVec("String", dataVec.elementAt(141).toString().trim());
			support.setDataVec("String", dataVec.elementAt(142).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(143).toString().trim());
			support.setDataVec("String", dataVec.elementAt(144).toString().trim());
			support.setDataVec("String", dataVec.elementAt(145).toString().trim());
			support.setDataVec("int", dataVec.elementAt(146).toString().trim());
			support.setDataVec("int", dataVec.elementAt(147).toString().trim());
			return masterUtil.setData(ds, strQuery, support);

		}
		
		public String editTest(DataSource ds, Vector dataVec, int numTestId)
		{
			Support support=new Support();
			String strQuery = "Update test_mapping set totalmarks=?, ";
			strQuery += "ques1=?, ques1_type=?, ques1_check=?, ques1_visible=?, ";
			strQuery += "ques2=?, ques2_type=?, ques2_check=?, ques2_visible=?, ";
			strQuery += "ques3=?, ques3_type=?, ques3_check=?, ques3_visible=?, ";
			strQuery += "ques4=?, ques4_type=?, ques4_check=?, ques4_visible=?, ";
			strQuery += "ques5=?, ques5_type=?, ques5_check=?, ques5_visible=?, ";
			strQuery += "ques6=?, ques6_type=?, ques6_check=?, ques6_visible=?, ";
			strQuery += "ques7=?, ques7_type=?, ques7_check=?, ques7_visible=?, ";
			strQuery += "ques8=?, ques8_type=?, ques8_check=?, ques8_visible=?, ";
			strQuery += "ques9=?, ques9_type=?, ques9_check=?, ques9_visible=?, ";
			strQuery += "ques10=?, ques10_type=?, ques10_check=?, ques10_visible=?, ";
			strQuery += "ques11=?, ques11_type=?, ques11_check=?, ques11_visible=?, ";
			strQuery += "ques12=?, ques12_type=?, ques12_check=?, ques12_visible=?, ";
			strQuery += "ques13=?, ques13_type=?, ques13_check=?, ques13_visible=?, ";
			strQuery += "ques14=?, ques14_type=?, ques14_check=?, ques14_visible=?, ";
			strQuery += "ques15=?, ques15_type=?, ques15_check=?, ques15_visible=?, ";
			strQuery += "ques16=?, ques16_type=?, ques16_check=?, ques16_visible=?, ";
			strQuery += "ques17=?, ques17_type=?, ques17_check=?, ques17_visible=?, ";
			strQuery += "ques18=?, ques18_type=?, ques18_check=?, ques18_visible=?, ";
			strQuery += "ques19=?, ques19_type=?, ques19_check=?, ques19_visible=?, ";
			strQuery += "ques20=?, ques20_type=?, ques20_check=?, ques20_visible=?, ";
			strQuery += "ques21=?, ques21_type=?, ques21_check=?, ques21_visible=?, ";
			strQuery += "ques22=?, ques22_type=?, ques22_check=?, ques22_visible=?, ";
			strQuery += "ques23=?, ques23_type=?, ques23_check=?, ques23_visible=?, ";
			strQuery += "ques24=?, ques24_type=?, ques24_check=?, ques24_visible=?, ";
			strQuery += "ques25=?, ques25_type=?, ques25_check=?, ques25_visible=?, ";
			strQuery += "ques26=?, ques26_type=?, ques26_check=?, ques26_visible=?, ";
			strQuery += "ques27=?, ques27_type=?, ques27_check=?, ques27_visible=?, ";
			strQuery += "ques28=?, ques28_type=?, ques28_check=?, ques28_visible=?, ";
			strQuery += "ques29=?, ques29_type=?, ques29_check=?, ques29_visible=?, ";
			strQuery += "ques30=?, ques30_type=?, ques30_check=?, ques30_visible=?, ";
			strQuery += "ques31=?, ques31_type=?, ques31_check=?, ques31_visible=?, ";
			strQuery += "ques32=?, ques32_type=?, ques32_check=?, ques32_visible=?, ";
			strQuery += "ques33=?, ques33_type=?, ques33_check=?, ques33_visible=?, ";
			strQuery += "ques34=?, ques34_type=?, ques34_check=?, ques34_visible=?, ";
			strQuery += "ques35=?, ques35_type=?, ques35_check=?, ques35_visible=?, ";
			strQuery += "testtype=?, testremarks=?, testcategory=?, maxtime=?, tc_id=? where sno=? and companyid=?";
			
			////////////////////System.out.println("Test Query "+strQuery);
			
			support.setDataVec("int", dataVec.elementAt(2).toString().trim());
			support.setDataVec("String", dataVec.elementAt(3).toString().trim());
			support.setDataVec("String", dataVec.elementAt(4).toString().trim());
			support.setDataVec("String", dataVec.elementAt(5).toString().trim());
			support.setDataVec("String", dataVec.elementAt(6).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(7).toString().trim());
			support.setDataVec("String", dataVec.elementAt(8).toString().trim());
			support.setDataVec("String", dataVec.elementAt(9).toString().trim());
			support.setDataVec("String", dataVec.elementAt(10).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(11).toString().trim());
			support.setDataVec("String", dataVec.elementAt(12).toString().trim());
			support.setDataVec("String", dataVec.elementAt(13).toString().trim());
			support.setDataVec("String", dataVec.elementAt(14).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(15).toString().trim());
			support.setDataVec("String", dataVec.elementAt(16).toString().trim());
			support.setDataVec("String", dataVec.elementAt(17).toString().trim());
			support.setDataVec("String", dataVec.elementAt(18).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(19).toString().trim());
			support.setDataVec("String", dataVec.elementAt(20).toString().trim());
			support.setDataVec("String", dataVec.elementAt(21).toString().trim());
			support.setDataVec("String", dataVec.elementAt(22).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(23).toString().trim());
			support.setDataVec("String", dataVec.elementAt(24).toString().trim());
			support.setDataVec("String", dataVec.elementAt(25).toString().trim());
			support.setDataVec("String", dataVec.elementAt(26).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(27).toString().trim());
			support.setDataVec("String", dataVec.elementAt(28).toString().trim());
			support.setDataVec("String", dataVec.elementAt(29).toString().trim());
			support.setDataVec("String", dataVec.elementAt(30).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(31).toString().trim());
			support.setDataVec("String", dataVec.elementAt(32).toString().trim());
			support.setDataVec("String", dataVec.elementAt(33).toString().trim());
			support.setDataVec("String", dataVec.elementAt(34).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(35).toString().trim());
			support.setDataVec("String", dataVec.elementAt(36).toString().trim());
			support.setDataVec("String", dataVec.elementAt(37).toString().trim());
			support.setDataVec("String", dataVec.elementAt(38).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(39).toString().trim());
			support.setDataVec("String", dataVec.elementAt(40).toString().trim());
			support.setDataVec("String", dataVec.elementAt(41).toString().trim());
			support.setDataVec("String", dataVec.elementAt(42).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(43).toString().trim());
			support.setDataVec("String", dataVec.elementAt(44).toString().trim());
			support.setDataVec("String", dataVec.elementAt(45).toString().trim());
			support.setDataVec("String", dataVec.elementAt(46).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(47).toString().trim());
			support.setDataVec("String", dataVec.elementAt(48).toString().trim());
			support.setDataVec("String", dataVec.elementAt(49).toString().trim());
			support.setDataVec("String", dataVec.elementAt(50).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(51).toString().trim());
			support.setDataVec("String", dataVec.elementAt(52).toString().trim());
			support.setDataVec("String", dataVec.elementAt(53).toString().trim());
			support.setDataVec("String", dataVec.elementAt(54).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(55).toString().trim());
			support.setDataVec("String", dataVec.elementAt(56).toString().trim());
			support.setDataVec("String", dataVec.elementAt(57).toString().trim());
			support.setDataVec("String", dataVec.elementAt(58).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(59).toString().trim());
			support.setDataVec("String", dataVec.elementAt(60).toString().trim());
			support.setDataVec("String", dataVec.elementAt(61).toString().trim());
			support.setDataVec("String", dataVec.elementAt(62).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(63).toString().trim());
			support.setDataVec("String", dataVec.elementAt(64).toString().trim());
			support.setDataVec("String", dataVec.elementAt(65).toString().trim());
			support.setDataVec("String", dataVec.elementAt(66).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(67).toString().trim());
			support.setDataVec("String", dataVec.elementAt(68).toString().trim());
			support.setDataVec("String", dataVec.elementAt(69).toString().trim());
			support.setDataVec("String", dataVec.elementAt(70).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(71).toString().trim());
			support.setDataVec("String", dataVec.elementAt(72).toString().trim());
			support.setDataVec("String", dataVec.elementAt(73).toString().trim());
			support.setDataVec("String", dataVec.elementAt(74).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(75).toString().trim());
			support.setDataVec("String", dataVec.elementAt(76).toString().trim());
			support.setDataVec("String", dataVec.elementAt(77).toString().trim());
			support.setDataVec("String", dataVec.elementAt(78).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(79).toString().trim());
			support.setDataVec("String", dataVec.elementAt(80).toString().trim());
			support.setDataVec("String", dataVec.elementAt(81).toString().trim());
			support.setDataVec("String", dataVec.elementAt(82).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(83).toString().trim());
			support.setDataVec("String", dataVec.elementAt(84).toString().trim());
			support.setDataVec("String", dataVec.elementAt(85).toString().trim());
			support.setDataVec("String", dataVec.elementAt(86).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(87).toString().trim());
			support.setDataVec("String", dataVec.elementAt(88).toString().trim());
			support.setDataVec("String", dataVec.elementAt(89).toString().trim());
			support.setDataVec("String", dataVec.elementAt(90).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(91).toString().trim());
			support.setDataVec("String", dataVec.elementAt(92).toString().trim());
			support.setDataVec("String", dataVec.elementAt(93).toString().trim());
			support.setDataVec("String", dataVec.elementAt(94).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(95).toString().trim());
			support.setDataVec("String", dataVec.elementAt(96).toString().trim());
			support.setDataVec("String", dataVec.elementAt(97).toString().trim());
			support.setDataVec("String", dataVec.elementAt(98).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(99).toString().trim());
			support.setDataVec("String", dataVec.elementAt(100).toString().trim());
			support.setDataVec("String", dataVec.elementAt(101).toString().trim());
			support.setDataVec("String", dataVec.elementAt(102).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(103).toString().trim());
			support.setDataVec("String", dataVec.elementAt(104).toString().trim());
			support.setDataVec("String", dataVec.elementAt(105).toString().trim());
			support.setDataVec("String", dataVec.elementAt(106).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(107).toString().trim());
			support.setDataVec("String", dataVec.elementAt(108).toString().trim());
			support.setDataVec("String", dataVec.elementAt(109).toString().trim());
			support.setDataVec("String", dataVec.elementAt(110).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(111).toString().trim());
			support.setDataVec("String", dataVec.elementAt(112).toString().trim());
			support.setDataVec("String", dataVec.elementAt(113).toString().trim());
			support.setDataVec("String", dataVec.elementAt(114).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(115).toString().trim());
			support.setDataVec("String", dataVec.elementAt(116).toString().trim());
			support.setDataVec("String", dataVec.elementAt(117).toString().trim());
			support.setDataVec("String", dataVec.elementAt(118).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(119).toString().trim());
			support.setDataVec("String", dataVec.elementAt(120).toString().trim());
			support.setDataVec("String", dataVec.elementAt(121).toString().trim());
			support.setDataVec("String", dataVec.elementAt(122).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(123).toString().trim());
			support.setDataVec("String", dataVec.elementAt(124).toString().trim());
			support.setDataVec("String", dataVec.elementAt(125).toString().trim());
			support.setDataVec("String", dataVec.elementAt(126).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(127).toString().trim());
			support.setDataVec("String", dataVec.elementAt(128).toString().trim());
			support.setDataVec("String", dataVec.elementAt(129).toString().trim());
			support.setDataVec("String", dataVec.elementAt(130).toString().trim());
						
			support.setDataVec("String", dataVec.elementAt(131).toString().trim());
			support.setDataVec("String", dataVec.elementAt(132).toString().trim());
			support.setDataVec("String", dataVec.elementAt(133).toString().trim());
			support.setDataVec("String", dataVec.elementAt(134).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(135).toString().trim());
			support.setDataVec("String", dataVec.elementAt(136).toString().trim());
			support.setDataVec("String", dataVec.elementAt(137).toString().trim());
			support.setDataVec("String", dataVec.elementAt(138).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(139).toString().trim());			
			support.setDataVec("String", dataVec.elementAt(140).toString().trim());
			support.setDataVec("String", dataVec.elementAt(141).toString().trim());
			support.setDataVec("String", dataVec.elementAt(142).toString().trim());
			
			support.setDataVec("String", dataVec.elementAt(143).toString().trim());
			support.setDataVec("String", dataVec.elementAt(144).toString().trim());
			support.setDataVec("String", dataVec.elementAt(145).toString().trim());
			
			support.setDataVec("int", dataVec.elementAt(146).toString().trim());
			support.setDataVec("int", dataVec.elementAt(147).toString().trim());
			
			support.setDataVec("int", String.valueOf(numTestId));
			support.setDataVec("int", dataVec.elementAt(0).toString().trim());
			return masterUtil.setData(ds, strQuery, support);

		}
		
		public String getLogoPath(DataSource ds, int numCompanyId)
		{
			Support support=new Support();
			String strQuery="Select logopath From companymaster where company_id="+numCompanyId;
						
			support.setFieldVec("string", "logopath");//1
			
			
			return masterUtil.getValue(ds, strQuery, support);
			
		}
		
		public Vector getTestListVec(DataSource ds, Vector dataVec) {
			Support support=new Support();
			String strQuery="";
			
			String testType = dataVec.elementAt(0).toString().trim();
			String companyid = dataVec.elementAt(1).toString().trim();
			
			
			Vector resultVec = new Vector();

			strQuery = "select * From test_mapping where testcategory ='"+testType+"' and companyid='"+companyid+"'";

			support.setFieldVec("int", "sno");	//0
			support.setFieldVec("int", "companyid");	//1
			support.setFieldVec("string", "testname");		//2
			support.setFieldVec("string", "testtype");			//3
			support.setFieldVec("string", "testremarks");	//4
			support.setFieldVec("string", "testcategory");		//5
			
			

			resultVec = masterUtil.getList(ds, strQuery, support);
			return resultVec;

		}
		
		
		
		public Vector getTestComListVec(DataSource ds, Vector dataVec)
		{
			Support support=new Support();
			String strQuery="";
			
			String testType = dataVec.elementAt(0).toString().trim();
			
			
			Vector<Vector>resultVec = new Vector<Vector>();
			
			Vector<String>tempVec = new Vector<String>();
		  	 tempVec.add("0");
		  	 tempVec.add("Select");
		  	 resultVec.add(tempVec);
		  	 
		  	 
		  	strQuery = "select t.companyid, c.company_name From test_mapping t, companymaster c where t.testcategory ='"+testType+"' and t.companyid=c.company_id GROUP by t.companyid ORDER BY c.company_name"; 

			
			support.setFieldVec("int", "t.companyid");	//0
			support.setFieldVec("string", "c.company_name");		//1
		  	 	
		  	 
				 Vector <Vector>tempResultVec  = new Vector<Vector>();
				 tempResultVec=(Vector)masterUtil.getList(ds, strQuery, support);
				 for(int i=0; i<tempResultVec.size();i++)
				 {
					 resultVec.add(tempResultVec.elementAt(i));
				 }
			
			 
			 return resultVec;
		}
		
		public Vector getTestComListVec(DataSource ds, Vector dataVec, int numSubCat)
		{
			Support support=new Support();
			String strQuery="";
			
			String testType = dataVec.elementAt(0).toString().trim();
			
			
			Vector<Vector>resultVec = new Vector<Vector>();
			
			Vector<String>tempVec = new Vector<String>();
		  	 tempVec.add("0");
		  	 tempVec.add("Select");
		  	 resultVec.add(tempVec);
		  	 
		  	if(testType.equalsIgnoreCase("TechnicalSkills"))
		  	{
		  		strQuery = "select t.companyid, c.company_name From test_mapping t, companymaster c where t.testcategory ='"+testType+"' and tc_id = "+numSubCat+" and t.companyid=c.company_id GROUP by t.companyid ORDER BY c.company_name";
		  	}
		  	else
		  	{
		  		strQuery = "select t.companyid, c.company_name From test_mapping t, companymaster c where t.testcategory ='"+testType+"' and t.companyid=c.company_id GROUP by t.companyid ORDER BY c.company_name";
		  	}
		  	 

			
			support.setFieldVec("int", "t.companyid");	//0
			support.setFieldVec("string", "c.company_name");		//1
		  	 	
		  	 
				 Vector <Vector>tempResultVec  = new Vector<Vector>();
				 tempResultVec=(Vector)masterUtil.getList(ds, strQuery, support);
				 for(int i=0; i<tempResultVec.size();i++)
				 {
					 resultVec.add(tempResultVec.elementAt(i));
				 }
			
			 
			 return resultVec;
		}
		public String getTestremarks(DataSource ds, String testId)
		{
			Support support=new Support();
			String strQuery="select testremarks From test_mapping where sno='"+testId+"'";
						
			support.setFieldVec("string", "testremarks");//0
			
			
			return masterUtil.getValue(ds, strQuery, support);
			
		}
		
		public Vector getTestType(DataSource ds, String testId)
		{
			Support support=new Support();
			String strQuery="select testtype, testname, totalmarks From test_mapping where sno='"+testId+"'";
						
			support.setFieldVec("string", "testtype");//0
			support.setFieldVec("string", "testname");//1
			support.setFieldVec("int", "totalmarks");//2
			
			
			
			return masterUtil.getDetail(ds, strQuery, support);
			
		}
		
		public String setTestAns(DataSource ds, Vector dataVec)
		{
			String strResult = "";
			for(int i=0; i<dataVec.size(); i++)
			{
				Vector tempVec = (Vector)dataVec.elementAt(i); 
				String strQuery = "Insert into test_ans(companyid, testno, testque, ans, marks) values(?, ?, ?, ?, ?)";
				Support support = new Support();
				support.setDataVec("int", tempVec.elementAt(0).toString().trim());
				support.setDataVec("int", tempVec.elementAt(1).toString().trim());
				support.setDataVec("String", tempVec.elementAt(2).toString().trim());
				support.setDataVec("String", tempVec.elementAt(3).toString().trim());
				support.setDataVec("String", tempVec.elementAt(4).toString().trim());
				String strStatus= masterUtil.setData(ds, strQuery, support);
				strResult = (strResult.equalsIgnoreCase("")) ? strStatus : strResult+", "+strStatus;
			}
			
			return strResult;
		}
		public String editTestAns(DataSource ds, Vector dataVec)
		{
			String strResult = "";
			////////////////System.out.println("dataVec.size()dataVec.size()"+dataVec.size());
			for(int i=0; i<dataVec.size(); i++)
			{
				Support support = new Support();
				String strQuery="";
				Vector tempVec = (Vector)dataVec.elementAt(i);
				int numId = Integer.parseInt(tempVec.elementAt(5).toString().trim());
				if(numId<=0)
				{
					strQuery = "Insert into test_ans(companyid, testno, testque, ans, marks) values(?, ?, ?, ?, ?)";
					
					support.setDataVec("int", tempVec.elementAt(0).toString().trim());
					support.setDataVec("int", tempVec.elementAt(1).toString().trim());
					support.setDataVec("String", tempVec.elementAt(2).toString().trim());
					support.setDataVec("String", tempVec.elementAt(3).toString().trim());
					support.setDataVec("String", tempVec.elementAt(4).toString().trim());
				}
				else if(numId>0 && tempVec.elementAt(3).toString().trim().length()<=0)
				{
					strQuery = "Delete from test_ans where id=?";
					support.setDataVec("int", tempVec.elementAt(5).toString().trim());
				}
				else
				{
					strQuery = "update test_ans set ans=?, marks=? where id=?";					
					support.setDataVec("String", tempVec.elementAt(3).toString().trim());
					support.setDataVec("String", tempVec.elementAt(4).toString().trim());
					support.setDataVec("int", tempVec.elementAt(5).toString().trim());
				}
					
				String strStatus= masterUtil.setData(ds, strQuery, support);
				strResult = (strResult.equalsIgnoreCase("")) ? strStatus : strResult+", "+strStatus;
			}
			
			return strResult;
		}
		public Vector getTestAns(DataSource ds,int qno, int numTestId)
		{
			Support support=new Support();
			String strQuery = "Select * From test_ans where testno="+numTestId+" and testque="+qno;
			
			
			support.setFieldVec("String", "id");
			support.setFieldVec("String", "companyid");
			support.setFieldVec("String", "testno");
			support.setFieldVec("String", "testque");
			support.setFieldVec("String", "ans");
			support.setFieldVec("String", "marks");
						
			return (Vector)masterUtil.getList(ds, strQuery, support);
		}
		
		
		public String insertFeedback(DataSource ds, Vector dataVec) {
			
			Support support=new Support();
			String 	strQuery = "Insert into feedback (name, userid, email, feedbtype,"
					
					+ "subject, text, fbdate, fbtime) values(?, ?, ?, ?, ?, ?, ?, ?)";

			support.setDataVec("string", dataVec.elementAt(0).toString().trim());
			support.setDataVec("string", dataVec.elementAt(1).toString().trim());
			support.setDataVec("string", dataVec.elementAt(2).toString().trim());
			support.setDataVec("string", dataVec.elementAt(3).toString().trim());
			support.setDataVec("string", dataVec.elementAt(4).toString().trim());
			support.setDataVec("string", dataVec.elementAt(5).toString().trim());
			support.setDataVec("string", dataVec.elementAt(6).toString().trim());
			support.setDataVec("string", dataVec.elementAt(7).toString().trim());
			
			return masterUtil.setData(ds, strQuery, support);
		}
		
		
		public Vector getdepCategory(DataSource ds,String dep_type)
		{
			Support support=new Support();
			String strQuery="";
			Vector<Vector>resultVec = new Vector<Vector>();
			Vector<String>tempVec = new Vector<String>();
			Vector<Vector> tempResultVec= new Vector<Vector>();
			tempVec.add("0");
			tempVec.add("Select");
			resultVec.add(tempVec);
			 
			strQuery = "select * from department where dep_enable=1 and dep_type='"+dep_type+"' order by dep_name";
			
			
			support.setFieldVec("int", "dep_id");
			support.setFieldVec("String", "dep_name");
			////System.out.println("strQuerystrQuerystrQuerystrQuery;;;;;;;;;;;"+strQuery);
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			for(int i=0;i<tempResultVec.size();i++)
			{
				resultVec.add((Vector)tempResultVec.elementAt(i));
			}
			Vector<String>tempVec1 = new Vector<String>();
			tempVec1.add("-1");
			tempVec1.add("Others");
			resultVec.add(tempVec1);
			
			 
			return resultVec;
		}
		
		public Vector getdesigationList(DataSource ds,int dep_id)
		{
			Support support=new Support();
			String strQuery="";
			Vector<Vector>resultVec = new Vector<Vector>();
			Vector<String>tempVec = new Vector<String>();
			Vector<Vector> tempResultVec= new Vector<Vector>();
			tempVec.add("0");
			tempVec.add("Select");
			resultVec.add(tempVec);
			 
			strQuery = "select * from designation where enable=1 order by des_name";
			support.setFieldVec("int", "des_id");
			support.setFieldVec("String", "des_name");
			
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			
			for(int i=0;i<tempResultVec.size();i++)
			{
				resultVec.add((Vector)tempResultVec.elementAt(i));
			}
			
			Vector<String>tempVec1 = new Vector<String>();
			tempVec1.add("-1");
			tempVec1.add("Others");
			resultVec.add(tempVec1);
			
			System.out.println("resultVec designation resultVec;;;;;;;;;;;"+resultVec);
			return resultVec;
		}
		
		public Vector getdesigationListS(DataSource ds)
		{
			Support support=new Support();
			String strQuery="";
			Vector<Vector>resultVec = new Vector<Vector>();
			Vector<String>tempVec = new Vector<String>();
			Vector<Vector> tempResultVec= new Vector<Vector>();
			tempVec.add("0");
			tempVec.add("Select");
			resultVec.add(tempVec);
			 
			strQuery = "select * from designation where enable=1 order by des_name";
			support.setFieldVec("int", "des_id");
			support.setFieldVec("String", "des_name");
			
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			
			for(int i=0;i<tempResultVec.size();i++)
			{
				resultVec.add((Vector)tempResultVec.elementAt(i));
			}
			
			Vector<String>tempVec1 = new Vector<String>();
			tempVec1.add("-1");
			tempVec1.add("Others");
			resultVec.add(tempVec1);
			
			System.out.println("resultVec designation resultVec;;;;;;;;;;;"+resultVec);
			return resultVec;
		}

		/**
		* This function is use for getting Resident information in indv.
		*/
		public Vector getResInfo(DataSource ds, int userid) {
		Support support=new Support();
		String strQuery="";
		
		strQuery = "select * from resident_details where user_id = " + userid;
		System.out.println("strQuery=>"+strQuery);
		System.out.println("Here In Indvaster=>");
		
		support.setFieldVec("string", "spouse_name");//0
		support.setFieldVec("string", "spouse_dob");//1
		support.setFieldVec("string", "child1_name");//2
		support.setFieldVec("string", "child1_dob");//3
		support.setFieldVec("string", "child2_name");//4
		support.setFieldVec("string", "child2_dob");//5
		support.setFieldVec("string", "child3_name");//6
		support.setFieldVec("string", "child3_dob");//7
		support.setFieldVec("string", "building");//8
		support.setFieldVec("string", "tower");//9
		support.setFieldVec("string", "flat");//10
		support.setFieldVec("string", "vehicle");//11
		support.setFieldVec("string", "vehicle_name");//12
		support.setFieldVec("string", "vehicle_make");//13
		support.setFieldVec("string", "vehicle_mfd");//14
			
		return masterUtil.getDetail(ds, strQuery, support);
		}
		
		
		
//		insert department detail in data base
		public String insertDepartment(DataSource ds,String dep_name,String dep_type) {
			Support support=new Support();
			String strQuery="";
			String result ="";
			strQuery = "Insert into department (dep_name,dep_type) values(?,?)";
			support.setDataVec("String", dep_name);
			support.setDataVec("String", dep_type);
			result = masterUtil.setData(ds, strQuery, support);
			if(result.equalsIgnoreCase("success"))
			{
				strQuery = "select max(dep_id) dep_id from department where dep_name='"+dep_name+"' and dep_type='"+dep_type+"'";
				result = String.valueOf(masterUtil.getId(ds, strQuery, "dep_id"));
			}
			////System.out.println("resultVec resultVec;;;;;;;;;;;"+result);
			return 	result;
		}
		//end inserting country	
		
//		insert department detail in data base
		public String insertDesignation(DataSource ds,String des_name,String des_type,String dep_id) {
			Support support=new Support();
			String strQuery="";
			String result ="";
			strQuery = "Insert into designation (des_name,dep_id) values(?,?)";
			support.setDataVec("String", des_name);
			support.setDataVec("String", dep_id);
			result = masterUtil.setData(ds, strQuery, support);
			if(result.equalsIgnoreCase("success"))
			{
				strQuery = "select max(des_id) des_id from designation where des_name='"+des_name+"' and dep_id='"+dep_id+"'";
				result = String.valueOf(masterUtil.getId(ds, strQuery, "des_id"));
			}
			//System.out.println("resultVec resultVec;;;;;;;;;;;"+result);
			return 	result;
			
		}
		//end inserting country	
		/**
		 * 
		 */
		public ArrayList getClassified(DataSource ds, int numMin, String complaint_id)
		{
			//int numMax = numMin+2;
			ArrayList arrResult= null;
			
			Support support = new Support();
			String strQuery = "Select * from complaints where q_type='blog'";
			if(complaint_id.trim().length()>0)
			{
				strQuery += " and complaint_id not in("+complaint_id+")";
			}
			strQuery += " limit "+numMin+", 2";
			System.out.println("strQuery "+strQuery);
			support.setFieldVec("int", "complaint_id");           //0
			support.setFieldVec("String", "complaint_title");	//1
			
			
			arrResult = masterUtil.getArrayList(ds, strQuery, support);
			
			return arrResult;
		}
		/**
		 * 
		 * @param ds
		 * @return
		 */
		public ArrayList getCurrentFacility(DataSource ds)
		{
			ArrayList arrResult= null;
			
			Support support = new Support();
			String strQuery = "Select facility_id, facility_name from facility order by facility_id desc limit 1,1";
			
			support.setFieldVec("int", "facility_id");           //0
			support.setFieldVec("String", "facility_name");	//1
			
			
			arrResult = masterUtil.getArrayList(ds, strQuery, support);
			
			return arrResult;
		}
		
//Added by Renuka 04.10.2009
		public ArrayList getDataOfCurrentFacility(DataSource ds, String strUserId)
		{
			ArrayList arrResult= null;
			
			Support support = new Support();
			String strQuery = "select country, state, city, companyid, college_id from loginmaster where userid = "+strUserId;
			System.out.println("strQuery::"+strQuery);
			support.setFieldVec("string", "country");
			support.setFieldVec("string", "state");
			support.setFieldVec("string", "city");
			support.setFieldVec("int", "companyid");
			support.setFieldVec("string", "college_id");
			
			arrResult = masterUtil.getArrayList(ds, strQuery, support);
			System.out.println("arrResult::::::=>"+arrResult);
			return arrResult;
		}

		
}
