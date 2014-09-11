package com.mm.struts.indv.action;


import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


import org.apache.struts.actions.LookupDispatchAction;
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
import com.mm.struts.indv.form.ModifyDraftForm;


/** 
 * MyEclipse Struts
 * Creation date: 06-11-2007
 * 
 * XDoclet definition:
 * @struts.action path="/modifyDraft" name="modifyDraftForm" input="/form/modifyDraft.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="modifyDraft.jsp"
 */
public class ModifyDraftAction extends LookupDispatchAction {
	/*
	 * Generated Methods
	 */
	protected Map getKeyMethodMap()
	{
		Map<String , String> map = new HashMap<String , String> ();
		map.put("draft.modify", "modify");
		map.put("draft.save", "submit");
		
		return map;
	}
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	//modify draft information
	public ActionForward modify(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		
		ModifyDraftForm modifyDraftForm = (ModifyDraftForm) form;// TODO Auto-generated method stub
		String status = "failure";
		DataSource dataSource = getDataSource(request,"indv");
		IndvMaster indvMaster = new IndvMaster();
		String draftId = modifyDraftForm.getDraftId();
		String dealerId = modifyDraftForm.getDealerId();
		//date manipulation
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
	    String lastModifyDate = sttotal.nextToken();
	    String lastModifyTime = sttotal.nextToken();
	    
	    //category manipulatin
	    String categoryId = getCatData(dataSource,modifyDraftForm.getCategory(),modifyDraftForm.getOtherCategory());
	    
	    String advtId = "0";
		String otherBrand = "";
		if(modifyDraftForm.getCategory().equals("-1") || modifyDraftForm.getBrand().equals("-1") )
		{
			advtId = String.valueOf(indvMaster.getCompanyId(dataSource,"Advertiser","other"));
			otherBrand = modifyDraftForm.getOtherBrand();
		}
		else
		{
			advtId = modifyDraftForm.getBrand();
		}
		String dealerResult = "";
		if(modifyDraftForm.getCheckDealer().equalsIgnoreCase("checked"))
		{
			if(Integer.parseInt(dealerId) != 0)
			{
				Vector<String> dataVec = new Vector<String>();
				dataVec.add(modifyDraftForm.getDealerName());
				dataVec.add(modifyDraftForm.getAddress());
				dataVec.add(modifyDraftForm.getCountry());
				dataVec.add(modifyDraftForm.getOtherCountry());
				dataVec.add(modifyDraftForm.getState());
				dataVec.add(modifyDraftForm.getOtherState());
				dataVec.add(modifyDraftForm.getCity());
				dataVec.add(modifyDraftForm.getOtherCity());
				dataVec.add(modifyDraftForm.getPincode());
				dataVec.add(dealerId);
				
				dealerResult = updateDealerInfo(dataSource,dataVec,advtId);
			}
			else
			{
				Vector<String> dataVec = new Vector<String>();
				dataVec.add(modifyDraftForm.getDealerName());
				dataVec.add(modifyDraftForm.getAddress());
				dataVec.add(modifyDraftForm.getCountry());
				dataVec.add(modifyDraftForm.getOtherCountry());
				dataVec.add(modifyDraftForm.getState());
				dataVec.add(modifyDraftForm.getOtherState());
				dataVec.add(modifyDraftForm.getCity());
				dataVec.add(modifyDraftForm.getOtherCity());
				dataVec.add(modifyDraftForm.getPincode());
				dealerId = insertDealerInfo(dataSource,dataVec,advtId);
			}
		}
		else
		{
			if(Integer.parseInt(dealerId) != 0)
			{
				dealerResult = indvMaster.deleteDealer(dataSource,Integer.parseInt(dealerId));
				if(dealerResult.equals("success"))
				{
					dealerId = "0";
				}
			}
		}
		/*
		support.setDataVec("string", paramVec.elementAt(0).toString().trim());	//0 draft_title
		support.setDataVec("string", paramVec.elementAt(1).toString().trim());	//1 draft_text
		support.setDataVec("string", paramVec.elementAt(2).toString().trim());	//2 relevent_info
		support.setDataVec("string", paramVec.elementAt(3).toString().trim());	//3 category
		support.setDataVec("string", paramVec.elementAt(4).toString().trim());	//4 lastmodify_date
		support.setDataVec("string", paramVec.elementAt(5).toString().trim());	//5 lastmodify_time
		support.setDataVec("int", paramVec.elementAt(6).toString().trim());		//6 dealer_id
		support.setDataVec("stirng", paramVec.elementAt(7).toString().trim());	//7 other
		support.setDataVec("int", paramVec.elementAt(8).toString().trim());		//8 advt_id
		support.setDataVec("INT", paramVec.elementAt(9).toString().trim());		//9 draft_id 
		*/
		
		Vector<String> draftVec = new Vector<String>();
		draftVec.add(modifyDraftForm.getBlogTitle());
		draftVec.add(modifyDraftForm.getBlogtext());
		draftVec.add(modifyDraftForm.getReleventtext());
		draftVec.add(categoryId);
		draftVec.add(lastModifyDate);
		draftVec.add(lastModifyTime);
		draftVec.add(dealerId);
		draftVec.add(otherBrand);
		draftVec.add(advtId);
		draftVec.add(draftId);
		
		status = indvMaster.updateDraft(dataSource, draftVec);
		/**
		 * Added By Ajay Kumar Jha
		 */
		if(status.equals("success"))
		{
			Vector<String> unnamedDataVec = new Vector<String>();
			for(int i=1; i<=35; i++)
			{
				if(request.getParameterValues("field"+i)!= null)
				{
					String arrTemp[] = request.getParameterValues("field"+i);
					String strTemp="";
					for(int j=0; j<arrTemp.length; j++)
					{
						strTemp=(strTemp.equalsIgnoreCase("")) ? arrTemp[j] : strTemp+", "+arrTemp[j];
						//System.out.println("Field "+i+" values "+arrTemp[j]);
					}
					unnamedDataVec.add(strTemp);
				}
				else
				{
					unnamedDataVec.add("");
				}
			}
			status=indvMaster.editDraftUnnamed(dataSource, unnamedDataVec, Integer.parseInt(draftId.trim()));
			if(status.equals("success"))
			{
				ActionErrors msg = new ActionErrors();
				msg.clear();
				msg.add("delete", new ActionError("indv.draft.ModifySuccess"));
				
				saveErrors(request, msg);
			}
		}
		else
		{
			ActionErrors msg = new ActionErrors();
			msg.clear();
			msg.add("delete", new ActionError("indv.draft.ModifyFailure"));
			
			saveErrors(request, msg);
		}
		/**
		 * end here
		 */
		
		
		
		return mapping.findForward(status);
	}
	
