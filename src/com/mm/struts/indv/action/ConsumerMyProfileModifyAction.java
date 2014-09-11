package com.mm.struts.indv.action;

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


import com.mm.struts.indv.form.ConsumerMyProfileForm;


public class ConsumerMyProfileModifyAction extends Action{
	

	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ConsumerMyProfileForm indvConsumerMyProfileModifyForm = new ConsumerMyProfileForm();// TODO Auto-generated method stub
		
		HttpSession session =request.getSession();
		String userid=(String)session.getAttribute("uId");
		//System.out.println("userid................" +userid);
		int uid = Integer.parseInt(userid);	
		String companyid=(String)session.getAttribute("compId");
		//System.out.println("Companyid................" +companyid);
		int cid = Integer.parseInt(companyid);	
		String result = null;
		IndvMaster indvMaster = new IndvMaster();
		RootMaster rootMaster = new RootMaster();
		DataSource ds = getDataSource(request,"indv");
			
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
		    		System.out.println("userVec................" +userVec);
		    	
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
		    		
		    		
		    		
		    		indvConsumerMyProfileModifyForm.setUserid(userVec.elementAt(2).toString());//0
		    		System.out.println("userVec................" +userVec.elementAt(2).toString());
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
		    		
		    		indvConsumerMyProfileModifyForm.setPhotoPath(userVec.elementAt(21).toString());
		    		
		    		session.setAttribute("pid",userVec.elementAt(6).toString());
		    		session.setAttribute("sid",userVec.elementAt(7).toString());
		    		session.setAttribute("cid",userVec.elementAt(11).toString());
		    		
		    		//System.out.println("pid................" +userVec.elementAt(6).toString());
		    		//System.out.println("sid................" +userVec.elementAt(7).toString());
		    		//System.out.println("cid................" +userVec.elementAt(11).toString());
		    		
           request.setAttribute("indvConsumerMyProfileForm",indvConsumerMyProfileModifyForm);
       	       
			
			return mapping.findForward("success");
		}
		    	
			
			
			
			
			
			
			
			
			
			
			

}
