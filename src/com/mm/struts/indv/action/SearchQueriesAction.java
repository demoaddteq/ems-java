package com.mm.struts.indv.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.IndvMaster;
import com.mm.struts.indv.form.SearchQueriesForm;

public class SearchQueriesAction  extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
	
		String result = "failure";
		HttpSession session = request.getSession();
		SearchQueriesForm SearchQueriesForm = (SearchQueriesForm) form;// TODO Auto-generated method stub
		
		String strField1 = SearchQueriesForm.getFcom_id();
		/*
		 * 			0 = Student (Consumer)
		 * 			1 = Consultants (Consumer)
		 * 			2 = Corporates (Corporates)
		 * 			3 = Colleges (Advertiser)
		 */
		
		String strField2 = SearchQueriesForm.getCountry();
		String strField3 = SearchQueriesForm.getState();
		String strField4 = SearchQueriesForm.getCity();
		String strField5 = SearchQueriesForm.getCategory();
		String strField6 = SearchQueriesForm.getBrand();
		String strField7 = SearchQueriesForm.getNameField();
		
		System.out.println("strField1..........."+strField1);
		System.out.println("strField2..........."+strField2);
		System.out.println("strField3..........."+strField3);
		System.out.println("strField4..........."+strField4);
		System.out.println("strField5..........."+strField5);
		System.out.println("strField6..........."+strField6);
		System.out.println("strField7..........."+strField7);
		
		
		Vector <String> datavec = new Vector<String>();
		
				
		if(strField1.equalsIgnoreCase("0")){   // 0 = Student (Consumer)
			
			datavec.add("login_type =");
			datavec.add("Student");		
			if(!strField2.equalsIgnoreCase("0")){
				datavec.add("country =");
				
				String arrCountry[] = strField2.split("~");				
				datavec.add(arrCountry[0]);		
			}
			if(!strField3.equalsIgnoreCase("0")){
				datavec.add("state =");
				datavec.add(strField3);		
			}
			if(!strField4.equalsIgnoreCase("0")){
				datavec.add("city =");
				datavec.add(strField4);		
			}
			if(strField7.length()!=0){
				datavec.add("first_name like");
				datavec.add("%"+strField7+"%");		
			}
		}
		else if(strField1.equalsIgnoreCase("1")){  // 1 = Consultants (Consumer)
			
			datavec.add("login_type =");
			datavec.add("Consultants");
			if(!strField2.equalsIgnoreCase("0")){
				datavec.add("country =");
				
				String arrCountry[] = strField2.split("~");				
				datavec.add(arrCountry[0]);	
			}
			if(!strField3.equalsIgnoreCase("0")){
				datavec.add("state =");
				datavec.add(strField3);		
			}
			if(!strField4.equalsIgnoreCase("0")){
				datavec.add("city =");
				datavec.add(strField4);		
			}
			if(strField7.length()!=0){
				datavec.add("first_name like");
				datavec.add("%"+strField7+"%");		
			}
		}
		else if(strField1.equalsIgnoreCase("2")){  // 2 = Corporates (Corporates)
			
			datavec.add("typeofcompany =");
			datavec.add("Corporates");
			if(!strField2.equalsIgnoreCase("0")){
				datavec.add("company_country =");
				
				String arrCountry[] = strField2.split("~");				
				datavec.add(arrCountry[0]);		
			}
			if(!strField3.equalsIgnoreCase("0")){
				datavec.add("company_state =");
				datavec.add(strField3);		
			}
			if(!strField4.equalsIgnoreCase("0")){
				datavec.add("company_city =");
				datavec.add(strField4);		
			}
			if(!strField5.equalsIgnoreCase("0")){
				
				datavec.add("category like ");
				datavec.add("%"+strField5+"%");	
			}
			if(strField7.length()!=0){
				datavec.add("company_name like");
				datavec.add("%"+strField7+"%");		
			}
		}
		else if(strField1.equalsIgnoreCase("3")){  // 	3 = Colleges (Advertiser)
			
			datavec.add("typeofcompany =");
			datavec.add("Advertiser");
			if(!strField2.equalsIgnoreCase("0")){
				datavec.add("company_country =");
				
				String arrCountry[] = strField2.split("~");				
				datavec.add(arrCountry[0]);		
			}
			if(!strField3.equalsIgnoreCase("0")){
				datavec.add("company_state =");
				datavec.add(strField3);		
			}
			if(!strField4.equalsIgnoreCase("0")){
				datavec.add("company_city =");
				datavec.add(strField4);		
			}
			if(strField7.length()!=0){
				datavec.add("company_name like");
				datavec.add("%"+strField7+"%");		
			}
		}
		
		
		System.out.println("size of datavec......"+datavec.size());
		System.out.println("datavec.............."+datavec);
		
		IndvMaster indvMaster = new IndvMaster();
		
		session.setAttribute("searchDataVec", datavec);
		if(session.getAttribute("searchDataVec")!=null)
		{
			result = "success";
		}
		
		return mapping.findForward(result);
	}

}
