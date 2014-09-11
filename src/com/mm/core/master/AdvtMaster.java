/** * 
 */
package com.mm.core.master;
import java.util.*;

import javax.sql.DataSource;

import com.mm.core.resource.*;
import com.mm.struts.advt.form.ComplaintForm;
import com.mm.struts.advt.form.ManipulatesubcatForm;
/**
 * @author dalchand
 * Date 9/5/2007
 * 
 */
/**
 * This class is use for performing all function of advt.
 * it creats a MasterUtility class object for performing basic operation
 * upon the Data Base.
 */


public class AdvtMaster { 
	
	
	MasterUtility masterUtil = new MasterUtility();
	
	/**
	 * Complaint related function written here.
	 */
	/**
	 * 
	 * @param compId defines perticauler complaint Id .
	 * @return Detail vector of complaint.
	 */
	/**
	 * This function is use for getting all complaint detail 
	 * of a particuler complaint.
	 */
	
	
	/**
	 * This function is use for getting all List of test Company  particuler
	 * Company.
	 */
	public Vector getExistingTestList(DataSource ds, Vector paramVec) {
		Support support=new Support();
		int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
		int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
		String fieldVal = paramVec.elementAt(2).toString().trim();
		String companyid=paramVec.elementAt(3).toString();
		String testtype1=paramVec.elementAt(4).toString();
		String strQuery = "select sno,testname,testtype,testcategory from test_mapping where companyid= '"+companyid+"' and testtype= '"+testtype1+"'order by "+fieldVal+" limit "+min+", "+max;
																		
		/*
		 * set all the fileds type and name for blogs table by using Support
		 * class object.
		 */
		support.setFieldVec("int", "sno");	// 0
		support.setFieldVec("string", "testname");	// 1
		support.setFieldVec("string", "testtype");		// 2
		support.setFieldVec("String", "testcategory");				// 3
		System.out.println("strQuery...in test list...."+strQuery);
		return masterUtil.getList(ds, strQuery, support);
		
	}
	
	
//	Added by Renuka 
	public Vector getExistingMember(DataSource ds,int advt_id , int advtL_id, int adminFlag ){
		Vector resultVec = new Vector();

		Support support=new Support();
		String strQuery="";
		strQuery = "select lm.userid userid, lm.first_name fname, lm.logindeletion validation, lm.status type from loginmaster lm INNER join resident_details on resident_details.user_id = lm.userid where lm.companyid =26";
		System.out.println("strQuery:"+strQuery);
		support.setFieldVec("int", "userid");
		support.setFieldVec("string", "fname");
		support.setFieldVec("string", "validation");
		support.setFieldVec("string", "type");
		
		////////////System.out.println("resultVec in advt master.."+strQuery);
		resultVec = masterUtil.getList(ds, strQuery, support);
		System.out.println("resultVec::::::"+resultVec);			
		return resultVec;
}
	/**
	 * Created by Ajay Kumar Jha
	 * Date on 19 Sept 2009
	 * @param ds
	 * @param advt_id
	 * @return
	 */
	public Vector getServices(DataSource ds, int advt_id)
	{
		Vector resultVec = new Vector();
		Support support = new Support();
		String strQuery = "Select * from service where active='y' and facilityid="+advt_id;
		support.setFieldVec("int", "serviceid");
		support.setFieldVec("string", "servicename");
		support.setFieldVec("string", "servicedesc");
		support.setFieldVec("int", "monthlyfee");
		support.setFieldVec("string", "subscription");
		//support.setFieldVec("string", "servicename");
		resultVec = masterUtil.getList(ds, strQuery, support);
		return resultVec;
	}
	
	/**
	 * Added By Ajay Kumar Jha
	 * Date - 20 Sept 2009
	 * @param ds
	 * @param numFacilityId
	 * @return
	 */
	public ArrayList getResidentList(DataSource ds, int numFacilityId)
	{
		ArrayList resultList = new ArrayList();
		try
		{
			Support support = new Support();
			String strQuery = "Select l.userid userid, l.first_name fname, l.last_name lname, l.status usrtype, l.creationDate creationdt,";
			strQuery += " l.B_IsValidated active, l.B_IsSuspended suspended, l.Address address, l.mobile mobile, l.phone phone";
			strQuery += " from loginmaster l where "
			//commented on 08.10.2009   strQuery += " from loginmaster l, resident_details r where "
				//+"l.userid = r.user_id and "+   commented on 08.10.2009 
				+"l.logindeletion=0 and l.companyid=26 and l.login_type= 'Student'";
			//strQuery += " and r.facility_id="+numFacilityId;
			strQuery += " and l.college_id="+numFacilityId;
			support.setFieldVec("int", "userid");
			support.setFieldVec("string", "fname");
			support.setFieldVec("string", "lname");
			support.setFieldVec("string", "usrtype");
			support.setFieldVec("string", "creationdt");
			support.setFieldVec("int", "active");
			//support.setFieldVec("int", "deleted");
			support.setFieldVec("int", "suspended");
			support.setFieldVec("string", "address");
			support.setFieldVec("string", "mobile");
			support.setFieldVec("string", "phone");
			//support.setFieldVec("string", "servicename");
			resultList = masterUtil.getArrayList(ds, strQuery, support);
			
			System.out.println(":strQuery:line14141441:::"+strQuery);
			
		}
		catch(Exception e){}
		return resultList;
	}
				
	/**
	 * Added by Ajay Kumar Jha
	 * Date 21 Sept 2009
	 * @param ds
	 * @param strUIds
	 * @param numStatus
	 * @return
	 */
	public String setValidStatus(DataSource ds, String strUIds, int numStatus)
	{
		String strResult = "failure";
		try
		{
			//B_IsSuspended , B_IsValidated =? , logindeletion =? ,								
			String strQuery = "update loginmaster set B_IsValidated="+numStatus+" where userid in ("+strUIds.trim()+")";
			
			MasterUtility mu = new MasterUtility();
			strResult = mu.setData(ds, strQuery);
			
		}
		catch(Exception e)
		{}
		
		return strResult;
	}
	
	/**
	 * Added By Ajay Kumar Jha
	 * 
	 * @param ds
	 * @param strUIds
	 * @param numStatus
	 * @return
	 */
	public String setSuspendStatus(DataSource ds, String strUIds, int numStatus)
	{
		String strResult = "failure";
		try
		{
			//B_IsSuspended , B_IsValidated =? , logindeletion =? ,								
			String strQuery = "update loginmaster set B_IsSuspended="+numStatus+" where userid in ("+strUIds.trim()+")";
			
			MasterUtility mu = new MasterUtility();
			strResult = mu.setData(ds, strQuery);
			
		}
		catch(Exception e)
		{}
		
		return strResult;
	}
	
	/**
	 * Added by Ajay Kumar Jha
	 * @param ds
	 * @param strUIds
	 * @param numStatus
	 * @return
	 */
	public String setDeleteStatus(DataSource ds, String strUIds, int numStatus)
	{
		String strResult = "failure";
		try
		{
			//B_IsSuspended , B_IsValidated =? , logindeletion =? ,								
			String strQuery = "update loginmaster set logindeletion="+numStatus+" where userid in ("+strUIds.trim()+")";
			
			MasterUtility mu = new MasterUtility();
			strResult = mu.setData(ds, strQuery);
			
		}
		catch(Exception e)
		{}
		
		return strResult;
	}
	
	
		public Vector getComplaintDetails(DataSource ds,Vector dataVec){
			
			Vector resultVec = new Vector();
			Support support=new Support();
			   String strQuery="";
			int advtL_id = Integer.parseInt(dataVec.elementAt(0).toString().trim());
			int advt_id = Integer.parseInt(dataVec.elementAt(1).toString().trim());
			int complaint_id = Integer.parseInt(dataVec.elementAt(2).toString().trim());
			int adminFlag = Integer.parseInt(dataVec.elementAt(3).toString().trim());
			if(adminFlag == 1)
			{
				strQuery = "select * from complaints where advt_id= "+advt_id+" and complaint_id= "+complaint_id;
			}
			else
			{
				strQuery = "select * from complaints where advt_id= "+advt_id+" and advtL_id= "+advtL_id+" and complaint_id= "+complaint_id;
			}
					
			/**
			 * set all the fileds type and name for complaints table by using
			 * Support class object.
			 */
			support.setFieldVec("int", "complaint_id");           //0
			support.setFieldVec("string", "complaint_title");     //1
			support.setFieldVec("string", "complaint_text");      //2
			support.setFieldVec("string", "relevent_info");       //3
			support.setFieldVec("string", "category");            //4			
			support.setFieldVec("string", "creation_date");       //5
			support.setFieldVec("string", "publish_date");        //6
			support.setFieldVec("string", "creation_time");       //7
			support.setFieldVec("string", "publish_time");        //8
			support.setFieldVec("string", "lastmodify_date");     //9
			support.setFieldVec("string", "lastmodify_time");     //10
			support.setFieldVec("int", "login_id");               //11
			support.setFieldVec("int", "companyid");              //12
			support.setFieldVec("string", "timepart");            //13
			support.setFieldVec("int", "viewcount");              //14
			support.setFieldVec("int", "entp_id");                //15
			support.setFieldVec("int", "cu_id");                  //16
			support.setFieldVec("String", "fcom_id");             //17
			support.setFieldVec("int", "advt_id");                //18
			support.setFieldVec("int", "advtL_id");               //19
			support.setFieldVec("int", "dealer_id");              //20
			support.setFieldVec("int", "tag_id");                 //21
			support.setFieldVec("String", "resolve_date");        //22
			support.setFieldVec("String", "closed_date");         //23
			support.setFieldVec("int", "brandFlag");              //24
			support.setFieldVec("int", "publish_flag");           //25
			support.setFieldVec("string", "publish_on");          //26
			support.setFieldVec("string", "other");               //27
			
			resultVec = masterUtil.getDetail(ds, strQuery, support);
			return resultVec;
		}
		
		
		/**
		 * This function is use for getting all complaint detail 
		 * of a particuler complaint.
		 */
			public Vector getQueryDetails(DataSource ds,Vector dataVec){
				
				Vector resultVec = new Vector();
				Support support=new Support();
				   String strQuery="";
				int advtL_id = Integer.parseInt(dataVec.elementAt(0).toString().trim());
				int advt_id = Integer.parseInt(dataVec.elementAt(1).toString().trim());
				int complaint_id = Integer.parseInt(dataVec.elementAt(2).toString().trim());
				int adminFlag = Integer.parseInt(dataVec.elementAt(3).toString().trim());
				if(adminFlag == 1)
				{
					strQuery = "select * from complaints where companyid= "+advt_id+" and complaint_id= "+complaint_id;
				}
				else
				{
					strQuery = "select * from complaints where companyid= "+advt_id+" and login_id= "+advtL_id+" and complaint_id= "+complaint_id;
				}
						
				/**
				 * set all the fileds type and name for complaints table by using
				 * Support class object.
				 */
				support.setFieldVec("int", "complaint_id");           //0
				support.setFieldVec("string", "complaint_title");     //1
				support.setFieldVec("string", "complaint_text");      //2
				support.setFieldVec("string", "relevent_info");       //3
				support.setFieldVec("string", "category");            //4			
				support.setFieldVec("string", "creation_date");       //5
				support.setFieldVec("string", "publish_date");        //6
				support.setFieldVec("string", "creation_time");       //7
				support.setFieldVec("string", "publish_time");        //8
				support.setFieldVec("string", "lastmodify_date");     //9
				support.setFieldVec("string", "lastmodify_time");     //10
				support.setFieldVec("int", "login_id");               //11
				support.setFieldVec("int", "companyid");              //12
				support.setFieldVec("string", "timepart");            //13
				support.setFieldVec("int", "viewcount");              //14
				support.setFieldVec("int", "entp_id");                //15
				support.setFieldVec("int", "cu_id");                  //16
				support.setFieldVec("String", "fcom_id");             //17
				support.setFieldVec("int", "advt_id");                //18
				support.setFieldVec("int", "advtL_id");               //19
				support.setFieldVec("int", "dealer_id");              //20
				support.setFieldVec("int", "tag_id");                 //21
				support.setFieldVec("String", "resolve_date");        //22
				support.setFieldVec("String", "closed_date");         //23
				support.setFieldVec("int", "brandFlag");              //24
				support.setFieldVec("int", "publish_flag");           //25
				support.setFieldVec("string", "publish_on");          //26
				support.setFieldVec("string", "other");               //27
				
				resultVec = masterUtil.getDetail(ds, strQuery, support);
				return resultVec;
			}
		
		/**
		 * 
		 * @param paramVec takes parameter like Minimu and maximum value
		 *  for fatching the list of complaint.
		 * @return
		 */	
		/**
		 * This function is use for getting list of  complaint's agaist a particuler  brand manager.
		 * It returns a list within limit which is given by min and max variable.
		 */		
			public Vector getComplaintList(DataSource ds,Vector paramVec){
				Vector resultVec = new Vector();
				////////System.out.println("paramVec......."+paramVec);
				int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
				int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
				String fieldVal = paramVec.elementAt(2).toString().trim();
				int advtL_id = Integer.parseInt(paramVec.elementAt(3).toString().trim());
				int advt_id = Integer.parseInt(paramVec.elementAt(4).toString().trim());
				int adminFlag = Integer.parseInt(paramVec.elementAt(5).toString().trim());
				String pageId = paramVec.elementAt(6).toString().trim();
				String lowerSubTag = paramVec.elementAt(8).toString().trim();
				Support support=new Support();
				   String strQuery="";
			   String mreq="Mrequest";
	strQuery = "SELECT * from complaints where advtL_id="+advtL_id+" and q_type= 'Personal'";
							   
			  /*
				   if(pageId.equalsIgnoreCase("1") && lowerSubTag.equalsIgnoreCase ("1"))
					{
						String ComList = paramVec.elementAt(7).toString().trim();
						if(ComList.length()>0)
						{
							strQuery = "SELECT * from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and brandFlag = 1  and (q_type= 'Technical' or q_type= 'Personal' or q_type= 'Trequest' )  and complaint_id  not in ("+ComList+") order by "+fieldVal+" limit "+min+", "+max;
						}
						else
						{
							strQuery = "SELECT * from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and brandFlag = 1  and (q_type= 'Technical' or q_type= 'Personal'or q_type= 'Trequest' )  order by "+fieldVal+" limit "+min+", "+max;
						}
					}
					else if(pageId.equalsIgnoreCase("1") && lowerSubTag.equalsIgnoreCase ("2"))
					{
						strQuery = "SELECT distinct(complaint_id),fcom_id,complaint_title,creation_date,creation_time from complaints b, communication r  where b.advt_id="+advt_id+" and b.advtL_id="+advtL_id+" and b.brandFlag = 1 and  (b.q_type= 'Technical' or b.q_type= 'Personal'or b.q_type= 'Trequest' )   and b.complaint_id = r.complaintid and r.userid=b.advtL_id order by "+fieldVal+" limit "+min+", "+max;
					}
					else if(pageId.equalsIgnoreCase("3"))
					{
						strQuery = "Select * from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+" and q_type='"+mreq+"'  and brandFlag = 1 order by "+fieldVal+" limit "+min+", "+max;
					}
					else if(pageId.equalsIgnoreCase("7"))
					{
						int lowerSubTag1 = Integer.parseInt(paramVec.elementAt(7).toString().trim());
						if(lowerSubTag1 == 1)
						{
							strQuery = "Select * from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+" and tag_id= 4 and brandFlag = 1 and resolve_date != 0000-00-00 and  reject_date= 0000-00-00 order by "+fieldVal+" limit "+min+", "+max;
						}
						else if(lowerSubTag1 == 2)
						{
							strQuery = "Select * from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+" and tag_id= 4 and brandFlag = 1 and resolve_date = 0000-00-00 and reject_date = 0000-00-00  order by "+fieldVal+" limit "+min+", "+max;
						}
						else
						{
							strQuery = "Select * from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+" and tag_id= 4 and brandFlag = 1 order by "+fieldVal+" limit "+min+", "+max;
						}
					}
					else if(pageId.equalsIgnoreCase("5") || pageId.equalsIgnoreCase("6"))
					{
						strQuery = "Select * from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+" and (tag_id= 2 or tag_id= 3 or tag_id= 4) and brandFlag = 1 order by "+fieldVal+" limit "+min+", "+max;
					}
				*/
				/**
				 * set all the fileds type and name for blogs table by using
				 * Support class object.
				 */
				support.setFieldVec("int", "complaint_id");		//0
				support.setFieldVec("string", "fcom_id");		//1
				support.setFieldVec("string", "complaint_title");	//2								
				support.setFieldVec("string", "creation_date");		//3
				support.setFieldVec("string", "creation_time");		//4
				support.setFieldVec("int", "login_id");				//5
					
				
				//////////System.out.println("strQuery......."+strQuery);
				resultVec = masterUtil.getList(ds, strQuery, support);
				return resultVec;
			}
			
			
			// for mentoring request list 
			public Vector getMentoringReqList(DataSource ds,Vector paramVec){
				Vector resultVec = new Vector();
				////////System.out.println("paramVec......."+paramVec);
				int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
				int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
				String fieldVal = paramVec.elementAt(2).toString().trim();
				int advtL_id = Integer.parseInt(paramVec.elementAt(3).toString().trim());
				int advt_id = Integer.parseInt(paramVec.elementAt(4).toString().trim());
				//int adminFlag = Integer.parseInt(paramVec.elementAt(5).toString().trim());
				//String pageId = paramVec.elementAt(6).toString().trim();
				//String lowerSubTag = paramVec.elementAt(8).toString().trim();
				Support support=new Support();
				   String strQuery="";
			   String mreq="Mrequest";
			  
				   
					
			strQuery = "Select * from complaints com, loginmaster log, places pl where com.advt_id="+advt_id+" and com.advtL_id="+advtL_id+" and com.q_type='"+mreq+"'  and com.brandFlag = 1 and com.login_id=log.userid and log.city = pl.placeId order by "+fieldVal+" limit "+min+", "+max;
					
				
				/**
				 * set all the fileds type and name for blogs table by using
				 * Support class object.
				 */
				support.setFieldVec("int", "com.complaint_id");		//0
				support.setFieldVec("string", "com.fcom_id");		//1
				support.setFieldVec("string", "com.complaint_title");	//2								
				support.setFieldVec("string", "com.creation_date");		//3
				support.setFieldVec("string", "com.creation_time");		//4
				support.setFieldVec("int", "com.login_id");				//5
				support.setFieldVec("string", "log.first_name");				//6
				support.setFieldVec("string", "log.last_name");				//7
				support.setFieldVec("string", "pl.PlaceName");				//8
					
				
				System.out.println("strQuery......."+strQuery);
				resultVec = masterUtil.getList(ds, strQuery, support);
				return resultVec;
			}
			
			// get mentoring requwst count.
			public int getMentoringReqCount(DataSource ds,Vector paramVec){
				////////////System.out.println("VecrotSize in Count>>>>>>>>>>"+paramVec.size());
				
				int result = 0;
				
				int advtL_id = Integer.parseInt(paramVec.elementAt(0).toString().trim());
				int advt_id = Integer.parseInt(paramVec.elementAt(1).toString().trim());
				//int adminFlag = Integer.parseInt(paramVec.elementAt(2).toString().trim());
				//String pageId = paramVec.elementAt(3).toString().trim();
				//String lowerSubTagStr = "1";
				
				 String strQuery="";
				
				  
				strQuery = "SELECT count(*) count from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and  q_type = 'Mrequest' and brandFlag = 1";
						
					
					
				  
				   ////////System.out.println(" Count strQuery>>>>>>>>>>>>"+strQuery);
				result = masterUtil.getCount(ds, strQuery);
				////////////System.out.println(" Count result>>>>>>>>>>>>"+result);
				return result;
			}
			
			// for BrandExistingComplaint List Action
			
			
			
			public Vector getStudentList(DataSource ds,Vector paramVec)
			{
				int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
				int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
				String fieldVal = paramVec.elementAt(2).toString().trim();
				String College_id=paramVec.elementAt(3).toString().trim();
				Support support=new Support();
				//////////////System.out.println("Sub Category 5 "+numSCatId);
				String strQuery = "Select * from loginmaster where college_id='"+College_id+"~1' order by "+fieldVal+" limit "+min+", "+max;
				support.setFieldVec("int", "userid");
				support.setFieldVec("string", "first_name");
				support.setFieldVec("string", "last_name");
				support.setFieldVec("string", "city");
				support.setFieldVec("string", "course");
				System.out.println(" Count strQuery>>>>>>>>>>>>"+strQuery);
				Vector resultVec = masterUtil.getList(ds, strQuery, support);
				
				return resultVec;
			}
			
			public int getStudentCount(DataSource ds, int College_id){
				int result = 0;
				 String strQuery="";
				strQuery = "SELECT count(*) count from loginmaster where college_id='"+College_id+"~1'";
				System.out.println(" Count strQuery>>>>>>>>>>>>"+strQuery);
				result = masterUtil.getCount(ds, strQuery);
				////////////System.out.println(" Count result>>>>>>>>>>>>"+result);
				return result;
			}
			
			public int getNoticesCount(DataSource ds, Vector paramVec){
				int result = 0;
				 String strQuery="";
				 int facilityid = Integer.parseInt(paramVec.elementAt(1).toString().trim());
				 int mon = Integer.parseInt(paramVec.elementAt(3).toString().trim());
				 int year = Integer.parseInt(paramVec.elementAt(4).toString().trim());
				strQuery = "SELECT count(*) count from noticeboard where facility_id="+facilityid+" and SUBSTRING(createdon,6,2)="+mon+" and SUBSTRING(createdon,1,4)="+year;
				System.out.println(" Count strQuery>>>>>>>>>>>>"+strQuery);
				result = masterUtil.getCount(ds, strQuery);
				////////////System.out.println(" Count result>>>>>>>>>>>>"+result);
				return result;
			}
			
			public Vector getBrandComplaintList(DataSource ds,Vector paramVec){
				Vector resultVec = new Vector();
				////////System.out.println("paramVec......."+paramVec);
				int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
				int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
				String fieldVal = paramVec.elementAt(2).toString().trim();
				int advtL_id = Integer.parseInt(paramVec.elementAt(3).toString().trim());
				int advt_id = Integer.parseInt(paramVec.elementAt(4).toString().trim());
				//int adminFlag = Integer.parseInt(paramVec.elementAt(5).toString().trim());
				String pageId = paramVec.elementAt(6).toString().trim();
				String lowerSubTag = paramVec.elementAt(8).toString().trim();
				
		//		System.out.println("advt id is"+advt_id+"advt_lId is "+advtL_id);
				Support support=new Support();
				   String strQuery="";
			  
			  
				   if(pageId.equalsIgnoreCase("1") && lowerSubTag.equalsIgnoreCase ("1"))
					{
						String ComList = paramVec.elementAt(7).toString().trim();
						if(ComList.length()>0)
						{
							strQuery = "SELECT * from complaints com, loginmaster log where com.advtL_id="+advtL_id+"  and com.brandFlag = 1  and  com.q_type= 'Personal' and com.login_id=log.userid   order by "+fieldVal+" limit "+min+", "+max;
							
						//	strQuery = "SELECT * from complaints com where com.advtL_id="+advtL_id+"  and com.brandFlag = 1  and com.q_type= 'Personal' order by "+fieldVal+" limit "+min+", "+max;
							
						}
						else
						{	
							strQuery = "SELECT * from complaints com, loginmaster log where com.advtL_id="+advtL_id+"  and com.brandFlag = 1  and  com.q_type= 'Personal' and com.login_id=log.userid  order by "+fieldVal+" limit "+min+", "+max;
							
						//	strQuery = "SELECT * from complaints where com.advtL_id="+advtL_id+"  and com.brandFlag = 1  and com.q_type= 'Personal' order by "+fieldVal+" limit "+min+", "+max;
						}
					}
					else if(pageId.equalsIgnoreCase("1") && lowerSubTag.equalsIgnoreCase ("2"))
					{
						
						strQuery = "SELECT distinct(com.complaint_id),com.fcom_id,com.complaint_title,com.creation_date,com.creation_time, com.login_id, log.first_name, log.last_name, pl.PlaceName from complaints com, communication r, loginmaster log, places pl  where com.advt_id="+advt_id+" and com.advtL_id="+advtL_id+" and com.brandFlag = 1 and  com.q_type= 'Personal'   and com.complaint_id = r.complaintid and r.userid=com.advtL_id and com.login_id=log.userid and log.city = pl.placeId order by "+fieldVal+" limit "+min+", "+max;
						
						//strQuery = "SELECT distinct(complaint_id),fcom_id,complaint_title,creation_date,creation_time from complaints b, communication r  where b.advt_id="+advt_id+" and b.advtL_id="+advtL_id+" and b.brandFlag = 1 and  (b.q_type= 'Technical' or b.q_type= 'Personal'or b.q_type= 'Trequest' )   and b.complaint_id = r.complaintid and r.userid=b.advtL_id order by "+fieldVal+" limit "+min+", "+max;
					}
					
				/**
				 * set all the fileds type and name for blogs table by using
				 * Support class object.
				 */
				support.setFieldVec("int", "com.complaint_id");		//0
				support.setFieldVec("string", "com.fcom_id");		//1
				support.setFieldVec("string", "com.complaint_title");	//2								
				support.setFieldVec("string", "com.creation_date");		//3
				support.setFieldVec("string", "com.creation_time");		//4
				support.setFieldVec("int", "com.login_id");				//5
				support.setFieldVec("string", "log.first_name");				//6
				support.setFieldVec("string", "log.last_name");				//7
					
				
				System.out.println("strQuery......."+strQuery);
				resultVec = masterUtil.getList(ds, strQuery, support);
				return resultVec;
			}

