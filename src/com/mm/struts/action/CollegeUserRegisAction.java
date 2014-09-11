package com.mm.struts.action;

import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import com.mm.core.master.AdvtMaster;
import com.mm.core.master.RootMaster;
import com.mm.core.resource.Constant;
import com.mm.core.resource.MailText;
import com.mm.core.resource.Resource;
import com.mm.struts.form.CollegeRegisVo;
import com.mm.struts.form.CollegeUserRegisForm;

public class CollegeUserRegisAction extends Action {
	
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
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(((String)session.getAttribute("flagforAdvt")).equalsIgnoreCase("1")){
		
		RootMaster rootMaster= new RootMaster();
		CollegeRegisVo collegeRegisVo;
		String companyType ="Advertiser";
		String strResult="";
		String creationDate ="";
		String expiryDate ="";
		String creationTime = "";
	    //String timePart = "";
		Vector<String> companyVec= new Vector<String>();
		DataSource dataSource = getDataSource(request,"main");
		
		
		if(session.getAttribute("companyData")!=null)
		{
			collegeRegisVo = (CollegeRegisVo)session.getAttribute("companyData");
			
			companyType = (String)session.getAttribute("type1");
			companyType="Advertiser";
		
		}
		else
		{
			return mapping.findForward("sessionFail");
		}
		CollegeUserRegisForm collegeUserRegisForm = (CollegeUserRegisForm) form;// TODO Auto-generated method stub
//		check userid for registration of a user
		
		String strVerifyResult=rootMaster.checkLoginId(dataSource,collegeUserRegisForm.getUserID(),collegeUserRegisForm.getUserEMail());
		if(strVerifyResult.equals("loginId"))
		{
			ActionErrors errors = new ActionErrors();
			errors.add("duplicate", new ActionError("errors.user.duplicate"));
			if(!errors.isEmpty())
			{
				saveErrors(request, errors);
			}
			return mapping.findForward("failure");
			
		}
		
		//end user check
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
	    creationDate = sttotal.nextToken();
	    creationTime = sttotal.nextToken();
	    sttotal.nextToken();
	    expiryDate=rootMaster.returnFinalDate("Months",3);
	    String tempCountry[] = collegeRegisVo.getCompCountry().split("~");
		String compCountry=tempCountry[0];
		String compState = collegeRegisVo.getCompanyState();
		String compCity = collegeRegisVo.getCompanyCity();
		
		////System.out.println("compCountry................."+compCountry);
		////System.out.println("compState................"+compState);
		////System.out.println("compCity................"+compCity);
		
		if(compCountry.equals("-1"))
		{
			//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
			compCountry=rootMaster.insertCountry(dataSource,collegeRegisVo.getTxtCPPrefix(),collegeRegisVo.getOthercompCountry().toString().trim());//return country id
			compState=rootMaster.insertState(dataSource,collegeRegisVo.getOthercompanyState().toString().trim(),compCountry);
			compCity=rootMaster.insertCity(dataSource,collegeRegisVo.getOthercompanyCity().toString().trim(),compState,compCountry);
			////System.out.println("compCountry.........if........"+compCountry);
		}
		else if(compState.equals("-1"))
		{
			//set state .city in data base and add these field in company master table
			compState=rootMaster.insertState(dataSource,collegeRegisVo.getOthercompanyState().toString(),compCountry);
			compCity=rootMaster.insertCity(dataSource,collegeRegisVo.getOthercompanyCity().trim(),compState,compCountry);
			////System.out.println("compState.........if........"+compState);
			////System.out.println("compCity.........if........"+compCity);
		}
		else if(compCity.equals("-1"))
		{
			//set sity in data base and add that is in company master.
			compCity=rootMaster.insertCity(dataSource,collegeRegisVo.getOthercompanyCity().toString().trim(),compState,compCountry);
			////System.out.println("compCity.........if........"+compCity);
		}
		else
		{
			//nothing 
		}
		String tempcat="0, ";
		String companyTemplate[] = collegeRegisVo.getCompanyTemplate();
		//////System.out.println("firstCreationVo.getCompanyTemplate()................"+collegeRegisVo.getCompanyTemplate());
		for(int i=0; i<companyTemplate.length;i++)
		{
			String tempCatval = companyTemplate[i];
			if(tempCatval.equals("-1"))
			{
				//set category in category table and add that id in company master table
				tempCatval=rootMaster.insertCollegeCategory(dataSource,collegeRegisVo.getOthercompanyTemplate());
			}
			if(i<companyTemplate.length)
				  tempcat = tempcat+tempCatval+", ";
			/*
			if(i<companyTemplate.length-1)
			  tempcat = tempcat+tempCatval+", ";
			else
				tempcat = tempcat+tempCatval;
				*/
		}
		
		String  teampMcat = "";
		  if(collegeRegisVo.getMcata().equalsIgnoreCase("1")){
		  teampMcat = 1+",";
		  }
		  else {
			  teampMcat = teampMcat+"0,";
		  }
		  if(collegeRegisVo.getMcatb().equalsIgnoreCase("1")){
		  teampMcat = teampMcat+2+",";
		  }
		  else {
			  teampMcat = teampMcat+"0,";
		  }
		  if(collegeRegisVo.getMcatc().equalsIgnoreCase("1")){
		  teampMcat = teampMcat+3+",";
		  }
		  else {
			  teampMcat = teampMcat+"0,";
		  }
		  if(collegeRegisVo.getMcatd().equalsIgnoreCase("1")){
		  teampMcat = teampMcat+4+",";
		  }
		  else {
			  teampMcat = teampMcat+"0,";
		  }
		  
		  teampMcat = teampMcat+"0";
		
		 
			
		  
		  
		String phoneNo = collegeRegisVo.getTxtCPPrefix()+"~"+collegeRegisVo.getTxtCAreacode()+"~"+collegeRegisVo.getTxtCPhone();
		companyVec.add(collegeRegisVo.getCompanySName());
		companyVec.add(collegeRegisVo.getCompanyName());
		companyVec.add(collegeRegisVo.getCompanyAddress1());
		companyVec.add(collegeRegisVo.getCompanyAddress2());
		companyVec.add(compCity);
		companyVec.add(compState);
		companyVec.add(compCountry);
		companyVec.add(collegeRegisVo.getCompanyPostalCode());
		companyVec.add(collegeRegisVo.getCompanyEMail());
		companyVec.add(phoneNo);
		companyVec.add(tempcat);
		companyVec.add("10");
		companyVec.add("0");
		companyVec.add("10");
		companyVec.add(expiryDate);
		companyVec.add("0");
		companyVec.add("1");
		companyVec.add(companyType);
		companyVec.add(collegeRegisVo.getCompanySName());
		companyVec.add("2");
		companyVec.add("1");
		companyVec.add(creationDate);
		companyVec.add(expiryDate);
		companyVec.add("0");
		companyVec.add("0");
		companyVec.add(teampMcat);
		
		//////System.out.println("companyVec................."+companyVec);
		
		strResult=rootMaster.insertCorpoDetail(dataSource,companyVec);
		if(strResult.equalsIgnoreCase("success"))
		{
			
			//add user rights by default 1 for first user
			
			
			Vector companyDetailVec=rootMaster.getCompanyDetail(dataSource,collegeRegisVo.getCompanySName());
			Vector<String>userVec= new Vector<String>();
			String tempuserCountry[] = collegeUserRegisForm.getCompCountry().split("~");
			String userCountry=tempuserCountry[0];
			String userState = collegeUserRegisForm.getCompanyState();
			String userCity = collegeUserRegisForm.getCompanyCity();
			
			////System.out.println("userCountry................."+userCountry);
			////System.out.println("userState................"+userState);
			////System.out.println("userCity................"+userCity);
			
			if(userCountry.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				userCountry=rootMaster.insertCountry(dataSource,collegeUserRegisForm.getTxtPPrefix(),collegeUserRegisForm.getOthercompCountry().toString().trim());//return country id
				userState=rootMaster.insertState(dataSource,collegeUserRegisForm.getOthercompanyState().toString().trim(),userCountry);
				userCity=rootMaster.insertCity(dataSource,collegeUserRegisForm.getOthercompanyCity().toString().trim(),userState,userCountry);
				////System.out.println("userCountry................."+userCountry);
				////System.out.println("userState................"+userState);
				////System.out.println("userCity................"+userCity);
			}
			else if(userState.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				userState=rootMaster.insertState(dataSource,collegeUserRegisForm.getOthercompanyState().toString().trim(),userCountry);
				userCity=rootMaster.insertCity(dataSource,collegeUserRegisForm.getOthercompanyCity().toString().trim(),userState,userCountry);
				
				////System.out.println("userState................"+userState);
				////System.out.println("userCity................"+userCity);
			}
			else if(userCity.equals("-1"))
			{
				//set sity in data base and add that is in company master.
				userCity=rootMaster.insertCity(dataSource,collegeUserRegisForm.getOthercompanyCity().toString().trim(),userState,userCountry);
				
				////System.out.println("userCity................"+userCity);
			}
			else
			{
				//nothing 
			}
			
			String userphoneNo ="";
			if(collegeUserRegisForm.getTxtAreacode().length()>0 || collegeUserRegisForm.getTxtPhone().length()>0)
			{
				userphoneNo=collegeUserRegisForm.getTxtPPrefix()+"~"+collegeUserRegisForm.getTxtAreacode()+"~"+collegeUserRegisForm.getTxtPhone();
			}
			
			 String depart = collegeUserRegisForm.getDepartment();
				String desig = collegeUserRegisForm.getDesignation();
				String type="college";
				if(depart.equals("-1"))
				{
					//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
					depart=rootMaster.insertDepartment(dataSource,collegeUserRegisForm.getOtherdepartment().toString().trim(),type);//return country id
					desig=rootMaster.insertDesignation(dataSource,collegeUserRegisForm.getOtherdesignation().toString().trim(),type,depart);
					
				}
				else if(desig.equals("-1"))
				{
					//set state .city in data base and add these field in company master table
					desig=rootMaster.insertDesignation(dataSource,collegeUserRegisForm.getOtherdesignation().toString().trim(),type,depart);
				}
			String userMobile = collegeUserRegisForm.getTxtPrefix()+"~"+collegeUserRegisForm.getTxtMobile();
			userVec.add(companyDetailVec.elementAt(0).toString());
			userVec.add("1");
			userVec.add(collegeUserRegisForm.getUserID());
			userVec.add(collegeUserRegisForm.getUserPassword());
			userVec.add(collegeUserRegisForm.getUserFirstName());
			userVec.add(collegeUserRegisForm.getUserLastName());
			userVec.add(collegeUserRegisForm.getUserEMail());
			userVec.add(userphoneNo);
			userVec.add(userMobile);
			userVec.add(collegeUserRegisForm.getUserAddress());
			userVec.add(userCountry);
			userVec.add(userState);
			userVec.add(userCity);
			userVec.add(collegeUserRegisForm.getUserPostalCode());
			userVec.add("nophoto.jpg");
			userVec.add("IN");
			userVec.add(creationDate);
			userVec.add(creationTime);
			userVec.add("0000-00-00");
			userVec.add("00:00:00");
			userVec.add("0");
			userVec.add("20");
			userVec.add("0");
			userVec.add("0");
			userVec.add("0");
			userVec.add("0");
			userVec.add("0");
			userVec.add("0");
			userVec.add("0");
			userVec.add("0");
			userVec.add("0");
			userVec.add("0");
			userVec.add("Male");
			userVec.add(companyType);
			userVec.add("N/A");
			userVec.add("N/A");
			userVec.add("N/A");
			userVec.add("N/A");
			userVec.add(teampMcat);
			userVec.add("1,2");
			userVec.add(depart);	      								 // 12	
			userVec.add(desig);	
			//////System.out.println("userVec................."+userVec);
			
			strResult=rootMaster.insertUserInfoCorpo(dataSource,userVec);
			
			
			//mail transfer code here.
			Resource resr = new Resource();
			MailText mt = new MailText();
			String strMailText = "";
			if(companyType.equalsIgnoreCase("Advertiser"))
			{
				strMailText = mt.getETextForCollegeReg(collegeUserRegisForm.getUserID(), collegeUserRegisForm.getUserPassword(), collegeUserRegisForm.getUserFirstName(), collegeUserRegisForm.getUserLastName());
			}
			else
			{
				strMailText = mt.getETextForEntpReg(collegeUserRegisForm.getUserID(), collegeUserRegisForm.getUserPassword(), collegeUserRegisForm.getUserFirstName(), collegeUserRegisForm.getUserLastName());
			}
			String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender, collegeUserRegisForm.getUserEMail(), Constant.Email_Title_For_UCreation);
			//System.out.println("str Email Status"+strEmailStatus);
			String strPassVal = "";
			for(int i=0; i<collegeUserRegisForm.getUserPassword().length();i++)
			{
				strPassVal = (strPassVal.equalsIgnoreCase("")) ? "*" : strPassVal+"*";
			}
			
			//UserBean ub = new UserBean();
			//ub.setCname(firstCreationVo.getCompanyName());
			//ub.setUserName(firstusercreationForm.getUserID());
			//ub.setPass(strPassVal);
			//ub.setContactUs(Constant.Email_Sender);
			//session.setAttribute("eusers", ub);
			
			

			Vector paramVec=new Vector();
			
			
			
			String strValue4="";
			
			strValue4+="<TABLE align=\"center\">";
			strValue4+="<tr align=\"center\">";
			strValue4+="<td>&nbsp;";
			strValue4+="</td>";
			strValue4+="<td colspan=\"2\" align=\"center\"> ";
			strValue4+="<div align=\"center\" > <img  src=\"../images/noprofile.jpg\" width=\"100%\" height=\"100%\">";
			strValue4+="</div>";
			strValue4+="</td>";
			strValue4+="<td>&nbsp;";
			strValue4+="</td>";
			strValue4+="</tr>";
			strValue4+="</TABLE>";
		String strValue3="";
			
		strValue3+="<TABLE align=\"center\">";
		strValue3+="<tr align=\"center\">";
		strValue3+="<td>&nbsp;";
		strValue3+="</td>";
		strValue3+="<td colspan=\"2\" align=\"center\">";
		strValue3+="<div align=\"center\" > <img  src=\"../images/noprofile.jpg\" width=\"100%\" height=\"100%\">";
		strValue3+="</div>";
		strValue3+="</td>";
		strValue3+="<td>&nbsp;";
		strValue3+="</td>";
		strValue3+="</tr>";
		strValue3+="</TABLE>";

			String strValue2="";
			
			strValue2+="<TABLE align=\"center\">";
			strValue2+="<tr align=\"center\">";
			strValue2+="<td>&nbsp;";
			strValue2+="</td>";
			strValue2+="<td colspan=\"2\" align=\"center\">";
			strValue2+="<div align=\"center\" > <img  src=\"../images/noprofile.jpg\" width=\"100%\" height=\"100%\">";
			strValue2+="</div>";
			strValue2+="</td>";
			strValue2+="<td>&nbsp;";
			strValue2+="</td>";
			strValue2+="</tr>";
			strValue2+="</TABLE>";

			String strValue1="";
			
			strValue1+="<TABLE align=\"center\">";
			strValue1+="<tr align=\"center\">";
			strValue1+="<td>&nbsp;";
			strValue1+="</td>";
			strValue1+="<td colspan=\"2\" align=\"center\">";
			strValue1+="<div align=\"center\" > <img  src=\"../images/noprofile.jpg\" width=\"100%\" height=\"100%\">";
			strValue1+="</div>";
			strValue1+="</td>";
			strValue1+="<td>&nbsp;";
			strValue1+="</td>";
			strValue1+="</tr>";
			strValue1+="</TABLE>";
			
			String strValue5="";
			
			strValue5+="<TABLE align=\"center\">";
			strValue5+="<tr align=\"center\">";
			strValue5+="<td>&nbsp;";
			strValue5+="</td>";
			strValue5+="<td colspan=\"2\" align=\"center\">";
			strValue5+="<div align=\"center\" > <img  src=\"../images/noprofile.jpg\" width=\"100%\" height=\"100%\">";
			strValue5+="</div>";
			strValue5+="</td>";
			strValue5+="<td>&nbsp;";
			strValue5+="</td>";
			strValue5+="</tr>";
			strValue5+="</TABLE>";
			
			 paramVec.add( companyDetailVec.elementAt(0).toString());
			 paramVec.add("About Us");
			 paramVec.add("Infrastructure");
			 paramVec.add("Faculty");
			 paramVec.add("Education");
			 paramVec.add("Events");
			 paramVec.add(strValue1);
			 paramVec.add(strValue2);
			 paramVec.add(strValue3);
			 paramVec.add(strValue4);
			 paramVec.add(strValue5);
			 paramVec.add(creationDate);
			 
			 AdvtMaster advtMaster=new AdvtMaster();
			 String strComplResult = advtMaster.insertTab(dataSource, paramVec);
			
			
			
			
			Vector<String> userDetailVec = new Vector<String>();
			userDetailVec.add(collegeRegisVo.getCompanyName());
			userDetailVec.add(collegeUserRegisForm.getUserID());
			userDetailVec.add(strPassVal);
			userDetailVec.add(Constant.Email_Sender);
			userDetailVec.add(collegeUserRegisForm.getUserEMail());
			userDetailVec.add(collegeUserRegisForm.getUserFirstName());
			userDetailVec.add(collegeUserRegisForm.getUserLastName());

			rootMaster.CreateBlogsFile(dataSource);
			
			session.setAttribute("eusers", userDetailVec);
		}
		session.removeAttribute("companyData");
		session.setAttribute("flagforAdvt", "0");
		
		return mapping.findForward(strResult);
		}
		else{
			return mapping.findForward("sessionFail");
			
		}
	}

	
}
