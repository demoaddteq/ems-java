package com.mm.struts.action;


/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.struts.form.FirstusercreationForm;
import javax.servlet.http.HttpSession;
import com.mm.struts.form.FirstCreationVo;
import javax.sql.DataSource;
import java.util.Vector;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import com.mm.core.master.*;
import com.mm.core.resource.Constant;
import com.mm.core.resource.MailText;
import com.mm.core.resource.Resource;


/** 
 * MyEclipse Struts
 * Creation date: 06-27-2007 
 * 
 * XDoclet definition:
 * @struts.action path="/firstCreation" name="firstCreationForm" input="/firstcreation.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/login.jsp"
 * @struts.action-forward name="failure" path="/firstcreation.jsp"
 */
public class FirstusercreationAction extends Action {
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
		
		if(((String)session.getAttribute("flagforCorpo")).equalsIgnoreCase("1")){
			
		
		
		RootMaster rootMaster= new RootMaster();
		IndvMaster indvMaster= new IndvMaster();
		FirstCreationVo firstCreationVo;
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
			firstCreationVo = (FirstCreationVo)session.getAttribute("companyData");
			
			companyType = (String)session.getAttribute("type1");
			////////////System.out.println("companyType......FirstusercreationAction..........."+companyType);
			
			if(companyType.equals("entp"))
			{
				companyType="Enterprise";
			}
			else
			{
				companyType="Corporates";
			}
		}
		else
		{
			return mapping.findForward("sessionFail");
		}
		FirstusercreationForm firstusercreationForm = (FirstusercreationForm) form;// TODO Auto-generated method stub
//		check userid for registration of a user
		
		String strVerifyResult=rootMaster.checkLoginId(dataSource,firstusercreationForm.getUserID(),firstusercreationForm.getUserEMail());
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
	    String tempCountry[] = firstCreationVo.getCompCountry().split("~");
		String compCountry=tempCountry[0];
		String compState = firstCreationVo.getCompanyState();
		String compCity = firstCreationVo.getCompanyCity();
		//////////System.out.println("compCountry................."+compCountry);
		//////////System.out.println("compState................."+compState);
		//////////System.out.println("compCity................."+compCity);
		
