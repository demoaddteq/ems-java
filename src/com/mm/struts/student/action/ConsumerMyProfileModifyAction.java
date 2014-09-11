
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


import com.mm.struts.student.form.ConsumerMyProfileForm;


public class ConsumerMyProfileModifyAction extends Action{
	

	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ConsumerMyProfileForm indvConsumerMyProfileModifyForm = new ConsumerMyProfileForm();// TODO Auto-generated method stub
		
		HttpSession session =request.getSession();
		String userid=(String)session.getAttribute("uId");
		int uid = Integer.parseInt(userid);	
		String companyid=(String)session.getAttribute("compId");
		int cid = Integer.parseInt("1");	
		String result = null;
		IndvMaster indvMaster = new IndvMaster();
		RootMaster rootMaster = new RootMaster();
		DataSource ds = getDataSource(request,"student");
		
		String spanId=(String)request.getParameter("spanId");
		String pgTask=(String)request.getParameter("pgTask");
		System.out.println("spanId-11->"+spanId);
		System.out.println("pgTask-22->"+pgTask);
		request.setAttribute("spanId",spanId);
	
			
		/*
		String country = indvConsumerMyProfileModifyForm.getCountry().toString().trim();
		String countryArr[] = country.split("~");
		String otherCountry = indvConsumerMyProfileModifyForm.getOthercountry().toString().trim();
		String state = indvConsumerMyProfileModifyForm.getState().toString().trim();
		String otherState = indvConsumerMyProfileModifyForm.getOtherstate().toString().trim();
		String city = indvConsumerMyProfileModifyForm.getCity().toString().trim();
		String otherCity = indvConsumerMyProfileModifyForm.getOthercity().toString().trim();
		*/
		String country="", state="",city="",txtPrefix="",txtMobile="";   
		String date="0",month="0",year="0";
		
		Vector userVec = indvMaster.getUserInfo(ds,uid);
		Vector residentDetailVec = indvMaster.getResInfo(ds,uid);
		session.setAttribute("detailVec", userVec);
		session.setAttribute("resDetailVec", residentDetailVec);
			
				
		    		//if(userVec.elementAt(10).toString().length()>0)
		    		//{
		    			//String mobile = (userVec.elementAt(10).toString());
		    			//StringTokenizer stMobile = new StringTokenizer(mobile,"~");	
		    			//txtPrefix = stMobile.nextToken();
		    			//txtMobile = stMobile.nextToken();
		    		//}
		    		if(userVec.elementAt(15).toString().length()>0)
		    		{
		    			String borthDate = (userVec.elementAt(15).toString());
		    			StringTokenizer stBirth = new StringTokenizer(borthDate,"-");
		    			year = stBirth.nextToken();
		    			month = stBirth.nextToken();
		    			date = stBirth.nextToken();
		    		}
		    		
		    		
		    		
		    		indvConsumerMyProfileModifyForm.setUserid(userVec.elementAt(2).toString());//0
		    		indvConsumerMyProfileModifyForm.setPassword(userVec.elementAt(3).toString());//1
		    		indvConsumerMyProfileModifyForm.setUserfname(userVec.elementAt(4).toString());//2
		    		indvConsumerMyProfileModifyForm.setUserlname(userVec.elementAt(5).toString());//3
		    		//indvConsumerMyProfileModifyForm.setCountry(userVec.elementAt(11).toString());
		    		//indvConsumerMyProfileModifyForm.setState(userVec.elementAt(7).toString());
		    		//indvConsumerMyProfileModifyForm.setCity(userVec.elementAt(6).toString());
		    		indvConsumerMyProfileModifyForm.setGender(userVec.elementAt(14).toString());//4
		    		indvConsumerMyProfileModifyForm.setUseradd(userVec.elementAt(9).toString());//5
		    		indvConsumerMyProfileModifyForm.setUserpincode(userVec.elementAt(13).toString());//9
		    		indvConsumerMyProfileModifyForm.setUseremail(userVec.elementAt(8).toString());//10
		    		indvConsumerMyProfileModifyForm.setTxtPrefix("+91");//11
		    		indvConsumerMyProfileModifyForm.setTxtMobile(userVec.elementAt(10).toString());//12
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
		    		indvConsumerMyProfileModifyForm.setProEnable(userVec.elementAt(31).toString());
		    		indvConsumerMyProfileModifyForm.setPdfEnable(userVec.elementAt(32).toString());
		    		String CId =userVec.elementAt(22).toString();
		    		String StrArr[]= CId.split("~");
		    		int compnyid=Integer.parseInt(StrArr[0]);
		    		
