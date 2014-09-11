
package com.mm.struts.student.action;

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
		DataSource ds = getDataSource(request,"student");
		
		IndvMaster indvMaster= new IndvMaster();
		RootMaster rootMaster=new RootMaster();
		Vector dataVec = new Vector();
	 	
		String pageid = (request.getParameter("pageid")!=null) ? request.getParameter("pageid") : (String)session.getAttribute("pageid");
	 	String detailFor = (request.getParameter("detailFor")!=null) ? request.getParameter("detailFor") : "";
	 	String cuid = (request.getParameter("cuid")!=null) ? request.getParameter("cuid") : "noid";
	 	
	 	//System.out.println("cuid...."+cuid);
	 	
	 	// new code
	 	int index = (request.getParameter("index")!=null) ? Integer.parseInt((String)request.getParameter("index").toString().trim()) : Integer.parseInt((String)session.getAttribute("index").toString().trim());	 
	 	//System.out.println("index...."+index);
	 	Vector allcid = (Vector)session.getAttribute("allcid");
	 	//System.out.println("allcid...."+allcid);
	 	
	 	String newId = (String)allcid.elementAt(index);	 	
	 	if(cuid.equalsIgnoreCase("noid")){	 		
	 		cuid = newId;
	 		}
	 	
	 	session.setAttribute("index", index+1);
	 	
	 	if((allcid.size()-1)==index){
	 		
	 		session.removeAttribute("index");
	 		session.setAttribute("index", "-1");
	 	}
	 	
	 	// end
	 	
	 	dataVec.add(cuid);
	 	Vector searchResultVec=new Vector();
		Vector tabVec = indvMaster.getTabDetails(getDataSource(request, "student"), dataVec);
		 searchResultVec = rootMaster.getCompanyDetail(getDataSource(request, "student"),Integer.parseInt(cuid));
		////System.out.println("tabVec....Corporates.....ist Action.........."+tabVec.size());
		 String img ="";
			img=searchResultVec.elementAt(26).toString().trim();
			session.setAttribute("img",img);
		
		session.setAttribute("tabVec",tabVec);
		session.setAttribute("cuid", cuid);
	 	session.setAttribute("detailFor", detailFor);
	 	session.setAttribute("pageid", pageid);
	 	
	 	//System.out.println("return..........success....");
		
		return mapping.findForward("success");
}
	
}
