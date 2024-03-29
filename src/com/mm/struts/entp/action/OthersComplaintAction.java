/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.entp.action;

import java.text.SimpleDateFormat;
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
import com.mm.struts.entp.form.OthersComplaintForm;


/** 
 * MyEclipse Struts
 * Creation date: 07-31-2007
 * 
 * XDoclet definition:
 * @struts.action path="/othersComplaint" name="othersComplaintForm" input="/entp/othersComplaint.jsp" scope="request" validate="true"
 */

public class OthersComplaintAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		String uId =(String)session.getAttribute("uId");
		String companyId =(String)session.getAttribute("compId");
		Vector allRights =(Vector)session.getAttribute("allRights");
		String adminFlag = allRights.elementAt(18).toString();
		
		EntpMaster entpMaster= new EntpMaster();		
		Vector dataVec3 = new Vector();		
		dataVec3.add(uId);
		dataVec3.add(companyId);	
		dataVec3.add(adminFlag);
		Vector entpCountVec = entpMaster.complaintCountEntp(getDataSource(request, "entp"), dataVec3);		
		session.setAttribute("entpCountVec", entpCountVec);						
		//System.out.println("entpCountVec.............OthersComplaintAction........... "+entpCountVec);
		
		OthersComplaintForm othersComplaintForm = (OthersComplaintForm) form;// TODO Auto-generated method stub
		String status = "failure";
		DataSource dataSource = getDataSource(request,"entp");
		
		Vector<Vector> dataVec = new Vector<Vector>();
		Vector<Vector> dataVec1 = new Vector<Vector>();
		//		date manipulation
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy");
		String year = sform.format(dt);
		
	   
		String cuId  = othersComplaintForm.getCoreUser();
		String newCuid = othersComplaintForm.getOtherCoreUser();
		String[] draftIds = othersComplaintForm.getBlogids();
		 
		 
		for(int i=0; i<draftIds.length; i++)
		{
			String drafts = draftIds[i];
			String fcomId = entpMaster.getFinalComplainId(Integer.parseInt(newCuid.trim()), Integer.parseInt(drafts.trim()), year);
			
			//String fcomId = drafts+"/"+newCuid+"/"+year;
			Vector<String> tempVec = new Vector<String>();
			tempVec.add(drafts);
			tempVec.add(fcomId);
			dataVec.add(tempVec);
			Vector oldcompVec = entpMaster.getComplaintDetails(dataSource,Integer.parseInt(drafts));
			tempVec.add(oldcompVec.elementAt(7).toString().trim());
			tempVec.add(oldcompVec.elementAt(3).toString().trim());
			dataVec1.add(tempVec);
			
		}
		status = entpMaster.updateComplaint(dataSource, newCuid, dataVec);
		if(status.equalsIgnoreCase("success"))
		{
			//mail code
			String coreuserName = "";
			String newCoreUserName = "";
			String finalFcompIds = "";
			for(int i=0;i<dataVec1.size();i++)
			{
				Vector tempVec = (Vector)dataVec1.elementAt(i);
				String compId = tempVec.elementAt(0).toString().trim();
				String newFcomId = tempVec.elementAt(1).toString().trim();
				String oldFcomId = tempVec.elementAt(2).toString().trim();
				
				int consumerId = Integer.parseInt(tempVec.elementAt(3).toString().trim());
				Vector<String> paramVec = new Vector<String>();
				paramVec.add(cuId);
				paramVec.add(String.valueOf(consumerId));
				paramVec.add(newCuid);
				IndvMaster indvMaster = new IndvMaster();
				Vector MasilId = indvMaster.getEmailList(dataSource, paramVec);
				////System.out.println("MasilId>>>>>>>>>>>"+MasilId);
				if(MasilId.size()==3)
				{
					Vector coreDataVec = (Vector)MasilId.elementAt(0);
					Vector consumerDataVec = (Vector)MasilId.elementAt(1);
					Vector newCoreDataVec = (Vector)MasilId.elementAt(2);
					
					coreuserName = coreDataVec.elementAt(0).toString()+" "+coreDataVec.elementAt(1).toString();
					String coreuserMail = coreDataVec.elementAt(2).toString();
					
					String consumerName = consumerDataVec.elementAt(0).toString()+" "+consumerDataVec.elementAt(1).toString();
					String consumerMail = consumerDataVec.elementAt(2).toString();
					
					newCoreUserName = newCoreDataVec.elementAt(0).toString()+" "+newCoreDataVec.elementAt(1).toString();
					String newcoreuserMail = newCoreDataVec.elementAt(2).toString();
				
					MailText mailtext = new MailText();
					Resource resource = new Resource();
					String subject = "Change Query id from ("+ oldFcomId + ") to ("+ newFcomId+").";
					String subjectCore = "Switch some queries from "+coreuserName +" to "+newCoreUserName;
					
					finalFcompIds = finalFcompIds + (i+1) + ". Change Query id from ("+ oldFcomId + ") to ("+ newFcomId+") which is registered by "+consumerName+".\n\n";
					
					if(i == (dataVec1.size()-1))	
					{
						Vector<String> tempMailParam = new Vector<String>();
						tempMailParam.add("Consumer");//sender company type
						tempMailParam.add("Enterprise");//receipient company type
						tempMailParam.add("Complaint");//mail description
						//get mail text alert fot sending mail to core user and admin core user
						RootMaster rootMaster = new RootMaster();
						String mailTextLAlert = rootMaster.getMailText(dataSource, tempMailParam);
						String strMailText = mailtext.getETextForCoreAllotComplaint(newCoreDataVec,finalFcompIds,mailTextLAlert);
						String strFinalStatus1 = resource.sendMail(strMailText, Constant.Email_Sender,newcoreuserMail, subjectCore);
						//System.out.println("strFinalStatus1>>>>>>>>>>"+strFinalStatus1);
						
						
						Vector<String> tempMailParam1 = new Vector<String>();
						tempMailParam1.add("Enterprise");//sender company type
						tempMailParam1.add("Enterprise");//receipient company type
						tempMailParam1.add("Switch");//mail description
						//get mail text alert fot sending mail to core user and admin core user
						
						String mailTextLAlert1 = rootMaster.getMailText(dataSource, tempMailParam1);
						String strMailText1 = mailtext.getETextForCoreAllotComplaint(coreDataVec,finalFcompIds,mailTextLAlert1);
						String strFinalStatus2 = resource.sendMail(strMailText1, Constant.Email_Sender,coreuserMail, subjectCore);
						//System.out.println("strFinalStatus2>>>>>>>>>>"+strFinalStatus2);
					}
					//send mail ti consumer
					String strConsumerMailtex = mailtext.getTextForConsumer(consumerName,oldFcomId,newFcomId);
					String strFinalStatus = resource.sendMail(strConsumerMailtex, Constant.Email_Sender, consumerMail, subject);
					//System.out.println("strFinalStatus>>>>>>>>>>"+strFinalStatus);
				}
			}
			
			//mail code end
			
			ActionErrors msg = new ActionErrors();
			msg.clear();
			msg.add("switch", new ActionError("errors.switch.Success",coreuserName,newCoreUserName));
			
				saveErrors(request, msg);
						
		}
		else
		{
			ActionErrors msg = new ActionErrors();
			msg.clear();
			msg.add("switch", new ActionError("errors.switch.Failure"));
			
			saveErrors(request, msg);
			
		}
		return mapping.findForward(status);
	}
}