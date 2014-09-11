
package com.mm.core.master;

import java.util.*;

import javax.sql.DataSource;

import com.mm.core.resource.*;
/**
 * @author dalchand
 * Date 10/5/2007
 */
/**
 * This class is use for performing all function of advt.
 * it creats a MasterUtility class object for performing basic operation
 * upon the Data Base.
 */

public class IndvMaster {
	
	
	MasterUtility masterUtil = new MasterUtility();
	
	
	
	
	/**
	 * **********************************************************************************************************
	*This function add all the data of Interview in Interview table .
	*and return the result string Success or failure accordiong to query result.
*	************************************************************************************************************
*	*/
	public String insertInterview(DataSource ds,Vector paramVec){
		
		
		Support support=new Support();
		String strQuery="";
		String strResult = "";
		strQuery = "Insert into interview (uid, designation, name, short_inter,p_url, "+
		"detail_inter,publish_flag,creationdate) Values( ?, ?, ?, ?, ?, ?, ?, ? )";
		////System.out.println("strQuerystrparamVec>>>>>paramVec>>>>>"+paramVec);	
		support.setDataVec("string", paramVec.elementAt(0).toString().trim()); //companyid
		support.setDataVec("string", paramVec.elementAt(1).toString().trim()); //tab1
		support.setDataVec("string", paramVec.elementAt(2).toString().trim()); //tab1
		support.setDataVec("string", paramVec.elementAt(3).toString().trim()); //tab1
		support.setDataVec("string", paramVec.elementAt(4).toString().trim()); //tab1
		support.setDataVec("string", paramVec.elementAt(5).toString().trim()); //tab1
		support.setDataVec("string", paramVec.elementAt(6).toString().trim()); //text3
		support.setDataVec("string", paramVec.elementAt(7).toString().trim()); //text3
		////System.out.println("strQuerystrQuerystrQuery>>>>>>>>>>"+strQuery);	
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
		
	}
	
	/**
	* This function is use for getting list of  interview by a interview table) .
	* 
	*/
	public Vector getinterview(DataSource ds,Vector dataVec){
				
				Vector resultVec = new Vector();
				Support support=new Support();
				int min = Integer.parseInt(dataVec.elementAt(0).toString().trim());
				int max = Integer.parseInt(dataVec.elementAt(1).toString().trim());
				
				String strQuery="";
				strQuery = "select * from interview where publish_flag=1 order by creationdate DESC  limit "+ min + ", " + max;				
				
				////System.out.println("strQuery>>>>>>>>>>>>>>"+strQuery);
				/**
				 * set all the fileds type and name for blogs table by using
				 * Support class object.
				 */
				support.setFieldVec("String", "designation");		//0
				support.setFieldVec("string", "name");		//1
				support.setFieldVec("string", "short_inter");	//2								
				support.setFieldVec("string", "p_url");		//3
				support.setFieldVec("string", "detail_inter");		//4
				support.setFieldVec("int", "publish_flag");				//5
				support.setFieldVec("string", "creationdate");				//6
				support.setFieldVec("int", "id");				//6
				
				////////System.out.println("strQuery>>>>>>>>>>>>>>"+strQuery);
				resultVec = masterUtil.getList(ds, strQuery, support);
				return resultVec;
			}
	
	
	
	
	
	/**
	* This function is use for getting total Interview count .
	* It returns a list within limit which is given by min and max variable.
	*/

	public int getICount(DataSource ds){
		int result = 0;
		
		
		String strQuery="";
		
		
		strQuery= "Select count(*) count from interview where publish_flag=1"  ;	
		
		
		result = masterUtil.getCount(ds, strQuery);
		return result;
	}		
	
	
	/**
	 * This function is use for getting all complaint detail 
	 * of a particuler complaint from complaints table. strQuery = "SELECT count(*) count FROM complaints where publish_flag =1";
	 */
	
	public Vector getComplaintDetails(DataSource ds,Vector dataVec){
		
		Vector resultVec = new Vector();
		int complaint_id = Integer.parseInt(dataVec.elementAt(0).toString().trim());
		int login_id= Integer.parseInt(dataVec.elementAt(1).toString().trim());	
		Support support=new Support();
		   String strQuery="";
		   
			strQuery = "SELECT * FROM complaints where complaint_id= "+complaint_id+" and login_id= "+login_id;				
		
		
		
		 /**
		 * set all the fileds type and name for complaints table by using
		 * Support class object.
		 */
			support.setFieldVec("int", "complaint_id");			//0
			support.setFieldVec("string", "complaint_title");	//1
			support.setFieldVec("string", "complaint_text");	//2
			support.setFieldVec("string", "relevent_info");		//3		
			support.setFieldVec("string", "category");			//4
			support.setFieldVec("string", "creation_date");		//5
			support.setFieldVec("string", "publish_date");		//6
			support.setFieldVec("string", "creation_time");		//7
			support.setFieldVec("string", "publish_time");		//8
			support.setFieldVec("string", "lastmodify_date");	//9
			support.setFieldVec("string", "lastmodify_time");	//10
			support.setFieldVec("int", "login_id");				//11
			support.setFieldVec("int", "companyid");			//12
			support.setFieldVec("string", "timepart");			//13
			support.setFieldVec("int", "viewcount");			//14
			support.setFieldVec("int", "entp_id");				//15
			support.setFieldVec("int", "cu_id");				//16		
			support.setFieldVec("String", "fcom_id");			//17
			support.setFieldVec("int", "advt_id");				//18
			support.setFieldVec("int", "advtL_id");				//19
			support.setFieldVec("int", "dealer_id");			//20
			support.setFieldVec("int", "tag_id");				//21
			support.setFieldVec("String", "resolve_date");		//22
			support.setFieldVec("String", "closed_date");		//23
			support.setFieldVec("int", "brandFlag");			//24
			support.setFieldVec("int", "publish_flag");			//25
			support.setFieldVec("string", "publish_on");		//26
			support.setFieldVec("string", "other");				//27
		
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		return resultVec;
	}
	/**
	 * This function is use for getting all complaint detail 
	 * of a particuler complaint from drafts table.
	 */
	public Vector getDraftsDetails(DataSource ds,Vector dataVec){
		
		Vector resultVec = new Vector();
		int draft_id = Integer.parseInt(dataVec.elementAt(0).toString().trim());
		int login_id= Integer.parseInt(dataVec.elementAt(1).toString().trim());	
		Support support=new Support();
		   String strQuery="";	
		
			strQuery = "SELECT * FROM drafts where draft_id= "+draft_id+" and login_id= "+login_id;				
		
		
		
		/**
		 * set all the fileds type and name for blogs table by using
		 * Support class object.
		 */
		support.setFieldVec("int", "draft_id");				//0
		support.setFieldVec("string", "draft_title");		//1
		support.setFieldVec("string", "draft_text");		//2
		support.setFieldVec("string", "relevent_info");		//3		
		support.setFieldVec("int", "category");				//4
		support.setFieldVec("string", "creation_date");		//5
		support.setFieldVec("string", "creation_time");		//6
		support.setFieldVec("string", "lastmodify_date");	//7		
		support.setFieldVec("string", "lastmodify_time");	//8
		support.setFieldVec("int", "login_id");				//9
		support.setFieldVec("int", "companyid");			//10
		support.setFieldVec("string", "timepart");			//11
		support.setFieldVec("int", "dealer_id");			//12
		support.setFieldVec("string", "other");				//13
		support.setFieldVec("string", "advt_id");			//14
		support.setFieldVec("string", "q_type");			//14
		
		
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		return resultVec;
	}
	 /**
	  * ****************************************************************************************************************
    *This method takes dataVec as argument and draft infoand returns a success/failure messg. after adding dealer info.in the dealer table
*	*******************************************************************************************************************
*	*/
	public String insertDealerInfo(DataSource ds,Vector paramVec){
		
		Support support=new Support();
		String strQuery="";
		String strResult = "";	
		strQuery = "Insert into dealer(dealername, contactperson, companyid,address,country,state, district, "+
		"city,email,phonenumber,mobileno,pincode) Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		support.setDataVec("string", paramVec.elementAt(0).toString().trim());
		support.setDataVec("string", paramVec.elementAt(1).toString().trim());
		support.setDataVec("int", paramVec.elementAt(2).toString().trim());
		support.setDataVec("string", paramVec.elementAt(3).toString().trim());
		support.setDataVec("string", paramVec.elementAt(4).toString().trim());
		support.setDataVec("string", paramVec.elementAt(5).toString().trim());
		support.setDataVec("string", paramVec.elementAt(6).toString().trim());
		support.setDataVec("string", paramVec.elementAt(7).toString().trim());
		support.setDataVec("string", paramVec.elementAt(8).toString().trim());
		support.setDataVec("string", paramVec.elementAt(9).toString().trim());
		support.setDataVec("string", paramVec.elementAt(10).toString().trim());
		support.setDataVec("int", paramVec.elementAt(11).toString().trim());
			
		strResult = masterUtil.setData(ds, strQuery, support);
	
		return strResult;	
				
	}
	
	
	/*****************************************************************************************************************
	 This function Get the Course_name by using the cat_id from the category Table.
*****************************************************************************************************************/
	public Vector getCourseName(DataSource ds,int cou_id)
	{
		Vector resultVec = new Vector();
		Support support=new Support();
	    String strQuery="";
		strQuery= "select cou_name,cou_enabled from courses where cou_id="+cou_id;
			
		support.setFieldVec("string", "cou_name");	
		support.setFieldVec("int", "cou_enabled");	
		
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		
		return resultVec;
		
	}
	
	/*****************************************************************************************************************
	 This function Get the Course_name by using the cat_id from the category Table.
*****************************************************************************************************************/
	public Vector getIcategory(DataSource ds,int cou_id)
	{
		Vector resultVec = new Vector();
		Support support=new Support();
	    String strQuery="";
		strQuery= "select cat_name,cat_enabled from category where cat_id="+cou_id;
			
		support.setFieldVec("string", "cat_name");	
		support.setFieldVec("int", "cat_enabled");	
		//////////////System.out.println("strQuery)>>>>>>>>>>>"+strQuery);
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		
		//////////////System.out.println("resultVec>>>>>>>>>>>"+resultVec);
		
		return resultVec;
		
	}
	
	/*****************************************************************************************************************
	 This function Get the categoryName( by using the cat_id from the category Table.
*****************************************************************************************************************/
	public String getIcategoryName(DataSource ds,int cou_id)
	{
		String strResult ="";
		Support support=new Support();
	    String strQuery="";
		strQuery= "select cat_name from category where cat_id="+cou_id;
			
		support.setFieldVec("string", "cat_name");	
		
		
		 strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
		
	}
	
	
	/*****************************************************************************************************************
	 This function Get the Stream_name by using the cat_id from the category Table.
*****************************************************************************************************************/
	public Vector getStreamName(DataSource ds,int coustr_id)
	{
		Vector resultVec = new Vector();
		Support support=new Support();
	    String strQuery="";
		strQuery= "select coustr_name,coustr_enabled from course_stream where coustr_id="+coustr_id;
			
		support.setFieldVec("string", "coustr_name");	
		support.setFieldVec("int", "coustr_enabled");	
		
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		
		return resultVec;
		
	}
	/**
	 * This function is use for getting detail 
	 * of a particuler delar  from `dealer` table.
	 */
	
	public Vector getDealerDetail(DataSource ds,int  dealerId){
		
		Vector resultVec = new Vector();
		Support support=new Support();
		String strQuery="";			
		
			strQuery = "SELECT  * FROM dealer where dealerid="+dealerId;		
		
		
		
		/**
		 * set all the fileds type and name for brands table by using
		 * Support class object.
		 */
			support.setFieldVec("int", "dealerid");			//0
			support.setFieldVec("string", "dealername");	//1
			support.setFieldVec("string", "contactperson");	//2
			support.setFieldVec("int", "companyid");		//3	
			support.setFieldVec("string", "address");		//4
			support.setFieldVec("string", "country");		//5
			support.setFieldVec("string", "state");			//6
			support.setFieldVec("string", "district");		//7
			support.setFieldVec("string", "city");			//8
			support.setFieldVec("string", "email");			//9
			support.setFieldVec("string", "phonenumber");	//10
			support.setFieldVec("string", "mobileno");		//11
			support.setFieldVec("int", "pincode");			//12					
		
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		return resultVec;
		}
	
	public String getMaxDealerId(DataSource ds, String dealerName)
	{
		String result = "0";
		Support support=new Support();
		String strQuery="SELECT  max(dealerid)dealerid FROM dealer where dealername='"+dealerName+"'";	
		support.setFieldVec("int", "dealerid");
		String strResult = masterUtil.getValue(ds, strQuery, support);
		if(!strResult.equalsIgnoreCase("failure"))
		{
			result =strResult;
		}
		return 	result;
	}
	
	
	/************************************************************************************************************
	    *This function Update the dealer informatin in the dealer table.     
	*	************************************************************************************************************
	*	*/
	public String getStaffID(DataSource ds, String catID, String facilityID, String cdate, String ctime)
	{
		String result = "0";
		Support support=new Support();
		String strQuery="SELECT  * from staff_schedule where facilityId="+facilityID+" and category_id="+catID+" and schedule_date = '"+cdate+"' LIMIT 1";	
		support.setFieldVec("int", "staff_id");
		System.out.print("Str Query in getStaffID is "+strQuery);
		String strResult = masterUtil.getValue(ds, strQuery, support);
		if(!strResult.equalsIgnoreCase("failure"))
		{
			result =strResult;
		}
		return 	result;
	}	
	/************************************************************************************************************
    *This function Update the dealer informatin in the dealer table.     
*	************************************************************************************************************
*	*/
	public String updateDealerInfo(DataSource ds,Vector paramVec){
		
		Support support=new Support();
		String strQuery="";
		String strResult = "";	
		strQuery = "Update dealer set dealername=?,contactperson=?, companyid=?,address=?, country=?, state=?,"+
		"district=?, city=?, email=?, phonenumber=?, mobileno=?, pincode=? where dealerid=?";
		
		support.setDataVec("string", paramVec.elementAt(0).toString().trim());
		support.setDataVec("string", paramVec.elementAt(1).toString().trim());
		support.setDataVec("int", paramVec.elementAt(2).toString().trim());
		support.setDataVec("string", paramVec.elementAt(3).toString().trim());
		support.setDataVec("string", paramVec.elementAt(4).toString().trim());
		support.setDataVec("string", paramVec.elementAt(5).toString().trim());
		support.setDataVec("string", paramVec.elementAt(6).toString().trim());
		support.setDataVec("string", paramVec.elementAt(7).toString().trim());
		support.setDataVec("string", paramVec.elementAt(8).toString().trim());
		support.setDataVec("string", paramVec.elementAt(9).toString().trim());
		support.setDataVec("string", paramVec.elementAt(10).toString().trim());
		support.setDataVec("int", paramVec.elementAt(11).toString().trim());
		support.setDataVec("int", paramVec.elementAt(12).toString().trim());
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;	
				
	}	
				