	//save as complaint
	public ActionForward submit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		
		ModifyDraftForm modifyDraftForm = (ModifyDraftForm) form;// TODO Auto-generated method stub
		System.out.println("manoj in draft save as complaint");
		String status = "failure";
		String draftId = modifyDraftForm.getDraftId();
		String dealerId = modifyDraftForm.getDealerId();
		
		IndvMaster indvMaster = new IndvMaster();
		EntpMaster entpMaster = new EntpMaster();
		RootMaster rootMaster = new RootMaster();
		HttpSession session = request.getSession();
		DataSource dataSource = getDataSource(request,"indv");
		String fcomId = "";
		
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss,a");
		String completeRemDate = sform.format(dt);
		StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
	    String creationDate = sttotal.nextToken();
	    String creationTime = sttotal.nextToken();
	    String timePart = sttotal.nextToken();
	    
	    String publishDate = "0000-00-00";
	    String publishTime = "00:00:00";
	    String publishFlag = "0";
	    String publishAt = "1@Week";
	    
	    boolean pubFlag = indvMaster.publishCheck();
	    if(pubFlag == true)
	    {
	    	publishDate = creationDate;
	    	publishTime = creationTime;
	    	publishFlag = "1";
	    	publishAt = "0@Week";
	    }
	    
