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

import com.mm.core.master.EntpMaster;
import com.mm.core.master.IndvMaster;
import com.mm.core.master.RootMaster;
import com.mm.core.resource.Constant;
import com.mm.core.resource.MailText;
import com.mm.core.resource.Resource;
import com.mm.struts.corpo.form.WriteQueryForm;
import com.mm.struts.corpo.form.SavestuResultForm;

public class WriteNoticeAction extends Action {
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		WriteQueryForm writeQueryForm = (WriteQueryForm) form;// TODO Auto-generated method stub
		//SavestuResultForm savestuResultForm = (SavestuResultForm)form;
		RootMaster rootMaster = new RootMaster();
		HttpSession session= request.getSession();	
		IndvMaster indvMaster = new IndvMaster();
		String result = "failure";
		String categoryName="",brandName="",countryName="", stateName="",cityName="";
		DataSource dataSource = getDataSource(request, "corpo");
		
		if(((String)session.getAttribute("flagform")).equalsIgnoreCase("1"))
		{	
			String userid =(String)session.getAttribute("uId");
			String userCid = String.valueOf(indvMaster.getCompanyId(dataSource,Integer.parseInt(userid)));
			String searchResultId=	(String)session.getAttribute("srid");
			String FacilityID =(String)session.getAttribute("fId");
			
		String strComplResult ="";

		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
	    String creationDate = sttotal.nextToken();
	    String creationTime = sttotal.nextToken();
	    String timePart = sttotal.nextToken();
		String result1 = "";	
		
	  
		Vector<String> complaintVec =  new Vector<String>();
		complaintVec.add(FacilityID);						//FacilityID
		complaintVec.add(writeQueryForm.getBlogTitle().toString().trim());	//blog Title
		complaintVec.add(writeQueryForm.getBlogtext().toString().trim());	//blog Text
		complaintVec.add("0000-00-00");						//Ending on
//		complaintVec.add("0");								//Category
		complaintVec.add(userid);						//Created By
		complaintVec.add(creationDate);						//Creation Date
		complaintVec.add("0");						//Modified By
		complaintVec.add("0000-00-00");						//Last Modify Date
		
		
		result1 = indvMaster.insertNewNotice(dataSource, complaintVec);
		
		  //////System.out.println("loop for submit complaint."+i+"..if.success..."+strComplResult);
		
/*
		if(strComplResult.equalsIgnoreCase("success"))
		{
			//get complaint Id of this new complaint and set fcom Id for this complaint
			fcomId = indvMaster.getFinalCompIdForComp(dataSource, Integer.parseInt(suId), writeQueryForm.getBlogTitle().toString().trim(), creationTime);
			String fcomId1 = indvMaster.getCompIdForComp(dataSource, Integer.parseInt(suId), writeQueryForm.getBlogTitle().toString().trim(), creationTime);
			String fcomarr[] = fcomId1.split("/");
			Vector<String> tempCompVec = new Vector<String>();
			tempCompVec.add(fcomId);//final compaint id
			tempCompVec.add(fcomarr[0]);//complaint id
			result1 = indvMaster.setFinalId(dataSource, tempCompVec);
		}
			EntpMaster entpMaster = new EntpMaster();
			
			
			Vector<String> paramVec = new Vector<String>();
			
			
			paramVec.add(userid);
			paramVec.add(suId);
			//////System.out.println("strMailText...paramVec............"+paramVec);
			Vector emailVec = indvMaster.getEmailList(getDataSource(request,"corpo"), paramVec);
			//////System.out.println("strMailText...paramVec............"+paramVec);
			////System.out.println("strMailText...emailVec...in action........."+emailVec);
			
			Vector<String> tempMailParam = new Vector<String>();
			
			tempMailParam.add("Corporates");//sender company type Consumer
			tempMailParam.add("Student");//receipient company type Enterprise
			tempMailParam.add("Query");//mail description
			
			
			String mailTextLAlert = rootMaster.getMailText(getDataSource(request,"corpo"), tempMailParam);
		
			Vector consumerdVec = (Vector)emailVec.elementAt(0);
			String consumerName =consumerdVec.elementAt(0).toString()+" "+consumerdVec.elementAt(1).toString();
			String consumermail = consumerdVec.elementAt(2).toString().trim();
			
			String subject = "Query for Students from "+ consumerName + ".";
			
			
			for(int j =1; j<emailVec.size();j++)
			{
				MailText mt = new MailText();
				Resource resr = new Resource();
				Vector coreMailVec = (Vector)emailVec.elementAt(j);
				////System.out.println("coreMailVecid...emailVec...in action........."+coreMailVec);
				String strMailText = mt.getQueriByCompany(coreMailVec,fcomId,mailTextLAlert,consumerName, writeQueryForm.getBlogTitle().toString().trim(),writeQueryForm.getBlogtext().toString().trim());
				String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender,((Vector)emailVec.elementAt(j)).elementAt(2).toString() , subject);
				//////System.out.println("strMailText...emailVec...in action........."+strMailText);
				////System.out.println("strEmailStatus...emailVec...in action........."+strEmailStatus);
				
				
			}
		
		*/
				
				 if(result1.equalsIgnoreCase("success"))
							{
								ActionErrors msg = new ActionErrors();
								msg.clear();
								msg.add("delete", new ActionError("corpo.WriteQuery.submitSuccess"));
								
								saveErrors(request, msg);
								
							}
							else
							{
								ActionErrors msg = new ActionErrors();
								msg.clear();
								msg.add("delete", new ActionError("corpo.WriteQuery.submitFailure"));
								
								saveErrors(request, msg);
								
							}
								
		if(result1.equalsIgnoreCase("success")){
			session.setAttribute("flagform","0");
			
		}
		
		return mapping.findForward(result1);
		
	}else{
			
			return mapping.findForward("success");
		}
	}

}