			public Vector getBrandComplaintList1(DataSource ds,Vector paramVec){
				Vector resultVec = new Vector();
				////////System.out.println("paramVec......."+paramVec);
				int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
				int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
				String fieldVal = paramVec.elementAt(2).toString().trim();
				int advtL_id = Integer.parseInt(paramVec.elementAt(3).toString().trim());
				int advt_id = Integer.parseInt(paramVec.elementAt(4).toString().trim());
				//int adminFlag = Integer.parseInt(paramVec.elementAt(5).toString().trim());
				String pageId = paramVec.elementAt(6).toString().trim();
				String lowerSubTag = paramVec.elementAt(8).toString().trim();
				String categoryid = paramVec.elementAt(9).toString().trim();
				
		//		System.out.println("advt id is"+advt_id+"advt_lId is "+advtL_id);
				Support support=new Support();
				   String strQuery="";
			  
			  
				   if(pageId.equalsIgnoreCase("1") && lowerSubTag.equalsIgnoreCase ("1"))
					{	
					   if (categoryid.equalsIgnoreCase("0")){
						   System.out.println("Statement here");
						String ComList = paramVec.elementAt(7).toString().trim();
						if(ComList.length()>0)
						{
							strQuery = "SELECT * from complaints com, loginmaster log where com.advtL_id="+advtL_id+"  and com.brandFlag = 1  and  com.q_type= 'Personal' and com.login_id=log.userid   order by "+fieldVal+" limit "+min+", "+max;
							
						//	strQuery = "SELECT * from complaints com where com.advtL_id="+advtL_id+"  and com.brandFlag = 1  and com.q_type= 'Personal' order by "+fieldVal+" limit "+min+", "+max;
							
						}
						else
						{	
							strQuery = "SELECT * from complaints com, loginmaster log where com.advtL_id="+advtL_id+" and com.brandFlag = 1  and  com.q_type= 'Personal' and com.login_id=log.userid  order by "+fieldVal+" limit "+min+", "+max;
							
						//	strQuery = "SELECT * from complaints where com.advtL_id="+advtL_id+"  and com.brandFlag = 1  and com.q_type= 'Personal' order by "+fieldVal+" limit "+min+", "+max;
						}}
					   else{
						   String ComList = paramVec.elementAt(7).toString().trim();
							if(ComList.length()>0)
							{
								strQuery = "SELECT * from complaints com, loginmaster log where com.advtL_id="+advtL_id+"  and com.category="+categoryid+" and com.brandFlag = 1  and  com.q_type= 'Personal' and com.login_id=log.userid   order by "+fieldVal+" limit "+min+", "+max;
								
							//	strQuery = "SELECT * from complaints com where com.advtL_id="+advtL_id+"  and com.brandFlag = 1  and com.q_type= 'Personal' order by "+fieldVal+" limit "+min+", "+max;
								
							}
							else
							{	
								strQuery = "SELECT * from complaints com, loginmaster log where com.advtL_id="+advtL_id+"  and com.category="+categoryid+"  and com.brandFlag = 1  and  com.q_type= 'Personal' and com.login_id=log.userid  order by "+fieldVal+" limit "+min+", "+max;
								
							//	strQuery = "SELECT * from complaints where com.advtL_id="+advtL_id+"  and com.brandFlag = 1  and com.q_type= 'Personal' order by "+fieldVal+" limit "+min+", "+max;
							}} 					   
					}
					else if(pageId.equalsIgnoreCase("1") && lowerSubTag.equalsIgnoreCase ("2"))
					{
						
						strQuery = "SELECT distinct(com.complaint_id),com.fcom_id,com.complaint_title,com.creation_date,com.creation_time, com.login_id, log.first_name, log.last_name, pl.PlaceName from complaints com, communication r, loginmaster log, places pl  where com.advt_id="+advt_id+" and com.advtL_id="+advtL_id+" and com.brandFlag = 1 and  com.q_type= 'Personal'   and com.complaint_id = r.complaintid and r.userid=com.advtL_id and com.login_id=log.userid and log.city = pl.placeId order by "+fieldVal+" limit "+min+", "+max;
						
						//strQuery = "SELECT distinct(complaint_id),fcom_id,complaint_title,creation_date,creation_time from complaints b, communication r  where b.advt_id="+advt_id+" and b.advtL_id="+advtL_id+" and b.brandFlag = 1 and  (b.q_type= 'Technical' or b.q_type= 'Personal'or b.q_type= 'Trequest' )   and b.complaint_id = r.complaintid and r.userid=b.advtL_id order by "+fieldVal+" limit "+min+", "+max;
					}
					
				/**
				 * set all the fileds type and name for blogs table by using
				 * Support class object.
				 */
				support.setFieldVec("int", "com.complaint_id");		//0
				support.setFieldVec("string", "com.fcom_id");		//1
				support.setFieldVec("string", "com.complaint_title");	//2								
				support.setFieldVec("string", "com.creation_date");		//3
				support.setFieldVec("string", "com.creation_time");		//4
				support.setFieldVec("int", "com.login_id");				//5
				support.setFieldVec("string", "log.first_name");				//6
				support.setFieldVec("string", "log.last_name");				//7
					
				
				System.out.println("strQuery......."+strQuery);
				resultVec = masterUtil.getList(ds, strQuery, support);
				return resultVec;
			}

					
			
			public Vector getBrandComplaintList1L(DataSource ds,Vector paramVec){
				Vector resultVec = new Vector();
				////////System.out.println("paramVec......."+paramVec);
				int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
				int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
				String fieldVal = paramVec.elementAt(2).toString().trim();
				int advtL_id = Integer.parseInt(paramVec.elementAt(3).toString().trim());
				int advt_id = Integer.parseInt(paramVec.elementAt(4).toString().trim());
				//int adminFlag = Integer.parseInt(paramVec.elementAt(5).toString().trim());
				String pageId = paramVec.elementAt(6).toString().trim();
				String lowerSubTag = paramVec.elementAt(8).toString().trim();
				String categoryid = paramVec.elementAt(9).toString().trim();
				String mont = paramVec.elementAt(10).toString().trim();
				int year = Integer.parseInt(paramVec.elementAt(11).toString().trim());
				String month=mont;
				int monsize = mont.length();
				System.out.println("month length is "+monsize);
				if (monsize == 1){ 
				month="0"+mont;	
				}
				System.out.println("Month is "+month);
				Support support=new Support();
				   String strQuery="";
			  
			  
				   if(pageId.equalsIgnoreCase("1") && lowerSubTag.equalsIgnoreCase ("1"))
					{	
					   if (categoryid.equalsIgnoreCase("0")){
						String ComList = paramVec.elementAt(7).toString().trim();
						if(ComList.length()>0)
						{
							strQuery = "SELECT * from complaints com, loginmaster log where com.advtL_id="+advtL_id+"  and com.brandFlag = 1  and  com.q_type= 'Personal' and com.login_id=log.userid and SUBSTRING(creation_date,6,2)="+month+" and SUBSTRING(creation_date,1,4)="+year+"  order by "+fieldVal;
							
						//	strQuery = "SELECT * from complaints com where com.advtL_id="+advtL_id+"  and com.brandFlag = 1  and com.q_type= 'Personal' order by "+fieldVal+" limit "+min+", "+max;
							
						}
						else
						{	
							strQuery = "SELECT * from complaints com, loginmaster log where com.advtL_id="+advtL_id+" and com.brandFlag = 1  and  com.q_type= 'Personal' and com.login_id=log.userid  and SUBSTRING(creation_date,6,2)="+month+" and SUBSTRING(creation_date,1,4)="+year+" order by "+fieldVal;
							
						//	strQuery = "SELECT * from complaints where com.advtL_id="+advtL_id+"  and com.brandFlag = 1  and com.q_type= 'Personal' order by "+fieldVal+" limit "+min+", "+max;
						}}
					   else{
						   String ComList = paramVec.elementAt(7).toString().trim();
							if(ComList.length()>0)
							{
								strQuery = "SELECT * from complaints com, loginmaster log where com.advtL_id="+advtL_id+"  and com.category="+categoryid+" and com.brandFlag = 1  and  com.q_type= 'Personal' and com.login_id=log.userid   and SUBSTRING(creation_date,6,2)="+month+" and SUBSTRING(creation_date,1,4)="+year+" order by "+fieldVal;
								
							//	strQuery = "SELECT * from complaints com where com.advtL_id="+advtL_id+"  and com.brandFlag = 1  and com.q_type= 'Personal' order by "+fieldVal+" limit "+min+", "+max;
								
							}
							else
							{	
								strQuery = "SELECT * from complaints com, loginmaster log where com.advtL_id="+advtL_id+"  and com.category="+categoryid+"  and com.brandFlag = 1  and  com.q_type= 'Personal' and com.login_id=log.userid   and SUBSTRING(creation_date,6,2)="+month+" and SUBSTRING(creation_date,1,4)="+year+" order by "+fieldVal;
								
							//	strQuery = "SELECT * from complaints where com.advtL_id="+advtL_id+"  and com.brandFlag = 1  and com.q_type= 'Personal' order by "+fieldVal+" limit "+min+", "+max;
							}} 					   
					}
					else if(pageId.equalsIgnoreCase("1") && lowerSubTag.equalsIgnoreCase ("2"))
					{
						
						strQuery = "SELECT distinct(com.complaint_id),com.fcom_id,com.complaint_title,com.creation_date,com.creation_time, com.login_id, log.first_name, log.last_name, pl.PlaceName from complaints com, communication r, loginmaster log, places pl  where com.advt_id="+advt_id+" and com.advtL_id="+advtL_id+" and com.brandFlag = 1 and  com.q_type= 'Personal'   and com.complaint_id = r.complaintid and r.userid=com.advtL_id and com.login_id=log.userid and log.city = pl.placeId order by "+fieldVal+" limit "+min+", "+max;
						
						//strQuery = "SELECT distinct(complaint_id),fcom_id,complaint_title,creation_date,creation_time from complaints b, communication r  where b.advt_id="+advt_id+" and b.advtL_id="+advtL_id+" and b.brandFlag = 1 and  (b.q_type= 'Technical' or b.q_type= 'Personal'or b.q_type= 'Trequest' )   and b.complaint_id = r.complaintid and r.userid=b.advtL_id order by "+fieldVal+" limit "+min+", "+max;
					}
					
				/**
				 * set all the fileds type and name for blogs table by using
				 * Support class object.
				 */
				support.setFieldVec("int", "com.complaint_id");		//0
				support.setFieldVec("string", "com.fcom_id");		//1
				support.setFieldVec("string", "com.complaint_title");	//2								
				support.setFieldVec("string", "com.creation_date");		//3
				support.setFieldVec("string", "com.creation_time");		//4
				support.setFieldVec("int", "com.login_id");				//5
				support.setFieldVec("string", "log.first_name");				//6
				support.setFieldVec("string", "log.last_name");				//7
				support.setFieldVec("int", "category");				//8	
				support.setFieldVec("int", "tag_id");				//9
				support.setFieldVec("string", "com.complaint_text");	//10								
				support.setFieldVec("int", "com.dealer_id");				//11
				System.out.println("strQuery......."+strQuery);
				resultVec = masterUtil.getList(ds, strQuery, support);
				return resultVec;
			}

			
			
			
			/**
			 * 
			 * @param paramVec takes parameter like Minimu and maximum value
			 *  for fatching the list of complaint.
			 * @return
			 */	
			/**
			 * This function is use for getting list of  complaint's agaist a particuler  brand manager.
			 * It returns a list within limit which is given by min and max variable.
			 */		
				public Vector getQueryList(DataSource ds,Vector paramVec){
					Vector resultVec = new Vector();
					System.out.println("paramVec...for com to std...."+paramVec.size());
					int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
					int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
					String fieldVal = paramVec.elementAt(2).toString().trim();
					int login_id = Integer.parseInt(paramVec.elementAt(3).toString().trim());
					int comp_id = Integer.parseInt(paramVec.elementAt(4).toString().trim());
					String pagetype=paramVec.elementAt(5).toString().trim();
					Support support=new Support();
					   String strQuery="";
					   /* Use This code if comment vector is Present
						   * String pageid="";
						   if(pageid.equalsIgnoreCase("1"))
					   		{
						   String ComList = paramVec.elementAt(4).toString().trim();
							if(ComList.length()>0)
							{
								strQuery = "SELECT * from complaints where login_id="+advtL_id+" and companyid="+advt_id+"  and brandFlag = 1  and q_type= 'ComToStd'  and complaint_id  not in ("+ComList+")";
							}
							else
							{
								strQuery = "SELECT * from complaints where login_id="+advtL_id+" and companyid="+advt_id+"  and brandFlag = 1  and q_type= 'ComToStd'";
							}
							}*/
					   if(paramVec.size()==7)
					   {
						   if(pagetype.equalsIgnoreCase("advt"))
					   		{
						   strQuery = "SELECT * from complaints where login_id="+login_id+" and companyid="+comp_id+"  and brandFlag = 1  and q_type= 'ColToStd'  order by "+fieldVal+" limit "+min+", "+max;
					   		}else if(pagetype.equalsIgnoreCase("student"))
					   		{
					   		 strQuery = "SELECT * from complaints where advtL_id="+login_id+" and advt_id="+comp_id+"  and brandFlag = 1  and q_type= 'ColToStd'  order by "+fieldVal+" limit "+min+", "+max;
					   		} 
					   }else
					   {
						   if(pagetype.equalsIgnoreCase("corpo"))
					   		{
						   strQuery = "SELECT * from complaints where login_id="+login_id+" and companyid="+comp_id+"  and brandFlag = 1  and q_type= 'ComToStd'  order by "+fieldVal+" limit "+min+", "+max;
					   		}else if(pagetype.equalsIgnoreCase("student"))
					   		{
					   		 strQuery = "SELECT * from complaints where advtL_id="+login_id+" and advt_id="+comp_id+"  and brandFlag = 1  and (q_type= 'ComToStd'or q_type= 'ColToStd')  order by "+fieldVal+" limit "+min+", "+max;
					   		} 
					   }
					support.setFieldVec("int", "complaint_id");
					support.setFieldVec("string", "fcom_id");
					support.setFieldVec("string", "complaint_title");								
					support.setFieldVec("string", "creation_date");	
					support.setFieldVec("string", "creation_time");
					support.setFieldVec("int", "login_id");
						
					
					////////System.out.println("strQuery....for com to std..."+strQuery);
					resultVec = masterUtil.getList(ds, strQuery, support);
					return resultVec;
				}
			
				
				/**
				 * This function use for getting total no. of complaint's agaist a particuler  brand manager.
				 * @return
				 */	
					public int getQueryCount(DataSource ds,Vector paramVec){
						
						int result = 0;
						
						int advtL_id = Integer.parseInt(paramVec.elementAt(0).toString().trim());
						int advt_id = Integer.parseInt(paramVec.elementAt(1).toString().trim());
						
						String pagetype=paramVec.elementAt(2).toString().trim();
						////////System.out.println("totalRow..........."+paramVec);
						Support support=new Support();
						   String strQuery="";
						  /* Use This code if comment vector is Present
						   * String pageid="";
						   if(pageid.equalsIgnoreCase("1"))
					   		{
						   String ComList = paramVec.elementAt(4).toString().trim();
							if(ComList.length()>0)
							{
								strQuery = "SELECT count(distinct(complaint_id)) count from complaints where login_id="+advtL_id+" and companyid="+advt_id+"  and brandFlag = 1  and q_type= 'ComToStd'  and complaint_id  not in ("+ComList+")";
							}
							else
							{
								strQuery = "SELECT count(distinct(complaint_id)) count from complaints where login_id="+advtL_id+" and companyid="+advt_id+"  and brandFlag = 1  and q_type= 'ComToStd'";
							}
							}*/
						   if(paramVec.size()==4)
						   {
							
						   if(pagetype.equalsIgnoreCase("advt"))
						   		{
							   strQuery = "SELECT count(*) count from complaints where login_id="+advtL_id+" and companyid="+advt_id+"  and brandFlag = 1  and q_type= 'ColToStd'";
						   		}else if(pagetype.equalsIgnoreCase("student"))
						   		{
						   			strQuery = "SELECT count(*) count from complaints where advtL_id="+advtL_id+" and advt_id="+advt_id+"  and brandFlag = 1  and q_type= 'ColToStd'";
						   		}
						   }else
						   {
							   if(pagetype.equalsIgnoreCase("corpo"))
						   		{
							   strQuery = "SELECT count(*) count from complaints where login_id="+advtL_id+" and companyid="+advt_id+"  and brandFlag = 1  and q_type= 'ComToStd'";
						   		}else if(pagetype.equalsIgnoreCase("student"))
						   		{
						   			strQuery = "SELECT count(*) count from complaints where advtL_id="+advtL_id+" and advt_id="+advt_id+"  and brandFlag = 1  and q_type= 'ComToStd'";
						   		} 
						   }
						   		
						   		result = masterUtil.getCount(ds, strQuery);
						   		////////System.out.println("totalRow..........."+result);
						return result;
					}

			
			/**
			 * This function use for getting total no. of complaint's agaist a particuler  brand manager.
			 * @return
			 */	
				public int getComplaintCount(DataSource ds,Vector paramVec){
					////////////System.out.println("VecrotSize in Count>>>>>>>>>>"+paramVec.size());
					
					int result = 0;
					
					int advtL_id = Integer.parseInt(paramVec.elementAt(0).toString().trim());
					int advt_id = Integer.parseInt(paramVec.elementAt(1).toString().trim());
					int adminFlag = Integer.parseInt(paramVec.elementAt(2).toString().trim());
					String pageId = paramVec.elementAt(3).toString().trim();
					String lowerSubTagStr = "1";
					
					  if(pageId.equalsIgnoreCase("1") )
						{
						 lowerSubTagStr = paramVec.elementAt(5).toString().trim();
						}
					
					System.out.println("tags pageID is"+pageId+"lower sub tag is"+lowerSubTagStr);
						
						String mreq="Mrequest";
					    
					    String strQuery="";
					    
					    if(pageId.equalsIgnoreCase("1") && lowerSubTagStr.equalsIgnoreCase ("1"))
						{
							String ComList = paramVec.elementAt(4).toString().trim();
							if(ComList.length()>0)
							{
								strQuery = "SELECT count(distinct(complaint_id)) count from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and brandFlag = 1 and q_type= 'Personal'";
							}
							else
							{
								strQuery = "SELECT count(distinct(complaint_id)) count from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and brandFlag = 1  and q_type= 'Personal'";
							}
						}
						else if(pageId.equalsIgnoreCase("1") && lowerSubTagStr.equalsIgnoreCase ("2"))
						{
							strQuery = "SELECT count(distinct(complaint_id)) count from complaints b, communication r  where b.advt_id="+advt_id+" and b.advtL_id="+advtL_id+" and b.brandFlag = 1 and  (b.q_type= 'Technical' or b.q_type= 'Personal' or q_type= 'Trequest' )   and b.complaint_id = r.complaintid and r.userid=b.advtL_id";
						}					    
					   
						else if(pageId.equalsIgnoreCase("2"))
						{
							strQuery = "SELECT count(distinct(complaint_id)) count from complaints  b, communication r  where b.advt_id="+advt_id+" and b.advtL_id="+advtL_id+" and b.brandFlag = 1  and b.complaint_id = r.complaintid and r.userid=b.advtL_id";
							
						}
						else if(pageId.equalsIgnoreCase("3"))
						{
							strQuery = "SELECT count(*) count from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and  q_type = 'Mrequest' and brandFlag = 1";
							
						}
						else if(pageId.equalsIgnoreCase("7"))
						{
							int lowerSubTag = Integer.parseInt(paramVec.elementAt(4).toString().trim());
							if(lowerSubTag == 1)
							{
								strQuery = "SELECT count(*) count from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and  tag_id= 4 and resolve_date != 0000-00-00 and  reject_date= 0000-00-00 and brandFlag = 1";
							}
							else if(lowerSubTag == 2)
							{
								strQuery = "SELECT count(*) count from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and  tag_id= 4 and resolve_date = 0000-00-00 and reject_date = 0000-00-00  and brandFlag = 1";
							}
							else
							{
								strQuery = "SELECT count(*) count from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and  tag_id= 4 and brandFlag = 1";
							}
							
						}
						else if(pageId.equalsIgnoreCase("5") || pageId.equalsIgnoreCase("6"))
						{
							strQuery = "SELECT count(*) count from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and (tag_id= 2 or tag_id= 3 or tag_id= 4) and brandFlag = 1";
							
						}
						
					  
					   ////////System.out.println(" Count strQuery>>>>>>>>>>>>"+strQuery);
					result = masterUtil.getCount(ds, strQuery);
					////////////System.out.println(" Count result>>>>>>>>>>>>"+result);
					return result;
				}

				/**
				 * This function use for getting total no. of complaint's agaist a facaility - for local life
				 * @return
				 */	
					public int getComplaintCountL(DataSource ds,Vector paramVec){
						////////////System.out.println("VecrotSize in Count>>>>>>>>>>"+paramVec.size());
						
						int result = 0;
						
						int advtL_id = Integer.parseInt(paramVec.elementAt(0).toString().trim());
						int advt_id = Integer.parseInt(paramVec.elementAt(1).toString().trim());
						int adminFlag = Integer.parseInt(paramVec.elementAt(2).toString().trim());
						String pageId = paramVec.elementAt(3).toString().trim();
						String lowerSubTagStr = "1";
						
						  if(pageId.equalsIgnoreCase("1") )
							{
							 lowerSubTagStr = paramVec.elementAt(5).toString().trim();
							}
						
						System.out.println("tags pageID is"+pageId+"lower sub tag is"+lowerSubTagStr);
							
							String mreq="Mrequest";
						    
						    String strQuery="";
						    
						    if(pageId.equalsIgnoreCase("1") && lowerSubTagStr.equalsIgnoreCase ("1"))
							{
								String ComList = paramVec.elementAt(4).toString().trim();
								if(ComList.length()>0)
								{
									strQuery = "SELECT count(distinct(complaint_id)) count from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and brandFlag = 1 and q_type= 'Personal'";
								}
								else
								{
									strQuery = "SELECT count(distinct(complaint_id)) count from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and brandFlag = 1  and q_type= 'Personal'";
								}
							}
							else if(pageId.equalsIgnoreCase("1") && lowerSubTagStr.equalsIgnoreCase ("2"))
							{
								strQuery = "SELECT count(distinct(complaint_id)) count from complaints b, communication r  where b.advt_id="+advt_id+" and b.advtL_id="+advtL_id+" and b.brandFlag = 1 and  (b.q_type= 'Technical' or b.q_type= 'Personal' or q_type= 'Trequest' )   and b.complaint_id = r.complaintid and r.userid=b.advtL_id";
							}					    
						   
							else if(pageId.equalsIgnoreCase("2"))
							{
								strQuery = "SELECT count(distinct(complaint_id)) count from complaints  b, communication r  where b.advt_id="+advt_id+" and b.advtL_id="+advtL_id+" and b.brandFlag = 1  and b.complaint_id = r.complaintid and r.userid=b.advtL_id";
								
							}
							else if(pageId.equalsIgnoreCase("3"))
							{
								strQuery = "SELECT count(*) count from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and  q_type = 'Mrequest' and brandFlag = 1";
								
							}
							else if(pageId.equalsIgnoreCase("7"))
							{
								int lowerSubTag = Integer.parseInt(paramVec.elementAt(4).toString().trim());
								if(lowerSubTag == 1)
								{
									strQuery = "SELECT count(*) count from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and  tag_id= 4 and resolve_date != 0000-00-00 and  reject_date= 0000-00-00 and brandFlag = 1";
								}
								else if(lowerSubTag == 2)
								{
									strQuery = "SELECT count(*) count from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and  tag_id= 4 and resolve_date = 0000-00-00 and reject_date = 0000-00-00  and brandFlag = 1";
								}
								else
								{
									strQuery = "SELECT count(*) count from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and  tag_id= 4 and brandFlag = 1";
								}
								
							}
							else if(pageId.equalsIgnoreCase("5") || pageId.equalsIgnoreCase("6"))
							{
								strQuery = "SELECT count(*) count from complaints where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and (tag_id= 2 or tag_id= 3 or tag_id= 4) and brandFlag = 1";
								
							}
							
						  
						   ////////System.out.println(" Count strQuery>>>>>>>>>>>>"+strQuery);
						result = masterUtil.getCount(ds, strQuery);
						////////////System.out.println(" Count result>>>>>>>>>>>>"+result);
						return result;
					}
				
				
				/**
				 * This function is use for getting all complaint detail of a particuler
				 * complaint.
				 */
				public Vector getComplaintDetails(DataSource ds, int compId) {
					Support support=new Support();
					String strQuery = "select * from complaints where complaint_id = " + compId;
					/*
					 * set all the fileds type and name for blogs table by using Support
					 * class object.
					 */
					support.setFieldVec("string", "complaint_title");	// 0
					support.setFieldVec("string", "complaint_text");	// 1
					support.setFieldVec("string", "relevent_info");		// 2
					support.setFieldVec("int", "login_id");				// 3
					support.setFieldVec("int", "companyid");			// 4
					support.setFieldVec("int", "entp_id");				// 5
					support.setFieldVec("int", "cu_id");				// 6
					support.setFieldVec("String", "fcom_id");			// 7
					support.setFieldVec("int", "advt_id");				// 8
					support.setFieldVec("int", "advtL_id");				// 9
					support.setFieldVec("int", "dealer_id");			// 10
					return masterUtil.getDetail(ds, strQuery, support);
				}
				
				
				
				
				
