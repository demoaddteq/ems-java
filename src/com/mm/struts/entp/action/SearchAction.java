/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.entp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.struts.entp.form.SearchForm;
import java.util.*;


/** 
 * MyEclipse Struts
 * Creation date: 06-19-2007
 * 
 * XDoclet definition: 
 * @struts.action path="/search" name="SearchForm" input="/form/search.jsp" scope="request" validate="fals"
 * @struts.action-forward name="success" path="search.jsp"
 */
public class SearchAction extends Action {
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
		SearchForm searchForm = (SearchForm) form;// TODO Auto-generated method stub
		String result = "failure";
		
		HttpSession session = request.getSession();
		if(session.getAttribute("searchVec")!= null)
		{
			session.removeAttribute("searchVec");
		}
		Vector <String> datavec = new Vector<String>();
		if(searchForm.getCid().length()>0)
		{
			datavec.add("fcom_id");
			datavec.add(searchForm.getCid());
		}
		if(searchForm.getCompdate().length()>0)
		{
			datavec.add("creation_date");
			String date = searchForm.getCompdate();
			String datearr[] = date.split("-");
			date = datearr[2]+"-"+datearr[1]+"-"+datearr[0];
			datavec.add(date);
		}
		if(!searchForm.getCategory().equals("0"))
		{
			datavec.add("category");
			datavec.add(searchForm.getCategory());
		}
		if(!searchForm.getBrand().equals("0"))
		{
			datavec.add("advt_id");
			datavec.add(searchForm.getBrand());
		}
		session.setAttribute("searchVec", datavec);
		if(session.getAttribute("searchVec")!= null)
		{
			result = "success";
		}
		
		return mapping.findForward(result);
	}
}