package com.mm.core.master;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.sql.DataSource;


import com.mm.core.resource.*;
import com.mm.struts.entp.form.CommunitcustForm;
import com.mm.struts.corpo.form.ComplaintForm;
import com.mm.struts.corpo.form.ManipulatesubcatForm;
/**
 * @author dalchand
 * Date 14/5/2007
 */
/**
 * This class is use for performing all function of entp.
 * it creats a MasterUtility class object for performing basic operation
 * upon the Data Base.
 */

public class EntpMaster {
		
	MasterUtility masterUtil = new MasterUtility();
	
	
	
	/**********************************************************************************************************************************************
	This function created to get All existing complains list, Current complains list and under Process complains list.
***********************************************************************************************************************************************/
	public Vector getComplainsList(DataSource ds,Vector dataVec)
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
		String strSearchType = dataVec.elementAt(0).toString().trim();
		int min = Integer.parseInt(dataVec.elementAt(1).toString().trim());
		int max = Integer.parseInt(dataVec.elementAt(2).toString().trim());	
		String fieldVal = dataVec.elementAt(3).toString().trim();
		String loginId = dataVec.elementAt(4).toString().trim();
		String companyId = dataVec.elementAt(5).toString().trim();
		int adminFlag = Integer.parseInt(dataVec.elementAt(6).toString().trim());
		
		Support support=new Support();
	    String strQuery="";
		   
		
		
			if(strSearchType.equalsIgnoreCase("All"))
			
				strQuery = "select * from complaints where cu_id = '"+loginId+"' and entp_id = '"+companyId+"'  order by "+fieldVal+"   limit "+min+", "+max;
			else if(strSearchType.equalsIgnoreCase("current"))
					
				strQuery = "select * from complaints where cu_id = '"+loginId+"' and entp_id = '"+companyId+"' and brandFlag = 0 and tag_id != 3 and tag_id != 4 order by "+fieldVal+"   limit "+min+", "+max;
				
			else if(strSearchType.equalsIgnoreCase("under"))
			
				strQuery = "select * from complaints where cu_id = '"+loginId+"' and entp_id = '"+companyId+"' and brandFlag = 1 and tag_id != 3 and tag_id != 4  order by "+fieldVal+"  limit "+min+", "+max;
			
			else if(strSearchType.equalsIgnoreCase("resolved"))
				
				strQuery = "select * from complaints where cu_id = '"+loginId+"' and entp_id = '"+companyId+"' and brandFlag = 1 and tag_id = 3   order by "+fieldVal+"  limit "+min+", "+max;
			else if(strSearchType.equalsIgnoreCase("closed"))
			{
				int lowerTab = Integer.parseInt(dataVec.elementAt(7).toString().trim());
				if(lowerTab == 1)
				{
					strQuery = "select * from complaints where cu_id = '"+loginId+"' and entp_id = '"+companyId+"' and brandFlag = 1  and tag_id = 4 and resolve_date != '0000-00-00' and  reject_date= '0000-00-00'  order by "+fieldVal+"  limit "+min+", "+max;
				}
				else if(lowerTab == 2)
				{
					strQuery = "select * from complaints where cu_id = '"+loginId+"' and entp_id = '"+companyId+"' and brandFlag = 1  and tag_id = 4 and resolve_date = '0000-00-00' and reject_date = '0000-00-00'  order by "+fieldVal+"  limit "+min+", "+max;
				}
				else if(lowerTab == 3)
				{
					strQuery = "select * from complaints where cu_id = '"+loginId+"' and entp_id = '"+companyId+"' and  tag_id = 4 and reject_date!= '0000-00-00' order by "+fieldVal+"  limit "+min+", "+max;
				}
		
			}
				
				
			
		
		
