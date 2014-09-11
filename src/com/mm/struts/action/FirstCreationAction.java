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
import com.mm.struts.form.FirstCreationForm;
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
 * @struts.action path="/communityselection" name="firstCreationFrm" input="/communityselection.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/communityselection.jsp"
 * 
 */
public class FirstCreationAction extends Action {
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
	    FirstCreationForm firstCreationFrm = (FirstCreationForm) form;
	    
	    String communitId = firstCreationFrm.getCommunity();
	    String loginType = firstCreationFrm.getLoginType();
	    loginType = "Corporates";
	    ////System.out.println("communitId...!!!!!!!!!Action!!!!!!!!!!!!!!!!!!...... "+communitId);
	    ////System.out.println("loginType...!!!!!!!!!!Action!!!!!!!!!!!!!!!!!....... "+loginType);
	    
	    
	    request.setAttribute("communitId", communitId);
	    
		DataSource dataSource = getDataSource(request,"main");	
		RootMaster rootMaster= new RootMaster();
		IndvMaster indvMaster= new IndvMaster();
		Vector <String> datavec = new Vector<String>();	
		
		String strVerifyResult=rootMaster.checkLoginId(dataSource,firstCreationFrm.getUserID(),firstCreationFrm.getUserEMail(), "Consumer");
		
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
			System.out.println("Reeee "+firstCreationFrm.getTxtMobile().trim().length());
			String mobileNo="";
			if(firstCreationFrm.getTxtMobile().trim().length()>0)
			{
				String Prefix = firstCreationFrm.getTxtPrefix().trim();
				String mobile = firstCreationFrm.getTxtMobile().trim();
				mobileNo = Prefix+mobile ;
			}
				
			String date =firstCreationFrm.getCmbDate().trim();
			String month = firstCreationFrm.getCmbMonth().trim();
			String year = firstCreationFrm.getCmbYear().trim();
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
		    String tempCountry[] = firstCreationFrm.getCompCountry().split("~");
			String userCountry=tempCountry[0];
			String userState = firstCreationFrm.getCompanyState();
			String userCity = firstCreationFrm.getCompanyCity();
		
			String builderID = firstCreationFrm.getbuilder();
			System.out.println("getBuilderFacility=>"+builderID);
			String uCity = firstCreationFrm.getbuilderCity();
			System.out.println("User City=>"+uCity);
			
			String ufacility = firstCreationFrm.getbuilderFacility();
			System.out.println("Builder Facility=>"+ufacility);
			
			Vector FacDetails = rootMaster.getFacilityDetails(dataSource,ufacility);
			System.out.println("Facility Details=>"+FacDetails);
			
			
			String designation = firstCreationFrm.getbuilderDesignation();
			System.out.println("User Designation=>"+designation);
			
			String landline = firstCreationFrm.getlandLine();
			System.out.println("User landline=>"+landline);
			String landlineNo="";
			if(firstCreationFrm.getlandLine().trim().length()>0)
			{
				String LPrefix = firstCreationFrm.getlandPrefix().trim();
				String tel = firstCreationFrm.getlandLine().trim();
				landlineNo = LPrefix+tel ;
			}
			userCountry = FacDetails.elementAt(4).toString().trim();
			userState = FacDetails.elementAt(3).toString().trim();
			userCity = FacDetails.elementAt(2).toString().trim();
			String userPin = FacDetails.elementAt(5).toString().trim();
			
			request.setAttribute("FDetails", FacDetails);
			
			String CId ="1~JIITNOIDA";
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
			
			String other_Cname = "Not Mention";
			other_Cname = firstCreationFrm.getOtherCollegeName();
			String other_CId = "0";
			
			
			
			
			if(userCountry.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				userCountry=rootMaster.insertCountry(dataSource,firstCreationFrm.getTxtPrefix().toString().trim(),firstCreationFrm.getOthercompCountry().toString().trim());//return country id
				userState=rootMaster.insertState(dataSource,firstCreationFrm.getOthercompanyState().toString().trim(),userCountry);
				userCity=rootMaster.insertCity(dataSource,firstCreationFrm.getOthercompanyCity().toString().trim(),userState,userCountry);
			}
			else if(userState.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				userState=rootMaster.insertState(dataSource,firstCreationFrm.getOthercompanyState().toString().trim(),userCountry);
				userCity=rootMaster.insertCity(dataSource,firstCreationFrm.getOthercompanyCity().toString().trim(),userState,userCountry);
			}
			else if(userCity.equals("-1"))
			{	
				
				//set sity in data base and add that is in company master.
				userCity=rootMaster.insertCity(dataSource,firstCreationFrm.getOthercompanyCity().toString().trim(),userState,userCountry);
			}
			else
			{
				//nothing 
			}
		    //end of country code
			//rightId=rootMaster.getRightId(dataSource,firstCreationFrm.getCommunity());
			rightId="1";
			
			/*
			 * college country,State,City
			 */
			String collCountryAr[] = firstCreationFrm.getCollegeCountry().split("~");
			
			String collCountry=collCountryAr[0].toString().trim();
			String collState ="1"; //firstCreationFrm.getCollegeState().toString().trim();
			String collCity = "1";//firstCreationFrm.getCollegeCity().toString().trim();
			