	/**
	* This function is use for getting list of  complaint's by a particuler user(consumer) .
	* It returns a list within limit which is given by min and max variable.
	*/
	public Vector getComplaintList(DataSource ds,Vector dataVec){
				
				Vector resultVec = new Vector();
				int vectorSize = dataVec.size();
				int loginid = Integer.parseInt(dataVec.elementAt(0).toString().trim());
				int companyid = Integer.parseInt(dataVec.elementAt(1).toString().trim());
				int min = Integer.parseInt(dataVec.elementAt(2).toString().trim());
				int max = Integer.parseInt(dataVec.elementAt(3).toString().trim());
				String fieldVal = dataVec.elementAt(4).toString().trim();
				Support support=new Support();
				String strQuery="";
				/*
				 * in case of search complaint by some given parameter's.
				 */
				
				if(vectorSize==7)												
					strQuery = "SELECT * FROM complaints where login_id="+loginid+" and "+dataVec.elementAt(5).toString().trim()+" ='"+dataVec.elementAt(6).toString().trim()+"' order by "+fieldVal+" limit "+min+", "+max;
				
				else if(vectorSize==9)											
					strQuery = "SELECT * FROM complaints where login_id="+loginid+" and "+dataVec.elementAt(5).toString().trim()+" ='"+dataVec.elementAt(6).toString().trim()+"'and "+ dataVec.elementAt(7).toString().trim()+" ='"+dataVec.elementAt(8).toString().trim()+"' order by "+fieldVal+" limit "+min+", "+max;
				
				else if(vectorSize==11)											
					strQuery = "SELECT * FROM complaints where login_id="+loginid+" and "+dataVec.elementAt(5).toString().trim()+" ='"+dataVec.elementAt(6).toString().trim()+"'and "+ dataVec.elementAt(7).toString().trim()+" ='"+dataVec.elementAt(8).toString().trim()+"'and "+ dataVec.elementAt(9).toString().trim()+" ='"+dataVec.elementAt(10).toString().trim()+"' order by "+fieldVal+" limit "+min+", "+max;
				
				else if(vectorSize==13)											
					strQuery = "SELECT * FROM complaints where login_id="+loginid+" and "+dataVec.elementAt(5).toString().trim()+" ='"+dataVec.elementAt(6).toString().trim()+"'and "+ dataVec.elementAt(7).toString().trim()+" ='"+dataVec.elementAt(8).toString().trim()+"'and "+ dataVec.elementAt(9).toString().trim()+" ='"+dataVec.elementAt(10).toString().trim()+"'and "+ dataVec.elementAt(11).toString().trim()+" ='"+dataVec.elementAt(13).toString().trim()+"' order by "+fieldVal+" limit "+min+", "+max;
				/*
				 * getting list of  complaint's by a particuler user(consumer)
				 * It returns a list within limit which is given by min and max variable.
				 */
				else				
				strQuery= "Select * from complaints where login_id= "+loginid+" and companyid="+companyid+" order by "+fieldVal+" limit "+min+", "+max;
				
				////////////////////System.out.println("strQuery>>>>>>>>>>>>>>"+strQuery);
				/**
				 * set all the fileds type and name for blogs table by using
				 * Support class object.
				 */
				support.setFieldVec("int", "complaint_id");
				support.setFieldVec("string", "fcom_id");
				support.setFieldVec("string", "complaint_title");								
				support.setFieldVec("string", "creation_date");	
				support.setFieldVec("string", "creation_time");
				
				
				resultVec = masterUtil.getList(ds, strQuery, support);
				return resultVec;
			}
	
	
	/**
	* This function is use for getting list of  complaint's by a particuler user(consumer) .
	* It returns a list within limit which is given by min and max variable.
	*/
	public Vector getComplaintList1(DataSource ds,Vector dataVec){
				
				Vector resultVec = new Vector();
				int vectorSize = dataVec.size();
				int loginid = Integer.parseInt(dataVec.elementAt(0).toString().trim());
				int companyid = Integer.parseInt(dataVec.elementAt(1).toString().trim());
				int min = Integer.parseInt(dataVec.elementAt(2).toString().trim());
				int max = Integer.parseInt(dataVec.elementAt(3).toString().trim());
				String fieldVal = dataVec.elementAt(4).toString().trim();
				String qtype = dataVec.elementAt(5).toString().trim();
				Support support=new Support();
				String strQuery="";
				/*
				 * in case of search complaint by some given parameter's.
				 */
				
								
				strQuery= "SELECT * from complaints com, loginmaster log, places pl, tages t, category c where com.login_id= "+loginid+" and com.companyid="+companyid+"  and  q_type='"+qtype+"' and com.advtL_id=log.userid and log.city = pl.placeId and com.tag_id=t.tagid and com.category= c.cat_id order by "+fieldVal+" limit "+min+", "+max;
				
				////////////////////System.out.println("strQuery>>>>>>>>>>>>>>"+strQuery);
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
				support.setFieldVec("string", "com.q_type");
				support.setFieldVec("string", "com.resolve_date");		//10
				support.setFieldVec("string", "t.tagname");		//11
				support.setFieldVec("string", "c.cat_name");		//11
				////////System.out.println("strQuery>>>>>>>>>>>>>>"+strQuery);
				resultVec = masterUtil.getList(ds, strQuery, support);
				return resultVec;
			}
	
	/**
	* This function is use for getting list of  complaint's by a particuler user(consumer) for a particular month/year.
	* It returns a list within limit which is given by min and max variable.
	*/
	public Vector getComplaintListAll(DataSource ds,Vector dataVec){
				
				Vector resultVec = new Vector();
				int vectorSize = dataVec.size();
				int loginid = Integer.parseInt(dataVec.elementAt(0).toString().trim());
				int companyid = Integer.parseInt(dataVec.elementAt(1).toString().trim());
				int min = Integer.parseInt(dataVec.elementAt(2).toString().trim());
				int max = Integer.parseInt(dataVec.elementAt(3).toString().trim());
				String fieldVal = dataVec.elementAt(4).toString().trim();
				String qtype = dataVec.elementAt(5).toString().trim();
				int Cmonth = Integer.parseInt(dataVec.elementAt(6).toString().trim());
				int Cyear = Integer.parseInt(dataVec.elementAt(7).toString().trim());
				Support support=new Support();
				String strQuery="";
				/*
				 * in case of search complaint by some given parameter's.
				 */
				
				if ((Cmonth==0) &&(Cyear ==0)){				
				strQuery= "SELECT * from complaints com, loginmaster log, places pl, tages t, category c where com.login_id= "+loginid+" and com.companyid="+companyid+"  and  q_type='"+qtype+"' and com.advtL_id=log.userid and log.city = pl.placeId and com.tag_id=t.tagid and com.category= c.cat_id order by "+fieldVal+" limit "+min+", "+max;
				}
				else{
				strQuery= "SELECT * from complaints com, loginmaster log, places pl, tages t, category c where com.login_id= "+loginid+" and com.companyid="+companyid+"  and  q_type='"+qtype+"' and com.advtL_id=log.userid and log.city = pl.placeId and com.tag_id=t.tagid and com.category= c.cat_id and SUBSTRING(creation_date,6,2)="+Cmonth+" and SUBSTRING(creation_date,1,4)="+Cyear+" order by "+fieldVal+" limit "+min+", "+max;
				}
				System.out.println("strQuery>>>>>>>>>>>>>>"+strQuery);
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
				support.setFieldVec("string", "com.q_type");	//9
				support.setFieldVec("string", "com.resolve_date");		//10
				support.setFieldVec("string", "t.tagname");		//11
				support.setFieldVec("string", "c.cat_name");		//12
				support.setFieldVec("int", "t.tagid");				//13
				support.setFieldVec("int", "com.dealer_id");				//14
				/////System.out.println("strQuery 1111"+strQuery);
				resultVec = masterUtil.getList(ds, strQuery, support);
				return resultVec;
			}
	
	public Vector getComplaintList2(DataSource ds,Vector dataVec){
		
		Vector resultVec = new Vector();
		int vectorSize = dataVec.size();
		int loginid = Integer.parseInt(dataVec.elementAt(0).toString().trim());
		int companyid = Integer.parseInt(dataVec.elementAt(1).toString().trim());
		int min = Integer.parseInt(dataVec.elementAt(2).toString().trim());
		int max = Integer.parseInt(dataVec.elementAt(3).toString().trim());
		String fieldVal = dataVec.elementAt(4).toString().trim();
		String qtype = dataVec.elementAt(5).toString().trim();
		int Cmonth = Integer.parseInt(dataVec.elementAt(6).toString().trim());
		int Cyear = Integer.parseInt(dataVec.elementAt(7).toString().trim());
	
	
		Support support=new Support();
		String strQuery="";
		/*
		 * in case of search complaint by some given parameter's.
		 */
		
						
		strQuery= "SELECT * from complaints com, loginmaster log, places pl where com.login_id= "+loginid+" and com.companyid="+companyid+"  and  q_type='"+qtype+"' and com.login_id=log.userid and log.city = pl.placeId and SUBSTRING(com.creation_date,6,2)="+Cmonth+" and SUBSTRING(com.creation_date,1,4)="+Cyear+" order by "+fieldVal+" limit "+min+", "+max;
		
	System.out.println("strQuery>>>>>>>>>>>>>>"+strQuery);
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
		support.setFieldVec("string", "com.q_type");
		
		////////System.out.println("strQuery>>>>>>>>>>>>>>"+strQuery);
		resultVec = masterUtil.getList(ds, strQuery, support);
		return resultVec;
	}

	/**
	* This function is use for getting total Query count .
	* It returns a list within limit which is given by min and max variable.
	*/

	public int getMTCount(DataSource ds,Vector dataVec){
		int result = 0;
		
		int loginid = Integer.parseInt(dataVec.elementAt(0).toString().trim());
		
	
		
		String strQuery="";
		
		
		strQuery= "Select count(*) count from complaints where login_id= "+loginid+"  and q_type='Mrequest'and and q_type='Trequest'";	
		
		
		result = masterUtil.getCount(ds, strQuery);
		return result;
	}				
	
	/**
	* This function is use for getting list of Mrequest&Trequest .
	* It returns a list within limit which is given by min and max variable.
	*/
	public Vector getMTList(DataSource ds,Vector dataVec){
				
				Vector resultVec = new Vector();
				int vectorSize = dataVec.size();
				int loginid = Integer.parseInt(dataVec.elementAt(0).toString().trim());
				int companyid = Integer.parseInt(dataVec.elementAt(1).toString().trim());
				int min = Integer.parseInt(dataVec.elementAt(2).toString().trim());
				int max = Integer.parseInt(dataVec.elementAt(3).toString().trim());
				String fieldVal = dataVec.elementAt(4).toString().trim();
				
				Support support=new Support();
				String strQuery="";
				/*
				 * in case of search complaint by some given parameter's.
				 */
				
								
				strQuery= "Select * from complaints where login_id= "+loginid+" and companyid="+companyid+" and ( q_type='Mrequest'or q_type='Trequest') order by "+fieldVal+" limit "+min+", "+max;
				
				////////////////////System.out.println("strQuery>>>>>>>>>>>>>>"+strQuery);
				/**
				 * set all the fileds type and name for blogs table by using
				 * Support class object.
				 */
				support.setFieldVec("int", "complaint_id");
				support.setFieldVec("string", "fcom_id");
				support.setFieldVec("string", "complaint_title");								
				support.setFieldVec("string", "creation_date");	
				support.setFieldVec("string", "creation_time");
				
				
				resultVec = masterUtil.getList(ds, strQuery, support);
				return resultVec;
			}
	/**
	* This function is use for getting list of drafts complaint's by a particuler user (consumer).
	* It returns a list within limit which is given by min and max variable.
	*/
			public Vector getDraftsList(DataSource ds,Vector paramVec){
					Vector resultVec = new Vector();
					int loginid = Integer.parseInt(paramVec.elementAt(0).toString().trim());
					int companyid = Integer.parseInt(paramVec.elementAt(1).toString().trim());
					int min = Integer.parseInt(paramVec.elementAt(2).toString().trim());
					int max = Integer.parseInt(paramVec.elementAt(3).toString().trim());
					String fieldVal = paramVec.elementAt(4).toString().trim();
					Support support=new Support();
					String strQuery="";
					
					strQuery= "Select * from drafts where login_id= "+loginid+" and companyid="+companyid+"  order by "+fieldVal+" limit "+min+", "+max;
					
				
					/**
					 * set all the fileds type and name for blogs table by using
					 * Support class object.
					 */
					support.setFieldVec("int", "draft_id");
					support.setFieldVec("string", "draft_title");
					support.setFieldVec("string", "draft_text");								
					support.setFieldVec("string", "creation_date");
					//support.setFieldVec("string", "creation_time");			
					// not used in draft support.setFieldVec("int", "fcom_id");
					
					resultVec = masterUtil.getList(ds, strQuery, support);
					return resultVec;
				}
	
	
			
			/**
			* This function is use for getting list of drafts complaint's by a particuler user (consumer).
			* It returns a list within limit which is given by min and max variable.
			*/
					public Vector getDraftsList1(DataSource ds,Vector paramVec){
							Vector resultVec = new Vector();
							int loginid = Integer.parseInt(paramVec.elementAt(0).toString().trim());
							int companyid = Integer.parseInt(paramVec.elementAt(1).toString().trim());
							int min = Integer.parseInt(paramVec.elementAt(2).toString().trim());
							int max = Integer.parseInt(paramVec.elementAt(3).toString().trim());
							String fieldVal = paramVec.elementAt(4).toString().trim();
							String qtype = paramVec.elementAt(5).toString().trim();
							Support support=new Support();
							String strQuery="";
							////////////////System.out.println("qtypeqtypeqtypeqtype"+qtype);
							strQuery= "Select * from drafts where login_id= "+loginid+" and companyid="+companyid+"  and q_type= '"+qtype+"' order by "+fieldVal+" limit "+min+", "+max;
							
						
							/**
							 * set all the fileds type and name for blogs table by using
							 * Support class object.
							 */
							support.setFieldVec("int", "draft_id");
							support.setFieldVec("string", "draft_title");
							support.setFieldVec("string", "draft_text");								
							support.setFieldVec("string", "creation_date");
							//support.setFieldVec("string", "creation_time");			
							// not used in draft support.setFieldVec("int", "fcom_id");
							////////////////System.out.println("strQuery........."+strQuery);
							resultVec = masterUtil.getList(ds, strQuery, support);
							return resultVec;
						}
			
			
				
	/**
	* This function use for getting total no. of draft's by a particuler user (consumer).
	 * @return
	*/	
				public int getDraftsCount(DataSource ds,Vector paramVec){
							int result = 0;
							int loginid = Integer.parseInt(paramVec.elementAt(0).toString().trim());
							int companyid = Integer.parseInt(paramVec.elementAt(1).toString().trim());
							
							String strQuery="";
							strQuery= "Select count(*) count from drafts where login_id= "+loginid+" and companyid="+companyid;	
							
							
							result = masterUtil.getCount(ds, strQuery);
							return result;
						}
				
				/**
				* This function use for getting total no. of draft's by a particuler user (consumer).
				 * @return
				*/	
							public int getDraftsCount1(DataSource ds,Vector paramVec){
										int result = 0;
										int loginid = Integer.parseInt(paramVec.elementAt(0).toString().trim());
										int companyid = Integer.parseInt(paramVec.elementAt(1).toString().trim());
										String qtype = paramVec.elementAt(2).toString().trim();
										String strQuery="";
										strQuery= "Select count(*) count from drafts where login_id= "+loginid+" and companyid="+companyid+" and q_type='"+qtype+"'";	
										
										///System.out.println("strQuery........."+strQuery);
										result = masterUtil.getCount(ds, strQuery);
										return result;
									}
	/**
	* This function use for getting total no. of blogs(complaints) by a particuler user (consumer).
	* @return
	*/	
					public int getComplaintCount(DataSource ds,Vector dataVec){
								int result = 0;
								int vectorSize = dataVec.size();
								int loginid = Integer.parseInt(dataVec.elementAt(0).toString().trim());
								int companyid = Integer.parseInt(dataVec.elementAt(1).toString().trim());
								System.out.println("Vector Size is"+vectorSize);

								String strQuery="";
								if(vectorSize==4)									
									strQuery = "SELECT count(*) count from complaints where login_id="+loginid+" and "+dataVec.elementAt(2).toString().trim()+" ='"+dataVec.elementAt(3).toString().trim()+"'"; 
									
								
								else if(vectorSize==6)								
									strQuery = "SELECT count(*) count from complaints where login_id="+loginid+" and "+dataVec.elementAt(2).toString().trim()+" ='"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" ='"+dataVec.elementAt(5).toString().trim()+"'";
									
								
								else if(vectorSize==8)								
									strQuery = "SELECT count(*) count from complaints where login_id="+loginid+" and "+dataVec.elementAt(2).toString().trim()+" ='"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" ='"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" ='"+dataVec.elementAt(7).toString().trim()+"'";
									
								
								else if(vectorSize==10)								
									strQuery = "SELECT count(*) count from complaints where login_id="+loginid+" and "+dataVec.elementAt(2).toString().trim()+" ='"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" ='"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" ='"+dataVec.elementAt(7).toString().trim()+"'and "+ dataVec.elementAt(9).toString().trim()+" ='"+dataVec.elementAt(10).toString().trim()+"'";
									
								else
								
								strQuery= "Select count(*) count from complaints where login_id= "+loginid+" and companyid="+companyid;	
								
								
								result = masterUtil.getCount(ds, strQuery);
								return result;
							}
	
					/**
					* This function use for getting total no. of complaints by a particuler user (consumer). for  a particular month/year
					* @return
					*/	
									public int ComplaintCountAll(DataSource ds,Vector dataVec){
												int result = 0;
												int vectorSize = dataVec.size();
												int loginid = Integer.parseInt(dataVec.elementAt(0).toString().trim());
												int companyid = Integer.parseInt(dataVec.elementAt(1).toString().trim());
												String Q_type = dataVec.elementAt(2).toString().trim();
												int month = Integer.parseInt(dataVec.elementAt(3).toString().trim());
												int year = Integer.parseInt(dataVec.elementAt(4).toString().trim());
												System.out.println("Vector Size is"+vectorSize);

												String strQuery="";
		if ((month==0) && (year==0)){
		strQuery= "Select count(*) count from complaints where login_id= "+loginid+" and companyid="+companyid+" and q_type='"+Q_type+"'";
		}
		else
		{
		strQuery= "Select count(*) count from complaints where login_id= "+loginid+" and companyid="+companyid+" and q_type= '"+Q_type+"' and SUBSTRING(creation_date,6,2)="+month+" and SUBSTRING(creation_date,1,4)="+year;
		}
					System.out.println("Query is "+strQuery);							
												
												result = masterUtil.getCount(ds, strQuery);
												return result;
											}
					
					
					public int getMentorReqCount(DataSource ds,Vector dataVec){
						int result = 0;
						
						int loginid = Integer.parseInt(dataVec.elementAt(0).toString().trim());
						String qtype = dataVec.elementAt(1).toString().trim();
					
						
						String strQuery="";
						
						
						strQuery= "Select count(*) count from complaints where login_id= "+loginid+" and q_type='"+qtype+"'";	
						
						
						result = masterUtil.getCount(ds, strQuery);
						return result;
					}				
					
