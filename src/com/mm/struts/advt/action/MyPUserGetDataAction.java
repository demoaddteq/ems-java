package com.mm.struts.advt.action;



import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.AdvtMaster;
import com.mm.core.master.RootMaster;
import com.mm.struts.advt.form.MyPUserDetailForm;

public class MyPUserGetDataAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		MyPUserDetailForm myPUserDeatil = new MyPUserDetailForm();// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String uId =(String)session.getAttribute("uId");		
		
		int intUserId = Integer.parseInt(uId);
	
		RootMaster rootMaster = new RootMaster();
		DataSource ds = getDataSource(request,"advt");
		
		Vector UserData = rootMaster.getUserInfo(ds, intUserId);
		////////System.out.println("UserData.....My Profile......"+UserData);		
		String UserPhoneNo  = UserData.elementAt(12).toString().trim();
		String uCuntryCode = "";
		String uAreaCode = "";
		String uPhoneNo = "";
		
		if(UserPhoneNo.length()!=0){
		
		StringTokenizer st = new StringTokenizer(UserPhoneNo,"~");		
		 uCuntryCode = st.nextToken();
		 uAreaCode = st.nextToken();
		 uPhoneNo = st.nextToken();
		}
		
		
		
		String umCuntryCode ="";
		String umNo ="";
		String UserMobileNo = UserData.elementAt(10).toString().trim();
		if(UserMobileNo.length()!=0){
			StringTokenizer st1 = new StringTokenizer(UserMobileNo,"~");		
			umCuntryCode = st1.nextToken();
			umNo = st1.nextToken();
		}
		
		myPUserDeatil.setLoginName(UserData.elementAt(2).toString());
		myPUserDeatil.setFname(UserData.elementAt(4).toString());
		myPUserDeatil.setLname(UserData.elementAt(5).toString());
		myPUserDeatil.setAddress(UserData.elementAt(9).toString());
		//myprofileGetDataForm.setCity(UserData.elementAt(6).toString());
		//myprofileGetDataForm.setState(UserData.elementAt(7).toString());
		//myprofileGetDataForm.setUcountry(UserData.elementAt(11).toString());
		myPUserDeatil.setZip(UserData.elementAt(13).toString());
		myPUserDeatil.setEmail(UserData.elementAt(8).toString());
		myPUserDeatil.setTxtPPrefix(uCuntryCode);
		myPUserDeatil.setTxtUACPrefix(uAreaCode);
		myPUserDeatil.setTxtPhone(uPhoneNo);
		myPUserDeatil.setTxtMobile(umCuntryCode);
		myPUserDeatil.setMobile(umNo);
		myPUserDeatil.setRid(UserData.elementAt(1).toString());
		
		myPUserDeatil.setPhotoPath(UserData.elementAt(14).toString());
		myPUserDeatil.setShortPro(UserData.elementAt(15).toString());
		//////////System.out.println("setPhotoPath.....mPUser adt......"+UserData.elementAt(14).toString());
		
		//////////System.out.println("UserData.elementAt(1).toString().....My Profile......"+UserData.elementAt(1).toString());	
		String compId =(String)session.getAttribute("compId");
		
		
		Vector companyData = rootMaster.getCompanyDetail(ds, Integer.parseInt(compId));
		
		
		String mcat = companyData.elementAt(25).toString();
		
		
		
			
