package com.mm.struts.student.action;

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

import com.mm.core.master.*;
import com.mm.struts.student.form.MyPreferencesForm;

public class MyPreferencesAction extends Action {
	
public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		MyPreferencesForm myPrefForm = (MyPreferencesForm) form;
		
		HttpSession session = request.getSession();
		String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
		DataSource ds = getDataSource(request,"student");
		Vector resultVec1 = (Vector)session.getAttribute("resultVec");
		request.setAttribute("resultVec", resultVec1);
		
		Vector expVec1 = (Vector)session.getAttribute("expVec");
		request.setAttribute("expVec", expVec1);
		
		Vector courseStreamVec = (Vector)session.getAttribute("courseStreamVec");
		request.setAttribute("courseStreamVec", courseStreamVec);
		Vector IcatVec = (Vector)session.getAttribute("IcatVec");
		request.setAttribute("IcatVec", IcatVec);
		
		Vector consumerUnName1 = new Vector();
		if(resultVec1.size()!=0){		
		 consumerUnName1 = (Vector)session.getAttribute("consumerUnName");
		request.setAttribute("consumerUnName", consumerUnName1);
		
		}
		
		
		RootMaster rootMaster= new RootMaster();
		EntpMaster entpMaster= new EntpMaster();
		
		Vector consumerUnName = rootMaster.getUserUnNameInfo(getDataSource(request, "student"), Integer.parseInt(uId), Integer.parseInt(compId) );
		//////////System.out.println("consumerUnName//////MyPreferencesAction///////"+consumerUnName);
		
		Vector resultVec = entpMaster.getCommunityUnnamedMap( getDataSource(request, "student") ,Integer.parseInt(compId));		
		//////////System.out.println("resultVec//////MyPreferencesPreAction////////"+resultVec);
		
		
		int i = 0;
 		