		support.setFieldVec("int", "complaint_id");
		support.setFieldVec("string", "fcom_id");
		support.setFieldVec("string", "complaint_title");								
		support.setFieldVec("string", "creation_date");	
		support.setFieldVec("string", "creation_time");
		support.setFieldVec("string", "q_type");
		
		
		resultVec = masterUtil.getList(ds, strQuery, support);
		return resultVec;
	}
	/**
	* This function use for getting total no. of complaints in a particuler core user acounts.
	* List of complaints are search on the base of All, Current and Under process complaints.
	* @return
	*/	
	public int getComplaintCount(DataSource ds,Vector dataVec){
			int result = 0;
			
		    String strQuery="";
			/************************************************************************************
			dataVec.elementAt(0) = Search Type;	
			dataVec.elementAt(1) = cuId;//loginId
			dataVec.elementAt(2) = entp_id;//companyId
			dataVec.elementAt(3) = adminFlag;
						
		*************************************************************************************/
			String strSearchType = dataVec.elementAt(0).toString().trim();
			String loginId = dataVec.elementAt(1).toString().trim();
			String companyId = dataVec.elementAt(2).toString().trim();
			int adminFlag = Integer.parseInt(dataVec.elementAt(3).toString().trim());
						
			
			
				if(strSearchType.equalsIgnoreCase("All"))
					strQuery = "SELECT count(*) count from complaints where cu_id = '"+loginId+"' and entp_id = '"+companyId+"'";
					
				else if(strSearchType.equalsIgnoreCase("current"))
					strQuery = "SELECT count(*) count from complaints where cu_id = '"+loginId+"' and entp_id = '"+companyId+"' and brandFlag = 0 and tag_id != 3 and tag_id != 4";
					
			
				else if(strSearchType.equalsIgnoreCase("under"))
					strQuery = "SELECT count(*) count from complaints where cu_id = '"+loginId+"' and entp_id = '"+companyId+"' and brandFlag = 1 and tag_id != 3 and tag_id != 4";
				
				else if(strSearchType.equalsIgnoreCase("resolved"))
					strQuery = "SELECT count(*) count from complaints where cu_id = '"+loginId+"' and entp_id = '"+companyId+"' and brandFlag = 1 and tag_id = 3";
				
				else if(strSearchType.equalsIgnoreCase("closed"))
				{
					int lowerTab = Integer.parseInt(dataVec.elementAt(4).toString().trim());
					if(lowerTab == 1)
					{
						strQuery = "SELECT count(*) count from complaints where cu_id = '"+loginId+"' and entp_id = '"+companyId+"' and brandFlag = 1 and tag_id = 4 and resolve_date != '0000-00-00' and  reject_date= '0000-00-00'";
					}
					else if(lowerTab == 2)
					{
						strQuery = "SELECT count(*) count from complaints where cu_id = '"+loginId+"' and entp_id = '"+companyId+"' and brandFlag = 1 and tag_id = 4 and resolve_date = '0000-00-00' and reject_date = '0000-00-00' ";
					}
					else if(lowerTab == 3)
					{
						strQuery = "SELECT count(*) count from complaints where cu_id = '"+loginId+"' and entp_id = '"+companyId+"' and tag_id = 4  and reject_date!= '0000-00-00'";
					}
				}
					
				
			
					result = masterUtil.getCount(ds, strQuery);
								
			return result;
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
		String cuId = dataVec.elementAt(4).toString().trim();		
		Support support=new Support();
	    String strQuery="";
		
		if(cuId.equals("0"))
		{
			strQuery = "select * from complaints where cu_id = '"+cuId+"' and entp_id = '"+companyId+"'  order by "+fieldVal+"  limit "+min+", "+max;
		}
		else
		{
			strQuery = "select * from complaints where tag_id=1 and  cu_id = '"+cuId+"' and entp_id = '"+companyId+"'  order by "+fieldVal+"  limit "+min+", "+max;
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
			String cuId = dataVec.elementAt(1).toString().trim();
			
			if(cuId.equals("0"))
			{
				strQuery = "SELECT count(*) count from complaints where cu_id = '"+cuId+"' and entp_id ="+companyId;
			}
			else
			{
				strQuery = "SELECT count(*) count from complaints where tag_id = 1 and  cu_id = '"+cuId+"' and entp_id ="+companyId;
			}
						
					result = masterUtil.getCount(ds, strQuery);
								
			return result;
		}
	
	/**
	* This function use for getting total no. of complaints in a particuler core user acounts.
	* List of complaints are search on the base of All, Current and Under process complaints.
	* @return
	*/	
	public int getOtherCompTotalCount(DataSource ds,Vector dataVec){
			int result = 0;
			
		    String strQuery="";
			/************************************************************************************
			
			dataVec.elementAt(0) = cuId;//loginId
			
						
		*************************************************************************************/
			
			String companyId = dataVec.elementAt(0).toString().trim();
					
			
				strQuery = "SELECT count(*) count from complaints where tag_id = 1 and cu_id!=0 and entp_id ="+companyId;
			
						
					result = masterUtil.getCount(ds, strQuery);
								
			return result;
		}
	
	
	/********************end of code unallotted***********************************/
	/**
	 * This function is use for getting list of  Companys from companymaster.
	 * It returns a list within limit which is given by min and max variable.
	 */
		public Vector getCompanyList(DataSource ds,Vector paramVec){
			
			Vector resultVec = new Vector();
			int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
			int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
			String fieldVal = paramVec.elementAt(2).toString().trim();
			
			Support support=new Support();
			   String strQuery="";
			
				strQuery = "select * from companymaster  order by "+fieldVal+" LIMIT "+min+", "+max;
			
			
			/**
			 * set all the fileds type and name for companymaster table by using
			 * Support class object.
			 */
			support.setFieldVec("int", "company_id");
			support.setFieldVec("string", "company_sname");								
			support.setFieldVec("string", "company_name");	
			support.setFieldVec("string", "typeofcompany");
			support.setFieldVec("int", "active");
			
			resultVec = masterUtil.getList(ds, strQuery, support);
			return resultVec;
		}
	
	/**
	 * This function use for getting total no. of companys .
	 * @return
	 */	
		public int getCompanyCount(DataSource ds,Vector paramVec){
			
			int result = 0;
			
									
			   String strQuery="";
			
				strQuery = "Select count(*) rowCount from companymaster";
			
			result = masterUtil.getCount(ds, strQuery);
			return result;
		}
		
		
		/**
		 * This function is use for getting list of  User from `loginmaster`.
		 * It returns a list within limit which is given by min and max variable.
		 */
			public Vector getUserList(DataSource ds,Vector paramVec){
				
				//////////System.out.println("In siet Entp Master getUserList");
				
				Vector resultVec = new Vector();
				int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
				int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
				int companyid = Integer.parseInt(paramVec.elementAt(2).toString().trim());
				String fieldVal = paramVec.elementAt(3).toString().trim();
				
				Support support=new Support();
				   String strQuery="";
				
					strQuery = "select * from loginmaster where companyid="+companyid+"  and logindeletion=0  order by "+fieldVal+" LIMIT "+min+", "+max;
				
				
				/**
				 * set all the fileds type and name for loginmaster table by using
				 * Support class object.
				 */
				support.setFieldVec("int", "companyid");
				support.setFieldVec("string", "loginname");								
				support.setFieldVec("string", "first_name");
				support.setFieldVec("int", "userid");
				
				
				
				
				resultVec = masterUtil.getList(ds, strQuery, support);
				
				//////////System.out.println("In siet Advt Master resultVec"+resultVec);
				return resultVec;
			}
			
			
			/**
			 * This function is use for getting list of all User from `loginmaster`.
			 * It returns a list within limit which is given by min and max variable.
			 */
				public Vector getAllUserList(DataSource ds,int companyid){
					
					//////////System.out.println("In siet Entp Master getUserList");
					Vector resultVec = new Vector();
					Support support=new Support();
					   String strQuery="";
					
						strQuery = "select * from loginmaster where companyid="+companyid;
					
					
					/**
					 * set all the fileds type and name for loginmaster table by using
					 * Support class object.
					 */
					support.setFieldVec("int", "userid");
					support.setFieldVec("string", "first_name");
					support.setFieldVec("string", "last_name");
					support.setFieldVec("string", "email");
				
					
					resultVec = masterUtil.getList(ds, strQuery, support);
					
					//////////System.out.println("In siet Advt Master resultVec"+resultVec);
					return resultVec;
				}
			
		
		/**
		 * This function use for getting total no. of User in the company .
		 * @return
		 */	
			public int getUserCount(DataSource ds,Vector paramVec){
				
				int companyid = Integer.parseInt(paramVec.elementAt(0).toString().trim());
				//////////System.out.println("In siet Advt Master getUserCount");
				int result = 0;
				
										
				   String strQuery="";
				
					strQuery = "Select count(*) rowCount from loginmaster where  logindeletion=0 and companyid="+companyid;
					
					result = masterUtil.getCount(ds, strQuery);
				//result = masterUtil.getCount(ds, strQuery);
				//////////System.out.println("In EntpMaster strQuery...."+strQuery);
				//////////System.out.println("In EntpMaster result...."+result);
				return result;
			}
		
	
	/*
	 * This function get the admin User of Compny from loginmaster and loginrights tables .
	 * takes companyid  as argument and return admin_flag.
	 */
	
	public int getCompnyAdminUserId(DataSource ds,int companyid)
	{
		int result = 0;		
		
	    String strQuery="";
		
		strQuery= "SELECT userid from loginmaster,loginrights where admin_flag= '1' and loginmaster.rid = loginrights.rid and loginmaster.companyid="+companyid;
		
		
		result = masterUtil.getId(ds, strQuery, "userid");	
		
		return result;
		
	}
	/*
	 * This function get the admin_flag from loginmaster and loginrights  tables. 
	 * takes userid as argument and  return admin_flag.
	 */
	
	public int getAdminFlsgs(DataSource ds,int userid)
	{
		int result = 0;		
		
	    String strQuery="";
		
		strQuery= "SELECT admin_flag from loginmaster,loginrights where loginmaster.rid = loginrights.rid and userid="+userid;
		
		
		result = masterUtil.getId(ds, strQuery, "admin_flag");	
		
		return result;
		
	}
	/*
	 * This function get the brandFlag from complaints table of MMDB.
	 */
	
	public int getBrandFlag(DataSource ds,int complaint_id)
	{
		int result = 0;
		
	    String strQuery="";
		strQuery= "select brandFlag from complaints where complaint_id = "+complaint_id;
		
		
		result = masterUtil.getId(ds, strQuery, "brandFlag");	
		
		return result;
		
	}
	/**
	 * This function use for Update  comment text and publish_flag  or suspend_flag into commentsoncomplaints table.It returns status of Updatetion.
	 */
		public String insertComment(DataSource ds,Vector dataVec)
		{
			
			String strResult = "";
			Support support=new Support();
		    String strQuery="";
			strQuery = "update commentsoncomplaints set commenttext= ? , publish_flag=? , suspend_flag=?  where commentid= ?";
			
			support.setDataVec("string", dataVec.elementAt(1).toString().trim());
			support.setDataVec("int", dataVec.elementAt(2).toString().trim());
			support.setDataVec("int", dataVec.elementAt(3).toString().trim());
			
			support.setDataVec("int", dataVec.elementAt(0).toString().trim());
			
			
			strResult = masterUtil.setData(ds, strQuery, support);
			return strResult;
		}
	/******************************************************************************************************************
    This method takes dataVec as argument and returns success if complain's brandFlag field updated
	 successfully else return false.
********************************************************************************************************************/

	public String updateBrandFlag(DataSource ds,Vector dataVec)
	{
		String strResult = "";
		Support support=new Support();
	    String strQuery="";
		/*************************************************************************************************
		dataVec.elementAt(0).toString().trim() = blogId;
		dataVec.elementAt(1).toString().trim() = brandFlag;
		dataVec.elementAt(2).toString().trim() = publish_flag;
*************************************************************************************************/
		
		int complaint_id = Integer.parseInt(dataVec.elementAt(0).toString().trim());
		int brandFlag= Integer.parseInt(dataVec.elementAt(1).toString().trim());
		int publish_flag= Integer.parseInt(dataVec.elementAt(2).toString().trim());
		
		if(brandFlag==0)
		{
			/*
			 * dataVec.elementAt(2).toString().trim() = publish_flag;
			 */
			if(publish_flag==0)	
			{
				strQuery ="update complaints set brandFlag = '1', publish_flag = ? where complaint_id="+complaint_id; 
				
				support.setDataVec("int", dataVec.elementAt(2).toString().trim());
			}
			/*
			 * dataVec.elementAt(3).toString().trim() = publish_date;
			 * dataVec.elementAt(4).toString().trim() = publish_time;
			 */	
		
			else 
			{
				strQuery ="update complaints set brandFlag = '1', publish_flag = ?, publish_date = ?, publish_time = ? where complaint_id="+complaint_id;
			
				support.setDataVec("int", dataVec.elementAt(2).toString().trim());
				support.setDataVec("string", dataVec.elementAt(3).toString().trim());
				support.setDataVec("string", dataVec.elementAt(4).toString().trim());
			}
			
		}
			
	
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
	
	}
	/*************************************************************************************
	This function created for get the details of particuler Complains in Core.
**************************************************************************************/
	/**
	 * This function is use for getting all complaint detail 
	 * of a particuler complaint from complaints table.
	 */
	
	public Vector getComplaintDetails(DataSource ds,Vector dataVec){
		
		Vector resultVec = new Vector();
		int complaint_id = Integer.parseInt(dataVec.elementAt(0).toString().trim());
		int cu_id= Integer.parseInt(dataVec.elementAt(1).toString().trim());
		String adminFlag= dataVec.elementAt(2).toString().trim();
		Support support=new Support();
	    String strQuery="";;	
	    
	    	
			strQuery = "SELECT * FROM complaints where complaint_id= "+complaint_id+" and cu_id= "+cu_id;	
	    
		
		
		/**
		 * set all the fileds type and name for complaints table by using
		 * Support class object.
		 */
			support.setFieldVec("int", "complaint_id");             //0
			support.setFieldVec("string", "complaint_title");		//1
			support.setFieldVec("string", "complaint_text");		//2
			support.setFieldVec("string", "relevent_info");			//3	
			support.setFieldVec("int", "category");					//4			
			support.setFieldVec("string", "creation_date"); 		//5
			support.setFieldVec("string", "publish_date");			//6
			support.setFieldVec("string", "creation_time");  		//7
			support.setFieldVec("string", "publish_time");          //8
			support.setFieldVec("string", "lastmodify_date");       //9
			support.setFieldVec("string", "lastmodify_time");      //10
			support.setFieldVec("int", "login_id");                //11
			support.setFieldVec("int", "companyid");               //12
			support.setFieldVec("string", "timepart");             //13
			support.setFieldVec("int", "viewcount");               //14
			support.setFieldVec("int", "entp_id");                 //15
			support.setFieldVec("int", "cu_id");                   //16	
			support.setFieldVec("String", "fcom_id");              //17
			support.setFieldVec("int", "advt_id");                 //18
			support.setFieldVec("int", "advtL_id");                //19
			support.setFieldVec("int", "dealer_id");               //20
			support.setFieldVec("int", "tag_id");                  //21
			support.setFieldVec("String", "resolve_date");         //22
			support.setFieldVec("String", "closed_date");          //23
			support.setFieldVec("int", "brandFlag");               //24
			support.setFieldVec("int", "publish_flag");            //25
			support.setFieldVec("string", "publish_on");           //26
			support.setFieldVec("string", "other");                //27
			support.setFieldVec("string", "q_type");                //28
		
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		return resultVec;
	}
	
	/**
	 * This function is use for getting company_id/ company_type
	 * of a particuler company  from companymaster table.
	 * takes company_sname  as argument and return a data vector.
	 */
	
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
	/**
	 * This function is use for getting all complaint detail of a particuler
	 * complaint.
	 */
	public Vector getCommentDetails(DataSource ds, int commentId) {
		Support support=new Support();
		String strQuery = "select c.commentid commentid, c.commenttext commenttext,c.commentdate commentdate ,l.first_name commentfName,l.last_name commentlname, " +
				"l1.first_name firstName, l1.last_name lastName, comp.complaint_text complaint_text" +
				" from commentsoncomplaints c, loginmaster l1,complaints comp, loginmaster l WHERE commentid="+commentId+
				" and c.userid=l.userid and c.complaintid = comp.complaint_id and comp.login_id =l1.userid";
		/*
		 * set all the fileds type and name for blogs table by using Support
		 * class object.
		 */
		support.setFieldVec("int", "commentid");		// 0
		support.setFieldVec("string", "commenttext");	// 1
		support.setFieldVec("string", "commentdate");	// 2
		support.setFieldVec("string", "commentfName");	// 3
		support.setFieldVec("string", "commentlname");	// 4
		support.setFieldVec("string", "firstName");		// 5
		support.setFieldVec("string", "lastName");		// 6
		support.setFieldVec("string", "complaint_text");// 7
		
		return masterUtil.getDetail(ds, strQuery, support);
	}

	
	public Vector getCompanyId(DataSource ds,String  company_sname){
		
		Vector resultVec = new Vector();
		Support support=new Support();
	    String strQuery="";				
		
			strQuery = "select * from companymaster where company_sname= '"+company_sname+"'";		
		
		/**
		 * set all the fileds type and name for companymaster table by using
		 * Support class object.
		 */
		support.setFieldVec("int", "company_id");
		support.setFieldVec("string", "typeofcompany");
							
		
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		return resultVec;
		}
	/**
	 * This function is use for getting detail 
	 * of a particuler delar  from dealer table.
	 */
	
	public Vector getDealerDetail(DataSource ds,int  dealerid){
		
		Vector resultVec = new Vector();
		Support support=new Support();
	    String strQuery="";
		strQuery = "SELECT  * FROM dealer where dealerid="+dealerid;		
		
		/**
		 * set all the fileds type and name for dealer table by using
		 * Support class object.
		 */
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
								
		
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		return resultVec;
		}
	
		
	/**
	 * 
	 */
	public String insertCoreUserId(DataSource ds,Vector paramVec)
	{
		String strResult = "";	
		Support support=new Support();
	    String strQuery="";
		
		strQuery = "Insert into complaintallocation (cuid,availiblity,allocationcriteria,)Values(?, ?, ?)";
		
		support.setDataVec("int", paramVec.elementAt(0).toString().trim());
		support.setDataVec("string", paramVec.elementAt(1).toString().trim());
		support.setDataVec("string", paramVec.elementAt(2).toString().trim());
		
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;	
		
	}
	 /******************************************************************************************************************
    This method takes dataVec as argument and draft infoand returns a success/failure messg. after adding dealer info.in the dealer table
********************************************************************************************************************/
	public String insertDealerInfo(DataSource ds,Vector paramVec){
		
		String strResult = "";	
		Support support=new Support();
	    String strQuery="";
		strQuery = "Insert into dealer(dealername,address, country, state,"+
		"city, district, contactperson, phonenumber, mobileno, email, pincode) Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		support.setDataVec("string", paramVec.elementAt(0).toString().trim());
		support.setDataVec("string", paramVec.elementAt(1).toString().trim());
		support.setDataVec("int", paramVec.elementAt(2).toString().trim());
		support.setDataVec("int", paramVec.elementAt(3).toString().trim());
		support.setDataVec("int", paramVec.elementAt(4).toString().trim());
		support.setDataVec("int", paramVec.elementAt(5).toString().trim());
		support.setDataVec("string", paramVec.elementAt(6).toString().trim());
		support.setDataVec("string", paramVec.elementAt(7).toString().trim());
		support.setDataVec("string", paramVec.elementAt(8).toString().trim());
		support.setDataVec("string", paramVec.elementAt(9).toString().trim());
		support.setDataVec("int", paramVec.elementAt(10).toString().trim());
		
		
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;	
				
	}
	/**
	 *  This function Update the Company Information in the `companymaster` table.
	 */
public String updateCompanyInfo(DataSource ds,Vector paramVec){
		
		String strResult = "";	
		Support support=new Support();
	    String strQuery="";
	    String tempcat = paramVec.elementAt(10).toString().trim();
	    
	    if(tempcat.length()==0){
		strQuery = "Update companymaster set company_name=?, company_address1=?, company_address2=?, company_city=?,"+
		"company_state=?, company_country=?, company_zip=?, company_email=?, company_phone=? where company_id=?";		
		
		
		support.setDataVec("string", paramVec.elementAt(1).toString().trim());
		support.setDataVec("string", paramVec.elementAt(2).toString().trim());
		support.setDataVec("string", paramVec.elementAt(3).toString().trim());
		support.setDataVec("string", paramVec.elementAt(4).toString().trim());
		support.setDataVec("string", paramVec.elementAt(5).toString().trim());
		support.setDataVec("string", paramVec.elementAt(6).toString().trim());
		support.setDataVec("string", paramVec.elementAt(7).toString().trim());
		support.setDataVec("string", paramVec.elementAt(8).toString().trim());
		support.setDataVec("string", paramVec.elementAt(9).toString().trim());
		
		support.setDataVec("int", paramVec.elementAt(0).toString().trim());
	    }
	    else{
	    	strQuery = "Update companymaster set company_name=?, company_address1=?, company_address2=?, company_city=?,"+
			"company_state=?, company_country=?, company_zip=?, company_email=?, company_phone=?, category=? where company_id=?";		
			
			
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
			
			support.setDataVec("int", paramVec.elementAt(0).toString().trim());
	    }
		
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;	
				
	}

	public String updateCompanyData(DataSource ds,Vector paramVec){
	
	String strResult = "";	
	Support support=new Support();
    String strQuery="";
    String tempcat = paramVec.elementAt(10).toString().trim();
    
   
    	strQuery = "Update companymaster set company_name=?, company_address1=?, company_address2=?, company_city=?,"+
		"company_state=?, company_country=?, company_zip=?, company_email=?, company_phone=?, mtype=?,short_pro=? where company_id=?";		
		
		
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
		support.setDataVec("string", paramVec.elementAt(11).toString().trim());
		
		support.setDataVec("int", paramVec.elementAt(0).toString().trim());
  
	
	
	strResult = masterUtil.setData(ds, strQuery, support);
	return strResult;	
			
}
	
	public String updateMentoringCat(DataSource ds,Vector paramVec){
		
		String strResult = "";	
		Support support=new Support();
	    String strQuery="";
	   	    
	   
	    	strQuery = "Update loginmaster set mentor_cat=? where userid=?";		
			
	    	
			support.setDataVec("string", paramVec.elementAt(1).toString().trim());		
			
			support.setDataVec("int", paramVec.elementAt(0).toString().trim());
	  
			//System.out.println("strQuery................."+strQuery);
		
		strResult = masterUtil.setData(ds, strQuery, support);
		
		//System.out.println("strResult................."+strResult);
		
		return strResult;	
				
	}
	/************************************************************************************************************
    This function Update the dealer informatin in the dealer table.     
**************************************************************************************************************/
	public String updateDealerInfo(DataSource ds,Vector paramVec){
		
		String strResult = "";	
		Support support=new Support();
	    String strQuery="";
		strQuery = "Update dealer set dealername=?,address=?, country=?, state=?,"+
		"city=?, district=?, contactperson =?, phonenumber=?, mobileno=? , email=?, pincode=? where dealerid=?";
	
		support.setDataVec("string", paramVec.elementAt(0).toString().trim());
		support.setDataVec("string", paramVec.elementAt(1).toString().trim());
		support.setDataVec("int", paramVec.elementAt(2).toString().trim());
		support.setDataVec("int", paramVec.elementAt(3).toString().trim());
		support.setDataVec("int", paramVec.elementAt(4).toString().trim());
		support.setDataVec("int", paramVec.elementAt(5).toString().trim());
		support.setDataVec("string", paramVec.elementAt(6).toString().trim());
		support.setDataVec("string", paramVec.elementAt(7).toString().trim());
		support.setDataVec("string", paramVec.elementAt(8).toString().trim());
		support.setDataVec("string", paramVec.elementAt(9).toString().trim());
		support.setDataVec("int", paramVec.elementAt(10).toString().trim());
		support.setDataVec("int", paramVec.elementAt(11).toString().trim());
		
		
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;	
				
	}	
	/*****************************************************************************************************************
	  This function is use for the get Max dealer id from dealer table.
	*****************************************************************************************************************/
	public int getMaxDealerId(DataSource ds)
	{
		int result = 0;
		
	    String strQuery="";		
		strQuery= "Select max(dealerid)dealerid dealerid  from dealer ";
		
		
		result = masterUtil.getId(ds, strQuery, "dealerid");
		
		
		return result;	
		
				
	}
	
	
	/*****************************************************************************************************************
	This function get the core User id from the complaintallocation table ,because complaint will assign to the 
	perticuler Core User for handling the complaint .
*****************************************************************************************************************/
	public int getCoreUserId(DataSource ds,int catid)
	{
		int result = 0;
		
	    String strQuery="";
		
		strQuery= "select cuid from complaintallocation where allocationcriteria like '%"+catid+"%'";
		
		result = masterUtil.getId(ds, strQuery, "cuid");	
		
		return result;
		
	}
/*****************************************************************************************************************
	This function get the core User's allotetted category ids from the complaintallocation table ,because complaint will assign to the 
	perticuler Core User for handling the complaint.
*****************************************************************************************************************/
	public String getCoreUserCatId(DataSource ds,int cuid)
	{
		String result = "0";
		Support support = new Support();
	    String strQuery="";
		
		strQuery= "select allocationcriteria from complaintallocation where cuid ="+cuid;
		support.setFieldVec("string", "allocationcriteria");
		result = masterUtil.getValue(ds, strQuery, support);	
		return result;
		
	}
	
	
	//this function is use  for getting category list for search complaint in core section
	public Vector getCategories(DataSource ds,String catIds)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		if(!catIds.equalsIgnoreCase("failure")) 
		{
			strQuery = "select cat_id,cat_name  from category where cat_enabled=1 and cat_id in("+catIds+")";
			support.setFieldVec("int", "cat_id");
			support.setFieldVec("String", "cat_name");
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			for(int i=0;i<tempResultVec.size();i++)
			{
				resultVec.add((Vector)tempResultVec.elementAt(i));
			}
		}
		 
		return resultVec;
	}
	//end of this code
	
	//	this function is use  for getting category list for search complaint in core section
	public Vector getUserCategories(DataSource ds,String catIds,int flag)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		if(!catIds.equalsIgnoreCase("failure")) 
		{
			if(flag == 0)
			{
				strQuery = "select cat_id,cat_name  from category where cat_enabled=1 and cat_id in("+catIds+")";
			}
			else
			{
				strQuery = "select cat_id,cat_name  from category where cat_enabled=1 and cat_id not in("+catIds+")";
			}
			support.setFieldVec("int", "cat_id");
			support.setFieldVec("String", "cat_name");
			tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
			for(int i=0;i<tempResultVec.size();i++)
			{
				resultVec.add((Vector)tempResultVec.elementAt(i));
			}
		}
		 
		return resultVec;
	}
	//end of this code
	
	/************************************************************************************************************
    This function Update the category value in the complaintallocation table.     
**************************************************************************************************************/
	public String updateAllotment(DataSource ds,Vector paramVec){
		
		String strResult = "";	
		Support support=new Support();
	    String strQuery="";
	    int uid = Integer.parseInt(paramVec.elementAt(0).toString().trim());
	    String catIds = paramVec.elementAt(1).toString().trim();
	    
	    String result = getCoreUserCatId(ds,uid);
	    if(result.equalsIgnoreCase("failure"))
	    {
	    	strQuery = "insert into complaintallocation (cuid, allocationcriteria) values ("+uid+", '"+catIds+"')";
	    }
	    else
	    {
	    	strQuery = "Update complaintallocation set allocationcriteria = '"+catIds+"' where cuid = "+uid;
	    }
		
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;	
				
	}		
	
	
	
	
	public String changetag(DataSource ds,Vector dataVec)
	{
			String strResult="";
			Support support=new Support();
		    String strQuery="";
			int complaint_id =Integer.parseInt(dataVec.elementAt(0).toString().trim());			
			String finaltag =dataVec.elementAt(1).toString().trim();
		
			java.util.Date dt = new java.util.Date();
			SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd");
			String completeRemDate = sform.format(dt);
			
			String resolvedDate = "0000-00-00";
			String closedDate = "0000-00-00";
			if(finaltag.equals("3"))
			{
				resolvedDate = completeRemDate;
			}
			else if(finaltag.equals("4"))
			{
				closedDate = completeRemDate;
			}
				
				/*************************************************************************************************
				dataVec.elementAt(0).toString().trim() = blogId;
				dataVec.elementAt(1).toString().trim() = tagid;//final tag id.
				
		        ***********************************************************************************************/
				if(finaltag.equals("3"))
				{
					strQuery="update complaints set tag_id = ?, resolve_date = ? where complaint_id="+complaint_id;
					support.setDataVec("int", finaltag);
					support.setDataVec("string", resolvedDate);
				}
				else if(finaltag.equals("4"))
				{
					strQuery="update complaints set tag_id = ? ,closed_date=? where complaint_id="+complaint_id;
					support.setDataVec("int", finaltag);
					support.setDataVec("string", closedDate);
				}
				else
				{
					strQuery="update complaints set tag_id = ?  where complaint_id="+complaint_id;
					support.setDataVec("int", finaltag);
				}
				
				
				strResult = masterUtil.setData(ds, strQuery, support);
				return strResult;
				
	}
	/*
	 * This function change  publish_on field of complaints table by the core user.
	 */
	
	public String changepublish(DataSource ds,Vector dataVec)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
		java.util.Date dt = new java.util.Date();
		
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("dd-MM-yyyy");
		String current_date = format.format(dt);
		
		java.text.SimpleDateFormat format1 = new java.text.SimpleDateFormat("HH:mm:ss");
	    String current_time = format1.format(dt);
	    
	   
	    /*************************************************************************************************
		dataVec.elementAt(0).toString().trim() = blogId;
		dataVec.elementAt(1).toString().trim() = publish_on;
		
        ***********************************************************************************************/	
	    int complaint_id =Integer.parseInt(dataVec.elementAt(0).toString().trim());
	    String publish_on=dataVec.elementAt(1).toString();
	    
	    if(publish_on.equalsIgnoreCase("Week"))
	    {
	    	strQuery ="update complaints set publish_flag = '1', publish_date = ?, publish_time = ? where complaint_id="+complaint_id;
		
	    	support.setDataVec("string", current_date);
	    	support.setDataVec("string", current_time);
	    }
		else
		{
			strQuery ="update complaints set publish_on = ? where complaint_id="+complaint_id;
			support.setDataVec("string", publish_on);
		}
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
	}
	/************************************************************************************************************
	This function update complaint in complaints table by core user.
	and return the result string Success or failure accordiong to query result.
**************************************************************************************************************/
	public String updateComplaint(DataSource ds,Vector paramVec){
		
		String strResult = "";
		Support support=new Support();
	    String strQuery="";
		int complaint_id =Integer.parseInt(paramVec.elementAt(15).toString().trim());
		
					
		strQuery = "Update complaints set complaint_title=?, complaint_text=?, relevent_info=?, publish_date=?,  "+
		"publish_time=?, lastmodify_date=?, lastmodify_time=?, advtL_id = ?,dealer_id=?, tag_id=?, brandFlag=?, publish_flag=?, publish_on=?, resolve_date=?, closed_date=?  where complaint_id="+complaint_id;
		
		support.setDataVec("string", paramVec.elementAt(0).toString().trim());
		support.setDataVec("string", paramVec.elementAt(1).toString().trim());
		support.setDataVec("string", paramVec.elementAt(2).toString().trim());
		support.setDataVec("string", paramVec.elementAt(3).toString().trim());
		support.setDataVec("string", paramVec.elementAt(4).toString().trim());
		support.setDataVec("string", paramVec.elementAt(5).toString().trim());
		support.setDataVec("string", paramVec.elementAt(6).toString().trim());
		support.setDataVec("int", paramVec.elementAt(7).toString().trim());
		support.setDataVec("int", paramVec.elementAt(8).toString().trim());
		support.setDataVec("int", paramVec.elementAt(9).toString().trim());
		support.setDataVec("int", paramVec.elementAt(10).toString().trim());
		support.setDataVec("int", paramVec.elementAt(11).toString().trim());
		support.setDataVec("string", paramVec.elementAt(12).toString().trim());
		support.setDataVec("string", paramVec.elementAt(13).toString().trim());
		support.setDataVec("string", paramVec.elementAt(14).toString().trim());
		
	
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
		
	}
	
	/************************************************************************************************************
	This function update complaint in complaints table by core user.
	and return the result string Success or failure accordiong to query result.
**************************************************************************************************************/
	public String updateComplaint(DataSource ds,String advtId,String advtL_id,String complanit_id){
		
		String strResult = "";
		Support support=new Support();
	    String strQuery="";
		int complaint_id =Integer.parseInt(complanit_id);
		
					
		strQuery = "Update complaints set advt_id=?, advtL_id=? , other=? where complaint_id="+complaint_id;
		
		
		support.setDataVec("string", advtId);
		support.setDataVec("string", advtL_id);
		support.setDataVec("string", "");
		
		
		
	
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
		
	}
	
	
	/************************************************************************************************************
	This function update complaint in complaints table by core user.
	and return the result string Success or failure accordiong to query result.
**************************************************************************************************************/
	public String updateComplaint(DataSource ds,String cuId,Vector paramVec){
		
		String strResult = "";
		String strQuery="";
	    Vector<String> queryVec =  new Vector<String>();
	    for(int i=0;i<paramVec.size();i++)
	    {
	    	Vector tempVec = (Vector)paramVec.elementAt(i);
	    	int complaint_id =Integer.parseInt(tempVec.elementAt(0).toString().trim());
	    	String fcompId =  tempVec.elementAt(1).toString().trim();
	    	strQuery = "Update complaints set cu_id="+cuId+", fcom_id='"+fcompId+"' where complaint_id="+complaint_id;
	    	queryVec.add(strQuery);
	    }
			
	
		strResult = masterUtil.setBatchData(ds, queryVec);
		return strResult;
		
		
	}
	
	/**
	 * ***************************************************************************************************************
	*This function get the core User id from the complaintallocation table ,because complaint will assign to the 
	*perticuler Core User for handling the complaint .
*	***************************************************************************************************************
*	*/
	public int getConsumerId(DataSource ds,int compId)
	{
		int result = 0;
		
		String strQuery="";
		
		strQuery= "select login_id from complaints where complaint_id="+compId;
		
		
		result = masterUtil.getId(ds, strQuery, "login_id");	
		
		return result;
		
	}
	/**
	 * This function is use for getting list of  Inbox from complaints and communication.
	 * It returns a list within limit which is given by min and max variable.
	 */
		public Vector getInboxList(DataSource ds,Vector paramVec){
			
			//////////System.out.println("insite getInboxList.Inbox......");
			
			Vector resultVec = new Vector();
			int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
			int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
			String fieldVal = paramVec.elementAt(2).toString().trim();
			int coreUserId = Integer.parseInt(paramVec.elementAt(3).toString().trim());
			int adminFlag = Integer.parseInt(paramVec.elementAt(4).toString().trim());
			String typeofcompany = paramVec.elementAt(5).toString().trim();
			
			Support support=new Support();
			   String strQuery="";
			
			if(typeofcompany.equalsIgnoreCase("Advertiser"))
			{	
		 	    
		 	    	strQuery = "select c.respid respid, c.responsetext responsetext, c.responsedate responsedate, c.complaintid complaintid, "+
		"c.userid userid, c.responseTime responseTime, cm.typeofcompany typeofcompany, "+ 
		"l.first_name first_name, l.last_name last_name, l.city city, cm.company_name company_name, cp.fcom_id fcom_id "+
		"from communication c,complaints cp, loginmaster l, companymaster cm "+
		 			"where  c.entp_rflag= '0' and cp.complaint_id=c.complaintid and c.userid = l.userid and l.companyid"+
		 			" = cm.company_id and cm.typeofcompany='Advertiser' and "+
		 			"cp.cu_id = "+coreUserId+" order by "+fieldVal+"  limit "+min+", "+max;
					
				
		}else
			{
			 	strQuery = "select c.respid respid, c.responsetext responsetext, c.responsedate responsedate, c.complaintid complaintid, "+
		"c.userid userid, c.responseTime responseTime, cm.typeofcompany typeofcompany, "+ 
		"l.first_name first_name, l.last_name last_name, l.city city, cm.company_name company_name, cp.fcom_id fcom_id "+
		"from communication c,complaints cp, loginmaster l, companymaster cm "+
		 			"where  c.entp_rflag= '0' and cp.complaint_id=c.complaintid and c.userid = l.userid and l.companyid"+
		 			" = cm.company_id and cm.typeofcompany='Consumer' and "+
		 			"cp.cu_id = "+coreUserId+" order by "+fieldVal+"  limit "+min+", "+max;
					
				
				
			}
			
			////////System.out.println("strQuery............."+strQuery);
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
			//////////System.out.println("insite getInboxList.Inbox..resultVec...."+resultVec);
			return resultVec;
		}
	
	/**
	 * This function use for getting total no. of Inbox Request.
	 * @return
	 */	
		public int getInboxCount(DataSource ds,Vector paramVec){
			
			int result = 0;
			int coreUserId = Integer.parseInt(paramVec.elementAt(0).toString().trim());
			int adminFlag = Integer.parseInt(paramVec.elementAt(1).toString().trim());
			String typeofcompany = paramVec.elementAt(2).toString().trim();
			
									
			   String strQuery="";
			
			   if(typeofcompany.equalsIgnoreCase("Advertiser"))
				{	
			 	    
			 	    	strQuery = "select count(*) count from communication c,complaints cp, loginmaster l, companymaster cm "+
			 			"where  c.entp_rflag= '0' and cp.complaint_id=c.complaintid and c.userid = l.userid and l.companyid"+
			 			" = cm.company_id and cm.typeofcompany='Advertiser' and "+
			 			"cp.cu_id = "+coreUserId;
						
					
				}else
				{
					
						strQuery = "select count(*) count from communication c,complaints cp, loginmaster l, companymaster cm "+
						"where  c.entp_rflag= 0 and cp.complaint_id=c.complaintid and c.userid = l.userid and l.companyid"+
						" = cm.company_id and cm.typeofcompany='Consumer' and "+
						"cp.cu_id = "+coreUserId;
						
					
					
				}
			   ////////System.out.println("strQuery....count......"+strQuery);
			result = masterUtil.getCount(ds, strQuery);
			//////////System.out.println("insite getInboxCount.Inbox..result...."+result);
			return result;
		}
		
		
	/**
	 * 
	 * @param ds
	 * @param dataVec
	 * @return
	 */
		public Vector getSearchComplainsList(DataSource ds,Vector dataVec){
			
			Vector resultVec = new Vector();
			int min = Integer.parseInt(dataVec.elementAt(0).toString().trim());
			int max = Integer.parseInt(dataVec.elementAt(1).toString().trim());		
			String fieldVal = dataVec.elementAt(2).toString().trim();
			int cuid = Integer.parseInt(dataVec.elementAt(3).toString().trim());
			int adminFlag = Integer.parseInt(dataVec.elementAt(4).toString().trim());

			int vectorSize=dataVec.size();
			
			Support support=new Support();
			   String strQuery="";

			
			
		
				if(vectorSize==7)
				{
				

					strQuery = "SELECT * FROM complaints where cu_id = '"+cuid+"' and  "+dataVec.elementAt(5).toString().trim()+" ='"+dataVec.elementAt(6).toString().trim() +"' order by "+fieldVal+"  limit "+min+", "+max;
				}
				
				else if (vectorSize==9)
				{
				

					strQuery = "SELECT * FROM complaints where cu_id = '"+cuid+"' and   "+dataVec.elementAt(5).toString().trim()+" ='"+dataVec.elementAt(6).toString().trim()+"' and "+dataVec.elementAt(7).toString().trim()+" ='"+dataVec.elementAt(8).toString().trim()+"' order by "+fieldVal+" limit "+min+", "+max;
				}
				 else if (vectorSize==11)
				{
					
					strQuery = "SELECT * FROM complaints where cu_id = '"+cuid+"' and   "+dataVec.elementAt(5).toString().trim()+ "='"+dataVec.elementAt(6).toString().trim()+"' and "+dataVec.elementAt(7).toString().trim()+" ='"+dataVec.elementAt(8).toString().trim()+"' and "+dataVec.elementAt(9).toString().trim()+" ='"+dataVec.elementAt(10).toString().trim()+"' order by "+fieldVal+"  limit "+min+", "+max;
				}
				else if (vectorSize==13)
				{

					strQuery = "SELECT * FROM complaints where cu_id = '"+cuid+"' and   "+dataVec.elementAt(5).toString().trim()+ "='"+dataVec.elementAt(6).toString().trim()+"' and "+dataVec.elementAt(7).toString().trim()+" ='"+dataVec.elementAt(8).toString().trim()+"' and "+dataVec.elementAt(9).toString().trim()+" ='"+dataVec.elementAt(10).toString().trim()+"' and "+dataVec.elementAt(11).toString().trim()+" ='"+dataVec.elementAt(12).toString().trim()+"' order by "+fieldVal+" limit "+min+", "+max;
				}
				else if (vectorSize==15)
				{
					
					strQuery = "SELECT * FROM complaints where cu_id = '"+cuid+"' and   "+dataVec.elementAt(5).toString().trim()+ "='"+dataVec.elementAt(6).toString().trim()+"' and "+dataVec.elementAt(7).toString().trim()+" ='"+dataVec.elementAt(8).toString().trim()+"' and "+dataVec.elementAt(9).toString().trim()+" ='"+dataVec.elementAt(10).toString().trim()+"' and "+dataVec.elementAt(11).toString().trim()+" ='"+dataVec.elementAt(12).toString().trim()+"' and "+dataVec.elementAt(13).toString().trim()+" ='"+dataVec.elementAt(14).toString().trim()+"' order by "+fieldVal+"  limit "+min+", "+max;
				}				
		
			   
			
			
			/**
			 * set all the fileds type and name for complaints table by using
			 * Support class object.
			 */
			support.setFieldVec("int", "complaint_id");
			support.setFieldVec("string", "fcom_id");
			support.setFieldVec("string", "complaint_title");								
			support.setFieldVec("string", "creation_date");	
			support.setFieldVec("string", "creation_time");
			support.setFieldVec("int", "brandFlag");
			
			
			
			resultVec = masterUtil.getList(ds, strQuery, support);
			return resultVec;
		}
		
		/**
		 * 
		 * @param ds
		 * @param dataVec
		 * @return
		 */
		
		
		public int getSearchComplainsCount(DataSource ds,Vector dataVec){
			
			int result = 0;
			int min = Integer.parseInt(dataVec.elementAt(0).toString().trim());
			int max = Integer.parseInt(dataVec.elementAt(1).toString().trim());		
			String fieldVal = dataVec.elementAt(2).toString().trim();
			int cuid = Integer.parseInt(dataVec.elementAt(3).toString().trim());
			int adminFlag = Integer.parseInt(dataVec.elementAt(4).toString().trim());

			int vectorSize=dataVec.size();
			
									
			   String strQueryTotal="";
			
		
				if(vectorSize==7)
				{
					
					strQueryTotal = "SELECT count(*) count FROM complaints where cu_id = '"+cuid+"' and   "+dataVec.elementAt(5).toString().trim()+"='"+dataVec.elementAt(6).toString().trim()+"'";
					

				}
				
				else if (vectorSize==9)
				{
					
					strQueryTotal = "SELECT count(*) count FROM complaints where cu_id = '"+cuid+"' and   "+dataVec.elementAt(5).toString().trim()+"='"+dataVec.elementAt(6).toString().trim()+"' and "+dataVec.elementAt(7).toString().trim()+" ='"+dataVec.elementAt(8).toString().trim()+"'";
					

				}
				 else if (vectorSize==11)
				{
					
					strQueryTotal = "SELECT count(*) count FROM complaints where cu_id = '"+cuid+"' and   "+dataVec.elementAt(5).toString().trim()+"='"+dataVec.elementAt(6).toString().trim()+"' and "+dataVec.elementAt(7).toString().trim()+" ='"+dataVec.elementAt(8).toString().trim()+"' and "+dataVec.elementAt(9).toString().trim()+"='"+dataVec.elementAt(10).toString().trim()+"'";
					

				}
				else if (vectorSize==13)
				{
					
					strQueryTotal = "SELECT count(*) count FROM complaints where cu_id = '"+cuid+"' and   "+dataVec.elementAt(5).toString().trim()+"='"+dataVec.elementAt(6).toString().trim()+"' and "+dataVec.elementAt(7).toString().trim()+" ='"+dataVec.elementAt(8).toString().trim()+"' and "+dataVec.elementAt(9).toString().trim()+"='"+dataVec.elementAt(10).toString().trim()+"' and "+dataVec.elementAt(11).toString().trim()+"='"+dataVec.elementAt(12).toString().trim()+"'";
					

				}
				else if (vectorSize==15)
				{
					
					strQueryTotal = "SELECT count(*) count FROM complaints where cu_id = '"+cuid+"' and   "+dataVec.elementAt(5).toString().trim()+"='"+dataVec.elementAt(6).toString().trim()+"' and "+dataVec.elementAt(7).toString().trim()+" ='"+dataVec.elementAt(8).toString().trim()+"' and "+dataVec.elementAt(9).toString().trim()+"='"+dataVec.elementAt(10).toString().trim()+"' and "+dataVec.elementAt(11).toString().trim()+"='"+dataVec.elementAt(12).toString().trim()+"' and "+dataVec.elementAt(13).toString().trim()+"='"+dataVec.elementAt(14).toString().trim()+"'";
					

				}				
		
			
			  
			
			result = masterUtil.getCount(ds, strQueryTotal);
			return result;
		}
		
		/**
		 * This function is use for getting list of  comments from `loginmaster` and commentsoncomplaints.
		 * It returns a list within limit which is given by min and max variable.
		 */
			public Vector getNewCommentsList(DataSource ds,Vector paramVec){
				
				//////////System.out.println("insite getInboxList.Inbox......");
				
				Vector resultVec = new Vector();
				int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
				int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
				String fieldVal = paramVec.elementAt(2).toString().trim();
				int coreUserId = Integer.parseInt(paramVec.elementAt(3).toString().trim());
				int adminFlag = Integer.parseInt(paramVec.elementAt(4).toString().trim());
				
				
				Support support=new Support();
				   String strQuery="";
				
					
				    
					
						strQuery = "select * from commentsoncomplaints,complaints where  commentsoncomplaints.publish_flag = 0 and commentsoncomplaints.suspend_flag = 0  and complaints.complaint_id= commentsoncomplaints.complaintid and complaints.cu_id = "+coreUserId+"  order by   "+fieldVal+"  limit "+min+", "+max;
					
				
				
				/**
				 * set all the fileds type and name for companymaster table by using
				 * Support class object.
				 */
				support.setFieldVec("int", "commentid");
				support.setFieldVec("int", "userid");
				support.setFieldVec("string", "commenttext");								
				support.setFieldVec("int", "complaintid");	
				support.setFieldVec("string", "commentdate");
				support.setFieldVec("int", "publish_flag");			
				support.setFieldVec("int", "suspend_flag");
				
				
				resultVec = masterUtil.getList(ds, strQuery, support);
				//////////System.out.println("insite getInboxList.Inbox..resultVec...."+resultVec);
				return resultVec;
			}
		
		/**
		 * This function use for getting total no. of comments Request.
		 * @return
		 */	
			public int getNewCommentCount(DataSource ds,Vector paramVec){
				
				int result = 0;
				int coreUserId = Integer.parseInt(paramVec.elementAt(0).toString().trim());
				int coreAdminId = Integer.parseInt(paramVec.elementAt(1).toString().trim());
				
				
										
				   String strQuery="";
				
				   
						strQuery = "select * from commentsoncomplaints,complaints where   commentsoncomplaints.publish_flag = 0 and commentsoncomplaints.suspend_flag = 0 and complaints.complaint_id= commentsoncomplaints.complaintid and complaints.cu_id = "+coreUserId;
					
				
				result = masterUtil.getCount(ds, strQuery);
				////////System.out.println("insite getInboxCount.Inbox..result...."+result);
				return result;
			}
			
			/************************************************************************************************************
		    This function Update the Active Field value in the companymaster table.     
		**************************************************************************************************************/
			public String updateComActiveFlag(DataSource ds,Vector paramVec){
				
				String strResult = "";	
				Support support=new Support();
			    String strQuery="";
				strQuery = "Update companymaster set active=? where company_id=?";
				
				
				support.setDataVec("int", paramVec.elementAt(0).toString().trim());
				support.setDataVec("int", paramVec.elementAt(1).toString().trim());
				
				
				
				strResult = masterUtil.setData(ds, strQuery, support);
				return strResult;	
						
			}		
	/**
	 * Function for MIS.
	 */
			/***********************************************************************************************************************
			this function use for getting no of complaints received daily
	************************************************************************************************************************/

		public int getComplaints(DataSource ds, String strcurrDate)
		{
			int result = 0;
			
				String strQuery="";
					
						strQuery = "Select count(*) count_complaint  from complaints where creation_date='"+strcurrDate+"'";
						
						result = masterUtil.getCount(ds, strQuery);

			
			return result;
		}

	/***********************************************************************************************************************
			this function use for getting total number of companits online published
	************************************************************************************************************************/

		public int getPublishedComplaints(DataSource ds)
		{
			
			int result = 0;

			String strQuery="";
					
						strQuery = "Select count(*) count_published  from complaints where publish_flag=1";
						
						result = masterUtil.getCount(ds, strQuery);
			
			return result;
		}


	/***********************************************************************************************************************
			this function use for getting no. of complaints responded
	************************************************************************************************************************/

		public int getRespondedComplaints(DataSource ds)
		{
			
			int result = 0;
			String strQuery="";
					
						strQuery = "Select count(*) count  from communication  group by complaintid";
						
						result = masterUtil.getCount(ds, strQuery);
								
			
			return result;
		}

	/***********************************************************************************************************************
			this function use for getting total number of complaints responded daily
	************************************************************************************************************************/

		public int getRespondedComplaintsDaily(DataSource ds,String strcurrDate)
		{
			
			int result = 0;
			
			String strQuery="";
					
						strQuery = "Select count(*) from communication  where responsedate='"+strcurrDate+"' group by complaintid";
						
						result = masterUtil.getCount(ds, strQuery);
			
			return result;
		}

	/***********************************************************************************************************************
			this function use for getting no. of brands against whom complaints are filed 
	************************************************************************************************************************/

		public int getBrandCount(DataSource ds)
		{
			
			int result = 0;
			String strQuery="";
			
			strQuery= "SELECT count(distinct(advt_id)) advt_id from complaints where advt_id !=0;";
			
			
			result = masterUtil.getCount(ds, strQuery);	
			
			return result;
		}
		



	/***********************************************************************************************************************
			this function use for getting brands against whom complaints are filed
	************************************************************************************************************************/

		public Vector getBrandList(DataSource ds, String strFShortBy)
		{
			
			Vector resultVec = new Vector();
			Support support=new Support();
			String strQuery="";
			
					strQuery = "select distinct(company_sname),companymaster.company_id, company_name, typeofcompany, active from companymaster, complaints where companymaster.company_id = complaints.advt_id and complaints.advt_id !=0  order by "+strFShortBy;
					
					support.setFieldVec("int", "company_id");
					support.setFieldVec("string", "company_sname");
					support.setFieldVec("string", "company_name");								
					support.setFieldVec("int", "active");
					support.setFieldVec("string", "typeofcompany");
					
					resultVec = masterUtil.getList(ds, strQuery, support);
					
					
					return resultVec;
		}


	/***********************************************************************************************************************
			this function use for getting no. of brands against whom complaints are pending
	************************************************************************************************************************/

		public int getBrandPending(DataSource ds)
		{
			
			int result = 0;
			
			String strQuery="";
			
			strQuery= "select  count(distinct(advt_id)) advt_id from complaints where advt_id !=0 and tag_id= 1"; 
			
			
			result = masterUtil.getCount(ds, strQuery);
			
			return result;
		}
		
	/***********************************************************************************************************************
			this function use for getting brands against whom complaints are pending
	************************************************************************************************************************/

		public Vector getBrandPendingList(DataSource ds, String strFShortBy)
		{
			Vector resultVec = new Vector();
			Support support=new Support();
			String strQuery="";
			
					strQuery = "select distinct(company_sname),companymaster.company_id, company_name, typeofcompany, active from companymaster, complaints where companymaster.company_id =complaints.advt_id and complaints.advt_id !=0 and complaints.tag_id=1  order by "+strFShortBy;
					
								
					support.setFieldVec("int", "company_id");	
					support.setFieldVec("string", "company_sname");
					support.setFieldVec("string", "company_name");								
					support.setFieldVec("int", "active");
					support.setFieldVec("string", "typeofcompany");
					
					resultVec = masterUtil.getList(ds, strQuery, support);
					
			
			return resultVec;
		}

	/***********************************************************************************************************************
			this function use for getting no. of brands who are registered or Not registered  according to coming parameter
				active =0 for Not Registered Brand
				active =1 for registered Brand
	************************************************************************************************************************/

		public int getRegisteredBrand(DataSource ds, int active)
		{
			int result = 0;
			
			String strQuery="";
			
			strQuery="select count(*) registeBrand from companymaster where registered="+active+" and typeofcompany='Advertiser' order by company_sname";
				
			result = masterUtil.getCount(ds, strQuery);
			
			return result;
		}

	/***********************************************************************************************************************
			this function use for getting brands who are registered or Not registered  according to coming parameter
			active =0 for Not Registered Brand
				active =1 for registered Brand
	************************************************************************************************************************/

		public Vector getRegisteredBrandList(DataSource ds, int active, String strFShortBy)
		{
			
			Vector resultVec = new Vector();
			Support support=new Support();
			String strQuery="";
			
					strQuery = "select company_sname, companymaster.company_id, company_name, typeofcompany, active, registered  from companymaster where registered="+active+" and typeofcompany='Advertiser' order by "+strFShortBy;
				
					support.setFieldVec("int", "company_id");				
					support.setFieldVec("string", "company_sname");
					support.setFieldVec("string", "company_name");								
					support.setFieldVec("int", "active");
					support.setFieldVec("string", "typeofcompany");
					support.setFieldVec("int", "registered");
					
					resultVec = masterUtil.getList(ds, strQuery, support);
					
			
			return resultVec;

		}
	/*************************************************************************************************************
			this code is given by Arun Sir
	**************************************************************************************************************/
