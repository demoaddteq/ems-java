package com.mm.struts.corpo.action;



import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.struts.corpo.form.VendorForm;
import com.addteq.struts.resource.APPConstant;
import com.mm.core.master.vendorDAO;




public class FindVendorAction extends Action {
	
	public ActionForward execute (
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response
			) throws Exception{
		System.out.println("Here-----------------09052010");
		VendorForm myVendorForm=(VendorForm)form;
		String operation = (String)request.getParameter("operation");
		DataSource myDataSource = getDataSource(request,"corpo");
		vendorDAO myVendorDAO = new vendorDAO();
		Vector vendorList = new Vector();
		Vector subconList = new Vector();
		
		String forward = "viewConsultantForm";
		try
		{
			if(operation.equals("show"))
			{
				vendorList = myVendorDAO.getVendorList(myDataSource);
				request.setAttribute(APPConstant.VENDOR_LIST,vendorList);
				forward = "showVendorForm";
			}
			else if(operation.equals("submited"))
			{
				//myConsultantDAO.insertConsultantInfo(myDataSource, consultantForm);
				//consultantList = myConsultantDAO.getConsultantList(myDataSource);
				//request.setAttribute(APPConstant.CON_LIST,consultantList);
				//forward = "showConsultantForm";
			}
			else if(operation.equals("add"))
			{
				//vendorList = myConsultantDAO.getVendorName(myDataSource);
				//request.setAttribute(APPConstant.VEN_LIST, vendorList);
				//subconList = myConsultantDAO.getSubconName(myDataSource);
				//request.setAttribute(APPConstant.SUBCON_LIST, subconList);
				
				forward = "addVendorForm";
				
			}
			else if(operation.equals("delete"))
			{
				String custIds= (String)request.getParameter("Cid");
				//String custIdsArr[] = request.getParameterValues("cust_id");
				String custIdsArr[]=custIds.split(",");
				int z=0;
				for(int i =0; i< custIdsArr.length;i++)
				{
					z= Integer.parseInt(custIdsArr[i]);
					//myConsultantDAO.deleteConsultant(myDataSource,z );
				}
				
				//consultantList = myConsultantDAO.getConsultantList(myDataSource);
				//request.setAttribute(APPConstant.CON_LIST,consultantList);
				forward = "showConsultantForm";
				
			}
			else if(operation.equals("edit"))
			{
				String custID = (String)request.getParameter("Cid");
				int theID = Integer.parseInt(custID);
				//vendorList = myConsultantDAO.getVendorName(myDataSource);
				request.setAttribute(APPConstant.VEN_LIST, vendorList);
				//consultantForm= myConsultantDAO.getConsultantInfo(myDataSource, theID);
				//request.setAttribute(APPConstant.CON_INFO, consultantForm);
				forward ="displayEdit";
			}
			else if(operation.equals("update"))
			{
				//myConsultantDAO.getConsultantUpdated(myDataSource,consultantForm);
				
				//consultantList = myConsultantDAO.getConsultantList(myDataSource);
				//request.setAttribute(APPConstant.CON_LIST,consultantList);
				forward = "showConsultantForm";
				
			}
			else if(operation.equals("addInto"))
			{
				//myConsultantDAO.insertConsultantInfo(myDataSource,consultantForm);
				operation = "show";
				
				
				//consultantList = myConsultantDAO.getConsultantList(myDataSource);
				//request.setAttribute(APPConstant.CON_LIST,consultantList);
				forward = "showConsultantForm";
			}
			
		}
		catch(Exception e)
		{
			
			System.out.println("we got a problem");
		}
		
		return mapping.findForward(forward);
}
}
