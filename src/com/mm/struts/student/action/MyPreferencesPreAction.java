package com.mm.struts.student.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.*;
import com.mm.struts.student.form.MyPreferencesForm;

public class MyPreferencesPreAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		MyPreferencesForm myPreferencesForm = new MyPreferencesForm();
		
		HttpSession session = request.getSession();
		String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
		
		int numCompId= Integer.parseInt(compId);
		int numUId= Integer.parseInt(uId);
		
		EntpMaster entpMaster= new EntpMaster();
		RootMaster rootMaster= new RootMaster();
		IndvMaster indvMaster= new IndvMaster();
		Vector expVec=indvMaster.getExpDetails( getDataSource(request, "student") ,numUId);
		request.setAttribute("expVec", expVec);
		Vector resultVec = entpMaster.getCommunityUnnamedMap( getDataSource(request, "student") ,numCompId);	
		request.setAttribute("resultVec", resultVec);
		////////System.out.println("resultVec//////MyPreferencesPreAction////////"+resultVec);
		
		Vector consumerUnName = new Vector();
		if(resultVec.size()!=0){
		
		 consumerUnName = rootMaster.getUserUnNameInfo(getDataSource(request, "student"), numUId, numCompId );
		 request.setAttribute("consumerUnName", consumerUnName);
		 ////////System.out.println("consumerUnName//////MyPreferencesPreAction////////"+consumerUnName);
		
		
		if(consumerUnName.size()!=0){
		myPreferencesForm.setField1(consumerUnName.elementAt(0).toString());
		myPreferencesForm.setField2(consumerUnName.elementAt(1).toString());
		myPreferencesForm.setField3(consumerUnName.elementAt(2).toString());
		myPreferencesForm.setField4(consumerUnName.elementAt(3).toString());
		myPreferencesForm.setField5(consumerUnName.elementAt(4).toString());
		myPreferencesForm.setField6(consumerUnName.elementAt(5).toString());
		myPreferencesForm.setField7(consumerUnName.elementAt(6).toString());
		myPreferencesForm.setField8(consumerUnName.elementAt(7).toString());
		myPreferencesForm.setField9(consumerUnName.elementAt(8).toString());
		myPreferencesForm.setField10(consumerUnName.elementAt(9).toString());
		myPreferencesForm.setField11(consumerUnName.elementAt(10).toString());
		myPreferencesForm.setField12(consumerUnName.elementAt(11).toString());
		myPreferencesForm.setField13(consumerUnName.elementAt(12).toString());
		myPreferencesForm.setField14(consumerUnName.elementAt(13).toString());
		myPreferencesForm.setField15(consumerUnName.elementAt(14).toString());
		myPreferencesForm.setField16(consumerUnName.elementAt(15).toString());
		myPreferencesForm.setField17(consumerUnName.elementAt(16).toString());
		myPreferencesForm.setField18(consumerUnName.elementAt(17).toString());
		myPreferencesForm.setField19(consumerUnName.elementAt(18).toString());
		myPreferencesForm.setField20(consumerUnName.elementAt(19).toString());
		myPreferencesForm.setField21(consumerUnName.elementAt(20).toString());
		myPreferencesForm.setField22(consumerUnName.elementAt(21).toString());
		myPreferencesForm.setField23(consumerUnName.elementAt(22).toString());
		myPreferencesForm.setField24(consumerUnName.elementAt(23).toString());
		myPreferencesForm.setField25(consumerUnName.elementAt(24).toString());
		myPreferencesForm.setField26(consumerUnName.elementAt(25).toString());
		myPreferencesForm.setField27(consumerUnName.elementAt(26).toString());
		myPreferencesForm.setField28(consumerUnName.elementAt(27).toString());
		myPreferencesForm.setField29(consumerUnName.elementAt(28).toString());
		myPreferencesForm.setField30(consumerUnName.elementAt(29).toString());
		myPreferencesForm.setField31(consumerUnName.elementAt(30).toString());
		myPreferencesForm.setField32(consumerUnName.elementAt(31).toString());
		myPreferencesForm.setField33(consumerUnName.elementAt(32).toString());
		myPreferencesForm.setField34(consumerUnName.elementAt(33).toString());
		myPreferencesForm.setField35(consumerUnName.elementAt(34).toString());
		}
		
		}
		request.setAttribute("stdMyPreferencesForm", myPreferencesForm);
		
		Vector VecCourse =new Vector();
		Vector VecStream =new Vector();
	
		Vector IcatVec=new Vector();
		
		Vector educationVec = rootMaster.getEducationInfo( uId, getDataSource(request, "student") );
		Vector courseStreamVec = new Vector();
		////System.out.println("expVec//////MyPreferencesPreAction////////"+expVec.size());
		Vector exp_idVec=new Vector();
		if(expVec.size()!=0)
		{
			////System.out.println("expVec//////MyPreferencesPreAction////////"+expVec);
			for(int i=0; i<expVec.size(); i++)
			{
				Vector teampVec = (Vector)expVec.elementAt(i);
				if(i==0)
				{
				myPreferencesForm.setTitle(teampVec.elementAt(2).toString());
				myPreferencesForm.setComp(teampVec.elementAt(3).toString());
				
				String catid = teampVec.elementAt(4).toString();
				if(!catid.equalsIgnoreCase("0")){
				VecStream =  indvMaster.getIcategory(getDataSource(request, "student"), Integer.parseInt(catid));
				//System.out.println("teampVec.elementAt(0).toString()>>>>>>>>>>>"+VecStream);
				String catidname = VecStream.elementAt(0).toString();
				String catidEnable = VecStream.elementAt(1).toString();
				if(catidEnable.equalsIgnoreCase("0")){
					catid = "-1";
					myPreferencesForm.setOthericat(catidname);
				}
				}
				IcatVec.add(catid);
				myPreferencesForm.setSmonth(teampVec.elementAt(5).toString());
				myPreferencesForm.setSyear(teampVec.elementAt(6).toString());
				myPreferencesForm.setEmonth(teampVec.elementAt(7).toString());
				myPreferencesForm.setEyear(teampVec.elementAt(8).toString());
				myPreferencesForm.setExp(teampVec.elementAt(9).toString());
				myPreferencesForm.setStatus(teampVec.elementAt(9).toString());
				myPreferencesForm.setExp_id(teampVec.elementAt(0).toString());
				////System.out.println("teampVec.elementAt(0).toString()>>>>>>>>>>>"+teampVec.elementAt(0).toString());
				exp_idVec.add(teampVec.elementAt(0).toString());
				}
				if(i==1)
				{
				myPreferencesForm.setTitle1(teampVec.elementAt(2).toString());
				myPreferencesForm.setComp1(teampVec.elementAt(3).toString());
				
				String catid = teampVec.elementAt(4).toString();	
				if(!catid.equalsIgnoreCase("0")){
				VecStream =  indvMaster.getIcategory(getDataSource(request, "student"), Integer.parseInt(catid));				
				String catidname = VecStream.elementAt(0).toString();
				String catidEnable = VecStream.elementAt(1).toString();
				if(catidEnable.equalsIgnoreCase("0")){
					catid = "-1";
					myPreferencesForm.setOthericat1(catid);
				}
				
				}
				IcatVec.add(catid);
				myPreferencesForm.setSmonth1(teampVec.elementAt(5).toString());
				myPreferencesForm.setSyear1(teampVec.elementAt(6).toString());
				myPreferencesForm.setEmonth1(teampVec.elementAt(7).toString());
				myPreferencesForm.setEyear1(teampVec.elementAt(8).toString());
				myPreferencesForm.setStatus1(teampVec.elementAt(9).toString());
				myPreferencesForm.setExp1(teampVec.elementAt(9).toString());
				myPreferencesForm.setExp_id1(teampVec.elementAt(0).toString());
				exp_idVec.add(teampVec.elementAt(0).toString());
				//System.out.println("teampVec.elementAt(0).toString()>>>1>>>>>>>>"+teampVec.elementAt(0).toString());
				}
				if(i==2)
				{
				myPreferencesForm.setTitle2(teampVec.elementAt(2).toString());
				myPreferencesForm.setComp2(teampVec.elementAt(3).toString());
			
				String catid = teampVec.elementAt(4).toString();
				if(!catid.equalsIgnoreCase("0")){
				//System.out.println("catid>>>1>>>>>>>>"+catid);
				VecStream =  indvMaster.getIcategory(getDataSource(request, "student"), Integer.parseInt(catid));	
				//System.out.println("VecStream>>>1>>VecStream>>>>>>"+VecStream);
				String catidname = VecStream.elementAt(0).toString();
				String catidEnable = VecStream.elementAt(1).toString();
				if(catidEnable.equalsIgnoreCase("0")){
					catid = "-1";
					myPreferencesForm.setOthericat2(catidname);
				}
				}
				IcatVec.add(catid);
				myPreferencesForm.setSmonth2(teampVec.elementAt(5).toString());
				myPreferencesForm.setSyear2(teampVec.elementAt(6).toString());
				myPreferencesForm.setEmonth2(teampVec.elementAt(7).toString());
				myPreferencesForm.setEyear2(teampVec.elementAt(8).toString());
				myPreferencesForm.setStatus2(teampVec.elementAt(9).toString());
				myPreferencesForm.setExp1(teampVec.elementAt(9).toString());
				myPreferencesForm.setExp_id2(teampVec.elementAt(0).toString());
				exp_idVec.add(teampVec.elementAt(0).toString());
			//	//System.out.println("teampVec.elementAt(0).toString()>>>2>>>>>>>>"+teampVec.elementAt(0).toString());
				}
			}
		}
		
		for(int i=0; i<educationVec.size(); i++){
			Vector teampVec = (Vector)educationVec.elementAt(i);
			
			if(i==0){
				
			String strTeampAr[]=teampVec.elementAt(5).toString().split(",");
				
					
					String course1 = teampVec.elementAt(2).toString();			
					VecCourse =  indvMaster.getCourseName(getDataSource(request, "student"), Integer.parseInt(course1));				
					String Course1name = VecCourse.elementAt(0).toString();
					String Course1Enable = VecCourse.elementAt(1).toString();
					if(Course1Enable.equalsIgnoreCase("0")){
						course1 = "-1";
						myPreferencesForm.setCouresOth(Course1name);
					}
					
					String stream1 = teampVec.elementAt(4).toString();			
					VecStream =  indvMaster.getStreamName(getDataSource(request, "student"), Integer.parseInt(stream1));				
					String stream1name = VecStream.elementAt(0).toString();
					String stream1Enable = VecStream.elementAt(1).toString();
					if(stream1Enable.equalsIgnoreCase("0")){
						stream1 = "-1";
						myPreferencesForm.setCouresStreamOth(stream1name);
					}
			
			myPreferencesForm.setEid0(teampVec.elementAt(0).toString());	
			courseStreamVec.add(course1);
			//myPreferencesForm.setCoures0(teampVec.elementAt(2).toString());		
			myPreferencesForm.setCollname0(teampVec.elementAt(3).toString());
			courseStreamVec.add(stream1);
			//myPreferencesForm.setMsub0(teampVec.elementAt(4).toString());
			myPreferencesForm.setPassmonth0(strTeampAr[0]);
			myPreferencesForm.setPassyear0(strTeampAr[1]);
			myPreferencesForm.setAdmno0(teampVec.elementAt(6).toString());
			myPreferencesForm.setCgpa0(teampVec.elementAt(7).toString());
			
			}
			if(i==1){
				String strTeampAr[]=teampVec.elementAt(5).toString().split(",");
				
				
				String course2 = teampVec.elementAt(2).toString();	
				if(!course2.equalsIgnoreCase("0")){
					VecCourse =  indvMaster.getCourseName(getDataSource(request, "student"), Integer.parseInt(course2));				
					String Course2name = VecCourse.elementAt(0).toString();
					String Course2Enable = VecCourse.elementAt(1).toString();
					if(Course2Enable.equalsIgnoreCase("0")){
						course2 = "-1";
						myPreferencesForm.setCoures1Oth(Course2name);
					}
				}
				
				String stream2 = teampVec.elementAt(4).toString();	
				if(!stream2.equalsIgnoreCase("0")){
					VecStream =  indvMaster.getStreamName(getDataSource(request, "student"), Integer.parseInt(stream2));				
					String stream2name = VecStream.elementAt(0).toString();
					String stream2Enable = VecStream.elementAt(1).toString();
					if(stream2Enable.equalsIgnoreCase("0")){
						stream2 = "-1";
						myPreferencesForm.setCoures1StreamOth(stream2name);
					}
				}
				
				myPreferencesForm.setEid1(teampVec.elementAt(0).toString());
				courseStreamVec.add(course2);
				//myPreferencesForm.setCoures1(teampVec.elementAt(2).toString());		
				myPreferencesForm.setCollname1(teampVec.elementAt(3).toString());
				courseStreamVec.add(stream2);
				//myPreferencesForm.setMsub1(teampVec.elementAt(4).toString());			
				myPreferencesForm.setPassmonth1(strTeampAr[0]);
				myPreferencesForm.setPassyear1(strTeampAr[1]);
				myPreferencesForm.setAdmno1(teampVec.elementAt(6).toString());
				myPreferencesForm.setCgpa1(teampVec.elementAt(7).toString());
				
				}
			if(i==2){
				String strTeampAr[]=teampVec.elementAt(5).toString().split(",");
				
				String course3 = teampVec.elementAt(2).toString();	
				if(!course3.equalsIgnoreCase("0")){
					VecCourse =  indvMaster.getCourseName(getDataSource(request, "student"), Integer.parseInt(course3));				
					String Course3name = VecCourse.elementAt(0).toString();
					String Course3Enable = VecCourse.elementAt(1).toString();
					if(Course3Enable.equalsIgnoreCase("0")){
						course3 = "-1";
						myPreferencesForm.setCoures2Oth(Course3name);
					}
				}
				
				String stream3 = teampVec.elementAt(4).toString();	
				if(!stream3.equalsIgnoreCase("0")){
					VecStream =  indvMaster.getStreamName(getDataSource(request, "student"), Integer.parseInt(stream3));				
					String stream3name = VecStream.elementAt(0).toString();
					String stream3Enable = VecStream.elementAt(1).toString();
					if(stream3Enable.equalsIgnoreCase("0")){
						stream3 = "-1";
						myPreferencesForm.setCoures2StreamOth(stream3name);
					}
				}
				
				myPreferencesForm.setEid2(teampVec.elementAt(0).toString());
				courseStreamVec.add(course3);
				//myPreferencesForm.setCoures2(teampVec.elementAt(2).toString());		
				myPreferencesForm.setCollname2(teampVec.elementAt(3).toString());
				courseStreamVec.add(stream3);
				//myPreferencesForm.setMsub2(teampVec.elementAt(4).toString());			
				myPreferencesForm.setPassmonth2(strTeampAr[0]);
				myPreferencesForm.setPassyear2(strTeampAr[1]);
				myPreferencesForm.setAdmno2(teampVec.elementAt(6).toString());
				myPreferencesForm.setCgpa2(teampVec.elementAt(7).toString());
				
				}
			if(i==3){
				String strTeampAr[]=teampVec.elementAt(5).toString().split(",");
				
				String course4 = teampVec.elementAt(2).toString();	
				if(!course4.equalsIgnoreCase("0")){
					VecCourse =  indvMaster.getCourseName(getDataSource(request, "student"), Integer.parseInt(course4));				
					String Course4name = VecCourse.elementAt(0).toString();
					String Course4Enable = VecCourse.elementAt(1).toString();
					if(Course4Enable.equalsIgnoreCase("0")){
						course4 = "-1";
						myPreferencesForm.setCoures3Oth(Course4name);
					}
				}
				
				String stream4 = teampVec.elementAt(4).toString();	
				if(!stream4.equalsIgnoreCase("0")){
					VecStream =  indvMaster.getStreamName(getDataSource(request, "student"), Integer.parseInt(stream4));				
					String stream4name = VecStream.elementAt(0).toString();
					String stream4Enable = VecStream.elementAt(1).toString();
					if(stream4Enable.equalsIgnoreCase("0")){
						stream4 = "-1";
						myPreferencesForm.setCoures3StreamOth(stream4name);
					}
				}
				
				myPreferencesForm.setEid3(teampVec.elementAt(0).toString());
				courseStreamVec.add(course4);
				//myPreferencesForm.setCoures3(teampVec.elementAt(2).toString());		
				myPreferencesForm.setCollname3(teampVec.elementAt(3).toString());
				courseStreamVec.add(stream4);
				//myPreferencesForm.setMsub3(teampVec.elementAt(4).toString());			
				myPreferencesForm.setPassmonth3(strTeampAr[0]);
				myPreferencesForm.setPassyear3(strTeampAr[1]);
				myPreferencesForm.setAdmno3(teampVec.elementAt(6).toString());
				myPreferencesForm.setCgpa3(teampVec.elementAt(7).toString());
				
				}
			if(i==4){
				String strTeampAr[]=teampVec.elementAt(5).toString().split(",");
				
				String course5 = teampVec.elementAt(2).toString();	
				if(!course5.equalsIgnoreCase("0")){
					VecCourse =  indvMaster.getCourseName(getDataSource(request, "student"), Integer.parseInt(course5));				
					String Course5name = VecCourse.elementAt(0).toString();
					String Course5Enable = VecCourse.elementAt(1).toString();
					if(Course5Enable.equalsIgnoreCase("0")){
						course5 = "-1";
						myPreferencesForm.setCoures4Oth(Course5name);
					}
				}
				String stream5 = teampVec.elementAt(4).toString();	
				if(!stream5.equalsIgnoreCase("0")){
					VecStream =  indvMaster.getStreamName(getDataSource(request, "student"), Integer.parseInt(stream5));				
					String stream5name = VecStream.elementAt(0).toString();
					String stream5Enable = VecStream.elementAt(1).toString();
					if(stream5Enable.equalsIgnoreCase("0")){
						stream5 = "-1";
						myPreferencesForm.setCoures4StreamOth(stream5name);
					}
				}
				
				
				myPreferencesForm.setEid4(teampVec.elementAt(0).toString());
				courseStreamVec.add(course5);
				//myPreferencesForm.setCoures4(teampVec.elementAt(2).toString());		
				myPreferencesForm.setCollname4(teampVec.elementAt(3).toString());
				courseStreamVec.add(stream5);
				//myPreferencesForm.setMsub4(teampVec.elementAt(4).toString());			
				myPreferencesForm.setPassmonth4(strTeampAr[0]);
				myPreferencesForm.setPassyear4(strTeampAr[1]);
				myPreferencesForm.setAdmno4(teampVec.elementAt(6).toString());
				myPreferencesForm.setCgpa4(teampVec.elementAt(7).toString());
				
				}
			if(i==5){
				String strTeampAr[]=teampVec.elementAt(5).toString().split(",");
				
				String course6 = teampVec.elementAt(2).toString();	
				if(!course6.equalsIgnoreCase("0")){
					VecCourse =  indvMaster.getCourseName(getDataSource(request, "student"), Integer.parseInt(course6));				
					String Course6name = VecCourse.elementAt(0).toString();
					String Course6Enable = VecCourse.elementAt(1).toString();
					if(Course6Enable.equalsIgnoreCase("0")){
						course6 = "-1";
						myPreferencesForm.setCoures5Oth(Course6name);
					}
				}
				
				String stream6 = teampVec.elementAt(4).toString();	
				if(!stream6.equalsIgnoreCase("0")){
					VecStream =  indvMaster.getStreamName(getDataSource(request, "student"), Integer.parseInt(stream6));				
					String stream6name = VecStream.elementAt(0).toString();
					String stream6Enable = VecStream.elementAt(1).toString();
					if(stream6Enable.equalsIgnoreCase("0")){
						stream6 = "-1";
						myPreferencesForm.setCoures5StreamOth(stream6name);
					}
				}
				
				myPreferencesForm.setEid5(teampVec.elementAt(0).toString());
				courseStreamVec.add(course6);
				//myPreferencesForm.setCoures5(teampVec.elementAt(2).toString());		
				myPreferencesForm.setCollname5(teampVec.elementAt(3).toString());
				courseStreamVec.add(stream6);
				myPreferencesForm.setMsub5(teampVec.elementAt(4).toString());			
				myPreferencesForm.setPassmonth5(strTeampAr[0]);
				myPreferencesForm.setPassyear5(strTeampAr[1]);
				myPreferencesForm.setAdmno5(teampVec.elementAt(6).toString());
				myPreferencesForm.setCgpa5(teampVec.elementAt(7).toString());
				
				}
			
			
			} 
		////System.out.println("courseStreamVec..####..."+courseStreamVec);
		request.setAttribute("courseStreamVec", courseStreamVec);
		
		request.setAttribute("IcatVec", IcatVec);
		
			
		return mapping.findForward("success");
	}

}