//	COMMENT LIST
//	getNewCommentsList ( EntpMaster)
	public Vector getCommentsList(DataSource ds)
		{
			Vector resultVec = new Vector();
			Support support=new Support();
			String strQuery="";
			
					strQuery = "select * from commentsoncomplaints where publish_flag=0 and suspend_flag=0";
					
					support.setFieldVec("comapanyid", "commentid");
					support.setFieldVec("string", "commenttext");								
					support.setFieldVec("string", "commentdate");
					support.setFieldVec("string", "commentTime");
					support.setFieldVec("int", "complaintid");
					support.setFieldVec("int", "userid");
					support.setFieldVec("int", "comapanyid");
					
					resultVec = masterUtil.getList(ds, strQuery, support);
					
			return resultVec;
		}

		
//	 grt Comments detail........
	public Vector getComments(DataSource ds, int commentid)
		{
			Vector resultVec = new Vector();
			Support support=new Support();
		    String strQuery="";
			strQuery = "select * from commentsoncomplaints where publish_flag=0 and suspend_flag=0 and commentid="+commentid;
					
			support.setFieldVec("int", "commentid");//0
			support.setFieldVec("string", "commenttext");//1
			support.setFieldVec("string", "commentdate");//2
			support.setFieldVec("string", "commentTime");//3
			support.setFieldVec("int", "complaintid");//4
			support.setFieldVec("int", "userid");//5
			support.setFieldVec("int", "comapanyid");//6

				
			resultVec = masterUtil.getDetail(ds, strQuery, support);
			return resultVec;
		}

		///getCommentCount...on a particular complaint....////////////////
		public int getCommentCount(DataSource ds, int ComplaintID)
		{
			int result = 0;
			
		    String strQuery="";

		    strQuery= "select * from complaints where complaint_id="+ComplaintID;
				
				result = masterUtil.getId(ds, strQuery, "viewcount");	
			
			return result;
			
			
		}
