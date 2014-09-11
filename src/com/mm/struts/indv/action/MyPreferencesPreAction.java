package com.mm.struts.indv.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.*;
import com.mm.struts.indv.form.MyPreferencesForm;

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
		
		Vector resultVec = entpMaster.getCommunityUnnamedMap( getDataSource(request, "indv") ,numCompId);	
		request.setAttribute("resultVec", resultVec);
		System.out.println("resultVec//////MyPreferencesPreAction////////"+resultVec);
		
		Vector consumerUnName = new Vector();
		if(resultVec.size()!=0){
		
		 consumerUnName = rootMaster.getUserUnNameInfo(getDataSource(request, "indv"), numUId, numCompId );
		request.setAttribute("consumerUnName", consumerUnName);
		System.out.println("consumerUnName//////MyPreferencesPreAction////////"+consumerUnName);
		
		
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
		request.setAttribute("indvMyPreferencesForm", myPreferencesForm);
		return mapping.findForward("success");
	}

}