		if(compCountry.equals("-1"))
		{
			//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
			compCountry=rootMaster.insertCountry(dataSource,firstCreationVo.getTxtCPPrefix(),firstCreationVo.getOthercompCountry().toString().trim());//return country id
			compState=rootMaster.insertState(dataSource,firstCreationVo.getOthercompanyState().toString().trim(),compCountry);
			compCity=rootMaster.insertCity(dataSource,firstCreationVo.getOthercompanyCity().toString().trim(),compState,compCountry);
			////////////System.out.println("compCountry.........if........"+compCountry);
		}
		else if(compState.equals("-1"))
		{
			//set state .city in data base and add these field in company master table
			compState=rootMaster.insertState(dataSource,firstCreationVo.getOthercompanyState().toString().trim(),compCountry);
			compCity=rootMaster.insertCity(dataSource,firstCreationVo.getOthercompanyCity().toString().trim(),compState,compCountry);
			//////////System.out.println("compState.........if........"+compState);
			//////////System.out.println("compCity.........if........"+compCity);
		}
		else if(compCity.equals("-1"))
		{
			//set sity in data base and add that is in company master.
			compCity=rootMaster.insertCity(dataSource,firstCreationVo.getOthercompanyCity().toString().trim(),compState,compCountry);
			//////////System.out.println("compCity.........if........"+compCity);
		}
		else
		{
			//nothing 
		}
		String tempcat="0, ";
		String companyTemplate[] = firstCreationVo.getCompanyTemplate();
		////////////System.out.println("firstCreationVo.getCompanyTemplate()................"+firstCreationVo.getCompanyTemplate());
		for(int i=0; i<companyTemplate.length;i++)
		{
			String tempCatval = companyTemplate[i];
			if(tempCatval.equals("-1"))
			{
				//set category in category table and add that id in company master table
				tempCatval=rootMaster.insertCategory(dataSource,firstCreationVo.getOthercompanyTemplate());
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
		
		
		////////System.out.println("mcata................."+firstCreationVo.getMcata());
		////////System.out.println("mcatb................."+firstCreationVo.getMcatb());
		////////System.out.println("mcatc................."+firstCreationVo.getMcatc());
		////////System.out.println("mcatd................."+firstCreationVo.getMcatd());
		
		String  teampMcat = "";
		  if(firstCreationVo.getMcata().equalsIgnoreCase("1")){
		  teampMcat = 1+",";
		  }
		  else {
			  teampMcat = teampMcat+"0,";
		  }
		  if(firstCreationVo.getMcatb().equalsIgnoreCase("1")){
		  teampMcat = teampMcat+2+",";
		  }
		  else {
			  teampMcat = teampMcat+"0,";
		  }
		  if(firstCreationVo.getMcatc().equalsIgnoreCase("1")){
		  teampMcat = teampMcat+3+",";
		  }
		  else {
			  teampMcat = teampMcat+"0,";
		  }
		  if(firstCreationVo.getMcatd().equalsIgnoreCase("1")){
		  teampMcat = teampMcat+4+",";
		  }
		  else {
			  teampMcat = teampMcat+"0,";
		  }
		  
		  teampMcat = teampMcat+"0";
		  ////////////System.out.println("teampMcat................."+teampMcat);
		  
		 
		
		
		
		String phoneNo = firstCreationVo.getTxtCPPrefix()+"~"+firstCreationVo.getTxtCAreacode()+"~"+firstCreationVo.getTxtCPhone();
		companyVec.add(firstCreationVo.getCompanySName());
		companyVec.add(firstCreationVo.getCompanyName());
		companyVec.add(firstCreationVo.getCompanyAddress1());
		companyVec.add(firstCreationVo.getCompanyAddress2());
		companyVec.add(compCity);
		companyVec.add(compState);
		companyVec.add(compCountry);
		companyVec.add(firstCreationVo.getCompanyPostalCode());
		companyVec.add(firstCreationVo.getCompanyEMail());
		companyVec.add(phoneNo);
		companyVec.add(tempcat);
		companyVec.add("10");
		companyVec.add("0");
		companyVec.add("10");
		companyVec.add(expiryDate);
		companyVec.add("0");
		companyVec.add("1");
		companyVec.add(companyType);
		companyVec.add(firstCreationVo.getCompanySName());
		companyVec.add("2");
		companyVec.add("1");
		companyVec.add(creationDate);
		companyVec.add(expiryDate);
		companyVec.add("0");
		companyVec.add("1");
		companyVec.add(teampMcat);
		
		
	
		strResult=rootMaster.insertCorpoDetail(dataSource,companyVec);
		if(strResult.equalsIgnoreCase("success"))
		{
			
			//add user rights by default 1 for first user
			
			
			Vector companyDetailVec=rootMaster.getCompanyDetail(dataSource,firstCreationVo.getCompanySName());
			Vector<String>userVec= new Vector<String>();
			String tempuserCountry[] = firstusercreationForm.getCompCountry().split("~");
			String userCountry=tempuserCountry[0];
			String userState = firstusercreationForm.getCompanyState();
			String userCity = firstusercreationForm.getCompanyCity();
			if(userCountry.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				userCountry=rootMaster.insertCountry(dataSource,firstusercreationForm.getTxtPPrefix(),firstusercreationForm.getOthercompCountry().toString().trim());//return country id
				userState=rootMaster.insertState(dataSource,firstusercreationForm.getOthercompanyState().toString().trim(),userCountry);
				userCity=rootMaster.insertCity(dataSource,firstusercreationForm.getOthercompanyCity().toString().trim(),userState,userCountry);
			}
			else if(userState.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				userState=rootMaster.insertState(dataSource,firstusercreationForm.getOthercompanyState().toString().trim(),userCountry);
				userCity=rootMaster.insertCity(dataSource,firstusercreationForm.getOthercompanyCity().toString().trim(),userState,userCountry);
			}
			else if(userCity.equals("-1"))
			{
				//set sity in data base and add that is in company master.
				userCity=rootMaster.insertCity(dataSource,firstusercreationForm.getOthercompanyCity().toString().trim(),userState,userCountry);
			}
			else
			{
				//nothing 
			}
			
			String userphoneNo ="";
			if(firstusercreationForm.getTxtAreacode().length()>0 || firstusercreationForm.getTxtPhone().length()>0)
			{
				userphoneNo=firstusercreationForm.getTxtPPrefix()+"~"+firstusercreationForm.getTxtAreacode()+"~"+firstusercreationForm.getTxtPhone();
			}
			String userMobile = firstusercreationForm.getTxtPrefix()+"~"+firstusercreationForm.getTxtMobile();
			String depart = firstusercreationForm.getDepartment();
			String desig = firstusercreationForm.getDesignation();
			String type="college";
			if(depart.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				depart=rootMaster.insertDepartment(dataSource,firstusercreationForm.getOtherdepartment().toString().trim(),type);//return country id
				desig=rootMaster.insertDesignation(dataSource,firstusercreationForm.getOtherdesignation().toString().trim(),type,depart);
				
			}
			else if(desig.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				desig=rootMaster.insertDesignation(dataSource,firstusercreationForm.getOtherdesignation().toString().trim(),type,depart);
			}
			
			
			userVec.add(companyDetailVec.elementAt(0).toString());
			userVec.add("1");
			userVec.add(firstusercreationForm.getUserID());
			userVec.add(firstusercreationForm.getUserPassword());
			userVec.add(firstusercreationForm.getUserFirstName());
			userVec.add(firstusercreationForm.getUserLastName());
			userVec.add(firstusercreationForm.getUserEMail());
			userVec.add(userphoneNo);
			userVec.add(userMobile);
			userVec.add(firstusercreationForm.getUserAddress());
			userVec.add(userCountry);
			userVec.add(userState);
			userVec.add(userCity);
			userVec.add(firstusercreationForm.getUserPostalCode());
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
			
			////////////System.out.println("userVec................."+userVec);
			
			strResult=rootMaster.insertUserInfoCorpo(dataSource,userVec);
			
			
			//mail transfer code here.
			Resource resr = new Resource();
			MailText mt = new MailText();
			String strMailText = "";
			if(companyType.equalsIgnoreCase("Corporates"))
			{
				strMailText = mt.getETextForAdvtReg(firstusercreationForm.getUserID(), firstusercreationForm.getUserPassword(), firstusercreationForm.getUserFirstName(), firstusercreationForm.getUserLastName());
			}
			else
			{
				strMailText = mt.getETextForEntpReg(firstusercreationForm.getUserID(), firstusercreationForm.getUserPassword(), firstusercreationForm.getUserFirstName(), firstusercreationForm.getUserLastName());
			}
			String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender, firstusercreationForm.getUserEMail(), Constant.Email_Title_For_UCreation);
			////////////System.out.println("str Email Status"+strEmailStatus);
			String strPassVal = "";
			for(int i=0; i<firstusercreationForm.getUserPassword().length();i++)
			{
				strPassVal = (strPassVal.equalsIgnoreCase("")) ? "*" : strPassVal+"*";
			}
			
			
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
			 paramVec.add("Product & Services");
			 paramVec.add("Recruitment");
			 paramVec.add("Work Culture");
			 paramVec.add("Announcements");
			 paramVec.add(strValue1);
			 paramVec.add(strValue2);
			 paramVec.add(strValue3);
			 paramVec.add(strValue4);
			 paramVec.add(strValue5);
			 paramVec.add(creationDate);
			 
			 AdvtMaster advtMaster=new AdvtMaster();
			 String strComplResult = advtMaster.insertTab(dataSource, paramVec);
			Vector<String> userDetailVec = new Vector<String>();
			userDetailVec.add(firstCreationVo.getCompanyName());
			userDetailVec.add(firstusercreationForm.getUserID());
			userDetailVec.add(strPassVal);
			userDetailVec.add(Constant.Email_Sender);
			userDetailVec.add(firstusercreationForm.getUserEMail());
			userDetailVec.add(firstusercreationForm.getUserFirstName());
			userDetailVec.add(firstusercreationForm.getUserLastName());
			
			
			rootMaster.CreateBlogsFile(dataSource);
			
			session.setAttribute("eusers", userDetailVec);
		}
		session.removeAttribute("companyData");
		
		session.setAttribute("flagforCorpo", "0");
		return mapping.findForward(strResult);
		
		}
		else{
			return mapping.findForward("sessionFail");
			
		}
	}
}