package com.mm.struts.action;

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

import com.mm.core.master.*;

public class LoginAfterMenterAction extends Action {
	
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
		String type = "indv";
		String subType = "student";
		
		Vector userdata1 = new Vector();
		Vector userdata = ((Vector)session.getAttribute("userlogindata") != null) ? (Vector)session.getAttribute("userlogindata"):	userdata1;
		session.removeAttribute("userlogindata");
		
		
		RootMaster rootMaster= new RootMaster();
		
		DataSource dataSource = getDataSource(request,"main");	
		Vector <String> dataVec = new Vector<String>();	
		
		String result = "failure";
		dataVec.add(userdata.elementAt(1).toString());
		dataVec.add(userdata.elementAt(2).toString());
		dataVec.add(type);
		//change type to full company type
		Vector userVec = rootMaster.verifyUser(dataSource,dataVec);
		
		if(userVec.size()==1)
		{
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
			
			session.setAttribute("uName",userData.elementAt(4).toString().trim());
			session.setAttribute("uId",strUserId);
			session.setAttribute("compId",userData.elementAt(0).toString().trim());
			int rightId = Integer.parseInt(userData.elementAt(1).toString().trim());
			
			
					
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
					
					IndvMaster indvMaster= new IndvMaster();		
					Vector dataVec1 = new Vector();		
					dataVec1.add(strUserId);
					dataVec1.add(userData.elementAt(0).toString().trim());					
					Vector countVec = indvMaster.complaintCount(getDataSource(request, "main"), dataVec1);		
					session.setAttribute("countVec", countVec);						
					////System.out.println("countVec.............loginAction........... "+countVec);
					
				
				}
				else if((type.equalsIgnoreCase("indv"))&&(subType.equalsIgnoreCase("student")))
				{
					result="studentsuccess";	
					session.setAttribute("compType","Student");
					
					IndvMaster indvMaster= new IndvMaster();		
					Vector dataVec1 = new Vector();		
					dataVec1.add(strUserId);
					dataVec1.add(userData.elementAt(0).toString().trim());					
					Vector countVec = indvMaster.complaintCount(getDataSource(request, "main"), dataVec1);		
					session.setAttribute("countVec", countVec);						
					////System.out.println("countVec.............loginAction........... "+countVec);
					
				
				}
				else if(type.equalsIgnoreCase("advt"))
				{
					result="advtsuccess";
					session.setAttribute("compType","Advertiser");
					////System.out.println(".............loginAction........... ");
					AdvtMaster advtMaster= new AdvtMaster();		
					Vector dataVec2 = new Vector();		
					dataVec2.add(strUserId);
					dataVec2.add(userData.elementAt(0).toString().trim());	
					dataVec2.add(userRightData.elementAt(18).toString().trim());	
										
					Vector advtCountVec = advtMaster.complaintCountAdvt(getDataSource(request, "main"), dataVec2);		
					session.setAttribute("advtCountVec", advtCountVec);						
					////System.out.println("advtCountVec.............loginAction........... "+advtCountVec);
					
				}
				else if(type.equalsIgnoreCase("corpo"))
				{
					result="corposuccess";
					session.setAttribute("compType","Corporates");
					////System.out.println(".............loginAction........... ");
					AdvtMaster advtMaster= new AdvtMaster();		
					Vector dataVec2 = new Vector();		
					dataVec2.add(strUserId);
					dataVec2.add(userData.elementAt(0).toString().trim());	
					dataVec2.add(userRightData.elementAt(18).toString().trim());	
										
					Vector advtCountVec = advtMaster.complaintCountAdvt(getDataSource(request, "main"), dataVec2);		
					session.setAttribute("advtCountVec", advtCountVec);						
					////System.out.println("CorpoCountVec.............loginAction........... "+advtCountVec);
					
				}
				else if(type.equalsIgnoreCase("entp"))
				{
					result="entpsuccess";
					session.setAttribute("compType","Enterprise");
					
					EntpMaster entpMaster= new EntpMaster();		
					Vector dataVec3 = new Vector();		
					dataVec3.add(strUserId);
					dataVec3.add(userData.elementAt(0).toString().trim());	
					dataVec3.add(userRightData.elementAt(18).toString().trim());
					Vector entpCountVec = entpMaster.complaintCountEntp(getDataSource(request, "main"), dataVec3);		
					session.setAttribute("entpCountVec", entpCountVec);						
					////System.out.println("entpCountVec.............loginAction........... "+entpCountVec);
					
				}
				else
				{
					result="failure";
				}
			}
			
			
			
		}

		////System.out.println("result.............loginAction........... "+result);
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
		strResult = compose+","+report+","+contact+","+campaign+","+myspace+","+complaint+","+support;
		
		return strResult;
	}
	private String getLowerRights(Vector userRightData, String type, String subType)
	{
		String strResult="";
		if((type.equalsIgnoreCase("indv"))&&(subType.equalsIgnoreCase("consultant")))
		{
			////System.out.println("userRightData.............loginAction...........consultant ");
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
			
			strResult=sms+","+twosms+","+pSchd+","+oSchd+","+mAlert+","+content+","+summery+","+sentItem+","+inbox+","+search+","+addCont+","+existCont+","+importCont+","+existGrp+","+addGrp+","+custCont+","+events+","+templates+","+mcoupons+","+RedeemedCoupon+","+keyword+","+accInfo+","+billInf+","+existUser+","+userCreation+","+myProfile+","+smsAllocation+","+senderId+","+newCompl+","+UnderProc+","+clsCompl+","+support;
			
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
			
			strResult=sms+","+twosms+","+pSchd+","+oSchd+","+mAlert+","+content+","+summery+","+sentItem+","+inbox+","+search+","+addCont+","+existCont+","+importCont+","+existGrp+","+addGrp+","+custCont+","+events+","+templates+","+mcoupons+","+RedeemedCoupon+","+keyword+","+accInfo+","+billInf+","+existUser+","+userCreation+","+myProfile+","+smsAllocation+","+senderId+","+newCompl+","+UnderProc+","+clsCompl+","+support;
			
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
			
			strResult=sms+","+twosms+","+pSchd+","+oSchd+","+mAlert+","+content+","+summery+","+sentItem+","+inbox+","+search+","+addCont+","+existCont+","+importCont+","+existGrp+","+addGrp+","+custCont+","+events+","+templates+","+mcoupons+","+keyword+","+accInfo+","+billInf+","+existUser+","+userCreation+","+myProfile+","+smsAllocation+","+senderId+","+comunCrea+","+existComun+","+compMgt+","+pkgMgt+","+smscAllot+","+complAllot+","+comCompl+","+mis+","+category+","+currCompl+","+allCompl+","+underCompl+","+resCompl+","+closCompl+","+inboxCompl+","+searchCompl+","+support;
			
		}
		
		return strResult;
	}

}
