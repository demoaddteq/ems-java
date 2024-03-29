/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.corpo.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.addteq.struts.DAO.expenseReportDAO;
import com.addteq.struts.form.ExpenseReportForm;
import com.addteq.struts.resource.APPConstant;

/** 
 * MyEclipse Struts
 * Creation date: 04-05-2010
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class FindExpenseReportAction extends Action {
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
		// TODO Auto-generated method stub
		
		ExpenseReportForm myExpenseReportForm=(ExpenseReportForm)form;
		String operation = (String)request.getParameter("operation");
		String year = (String)request.getParameter("year");
		String m = (String)request.getParameter("description");
		DataSource myDataSource = getDataSource(request,"corpo");
		expenseReportDAO myExpenseReportDAO = new expenseReportDAO();
		Vector categoryList = new Vector();
		//Vector<ExpenseReportForm> categoryDetailList[];
		Vector categoryDetailList;
		Vector vendorList = new Vector();
		Vector subconList = new Vector();
		Vector searchResult= new Vector();
		String forward = "viewConsultantForm";
		try
		{
			if(operation.equals("show"))
			{
				forward = "showExpenseReport";
			}
			else if(operation.equals("view"))
			{
				 categoryList = myExpenseReportDAO.getCategoryReport(myDataSource,year);
				 request.setAttribute(APPConstant.EXPENSE_CATEGORY_LIST,categoryList);
				 forward = "viewResult";
			}
			else if(operation.equals("home"))
			{
				forward ="viewIndex";
				
			}
			else if(operation.equals("detail"))
			{
				String custIds= (String)request.getParameter("Cid");
				//String custIdsArr[] = request.getParameterValues("cust_id");
				
				String custIdsArr[]=custIds.split(",");
				//categoryDetailList = new Vector[custIdsArr.length];
				categoryDetailList = new Vector();
				int z=0;
				//for(int i =0; i< custIdsArr.length;i++)
				//{
					z= Integer.parseInt(custIdsArr[0]);
					categoryDetailList=myExpenseReportDAO.getCategoryDetailReport(myDataSource, z );
				//}
				request.setAttribute("cid", custIds);
				request.setAttribute(APPConstant.EXPENSE_CATEGORYDETAIL_LIST,categoryDetailList);
				forward = "showCategoryDetailForm";
				
			}
			else if(operation.equals("search"))
			{
				
				searchResult = myExpenseReportDAO.getSearchResult(myDataSource,m);
				 request.setAttribute(APPConstant.EXPENSE_SEARCH,searchResult);
				 forward = "viewSearchResult";
				
			}
			else if(operation.equals("viewSearch"))
			{
				forward = "showSearch";
				
			}
			
			
		}
		catch(Exception e)
		{
			
			System.out.println("we got a problem");
		}
		
		
		return mapping.findForward(forward);
	}
}