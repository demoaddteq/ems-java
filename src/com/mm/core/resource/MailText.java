/**
 * 
 * 
 */
package com.mm.core.resource;

import java.util.Vector;

/**
 * @author arun
 *
 */
public class MailText 
{
	public String getETextForConsumerReg(String strUName, String strPass, String strFname, String strLName)
	{
		String strResult = "Dear" + " " + strFname + " " + strLName + "," + "\n\n";

		strResult = strResult + "You have done it ! Before you start reaching out to the world for building your career, take following steps\n\n";		
		strResult = strResult + "1.	We have sent you this mail for authentication. Click on the below given link to activate your account. http://www.campusyogi.com/campusyogi/activate.jsp?uName="+strUName+"\n\n";		
	    strResult = strResult + "2.	Once activated, go to your profile, using your login and password and reply questions which reflects your personality. This is an unique opportunity for you to show the potential recruiter about yourself, your personality, your aptitude, your likes & dislikes before an actual interview.\n\n";
		strResult = strResult + "3.	Walk through the test and discover your personality. Once you discover your personality out of nine types according to Enneagram(it is a scientific method of classifying people into nine personality types).Trust us, it would be easier for you to face the world and get your dream job.\n\n";
		strResult = strResult + "4.	By now, your qualification, your vision and type of personality will be visible to the companies. You can also start posting your querys as blogs on the site, apply for summer training & choose a mentor. Be sure that you will receive the replies for your specific querys from campus yogi panel and of course from the corporate managers too.\n\n\n";
		strResult = strResult + "Your registration details are as under\n\n\n";		
		strResult = strResult + "Username: "+strUName+"\n";
		strResult = strResult + "Password: "+strPass+" \n";
		strResult = strResult + "First Name: "+ strFname +"\n";
		strResult = strResult + "Last Name: "+ strLName +"\n\n\n";		
		strResult = strResult + "Here are some of the links, which will help you go forward\n\n\n";		
		strResult = strResult + "1. Your login id is "+strUName+" and password is "+strPass+"\n\n";		
		strResult = strResult + "2. If you have any problem on the site, send email at \"response@campusyogi.com\" \n\n";		
		strResult = strResult + "3. If you have suggestions or querys, write to us at \"feedback@campusyogi.com\" Like it??? Let us rock… get connected with the corporate world of your choice and let them groom you and feel the difference… click here to login to your account.With love and best wishes \n\n";		
		
		strResult = strResult + "Campusyogi Team\n\n";
		
		return strResult;
	}
	public String getETextForAdvtReg(String strUName, String strPass, String strFname, String strLName)
	{
	  	String strResult = "Dear" + " "+ strFname + " ," + "\n\n\n";

		strResult = strResult + "Welcome to Campusyogi.\n\n\n You have been nominated as an administrator/facilitator by your organization to handle online query(s) and request(s) and manage user account(s).\n\n\n";
		strResult = strResult + "You can now log on to the system and respond to query(s) , request(s) or blog(s) by students, colleges or other companies, using the following information:\n\n\n";		
		strResult = strResult + "Username: " + strUName + "\n";
		strResult = strResult + "Password: "+ strPass +" \n";
		strResult = strResult + "First Name: "+ strFname +"\n";
		strResult = strResult + "Last Name: "+ strLName +"\n\n\n";
		strResult = strResult + "In case of any problem, please contact us at response@campusyogi.com \n\n";
		strResult = strResult + "Thank you for registering.\n\n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
	
	public String getETextForAdvtUserReg(String strUName, String strPass, String strFname, String strLName)
	{
	  	String strResult = "Dear" + " "+ strFname + " ," + "\n\n\n";

		strResult = strResult + "Welcome to Campusyogi.\n\n\n";
		strResult = strResult + "You can now log on to the system and respond to query(s) , request(s) or blog(s) by students, colleges or other companies, using the following information:\n\n\n";		
		strResult = strResult + "Username: " + strUName + "\n";
		strResult = strResult + "Password: "+ strPass +" \n";
		strResult = strResult + "First Name: "+ strFname +"\n";
		strResult = strResult + "Last Name: "+ strLName +"\n\n\n";
		strResult = strResult + "In case of any problem, please contact us at response@campusyogi.com \n\n";
		strResult = strResult + "Thank you for registering.\n\n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
	
	public String getETextForCollegeReg(String strUName, String strPass, String strFname, String strLName)
	{
	  	String strResult = "Dear" + " "+ strFname + " ," + "\n\n";

	  	strResult = strResult + "Thanks you for registering with campusyogi. You can log on using your Id and password \n\n\n";
		strResult = strResult + "Start coordinating with campusyogi by searching for companies as a Potential employees for your students. Arrange campus recruitment Programme at a click.\n\n";		
		strResult = strResult + "By default you are the administrator, so you can manage whole multiuser account by creating new one,deleting old one and assigning rights to them \n\n\n";			
	    strResult = strResult + "Your login details are \n\n\n";
		strResult = strResult + "Username: " + strUName + "\n";
		strResult = strResult + "Password: "+ strPass +" \n";
		strResult = strResult + "First Name: "+ strFname +"\n";
		strResult = strResult + "Last Name: "+ strLName +"\n\n\n";
		strResult = strResult + "In case of any problem, please contact us at response@campusyogi.com \n\n";
		strResult = strResult + "Thanks & Regards \n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
	
	public String getETextForCollegeUserReg(String strUName, String strPass, String strFname, String strLName)
	{
	  	String strResult = "Dear" + " "+ strFname + " ," + "\n\n";

	  	strResult = strResult + "Thanks you for registering with campusyogi. You can log on using your Id and password \n\n\n";
		strResult = strResult + "Start coordinating with campusyogi by searching for companies as a Potential employees for your students. Arrange campus recruitment Programme at a click.\n\n";		
		strResult = strResult + "Your login details are \n\n\n";
		strResult = strResult + "Username: " + strUName + "\n";
		strResult = strResult + "Password: "+ strPass +" \n";
		strResult = strResult + "First Name: "+ strFname +"\n";
		strResult = strResult + "Last Name: "+ strLName +"\n\n\n";
		strResult = strResult + "In case of any problem, please contact us at response@campusyogi.com \n\n";
		strResult = strResult + "Thanks & Regards \n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
	
	public String getETextForEntpReg(String strUName, String strPass, String strFname, String strLName)
	{
	  	String strResult = "Dear" + " "+ strFname + " ," + "\n\n";

	  	strResult = strResult + "Welcome to Campusyogi.\n\n\n You have been nominated as an administrator/facilitator by your organization to handle online query(s) and request(s) and manage user account(s).\n\n\n";
		strResult = strResult + "You can now log on to the system and respond to query(s) , request(s) or blog(s) by students, colleges or other companies, using the following information:\n\n\n";		
		strResult = strResult + "Username: " + strUName + "\n";
		strResult = strResult + "Password: "+ strPass +" \n";
		strResult = strResult + "First Name: "+ strFname +"\n";
		strResult = strResult + "Last Name: "+ strLName +"\n\n\n";
		strResult = strResult + "In case of any problem, please contact us at response@campusyogi.com \n\n";
		strResult = strResult + "Thank you for registering.\n\n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
	public String getETextForIndvReg(String strUName, String strPass, String strFname, String strLName)
	{
	  	String strResult = "Dear" + " "+ strFname + " ," + "\n\n";

		strResult = strResult + "Welcome to Campus Yogi, India`s only online consumer redressal system. \n\n";
		strResult = strResult + "Providers (Brands) of products and services can benefit from us by using our platform for online redressal of complaints lodged by their customers. Our goal is to ensure that consumer complaints are acknowledged and resolved at decision-making level at all the Brands.\n\n";
		strResult = strResult + "You have been nominated as an administrator/facilitator by your organization to handle online query redressal and manage user account(s).\n\n";
	    strResult = strResult + "You can now logon to the system and respond to the complaints against your product(s)/service(s) using the following information: \n\n\n";
		strResult = strResult + "Username: " + strUName + "\n\n";
		strResult = strResult + "Password: "+ strPass +" \n\n";
		strResult = strResult + "First Name: "+ strFname +"\n\n";
		strResult = strResult + "Last Name: "+ strLName +"\n\n\n";
		strResult = strResult + "In case of any problem, please contact us at response@campusyogi.com \n\n";
		strResult = strResult + "Thanks for registration. \n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
	public String getETextForFogetPass(String strUname, Vector dataVec)
	{
		String fname = dataVec.elementAt(0).toString().trim();
		String lname = dataVec.elementAt(1).toString().trim();
		String pass = dataVec.elementAt(2).toString().trim();
		
		String strResult = "Dear" + " " + fname+" "+lname + "," + "\n\n";
		strResult = strResult + "Your Login Details are as follows:\n\n";
		strResult = strResult + "Username: " +  strUname + "\n";
		strResult = strResult + "Password: " + pass + "\n\n";
		strResult = strResult + "For any support, please contact us at response@campusyogi.com\n\n\n";
		strResult = strResult + "Warm Regards!\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
	
	public String getETextForCoreNewComplaint(Vector dataVec,String fcomId , String mailAlert ,String consumerName, String compId)
	{ 	
		//System.out.println("compId......."+compId);
	 	////////System.out.println("dataVec[';;;;;;;;;;;;';';"+dataVec.size());
		
	 	String fname = dataVec.elementAt(0).toString().trim();
		String lname = dataVec.elementAt(1).toString().trim();
		String name = fname+" "+lname;
		
		String ui = dataVec.elementAt(3).toString().trim();
		String logintpe = dataVec.elementAt(5).toString().trim();
		
		
		//System.out.println("logintpe......."+logintpe);
		
		//Advertiser, Corporates, Consultants, Student
		
		String strResult = "Dear "+ name+ ","+ "\n\n";
		strResult = strResult + " query reference Id is "+ fcomId + ".\n\n";
		strResult = strResult + ""+ mailAlert + " "+ "\n\n";
		
		if(logintpe.equalsIgnoreCase("Advertiser")){
			
			strResult = strResult + "http://www.campusyogi.com/campusyogi/brandLogin.jsp?qtype=qoery&ui="+ui+"&compId="+compId+"\n\n";
			
		}else if (logintpe.equalsIgnoreCase("Corporates")){
			
			strResult = strResult + "http://www.campusyogi.com/campusyogi/corporatesLogin.jsp?qtype=qoery&ui="+ui+"&compId="+compId+"\n\n";	
		}
		else if (logintpe.equalsIgnoreCase("Consultants")){
			
			strResult = strResult + "http://www.campusyogi.com/campusyogi/consultantPreLogin.jsp?qtype=qoery&ui="+ui+"&compId="+compId+"\n\n";	
		}
		
		
		strResult = strResult + "For any support, please contact us at response@campusyogi.com \n\n\n";
		strResult = strResult + "Thanks & Regards\n\n";
		strResult = strResult + consumerName;
		
		return strResult;
	}
	
	public String getTextForConsumer(String Uname, String fcomId)
	{
		String strResult = "Dear " + Uname + " "+",\n\n";
		strResult = strResult + "Thanks for registering the query with us. Your query reference Id is "+ fcomId + ". Kindly use the query Id for all your future references.\n\n";
		strResult = strResult + "Campus Yogi, has forwarded your query to the respective person.\n\n";
		strResult = strResult + "In case of any problem, please contact us at  response@campusyogi.com \n\n\n\n Regards\n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
	
	public String getMailtextReminder(String Uname,String  fcomplainId,String tagId, String CompnyName, String addres1,String addres2,String strCity, String strState,String strCountry, String strZip, String  strPhone)
	{
		String arrNameVal[] = Uname.toString().trim().split(" ");
		String strPhoneTeamp = strPhone.replace("~", "-");
		
		String strResult ="<strong>"+ Uname + ","+"</strong>"+" \n";
		strResult = strResult + "<strong>"+ CompnyName + "</strong>"+ "\n";
		strResult = strResult + "<strong>"+ addres1 + "</strong>"+ "\n";
		if(addres2.length()!= 0){
		strResult = strResult + "<strong>"+ addres2 + "</strong>"+ "\n";
		}
		strResult = strResult + "<strong>"+ strCity + ", "+ strState + ", "+ strCountry + "</strong>"+ "\n";
		strResult = strResult + "<strong>Pin: "+ strZip + "</strong>"+ "\n";
		if(strPhone.length()!= 0){
			strResult = strResult + "<strong>Phone No: "+ strPhoneTeamp +". "+"</strong>"+ "\n\n\n\n";
			}
		
		strResult = strResult + "<strong>Dear  "+ arrNameVal[0] + ","+"</strong>" +"\n\n";
		strResult = strResult + "We are in receipt of a query against your brand" + ", ";
		if(tagId.equalsIgnoreCase("2"))
		{
		strResult = strResult + "query reference Id is <strong>"+ fcomplainId + "</strong>."+ "\n\n";
		strResult = strResult + "If you are a registered user with Campus Yogi, you can log-on to our system using your login details to access the ";
		strResult = strResult + "query and respond accordingly. Alternatively you can register with <a href=\"//www.campusyogi.com\">campusyogi.com</a> \"in Brand\'s section\"\n ( <a href=\"http://72.18.205.154:8080/advtlogin.jsp\">http://72.18.205.154:8080/advtlogin.jsp</a> ) as new user.\n\n";
		strResult = strResult + "Kindly use the query Id for all future references.\n\n";
		strResult = strResult + "Campus Yogi, an initiative supported by Ministry of Consumer Affairs, to facilitate redressal of complaints. ";
		strResult = strResult + "Campus Yogi mediates on behalf of the consumers with the provider of products / services to get their grievance redressed amicably. \n\n";
		strResult = strResult + "We are confident that you will realize the value of the customer, and try your best to resolve this query ";
		strResult = strResult + "amicably at the earliest. \n\n";
		strResult = strResult + "We would appreciate your response within 7 days. \n\n";
		}
		if(tagId.equalsIgnoreCase("3"))
		{
		strResult = strResult + "query reference Id <strong>"+ fcomplainId + "</strong> and forwarded the same to you.";
		strResult = strResult + "It is regrettable that there has been no response to the query from an organization of your repute.\n\n";
		strResult = strResult + "If you are a registered user with Campus Yogi, you can logon to our system using your login details to access ";
		strResult = strResult + "the query and respond. use the same linc as New query.\n\n";
		strResult = strResult + "Kindly use the query Id for all future references. \n\n";
	
		strResult = strResult + "We would request you to respond to this matter at the earliest. If you need any further information from the consumer, ";
		strResult = strResult + "you can mention the same in your response. \n\n";
		strResult = strResult + "We are confident that you understand the importance of immediate resolution of the customer query, ";
		strResult = strResult + "as it not only satisfies your customer but also enhance the brand value. \n\n";
		strResult = strResult + "In spite of our reminder, if you fail to respond within a fortnight of this reminder, ";
		strResult = strResult + "we will have no other option but to make the information about your inaction public and to advise the complainant to initiate legal action.\n\n";
		strResult = strResult + "We are confident that you will realize the value of the consumer, and try your best to resolve this query ";
		strResult = strResult + "amicably at the earliest. \n\n";

		strResult = strResult + "We would appreciate your response within next 7 days. \n\n";
		}
		if(tagId.equalsIgnoreCase("4"))
		{
		strResult = strResult + "query reference Id <strong>"+ fcomplainId + "</strong> Which was forwarded to you. ";
		strResult = strResult + "We observe with regret that you have not responded to our earlier reminders concerning query .\n\n";
		strResult = strResult + "We had provided you ample opportunity to hear your viewpoint on the issue, however you have chosen not to respond to the query. ";
		strResult = strResult + "This suggests that there is no intent on your part to resolve the query. Under the circumstances, we will be left with no option ";
		strResult = strResult + "but to advise the complainant to seek legal recourse.  \n\n";
		strResult = strResult + "We are still hopeful of an immediate action from your end to resolve the query to avoid complainant’s legal suit. \n\n";
		strResult = strResult + "We look forward to your immediate response. \n\n";
		}
		
		
		return strResult;
	}
	
	public String getMailtextReminder(String Uname,String  fcomplainId,String tagId)
	{
		String strResult ="<strong>Dear</strong> "+ Uname + ","+ "\n";	
		strResult = strResult + "We are in receipt of a query against your brand" + ", ";
		if(tagId.equalsIgnoreCase("2"))
		{
		strResult = strResult + "query reference Id is <strong>"+ fcomplainId + "</strong>."+ "\n\n";
		strResult = strResult + "If you are a registered user with Campus Yogi, you can log-on to our system using your login details to access the ";
		strResult = strResult + "query and respond accordingly. Alternatively you can register with <a href=\"//www.core.nic.in\">core.nic.in</a> \"in Brand\'s section\"\n ( <a href=\"http://72.18.205.154:8080/advtlogin.jsp\">http://72.18.205.154:8080/advtlogin.jsp</a> ) as new user.\n\n";
		strResult = strResult + "Kindly use the query Id for all future references.\n\n";
		strResult = strResult + "Campus Yogi, an initiative supported by Ministry of Consumer Affairs, to facilitate redressal of complaints. ";
		strResult = strResult + "Campus Yogi mediates on behalf of the consumers with the provider of products / services to get their grievance redressed amicably. \n\n";
		strResult = strResult + "We are confident that you will realize the value of the customer, and try your best to resolve this query ";
		strResult = strResult + "amicably at the earliest. \n\n";
		strResult = strResult + "We would appreciate your response within 7 days. \n\n";
		}
		if(tagId.equalsIgnoreCase("3"))
		{
		strResult = strResult + "query reference Id <strong>"+ fcomplainId + "</strong> and forwarded the same to you.";
		strResult = strResult + "It is regrettable that there has been no response to the query from an organization of your repute.\n\n";
		strResult = strResult + "If you are a registered user with Campus Yogi, you can logon to our system using your login details to access ";
		strResult = strResult + "the query and respond. use the same linc as New query.\n\n";
		strResult = strResult + "Kindly use the query Id for all future references. \n\n";
	
		strResult = strResult + "We would request you to respond to this matter at the earliest. If you need any further information from the consumer, ";
		strResult = strResult + "you can mention the same in your response. \n\n";
		strResult = strResult + "We are confident that you understand the importance of immediate resolution of the customer query, ";
		strResult = strResult + "as it not only satisfies your customer but also enhance the brand value. \n\n";
		strResult = strResult + "In spite of our reminder, if you fail to respond within a fortnight of this reminder, ";
		strResult = strResult + "we will have no other option but to make the information about your inaction public and to advise the complainant to initiate legal action.\n\n";
		strResult = strResult + "We are confident that you will realize the value of the consumer, and try your best to resolve this query ";
		strResult = strResult + "amicably at the earliest. \n\n";

		strResult = strResult + "We would appreciate your response within next 7 days. \n\n";
		}
		if(tagId.equalsIgnoreCase("4"))
		{
		strResult = strResult + "query reference Id <strong>"+ fcomplainId + "</strong> Which was forwarded to you. ";
		strResult = strResult + "We observe with regret that you have not responded to our earlier reminders concerning query .\n\n";
		strResult = strResult + "We had provided you ample opportunity to hear your viewpoint on the issue, however you have chosen not to respond to the query. ";
		strResult = strResult + "This suggests that there is no intent on your part to resolve the query. Under the circumstances, we will be left with no option ";
		strResult = strResult + "but to advise the complainant to seek legal recourse.  \n\n";
		strResult = strResult + "We are still hopeful of an immediate action from your end to resolve the query to avoid complainant’s legal suit. \n\n";
		strResult = strResult + "We look forward to your immediate response. \n\n";
		}
		
		
		return strResult;
	}
	
	public String getMailtextMail(String  fcomplainId,String tagId)
	{
		String strResult ="";
		strResult = strResult + "We are in receipt of a query against your brand" + ", ";
		if(tagId.equalsIgnoreCase("2"))
		{
			strResult = strResult + "query reference Id is "+ fcomplainId + "."+ "\n\n";
			strResult = strResult + "If you are a registered user with Campus Yogi, you can log-on to our system using your login details to access the ";
			strResult = strResult + "query and respond accordingly. Alternatively you can register with core.nic.in in \"brand's section\"(http://72.18.205.154:8080/advtlogin.jsp) as new user.\n\n";
			strResult = strResult + "Kindly use the query Id for all future references.\n\n";
			strResult = strResult + "Campus Yogi, an initiative supported by Ministry of Consumer Affairs, to facilitate redressal of complaints.";
			strResult = strResult + "Campus Yogi mediates on behalf of the consumers with the provider of products / services to get their grievance redressed amicably. \n\n";
			strResult = strResult + "We are confident that you will realize the value of the customer, and try your best to resolve this query ";
			strResult = strResult + "amicably at the earliest. \n\n";
			strResult = strResult + "We would appreciate your response within 7 days. \n\n";
		}
		if(tagId.equalsIgnoreCase("3"))
		{
			strResult = strResult + "query reference Id "+ fcomplainId + " and forwarded the same to you.\n\n";
			strResult = strResult + "It is regrettable that there has been no response to the query from an organization of your repute.\n\n";
			strResult = strResult + "If you are a registered user with Campus Yogi, you can logon to our system using your login details to access ";
			strResult = strResult + "the query and respond. use the same linc as New query.\n\n";
			strResult = strResult + "Kindly use the query Id for all future references. \n\n";
		
			strResult = strResult + "We would request you to respond to this matter at the earliest. If you need any further information from the consumer, ";
			strResult = strResult + "you can mention the same in your response. \n\n";
			strResult = strResult + "We are confident that you understand the importance of immediate resolution of the customer query, ";
			strResult = strResult + "as it not only satisfies your customer but also enhance the brand value. \n\n";
			strResult = strResult + "In spite of our reminder, if you fail to respond within a fortnight of this reminder, ";
			strResult = strResult + "we will have no other option but to make the information about your inaction public and to advise the complainant to initiate legal action.\n\n";
			strResult = strResult + "We are confident that you will realize the value of the consumer, and try your best to resolve this query ";
			strResult = strResult + "amicably at the earliest. \n\n";

			strResult = strResult + "We would appreciate your response within next 7 days. \n\n";
		}
		if(tagId.equalsIgnoreCase("4"))
		{
			strResult = strResult + "query reference Id "+ fcomplainId + " and forwarded the same to you.";
			strResult = strResult + "We observe with regret that you have not responded to our earlier reminders concerning query .\n\n";
			strResult = strResult + "We had provided you ample opportunity to hear your viewpoint on the issue, however you have chosen not to respond to the query.";
			strResult = strResult + "This suggests that there is no intent on your part to resolve the query. Under the circumstances, we will be left with no option ";
			strResult = strResult + "but to advise the complainant to seek legal recourse.  \n\n";
			strResult = strResult + "We are still hopeful of an immediate action from your end to resolve the query to avoid complainant’s legal suit. \n\n";
			strResult = strResult + "We look forward to your immediate response. \n\n";
		}
		
		
		return strResult;
	}
	public String getMailtextReminderSubject(String tagId,String  fcomplainId)
	{
		String strResult ="";
		if(tagId.equalsIgnoreCase("2"))
		{
			strResult = "New query Register - query Id : "+ fcomplainId + ".";

		}
		if(tagId.equalsIgnoreCase("3"))
		{
			strResult = "First Reminder - query Id : "+ fcomplainId + ".";
		}
		if(tagId.equalsIgnoreCase("4"))
		{
			strResult = "Second Reminder - query Id : "+ fcomplainId + ".";
		}
		
		
		return strResult;
	}
	
	public String getETextForCoreChangeComplaint(Vector dataVec,String fcomId , String mailAlert ,String consumerName)
	{
		String fname = dataVec.elementAt(0).toString().trim();
		String lname = dataVec.elementAt(1).toString().trim();
		String name = fname+" "+lname;
		String strResult = "Dear "+ name+ ","+ "\n\n";
		strResult = strResult + " query reference Id is "+ fcomId + ".\n\n";
		strResult = strResult + ""+ mailAlert + " "+ "\n\n";
		strResult = strResult + "For any support, please contact us at response@campusyogi.com \n\n\n";
		strResult = strResult + "Thanks & Regards\n\n";
		strResult = strResult + consumerName;
		return strResult;
	}
	
	public String getTextForConsumer(String Uname, String oldFcomId,String newFcomId)
	{
		String strResult = "Dear" + Uname + " "+",\n\n";
		strResult = strResult + "Your query reference Id was "+ oldFcomId + ". Your new query reference Id is "+ newFcomId + ". Kindly use this new query Id for all future references.  \n\n";
		strResult = strResult + "Campus Yogi, has forwarded your query to the respective person.\n\n";
		
		strResult = strResult + "In case of any problem, please contact us at  response@campusyogi.com \n\n\n\n Regards\n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
	public String getETextForBrandNewComplaint(Vector dataVec,String fcomId , String mailAlert)
	{
		String fname = dataVec.elementAt(0).toString().trim();
		String lname = dataVec.elementAt(1).toString().trim();
		String name = fname+" "+lname;
		String strResult = "Dear "+ name+ ","+ "\n\n";
		strResult = strResult + " query reference Ids are \n\n "+ fcomId + "\n\n ";
		strResult = strResult + ""+ mailAlert + " "+ "\n\n";
		strResult = strResult + "For any support, please contact us at response@campusyogi.com \n\n\n";
		strResult = strResult + "Thanks & Regards\n\n";
		return strResult;
	}
	public String getETextForCoreAllotComplaint(Vector dataVec,String fcomId , String mailAlert )
	{
		String fname = dataVec.elementAt(0).toString().trim();
		String lname = dataVec.elementAt(1).toString().trim();
		String name = fname+" "+lname;
		String strResult = "Dear "+ name+ ","+ "\n\n";
		strResult = strResult + " query reference Ids are \n\n "+ fcomId + "\n\n ";
		strResult = strResult + ""+ mailAlert + " "+ "\n\n";
		strResult = strResult + "For any support, please contact us at response@campusyogi.com \n\n\n";
		strResult = strResult + "Thanks & Regards\n\n";
		
		return strResult;
	}
	
	public String getReplyByCore(Vector dataVec,String fcomId , String mailAlert ,String Name)
	{
		String fname = dataVec.elementAt(0).toString().trim();
		String lname = dataVec.elementAt(1).toString().trim();
		String name = fname+" "+lname;
		String strResult = "Dear "+ name+ ","+ "\n\n";
		strResult = strResult + " query reference Id is "+ fcomId + ".\n\n";
		strResult = strResult + ""+ mailAlert + " "+ "\n\n";
		strResult = strResult + "For any support, please contact us at response@campusyogi.com \n\n\n";
		strResult = strResult + "Thanks & Regards\n\n";
		strResult = strResult + Name;
		return strResult;
	}
	
	public String getReplyByConsumer(Vector dataVec,String fcomId , String mailAlert ,String Name)
	{
		String fname = dataVec.elementAt(0).toString().trim();
		String lname = dataVec.elementAt(1).toString().trim();
		String name = fname+" "+lname;
		String strResult = "Dear "+ name+ ","+ "\n\n";
		strResult = strResult + " query reference Id is "+ fcomId + ".\n\n";
		strResult = strResult + ""+ mailAlert + " "+ "\n\n";
		strResult = strResult + "For any support, please contact us at response@campusyogi.com \n\n\n";
		strResult = strResult + "Thanks & Regards\n\n";
		strResult = strResult + Name;
		return strResult;
	}
	public String getReplyByBrand(Vector dataVec,String fcomId , String mailAlert ,String Name, String flag)
	{
		String fname = dataVec.elementAt(0).toString().trim();
		String lname = dataVec.elementAt(1).toString().trim();
		String name = fname+" "+lname;
		String strResult = "Dear "+ name+ ","+ "\n\n";
		
		if(flag.equalsIgnoreCase("1")){
			
			strResult = strResult + "Your mentoring request having reference id "+ fcomId + ", has been accepted by "+ Name + ". Please log on Campusyogi to view the details \n\n";
			
		}else if(flag.equalsIgnoreCase("0")){
			
			strResult = strResult + "Your mentoring request having reference id "+ fcomId + ", has been denied by "+ Name + ". Please log on Campusyogi to view the details \n\n";
			
		}else{
			strResult = strResult + "mentoring request reference Id is "+ fcomId + ".\n\n";		
			strResult = strResult + ""+ mailAlert + " "+ "\n\n";
		}
		strResult = strResult + "For any support, please contact us at response@campusyogi.com \n\n\n";
		strResult = strResult + "Thanks & Regards\n\n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		
		
		return strResult;
	}
	public String getReplyOnTrByBrand(Vector dataVec,String fcomId , String mailAlert, String brandName )
	{
		String fname = dataVec.elementAt(0).toString().trim();
		String lname = dataVec.elementAt(1).toString().trim();
		String name = fname+" "+lname;
		String strResult = "Dear "+ name+ ","+ "\n\n";
		
		
			strResult = strResult + "Traning request reference Id is "+ fcomId + ".\n\n";		
			strResult = strResult + ""+ mailAlert + " "+ "\n\n";
		
		strResult = strResult + "For any support, please contact us at response@campusyogi.com \n\n\n";
		strResult = strResult + "Thanks & Regards\n\n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		
		
		return strResult;
	}
	public String getReplyByBrand(Vector dataVec,String fcomId , String mailAlert, String brandName )
	{
		String fname = dataVec.elementAt(0).toString().trim();
		String lname = dataVec.elementAt(1).toString().trim();
		String name = fname+" "+lname;
		String strResult = "Dear "+ name+ ","+ "\n\n";
		
		
			strResult = strResult + "Request reference Id is "+ fcomId + ".\n\n";		
			strResult = strResult + ""+ mailAlert + " "+ "\n\n";
		
		strResult = strResult + "For any support, please contact us at response@campusyogi.com \n\n\n";
		strResult = strResult + "Thanks & Regards\n\n\n";
		strResult = strResult + ""+ brandName+ "\n\n";
		
		
		return strResult;
	}
	
	public String getChangedPassWord(String userName,String newPassWord,String LOGINNAME)
	{
		
		String strResult = "Dear "+ userName+ ","+ "\n\n";
		strResult = strResult + "Your Password is changed.\n\n";		
		strResult = strResult + "Your New Login Details are as follows:\n\n";		
		strResult = strResult + "Username: " +  LOGINNAME + "\n";
		strResult = strResult + "Password: " + newPassWord + "\n\n";		
		strResult = strResult + "For any support, please contact us at response@campusyogi.com \n\n\n";
		strResult = strResult + "Thanks & Regards\n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
	
	public String getETextForTrainingRequest(Vector dataVec,String fcomId , String mailAlert ,String consumerName, String compId)
	{ 	
				
	 	String fname = dataVec.elementAt(0).toString().trim();
		String lname = dataVec.elementAt(1).toString().trim();
		String name = fname+" "+lname;
		
		String ui = dataVec.elementAt(3).toString().trim();
		String logintpe = dataVec.elementAt(5).toString().trim();
		
		
		String strResult = "Dear "+ name+ ","+ "\n\n";
		strResult = strResult + "Your training request Id is "+ fcomId + ".\n\n";
		
		if(logintpe.equalsIgnoreCase("Advertiser")){
			
			strResult = strResult + "http://www.campusyogi.com/campusyogi/brandLogin.jsp?qtype=trequest&ui="+ui+"&compId="+compId+"\n\n";
			
		}else if (logintpe.equalsIgnoreCase("Corporates")){
			
			strResult = strResult + "http://www.campusyogi.com/campusyogi/corporatesLogin.jsp?qtype=trequest&ui="+ui+"&compId="+compId+"\n\n";	
		}
		else if (logintpe.equalsIgnoreCase("Consultants")){
			
			strResult = strResult + "http://www.campusyogi.com/campusyogi/consultantPreLogin.jsp?qtype=trequest&ui="+ui+"&compId="+compId+"\n\n";	
		}

		strResult = strResult + ""+ mailAlert + " "+ "\n\n";
		strResult = strResult + "For any support, please contact us at response@campusyogi.com \n\n\n";
		strResult = strResult + "Thanks & Regards\n\n";
		strResult = strResult + consumerName;
		return strResult;
	}
	
	public String getMentoringReqByStudent(Vector dataVec,String fcomId , String mailAlert ,String consumerName, String title, String StrText, String compId )
	{ 	
				
	 	String fname = dataVec.elementAt(0).toString().trim();
		String lname = dataVec.elementAt(1).toString().trim();
		String name = fname+" "+lname;
		
		String ui = dataVec.elementAt(3).toString().trim();
		String logintpe = dataVec.elementAt(5).toString().trim();
		
		
		String strResult = "Dear "+ name+ ","+ "\n\n";
		strResult = strResult + ""+ consumerName + " has sent a request for mentoring having reference id as "+ fcomId + ".\n\n";
		strResult = strResult + "Details are as follows "+ "\n";
		
		strResult = strResult + "Title: "+ title + " "+ "\n\n";
		strResult = strResult + "Text: "+ StrText + " "+ "\n\n";
		
		if(logintpe.equalsIgnoreCase("Advertiser")){
			
			strResult = strResult + "http://www.campusyogi.com/campusyogi/brandLogin.jsp?qtype=mrequest&ui="+ui+"&compId="+compId+"\n\n";
			
		}else if (logintpe.equalsIgnoreCase("Corporates")){
			
			strResult = strResult + "http://www.campusyogi.com/campusyogi/corporatesLogin.jsp?qtype=mrequest&ui="+ui+"&compId="+compId+"\n\n";	
		}
		else if (logintpe.equalsIgnoreCase("Consultants")){
			
			strResult = strResult + "http://www.campusyogi.com/campusyogi/consultantPreLogin.jsp?qtype=mrequest&ui="+ui+"&compId="+compId+"\n\n";	
		}
		
		strResult = strResult + "For any support, please contact us at response@campusyogi.com \n\n\n";
		strResult = strResult + "Regards\n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
	public String getQueriByCompany(Vector dataVec,String fcomId , String mailAlert ,String consumerName, String title, String StrText)
	{ 	
				
	 	String fname = dataVec.elementAt(0).toString().trim();
		String lname = dataVec.elementAt(1).toString().trim();
		String name = fname+" "+lname;
		String strResult = "Dear "+ name+ ","+ "\n\n";
		strResult = strResult + ""+ consumerName + " has sent a Query having reference id as "+ fcomId + ".\n\n";
		strResult = strResult + "Details are as follows "+ "\n";
		
		strResult = strResult + "Title: "+ title + " "+ "\n\n";
		strResult = strResult + "Text: "+ StrText + " "+ "\n\n";
		
		strResult = strResult + "For any support, please contact us at response@campusyogi.com \n\n\n";
		strResult = strResult + "Regards\n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
	public String getRequestByStudent(Vector dataVec,String fcomId , String mailAlert ,String consumerName)
	{ 	
				
	 	String fname = dataVec.elementAt(0).toString().trim();
		String lname = dataVec.elementAt(1).toString().trim();
		String name = fname+" "+lname;
		String strResult = "Dear "+ name+ ","+ "\n\n";
		strResult = strResult + ""+ consumerName + " has sent a request having reference id as "+ fcomId + ".\n\n";
		
		strResult = strResult + "For any support, please contact us at response@campusyogi.com \n\n\n";
		strResult = strResult + "Regards\n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
	
	public String getRequestTextForStudent(String Uname, String fcomId)
	{
		String strResult = "Dear " + Uname + " "+",\n\n";
		strResult = strResult + "Your training request id is "+ fcomId + ". Kindly use this id for all your future references.\n\n";
		strResult = strResult + "Campus Yogi, has forwarded your request to the respective person.\n\n";
		strResult = strResult + "In case of any problem, please contact us at  response@campusyogi.com \n\n\n\n Regards\n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
	
	public String getEmailTextFeedBack(String name, String emailid, String feedbacktype, String subject, String text )
	{
	  	String strResult = "Dear" + "Sir/Madam ," + "\n\n\n";

		strResult = strResult + "A Feed back is submit by "+name+"\n\n";
		strResult = strResult + "Email Id of "+name+" : " + emailid + "\n";
		strResult = strResult + "Type of FeedBack: " + feedbacktype + "\n";
		strResult = strResult + "Subject: "+ subject +" \n";		
		strResult = strResult + "Text: "+ text +"\n\n\n";
		strResult = strResult + "In case of any problem, please contact us at response@campusyogi.com \n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
	
	public String getEmailAddNew(String field, String name, String strId  )
	{
	  	String strResult = "Dear" + "Sir/Madam ," + "\n\n\n";

		strResult = strResult + "Add a new " + field + " : " + name + "\n";
		strResult = strResult + "ID : " + strId + "\n\n\n";
		
		strResult = strResult + "In case of any problem, please contact us at response@campusyogi.com \n\n";
		strResult = strResult + "Campusyogi Team\n\n";
		return strResult;
	}
}