		    		String Cname="NA";
		    	/*	if(compnyid==0)
		    		{
		    			 compnyid=Integer.parseInt("1");
		    			 Vector name=indvMaster.getOtherCollege(ds, compnyid);
		    			 
		    			 Cname=name.elementAt(0).toString().trim();
		    		}else if(StrArr[1].equalsIgnoreCase("1"))
		    		{
		    				Cname=rootMaster.getCompnyName(ds, compnyid);
		    		 }
		    		else
		    		{
		    			
		    			Cname = rootMaster.getCollegeName(ds, compnyid);
		    		}
		    	*/	
		    		indvConsumerMyProfileModifyForm.setCollegeName(Cname);//18
		    		
		    		String Fname="";
		    		Fname=rootMaster.FaciltyName(ds, uid);
		    		indvConsumerMyProfileModifyForm.setFacilityName(Fname);//22
		    			
		    		int CurId=Integer.parseInt("1");
		    		Vector CourVec=indvMaster.getCourseName(ds, CurId);
		    		if(CourVec.elementAt(1).toString().equalsIgnoreCase("0"))
		    		{
		    			CurId=-1;
		    			indvConsumerMyProfileModifyForm.setOthercourse(CourVec.elementAt(0).toString());//19
		    		}else
		    		{
		    			indvConsumerMyProfileModifyForm.setCourseAtte(String.valueOf(CurId));//19
		    		}
		    		
		    		
		    		
		    		int CurStr=Integer.parseInt("1");
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
		    		
		    		
		    		
		    		indvConsumerMyProfileModifyForm.setStatus(userVec.elementAt(28).toString());
		    		
		    		session.setAttribute("pid",userVec.elementAt(6).toString());
		    		session.setAttribute("sid",userVec.elementAt(7).toString());
		    		session.setAttribute("cid",userVec.elementAt(11).toString());
		    		
		    		int countryId = Integer.parseInt("1");
		    		int stateId = Integer.parseInt("1");
		    		int cityId = Integer.parseInt("1");
		    		
		    		/**Breaking Spouse Date Of Birth Into Date Month And  Year**/
		    		
		    		String spDate="0",spMonth="0",spYear="0";
		    		String ch1Date="0",ch1Month="0",ch1Year="0";
		    		String ch2Date="0",ch2Month="0",ch2Year="0";
		    		String ch3Date="0",ch3Month="0",ch3Year="0";
		    		
		  		if(residentDetailVec.elementAt(1).toString().length()>0)
		    		{
		    			String spBirthDate = (residentDetailVec.elementAt(1).toString());
		    			StringTokenizer spBdt = new StringTokenizer(spBirthDate,"-");
		    			spYear = spBdt.nextToken();
		    			spMonth = spBdt.nextToken();
		    			spDate = spBdt.nextToken();
		    		}
		    		if(residentDetailVec.elementAt(3).toString().length()>0)
		    		{
		    			String ch1BirthDate = (residentDetailVec.elementAt(3).toString());
		    			StringTokenizer ch1Bdt = new StringTokenizer(ch1BirthDate,"-");
		    			ch1Year = ch1Bdt.nextToken();
		    			ch1Month = ch1Bdt.nextToken();
		    			ch1Date = ch1Bdt.nextToken();
		    		}
		    		if(residentDetailVec.elementAt(5).toString().length()>0)
		    		{
		    			String ch2BirthDate = (residentDetailVec.elementAt(5).toString());
		    			StringTokenizer ch2Bdt = new StringTokenizer(ch2BirthDate,"-");
		    			ch2Year = ch2Bdt.nextToken();
		    			ch2Month = ch2Bdt.nextToken();
		    			ch2Date = ch2Bdt.nextToken();
		    		}
		    		
		    		if(residentDetailVec.elementAt(7).toString().length()>0)
		    		{
		    			String ch3BirthDate = (residentDetailVec.elementAt(7).toString());
		    			StringTokenizer ch3Bdt = new StringTokenizer(ch3BirthDate,"-");
		    			ch3Year = ch3Bdt.nextToken();
		    			ch3Month = ch3Bdt.nextToken();
		    			ch3Date = ch3Bdt.nextToken();
		    		}
		    					    		