					/**
					* This function use for getting total no. of blogs(complaints) by a particuler user (consumer).
					* @return
					*/	
	public int getComplaintCount1(DataSource ds,Vector dataVec){
				int result = 0;
				int vectorSize = dataVec.size();
				int loginid = Integer.parseInt(dataVec.elementAt(0).toString().trim());
				int companyid = Integer.parseInt(dataVec.elementAt(1).toString().trim());
				String qtype = dataVec.elementAt(2).toString().trim();
				int Cmonth = Integer.parseInt(dataVec.elementAt(3).toString().trim());
				int Cyear = Integer.parseInt(dataVec.elementAt(4).toString().trim());
			
				//System.out.println("Q type at indv master "+qtype);
				String strQuery="";
								
				strQuery= "Select count(*) count from complaints where login_id= "+loginid+" and companyid="+companyid+" and q_type='"+qtype+"' and SUBSTRING(creation_date,6,2)="+Cmonth+" and SUBSTRING(creation_date,1,4)="+Cyear;;	
				//System.out.println("Query at indv master "+strQuery);
				
				
				result = masterUtil.getCount(ds, strQuery);
				return result;
			}

	public int getNoticesCount(DataSource ds,Vector dataVec){
		int result = 0;
		int vectorSize = dataVec.size();
		int loginid = Integer.parseInt(dataVec.elementAt(0).toString().trim());
		String facilityID = dataVec.elementAt(1).toString().trim();
		int Cmonth = Integer.parseInt(dataVec.elementAt(2).toString().trim());
		int Cyear = Integer.parseInt(dataVec.elementAt(3).toString().trim());
		
		System.out.println("Facility  type at indv master "+facilityID+Cmonth+Cyear);
		
		
		String strQuery="";
		
		
		strQuery= "Select count(*) count from noticeboard where facility_id="+facilityID+" and SUBSTRING(createdon,6,2)="+Cmonth+" and SUBSTRING(createdon,1,4)="+Cyear;	
		System.out.println("Query at indv master "+strQuery);
		
		
		result = masterUtil.getCount(ds, strQuery);
		return result;
	}	
	
	/* Added by Anu */
	
	public Vector getServiceList(DataSource ds,Vector paramVec,String month, String year){
		int result = 0;
		Vector resultVec = new Vector();
		int residentid = Integer.parseInt(paramVec.elementAt(0).toString().trim());
		int facilityid = Integer.parseInt(paramVec.elementAt(1).toString().trim());
		int status = Integer.parseInt(paramVec.elementAt(2).toString().trim());
		Support support=new Support();
		String strQuery="";
		////////////////System.out.println("qtypeqtypeqtypeqtype"+qtype);
		strQuery= "Select s.serviceid, s.servicename, s.monthlyfee, s.taxes, s.cess from service s, consumerservicemapping csm where s.serviceid = csm.serviceid and csm.consumerid = "+residentid+" and csm.status = "+status+" and SUBSTRING(csm.datefrom,1,4)<="+year+" and SUBSTRING(csm.datefrom,6,2) <="+month;
		System.out.println("Query is"+strQuery);
		/**
		 * set all the fileds type and name for blogs table by using
		 * Support class object.
		 */
		support.setFieldVec("int", "serviceid");
		support.setFieldVec("string", "servicename");
		support.setFieldVec("int", "monthlyfee");
		support.setFieldVec("string", "taxes");
		support.setFieldVec("int", "cess");
	
		//support.setFieldVec("string", "creation_time");			
		// not used in draft support.setFieldVec("int", "fcom_id");
		////////////////System.out.println("strQuery........."+strQuery);
		resultVec = masterUtil.getList(ds, strQuery, support);
		System.out.println("Services done");
		return resultVec;
}

	
	public Vector getServicePendingList(DataSource ds,Vector paramVec, String month, String year){
		int result = 0;
		Vector resultVec = new Vector();
		int residentid = Integer.parseInt(paramVec.elementAt(0).toString().trim());
		int facilityid = Integer.parseInt(paramVec.elementAt(1).toString().trim());
		int status = Integer.parseInt(paramVec.elementAt(2).toString().trim());
		Support support=new Support();
		String strQuery="";
		////////////////System.out.println("qtypeqtypeqtypeqtype"+qtype);
		strQuery= "Select s.serviceid, s.servicename, s.monthlyfee, s.taxes, s.cess from service s, consumerservicemapping csm where s.serviceid = csm.serviceid and csm.consumerid = "+residentid+" and csm.status = "+status+" and SUBSTRING(csm.createdon,1,4)<="+year+" and SUBSTRING(csm.createdon,6,2) <="+month;
		System.out.println("Query is"+strQuery);
		/**
		 * set all the fileds type and name for blogs table by using
		 * Support class object.
		 */
		support.setFieldVec("int", "serviceid");
		support.setFieldVec("string", "servicename");
		support.setFieldVec("int", "monthlyfee");
		support.setFieldVec("string", "taxes");
		support.setFieldVec("int", "cess");
	
		//support.setFieldVec("string", "creation_time");			
		// not used in draft support.setFieldVec("int", "fcom_id");
		////////////////System.out.println("strQuery........."+strQuery);
		resultVec = masterUtil.getList(ds, strQuery, support);
		System.out.println("Services Pending done");
		return resultVec;
}

	public Vector viewNoticesList(DataSource ds,Vector paramVec){
		int result = 0;
		Vector resultVec = new Vector();
		int residentid = Integer.parseInt(paramVec.elementAt(0).toString().trim());
		int facilityid = Integer.parseInt(paramVec.elementAt(3).toString().trim());
		int status = Integer.parseInt(paramVec.elementAt(2).toString().trim());
		int Cmonth = Integer.parseInt(paramVec.elementAt(4).toString().trim());
		int Cyear = Integer.parseInt(paramVec.elementAt(5).toString().trim());
		
		System.out.println("Facility  type at indv master "+facilityid+Cmonth+Cyear);
		
		
		String strQuery="";
		
		Support support=new Support();
		
		////////////////System.out.println("qtypeqtypeqtypeqtype"+qtype);
		strQuery= "Select * from noticeboard where facility_id = "+facilityid+" and SUBSTRING(createdon,6,2)="+Cmonth+" and SUBSTRING(createdon,1,4)="+Cyear;
		
		System.out.println("Query is"+strQuery);
		/**
		 * set all the fileds type and name for blogs table by using
		 * Support class object.
		 */
		support.setFieldVec("int", "noticeid");
		support.setFieldVec("string", "noticetitle");		
		support.setFieldVec("string", "noticetext");
		support.setFieldVec("date", "createdon");
	
		//support.setFieldVec("string", "creation_time");			
		// not used in draft support.setFieldVec("int", "fcom_id");
		////////////////System.out.println("strQuery........."+strQuery);
		resultVec = masterUtil.getList(ds, strQuery, support);
		System.out.println("Services done");
		return resultVec;
}

/**
 * Get eBills for Residents
 * 
 */
	
	
	public Vector eBills(DataSource ds,Vector paramVec){
		int result = 0;
		Vector resultVec = new Vector();
		int residentid = Integer.parseInt(paramVec.elementAt(0).toString().trim());
		int facilityid = Integer.parseInt(paramVec.elementAt(1).toString().trim());
		int status = Integer.parseInt(paramVec.elementAt(2).toString().trim());
		int type = Integer.parseInt(paramVec.elementAt(3).toString().trim());
		int Cmonth = Integer.parseInt(paramVec.elementAt(4).toString().trim());
		int Cyear = Integer.parseInt(paramVec.elementAt(5).toString().trim());
		
		Support support=new Support();		
		String strQuery="";
		////////////////System.out.println("qtypeqtypeqtypeqtype"+qtype);
		
		strQuery= "Select c.serviceid, s.servicename, c.dueamount, c.duedate,s.Subscription from consumerbilldetail c, service s where c.serviceid = s.serviceid and c.consumerid="+residentid+" and c.month="+Cmonth+" and c.year="+Cyear+" and s.is_mendetory="+type;
		
		System.out.println("Query is"+strQuery);
		/**
		 * set all the fileds type and name for blogs table by using
		 * Support class object.
		 */
		support.setFieldVec("int", "c.serviceid");
		support.setFieldVec("string", "s.servicename");
		support.setFieldVec("int", "c.dueamount");
		support.setFieldVec("date", "c.duedate");
		support.setFieldVec("String", "s.Subscription");
		//support.setFieldVec("string", "creation_time");			
		// not used in draft support.setFieldVec("int", "fcom_id");
		////////////////System.out.println("strQuery........."+strQuery);
		resultVec = masterUtil.getList(ds, strQuery, support);
		System.out.println("bills done");
		return resultVec;
}

	/**
	 * ***************************************************************************************************************
	 * This function is use for the get the meter reading details for a consumption based service in a month/year
	****************************************************************************************************************
	*/
	public Vector BilDetailsReading(DataSource ds,Vector paramVec){
		Vector resultVec = new Vector();
		int residentid = Integer.parseInt(paramVec.elementAt(0).toString().trim());
		int facilityid = Integer.parseInt(paramVec.elementAt(1).toString().trim());
		int serviceid = Integer.parseInt(paramVec.elementAt(2).toString().trim());
		int Cmonth = Integer.parseInt(paramVec.elementAt(3).toString().trim());
		int Cyear = Integer.parseInt(paramVec.elementAt(4).toString().trim());
		
		Support support=new Support();		
		String strQuery="";
		////////////////System.out.println("qtypeqtypeqtypeqtype"+qtype);
		
		strQuery= "Select * from consumer_bills c where c.serviceid = "+serviceid+" and c.facilityid="+facilityid+" and c.residentid="+residentid;
		
		System.out.println("Query is"+strQuery);
		/**
		 * set all the fileds type and name for blogs table by using
		 * Support class object.
		 */
		support.setFieldVec("int", "c.serviceid");
		support.setFieldVec("int", "c.residentid");
		support.setFieldVec("float", "c.previousmeterreading");
		support.setFieldVec("float", "c.currentmeterreading");
		
		resultVec = masterUtil.getList(ds, strQuery, support);
		
		return resultVec;
}

	/**
	 * ***************************************************************************************************************
	 * This function is use for the get First tag id from data Base because complaint or Draft add in First status.
	****************************************************************************************************************
	*/
	public int getMinTagID(DataSource ds)
	{
		int result = 0;
		
		String strQuery="";		
		strQuery= "select min(tagid)tagid  from tages ";
		
		result = masterUtil.getId(ds, strQuery, "tagid");
		
		return result;	
		
				
	}
	/**
	 * ***************************************************************************************************************
	 *This function Get the tag Name by using the tag_Id from the tags Table.
*	***************************************************************************************************************
*	*/
	public Vector getTagName(DataSource ds,int tagid)
	{
		Vector resultVec = new Vector();
		Support support=new Support();
		String strQuery="";;
		strQuery= "select tagname from tages where tagid="+tagid;
			
		support.setFieldVec("string", "stagename");	
		
	
		
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		
		return resultVec;
		
	}
	
	/**
	 * ***************************************************************************************************************
	*This function get the core User id from the complaintallocation table ,because complaint will assign to the 
	*perticuler Core User for handling the complaint .
*	***************************************************************************************************************
*	*/
	public int getCoreUserId(DataSource ds,int catid)
	{
		int result = 0;
		
		String strQuery="";
		
		strQuery= "select cuid from complaintallocation where allocationcriteria like '%"+catid+"%'";
		
		
		result = masterUtil.getId(ds, strQuery, "cuid");	
		
		return result;
		
	}
	

	/**
	 * ***************************************************************************************************************
	*This function get the category from classifiedcategory table ,
*	***************************************************************************************************************
*	*/
	public String findcategory(DataSource ds,String catid)
	{
		String result = "";
		Support support=new Support();
		String strQuery="";
		
		strQuery= "select cat_name from classifiedcategory where cat_id="+catid;
		
		support.setFieldVec("string", "cat_name");//21
		
		
		return masterUtil.getValue(ds, strQuery, support);
		
		
		
	}
	
	/**
	 * This function get the company id from companymaster table.
	 * takes type_of_company,company_sname as argument and  return company_id.
	 *
	 */
	public int getCompanyId(DataSource ds,String typeofcompany, String company_sname)
	{
		int result = 0;
		
		String strQuery="";
		strQuery= "select company_id from companymaster where typeofcompany = '"+typeofcompany+"' and company_sname ='"+company_sname+"'";
		
		
		result = masterUtil.getId(ds, strQuery, "company_id");	
		
		return result;
		
	}
	
	/**
	 * This function get the company id from companymaster table.
	 * takes type_of_company,company_sname as argument and  return company_id.
	 *
	 */
	public int getCompanyId(DataSource ds,int uid)
	{
		int result = 0;
		
		String strQuery="";
		strQuery= "select companyid from loginmaster where userid = "+uid;
		
		
		result = masterUtil.getId(ds, strQuery, "companyid");	
		
		return result;
		
	}
	
	
	
	
	/**
	 * ***************************************************************************************************************
	 * This function is use for the get Max brand id from `dealer` table.
	 ****************************************************************************************************************
	 **/
	public int getMaxDealerId(DataSource ds)
	{
		int result = 0;
		
		String strQuery="";		
		strQuery= "Select max(dealerid)dealerid dealerid  from dealer ";
		
		result = masterUtil.getId(ds, strQuery, "dealerid");
		
		
		return result;			
				
	}
	
	
	
	
	/**
	 * **********************************************************************************************************
	*This function add all the data of complaint in complaints table .
	*and return the result string Success or failure accordiong to query result.
*	************************************************************************************************************
*	*/
	public String insertNewComplaints(DataSource ds,Vector paramVec){
		
		
		Support support=new Support();
		String strQuery="";
		String strResult = "";
		strQuery = "Insert into complaints (complaint_title, complaint_text, relevent_info, category, "+
		"creation_date,publish_date,creation_time, "+
		"publish_time,lastmodify_date,lastmodify_time,login_id ,companyid,timepart,viewcount,entp_id,cu_id,advt_id,advtL_id,dealer_id,tag_id,resolve_date,closed_date,brandFlag,publish_flag,publish_on,other,q_type) Values(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		support.setDataVec("string", paramVec.elementAt(0).toString().trim()); //complaint_title
		support.setDataVec("string", paramVec.elementAt(1).toString().trim()); //complaint_text
		support.setDataVec("string", paramVec.elementAt(2).toString().trim()); //relevent_info
		support.setDataVec("string", paramVec.elementAt(3).toString().trim()); //category
		support.setDataVec("string", paramVec.elementAt(4).toString().trim()); //creation_date
		support.setDataVec("string", paramVec.elementAt(5).toString().trim()); //publish_date
		support.setDataVec("string", paramVec.elementAt(6).toString().trim()); //creation_time
		support.setDataVec("string", paramVec.elementAt(7).toString().trim()); //publish_time
		support.setDataVec("string", paramVec.elementAt(4).toString().trim()); //lastmodify_date
		support.setDataVec("string", paramVec.elementAt(6).toString().trim()); //lastmodify_time
		support.setDataVec("int", paramVec.elementAt(10).toString().trim());   //login_id
		support.setDataVec("int", paramVec.elementAt(11).toString().trim());   //companyid
		support.setDataVec("string", paramVec.elementAt(12).toString().trim());//timepart
		support.setDataVec("int", paramVec.elementAt(13).toString().trim());   //viewcount
		support.setDataVec("int", paramVec.elementAt(14).toString().trim());   //entp_id
		support.setDataVec("int", paramVec.elementAt(15).toString().trim());   //cu_id
		support.setDataVec("int", paramVec.elementAt(16).toString().trim());   //advt_id
		support.setDataVec("int", paramVec.elementAt(17).toString().trim());   //advtL_id
		support.setDataVec("int", paramVec.elementAt(18).toString().trim());   //dealer_id
		support.setDataVec("int", paramVec.elementAt(19).toString().trim());   //tag_id
		support.setDataVec("string", paramVec.elementAt(20).toString().trim());//resolve_date
		support.setDataVec("string", paramVec.elementAt(21).toString().trim());//closed_date
		support.setDataVec("int", paramVec.elementAt(22).toString().trim());   //brandFlag
		support.setDataVec("int", paramVec.elementAt(23).toString().trim());//publish_flag
		support.setDataVec("string", paramVec.elementAt(24).toString().trim());   //publish_on
		support.setDataVec("string", paramVec.elementAt(25).toString().trim());   //other
		support.setDataVec("string", paramVec.elementAt(26).toString().trim());   //other
		
		
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
		
	}
	/** Adding new Service Request in ConsumerService Table
	 * 
	 * */
	
