package com.mm.struts.corpo.action;

import com.mm.struts.corpo.form.CreateInvoiceForm;
import com.mm.core.master.CreateInvoiceDAO;
import com.mm.resource.APPConstant;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import java.util.Vector;

public class CreateInvoiceAction extends Action{
	
	protected final Log logging = LogFactory.getLog(getClass());
	
	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)throws Exception{
		
		String ops = request.getParameter("operation");
		DataSource datasource=getDataSource(request,"corpo");
		CreateInvoiceForm invForm = (CreateInvoiceForm)form;
		CreateInvoiceDAO invDao = new CreateInvoiceDAO();
		Vector vector = new Vector();
		
		if (ops.equals("create")){
			
			logging.info("Inside operation=create");
			int wid = Integer.parseInt(request.getParameter("wid"));
			int tsId = Integer.parseInt(request.getParameter("tsId"));
			logging.info("wid-->"+wid);
			logging.info("tsId-->"+tsId);
			
			invDao.createInvoice(datasource, wid, tsId);
			vector = invDao.showPending(datasource);
			request.setAttribute(APPConstant.PENDING_INVOICE, vector);
			return mapping.findForward("showForm");
		}
		
		
		else {
			
			vector = invDao.showPending(datasource);
			request.setAttribute(APPConstant.PENDING_INVOICE, vector);
			return mapping.findForward("showForm");
		}
	}

}