				/************************************************************************************************************
				This function update complaint in complaints table by core user.
				and return the result string Success or failure accordiong to query result.
			**************************************************************************************************************/
				public String updateComplaint(DataSource ds,String uId,Vector paramVec){
					
					String strResult = "";
					String strQuery="";
				    Vector<String> queryVec =  new Vector<String>();
				    for(int i=0;i<paramVec.size();i++)
				    {
				    	
				    	int complaint_id =Integer.parseInt(paramVec.elementAt(i).toString().trim());
				    	
				    	strQuery = "Update complaints set advtL_id="+uId+" where complaint_id="+complaint_id;
				    	queryVec.add(strQuery);
				    }
						
				
					strResult = masterUtil.setBatchData(ds, queryVec);
					return strResult;
					
					
				}
				/*
			 *	Function getUnderprocessList
			 */
				
				
			public Vector getUnderprocessList(DataSource ds,int advt_id , int advtL_id, int adminFlag ){
				Vector resultVec = new Vector();

				Support support=new Support();
				String strQuery="";
				strQuery = "SELECT distinct(complaint_id) from complaints b, communication r  where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and b.brandFlag = 1  and b.complaint_id = r.complaintid and r.userid=b.advtL_id";
				
				support.setFieldVec("int", "complaint_id");
				
				////////////System.out.println("resultVec in advt master.."+strQuery);
				resultVec = masterUtil.getList(ds, strQuery, support);
				
				
				return resultVec;
	}
			
			/*
			 *	Function getUnderprocessList
			 */
				
				
			public Vector getUnderprocessList1(DataSource ds,int advt_id , int advtL_id, int adminFlag ){
				Vector resultVec = new Vector();

				Support support=new Support();
				String strQuery="";
				strQuery = "SELECT distinct(complaint_id) from complaints  where advt_id="+advt_id+" and advtL_id="+advtL_id+"  and brandFlag = 1  and  m_accept = 0";
				
				support.setFieldVec("int", "complaint_id");
				
				////////////System.out.println("resultVec in advt master.."+strQuery);
				resultVec = masterUtil.getList(ds, strQuery, support);
				
				
				return resultVec;
	}
			/**
			 * this functionis use for getting compaints those are unallotted.
			 * 
			 */
				/**********************************************************************************************************************************************
				This function created to get All existing complains list, Current complains list and under Process complains list.
			***********************************************************************************************************************************************/
				public Vector getUnAllottedComplainsList(DataSource ds,Vector dataVec)
				{
					Vector resultVec = new Vector();
					
					/************************************************************************************
					dataVec.elementAt(0) = Search Type;
					dataVec.elementAt(1) = min;
					dataVec.elementAt(2) = max;		
					dataVec.elementAt(3) = cuId;//loginId
					dataVec.elementAt(4) = entp_id;//companyId
					dataVec.elementAt(5) = adminFlag;
								
				*************************************************************************************/
					int min = Integer.parseInt(dataVec.elementAt(0).toString().trim());
					int max = Integer.parseInt(dataVec.elementAt(1).toString().trim());	
					String fieldVal = dataVec.elementAt(2).toString().trim();
					String companyId = dataVec.elementAt(3).toString().trim();
					String uId = dataVec.elementAt(4).toString().trim();		
					Support support=new Support();
				    String strQuery="";
					   
					if(uId.equals("0"))
					{
						strQuery = "select * from complaints where brandFlag = 1 and advtL_id = "+uId+" and advt_id = '"+companyId+"'  order by "+fieldVal+"  limit "+min+", "+max;
					}
					else
					{
						String ComList = dataVec.elementAt(5).toString().trim();
						
						if(ComList.length()>0)
						{
							strQuery = "select * from complaints where brandFlag = 1 and advtL_id = "+uId+" and advt_id = '"+companyId+"' and tag_id = 2  and complaint_id  not in ("+ComList+") order by "+fieldVal+"  limit "+min+", "+max;
						}
						else
						{
							strQuery = "select * from complaints where brandFlag = 1 and advtL_id = "+uId+" and advt_id = '"+companyId+"'  and tag_id = 2   order by "+fieldVal+"  limit "+min+", "+max;
						}
					}
						
					
					
					support.setFieldVec("int", "complaint_id");
					support.setFieldVec("string", "fcom_id");
					support.setFieldVec("string", "complaint_title");								
					support.setFieldVec("string", "creation_date");	
					support.setFieldVec("string", "creation_time");
					support.setFieldVec("int", "category");	
					support.setFieldVec("int", "advt_id");
					support.setFieldVec("string", "other");
					
					
					resultVec = masterUtil.getList(ds, strQuery, support);
					return resultVec;
				}
				/**
				* This function use for getting total no. of complaints in a particuler core user acounts.
				* List of complaints are search on the base of All, Current and Under process complaints.
				* @return
				*/	
				public int getUnallottedComplaintCount(DataSource ds,Vector dataVec){
						int result = 0;
						
					    String strQuery="";
						/************************************************************************************
						dataVec.elementAt(0) = Search Type;	
						dataVec.elementAt(1) = cuId;//loginId
						dataVec.elementAt(2) = entp_id;//companyId
						dataVec.elementAt(3) = adminFlag;
									
					*************************************************************************************/
						
						String companyId = dataVec.elementAt(0).toString().trim();
						String uId = dataVec.elementAt(1).toString().trim();
						
						if(uId.equals("0"))
						{
							strQuery = "SELECT count(*) count from complaints where brandFlag = 1 and  advtL_id = "+uId+" and advt_id ="+companyId;
						}
						else
						{
							String ComList = dataVec.elementAt(2).toString().trim();
							if(ComList.length()>0)
							{
								strQuery = "SELECT count(*) count from complaints where brandFlag = 1 and  advtL_id = "+uId+" and advt_id ="+companyId+" and tag_id = 2   and complaint_id  not in ("+ComList+")" ;
							}
							else
							{
								strQuery = "SELECT count(*) count from complaints where brandFlag = 1 and  advtL_id = "+uId+" and advt_id ="+companyId+" and tag_id = 2 ";
							}
						}
									
								result = masterUtil.getCount(ds, strQuery);
											
						return result;
					}
				
				/**
				 * This function use for getting total no. of Other complaints in core admin user acounts.
				 */
				public int getOtherCompTotalCount(DataSource ds,Vector dataVec){
					int result = 0;
					
				    String strQuery="";
					/************************************************************************************
					
					dataVec.elementAt(0) =  //companyId
					dataVec.elementAt(1) =  //loginId
					dataVec.elementAt(2) =  //ComList
					
								
				*************************************************************************************/
				    String companyId = dataVec.elementAt(0).toString().trim();
					String uId = dataVec.elementAt(1).toString().trim();
					String ComList = dataVec.elementAt(2).toString().trim();
					
					if(ComList.length()>0)
					{
						strQuery = "SELECT count(*) count from complaints where brandFlag = 1 and advt_id ="+companyId+" and advtL_id!=0 and tag_id =2  and complaint_id  not in ("+ComList+")" ;
					}
					else
					{
						strQuery = "SELECT count(*) count from complaints where brandFlag = 1 and advt_id ="+companyId+" and advtL_id!=0 and  tag_id =2";
					}
					
								
							result = masterUtil.getCount(ds, strQuery);
										
					return result;
				}
				
				/********************end of code unallotted***********************************/
				
				public Vector getNoticesList(DataSource ds,Vector paramVec){
					
					//////////////System.out.println("In siet Advt Master getUserList");
					
					Vector resultVec = new Vector();
					int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
					int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
					int facilityid = Integer.parseInt(paramVec.elementAt(4).toString().trim());
					String fieldVal = paramVec.elementAt(3).toString().trim();
					int mon = Integer.parseInt(paramVec.elementAt(6).toString().trim());
					int year = Integer.parseInt(paramVec.elementAt(7).toString().trim());
					
					Support support=new Support();
					   String strQuery="";
					
						strQuery = "select * from noticeboard where facility_id="+facilityid+" and SUBSTRING(createdon,6,2)="+mon+" and SUBSTRING(createdon,1,4)="+year;
					
					
					/**
					 * set all the fileds type and name for loginmaster table by using
					 * Support class object.
					 */
					support.setFieldVec("int", "facility_id");
					support.setFieldVec("string", "noticetitle");								
					support.setFieldVec("string", "noticetext");	
//					support.setFieldVec("int", "userid");
					support.setFieldVec("string", "createdon");
//					support.setFieldVec("int", "rid");
					
					
					
					resultVec = masterUtil.getList(ds, strQuery, support);
					
					//////////////System.out.println("In siet Advt Master resultVec"+resultVec);
					return resultVec;
				}
				/**
				 * This function is use for getting list of  User from `loginmaster`.
				 * It returns a list within limit which is given by min and max variable.
				 */
					public Vector getUserList(DataSource ds,Vector paramVec){
						
						//////////////System.out.println("In siet Advt Master getUserList");
						
						Vector resultVec = new Vector();
						int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
						int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
						int companyid = Integer.parseInt(paramVec.elementAt(2).toString().trim());
						String fieldVal = paramVec.elementAt(3).toString().trim();
						
						Support support=new Support();
						   String strQuery="";
						
							strQuery = "select * from loginmaster where companyid="+companyid+" and logindeletion=0  order by "+fieldVal+" LIMIT "+min+", "+max;
						
						
						/**
						 * set all the fileds type and name for loginmaster table by using
						 * Support class object.
						 */
						support.setFieldVec("int", "companyid");
						support.setFieldVec("string", "loginname");								
						support.setFieldVec("string", "first_name");	
						support.setFieldVec("int", "userid");
						support.setFieldVec("string", "last_name");
						support.setFieldVec("int", "rid");
						
						
						
						resultVec = masterUtil.getList(ds, strQuery, support);
						
						//////////////System.out.println("In siet Advt Master resultVec"+resultVec);
						return resultVec;
					}
					
					public Vector getAllUsersList(DataSource ds,Vector paramVec){
						
						//////////////System.out.println("In siet Advt Master getUserList");
						
						Vector resultVec = new Vector();
						
						int companyid = Integer.parseInt(paramVec.elementAt(0).toString().trim());
						
						
						Support support=new Support();
						   String strQuery="";
						
							strQuery = "select * from loginmaster where companyid="+companyid;
						
						
						/**
						 * set all the fileds type and name for loginmaster table by using
						 * Support class object.
						 */
						support.setFieldVec("int", "userid");
						support.setFieldVec("int", "companyid");
						support.setFieldVec("string", "mentor_cat");								
						
						
						
						resultVec = masterUtil.getList(ds, strQuery, support);
						
						//////////////System.out.println("In siet Advt Master resultVec"+resultVec);
						return resultVec;
					}
				
				/**
				 * This function use for getting total no. of User in the company .
				 * @return
				 */	
					public int getUserCount(DataSource ds,Vector paramVec){
						
						int companyid = Integer.parseInt(paramVec.elementAt(0).toString().trim());
						//////////////System.out.println("In siet Advt Master getUserCount");
						int result = 0;
						
												
						   String strQuery="";
						
							strQuery = "Select count(*) rowCount from loginmaster where logindeletion=0 and   companyid="+companyid;
							
							result = masterUtil.getCount(ds, strQuery);
						//result = masterUtil.getCount(ds, strQuery);
						//////////////System.out.println("In siet Advt Master result..."+result);
						return result;
					}
					/**
					 * This function returns vector of category. Which already alloted to Brand.
					 * @param numCompId
					 * @param ds
					 * @return
					 */
					public Vector getBrandCategory(int numCompId, DataSource ds)
					{
						Vector resultVec = new Vector();
						MasterUtility mu = new MasterUtility();
						Support support=new Support();
						String strQuery="Select *  From companymaster where company_id="+numCompId;
						support.setFieldVec("string", "category");
						String strCat = mu.getValue(ds, strQuery, support);
						//System.out.println("Brand strQuery............. "+strQuery);
						//System.out.println("strCat ............. "+strCat);
						String strCat1 = strCat.substring(0, strCat.length()-1);
						
						//////////////System.out.println("Brand Category "+strCat);
						if(!strCat.equalsIgnoreCase("failure"))
						{
							String strQuery1 = "select * from category where cat_id in ("+strCat1+")";
							Support support1 = new Support();
							support1.setFieldVec("int", "cat_id");
							support1.setFieldVec("string", "cat_name");
							resultVec = mu.getList(ds, strQuery1, support1);
							
							//System.out.println("Brand strQuery1................. "+strQuery1);
							//////////////System.out.println("result Vec in Brand Cat Advt Master "+resultVec);
						}
						
						return resultVec;
					}
					/**
					 * This private function is used to get any entry exist in complaint_mapping table 
					 * for given category and companyid.
					 * @param numCompId
					 * @param numCatId
					 * @param ds
					 * @return
					 */
					public boolean getComplaintUnnamedMap(int numCompId, int numCatId, DataSource ds)
					{
						boolean blnResult = false;
						MasterUtility mu = new MasterUtility();
						String strQuery = "Select count(*) From complaint_mapping Where companyid="+numCompId+" And categoryid="+numCatId;
						int numRowCount = mu.getCount(ds, strQuery);
						
						blnResult = (numRowCount>0) ? true : false;
						return blnResult;
					}
					
					/**
					 * This function is used to add or modify row in Compalint Unnnamed Mapping.
					 * @param dataVec
					 * @param ds
					 * @return
					 */
					public String setComplaintUnnamedMap(ComplaintForm cf, DataSource ds, int numCompId, int numCatId)
					{
						/**
						 * This function set unnamed Mapping field for Complaint writing procedure.
						 * In this function first we check row is exist for given criteria. 
						 * If exist then every time we modify that line. 
						 * else we insert a row for company and category. 
						 */
						String strResult = "failuer";
						
						//Get Unnamed Mapping Id here.
						boolean blnStatus = getComplaintUnnamedMap(numCompId, numCatId, ds);
						//Check if Unnamed Mapping id greter then 0.
						Support support = new Support();
						//support.setDataVec("string", strData)
						
//						Field 1 Values
						support.setDataVec("string", cf.getField1());
						support.setDataVec("string", cf.getCmbfield1());
						support.setDataVec("string", cf.getValfield1());
						support.setDataVec("string", cf.getMndfield1());
						support.setDataVec("string", cf.getVsbfield1());
						
//						Field 2 Values
						support.setDataVec("string", cf.getField2());
						support.setDataVec("string", cf.getCmbfield2());
						support.setDataVec("string", cf.getValfield2());
						support.setDataVec("string", cf.getMndfield2());
						support.setDataVec("string", cf.getVsbfield2());
						
//						Field 3 Values
						support.setDataVec("string", cf.getField3());
						support.setDataVec("string", cf.getCmbfield3());
						support.setDataVec("string", cf.getValfield3());
						support.setDataVec("string", cf.getMndfield3());
						support.setDataVec("string", cf.getVsbfield3());
						
//						Field 4 Values
						support.setDataVec("string", cf.getField4());
						support.setDataVec("string", cf.getCmbfield4());
						support.setDataVec("string", cf.getValfield4());
						support.setDataVec("string", cf.getMndfield4());
						support.setDataVec("string", cf.getVsbfield4());
						
//						Field 5 Values
						support.setDataVec("string", cf.getField5());
						support.setDataVec("string", cf.getCmbfield5());
						support.setDataVec("string", cf.getValfield5());
						support.setDataVec("string", cf.getMndfield5());
						support.setDataVec("string", cf.getVsbfield5());
						
//						Field 6 Values
						support.setDataVec("string", cf.getField6());
						support.setDataVec("string", cf.getCmbfield6());
						support.setDataVec("string", cf.getValfield6());
						support.setDataVec("string", cf.getMndfield6());
						support.setDataVec("string", cf.getVsbfield6());
						
//						Field 7 Values
						support.setDataVec("string", cf.getField7());
						support.setDataVec("string", cf.getCmbfield7());
						support.setDataVec("string", cf.getValfield7());
						support.setDataVec("string", cf.getMndfield7());
						support.setDataVec("string", cf.getVsbfield7());
						
//						Field 8 Values
						support.setDataVec("string", cf.getField8());
						support.setDataVec("string", cf.getCmbfield8());
						support.setDataVec("string", cf.getValfield8());
						support.setDataVec("string", cf.getMndfield8());
						support.setDataVec("string", cf.getVsbfield8());
						
//						Field 9 Values
						support.setDataVec("string", cf.getField9());
						support.setDataVec("string", cf.getCmbfield9());
						support.setDataVec("string", cf.getValfield9());
						support.setDataVec("string", cf.getMndfield9());
						support.setDataVec("string", cf.getVsbfield9());
						
//						Field 10 Values
						support.setDataVec("string", cf.getField10());
						support.setDataVec("string", cf.getCmbfield10());
						support.setDataVec("string", cf.getValfield10());
						support.setDataVec("string", cf.getMndfield10());
						support.setDataVec("string", cf.getVsbfield10());
						
//						Field 11 Values
						support.setDataVec("string", cf.getField11());
						support.setDataVec("string", cf.getCmbfield11());
						support.setDataVec("string", cf.getValfield11());
						support.setDataVec("string", cf.getMndfield11());
						support.setDataVec("string", cf.getVsbfield11());
						
//						Field 12 Values
						support.setDataVec("string", cf.getField12());
						support.setDataVec("string", cf.getCmbfield12());
						support.setDataVec("string", cf.getValfield12());
						support.setDataVec("string", cf.getMndfield12());
						support.setDataVec("string", cf.getVsbfield12());
						
//						Field 13 Values
						support.setDataVec("string", cf.getField13());
						support.setDataVec("string", cf.getCmbfield13());
						support.setDataVec("string", cf.getValfield13());
						support.setDataVec("string", cf.getMndfield13());
						support.setDataVec("string", cf.getVsbfield13());
						
//						Field 14 Values
						support.setDataVec("string", cf.getField14());
						support.setDataVec("string", cf.getCmbfield14());
						support.setDataVec("string", cf.getValfield14());
						support.setDataVec("string", cf.getMndfield14());
						support.setDataVec("string", cf.getVsbfield14());
						
//						Field 15 Values
						support.setDataVec("string", cf.getField15());
						support.setDataVec("string", cf.getCmbfield15());
						support.setDataVec("string", cf.getValfield15());
						support.setDataVec("string", cf.getMndfield15());
						support.setDataVec("string", cf.getVsbfield15());
						
//						Field 16 Values
						support.setDataVec("string", cf.getField16());
						support.setDataVec("string", cf.getCmbfield16());
						support.setDataVec("string", cf.getValfield16());
						support.setDataVec("string", cf.getMndfield16());
						support.setDataVec("string", cf.getVsbfield16());
						
//						Field 17 Values
						support.setDataVec("string", cf.getField17());
						support.setDataVec("string", cf.getCmbfield17());
						support.setDataVec("string", cf.getValfield17());
						support.setDataVec("string", cf.getMndfield17());
						support.setDataVec("string", cf.getVsbfield17());
						
//						Field 18 Values
						support.setDataVec("string", cf.getField18());
						support.setDataVec("string", cf.getCmbfield18());
						support.setDataVec("string", cf.getValfield18());
						support.setDataVec("string", cf.getMndfield18());
						support.setDataVec("string", cf.getVsbfield18());
						
//						Field 19 Values
						support.setDataVec("string", cf.getField19());
						support.setDataVec("string", cf.getCmbfield19());
						support.setDataVec("string", cf.getValfield19());
						support.setDataVec("string", cf.getMndfield19());
						support.setDataVec("string", cf.getVsbfield19());
						
//						Field 20 Values
						support.setDataVec("string", cf.getField20());
						support.setDataVec("string", cf.getCmbfield20());
						support.setDataVec("string", cf.getValfield20());
						support.setDataVec("string", cf.getMndfield20());
						support.setDataVec("string", cf.getVsbfield20());
						
//						Field 21 Values
						support.setDataVec("string", cf.getField21());
						support.setDataVec("string", cf.getCmbfield21());
						support.setDataVec("string", cf.getValfield21());
						support.setDataVec("string", cf.getMndfield21());
						support.setDataVec("string", cf.getVsbfield21());
						
//						Field 2 Values
						support.setDataVec("string", cf.getField22());
						support.setDataVec("string", cf.getCmbfield22());
						support.setDataVec("string", cf.getValfield22());
						support.setDataVec("string", cf.getMndfield22());
						support.setDataVec("string", cf.getVsbfield22());
						
//						Field 23 Values
						support.setDataVec("string", cf.getField23());
						support.setDataVec("string", cf.getCmbfield23());
						support.setDataVec("string", cf.getValfield23());
						support.setDataVec("string", cf.getMndfield23());
						support.setDataVec("string", cf.getVsbfield23());
						
//						Field 24 Values
						support.setDataVec("string", cf.getField24());
						support.setDataVec("string", cf.getCmbfield24());
						support.setDataVec("string", cf.getValfield24());
						support.setDataVec("string", cf.getMndfield24());
						support.setDataVec("string", cf.getVsbfield24());
						
//						Field 25 Values
						support.setDataVec("string", cf.getField25());
						support.setDataVec("string", cf.getCmbfield25());
						support.setDataVec("string", cf.getValfield25());
						support.setDataVec("string", cf.getMndfield25());
						support.setDataVec("string", cf.getVsbfield25());
						
//						Field 26 Values
						support.setDataVec("string", cf.getField26());
						support.setDataVec("string", cf.getCmbfield26());
						support.setDataVec("string", cf.getValfield26());
						support.setDataVec("string", cf.getMndfield26());
						support.setDataVec("string", cf.getVsbfield26());
						
//						Field 27 Values
						support.setDataVec("string", cf.getField27());
						support.setDataVec("string", cf.getCmbfield27());
						support.setDataVec("string", cf.getValfield27());
						support.setDataVec("string", cf.getMndfield27());
						support.setDataVec("string", cf.getVsbfield27());
						
//						Field 28 Values
						support.setDataVec("string", cf.getField28());
						support.setDataVec("string", cf.getCmbfield28());
						support.setDataVec("string", cf.getValfield28());
						support.setDataVec("string", cf.getMndfield28());
						support.setDataVec("string", cf.getVsbfield28());
						
//						Field 29 Values
						support.setDataVec("string", cf.getField29());
						support.setDataVec("string", cf.getCmbfield29());
						support.setDataVec("string", cf.getValfield29());
						support.setDataVec("string", cf.getMndfield29());
						support.setDataVec("string", cf.getVsbfield29());
						
//						Field 30 Values
						support.setDataVec("string", cf.getField30());
						support.setDataVec("string", cf.getCmbfield30());
						support.setDataVec("string", cf.getValfield30());
						support.setDataVec("string", cf.getMndfield30());
						support.setDataVec("string", cf.getVsbfield30());
						
//						Field 31 Values
						support.setDataVec("string", cf.getField31());
						support.setDataVec("string", cf.getCmbfield31());
						support.setDataVec("string", cf.getValfield31());
						support.setDataVec("string", cf.getMndfield31());
						support.setDataVec("string", cf.getVsbfield31());
						
//						Field 32 Values
						support.setDataVec("string", cf.getField32());
						support.setDataVec("string", cf.getCmbfield32());
						support.setDataVec("string", cf.getValfield32());
						support.setDataVec("string", cf.getMndfield32());
						support.setDataVec("string", cf.getVsbfield32());
						
//						Field 33 Values
						support.setDataVec("string", cf.getField33());
						support.setDataVec("string", cf.getCmbfield33());
						support.setDataVec("string", cf.getValfield33());
						support.setDataVec("string", cf.getMndfield33());
						support.setDataVec("string", cf.getVsbfield33());
						
//						Field 34 Values
						support.setDataVec("string", cf.getField34());
						support.setDataVec("string", cf.getCmbfield34());
						support.setDataVec("string", cf.getValfield34());
						support.setDataVec("string", cf.getMndfield34());
						support.setDataVec("string", cf.getVsbfield34());
						
//						Field 35 Values
						support.setDataVec("string", cf.getField35());
						support.setDataVec("string", cf.getCmbfield35());
						support.setDataVec("string", cf.getValfield35());
						support.setDataVec("string", cf.getMndfield35());
						support.setDataVec("string", cf.getVsbfield35());
						
						support.setDataVec("int", String.valueOf(numCompId));
						support.setDataVec("int", String.valueOf(numCatId));
						
						//////////////System.out.println("Category from Form "+cf.getCmbcat());
						if(blnStatus==false)
						{
							MasterUtility mu = new MasterUtility();
														
							String strQuery = "Insert into complaint_mapping (";
							for(int i=1; i<=35; i++)
							{
								strQuery += "field"+i;
								strQuery += ", field"+i+"_type";
								strQuery += ", field"+i+"_value";
								strQuery += ", field"+i+"_check";
								strQuery += ", field"+i+"_visible,";
							}
							strQuery += "companyid, categoryid) Values(";
							for(int j=1; j<=177; j++)
							{
								strQuery += "?,";
								if(j==177)
								{
									strQuery = strQuery.substring(0, strQuery.lastIndexOf(","));
								}
								//////////////System.out.println(strQuery);
							}
							strQuery += ")";
							strResult = mu.setData(ds, strQuery, support);
							//////////////System.out.println(strQuery);
						}
						else
						{
							MasterUtility mu = new MasterUtility();
							//Support support = new Support();
							String strQuery = "Update complaint_mapping Set ";
							
							for(int i=1; i<=35; i++)
							{
								strQuery += "field"+i+" = ?";
								strQuery += ", field"+i+"_type = ?";
								strQuery += ", field"+i+"_value = ?";
								strQuery += ", field"+i+"_check = ?";
								strQuery += ", field"+i+"_visible = ?, ";
								if(i==35)
								{
									strQuery = strQuery.substring(0, strQuery.lastIndexOf(","));
								}
							}
							strQuery += " Where companyid = ? And categoryid = ?";
							
							strResult = mu.setData(ds, strQuery, support);
							//////////////System.out.println(strQuery);
						}
						
						
						return strResult;
					}
					/**
					 * 
					 * @param ds
					 * @param numCompId
					 * @param numCatId
					 * @return
					 */
					public Vector getComplaintUnnamedMap(DataSource ds, int numCompId, int numCatId)
					{
						Vector resultVec = new Vector();
//						Get Unnamed Mapping Id here.
						boolean blnStatus = getComplaintUnnamedMap(numCompId, numCatId, ds);
						//Check if Unnamed Mapping id greter then 0.
						if(blnStatus==true)
						{
							MasterUtility mu = new MasterUtility();
							String strQuery = "Select * from complaint_mapping Where companyid = "+numCompId+" And categoryid = "+numCatId;
							Support support = new Support();
							for(int i=1; i<=35; i++)
							{
								support.setFieldVec("string", "field"+i);
								support.setFieldVec("string", "field"+i+"_type");
								support.setFieldVec("string", "field"+i+"_value");
								support.setFieldVec("string", "field"+i+"_check");
								support.setFieldVec("string", "field"+i+"_visible");
							}
							resultVec = mu.getDetail(ds, strQuery, support);
							//////////////System.out.println("Result Vector "+resultVec);
						}
						else
						{
							
							
						}
						return resultVec;
					}
					