String srtMentoring[] = UserData.elementAt(16).toString().trim().split(",");
//System.out.println("srtMentoring.length...My Profile......"+srtMentoring.length);
		if(srtMentoring.length==5){
			
			myPUserDeatil.setMcata(srtMentoring[0]);
			myPUserDeatil.setMcatb(srtMentoring[1]);
			myPUserDeatil.setMcatc(srtMentoring[2]);
			myPUserDeatil.setMcatd(srtMentoring[3]);
		}
		
		String srtQue[] = UserData.elementAt(17).toString().trim().split(",");
		
		if(srtQue.length==2){
			
			myPUserDeatil.setTecquer(srtQue[0]);
			myPUserDeatil.setPerquer(srtQue[1]);
		
		}
		
		AdvtMaster am = new AdvtMaster();
		String strCompId = (String)session.getAttribute("compId");
		Vector resultVec = am.getUserCategoryDetails(ds, Integer.parseInt(strCompId.trim()), intUserId);
		if(resultVec.size()<=0)
		{
			resultVec.add("0");//0
			resultVec.add("0");//1
			resultVec.add("0");//2
			resultVec.add("0");//3
			resultVec.add("0");//4
			resultVec.add("0");//5
			resultVec.add("0");//6
			resultVec.add("0");//7
			resultVec.add("0");//8
			resultVec.add("0");//9
			resultVec.add("0");//10
		}
		
		session.setAttribute("cid", UserData.elementAt(11).toString()+"~"+uCuntryCode);
		session.setAttribute("sid", UserData.elementAt(7).toString());
		session.setAttribute("pid", UserData.elementAt(6).toString());
		session.setAttribute("depid", UserData.elementAt(19).toString());
		session.setAttribute("desid", UserData.elementAt(20).toString());
		
		int countryId = Integer.parseInt(UserData.elementAt(11).toString());
		int stateId = Integer.parseInt(UserData.elementAt(7).toString());
		int cityId = Integer.parseInt(UserData.elementAt(6).toString());
		int dep_Id = Integer.parseInt(UserData.elementAt(19).toString());
		int des_Id = Integer.parseInt(UserData.elementAt(20).toString());
		String desStrname = rootMaster.getDesName(getDataSource(request, "advt"),des_Id);	
		String depStrname = rootMaster.getDepName(getDataSource(request, "advt"),dep_Id);
		
		int caid = rootMaster.getActivFlagCountry(ds, countryId);
		int said = rootMaster.getActivFlagState(ds, stateId);
		int paid = rootMaster.getActivFlagPlace(ds, cityId);
		
		int depid = rootMaster.getEnableFlagDep(ds, dep_Id);
		int desid = rootMaster.getEnableFlagDes(ds, des_Id);
		System.out.println("result Vec in user get data Action "+dep_Id);
		String contryStr = "";
		String stateStr = "";
		String cityStr = "";
		String depStr = "";
		String desStr = "";
		if(caid==0){
			 contryStr = rootMaster.getCountryName(ds,countryId);	
			
			//////////System.out.println("otherpid....corpo............" +contryStr);
			session.setAttribute("cid","-1");
			
		}else{
			 contryStr = rootMaster.getCountryName(ds,countryId);	
		}
		if(said==0){
			 stateStr = rootMaster.getStateName(ds,stateId);	
			
			//////////System.out.println("othersid....corpo............" +stateStr);
			session.setAttribute("sid","-1");
			
		}else{
			 stateStr = rootMaster.getStateName(ds,stateId);	
		}
		if(paid==0){
			 cityStr = rootMaster.getPlaceName(ds,cityId);
			
			//////////System.out.println("othercity  name..corpo......." +cityStr);
			session.setAttribute("pid","-1");			
		}else{
			 cityStr = rootMaster.getPlaceName(ds,cityId);
		}
		if(depid==0){
			 depStr = rootMaster.getDepName(ds,dep_Id);	
			
			////System.out.println("otherpid....advt............" +contryStr);
			session.setAttribute("depid","-1");
			
		}
		if(desid==0){
			 desStr = rootMaster.getDesName(ds,des_Id);	
			
			////System.out.println("othersid....advt............" +stateStr);
			session.setAttribute("desid","-1");
			
		}		
		
		myPUserDeatil.setOthercountry(contryStr.toString());
		myPUserDeatil.setOtherstate(stateStr.toString());
		myPUserDeatil.setOthercity(cityStr.toString());
		myPUserDeatil.setOtherdepartment(depStr.toString());
		myPUserDeatil.setOtherdesignation(desStr.toString());
	
		
		
		Vector CompanyUserData = new Vector();
		
		CompanyUserData.add(UserData.elementAt(2).toString());  //0
		CompanyUserData.add(UserData.elementAt(4).toString());	//1	
		CompanyUserData.add(UserData.elementAt(5).toString());	//2	
		CompanyUserData.add(UserData.elementAt(9).toString());	//3
		CompanyUserData.add(cityStr);							//4
		CompanyUserData.add(stateStr);							//5	
		CompanyUserData.add(contryStr);							//6
		CompanyUserData.add(UserData.elementAt(13).toString());	//7
		CompanyUserData.add(UserData.elementAt(8).toString());	//8
		CompanyUserData.add(uCuntryCode);						//9	
		CompanyUserData.add(uAreaCode);							//10
		CompanyUserData.add(uPhoneNo);							//11	
		CompanyUserData.add(umCuntryCode);						//12
		CompanyUserData.add(umNo);								//13
		CompanyUserData.add(UserData.elementAt(15).toString());	//14
		CompanyUserData.add(UserData.elementAt(16).toString());	//15
		CompanyUserData.add(UserData.elementAt(17).toString());	//16
		CompanyUserData.add(desStrname);	//17
		CompanyUserData.add(depStrname);	//18
		
		request.setAttribute("CompanyUserData", CompanyUserData);	
		request.setAttribute("mcat", mcat);
		
		session.setAttribute("userCat", resultVec);
		request.setAttribute("advtMyPUserDetailForm", myPUserDeatil);
		
		return mapping.findForward("success");
	}

}
