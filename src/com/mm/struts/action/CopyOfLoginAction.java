/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

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
import com.mm.struts.form.LoginForm;
import com.mm.core.master.*;
import com.mm.core.resource.MasterUtility;




/** 
 * MyEclipse Struts
 * Creation date: 06-06-2007
 * 
 * XDoclet definition:
 * @struts.action path="/login" name="loginForm" input="/login.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/loginSuccess.jsp"
 * @struts.action-forward name="failure" path="/login.jsp"
 */
public class CopyOfLoginAction extends Action {
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
		
		HttpSession session= request.getSession();
		String type = "";
		String subType = "";
		
		if(session.getAttribute("type")==null)
		{
			return mapping.findForward("sessionFail");
		}
		else
		{
			type = (String)session.getAttribute("type");
			
			if(session.getAttribute("subType")!=null)
			{
				subType= (String)session.getAttribute("subType");
				
				//////System.out.println("subType.......on LoginAction.jsp........"+subType);
			}
		}
		RootMaster rootMaster= new RootMaster();
		LoginForm loginForm = (LoginForm) form;// TODO Auto-generated method stub
		DataSource dataSource = getDataSource(request,"main");	
		Vector <String> dataVec = new Vector<String>();	
		
		String result = "failure";
		dataVec.add(loginForm.getLoginId().trim());
		dataVec.add(loginForm.getPasword().trim());
		dataVec.add(type);
		//change type to full company type
		Vector userVec = rootMaster.verifyUser(dataSource,dataVec);
		