	 public String insertNewServiceRequest(DataSource ds,Vector paramVec){
		
		
		Support support=new Support();
		String strQuery="";
		String strResult = "";
		strQuery = "Insert into consumerservicemapping (consumerid, serviceid, datefrom, dateto, createdby, createdon, "+
		"modifiedby, modifiedon, status) Values(?,?, ?, ?, ?, ?, ?, ?, ?)";
		
		support.setDataVec("string", paramVec.elementAt(0).toString().trim()); //consumer ID
		support.setDataVec("string", paramVec.elementAt(1).toString().trim()); //Service ID
		support.setDataVec("string", paramVec.elementAt(2).toString().trim()); //date from
		support.setDataVec("string", paramVec.elementAt(3).toString().trim()); //dateto
		support.setDataVec("string", paramVec.elementAt(4).toString().trim()); //createdby
		support.setDataVec("string", paramVec.elementAt(5).toString().trim()); //createdon
		support.setDataVec("string", paramVec.elementAt(6).toString().trim()); //modifiedby
		support.setDataVec("string", paramVec.elementAt(7).toString().trim()); //modifiedon
		support.setDataVec("string", paramVec.elementAt(8).toString().trim()); //status
				
		
		strResult = masterUtil.setData(ds, strQuery, support);
		
		System.out.println("checkpoint 2000");
		return strResult;
		
		
	}
	
	/* end consumer service table insertion*/

	 /*New notice to residents
	  * 
	  */
	 
	 public String insertNewNotice(DataSource ds,Vector paramVec){
			
			
			Support support=new Support();
			String strQuery="";
			String strResult = "";
			strQuery = "Insert into noticeboard (facility_id, noticetitle, noticetext, endingon, createdby, createdon,modifiedby, modifiedon) Values(?,?, ?, ?, ?, ?, ?, ?)";
			
			support.setDataVec("string", paramVec.elementAt(0).toString().trim()); //FacilityID
			support.setDataVec("string", paramVec.elementAt(1).toString().trim()); //Notice Title
			support.setDataVec("string", paramVec.elementAt(2).toString().trim()); //Notice Text
			support.setDataVec("string", paramVec.elementAt(3).toString().trim()); //endingon
			support.setDataVec("string", paramVec.elementAt(4).toString().trim()); //createdby
			support.setDataVec("string", paramVec.elementAt(5).toString().trim()); //createdon
			support.setDataVec("string", paramVec.elementAt(6).toString().trim()); //modifiedby
			support.setDataVec("string", paramVec.elementAt(7).toString().trim()); //modifiedon
					
			
			strResult = masterUtil.setData(ds, strQuery, support);
			
			System.out.println("Notices writing Done");
			return strResult;
		
		}
/* end - new notice insertion
 * 
 */
	
	/**
	 **********************************************************************************************************
	 *This function add all the data of complaint in Drafts table .
	 *and return the result string Success or failure accordiong to query result.
*	 ************************************************************************************************************
*	*/
	public String insertDraft(DataSource ds,Vector paramVec){
		
		Support support=new Support();
		String strQuery="";
		String strResult = "";
		strQuery = "Insert into drafts (draft_title, draft_text, relevent_info, category, "+
		"creation_date,creation_time,lastmodify_date, "+
		"lastmodify_time,login_id,companyid,timepart ,dealer_id,other,advt_id,q_type) Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		support.setDataVec("string", paramVec.elementAt(0).toString().trim());
		support.setDataVec("string", paramVec.elementAt(1).toString().trim());
		support.setDataVec("string", paramVec.elementAt(2).toString().trim());
		support.setDataVec("int", paramVec.elementAt(3).toString().trim());
		support.setDataVec("string", paramVec.elementAt(4).toString().trim());
		support.setDataVec("string", paramVec.elementAt(5).toString().trim());
		support.setDataVec("string", paramVec.elementAt(6).toString().trim());
		support.setDataVec("string", paramVec.elementAt(7).toString().trim());
		support.setDataVec("int", paramVec.elementAt(8).toString().trim());
		support.setDataVec("int", paramVec.elementAt(9).toString().trim());
		support.setDataVec("string", paramVec.elementAt(10).toString().trim());
		support.setDataVec("int", paramVec.elementAt(11).toString().trim());
		support.setDataVec("string", paramVec.elementAt(12).toString().trim());
		support.setDataVec("int", paramVec.elementAt(13).toString().trim());
		support.setDataVec("String", paramVec.elementAt(14).toString().trim());
		
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
		
	}
	
	
	/**
	 **********************************************************************************************************
	 *This function add all the data of tab & tabtext content in compayprofile table .
	 *and return the result string Success or failure accordiong to query result.
*	 ************************************************************************************************************
*	*/
	
public String insertTab(DataSource ds,Vector paramVec){
		
		Support support=new Support();
		String strQuery="";
		String strResult = "";
		strQuery = "Insert into companyprofile (company_id, user_id, tab1, tab2, "+
		"tab3 ,tab4 ,tab5 , "+
		"tabtext1 ,tabtext2 ,tabtext3 ,tabtext4 ,tabtext5 ) Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		support.setDataVec("int", paramVec.elementAt(0).toString().trim());
		support.setDataVec("int", paramVec.elementAt(1).toString().trim());
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
	
				
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
		
	}
	
	
/**
 * **************************************************************************************************
 *	update  tab & tabtext information in compayprofile table and return result status result string.
 *********************************************************************************************************
 */	
public String updateTab(DataSource ds,Vector paramVec){
	
	Support support=new Support();
	String strQuery="";
    String strResult = "";
	strQuery = "Update companyprofile set tab1=?, tab2=?, tab3=?,tab4=?,  "+
	"tab5=? ,tabtext1=?,tabtext2=?, tabtext3=?, tabtext4=? tabtext5=? where tab_id=? and user_id=?";
	
	support.setDataVec("string", paramVec.elementAt(0).toString().trim());	//0 tab1
	support.setDataVec("string", paramVec.elementAt(1).toString().trim());	//1 tab2
	support.setDataVec("string", paramVec.elementAt(2).toString().trim());	//2 tab3
	support.setDataVec("string", paramVec.elementAt(3).toString().trim());	//3 tab4
	support.setDataVec("string", paramVec.elementAt(4).toString().trim());	//4 tab5
	support.setDataVec("string", paramVec.elementAt(5).toString().trim());	//5 tabtext1
	support.setDataVec("string", paramVec.elementAt(6).toString().trim());	//6 tabtext2
	support.setDataVec("stirng", paramVec.elementAt(7).toString().trim());	//7 tabtext3
	support.setDataVec("string", paramVec.elementAt(8).toString().trim());	//8 tabtext4
	support.setDataVec("string", paramVec.elementAt(9).toString().trim());	//9 tabtext5 
	
			
	strResult = masterUtil.setData(ds, strQuery, support);
	return strResult;
	
	
}



/**
 * This function is use for getting all tab detail 
 * of a particuler tab from companyprofile table.
 */

public Vector getTabDetails(DataSource ds,Vector dataVec){
	
	Vector resultVec = new Vector();
	//int tab_id = Integer.parseInt(dataVec.elementAt(0).toString().trim());
	int user_id= Integer.parseInt(dataVec.elementAt(0).toString().trim());	
	Support support=new Support();
	   String strQuery="";
	   
		strQuery = "SELECT * FROM comppro_data where  companyid= "+user_id;				
	
	
	
	 /**
	 * set all the fileds type and name for companyprofile table by using
	 * Support class object.
	 */
		support.setFieldVec("string", "tab1");			//0
		support.setFieldVec("string", "tab2");	        //1
		support.setFieldVec("string", "tab3");	        //2
		support.setFieldVec("string", "tab4");		    //3		
		support.setFieldVec("string", "tab5");			//4
		support.setFieldVec("string", "text1");		//5
		support.setFieldVec("string", "text2");		//6
		support.setFieldVec("string", "text3");		//7
		support.setFieldVec("string", "text4");		//8
		support.setFieldVec("string", "text5");	    //9
		support.setFieldVec("int", "companyid");		//11
		support.setFieldVec("int", "tab_id");		//11
	
	resultVec = masterUtil.getDetail(ds, strQuery, support);
	return resultVec;
}



	
	
	/**
	 * **************************************************************************************************
     *	update  draft information in drafts table and return result status result string.
	 *********************************************************************************************************
	 */	
public String updateDraft(DataSource ds,Vector paramVec){
		
		Support support=new Support();
		String strQuery="";
	    String strResult = "";
		strQuery = "Update drafts set draft_title=?, draft_text=?, relevent_info=?,category=?,  "+
		"lastmodify_date=? ,lastmodify_time=?,dealer_id=?, other=?, advt_id=? where draft_id=?";
		
		support.setDataVec("string", paramVec.elementAt(0).toString().trim());	//0 draft_title
		support.setDataVec("string", paramVec.elementAt(1).toString().trim());	//1 draft_text
		support.setDataVec("string", paramVec.elementAt(2).toString().trim());	//2 relevent_info
		support.setDataVec("string", paramVec.elementAt(3).toString().trim());	//3 category
		support.setDataVec("string", paramVec.elementAt(4).toString().trim());	//4 lastmodify_date
		support.setDataVec("string", paramVec.elementAt(5).toString().trim());	//5 lastmodify_time
		support.setDataVec("int", paramVec.elementAt(6).toString().trim());		//6 dealer_id
		support.setDataVec("stirng", paramVec.elementAt(7).toString().trim());	//7 other
		support.setDataVec("int", paramVec.elementAt(8).toString().trim());		//8 advt_id
		support.setDataVec("INT", paramVec.elementAt(9).toString().trim());		//9 draft_id 
		
				
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
		
	}
	/**
	******************************************************************************************************************
	*This function takes loginId ,complainTitle and current_time  as argument and according to these parameter get the 
	*complaint Id from the complaints table for showing the complaint Id to the user after submission of copmplaint if 
	*submission is Successful.
	********************************************************************************************************************
	*/
	public String getComplainId(DataSource ds,int loginId, String complainTitle, String creation_time)
	{
		Vector tempVec = new Vector();
		Support support=new Support();
	    String strQuery="";
	    String strResult = "";
	    String cTime = creation_time.toString().trim();
		
		strQuery = "Select complaint_id,cu_id,creation_date from complaints where complaint_title = '"+complainTitle+"' and login_id = "+loginId+" and creation_time = '"+cTime+"'";
		
		support.setFieldVec("int", "complaint_id");
		support.setFieldVec("int", "cu_id");
		support.setFieldVec("String", "creation_date");
		
		tempVec = masterUtil.getDetail(ds, strQuery, support);
		
		System.out.println("temp vector is "+tempVec);
		String draft_id = tempVec.elementAt(0).toString().trim();
		String cu_id = tempVec.elementAt(1).toString().trim();
		String creation_date = tempVec.elementAt(2).toString().trim();
		String year=creation_date.substring(0,4);
		
		strResult = draft_id+"/"+cu_id+"/"+year;
		
		
		return strResult;
				
	}
	
