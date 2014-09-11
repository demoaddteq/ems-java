
/*
 * 
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.student.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.sql.DataSource;
import com.mm.struts.student.form.SubmitRequestForm;
import com.mm.core.master.*;
import com.mm.core.resource.Constant;
import com.mm.core.resource.MailText;
import com.mm.core.resource.Resource;


import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * MyEclipse Struts
 * Creation date: 07-12-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */


public class SubmitRequestAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	
	
	
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,	HttpServletRequest request, HttpServletResponse response) {
			
		String fianlStatus = "failure";
		IndvMaster indvMaster = new IndvMaster();
		EntpMaster entpMaster = new EntpMaster();
		RootMaster rootMaster = new RootMaster();
		HttpSession session = request.getSession();
		DataSource dataSource = getDataSource(request,"student");
		String fcomId = "";
		if(((String)session.getAttribute("flagforr")).equalsIgnoreCase("1"))
		{
			
			
			
			
			Vector dataVec = getData(request);
			/**
			 * Added By Ajay Kumar Jha
			 */		
			
			////System.out.println("unnamedDataVec Size  ");
			////System.out.println("fianlStatusfianlStatus 1111fianlStatus >>>>"+fianlStatus);
			////System.out.println("unnamedDataVec ");
			/**
			 * end by Ajay Kumar Jha
			 */
			java.util.Date dt = new java.util.Date();
			SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
			String completeRemDate = sform.format(dt);
			StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
		    String creationDate = sttotal.nextToken();
		    String creationTime = sttotal.nextToken();
		    String timePart = sttotal.nextToken();
		    
		    String publishDate = "0000-00-00";
		    String publishTime = "00:00:00";
		    String publishFlag = "0";
		    String publishAt = "1@Week";
		    
		  /*  boolean pubFlag = indvMaster.publishCheck();
		    if(pubFlag == true)
		    {
		    	publishDate = creationDate;
		    	publishTime = creationTime;
		    	publishFlag = "1";
		    	publishAt = "0@Week";
		    }*/
		    
		    publishDate = creationDate;
	    	publishTime = creationTime;
	    	publishFlag = "1";
	    	publishAt = "0@Week";
		    
			String uId = (String)session.getAttribute("uId");
			String compId = (String)session.getAttribute("compId");
			String categoryId = getCatData(dataSource,dataVec);
			String otherBrand = "";
			
			String advtId = "0";
			String advtLid = dataVec.elementAt(18).toString();
			////System.out.println("dataVec.....cat id..."+dataVec.elementAt(1).toString());
			////System.out.println("dataVec...brand id....."+dataVec.elementAt(3).toString());
			
			
			
			if(dataVec.elementAt(1).toString().equals("-1") || dataVec.elementAt(3).toString().equals("-1") )
			{
				advtId = String.valueOf(indvMaster.getCompanyId(dataSource,"Advertiser","other"));
				otherBrand = dataVec.elementAt(4).toString();
			}
			else
			{
				advtId = dataVec.elementAt(3).toString().trim();
				
			}
			//get brand admin person id 
			Vector<String> tempDataVec = new Vector<String>();
			tempDataVec.add(categoryId);
			
			//////System.out.println("tempDataVec "+tempDataVec);
			// advtLid = String.valueOf(indvMaster.getBrandUserId(dataSource, Integer.parseInt(advtId), tempDataVec));
			//////System.out.println("advtLid "+advtLid);
			if(Integer.parseInt(advtLid.trim())<=0)
			{
				
				advtLid = String.valueOf(entpMaster.getCompnyAdminUserId(dataSource,Integer.parseInt(advtId)));
				if(advtLid=="0"){
					advtLid =dataVec.elementAt(18).toString().trim();
				}
			}
			
			String dealerId = "0";
			if(dataVec.elementAt(5).toString().equalsIgnoreCase("checked"))
				dealerId = insertDealerInfo(dataSource,dataVec,advtId);
		    String cu_id = String.valueOf(indvMaster.getCoreUserId(dataSource,Integer.parseInt(categoryId)));
		    String fcu_id = cu_id;
		    String com_type=(String)session.getAttribute("cptype");
		    ////System.out.println("com_typecom_typecom_typecom_type in submitaction"+com_type);
			String entpId = String.valueOf(indvMaster.getCompanyId(dataSource,"Enterprise","com_type"));
			if(cu_id.equals("0"))
			{
				cu_id = String.valueOf(entpMaster.getCompnyAdminUserId(dataSource,Integer.parseInt(entpId)));
				fcu_id = "0";
			}
			String tagId = String.valueOf(indvMaster.getMinTagID(dataSource));
			//set all complaint data for inserting complaint in data Base.
			session.removeAttribute("cptype");
			Vector<String> complaintVec =  new Vector<String>();
			complaintVec.add(dataVec.elementAt(0).toString());	//blog Title
			complaintVec.add(dataVec.elementAt(15).toString());	//blog Text
			complaintVec.add(dataVec.elementAt(16).toString());	//Relevent Text
			complaintVec.add(categoryId);						//Category
			complaintVec.add(creationDate);						//Creation Date
			complaintVec.add(publishDate);						//Publish Date
			complaintVec.add(creationTime);						//Creation Time
			complaintVec.add(publishTime);						//Publish Time
			complaintVec.add("0000-00-00");						//Last Modify Date
			complaintVec.add("00:00:00");						//Last Modify Time
			complaintVec.add(uId);								//User (Complainent id) LoginId()uId
			complaintVec.add(compId);							//User Company Id
			complaintVec.add(timePart);							//Time Part
			complaintVec.add("0");								//View Count
			complaintVec.add(entpId);							//Entp Id(Complaint handler company Id)
			complaintVec.add(fcu_id);							//cu_Id(Complaint handler User Id)
			complaintVec.add(advtId);							//advtId(Company Id of brand) 
			complaintVec.add(advtLid);							//advtlId(Company user Id of brand) 
			complaintVec.add(dealerId);							//Dealer Id
			complaintVec.add("2");							    //Tag Id
			complaintVec.add("0000-00-00");						//Resolved Date
			complaintVec.add("0000-00-00");						//Closed Date
			complaintVec.add("1");								//Brand Flag
			complaintVec.add(publishFlag);						//Publish Flag
			complaintVec.add(publishAt);						//Publish On 
			complaintVec.add(otherBrand);						//otherBranddataVec.elementAt(17).toString()
			complaintVec.add(dataVec.elementAt(17).toString());	//Query type
			////System.out.println("complaintVec.............SubmitComplaintAction........... "+complaintVec);
			////System.out.println("complaintVec.............SubmitComplaintAction........... "+complaintVec.elementAt(16));
			////System.out.println("complaintVec.............SubmitComplaintAction........... "+complaintVec.elementAt(17));
			////System.out.println("complaintVec.............SubmitComplaintAction........... "+complaintVec.elementAt(18));
			
			String strComplResult = indvMaster.insertNewComplaints(dataSource, complaintVec);
			////System.out.println("strComplResult.............SubmitComplaintAction........... "+strComplResult);
			
			Vector dataVec1 = new Vector();		
			dataVec1.add(uId);
			dataVec1.add(compId);					
			Vector countVec = indvMaster.complaintCount(getDataSource(request, "student"), dataVec1);		
			session.setAttribute("countVec", countVec);						
			////System.out.println("countVec.............SubmitComplaintAction........... "+countVec);
			fianlStatus=strComplResult;
//			get complaint Id of this new complaint and set fcom Id for this complaint
			if(strComplResult.equalsIgnoreCase("success")){
			fcomId = indvMaster.getFinalComplainId(dataSource, Integer.parseInt(uId), dataVec.elementAt(0).toString(), creationTime);
			String fcomId1 = indvMaster.getComplainId(dataSource, Integer.parseInt(uId), dataVec.elementAt(0).toString(), creationTime);
			String fcomarr[] = fcomId1.split("/");
			Vector<String> tempCompVec = new Vector<String>();
			tempCompVec.add(fcomId);//final compaint id
			tempCompVec.add(fcomarr[0]);//complaint id
			indvMaster.setFinalId(dataSource, tempCompVec);	  
			
			
			
			
			
			//get mail text for sending mail
			Vector<String> paramVec = new Vector<String>();
			String coreAdminid = String.valueOf(entpMaster.getCompnyAdminUserId(dataSource,Integer.parseInt(entpId)));
			paramVec.add(uId);
			paramVec.add(advtLid);
			/*if(coreAdminid.equals(cu_id))
			{
				paramVec.add(cu_id);
			}
			else
			{
				paramVec.add(cu_id);
				paramVec.add(coreAdminid);
			}
			*/
			System.out.println("paramVec>>for ..........>emailId>>>>>>>"+paramVec);
			Vector emailVec = indvMaster.getEmailList(dataSource, paramVec);
			System.out.println("emailVec>>>>>>>>>>"+emailVec);
			
			Vector<String> tempMailParam = new Vector<String>();
			tempMailParam.add("Student");//sender company type
			tempMailParam.add("Corporates");//receipient company type
			tempMailParam.add("Request");//mail description
			//get mail text alert fot sending mail to core user and admin core user
			String mailTextLAlert = rootMaster.getMailText(dataSource, tempMailParam);
			Vector consumerVec = (Vector)emailVec.elementAt(0);
			String consumerName =consumerVec.elementAt(0).toString()+" "+consumerVec.elementAt(1).toString();
			String consumermail = consumerVec.elementAt(2).toString().trim();
			String subject = "A New Request ("+ fcomId + ") by "+ consumerName;
			////////System.out.println("emailVec>>>>>>>>>>>>>>"+emailVec);
			for(int i=1; i<emailVec.size();i++)
			{
				MailText mt = new MailText();
				Resource resr = new Resource();
				Vector coreMailVec = (Vector)emailVec.elementAt(i);
				String strMailText = mt.getETextForTrainingRequest(coreMailVec,fcomId,mailTextLAlert,consumerName, fcomarr[0]);
				////System.out.println("strMailText>>>>>>>>>>"+strMailText);
				
				resr.sendMail(strMailText, Constant.Email_Sender,((Vector)emailVec.elementAt(i)).elementAt(2).toString() , subject);
				////////System.out.println("str Email status fo core"+strEmailStatus);
			}
			//send mail to consumer email address
			//get mail text from the mailText file
			MailText mailtext = new MailText();
			Resource resource = new Resource();
			String strConsumerMailtex = mailtext.getRequestTextForStudent(consumerName,fcomId);
			String strFinalStatus = resource.sendMail(strConsumerMailtex, Constant.Email_Sender, consumermail, subject);
			System.out.println("str Email Status after consumer"+strFinalStatus);
			if(strFinalStatus.equalsIgnoreCase("success"))
			{
				ActionErrors msg = new ActionErrors();
				msg.clear();
				msg.add("delete", new ActionError("std.Request.submitSuccess"));
				
				saveErrors(request, msg);
				fianlStatus = "success";
			}
			else
			{
				ActionErrors msg = new ActionErrors();
				msg.clear();
				msg.add("delete", new ActionError("std.Request.submitFailure"));
				
				saveErrors(request, msg);
				fianlStatus = "failure";
			}
				
			
			//send mail to core user and complaintent user  with fcom id
			
			
			
			
			
			
			
			
			
			}
			if(fianlStatus.equalsIgnoreCase("failure"))
			{
				SubmitRequestForm writeComplaintForm = new SubmitRequestForm();
				writeComplaintForm.setBlogTitle(dataVec.elementAt(0).toString());
				writeComplaintForm.setCategory(dataVec.elementAt(1).toString());
				writeComplaintForm.setOtherCategory(dataVec.elementAt(2).toString());
				writeComplaintForm.setBrand(dataVec.elementAt(3).toString());
				writeComplaintForm.setOtherBrand(dataVec.elementAt(4).toString());
				writeComplaintForm.setCheckDealer(dataVec.elementAt(5).toString());
				writeComplaintForm.setDealerName(dataVec.elementAt(6).toString());
				writeComplaintForm.setAddress(dataVec.elementAt(7).toString());
				writeComplaintForm.setCountry(dataVec.elementAt(8).toString());
				writeComplaintForm.setOtherCountry(dataVec.elementAt(9).toString());
				writeComplaintForm.setState(dataVec.elementAt(10).toString());
				writeComplaintForm.setOtherState(dataVec.elementAt(11).toString());
				writeComplaintForm.setCity(dataVec.elementAt(12).toString());
				writeComplaintForm.setOtherCity(dataVec.elementAt(13).toString());
				writeComplaintForm.setPincode(dataVec.elementAt(14).toString());
				writeComplaintForm.setBlogtext(dataVec.elementAt(15).toString());
				writeComplaintForm.setReleventtext(dataVec.elementAt(16).toString());
				writeComplaintForm.setQtype(dataVec.elementAt(17).toString());
				writeComplaintForm.setCons(dataVec.elementAt(18).toString());
				
				request.setAttribute("stdWriteComplaintForm", writeComplaintForm);
				
			}
			else
			{
				fianlStatus="compSuccess";
				
				session.removeAttribute("cid");
				session.removeAttribute("sid");
				session.removeAttribute("pid");
			}
			////System.out.println("return mapping.findForward(fianlStatus);>>>>>>>>"+fianlStatus);
			////System.out.println("return mapping.findForward(fianlStatus);>>>>>>>>"+fianlStatus);
			
			if(fianlStatus.equalsIgnoreCase("compSuccess")){
				session.setAttribute("flagforr","0");
				
			}
		return mapping.findForward(fianlStatus);
		}
		else{
			
			return mapping.findForward("compSuccess");
			}
	}
	
	
	public Vector getData(HttpServletRequest request)
	{
		Vector<String> dataVec = new Vector<String>();
		dataVec.add((String)request.getParameter("blogTitle"));			//0
		dataVec.add((String)request.getParameter("category"));			//1
		dataVec.add((String)request.getParameter("otherCategory"));		//2
		dataVec.add((String)request.getParameter("Brand"));				//3
		dataVec.add((String)request.getParameter("otherBrand"));		//4
		dataVec.add((String)request.getParameter("checkDealer"));		//5
		dataVec.add((String)request.getParameter("dealerName"));		//6
		dataVec.add((String)request.getParameter("address"));			//7
		dataVec.add((String)request.getParameter("country"));			//8
		dataVec.add((String)request.getParameter("otherCountry"));		//9
		dataVec.add((String)request.getParameter("state"));				//10
		dataVec.add((String)request.getParameter("otherState"));		//11	
		dataVec.add((String)request.getParameter("city"));				//12
		dataVec.add((String)request.getParameter("otherCity"));			//13
		dataVec.add((String)request.getParameter("pincode"));			//14
		dataVec.add((String)request.getParameter("blogText"));			//15
		dataVec.add((String)request.getParameter("relventText"));		//16
		dataVec.add((String)request.getParameter("qtype"));		        //17
		dataVec.add((String)request.getParameter("cons"));		        //17
		////System.out.println("dataVec.......in side function......dataVec........... "+dataVec);	
		return dataVec;
	}
	public Vector getExtraData(HttpServletRequest request)
	{
		Vector<String> dataVec = new Vector<String>();
		dataVec.add((String)request.getParameter("categoryName"));
		dataVec.add((String)request.getParameter("brandName"));
		dataVec.add((String)request.getParameter("countryName"));
		dataVec.add((String)request.getParameter("stateName"));
		dataVec.add((String)request.getParameter("cityName"));
		return dataVec;
	}
	public String getCatData(DataSource dataSource,Vector dataVec)
	{
		
		String userCategory=dataVec.elementAt(1).toString();
		RootMaster rootMaster = new RootMaster();
		if(userCategory.equals("-1"))
		{
			//SET category,brand and contact person IN DATA BASE .
			userCategory=rootMaster.insertCategory(dataSource,dataVec.elementAt(2).toString());//return category id
			
		}				
		return userCategory;
	}
	public Vector getCountryData(DataSource dataSource,Vector dataVec)
	{
		Vector<String> resultVec = new Vector<String>();
		String userCountry=dataVec.elementAt(8).toString();
		String userState=dataVec.elementAt(10).toString();
		String userCity=dataVec.elementAt(12).toString();
		RootMaster rootMaster = new RootMaster();
		if(dataVec.elementAt(5).toString().equalsIgnoreCase("checked"))
		{
			if(userCountry.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				userCountry=rootMaster.insertCountry(dataSource,"+0",dataVec.elementAt(9).toString());//return country id
				userState=rootMaster.insertState(dataSource,dataVec.elementAt(11).toString(),userCountry);
				userCity=rootMaster.insertCity(dataSource,dataVec.elementAt(13).toString(),userState,userCountry);
			}
			else if(userState.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				userState=rootMaster.insertState(dataSource,dataVec.elementAt(11).toString(),userCountry);
				userCity=rootMaster.insertCity(dataSource,dataVec.elementAt(13).toString(),userState,userCountry);
			}
			else if(userCity.equals("-1"))
			{
				//set sity in data base and add that is in company master.
				userCity=rootMaster.insertCity(dataSource,dataVec.elementAt(13).toString(),userState,userCountry);
			}
			else
			{
				//nothing 
			}
		}
		resultVec.add(userCountry);
		resultVec.add(userState);
		resultVec.add(userCity);
		
		return resultVec;
	}
	public String insertDealerInfo(DataSource dataSource,Vector dataVec,String advtId)
	{
		String strResult = "0";
		
		IndvMaster indvMaster = new IndvMaster();
		Vector<String> dealerVec = new Vector<String>();
		Vector countryVec = getCountryData(dataSource,dataVec);
		
		String countryarr [] = countryVec.elementAt(0).toString().trim().split("~");
		
		dealerVec.add(dataVec.elementAt(6).toString().trim());
		dealerVec.add("");
		dealerVec.add(advtId);
		dealerVec.add(dataVec.elementAt(7).toString().trim());
		dealerVec.add(countryarr[0]);
		dealerVec.add(countryVec.elementAt(1).toString().trim());
		dealerVec.add(countryVec.elementAt(2).toString().trim());
		dealerVec.add(countryVec.elementAt(2).toString().trim());
		dealerVec.add("");
		dealerVec.add("");
		dealerVec.add("");
		dealerVec.add(dataVec.elementAt(14).toString().trim());
		
		String result = indvMaster.insertDealerInfo(dataSource,dealerVec);
		if(result.equalsIgnoreCase("success"))
		{
			strResult = indvMaster.getMaxDealerId(dataSource, dataVec.elementAt(6).toString().trim());
			
		}
		
		
		
		return strResult;
	}
}