		if(userVec.size()==1)
		{
			loginForm.setPasword("");
			
			ActionErrors errors = new ActionErrors();
			if(userVec.elementAt(0).toString().equalsIgnoreCase("loginFailure"))
			{
				errors.add("notFound", new ActionError("errors.user.notFound"));
				if(!errors.isEmpty())
				{
					saveErrors(request, errors);
				}
				return mapping.findForward("failure");
			}
			if(userVec.elementAt(0).toString().equalsIgnoreCase("companyFailure"))
			{
				errors.add("notFound", new ActionError("errors.company.notExist"));
				if(!errors.isEmpty())
				{
					saveErrors(request, errors);
				}
				return mapping.findForward("failure");
			}
			if(userVec.elementAt(0).toString().equalsIgnoreCase("sectionFailure"))
			{
				errors.add("sectionFound", new ActionError("errors.user.sectionExist"));
				if(!errors.isEmpty())
				{
					saveErrors(request, errors);
				}
				return mapping.findForward("failure");
			}
			if(userVec.elementAt(0).toString().equalsIgnoreCase("activeFailure"))
			{
				if(type.equalsIgnoreCase("indv"))
				{
					
					return mapping.findForward("activeFailure");
				}
				else
				{
					errors.add("notFound", new ActionError("errors.user.acivate"));
					if(!errors.isEmpty())
					{
						saveErrors(request, errors);
					}
					return mapping.findForward("failure");
				}
			}
			
		}
		else
		{
			String strUserId =  userVec.elementAt(1).toString();
			Vector userData = rootMaster.getUserInfo(dataSource,Integer.parseInt(strUserId));	
			
			MasterUtility mu = new MasterUtility();
			ArrayList countriesArr = mu.getListCountries(dataSource);
			HashMap stateMap = mu.getListState(dataSource);
			HashMap citiesMap = mu.getListCities(dataSource);
			HashMap testCategoryMap = mu.getTestCategory(dataSource);
			session.setAttribute("AllCountry", countriesArr);
			session.setAttribute("AllStates", stateMap);
			session.setAttribute("AllCities", citiesMap);
			session.setAttribute("AllTestCategory", testCategoryMap);
			
			session.setAttribute("uName",userData.elementAt(4).toString().trim());
			session.setAttribute("uId",strUserId);
			session.setAttribute("compId",userData.elementAt(0).toString().trim());
			int rightId = Integer.parseInt(userData.elementAt(1).toString().trim());
			
			IndvMaster indvMaster = new IndvMaster();
			Vector userSideVec = indvMaster.getUserInfo(dataSource,Integer.parseInt(strUserId));
			
			String contryStr1 = rootMaster.getCountryName(dataSource,Integer.parseInt(userSideVec.elementAt(11).toString()));
    		String stateStr1 = rootMaster.getStateName(dataSource,Integer.parseInt(userSideVec.elementAt(7).toString()));	
    		String cityStr1 = rootMaster.getPlaceName(dataSource,Integer.parseInt(userSideVec.elementAt(6).toString()));
    		
    		Vector<String> CSCVec =new Vector<String>();
    		CSCVec.add(contryStr1);
    		CSCVec.add(stateStr1);
    		CSCVec.add(cityStr1);
    		
    		session.setAttribute("CSCVec", CSCVec);
    		//2461
			session.setAttribute("userSideVec", userSideVec);
			
			
					
			//get and set upper rights for the user
			Vector userRightData= rootMaster.getUserRights(dataSource,rightId);
			if(userRightData.size() <= 0)
			{
				return mapping.findForward("failure");
			}
			else
			{
				String upperRights = getUpperRights(userRightData);
				session.setAttribute("rights",upperRights);
				//set Lowwer Rights
				String lowerRights = getLowerRights(userRightData, type, subType);
				session.setAttribute("lowerRights",lowerRights);
				//set all rights for the user
				
				session.setAttribute("allRights", userRightData);
			
			
				if((type.equalsIgnoreCase("indv"))&&(subType.equalsIgnoreCase("consultant")))
				{
					result="success";	
					session.setAttribute("compType","Consumer");
					
						
					Vector<String> dataVec1 = new Vector<String>();		
					dataVec1.add(strUserId);
					dataVec1.add(userData.elementAt(0).toString().trim());					
					Vector countVec = indvMaster.complaintCount(getDataSource(request, "main"), dataVec1);		
					session.setAttribute("countVec", countVec);						
					//////System.out.println("countVec.............loginAction........... "+countVec);
					
				
				}
				else if((type.equalsIgnoreCase("indv"))&&(subType.equalsIgnoreCase("student")))
				{
					result="studentsuccess";	
					session.setAttribute("compType","Student");
					
					
					
					Vector<String> dataVec1 = new Vector<String>();		
					dataVec1.add(strUserId);
					dataVec1.add(userData.elementAt(0).toString().trim());					
					Vector countVec = indvMaster.complaintCount(getDataSource(request, "main"), dataVec1);		
					session.setAttribute("countVec", countVec);	
					ArrayList TComplaintList = new ArrayList();
					
					Vector<String> DataVecT=new Vector<String>();
					
					DataVecT.add("Student");
					DataVecT.add(strUserId);
					DataVecT.add(userData.elementAt(0).toString().trim());
					DataVecT.add("Technical");
					TComplaintList=mu.getComplaintList(getDataSource(request,"main"),DataVecT);
					//TDraftList=mu.getDraftList(getDataSource(request,"main"),DataVecT);
					//session.setAttribute("TDraftList", TDraftList);
					session.setAttribute("TComplaintList", TComplaintList);
					
					ArrayList PComplaintList = new ArrayList();
					
					Vector<String> DataVecP=new Vector<String>();
					
					DataVecP.add("Student");
					DataVecP.add(strUserId);
					DataVecP.add(userData.elementAt(0).toString().trim());
					DataVecP.add("Personal");
					PComplaintList=mu.getComplaintList(getDataSource(request,"main"),DataVecP);
					//PDraftList=mu.getDraftList(getDataSource(request,"main"),DataVecP);
					//session.setAttribute("PDraftList", PDraftList);
					session.setAttribute("PComplaintList", PComplaintList);
					
					
					ArrayList MComplaintList = new ArrayList();
					Vector<String> DataVecM=new Vector<String>();
					DataVecM.add("Student");
					DataVecM.add(strUserId);
					DataVecM.add(userData.elementAt(0).toString().trim());
					DataVecM.add("Mrequest");
					MComplaintList=mu.getComplaintList(getDataSource(request,"main"),DataVecM);
					session.setAttribute("MComplaintList", MComplaintList);
					
					ArrayList TRComplaintList = new ArrayList();
					Vector<String> DataVecTR=new Vector<String>();
					
					DataVecTR.add("Student");
					DataVecTR.add(strUserId);
					DataVecTR.add(userData.elementAt(0).toString().trim());
					DataVecTR.add("Trequest");
					TRComplaintList=mu.getComplaintList(getDataSource(request,"main"),DataVecTR);
					session.setAttribute("TRComplaintList", TRComplaintList);
					
					int infoflag = indvMaster.getInfoFlag(getDataSource(request, "main"),strUserId);
					System.out.println("infoflag.............loginAction........... "+infoflag);
					session.setAttribute("infoflag", String.valueOf(infoflag));	
					
					
				
				}
				else if(type.equalsIgnoreCase("advt"))
				{
					result="advtsuccess";
					session.setAttribute("compType","Advertiser");
					//////System.out.println(".............loginAction........... ");
					AdvtMaster advtMaster= new AdvtMaster();		
					
					
					
					
					Vector<String> dataVec2 = new Vector<String>();		
					dataVec2.add(strUserId);
					dataVec2.add(userData.elementAt(0).toString().trim());	
					dataVec2.add(userRightData.elementAt(18).toString().trim());	
										
					Vector advtCountVec = advtMaster.complaintCountAdvt(getDataSource(request, "main"), dataVec2);		
				
					
					
					session.setAttribute("advtCountVec", advtCountVec);						
					//////System.out.println("advtCountVec.............loginAction........... "+advtCountVec);
					
				}
				else if(type.equalsIgnoreCase("corpo"))
				{
					result="corposuccess";
					session.setAttribute("compType","Corporates");
					//////System.out.println(".............loginAction........... ");
					AdvtMaster advtMaster= new AdvtMaster();		
					Vector<String> dataVec2 = new Vector<String>();		
					dataVec2.add(strUserId);
					dataVec2.add(userData.elementAt(0).toString().trim());	
					dataVec2.add(userRightData.elementAt(18).toString().trim());	
										
					Vector advtCountVec = advtMaster.complaintCountAdvt(getDataSource(request, "main"), dataVec2);		
					session.setAttribute("advtCountVec", advtCountVec);						
					//////System.out.println("CorpoCountVec.............loginAction........... "+advtCountVec);
					
				}
				else if(type.equalsIgnoreCase("entp"))
				{
					result="entpsuccess";
					session.setAttribute("compType","Enterprise");
					
					EntpMaster entpMaster= new EntpMaster();		
					Vector<String> dataVec3 = new Vector<String>();		
					dataVec3.add(strUserId);
					dataVec3.add(userData.elementAt(0).toString().trim());	
					dataVec3.add(userRightData.elementAt(18).toString().trim());
					Vector entpCountVec = entpMaster.complaintCountEntp(getDataSource(request, "main"), dataVec3);		
					session.setAttribute("entpCountVec", entpCountVec);						
					//////System.out.println("entpCountVec.............loginAction........... "+entpCountVec);
					
				}
				else
				{
					result="failure";
				}
			}
			
			
			
		}