 		for(int j=1;j<36;j++){
 			
 			////////////System.out.println("///////......inside For.......//////////.........."+j);
 	
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
					if(j==1){teamp = myPrefForm.getField1();}
					else if(j==2){teamp = myPrefForm.getField2();}
					else if(j==3){teamp = myPrefForm.getField3();}
					else if(j==4){teamp = myPrefForm.getField4();}
					else if(j==5){teamp = myPrefForm.getField5();}
					else if(j==6){teamp = myPrefForm.getField6();}
					else if(j==7){teamp = myPrefForm.getField7();}
					else if(j==8){teamp = myPrefForm.getField8();}
					else if(j==9){teamp = myPrefForm.getField9();}
					else if(j==10){teamp = myPrefForm.getField10();}
					else if(j==11){teamp = myPrefForm.getField11();}
					else if(j==12){teamp = myPrefForm.getField12();}
					else if(j==13){teamp = myPrefForm.getField13();}
					else if(j==14){teamp = myPrefForm.getField14();}
					else if(j==15){teamp = myPrefForm.getField15();}
					else if(j==16){teamp = myPrefForm.getField16();}
					else if(j==17){teamp = myPrefForm.getField17();}
					else if(j==18){teamp = myPrefForm.getField18();}
					else if(j==19){teamp = myPrefForm.getField19();}
					else if(j==20){teamp = myPrefForm.getField20();}
					else if(j==21){teamp = myPrefForm.getField21();}
					else if(j==22){teamp = myPrefForm.getField22();}
					else if(j==23){teamp = myPrefForm.getField23();}
					else if(j==24){teamp = myPrefForm.getField24();}
					else if(j==25){teamp = myPrefForm.getField25();}
					else if(j==26){teamp = myPrefForm.getField26();}
					else if(j==27){teamp = myPrefForm.getField27();}
					else if(j==28){teamp = myPrefForm.getField28();}
					else if(j==29){teamp = myPrefForm.getField29();}
					else if(j==30){teamp = myPrefForm.getField30();}
					else if(j==31){teamp = myPrefForm.getField31();}
					else if(j==32){teamp = myPrefForm.getField32();}
					else if(j==33){teamp = myPrefForm.getField33();}
					else if(j==34){teamp = myPrefForm.getField34();}
					else if(j==35){teamp = myPrefForm.getField35();}
					//////////System.out.println("///j////......inside For.......//////////.........."+j);
					//////////System.out.println("///teamp////......inside For.......//////////.........."+teamp);
					
					if(teamp.length()==0){
						//////////System.out.println("///////......inside IF...222...//////////..........");
						ActionErrors errors = new ActionErrors();
			           	errors.clear();
			          	errors.add("myPreferencesFailure", new ActionError("errors.myPreferences.modiyFailure",fieldName));
						saveErrors(request, errors);
						return mapping.findForward("failure");
					}
	  			}
				
  			}
 		}
 		
		
 		
		
		Vector <String>dataVec = new Vector<String>();
		
		
		dataVec.add(uId);
		dataVec.add(compId);		
		dataVec.add(myPrefForm.getField1());
		dataVec.add(myPrefForm.getField2());
		dataVec.add(myPrefForm.getField3());
		dataVec.add(myPrefForm.getField4());
		dataVec.add(myPrefForm.getField5());
		dataVec.add(myPrefForm.getField6());
		dataVec.add(myPrefForm.getField7());
		dataVec.add(myPrefForm.getField8());
		dataVec.add(myPrefForm.getField9());
		dataVec.add(myPrefForm.getField10());
		dataVec.add(myPrefForm.getField11());
		dataVec.add(myPrefForm.getField12());
		dataVec.add(myPrefForm.getField13());
		dataVec.add(myPrefForm.getField14());
		dataVec.add(myPrefForm.getField15());
		dataVec.add(myPrefForm.getField16());
		dataVec.add(myPrefForm.getField17());
		dataVec.add(myPrefForm.getField18());
		dataVec.add(myPrefForm.getField19());
		dataVec.add(myPrefForm.getField20());
		dataVec.add(myPrefForm.getField21());
		dataVec.add(myPrefForm.getField22());
		dataVec.add(myPrefForm.getField23());
		dataVec.add(myPrefForm.getField24());
		dataVec.add(myPrefForm.getField25());
		dataVec.add(myPrefForm.getField26());
		dataVec.add(myPrefForm.getField27());
		dataVec.add(myPrefForm.getField28());
		dataVec.add(myPrefForm.getField29());
		dataVec.add(myPrefForm.getField30());
		dataVec.add(myPrefForm.getField31());
		dataVec.add(myPrefForm.getField32());
		dataVec.add(myPrefForm.getField33());
		dataVec.add(myPrefForm.getField34());
		dataVec.add(myPrefForm.getField35());
		//////////////////////////////////////////////
		
				
		String result = "failure";
		IndvMaster indvMaster=new IndvMaster();
		Vector expVec=indvMaster.getExpDetails( getDataSource(request, "student") ,Integer.parseInt(uId));
		
		
		
		
		Vector dataVec1=new Vector();
		
		dataVec1.add(uId);
		dataVec1.add(myPrefForm.getTitle());
		dataVec1.add(myPrefForm.getComp());
		
		String otherid=myPrefForm.getIcat();
		if(otherid.equalsIgnoreCase("-1")){	
			otherid = rootMaster.insertCategory(getDataSource(request, "student"), myPrefForm.getOthericat().trim());		
						
		}
		dataVec1.add(otherid);
		dataVec1.add(myPrefForm.getSmonth());
		dataVec1.add(myPrefForm.getSyear());
		dataVec1.add(myPrefForm.getEmonth());
		dataVec1.add(myPrefForm.getEyear());
		dataVec1.add(myPrefForm.getStatus());
		dataVec1.add("0000-00-00");
		
		
		
		if(expVec.size()>0)
		{
			dataVec1.add(myPrefForm.getExp_id().toString());
			//System.out.println("myPrefForm.getExp_id().size()>>>>>>>>>>>"+myPrefForm.getStatus());
		}
		
		Vector dataVec2=new Vector();
		dataVec2.add(uId);
		dataVec2.add(myPrefForm.getTitle1());
		dataVec2.add(myPrefForm.getComp1());
		
		String otherid1=myPrefForm.getIcat1();
		if(otherid1.equalsIgnoreCase("-1")){	
			otherid1 = rootMaster.insertCategory(getDataSource(request, "student"), myPrefForm.getOthericat1().trim());		
						
		}
		dataVec2.add(otherid1);
		dataVec2.add(myPrefForm.getSmonth1());
		dataVec2.add(myPrefForm.getSyear1());
		dataVec2.add(myPrefForm.getEmonth1());
		dataVec2.add(myPrefForm.getEyear1());
		dataVec2.add(myPrefForm.getStatus1());
		dataVec2.add("0000-00-00");
		if(expVec.size()>0)
		{
		dataVec2.add(myPrefForm.getExp_id1().toString());
		//System.out.println("myPrefForm.getExp_id1().size()>>>>>>>>>>>"+myPrefForm.getStatus1());
		}
		
		Vector dataVec3=new Vector();
		dataVec3.add(uId);
		dataVec3.add(myPrefForm.getTitle2());
		dataVec3.add(myPrefForm.getComp2());
		
		String otherid2=myPrefForm.getIcat2();
		if(otherid2.equalsIgnoreCase("-1")){	
			otherid2 = rootMaster.insertCategory(getDataSource(request, "student"), myPrefForm.getOthericat2().trim());		
						
		}
		dataVec3.add(otherid2);
		dataVec3.add(myPrefForm.getSmonth2());
		dataVec3.add(myPrefForm.getSyear2());
		dataVec3.add(myPrefForm.getEmonth2());
		dataVec3.add(myPrefForm.getEyear2());
		dataVec3.add(myPrefForm.getStatus2());
		dataVec3.add("0000-00-00");
		if(expVec.size()>0)
		{
			dataVec3.add(myPrefForm.getExp_id2().toString());
			//System.out.println("myPrefForm.getExp_id2().size()>>>>>>>>>>>"+myPrefForm.getStatus2());
			//System.out.println("myPrefForm.getExp_id2().size()>>>>>>>>>>>"+myPrefForm.getIcat2());
		}
		
		
		//System.out.println("expVec.size()>>>>>>>>>>>"+expVec.size());
		
		
		
		
		if(myPrefForm.getTitle().length()!=0)
		{	
		
			if(myPrefForm.getComp().length()==0){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureFill","Company Name"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getIcat().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Industry Category in the First row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getSmonth().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Start Month in the First row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getSyear().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Start Year in the First row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getEmonth().equalsIgnoreCase("0")){
			
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","End Year in the First row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getEyear().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","End year in the First row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
		}
		if(myPrefForm.getTitle1().length()!=0)
		{	
			

			if(myPrefForm.getComp1().length()==0){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureFill","Company Name"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getIcat1().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Industry Category in the Second row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getSmonth1().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Start Month in the Second row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getSyear1().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Start Year in the Second row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getEmonth1().equalsIgnoreCase("0")){
			
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","End Year in the Second row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getEyear1().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","End year in the Second row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
		}
		if(myPrefForm.getTitle2().length()!=0)
		{	
			

			if(myPrefForm.getComp2().length()==0){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureFill","Company Name"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getIcat2().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Industry Category in the Third row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getSmonth2().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Start Month in the Third row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getSyear2().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Start Year in the Third row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getEmonth2().equalsIgnoreCase("0")){
			
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","End Month in the Third row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getEyear2().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","End Year in the Third row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
		}
		String Expactive=myPrefForm.getExp().toString();
		if(Expactive.equalsIgnoreCase("1"))
		{
		if(expVec.size()==0)
		{
			result = indvMaster.insertExperience( getDataSource(request, "student"),dataVec1);
			result = indvMaster.insertExperience( getDataSource(request, "student"),dataVec2);
			result = indvMaster.insertExperience( getDataSource(request, "student"),dataVec3);
			
		}else
		{
			if(myPrefForm.getTitle().length()!=0)
			{
				result = indvMaster.updateExperience( getDataSource(request, "student"),dataVec1);
					
			}
		 if(myPrefForm.getTitle1().length()!=0)
		{
			result = indvMaster.updateExperience( getDataSource(request, "student"),dataVec2);
			
		}
		 if(myPrefForm.getTitle2().length()!=0)
			{
			// //System.out.println("updateExperience.updateExperience>>>>>>>>>>>"+dataVec3);
			 ////System.out.println("updateExperience.updateExperience>>>>>>>>>>>"+dataVec3.size());
			 result = indvMaster.updateExperience( getDataSource(request, "student"),dataVec3);
				
			}
			
		}
		}
		
	
		
			
		
		////System.out.println("dataVec//////MyPreferencesAction////////"+dataVec);
		
		
		
		if(consumerUnName.size()==0){
		result = rootMaster.insertUserUnNameInfo( getDataSource(request, "student"),dataVec);
		}else{		
		result = rootMaster.updateUserUnNameInfo( getDataSource(request, "student"),dataVec);
		}
		
		
	
	
		
		if(!myPrefForm.getCoures0().equalsIgnoreCase("0"))
		{	
			
			if(myPrefForm.getCollname0().length()==0){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureFill","College/Institute name in the first row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getMsub0().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Major Subject in the first row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getPassmonth0().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Pass Out Month in the first row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getPassyear0().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Pass Out Year in the first row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getCgpa0().equalsIgnoreCase("0")){
			
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","%/CGPA in the first row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
		}
		
		
		if(!myPrefForm.getCoures1().equalsIgnoreCase("0"))
		{	
			
			if(myPrefForm.getCollname1().length()==0){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureFill","College/Institute name in the Second row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getMsub1().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Major Subject in the Second row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getPassmonth1().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Pass Out Month in the Second row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getPassyear1().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Pass Out Year in the Second row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getCgpa1().equalsIgnoreCase("0")){
			
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","%/CGPA in the Second row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
		}
		
		if(!myPrefForm.getCoures2().equalsIgnoreCase("0"))
		{	
			
			if(myPrefForm.getCollname2().length()==0){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureFill","College/Institute name in the Third row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getMsub2().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Major Subject in the Third row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getPassmonth2().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Pass Out Month in the Third row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getPassyear2().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Pass Out Year in the Third row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getCgpa2().equalsIgnoreCase("0")){
			
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","%/CGPA in the Third row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
		}
		
		if(!myPrefForm.getCoures3().equalsIgnoreCase("0"))
		{	
			
			if(myPrefForm.getCollname3().length()==0){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureFill","College/Institute name in the forth row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getMsub3().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Major Subject in the forth row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getPassmonth3().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Pass Out Month in the forth row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getPassyear3().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Pass Out Year in the forth row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getCgpa3().equalsIgnoreCase("0")){
			
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","%/CGPA in the forth row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
		}
		
		if(!myPrefForm.getCoures4().equalsIgnoreCase("0"))
		{	
			
			if(myPrefForm.getCollname4().length()==0){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureFill","College/Institute name in the Fifth row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getMsub4().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Major Subject in the Fifth row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getPassmonth4().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Pass Out Month in the Fifth row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getPassyear4().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Pass Out Year in the Fifth row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getCgpa4().equalsIgnoreCase("0")){
			
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","%/CGPA in the Fifth row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
		}
		
		if(!myPrefForm.getCoures5().equalsIgnoreCase("0"))
		{	
			
			if(myPrefForm.getCollname5().length()==0){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureFill","College/Institute name in the Sixth row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getMsub5().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Major Subject in the Sixth row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getPassmonth5().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Pass Out Month in the Sixth row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getPassyear5().equalsIgnoreCase("0")){
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","Pass Out Year in the Sixth row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			if(myPrefForm.getCgpa5().equalsIgnoreCase("0")){
			
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailuresrt", new ActionError("errors.myPreferences.modiyFailureSelect","%/CGPA in the Sixth row"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
		}
			
		Vector <Vector>educationVecFinal = new Vector<Vector>();
		
		Vector educationVec0 = new Vector();
		Vector educationVec1 = new Vector();
		Vector educationVec2 = new Vector();
		Vector educationVec3 = new Vector();
		Vector educationVec4 = new Vector();
		Vector educationVec5 = new Vector();
		
		String strMMY0 = myPrefForm.getPassmonth0().toString()+","+ myPrefForm.getPassyear0().toString();
		String strMMY1 = myPrefForm.getPassmonth1().toString()+","+ myPrefForm.getPassyear1().toString();		
		String strMMY2 = myPrefForm.getPassmonth2().toString()+","+ myPrefForm.getPassyear2().toString();		
		String strMMY3 = myPrefForm.getPassmonth3().toString()+","+ myPrefForm.getPassyear3().toString();		
		String strMMY4 = myPrefForm.getPassmonth4().toString()+","+ myPrefForm.getPassyear4().toString();		
		String strMMY5 = myPrefForm.getPassmonth5().toString()+","+ myPrefForm.getPassyear5().toString();
		
		//////System.out.println("myPrefForm.getCoures0().........."+myPrefForm.getCoures0());
		//////System.out.println("myPrefForm.getMsub0().........."+myPrefForm.getMsub0());
		//////System.out.println("myPrefForm.getCouresOth().........."+myPrefForm.getCouresOth());
		//////System.out.println("myPrefForm.getCouresStreamOth().........."+myPrefForm.getCouresStreamOth());
		
		//////System.out.println("myPrefForm.getCoures1().........."+myPrefForm.getCoures1());
		//////System.out.println("myPrefForm.getMsub1().........."+myPrefForm.getMsub1());
		//////System.out.println("myPrefForm.getCoures1Oth().........."+myPrefForm.getCoures1Oth());
		//////System.out.println("myPrefForm.getCoures1StreamOth().........."+myPrefForm.getCoures1StreamOth());
		
		//////System.out.println("myPrefForm.getCoures2().........."+myPrefForm.getCoures2());
		//////System.out.println("myPrefForm.getMsub2().........."+myPrefForm.getMsub2());
		//////System.out.println("myPrefForm.getCoures2Oth().........."+myPrefForm.getCoures2Oth());
		//////System.out.println("myPrefForm.getCoures2StreamOth().........."+myPrefForm.getCoures2StreamOth());
		
		//////System.out.println("myPrefForm.getCoures3().........."+myPrefForm.getCoures3());
		//////System.out.println("myPrefForm.getMsub3().........."+myPrefForm.getMsub3());
		//////System.out.println("myPrefForm.getCoures3Oth().........."+myPrefForm.getCoures3Oth());
		//////System.out.println("myPrefForm.getCoures3StreamOth().........."+myPrefForm.getCoures3StreamOth());
		
		//////System.out.println("myPrefForm.getCoures4().........."+myPrefForm.getCoures4());
		//////System.out.println("myPrefForm.getMsub4().........."+myPrefForm.getMsub4());
		//////System.out.println("myPrefForm.getCoures4Oth().........."+myPrefForm.getCoures4Oth());
		//////System.out.println("myPrefForm.getCoures4StreamOth().........."+myPrefForm.getCoures4StreamOth());
		
		//////System.out.println("myPrefForm.getCoures5().........."+myPrefForm.getCoures5());
		//////System.out.println("myPrefForm.getMsub5().........."+myPrefForm.getMsub5());
		//////System.out.println("myPrefForm.getCoures5Oth().........."+myPrefForm.getCoures5Oth());
		//////System.out.println("myPrefForm.getCoures5StreamOth().........."+myPrefForm.getCoures5StreamOth());
		
		Vector VecCourse =new Vector();
		Vector VecStream =new Vector();
		
		
		String course0 = myPrefForm.getCoures0();
		String stream0 = myPrefForm.getMsub0();
		
		String course1 = myPrefForm.getCoures1();
		String stream1 = myPrefForm.getMsub1();
		
		String course2 = myPrefForm.getCoures2();
		String stream2 = myPrefForm.getMsub2();
		
		String course3 = myPrefForm.getCoures3();
		String stream3 = myPrefForm.getMsub3();
		
		String course4 = myPrefForm.getCoures4();
		String stream4 = myPrefForm.getMsub4();
		
		String course5 = myPrefForm.getCoures5();
		String stream5 = myPrefForm.getMsub5();
		Vector updateVec=new Vector();
		//this Code is used for update course id in login master
		updateVec.add(course0);
		updateVec.add(stream0);
		updateVec.add(uId);
		result = indvMaster.updateUserCourseid(ds, updateVec);
		//code ends here
		if(course0.equalsIgnoreCase("-1")){			
			course0 = rootMaster.insertCourse(getDataSource(request, "student"), myPrefForm.getCouresOth().trim());
			stream0 = rootMaster.insertStream(getDataSource(request, "student"), course0.trim(),  myPrefForm.getCouresStreamOth().trim());			
			
		}
		else if(stream0.equalsIgnoreCase("-1")){	
			stream0 = rootMaster.insertStream(getDataSource(request, "student"), course0.trim(),  myPrefForm.getCouresStreamOth().trim());		
						
		}
		
		if(course1.equalsIgnoreCase("-1")){			
			course1 = rootMaster.insertCourse(getDataSource(request, "student"), myPrefForm.getCoures1Oth().trim());
			stream1 = rootMaster.insertStream(getDataSource(request, "student"), course1.trim(),  myPrefForm.getCoures1StreamOth().trim());			
			
		}
		else if(stream1.equalsIgnoreCase("-1")){	
			stream1 = rootMaster.insertStream(getDataSource(request, "student"), course1.trim(),  myPrefForm.getCoures1StreamOth().trim());		
						
		}
		
		if(course2.equalsIgnoreCase("-1")){			
			course2 = rootMaster.insertCourse(getDataSource(request, "student"), myPrefForm.getCoures2Oth().trim());
			stream2 = rootMaster.insertStream(getDataSource(request, "student"), course2.trim(),  myPrefForm.getCoures2StreamOth().trim());			
			
		}
		else if(stream2.equalsIgnoreCase("-1")){	
			stream2 = rootMaster.insertStream(getDataSource(request, "student"), course2.trim(),  myPrefForm.getCoures2StreamOth().trim());		
						
		}
		
		if(course3.equalsIgnoreCase("-1")){			
			course3 = rootMaster.insertCourse(getDataSource(request, "student"), myPrefForm.getCoures3Oth().trim());
			stream3 = rootMaster.insertStream(getDataSource(request, "student"), course3.trim(),  myPrefForm.getCoures3StreamOth().trim());			
			
		}
		else if(stream3.equalsIgnoreCase("-1")){	
			stream3 = rootMaster.insertStream(getDataSource(request, "student"), course3.trim(),  myPrefForm.getCoures3StreamOth().trim());		
						
		}
		
		if(course4.equalsIgnoreCase("-1")){			
			course4 = rootMaster.insertCourse(getDataSource(request, "student"), myPrefForm.getCoures4Oth().trim());
			stream4 = rootMaster.insertStream(getDataSource(request, "student"), course4.trim(),  myPrefForm.getCoures4StreamOth().trim());			
			
		}
		else if(stream4.equalsIgnoreCase("-1")){	
			stream4 = rootMaster.insertStream(getDataSource(request, "student"), course4.trim(),  myPrefForm.getCoures4StreamOth().trim());		
						
		}
		
		if(course5.equalsIgnoreCase("-1")){			
			course5 = rootMaster.insertCourse(getDataSource(request, "student"), myPrefForm.getCoures5Oth().trim());
			stream5 = rootMaster.insertStream(getDataSource(request, "student"), course5.trim(),  myPrefForm.getCoures5StreamOth().trim());			
			
		}
		else if(stream5.equalsIgnoreCase("-1")){	
			stream5 = rootMaster.insertStream(getDataSource(request, "student"), course5.trim(),  myPrefForm.getCoures5StreamOth().trim());		
						
		}
		
		
		educationVec0.add(myPrefForm.getEid0());
		educationVec0.add(course0);
		educationVec0.add(myPrefForm.getCollname0());
		educationVec0.add(stream0);
		educationVec0.add(strMMY0);
		educationVec0.add(myPrefForm.getAdmno0());
		educationVec0.add(myPrefForm.getCgpa0());
		educationVec0.add(myPrefForm.getCouresOth());
		
		educationVec1.add(myPrefForm.getEid1());
		educationVec1.add(course1);
		educationVec1.add(myPrefForm.getCollname1());
		educationVec1.add(stream1);
		educationVec1.add(strMMY1);
		educationVec1.add(myPrefForm.getAdmno1());
		educationVec1.add(myPrefForm.getCgpa1());
		educationVec1.add(myPrefForm.getCoures1Oth());
		
		educationVec2.add(myPrefForm.getEid2());
		educationVec2.add(course2);
		educationVec2.add(myPrefForm.getCollname2());
		educationVec2.add(stream2);
		educationVec2.add(strMMY2);
		educationVec2.add(myPrefForm.getAdmno2());
		educationVec2.add(myPrefForm.getCgpa2());
		educationVec2.add(myPrefForm.getCoures2Oth());
		
		educationVec3.add(myPrefForm.getEid3());
		educationVec3.add(course3);
		educationVec3.add(myPrefForm.getCollname3());
		educationVec3.add(stream3);
		educationVec3.add(strMMY3);
		educationVec3.add(myPrefForm.getAdmno3());
		educationVec3.add(myPrefForm.getCgpa3());
		educationVec3.add(myPrefForm.getCoures3Oth());
		
		educationVec4.add(myPrefForm.getEid4());
		educationVec4.add(course4);
		educationVec4.add(myPrefForm.getCollname4());
		educationVec4.add(stream4);
		educationVec4.add(strMMY4);
		educationVec4.add(myPrefForm.getAdmno4());
		educationVec4.add(myPrefForm.getCgpa4());
		educationVec4.add(myPrefForm.getCoures4Oth());
		
		educationVec5.add(myPrefForm.getEid5());
		educationVec5.add(course5);
		educationVec5.add(myPrefForm.getCollname5());
		educationVec5.add(stream5);
		educationVec5.add(strMMY5);
		educationVec5.add(myPrefForm.getAdmno5());
		educationVec5.add(myPrefForm.getCgpa5());
		educationVec5.add(myPrefForm.getCoures5Oth());
		
		if(!myPrefForm.getCoures0().equalsIgnoreCase("0")){
			rootMaster.updateEducationInfo(getDataSource(request, "student"), educationVec0);
		}
		if(!myPrefForm.getCoures1().equalsIgnoreCase("0")){
			rootMaster.updateEducationInfo(getDataSource(request, "student"), educationVec1);
		}
		if(!myPrefForm.getCoures2().equalsIgnoreCase("0")){
			rootMaster.updateEducationInfo(getDataSource(request, "student"), educationVec2);
		}
		if(!myPrefForm.getCoures3().equalsIgnoreCase("0")){
			rootMaster.updateEducationInfo(getDataSource(request, "student"), educationVec3);
		}
		if(!myPrefForm.getCoures4().equalsIgnoreCase("0")){
			rootMaster.updateEducationInfo(getDataSource(request, "student"), educationVec4);
		}
		if(!myPrefForm.getCoures5().equalsIgnoreCase("0")){
			rootMaster.updateEducationInfo(getDataSource(request, "student"), educationVec5);
		}
		
if(result.equalsIgnoreCase("success")){
			
			//System.out.println("///////......inside IF...222...//////////.........."+result);
			ActionErrors errors = new ActionErrors();
           	errors.clear();
          	errors.add("myPreferencesSuccess", new ActionError("errors.myPreferences.modiySuccess"));
			saveErrors(request, errors);
			
		}
		return mapping.findForward(result);
		
}

}