					public boolean getSubCatStatus(int numCompId, int numCatId, String strPageId, String strValue, DataSource ds)
					{
						boolean blnResult = false;
						MasterUtility mu = new MasterUtility();
						String strQuery = "";
						//Sub Category/ Field1
						if(strPageId.equalsIgnoreCase("click1"))
						{
							strQuery = "Select count(*) from subcategory where companyid="+numCompId+" and subcat_name='"+strValue+"' and cat_id="+numCatId;
						}
//						Sub Category1/ Field2
						if(strPageId.equalsIgnoreCase("click2"))
						{
							strQuery = "Select count(*) from subcategory1 where companyid="+numCompId+" and subcat1_name='"+strValue+"' and subcat_id="+numCatId;
						}
//						Sub Category2/ Field3
						if(strPageId.equalsIgnoreCase("click3"))
						{
							strQuery = "Select count(*) from subcategory2 where companyid="+numCompId+" and subcat2_name='"+strValue+"' and subcat1_id="+numCatId;
						}
//						Sub Category3/ Field4
						if(strPageId.equalsIgnoreCase("click4"))
						{
							strQuery = "Select count(*) from subcategory3 where companyid="+numCompId+" and subcat3_name='"+strValue+"' and subcat2_id="+numCatId;
						}
//						Sub Category4/ Field5
						if(strPageId.equalsIgnoreCase("click5"))
						{
							strQuery = "Select count(*) from subcategory4 where companyid="+numCompId+" and subcat4_name='"+strValue+"' and subcat3_id="+numCatId;
						}
//						Sub Category5/ Field6
						if(strPageId.equalsIgnoreCase("click6"))
						{
							strQuery = "Select count(*) from subcategory5 where companyid="+numCompId+" and subcat5_name='"+strValue+"' and subcat4_id="+numCatId;
						}
//						Sub Category6/ Field7
						if(strPageId.equalsIgnoreCase("click7"))
						{
							strQuery = "Select count(*) from subcategory6 where companyid="+numCompId+" and subcat6_name='"+strValue+"' and subcat5_id="+numCatId;
						}
//						Sub Category7/ Field8
						if(strPageId.equalsIgnoreCase("click8"))
						{
							strQuery = "Select count(*) from subcategory7 where companyid="+numCompId+" and subcat7_name='"+strValue+"' and subcat6_id="+numCatId;
						}
//						Sub Category8/ Field9
						if(strPageId.equalsIgnoreCase("click9"))
						{
							strQuery = "Select count(*) from subcategory8 where companyid="+numCompId+" and subcat8_name='"+strValue+"' and subcat7_id="+numCatId;
						}
//						Sub Category9/ Field10
						if(strPageId.equalsIgnoreCase("click10"))
						{
							strQuery = "Select count(*) from subcategory9 where companyid="+numCompId+" and subcat9_name='"+strValue+"' and subcat8_id="+numCatId;
						}
						
						int numRowCount = mu.getCount(ds, strQuery);
						
						blnResult = (numRowCount>0) ? true : false;
						return blnResult;
					}
					
					public String setSubCategory(ManipulatesubcatForm mscf, DataSource ds, int numCompId)
					{
						String strResult = "failure";
						int numCatid = Integer.parseInt(mscf.getSubcatid().trim());
						String strValue = mscf.getSubcatvalue().trim();
						String strPageId = mscf.getCatclick().trim();
						boolean blnStatus = getSubCatStatus(numCompId, numCatid, strPageId, strValue, ds);
						if(blnStatus!=true)
						{
							String strQuery = "Insert into ";
							if(strPageId.equalsIgnoreCase("click1"))
							{
								strQuery += "subcategory (companyid, subcat_name, cat_id";
							}
//							Sub Category1/ Field2
							if(strPageId.equalsIgnoreCase("click2"))
							{
								strQuery += "subcategory1 (companyid, subcat1_name, subcat_id";
							}
//							Sub Category2/ Field3
							if(strPageId.equalsIgnoreCase("click3"))
							{
								strQuery += "subcategory2 (companyid, subcat2_name, subcat1_id";
							}
//							Sub Category3/ Field4
							if(strPageId.equalsIgnoreCase("click4"))
							{
								strQuery += "subcategory3 (companyid, subcat3_name, subcat2_id";
							}
//							Sub Category4/ Field5
							if(strPageId.equalsIgnoreCase("click5"))
							{
								strQuery += "subcategory4 (companyid, subcat4_name, subcat3_id";
							}
//							Sub Category5/ Field6
							if(strPageId.equalsIgnoreCase("click6"))
							{
								strQuery += "subcategory5 (companyid, subcat5_name, subcat4_id";
							}
//							Sub Category6/ Field7
							if(strPageId.equalsIgnoreCase("click7"))
							{
								strQuery += "subcategory6 (companyid, subcat6_name, subcat5_id";
							}
//							Sub Category7/ Field8
							if(strPageId.equalsIgnoreCase("click8"))
							{
								strQuery += "subcategory7 (companyid, subcat7_name, subcat6_id";
							}
//							Sub Category8/ Field9
							if(strPageId.equalsIgnoreCase("click9"))
							{
								strQuery += "subcategory8 (companyid, subcat8_name, subcat7_id";
							}
//							Sub Category9/ Field10
							if(strPageId.equalsIgnoreCase("click10"))
							{
								strQuery += "subcategory9 (companyid, subcat9_name, subcat8_id";
							}
							strQuery +=") Values(?, ?, ?)";
							//////////////System.out.println("Category Id >> "+numCatid);
							Support support = new Support();
							support.setDataVec("int", String.valueOf(numCompId));
							support.setDataVec("string", strValue);
							support.setDataVec("int", String.valueOf(numCatid));
							MasterUtility mu = new MasterUtility();
							strResult = mu.setData(ds, strQuery, support);
							
						}
						else
						{
							strResult = "Already Exist";
						}
						
						return strResult;
					}
					
