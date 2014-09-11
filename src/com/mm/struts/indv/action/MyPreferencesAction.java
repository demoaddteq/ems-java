package com.mm.struts.indv.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.*;
import com.mm.struts.indv.form.MyPreferencesForm;

public class MyPreferencesAction extends Action {
	
public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		MyPreferencesForm myPrefForm = (MyPreferencesForm) form;
		
		HttpSession session = request.getSession();
		String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
		
		RootMaster rootMaster= new RootMaster();
		EntpMaster entpMaster= new EntpMaster();
		
		Vector consumerUnName = rootMaster.getUserUnNameInfo(getDataSource(request, "indv"), Integer.parseInt(uId), Integer.parseInt(compId) );
		System.out.println("consumerUnName//////MyPreferencesAction///////"+consumerUnName);
		
		Vector resultVec = entpMaster.getCommunityUnnamedMap( getDataSource(request, "indv") ,Integer.parseInt(compId));		
		System.out.println("resultVec//////MyPreferencesPreAction////////"+resultVec);
		
		
		int i = 0;
 		
 		for(int j=1;j<36;j++){
 			
 			//System.out.println("///////......inside For.......//////////.........."+j);
 	
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
					System.out.println("///j////......inside For.......//////////.........."+j);
					System.out.println("///teamp////......inside For.......//////////.........."+teamp);
					
					if(teamp.length()==0){
						System.out.println("///////......inside IF...222...//////////..........");
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
		
		System.out.println("dataVec//////MyPreferencesAction////////"+dataVec);
		
		String result = "failure";
		
		if(consumerUnName.size()==0){
		result = rootMaster.insertUserUnNameInfo( getDataSource(request, "indv"),dataVec);
		}else{		
		result = rootMaster.updateUserUnNameInfo( getDataSource(request, "indv"),dataVec);
		}
		
		if(result.equalsIgnoreCase("success")){
			
			System.out.println("///////......inside IF...222...//////////..........");
			ActionErrors errors = new ActionErrors();
           	errors.clear();
          	errors.add("myPreferencesSuccess", new ActionError("errors.myPreferences.modiySuccess"));
			saveErrors(request, errors);
			
		}
				
		return mapping.findForward(result);
		
}

}