		String uId = (String)session.getAttribute("uId");
		String compId = (String)session.getAttribute("compId");
		
		String categoryId = getCatData(dataSource,modifyDraftForm.getCategory(),modifyDraftForm.getOtherCategory());
		String otherBrand = "";
		
		String advtId = "0";
		String advtLid = "0";
		if(modifyDraftForm.getCategory().equals("-1") || modifyDraftForm.getBrand().equals("-1") )
		{
			advtId = String.valueOf(indvMaster.getCompanyId(dataSource,"Advertiser","other"));
			otherBrand = modifyDraftForm.getOtherBrand();
		}
		else
		{
			advtId = modifyDraftForm.getBrand();
		}
		advtLid = String.valueOf(entpMaster.getCompnyAdminUserId(dataSource,Integer.parseInt(advtId)));
		
		String dealerResult = "";
		if(modifyDraftForm.getCheckDealer().equalsIgnoreCase("checked"))
		{
			if(Integer.parseInt(dealerId) != 0)
			{
				Vector<String> dataVec = new Vector<String>();
				dataVec.add(modifyDraftForm.getDealerName());
				dataVec.add(modifyDraftForm.getAddress());
				dataVec.add(modifyDraftForm.getCountry());
				dataVec.add(modifyDraftForm.getOtherCountry());
				dataVec.add(modifyDraftForm.getState());
				dataVec.add(modifyDraftForm.getOtherState());
				dataVec.add(modifyDraftForm.getCity());
				dataVec.add(modifyDraftForm.getOtherCity());
				dataVec.add(modifyDraftForm.getPincode());
				dataVec.add(dealerId);
				
				dealerResult = updateDealerInfo(dataSource,dataVec,advtId);
				session.removeAttribute("cid");
				session.removeAttribute("sid");
				session.removeAttribute("pid");
				session.removeAttribute("catid");
				session.removeAttribute("Bid");
				session.removeAttribute("otherCatid");
				session.removeAttribute("otherBid");
				
			}
			else
			{
				Vector<String> dataVec = new Vector<String>();
				dataVec.add(modifyDraftForm.getDealerName());
				dataVec.add(modifyDraftForm.getAddress());
				dataVec.add(modifyDraftForm.getCountry());
				dataVec.add(modifyDraftForm.getOtherCountry());
				dataVec.add(modifyDraftForm.getState());
				dataVec.add(modifyDraftForm.getOtherState());
				dataVec.add(modifyDraftForm.getCity());
				dataVec.add(modifyDraftForm.getOtherCity());
				dataVec.add(modifyDraftForm.getPincode());
				dealerId = insertDealerInfo(dataSource,dataVec,advtId);
				session.removeAttribute("cid");
				session.removeAttribute("sid");
				session.removeAttribute("pid");
				session.removeAttribute("catid");
				session.removeAttribute("Bid");
				session.removeAttribute("otherCatid");
				session.removeAttribute("otherBid");
			}
		}
		else
		{
			if(Integer.parseInt(dealerId) != 0)
			{
				dealerResult = indvMaster.deleteDealer(dataSource,Integer.parseInt(dealerId));
				if(dealerResult.equals("success"))
				{
					dealerId = "0";
				}
			}
		}
		
		String cu_id = String.valueOf(indvMaster.getCoreUserId(dataSource,Integer.parseInt(categoryId)));
		String fcu_id = cu_id;
		String entpId = String.valueOf(indvMaster.getCompanyId(dataSource,"Enterprise","core"));
		if(cu_id.equals("0"))
		{
			cu_id = String.valueOf(entpMaster.getCompnyAdminUserId(dataSource,Integer.parseInt(entpId)));
			fcu_id = "0";
		}
		String tagId = String.valueOf(indvMaster.getMinTagID(dataSource));
		
