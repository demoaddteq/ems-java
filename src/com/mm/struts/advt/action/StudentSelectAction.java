/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.advt.action;

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
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;
import com.mm.core.master.AdvtMaster;
import com.mm.core.master.EntpMaster;
import com.mm.core.master.IndvMaster;
import com.mm.core.master.RootMaster;
import com.mm.core.resource.Constant;
import com.mm.core.resource.MailText;
import com.mm.core.resource.Resource;

import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.Vector;
import com.mm.struts.advt.form.StudentSelectForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-19-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class StudentSelectAction extends Action {
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
		RootMaster rootMaster = new RootMaster();
		IndvMaster indvMaster = new IndvMaster();
		String result = "failure";
		String categoryName="",brandName="",countryName="", stateName="",cityName="";
		DataSource dataSource = getDataSource(request, "advt");
		
		String title =request.getParameter("titel");
		String Rtext =request.getParameter("rtext");
		
		if(title.length()==0)
		{
			
			if((Rtext.length()==0)&&(title.length()==0)){
				////////////System.out.println("///////......inside IF...222...//////////..........");
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailure", new ActionError("errors.blogText.required"));
	          	errors.add("myPreferencesFailure", new ActionError("errors.blogTitle.required"));
				saveErrors(request, errors);
				return mapping.findForward("failure");
			}else{////////////System.out.println("///////......inside IF...222...//////////..........");
			ActionErrors errors = new ActionErrors();
           	errors.clear();
          	errors.add("myPreferencesFailure", new ActionError("errors.blogTitle.required"));
			saveErrors(request, errors);
			return mapping.findForward("failure");
			}
		}
		if(Rtext.length()==0)
		{
			if((Rtext.length()==0)&&(title.length()==0)){
				////////////System.out.println("///////......inside IF...222...//////////..........");
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("myPreferencesFailure", new ActionError("errors.blogText.required"));
	          	errors.add("myPreferencesFailure", new ActionError("errors.blogTitle.required"));
				saveErrors(request, errors);
				return mapping.findForward("failure");
			}else{////////////System.out.println("///////......inside IF...222...//////////..........");
			ActionErrors errors = new ActionErrors();
           	errors.clear();
          	errors.add("myPreferencesFailure", new ActionError("errors.blogText.required"));
			saveErrors(request, errors);
			return mapping.findForward("failure");
			}
		}
		
		
		////System.out.println("dc in action >>>>>"+request.getParameter("rtext"));
				
		AdvtMaster advtMaster = new AdvtMaster();
		String status = "failure";
		StudentSelectForm inboxDeletionForm = (StudentSelectForm)form;
		String[] inboxIds = inboxDeletionForm.getInboxids();
		////System.out.println("dc in action inboxIds >>>>>"+inboxIds[1]);
		
		
		
		if(((String)session.getAttribute("flagform")).equalsIgnoreCase("1"))
		{	
			String userid =(String)session.getAttribute("uId");
			String userCid = (String)session.getAttribute("compId");
			String searchResultId=	(String)session.getAttribute("srid");
			
		String strComplResult ="";

		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
	    String creationDate = sttotal.nextToken();
	    String creationTime = sttotal.nextToken();
	    String timePart = sttotal.nextToken();
	    String entpId = String.valueOf(indvMaster.getCompanyId(dataSource,"Enterprise","mm"));
	    String fcu_id = "1";
	    String publishDate = "0000-00-00";
	    String publishTime = "00:00:00";
	    String publishFlag = "0";
	    String publishAt = "0@Week";
	    String fcomId = "";
		String result1 = "";
		
		
		Vector SidVec=new Vector();
			
				for(int i=0;i<inboxIds.length;i++)
			{// //System.out.println("SidVecSidVecSidVec....in.getStrid..."+inboxIds[i]);
				SidVec.add(inboxIds[i]);
				
			} 
			
		
		
			
		
		
		 
		
	   
		for(int i=0;i<SidVec.size();i++)
		{
		 String suId= (String)SidVec.elementAt(i);
	    
	    String compId = String.valueOf(indvMaster.getCompanyId(dataSource,Integer.parseInt(suId)));
	    
	  
		Vector<String> complaintVec =  new Vector<String>();
		complaintVec.add(title);	//blog Title
		complaintVec.add(Rtext);	//blog Text
		complaintVec.add("N/A");							//Relevent Text
		complaintVec.add("0");								//Category
		complaintVec.add(creationDate);						//Creation Date
		complaintVec.add(publishDate);						//Publish Date
		complaintVec.add(creationTime);						//Creation Time
		complaintVec.add(publishTime);						//Publish Time
		complaintVec.add("0000-00-00");						//Last Modify Date
		complaintVec.add("00:00:00");						//Last Modify Time
		complaintVec.add(userid);								//User (Complainent id) LoginId()uId
		complaintVec.add(userCid);								//User Company Id
		complaintVec.add(timePart);							//Time Part
		complaintVec.add("0");								//View Count
		complaintVec.add(entpId);							//Entp Id(Complaint handler company Id)
		complaintVec.add(fcu_id);							//cu_Id(Complaint handler User Id)
		complaintVec.add(compId);							//advtId(Company Id of brand) 
		complaintVec.add(suId);								//advtlId(Company user Id of brand) 
		complaintVec.add("0");								//Dealer Id
		complaintVec.add("2");							    //Tag Id
		complaintVec.add("0000-00-00");						//Resolved Date
		complaintVec.add("0000-00-00");						//Closed Date
		complaintVec.add("0");								//Brand Flag
		complaintVec.add(publishFlag);						//Publish Flag
		complaintVec.add(publishAt);						//Publish On 
		complaintVec.add("0");								//otherBranddataVec.elementAt(17).toString()
		complaintVec.add("ColToStd");	                    //Query type
		
		strComplResult = indvMaster.insertNewComplaints(dataSource, complaintVec);
		
		  ////System.out.println("loop for submit complaint."+i+"..if.success..."+strComplResult);
		
		if(strComplResult.equalsIgnoreCase("success"))
		{
			//get complaint Id of this new complaint and set fcom Id for this complaint
			fcomId = indvMaster.getFinalCompIdForComp(dataSource, Integer.parseInt(suId), title, creationTime);
			String fcomId1 = indvMaster.getCompIdForComp(dataSource, Integer.parseInt(suId), title, creationTime);
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
			////System.out.println("strMailText...paramVec............"+paramVec);
			Vector emailVec = indvMaster.getEmailList(getDataSource(request,"advt"), paramVec);
			////System.out.println("strMailText...paramVec............"+paramVec);
			////System.out.println("strMailText...emailVec...in action........."+emailVec);
			
			Vector<String> tempMailParam = new Vector<String>();
			
			tempMailParam.add("Corporates");//sender company type Consumer
			tempMailParam.add("Student");//receipient company type Enterprise
			tempMailParam.add("Query");//mail description
			
			
			String mailTextLAlert = rootMaster.getMailText(getDataSource(request,"advt"), tempMailParam);
		
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
				String strMailText = mt.getQueriByCompany(coreMailVec,fcomId,mailTextLAlert,consumerName, title,Rtext);
				String strEmailStatus = resr.sendMail(strMailText, Constant.Email_Sender,((Vector)emailVec.elementAt(j)).elementAt(2).toString() , subject);
				////System.out.println("strMailText...emailVec...in action........."+strMailText);
				////System.out.println("strEmailStatus...emailVec...in action........."+strEmailStatus);
				
				
			}
		
		
		}
		
		
		
			
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