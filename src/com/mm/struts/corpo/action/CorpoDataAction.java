package com.mm.struts.corpo.action;


import java.text.SimpleDateFormat;
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


import com.mm.core.master.*;

import com.mm.struts.corpo.form.CorpoDataForm;
import com.mm.struts.corpo.form.SavestuResultForm;

public class CorpoDataAction extends Action {
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		CorpoDataForm corpoDataForm = (CorpoDataForm) form;// TODO Auto-generated method stub
		//SavestuResultForm savestuResultForm = (SavestuResultForm)form;
		
	//	RootMaster rootMaster = new RootMaster();
		HttpSession session= request.getSession();	
		IndvMaster indvMaster = new IndvMaster();
		AdvtMaster advtMaster = new AdvtMaster();
		RootMaster rootMaster=new RootMaster();
		String result = "failure";
		String result1 = "failure";
		Vector dataVec=new Vector();
		
		DataSource dataSource = getDataSource(request, "corpo");
		
		String ptype= corpoDataForm.getPtype().toString();
		//////////System.out.println("ptype>>>>>>>on action>>>>>>"+corpoDataForm.getPtype());
		if(((String)session.getAttribute("flagforr")).equalsIgnoreCase("1"))
		{	
			String userid =(String)session.getAttribute("uId");
			String userCid = String.valueOf(indvMaster.getCompanyId(dataSource,Integer.parseInt(userid)));
			
			
			
		String strComplResult ="";
		dataVec.add(userCid);
			 
		Vector<String> complaintVec =  new Vector<String>();
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
	    String creationDate = sttotal.nextToken();
	    String creationTime = sttotal.nextToken();
	    String timePart = sttotal.nextToken();
	    
						
		complaintVec.add(userCid);	
		complaintVec.add(creationDate);		
		
		if(ptype.equalsIgnoreCase("1")){
			complaintVec.add("tab1");	    //Tab1 Title
			complaintVec.add(corpoDataForm.getTab1().toString().trim());	    //Tab1 Title
			complaintVec.add("text1");	    //Tab1 Title
			complaintVec.add(corpoDataForm.getTabText1().toString().trim());	//TabText1 Title	
			//////System.out.println("getTabText1........................."+corpoDataForm.getTabText1());
			//////System.out.println("request.............."+request.getParameter("tabText1"));
			
		}else if(ptype.equalsIgnoreCase("2")){
			complaintVec.add("tab2");	    //Tab2 Title
			complaintVec.add(corpoDataForm.getTab2().toString().trim());	    //Tab2 Title
			complaintVec.add("text2");	    //Tab2 Title
			complaintVec.add(corpoDataForm.getTabText2().toString().trim());	//TabText2 Title				
		}if(ptype.equalsIgnoreCase("3")){
			complaintVec.add("tab3");	    //Tab3 Title
			complaintVec.add(corpoDataForm.getTab3().toString().trim());	    //Tab3 Title
			complaintVec.add("text3");	    //Tab3 Title
			complaintVec.add(corpoDataForm.getTabText3().toString().trim());	//TabText3 Title				
		}if(ptype.equalsIgnoreCase("4")){
			complaintVec.add("tab4");	    //Tab4 Title
			complaintVec.add(corpoDataForm.getTab4().toString().trim());	    //Tab4 Title
			complaintVec.add("text4");	    //Tab4 Title
			complaintVec.add(corpoDataForm.getTabText4().toString().trim());	//TabText4 Title				
		}if(ptype.equalsIgnoreCase("5")){
			complaintVec.add("tab5");	    //Tab5 Title
			complaintVec.add(corpoDataForm.getTab5().toString().trim());	    //Tab5 Title
			complaintVec.add("text5");	    //Tab5 Title
			complaintVec.add(corpoDataForm.getTabText5().toString().trim());	//TabText5 Title				
		}
		
							
		Vector tabVec = indvMaster.getTabDetails(dataSource, dataVec);	
		request.setAttribute("tabVec", tabVec);
		if(tabVec.size()>0)
		{
			strComplResult = advtMaster.updateTab(dataSource, complaintVec );
			
		}
		else
		{
			strComplResult = advtMaster.insertTab(dataSource, complaintVec);
		}
	    
		
		
	
		
			
					 if(strComplResult.equalsIgnoreCase("success"))
							{
								ActionErrors errors = new ActionErrors();
								errors.clear();
								errors.add("usermodify", new ActionError("errors.user.modifySuucess"));
								
								saveErrors(request, errors);
								
							}
							else
							{
								ActionErrors errors = new ActionErrors();
								errors.clear();
								errors.add("usermodify", new ActionError("errors.user.modifyFailure"));
								
								saveErrors(request, errors);
								
							}
							//////System.out.println("result1result1>>>>>>>>>>>>in action"+strComplResult);	
		if(strComplResult.equalsIgnoreCase("success")){
			session.setAttribute("flagform","0");
			
		}
		
		return mapping.findForward(strComplResult);
		
	}else{
			
			return mapping.findForward("success");
		}
	}

}