		Vector<String> complaintVec =  new Vector<String>();
		complaintVec.add(modifyDraftForm.getBlogTitle());	//blog Title
		complaintVec.add(modifyDraftForm.getBlogtext());	//blog Text
		complaintVec.add(modifyDraftForm.getReleventtext());//Relevent Text
		complaintVec.add(categoryId);						//Category
		complaintVec.add(creationDate);						//Creation Date
		complaintVec.add(publishDate);						//Publish Date
		complaintVec.add(creationTime);						//Creation Time
		complaintVec.add(publishTime);						//Publish Time
		complaintVec.add("0000-00-00");						//Last Modify Date
		complaintVec.add("00:00:00");						//Last Modify Time
		complaintVec.add(uId);								//User (Complainent id) LoginId()uId
		complaintVec.add(compId);							//User Company Id
		complaintVec.add(timePart);							//Time Part
		complaintVec.add("0");								//View Count
		complaintVec.add(entpId);							//Entp Id(Complaint handler company Id)
		complaintVec.add(fcu_id);							//cu_Id(Complaint handler User Id)
		complaintVec.add(advtId);							//advtId(Company Id of brand) 
		complaintVec.add(advtLid);							//advtlId(Company user Id of brand) 
		complaintVec.add(dealerId);							//Dealer Id
		complaintVec.add(tagId);							//Tag Id
		complaintVec.add("0000-00-00");						//Resolved Date
		complaintVec.add("0000-00-00");						//Closed Date
		complaintVec.add("0");								//Brand Flag
		complaintVec.add(publishFlag);						//Publish Flag
		complaintVec.add(publishAt);						//Publish On 
		complaintVec.add(otherBrand);						//otherBrand
		String strComplResult = indvMaster.insertNewComplaints(dataSource, complaintVec);
		
