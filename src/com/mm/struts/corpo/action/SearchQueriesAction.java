package com.mm.struts.corpo.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.IndvMaster;
import com.mm.struts.corpo.form.SearchQueriesForm;

public class SearchQueriesAction  extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
	
		String result = "failure";
		HttpSession session = request.getSession();
		SearchQueriesForm searchForm = (SearchQueriesForm) form;// TODO Auto-generated method stub
		
		
		
			Vector <String> datavec = new Vector<String>();
			Vector <String> datavec1 = new Vector<String>();
			
			session.removeAttribute("userVec");
			session.removeAttribute("searchVec");
			session.removeAttribute("searchFor");
			
			String numCount = (request.getParameter("numCount")!=null)? request.getParameter("numCount") :(String)session.getAttribute("numCount");
			
			int count = Integer.parseInt(numCount);
			int reminder = count%2;
			
			if(reminder==0){
				//////System.out.println("numCount...................."+numCount);
			}
			
			String searchFor= "";
			
			if(searchForm.getFcom_id().equalsIgnoreCase("0"))
			{
						
				searchFor= "0";  // Student
				
				if(!searchForm.getTxtCourse().equals("0"))
				{
					datavec.add("course =");
					datavec.add(searchForm.getTxtCourse());
				}
				if(searchForm.getNameField().length()>1)
				{
					datavec.add("first_name like");
					datavec.add("%"+searchForm.getNameField()+"%");
				}
				if(!searchForm.getCollegeList().equals("0"))
				{
					datavec.add("college_id =");
					datavec.add(searchForm.getCollegeList());
				}
				
				
				if(!searchForm.getCountry().equals("0~+0"))
				{
					datavec1.add("country =");
					String[] countryArr = searchForm.getCountry().split("~");
					datavec1.add(countryArr[0]);
				}
				if(!searchForm.getState().equals("0"))
				{
					datavec1.add("state =");
					datavec1.add(searchForm.getState());
				}
				if(!searchForm.getCity().equals("0"))
				{
					datavec1.add("city =");
					datavec1.add(searchForm.getCity());
				}
			}
			else if(searchForm.getFcom_id().equalsIgnoreCase("2"))
			{
				
				
				//datavec.add(searchForm.getNameField());
				//datavec.add(searchForm.getCategory());
				//datavec.add(searchForm.getCountry());
				//datavec.add(searchForm.getState());
				//datavec.add(searchForm.getCity());
				
				searchFor= "2"; //
				
				if(searchForm.getNameField().length()>1)
				{
					datavec.add("company_name like");
					datavec.add("%"+searchForm.getNameField()+"%");
				}
				if(!searchForm.getCategory().equals("0"))
				{
					datavec.add("category like");
					datavec.add("%"+", "+searchForm.getCategory()+","+"%");
				}
				
				
				
				if(!searchForm.getCountry().equals("0~+0"))
				{
					datavec1.add("company_country =");
					String[] countryArr = searchForm.getCountry().split("~");
					datavec1.add(countryArr[0]);
				}
				if(!searchForm.getState().equals("0"))
				{
					datavec1.add("company_state =");
					datavec1.add(searchForm.getState());
				}
				if(!searchForm.getCity().equals("0"))
				{
					datavec1.add("company_city =");
					datavec1.add(searchForm.getCity());
				}
			}
			else if(searchForm.getFcom_id().equalsIgnoreCase("3"))
			{
				
				//datavec.add(searchForm.getNameField());
				//datavec.add(searchForm.getCompanyTemplate());
				//datavec.add(searchForm.getCountry());
				//datavec.add(searchForm.getState());
				//datavec.add(searchForm.getCity());
				
				searchFor= "3"; // College
				
				if(searchForm.getNameField().length()>1)
				{
					datavec.add("company_name like");
					datavec.add("%"+searchForm.getNameField()+"%");
				}
				if(!searchForm.getCompanyTemplate().equals("0"))
				{
					datavec.add("category like");
					datavec.add("%"+", "+searchForm.getCompanyTemplate()+","+"%");
				}
				
				if(!searchForm.getCountry().equals("0~+0"))
				{
					datavec1.add("company_country =");
					String[] countryArr = searchForm.getCountry().split("~");
					datavec1.add(countryArr[0]);
				}
				if(!searchForm.getState().equals("0"))
				{
					datavec1.add("company_state =");
					datavec1.add(searchForm.getState());
				}
				if(!searchForm.getCity().equals("0"))
				{
					datavec1.add("company_city =");
					datavec1.add(searchForm.getCity());
				}
			}
			
			
			
			
			if(datavec1.size()>0)
			{
				session.setAttribute("userVec", datavec1);
				//session.setAttribute("cscVec", datavec1);
			}
			if(datavec.size()>0)
			{
				session.setAttribute("searchVec", datavec);
			}
			if(searchFor.length()>0)
			{
				session.setAttribute("searchFor", searchFor);
			}
			
			if((session.getAttribute("searchVec")!=null) || (session.getAttribute("userVec")!=null))
			{
				result = "success";
			}
			
			
			////System.out.println("userVec...................."+datavec1);
			////System.out.println("searchVec...................."+datavec);
			////System.out.println("searchFor...................."+searchFor);
			
			
		return mapping.findForward(result);
	}

}