//	end of code

		public String updateComments(DataSource ds, String numCId, String strCText, int numFlag)
		{
			String strResult = "failure";	
					Support support=new Support();
				    String strQuery="";
			
						
				if (numFlag == 1)
				{
					strQuery = "Update commentsoncomplaints set commenttext=?, publish_flag = 1 where commentid=?"; 

					support.setDataVec("String", strCText);
					support.setDataVec("int", numCId);
				}
				if (numFlag == 2)
				{
					strQuery = "Update commentsoncomplaints set commenttext=?, suspend_flag = 1 where commentid=?";

					support.setDataVec("String", strCText);
					support.setDataVec("int", numCId);
				}
				if (numFlag == 3)
				{
					strQuery = "delete from commentsoncomplaints where commentid=?";
					support.setDataVec("int", numCId);

				}
				
			strResult = masterUtil.setData(ds, strQuery, support);
			return strResult;
		}


		public String setCommentCount(DataSource ds, String numCmtCount, String ComplaintID)
		{		String strResult = "";	
					Support support=new Support();
				    String strQuery="";
			
				strQuery = "Update complaints Set viewcount= ? where complaint_id= ?";

				support.setDataVec("int", numCmtCount);
				support.setDataVec("string", ComplaintID);

				strResult = masterUtil.setData(ds, strQuery, support);
				return strResult;
				
		}
		
		public String companyVerify(DataSource ds, String comp_sname)
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
				
			}else
				strResult="success";
			
			return strResult;
		}
		
		/***********************************************************************************************************************
		this function use for getting no. of Community .
************************************************************************************************************************/

	public int getCommunityCount(DataSource ds)
	{
		
		int result = 0;
		String strQuery="";
		
		strQuery= "SELECT count(distinct(company_sname)) company_sname from companymaster where typeofcompany ='Consumer';";
		
		
		result = masterUtil.getCount(ds, strQuery);	
		
		return result;
	}
	