					public String editSubCategory(ManipulatesubcatForm mscf, DataSource ds, int numCompId)
					{
						String strResult = "failure";
						int numCatid = Integer.parseInt(mscf.getSubcatid().trim());
						String strValue = mscf.getSubcatvalue().trim();
						String strPageId = mscf.getCatclick().trim();
						String strQuery = "Update ";
						if(strPageId.equalsIgnoreCase("click1"))
						{
							strQuery += "subcategory set subcat_name=? where companyid=? and subcat_id=?";
						}
//						Sub Category1/ Field2
						if(strPageId.equalsIgnoreCase("click2"))
						{
							strQuery += "subcategory1 set subcat1_name=? where companyid=? and subcat1_id=?";
						}
//						Sub Category2/ Field3
						if(strPageId.equalsIgnoreCase("click3"))
						{
							strQuery += "subcategory2 set subcat2_name=? where companyid=? and subcat2_id=?";
						}
//						Sub Category3/ Field4
						if(strPageId.equalsIgnoreCase("click4"))
						{
							strQuery += "subcategory3 set subcat3_name=? where companyid=? and subcat3_id=?";
						}
//						Sub Category4/ Field5
						if(strPageId.equalsIgnoreCase("click5"))
						{
							strQuery += "subcategory4 set subcat4_name=? where companyid=? and subcat4_id=?";
						}
//						Sub Category5/ Field6
						if(strPageId.equalsIgnoreCase("click6"))
						{
							strQuery += "subcategory5 set subcat5_name=? where companyid=? and subcat5_id=?";
						}
//						Sub Category6/ Field7
						if(strPageId.equalsIgnoreCase("click7"))
						{
							strQuery += "subcategory6 set subcat6_name=? where companyid=? and subcat6_id=?";
						}
//						Sub Category7/ Field8
						if(strPageId.equalsIgnoreCase("click8"))
						{
							strQuery += "subcategory7 set subcat7_name=? where companyid=? and subcat7_id=?";
						}
//						Sub Category8/ Field9
						if(strPageId.equalsIgnoreCase("click9"))
						{
							strQuery += "subcategory8 set subcat8_name=? where companyid=? and subcat8_id=?";
						}
//						Sub Category9/ Field10
						if(strPageId.equalsIgnoreCase("click10"))
						{
							strQuery += "subcategory9 set subcat9_name=? where companyid=? and subcat9_id=?";
						}
						
						Support support = new Support();
						support.setDataVec("string", strValue);
						support.setDataVec("int", String.valueOf(numCompId));
						support.setDataVec("int", String.valueOf(numCatid));
						MasterUtility mu = new MasterUtility();
						strResult = mu.setData(ds, strQuery, support);
							
						return strResult;
					}
					public void allRelSubCat(DataSource ds, int numSCatid, String strPageId)
					{
						MasterUtility mu = new MasterUtility();
						
						if(strPageId.equalsIgnoreCase("click1"))
						{
							String strQuery = "Select subcat1_id from subcategory1 where subcat_id="+numSCatid; 
							Vector statusVec = mu.getIds(ds, strQuery);
							if(statusVec.size()>0)
							{
								String strStatus = "";
								for(int i=0; i<statusVec.size(); i++)
								{
									strStatus = strStatus.equalsIgnoreCase("") ? statusVec.elementAt(i).toString().trim() : strStatus+","+statusVec.elementAt(i).toString().trim();
								}
								String strQuery1 = "Select subcat2_id from subcategory2 where subcat1_id in("+strStatus+")";
								Vector statusVec1 = mu.getIds(ds, strQuery1);
								//to delete related values
								String strDelQuery = "delete from subcategory1 where subcat1_id in("+strStatus+")";
								String strResult = mu.deleteData(ds, strDelQuery);
								////////////System.out.println("Subcategory 1 Result "+strResult);
								if(statusVec1.size()>0)
								{
									String strStatus1 = "";
									for(int i=0; i<statusVec1.size(); i++)
									{
										strStatus1 = strStatus1.equalsIgnoreCase("") ? statusVec1.elementAt(i).toString().trim() : strStatus1+","+statusVec1.elementAt(i).toString().trim();
									}
									String strQuery2 = "Select subcat3_id from subcategory3 where subcat2_id in("+strStatus1+")";
									Vector statusVec2 = mu.getIds(ds, strQuery2);
	//								to delete related values
									String strDelQuery1 = "delete from subcategory2 where subcat2_id in("+strStatus1+")";
									String strResult1 = mu.deleteData(ds, strDelQuery1);
									////////////System.out.println("Subcategory 2 Result "+strResult1);
									if(statusVec2.size()>0)
									{
										String strStatus2 = "";
										for(int i=0; i<statusVec2.size(); i++)
										{
											strStatus2 = strStatus2.equalsIgnoreCase("") ? statusVec2.elementAt(i).toString().trim() : strStatus2+","+statusVec2.elementAt(i).toString().trim();
										}
										String strQuery3 = "Select subcat4_id from subcategory4 where subcat3_id in("+strStatus2+")";
										Vector statusVec3 = mu.getIds(ds, strQuery3);
										//to delete related values
										String strDelQuery2 = "delete from subcategory3 where subcat3_id in("+strStatus2+")";
										String strResult2 = mu.deleteData(ds, strDelQuery2);
										////////////System.out.println("Subcategory 3 Result "+strResult2);
										if(statusVec3.size()>0)
										{
											String strStatus3 = "";
											for(int i=0; i<statusVec3.size(); i++)
											{
												strStatus3 = strStatus3.equalsIgnoreCase("") ? statusVec3.elementAt(i).toString().trim() : strStatus3+","+statusVec3.elementAt(i).toString().trim();
											}
											String strQuery4 = "Select subcat5_id from subcategory5 where subcat4_id in("+strStatus3+")";
											Vector statusVec4 = mu.getIds(ds, strQuery4);
	//										to delete related values
											String strDelQuery3 = "delete from subcategory4 where subcat4_id in("+strStatus3+")";
											String strResult3 = mu.deleteData(ds, strDelQuery3);
											////////////System.out.println("Subcategory 4 Result "+strResult3);
											if(statusVec4.size()>0)
											{
												String strStatus4 = "";
												for(int i=0; i<statusVec4.size(); i++)
												{
													strStatus4 = strStatus4.equalsIgnoreCase("") ? statusVec4.elementAt(i).toString().trim() : strStatus4+","+statusVec4.elementAt(i).toString().trim();
												}
												String strQuery5 = "Select subcat6_id from subcategory6 where subcat5_id in("+strStatus4+")";
												Vector statusVec5 = mu.getIds(ds, strQuery5);
	//											to delete related values
												String strDelQuery4 = "delete from subcategory5 where subcat5_id in("+strStatus4+")";
												String strResult4 = mu.deleteData(ds, strDelQuery4);
												////////////System.out.println("Subcategory 5 Result "+strResult4);
												if(statusVec5.size()>0)
												{
													String strStatus5 = "";
													for(int i=0; i<statusVec5.size(); i++)
													{
														strStatus5 = strStatus5.equalsIgnoreCase("") ? statusVec5.elementAt(i).toString().trim() : strStatus5+","+statusVec5.elementAt(i).toString().trim();
													}
													String strQuery6 = "Select subcat7_id from subcategory7 where subcat6_id in("+strStatus5+")";
													Vector statusVec6 = mu.getIds(ds, strQuery6);
	//												to delete related values
													String strDelQuery5 = "delete from subcategory6 where subcat6_id in("+strStatus5+")";
													String strResult5 = mu.deleteData(ds, strDelQuery5);
													////////////System.out.println("Subcategory 6 Result "+strResult5);
													if(statusVec6.size()>0)
													{
														String strStatus6 = "";
														for(int i=0; i<statusVec6.size(); i++)
														{
															strStatus6 = strStatus6.equalsIgnoreCase("") ? statusVec6.elementAt(i).toString().trim() : strStatus6+","+statusVec6.elementAt(i).toString().trim();
														}
														String strQuery7 = "Select subcat8_id from subcategory8 where subcat7_id in("+strStatus6+")";
														Vector statusVec7 = mu.getIds(ds, strQuery7);
	//													to delete related values
														String strDelQuery6 = "delete from subcategory7 where subcat7_id in("+strStatus6+")";
														String strResult6 = mu.deleteData(ds, strDelQuery6);
														////////////System.out.println("Subcategory 7 Result "+strResult6);
														if(statusVec7.size()>0)
														{
															String strStatus7 = "";
															for(int i=0; i<statusVec7.size(); i++)
															{
																strStatus7 = strStatus7.equalsIgnoreCase("") ? statusVec7.elementAt(i).toString().trim() : strStatus7+","+statusVec7.elementAt(i).toString().trim();
															}
															String strQuery8 = "Select subcat9_id from subcategory9 where subcat8_id in("+strStatus7+")";
															Vector statusVec8 = mu.getIds(ds, strQuery8);
	//														to delete related values
															String strDelQuery7 = "delete from subcategory8 where subcat8_id in("+strStatus7+")";
															String strResult7 = mu.deleteData(ds, strDelQuery7);
															////////////System.out.println("Subcategory 8 Result "+strResult7);
															if(statusVec8.size()>0)
															{
																String strStatus8 = "";
																for(int i=0; i<statusVec8.size(); i++)
																{
																	strStatus8 = strStatus8.equalsIgnoreCase("") ? statusVec8.elementAt(i).toString().trim() : strStatus8+","+statusVec8.elementAt(i).toString().trim();
																}
	//															to delete related values
																String strDelQuery8 = "delete from subcategory9 where subcat9_id in("+strStatus8+")";
																String strResult8 = mu.deleteData(ds, strDelQuery8);
																////////////System.out.println("Subcategory 8 Result "+strResult8);
															}
														}
													}
												}
											}
										}
									}
								}
							}
							
						}
						if(strPageId.equalsIgnoreCase("click2"))
						{
							String strQuery1 = "Select subcat2_id from subcategory2 where subcat1_id ="+numSCatid;
							Vector statusVec1 = mu.getIds(ds, strQuery1);
							if(statusVec1.size()>0)
							{
								String strStatus1 = "";
								for(int i=0; i<statusVec1.size(); i++)
								{
									strStatus1 = strStatus1.equalsIgnoreCase("") ? statusVec1.elementAt(i).toString().trim() : strStatus1+","+statusVec1.elementAt(i).toString().trim();
								}
								String strQuery2 = "Select subcat3_id from subcategory3 where subcat2_id in("+strStatus1+")";
								Vector statusVec2 = mu.getIds(ds, strQuery2);
//								to delete related values
								String strDelQuery1 = "delete from subcategory2 where subcat2_id in("+strStatus1+")";
								String strResult1 = mu.deleteData(ds, strDelQuery1);
								////////////System.out.println("Subcategory 2 Result "+strResult1);
								if(statusVec2.size()>0)
								{
									String strStatus2 = "";
									for(int i=0; i<statusVec2.size(); i++)
									{
										strStatus2 = strStatus2.equalsIgnoreCase("") ? statusVec2.elementAt(i).toString().trim() : strStatus2+","+statusVec2.elementAt(i).toString().trim();
									}
									String strQuery3 = "Select subcat4_id from subcategory4 where subcat3_id in("+strStatus2+")";
									Vector statusVec3 = mu.getIds(ds, strQuery3);
									//to delete related values
									String strDelQuery2 = "delete from subcategory3 where subcat3_id in("+strStatus2+")";
									String strResult2 = mu.deleteData(ds, strDelQuery2);
									////////////System.out.println("Subcategory 3 Result "+strResult2);
									if(statusVec3.size()>0)
									{
										String strStatus3 = "";
										for(int i=0; i<statusVec3.size(); i++)
										{
											strStatus3 = strStatus3.equalsIgnoreCase("") ? statusVec3.elementAt(i).toString().trim() : strStatus3+","+statusVec3.elementAt(i).toString().trim();
										}
										String strQuery4 = "Select subcat5_id from subcategory5 where subcat4_id in("+strStatus3+")";
										Vector statusVec4 = mu.getIds(ds, strQuery4);
//										to delete related values
										String strDelQuery3 = "delete from subcategory4 where subcat4_id in("+strStatus3+")";
										String strResult3 = mu.deleteData(ds, strDelQuery3);
										////////////System.out.println("Subcategory 4 Result "+strResult3);
										if(statusVec4.size()>0)
										{
											String strStatus4 = "";
											for(int i=0; i<statusVec4.size(); i++)
											{
												strStatus4 = strStatus4.equalsIgnoreCase("") ? statusVec4.elementAt(i).toString().trim() : strStatus4+","+statusVec4.elementAt(i).toString().trim();
											}
											String strQuery5 = "Select subcat6_id from subcategory6 where subcat5_id in("+strStatus4+")";
											Vector statusVec5 = mu.getIds(ds, strQuery5);
//											to delete related values
											String strDelQuery4 = "delete from subcategory5 where subcat5_id in("+strStatus4+")";
											String strResult4 = mu.deleteData(ds, strDelQuery4);
											////////////System.out.println("Subcategory 5 Result "+strResult4);
											if(statusVec5.size()>0)
											{
												String strStatus5 = "";
												for(int i=0; i<statusVec5.size(); i++)
												{
													strStatus5 = strStatus5.equalsIgnoreCase("") ? statusVec5.elementAt(i).toString().trim() : strStatus5+","+statusVec5.elementAt(i).toString().trim();
												}
												String strQuery6 = "Select subcat7_id from subcategory7 where subcat6_id in("+strStatus5+")";
												Vector statusVec6 = mu.getIds(ds, strQuery6);
//												to delete related values
												String strDelQuery5 = "delete from subcategory6 where subcat6_id in("+strStatus5+")";
												String strResult5 = mu.deleteData(ds, strDelQuery5);
												////////////System.out.println("Subcategory 6 Result "+strResult5);
												if(statusVec6.size()>0)
												{
													String strStatus6 = "";
													for(int i=0; i<statusVec6.size(); i++)
													{
														strStatus6 = strStatus6.equalsIgnoreCase("") ? statusVec6.elementAt(i).toString().trim() : strStatus6+","+statusVec6.elementAt(i).toString().trim();
													}
													String strQuery7 = "Select subcat8_id from subcategory8 where subcat7_id in("+strStatus6+")";
													Vector statusVec7 = mu.getIds(ds, strQuery7);
//													to delete related values
													String strDelQuery6 = "delete from subcategory7 where subcat7_id in("+strStatus6+")";
													String strResult6 = mu.deleteData(ds, strDelQuery6);
													////////////System.out.println("Subcategory 7 Result "+strResult6);
													if(statusVec7.size()>0)
													{
														String strStatus7 = "";
														for(int i=0; i<statusVec7.size(); i++)
														{
															strStatus7 = strStatus7.equalsIgnoreCase("") ? statusVec7.elementAt(i).toString().trim() : strStatus7+","+statusVec7.elementAt(i).toString().trim();
														}
														String strQuery8 = "Select subcat9_id from subcategory9 where subcat8_id in("+strStatus7+")";
														Vector statusVec8 = mu.getIds(ds, strQuery8);
//														to delete related values
														String strDelQuery7 = "delete from subcategory8 where subcat8_id in("+strStatus7+")";
														String strResult7 = mu.deleteData(ds, strDelQuery7);
														////////////System.out.println("Subcategory 8 Result "+strResult7);
														if(statusVec8.size()>0)
														{
															String strStatus8 = "";
															for(int i=0; i<statusVec8.size(); i++)
															{
																strStatus8 = strStatus8.equalsIgnoreCase("") ? statusVec8.elementAt(i).toString().trim() : strStatus8+","+statusVec8.elementAt(i).toString().trim();
															}
//															to delete related values
															String strDelQuery8 = "delete from subcategory9 where subcat9_id in("+strStatus8+")";
															String strResult8 = mu.deleteData(ds, strDelQuery8);
															////////////System.out.println("Subcategory 8 Result "+strResult8);
														}
													}
												}
											}
										}
									}
								}
							}
							
						}
						if(strPageId.equalsIgnoreCase("click3"))
						{
							String strQuery2 = "Select subcat3_id from subcategory3 where subcat2_id ="+numSCatid;
							Vector statusVec2 = mu.getIds(ds, strQuery2);
							if(statusVec2.size()>0)
							{
								String strStatus2 = "";
								for(int i=0; i<statusVec2.size(); i++)
								{
									strStatus2 = strStatus2.equalsIgnoreCase("") ? statusVec2.elementAt(i).toString().trim() : strStatus2+","+statusVec2.elementAt(i).toString().trim();
								}
								String strQuery3 = "Select subcat4_id from subcategory4 where subcat3_id in("+strStatus2+")";
								Vector statusVec3 = mu.getIds(ds, strQuery3);
								//to delete related values
								String strDelQuery2 = "delete from subcategory3 where subcat3_id in("+strStatus2+")";
								String strResult2 = mu.deleteData(ds, strDelQuery2);
								////////////System.out.println("Subcategory 3 Result "+strResult2);
								if(statusVec3.size()>0)
								{
									String strStatus3 = "";
									for(int i=0; i<statusVec3.size(); i++)
									{
										strStatus3 = strStatus3.equalsIgnoreCase("") ? statusVec3.elementAt(i).toString().trim() : strStatus3+","+statusVec3.elementAt(i).toString().trim();
									}
									String strQuery4 = "Select subcat5_id from subcategory5 where subcat4_id in("+strStatus3+")";
									Vector statusVec4 = mu.getIds(ds, strQuery4);
//									to delete related values
									String strDelQuery3 = "delete from subcategory4 where subcat4_id in("+strStatus3+")";
									String strResult3 = mu.deleteData(ds, strDelQuery3);
									////////////System.out.println("Subcategory 4 Result "+strResult3);
									if(statusVec4.size()>0)
									{
										String strStatus4 = "";
										for(int i=0; i<statusVec4.size(); i++)
										{
											strStatus4 = strStatus4.equalsIgnoreCase("") ? statusVec4.elementAt(i).toString().trim() : strStatus4+","+statusVec4.elementAt(i).toString().trim();
										}
										String strQuery5 = "Select subcat6_id from subcategory6 where subcat5_id in("+strStatus4+")";
										Vector statusVec5 = mu.getIds(ds, strQuery5);
//										to delete related values
										String strDelQuery4 = "delete from subcategory5 where subcat5_id in("+strStatus4+")";
										String strResult4 = mu.deleteData(ds, strDelQuery4);
										////////////System.out.println("Subcategory 5 Result "+strResult4);
										if(statusVec5.size()>0)
										{
											String strStatus5 = "";
											for(int i=0; i<statusVec5.size(); i++)
											{
												strStatus5 = strStatus5.equalsIgnoreCase("") ? statusVec5.elementAt(i).toString().trim() : strStatus5+","+statusVec5.elementAt(i).toString().trim();
											}
											String strQuery6 = "Select subcat7_id from subcategory7 where subcat6_id in("+strStatus5+")";
											Vector statusVec6 = mu.getIds(ds, strQuery6);
//											to delete related values
											String strDelQuery5 = "delete from subcategory6 where subcat6_id in("+strStatus5+")";
											String strResult5 = mu.deleteData(ds, strDelQuery5);
											////////////System.out.println("Subcategory 6 Result "+strResult5);
											if(statusVec6.size()>0)
											{
												String strStatus6 = "";
												for(int i=0; i<statusVec6.size(); i++)
												{
													strStatus6 = strStatus6.equalsIgnoreCase("") ? statusVec6.elementAt(i).toString().trim() : strStatus6+","+statusVec6.elementAt(i).toString().trim();
												}
												String strQuery7 = "Select subcat8_id from subcategory8 where subcat7_id in("+strStatus6+")";
												Vector statusVec7 = mu.getIds(ds, strQuery7);
//												to delete related values
												String strDelQuery6 = "delete from subcategory7 where subcat7_id in("+strStatus6+")";
												String strResult6 = mu.deleteData(ds, strDelQuery6);
												////////////System.out.println("Subcategory 7 Result "+strResult6);
												if(statusVec7.size()>0)
												{
													String strStatus7 = "";
													for(int i=0; i<statusVec7.size(); i++)
													{
														strStatus7 = strStatus7.equalsIgnoreCase("") ? statusVec7.elementAt(i).toString().trim() : strStatus7+","+statusVec7.elementAt(i).toString().trim();
													}
													String strQuery8 = "Select subcat9_id from subcategory9 where subcat8_id in("+strStatus7+")";
													Vector statusVec8 = mu.getIds(ds, strQuery8);
//													to delete related values
													String strDelQuery7 = "delete from subcategory8 where subcat8_id in("+strStatus7+")";
													String strResult7 = mu.deleteData(ds, strDelQuery7);
													////////////System.out.println("Subcategory 8 Result "+strResult7);
													if(statusVec8.size()>0)
													{
														String strStatus8 = "";
														for(int i=0; i<statusVec8.size(); i++)
														{
															strStatus8 = strStatus8.equalsIgnoreCase("") ? statusVec8.elementAt(i).toString().trim() : strStatus8+","+statusVec8.elementAt(i).toString().trim();
														}
//														to delete related values
														String strDelQuery8 = "delete from subcategory9 where subcat9_id in("+strStatus8+")";
														String strResult8 = mu.deleteData(ds, strDelQuery8);
														////////////System.out.println("Subcategory 8 Result "+strResult8);
													}
												}
											}
										}
									}
								}
							}							
						}
						if(strPageId.equalsIgnoreCase("click4"))
						{
							String strQuery3 = "Select subcat4_id from subcategory4 where subcat3_id ="+numSCatid;
							Vector statusVec3 = mu.getIds(ds, strQuery3);
							if(statusVec3.size()>0)
							{
								String strStatus3 = "";
								for(int i=0; i<statusVec3.size(); i++)
								{
									strStatus3 = strStatus3.equalsIgnoreCase("") ? statusVec3.elementAt(i).toString().trim() : strStatus3+","+statusVec3.elementAt(i).toString().trim();
								}
								String strQuery4 = "Select subcat5_id from subcategory5 where subcat4_id in("+strStatus3+")";
								Vector statusVec4 = mu.getIds(ds, strQuery4);
//								to delete related values
								String strDelQuery3 = "delete from subcategory4 where subcat4_id in("+strStatus3+")";
								String strResult3 = mu.deleteData(ds, strDelQuery3);
								////////////System.out.println("Subcategory 4 Result "+strResult3);
								if(statusVec4.size()>0)
								{
									String strStatus4 = "";
									for(int i=0; i<statusVec4.size(); i++)
									{
										strStatus4 = strStatus4.equalsIgnoreCase("") ? statusVec4.elementAt(i).toString().trim() : strStatus4+","+statusVec4.elementAt(i).toString().trim();
									}
									String strQuery5 = "Select subcat6_id from subcategory6 where subcat5_id in("+strStatus4+")";
									Vector statusVec5 = mu.getIds(ds, strQuery5);
//									to delete related values
									String strDelQuery4 = "delete from subcategory5 where subcat5_id in("+strStatus4+")";
									String strResult4 = mu.deleteData(ds, strDelQuery4);
									////////////System.out.println("Subcategory 5 Result "+strResult4);
									if(statusVec5.size()>0)
									{
										String strStatus5 = "";
										for(int i=0; i<statusVec5.size(); i++)
										{
											strStatus5 = strStatus5.equalsIgnoreCase("") ? statusVec5.elementAt(i).toString().trim() : strStatus5+","+statusVec5.elementAt(i).toString().trim();
										}
										String strQuery6 = "Select subcat7_id from subcategory7 where subcat6_id in("+strStatus5+")";
										Vector statusVec6 = mu.getIds(ds, strQuery6);
//										to delete related values
										String strDelQuery5 = "delete from subcategory6 where subcat6_id in("+strStatus5+")";
										String strResult5 = mu.deleteData(ds, strDelQuery5);
										////////////System.out.println("Subcategory 6 Result "+strResult5);
										if(statusVec6.size()>0)
										{
											String strStatus6 = "";
											for(int i=0; i<statusVec6.size(); i++)
											{
												strStatus6 = strStatus6.equalsIgnoreCase("") ? statusVec6.elementAt(i).toString().trim() : strStatus6+","+statusVec6.elementAt(i).toString().trim();
											}
											String strQuery7 = "Select subcat8_id from subcategory8 where subcat7_id in("+strStatus6+")";
											Vector statusVec7 = mu.getIds(ds, strQuery7);
//											to delete related values
											String strDelQuery6 = "delete from subcategory7 where subcat7_id in("+strStatus6+")";
											String strResult6 = mu.deleteData(ds, strDelQuery6);
											////////////System.out.println("Subcategory 7 Result "+strResult6);
											if(statusVec7.size()>0)
											{
												String strStatus7 = "";
												for(int i=0; i<statusVec7.size(); i++)
												{
													strStatus7 = strStatus7.equalsIgnoreCase("") ? statusVec7.elementAt(i).toString().trim() : strStatus7+","+statusVec7.elementAt(i).toString().trim();
												}
												String strQuery8 = "Select subcat9_id from subcategory9 where subcat8_id in("+strStatus7+")";
												Vector statusVec8 = mu.getIds(ds, strQuery8);
//												to delete related values
												String strDelQuery7 = "delete from subcategory8 where subcat8_id in("+strStatus7+")";
												String strResult7 = mu.deleteData(ds, strDelQuery7);
												////////////System.out.println("Subcategory 8 Result "+strResult7);
												if(statusVec8.size()>0)
												{
													String strStatus8 = "";
													for(int i=0; i<statusVec8.size(); i++)
													{
														strStatus8 = strStatus8.equalsIgnoreCase("") ? statusVec8.elementAt(i).toString().trim() : strStatus8+","+statusVec8.elementAt(i).toString().trim();
													}
//													to delete related values
													String strDelQuery8 = "delete from subcategory9 where subcat9_id in("+strStatus8+")";
													String strResult8 = mu.deleteData(ds, strDelQuery8);
													////////////System.out.println("Subcategory 8 Result "+strResult8);
												}
											}
										}
									}
								}
							}							
						}
						
						if(strPageId.equalsIgnoreCase("click5"))
						{
							String strQuery4 = "Select subcat5_id from subcategory5 where subcat4_id ="+numSCatid;
							Vector statusVec4 = mu.getIds(ds, strQuery4);
							if(statusVec4.size()>0)
							{
								String strStatus4 = "";
								for(int i=0; i<statusVec4.size(); i++)
								{
									strStatus4 = strStatus4.equalsIgnoreCase("") ? statusVec4.elementAt(i).toString().trim() : strStatus4+","+statusVec4.elementAt(i).toString().trim();
								}
								String strQuery5 = "Select subcat6_id from subcategory6 where subcat5_id in("+strStatus4+")";
								Vector statusVec5 = mu.getIds(ds, strQuery5);
//								to delete related values
								String strDelQuery4 = "delete from subcategory5 where subcat5_id in("+strStatus4+")";
								String strResult4 = mu.deleteData(ds, strDelQuery4);
								////////////System.out.println("Subcategory 5 Result "+strResult4);
								if(statusVec5.size()>0)
								{
									String strStatus5 = "";
									for(int i=0; i<statusVec5.size(); i++)
									{
										strStatus5 = strStatus5.equalsIgnoreCase("") ? statusVec5.elementAt(i).toString().trim() : strStatus5+","+statusVec5.elementAt(i).toString().trim();
									}
									String strQuery6 = "Select subcat7_id from subcategory7 where subcat6_id in("+strStatus5+")";
									Vector statusVec6 = mu.getIds(ds, strQuery6);
//									to delete related values
									String strDelQuery5 = "delete from subcategory6 where subcat6_id in("+strStatus5+")";
									String strResult5 = mu.deleteData(ds, strDelQuery5);
									////////////System.out.println("Subcategory 6 Result "+strResult5);
									if(statusVec6.size()>0)
									{
										String strStatus6 = "";
										for(int i=0; i<statusVec6.size(); i++)
										{
											strStatus6 = strStatus6.equalsIgnoreCase("") ? statusVec6.elementAt(i).toString().trim() : strStatus6+","+statusVec6.elementAt(i).toString().trim();
										}
										String strQuery7 = "Select subcat8_id from subcategory8 where subcat7_id in("+strStatus6+")";
										Vector statusVec7 = mu.getIds(ds, strQuery7);
//										to delete related values
										String strDelQuery6 = "delete from subcategory7 where subcat7_id in("+strStatus6+")";
										String strResult6 = mu.deleteData(ds, strDelQuery6);
										////////////System.out.println("Subcategory 7 Result "+strResult6);
										if(statusVec7.size()>0)
										{
											String strStatus7 = "";
											for(int i=0; i<statusVec7.size(); i++)
											{
												strStatus7 = strStatus7.equalsIgnoreCase("") ? statusVec7.elementAt(i).toString().trim() : strStatus7+","+statusVec7.elementAt(i).toString().trim();
											}
											String strQuery8 = "Select subcat9_id from subcategory9 where subcat8_id in("+strStatus7+")";
											Vector statusVec8 = mu.getIds(ds, strQuery8);
//											to delete related values
											String strDelQuery7 = "delete from subcategory8 where subcat8_id in("+strStatus7+")";
											String strResult7 = mu.deleteData(ds, strDelQuery7);
											////////////System.out.println("Subcategory 8 Result "+strResult7);
											if(statusVec8.size()>0)
											{
												String strStatus8 = "";
												for(int i=0; i<statusVec8.size(); i++)
												{
													strStatus8 = strStatus8.equalsIgnoreCase("") ? statusVec8.elementAt(i).toString().trim() : strStatus8+","+statusVec8.elementAt(i).toString().trim();
												}
//												to delete related values
												String strDelQuery8 = "delete from subcategory9 where subcat9_id in("+strStatus8+")";
												String strResult8 = mu.deleteData(ds, strDelQuery8);
												////////////System.out.println("Subcategory 8 Result "+strResult8);
											}
										}
									}
								}
							}
						}							
						if(strPageId.equalsIgnoreCase("click6"))
						{
							String strQuery5 = "Select subcat6_id from subcategory6 where subcat5_id ="+numSCatid;
							Vector statusVec5 = mu.getIds(ds, strQuery5);
							if(statusVec5.size()>0)
							{
								String strStatus5 = "";
								for(int i=0; i<statusVec5.size(); i++)
								{
									strStatus5 = strStatus5.equalsIgnoreCase("") ? statusVec5.elementAt(i).toString().trim() : strStatus5+","+statusVec5.elementAt(i).toString().trim();
								}
								String strQuery6 = "Select subcat7_id from subcategory7 where subcat6_id in("+strStatus5+")";
								Vector statusVec6 = mu.getIds(ds, strQuery6);
//								to delete related values
								String strDelQuery5 = "delete from subcategory6 where subcat6_id in("+strStatus5+")";
								String strResult5 = mu.deleteData(ds, strDelQuery5);
								////////////System.out.println("Subcategory 6 Result "+strResult5);
								if(statusVec6.size()>0)
								{
									String strStatus6 = "";
									for(int i=0; i<statusVec6.size(); i++)
									{
										strStatus6 = strStatus6.equalsIgnoreCase("") ? statusVec6.elementAt(i).toString().trim() : strStatus6+","+statusVec6.elementAt(i).toString().trim();
									}
									String strQuery7 = "Select subcat8_id from subcategory8 where subcat7_id in("+strStatus6+")";
									Vector statusVec7 = mu.getIds(ds, strQuery7);
//									to delete related values
									String strDelQuery6 = "delete from subcategory7 where subcat7_id in("+strStatus6+")";
									String strResult6 = mu.deleteData(ds, strDelQuery6);
									////////////System.out.println("Subcategory 7 Result "+strResult6);
									if(statusVec7.size()>0)
									{
										String strStatus7 = "";
										for(int i=0; i<statusVec7.size(); i++)
										{
											strStatus7 = strStatus7.equalsIgnoreCase("") ? statusVec7.elementAt(i).toString().trim() : strStatus7+","+statusVec7.elementAt(i).toString().trim();
										}
										String strQuery8 = "Select subcat9_id from subcategory9 where subcat8_id in("+strStatus7+")";
										Vector statusVec8 = mu.getIds(ds, strQuery8);
//										to delete related values
										String strDelQuery7 = "delete from subcategory8 where subcat8_id in("+strStatus7+")";
										String strResult7 = mu.deleteData(ds, strDelQuery7);
										////////////System.out.println("Subcategory 8 Result "+strResult7);
										if(statusVec8.size()>0)
										{
											String strStatus8 = "";
											for(int i=0; i<statusVec8.size(); i++)
											{
												strStatus8 = strStatus8.equalsIgnoreCase("") ? statusVec8.elementAt(i).toString().trim() : strStatus8+","+statusVec8.elementAt(i).toString().trim();
											}
//											to delete related values
											String strDelQuery8 = "delete from subcategory9 where subcat9_id in("+strStatus8+")";
											String strResult8 = mu.deleteData(ds, strDelQuery8);
											////////////System.out.println("Subcategory 8 Result "+strResult8);
										}
									}
								}
							}
						}
						if(strPageId.equalsIgnoreCase("click7"))
						{
							String strQuery6 = "Select subcat7_id from subcategory7 where subcat6_id ="+numSCatid;
							Vector statusVec6 = mu.getIds(ds, strQuery6);
							if(statusVec6.size()>0)
							{
								String strStatus6 = "";
								for(int i=0; i<statusVec6.size(); i++)
								{
									strStatus6 = strStatus6.equalsIgnoreCase("") ? statusVec6.elementAt(i).toString().trim() : strStatus6+","+statusVec6.elementAt(i).toString().trim();
								}
								String strQuery7 = "Select subcat8_id from subcategory8 where subcat7_id in("+strStatus6+")";
								Vector statusVec7 = mu.getIds(ds, strQuery7);
//								to delete related values
								String strDelQuery6 = "delete from subcategory7 where subcat7_id in("+strStatus6+")";
								String strResult6 = mu.deleteData(ds, strDelQuery6);
								////////////System.out.println("Subcategory 7 Result "+strResult6);
								if(statusVec7.size()>0)
								{
									String strStatus7 = "";
									for(int i=0; i<statusVec7.size(); i++)
									{
										strStatus7 = strStatus7.equalsIgnoreCase("") ? statusVec7.elementAt(i).toString().trim() : strStatus7+","+statusVec7.elementAt(i).toString().trim();
									}
									String strQuery8 = "Select subcat9_id from subcategory9 where subcat8_id in("+strStatus7+")";
									Vector statusVec8 = mu.getIds(ds, strQuery8);
//									to delete related values
									String strDelQuery7 = "delete from subcategory8 where subcat8_id in("+strStatus7+")";
									String strResult7 = mu.deleteData(ds, strDelQuery7);
									////////////System.out.println("Subcategory 8 Result "+strResult7);
									if(statusVec8.size()>0)
									{
										String strStatus8 = "";
										for(int i=0; i<statusVec8.size(); i++)
										{
											strStatus8 = strStatus8.equalsIgnoreCase("") ? statusVec8.elementAt(i).toString().trim() : strStatus8+","+statusVec8.elementAt(i).toString().trim();
										}
//										to delete related values
										String strDelQuery8 = "delete from subcategory9 where subcat9_id in("+strStatus8+")";
										String strResult8 = mu.deleteData(ds, strDelQuery8);
										////////////System.out.println("Subcategory 8 Result "+strResult8);
									}
								}
							}
						}
						if(strPageId.equalsIgnoreCase("click8"))
						{
							String strQuery7 = "Select subcat8_id from subcategory8 where subcat7_id ="+numSCatid;
							Vector statusVec7 = mu.getIds(ds, strQuery7);
							if(statusVec7.size()>0)
							{
								String strStatus7 = "";
								for(int i=0; i<statusVec7.size(); i++)
								{
									strStatus7 = strStatus7.equalsIgnoreCase("") ? statusVec7.elementAt(i).toString().trim() : strStatus7+","+statusVec7.elementAt(i).toString().trim();
								}
								String strQuery8 = "Select subcat9_id from subcategory9 where subcat8_id in("+strStatus7+")";
								Vector statusVec8 = mu.getIds(ds, strQuery8);
//								to delete related values
								String strDelQuery7 = "delete from subcategory8 where subcat8_id in("+strStatus7+")";
								String strResult7 = mu.deleteData(ds, strDelQuery7);
								////////////System.out.println("Subcategory 8 Result "+strResult7);
								if(statusVec8.size()>0)
								{
									String strStatus8 = "";
									for(int i=0; i<statusVec8.size(); i++)
									{
										strStatus8 = strStatus8.equalsIgnoreCase("") ? statusVec8.elementAt(i).toString().trim() : strStatus8+","+statusVec8.elementAt(i).toString().trim();
									}
//									to delete related values
									String strDelQuery8 = "delete from subcategory9 where subcat9_id in("+strStatus8+")";
									String strResult8 = mu.deleteData(ds, strDelQuery8);
									//////////////System.out.println("Subcategory 8 Result "+strResult8);
								}
							}
						}
						if(strPageId.equalsIgnoreCase("click9"))
						{
							String strQuery8 = "Select subcat9_id from subcategory9 where subcat8_id ="+numSCatid;
							Vector statusVec8 = mu.getIds(ds, strQuery8);
							if(statusVec8.size()>0)
							{
								String strStatus8 = "";
								for(int i=0; i<statusVec8.size(); i++)
								{
									strStatus8 = strStatus8.equalsIgnoreCase("") ? statusVec8.elementAt(i).toString().trim() : strStatus8+","+statusVec8.elementAt(i).toString().trim();
								}
//								to delete related values
								String strDelQuery8 = "delete from subcategory9 where subcat9_id in("+strStatus8+")";
								String strResult8 = mu.deleteData(ds, strDelQuery8);
								//////////////System.out.println("Subcategory 8 Result "+strResult8);
							}
						}
									
					}
					public String deleteSubCategory(ManipulatesubcatForm mscf, DataSource ds, int numCompId)
					{
						String strResult = "failure";
						int numCatid = Integer.parseInt(mscf.getSubcatid().trim());
						String strPageId = mscf.getCatclick().trim();
						allRelSubCat(ds, numCatid, strPageId);
						String strQuery = "delete from ";
						if(strPageId.equalsIgnoreCase("click1"))
						{
							strQuery += "subcategory where companyid="+numCompId+" and subcat_id="+numCatid;
							
						}
//						Sub Category1/ Field2
						if(strPageId.equalsIgnoreCase("click2"))
						{
							strQuery += "subcategory1 where companyid="+numCompId+" and subcat1_id="+numCatid;
							
						}
//						Sub Category2/ Field3
						if(strPageId.equalsIgnoreCase("click3"))
						{
							strQuery += "subcategory2 where companyid="+numCompId+" and subcat2_id="+numCatid;
							
						}
//						Sub Category3/ Field4
						if(strPageId.equalsIgnoreCase("click4"))
						{
							strQuery += "subcategory3 where companyid="+numCompId+" and subcat3_id="+numCatid;
							
						}
//						Sub Category4/ Field5
						if(strPageId.equalsIgnoreCase("click5"))
						{
							strQuery += "subcategory4 where companyid="+numCompId+" and subcat4_id="+numCatid;
							
						}
//						Sub Category5/ Field6
						if(strPageId.equalsIgnoreCase("click6"))
						{
							strQuery += "subcategory5 where companyid="+numCompId+" and subcat5_id="+numCatid;
							
						}
//						Sub Category6/ Field7
						if(strPageId.equalsIgnoreCase("click7"))
						{
							strQuery += "subcategory6 where companyid="+numCompId+" and subcat6_id="+numCatid;
							
						}
//						Sub Category7/ Field8
						if(strPageId.equalsIgnoreCase("click8"))
						{
							strQuery += "subcategory7 where companyid="+numCompId+" and subcat7_id="+numCatid;
							
						}
//						Sub Category8/ Field9
						if(strPageId.equalsIgnoreCase("click9"))
						{
							strQuery += "subcategory8 where companyid="+numCompId+" and subcat8_id="+numCatid;
							
						}
//						Sub Category9/ Field10
						if(strPageId.equalsIgnoreCase("click10"))
						{
							strQuery += "subcategory9 where companyid="+numCompId+" and subcat9_id="+numCatid;
						}
						
						MasterUtility mu = new MasterUtility();
						strResult = mu.deleteData(ds, strQuery);
													
						return strResult;
					}
					