	public String getFinalComplainId(DataSource ds,int loginId, String complainTitle, String creation_time)
	{
		Vector tempVec = new Vector();
		Support support=new Support();
	    String strQuery="";
	    String strResult = "";
	    String Ctime = creation_time.toString().trim();
		
	    
		strQuery = "Select complaint_id,cu_id,creation_date from complaints where complaint_title = '"+complainTitle+"' and login_id = "+loginId+" and creation_time = '"+Ctime+"'";
		
		System.out.println("Query at complaintID "+strQuery);
		
		support.setFieldVec("int", "complaint_id");
		support.setFieldVec("int", "cu_id");
		support.setFieldVec("String", "creation_date");
		
		System.out.println("temp vector is "+tempVec);
			
		tempVec = masterUtil.getDetail(ds, strQuery, support);
		System.out.println("temp vector is "+tempVec);
					
		String draft_id = tempVec.elementAt(0).toString().trim();
		String cu_id = tempVec.elementAt(1).toString().trim();
		String creation_date = tempVec.elementAt(2).toString().trim();
		String year=creation_date.substring(0,4);
		Resource rsource = new Resource();
		String strComplaintId = "", strCuId = "", strComplaintYear="";
		
		long numComplaintId = rsource.rndCompalintId(Integer.parseInt(draft_id.trim()), 99999);
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
		
		long numCuId = rsource.rndEntpUserId(Integer.parseInt(cu_id.trim()), 99999);
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
	
	
	
	
	public String getCompIdForComp(DataSource ds,int loginId, String complainTitle, String creation_time)
	{
		Vector tempVec = new Vector();
		Support support=new Support();
	    String strQuery="";
	    String strResult = "";
		
		strQuery = "Select complaint_id,cu_id,creation_date from complaints where complaint_title = '"+complainTitle+"' and advtL_id = "+loginId+" and creation_time = '"+creation_time+"'";
		
		support.setFieldVec("int", "complaint_id");
		support.setFieldVec("int", "cu_id");
		support.setFieldVec("String", "creation_date");
		
		tempVec = masterUtil.getDetail(ds, strQuery, support);
		
		String draft_id = tempVec.elementAt(0).toString().trim();
		String cu_id = tempVec.elementAt(1).toString().trim();
		String creation_date = tempVec.elementAt(2).toString().trim();
		String year=creation_date.substring(0,4);
		
		strResult = draft_id+"/"+cu_id+"/"+year;
		
		
		return strResult;
				
	}
	
	
	public String getFinalCompIdForComp(DataSource ds,int loginId, String complainTitle, String creation_time)
	{
		Vector tempVec = new Vector();
		Support support=new Support();
	    String strQuery="";
	    String strResult = "";
		
		strQuery = "Select complaint_id,cu_id,creation_date from complaints where complaint_title = '"+complainTitle+"' and advtL_id = "+loginId+" and creation_time = '"+creation_time+"'";
		
		support.setFieldVec("int", "complaint_id");
		support.setFieldVec("int", "cu_id");
		support.setFieldVec("String", "creation_date");
		
		tempVec = masterUtil.getDetail(ds, strQuery, support);
		
		String draft_id = tempVec.elementAt(0).toString().trim();
		String cu_id = tempVec.elementAt(1).toString().trim();
		String creation_date = tempVec.elementAt(2).toString().trim();
		String year=creation_date.substring(0,4);
		Resource rsource = new Resource();
		String strComplaintId = "", strCuId = "", strComplaintYear="";
		
		long numComplaintId = rsource.rndCompalintId(Integer.parseInt(draft_id.trim()), 99999);
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
		
		long numCuId = rsource.rndEntpUserId(Integer.parseInt(cu_id.trim()), 99999);
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
	 * Set final complaint id in to the complaints table.
	 * This function takes fcom_id and complaint_id as argument and  return Success or Failure.
	 */
	
	public String setFinalId(DataSource ds,Vector dataVec){
		
		Support support=new Support();
	    String strQuery="";
		String strResult = "";
		strQuery = "Update complaints set fcom_id=? where complaint_id=?";
		
		support.setDataVec("string", dataVec.elementAt(0).toString().trim());
		support.setDataVec("int", dataVec.elementAt(1).toString().trim());
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
	}
	/**
	 * get final complaint id in to the complaints table.
	 * This function takes fcom_id and complaint_id as argument and  return Success or Failure.
	 */
	
	public Vector getFinalId(DataSource ds,int loginId, String complainTitle, String creation_time){
		
		Vector resultVec = new Vector();
		Support support=new Support();
	    String strQuery="";
		strQuery = "Select * from complaints where complaint_title = '"+complainTitle+"' and login_id = "+loginId+" and creation_time = '"+creation_time+"'";
		
		support.setFieldVec("string", "fcom_id");
		
		resultVec = masterUtil.getDetail(ds, strQuery, support);
		
		return resultVec;
		
	}
	
	/**
	 * get Mial Ids accordding to coming vector from loginMaster table.
	 * This function takes Vector of ids as argument and  return Vector of mail id.
	 */
	
	public Vector getEmailList(DataSource ds,Vector dataVec){
		
		Vector<Vector> resultVec = new Vector<Vector>();
		String strQuery = "";
		for(int i=0; i<dataVec.size(); i++)
		{
			strQuery = "select first_name, last_name, email, userid, companyid, login_type  from loginmaster where userid ="+dataVec.elementAt(i).toString().trim();
			Support support=new Support();
			support.setFieldVec("string", "first_name");
			support.setFieldVec("string", "last_name");
			support.setFieldVec("string", "email");
			support.setFieldVec("int", "userid");
			support.setFieldVec("int", "companyid");
			support.setFieldVec("string", "login_type");
			
			Vector tempVec = masterUtil.getDetail(ds, strQuery, support);
			//////////////System.out.println(" to userid...foe Mail....strQuery"+strQuery);
			//////////////System.out.println(" to userid...foe Mail....resultVec"+tempVec);
			resultVec.add(tempVec);
			
		}
	
		return resultVec;
		
	}
	

	/**
	 * *****************************************************************************************************************
	This function is use for the deletion of Draft from data base and return status result string it takes
	data vector as argument.
	 *********************************************************************************************************************/
	public String deleteDraft(DataSource ds,Vector dataVec)
	{	
		
		
	    String strQuery="";
		String strResult="";
			  
		strQuery = "delete from drafts where draft_id in ("+dataVec.elementAt(0).toString().trim()+")";
		
		strResult = masterUtil.deleteData(ds, strQuery);
		
		return strResult;
	}
	/**
	 * *****************************************************************************************************************
	This function is use for the deletion of dealer from data base and return status result string it takes
	data vector as argument.
	 *********************************************************************************************************************/
	public String deleteDealer(DataSource ds,int dealerId)
	{	
		
		
	    String strQuery="";
		String strResult="";
			  
		strQuery = "delete from dealer where dealerid ="+dealerId;
		
		strResult = masterUtil.deleteData(ds, strQuery);
		
		return strResult;
	}
	
	public Vector getCategories(DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		 
		strQuery = "select cat_id,cat_name  from category where cat_enabled=1";
		support.setFieldVec("int", "cat_id");
		support.setFieldVec("String", "cat_name");
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
		 
		return resultVec;
	}
//	get brand list
	public Vector getBrand(int catid, DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		String catsearchId= ", "+catid+",";
		//////////////System.out.println("catsearchId.........."+catsearchId);
		//Consumer cr = null; 
		
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
				
		strQuery = "select company_id, company_name  from companymaster where active=1 and typeofcompany=\"Corporates\" and category like '%"+catsearchId+"%'order by company_name";
		//////////////System.out.println("strQuerystrQuery>>>>>>>>>Cate>>>>>>>>"+strQuery);
		support.setFieldVec("int", "company_id");
		support.setFieldVec("String", "company_name");
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
	 //////////////System.out.println("resultVec.........."+resultVec);
	 return resultVec;
	}
	
	public Vector getNewBrand(int catid, DataSource ds,String Arry)
	{
		Support support=new Support();
		String strQuery="";
		
		String catsearchId1= ", "+catid+",";
		//////////////System.out.println("catsearchId.........."+catsearchId);
		//Consumer cr = null; 
		
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		if(Arry.equalsIgnoreCase("3"))
		{
			Arry="";
		}
		strQuery = "select distinct C.company_id, C.company_name  from companymaster C,loginmaster L where C.company_id=L.companyid and L.rid!=0 and L.logindeletion=0 and C.active=1 and C.typeofcompany=\"Corporates\" and C.category like '%"+catsearchId1+"%' and L.qure_cat like '%"+Arry+"%'order by company_name";
		////////System.out.println("strQuerystrQuery>>>>>>>>>Cate>>>>>>>>"+strQuery);
		support.setFieldVec("int", "company_id");
		support.setFieldVec("String", "company_name");
		////////System.out.println("strQuery.........."+strQuery);
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
	 //////////////System.out.println("resultVec.........."+resultVec);
	 return resultVec;
	}
	
//	get College list
	public Vector getCollege(int catid, DataSource ds)
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
				
		strQuery = "select company_id, company_name  from companymaster where  typeofcompany=\"Advertiser\" and active=1  ";
		support.setFieldVec("int", "company_id");
		support.setFieldVec("String", "company_name");
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		Vector<String>tempVec2 = new Vector<String>();
		tempVec2.add("-1");
		tempVec2.add("Others");
		resultVec.add(tempVec2);	
	 
	 return resultVec;
	}
	
	
	public Vector getNewCollege(int catid, DataSource ds,String Arry)
	{
		Support support=new Support();
		String strQuery="";
		//Consumer cr = null; 
		
		String catsearchId1= ", "+catid+",";
		 //ArrayList<Consumer> arrPlace = new ArrayList<Consumer>();
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		if(Arry.equalsIgnoreCase("3"))
		{
			Arry="";
		}	
		strQuery = "select distinct C.company_id, C.company_name  from companymaster C,loginmaster L where C.company_id=L.companyid and L.rid!=0 and L.logindeletion=0 and C.active=1 and C.typeofcompany=\"Advertiser\" and C.category like '%"+catsearchId1+"%' and L.qure_cat like '%"+Arry+"%'order by company_name";
		support.setFieldVec("int", "company_id");
		support.setFieldVec("String", "company_name");
		
		//////System.out.println("strQuery.........."+strQuery);
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
	 
	 return resultVec;
	}
	
	
	public Vector getCollegeForQuery(int catid, DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
				
		strQuery = "select company_id, company_name  from companymaster where  typeofcompany=\"Advertiser\" and active=1  ";
		support.setFieldVec("int", "company_id");
		support.setFieldVec("String", "company_name");
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
	 ////////////System.out.println("resultVec.........."+resultVec);
	 return resultVec;
	}
	
	
	
//	get Student  list
	public Vector getStudent ( DataSource ds)
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
				
		strQuery = "select userid, first_name, last_name  from loginmaster where  login_type=\"Student \" and logindeletion=0  ";
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
	
//	get Consultants  list
	public Vector getConsultants ( DataSource ds)
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
		tempVec.add("");
		resultVec.add(tempVec);
				
		strQuery = "select userid, first_name, last_name  from loginmaster where  login_type=\"Consultants \" and logindeletion=0  ";
		//////////////System.out.println("strQuerystrQuerystrQuerystrQuery"+strQuery);
		
		support.setFieldVec("int", "userid");
		
		support.setFieldVec("String", "first_name");
		support.setFieldVec("String", "last_name");
		
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		//////////////System.out.println("tempResultVectempResultVec"+tempResultVec);
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
			//////////////System.out.println("resultVecresultVecresultVec"+resultVec);
		}
		
		
	 return resultVec;
	}

	
	
	//end of code
	//get contact person list
	public Vector getContactPerson(int catid, int brandid, DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		tempVec.add("0");
		tempVec.add("Select");
		tempVec.add("");
		resultVec.add(tempVec);
		 if(catid>0 && brandid >=0)
		 {
			 strQuery = "select userid, first_name,last_name from loginmaster where logindeletion=0 and  companyid="+brandid;
				 
			 support.setFieldVec("int", "userid");
			 support.setFieldVec("String", "first_name");
			 support.setFieldVec("String", "last_name");
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
		 tempVec2.add("");
		 resultVec.add(tempVec2);
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
	
	
	public Vector getActivContactPerson(String qtype, int brandid, DataSource ds)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		tempVec.add("0");
		tempVec.add("Select");
		tempVec.add("");
		resultVec.add(tempVec);
		 strQuery = "select userid, first_name,last_name from loginmaster where logindeletion=0 and rid!=0 and qure_cat like '%"+qtype+"%' and  companyid="+brandid;
				 
			 support.setFieldVec("int", "userid");
			 support.setFieldVec("String", "first_name");
			 support.setFieldVec("String", "last_name");
			 Vector <Vector>tempResultVec  = new Vector<Vector>();
			 tempResultVec=(Vector)masterUtil.getList(ds, strQuery, support);
			 for(int i=0; i<tempResultVec.size();i++)
			 {
				 resultVec.add(tempResultVec.elementAt(i));
			 }
			
		
		 return resultVec;
	}
	
	//get all complaint list for the consumer
	/*
	 * public Vector getResponses(DataSource ds , String complaintId)
	 * {
	 * 	
	 * }
	 */
	
	//end of contact person list code
	//Complaint should be publish or not that is decided after
	public boolean publishCheck ()
	{
		boolean blnResult =true;
		
		return blnResult;
	}


	/**
	* This function is use for getting user information in indv.
	*/
	public Vector getUserInfo(DataSource ds, int userid) {
	Support support=new Support();
	String strQuery="";
	
	strQuery = "select * from loginmaster where userid = " + userid;
	
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
	 *  This function Update the User Information in the loginmaster table.
	 */
	public String updateUserInfo(DataSource ds,Vector paramVec){
		
		String strResult = "failure";	
		Support support=new Support();
	    String strQuery="";
		strQuery = "Update loginmaster set first_name=?, last_name=?, email=?, phone=?, mobile=? , address=?, country=?,"+
		"state=?, city=?, zip=?, lastmodifiedDate=?,lastmodifiedTime=?, aboutus=?,annualincome=?,Birthday=?, " +
		"education=?, maritalstatus=?, profession=?, gender=?, status=?, course=?, major_sub=?, pro_enable=?,pd_enable=? where userid=?";
		
		
		
		support.setDataVec("string", paramVec.elementAt(1).toString().trim()); //0 first_name
		support.setDataVec("string", paramVec.elementAt(2).toString().trim()); //1 last_name
		support.setDataVec("string", paramVec.elementAt(3).toString().trim()); //2 email
		support.setDataVec("string", paramVec.elementAt(4).toString().trim()); //3 phone
		support.setDataVec("string", paramVec.elementAt(5).toString().trim()); //4 mobile 
		support.setDataVec("string", paramVec.elementAt(6).toString().trim()); //5 address
		support.setDataVec("string", paramVec.elementAt(7).toString().trim()); //6 country
		support.setDataVec("string", paramVec.elementAt(8).toString().trim()); //7 state
		support.setDataVec("string", paramVec.elementAt(9).toString().trim()); //8 city
		support.setDataVec("string", paramVec.elementAt(10).toString().trim());//9 zip
		support.setDataVec("string", paramVec.elementAt(11).toString().trim());//10 lastmodifiedDate
		support.setDataVec("string", paramVec.elementAt(12).toString().trim());//11 lastmodifiedTime
		support.setDataVec("string", paramVec.elementAt(13).toString().trim());//12 aboutus
		support.setDataVec("string", paramVec.elementAt(14).toString().trim());//13 annualincome
		support.setDataVec("string", paramVec.elementAt(15).toString().trim());//14 Birthday
		support.setDataVec("string", paramVec.elementAt(16).toString().trim());//15 education
		support.setDataVec("string", paramVec.elementAt(17).toString().trim());//16 maritalstatus
		support.setDataVec("string", paramVec.elementAt(18).toString().trim());//17 profession
		support.setDataVec("string", paramVec.elementAt(19).toString().trim());//18 gender
		support.setDataVec("string", paramVec.elementAt(20).toString().trim());//19 Status		
		support.setDataVec("string", paramVec.elementAt(21).toString().trim());//20 Courseid
		support.setDataVec("string", paramVec.elementAt(22).toString().trim());//21 Major_sub
		
		support.setDataVec("string", paramVec.elementAt(23).toString().trim());//21 Major_sub
		support.setDataVec("string", paramVec.elementAt(24).toString().trim());//21 Major_sub
		
		support.setDataVec("int", paramVec.elementAt(0).toString().trim());    //22 user Id
		
		////////////System.out.println("paramVec.elementAt(20).toString().trim()>>>>>>>>>>>>>>>>>>>"+paramVec.elementAt(21).toString().trim());
		////////////System.out.println("paramVec.elementAt(20).toString().trim()>>>>>>>>>>>>>>>>>>>"+paramVec.elementAt(22).toString().trim());	
		
		
		////////////System.out.println("strQuery>>>>>>>>>>>>>>>>>>>"+strQuery);
		
		strResult = masterUtil.setData(ds, strQuery, support);
		////////////System.out.println("strResult>>>>>>>>>>>>>>>>>>>"+strResult);
		return strResult;	
				
	}
	
	

	/**
	 *  This function Update the User Information in the loginmaster table.
	 */
	public String updateResidentDetailInfo(DataSource ds,Vector paramVec){
		
		String strResult = "failure";	
		Support support=new Support();
	    String strQuery="";
		strQuery = "Update resident_details set spouse_name=?, spouse_dob=?, child1_name=?, child1_dob=?, child2_name=? , child2_dob=?, child3_name=?,"+
		"child3_dob=?, building=?, tower=?, flat=?,vehicle=?, vehicle_name=?,vehicle_make=?,vehicle_mfd=? where user_id=?"; 
		
		//"education=?, maritalstatus=?, profession=?, gender=?, status=?, course=?, major_sub=?, pro_enable=?,pd_enable=? where userid=?";
		
		//details_id,user_id,details_id,user_id,spouse_name,spouse_dob,child1_name,child1_dob,child2_name,child2_dob,child3_name,
		//child3_dob,facility_id,building,tower,flat,vehicle,vehicle_name,vehicle_make,vehicle_mfd,comments
		
		support.setDataVec("string", paramVec.elementAt(25).toString().trim()); //25 Spouse Name
		support.setDataVec("string", paramVec.elementAt(26).toString().trim()); //26 Spouse DOB
		support.setDataVec("string", paramVec.elementAt(27).toString().trim()); //27 Child 1 Name
		support.setDataVec("string", paramVec.elementAt(28).toString().trim()); //28 Child 1 DOB
		support.setDataVec("string", paramVec.elementAt(29).toString().trim()); //29 Child 2 Name 
		support.setDataVec("string", paramVec.elementAt(30).toString().trim()); //30 Child 2 DOB
		support.setDataVec("string", paramVec.elementAt(31).toString().trim()); //31 Child 3 Name
		support.setDataVec("string", paramVec.elementAt(32).toString().trim());  //32 Child 3 DOB
		support.setDataVec("string", paramVec.elementAt(33).toString().trim());  //33 Flat No
		support.setDataVec("string", paramVec.elementAt(34).toString().trim()); //34 Building Name
		support.setDataVec("string", paramVec.elementAt(35).toString().trim()); //35 Tower No 
		support.setDataVec("string", paramVec.elementAt(36).toString().trim()); //36 Vehicle Type
		support.setDataVec("string", paramVec.elementAt(37).toString().trim()); //37 Vehicle Name 
		support.setDataVec("string", paramVec.elementAt(38).toString().trim()); //38 Vehicle Model Year
		support.setDataVec("string", paramVec.elementAt(39).toString().trim()); //39 Vehicle Manufacturer
		support.setDataVec("int", paramVec.elementAt(0).toString().trim());     //22 user Id
		
		////////////System.out.println("paramVec.elementAt(20).toString().trim()>>>>>>>>>>>>>>>>>>>"+paramVec.elementAt(21).toString().trim());
		////////////System.out.println("paramVec.elementAt(20).toString().trim()>>>>>>>>>>>>>>>>>>>"+paramVec.elementAt(22).toString().trim());	
		
		
		System.out.println("strQuery>>>>>>>>>>>>>>>>>>>"+strQuery);
		
		strResult = masterUtil.setData(ds, strQuery, support);
		////////////System.out.println("strResult>>>>>>>>>>>>>>>>>>>"+strResult);
		return strResult;	
				
	}	
	
public String updateUserCourseid(DataSource ds,Vector paramVec){
		
		String strResult = "failure";	
		Support support=new Support();
	    String strQuery="";
		strQuery = "Update loginmaster set  course=?, major_sub=? where userid=?";
		support.setDataVec("string", paramVec.elementAt(0).toString().trim());//0 Courseid
		support.setDataVec("string", paramVec.elementAt(1).toString().trim());//1 Major_sub
		support.setDataVec("int", paramVec.elementAt(2).toString().trim());    //2 user Id
		
			
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;	
				
	}
	/**
	* This function is use for getting user photo path in indv.
	*/
	public String getUserPhotoPath(DataSource ds, int userid) {
	Support support=new Support();
	String strQuery="";
	
	strQuery = "select photopath from loginmaster where userid = " + userid;
	
	support.setFieldVec("string", "photopath");//21
	
	
	return masterUtil.getValue(ds, strQuery, support);
	}
	
	/**
	/**
	 *  This function Update the User Information in the loginmaster table.
	 */
	public String updatePhotoPath(DataSource ds,Vector paramVec){
		
		String strResult = "failure";	
		Support support=new Support();
	    String strQuery="";
		strQuery = "Update loginmaster set photopath=? where userid=?";
		
		support.setDataVec("string", paramVec.elementAt(1).toString().trim());//1 PhotoPath
		support.setDataVec("int", paramVec.elementAt(0).toString().trim());    //2 user Id
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;	
				
	}
	
	
	
	
	/**
	* This function is use for getting user logo path in corpo.
	*/
	public String getUserLogoPath(DataSource ds, int numCompanyId) {
	Support support=new Support();
	String strQuery="";
	
	strQuery = "select logopath from companymaster where company_id = "+numCompanyId;
	
	support.setFieldVec("string", "logopath");//21
	
	
	return masterUtil.getValue(ds, strQuery, support);
	}
	
	/**
	/**
	 *  This function Update the UserLogo Information in the companymaster table.
	 */
	public String updateLogoPath(DataSource ds,Vector paramVec){
		
		String strResult = "failure";	
		Support support=new Support();
	    String strQuery="";
		strQuery = "Update companymaster set logopath=? where company_id=?";
		
		support.setDataVec("string", paramVec.elementAt(1).toString().trim());//1 LogoPath
		support.setDataVec("int", paramVec.elementAt(0).toString().trim());    //2 user Id
		
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;	
				
	}
	
	
	
	
	
	
	
	/**
	 * This function use for getting total no. of Inbox Request.
	 * @return
	 */	
		public int getInboxCount(DataSource ds,Vector paramVec){
			
			int result = 0;
			int UserId = Integer.parseInt(paramVec.elementAt(0).toString().trim());
			int admiFlag = Integer.parseInt(paramVec.elementAt(1).toString().trim());
			String typeofcompany = paramVec.elementAt(2).toString().trim();
			
									
			   String strQuery="";
			
			   if(typeofcompany.equalsIgnoreCase("Enterprise"))
				{	
			 	    	strQuery = "select count(*) count from communication c,complaints cp, loginmaster l, companymaster cm "+
			 			"where  c.indv_rflag= '0' and cp.complaint_id=c.complaintid and c.userid = l.userid and l.companyid"+
			 			" = cm.company_id and cm.typeofcompany='Enterprise' and (c.privateflag = 2 or c.privateflag = 0) and  "+
			 			"cp.login_id = "+UserId;
						
					
				}
			   //////////////////System.out.println("strQuery....count......"+strQuery);
			result = masterUtil.getCount(ds, strQuery);
			////////////////////System.out.println("insite getInboxCount.Inbox..result...."+result);
			return result;
		}
		/**
		 * This function is use for getting list of  Inbox from complaints and communication.
		 * It returns a list within limit which is given by min and max variable.
		 */
			public Vector getInboxList(DataSource ds,Vector paramVec){
				
				////////////////////System.out.println("insite getInboxList.Inbox......");
				
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
			 			"where  c.indv_rflag= '0' and cp.complaint_id=c.complaintid and c.userid = l.userid and l.companyid"+
			 			" = cm.company_id and cm.typeofcompany='Enterprise' and (c.privateflag = 2 or c.privateflag = 0) and "+
			 			"cp.login_id = "+UserId+" order by "+fieldVal+"  limit "+min+", "+max;
						
				
					
			}
				
				//////////////////System.out.println("strQuery............."+strQuery);
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
				////////////////////System.out.println("insite getInboxList.Inbox..resultVec...."+resultVec);
				return resultVec;
			}
			/**
			 * Ajay Kumar Jha
			 * @param ds
			 * @param unnamedDataVec
			 * @param numComplaintId
			 * @return
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
			/**
			 * Ajay Kumar Jha
			 * @param ds
			 * @param strTitle
			 * @param strText
			 * @param numCompId
			 * @param numUid
			 * @return
			 */
			public int getDraftId(DataSource ds, String strTitle, String strText, int numCompId, int numUid)
			{
				int numResult = 0;
				String strQuery = "Select * FROM drafts Where draft_title='"+strTitle+"' And draft_text='"+strText+"' And companyid="+numCompId+" And login_id="+numUid;
				MasterUtility mu = new MasterUtility();
				numResult = mu.getId(ds, strQuery, "draft_id");
				return numResult;
			}
			/**
			 * Ajay Kumar Jha
			 * @param ds
			 * @param unnamedDataVec
			 * @param numDraftId
			 * @return
			 */
			public String setDraftUnnamed(DataSource ds, Vector unnamedDataVec, int numDraftId)
			{
				String strResult="";
				Support support=new Support();
			    String strQuery="";
				
				strQuery = "Insert Into draft_data(draft_id, field1, field2, field3, field4, field5, field6, ";
				strQuery +="field7, field8, field9, field10, field11, field12, field13, field14, field15, field16, ";
				strQuery +="field17, field18, field19, field20, field21, field22, field23, field24, field25, field26, ";
				strQuery +="field27, field28, field29, field30, field31, field32, field33, field34, field35)";
				strQuery +=" Values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				support.setDataVec("int", String.valueOf(numDraftId));
				for(int i=0; i<unnamedDataVec.size(); i++)
				{
					support.setDataVec("string", unnamedDataVec.elementAt(i).toString().trim());
				}
				
				
				strResult = masterUtil.setData(ds, strQuery, support);
				return strResult;
				
			}
			/**
			 * 
			 * @param ds
			 * @param unnamedDataVec
			 * @param numDraftId
			 * @return
			 */
			public String editDraftUnnamed(DataSource ds, Vector unnamedDataVec, int numDraftId)
			{
				String strResult="";
				Support support=new Support();
			    String strQuery="";
				
				strQuery = "Update draft_data set field1=?, field2=?, field3=?, field4=?, field5=?, field6=?, ";
				strQuery +="field7=?, field8=?, field9=?, field10=?, field11=?, field12=?, field13=?, field14=?, field15=?, field16=?, ";
				strQuery +="field17=?, field18=?, field19=?, field20=?, field21=?, field22=?, field23=?, field24=?, field25=?, field26=?, ";
				strQuery +="field27=?, field28=?, field29=?, field30=?, field31=?, field32=?, field33=?, field34=?, field35=? where draft_id=?";
				
				for(int i=0; i<unnamedDataVec.size(); i++)
				{
					support.setDataVec("string", unnamedDataVec.elementAt(i).toString().trim());
				}
				support.setDataVec("int", String.valueOf(numDraftId));
				
				strResult = masterUtil.setData(ds, strQuery, support);
				return strResult;
				
			}
			public String deleteDraftUnnamed(DataSource ds, int numDraftId)
			{
				String strResult="";
				String strQuery="";
				
				strQuery = "delete from draft_data where draft_id="+numDraftId;
				
				strResult = masterUtil.deleteData(ds, strQuery);
				return strResult;
				
			}
			/**
			 * Ajay Kumar Jha
			 * @param ds
			 * @param numComplaintId
			 * @return
			 */
			
			public Vector getComplaintUnnamedData(DataSource ds, int numComplaintId)
			{
				String strQuery = "Select * from complaint_data where complaintid="+numComplaintId;
				Support support = new Support();
				for(int i=1; i<=35; i++)
				{
					support.setFieldVec("String", "field"+i);
				}
				MasterUtility mu = new MasterUtility();
				Vector resultVec = mu.getDetail(ds, strQuery, support);
				return resultVec;
			}
			/**
			 * Ajay Kumar Jha
			 * @param ds
			 * @param numDraftId
			 * @return
			 */
			public Vector getDraftUnnamedData(DataSource ds, int numDraftId)
			{
				String strQuery = "Select * from draft_data where draft_id="+numDraftId;
				Support support = new Support();
				for(int i=1; i<=35; i++)
				{
					support.setFieldVec("String", "field"+i);
				}
				MasterUtility mu = new MasterUtility();
				Vector resultVec = mu.getDetail(ds, strQuery, support);
				return resultVec;
			}
			public String getValueSubCat1(DataSource ds, int numCatId)
			{
				String strResult = "";
				String strQuery = "Select * from subcategory where subcat_id="+numCatId;
				Support support = new Support();
				support.setFieldVec("String", "subcat_name");
				
				MasterUtility mu = new MasterUtility();
				strResult=mu.getValue(ds, strQuery, support);
								
				return strResult;
			}
			public String getValueSubCat2(DataSource ds, int numCatId)
			{
				String strResult = "";
				String strQuery = "Select * from subcategory1 where subcat1_id="+numCatId;
				Support support = new Support();
				support.setFieldVec("String", "subcat1_name");
				
				MasterUtility mu = new MasterUtility();
				strResult=mu.getValue(ds, strQuery, support);
								
				return strResult;
			}
			public String getValueSubCat3(DataSource ds, int numCatId)
			{
				String strResult = "";
				String strQuery = "Select * from subcategory2 where subcat2_id="+numCatId;
				Support support = new Support();
				support.setFieldVec("String", "subcat2_name");
				
				MasterUtility mu = new MasterUtility();
				strResult=mu.getValue(ds, strQuery, support);
								
				return strResult;
			}
			public String getValueSubCat4(DataSource ds, int numCatId)
			{
				String strResult = "";
				String strQuery = "Select * from subcategory3 where subcat3_id="+numCatId;
				Support support = new Support();
				support.setFieldVec("String", "subcat3_name");
				
				MasterUtility mu = new MasterUtility();
				strResult=mu.getValue(ds, strQuery, support);
								
				return strResult;
			}
			public String getValueSubCat5(DataSource ds, int numCatId)
			{
				String strResult = "";
				String strQuery = "Select * from subcategory4 where subcat4_id="+numCatId;
				Support support = new Support();
				support.setFieldVec("String", "subcat4_name");
				
				MasterUtility mu = new MasterUtility();
				strResult=mu.getValue(ds, strQuery, support);
								
				return strResult;
			}
			public String getValueSubCat6(DataSource ds, int numCatId)
			{
				String strResult = "";
				String strQuery = "Select * from subcategory5 where subcat5_id="+numCatId;
				Support support = new Support();
				support.setFieldVec("String", "subcat5_name");
				
				MasterUtility mu = new MasterUtility();
				strResult=mu.getValue(ds, strQuery, support);
								
				return strResult;
			}
			public String getValueSubCat7(DataSource ds, int numCatId)
			{
				String strResult = "";
				String strQuery = "Select * from subcategory6 where subcat6_id="+numCatId;
				Support support = new Support();
				support.setFieldVec("String", "subcat6_name");
				
				MasterUtility mu = new MasterUtility();
				strResult=mu.getValue(ds, strQuery, support);
								
				return strResult;
			}
			public String getValueSubCat8(DataSource ds, int numCatId)
			{
				String strResult = "";
				String strQuery = "Select * from subcategory7 where subcat7_id="+numCatId;
				Support support = new Support();
				support.setFieldVec("String", "subcat7_name");
				
				MasterUtility mu = new MasterUtility();
				strResult=mu.getValue(ds, strQuery, support);
								
				return strResult;
			}
			public String getValueSubCat9(DataSource ds, int numCatId)
			{
				String strResult = "";
				String strQuery = "Select * from subcategory8 where subcat8_id="+numCatId;
				Support support = new Support();
				support.setFieldVec("String", "subcat8_name");
				
				MasterUtility mu = new MasterUtility();
				strResult=mu.getValue(ds, strQuery, support);
								
				return strResult;
			}
			public String getValueSubCat10(DataSource ds, int numCatId)
			{
				String strResult = "";
				String strQuery = "Select * from subcategory9 where subcat9_id="+numCatId;
				Support support = new Support();
				support.setFieldVec("String", "subcat9_name");
				
				MasterUtility mu = new MasterUtility();
				strResult=mu.getValue(ds, strQuery, support);
								
				return strResult;
			}
			
			public int getStrCategoryName(DataSource ds,int cat_id)
			{
				int numResult = 0;
				String strQuery="";
				strQuery= "select cat_enabled from category where cat_id="+cat_id;	
				numResult = masterUtil.getId(ds, strQuery, "cat_enabled");
								
				return numResult;
				
			}
			
			public int getBrandUserId(DataSource ds, int companyid, Vector dataVec)
			{
				int result = 0;		
				
			    String strQuery="Select login_id from logincategories where cat_id="+dataVec.elementAt(0).toString().trim();
				strQuery += " And subcat_id="+dataVec.elementAt(1).toString().trim();
				strQuery += " And subcat1_id="+dataVec.elementAt(2).toString().trim();
				strQuery += " And subcat2_id="+dataVec.elementAt(3).toString().trim();
				strQuery += " And subcat3_id="+dataVec.elementAt(4).toString().trim();
				strQuery += " And subcat4_id="+dataVec.elementAt(5).toString().trim();
				strQuery += " And subcat5_id="+dataVec.elementAt(6).toString().trim();
				strQuery += " And subcat6_id="+dataVec.elementAt(7).toString().trim();
				strQuery += " And subcat7_id="+dataVec.elementAt(8).toString().trim();
				strQuery += " And subcat8_id="+dataVec.elementAt(9).toString().trim();
				strQuery += " And subcat9_id="+dataVec.elementAt(10).toString().trim();
				strQuery += " And company_id="+companyid;
				//////////////////System.out.println("strQuery in getBrandUserId "+strQuery);
				
				result = masterUtil.getId(ds, strQuery, "login_id");	
				
				return result;
				
			}
			
			/**
			 * **************************************************************************************************
		     *	update indv_rflag in communication table and return result status result string.
			 *********************************************************************************************************
			 */	
		public String updateInbox(DataSource ds,Vector dataVec){
				
				Support support=new Support();
				String strQuery="";
			    String strResult = "";
			   
				
				strQuery = "Update communication set indv_rflag = 1 where respid in ("+dataVec.elementAt(0).toString().trim()+")";
				//////////////System.out.println("dc strQuery >>>>>"+strQuery);
						
				strResult = masterUtil.setData(ds, strQuery, support);
				return strResult;
				
				
			}
		
		/**
		 *  This function is used for all count in indv.
		 */
		public Vector complaintCount(DataSource ds,Vector dataVec){
			
			Vector resultVec = new Vector();
			
			Vector dataVec1 = new Vector();
			Vector dataVec2 = new Vector();
			
			dataVec1.add(dataVec.elementAt(0).toString());
			dataVec1.add(dataVec.elementAt(1).toString());
			
			dataVec2.add(dataVec.elementAt(0).toString());
			dataVec2.add("1");
			dataVec2.add("Enterprise");
			
			
			
			int count1 = getComplaintCount(ds, dataVec1);			
			int count2 = getInboxCount(ds, dataVec2);			
			int count3 = getDraftsCount(ds, dataVec1);
			
			
			resultVec.add(count1);
			resultVec.add(count2);	
			resultVec.add(count3);	
			
			return resultVec;
		}
		
///////////////////////////////Function for student packeg start from here/////////////////////////////////
		  
////////for menter list///////////////////	
		
		
		
		
		
		public   Vector getMentordata(DataSource ds,Vector inputvec)
		{
			int uid=Integer.parseInt(inputvec.elementAt(0).toString().trim());
			String fieldVal="creation_date";
			String Val="Mrequest";
			int min=Integer.parseInt(inputvec.elementAt(2).toString().trim());
			int max=Integer.parseInt(inputvec.elementAt(3).toString().trim());
			Vector resultVec = new Vector();
			Support support=new Support();
			
		    String strQuery="";
			
			strQuery= "select  * from complaints where login_id ="+uid+" and q_type ='"+Val+"' order by "+fieldVal+"  limit "+min+", "+max;
			
			//////////////System.out.println("insite getInboxList.strQuery..strQuery...."+strQuery);
			
			/**
			 * set all the fileds type and name for loginmaster table by using
			 * Support class object.
			 */
			support.setFieldVec("int", "complaint_id");//0
			support.setFieldVec("int", "advtL_id");//1
			support.setFieldVec("int", "advt_id");//3
			support.setFieldVec("String", "creation_date");//3
			support.setFieldVec("int", "fcom_id");//4
			
			
			resultVec = masterUtil.getList(ds, strQuery, support);
			
			
			
			//////////////System.out.println("insite getInboxList.Inbox..resultVec...."+resultVec);
			return resultVec;
			
		}
		
		
		public   Vector getMentordata1(DataSource ds,Vector inputvec)
		{
			int uid=Integer.parseInt(inputvec.elementAt(0).toString().trim());
			int muid=Integer.parseInt(inputvec.elementAt(1).toString().trim());
			int cuid=Integer.parseInt(inputvec.elementAt(2).toString().trim());
			Vector resultVec = new Vector();
			Support support=new Support();
			
		    String strQuery="";
			
			strQuery= "select  * from commentsoncomplaints where mentored_id ="+uid+" AND userid ="+muid+" AND comapanyid ="+cuid ;
			

			
			/**
			 * set all the fileds type and name for loginmaster table by using
			 * Support class object.
			 */
			support.setFieldVec("int", "commentid");//0
			support.setFieldVec("string", "commenttext");//1
			support.setFieldVec("string", "commentdate");//2
			support.setFieldVec("int", "complaintid");//3
			support.setFieldVec("int", "userid");//4
			support.setFieldVec("int", "comapanyid");//5
			support.setFieldVec("int", "mentored_id");//6
			support.setFieldVec("string", "commentTime");//7
			
			//////////////System.out.println("....support............."+support);	
			//////////////System.out.println("....resultVec............."+resultVec);	
			resultVec = masterUtil.getList(ds, strQuery, support);
			////////////////////System.out.println("insite getInboxList.Inbox..resultVec...."+resultVec);
			return resultVec;
			
		}
		
		
		
		public   Vector getProcessedList(DataSource ds, String comList)
		{
			
			Vector resultVec = new Vector();
			Support support=new Support();
			
			
		    String strQuery="";
			
			strQuery= "select  * from communication where complaintid="+comList;
			
			
			
			/**
			 * set all the fileds type and name for loginmaster table by using
			 * Support class object.
			 */
			support.setFieldVec("int", "respid");//0
			support.setFieldVec("string", "responsetext");//1
			support.setFieldVec("string", "responsedate");//2
			support.setFieldVec("int", "complaintid");//3
			support.setFieldVec("int", "userid");//4
			support.setFieldVec("int", "privateflag");//5
			
			
		
			resultVec = masterUtil.getList(ds, strQuery, support);
			//////////////System.out.println("....resultVec............."+resultVec);	
			return resultVec;
			
		}
		/**
		 * Function for Mentor list for Student
		 * Support class object.
		 */
		public   Vector getNewMentorList(DataSource ds, String comList)
		{
			
			Vector resultVec = new Vector();
			Support support=new Support();
			
			
		    String strQuery="";
			
			strQuery= "select  * from communication where complaintid="+comList+" and (a_type= 'Accept' or a_type= 'Reject')";
			
			
			
			/**
			 * set all the fileds type and name for loginmaster table by using
			 * Support class object.
			 */
			support.setFieldVec("int", "respid");//0
			support.setFieldVec("string", "responsetext");//1
			support.setFieldVec("string", "responsedate");//2
			support.setFieldVec("int", "complaintid");//3
			support.setFieldVec("int", "userid");//4
			support.setFieldVec("String", "a_type");//5
			
			
		
			resultVec = masterUtil.getList(ds, strQuery, support);
			//////////////System.out.println("....resultVec............."+resultVec);	
			return resultVec;
			
		}
		public   Vector getMentorAccept(DataSource ds, String comList)
		{
			
			Vector resultVec = new Vector();
			Support support=new Support();
			
			
		    String strQuery="";
			
			strQuery= "select  * from communication where complaintid="+comList+" and (a_type= 'Accept')";
			
			/**
			 * set all the fileds type and name for loginmaster table by using
			 * Support class object.
			 */
			support.setFieldVec("int", "respid");//0
			support.setFieldVec("string", "responsetext");//1
			support.setFieldVec("string", "responsedate");//2
			support.setFieldVec("int", "complaintid");//3
			support.setFieldVec("int", "userid");//4
			support.setFieldVec("String", "a_type");//5
			
			
		
			resultVec = masterUtil.getList(ds, strQuery, support);
			//////////////System.out.println("....resultVec............."+resultVec);	
			return resultVec;
			
		}
		
		/**
		 * Function for Comment list for mentorning process
		 * Support class object.
		 */
		public   Vector getCommentList(DataSource ds, String comList)
		{
			
			Vector resultVec = new Vector();
			Support support=new Support();
			
				
			/**
			 * set all the fileds type and name for loginmaster table by using
			 * Support class object.
			 */
			String strQuery="";
			strQuery = "select * from commentsoncomplaints where complaintid = '"+ comList+"' and publish_flag=1 order by commentdate, commentTime";
		
			
			support.setFieldVec("string", "commenttext");
			support.setFieldVec("string", "commentdate");
			support.setFieldVec("int", "complaintid");
			support.setFieldVec("int", "userid");
			support.setFieldVec("string", "commentTime");
			support.setFieldVec("int", "comapanyid");
			support.setFieldVec("int", "publish_flag");
			support.setFieldVec("int", "suspend_flag");
			
			
			resultVec = masterUtil.getList(ds, strQuery, support);
			
			
			return resultVec;
			
		}
		
		
		/**
		* This function use for getting total no. of Bill Entries for a particuler user (consumer).
		* @return
		*/	
		public int eBillcount1(DataSource ds,Vector ParamVec){
					int result = 0;
					int residentid = Integer.parseInt(ParamVec.elementAt(0).toString().trim());
					int facilityid = Integer.parseInt(ParamVec.elementAt(1).toString().trim());
					int status = Integer.parseInt(ParamVec.elementAt(2).toString().trim());
					int type = Integer.parseInt(ParamVec.elementAt(3).toString().trim());
					int Cmonth = Integer.parseInt(ParamVec.elementAt(4).toString().trim());
					int Cyear = Integer.parseInt(ParamVec.elementAt(5).toString().trim());
				
					Support support=new Support();		
					String strQuery="";
					////////////////System.out.println("qtypeqtypeqtypeqtype"+qtype);
					strQuery= "Select count(*) count from consumerbilldetail c, service s where c.serviceid = s.serviceid and c.consumerid="+residentid+" and c.month="+Cmonth+" and c.year="+Cyear+" and s.is_mendetory="+type;
					System.out.println("Query is"+strQuery);
											
					result = masterUtil.getCount(ds, strQuery);
					return result;
				}
		
		/**
		* This function use for getting total no. of mentor(complaints) by a particuler user (consumer).
		* @return
		*/	
		public int getMentorCount(DataSource ds,int uid){
					int result = 0;
					
					String strQuery="";
					String val="Mrequest";
					strQuery= "select count(*)count from complaints where login_id ="+uid+" and q_type='"+val+"'";
					//////////////System.out.println("....strQuery............."+strQuery);	
					result = masterUtil.getCount(ds, strQuery);
					return result;
				}
		
		
		/**
		* This function use for getting total no. of mentor(complaints) by a particuler user (consumer).
		* @return
		*/	
		public int getMentorTotalCount(DataSource ds,int uid){
		int result = 0;
		
		String strQuery="";
		String val="Mrequest";
		strQuery= "select count(distinct(complaintid)) count from communication comm, complaints comp where comp.login_id ="+uid+" and comp.q_type='"+val+"' and comm.complaintid=comp.complaint_id and comm.privateflag=1 ";
		//////System.out.println("....strQuery............."+strQuery);	
		result = masterUtil.getCount(ds, strQuery);
		return result;
	}
		
		
		/**
		* This function use for getting total no. of mentor(complaints) by a particuler user (consumer).
		* @return
		*/	
		public Vector getOtherCollege(DataSource ds,int cid){
			
			Support support=new Support();
			String strQuery="";
			//////////////System.out.println("cid in indvgetOtherCollege "+cid);		
			strQuery = "select cat_name from othercollegeid where other_cid = " + cid;
			//////////////System.out.println("strQuery in indvgetOtherCollege "+strQuery);	
			
			support.setFieldVec("string", "cat_name");
					return masterUtil.getDetail(ds, strQuery, support);
					
				}
		
		/**
		* This function is use for getting list of  Queris Result  .
		* It returns a list within limit which is given by min and max variable.
		*/
		public Vector getQuerisResultList(DataSource ds, Vector dataVec){
					
					Vector resultVec = new Vector();
					int vectorSize = dataVec.size();
					
					int min = Integer.parseInt(dataVec.elementAt(0).toString().trim());
					int max = Integer.parseInt(dataVec.elementAt(1).toString().trim());
					
					
					Support support=new Support();
					String strQuery="";
					/*
					 * in case of search complaint by some given parameter's.
					 */
					
					if(vectorSize==4)												
						strQuery = "SELECT * FROM loginmaster where logindeletion =0 and "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"' limit "+min+", "+max;
					
					else if(vectorSize==6)											
						strQuery = "SELECT * FROM loginmaster where logindeletion =0 and  "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"' and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'  limit "+min+", "+max;
					
					else if(vectorSize==8)											
						strQuery = "SELECT * FROM loginmaster where logindeletion =0 and  "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"' and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"' and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"'  limit "+min+", "+max;
					
					else if(vectorSize==10)											
						strQuery = "SELECT * FROM loginmaster where logindeletion =0 and  "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"' and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"' and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"' and "+ dataVec.elementAt(8).toString().trim()+" '"+dataVec.elementAt(9).toString().trim()+"'  limit "+min+", "+max;
					
					else if(vectorSize==12)											
						strQuery = "SELECT * FROM loginmaster where logindeletion =0 and  "+dataVec.elementAt(2).toString().trim()+"  '"+dataVec.elementAt(3).toString().trim()+"' and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"' and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"' and "+ dataVec.elementAt(8).toString().trim()+" '"+dataVec.elementAt(9).toString().trim()+"' and "+ dataVec.elementAt(10).toString().trim()+" '"+dataVec.elementAt(11).toString().trim()+"'  limit "+min+", "+max;
					
					else if(vectorSize==14)											
						strQuery = "SELECT * FROM loginmaster where logindeletion =0 and  "+dataVec.elementAt(2).toString().trim()+"  '"+dataVec.elementAt(3).toString().trim()+"' and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"' and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"' and "+ dataVec.elementAt(8).toString().trim()+" '"+dataVec.elementAt(9).toString().trim()+"' and "+ dataVec.elementAt(10).toString().trim()+" '"+dataVec.elementAt(11).toString().trim()+"' and "+ dataVec.elementAt(12).toString().trim()+" '"+dataVec.elementAt(13).toString().trim()+"'  limit "+min+", "+max;
					
					
					//////////////System.out.println("strQuery>>>>>loginmaster>>>>>>>>>"+strQuery);
					/**
					 * set all the fileds type and name for blogs table by using
					 * Support class object.
					 */
					support.setFieldVec("int", "userid");
					support.setFieldVec("string", "first_name");
					support.setFieldVec("string", "last_name");								
					support.setFieldVec("string", "email");	
					support.setFieldVec("string", "phone");
					support.setFieldVec("string", "mobile");
					support.setFieldVec("string", "address");
					support.setFieldVec("string", "country");
					support.setFieldVec("string", "state");
					support.setFieldVec("string", "city");
					support.setFieldVec("string", "zip");
					support.setFieldVec("string", "age");
					support.setFieldVec("string", "Birthday");
					support.setFieldVec("string", "education");
					support.setFieldVec("string", "maritalstatus");
					support.setFieldVec("string", "profession");
					support.setFieldVec("string", "gender");
					
					
					
					resultVec = masterUtil.getList(ds, strQuery, support);
					return resultVec;
				}
		
		public int getQuerisResultCount(DataSource ds, Vector dataVec){
			
			
			int vectorSize = dataVec.size();
			int result = 0;
			
			String strQuery="";
			/*
			 * in case of search complaint by some given parameter's.
			 */
			
			if(vectorSize==4)												
				strQuery = "SELECT count(*)count FROM loginmaster where logindeletion =0 and "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'";
			
			else if(vectorSize==6)											
				strQuery = "SELECT count(*)count FROM loginmaster where logindeletion =0  and "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'";
			
			else if(vectorSize==8)											
				strQuery = "SELECT count(*)count FROM loginmaster where logindeletion =0  and "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"'";
			
			else if(vectorSize==10)											
				strQuery = "SELECT count(*)count FROM loginmaster where logindeletion =0  and "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"'and "+ dataVec.elementAt(8).toString().trim()+" '"+dataVec.elementAt(9).toString().trim()+"'";
			
			else if(vectorSize==12)											
				strQuery = "SELECT count(*)count FROM loginmaster where logindeletion =0  and "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"'and "+ dataVec.elementAt(8).toString().trim()+" '"+dataVec.elementAt(9).toString().trim()+"'and "+ dataVec.elementAt(10).toString().trim()+" '"+dataVec.elementAt(11).toString().trim()+"'";
			
			else if(vectorSize==14)											
				strQuery = "SELECT count(*)count FROM loginmaster where logindeletion =0  and "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"'and "+ dataVec.elementAt(8).toString().trim()+" '"+dataVec.elementAt(9).toString().trim()+"'and "+ dataVec.elementAt(10).toString().trim()+" '"+dataVec.elementAt(11).toString().trim()+"' and "+ dataVec.elementAt(12).toString().trim()+" '"+dataVec.elementAt(13).toString().trim()+"'";
			
			
			//////////////System.out.println("strQuery>>>>>count>>>>>>>>>"+strQuery);
			result = masterUtil.getCount(ds, strQuery);
			return result;
		}
		
		/**
		* This function is use for getting list of  Queris Result  .
		* It returns a list within limit which is given by min and max variable.
		*/
		public Vector getQuerisResultComList(DataSource ds,Vector dataVec){
					
					Vector resultVec = new Vector();
					int vectorSize = dataVec.size();
					
					int min = Integer.parseInt(dataVec.elementAt(0).toString().trim());
					int max = Integer.parseInt(dataVec.elementAt(1).toString().trim());
					
					
					Support support=new Support();
					String strQuery="";
					/*
					 * in case of search complaint by some given parameter's.
					 */
					
					if(vectorSize==4)												
						strQuery = "SELECT * FROM companymaster where "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"' limit "+min+", "+max;
					
					else if(vectorSize==6)											
						strQuery = "SELECT * FROM companymaster where  "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'  limit "+min+", "+max;
					
					else if(vectorSize==8)											
						strQuery = "SELECT * FROM companymaster where  "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"'  limit "+min+", "+max;
					
					else if(vectorSize==10)											
						strQuery = "SELECT * FROM companymaster where  "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"'and "+ dataVec.elementAt(8).toString().trim()+" '"+dataVec.elementAt(9).toString().trim()+"'  limit "+min+", "+max;
					
					else if(vectorSize==12)											
						strQuery = "SELECT * FROM companymaster where  "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"'and "+ dataVec.elementAt(8).toString().trim()+" '"+dataVec.elementAt(9).toString().trim()+"'and "+ dataVec.elementAt(10).toString().trim()+" '"+dataVec.elementAt(11).toString().trim()+"'  limit "+min+", "+max;
					
					else if(vectorSize==14)											
						strQuery = "SELECT * FROM companymaster where  "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+"  '"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"'and "+ dataVec.elementAt(8).toString().trim()+" '"+dataVec.elementAt(9).toString().trim()+"'and "+ dataVec.elementAt(10).toString().trim()+" '"+dataVec.elementAt(11).toString().trim()+"' and "+ dataVec.elementAt(12).toString().trim()+" '"+dataVec.elementAt(13).toString().trim()+"'  limit "+min+", "+max;
					
					
					//////////////System.out.println("strQuery>>>>>companymaster>>>>>>>>>"+strQuery);
					/**
					 * set all the fileds type and name for blogs table by using
					 * Support class object.
					 */
					support.setFieldVec("int", "company_id");
					support.setFieldVec("string", "company_sname");
					support.setFieldVec("string", "company_name");								
					support.setFieldVec("string", "company_address1");	
					support.setFieldVec("string", "company_address2");
					support.setFieldVec("string", "company_city");
					support.setFieldVec("string", "company_state");
					support.setFieldVec("string", "company_country");
					support.setFieldVec("string", "company_zip");
					support.setFieldVec("string", "company_email");
					support.setFieldVec("string", "company_phone");
					support.setFieldVec("string", "category");
					support.setFieldVec("string", "typeofcompany");
					support.setFieldVec("string", "registered");
					
					
					
					
					resultVec = masterUtil.getList(ds, strQuery, support);
					return resultVec;
				}
		
			public int getQuerisResultComCount(DataSource ds, Vector dataVec){
			
			
			int vectorSize = dataVec.size();
			int result = 0;
			
			String strQuery="";
			/*
			 * in case of search complaint by some given parameter's.
			 */
			
			if(vectorSize==4)												
				strQuery = "SELECT count(*)count FROM companymaster where "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'";
			
			else if(vectorSize==6)											
				strQuery = "SELECT count(*)count FROM companymaster where  "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'";
			
			else if(vectorSize==8)											
				strQuery = "SELECT count(*)count FROM companymaster where  "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"'";
			
			else if(vectorSize==10)											
				strQuery = "SELECT count(*)count FROM companymaster where  "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"'and "+ dataVec.elementAt(8).toString().trim()+" '"+dataVec.elementAt(9).toString().trim()+"'";
			
			else if(vectorSize==12)											
				strQuery = "SELECT count(*)count FROM companymaster where  "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"'and "+ dataVec.elementAt(8).toString().trim()+" '"+dataVec.elementAt(9).toString().trim()+"'and "+ dataVec.elementAt(10).toString().trim()+" '"+dataVec.elementAt(11).toString().trim()+"'";
			
			else if(vectorSize==14)											
				strQuery = "SELECT count(*)count FROM companymaster where  "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"'and "+ dataVec.elementAt(8).toString().trim()+" '"+dataVec.elementAt(9).toString().trim()+"'and "+ dataVec.elementAt(10).toString().trim()+" '"+dataVec.elementAt(11).toString().trim()+"' and "+ dataVec.elementAt(12).toString().trim()+" '"+dataVec.elementAt(13).toString().trim()+"'";
			
			
			//////////////System.out.println("strQuery>>>>>count>>>>>>>>>"+strQuery);
			
			result = masterUtil.getCount(ds, strQuery);
			return result;
		}
		
			/**
			* This function is use for getting list of  complaint's by a particuler user(consumer) .
			* It returns a list within limit which is given by min and max variable.
			*/
			public Vector getQueriesList(DataSource ds,Vector dataVec){
						
			Vector resultVec = new Vector();
			int vectorSize = dataVec.size();
			int loginid = Integer.parseInt(dataVec.elementAt(0).toString().trim());
			int companyid = Integer.parseInt(dataVec.elementAt(1).toString().trim());
			int min = Integer.parseInt(dataVec.elementAt(2).toString().trim());
			int max = Integer.parseInt(dataVec.elementAt(3).toString().trim());
			String fieldVal = dataVec.elementAt(4).toString().trim();
			Support support=new Support();
			String strQuery="";
			/*
			 * in case of search complaint by some given parameter's.
			 */
			
			if(vectorSize==7)												
				strQuery = "SELECT * FROM complaints where login_id="+loginid+" and "+dataVec.elementAt(5).toString().trim()+" '"+dataVec.elementAt(6).toString().trim()+"' order by "+fieldVal+" limit "+min+", "+max;
			
			else if(vectorSize==9)											
				strQuery = "SELECT * FROM complaints where login_id="+loginid+" and "+dataVec.elementAt(5).toString().trim()+" '"+dataVec.elementAt(6).toString().trim()+"'and "+ dataVec.elementAt(7).toString().trim()+" '"+dataVec.elementAt(8).toString().trim()+"' order by "+fieldVal+" limit "+min+", "+max;
			
			else if(vectorSize==11)											
				strQuery = "SELECT * FROM complaints where login_id="+loginid+" and "+dataVec.elementAt(5).toString().trim()+" '"+dataVec.elementAt(6).toString().trim()+"'and "+ dataVec.elementAt(7).toString().trim()+" '"+dataVec.elementAt(8).toString().trim()+"'and "+ dataVec.elementAt(9).toString().trim()+" '"+dataVec.elementAt(10).toString().trim()+"' order by "+fieldVal+" limit "+min+", "+max;
			
			else if(vectorSize==13)											
				strQuery = "SELECT * FROM complaints where login_id="+loginid+" and "+dataVec.elementAt(5).toString().trim()+" '"+dataVec.elementAt(6).toString().trim()+"'and "+ dataVec.elementAt(7).toString().trim()+" '"+dataVec.elementAt(8).toString().trim()+"'and "+ dataVec.elementAt(9).toString().trim()+" '"+dataVec.elementAt(10).toString().trim()+"'and "+ dataVec.elementAt(11).toString().trim()+" '"+dataVec.elementAt(13).toString().trim()+"' order by "+fieldVal+" limit "+min+", "+max;
			/*
			 * getting list of  complaint's by a particuler user(consumer)
			 * It returns a list within limit which is given by min and max variable.
			 */
			else				
			strQuery= "Select * from complaints where login_id= "+loginid+" and companyid="+companyid+" order by "+fieldVal+" limit "+min+", "+max;
			
			//////////////////System.out.println("strQuery>>>>>>>>>>>>>>"+strQuery);
			/**
			 * set all the fileds type and name for blogs table by using
			 * Support class object.
			 */
			support.setFieldVec("int", "complaint_id");
			support.setFieldVec("string", "fcom_id");
			support.setFieldVec("string", "complaint_title");								
			support.setFieldVec("string", "creation_date");	
			support.setFieldVec("string", "creation_time");
			support.setFieldVec("int", "advtL_id");
			
			
			
			
			resultVec = masterUtil.getList(ds, strQuery, support);
			return resultVec;
		}
			
			/**
			* This function use for getting total no. of blogs(complaints) by a particuler user (consumer).
			* @return
			*/	
		public int getQueriesCount(DataSource ds,Vector dataVec){
					int result = 0;
					int vectorSize = dataVec.size();
					int loginid = Integer.parseInt(dataVec.elementAt(0).toString().trim());
					int companyid = Integer.parseInt(dataVec.elementAt(1).toString().trim());
					
					String strQuery="";
					if(vectorSize==4)									
						strQuery = "SELECT count(*) count from complaints where login_id="+loginid+" and "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'"; 
						
					
					else if(vectorSize==6)								
						strQuery = "SELECT count(*) count from complaints where login_id="+loginid+" and "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'";
						
					
					else if(vectorSize==8)								
						strQuery = "SELECT count(*) count from complaints where login_id="+loginid+" and "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"'";
						
					
					else if(vectorSize==10)								
						strQuery = "SELECT count(*) count from complaints where login_id="+loginid+" and "+dataVec.elementAt(2).toString().trim()+" '"+dataVec.elementAt(3).toString().trim()+"'and "+ dataVec.elementAt(4).toString().trim()+" '"+dataVec.elementAt(5).toString().trim()+"'and "+ dataVec.elementAt(6).toString().trim()+" '"+dataVec.elementAt(7).toString().trim()+"'and "+ dataVec.elementAt(9).toString().trim()+" '"+dataVec.elementAt(10).toString().trim()+"'";
						
					else
					
					strQuery= "Select count(*) count from complaints where login_id= "+loginid+" and companyid="+companyid;	
					
					
					result = masterUtil.getCount(ds, strQuery);
					return result;
				}
		
		
		
		
		/**
		 * This function is use for getting list of  Inbox from complaints and communication.
		 * It returns a list within limit which is given by min and max variable.
		 */
			public Vector getRreplyList(DataSource ds,Vector paramVec){
				
				////////////////////System.out.println("insite getInboxList.Inbox......");
				
				Vector resultVec = new Vector();
				int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
				int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());
				String fieldVal = paramVec.elementAt(2).toString().trim();
				int UserId = Integer.parseInt(paramVec.elementAt(3).toString().trim());
				
				
				
				Support support=new Support();
				String strQuery="";
				
					
				   strQuery = "SELECT Distinct(c.complaintid), cp.complaint_title, cp.creation_date,  cp.fcom_id from communication c , complaints cp where c.complaintid=cp.complaint_id and cp.login_id='"+UserId+" ' and cp.q_type= 'Trequest' order by "+fieldVal+" limit "+min+", "+max;
			 	    	 
					
				/**
				 * set all the fileds type and name for companymaster table by using
				 * Support class object.
				 */
				
				
				support.setFieldVec("string", "complaint_title");								
				support.setFieldVec("string", "complaintid");	
				support.setFieldVec("string", "creation_date");
				support.setFieldVec("string", "fcom_id");
				
				
				//////////////System.out.println("strQuery strQuery.strQuery......"+strQuery);
				
				resultVec = masterUtil.getList(ds, strQuery, support);
				//////////////System.out.println("resultVec......"+resultVec);
				
				return resultVec;
			}
			/**
			 * This function is use for getting list of  Inbox from complaints and communication.
			 * It returns a list within limit which is given by min and max variable.
			 */
				public int getRreplyCount(DataSource ds,Vector paramVec){
					
					//////////////System.out.println("insite getInboxList.Inbox......"+paramVec.elementAt(0).toString().trim());
					
					int result = 0;
					
					int UserId = Integer.parseInt(paramVec.elementAt(0).toString().trim());
					
					   String strQuery="";
					
				 	    	 strQuery = "select  count(*) count from communication c , complaints cp  where c.complaintid = cp.complaint_id and cp.login_id="+UserId;
							
				 	    	//////////////System.out.println("strQuery strQuery.strQuery......"+strQuery);
				
				 	    	 	result = masterUtil.getCount(ds, strQuery);
				 	    	
				 	    	//////////////System.out.println("result strQuery.strQuery......"+result);
					
				 	    	return 	result;
					
				}
				
				public String setTestVec(DataSource ds, Vector datavec)
				{
					String strResult="";
					Support support=new Support();
				    String strQuery="";
				    
				   					
					strQuery = "Insert Into test(test_id,user_id,max_marks,obtained_marks,analysis,test_name, submit_date, publish_flag)";
					strQuery +="Values (?, ?, ?, ?, ?, ?, ?, ?)";

					support.setDataVec("int", datavec.elementAt(0).toString().trim()); 
					support.setDataVec("int", datavec.elementAt(1).toString().trim()); 
					support.setDataVec("String", datavec.elementAt(2).toString().trim()); 
					support.setDataVec("String", datavec.elementAt(3).toString().trim()); 
					support.setDataVec("string", datavec.elementAt(4).toString().trim()); 
					support.setDataVec("string", datavec.elementAt(5).toString().trim()); 
					support.setDataVec("string", datavec.elementAt(6).toString().trim()); 
					support.setDataVec("int", datavec.elementAt(7).toString().trim()); 

			 		strResult = masterUtil.setData(ds, strQuery, support);
			        return strResult;
				}
				
				
					public Vector getTestVecList(DataSource ds,Vector paramVec){
						
						////////////////////System.out.println("insite getInboxList.Inbox......");
						
						Vector resultVec = new Vector();
						int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
						int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());						
						int UserId = Integer.parseInt(paramVec.elementAt(2).toString().trim());
						
						
						
						Support support=new Support();
						String strQuery="";
						
							
						   strQuery = "SELECT * from  test where  user_id="+UserId+" and publish_flag=0 limit "+min+", "+max;
					 	    	 
							
						/**
						 * set all the fileds type and name for companymaster table by using
						 * Support class object.
						 */
						
						
						support.setFieldVec("int", "test_id");								
						support.setFieldVec("string", "max_marks");	
						support.setFieldVec("string", "obtained_marks");
						support.setFieldVec("string", "analysis");
						support.setFieldVec("string", "test_name");
						
						
						//////////////System.out.println("strQuery strQuery.strQuery......"+strQuery);
						
						resultVec = masterUtil.getList(ds, strQuery, support);
						//////////////System.out.println("resultVec......"+resultVec);
						
						return resultVec;
					}
					
					
					public Vector getTestVecdetail(DataSource ds,Vector paramVec){
						
						////////////////////System.out.println("insite getInboxList.Inbox......");
						
						Vector resultVec = new Vector();
						int min = Integer.parseInt(paramVec.elementAt(0).toString().trim());
						int max = Integer.parseInt(paramVec.elementAt(1).toString().trim());						
						int UserId = Integer.parseInt(paramVec.elementAt(2).toString().trim());
						int TestId = Integer.parseInt(paramVec.elementAt(3).toString().trim());
						
						
						
						Support support=new Support();
						String strQuery="";
						
							
						   strQuery = "SELECT * from  test where  user_id="+UserId+" and test_id="+TestId;
					 	    	 
							
						/**
						 * set all the fileds type and name for companymaster table by using
						 * Support class object.
						 */
						
						
						support.setFieldVec("int", "test_id");								
						support.setFieldVec("string", "max_marks");	
						support.setFieldVec("string", "obtained_marks");
						support.setFieldVec("string", "analysis");
						support.setFieldVec("string", "test_name");
						support.setFieldVec("string", "submit_date");
						support.setFieldVec("int", "publish_flag");
						
						
						//////////////System.out.println("strQuery strQuery.strQuery......"+strQuery);
						
						resultVec = masterUtil.getDetail(ds, strQuery, support);
						//////////////System.out.println("resultVec......"+resultVec);
						
						return resultVec;
					}
		public int getTestVecCount(DataSource ds,Vector paramVec){
			
		
			
			int result = 0;
			
			int UserId = Integer.parseInt(paramVec.elementAt(2).toString().trim());
			
			
			   String strQuery="";
			
				
			    
		 	    	 strQuery = "select  count(*) count from  test where  user_id="+UserId;
					
			
		 	    	 	result = masterUtil.getCount(ds, strQuery);
		 	    	
		 	    	//////////////System.out.println("result strQuery.strQuery......"+result);
			
		 	    	return 	result;
			
		}
		

		/**
		 * **********************************************************************************************************
		*This function add all the data of Professional Experience in complaints table .
		*and return the result string Success or failure accordiong to query result.
	*	************************************************************************************************************
	*	*/
		public String insertExperience(DataSource ds,Vector paramVec){
			
			
			Support support=new Support();
			String strQuery="";
			String strResult = "";
			strQuery = "Insert into professional_exp (user_id, title, company_name, industry_cat, "+
			"start_month,start_year,end_month, "+
			"end_year,status) Values(?,?, ?, ?, ?, ?, ?, ?, ?)";
			
			support.setDataVec("string", paramVec.elementAt(0).toString().trim()); //user_id
			support.setDataVec("string", paramVec.elementAt(1).toString().trim()); //title
			support.setDataVec("string", paramVec.elementAt(2).toString().trim()); //company_name
			support.setDataVec("string", paramVec.elementAt(3).toString().trim()); //industry_cat
			support.setDataVec("string", paramVec.elementAt(4).toString().trim()); //start_month
			support.setDataVec("string", paramVec.elementAt(5).toString().trim()); //start_year
			support.setDataVec("string", paramVec.elementAt(6).toString().trim()); //end_month
			support.setDataVec("string", paramVec.elementAt(7).toString().trim()); //end_year
			support.setDataVec("string", paramVec.elementAt(8).toString().trim()); //status
					
			strResult = masterUtil.setData(ds, strQuery, support);
			return strResult;
			
			
		}
		
		/**
		 * **************************************************************************************************
	     *	update  Experience information in proffisionalExp table and return result status result string.
		 *********************************************************************************************************
		 */	
	public String updateExperience(DataSource ds,Vector paramVec){
			////////////System.out.println("paramVecin indvmaster>>>>>>>>>"+paramVec);
			Support support=new Support();
			String strQuery="";
		    String strResult = "";
		  	strQuery = "Update professional_exp set title=?, company_name=?, industry_cat=?, start_month=?, "+
			"start_year=?, end_month=?, end_year=?, status=?, lastmodify_date=? where exp_id=?";
			
			support.setDataVec("string", paramVec.elementAt(1).toString().trim());	//0 title
			support.setDataVec("string", paramVec.elementAt(2).toString().trim());	//1 company_name
			support.setDataVec("string", paramVec.elementAt(3).toString().trim());	//2 industry_cat
			support.setDataVec("string", paramVec.elementAt(4).toString().trim());	//3 start_month
			support.setDataVec("string", paramVec.elementAt(5).toString().trim());	//4 start_year
			support.setDataVec("string", paramVec.elementAt(6).toString().trim());	//5 end_month
			support.setDataVec("string", paramVec.elementAt(7).toString().trim());	//6 end_year
			support.setDataVec("stirng", paramVec.elementAt(8).toString().trim());	//7 status
			support.setDataVec("stirng", paramVec.elementAt(9).toString().trim());	//7 status
			support.setDataVec("int", paramVec.elementAt(10).toString().trim());	//8 exp_id
			
			////////////System.out.println("strQuery indvmaster>>>>>>>>>"+strQuery);
					
			strResult = masterUtil.setData(ds, strQuery, support);
			return strResult;
			
			
		}
				
	/**
	 * This function is use for getting all professional_exp detail 
	 * of a particuler professional_exp from professional_exp table.
	 */
	public Vector getExpDetails(DataSource ds,int numCompId){
		
		Vector resultVec = new Vector();
		
		
		Support support=new Support();
		   String strQuery="";	
		
			strQuery = "SELECT * FROM professional_exp where  user_id= "+numCompId;				
		
		
			////////////System.out.println("result strQuery.strQuery......"+strQuery);	
		/**
		 * set all the fileds type and name for blogs table by using
		 * Support class object.
		 */
		
		support.setFieldVec("int", "exp_id");
		support.setFieldVec("int", "user_id");				//0
		support.setFieldVec("string", "title");		//1
		support.setFieldVec("string", "company_name");		//2
		support.setFieldVec("string", "industry_cat");		//3		
		support.setFieldVec("int", "start_month");				//4
		support.setFieldVec("string", "start_year");		//5
		support.setFieldVec("string", "end_month");		//6
		support.setFieldVec("string", "end_year");	//7		
		support.setFieldVec("string", "status");	//8
	
		
		resultVec = masterUtil.getList(ds, strQuery, support);
		//////////System.out.println("resultVec strQuery.strQuery......"+resultVec);	
		return resultVec;
	}
	
	
	
	public int getInfoFlag(DataSource ds, String userid)
	{
		int result = 0;
		
		String strQuery="";		
		strQuery= "Select info_flag  from loginmaster where userid = '"+userid+"'";
		
		result = masterUtil.getId(ds, strQuery, "info_flag");
		
		
		return result;			
				
	}
	
	
	public String updatePublishFlag(DataSource ds,Vector paramVec){
		////////////System.out.println("paramVecin indvmaster>>>>>>>>>"+paramVec);
		Support support=new Support();
		String strQuery="";
	    String strResult = "";
	  	strQuery = "Update test set publish_flag=? where test_id=?";
		
		support.setDataVec("int", paramVec.elementAt(1).toString().trim());	//0 title
		support.setDataVec("int", paramVec.elementAt(0).toString().trim());	//1 company_name
		
		
		////////////System.out.println("strQuery indvmaster>>>>>>>>>"+strQuery);
				
		strResult = masterUtil.setData(ds, strQuery, support);
		return strResult;
		
		
	}
	
	public Vector getcatList(DataSource ds,String c_id)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("Select");
		resultVec.add(tempVec);
		 
		strQuery = "select * from courses where cou_enabled=1 and cou_id in ("+c_id+") order by cou_name";
		support.setFieldVec("int", "cou_id");
		support.setFieldVec("String", "cou_name");
		
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
		Vector<String>tempVec1 = new Vector<String>();
		tempVec1.add("-1");
		tempVec1.add("Others");
		resultVec.add(tempVec1);
		
		////System.out.println("resultVec designation resultVec;;;;;;;;;;;"+strQuery);
		return resultVec;
	}			
	public Vector getStudentList(DataSource ds,String c_id,String course_id)
	{
		Support support=new Support();
		String strQuery="";
		Vector<Vector>resultVec = new Vector<Vector>();
		Vector<String>tempVec = new Vector<String>();
		Vector<Vector> tempResultVec= new Vector<Vector>();
		tempVec.add("0");
		tempVec.add("");
		tempVec.add("Select");
		resultVec.add(tempVec);
		 
		strQuery = "select * from loginmaster where logindeletion=0 and college_id ="+c_id+" and course ="+course_id+" order by first_name";
		support.setFieldVec("int", "userid");
		support.setFieldVec("String", "first_name");
		support.setFieldVec("String", "last_name");
		
		tempResultVec = (Vector)masterUtil.getList(ds, strQuery, support);
		
		for(int i=0;i<tempResultVec.size();i++)
		{
			resultVec.add((Vector)tempResultVec.elementAt(i));
		}
		
		Vector<String>tempVec1 = new Vector<String>();
		tempVec1.add("-1");
		tempVec1.add(" ");
		tempVec1.add("Others");
		resultVec.add(tempVec1);
		
		//System.out.println("resultVec designation resultVec;;;;;;;;;;;"+strQuery);
		return resultVec;
	}		
	
	
	public String getCatForcomp(DataSource ds,int compnyid)
	{
		String strResult="";
		Support support=new Support();
	    String strQuery="";
		strQuery= "select category from companymaster where company_id="+compnyid;
			
		support.setFieldVec("string", "category");	
		
		strResult = masterUtil.getValue(ds, strQuery, support);
		
		return strResult;
		
	}
///////////////////////////////Function for student packeg end at here/////////////////////////////////		
			
			
}