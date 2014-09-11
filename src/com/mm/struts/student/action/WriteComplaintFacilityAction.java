package com.mm.struts.student.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.sql.DataSource;
import com.mm.core.master.*;
import java.util.*;


import com.mm.struts.student.form.WriteComplaintForm;


public class WriteComplaintFacilityAction extends Action{
	

	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println("BBBB");
		WriteComplaintForm cmplainForm = new WriteComplaintForm();// TODO Auto-generated method stub
		
		HttpSession session =request.getSession();
		String userid=(String)session.getAttribute("uId");
				
		System.out.println("AAAAAAAAAAAAAAAAAAAAAA................" +userid);
		//int uid = Integer.parseInt(userid);
		RootMaster rootMaster = new RootMaster();
		DataSource ds = getDataSource(request,"student");
		
	//	Vector myVector=rootMaster.getfacilityname(ds,userid);
		
		/*
		String companyid=(String)session.getAttribute("compId");
		//////System.out.println("Companyid................" +companyid);
		int cid = Integer.parseInt(companyid);	
		String result = null;
		IndvMaster indvMaster = new IndvMaster();
		RootMaster rootMaster = new RootMaster();
		DataSource ds = getDataSource(request,"student");
			
		/*
		String country = indvConsumerMyProfileModifyForm.getCountry().toString().trim();
		String countryArr[] = country.split("~");
		String otherCountry = indvConsumerMyProfileModifyForm.getOthercountry().toString().trim();
		String state = indvConsumerMyProfileModifyForm.getState().toString().trim();
		String otherState = indvConsumerMyProfileModifyForm.getOtherstate().toString().trim();
		String city = indvConsumerMyProfileModifyForm.getCity().toString().trim();
		String otherCity = indvConsumerMyProfileModifyForm.getOthercity().toString().trim();
		
		String country="", state="",city="",txtPrefix="",txtMobile="";   
		String date="0",month="0",year="0";
		
		Vector userVec = indvMaster.getUserInfo(ds,uid);
		session.setAttribute("detailVec", userVec);
					
		for(int i=0;i<30;i++){
		    		System.out.println("userVec.By Me.......--"+ i+"--........" +userVec.elementAt(i));
		}
		    		if(userVec.elementAt(10).toString().length()>0)
		    		{
		    			String mobile = (userVec.elementAt(10).toString());
		    			StringTokenizer stMobile = new StringTokenizer(mobile,"~");	
		    			txtPrefix = stMobile.nextToken();
		    			txtMobile = stMobile.nextToken();
		    		}
		    		if(userVec.elementAt(15).toString().length()>0)
		    		{
		    			String borthDate = (userVec.elementAt(15).toString());
		    			StringTokenizer stBirth = new StringTokenizer(borthDate,"-");
		    			year = stBirth.nextToken();
		    			month = stBirth.nextToken();
		    			date = stBirth.nextToken();
		    		}
		    		
		    		
		    		
		    		cmplainForm.setUserid(userVec.elementAt(2).toString());//0
		    		////System.out.println("userVec................" +userVec.elementAt(2).toString());
		    		cmplainForm.setPassword(userVec.elementAt(3).toString());//1
		    		cmplainForm.setUserfname(userVec.elementAt(4).toString());//2
		    		cmplainForm.setUserlname(userVec.elementAt(5).toString());//3
		    		//indvConsumerMyProfileModifyForm.setCountry(userVec.elementAt(11).toString());
		    		//indvConsumerMyProfileModifyForm.setState(userVec.elementAt(7).toString());
		    		//indvConsumerMyProfileModifyForm.setCity(userVec.elementAt(6).toString());
		    		indvConsumerMyProfileModifyForm.setGender(userVec.elementAt(14).toString());//4
		    		indvConsumerMyProfileModifyForm.setUseradd(userVec.elementAt(9).toString());//5
		    		indvConsumerMyProfileModifyForm.setUserpincode(userVec.elementAt(13).toString());//9
		    		indvConsumerMyProfileModifyForm.setUseremail(userVec.elementAt(8).toString());//10
		    		indvConsumerMyProfileModifyForm.setTxtPrefix(txtPrefix);//11
		    		indvConsumerMyProfileModifyForm.setTxtMobile(txtMobile);//12
		    		indvConsumerMyProfileModifyForm.setUserDate(date);//13
		    		indvConsumerMyProfileModifyForm.setUserMonth(month);//13
		    		indvConsumerMyProfileModifyForm.setUserYear(year);//13
		    		indvConsumerMyProfileModifyForm.setEducationCombo(userVec.elementAt(20).toString());
		    		indvConsumerMyProfileModifyForm.setMaritalcombo(userVec.elementAt(16).toString());//14
		    		indvConsumerMyProfileModifyForm.setProfessioncombo(userVec.elementAt(17).toString());//15
		    		indvConsumerMyProfileModifyForm.setAnnualincomecombo(userVec.elementAt(18).toString());//16
		    		indvConsumerMyProfileModifyForm.setAboutuscombo(userVec.elementAt(19).toString());//17
		    		indvConsumerMyProfileModifyForm.setStatus(userVec.elementAt(28).toString());//16
		    		//indvConsumerMyProfileModifyForm.setOthercourse(userVec.elementAt(19).toString());//17
		    		//indvConsumerMyProfileModifyForm.setOthermajorsub(userVec.elementAt(28).toString());//16
		    		System.out.println("Companyid................" +userVec.elementAt(31).toString());
		    		System.out.println("Companyid................" +userVec.elementAt(32).toString());
		    		indvConsumerMyProfileModifyForm.setProEnable(userVec.elementAt(31).toString());
		    		indvConsumerMyProfileModifyForm.setPdfEnable(userVec.elementAt(32).toString());
		    		String CId =userVec.elementAt(22).toString();
		    		String StrArr[]= CId.split("~");
		    		int compnyid=Integer.parseInt(StrArr[0]);
		    		
		    		System.out.println("College--1===>" +compnyid+  "---StrArr[1]=>"+StrArr[1]);
		    		String Cname="";
		    		if(compnyid==0)
		    		{
		    			 compnyid=Integer.parseInt(userVec.elementAt(27).toString());
		    			 Vector name=indvMaster.getOtherCollege(ds, compnyid);
		    			 
		    			 Cname=name.elementAt(0).toString().trim();
		    		}else if(StrArr[1].equalsIgnoreCase("1"))
		    		{
		    				Cname=rootMaster.getCompnyName(ds, compnyid);
		    				System.out.println("College--2" +Cname);
		    		 }
		    		else
		    		{
		    			
		    			Cname = rootMaster.getCollegeName(ds, compnyid);
		    			System.out.println("College--3" +Cname);
		    		}
		    		
		    		indvConsumerMyProfileModifyForm.setCollegeName(Cname);//18
		    		
		    		String Fname="";
		    		Fname=rootMaster.FaciltyName(ds, uid);
		    		indvConsumerMyProfileModifyForm.setFacilityName(Fname);//22
		    		
		    		int CurId=Integer.parseInt(userVec.elementAt(23).toString());
		    		System.out.println("CurId....mmmmmmmmmm............" +CurId);
		    		Vector CourVec=indvMaster.getCourseName(ds, CurId);
		    		if(CourVec.elementAt(1).toString().equalsIgnoreCase("0"))
		    		{
		    			CurId=-1;
		    			indvConsumerMyProfileModifyForm.setOthercourse(CourVec.elementAt(0).toString());//19
		    		}else
		    		{
		    			indvConsumerMyProfileModifyForm.setCourseAtte(String.valueOf(CurId));//19
		    		}
		    		
		    		
		    		
		    		int CurStr=Integer.parseInt(userVec.elementAt(24).toString());
		    		Vector CurStrVec=indvMaster.getStreamName(ds, CurStr);
		    		
		    		if(CurStrVec.elementAt(1).toString().equalsIgnoreCase("0"))
		    		{
		    			CurStr=-1;
		    			indvConsumerMyProfileModifyForm.setOthermajorsub(CurStrVec.elementAt(0).toString());//19
		    		}else
		    		{
		    			indvConsumerMyProfileModifyForm.setMajorsub(String.valueOf(CurStr));//19
		    		}
		    		Vector NameVec=new Vector();
		    		NameVec.add(CourVec.elementAt(0).toString());
		    		NameVec.add(CurStrVec.elementAt(0).toString());
		    		
		    		request.setAttribute("NameVec",NameVec);
		    		session.setAttribute("Courid",String.valueOf(CurId));
		    		session.setAttribute("Subid",String.valueOf(CurStr));
		    		
		    		indvConsumerMyProfileModifyForm.setAdmiNo(userVec.elementAt(25).toString());//21
		    		
		    		
		    		indvConsumerMyProfileModifyForm.setPhotoPath(userVec.elementAt(21).toString());
		    		
		    		
		    		System.out.println("Status-2 In Java Action==>" +userVec.elementAt(28).toString());
		    		indvConsumerMyProfileModifyForm.setStatus(userVec.elementAt(28).toString());
		    		
		    		session.setAttribute("pid",userVec.elementAt(6).toString());
		    		session.setAttribute("sid",userVec.elementAt(7).toString());
		    		session.setAttribute("cid",userVec.elementAt(11).toString());
		    		
		    		int countryId = Integer.parseInt(userVec.elementAt(11).toString());
		    		int stateId = Integer.parseInt(userVec.elementAt(7).toString());
		    		int cityId = Integer.parseInt(userVec.elementAt(6).toString());
		    		
		    		String contryStr1 = rootMaster.getCountryName(ds,countryId);
		    		String stateStr1 = rootMaster.getStateName(ds,stateId);	
		    		String cityStr1 = rootMaster.getPlaceName(ds,cityId);
		    		
		    		int caid = rootMaster.getActivFlagCountry(ds, countryId);
		    		int said = rootMaster.getActivFlagState(ds, stateId);
		    		int paid = rootMaster.getActivFlagPlace(ds, cityId);
		    		
		    		String contryStr = "";
		    		String stateStr = "";
		    		String cityStr = "";
		    		
		    		if((caid==0)&(countryId!=0)){
		    			 contryStr = rootMaster.getCountryName(ds,countryId);	
		    			
		    			//System.out.println("pid....mmmmmmmmmm............" +contryStr);
		    			session.setAttribute("cid","-1");
		    			
		    		}
		    		if((said==0)&(stateId!=0)){
		    			 stateStr = rootMaster.getStateName(ds,stateId);	
		    			
		    			//System.out.println("sid....mmmmmmmm............" +stateStr);
		    			session.setAttribute("sid","-1");
		    			
		    		}
		    		if((paid==0)&(cityId!=0)){
		    			 cityStr = rootMaster.getPlaceName(ds,cityId);
		    			
		    			//System.out.println("othercity  name........." +cityStr);
		    			session.setAttribute("pid","-1");
		    			
		    		}
		    		
		    		Vector CSCVec =new Vector();
		    		
		    		
		    		if((caid!=0)&&(said!=0)&&(paid!=0))
		    		{
		    			CSCVec.add(contryStr1);
			    		CSCVec.add(stateStr1);
			    		CSCVec.add(cityStr1);
		    		}
		    		else if((caid!=0)&&(said!=0))
		    		{
		    			CSCVec.add(contryStr1);
			    		CSCVec.add(stateStr1);
			    		CSCVec.add(cityStr);
		    		}
		    		else if(caid!=0)
		    		{
		    			CSCVec.add(contryStr1);
			    		CSCVec.add(stateStr);
			    		CSCVec.add(cityStr);
			    		
		    		}
		    		else
		    		{
		    			CSCVec.add(contryStr);
			    		CSCVec.add(stateStr);
			    		CSCVec.add(cityStr);
		    		}
		    		
		    		request.setAttribute("placeVec",CSCVec);
		    		
		    		indvConsumerMyProfileModifyForm.setOthercountry(contryStr.toString());
		    		indvConsumerMyProfileModifyForm.setOtherstate(stateStr.toString());
		    		indvConsumerMyProfileModifyForm.setOthercity(cityStr.toString());
		    		
		*/    		
		   cmplainForm.setQtype("Technical");
		   
		   
//		    cmplainForm.setFacility((String)myVector.elementAt(0));                     
		
           request.setAttribute("WriteComplaityntFormFacility",cmplainForm);
       	       
			
			return mapping.findForward("success");
		}
		    	
			
			
			
			
			
			
			
			
			
			
			

}