		//////System.out.println("result.............loginAction........... "+result);
		return mapping.findForward(result);
	}
	private String getUpperRights(Vector userRightData)
	{
		String strResult="";
		String compose = "0";
		String report = "0";
		String contact = "0";
		String campaign = "0";
		String myspace = userRightData.elementAt(0).toString(); //accmanagement (My Profile/My preference Right)
		String complaint = userRightData.elementAt(29).toString(); //complaint Rights for complaint module
		String support="1";
		String test = userRightData.elementAt(31).toString(); //Rights for test ceation.
		String mentor = userRightData.elementAt(32).toString(); //Right for mentoring.
		strResult = compose+","+report+","+contact+","+campaign+","+myspace+","+complaint+","+support+","+test+","+mentor;
		
		return strResult;
	}
	private String getLowerRights(Vector userRightData, String type, String subType)
	{
		String strResult="";
		if((type.equalsIgnoreCase("indv"))&&(subType.equalsIgnoreCase("consultant")))
		{
			//////System.out.println("userRightData.............loginAction...........consultant ");
			String sms = "0";       	//0 compose rights
			String twosms = "0";    	//1 two way messeging 
			String pSchd = "0";			//2 personal scheduler
			String oSchd = "0";			//3 Occessional scheduler
			String mAlert = "0";		//4 My Alert
			String content = "0";		//5 content
			String summery = "0";		//6 report summery 
			String sentItem = "0";		//7 sent item report
			String inbox = "0"; 		//8 inbox report
			String search = "0";		//9 search report
			String addCont = "0";		//10 Add contact
			String existCont = "0";		//11 Existing contact
			String importCont = "0";	//12 Import Contact
			String existGrp = "0";		//13 Exisiting Group
			String addGrp = "0";		//14 Add Group
			String custCont = "0";		//15 Customize Contact
			String events = "0";		//16 Events
			String templates = "0";		//17 Templates 
			String mcoupons = "0";		//18 M-Coupons
			String keyword = "0";		//19 Keywords
			String accInfo = userRightData.elementAt(1).toString();		//20 Account Information
			String myProfile = userRightData.elementAt(0).toString();		//21 My Profile
			String myPreference = userRightData.elementAt(0).toString();	//22 My Preference
			String writeCompl = "1";	//23 Write Complaint
			String existCompl = "1";	//24 Existing Complaint
			String searchCompl = "1";	//25 Search Complaint
			String Draft = "1";			//26 Draft Complaint
			String support = "1";		//27 Support
			String test = userRightData.elementAt(31).toString(); //Rights for test ceation.
			String mentor = userRightData.elementAt(32).toString(); //Right for mentoring.
			
			strResult=sms+","+twosms+","+pSchd+","+oSchd+","+mAlert+","+content+","+summery+","+sentItem+","+inbox+","+search+","+addCont+","+existCont+","+importCont+","+existGrp+","+addGrp+","+custCont+","+events+","+templates+","+mcoupons+","+keyword+","+accInfo+","+myProfile+","+myPreference+","+writeCompl+","+existCompl+","+searchCompl+","+Draft+","+support+","+test+","+mentor;
			
			
		}
		else if((type.equalsIgnoreCase("indv"))&&(subType.equalsIgnoreCase("student")))
		{
			
			////System.out.println("userRightData.............loginAction...........student ");
			String sms = "0";       	//0 compose rights
			String twosms = "0";    	//1 two way messeging 
			String pSchd = "0";			//2 personal scheduler
			String oSchd = "0";			//3 Occessional scheduler
			String mAlert = "0";		//4 My Alert
			String content = "0";		//5 content
			String summery = "0";		//6 report summery 
			String sentItem = "0";		//7 sent item report
			String inbox = "0"; 		//8 inbox report
			String search = "0";		//9 search report
			String addCont = "0";		//10 Add contact
			String existCont = "0";		//11 Existing contact
			String importCont = "0";	//12 Import Contact
			String existGrp = "0";		//13 Exisiting Group
			String addGrp = "0";		//14 Add Group
			String custCont = "0";		//15 Customize Contact
			String events = "0";		//16 Events
			String templates = "0";		//17 Templates 
			String mcoupons = "0";		//18 M-Coupons
			String keyword = "0";		//19 Keywords
			String accInfo = userRightData.elementAt(1).toString();		//20 Account Information
			String myProfile = userRightData.elementAt(0).toString();		//21 My Profile
			String myPreference = userRightData.elementAt(0).toString();	//22 My Preference
			String writeCompl = "1";	//23 Write Complaint
			String existCompl = "1";	//24 Existing Complaint
			String searchCompl = "1";	//25 Search Complaint
			String Draft = "1";			//26 Draft Complaint
			String support = "1";		//27 Support
			
			strResult=sms+","+twosms+","+pSchd+","+oSchd+","+mAlert+","+content+","+summery+","+sentItem+","+inbox+","+search+","+addCont+","+existCont+","+importCont+","+existGrp+","+addGrp+","+custCont+","+events+","+templates+","+mcoupons+","+keyword+","+accInfo+","+myProfile+","+myPreference+","+writeCompl+","+existCompl+","+searchCompl+","+Draft+","+support;
			
			
		}
		else if(type.equalsIgnoreCase("advt"))
		{
			String sms = "0";       	//0 compose rights
			String twosms = "0";    	//1 two way messeging 
			String pSchd = "0";			//2 personal scheduler
			String oSchd = "0";			//3 Occessional scheduler
			String mAlert = "0";		//4 My Alert
			String content = "0";		//5 content
			String summery = "0";		//6 report summery 
			String sentItem = "0";		//7 sent item report
			String inbox = "0"; 		//8 inbox report
			String search = "0";		//9 search report
			String addCont = "0";		//10 Add contact
			String existCont = "0";		//11 Existing contact
			String importCont = "0";	//12 Import Contact
			String existGrp = "0";		//13 Exisiting Group
			String addGrp = "0";		//14 Add Group
			String custCont = "0";		//15 Customize Contact
			String events = "0";		//16 Events
			String templates = "0";		//17 Templates 
			String mcoupons = "0";		//18 M-Coupons
			String RedeemedCoupon = "0";//19 Redeemed Coupon
			String keyword = "0";		//20 Keywords
			String accInfo = userRightData.elementAt(1).toString();		//21 Account Information
			String billInf = userRightData.elementAt(2).toString();		//22 Billing Information
			String existUser = userRightData.elementAt(10).toString();		//23 Existing User
			String userCreation = userRightData.elementAt(11).toString();	//24 User Creation 
			String myProfile = userRightData.elementAt(0).toString();		//25 My Profile
			String smsAllocation = userRightData.elementAt(9).toString();	//26 SMS Allocation
			String senderId = userRightData.elementAt(5).toString();		//27 sender Id 
			String newCompl = "1";		//28 New Complaint
			String UnderProc = "1";		//29 UnderProcess Complaint
			String clsCompl = "1";		//30 Resolved and Closed Complaint
			String support = "1";		//31 Support
			String test = userRightData.elementAt(31).toString(); //Rights for test ceation.
			String mentor = userRightData.elementAt(32).toString(); //Right for mentoring.
			
			strResult=sms+","+twosms+","+pSchd+","+oSchd+","+mAlert+","+content+","+summery+","+sentItem+","+inbox+","+search+","+addCont+","+existCont+","+importCont+","+existGrp+","+addGrp+","+custCont+","+events+","+templates+","+mcoupons+","+RedeemedCoupon+","+keyword+","+accInfo+","+billInf+","+existUser+","+userCreation+","+myProfile+","+smsAllocation+","+senderId+","+newCompl+","+UnderProc+","+clsCompl+","+support+","+test+","+mentor;
			
		}
		else if(type.equalsIgnoreCase("corpo"))
		{
			String sms = "0";       	//0 compose rights
			String twosms = "0";    	//1 two way messeging 
			String pSchd = "0";			//2 personal scheduler
			String oSchd = "0";			//3 Occessional scheduler
			String mAlert = "0";		//4 My Alert
			String content = "0";		//5 content
			String summery = "0";		//6 report summery 
			String sentItem = "0";		//7 sent item report
			String inbox = "0"; 		//8 inbox report
			String search = "0";		//9 search report
			String addCont = "0";		//10 Add contact
			String existCont = "0";		//11 Existing contact
			String importCont = "0";	//12 Import Contact
			String existGrp = "0";		//13 Exisiting Group
			String addGrp = "0";		//14 Add Group
			String custCont = "0";		//15 Customize Contact
			String events = "0";		//16 Events
			String templates = "0";		//17 Templates 
			String mcoupons = "0";		//18 M-Coupons
			String RedeemedCoupon = "0";//19 Redeemed Coupon
			String keyword = "0";		//20 Keywords
			String accInfo = userRightData.elementAt(1).toString();		//21 Account Information
			String billInf = userRightData.elementAt(2).toString();		//22 Billing Information
			String existUser = userRightData.elementAt(10).toString();		//23 Existing User
			String userCreation = userRightData.elementAt(11).toString();	//24 User Creation 
			String myProfile = userRightData.elementAt(0).toString();		//25 My Profile
			String smsAllocation = userRightData.elementAt(9).toString();	//26 SMS Allocation
			String senderId = userRightData.elementAt(5).toString();		//27 sender Id 
			String newCompl = "1";		//28 New Complaint
			String UnderProc = "1";		//29 UnderProcess Complaint
			String clsCompl = "1";		//30 Resolved and Closed Complaint
			String support = "1";		//31 Support
			String test = userRightData.elementAt(31).toString(); //Rights for test ceation.
			String mentor = userRightData.elementAt(32).toString(); //Right for mentoring.
			
			strResult=sms+","+twosms+","+pSchd+","+oSchd+","+mAlert+","+content+","+summery+","+sentItem+","+inbox+","+search+","+addCont+","+existCont+","+importCont+","+existGrp+","+addGrp+","+custCont+","+events+","+templates+","+mcoupons+","+RedeemedCoupon+","+keyword+","+accInfo+","+billInf+","+existUser+","+userCreation+","+myProfile+","+smsAllocation+","+senderId+","+newCompl+","+UnderProc+","+clsCompl+","+support+","+test+","+mentor;
			
		}
		else 
		{
			String sms = "0";       	//0 compose rights
			String twosms = "0";    	//1 two way messeging 
			String pSchd = "0";			//2 personal scheduler
			String oSchd = "0";			//3 Occessional scheduler
			String mAlert = "0";		//4 My Alert
			String content = "0";		//5 content
			String summery = "0";		//6 report summery 
			String sentItem = "0";		//7 sent item report
			String inbox = "0"; 		//8 inbox report
			String search = "0";		//9 search report
			String addCont = "0";		//10 Add contact
			String existCont = "0";		//11 Existing contact
			String importCont = "0";	//12 Import Contact
			String existGrp = "0";		//13 Exisiting Group
			String addGrp = "0";		//14 Add Group
			String custCont = "0";		//15 Customize Contact
			String events = "0";		//16 Events
			String templates = "0";		//17 Templates 
			String mcoupons = "0";		//18 M-Coupons
			String keyword = "0";		//19 Keywords
			String accInfo = userRightData.elementAt(1).toString();			//20 Account Information
			String billInf = userRightData.elementAt(2).toString();			//21 Billing Information
			String existUser = userRightData.elementAt(10).toString();		//22 Existing User
			String userCreation = userRightData.elementAt(11).toString();	//23 User Creation 
			String myProfile = userRightData.elementAt(0).toString();		//24 My Profile
			String smsAllocation = userRightData.elementAt(9).toString();	//25 SMS Allocation
			String senderId = userRightData.elementAt(5).toString();		//26 sender Id 
			String comunCrea = userRightData.elementAt(20).toString();		//27 Community Creation 
			String existComun = userRightData.elementAt(22).toString();		//28 Existing Community
			String compMgt = userRightData.elementAt(27).toString();		//29 Company Management
			String pkgMgt = userRightData.elementAt(23).toString();			//30 Package Management
			String smscAllot = userRightData.elementAt(24).toString();		//31 SMSC Allocation
			String complAllot = userRightData.elementAt(25).toString();		//32 Complaint Allocation
			String comCompl = userRightData.elementAt(26).toString();		//33 Comment On Complaint
			String mis = userRightData.elementAt(30).toString();												//34 Management Information(MIS)
			String category = userRightData.elementAt(28).toString();		//35 category
			String currCompl = "1";			//36 Current Complaint
			String allCompl = "1";			//37 All Complaint
			String underCompl = "1";		//38 UnderProcess Complaint
			String resCompl = "1";			//39 Resolved Complaint
			String closCompl = "1";			//40 Closed Complaint
			String inboxCompl = "1";		//41 Inbox for complaint
			String searchCompl = "1";		//42 Search Complaint
			String support = "1";			//43 Support
			String test = userRightData.elementAt(31).toString(); //Rights for test ceation.
			String mentor = userRightData.elementAt(32).toString(); //Right for mentoring.
			
			strResult=sms+","+twosms+","+pSchd+","+oSchd+","+mAlert+","+content+","+summery+","+sentItem+","+inbox+","+search+","+addCont+","+existCont+","+importCont+","+existGrp+","+addGrp+","+custCont+","+events+","+templates+","+mcoupons+","+keyword+","+accInfo+","+billInf+","+existUser+","+userCreation+","+myProfile+","+smsAllocation+","+senderId+","+comunCrea+","+existComun+","+compMgt+","+pkgMgt+","+smscAllot+","+complAllot+","+comCompl+","+mis+","+category+","+currCompl+","+allCompl+","+underCompl+","+resCompl+","+closCompl+","+inboxCompl+","+searchCompl+","+support+","+test+","+mentor;
			
		}
		
		return strResult;
	}
}