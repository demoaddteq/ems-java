
package com.mm.struts.advt.action;

import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.*;


public class CompProfilePreAction extends Action {
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		HttpSession session =request.getSession();
		String numCount = (String)session.getAttribute("numCount");	
		DataSource ds = getDataSource(request,"advt");
		IndvMaster indvMaster= new IndvMaster();
		RootMaster rootMaster=new RootMaster();
		Vector dataVec = new Vector();
	 	String pageid = (request.getParameter("pageid")!=null) ? request.getParameter("pageid") : "";
	 	String detailFor = (request.getParameter("detailFor")!=null) ? request.getParameter("detailFor") : "";
	 	String cuid = (request.getParameter("cuid")!=null) ? request.getParameter("cuid") : "";
	 	dataVec.add(cuid);
	 	Vector searchResultVec=new Vector();
		Vector tabVec = indvMaster.getTabDetails(getDataSource(request, "advt"), dataVec);
		 searchResultVec = rootMaster.getCompanyDetail(getDataSource(request, "advt"),Integer.parseInt(cuid));
		//////System.out.println("tabVec....Corporates.....ist Action.........."+tabVec.size());
		 String img ="";
			img=searchResultVec.elementAt(26).toString().trim();
			session.setAttribute("img",img);
	
		//////System.out.println("tabVec....Corporates.....ist Action.........."+tabVec.size());
		
		session.setAttribute("tabVec",tabVec);
		session.setAttribute("cuid", cuid);
	 	session.setAttribute("detailFor", detailFor);
	 	session.setAttribute("pageid", pageid);;
		
		return mapping.findForward("success");
}
	
}
