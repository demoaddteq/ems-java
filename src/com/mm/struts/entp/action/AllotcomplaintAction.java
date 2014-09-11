package com.mm.struts.entp.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.struts.entp.form.AllotcomplaintForm;
import com.mm.core.master.*;

import javax.sql.DataSource;
import java.util.*;

/** 
 * MyEclipse Struts
 * Creation date: 06-20-2007
 * 
 * XDoclet definition:
 * @struts.action path="/allotcomplaint" name="AllotcomplaintAction" input="/form/allotcomplaint.jsp" scope="request" 
 * @struts.action-forward name="success" path="allotcomplaint.jsp"
 */

public class AllotcomplaintAction extends Action {
	/*
	 * Generated Methods
	 */
	EntpMaster objectStr = new EntpMaster();

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		AllotcomplaintForm allotcomplaintForm = (AllotcomplaintForm) form;// TODO Auto-generated method stub
		
		String status ="failure";
		Vector <String> datavec = new Vector<String>();	
		EntpMaster entpMaster = new EntpMaster();
		DataSource dataSource = getDataSource(request,"entp");		
		String strCategory = allotcomplaintForm.getPermissions();
		String uid = allotcomplaintForm.getCmbUser();
		String strFinalCat = strCategory.substring(0, strCategory.lastIndexOf(","));
		
		datavec.add(uid);
		datavec.add(strFinalCat);
		
		status = entpMaster.updateAllotment(dataSource,datavec);
		if(status.equalsIgnoreCase("success"))
		{
			Vector<String> paramVec = new Vector<String>();
			paramVec.add(uid);
			IndvMaster indvMaster = new IndvMaster();
			Vector MasilId = indvMaster.getEmailList(dataSource, paramVec);
			String coreuserName = "";
			if(MasilId.size()>0)
			{
				Vector userDataVec = (Vector)MasilId.elementAt(0);
				coreuserName = userDataVec.elementAt(0).toString()+" "+userDataVec.elementAt(1).toString();
				String coreuserMail = userDataVec.elementAt(2).toString();
			}
			ActionErrors msg = new ActionErrors();
			msg.clear();
			msg.add("allotement", new ActionError("errors.catallot.Success",coreuserName));
			
				saveErrors(request, msg);
		}
		else
		{
			ActionErrors msg = new ActionErrors();
			msg.clear();
			msg.add("allotement", new ActionError("errors.catallot.Failure"));
			
			saveErrors(request, msg);
		}
		request.setAttribute("uId", uid);
		return mapping.findForward(status);
	}
}