					public Vector getSubCatList1(DataSource ds, int numCompId, int numSCatId)
					{
						Support support=new Support();
						//////////////System.out.println("Sub Category 1 "+numSCatId);
						String strQuery = "Select * from subcategory where companyid="+numCompId+" and cat_id="+numSCatId+" order by subcat_name";
						support.setFieldVec("int", "subcat_id");
						support.setFieldVec("string", "subcat_name");
						
						Vector<String> tempVec1 = new Vector<String>();
						tempVec1.add("0");
						tempVec1.add("Select");
						
//																	
						Vector<Vector> resultVec = masterUtil.getList(ds, strQuery, support);
						resultVec.add(0, tempVec1);
						
						return resultVec;
					}
					
					public Vector getSubCatList2(DataSource ds, int numCompId, int numSCatId)
					{
						Support support=new Support();
						//////////////System.out.println("Sub Category 2 "+numSCatId);
						String strQuery = "Select * from subcategory1 where companyid="+numCompId+" and subcat_id="+numSCatId+" order by subcat1_name";
						support.setFieldVec("int", "subcat1_id");
						support.setFieldVec("string", "subcat1_name");
						Vector<String> tempVec1 = new Vector<String>();
						tempVec1.add("0");
						tempVec1.add("Select");	
						Vector resultVec = masterUtil.getList(ds, strQuery, support);
						resultVec.add(0, tempVec1);
						
						return resultVec;
					}
					
					public Vector getSubCatList3(DataSource ds, int numCompId, int numSCatId)
					{
						Support support=new Support();
						//////////////System.out.println("Sub Category 3 "+numSCatId);
						String strQuery = "Select * from subcategory2 where companyid="+numCompId+" and subcat1_id="+numSCatId+" order by subcat2_name";
						support.setFieldVec("int", "subcat2_id");
						support.setFieldVec("string", "subcat2_name");
						Vector<String> tempVec1 = new Vector<String>();
						tempVec1.add("0");
						tempVec1.add("Select");	
						Vector resultVec = masterUtil.getList(ds, strQuery, support);
						resultVec.add(0, tempVec1);
						return resultVec;
					}
					
					public Vector getSubCatList4(DataSource ds, int numCompId, int numSCatId)
					{
						Support support=new Support();
						//////////////System.out.println("Sub Category 4 "+numSCatId);
						String strQuery = "Select * from subcategory3 where companyid="+numCompId+" and subcat2_id="+numSCatId+" order by subcat3_name";
						support.setFieldVec("int", "subcat3_id");
						support.setFieldVec("string", "subcat3_name");
						Vector<String> tempVec1 = new Vector<String>();
						tempVec1.add("0");
						tempVec1.add("Select");	
						Vector resultVec = masterUtil.getList(ds, strQuery, support);
						resultVec.add(0, tempVec1);
						return resultVec;
					}
					
					public Vector getSubCatList5(DataSource ds, int numCompId, int numSCatId)
					{
						
						Support support=new Support();
						//////////////System.out.println("Sub Category 5 "+numSCatId);
						String strQuery = "Select * from subcategory4 where companyid="+numCompId+" and subcat3_id="+numSCatId+" order by subcat4_name";
						support.setFieldVec("int", "subcat4_id");
						support.setFieldVec("string", "subcat4_name");
						Vector<String> tempVec1 = new Vector<String>();
						tempVec1.add("0");
						tempVec1.add("Select");	
						Vector resultVec = masterUtil.getList(ds, strQuery, support);
						resultVec.add(0, tempVec1);
						return resultVec;
					}
					
					public Vector getSubCatList6(DataSource ds, int numCompId, int numSCatId)
					{
						
						Support support=new Support();
						//////////////System.out.println("Sub Category 6 "+numSCatId);
						String strQuery = "Select * from subcategory5 where companyid="+numCompId+" and subcat4_id="+numSCatId+" order by subcat5_name";
						support.setFieldVec("int", "subcat5_id");
						support.setFieldVec("string", "subcat5_name");			
						Vector<String> tempVec1 = new Vector<String>();
						tempVec1.add("0");
						tempVec1.add("Select");	
						
						Vector resultVec = masterUtil.getList(ds, strQuery, support);
						resultVec.add(0, tempVec1);
						return resultVec;
					}
					
					public Vector getSubCatList7(DataSource ds, int numCompId, int numSCatId)
					{
						
						Support support=new Support();
						//////////////System.out.println("Sub Category 7 "+numSCatId);
						String strQuery = "Select * from subcategory6 where companyid="+numCompId+" and subcat5_id="+numSCatId+" order by subcat6_name";
						support.setFieldVec("int", "subcat6_id");
						support.setFieldVec("string", "subcat6_name");
						Vector<String> tempVec1 = new Vector<String>();
						tempVec1.add("0");
						tempVec1.add("Select");										
						Vector resultVec = masterUtil.getList(ds, strQuery, support);
						resultVec.add(0, tempVec1);
						return resultVec;
					}
					
					public Vector getSubCatList8(DataSource ds, int numCompId, int numSCatId)
					{
						
						Support support=new Support();
						//////////////System.out.println("Sub Category 8 "+numSCatId);
						String strQuery = "Select * from subcategory7 where companyid="+numCompId+" and subcat6_id="+numSCatId+" order by subcat7_name";
						support.setFieldVec("int", "subcat7_id");
						support.setFieldVec("string", "subcat7_name");
						Vector<String> tempVec1 = new Vector<String>();
						tempVec1.add("0");
						tempVec1.add("Select");	
						Vector resultVec = masterUtil.getList(ds, strQuery, support);
						resultVec.add(0, tempVec1);
						return resultVec;
					}
					
					public Vector getSubCatList9(DataSource ds, int numCompId, int numSCatId)
					{
						
						Support support=new Support();
						//////////////System.out.println("Sub Category 9 "+numSCatId);
						String strQuery = "Select * from subcategory8 where companyid="+numCompId+" and subcat7_id="+numSCatId+" order by subcat8_name";
						support.setFieldVec("int", "subcat8_id");
						support.setFieldVec("string", "subcat8_name");
						
						Vector<String> tempVec1 = new Vector<String>();
						tempVec1.add("0");
						tempVec1.add("Select");	
						Vector resultVec = masterUtil.getList(ds, strQuery, support);
						resultVec.add(0, tempVec1);
						return resultVec;
					}
					
					public Vector getSubCatList10(DataSource ds, int numCompId, int numSCatId)
					{
						
						Support support=new Support();
						//////////////System.out.println("Sub Category 10 "+numSCatId);
						String strQuery = "Select * from subcategory9 where companyid="+numCompId+" and subcat8_id="+numSCatId+" order by subcat9_name";
						support.setFieldVec("int", "subcat9_id");
						support.setFieldVec("string", "subcat9_name");
						Vector<String> tempVec1 = new Vector<String>();
						tempVec1.add("0");
						tempVec1.add("Select");			
						Vector resultVec = masterUtil.getList(ds, strQuery, support);
						resultVec.add(0, tempVec1);
						return resultVec;
					}
					
					
					

					/**
					 * This function use for getting total no. of UserGroup in the company .
					 * @return
					 */	
						public int getUserGroupCount(DataSource ds,int numCompId){
							
							
							
							int result = 0;
							
													
							   String strQuery="";
							
								strQuery = "Select count(*) rowCount from loginrights where   companyid="+numCompId;
								
								result = masterUtil.getCount(ds, strQuery);
							//result = masterUtil.getCount(ds, strQuery);
							//////////////System.out.println("In EntpMaster result..."+result);
							return result;
						}
					
					
					
					/**
					 *  For User Group 
					 */
					/**
					 * 
					 * @param ds
					 * @param numCompId
					 * @return
					 */
					public Vector getUserGroupList(DataSource ds, Vector paramVec)
					{
							int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
							int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
							int companyid = Integer.parseInt(paramVec.elementAt(2).toString().trim());
							String fieldVal = paramVec.elementAt(3).toString().trim();
							
							Support support=new Support();
							   String strQuery="";
							
								strQuery = "select * from loginrights where companyid="+companyid+"  order by "+fieldVal+" LIMIT "+min+", "+max;
								
							
							/**
							 * set all the fileds type and name for loginrights table by using
							 * Support class object.
							 */
							support.setFieldVec("int", "rid");
							support.setFieldVec("string", "groupname");								
							
						Vector resultVec = masterUtil.getList(ds, strQuery, support);
						
						return resultVec;
					}
					
				
				/**
				 * End For User Group 
				 */
					
					public String setUserGroup(DataSource ds, Vector dataVec)
					{
						String strResult = "failure";
						Support support = new Support();
						support.setDataVec("int", dataVec.elementAt(1).toString().trim());
						support.setDataVec("string", dataVec.elementAt(2).toString().trim());
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
						String strQuery = "insert into loginrights (companyid, groupname, accmanagement, users, ";
						strQuery +="usercreation, userdeletion, usermodification, change_password, complaintmanagement, ";
						strQuery +="mis, complaints, test_creation, mentoring) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						MasterUtility mu = new MasterUtility();
						strResult = mu.setData(ds, strQuery, support);
						return strResult;
					}
					public String editUserGroup(DataSource ds, Vector dataVec)
					{
						String strResult = "failure";
						Support support = new Support();
						support.setDataVec("string", dataVec.elementAt(2).toString().trim());
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
						support.setDataVec("int", dataVec.elementAt(1).toString().trim());
						support.setDataVec("int", dataVec.elementAt(0).toString().trim());
						String strQuery = "update loginrights set groupname=?, accmanagement=?, users=?, ";
						strQuery +="usercreation=?, userdeletion=?, usermodification=?, change_password=?, complaintmanagement=?, ";
						strQuery +="mis=?, complaints=?, test_creation=?, mentoring=? where companyid=? and rid=?";
						MasterUtility mu = new MasterUtility();
						strResult = mu.setData(ds, strQuery, support);
						return strResult;
					}
					
					
					public String setUserGroupColl(DataSource ds, Vector dataVec)
					{
						String strResult = "failure";
						Support support = new Support();
						support.setDataVec("int", dataVec.elementAt(1).toString().trim());
						support.setDataVec("string", dataVec.elementAt(2).toString().trim());
						support.setDataVec("int", dataVec.elementAt(3).toString().trim());
						support.setDataVec("int", dataVec.elementAt(4).toString().trim());
						support.setDataVec("int", dataVec.elementAt(5).toString().trim());
						support.setDataVec("int", dataVec.elementAt(6).toString().trim());
						support.setDataVec("int", dataVec.elementAt(7).toString().trim());
						support.setDataVec("int", dataVec.elementAt(8).toString().trim());
						support.setDataVec("int", dataVec.elementAt(9).toString().trim());
						support.setDataVec("int", dataVec.elementAt(10).toString().trim());
						support.setDataVec("int", dataVec.elementAt(11).toString().trim());
						
						String strQuery = "insert into loginrights (companyid, groupname, accmanagement, users, ";
						strQuery +="usercreation, userdeletion, usermodification, change_password, complaintmanagement, ";
						strQuery +="mis, complaints) values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						MasterUtility mu = new MasterUtility();
						strResult = mu.setData(ds, strQuery, support);
						return strResult;
					}
					public String editUserGroupColl(DataSource ds, Vector dataVec)
					{
						String strResult = "failure";
						Support support = new Support();
						support.setDataVec("string", dataVec.elementAt(2).toString().trim());
						support.setDataVec("int", dataVec.elementAt(3).toString().trim());
						support.setDataVec("int", dataVec.elementAt(4).toString().trim());
						support.setDataVec("int", dataVec.elementAt(5).toString().trim());
						support.setDataVec("int", dataVec.elementAt(6).toString().trim());
						support.setDataVec("int", dataVec.elementAt(7).toString().trim());
						support.setDataVec("int", dataVec.elementAt(8).toString().trim());
						support.setDataVec("int", dataVec.elementAt(9).toString().trim());
						support.setDataVec("int", dataVec.elementAt(10).toString().trim());
						support.setDataVec("int", dataVec.elementAt(11).toString().trim());
						
						support.setDataVec("int", dataVec.elementAt(1).toString().trim());
						support.setDataVec("int", dataVec.elementAt(0).toString().trim());
						String strQuery = "update loginrights set groupname=?, accmanagement=?, users=?, ";
						strQuery +="usercreation=?, userdeletion=?, usermodification=?, change_password=?, complaintmanagement=?, ";
						strQuery +="mis=?, complaints=? where companyid=? and rid=?";
						MasterUtility mu = new MasterUtility();
						strResult = mu.setData(ds, strQuery, support);
						return strResult;
					}
					
					/**
					 * 
					 * @param ds
					 * @param numCompId
					 * @param numRId
					 * @return
					 */
					public Vector getUserGroupDetails(DataSource ds, int numCompId, int numRId, String strType)
					{
						Vector<String> resultVec = new Vector<String>();
						if(!strType.equalsIgnoreCase("Add New"))
						{
							Support support=new Support();
														
							String strQuery = "SELECT * FROM loginrights where companyid="+numCompId+" and rid="+numRId;
								
							/**
							 * set all the fileds type and name for Rights table by using
							 * Support class object.
							*/
							support.setFieldVec("int", "rid");
							support.setFieldVec("string", "groupname");
							support.setFieldVec("int", "accmanagement");
							support.setFieldVec("int", "users");
							support.setFieldVec("int", "usercreation");
							support.setFieldVec("int", "userdeletion");
							support.setFieldVec("int", "usermodification");
							support.setFieldVec("int", "change_password");
							support.setFieldVec("int", "complaintmanagement");
							support.setFieldVec("int", "mis");
							support.setFieldVec("int", "complaints");
							support.setFieldVec("int", "test_creation");
							support.setFieldVec("int", "mentoring");
								
							resultVec = masterUtil.getDetail(ds, strQuery, support);
						}
						else
						{
							resultVec.add("0");
							resultVec.add("");
							resultVec.add("0");
							resultVec.add("0");
							resultVec.add("0");
							resultVec.add("0");
							resultVec.add("0");
							resultVec.add("0");
							resultVec.add("0");
							resultVec.add("0");
							resultVec.add("0");
							resultVec.add("0");
							resultVec.add("0");
						}
						
						return resultVec;
					}
					public String deleteUserGroup(DataSource ds, int numUrid, int numCompId)
					{
						String strResult = "failure";
						String strQuery = "delete from loginrights where companyid="+numCompId+" and rid="+numUrid;
						MasterUtility mu = new MasterUtility();
						strResult = mu.deleteData(ds, strQuery);
						return strResult;
					}
					
					
					/*
					 * This function update rid of all user of a company for a particular group.
					 */
					public String updateRid(DataSource ds,int numUrid, int numCompId){
						////System.out.println("paramVecin indvmaster>>>>>>>>>"+paramVec);
						Support support=new Support();
						String strQuery="";
					    String strResult = "";
					   
					    
					  	strQuery = "Update loginmaster set rid=0 where companyid="+numCompId+" and rid="+numUrid;
					
						System.out.println("strQuery indvmaster>>>>>>>>>"+strQuery);
								
						strResult = masterUtil.setData(ds, strQuery, support);
						System.out.println("strResult advt>>>>>>>>>"+strResult);
						
						return strResult;
						
						
					}

					
					/**
					 * This function use for getting total no. of Inbox Request.
					 * @return
					 */	
						public int getInboxCount(DataSource ds,Vector paramVec){
							
							int result = 0;
							int UserId = Integer.parseInt(paramVec.elementAt(0).toString().trim());
							int adminFlag = Integer.parseInt(paramVec.elementAt(1).toString().trim());
							String typeofcompany = paramVec.elementAt(2).toString().trim();
							
													
							   String strQuery="";
							
							   if(typeofcompany.equalsIgnoreCase("Enterprise"))
								{	
							 	    
							 	    	strQuery = "select count(*) count from communication c,complaints cp, loginmaster l, companymaster cm "+
							 			"where  c.advt_rflag= '0' and cp.complaint_id=c.complaintid and c.userid = l.userid and l.companyid"+
							 			" = cm.company_id and  (c.privateflag = 3 or c.privateflag = 0) and cm.typeofcompany='Enterprise' and "+
							 			"cp.advtL_id = "+UserId;
										
									
								}
							   ////////////System.out.println("strQuery....count......"+strQuery);
							result = masterUtil.getCount(ds, strQuery);
							//////////////System.out.println("insite getInboxCount.Inbox..result...."+result);
							return result;
						}
						/**
						 * This function is use for getting list of  Inbox from complaints and communication.
						 * It returns a list within limit which is given by min and max variable.
						 */
							public Vector getInboxList(DataSource ds,Vector paramVec){
								
								//////////////System.out.println("insite getInboxList.Inbox......");
								
								Vector resultVec = new Vector();
								int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
								int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
								String fieldVal = paramVec.elementAt(2).toString().trim();
								int UserId = Integer.parseInt(paramVec.elementAt(3).toString().trim());
								int adminFlag = Integer.parseInt(paramVec.elementAt(4).toString().trim());
								String typeofcompany = paramVec.elementAt(5).toString().trim();
								
								Support support=new Support();
								   String strQuery="";
								
								if(typeofcompany.equalsIgnoreCase("Enterprise"))
								{	
							 	    
							 	    	strQuery = "select c.respid respid, c.responsetext responsetext, c.responsedate responsedate, c.complaintid complaintid, "+
							"c.userid userid, c.responseTime responseTime, cm.typeofcompany typeofcompany, "+ 
							"l.first_name first_name, l.last_name last_name, l.city city, cm.company_name company_name, cp.fcom_id fcom_id "+
							"from communication c,complaints cp, loginmaster l, companymaster cm "+
							 			"where  c.advt_rflag= '0' and cp.complaint_id=c.complaintid and c.userid = l.userid and l.companyid"+
							 			" = cm.company_id and cm.typeofcompany='Enterprise' and (c.privateflag = 3 or c.privateflag = 0) and "+
							 			"cp.advtL_id = "+UserId+" order by "+fieldVal+"  limit "+min+", "+max;
										
									
							}
								
								////////////System.out.println("strQuery..inbox..........."+strQuery);
								/**
								 * set all the fileds type and name for companymaster table by using
								 * Support class object.
								 */
								support.setFieldVec("int", "respid");
								support.setFieldVec("int", "userid");
								support.setFieldVec("string", "responsetext");								
								support.setFieldVec("string", "complaintid");	
								support.setFieldVec("string", "responsedate");
								support.setFieldVec("string", "responseTime");			
								support.setFieldVec("string", "fcom_id");
								
								/*
								 * support.setFieldVec("int", "respid");			//0
							support.setFieldVec("string", "responsetext");	//1
							support.setFieldVec("string", "responsedate");	//2
							support.setFieldVec("int", "userid");			//3
							support.setFieldVec("string", "responseTime");	//4
							support.setFieldVec("string", "typeofcompany");	//5
							support.setFieldVec("string", "first_name");	//6
							support.setFieldVec("string", "last_name");		//7
							support.setFieldVec("int", "city");				//8
							support.setFieldVec("string", "company_name");	//9
								 */
								
								
								resultVec = masterUtil.getList(ds, strQuery, support);
								//////////////System.out.println("insite getInboxList.Inbox..resultVec...."+resultVec);
								return resultVec;
							}
		public int getCreatedUserId(DataSource ds, Vector userVec)
		{
			int numResult = 0;
						
			String strQuery = "Select * from loginmaster where companyid="+userVec.elementAt(0).toString().trim();
			strQuery +=" and loginname='"+userVec.elementAt(2).toString().trim();
			strQuery +="' and password='"+userVec.elementAt(3).toString().trim();
			strQuery +="' and first_name='"+userVec.elementAt(4).toString().trim();
			strQuery +="' and last_name='"+userVec.elementAt(5).toString().trim();
			strQuery +="' and email='"+userVec.elementAt(6).toString().trim();
			strQuery +="' and mobile='"+userVec.elementAt(8).toString().trim();
			strQuery +="' and address='"+userVec.elementAt(9).toString().trim();
			strQuery +="' and country='"+userVec.elementAt(10).toString().trim();
			strQuery +="' and state='"+userVec.elementAt(11).toString().trim();
			strQuery +="' and city='"+userVec.elementAt(12).toString().trim();
			strQuery +="' and zip='"+userVec.elementAt(13).toString().trim()+"'";
			MasterUtility mu = new MasterUtility();
			numResult = mu.getId(ds, strQuery, "userid");
			return numResult;
		}
		public Vector getUserCategoryDetails(DataSource ds, int numCompId, int numUserId)
		{
			Vector resultVec = new Vector();
			Support support=new Support();
			String strQuery="Select * from logincategories where company_id="+numCompId+" and login_id="+numUserId;
						
			support.setFieldVec("int", "cat_id");           //0
			support.setFieldVec("int", "subcat_id");     //1
			support.setFieldVec("int", "subcat1_id");      //2
			support.setFieldVec("int", "subcat2_id");       //3
			support.setFieldVec("int", "subcat3_id");            //4			
			support.setFieldVec("int", "subcat4_id");       //5
			support.setFieldVec("int", "subcat5_id");        //6
			support.setFieldVec("int", "subcat6_id");       //7
			support.setFieldVec("int", "subcat7_id");        //8
			support.setFieldVec("int", "subcat8_id");     //9
			support.setFieldVec("int", "subcat9_id");     //10
			resultVec = masterUtil.getDetail(ds, strQuery, support);
			return resultVec;
		}
		public String setUserCategory(DataSource ds, Vector usrCatVec)
		{
			String strResult = "";
			Support support = new Support();
			support.setDataVec("int", usrCatVec.elementAt(0).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(1).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(2).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(3).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(4).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(5).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(6).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(7).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(8).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(9).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(10).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(11).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(12).toString().trim());
			
			String strQuery = "insert into logincategories (company_id, login_id, cat_id, subcat_id, ";
			strQuery +="subcat1_id, subcat2_id, subcat3_id, subcat4_id, subcat5_id, ";
			strQuery +="subcat6_id, subcat7_id, subcat8_id, subcat9_id) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			MasterUtility mu = new MasterUtility();
			strResult = mu.setData(ds, strQuery, support);
			return strResult;
		}
		
		public int getUserCatStatus(DataSource ds, int numCompId, int numUid)
		{
			int numResult = 0;
						
			String strQuery = "Select * from logincategories where company_id="+numCompId+" and login_id="+numUid;
			//String strQuery1 = "Select * from logincategories where login_id="+numUid;
			//////////////System.out.println("Str Query "+strQuery);
			MasterUtility mu = new MasterUtility();
			numResult = mu.getId(ds, strQuery, "lc_id");
			return numResult;
		}
		public String editUserCategory(DataSource ds, Vector usrCatVec)
		{
			String strResult = "";
			Support support = new Support();
			support.setDataVec("int", usrCatVec.elementAt(2).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(3).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(4).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(5).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(6).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(7).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(8).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(9).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(10).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(11).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(12).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(0).toString().trim());
			support.setDataVec("int", usrCatVec.elementAt(1).toString().trim());
			
			String strQuery = "update logincategories set cat_id=?, subcat_id=?, ";
			strQuery +="subcat1_id=?, subcat2_id=?, subcat3_id=?, subcat4_id=?, subcat5_id=?, ";
			strQuery +="subcat6_id=?, subcat7_id=?, subcat8_id=?, subcat9_id=? where company_id=? and login_id=?";
			MasterUtility mu = new MasterUtility();
			strResult = mu.setData(ds, strQuery, support);
			return strResult;
		}
		
