package com.mm.struts.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.core.master.*;
import javax.servlet.http.HttpSession;
import com.mm.core.resource.Constant;
import com.mm.core.resource.MailText;
import com.mm.core.resource.Resource;
import com.mm.core.resource.Support;

import com.mm.struts.form.CommunitySUnnameForm;

public class FacilityAction extends Action {	
	
	
public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
	HttpSession session =request.getSession();	
	
	if(((String)session.getAttribute("flagforSr")).equalsIgnoreCase("1"))
	{
		
		
	//String CompId = (request.getParameter("compId")!=null) ? request.getParameter("compId") : "0";
	System.out.println("compId......CommunitySUnnameAction..............");
	
	
	////////System.out.println("team......CommunitySUnnameAction.............."+team);
	DataSource ds = getDataSource(request,"main");	
	EntpMaster entpMaster = new EntpMaster();
	RootMaster rootMaster = new RootMaster();
	String strComName = "Student";
	Vector CompIdVec =entpMaster.getCompanyId(ds, strComName);
	System.out.println("CompIdVec......."+CompIdVec);
	String CompId =  CompIdVec.elementAt(0).toString();
	String rid=rootMaster.getRightId(ds, CompId);
	
	Vector resultVec = entpMaster.getCommunityUnnamedMap( getDataSource(request, "main") ,Integer.parseInt(CompId));		
	System.out.println("resultVec//////CommunitySUnnameAction////////"+resultVec);
  			
	
	////////System.out.println("datavec......CommunitySUnnameAction.............."+infoVec);
	////////System.out.println("datavec Size......CommunitySUnnameAction.............."+infoVec.size());
		String result ="success";
	System.out.println("In Login Action result "+result);
	if(result.equalsIgnoreCase("success")){
		Resource resr = new Resource();
		String result1 ="success";
	}
 int user_id=7;
	
			System.out.println("Hi Ajay Querry==>");
			String strQuery_res;
			String strQuery="";
			String strResult="";
			Support support=new Support();
			strQuery ="SELECT facility_name FROM facility WHERE facility_id= (select facility_id from resident_details where user_id="+user_id+")";
			System.out.println("Hi Ajay Querry==>"+strQuery);
			support.setFieldVec("string", "company_name");
			// strResult = masterUtil.getValue(ds, strQuery, support);
			
			return null;
		}


		return mapping.findForward("sessionFail");
	}
		/*return mapping.findForward(result);
	*/

}