		    		String contryStr1 = rootMaster.getCountryName(ds,countryId);
		    		String stateStr1 = rootMaster.getStateName(ds,stateId);	
		    		String cityStr1 = rootMaster.getPlaceName(ds,cityId);
		    		
		    		
		    		
		    		/** Set Data For resident Detail Start**/
		    		
		    		indvConsumerMyProfileModifyForm.setSpouseName(residentDetailVec.elementAt(0).toString());//0
		    		//indvConsumerMyProfileModifyForm.setDOBSp(residentDetailVec.elementAt(1).toString());//1
		    		//indvConsumerMyProfileModifyForm.setUserYear(year);
		    		indvConsumerMyProfileModifyForm.setSpouseDate(spDate);
		    		indvConsumerMyProfileModifyForm.setSpouseMonth(spMonth);
		    		indvConsumerMyProfileModifyForm.setSpouseYear(spYear);
		    		indvConsumerMyProfileModifyForm.setChildName1(residentDetailVec.elementAt(2).toString());//2
		    		
		    		indvConsumerMyProfileModifyForm.setChild1Date(ch1Date);
		    		indvConsumerMyProfileModifyForm.setChild1Month(ch1Month);
		    		indvConsumerMyProfileModifyForm.setChild1Year(ch1Year);
		    		
		    		//indvConsumerMyProfileModifyForm.setDOBCh1(residentDetailVec.elementAt(3).toString());//3
		    		
		    		indvConsumerMyProfileModifyForm.setChildName2(residentDetailVec.elementAt(4).toString());//4
		    		
		    		//indvConsumerMyProfileModifyForm.setDOBCh2(residentDetailVec.elementAt(5).toString());
		    		
		    		indvConsumerMyProfileModifyForm.setChild2Date(ch2Date);
		    		indvConsumerMyProfileModifyForm.setChild2Month(ch2Month);
		    		indvConsumerMyProfileModifyForm.setChild2Year(ch2Year);
		    		
		    		indvConsumerMyProfileModifyForm.setChildName3(residentDetailVec.elementAt(6).toString());//6
		    		
		    		indvConsumerMyProfileModifyForm.setChild3Date(ch3Date);
		    		indvConsumerMyProfileModifyForm.setChild3Month(ch3Month);
		    		indvConsumerMyProfileModifyForm.setChild3Year(ch3Year);
		    		
		    		//indvConsumerMyProfileModifyForm.setDOBCh3(residentDetailVec.elementAt(7).toString());//7
		    		indvConsumerMyProfileModifyForm.setBuildingName(residentDetailVec.elementAt(8).toString());//8
		    		indvConsumerMyProfileModifyForm.setTowerName(residentDetailVec.elementAt(9).toString());//9
		    		indvConsumerMyProfileModifyForm.setFlatNo(residentDetailVec.elementAt(10).toString());//10
		    		indvConsumerMyProfileModifyForm.setVehicleType(residentDetailVec.elementAt(11).toString());//11
		    		indvConsumerMyProfileModifyForm.setVehicleName(residentDetailVec.elementAt(12).toString());//12
		    		indvConsumerMyProfileModifyForm.setModelYear(residentDetailVec.elementAt(13).toString());//13
		    		indvConsumerMyProfileModifyForm.setManuFacturer(residentDetailVec.elementAt(14).toString());//14
		    		/** Set Data For resident Detail End**/
		    		
		    		
		    		
		    		
		    		
		    		int caid = rootMaster.getActivFlagCountry(ds, countryId);
		    		int said = rootMaster.getActivFlagState(ds, stateId);
		    		int paid = rootMaster.getActivFlagPlace(ds, cityId);
		    		
		    		String contryStr = "";
		    		String stateStr = "";
		    		String cityStr = "";
		    		
		    		if((caid==0)&(countryId!=0)){
		    			 contryStr = rootMaster.getCountryName(ds,countryId);	
		    			
		    			session.setAttribute("cid","-1");
		    			
		    		}
		    		if((said==0)&(stateId!=0)){
		    			 stateStr = rootMaster.getStateName(ds,stateId);	
		    			
		    			session.setAttribute("sid","-1");
		    			
		    		}
		    		if((paid==0)&(cityId!=0)){
		    			 cityStr = rootMaster.getPlaceName(ds,cityId);
		    			
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
		    		
		    		
           request.setAttribute("stdConsumerMyProfileForm",indvConsumerMyProfileModifyForm);
       	       
			return mapping.findForward("success");
		}
		    	
			
			
			
			
			
			
			
			
			
			
			

}