		public String getSubCatDetail(DataSource ds, int numCompId, int numSCatId, int numCount)
		{
			Support support=new Support();
			String strQuery = "Select * From ";
			if(numCount == 0)
			{
				strQuery +="category where cat_id="+numSCatId;
				support.setFieldVec("string", "cat_name");
			}
			if(numCount == 1)
			{
				strQuery +="subcategory where companyid="+numCompId+" and subcat_id="+numSCatId;
				support.setFieldVec("string", "subcat_name");
			}
			if(numCount == 2)
			{
				strQuery +="subcategory1 where companyid="+numCompId+" and subcat1_id="+numSCatId;
				support.setFieldVec("string", "subcat1_name");
			}
			if(numCount == 3)
			{
				strQuery +="subcategory2 where companyid="+numCompId+" and subcat2_id="+numSCatId;
				support.setFieldVec("string", "subcat2_name");
			}
			if(numCount == 4)
			{
				strQuery +="subcategory3 where companyid="+numCompId+" and subcat3_id="+numSCatId;
				support.setFieldVec("string", "subcat3_name");
			}
			if(numCount == 5)
			{
				strQuery +="subcategory4 where companyid="+numCompId+" and subcat4_id="+numSCatId;
				support.setFieldVec("string", "subcat4_name");
			}
			if(numCount == 6)
			{
				strQuery +="subcategory5 where companyid="+numCompId+" and subcat5_id="+numSCatId;
				support.setFieldVec("string", "subcat5_name");
			}
			if(numCount == 7)
			{
				strQuery +="subcategory6 where companyid="+numCompId+" and subcat6_id="+numSCatId;
				support.setFieldVec("string", "subcat6_name");
			}
			if(numCount == 8)
			{
				strQuery +="subcategory7 where companyid="+numCompId+" and subcat7_id="+numSCatId;
				support.setFieldVec("string", "subcat7_name");
			}
			if(numCount == 9)
			{
				strQuery +="subcategory8 where companyid="+numCompId+" and subcat8_id="+numSCatId;
				support.setFieldVec("string", "subcat8_name");
			}
			if(numCount == 10)
			{
				strQuery +="subcategory9 where companyid="+numCompId+" and subcat9_id="+numSCatId;
				support.setFieldVec("string", "subcat9_name");
			}
				
			String strResult = masterUtil.getValue(ds, strQuery, support);
			return strResult;
		}
		public String getAdminName(DataSource ds, int numCompId, int numSCatId, int numCount)
		{
			String strTemp="";
			if(numCount==0)
			{
				strTemp +="cat_id="+numSCatId+" and ";
				strTemp +="subcat_id=0 and ";
				strTemp +="subcat1_id=0 and ";
				strTemp +="subcat2_id=0 and ";
				strTemp +="subcat3_id=0 and ";
				strTemp +="subcat4_id=0 and ";
				strTemp +="subcat5_id=0 and ";
				strTemp +="subcat6_id=0 and ";
				strTemp +="subcat7_id=0 and ";
				strTemp +="subcat8_id=0 and ";
				strTemp +="subcat9_id=0";
			}
			if(numCount==1)
			{
				strTemp +="subcat_id="+numSCatId+" and ";
				strTemp +="subcat1_id=0 and ";
				strTemp +="subcat2_id=0 and ";
				strTemp +="subcat3_id=0 and ";
				strTemp +="subcat4_id=0 and ";
				strTemp +="subcat5_id=0 and ";
				strTemp +="subcat6_id=0 and ";
				strTemp +="subcat7_id=0 and ";
				strTemp +="subcat8_id=0 and ";
				strTemp +="subcat9_id=0";
			}
			if(numCount==2)
			{
				strTemp +="subcat1_id="+numSCatId+" and ";
				strTemp +="subcat2_id=0 and ";
				strTemp +="subcat3_id=0 and ";
				strTemp +="subcat4_id=0 and ";
				strTemp +="subcat5_id=0 and ";
				strTemp +="subcat6_id=0 and ";
				strTemp +="subcat7_id=0 and ";
				strTemp +="subcat8_id=0 and ";
				strTemp +="subcat9_id=0";
			}
			if(numCount==3)
			{
				strTemp +="subcat2_id="+numSCatId+" and ";
				strTemp +="subcat3_id=0 and ";
				strTemp +="subcat4_id=0 and ";
				strTemp +="subcat5_id=0 and ";
				strTemp +="subcat6_id=0 and ";
				strTemp +="subcat7_id=0 and ";
				strTemp +="subcat8_id=0 and ";
				strTemp +="subcat9_id=0";
			}
			if(numCount==4)
			{
				strTemp +="subcat3_id="+numSCatId+" and ";
				strTemp +="subcat4_id=0 and ";
				strTemp +="subcat5_id=0 and ";
				strTemp +="subcat6_id=0 and ";
				strTemp +="subcat7_id=0 and ";
				strTemp +="subcat8_id=0 and ";
				strTemp +="subcat9_id=0";
			}
			if(numCount==5)
			{
				strTemp +="subcat4_id="+numSCatId+" and ";
				strTemp +="subcat5_id=0 and ";
				strTemp +="subcat6_id=0 and ";
				strTemp +="subcat7_id=0 and ";
				strTemp +="subcat8_id=0 and ";
				strTemp +="subcat9_id=0";
			}
			if(numCount==6)
			{
				strTemp +="subcat5_id="+numSCatId+" and ";
				strTemp +="subcat6_id=0 and ";
				strTemp +="subcat7_id=0 and ";
				strTemp +="subcat8_id=0 and ";
				strTemp +="subcat9_id=0";
			}
			if(numCount==7)
			{
				strTemp +="subcat6_id="+numSCatId+" and ";
				strTemp +="subcat7_id=0 and ";
				strTemp +="subcat8_id=0 and ";
				strTemp +="subcat9_id=0";
			}
			if(numCount==8)
			{
				strTemp +="subcat7_id="+numSCatId+" and ";
				strTemp +="subcat8_id=0 and ";
				strTemp +="subcat9_id=0";
			}
			if(numCount==9)
			{
				strTemp +="subcat8_id="+numSCatId+" and ";
				strTemp +="subcat9_id=0";
			}
			if(numCount==10)
			{
				strTemp +="subcat9_id="+numSCatId;
			}
			String strQuery = "Select * from logincategories where company_id="+numCompId+" and "+strTemp;
			//String strQuery1 = "Select * from logincategories where login_id="+numUid;
			//////////////System.out.println("Str Query "+strQuery);
			MasterUtility mu = new MasterUtility();
			int numResult = mu.getId(ds, strQuery, "login_id");
			Support support=new Support();
			String strQuery1 = "Select * From loginmaster where companyid="+numCompId+" and userid="+numResult;
			support.setFieldVec("string", "first_name");
			support.setFieldVec("string", "last_name");
			Vector resultVec = masterUtil.getDetail(ds, strQuery1, support);
			String strResult = (resultVec.size()<=0) ? "" : resultVec.elementAt(0).toString().trim()+" "+resultVec.elementAt(1).toString().trim();
			return strResult;
		}
		
		/**
		 * **************************************************************************************************
	     *	update ``advt_rflag`` in communication table and return result status result string.
		 *********************************************************************************************************
		 */	
	public String updateInbox(DataSource ds,Vector dataVec){
			
			Support support=new Support();
			String strQuery="";
		    String strResult = "";
		   
			
			strQuery = "Update communication set advt_rflag = 1 where respid in ("+dataVec.elementAt(0).toString().trim()+")";
			////////////System.out.println("dc strQuery >>>>>"+strQuery);
					
			strResult = masterUtil.setData(ds, strQuery, support);
			return strResult;
			
			
		}
	/**
	 *  This function is used for all count in advt.
	 */
	public Vector complaintCountAdvt(DataSource ds,Vector dataVec){
		
		Vector resultVec = new Vector();


		int Uid = Integer.parseInt(dataVec.elementAt(0).toString().trim());
		int Cid = Integer.parseInt(dataVec.elementAt(1).toString().trim());
		int aFid = Integer.parseInt(dataVec.elementAt(2).toString().trim());

		Vector comVec = getUnderprocessList(ds ,Cid, Uid, aFid);
		Vector comListVec = new Vector();
		String comList	= "";
				
		for(int i=0; i<comVec.size();i++)
			{
			comListVec = (Vector)comVec.elementAt(i);					
			int tempComId = Integer.parseInt(comListVec.elementAt(0).toString().trim());
			
			comList = comList + tempComId;
			if((i+1)<comVec.size()){
				comList = comList+",";  
				
			}
			
			}
			
		
		Vector dataVec1 = new Vector();			
		dataVec1.add(dataVec.elementAt(0).toString());
		dataVec1.add(dataVec.elementAt(1).toString());
		dataVec1.add(dataVec.elementAt(2).toString());
		dataVec1.add("1");  	
		dataVec1.add(comList);
		dataVec1.add("1"); 
		int count1 = getComplaintCount(ds, dataVec1);         // New Complaints Count (Inbox)

		Vector dataVec2 = new Vector();			
		dataVec2.add(dataVec.elementAt(0).toString());
		dataVec2.add(dataVec.elementAt(1).toString());
		dataVec2.add(dataVec.elementAt(2).toString());
		dataVec2.add("1");  	
		dataVec2.add(" ");
		dataVec2.add("2"); 
		
		int count2 = getComplaintCount(ds, dataVec2);		 //   Complaints Count (out Inbox)

		Vector dataVec3 = new Vector();			
		dataVec3.add(dataVec.elementAt(0).toString());
		dataVec3.add(dataVec.elementAt(1).toString());
		dataVec3.add(dataVec.elementAt(2).toString());
		dataVec3.add("3");  	
		dataVec3.add(" ");
		dataVec3.add(" ");
		int count3 = getComplaintCount(ds, dataVec3);		 // Mentoring request's

		Vector dataVec4 = new Vector();			
		dataVec4.add(dataVec.elementAt(0).toString());
		dataVec4.add(dataVec.elementAt(1).toString());
		dataVec4.add(dataVec.elementAt(2).toString());
		dataVec4.add("7");  	
		dataVec4.add("1");
		dataVec3.add(" ");
		int count4 = getComplaintCount(ds, dataVec4);		 //

		Vector dataVec5 = new Vector();			
		dataVec5.add(dataVec.elementAt(0).toString());
		dataVec5.add(dataVec.elementAt(1).toString());
		dataVec5.add(dataVec.elementAt(2).toString());
		dataVec5.add("7");  	
		dataVec5.add("2");
		dataVec3.add(" ");
		int count5 = getComplaintCount(ds, dataVec5);		 //  Closed UnResolved Complaints Count

		int count6 = count5 + count4;						//  Total Closed  Complaints Count
		
		Vector dataVec6 = new Vector();			
		dataVec6.add(dataVec.elementAt(0).toString());	
		dataVec6.add(dataVec.elementAt(2).toString());
		dataVec6.add("Enterprise");		
		int count7 = getInboxCount(ds, dataVec6);		 //   Inbox   Count



		Vector dataVec7 = new Vector();			
		dataVec7.add(dataVec.elementAt(1).toString());	
		dataVec7.add("0");
		int count8 = getUnallottedComplaintCount(ds, dataVec7);
		
		Vector dataVec8 = new Vector();			
		dataVec8.add(dataVec.elementAt(1).toString());	
		dataVec8.add(dataVec.elementAt(0).toString());
		dataVec8.add(comList);
		int count9 = getOtherCompTotalCount(ds, dataVec8);
		


		
		resultVec.add(count1);				  //0  New Complaints Count
		resultVec.add(count2);				  //1  UnderProcess Complaints Count
		resultVec.add(count3);				  //2  Resolved Complaints Count
		resultVec.add(count4);				  //3  Closed Resolved Complaints  Count
		resultVec.add(count5);				  //4  Closed UnResolved Complaints Count
		resultVec.add(count6);				  //5  Total Closed  Complaints Count
		resultVec.add(count7);				  //6   Inbox Count
		resultVec.add(count8);				  //7   Unallotted  Complaints  Count
		resultVec.add(count9);				  //8   Total Other  Complaints  Count
				
		return resultVec;
	}
	
	public Vector getUserGroupList(DataSource ds, int numCompId)
	{			
		Support support=new Support();
		String strQuery="";
				
		strQuery = "select * from loginrights where companyid="+numCompId;
				
		/**
		* set all the fileds type and name for loginrights table by using
		* Support class object.
		*/
		support.setFieldVec("int", "rid");
		support.setFieldVec("string", "groupname");								
				
		Vector resultVec = masterUtil.getList(ds, strQuery, support);
			
		return resultVec;
	}
	
	
	public int subCategoryCount(DataSource ds,String compId)
	{
		int result = 0;
		
		
		   String strQuery="";
		
			strQuery = "Select count(*) rowCount from subcategory where companyid="+compId;
			
			result = masterUtil.getCount(ds, strQuery);
		
		return result;
		
	}
//	this function is use for getting MIS report
	
