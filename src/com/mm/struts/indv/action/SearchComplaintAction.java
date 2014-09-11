
package com.mm.struts.indv.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.struts.indv.form.SearchComplaintForm;
import java.util.Vector;




/** 
 * MyEclipse Struts
 * Creation date: 06-11-2007
 * 
 * XDoclet definition:
 * @struts.action path="/SearchComplaint" name="SearchComplaintForm" input="/form/SearchComplaint.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="SearchComplaint.jsp"
 */
public class SearchComplaintAction extends Action {
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
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		SearchComplaintForm SearchComplaintForm = (SearchComplaintForm) form;// TODO Auto-generated method stub
		 String result = "failure";
		 HttpSession session = request.getSession();
			Vector <String> datavec = new Vector<String>();
			if(SearchComplaintForm.getCid().length()>0)
			{
				datavec.add("fcom_id");
				datavec.add(SearchComplaintForm.getCid());
			}
			if(SearchComplaintForm.getCompdate().length()>0)
			{
				datavec.add("creation_date");
				String date = SearchComplaintForm.getCompdate();
				String datearr[] = date.split("-");
				date = datearr[2]+"-"+datearr[1]+"-"+datearr[0];
				datavec.add(date);
			}
			if(!SearchComplaintForm.getTag().equals("0"))
			{
				datavec.add("tag_id");
				datavec.add(SearchComplaintForm.getTag());
			}
			
			session.setAttribute("searchVec", datavec);
			if(session.getAttribute("searchVec")!=null)
			{
				result = "success";
			}
			
		return mapping.findForward(result);
	}
}


