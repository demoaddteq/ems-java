package com.mm.struts.corpo.action;

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

import com.mm.core.master.RootMaster;
import com.mm.struts.corpo.form.MyprofileForm;

public class MyprofileGteDataAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		MyprofileForm myprofileGetDataForm = new MyprofileForm();// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
		
		int intUserId = Integer.parseInt(uId);
		int intCompId = Integer.parseInt(compId);
		////////////System.out.println("intUserId....My Profile......."+intUserId);
		////////////System.out.println("intCompId....My Profile......."+intCompId);
		
	
		RootMaster rootMaster = new RootMaster();
		DataSource ds = getDataSource(request,"corpo");
		
			
		Vector companyData = rootMaster.getCompanyDetail(ds, intCompId);
		////////////System.out.println("categor....Corpo......."+companyData.elementAt(25).toString());
		
		String mcat = companyData.elementAt(25).toString();
		////////////System.out.println("categor....Corpo......."+mcat);
		
		String mcatCom[] = mcat.split(",");
		
		////////////System.out.println("length....Corpo......."+mcatCom.length);
		
		if(mcatCom[0].equalsIgnoreCase("1")){
			myprofileGetDataForm.setMcata("1");
		}
		else
		{
			myprofileGetDataForm.setMcata("0");
		}
		if(mcatCom[1].equalsIgnoreCase("2")){
			myprofileGetDataForm.setMcatb("1");
		}
		else
		{
			myprofileGetDataForm.setMcatb("0");
		}
		if(mcatCom[2].equalsIgnoreCase("3")){
			myprofileGetDataForm.setMcatc("1");
		}
		else
		{
			myprofileGetDataForm.setMcatc("0");
		}
		if(mcatCom[3].equalsIgnoreCase("4")){
			myprofileGetDataForm.setMcatd("1");
		}
		else
		{
			myprofileGetDataForm.setMcatd("0");
		}
		//myprofileGetDataForm.setMcata("1");
		//myprofileGetDataForm.setMcatb("1");
		//myprofileGetDataForm.setMcatc("1");
		//myprofileGetDataForm.setMcatd("1");
		
		String CserPhoneNo = companyData.elementAt(9).toString().trim();
		StringTokenizer stC = new StringTokenizer(CserPhoneNo,"~");		
		String CCuntryCode = stC.nextToken();
		String CAreaCode = stC.nextToken();
		String CPhoneNo = stC.nextToken();
		
		myprofileGetDataForm.setCompanySName(companyData.elementAt(0).toString());
		myprofileGetDataForm.setCompanyName(companyData.elementAt(1).toString());
		myprofileGetDataForm.setCompanyAddress1(companyData.elementAt(2).toString());
		myprofileGetDataForm.setCompanyAddress2(companyData.elementAt(3).toString());
		//myprofileGetDataForm.setCompanyCity(companyData.elementAt(4).toString());
		//myprofileGetDataForm.setCompanyState(companyData.elementAt(5).toString());
		//myprofileGetDataForm.setCcountry(companyData.elementAt(6).toString());
		myprofileGetDataForm.setCompanyPostalCode(companyData.elementAt(7).toString());
		myprofileGetDataForm.setCompanyEMail(companyData.elementAt(8).toString());		
		myprofileGetDataForm.setTxtCPPrefix(CCuntryCode);
		myprofileGetDataForm.setTxtCACPrefix(CAreaCode);
		myprofileGetDataForm.setCphone(CPhoneNo);
		
		session.setAttribute("pid",companyData.elementAt(4).toString());
		session.setAttribute("sid",companyData.elementAt(5).toString());
		session.setAttribute("cid",companyData.elementAt(6).toString());
		
		int countryId = Integer.parseInt(companyData.elementAt(6).toString());
		int stateId = Integer.parseInt(companyData.elementAt(5).toString());
		int cityId = Integer.parseInt(companyData.elementAt(4).toString());
		
		int caid = rootMaster.getActivFlagCountry(ds, countryId);
		int said = rootMaster.getActivFlagState(ds, stateId);
		int paid = rootMaster.getActivFlagPlace(ds, cityId);
		
		String contryStr = "";
		String stateStr = "";
		String cityStr = "";
		
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
			
			////////System.out.println("othercity  name..corpo......." +cityStr);
			session.setAttribute("pid","-1");
			
		}else{
			cityStr = rootMaster.getPlaceName(ds,cityId);
		}
			
		
		myprofileGetDataForm.setOthercountry(contryStr.toString());
		myprofileGetDataForm.setOtherstate(stateStr.toString());
		myprofileGetDataForm.setOthercity(cityStr.toString());
		myprofileGetDataForm.setLogoPath(companyData.elementAt(26).toString().trim());
		myprofileGetDataForm.setShortPro(companyData.elementAt(27).toString().trim());
		
		
		Vector CompanyData = new Vector();
		
		CompanyData.add(companyData.elementAt(0).toString());  //0
		CompanyData.add(companyData.elementAt(1).toString());	//1	
		CompanyData.add(companyData.elementAt(2).toString());	//2	
		CompanyData.add(companyData.elementAt(3).toString());	//3
		CompanyData.add(cityStr);							//4
		CompanyData.add(stateStr);							//5	
		CompanyData.add(contryStr);							//6
		CompanyData.add(companyData.elementAt(7).toString());	//7
		CompanyData.add(companyData.elementAt(8).toString());	//8
		CompanyData.add(CCuntryCode);						//9	
		CompanyData.add(CAreaCode);							//10
		CompanyData.add(CPhoneNo);							//11	
		CompanyData.add(companyData.elementAt(27).toString());							//12	
		CompanyData.add(mcatCom[0]);							//13	
		CompanyData.add(mcatCom[1]);							//14	
		CompanyData.add(mcatCom[2]);							//15	
		CompanyData.add(mcatCom[3]);							//16	
		
		
		
		
		session.setAttribute("CompanyData", CompanyData);	
		
		
		request.setAttribute("corpoMyprofileForm", myprofileGetDataForm);
		
		return mapping.findForward("success");
	}

}