	public Vector getMisReport(DataSource ds,Vector dataVec)
	{
		Vector resultVec = new Vector();
		
		/************************************************************************************
		String field1 = dataVec.elementAt(0).toString().trim();
		String field2 = dataVec.elementAt(1).toString().trim();
		String field3 = dataVec.elementAt(2).toString().trim();	
		String date1 = dataVec.elementAt(3).toString().trim();
		String date2 = dataVec.elementAt(4).toString().trim();
		String compId = dataVec.elementAt(5).toString().trim();    company Id of company
		String subCatCount = dataVec.elementAt(6).toString().trim();   subCategory count of company.
					
	*************************************************************************************/
		
		String field1 = dataVec.elementAt(0).toString().trim();
		String field2 = dataVec.elementAt(1).toString().trim();
		String field3 = dataVec.elementAt(2).toString().trim();	
		String date1 = dataVec.elementAt(3).toString().trim();
		String date2 = dataVec.elementAt(4).toString().trim();
		String compId = dataVec.elementAt(5).toString().trim();
		String subCatCount = dataVec.elementAt(6).toString().trim();
		////////////System.out.println("MIS.....subCatCount......getMisReport.Action........."+subCatCount);
		
		
		
		Support support=new Support();
		 String strQuery ="";
		 String subQuery = "";
		 
		 
		
	if(subCatCount.equalsIgnoreCase("0")){// IF "1" brand have no sub Category for a category.
		
		if(field1.equals("0"))
		 {
			 subQuery = "cmp.brandFlag=1 and cmp.advt_id = "+compId+"  and ";
		 }
		 else if(field1.equals("1"))//(Under construction)
		 {
			 subQuery = "cmp.brandFlag=1 and  cmp.complaint_id = communi.complaintid  and communi.userid = lm.userid and lm.companyid = "+compId+" and cmp.tag_id = 2 and cmp.advt_id = "+compId+" and ";
			 
			 //strQuery = "SELECT distinct(complaint_id),fcom_id,complaint_title,creation_date,creation_time from complaints b, communication r  where b.advt_id="+advt_id+" and b.advtL_id="+advtL_id+" and b.brandFlag = 1 and b.tag_id = 2  and b.complaint_id = r.complaintid and r.userid=b.advtL_id order by "+fieldVal+" limit "+min+", "+max;
			
//			strQuery = "distinct(complaint_id) from complaints b, communication r  where  and b.complaint_id = r.complaintid and r.userid=b.advtL_id 
				
		 }
		 else if(field1.equals("2"))//
		 {
			 subQuery= "cmp.tag_id=4 and cmp.advt_id = "+compId+" and cmp.resolve_date != 0000-00-00 and  cmp.reject_date= 0000-00-00 and ";
		 }
		 else if(field1.equals("3"))//
		 {
			 subQuery= "cmp.tag_id = 4 and cmp.advt_id = "+compId+" and cmp.resolve_date = 0000-00-00 and cmp.reject_date = 0000-00-00  and ";
		 }
		 
		 
		 
		 //time duration check for getting information
		 String timeQuery = "";
		 
		 if(field3.equals("0"))
		 {
			 timeQuery =" and cmp.creation_date  BETWEEN '"+date1+"' and '"+date2+"' ";
		 }
		 else if(field3.equals("1"))
		 {
			//under construction 
		 }
		 else if(field3.equals("2"))
		 {
//			under construction 
		 }
		 
		if(!field1.equals("4")&& !field1.equals("1"))   // if field1 is not 1 && 4 . (IF 1)
		{
			if(field2.equals("0"))//categorywise
			{
		    strQuery="select cat.cat_name cat_name,cat.cat_id cat_id,  creation_date creation_date, count(*) numcount " +
		    				"from complaints cmp, category cat " +
		    				"where "+subQuery +" cmp.category = cat.cat_id  " +timeQuery +
		    				"group by  cmp.category, cmp.creation_date " +
		    				"order by cat.cat_name, cmp.creation_date";
		    support.setFieldVec("string", "cat_name");
			support.setFieldVec("int", "cat_id");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			////////////System.out.println("MIS.....IF.....categorywise..................");
			}
			else if(field2.equals("1"))//SubCategorywise wise
			{
			    strQuery="select cat.cat_name cat_name, subcat.subcat_name subcat_name, creation_date creation_date, count(*) numcount " +
			    				"from complaints cmp, category cat,subcategory subcat " +
			    				"where "+subQuery +" cmp.category = cat.cat_id and cat.cat_id = subcat.cat_id " +timeQuery +
			    				"group by  cmp.category, subcat.subcat_id, cmp.creation_date " +
			    				"order by cat.cat_name, cmp.creation_date";
			    support.setFieldVec("string", "cat_name");
				support.setFieldVec("string", "subcat_name");
				support.setFieldVec("string", "creation_date");								
				support.setFieldVec("int", "numcount");	
				////////////System.out.println("MIS......IF....categorywise..................");
				}
			else if(field2.equals("2"))//Statewise
			{
		    strQuery="select cat.cat_name cat_name, cat.cat_id cat_id," +
		    		" creation_date creation_date, count(*) numcount ,st.statename statename ,pl.Place Place "+
		    		"from complaints cmp, category cat,loginmaster lm ,places pl,states st "+
		    		"where "+subQuery +" cmp.category = cat.cat_id  "+
	                " and lm.userid=cmp.login_id and st.stateid=lm.state and pl.PlaceID = lm.city  "+timeQuery +
		    		"group by  st.statename, cmp.category, cmp.creation_date "+
		    		"order by st.statename,cat.cat_name,  cmp.creation_date;";
		   
		    support.setFieldVec("string", "cat_name");
			support.setFieldVec("int", "cat_id");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			 support.setFieldVec("string", "statename");
			    support.setFieldVec("string", "Place");
			    ////////////System.out.println("MIS.....IF.....Statewise..................");
			}
			else if(field2.equals("3"))//citywise
			{
		    strQuery="select cat.cat_name cat_name, cat.cat_id cat_id," +
		    		" creation_date creation_date, count(*) numcount ,pl.Place Place,st.statename statename "+
		    		"from complaints cmp, category cat,loginmaster lm ,places pl,states st "+
		    		"where "+subQuery +" cmp.category = cat.cat_id  "+
	                " and lm.userid=cmp.login_id and pl.PlaceID = lm.city and st.stateid=lm.state "+timeQuery +
		    		"group by  pl.Place,cmp.category, cmp.creation_date "+
		    		"order by pl.Place,cat.cat_name,  cmp.creation_date;";
		   
		    support.setFieldVec("string", "cat_name");
			support.setFieldVec("int", "cat_id");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			support.setFieldVec("string", "Place");
			support.setFieldVec("string", "statename");
			
			 ////////////System.out.println("MIS....IF......citywise..................");
			}
			
						
			else if(field2.equals("4"))//Manager wise time taken (Under construction)
			{
				strQuery="select comp.company_name company_name, cat.cat_name cat_name,  creation_date creation_date, count(*) numcount " +
				"from complaints cmp, category cat,companymaster comp " +
				"where "+subQuery +" cmp.category = cat.cat_id and cmp.advt_id = comp.company_id " +timeQuery +
				"group by  comp.company_name, comp.company_id ,cmp.creation_date " +
				"order by comp.company_name, cmp.creation_date";
				
				support.setFieldVec("string", "company_name");
				support.setFieldVec("string", "cat_name");
				support.setFieldVec("string", "creation_date");								
				support.setFieldVec("int", "numcount");	
			}
		}
		else if(field1.equals("1")) // if field1 is  1 . (IF 1)
		{
			//(Under construction}****************
			if(field2.equals("0"))//categorywise
			{
				////////////System.out.println("MIS.....else.....categorywise......field1............");
		    strQuery="select cat.cat_name cat_name, cat.cat_id cat_id, creation_date creation_date, count(*) numcount " +
		    				"from complaints cmp, category cat, communication  communi, loginmaster lm " +
		    				"where  "+subQuery +" cmp.category = cat.cat_id   " +timeQuery +
		    				"group by  cmp.category,  cmp.creation_date " +
		    				"order by cat.cat_name, cmp.creation_date";
		    support.setFieldVec("string", "cat_name");
			support.setFieldVec("int", "cat_id");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			////////////System.out.println("MIS.....else.....categorywise......field1............");
			}
			else if(field2.equals("1"))//SubCategorywise wise
			{
			    strQuery="select cat.cat_name cat_name, cat.cat_id cat_id, creation_date creation_date, count(*) numcount " +
			    				"from complaints cmp, category cat, communication  communi, loginmaster lm " +
			    				"where "+subQuery +" cmp.category = cat.cat_id  " +timeQuery +
			    				"group by  cmp.category,  cmp.creation_date " +
			    				"order by cat.cat_name, cmp.creation_date";
			    support.setFieldVec("string", "cat_name");
				support.setFieldVec("int", "cat_id");
				support.setFieldVec("string", "creation_date");								
				support.setFieldVec("int", "numcount");	
				////////////System.out.println("MIS.....else.....categorywise.......field1...........");
				}
			else if(field2.equals("2"))//Statewise
			{
		    strQuery="select cat.cat_name cat_name, cat.cat_id cat_id," +
		    		" creation_date creation_date, count(*) numcount ,st.statename statename ,pl.Place Place "+
		    		"from complaints cmp, category cat, communication  communi, loginmaster lm , places pl, states st "+
		    		"where "+subQuery +" cmp.category = cat.cat_id and lm.userid=cmp.login_id"+
	                "and st.stateid=lm.state and pl.PlaceID = lm.city "+timeQuery +
		    		"group by  st.statename, cmp.category, cmp.creation_date "+
		    		"order by st.statename,cat.cat_name,  cmp.creation_date;";
		   
		    support.setFieldVec("string", "cat_name");
			support.setFieldVec("int", "cat_id");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			 support.setFieldVec("string", "statename");
			    support.setFieldVec("string", "Place");
			    ////////////System.out.println("MIS....else......Statewise.........field1.........");
			}
			else if(field2.equals("3"))//citywise
			{
		    strQuery="select cat.cat_name cat_name, cat.cat_id cat_id," +
		    		" creation_date creation_date, count(*) numcount ,pl.Place Place,st.statename statename "+
		    		"from complaints cmp, category cat,communication  communi, loginmaster lm ,places pl,states st "+
		    		"where "+subQuery +" cmp.category = cat.cat_id and lm.userid=cmp.login_id "+
	                " and pl.PlaceID = lm.city and st.stateid=lm.state "+timeQuery +
		    		"group by  pl.Place,cmp.category, cmp.creation_date "+
		    		"order by pl.Place,cat.cat_name,  cmp.creation_date;";
		   
		    support.setFieldVec("string", "cat_name");
			support.setFieldVec("int", "cat_id");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			support.setFieldVec("string", "Place");
			support.setFieldVec("string", "statename");
			
			 ////////////System.out.println("MIS....else.....citywise........field1..........");
			}
			
						
			else if(field2.equals("4"))//Manager wise time taken (Under construction)
			{
				strQuery="select comp.company_name company_name, cat.cat_name cat_name,  creation_date creation_date, count(*) numcount " +
				"from complaints cmp, category cat,companymaster comp " +
				"where "+subQuery +" cmp.category = cat.cat_id and cmp.advt_id = comp.company_id " +timeQuery +
				"group by  comp.company_name, comp.company_id ,cmp.creation_date " +
				"order by comp.company_name, cmp.creation_date";
				
				support.setFieldVec("string", "company_name");
				support.setFieldVec("string", "cat_name");
				support.setFieldVec("string", "creation_date");								
				support.setFieldVec("int", "numcount");	
			}
			
			//*********************
		}
		else // if field1 is 4 .  (IF 1)
		{
			if(field2.equals("0"))         //recieived //(Under construction)
			 {
				 subQuery = "";
			 }
			 else if(field2.equals("1"))  //Attended //(Under construction)
			 {
				 subQuery = "cmp.brandFlag=1 and ";
			 }
			 else if(field2.equals("2"))  //closed Resolved //(Under construction)
			 {
				 subQuery= "cmp.tag_id=4 and cmp.resolve_date != 0000-00-00 and  cmp.reject_date= 0000-00-00 and ";
			 }
			 else if(field2.equals("3"))  //closed unresolved //(Under construction)
			 {
				 subQuery= "cmp.tag_id = 4 and cmp.resolve_date = 0000-00-00 and cmp.reject_date = 0000-00-00  and ";
			 }
			 
			 else if(field2.equals("4")) //time wise (Under construction)
			 {
				 subQuery= "";
			 }
			strQuery= "select cat.cat_name cat_name, comp.company_name company_name," +
    		" creation_date creation_date, count(*) numcount ,lm1.first_name first_name,st.statename statename "+
    		"from complaints cmp, category cat,companymaster comp,loginmaster lm ,loginmaster lm1 ,places pl,states st "+
    		"where "+subQuery +"cmp.category = cat.cat_id and cmp.advt_id = comp.company_id and lm1.userid = cmp.cu_id "+
           " and lm.userid=cmp.login_id and pl.PlaceID = lm.city and st.stateid=lm.state "+timeQuery +
    		"group by  lm1.first_name,cmp.category, comp.company_id ,cmp.creation_date "+
    		"order by lm1.first_name,cat.cat_name,  cmp.creation_date;";
		 	support.setFieldVec("string", "cat_name");
			support.setFieldVec("string", "company_name");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			support.setFieldVec("string", "first_name");
			support.setFieldVec("string", "statename");
		}
	}else{// else of if "1" 
		
		if(field1.equals("0"))
		 {
			 subQuery = "cmp.brandFlag=1 and cmap.companyid = "+compId+" and cmp.advt_id = "+compId+"  and ";
		 }
		 else if(field1.equals("1"))//(Under construction)
		 {
			 subQuery = "cmp.brandFlag=1 and  cmp.complaint_id = communi.complaintid  and communi.userid = lm.userid and lm.companyid = "+compId+" and cmp.tag_id = 2 and cmp.advt_id = "+compId+" and ";
			 
			 //strQuery = "SELECT distinct(complaint_id),fcom_id,complaint_title,creation_date,creation_time from complaints b, communication r  where b.advt_id="+advt_id+" and b.advtL_id="+advtL_id+" and b.brandFlag = 1 and b.tag_id = 2  and b.complaint_id = r.complaintid and r.userid=b.advtL_id order by "+fieldVal+" limit "+min+", "+max;
			
			 //strQuery = "distinct(complaint_id) from complaints b, communication r  where  and b.complaint_id = r.complaintid and r.userid=b.advtL_id 
				
		 }
		 else if(field1.equals("2"))//
		 {
			 subQuery= "cmp.tag_id=4 and cmap.companyid = "+compId+" and cmp.advt_id = "+compId+" and cmp.resolve_date != 0000-00-00 and  cmp.reject_date= 0000-00-00 and ";
		 }
		 else if(field1.equals("3"))//
		 {
			 subQuery= "cmp.tag_id=4 and cmap.companyid = "+compId+" and cmp.advt_id = "+compId+" and cmp.resolve_date = 0000-00-00 and cmp.reject_date = 0000-00-00  and ";
		 }
		 
		 
		 
		 //time duration check for getting information
		 String timeQuery = "";
		 
		 if(field3.equals("0"))
		 {
			 timeQuery =" and cmp.creation_date  BETWEEN '"+date1+"' and '"+date2+"' ";
		 }
		 else if(field3.equals("1"))
		 {
			//under construction 
		 }
		 else if(field3.equals("2"))
		 {
//			under construction 
		 }
		 
		if(!field1.equals("4")&& !field1.equals("1")) // if field1 is not 1 && 4 . (else of if 1) 
		{
			if(field2.equals("0"))//categorywise
			{
		    strQuery="select cat.cat_name cat_name, subcat.subcat_name subcat_name,  creation_date creation_date, count(*) numcount " +
		    				"from complaints cmp, category cat, complaint_mapping cmap, complaint_data compdata, subcategory subcat " +
		    				"where  "+subQuery +" cmp.category = cat.cat_id  and cmp.category = cmap.categoryid and cmp.complaint_id = compdata.complaintid and  cmap.subcategoryid = compdata.field1 and cmap.subcategoryid = subcat.subcat_id " +timeQuery +
		    				"group by  cmp.category, cmap.subcategoryid, cmp.creation_date " +
		    				"order by cat.cat_name, cmp.creation_date";
		    support.setFieldVec("string", "cat_name");
			support.setFieldVec("string", "subcat_name");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			////////////System.out.println("MIS.....else.....categorywise..................");
			}
			else if(field2.equals("1"))//SubCategorywise wise
			{
			    strQuery="select cat.cat_name cat_name, subcat.subcat_name subcat_name, creation_date creation_date, count(*) numcount " +
			    				"from complaints cmp, category cat,complaint_mapping cmap,complaint_data compdata, subcategory subcat " +
			    				"where "+subQuery +" cmp.category = cat.cat_id and cmp.category = cmap.categoryid and cmp.complaint_id = compdata.complaintid and  cmap.subcategoryid = compdata.field1 and cmap.subcategoryid = subcat.subcat_id " +timeQuery +
			    				"group by  cmp.category, cmap.subcategoryid, cmp.creation_date " +
			    				"order by cat.cat_name, cmp.creation_date";
			    support.setFieldVec("string", "cat_name");
				support.setFieldVec("string", "subcat_name");
				support.setFieldVec("string", "creation_date");								
				support.setFieldVec("int", "numcount");	
				////////////System.out.println("MIS.....else.....categorywise..................");
				}
			else if(field2.equals("2"))//Statewise
			{
		    strQuery="select cat.cat_name cat_name, subcat.subcat_name subcat_name,  creation_date creation_date ,st.statename statename ,pl.Place Place , count(*) numcount " +
		    "from complaints cmp, category cat, complaint_mapping cmap, subcategory subcat,complaint_data compdata, loginmaster lm, places pl, states st " +
		    "where  "+subQuery +" cmp.category = cat.cat_id and lm.userid=cmp.login_id  and cmp.category = cmap.categoryid and cmp.complaint_id = compdata.complaintid and  cmap.subcategoryid = compdata.field1 and cmap.subcategoryid = subcat.subcat_id and st.stateid=lm.state and pl.PlaceID = lm.city  " +timeQuery +
		    "group by  cmp.category, cmap.subcategoryid, cmp.creation_date " +
		    "order by cat.cat_name, cmp.creation_date";

		   
		    support.setFieldVec("string", "cat_name");
			support.setFieldVec("string", "subcat_name");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			 support.setFieldVec("string", "statename");
			    support.setFieldVec("string", "Place");
			    ////////////System.out.println("MIS....else......Statewise..................");
			}
			else if(field2.equals("3"))//citywise
			{
		    strQuery="select cat.cat_name cat_name, subcat.subcat_name subcat_name," +
		    		" creation_date creation_date, count(*) numcount ,pl.Place Place,st.statename statename "+
		    		"from complaints cmp, category cat,complaint_mapping cmap,complaint_data compdata, subcategory subcat, loginmaster lm ,places pl,states st "+
		    		"where "+subQuery +" cmp.category = cat.cat_id and cmp.category = cmap.categoryid and cmp.complaint_id = compdata.complaintid and  cmap.subcategoryid = compdata.field1 and cmap.subcategoryid = subcat.subcat_id "+
	                " and lm.userid=cmp.login_id and pl.PlaceID = lm.city and st.stateid=lm.state "+timeQuery +
		    		"group by  pl.Place,cmp.category,cmap.subcategoryid, cmp.creation_date "+
		    		"order by pl.Place,cat.cat_name,  cmp.creation_date;";
		   
		    support.setFieldVec("string", "cat_name");
			support.setFieldVec("string", "subcat_name");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			support.setFieldVec("string", "Place");
			support.setFieldVec("string", "statename");
			
			 ////////////System.out.println("MIS....else.....citywise..................");
			}
			
						
			else if(field2.equals("4"))//Manager wise time taken (Under construction)
			{
				strQuery="select comp.company_name company_name, cat.cat_name cat_name,  creation_date creation_date, count(*) numcount " +
				"from complaints cmp, category cat,companymaster comp " +
				"where "+subQuery +" cmp.category = cat.cat_id and cmp.advt_id = comp.company_id " +timeQuery +
				"group by  comp.company_name, comp.company_id ,cmp.creation_date " +
				"order by comp.company_name, cmp.creation_date";
				
				support.setFieldVec("string", "company_name");
				support.setFieldVec("string", "cat_name");
				support.setFieldVec("string", "creation_date");								
				support.setFieldVec("int", "numcount");	
			}
		}
		else if (field1.equals("1")) // if field1 is  1  . (else of if 1) 
		
		{
			if(field2.equals("0"))//categorywise
			{
				////////////System.out.println("MIS.....else.....categorywise......field1............");
		    strQuery="select cat.cat_name cat_name, subcat.subcat_name subcat_name,  creation_date creation_date, count(*) numcount " +
		    				"from complaints cmp, category cat, complaint_mapping cmap, complaint_data compdata, subcategory subcat, communication  communi, loginmaster lm " +
		    				"where  "+subQuery +" cmp.category = cat.cat_id and cmp.complaint_id = compdata.complaintid  and  cmap.subcategoryid = compdata.field1 and cmp.category = cmap.categoryid and cmap.subcategoryid = subcat.subcat_id " +timeQuery +
		    				"group by  cmp.category, cmap.subcategoryid, cmp.creation_date " +
		    				"order by cat.cat_name, cmp.creation_date";
		    support.setFieldVec("string", "cat_name");
			support.setFieldVec("string", "subcat_name");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			////////////System.out.println("MIS.....else.....categorywise......field1............");
			}
			else if(field2.equals("1"))//SubCategorywise wise
			{
			    strQuery="select cat.cat_name cat_name, subcat.subcat_name subcat_name, creation_date creation_date, count(*) numcount " +
			    				"from complaints cmp, category cat,complaint_mapping cmap, complaint_data compdata, subcategory subcat, communication  communi, loginmaster lm " +
			    				"where "+subQuery +" cmp.category = cat.cat_id and cmp.category = cmap.categoryid and  cmap.subcategoryid = compdata.field1 and cmp.complaint_id = compdata.complaintid and cat.cat_id = subcat.cat_id and cmap.subcategoryid = subcat.subcat_id " +timeQuery +
			    				"group by  cmp.category, cmap.subcategoryid, cmp.creation_date " +
			    				"order by cat.cat_name, cmp.creation_date";
			    support.setFieldVec("string", "cat_name");
				support.setFieldVec("string", "subcat_name");
				support.setFieldVec("string", "creation_date");								
				support.setFieldVec("int", "numcount");	
				////////////System.out.println("MIS.....else.....categorywise.......field1...........");
				}
			else if(field2.equals("2"))//Statewise
			{
		    strQuery="select cat.cat_name cat_name, subcat.subcat_name subcat_name," +
		    		" creation_date creation_date, count(*) numcount ,st.statename statename ,pl.Place Place "+
		    		"from complaints cmp, category cat,complaint_mapping cmap, complaint_data compdata, subcategory subcat, communication  communi, loginmaster lm , loginmaster lm1 , places pl, states st "+
		    		"where "+subQuery +" cmp.category = cat.cat_id and cmp.complaint_id = compdata.complaintid and  cmap.subcategoryid = compdata.field1 and cmap.subcategoryid = subcat.subcat_id and  cmp.category = cmap.categoryid "+
	                " and lm1.userid=cmp.login_id and st.stateid=lm1.state and pl.PlaceID = lm1.city "+timeQuery +
		    		"group by  st.statename, cmp.category, cmap.subcategoryid, cmp.creation_date "+
		    		"order by st.statename,cat.cat_name,  cmp.creation_date;";
		   
		    support.setFieldVec("string", "cat_name");
			support.setFieldVec("string", "subcat_name");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			 support.setFieldVec("string", "statename");
			    support.setFieldVec("string", "Place");
			    ////////////System.out.println("MIS....else......Statewise.........field1.........");
			}
			else if(field2.equals("3"))//citywise
			{
		    strQuery="select cat.cat_name cat_name, subcat.subcat_name subcat_name," +
		    		" creation_date creation_date, count(*) numcount ,pl.Place Place,st.statename statename "+
		    		"from complaints cmp, category cat,complaint_mapping cmap, complaint_data compdata, subcategory subcat, communication  communi, loginmaster lm , loginmaster lm1 , places pl,states st "+
		    		"where "+subQuery +" cmp.category = cat.cat_id and cmp.complaint_id = compdata.complaintid and  cmap.subcategoryid = compdata.field1 and cmap.subcategoryid = subcat.subcat_id and  cmp.category = cmap.categoryid "+
	                " and lm1.userid=cmp.login_id and pl.PlaceID = lm1.city and st.stateid=lm1.state "+timeQuery +
		    		"group by  pl.Place,cmp.category,cmap.subcategoryid, cmp.creation_date "+
		    		"order by pl.Place,cat.cat_name,  cmp.creation_date;";
		   
		    support.setFieldVec("string", "cat_name");
			support.setFieldVec("string", "subcat_name");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			support.setFieldVec("string", "Place");
			support.setFieldVec("string", "statename");
			
			 ////////////System.out.println("MIS....else.....citywise........field1..........");
			}
			
						
			else if(field2.equals("4"))//Manager wise time taken (Under construction)
			{
				strQuery="select comp.company_name company_name, cat.cat_name cat_name,  creation_date creation_date, count(*) numcount " +
				"from complaints cmp, category cat,companymaster comp " +
				"where "+subQuery +" cmp.category = cat.cat_id and cmp.complaint_id = compdata.complaintid and cmp.advt_id = comp.company_id " +timeQuery +
				"group by  comp.company_name, comp.company_id ,cmp.creation_date " +
				"order by comp.company_name, cmp.creation_date";
				
				support.setFieldVec("string", "company_name");
				support.setFieldVec("string", "cat_name");
				support.setFieldVec("string", "creation_date");								
				support.setFieldVec("int", "numcount");	
			}
		}
		else // if field1 is 4 . (else of if 1) 
		{
			if(field2.equals("0"))         //recieived //(Under construction)
			 {
				 subQuery = "";
			 }
			 else if(field2.equals("1"))  //Attended //(Under construction)
			 {
				 subQuery = "cmp.brandFlag=1 and ";
			 }
			 else if(field2.equals("2"))  //closed Resolved //(Under construction)
			 {
				 subQuery= "cmp.tag_id=4 and cmp.resolve_date != 0000-00-00 and  cmp.reject_date= 0000-00-00 and ";
			 }
			 else if(field2.equals("3"))  //closed unresolved //(Under construction)
			 {
				 subQuery= "cmp.tag_id = 4 and cmp.resolve_date = 0000-00-00 and cmp.reject_date = 0000-00-00  and ";
			 }
			 
			 else if(field2.equals("4")) //time wise (Under construction)
			 {
				 subQuery= "";
			 }
			strQuery= "select cat.cat_name cat_name, comp.company_name company_name," +
    		" creation_date creation_date, count(*) numcount ,lm1.first_name first_name,st.statename statename "+
    		"from complaints cmp, category cat,companymaster comp,loginmaster lm ,loginmaster lm1 ,places pl,states st "+
    		"where "+subQuery +"cmp.category = cat.cat_id and cmp.advt_id = comp.company_id and lm1.userid = cmp.cu_id "+
           " and lm.userid=cmp.login_id and pl.PlaceID = lm.city and st.stateid=lm.state "+timeQuery +
    		"group by  lm1.first_name,cmp.category, comp.company_id ,cmp.creation_date "+
    		"order by lm1.first_name,cat.cat_name,  cmp.creation_date;";
		 	support.setFieldVec("string", "cat_name");
			support.setFieldVec("string", "company_name");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			support.setFieldVec("string", "first_name");
			support.setFieldVec("string", "statename");
		}
		
	}
		
		////////////System.out.println("MIS..........ADVT.................."+strQuery);
		resultVec = masterUtil.getList(ds, strQuery, support);
		//////////System.out.println("MIS..resultVec........ADVT.................."+resultVec);
		return resultVec;
	}
	
	
	
	
	/**
	 * **********************************************************************************************************
	*This function add all the data of Professional Experience in complaints table .
	*and return the result string Success or failure accordiong to query result.
*	************************************************************************************************************
*	*/
	public String insertTab(DataSource ds,Vector paramVec){
		
		
		Support support=new Support();
		String strQuery="";
		String strResult = "";
		strQuery = "Insert into comppro_data (companyid, tab1, tab2, tab3, "+
		"tab4,tab5,text1, "+
		"text2,text3,text4,text5,modify_date) Values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		System.out.println("strQuerystrparamVec>>>>>paramVec>>>>>"+paramVec);	
		support.setDataVec("string", paramVec.elementAt(0).toString().trim()); //companyid
		support.setDataVec("string", paramVec.elementAt(1).toString().trim()); //tab1
		support.setDataVec("string", paramVec.elementAt(2).toString().trim()); //tab1
		support.setDataVec("string", paramVec.elementAt(3).toString().trim()); //tab1
		support.setDataVec("string", paramVec.elementAt(4).toString().trim()); //tab1
		support.setDataVec("string", paramVec.elementAt(5).toString().trim()); //tab1
		support.setDataVec("string", paramVec.elementAt(6).toString().trim()); //text3
		support.setDataVec("string", paramVec.elementAt(7).toString().trim()); //text3
		support.setDataVec("string", paramVec.elementAt(8).toString().trim()); //text3
		support.setDataVec("string", paramVec.elementAt(9).toString().trim()); //text3
		support.setDataVec("string", paramVec.elementAt(10).toString().trim()); //text3
		support.setDataVec("string", paramVec.elementAt(11).toString().trim()); //modify_date
		System.out.println("strQuerystrQuerystrQuery>>>>>>>>>>"+strQuery);	
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
		
	}
	
	/**
	 * **************************************************************************************************
     *	update  Experience information in proffisionalExp table and return result status result string.
	 *********************************************************************************************************
	 */	
	public String updateTab(DataSource ds,Vector paramVec){
		////System.out.println("paramVecin indvmaster>>>>>>>>>"+paramVec);
		Support support=new Support();
		String strQuery="";
	    String strResult = "";
	   
	    String tab = paramVec.elementAt(2).toString().trim(); //tab
	    String text = paramVec.elementAt(4).toString().trim(); //text
	  	strQuery = "Update comppro_data set "+tab+"=? , "+text+"=?, modify_date=? where companyid=?";
	
		support.setDataVec("string", paramVec.elementAt(3).toString().trim()); //tab1
		support.setDataVec("string", paramVec.elementAt(5).toString().trim()); //tab1		
		support.setDataVec("string", paramVec.elementAt(1).toString().trim()); //text3
		support.setDataVec("int", paramVec.elementAt(0).toString().trim()); //modify_date
	
		////System.out.println("strQuery indvmaster>>>>>>>>>"+strQuery);
				
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
		
	}
	
	
	/*
	 * function for MIS
	 */
	
	/**
	 * This function is use for getting all List of tests of a  Company and no of student take that test.
	 * 
	 */
	public Vector getTestNameAndStu(DataSource ds, Vector paramVec) {
		Support support=new Support();
		
		String companyid=paramVec.elementAt(0).toString();
		
		
		//String strQuery = "select tm.sno sno, tm.testname testname, tm.testcategory testcategory, tm.testtype testtype , t.user_id user_id from test_mapping tm, test t  where tm.sno = t.test_id and tm.companyid= '"+companyid;
		String strQuery = "select * from test_mapping where companyid= '"+companyid+"' ";
																	
		/*
		 * set all the fileds type and name for blogs table by using Support
		 * class object.
		 */
		support.setFieldVec("int", "sno");	// 0
		support.setFieldVec("string", "testname");	// 1
		support.setFieldVec("string", "testtype");		// 2
		support.setFieldVec("String", "testcategory");				// 3
		
		
		System.out.println("strQuery...in test list...."+strQuery);
		return masterUtil.getList(ds, strQuery, support);
		
	}
	
	public int gettotalCount(DataSource ds, String testid) {
		
		
		
		int result= 0;
		
		
		//String strQuery = "select tm.sno sno, tm.testname testname, tm.testcategory testcategory, tm.testtype testtype , t.user_id user_id from test_mapping tm, test t  where tm.sno = t.test_id and tm.companyid= '"+companyid;
		String strQuery = "select count(ID) tno from test where test_id= '"+testid+"' ";
																	
		/*
		 * set all the fileds type and name for blogs table by using Support
		 * class object.
		 */
	
		
		return result = masterUtil.getCount(ds, strQuery);
		
		
	}
	
	
	public int gettotalMentoringCount(DataSource ds, String compId) {
		
		
		
		int result= 0;
		
		
		//String strQuery = "select tm.sno sno, tm.testname testname, tm.testcategory testcategory, tm.testtype testtype , t.user_id user_id from test_mapping tm, test t  where tm.sno = t.test_id and tm.companyid= '"+companyid;
		String strQuery = "select count(complaint_id) count from complaints where advt_id= '"+compId+"' and q_type='Trequest'";
																	
		/*
		 * set all the fileds type and name for blogs table by using Support
		 * class object.
		 */
		return result = masterUtil.getCount(ds, strQuery);
	}
	
	
		public int gettotalQueriesCount(DataSource ds, String compId, String quretype) {
		
		
		int result= 0;
		
		//String strQuery = "select tm.sno sno, tm.testname testname, tm.testcategory testcategory, tm.testtype testtype , t.user_id user_id from test_mapping tm, test t  where tm.sno = t.test_id and tm.companyid= '"+companyid;
		String strQuery = "select count(complaint_id) count from complaints where advt_id= '"+compId+"' and q_type='"+quretype+"'";
																	
		/*
		 * set all the fileds type and name for blogs table by using Support
		 * class object.
		 */
		return result = masterUtil.getCount(ds, strQuery);
	}
	
	
		public Vector gettotalQueriesAneCount(DataSource ds, String compId, String quretype) {
			
			
			Support support=new Support();
			
			//String strQuery = "select tm.sno sno, tm.testname testname, tm.testcategory testcategory, tm.testtype testtype , t.user_id user_id from test_mapping tm, test t  where tm.sno = t.test_id and tm.companyid= '"+companyid;
			String strQuery = "select distinct(complaint_id) from complaints c, communication comm where c.advt_id= '"+compId+"' and c.q_type='"+quretype+"' and  c.complaint_id = comm.complaintid";
			
			System.out.println("strQuery...in test list...."+strQuery);
			
			/*
			 * set all the fileds type and name for blogs table by using Support
			 * class object.
			 */
			return masterUtil.getList(ds, strQuery, support);
		}
		
		
		public Vector getMenAcseptRejectCount(DataSource ds, String compId, String quretype) {
			
			
			Support support=new Support();
			
			//String strQuery = "select tm.sno sno, tm.testname testname, tm.testcategory testcategory, tm.testtype testtype , t.user_id user_id from test_mapping tm, test t  where tm.sno = t.test_id and tm.companyid= '"+companyid;
			String strQuery = "select distinct(complaint_id) from complaints c, communication comm where c.advt_id= '"+compId+"' and c.q_type='Mrequest' and  c.complaint_id = comm.complaintid and  comm.a_type = '"+quretype+"'";
			
			System.out.println("strQuery...in test list...."+strQuery);
			
			/*
			 * set all the fileds type and name for blogs table by using Support
			 * class object.
			 */
			return masterUtil.getList(ds, strQuery, support);
		}
	
		/**
		 * Added By Ajay Kumar Jha
		 * @param ds
		 * @param numFacilityId
		 * @return
		 */
		public Vector getShiftList(DataSource ds, int numFacilityId) {
			
			
			Support support=new Support();
			
			
			String strQuery = "select * from staff_shift_master where isvalid=1 and facility_id="+numFacilityId;
			support.setFieldVec("int", "shift_id");	// 0
			support.setFieldVec("string", "shift_name");	// 1
			support.setFieldVec("string", "shiftstarttime");		// 2
			support.setFieldVec("String", "shiftendtime");				// 3
			System.out.println("strQuery...in test list...."+strQuery);
			
			/*
			 * set all the fileds type and name for blogs table by using Support
			 * class object.
			 */
			return masterUtil.getList(ds, strQuery, support);
		}
		
		/**
		 * Added by Ajay Kumar Jha
		 * @param ds
		 * @param numFacilityId
		 * @param numSiftId
		 * @param strDate
		 * @return
		 */
		public Vector getShiftSchedule(DataSource ds, int numFacilityId, int numSiftId, String strDate) {
			
			
			Support support=new Support();
			
			//select s.scheduleid sid, s.schedule_date sdate, sm.staff_id stid, sm.staffname sname from staff_schedule s, staff_master sm  where sm.staff_id= s.staff_id and s.facilityid=1 and s.shift_id=29 and s.schedule_date="02/10/2009";
			String strQuery = "select s.sheduleid sid, s.schedule_date sdate, sm.staff_id stid, sm.staffname sname from staff_schedule s, staff_master sm  where sm.staff_id= s.staff_id and s.facilityid="+numFacilityId+" and s.shift_id="+numSiftId+" and s.schedule_date='"+strDate.trim()+"'";
			support.setFieldVec("int", "sid");	// 0
			support.setFieldVec("string", "sdate");	// 1
			support.setFieldVec("int", "stid");		// 2
			support.setFieldVec("string", "sname");				// 3
			System.out.println("strQuery...getShiftSchedule list...."+strQuery);
			
			/*
			 * set all the fileds type and name for blogs table by using Support
			 * class object.
			 */
			return masterUtil.getList(ds, strQuery, support);
		}
	
					
					
}