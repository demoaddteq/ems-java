package com.mm.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.struts.form.CommunityselectionForm;
import javax.sql.DataSource;
import java.util.Vector;
import java.util.Date;
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
 * @struts.action path="/communityselection" name="CommunityselectionForm" input="/communityselection.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/communityselection.jsp"
 * 
 */
public class CommunityselectionAction extends Action {
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
			HttpServletRequest request, HttpServletResponse response) 
	{
		//System.out.println("Ok");
		HttpSession session = request.getSession();
		if(session.getAttribute("type1")==null)
		{
			
			return mapping.findForward("sessionFail");
		}
		
		
		//define some variables
		String result = "failure";
		String creationDate ="";
		String expiryDate ="";
		String creationTime = "";
	    String timePart = "";
	    String birthday="";
	    String birthdaySp="";
	    String birthdayCh1="";
	    String birthdayCh2="";
	    String birthdayCh3="";
	    
	    String rightId="";
	    CommunityselectionForm communityselectionForm = (CommunityselectionForm) form;
	    
	    String communitId = communityselectionForm.getCommunity();
	    String loginType = communityselectionForm.getLoginType();
	    
	    ////System.out.println("communitId...!!!!!!!!!Action!!!!!!!!!!!!!!!!!!...... "+communitId);
	    ////System.out.println("loginType...!!!!!!!!!!Action!!!!!!!!!!!!!!!!!....... "+loginType);
	    
	    
	    request.setAttribute("communitId", communitId);
	    
		DataSource dataSource = getDataSource(request,"main");	
		RootMaster rootMaster= new RootMaster();
		IndvMaster indvMaster= new IndvMaster();
		Vector <String> datavec = new Vector<String>();	
		
		String strVerifyResult=rootMaster.checkLoginId(dataSource,communityselectionForm.getUserID(),communityselectionForm.getUserEMail(), "Consumer");
		
		System.out.println("Ajay Kumar Jha strVerifyResult "+strVerifyResult);
		
		//if(!strVerifyResult.equals("success"))
		
				
		if(strVerifyResult.indexOf("success") <= -1)
		{
			if(strVerifyResult.equals("loginId"))
			{
				ActionErrors errors = new ActionErrors();
				errors.add("duplicate", new ActionError("errors.user.duplicate"));
				if(!errors.isEmpty())
				{
					saveErrors(request, errors);
				}	
			}
			if(strVerifyResult.equals("mailId"))
			{
				ActionErrors errors = new ActionErrors();
				errors.add("duplicate", new ActionError("errors.user.mailid"));
				if(!errors.isEmpty())
				{
					saveErrors(request, errors);
				}
			}

			
			result="failure";
		}
		else
		{
			System.out.println("Reeee "+communityselectionForm.getTxtMobile().trim().length());
			String mobileNo="";
			if(communityselectionForm.getTxtMobile().trim().length()>0)
			{
				String Prefix = communityselectionForm.getTxtPrefix().trim();
				String mobile = communityselectionForm.getTxtMobile().trim();
				mobileNo = Prefix+mobile ;
			}
				
			String date =communityselectionForm.getCmbDate().trim();
			String month = communityselectionForm.getCmbMonth().trim();
			String year = communityselectionForm.getCmbYear().trim();
			////////////System.out.println("date   month   year>>>>"+year+"-"+month+"-"+date);
			if(date.length()>0 && month.length()>0 && year.length()>0)
				birthday = year+"-"+month+"-"+date;
			
			java.util.Date dt = new java.util.Date();
			SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
			String completeRemDate = sform.format(dt);
			StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
		    creationDate = sttotal.nextToken();
		    creationTime = sttotal.nextToken();
		    timePart = sttotal.nextToken();
		    expiryDate=rootMaster.returnFinalDate("Months",3);
			
		    //set country ,state and city in data base in case of other
		    String tempCountry[] = communityselectionForm.getCompCountry().split("~");
			String userCountry=tempCountry[0];
			String userState = communityselectionForm.getCompanyState();
			System.out.println("get State=>"+userState);
			String builderID = communityselectionForm.getbuilder();
			System.out.println("getBuilder.....=>"+builderID);			
			String Facility = communityselectionForm.getbuilderFacility();
			System.out.println("getBuilderFacility=>"+Facility);
			String uCity = communityselectionForm.getbuilderCity();
			System.out.println("User City=>"+uCity);
			
			Vector FacDetails = rootMaster.getFacilityDetails(dataSource,Facility);
			userCountry = FacDetails.elementAt(4).toString().trim();
			userState = FacDetails.elementAt(3).toString().trim();
			String userCity = FacDetails.elementAt(2).toString().trim();
			String userPin = FacDetails.elementAt(5).toString().trim();
			
			request.setAttribute("FDetails", FacDetails);
			
			String CId ="1~JIITNOIDA";
			System.out.println("CollegeId  ........."+CId);
			String StrArr[]= CId.split("~");
			int numCId=Integer.parseInt(StrArr[0]);
			
			
			String cName = "Not Mention.";
			if(StrArr.length==2){
				
				if(StrArr[1].equalsIgnoreCase("1"))
				{
					 cName = rootMaster.getCompnyName(dataSource, numCId);
				}
				else{
					
					 cName = rootMaster.getCollegeName(dataSource, numCId);
				}
				
			}
			
			String other_Cname = "Not Mentioned";
		//	other_Cname = communityselectionForm.getOtherCollegeName();
			String other_CId = "0";
			
			
			
			
			if(userCountry.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				userCountry=rootMaster.insertCountry(dataSource,communityselectionForm.getTxtPrefix().toString().trim(),communityselectionForm.getOthercompCountry().toString().trim());//return country id
				userState=rootMaster.insertState(dataSource,communityselectionForm.getOthercompanyState().toString().trim(),userCountry);
				uCity=rootMaster.insertCity(dataSource,communityselectionForm.getOthercompanyCity().toString().trim(),userState,userCountry);
			}
			else if(userState.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				userState=rootMaster.insertState(dataSource,communityselectionForm.getOthercompanyState().toString().trim(),userCountry);
				uCity=rootMaster.insertCity(dataSource,communityselectionForm.getOthercompanyCity().toString().trim(),userState,userCountry);
			}
			else if(uCity.equals("-1"))
			{	
				
				//set sity in data base and add that is in company master.
				uCity=rootMaster.insertCity(dataSource,communityselectionForm.getOthercompanyCity().toString().trim(),userState,userCountry);
			}
			else
			{
				//nothing 
			}
		    //end of country code
			rightId=rootMaster.getRightId(dataSource,communityselectionForm.getCommunity());
			
			
			/*
			 * college country,State,City
			 */
			String collCountryAr[] = communityselectionForm.getCollegeCountry().split("~");
			
			String collCountry="94";
			String collState = Facility;
			String collCity = uCity;//communityselectionForm.getCollegeCity().toString().trim();
			
			if(collCountry.equals("-1"))
			{	
				if(communityselectionForm.getOtherCollegeCountry().trim().length()==0){
					//System.out.println(".....fffffffff........");
					
					ActionErrors errors = new ActionErrors();
					errors.add("citName", new ActionError("errors.user.city","country name"));
					if(!errors.isEmpty())
					{
						saveErrors(request, errors);
					}
					
					result="failure";
					return mapping.findForward(result);
				}
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				collCountry=rootMaster.insertCountry(dataSource,communityselectionForm.getTxtPrefix().toString().trim(),communityselectionForm.getOtherCollegeCountry().toString().trim());//return country id
				collState=rootMaster.insertState(dataSource,communityselectionForm.getOtherCollegeState().toString().trim(),collCountry);
				collCity=rootMaster.insertCity(dataSource,communityselectionForm.getOtherCollegeCity().toString().trim(),collState,collCountry);
			}
			else if(collState.equals("-1"))
			{	
				if(communityselectionForm.getOtherCollegeState().trim().length()==0){
					//System.out.println(".....fffffffff........");
					
					ActionErrors errors = new ActionErrors();
					errors.add("citName", new ActionError("errors.user.city","state name"));
					if(!errors.isEmpty())
					{
						saveErrors(request, errors);
					}
					
					result="failure";
					return mapping.findForward(result);
				}
				
				//set state .city in data base and add these field in company master table
				collState=rootMaster.insertState(dataSource,communityselectionForm.getOtherCollegeState().toString().trim(),collCountry);
				collCity=rootMaster.insertCity(dataSource,communityselectionForm.getOtherCollegeCity().toString().trim(),collState,collCountry);
			}
			else if(collCity.equals("-1"))
			{	
				if(communityselectionForm.getOtherCollegeCity().trim().length()==0){
					////System.out.println(".....fffffffff........");
					
					ActionErrors errors = new ActionErrors();
					errors.add("citName", new ActionError("errors.user.city","city name"));
					if(!errors.isEmpty())
					{
						saveErrors(request, errors);
					}
					
					result="failure";
					return mapping.findForward(result);
				}
				
				//set sity in data base and add that is in company master.
				collCity=rootMaster.insertCity(dataSource,communityselectionForm.getOtherCollegeCity().toString().trim(),collState,collCountry);
			}
			else
			{
				//nothing 
			}
			
			
			
			
			if(CId.equals("-1"))
			{	
				CId = "0";
				other_CId=rootMaster.insertOtherCollege(dataSource, other_Cname.trim(), collCountry , collState, collCity);//return Other College id
				//System.out.println("other_CId  ........."+other_CId);
			}
			
			String strCourse1 = communityselectionForm.getTxtCourse();
			String strStream1 = communityselectionForm.getTxtSubject();	
			//////////System.out.println("strCourse1.......111........"+strCourse1);
			//////////System.out.println("strStream1.......111........"+strStream1);
			
			String course = "1";
			String subject = "2";
			
			Vector VecCourse =new Vector();
			Vector VecStream =new Vector();
			
			String strCourse = "";
			String strStream = "";
			
			
			if(!course.equalsIgnoreCase("-1")){
				
				VecCourse =  indvMaster.getCourseName(dataSource, Integer.parseInt(course));				
				strCourse = VecCourse.elementAt(0).toString();
				
			}else
			{	
				if(communityselectionForm.getTxtOthCourse().trim().length()==0){
					////System.out.println(".....fffffffff........"+communityselectionForm.getTxtOthCourse().length());
					
					ActionErrors errors = new ActionErrors();
					errors.add("citName", new ActionError("errors.user.city","course name"));
					if(!errors.isEmpty())
					{
						saveErrors(request, errors);
					}
					
					result="failure";
					return mapping.findForward(result);
				}
				strCourse = communityselectionForm.getTxtOthCourse();
				strCourse1 = rootMaster.insertCourse(dataSource, strCourse.trim());
				course = strCourse1;
			}
			
			if(!subject.equalsIgnoreCase("-1")){
				//////////System.out.println("..ELSE.....222.......");	
				
				VecStream =  indvMaster.getStreamName(dataSource, Integer.parseInt(subject));
				//////////System.out.println(".IF......222.......");
				strStream = VecStream.elementAt(0).toString();
				
			}
			else
			{
				//////////System.out.println("..ELSE.....222.......");
				strStream = communityselectionForm.getTxtOthSubject();
				if(communityselectionForm.getTxtOthSubject().trim().length()==0){
					////System.out.println(".....fffffffff........");
					
					ActionErrors errors = new ActionErrors();
					errors.add("citName", new ActionError("errors.user.city","Major/Stream name"));
					if(!errors.isEmpty())
					{
						saveErrors(request, errors);
					}
					
					result="failure";
					return mapping.findForward(result);
				}
				//////////System.out.println("strStream..ELSE.....222......."+strStream);
				strStream1 = rootMaster.insertStream(dataSource, course, strStream.trim());
			}
			
			String dateSp =communityselectionForm.getCmbDateSp().trim();
			String monthSp = communityselectionForm.getCmbMonthSp().trim();
			String yearSp = communityselectionForm.getCmbYearSp().trim();
			System.out.println(" Spouse--date   month   year>>>>"+year+"-"+month+"-"+date);
			if(dateSp.length()>0 && monthSp.length()>0 && yearSp.length()>0)
				birthdaySp = yearSp+"-"+monthSp+"-"+dateSp;
			
			
			String dateCh1 =communityselectionForm.getCmbDateCh1().trim();
			String monthCh1 = communityselectionForm.getCmbMonthCh1().trim();
			String yearCh1 = communityselectionForm.getCmbYearCh1().trim();
			System.out.println(" Ch1--date   month   year>>>>"+year+"-"+month+"-"+date);
			if(dateCh1.length()>0 && monthCh1.length()>0 && yearCh1.length()>0)
				birthdayCh1 = yearCh1+"-"+monthCh1+"-"+dateCh1;


			String dateCh2 =communityselectionForm.getCmbDateCh2().trim();
			String monthCh2 = communityselectionForm.getCmbMonthCh2().trim();
			String yearCh2 = communityselectionForm.getCmbYearCh2().trim();
			System.out.println(" Ch2--date   month   year>>>>"+year+"-"+month+"-"+date);
			if(dateCh2.length()>0 && monthCh2.length()>0 && yearCh2.length()>0)
				birthdayCh2 = yearCh2+"-"+monthCh2+"-"+dateCh2;

			

			String dateCh3 =communityselectionForm.getCmbDateCh3().trim();
			String monthCh3 = communityselectionForm.getCmbMonthCh3().trim();
			String yearCh3 = communityselectionForm.getCmbYearCh3().trim();
			System.out.println("Ch3--date   month   year>>>>"+year+"-"+month+"-"+date);
			if(dateCh3.length()>0 && monthCh3.length()>0 && yearCh3.length()>0)
				birthdayCh3 = yearCh3+"-"+monthCh3+"-"+dateCh3;

			
			//System.out.println("Spouse Name"+communityselectionForm.getSpouseName());
			
			//set data in data vector for sending form data to root master class .
			datavec.add(communityselectionForm.getCommunity());//0
			datavec.add(rightId);//1
			datavec.add(communityselectionForm.getUserID());//2
			datavec.add(communityselectionForm.getUserPassword());//3		
			datavec.add(communityselectionForm.getUserFirstName());//4
			datavec.add(communityselectionForm.getUserLastName());//5
			datavec.add(communityselectionForm.getUserEMail());//6
			datavec.add("");//7
			datavec.add(mobileNo);//8
			datavec.add(communityselectionForm.getTxtAddress());//9			
			datavec.add(collCountry);//10
			datavec.add(collState);//11
			datavec.add(collCity);//12
			datavec.add(communityselectionForm.getTxtPincode());//13
			datavec.add("nophoto.jpg");//14
			datavec.add("IN");//15
			datavec.add(creationDate);//16
			datavec.add(creationTime);//17
			datavec.add("0000-00-00");//18
			datavec.add("00:00:00");//19
			datavec.add("0");//20
			datavec.add("10");//21
			datavec.add(communityselectionForm.getAboutUsCombo());//22
			datavec.add("");//23
			datavec.add(communityselectionForm.getAnnualincomeCombo());//24
			datavec.add(birthday);//25
			datavec.add("");//26
			datavec.add(communityselectionForm.getEducationCombo());//27
			datavec.add(communityselectionForm.getMaritalCombo());//28
			datavec.add(communityselectionForm.getProfessionCombo());//29
			datavec.add("0");//30
			datavec.add("0");//31		
			datavec.add(communityselectionForm.getCmbGender());//32		
			datavec.add(loginType); //33
			datavec.add(Facility); //34
			datavec.add("Course"); //35
			datavec.add("Computer"); //36
			datavec.add("Txn_No"); //37
			datavec.add("other_CId"); //38 
			datavec.add(communityselectionForm.getStatus()); //39
			
			datavec.add(communityselectionForm.getSpouseName());//40		
			datavec.add(birthdaySp);//41
			datavec.add(communityselectionForm.getChildName1());//42
			datavec.add(birthdayCh1);//43
			datavec.add(communityselectionForm.getChildName2());//44
			datavec.add(birthdayCh2);//45
			datavec.add(communityselectionForm.getChildName3());//46
			datavec.add(birthdayCh3);//47
			
			datavec.add(communityselectionForm.getVehicleType());//48
			datavec.add(communityselectionForm.getVehicleName());//49
			datavec.add(communityselectionForm.getModelYear());//50
			datavec.add(communityselectionForm.getManuFacturer());//51
			
			datavec.add(communityselectionForm.getFlatNo());//52
			datavec.add(communityselectionForm.getTowerName());//53
			datavec.add(communityselectionForm.getBuildingName());//54
			
			
			
			
			System.out.println("datavec 30082009..   Today 05092009............."+datavec);
			
			
			request.setAttribute("datavec", datavec);
			
			String strPassVal = "";
			for(int i=0; i<communityselectionForm.getUserPassword().length();i++)
			{
				strPassVal = (strPassVal.equalsIgnoreCase("")) ? "*" : strPassVal+"*";
			}
			String countryN = rootMaster.getCountryName(dataSource, Integer.parseInt(userCountry));
			System.out.println("userState1-->"+userState);
			String stateN = rootMaster.getStateName(dataSource, Integer.parseInt(userState));
			System.out.println("userState2-->"+userState);
			String cityN = rootMaster.getPlaceName(dataSource, Integer.parseInt(userCity)) ;
			System.out.println("userCity-->"+userCity);
			String communityN = "com";//rootMaster.getCompnyName(dataSource, Integer.parseInt(communityselectionForm.getCommunity()));
			
			String countryColl = rootMaster.getCountryName(dataSource, Integer.parseInt(collCountry));
			String stateColl= rootMaster.getStateName(dataSource, Integer.parseInt(collState));
			String cityColl = rootMaster.getPlaceName(dataSource, Integer.parseInt(collCity)) ;
			
			
			
			
			Vector<String> userDetailVec = new Vector<String>();
			userDetailVec.add(communityselectionForm.getUserID());
			userDetailVec.add(strPassVal);
			userDetailVec.add(Constant.Email_Sender);
			userDetailVec.add(communityselectionForm.getUserEMail());
			userDetailVec.add(communityselectionForm.getUserFirstName());
			userDetailVec.add(communityselectionForm.getUserLastName());
			userDetailVec.add(countryN);
			userDetailVec.add(stateN);
			userDetailVec.add(cityN);
			userDetailVec.add(communityN);
			userDetailVec.add(other_Cname);
			userDetailVec.add(cName);//11
			userDetailVec.add(countryColl);//12
			userDetailVec.add(stateColl);//13
			userDetailVec.add(cityColl);//14
			userDetailVec.add(strCourse);//15
			userDetailVec.add(strStream);//16
			System.out.println("userDetailVec 23082009 >>>>>"+userDetailVec);
			session.setAttribute("consumer", userDetailVec);
			result = "success";
		}
		////////System.out.println("result in action >>>>>"+result);
		System.out.println("Result==>"+result);
		return mapping.findForward(result);
				
	}
}