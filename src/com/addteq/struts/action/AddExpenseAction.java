package com.addteq.struts.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.addteq.struts.DAO.ExpenseDAO;
import com.addteq.struts.form.ExpenseForm;
import com.addteq.struts.resource.APPConstant;

import javax.sql.DataSource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.StringTokenizer;

public class AddExpenseAction extends Action{
	
	protected final Log logging = LogFactory.getLog(getClass());

	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
								 throws Exception {
		
		String ops = request.getParameter("operation");
		DataSource datasource=getDataSource(request,"corpo");
		ExpenseForm expForm = (ExpenseForm)form;
		ExpenseDAO expDao = new ExpenseDAO();
		
		Vector vector = new Vector();
		Vector vector2 = new Vector();
		vector2 = expDao.getCategory(datasource);
		request.setAttribute(APPConstant.CATEGORY, vector2);

		if (ops.equals("add")){
			
			String str = expForm.getExpDate();

			expDao.addExpense(datasource, expForm);

			vector = expDao.getExpenses(datasource, "current", str);
			request.setAttribute(APPConstant.EXPENSES, vector);
			request.setAttribute("page", str);
			
			return mapping.findForward("showForm");
		}
		
		else if (ops.equals("previous")){
			
			String s = (String)request.getParameter("str");
			logging.info("request.getParameter(page)-->"+s);
			
			Calendar c1 = Calendar.getInstance(); 
			
			StringTokenizer sToken = new StringTokenizer(s, "/");
			int  month = Integer.parseInt(sToken.nextToken());
			int day = Integer.parseInt(sToken.nextToken());
			int year = Integer.parseInt(sToken.nextToken());
			
			c1.set(year,month-1,day);
			c1.add(Calendar.MONTH, -1);
			logging.info("Previous Date is : " + c1.getTime());

			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			request.setAttribute("page", sdf.format(c1.getTime()));
			
			vector = expDao.getExpenses(datasource, ops, sdf.format(c1.getTime()));
			request.setAttribute(APPConstant.EXPENSES, vector);
			return mapping.findForward("showForm");
		}
		
		else if (ops.equals("next")){

			String s = (String)request.getParameter("str");
			logging.info("request.getParameter(page)-->"+s);
			
			Calendar c1 = Calendar.getInstance(); 
			
			StringTokenizer sToken = new StringTokenizer(s, "/");
			int  month = Integer.parseInt(sToken.nextToken());
			int day = Integer.parseInt(sToken.nextToken());
			int year = Integer.parseInt(sToken.nextToken());
			
			c1.set(year,month-1,day);
			c1.add(Calendar.MONTH, 1);
			logging.info("Next Date is : " + c1.getTime());

			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			request.setAttribute("page", sdf.format(c1.getTime()));
			
			vector = expDao.getExpenses(datasource, ops, sdf.format(c1.getTime()));
			request.setAttribute(APPConstant.EXPENSES, vector);
			
			return mapping.findForward("showForm");
		}
		
		else if (ops.equals("first")){
			
			logging.info("Before calling the DAO with 'first'.");
			vector = expDao.getExpenses(datasource, ops, null);
			
			if (vector.size()!=0){
				ExpenseForm eForm = (ExpenseForm) vector.get(0);
				request.setAttribute("page", eForm.getExpDate());
			}
			
			request.setAttribute(APPConstant.EXPENSES, vector);
					
			return mapping.findForward("showForm");
		}
		
		else {
			
			vector = expDao.getExpenses(datasource, "last", null);
			
			if (vector.size()!=0){
				ExpenseForm eForm = (ExpenseForm) vector.get(0);
				request.setAttribute("page", eForm.getExpDate());
			}
			
			request.setAttribute(APPConstant.EXPENSES, vector);
			
			return mapping.findForward("showForm");
		}
	}
}