			if(collCountry.equals("-1"))
			{	
				if(firstCreationFrm.getOtherCollegeCountry().trim().length()==0){
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
				collCountry=rootMaster.insertCountry(dataSource,firstCreationFrm.getTxtPrefix().toString().trim(),firstCreationFrm.getOtherCollegeCountry().toString().trim());//return country id
				collState=rootMaster.insertState(dataSource,firstCreationFrm.getOtherCollegeState().toString().trim(),collCountry);
				collCity=rootMaster.insertCity(dataSource,firstCreationFrm.getOtherCollegeCity().toString().trim(),collState,collCountry);
			}
			else if(collState.equals("-1"))
			{	
				if(firstCreationFrm.getOtherCollegeState().trim().length()==0){
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
				collState=rootMaster.insertState(dataSource,firstCreationFrm.getOtherCollegeState().toString().trim(),collCountry);
				collCity=rootMaster.insertCity(dataSource,firstCreationFrm.getOtherCollegeCity().toString().trim(),collState,collCountry);
			}
			else if(collCity.equals("-1"))
			{	
				if(firstCreationFrm.getOtherCollegeCity().trim().length()==0){
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
				collCity=rootMaster.insertCity(dataSource,firstCreationFrm.getOtherCollegeCity().toString().trim(),collState,collCountry);
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
			
			String strCourse1 = firstCreationFrm.getTxtCourse();
			String strStream1 = firstCreationFrm.getTxtSubject();	
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
				if(firstCreationFrm.getTxtOthCourse().trim().length()==0){
					////System.out.println(".....fffffffff........"+firstCreationFrm.getTxtOthCourse().length());
					
					ActionErrors errors = new ActionErrors();
					errors.add("citName", new ActionError("errors.user.city","course name"));
					if(!errors.isEmpty())
					{
						saveErrors(request, errors);
					}
					
					result="failure";
					return mapping.findForward(result);
				}
				strCourse = firstCreationFrm.getTxtOthCourse();
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
				strStream = firstCreationFrm.getTxtOthSubject();
				if(firstCreationFrm.getTxtOthSubject().trim().length()==0){
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
			
			
			
			//System.out.println("Spouse Name"+firstCreationFrm.getSpouseName());
			
			//set data in data vector for sending form data to root master class .
			datavec.add(builderID);//0
			datavec.add(rightId);//1
			datavec.add(firstCreationFrm.getUserID());//2
			datavec.add(firstCreationFrm.getUserPassword());//3		
			datavec.add(firstCreationFrm.getUserFirstName());//4
			datavec.add(firstCreationFrm.getUserLastName());//5
			datavec.add(firstCreationFrm.getUserEMail());//6
			datavec.add(landlineNo);//7
			datavec.add(mobileNo);//8
			datavec.add(firstCreationFrm.getTxtAddress());//9			
			datavec.add(userCountry);//10
			datavec.add(userState);//11
			datavec.add(userCity);//12
			datavec.add(userPin);//13
			datavec.add("nophoto.jpg");//14
			datavec.add("IN");//15
			datavec.add(creationDate);//16
			datavec.add(creationTime);//17
			datavec.add("0000-00-00");//18
			datavec.add("00:00:00");//19
			datavec.add("0");//20
			datavec.add("10");//21
			datavec.add(firstCreationFrm.getAboutUsCombo());//22
			datavec.add("");//23
			datavec.add(firstCreationFrm.getAnnualincomeCombo());//24
			datavec.add(birthday);//25
			datavec.add("");//26
			datavec.add(firstCreationFrm.getEducationCombo());//27
			datavec.add(firstCreationFrm.getMaritalCombo());//28
			datavec.add("Facility Manager");//29
			datavec.add("0");//30
			datavec.add(builderID);//31		
			datavec.add(firstCreationFrm.getCmbGender());//32		
			datavec.add(loginType); //33
			datavec.add(ufacility); //34
			datavec.add("Course"); //35
			datavec.add("Computer"); //36
			datavec.add("Txn_No"); //37
			datavec.add("other_CId"); //38 
			datavec.add("Facility Manager"); //39
			//datavec.add(firstCreationFrm.getStatus()); //39
			
			
			System.out.println("datavec30082009..............."+datavec);
			
			
			request.setAttribute("datavec", datavec);
			
			String strPassVal = "";
			for(int i=0; i<firstCreationFrm.getUserPassword().length();i++)
			{
				strPassVal = (strPassVal.equalsIgnoreCase("")) ? "*" : strPassVal+"*";
			}
			
			String countryN = "1";//rootMaster.getCountryName(dataSource, Integer.parseInt(userCountry));
			String stateN = "1";//rootMaster.getStateName(dataSource, Integer.parseInt(userState));
			String cityN = "1";//rootMaster.getPlaceName(dataSource, Integer.parseInt(userCity)) ;
			String communityN = "com";//rootMaster.getCompnyName(dataSource, Integer.parseInt(firstCreationFrm.getCommunity()));
			
			String countryColl = "1";//rootMaster.getCountryName(dataSource, Integer.parseInt(collCountry));
			String stateColl= "1";//rootMaster.getStateName(dataSource, Integer.parseInt(collState));
			String cityColl = "1";//rootMaster.getPlaceName(dataSource, Integer.parseInt(collCity)) ;
			
			
			
			
			Vector<String> userDetailVec = new Vector<String>();
			userDetailVec.add(firstCreationFrm.getUserID());
			userDetailVec.add(strPassVal);
			userDetailVec.add(Constant.Email_Sender);
			userDetailVec.add(firstCreationFrm.getUserEMail());
			userDetailVec.add(firstCreationFrm.getUserFirstName());
			userDetailVec.add(firstCreationFrm.getUserLastName());
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