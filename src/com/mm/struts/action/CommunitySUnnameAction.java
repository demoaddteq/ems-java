package com.mm.struts.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.core.master.*;
import javax.servlet.http.HttpSession;
import com.mm.core.resource.Constant;
import com.mm.core.resource.MailText;
import com.mm.core.resource.Resource;

import com.mm.struts.form.CommunitySUnnameForm;

public class CommunitySUnnameAction extends Action {	
	
	
public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
	HttpSession session =request.getSession();	
	
	System.out.println("Ajay111111111111111111");
	if(((String)session.getAttribute("flagforSr")).equalsIgnoreCase("1"))
	{
		
	
	CommunitySUnnameForm communitySUnnameForm = (CommunitySUnnameForm) form;
	
	//String CompId = (request.getParameter("compId")!=null) ? request.getParameter("compId") : "0";
	//System.out.println("compId......CommunitySUnnameAction..............");
	
	
	////////System.out.println("team......CommunitySUnnameAction.............."+team);
DataSource ds = getDataSource(request,"main");	
EntpMaster entpMaster = new EntpMaster();
RootMaster rootMaster = new RootMaster();
String strComName = "Student";
Vector CompIdVec =entpMaster.getCompanyId(ds, strComName);
 
System.out.println("CompIdVec......."+CompIdVec);
String CompId =  CompIdVec.elementAt(0).toString();
String rid=rootMaster.getRightId(ds, CompId);
	
	Vector resultVec = entpMaster.getCommunityUnnamedMap( getDataSource(request, "main") ,Integer.parseInt(CompId));		
	System.out.println("resultVec//////CommunitySUnnameAction////////"+resultVec);
	System.out.println("Check For Values=>"+communitySUnnameForm.getValue40());
	
	Vector infoVec = new Vector();
	
	infoVec.add(CompId);
	infoVec.add(rid);
	infoVec.add(communitySUnnameForm.getValue2());
	infoVec.add(communitySUnnameForm.getValue3());
	infoVec.add(communitySUnnameForm.getValue4());
	infoVec.add(communitySUnnameForm.getValue5());
	infoVec.add(communitySUnnameForm.getValue6());
	infoVec.add(communitySUnnameForm.getValue7());
	infoVec.add(communitySUnnameForm.getValue8());
	infoVec.add(communitySUnnameForm.getValue9());
	infoVec.add(communitySUnnameForm.getValue10());
	infoVec.add(communitySUnnameForm.getValue11());
	infoVec.add(communitySUnnameForm.getValue12());
	infoVec.add(communitySUnnameForm.getValue13());
	infoVec.add(communitySUnnameForm.getValue14());
	infoVec.add(communitySUnnameForm.getValue15());
	infoVec.add(communitySUnnameForm.getValue16());
	infoVec.add(communitySUnnameForm.getValue17());
	infoVec.add(communitySUnnameForm.getValue18());
	infoVec.add(communitySUnnameForm.getValue19());
	infoVec.add(communitySUnnameForm.getValue20());
	infoVec.add(communitySUnnameForm.getValue21());
	infoVec.add(communitySUnnameForm.getValue22());
	infoVec.add(communitySUnnameForm.getValue23());
	infoVec.add(communitySUnnameForm.getValue24());
	infoVec.add(communitySUnnameForm.getValue25());
	infoVec.add(communitySUnnameForm.getValue26());
	infoVec.add(communitySUnnameForm.getValue27());
	infoVec.add(communitySUnnameForm.getValue28());
	infoVec.add(communitySUnnameForm.getValue29());
	infoVec.add(communitySUnnameForm.getValue30());
	infoVec.add(communitySUnnameForm.getValue31());
	infoVec.add(communitySUnnameForm.getValue32());
	infoVec.add(communitySUnnameForm.getValue33());
	infoVec.add(communitySUnnameForm.getValue34());
	infoVec.add(communitySUnnameForm.getValue35());
	infoVec.add(communitySUnnameForm.getValue36());
	infoVec.add(communitySUnnameForm.getValue37());
	infoVec.add(communitySUnnameForm.getValue38());
	infoVec.add(communitySUnnameForm.getValue39());
	//System.out.println("Spose=>"+communitySUnnameForm.getValue40());
	infoVec.add(communitySUnnameForm.getValue40());
	infoVec.add(communitySUnnameForm.getValue41());
	infoVec.add(communitySUnnameForm.getValue42());
	infoVec.add(communitySUnnameForm.getValue43());
	infoVec.add(communitySUnnameForm.getValue44());
	infoVec.add(communitySUnnameForm.getValue45());
	infoVec.add(communitySUnnameForm.getValue46());
	infoVec.add(communitySUnnameForm.getValue47());
	infoVec.add(communitySUnnameForm.getValue48());
	infoVec.add(communitySUnnameForm.getValue49());
	infoVec.add(communitySUnnameForm.getValue50());
	infoVec.add(communitySUnnameForm.getValue51());
	infoVec.add(communitySUnnameForm.getValue52());
	infoVec.add(communitySUnnameForm.getValue53());
	infoVec.add(communitySUnnameForm.getValue54());
	
	request.setAttribute("datavec", infoVec);
	request.setAttribute("communitId", CompId);	
	
	int i = 0;
	
	if(resultVec.size()!=0){
		ActionErrors errors = new ActionErrors();
		
		for(int j=1;j<36;j++){
			
			System.out.println("///////......inside For..@@@@@@@@@@@@@@@.....//////////.........."+j);
	
		String fieldName = resultVec.elementAt(i++).toString();
		String fieldType = resultVec.elementAt(i++).toString();
		String fieldVal = resultVec.elementAt(i++).toString();
		String reqiredfield = resultVec.elementAt(i++).toString();
		String visibleField = resultVec.elementAt(i++).toString();
		
		String field = "Field";
		
		field = field+j;
		
		if(visibleField.equalsIgnoreCase("1"))
			{
			if(reqiredfield.equalsIgnoreCase("1"))
  			{										
				
				String teamp = "";
				if(j==1){teamp = communitySUnnameForm.getField1();}
				else if(j==2){teamp = communitySUnnameForm.getField2();}
				else if(j==3){teamp = communitySUnnameForm.getField3();}
				else if(j==4){teamp = communitySUnnameForm.getField4();}
				else if(j==5){teamp = communitySUnnameForm.getField5();}
				else if(j==6){teamp = communitySUnnameForm.getField6();}
				else if(j==7){teamp = communitySUnnameForm.getField7();}
				else if(j==8){teamp = communitySUnnameForm.getField8();}
				else if(j==9){teamp = communitySUnnameForm.getField9();}
				else if(j==10){teamp = communitySUnnameForm.getField10();}
				else if(j==11){teamp = communitySUnnameForm.getField11();}
				else if(j==12){teamp = communitySUnnameForm.getField12();}
				else if(j==13){teamp = communitySUnnameForm.getField13();}
				else if(j==14){teamp = communitySUnnameForm.getField14();}
				else if(j==15){teamp = communitySUnnameForm.getField15();}
				else if(j==16){teamp = communitySUnnameForm.getField16();}
				else if(j==17){teamp = communitySUnnameForm.getField17();}
				else if(j==18){teamp = communitySUnnameForm.getField18();}
				else if(j==19){teamp = communitySUnnameForm.getField19();}
				else if(j==20){teamp = communitySUnnameForm.getField20();}
				else if(j==21){teamp = communitySUnnameForm.getField21();}
				else if(j==22){teamp = communitySUnnameForm.getField22();}
				else if(j==23){teamp = communitySUnnameForm.getField23();}
				else if(j==24){teamp = communitySUnnameForm.getField24();}
				else if(j==25){teamp = communitySUnnameForm.getField25();}
				else if(j==26){teamp = communitySUnnameForm.getField26();}
				else if(j==27){teamp = communitySUnnameForm.getField27();}
				else if(j==28){teamp = communitySUnnameForm.getField28();}
				else if(j==29){teamp = communitySUnnameForm.getField29();}
				else if(j==30){teamp = communitySUnnameForm.getField30();}
				else if(j==31){teamp = communitySUnnameForm.getField31();}
				else if(j==32){teamp = communitySUnnameForm.getField32();}
				else if(j==33){teamp = communitySUnnameForm.getField33();}
				else if(j==34){teamp = communitySUnnameForm.getField34();}
				else if(j==35){teamp = communitySUnnameForm.getField35();}
				System.out.println("///j////......inside For.......//////////.........."+j);
				System.out.println("///teamp////......inside For.......//////////.........."+teamp);
				
				if(teamp.length()==0){
					System.out.println("///////......inside IF...222...//////////..........");
					errors.clear();
		          	errors.add("myPreferencesFailure", new ActionError("errors.myPreferences.modiyFailure",fieldName));
		          	saveErrors(request, errors);
					System.out.println("///////......failure ..@@@@@@@@@@@@@@@.....//////////..........");
					return mapping.findForward("failure");
				}
				
				
				
								
  			}
			
			}
		}
		
		
		}	
	
	
	
	////////System.out.println("datavec......CommunitySUnnameAction.............."+infoVec);
	////////System.out.println("datavec Size......CommunitySUnnameAction.............."+infoVec.size());
	
	DataSource dataSource = getDataSource(request,"main");
	
	String result ="success";
	String result1 ="success";
	System.out.println("infoVec +Spouse..............."+infoVec);
	result = rootMaster.insertUserInfo(dataSource,infoVec);
	
	System.out.println("In Login Action result "+result);
	
	if(result.equalsIgnoreCase("success")){
		Resource resr = new Resource();
		int userId = rootMaster.getUserId(dataSource, communitySUnnameForm.getValue2().toString());
		System.out.println("CSUN 2508-2.............."+userId);
		result1 = rootMaster.insertUserResidentInfo(dataSource,infoVec,userId);
		//insertUserResidentInfo(ds,dataVec);
	}
	System.out.println("result1--> 05092009-->"+result1);
	
	if(result1.equalsIgnoreCase("success")){
		Resource resr = new Resource();
		int userId = rootMaster.getUserId(dataSource, communitySUnnameForm.getValue2().toString());
		System.out.println("CSUN 2508-2.............."+userId);
		
		//int userId = rootMaster.getUserId(dataSource, communitySUnnameForm.getValue2().toString());
		//System.out.println("CSUN 2508-2.............."+userId);
		result1 = rootMaster.insertCosumerServiceMap(dataSource,infoVec,userId);
		//insertUserResidentInfo(ds,dataVec);
	}
	/*
	if(result.equalsIgnoreCase("success")){
	Resource resr = new Resource();
	MailText mt = new MailText();
	String strMailText = "";
	
	strMailText = mt.getETextForConsumerReg(communitySUnnameForm.getValue2(), communitySUnnameForm.getValue3(), communitySUnnameForm.getValue4(), communitySUnnameForm.getValue5());
    System.out.println("strMailText Email text");
	String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender, communitySUnnameForm.getValue6(), Constant.Email_Title_For_UCreation);
	System.out.println("strMailText Email text after");
	////System.out.println("strMailText Email text"+strMailText);	
	////System.out.println("str Email Status"+strEmailStatus);		
	}
	*/
	int userId = rootMaster.getUserId(dataSource, communitySUnnameForm.getValue2().toString());
	System.out.println("userId......CommunitySUnnameAction.............."+userId);
	
	///////////////Un Named/////////////
	if(!communitySUnnameForm.getField1().equalsIgnoreCase("nodataUnName")){
	
	Vector datavecUnname = new Vector();
	datavecUnname.add(userId);
	datavecUnname.add(CompId);	
	datavecUnname.add(communitySUnnameForm.getField1());
	datavecUnname.add(communitySUnnameForm.getField2());
	datavecUnname.add(communitySUnnameForm.getField3());
	datavecUnname.add(communitySUnnameForm.getField4());
	datavecUnname.add(communitySUnnameForm.getField5());
	datavecUnname.add(communitySUnnameForm.getField6());
	datavecUnname.add(communitySUnnameForm.getField7());
	datavecUnname.add(communitySUnnameForm.getField8());
	datavecUnname.add(communitySUnnameForm.getField9());
	datavecUnname.add(communitySUnnameForm.getField10());
	datavecUnname.add(communitySUnnameForm.getField11());
	datavecUnname.add(communitySUnnameForm.getField12());
	datavecUnname.add(communitySUnnameForm.getField13());
	datavecUnname.add(communitySUnnameForm.getField14());
	datavecUnname.add(communitySUnnameForm.getField15());
	datavecUnname.add(communitySUnnameForm.getField16());
	datavecUnname.add(communitySUnnameForm.getField17());
	datavecUnname.add(communitySUnnameForm.getField18());
	datavecUnname.add(communitySUnnameForm.getField19());
	datavecUnname.add(communitySUnnameForm.getField20());
	datavecUnname.add(communitySUnnameForm.getField21());
	datavecUnname.add(communitySUnnameForm.getField22());
	datavecUnname.add(communitySUnnameForm.getField23());
	datavecUnname.add(communitySUnnameForm.getField24());
	datavecUnname.add(communitySUnnameForm.getField25());
	datavecUnname.add(communitySUnnameForm.getField26());
	datavecUnname.add(communitySUnnameForm.getField27());
	datavecUnname.add(communitySUnnameForm.getField28());
	datavecUnname.add(communitySUnnameForm.getField29());
	datavecUnname.add(communitySUnnameForm.getField30());
	datavecUnname.add(communitySUnnameForm.getField31());
	datavecUnname.add(communitySUnnameForm.getField32());
	datavecUnname.add(communitySUnnameForm.getField33());
	datavecUnname.add(communitySUnnameForm.getField34());
	datavecUnname.add(communitySUnnameForm.getField35());
	System.out.println("datavecUnname......CommunitySUnnameAction.............."+datavecUnname);
	////////System.out.println("datavecUnname Size......CommunitySUnnameAction.............."+datavecUnname.size());	
	if(result.equalsIgnoreCase("success")){
		
		 result = rootMaster.insertUserUnNameInfo(dataSource,datavecUnname);
		
	}
	}
	
	/////////end/////////
	
	/*
	 * For insert Education detail of Student
	 */
	
Vector <Vector>educationVecFinal = new Vector<Vector>();
	
	Vector educationVec = new Vector();
	Vector educationVec1 = new Vector();
	Vector educationVec2 = new Vector();
	Vector educationVec3 = new Vector();
	Vector educationVec4 = new Vector();
	Vector educationVec5 = new Vector();
	
	String passOutY =communitySUnnameForm.getPassyear().toString();
	System.out.println("passOutY..........."+passOutY);
	if(passOutY.equalsIgnoreCase("")){
		passOutY ="0,0";
		//System.out.println("passOutY..........."+passOutY);
	}
	
	String Msub =communitySUnnameForm.getMsub().toString();
	if(Msub.equalsIgnoreCase("")){
		Msub ="0";
	}
	
	educationVec.add(communitySUnnameForm.getCoures());
	educationVec.add(communitySUnnameForm.getCollname());
	educationVec.add(Msub);
	educationVec.add(passOutY);
	educationVec.add(communitySUnnameForm.getAdmno());
	educationVec.add(communitySUnnameForm.getCgpa());
	educationVec.add(communitySUnnameForm.getCouresOth());
	
	String passOutY1 =communitySUnnameForm.getPassyear1().toString();
	if(passOutY1.equalsIgnoreCase("")){
		passOutY1 ="0,0";
	}
	String Msub1 =communitySUnnameForm.getMsub1().toString();
	if(Msub1.equalsIgnoreCase("")){
		Msub1 ="0";
	}
	
	educationVec1.add(communitySUnnameForm.getCoures1());
	educationVec1.add(communitySUnnameForm.getCollname1());
	educationVec1.add(Msub1);
	educationVec1.add(passOutY1);
	educationVec1.add(communitySUnnameForm.getAdmno1());
	educationVec1.add(communitySUnnameForm.getCgpa1());
	educationVec1.add(communitySUnnameForm.getCoures1Oth());
	
	String passOutY2 =communitySUnnameForm.getPassyear2().toString();
	if(passOutY2.equalsIgnoreCase("")){
		passOutY2 ="0,0";
	}
	String Msub2 =communitySUnnameForm.getMsub2().toString();
	if(Msub2.equalsIgnoreCase("")){
		Msub2 ="0";
	}
	
	educationVec2.add(communitySUnnameForm.getCoures2());
	educationVec2.add(communitySUnnameForm.getCollname2());
	educationVec2.add(Msub2);
	educationVec2.add(passOutY2);
	educationVec2.add(communitySUnnameForm.getAdmno2());
	educationVec2.add(communitySUnnameForm.getCgpa2());
	educationVec2.add(communitySUnnameForm.getCoures2Oth());
	
	String passOutY3 =communitySUnnameForm.getPassyear3().toString();
	if(passOutY3.equalsIgnoreCase("")){
		passOutY3 ="0,0";
	}
	String Msub3 =communitySUnnameForm.getMsub3().toString();
	if(Msub3.equalsIgnoreCase("")){
		Msub3 ="0";
	}
	
	educationVec3.add(communitySUnnameForm.getCoures3());
	educationVec3.add(communitySUnnameForm.getCollname3());
	educationVec3.add(Msub3);
	educationVec3.add(passOutY3);
	educationVec3.add(communitySUnnameForm.getAdmno3());
	educationVec3.add(communitySUnnameForm.getCgpa3());
	educationVec3.add(communitySUnnameForm.getCoures3Oth());
	
	String passOutY4 =communitySUnnameForm.getPassyear4().toString();
	if(passOutY4.equalsIgnoreCase("")){
		passOutY4 ="0,0";
	}
	String Msub4 =communitySUnnameForm.getMsub4().toString();
	if(Msub4.equalsIgnoreCase("")){
		Msub4 ="0";
	}
	
	educationVec4.add(communitySUnnameForm.getCoures4());
	educationVec4.add(communitySUnnameForm.getCollname4());
	educationVec4.add(Msub4);
	educationVec4.add(passOutY4);
	educationVec4.add(communitySUnnameForm.getAdmno4());
	educationVec4.add(communitySUnnameForm.getCgpa4());
	educationVec4.add(communitySUnnameForm.getCoures4Oth());
	
	String passOutY5 =communitySUnnameForm.getPassyear5().toString();
	if(passOutY5.equalsIgnoreCase("")){
		passOutY5 ="0,0";
	}
	String Msub5 =communitySUnnameForm.getMsub5().toString();
	if(Msub5.equalsIgnoreCase("")){
		Msub5 ="0";
	}
	
	educationVec5.add(communitySUnnameForm.getCoures5());
	educationVec5.add(communitySUnnameForm.getCollname5());
	educationVec5.add(Msub5);
	educationVec5.add(passOutY5);
	educationVec5.add(communitySUnnameForm.getAdmno5());
	educationVec5.add(communitySUnnameForm.getCgpa5());
	educationVec5.add(communitySUnnameForm.getCoures5Oth());
	
	/*if(!communitySUnnameForm.getCoures().equalsIgnoreCase("0")){
		educationVecFinal.add(educationVec);
	}
	if(!communitySUnnameForm.getCoures1().equalsIgnoreCase("0")){
		educationVecFinal.add(educationVec1);
	}
	if(!communitySUnnameForm.getCoures2().equalsIgnoreCase("0")){
		educationVecFinal.add(educationVec2);
	}
	if(!communitySUnnameForm.getCoures3().equalsIgnoreCase("0")){
		educationVecFinal.add(educationVec3);
	}
	if(!communitySUnnameForm.getCoures4().equalsIgnoreCase("0")){
		educationVecFinal.add(educationVec4);
	}
	if(!communitySUnnameForm.getCoures5().equalsIgnoreCase("0")){
		educationVecFinal.add(educationVec5);
	}*/
	
	
	educationVecFinal.add(educationVec);
	educationVecFinal.add(educationVec1);
	educationVecFinal.add(educationVec2);
	educationVecFinal.add(educationVec3);
	educationVecFinal.add(educationVec4);
	educationVecFinal.add(educationVec5);
	
	
	System.out.println("educationVecFinal........"+educationVecFinal);
	for(int j=0; j <educationVecFinal.size(); j++ ){
		
		Vector teampVec = (Vector)educationVecFinal.elementAt(j);
		result1="Success";
		System.out.println("teampVec........"+teampVec);
		result1 = rootMaster.insertEducationInfo(dataSource, teampVec, String.valueOf(userId));
		System.out.println("result1........"+result1);
	}
	
	/*
	 * 
	 */
	if(result.equalsIgnoreCase("success")){
		session.setAttribute("flagforSr","0");
		
	}
	return mapping.findForward(result);
	 
}else{
	
	return mapping.findForward("sessionFail");
}
		/*return mapping.findForward(result);
	*/

}
}