		if(strComplResult.equalsIgnoreCase("success"))
		{
			
			Vector<String> paramVec1 = new Vector<String>();
			paramVec1.add(draftId);
			String draftDelResult = indvMaster.deleteDraft(dataSource, paramVec1);
			if(draftDelResult.equalsIgnoreCase("success"))
			{
			//get complaint Id of this new complaint and set fcom Id for this complaint
				fcomId = indvMaster.getFinalComplainId(dataSource, Integer.parseInt(uId), modifyDraftForm.getBlogTitle(), creationTime);
				String fcomId1 = indvMaster.getComplainId(dataSource, Integer.parseInt(uId), modifyDraftForm.getBlogTitle(), creationTime);
				String fcomarr[] = fcomId1.split("/");
				Vector<String> tempCompVec = new Vector<String>();
				tempCompVec.add(fcomId);//final compaint id
				tempCompVec.add(fcomarr[0]);//complaint id
				indvMaster.setFinalId(dataSource, tempCompVec);
				/**
				 * Added By Ajay Kumar Jha
				 */
				Vector<String> unnamedDataVec = new Vector<String>();
				for(int i=1; i<=35; i++)
				{
					if(request.getParameterValues("field"+i)!= null)
					{
						String arrTemp[] = request.getParameterValues("field"+i);
						String strTemp="";
						for(int j=0; j<arrTemp.length; j++)
						{
							strTemp=(strTemp.equalsIgnoreCase("")) ? arrTemp[j] : strTemp+", "+arrTemp[j];
							//System.out.println("Field "+i+" values "+arrTemp[j]);
						}
						unnamedDataVec.add(strTemp);
					}
					else
					{
						unnamedDataVec.add("");
					}
				}
				String strTempResult = indvMaster.setComplaintUnnamed(dataSource, unnamedDataVec, Integer.parseInt(fcomarr[0].trim()));
				if(strTempResult.equalsIgnoreCase("success"))
				{
					strTempResult = indvMaster.deleteDraftUnnamed(dataSource, Integer.parseInt(draftId.trim()));
				}
				/**
				 * End Here
				 */
				//get mail text for sending mail
				Vector<String> paramVec = new Vector<String>();
				String coreAdminid = String.valueOf(entpMaster.getCompnyAdminUserId(dataSource,Integer.parseInt(entpId)));
				paramVec.add(uId);
				if(coreAdminid.equals(cu_id))
				{
					paramVec.add(cu_id);
				}
				else
				{
					paramVec.add(cu_id);
					paramVec.add(coreAdminid);
				}
				//System.out.println("paramVec>>>>>>>>>>"+paramVec);
				Vector emailVec = indvMaster.getEmailList(dataSource, paramVec);
				Vector<String> tempMailParam = new Vector<String>();
				tempMailParam.add("Consumer");//sender company type
				tempMailParam.add("Enterprise");//receipient company type
				tempMailParam.add("Complaint");//mail description
				//get mail text alert fot sending mail to core user and admin core user
				String mailTextLAlert = rootMaster.getMailText(dataSource, tempMailParam);
				Vector consumerVec = (Vector)emailVec.elementAt(0);
				String consumerName =consumerVec.elementAt(0).toString()+" "+consumerVec.elementAt(1).toString();
				String consumermail = consumerVec.elementAt(2).toString().trim();
				String subject = "Register New Complaint ("+ fcomId + ") by "+ consumerName;
				//System.out.println("emailVec>>>>>>>>>>>>>>"+emailVec);
				for(int i=1; i<emailVec.size();i++)
				{
					MailText mt = new MailText();
					Resource resr = new Resource();
					Vector coreMailVec = (Vector)emailVec.elementAt(i);
					String strMailText = mt.getETextForCoreNewComplaint(coreMailVec,fcomId,mailTextLAlert,consumerName,compId);
					
					resr.sendMail(strMailText, Constant.Email_Sender,((Vector)emailVec.elementAt(i)).elementAt(2).toString() , subject);
					//System.out.println("str Email status fo core"+strEmailStatus);
				}
				//send mail to consumer email address
				//get mail text from the mailText file
				MailText mailtext = new MailText();
				Resource resource = new Resource();
				String strConsumerMailtex = mailtext.getTextForConsumer(consumerName,fcomId);
				String strFinalStatus = resource.sendMail(strConsumerMailtex, Constant.Email_Sender, consumermail, subject);
				System.out.println("str Email Status after consumer"+strFinalStatus);
				if(strFinalStatus.equalsIgnoreCase("success"))
				{
					ActionErrors msg = new ActionErrors();
					msg.clear();
					msg.add("delete", new ActionError("indv.draft.submitSuccess",fcomId));
					
					saveErrors(request, msg);
					status = "Complaintsuccess";
					
					session.removeAttribute("cid");
					session.removeAttribute("sid");
					session.removeAttribute("pid");
					session.removeAttribute("catid");
					session.removeAttribute("Bid");
					session.removeAttribute("otherCatid");
					session.removeAttribute("otherBid");
					
				}
				else
				{
					ActionErrors msg = new ActionErrors();
					msg.clear();
					msg.add("delete", new ActionError("indv.draft.submitFailure"));
					
					saveErrors(request, msg);
					status = "failure";
				}
			}
			//send mail to core user and complaintent user  with fcom id
		}
		else
		{
			ActionErrors msg = new ActionErrors();
			msg.clear();
			msg.add("delete", new ActionError("indv.draft.submitFailure"));
			
			saveErrors(request, msg);
			status = "failure";
		}
		
		
				
		
		return mapping.findForward(status);
	}
	
	
	//supportin function
	public String getCatData(DataSource dataSource,String category , String other)
	{
		
		String userCategory=category;
		RootMaster rootMaster = new RootMaster();
		if(userCategory.equals("-1"))
		{
			//SET category,brand and contact person IN DATA BASE .
			userCategory=rootMaster.insertCategory(dataSource,other);//return category id
			
		}				
		return userCategory;
	}
	public Vector getCountryData(DataSource dataSource,Vector dataVec)
	{
		Vector<String> resultVec = new Vector<String>();
		String userCountry=dataVec.elementAt(2).toString();
		String userState=dataVec.elementAt(4).toString();
		String userCity=dataVec.elementAt(6).toString();
		RootMaster rootMaster = new RootMaster();
		
			if(userCountry.equals("-1"))
			{
				//SET COUNTRY ,STATE ,CITY IN DATA BASE AND GENERATE ID FOR THAT AND ADD THESE IDS IN COMPANY MASTER TABLE.
				userCountry=rootMaster.insertCountry(dataSource,"+0",dataVec.elementAt(3).toString());//return country id
				userState=rootMaster.insertState(dataSource,dataVec.elementAt(5).toString(),userCountry);
				userCity=rootMaster.insertCity(dataSource,dataVec.elementAt(7).toString(),userState,userCountry);
			}
			else if(userState.equals("-1"))
			{
				//set state .city in data base and add these field in company master table
				userState=rootMaster.insertState(dataSource,dataVec.elementAt(5).toString(),userCountry);
				userCity=rootMaster.insertCity(dataSource,dataVec.elementAt(7).toString(),userState,userCountry);
			}
			else if(userCity.equals("-1"))
			{
				//set sity in data base and add that is in company master.
				userCity=rootMaster.insertCity(dataSource,dataVec.elementAt(7).toString(),userState,userCountry);
			}
			else
			{
				//nothing 
			}
		
		resultVec.add(userCountry);
		resultVec.add(userState);
		resultVec.add(userCity);
		
		return resultVec;
	}
	public String updateDealerInfo(DataSource dataSource,Vector dataVec,String advtId)
	{
		String result = "failure";
		
		IndvMaster indvMaster = new IndvMaster();
		Vector<String> dealerVec = new Vector<String>();
		Vector countryVec = getCountryData(dataSource,dataVec);
		
		String countryarr [] = countryVec.elementAt(0).toString().trim().split("~");
		
		dealerVec.add(dataVec.elementAt(0).toString().trim());	//0
		dealerVec.add("");										//1
		dealerVec.add(advtId);									//2
		dealerVec.add(dataVec.elementAt(1).toString().trim());	//3
		dealerVec.add(countryarr[0]);							//4
		dealerVec.add(countryVec.elementAt(1).toString().trim());//5
		dealerVec.add(countryVec.elementAt(2).toString().trim());//6
		dealerVec.add(countryVec.elementAt(2).toString().trim());//7
		dealerVec.add("");										//8
		dealerVec.add("");										//9
		dealerVec.add("");										//10
		dealerVec.add(dataVec.elementAt(8).toString().trim());	//11
		dealerVec.add(dataVec.elementAt(9).toString().trim());	//12
		result = indvMaster.updateDealerInfo(dataSource,dealerVec);
		return result;
	}
	public String insertDealerInfo(DataSource dataSource,Vector dataVec,String advtId)
	{
		String strResult = "0";
		
		IndvMaster indvMaster = new IndvMaster();
		Vector<String> dealerVec = new Vector<String>();
		Vector countryVec = getCountryData(dataSource,dataVec);
		
		String countryarr [] = countryVec.elementAt(0).toString().trim().split("~");
		
		dealerVec.add(dataVec.elementAt(0).toString().trim());	//0
		dealerVec.add("");										//1
		dealerVec.add(advtId);									//2
		dealerVec.add(dataVec.elementAt(1).toString().trim());	//3
		dealerVec.add(countryarr[0]);							//4
		dealerVec.add(countryVec.elementAt(1).toString().trim());//5
		dealerVec.add(countryVec.elementAt(2).toString().trim());//6
		dealerVec.add(countryVec.elementAt(2).toString().trim());//7
		dealerVec.add("");										//8
		dealerVec.add("");										//9
		dealerVec.add("");										//10
		dealerVec.add(dataVec.elementAt(8).toString().trim());	//11
		
		String result = indvMaster.insertDealerInfo(dataSource,dealerVec);
		if(result.equalsIgnoreCase("success"))
		{
			strResult = indvMaster.getMaxDealerId(dataSource, dataVec.elementAt(0).toString().trim());
		}
		
		
		
		return strResult;
	}
}
