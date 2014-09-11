package com.mm.struts.entp.action;


import java.io.IOException;
import java.rmi.ServerException;
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
import com.mm.struts.entp.form.ManipulategrpForm;

/** 
 * MyEclipse Struts
 * Creation date: 08-01-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */

public class ManipulateusergrpAction extends Action {
	
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
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServerException 
	{
		
		
		String target = new String("failure");
			
			EntpMaster entpMaster = new EntpMaster();
			RootMaster rootMaster = new RootMaster();
			ManipulategrpForm mgf = (ManipulategrpForm)form;
			HttpSession session = request.getSession();
			int numTempCount = Integer.parseInt(mgf.getTempcount().trim());
			String strPageTarget = "manipulategrp.jsp?numCount="+numTempCount;
	//		Company Id
			String strCompId = (session.getAttribute("compId") !=null) ? session.getAttribute("compId").toString().trim(): "0";
			
			String strCmdType = mgf.getCmdtyp();
			//System.out.println("Command Type "+strCmdType);
			String strGRid = mgf.getGrpid();
			////System.out.println("Group Id "+strGRid);
			
			if(strCmdType.trim().equalsIgnoreCase("Delete"))
			{
				target = entpMaster.deleteUserGroup(getDataSource(request, "entp"), Integer.parseInt(strGRid.trim()), Integer.parseInt(strCompId.trim()));
				
				if(target.equalsIgnoreCase("success")){
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("deleteUserGroup", new ActionError("errors.entp.deleteUserGroup"));
					saveErrors(request, errors);
					target = "success";
				}
				return (mapping.findForward(target));
			}
			
			String strGrpName = mgf.getGrpname();
			////System.out.println("Group Name "+strGrpName);
			String strRights = mgf.getPermissions();
			////System.out.println("Rights "+strRights);
			String strAllRights = "accmanagement, users, usercreation, userdeletion, usermodification, change_password, complaintmanagement, mis, complaints";
			String arrAllRights[] = strAllRights.split(",");
			
			if(strGrpName.equalsIgnoreCase(""))
			{
				ActionErrors errors = new ActionErrors();
				errors.add("notexistgroup", new ActionError("errors.usergroup.notexistgroup"));
				
					saveErrors(request, errors);
				
				return mapping.findForward("failure");
				
			}
			
			
			Vector<String> dataVec = new Vector<String>();
			dataVec.add(strGRid);
			dataVec.add(strCompId);
			dataVec.add(strGrpName);
			for(int i=0; i<arrAllRights.length; i++)
			{
				////System.out.println("arrAllRights["+i+"] = "+strRights.indexOf(arrAllRights[i].trim()));
				if(strRights.indexOf(arrAllRights[i].trim()) <= -1)
				{
					dataVec.add("0");
				}
				else
				{
					dataVec.add("1");
				}
				
			}
			////System.out.println("dataVec "+dataVec);
			if(strCmdType.trim().equalsIgnoreCase("Add New"))
			{	
				DataSource ds = getDataSource(request,"entp");	
				String strVerifyResult=rootMaster.checkGroupName(ds, strCompId, strGrpName);
				////System.out.println("out side if ........1....");
				if(!strVerifyResult.equals("failure"))
				{
					ActionErrors errors = new ActionErrors();
					errors.add("duplicategroup", new ActionError("errors.group.duplicategroup"));
					
						saveErrors(request, errors);
						//System.out.println("in side if ............");
					
					return mapping.findForward("failure");
					
				}
				
				target = entpMaster.setUserGroup(getDataSource(request, "entp"), dataVec);
				
				if(target.equalsIgnoreCase("success")){
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("addUserGroup", new ActionError("errors.entp.addUserGroupSuccess"));
					saveErrors(request, errors);
					target = "success";
				}
			}
			else if(strCmdType.trim().equalsIgnoreCase("Modify"))
			{
				target = entpMaster.editUserGroup(getDataSource(request, "entp"), dataVec);
				if(target.equalsIgnoreCase("success")){
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("modifyUserGroup", new ActionError("errors.entp.modifyUserGroupSuccess"));
					saveErrors(request, errors);
					target = "success";
				}
				
			}
			else if(strCmdType.trim().equalsIgnoreCase("Delete"))
			{
				target = entpMaster.deleteUserGroup(getDataSource(request, "entp"), Integer.parseInt(strGRid.trim()), Integer.parseInt(strCompId.trim()));
				
				if(target.equalsIgnoreCase("success")){
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("deleteUserGroup", new ActionError("errors.entp.deleteUserGroup"));
					saveErrors(request, errors);
					target = "success";
				}
			}
			
		
		
		return (mapping.findForward(target));
	}

}
