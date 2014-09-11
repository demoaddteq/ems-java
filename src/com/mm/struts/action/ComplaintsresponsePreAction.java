
package com.mm.struts.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.RootMaster;
import com.mm.struts.form.ComplaintsresponseForm;



/** 
 * MyEclipse Struts
 * Creation date: 08-03-2007
 * 
 * XDoclet definition:
 * @struts.action path="/complaintsresponse" name="ComplaintsresponseForm" input="/indvLoginForComments.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/complaintsresponse.jsp"
 * @struts.action-forward name="failure" path="/indvLoginForComments.jsp"
 */

public class ComplaintsresponsePreAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ComplaintsresponseForm objectForm = new ComplaintsresponseForm();
		
		String result = "";
		
		String complainid = (request.getParameter("complainid")!=null) ? request.getParameter("complainid") : (String)request.getAttribute("complainid");
		request.setAttribute("complainid", complainid);
		////System.out.println("complainid..........ComplaintsresponsePreAction.........."+complainid);
		//////System.out.println("request.getParameter.........ComplaintsresponsePreAction.........."+request.getParameter("complainid"));
		
		
		String strUserId = (request.getParameter("strUserId")!=null) ? request.getParameter("strUserId") : (String)request.getAttribute("strUserId");
		request.setAttribute("strUserId", strUserId);
		////System.out.println("strUserId..........ComplaintsresponsePreAction.........."+strUserId);
		//////System.out.println("request.getParameter.........ComplaintsresponsePreAction.........."+request.getParameter("strUserId"));
			
		RootMaster rootMaster= new RootMaster();		
		DataSource dataSource = getDataSource(request,"main");	
		
		Vector userData = rootMaster.getUserInfo(dataSource,Integer.parseInt(strUserId));
		String companyId = userData.elementAt(0).toString();
		request.setAttribute("companyId", companyId);
		String first_name = userData.elementAt(4).toString();
		String last_name = userData.elementAt(5).toString();
		////System.out.println("first_name..........ComplaintsresponsePreAction.........."+first_name);
		
		Vector compData = rootMaster.getComplaintDetails(dataSource,Integer.parseInt(complainid));
		String fcom_id = compData.elementAt(17).toString();
		String complaint_title = compData.elementAt(1).toString();
		////System.out.println("fcom_id..........ComplaintsresponsePreAction.........."+fcom_id);
		////System.out.println("complaint_title..........ComplaintsresponsePreAction.........."+complaint_title);
		
		//objectForm.setComplainTitle(complaint_title);
		//objectForm.setFcomplainId(fcom_id);
		request.setAttribute("fcom_id", fcom_id);
		request.setAttribute("complaint_title", complaint_title);
		
		
		return mapping.findForward("success");
	}
		

}
