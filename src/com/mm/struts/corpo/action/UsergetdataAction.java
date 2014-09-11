package com.mm.struts.corpo.action;

import java.util.StringTokenizer;
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

import com.mm.core.master.AdvtMaster;
import com.mm.core.master.RootMaster;
import com.mm.struts.corpo.form.UsermodifyForm;


public class UsergetdataAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UsermodifyForm corpousermodifyForm = new UsermodifyForm();// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String strUId = (request.getParameter("userid")!=null) ? request.getParameter("userid") : "0";
		String strTask = (request.getParameter("task")!=null) ? request.getParameter("task") : "Modify";
		int UserId = Integer.parseInt(strUId);
			
		RootMaster rootMaster = new RootMaster();
		DataSource ds = getDataSource(request,"corpo");
		
		String compId =(String)session.getAttribute("compId");
		////////////System.out.println("compId............UsercreationPreAction.............."+compId);
		
		int intCompId = Integer.parseInt(compId);
		
		Vector companyData = rootMaster.getCompanyDetail(ds, intCompId);
		////////////////System.out.println("categor....Corpo......."+companyData.elementAt(25).toString());
		String mcat = companyData.elementAt(25).toString();
		////////////////System.out.println("categor....Corpo......."+mcat);		
				
		session.setAttribute("compMentorReq", mcat);
		
		Vector UserData = rootMaster.getUserInfo(ds, UserId);
			
			
		String userPhoneno = UserData.elementAt(12).toString().trim();
		String phoneCcode = "";
		String areaCode = "";
		String phoneNo = "";
		if(userPhoneno.length()!=0)
		{
		 StringTokenizer st = new StringTokenizer(userPhoneno,"~");		
		 phoneCcode = st.nextToken();
		 areaCode = st.nextToken();
		 phoneNo = st.nextToken();
		}
		String userMobileno = UserData.elementAt(10).toString().trim();
		StringTokenizer st1 = new StringTokenizer(userMobileno,"~");		
		String mobileCcode = st1.nextToken();
		String mobileNo = st1.nextToken();
		
		
		
		String srtMentoring[] = UserData.elementAt(16).toString().trim().split(",");
		
		if(srtMentoring.length==4){
			
			corpousermodifyForm.setMcata(srtMentoring[0]);
			corpousermodifyForm.setMcatb(srtMentoring[1]);
			corpousermodifyForm.setMcatc(srtMentoring[2]);
			corpousermodifyForm.setMcatd(srtMentoring[3]);
		}
		
		String srtQue[] = UserData.elementAt(17).toString().trim().split(",");
		
		if(srtQue.length==2){
			
		corpousermodifyForm.setTecquer(srtQue[0]);
		corpousermodifyForm.setPerquer(srtQue[1]);
		
		}
	
		/*datavec.add(usercreationForm.getLoginName());//0
		datavec.add(usercreationForm.getState());//1
		datavec.add(usercreationForm.getPassword());//2
		datavec.add(usercreationForm.getCity());//3
		datavec.add(usercreationForm.getRepassword());//4
		datavec.add(usercreationForm.getFname());//5
		datavec.add(usercreationForm.getEmail());//6
		datavec.add(usercreationForm.getLname());//7
		datavec.add(usercreationForm.getTxtPPrefix());//8
		datavec.add(usercreationForm.getTxtAreacode());//9
		datavec.add(usercreationForm.getTxtPhone());//10
		datavec.add(usercreationForm.getAddress());//11
		datavec.add(usercreationForm.getTxtPrefix());//12
		datavec.add(usercreationForm.getTxtMobile());//13
		datavec.add(usercreationForm.getUcountry());//14
		datavec.add(usercreationForm.getZip());//15
		
		*/
		corpousermodifyForm.setLoginName(UserData.elementAt(2).toString());			//0 setLoginName
		corpousermodifyForm.setFname(UserData.elementAt(4).toString());				//1 setFname
		corpousermodifyForm.setLname(UserData.elementAt(5).toString());				//2 setLName
		corpousermodifyForm.setPassword(UserData.elementAt(3).toString());			//3 setPassword
		//myprofileGetDataForm.setCity(UserData.elementAt(6).toString());			//4 setCity
		//myprofileGetDataForm.setState(UserData.elementAt(7).toString());			//5 setState
		//myprofileGetDataForm.setCountry(UserData.elementAt(11).toString());		//6 setCountry
		corpousermodifyForm.setZip(UserData.elementAt(13).toString());				//7 setZip
		corpousermodifyForm.setEmail(UserData.elementAt(8).toString());				//8 setEmail
		corpousermodifyForm.setTxtPPrefix(phoneCcode);								//9 setTxtPPrefix
		corpousermodifyForm.setTxtAreacode(areaCode);								//10 setTxtAreacode
		corpousermodifyForm.setTxtPhone(phoneNo);									//11 setTxtPhone
		corpousermodifyForm.setAddress(UserData.elementAt(9).toString());			//12 setAddress
		corpousermodifyForm.setTxtPrefix(mobileCcode);								//13 setTxtPrefix
		corpousermodifyForm.setTxtMobile(mobileNo);									//14 setTxtMobile
		corpousermodifyForm.setUserid(strUId);
		corpousermodifyForm.setTask(strTask);
		
		AdvtMaster am = new AdvtMaster();
		String strCompId = (String)session.getAttribute("compId");
		Vector resultVec = am.getUserCategoryDetails(ds, Integer.parseInt(strCompId.trim()), Integer.parseInt(strUId.trim()));
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
		String strUserGroup = UserData.elementAt(1).toString().trim();
		////////////System.out.println("result Vec in user get data Action "+resultVec);
		session.setAttribute("cid", UserData.elementAt(11).toString()+"~"+phoneCcode);
		session.setAttribute("sid", UserData.elementAt(7).toString());
		session.setAttribute("pid", UserData.elementAt(6).toString());
		session.setAttribute("depid", UserData.elementAt(19).toString());
		session.setAttribute("desid", UserData.elementAt(20).toString());
		
		int countryId = Integer.parseInt(UserData.elementAt(11).toString());
		int stateId = Integer.parseInt(UserData.elementAt(7).toString());
		int cityId = Integer.parseInt(UserData.elementAt(6).toString());
		
		int dep_Id = Integer.parseInt(UserData.elementAt(19).toString());
		int des_Id = Integer.parseInt(UserData.elementAt(20).toString());
		
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
			
			////System.out.println("otherpid....advt............" +contryStr);
			session.setAttribute("cid","-1");
			
		}
		if(said==0){
			 stateStr = rootMaster.getStateName(ds,stateId);	
			
			////System.out.println("othersid....advt............" +stateStr);
			session.setAttribute("sid","-1");
			
		}
		if(paid==0){
			 cityStr = rootMaster.getPlaceName(ds,cityId);
			
			////System.out.println("othercity  advt......." +cityStr);
			session.setAttribute("pid","-1");
			
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
		
		corpousermodifyForm.setOthercountry(contryStr.toString());
		corpousermodifyForm.setOtherstate(stateStr.toString());
		corpousermodifyForm.setOthercity(cityStr.toString());
		
		corpousermodifyForm.setOtherdepartment(depStr.toString());
		corpousermodifyForm.setOtherdesignation(desStr.toString());
	
		session.setAttribute("userCat", resultVec);
		session.setAttribute("usergrp", strUserGroup);
		request.setAttribute("corpoUsermodifyForm", corpousermodifyForm);
		
		
		Vector  rightListVec = new Vector();		
		
		rightListVec = rootMaster.getGroupRightList(ds, intCompId);
		
		if(rightListVec.size()==0)
		{		
				
			ActionErrors errors = new ActionErrors();
			errors.add("groupNotExist", new ActionError("errors.rgroup.groupNotExist"));			
				saveErrors(request, errors);
				
		}
	
		return mapping.findForward("success");
		
	}

}