/***********************************************************************************************************************
		this function use for getting Community List.
************************************************************************************************************************/

	public Vector getCommunityList(DataSource ds, String strFShortBy,int min , int max)
	{
		
		Vector resultVec = new Vector();
		Support support=new Support();
		String strQuery="";
		
				strQuery = "select *  from companymaster where typeofcompany ='Consumer' order by "+strFShortBy +" limit "+min+", "+max;
				
				support.setFieldVec("int", "company_id");
				support.setFieldVec("string", "company_sname");
				support.setFieldVec("string", "company_name");								
				support.setFieldVec("int", "active");
				support.setFieldVec("string", "typeofcompany");
				
				resultVec = masterUtil.getList(ds, strQuery, support);
				
				
				return resultVec;
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
	public Vector getUserGroupList(DataSource ds, int numCompId)
	{
		Support support=new Support();
		String strQuery = "SELECT * FROM loginrights where companyid="+numCompId;
		support.setFieldVec("int", "rid");
		support.setFieldVec("string", "groupname");
					
		Vector resultVec = masterUtil.getList(ds, strQuery, support);
		
		return resultVec;
	}
	
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
		String strQuery = "insert into loginrights (companyid, groupname, accmanagement, users, ";
		strQuery +="usercreation, userdeletion, usermodification, change_password, complaintmanagement, ";
		strQuery +="mis, complaints) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
		}
		
		return resultVec;
	}
	
	public Vector getCommunityRightsGroupDetails(DataSource ds, int numCompId, int numRId, String strType)
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
			
			support.setFieldVec("int", "complaints");
				
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
			
		}
		
		return resultVec;
	}
	public String setCommunitRightsGroup(DataSource ds, Vector dataVec)
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
		strQuery +="mis, complaints) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		MasterUtility mu = new MasterUtility();
		strResult = mu.setData(ds, strQuery, support);
		return strResult;
	}
	public String editCommunitRightsGroup(DataSource ds, Vector dataVec)
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
	public String deleteCommunitRightsGroup(DataSource ds, int numUrid)
	{
		String strResult = "failure";
		String strQuery = "delete from loginrights where companyid=0 and rid="+numUrid;
		MasterUtility mu = new MasterUtility();
		
		////////System.out.println("strQuery............"+strQuery);
		
		strResult = mu.deleteData(ds, strQuery);
		return strResult;
	}
	
	
	
	public String deleteUserGroup(DataSource ds, int numUrid, int numCompId)
	{
		String strResult = "failure";
		String strQuery = "delete from loginrights where companyid="+numCompId+" and rid="+numUrid;
		MasterUtility mu = new MasterUtility();
		strResult = mu.deleteData(ds, strQuery);
		return strResult;
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
			//////////System.out.println("In EntpMaster result..."+result);
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
				
				////////System.out.println("strQuery................"+strQuery);
				
			Vector resultVec = masterUtil.getList(ds, strQuery, support);
			
			return resultVec;
		}
		
	
	/**
	 * End For User Group 
	 */
	public String setComplaintUnnamed(DataSource ds, Vector unnamedDataVec, int numComplaintId)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
		
		strQuery = "Insert Into complaint_data(complaintid, field1, field2, field3, field4, field5, field6, ";
		strQuery +="field7, field8, field9, field10, field11, field12, field13, field14, field15, field16, ";
		strQuery +="field17, field18, field19, field20, field21, field22, field23, field24, field25, field26, ";
		strQuery +="field27, field28, field29, field30, field31, field32, field33, field34, field35)";
		strQuery +=" Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		support.setDataVec("int", String.valueOf(numComplaintId));
		for(int i=0; i<unnamedDataVec.size(); i++)
		{
			support.setDataVec("string", unnamedDataVec.elementAt(i).toString().trim());
		}
		
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
	}
	
	public String editComplaintUnnamed(DataSource ds, Vector unnamedDataVec, int numComplaintId)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
		
		strQuery = "Update complaint_data set field1=?, field2=?, field3=?, field4=?, field5=?, field6=?, ";
		strQuery +="field7=?, field8=?, field9=?, field10=?, field11=?, field12=?, field13=?, field14=?, field15=?, field16=?, ";
		strQuery +="field17=?, field18=?, field19=?, field20=?, field21=?, field22=?, field23=?, field24=?, field25=?, field26=?, ";
		strQuery +="field27=?, field28=?, field29=?, field30=?, field31=?, field32=?, field33=?, field34=?, field35=? Where complaintid=?";
		
		for(int i=0; i<unnamedDataVec.size(); i++)
		{
			support.setDataVec("string", unnamedDataVec.elementAt(i).toString().trim());
		}
		support.setDataVec("int", String.valueOf(numComplaintId));
				
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
	}
	
	public Vector getUserInfoForMail(DataSource ds, int numUId)
	{
		Support support=new Support();
		String strQuery = "SELECT * FROM loginmaster where userid="+numUId;
		support.setFieldVec("int", "userid");
		support.setFieldVec("string", "email");
		support.setFieldVec("string", "first_name");
		support.setFieldVec("string", "last_name");
		Vector resultVec = masterUtil.getDetail(ds, strQuery, support);
		
		return resultVec;
	}
	/**
	 * this functionis use for rejection of complaint.
	 * @param ds
	 * @param paramVec
	 * @return
	 */
	public String rejectComplaint(DataSource ds, Vector paramVec)
	{
		String strResult = "";
		String strQuery="";
		Support support = new Support();
	   	int complaint_id =Integer.parseInt(paramVec.elementAt(0).toString().trim());
    	String date =  paramVec.elementAt(1).toString().trim();
    	String time =  paramVec.elementAt(2).toString().trim();
    	strQuery = "Update complaints set lastmodify_date=?, lastmodify_time=?,tag_id=? ,closed_date=?, reject_date=? where complaint_id="+complaint_id;
    	
		support.setDataVec("string", date);
		support.setDataVec("string", time);
		support.setDataVec("int", "4");
		support.setDataVec("string", date);
		support.setDataVec("string", date);
		
    	strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
	}
	/**
	 * For unnamed field.
	 */
	
	public boolean getCommunityUnnamedMap(int numCompId, DataSource ds)
	{
		boolean blnResult = false;
		MasterUtility mu = new MasterUtility();
		String strQuery = "Select count(*) From consumer_mapping Where companyid="+numCompId;
		int numRowCount = mu.getCount(ds, strQuery);
		
		blnResult = (numRowCount>0) ? true : false;
		////////System.out.println("blnResult................."+blnResult);
		return blnResult;
	}
	
	/**
	 * This function is used to add or modify row in Compalint Unnnamed Mapping.
	 * @param dataVec
	 * @param ds
	 * @return
	 */
	public String setCommunityUnnamedMap(CommunitcustForm cf, DataSource ds, int numCompId)
	{
		/**
		 * This function set unnamed Mapping field for Complaint writing procedure.
		 * In this function first we check row is exist for given criteria. 
		 * If exist then every time we modify that line. 
		 * else we insert a row for company and category. 
		 */
		String strResult = "failuer";
		
		//Get Unnamed Mapping Id here.
		boolean blnStatus = getCommunityUnnamedMap(numCompId, ds);
		//Check if Unnamed Mapping id greter then 0.
		Support support = new Support();
		//support.setDataVec("string", strData)  Select
		
//		Field 1 Values
		support.setDataVec("string", cf.getField1());
		if(!cf.getHcmbfield1().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield1());
		}else{
		support.setDataVec("string", cf.getCmbfield1());
		}
		support.setDataVec("string", cf.getValfield1());
		support.setDataVec("string", cf.getMndfield1());
		support.setDataVec("string", cf.getVsbfield1());
		
//		Field 2 Values
		support.setDataVec("string", cf.getField2());
		if(!cf.getHcmbfield2().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield2());
		}else{
		support.setDataVec("string", cf.getCmbfield2());
		}
		support.setDataVec("string", cf.getValfield2());
		support.setDataVec("string", cf.getMndfield2());
		support.setDataVec("string", cf.getVsbfield2());
		
//		Field 3 Values
		support.setDataVec("string", cf.getField3());
		if(!cf.getHcmbfield3().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield3());
		}else{
		support.setDataVec("string", cf.getCmbfield3());
		}
		support.setDataVec("string", cf.getValfield3());
		support.setDataVec("string", cf.getMndfield3());
		support.setDataVec("string", cf.getVsbfield3());
		
//		Field 4 Values
		support.setDataVec("string", cf.getField4());
		if(!cf.getHcmbfield4().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield4());
		}else{
		support.setDataVec("string", cf.getCmbfield4());
		}
		support.setDataVec("string", cf.getValfield4());
		support.setDataVec("string", cf.getMndfield4());
		support.setDataVec("string", cf.getVsbfield4());
		
//		Field 5 Values
		support.setDataVec("string", cf.getField5());
		if(!cf.getHcmbfield5().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield5());
		}else{
		support.setDataVec("string", cf.getCmbfield5());
		}
		support.setDataVec("string", cf.getValfield5());
		support.setDataVec("string", cf.getMndfield5());
		support.setDataVec("string", cf.getVsbfield5());
		
//		Field 6 Values
		support.setDataVec("string", cf.getField6());
		if(!cf.getHcmbfield6().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield6());
		}else{
		support.setDataVec("string", cf.getCmbfield6());
		}
		support.setDataVec("string", cf.getValfield6());
		support.setDataVec("string", cf.getMndfield6());
		support.setDataVec("string", cf.getVsbfield6());
		
//		Field 7 Values
		support.setDataVec("string", cf.getField7());
		if(!cf.getHcmbfield7().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield7());
		}else{
		support.setDataVec("string", cf.getCmbfield7());
		}
		support.setDataVec("string", cf.getValfield7());
		support.setDataVec("string", cf.getMndfield7());
		support.setDataVec("string", cf.getVsbfield7());
		
//		Field 8 Values
		support.setDataVec("string", cf.getField8());
		if(!cf.getHcmbfield8().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield8());
		}else{
		support.setDataVec("string", cf.getCmbfield8());
		}
		support.setDataVec("string", cf.getValfield8());
		support.setDataVec("string", cf.getMndfield8());
		support.setDataVec("string", cf.getVsbfield8());
		
//		Field 9 Values
		support.setDataVec("string", cf.getField9());
		if(!cf.getHcmbfield9().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield9());
		}else{
		support.setDataVec("string", cf.getCmbfield9());
		}
		support.setDataVec("string", cf.getValfield9());
		support.setDataVec("string", cf.getMndfield9());
		support.setDataVec("string", cf.getVsbfield9());
		
//		Field 10 Values
		support.setDataVec("string", cf.getField10());
		if(!cf.getHcmbfield10().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield10());
		}else{
		support.setDataVec("string", cf.getCmbfield10());
		}
		support.setDataVec("string", cf.getValfield10());
		support.setDataVec("string", cf.getMndfield10());
		support.setDataVec("string", cf.getVsbfield10());
		
//		Field 11 Values
		support.setDataVec("string", cf.getField11());
		if(!cf.getHcmbfield11().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield11());
		}else{
		support.setDataVec("string", cf.getCmbfield11());
		}
		support.setDataVec("string", cf.getValfield11());
		support.setDataVec("string", cf.getMndfield11());
		support.setDataVec("string", cf.getVsbfield11());
		
//		Field 12 Values
		support.setDataVec("string", cf.getField12());
		if(!cf.getHcmbfield11().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield11());
		}else{
		support.setDataVec("string", cf.getCmbfield11());
		}
		support.setDataVec("string", cf.getValfield12());
		support.setDataVec("string", cf.getMndfield12());
		support.setDataVec("string", cf.getVsbfield12());
		
//		Field 13 Values
		support.setDataVec("string", cf.getField13());
		if(!cf.getHcmbfield13().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield13());
		}else{
		support.setDataVec("string", cf.getCmbfield13());
		}
		support.setDataVec("string", cf.getValfield13());
		support.setDataVec("string", cf.getMndfield13());
		support.setDataVec("string", cf.getVsbfield13());
		
//		Field 14 Values
		support.setDataVec("string", cf.getField14());
		if(!cf.getHcmbfield14().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield14());
		}else{
		support.setDataVec("string", cf.getCmbfield14());
		}
		support.setDataVec("string", cf.getValfield14());
		support.setDataVec("string", cf.getMndfield14());
		support.setDataVec("string", cf.getVsbfield14());
		
//		Field 15 Values
		support.setDataVec("string", cf.getField15());
		if(!cf.getHcmbfield15().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield15());
		}else{
		support.setDataVec("string", cf.getCmbfield15());
		}
		support.setDataVec("string", cf.getValfield15());
		support.setDataVec("string", cf.getMndfield15());
		support.setDataVec("string", cf.getVsbfield15());
		
//		Field 16 Values
		support.setDataVec("string", cf.getField16());
		if(!cf.getHcmbfield16().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield16());
		}else{
		support.setDataVec("string", cf.getCmbfield16());
		}
		support.setDataVec("string", cf.getValfield16());
		support.setDataVec("string", cf.getMndfield16());
		support.setDataVec("string", cf.getVsbfield16());
		
//		Field 17 Values
		support.setDataVec("string", cf.getField17());
		if(!cf.getHcmbfield17().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield17());
		}else{
		support.setDataVec("string", cf.getCmbfield17());
		}
		support.setDataVec("string", cf.getValfield17());
		support.setDataVec("string", cf.getMndfield17());
		support.setDataVec("string", cf.getVsbfield17());
		
//		Field 18 Values
		support.setDataVec("string", cf.getField18());
		if(!cf.getHcmbfield10().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield10());
		}else{
		support.setDataVec("string", cf.getCmbfield10());
		}
		support.setDataVec("string", cf.getValfield18());
		support.setDataVec("string", cf.getMndfield18());
		support.setDataVec("string", cf.getVsbfield18());
		
//		Field 19 Values
		support.setDataVec("string", cf.getField19());
		if(!cf.getHcmbfield19().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield19());
		}else{
		support.setDataVec("string", cf.getCmbfield19());
		}
		support.setDataVec("string", cf.getValfield19());
		support.setDataVec("string", cf.getMndfield19());
		support.setDataVec("string", cf.getVsbfield19());
		
//		Field 20 Values
		support.setDataVec("string", cf.getField20());
		if(!cf.getHcmbfield20().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield20());
		}else{
		support.setDataVec("string", cf.getCmbfield20());
		}
		support.setDataVec("string", cf.getValfield20());
		support.setDataVec("string", cf.getMndfield20());
		support.setDataVec("string", cf.getVsbfield20());
		
//		Field 21 Values
		support.setDataVec("string", cf.getField21());
		if(!cf.getHcmbfield21().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield21());
		}else{
		support.setDataVec("string", cf.getCmbfield21());
		}
		support.setDataVec("string", cf.getValfield21());
		support.setDataVec("string", cf.getMndfield21());
		support.setDataVec("string", cf.getVsbfield21());
		
//		Field 2 Values
		support.setDataVec("string", cf.getField22());
		if(!cf.getHcmbfield22().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield22());
		}else{
		support.setDataVec("string", cf.getCmbfield22());
		}
		support.setDataVec("string", cf.getValfield22());
		support.setDataVec("string", cf.getMndfield22());
		support.setDataVec("string", cf.getVsbfield22());
		
//		Field 23 Values
		support.setDataVec("string", cf.getField23());
		if(!cf.getHcmbfield23().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield23());
		}else{
		support.setDataVec("string", cf.getCmbfield23());
		}
		support.setDataVec("string", cf.getValfield23());
		support.setDataVec("string", cf.getMndfield23());
		support.setDataVec("string", cf.getVsbfield23());
		
//		Field 24 Values
		support.setDataVec("string", cf.getField24());
		if(!cf.getHcmbfield24().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield24());
		}else{
		support.setDataVec("string", cf.getCmbfield24());
		}
		support.setDataVec("string", cf.getValfield24());
		support.setDataVec("string", cf.getMndfield24());
		support.setDataVec("string", cf.getVsbfield24());
		
//		Field 25 Values
		support.setDataVec("string", cf.getField25());
		if(!cf.getHcmbfield25().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield25());
		}else{
		support.setDataVec("string", cf.getCmbfield25());
		}
		support.setDataVec("string", cf.getValfield25());
		support.setDataVec("string", cf.getMndfield25());
		support.setDataVec("string", cf.getVsbfield25());
		
//		Field 26 Values
		support.setDataVec("string", cf.getField26());
		if(!cf.getHcmbfield26().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield26());
		}else{
		support.setDataVec("string", cf.getCmbfield26());
		}
		support.setDataVec("string", cf.getValfield26());
		support.setDataVec("string", cf.getMndfield26());
		support.setDataVec("string", cf.getVsbfield26());
		
//		Field 27 Values
		support.setDataVec("string", cf.getField27());
		if(!cf.getHcmbfield27().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield27());
		}else{
		support.setDataVec("string", cf.getCmbfield27());
		}
		support.setDataVec("string", cf.getValfield27());
		support.setDataVec("string", cf.getMndfield27());
		support.setDataVec("string", cf.getVsbfield27());
		
//		Field 28 Values
		support.setDataVec("string", cf.getField28());
		if(!cf.getHcmbfield28().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield28());
		}else{
		support.setDataVec("string", cf.getCmbfield28());
		}
		support.setDataVec("string", cf.getValfield28());
		support.setDataVec("string", cf.getMndfield28());
		support.setDataVec("string", cf.getVsbfield28());
		
//		Field 29 Values
		support.setDataVec("string", cf.getField29());
		if(!cf.getHcmbfield29().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield29());
		}else{
		support.setDataVec("string", cf.getCmbfield29());
		}
		support.setDataVec("string", cf.getValfield29());
		support.setDataVec("string", cf.getMndfield29());
		support.setDataVec("string", cf.getVsbfield29());
		
//		Field 30 Values
		support.setDataVec("string", cf.getField30());
		if(!cf.getHcmbfield30().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield30());
		}else{
		support.setDataVec("string", cf.getCmbfield30());
		}
		support.setDataVec("string", cf.getValfield30());
		support.setDataVec("string", cf.getMndfield30());
		support.setDataVec("string", cf.getVsbfield30());
		
//		Field 31 Values
		support.setDataVec("string", cf.getField31());
		if(!cf.getHcmbfield31().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield31());
		}else{
		support.setDataVec("string", cf.getCmbfield31());
		}
		support.setDataVec("string", cf.getValfield31());
		support.setDataVec("string", cf.getMndfield31());
		support.setDataVec("string", cf.getVsbfield31());
		
//		Field 32 Values
		support.setDataVec("string", cf.getField32());
		if(!cf.getHcmbfield32().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield32());
		}else{
		support.setDataVec("string", cf.getCmbfield32());
		}
		support.setDataVec("string", cf.getValfield32());
		support.setDataVec("string", cf.getMndfield32());
		support.setDataVec("string", cf.getVsbfield32());
		
//		Field 33 Values
		support.setDataVec("string", cf.getField33());
		if(!cf.getHcmbfield33().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield33());
		}else{
		support.setDataVec("string", cf.getCmbfield33());
		}
		support.setDataVec("string", cf.getValfield33());
		support.setDataVec("string", cf.getMndfield33());
		support.setDataVec("string", cf.getVsbfield33());
		
//		Field 34 Values
		support.setDataVec("string", cf.getField34());
		if(!cf.getHcmbfield34().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield34());
		}else{
		support.setDataVec("string", cf.getCmbfield34());
		}
		support.setDataVec("string", cf.getValfield34());
		support.setDataVec("string", cf.getMndfield34());
		support.setDataVec("string", cf.getVsbfield34());
		
//		Field 35 Values
		support.setDataVec("string", cf.getField35());
		if(!cf.getHcmbfield35().toString().equalsIgnoreCase("Select")){
			support.setDataVec("string", cf.getHcmbfield35());
		}else{
		support.setDataVec("string", cf.getCmbfield35());
		}
		support.setDataVec("string", cf.getValfield35());
		support.setDataVec("string", cf.getMndfield35());
		support.setDataVec("string", cf.getVsbfield35());
		
		support.setDataVec("int", String.valueOf(numCompId));
		
		
		//////////System.out.println("Category from Form "+cf.getCmbcat());
		if(blnStatus==false)
		{
			MasterUtility mu = new MasterUtility();
										
			String strQuery = "Insert into consumer_mapping (";
			for(int i=1; i<=35; i++)
			{
				strQuery += "field"+i;
				strQuery += ", field"+i+"_type";
				strQuery += ", field"+i+"_value";
				strQuery += ", field"+i+"_check";
				strQuery += ", field"+i+"_visible, ";
			}
			strQuery += "companyid ) Values(";
			for(int j=1; j<=176; j++)
			{
				strQuery += "?,";
				if(j==176)
				{
					strQuery = strQuery.substring(0, strQuery.lastIndexOf(","));
				}
				//////////System.out.println(strQuery);
			}
			strQuery += ")";
			strResult = mu.setData(ds, strQuery, support);
			//////////System.out.println(strQuery);
		}
		else
		{
			MasterUtility mu = new MasterUtility();
			//Support support = new Support();
			String strQuery = "Update consumer_mapping Set ";
			
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
			strQuery += " Where companyid = ? ";
			
			strResult = mu.setData(ds, strQuery, support);
			//////////System.out.println(strQuery);
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
	public Vector getCommunityUnnamedMap(DataSource ds, int numCompId)
	{
		Vector resultVec = new Vector();
//		Get Unnamed Mapping Id here.
		boolean blnStatus = getCommunityUnnamedMap(numCompId, ds);
		////////System.out.println("blnStatus blnStatus "+blnStatus);
		//Check if Unnamed Mapping id greter then 0.
		if(blnStatus==true)
		{
			MasterUtility mu = new MasterUtility();
			String strQuery = "Select * from consumer_mapping Where companyid = "+numCompId;
			Support support = new Support();
			for(int i=1; i<=35; i++)
			{
				support.setFieldVec("string", "field"+i);
				support.setFieldVec("string", "field"+i+"_type");
				support.setFieldVec("string", "field"+i+"_value");
				support.setFieldVec("string", "field"+i+"_check");
				support.setFieldVec("string", "field"+i+"_visible");
			}
			
			////////System.out.println("strQuery strQuery "+strQuery);
			resultVec = mu.getDetail(ds, strQuery, support);
			////////System.out.println("Result Vector "+resultVec);
		}
		else
		{
			
			
		}
		return resultVec;
	}
	
	public String getFinalComplainId(int numCUId, int numComplaintID, String year)
	{
		String strResult="";
		Resource rsource = new Resource();
		String strComplaintId = "", strCuId = "", strComplaintYear="";
		
		long numComplaintId = rsource.rndCompalintId(numComplaintID, 99999);
		if(numComplaintId<10){strComplaintId="00000"+numComplaintId;}
		else if(numComplaintId<100){strComplaintId="0000"+numComplaintId;}
		else if(numComplaintId<1000){strComplaintId="000"+numComplaintId;}
		else if(numComplaintId<10000){strComplaintId="00"+numComplaintId;}
		else if(numComplaintId<100000){strComplaintId="0"+numComplaintId;}
		else
		{
			String codeNumber = numComplaintId+"";
			int len = codeNumber.length();
			String fcodeNumber = codeNumber.substring(len-6,len);
			strComplaintId = fcodeNumber;
		}
		
		long numCuId = rsource.rndEntpUserId(numCUId, 99999);
		if(numCuId<10){strCuId="00000"+numCuId;}
		else if(numCuId<100){strCuId="0000"+numCuId;}
		else if(numCuId<1000){strCuId="000"+numCuId;}
		else if(numCuId<10000){strCuId="00"+numCuId;}
		else if(numCuId<100000){strCuId="0"+numCuId;}
		else
		{
			String codeNumber = numCuId+"";
			int len = codeNumber.length();
			String fcodeNumber = codeNumber.substring(len-6,len);
			strCuId = fcodeNumber;
		}
		long numCompaintYear = rsource.rndComplaintYear(Integer.parseInt(year.trim()), 9999);
		if(numCompaintYear<10){strCuId="000"+numCompaintYear;}
		else if(numCompaintYear<100){strCuId="00"+numCompaintYear;}
		else if(numCompaintYear<1000){strCuId="0"+numCompaintYear;}
		else
		{
			String codeNumber = numCompaintYear+"";
			int len = codeNumber.length();
			String fcodeNumber = codeNumber.substring(len-4,len);
			strComplaintYear = fcodeNumber;
		}
		
		strResult = strComplaintYear+strCuId+strComplaintId;
			
		return strResult;		
	}
	
	/**
	 * **************************************************************************************************
     *	update `entp_rflag` in communication table and return result status result string.
	 *********************************************************************************************************
	 */	
		public String updateInbox(DataSource ds,Vector dataVec){
		
		Support support=new Support();
		String strQuery="";
	    String strResult = "";
	   
		
		strQuery = "Update communication set entp_rflag = 1 where respid in ("+dataVec.elementAt(0).toString().trim()+")";
		//////System.out.println("dc strQuery >>>>>"+strQuery);
				
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
		
		}

			/**
			 *  This function is used for all count in entp.
			 */
			public Vector complaintCountEntp(DataSource ds,Vector entpDataVec){
				
			/************************************************************************************
				entpDataVec.elementAt(0) = //loginId
				entpDataVec.elementAt(1) = //companyId
				entpDataVec.elementAt(2) = //adminFlag;
							
			*************************************************************************************/
			
			Vector resultVec = new Vector();
			
			Vector dataVec1 = new Vector();
			Vector dataVec2 = new Vector();
			Vector dataVec3 = new Vector();
			Vector dataVec4 = new Vector();
			Vector dataVec5 = new Vector();
			Vector dataVec6 = new Vector();
			Vector dataVec7 = new Vector();
			Vector dataVec8 = new Vector();
			Vector dataVec9 = new Vector();
			Vector dataVec10 = new Vector();
			Vector dataVec11 = new Vector();
			
			
			dataVec1.add("current");
			dataVec1.add(entpDataVec.elementAt(0).toString());
			dataVec1.add(entpDataVec.elementAt(1).toString());
			dataVec1.add(entpDataVec.elementAt(2).toString());
			int currentCount = getComplaintCount(ds, dataVec1);	
			
			dataVec2.add("All");
			dataVec2.add(entpDataVec.elementAt(0).toString());
			dataVec2.add(entpDataVec.elementAt(1).toString());
			dataVec2.add(entpDataVec.elementAt(2).toString());
			int allCount = getComplaintCount(ds, dataVec2);	
			
			dataVec3.add("under");
			dataVec3.add(entpDataVec.elementAt(0).toString());
			dataVec3.add(entpDataVec.elementAt(1).toString());
			dataVec3.add(entpDataVec.elementAt(2).toString());
			int underCount = getComplaintCount(ds, dataVec3);	
			
			dataVec4.add("resolved");
			dataVec4.add(entpDataVec.elementAt(0).toString());
			dataVec4.add(entpDataVec.elementAt(1).toString());
			dataVec4.add(entpDataVec.elementAt(2).toString());
			int resolvedCount = getComplaintCount(ds, dataVec4);	
			
			dataVec5.add("closed");
			dataVec5.add(entpDataVec.elementAt(0).toString());
			dataVec5.add(entpDataVec.elementAt(1).toString());
			dataVec5.add(entpDataVec.elementAt(2).toString());
			dataVec5.add("1");
			int resolCount = getComplaintCount(ds, dataVec5);	
			
			dataVec6.add("closed");
			dataVec6.add(entpDataVec.elementAt(0).toString());
			dataVec6.add(entpDataVec.elementAt(1).toString());
			dataVec6.add(entpDataVec.elementAt(2).toString());
			dataVec6.add("2");
			int unResolCount = getComplaintCount(ds, dataVec6);
			
			dataVec7.add("closed");
			dataVec7.add(entpDataVec.elementAt(0).toString());
			dataVec7.add(entpDataVec.elementAt(1).toString());
			dataVec7.add(entpDataVec.elementAt(2).toString());
			dataVec7.add("3");
			int rejetedCount = getComplaintCount(ds, dataVec7);
			
			int closedCount = resolCount + unResolCount + rejetedCount;
			
			dataVec8.add(entpDataVec.elementAt(0).toString());
			dataVec8.add(entpDataVec.elementAt(2).toString());
			dataVec8.add("Advertiser");
			int inboxCountAdvt = getInboxCount(ds, dataVec8);
			
			dataVec9.add(entpDataVec.elementAt(0).toString());
			dataVec9.add(entpDataVec.elementAt(2).toString());
			dataVec9.add("Consumer");
			int inboxCountIndv = getInboxCount(ds, dataVec9);
			
			int inboxCount = inboxCountAdvt + inboxCountIndv;
			
			dataVec10.add(entpDataVec.elementAt(1).toString());
			dataVec10.add("0");
			int unallottedCount = getUnallottedComplaintCount(ds, dataVec10);
			
			
			dataVec11.add(entpDataVec.elementAt(1).toString());						
			int otherCount = getOtherCompTotalCount(ds, dataVec11);
			
			
			
			resultVec.add(currentCount);    	// 0 current
			resultVec.add(allCount);			// 1 All
			resultVec.add(underCount);      	// 2 under
			resultVec.add(resolvedCount);		// 3 resolved
			resultVec.add(resolCount); 			// 4 closed resolved
			resultVec.add(unResolCount); 		// 5 closed unresolved
			resultVec.add(rejetedCount);		// 6 closed rejeted			
			resultVec.add(closedCount);     	// 7 closed total
			resultVec.add(inboxCount);      	// 8 total inbox
			resultVec.add(inboxCountIndv);  	// 9 indv inbox
			resultVec.add(inboxCountAdvt);  	// 10 advt inbox
			resultVec.add(unallottedCount); 	// 11 unallotted
			resultVec.add(otherCount);			// 12 other
			
			
			return resultVec;
			}
			
	//this function is use for getting MIS report
			
	public Vector getMisReport(DataSource ds,Vector dataVec)
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
		//int min = Integer.parseInt(dataVec.elementAt(0).toString().trim());
		//int max = Integer.parseInt(dataVec.elementAt(1).toString().trim());	
		String field1 = dataVec.elementAt(0).toString().trim();
		String field2 = dataVec.elementAt(1).toString().trim();
		String field3 = dataVec.elementAt(2).toString().trim();	
		String date1 = dataVec.elementAt(3).toString().trim();
		String date2 = dataVec.elementAt(4).toString().trim();
		
				
		Support support=new Support();
		 String strQuery ="";
		 String subQuery = "";
		 if(field1.equals("0"))
		 {
			 subQuery = "";
		 }
		 else if(field1.equals("1"))
		 {
			 subQuery = "cmp.brandFlag=1 and ";
		 }
		 else if(field1.equals("2"))
		 {
			 subQuery= "cmp.tag_id=4 and cmp.resolve_date != 0000-00-00 and  cmp.reject_date= 0000-00-00 and ";
		 }
		 else if(field1.equals("3"))
		 {
			 subQuery= "cmp.tag_id = 4 and cmp.resolve_date = 0000-00-00 and cmp.reject_date = 0000-00-00  and ";
		 }
		 else if(field1.equals("4"))
		 {
			 subQuery= "cmp.tag_id =4 and cmp.reject_date!= 0000-00-00 and ";
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
		 
		
		if(!field1.equals("5"))
		{
			if(field2.equals("0"))//categorywise
			{
		    strQuery="select cat.cat_name cat_name, comp.company_name company_name, creation_date creation_date, count(*) numcount " +
		    				"from complaints cmp, category cat,companymaster comp " +
		    				"where "+subQuery +" cmp.category = cat.cat_id and cmp.advt_id = comp.company_id " +timeQuery +
		    				"group by  cmp.category, comp.company_id ,cmp.creation_date " +
		    				"order by cat.cat_name, cmp.creation_date";
		    support.setFieldVec("string", "cat_name");
			support.setFieldVec("string", "company_name");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			}
			else if(field2.equals("1"))//brand wise
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
			else if(field2.equals("2"))//Statewise
			{
		    strQuery="select cat.cat_name cat_name, comp.company_name company_name," +
		    		" creation_date creation_date, count(*) numcount ,st.statename statename ,pl.Place Place "+
		    		"from complaints cmp, category cat,companymaster comp,loginmaster lm ,places pl,states st "+
		    		"where "+subQuery +" cmp.category = cat.cat_id and cmp.advt_id = comp.company_id "+
	                " and lm.userid=cmp.login_id and st.stateid=lm.state and pl.PlaceID = lm.city "+timeQuery +
		    		"group by  st.statename, cmp.category, comp.company_id ,cmp.creation_date "+
		    		"order by st.statename,cat.cat_name,  cmp.creation_date;";
		   
		    support.setFieldVec("string", "cat_name");
			support.setFieldVec("string", "company_name");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			 support.setFieldVec("string", "statename");
			    support.setFieldVec("string", "Place");
			}
			else if(field2.equals("3"))//citywise
			{
		    strQuery="select cat.cat_name cat_name, comp.company_name company_name," +
		    		" creation_date creation_date, count(*) numcount ,pl.Place Place,st.statename statename "+
		    		"from complaints cmp, category cat,companymaster comp,loginmaster lm ,places pl,states st "+
		    		"where "+subQuery +" cmp.category = cat.cat_id and cmp.advt_id = comp.company_id "+
	                " and lm.userid=cmp.login_id and pl.PlaceID = lm.city and st.stateid=lm.state "+timeQuery +
		    		"group by  pl.Place,cmp.category, comp.company_id ,cmp.creation_date "+
		    		"order by pl.Place,cat.cat_name,  cmp.creation_date;";
		   
		    support.setFieldVec("string", "cat_name");
			support.setFieldVec("string", "company_name");
			support.setFieldVec("string", "creation_date");								
			support.setFieldVec("int", "numcount");	
			support.setFieldVec("string", "Place");
			support.setFieldVec("string", "statename");
			}
			
			else if(field2.equals("4"))//brand wise time taken  (Under construction)
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
		else
		{
			if(field2.equals("0"))         //recieived
			 {
				 subQuery = "";
			 }
			 else if(field2.equals("1"))  //Attended
			 {
				 subQuery = "cmp.brandFlag=1 and ";
			 }
			 else if(field2.equals("2"))  //closed Resolved
			 {
				 subQuery= "cmp.tag_id=4 and cmp.resolve_date != 0000-00-00 and  cmp.reject_date= 0000-00-00 and ";
			 }
			 else if(field2.equals("3"))  //closed unresolved
			 {
				 subQuery= "cmp.tag_id = 4 and cmp.resolve_date = 0000-00-00 and cmp.reject_date = 0000-00-00  and ";
			 }
			 else if(field2.equals("4"))  //closed rejected
			 {
				 subQuery= "cmp.tag_id =4 and cmp.reject_date!= 0000-00-00 and ";
			 }
			 else if(field2.equals("5")) //time wise (Under construction)
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
		
		
		
		resultVec = masterUtil.getList(ds, strQuery, support);
		return resultVec;
	}
	
	// 26-02-2008
	
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
		
//		Field 1 Values
		support.setDataVec("string", cf.getField1());
		support.setDataVec("string", cf.getCmbfield1());
		support.setDataVec("string", cf.getValfield1());
		support.setDataVec("string", cf.getMndfield1());
		support.setDataVec("string", cf.getVsbfield1());
		
//		Field 2 Values
		support.setDataVec("string", cf.getField2());
		support.setDataVec("string", cf.getCmbfield2());
		support.setDataVec("string", cf.getValfield2());
		support.setDataVec("string", cf.getMndfield2());
		support.setDataVec("string", cf.getVsbfield2());
		
//		Field 3 Values
		support.setDataVec("string", cf.getField3());
		support.setDataVec("string", cf.getCmbfield3());
		support.setDataVec("string", cf.getValfield3());
		support.setDataVec("string", cf.getMndfield3());
		support.setDataVec("string", cf.getVsbfield3());
		
//		Field 4 Values
		support.setDataVec("string", cf.getField4());
		support.setDataVec("string", cf.getCmbfield4());
		support.setDataVec("string", cf.getValfield4());
		support.setDataVec("string", cf.getMndfield4());
		support.setDataVec("string", cf.getVsbfield4());
		
//		Field 5 Values
		support.setDataVec("string", cf.getField5());
		support.setDataVec("string", cf.getCmbfield5());
		support.setDataVec("string", cf.getValfield5());
		support.setDataVec("string", cf.getMndfield5());
		support.setDataVec("string", cf.getVsbfield5());
		
//		Field 6 Values
		support.setDataVec("string", cf.getField6());
		support.setDataVec("string", cf.getCmbfield6());
		support.setDataVec("string", cf.getValfield6());
		support.setDataVec("string", cf.getMndfield6());
		support.setDataVec("string", cf.getVsbfield6());
		
//		Field 7 Values
		support.setDataVec("string", cf.getField7());
		support.setDataVec("string", cf.getCmbfield7());
		support.setDataVec("string", cf.getValfield7());
		support.setDataVec("string", cf.getMndfield7());
		support.setDataVec("string", cf.getVsbfield7());
		
//		Field 8 Values
		support.setDataVec("string", cf.getField8());
		support.setDataVec("string", cf.getCmbfield8());
		support.setDataVec("string", cf.getValfield8());
		support.setDataVec("string", cf.getMndfield8());
		support.setDataVec("string", cf.getVsbfield8());
		
//		Field 9 Values
		support.setDataVec("string", cf.getField9());
		support.setDataVec("string", cf.getCmbfield9());
		support.setDataVec("string", cf.getValfield9());
		support.setDataVec("string", cf.getMndfield9());
		support.setDataVec("string", cf.getVsbfield9());
		
//		Field 10 Values
		support.setDataVec("string", cf.getField10());
		support.setDataVec("string", cf.getCmbfield10());
		support.setDataVec("string", cf.getValfield10());
		support.setDataVec("string", cf.getMndfield10());
		support.setDataVec("string", cf.getVsbfield10());
		
//		Field 11 Values
		support.setDataVec("string", cf.getField11());
		support.setDataVec("string", cf.getCmbfield11());
		support.setDataVec("string", cf.getValfield11());
		support.setDataVec("string", cf.getMndfield11());
		support.setDataVec("string", cf.getVsbfield11());
		
//		Field 12 Values
		support.setDataVec("string", cf.getField12());
		support.setDataVec("string", cf.getCmbfield12());
		support.setDataVec("string", cf.getValfield12());
		support.setDataVec("string", cf.getMndfield12());
		support.setDataVec("string", cf.getVsbfield12());
		
//		Field 13 Values
		support.setDataVec("string", cf.getField13());
		support.setDataVec("string", cf.getCmbfield13());
		support.setDataVec("string", cf.getValfield13());
		support.setDataVec("string", cf.getMndfield13());
		support.setDataVec("string", cf.getVsbfield13());
		
//		Field 14 Values
		support.setDataVec("string", cf.getField14());
		support.setDataVec("string", cf.getCmbfield14());
		support.setDataVec("string", cf.getValfield14());
		support.setDataVec("string", cf.getMndfield14());
		support.setDataVec("string", cf.getVsbfield14());
		
//		Field 15 Values
		support.setDataVec("string", cf.getField15());
		support.setDataVec("string", cf.getCmbfield15());
		support.setDataVec("string", cf.getValfield15());
		support.setDataVec("string", cf.getMndfield15());
		support.setDataVec("string", cf.getVsbfield15());
		
//		Field 16 Values
		support.setDataVec("string", cf.getField16());
		support.setDataVec("string", cf.getCmbfield16());
		support.setDataVec("string", cf.getValfield16());
		support.setDataVec("string", cf.getMndfield16());
		support.setDataVec("string", cf.getVsbfield16());
		
//		Field 17 Values
		support.setDataVec("string", cf.getField17());
		support.setDataVec("string", cf.getCmbfield17());
		support.setDataVec("string", cf.getValfield17());
		support.setDataVec("string", cf.getMndfield17());
		support.setDataVec("string", cf.getVsbfield17());
		
//		Field 18 Values
		support.setDataVec("string", cf.getField18());
		support.setDataVec("string", cf.getCmbfield18());
		support.setDataVec("string", cf.getValfield18());
		support.setDataVec("string", cf.getMndfield18());
		support.setDataVec("string", cf.getVsbfield18());
		
//		Field 19 Values
		support.setDataVec("string", cf.getField19());
		support.setDataVec("string", cf.getCmbfield19());
		support.setDataVec("string", cf.getValfield19());
		support.setDataVec("string", cf.getMndfield19());
		support.setDataVec("string", cf.getVsbfield19());
		
//		Field 20 Values
		support.setDataVec("string", cf.getField20());
		support.setDataVec("string", cf.getCmbfield20());
		support.setDataVec("string", cf.getValfield20());
		support.setDataVec("string", cf.getMndfield20());
		support.setDataVec("string", cf.getVsbfield20());
		
//		Field 21 Values
		support.setDataVec("string", cf.getField21());
		support.setDataVec("string", cf.getCmbfield21());
		support.setDataVec("string", cf.getValfield21());
		support.setDataVec("string", cf.getMndfield21());
		support.setDataVec("string", cf.getVsbfield21());
		
//		Field 2 Values
		support.setDataVec("string", cf.getField22());
		support.setDataVec("string", cf.getCmbfield22());
		support.setDataVec("string", cf.getValfield22());
		support.setDataVec("string", cf.getMndfield22());
		support.setDataVec("string", cf.getVsbfield22());
		
//		Field 23 Values
		support.setDataVec("string", cf.getField23());
		support.setDataVec("string", cf.getCmbfield23());
		support.setDataVec("string", cf.getValfield23());
		support.setDataVec("string", cf.getMndfield23());
		support.setDataVec("string", cf.getVsbfield23());
		
//		Field 24 Values
		support.setDataVec("string", cf.getField24());
		support.setDataVec("string", cf.getCmbfield24());
		support.setDataVec("string", cf.getValfield24());
		support.setDataVec("string", cf.getMndfield24());
		support.setDataVec("string", cf.getVsbfield24());
		
//		Field 25 Values
		support.setDataVec("string", cf.getField25());
		support.setDataVec("string", cf.getCmbfield25());
		support.setDataVec("string", cf.getValfield25());
		support.setDataVec("string", cf.getMndfield25());
		support.setDataVec("string", cf.getVsbfield25());
		
//		Field 26 Values
		support.setDataVec("string", cf.getField26());
		support.setDataVec("string", cf.getCmbfield26());
		support.setDataVec("string", cf.getValfield26());
		support.setDataVec("string", cf.getMndfield26());
		support.setDataVec("string", cf.getVsbfield26());
		
//		Field 27 Values
		support.setDataVec("string", cf.getField27());
		support.setDataVec("string", cf.getCmbfield27());
		support.setDataVec("string", cf.getValfield27());
		support.setDataVec("string", cf.getMndfield27());
		support.setDataVec("string", cf.getVsbfield27());
		
//		Field 28 Values
		support.setDataVec("string", cf.getField28());
		support.setDataVec("string", cf.getCmbfield28());
		support.setDataVec("string", cf.getValfield28());
		support.setDataVec("string", cf.getMndfield28());
		support.setDataVec("string", cf.getVsbfield28());
		
//		Field 29 Values
		support.setDataVec("string", cf.getField29());
		support.setDataVec("string", cf.getCmbfield29());
		support.setDataVec("string", cf.getValfield29());
		support.setDataVec("string", cf.getMndfield29());
		support.setDataVec("string", cf.getVsbfield29());
		
//		Field 30 Values
		support.setDataVec("string", cf.getField30());
		support.setDataVec("string", cf.getCmbfield30());
		support.setDataVec("string", cf.getValfield30());
		support.setDataVec("string", cf.getMndfield30());
		support.setDataVec("string", cf.getVsbfield30());
		
//		Field 31 Values
		support.setDataVec("string", cf.getField31());
		support.setDataVec("string", cf.getCmbfield31());
		support.setDataVec("string", cf.getValfield31());
		support.setDataVec("string", cf.getMndfield31());
		support.setDataVec("string", cf.getVsbfield31());
		
//		Field 32 Values
		support.setDataVec("string", cf.getField32());
		support.setDataVec("string", cf.getCmbfield32());
		support.setDataVec("string", cf.getValfield32());
		support.setDataVec("string", cf.getMndfield32());
		support.setDataVec("string", cf.getVsbfield32());
		
//		Field 33 Values
		support.setDataVec("string", cf.getField33());
		support.setDataVec("string", cf.getCmbfield33());
		support.setDataVec("string", cf.getValfield33());
		support.setDataVec("string", cf.getMndfield33());
		support.setDataVec("string", cf.getVsbfield33());
		
//		Field 34 Values
		support.setDataVec("string", cf.getField34());
		support.setDataVec("string", cf.getCmbfield34());
		support.setDataVec("string", cf.getValfield34());
		support.setDataVec("string", cf.getMndfield34());
		support.setDataVec("string", cf.getVsbfield34());
		
//		Field 35 Values
		support.setDataVec("string", cf.getField35());
		support.setDataVec("string", cf.getCmbfield35());
		support.setDataVec("string", cf.getValfield35());
		support.setDataVec("string", cf.getMndfield35());
		support.setDataVec("string", cf.getVsbfield35());
		
		support.setDataVec("int", String.valueOf(numCompId));
		support.setDataVec("int", String.valueOf(numCatId));
		
		////////////////System.out.println("Category from Form "+cf.getCmbcat());
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
				////////////////System.out.println(strQuery);
			}
			strQuery += ")";
			strResult = mu.setData(ds, strQuery, support);
			////////////////System.out.println(strQuery);
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
			////////////////System.out.println(strQuery);
		}
		
		
		return strResult;
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
//		Sub Category1/ Field2
		if(strPageId.equalsIgnoreCase("click2"))
		{
			strQuery = "Select count(*) from subcategory1 where companyid="+numCompId+" and subcat1_name='"+strValue+"' and subcat_id="+numCatId;
		}
//		Sub Category2/ Field3
		if(strPageId.equalsIgnoreCase("click3"))
		{
			strQuery = "Select count(*) from subcategory2 where companyid="+numCompId+" and subcat2_name='"+strValue+"' and subcat1_id="+numCatId;
		}
//		Sub Category3/ Field4
		if(strPageId.equalsIgnoreCase("click4"))
		{
			strQuery = "Select count(*) from subcategory3 where companyid="+numCompId+" and subcat3_name='"+strValue+"' and subcat2_id="+numCatId;
		}
//		Sub Category4/ Field5
		if(strPageId.equalsIgnoreCase("click5"))
		{
			strQuery = "Select count(*) from subcategory4 where companyid="+numCompId+" and subcat4_name='"+strValue+"' and subcat3_id="+numCatId;
		}
//		Sub Category5/ Field6
		if(strPageId.equalsIgnoreCase("click6"))
		{
			strQuery = "Select count(*) from subcategory5 where companyid="+numCompId+" and subcat5_name='"+strValue+"' and subcat4_id="+numCatId;
		}
//		Sub Category6/ Field7
		if(strPageId.equalsIgnoreCase("click7"))
		{
			strQuery = "Select count(*) from subcategory6 where companyid="+numCompId+" and subcat6_name='"+strValue+"' and subcat5_id="+numCatId;
		}
//		Sub Category7/ Field8
		if(strPageId.equalsIgnoreCase("click8"))
		{
			strQuery = "Select count(*) from subcategory7 where companyid="+numCompId+" and subcat7_name='"+strValue+"' and subcat6_id="+numCatId;
		}
//		Sub Category8/ Field9
		if(strPageId.equalsIgnoreCase("click9"))
		{
			strQuery = "Select count(*) from subcategory8 where companyid="+numCompId+" and subcat8_name='"+strValue+"' and subcat7_id="+numCatId;
		}
//		Sub Category9/ Field10
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
//			Sub Category1/ Field2
			if(strPageId.equalsIgnoreCase("click2"))
			{
				strQuery += "subcategory1 (companyid, subcat1_name, subcat_id";
			}
//			Sub Category2/ Field3
			if(strPageId.equalsIgnoreCase("click3"))
			{
				strQuery += "subcategory2 (companyid, subcat2_name, subcat1_id";
			}
//			Sub Category3/ Field4
			if(strPageId.equalsIgnoreCase("click4"))
			{
				strQuery += "subcategory3 (companyid, subcat3_name, subcat2_id";
			}
//			Sub Category4/ Field5
			if(strPageId.equalsIgnoreCase("click5"))
			{
				strQuery += "subcategory4 (companyid, subcat4_name, subcat3_id";
			}
//			Sub Category5/ Field6
			if(strPageId.equalsIgnoreCase("click6"))
			{
				strQuery += "subcategory5 (companyid, subcat5_name, subcat4_id";
			}
//			Sub Category6/ Field7
			if(strPageId.equalsIgnoreCase("click7"))
			{
				strQuery += "subcategory6 (companyid, subcat6_name, subcat5_id";
			}
//			Sub Category7/ Field8
			if(strPageId.equalsIgnoreCase("click8"))
			{
				strQuery += "subcategory7 (companyid, subcat7_name, subcat6_id";
			}
//			Sub Category8/ Field9
			if(strPageId.equalsIgnoreCase("click9"))
			{
				strQuery += "subcategory8 (companyid, subcat8_name, subcat7_id";
			}
//			Sub Category9/ Field10
			if(strPageId.equalsIgnoreCase("click10"))
			{
				strQuery += "subcategory9 (companyid, subcat9_name, subcat8_id";
			}
			strQuery +=") Values(?, ?, ?)";
			////////////////System.out.println("Category Id >> "+numCatid);
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
//		Sub Category1/ Field2
		if(strPageId.equalsIgnoreCase("click2"))
		{
			strQuery += "subcategory1 set subcat1_name=? where companyid=? and subcat1_id=?";
		}
//		Sub Category2/ Field3
		if(strPageId.equalsIgnoreCase("click3"))
		{
			strQuery += "subcategory2 set subcat2_name=? where companyid=? and subcat2_id=?";
		}
//		Sub Category3/ Field4
		if(strPageId.equalsIgnoreCase("click4"))
		{
			strQuery += "subcategory3 set subcat3_name=? where companyid=? and subcat3_id=?";
		}
//		Sub Category4/ Field5
		if(strPageId.equalsIgnoreCase("click5"))
		{
			strQuery += "subcategory4 set subcat4_name=? where companyid=? and subcat4_id=?";
		}
//		Sub Category5/ Field6
		if(strPageId.equalsIgnoreCase("click6"))
		{
			strQuery += "subcategory5 set subcat5_name=? where companyid=? and subcat5_id=?";
		}
//		Sub Category6/ Field7
		if(strPageId.equalsIgnoreCase("click7"))
		{
			strQuery += "subcategory6 set subcat6_name=? where companyid=? and subcat6_id=?";
		}
//		Sub Category7/ Field8
		if(strPageId.equalsIgnoreCase("click8"))
		{
			strQuery += "subcategory7 set subcat7_name=? where companyid=? and subcat7_id=?";
		}
//		Sub Category8/ Field9
		if(strPageId.equalsIgnoreCase("click9"))
		{
			strQuery += "subcategory8 set subcat8_name=? where companyid=? and subcat8_id=?";
		}
//		Sub Category9/ Field10
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
				//////////////System.out.println("Subcategory 1 Result "+strResult);
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
					//////////////System.out.println("Subcategory 2 Result "+strResult1);
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
						//////////////System.out.println("Subcategory 3 Result "+strResult2);
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
							//////////////System.out.println("Subcategory 4 Result "+strResult3);
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
								//////////////System.out.println("Subcategory 5 Result "+strResult4);
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
									//////////////System.out.println("Subcategory 6 Result "+strResult5);
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
										//////////////System.out.println("Subcategory 7 Result "+strResult6);
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
											//////////////System.out.println("Subcategory 8 Result "+strResult7);
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
												//////////////System.out.println("Subcategory 8 Result "+strResult8);
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
//				to delete related values
				String strDelQuery1 = "delete from subcategory2 where subcat2_id in("+strStatus1+")";
				String strResult1 = mu.deleteData(ds, strDelQuery1);
				//////////////System.out.println("Subcategory 2 Result "+strResult1);
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
					//////////////System.out.println("Subcategory 3 Result "+strResult2);
					if(statusVec3.size()>0)
					{
						String strStatus3 = "";
						for(int i=0; i<statusVec3.size(); i++)
						{
							strStatus3 = strStatus3.equalsIgnoreCase("") ? statusVec3.elementAt(i).toString().trim() : strStatus3+","+statusVec3.elementAt(i).toString().trim();
						}
						String strQuery4 = "Select subcat5_id from subcategory5 where subcat4_id in("+strStatus3+")";
						Vector statusVec4 = mu.getIds(ds, strQuery4);
//						to delete related values
						String strDelQuery3 = "delete from subcategory4 where subcat4_id in("+strStatus3+")";
						String strResult3 = mu.deleteData(ds, strDelQuery3);
						//////////////System.out.println("Subcategory 4 Result "+strResult3);
						if(statusVec4.size()>0)
						{
							String strStatus4 = "";
							for(int i=0; i<statusVec4.size(); i++)
							{
								strStatus4 = strStatus4.equalsIgnoreCase("") ? statusVec4.elementAt(i).toString().trim() : strStatus4+","+statusVec4.elementAt(i).toString().trim();
							}
							String strQuery5 = "Select subcat6_id from subcategory6 where subcat5_id in("+strStatus4+")";
							Vector statusVec5 = mu.getIds(ds, strQuery5);
//							to delete related values
							String strDelQuery4 = "delete from subcategory5 where subcat5_id in("+strStatus4+")";
							String strResult4 = mu.deleteData(ds, strDelQuery4);
							//////////////System.out.println("Subcategory 5 Result "+strResult4);
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
								//////////////System.out.println("Subcategory 6 Result "+strResult5);
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
									//////////////System.out.println("Subcategory 7 Result "+strResult6);
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
										//////////////System.out.println("Subcategory 8 Result "+strResult7);
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
											//////////////System.out.println("Subcategory 8 Result "+strResult8);
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
				//////////////System.out.println("Subcategory 3 Result "+strResult2);
				if(statusVec3.size()>0)
				{
					String strStatus3 = "";
					for(int i=0; i<statusVec3.size(); i++)
					{
						strStatus3 = strStatus3.equalsIgnoreCase("") ? statusVec3.elementAt(i).toString().trim() : strStatus3+","+statusVec3.elementAt(i).toString().trim();
					}
					String strQuery4 = "Select subcat5_id from subcategory5 where subcat4_id in("+strStatus3+")";
					Vector statusVec4 = mu.getIds(ds, strQuery4);
//					to delete related values
					String strDelQuery3 = "delete from subcategory4 where subcat4_id in("+strStatus3+")";
					String strResult3 = mu.deleteData(ds, strDelQuery3);
					//////////////System.out.println("Subcategory 4 Result "+strResult3);
					if(statusVec4.size()>0)
					{
						String strStatus4 = "";
						for(int i=0; i<statusVec4.size(); i++)
						{
							strStatus4 = strStatus4.equalsIgnoreCase("") ? statusVec4.elementAt(i).toString().trim() : strStatus4+","+statusVec4.elementAt(i).toString().trim();
						}
						String strQuery5 = "Select subcat6_id from subcategory6 where subcat5_id in("+strStatus4+")";
						Vector statusVec5 = mu.getIds(ds, strQuery5);
//						to delete related values
						String strDelQuery4 = "delete from subcategory5 where subcat5_id in("+strStatus4+")";
						String strResult4 = mu.deleteData(ds, strDelQuery4);
						//////////////System.out.println("Subcategory 5 Result "+strResult4);
						if(statusVec5.size()>0)
						{
							String strStatus5 = "";
							for(int i=0; i<statusVec5.size(); i++)
							{
								strStatus5 = strStatus5.equalsIgnoreCase("") ? statusVec5.elementAt(i).toString().trim() : strStatus5+","+statusVec5.elementAt(i).toString().trim();
							}
							String strQuery6 = "Select subcat7_id from subcategory7 where subcat6_id in("+strStatus5+")";
							Vector statusVec6 = mu.getIds(ds, strQuery6);
//							to delete related values
							String strDelQuery5 = "delete from subcategory6 where subcat6_id in("+strStatus5+")";
							String strResult5 = mu.deleteData(ds, strDelQuery5);
							//////////////System.out.println("Subcategory 6 Result "+strResult5);
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
								//////////////System.out.println("Subcategory 7 Result "+strResult6);
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
									//////////////System.out.println("Subcategory 8 Result "+strResult7);
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
										//////////////System.out.println("Subcategory 8 Result "+strResult8);
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
//				to delete related values
				String strDelQuery3 = "delete from subcategory4 where subcat4_id in("+strStatus3+")";
				String strResult3 = mu.deleteData(ds, strDelQuery3);
				//////////////System.out.println("Subcategory 4 Result "+strResult3);
				if(statusVec4.size()>0)
				{
					String strStatus4 = "";
					for(int i=0; i<statusVec4.size(); i++)
					{
						strStatus4 = strStatus4.equalsIgnoreCase("") ? statusVec4.elementAt(i).toString().trim() : strStatus4+","+statusVec4.elementAt(i).toString().trim();
					}
					String strQuery5 = "Select subcat6_id from subcategory6 where subcat5_id in("+strStatus4+")";
					Vector statusVec5 = mu.getIds(ds, strQuery5);
//					to delete related values
					String strDelQuery4 = "delete from subcategory5 where subcat5_id in("+strStatus4+")";
					String strResult4 = mu.deleteData(ds, strDelQuery4);
					//////////////System.out.println("Subcategory 5 Result "+strResult4);
					if(statusVec5.size()>0)
					{
						String strStatus5 = "";
						for(int i=0; i<statusVec5.size(); i++)
						{
							strStatus5 = strStatus5.equalsIgnoreCase("") ? statusVec5.elementAt(i).toString().trim() : strStatus5+","+statusVec5.elementAt(i).toString().trim();
						}
						String strQuery6 = "Select subcat7_id from subcategory7 where subcat6_id in("+strStatus5+")";
						Vector statusVec6 = mu.getIds(ds, strQuery6);
//						to delete related values
						String strDelQuery5 = "delete from subcategory6 where subcat6_id in("+strStatus5+")";
						String strResult5 = mu.deleteData(ds, strDelQuery5);
						//////////////System.out.println("Subcategory 6 Result "+strResult5);
						if(statusVec6.size()>0)
						{
							String strStatus6 = "";
							for(int i=0; i<statusVec6.size(); i++)
							{
								strStatus6 = strStatus6.equalsIgnoreCase("") ? statusVec6.elementAt(i).toString().trim() : strStatus6+","+statusVec6.elementAt(i).toString().trim();
							}
							String strQuery7 = "Select subcat8_id from subcategory8 where subcat7_id in("+strStatus6+")";
							Vector statusVec7 = mu.getIds(ds, strQuery7);
//							to delete related values
							String strDelQuery6 = "delete from subcategory7 where subcat7_id in("+strStatus6+")";
							String strResult6 = mu.deleteData(ds, strDelQuery6);
							//////////////System.out.println("Subcategory 7 Result "+strResult6);
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
								//////////////System.out.println("Subcategory 8 Result "+strResult7);
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
//				to delete related values
				String strDelQuery4 = "delete from subcategory5 where subcat5_id in("+strStatus4+")";
				String strResult4 = mu.deleteData(ds, strDelQuery4);
				//////////////System.out.println("Subcategory 5 Result "+strResult4);
				if(statusVec5.size()>0)
				{
					String strStatus5 = "";
					for(int i=0; i<statusVec5.size(); i++)
					{
						strStatus5 = strStatus5.equalsIgnoreCase("") ? statusVec5.elementAt(i).toString().trim() : strStatus5+","+statusVec5.elementAt(i).toString().trim();
					}
					String strQuery6 = "Select subcat7_id from subcategory7 where subcat6_id in("+strStatus5+")";
					Vector statusVec6 = mu.getIds(ds, strQuery6);
//					to delete related values
					String strDelQuery5 = "delete from subcategory6 where subcat6_id in("+strStatus5+")";
					String strResult5 = mu.deleteData(ds, strDelQuery5);
					//////////////System.out.println("Subcategory 6 Result "+strResult5);
					if(statusVec6.size()>0)
					{
						String strStatus6 = "";
						for(int i=0; i<statusVec6.size(); i++)
						{
							strStatus6 = strStatus6.equalsIgnoreCase("") ? statusVec6.elementAt(i).toString().trim() : strStatus6+","+statusVec6.elementAt(i).toString().trim();
						}
						String strQuery7 = "Select subcat8_id from subcategory8 where subcat7_id in("+strStatus6+")";
						Vector statusVec7 = mu.getIds(ds, strQuery7);
//						to delete related values
						String strDelQuery6 = "delete from subcategory7 where subcat7_id in("+strStatus6+")";
						String strResult6 = mu.deleteData(ds, strDelQuery6);
						//////////////System.out.println("Subcategory 7 Result "+strResult6);
						if(statusVec7.size()>0)
						{
							String strStatus7 = "";
							for(int i=0; i<statusVec7.size(); i++)
							{
								strStatus7 = strStatus7.equalsIgnoreCase("") ? statusVec7.elementAt(i).toString().trim() : strStatus7+","+statusVec7.elementAt(i).toString().trim();
							}
							String strQuery8 = "Select subcat9_id from subcategory9 where subcat8_id in("+strStatus7+")";
							Vector statusVec8 = mu.getIds(ds, strQuery8);
//							to delete related values
							String strDelQuery7 = "delete from subcategory8 where subcat8_id in("+strStatus7+")";
							String strResult7 = mu.deleteData(ds, strDelQuery7);
							//////////////System.out.println("Subcategory 8 Result "+strResult7);
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
//				to delete related values
				String strDelQuery5 = "delete from subcategory6 where subcat6_id in("+strStatus5+")";
				String strResult5 = mu.deleteData(ds, strDelQuery5);
				//////////////System.out.println("Subcategory 6 Result "+strResult5);
				if(statusVec6.size()>0)
				{
					String strStatus6 = "";
					for(int i=0; i<statusVec6.size(); i++)
					{
						strStatus6 = strStatus6.equalsIgnoreCase("") ? statusVec6.elementAt(i).toString().trim() : strStatus6+","+statusVec6.elementAt(i).toString().trim();
					}
					String strQuery7 = "Select subcat8_id from subcategory8 where subcat7_id in("+strStatus6+")";
					Vector statusVec7 = mu.getIds(ds, strQuery7);
//					to delete related values
					String strDelQuery6 = "delete from subcategory7 where subcat7_id in("+strStatus6+")";
					String strResult6 = mu.deleteData(ds, strDelQuery6);
					//////////////System.out.println("Subcategory 7 Result "+strResult6);
					if(statusVec7.size()>0)
					{
						String strStatus7 = "";
						for(int i=0; i<statusVec7.size(); i++)
						{
							strStatus7 = strStatus7.equalsIgnoreCase("") ? statusVec7.elementAt(i).toString().trim() : strStatus7+","+statusVec7.elementAt(i).toString().trim();
						}
						String strQuery8 = "Select subcat9_id from subcategory9 where subcat8_id in("+strStatus7+")";
						Vector statusVec8 = mu.getIds(ds, strQuery8);
//						to delete related values
						String strDelQuery7 = "delete from subcategory8 where subcat8_id in("+strStatus7+")";
						String strResult7 = mu.deleteData(ds, strDelQuery7);
						//////////////System.out.println("Subcategory 8 Result "+strResult7);
						if(statusVec8.size()>0)
						{
							String strStatus8 = "";
							for(int i=0; i<statusVec8.size(); i++)
							{
								strStatus8 = strStatus8.equalsIgnoreCase("") ? statusVec8.elementAt(i).toString().trim() : strStatus8+","+statusVec8.elementAt(i).toString().trim();
							}
//							to delete related values
							String strDelQuery8 = "delete from subcategory9 where subcat9_id in("+strStatus8+")";
							String strResult8 = mu.deleteData(ds, strDelQuery8);
							//////////////System.out.println("Subcategory 8 Result "+strResult8);
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
//				to delete related values
				String strDelQuery6 = "delete from subcategory7 where subcat7_id in("+strStatus6+")";
				String strResult6 = mu.deleteData(ds, strDelQuery6);
				//////////////System.out.println("Subcategory 7 Result "+strResult6);
				if(statusVec7.size()>0)
				{
					String strStatus7 = "";
					for(int i=0; i<statusVec7.size(); i++)
					{
						strStatus7 = strStatus7.equalsIgnoreCase("") ? statusVec7.elementAt(i).toString().trim() : strStatus7+","+statusVec7.elementAt(i).toString().trim();
					}
					String strQuery8 = "Select subcat9_id from subcategory9 where subcat8_id in("+strStatus7+")";
					Vector statusVec8 = mu.getIds(ds, strQuery8);
//					to delete related values
					String strDelQuery7 = "delete from subcategory8 where subcat8_id in("+strStatus7+")";
					String strResult7 = mu.deleteData(ds, strDelQuery7);
					//////////////System.out.println("Subcategory 8 Result "+strResult7);
					if(statusVec8.size()>0)
					{
						String strStatus8 = "";
						for(int i=0; i<statusVec8.size(); i++)
						{
							strStatus8 = strStatus8.equalsIgnoreCase("") ? statusVec8.elementAt(i).toString().trim() : strStatus8+","+statusVec8.elementAt(i).toString().trim();
						}
//						to delete related values
						String strDelQuery8 = "delete from subcategory9 where subcat9_id in("+strStatus8+")";
						String strResult8 = mu.deleteData(ds, strDelQuery8);
						//////////////System.out.println("Subcategory 8 Result "+strResult8);
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
//				to delete related values
				String strDelQuery7 = "delete from subcategory8 where subcat8_id in("+strStatus7+")";
				String strResult7 = mu.deleteData(ds, strDelQuery7);
				//////////////System.out.println("Subcategory 8 Result "+strResult7);
				if(statusVec8.size()>0)
				{
					String strStatus8 = "";
					for(int i=0; i<statusVec8.size(); i++)
					{
						strStatus8 = strStatus8.equalsIgnoreCase("") ? statusVec8.elementAt(i).toString().trim() : strStatus8+","+statusVec8.elementAt(i).toString().trim();
					}
//					to delete related values
					String strDelQuery8 = "delete from subcategory9 where subcat9_id in("+strStatus8+")";
					String strResult8 = mu.deleteData(ds, strDelQuery8);
					////////////////System.out.println("Subcategory 8 Result "+strResult8);
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
//				to delete related values
				String strDelQuery8 = "delete from subcategory9 where subcat9_id in("+strStatus8+")";
				String strResult8 = mu.deleteData(ds, strDelQuery8);
				////////////////System.out.println("Subcategory 8 Result "+strResult8);
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
//		Sub Category1/ Field2
		if(strPageId.equalsIgnoreCase("click2"))
		{
			strQuery += "subcategory1 where companyid="+numCompId+" and subcat1_id="+numCatid;
			
		}
//		Sub Category2/ Field3
		if(strPageId.equalsIgnoreCase("click3"))
		{
			strQuery += "subcategory2 where companyid="+numCompId+" and subcat2_id="+numCatid;
			
		}
//		Sub Category3/ Field4
		if(strPageId.equalsIgnoreCase("click4"))
		{
			strQuery += "subcategory3 where companyid="+numCompId+" and subcat3_id="+numCatid;
			
		}
//		Sub Category4/ Field5
		if(strPageId.equalsIgnoreCase("click5"))
		{
			strQuery += "subcategory4 where companyid="+numCompId+" and subcat4_id="+numCatid;
			
		}
//		Sub Category5/ Field6
		if(strPageId.equalsIgnoreCase("click6"))
		{
			strQuery += "subcategory5 where companyid="+numCompId+" and subcat5_id="+numCatid;
			
		}
//		Sub Category6/ Field7
		if(strPageId.equalsIgnoreCase("click7"))
		{
			strQuery += "subcategory6 where companyid="+numCompId+" and subcat6_id="+numCatid;
			
		}
//		Sub Category7/ Field8
		if(strPageId.equalsIgnoreCase("click8"))
		{
			strQuery += "subcategory7 where companyid="+numCompId+" and subcat7_id="+numCatid;
			
		}
//		Sub Category8/ Field9
		if(strPageId.equalsIgnoreCase("click9"))
		{
			strQuery += "subcategory8 where companyid="+numCompId+" and subcat8_id="+numCatid;
			
		}
//		Sub Category9/ Field10
		if(strPageId.equalsIgnoreCase("click10"))
		{
			strQuery += "subcategory9 where companyid="+numCompId+" and subcat9_id="+numCatid;
		}
		
		MasterUtility mu = new MasterUtility();
		strResult = mu.deleteData(ds, strQuery);
									
		return strResult;
	}
	
}
	


