package com.mm.struts.corpo.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.IndvMaster;
import com.mm.core.master.RootMaster;

public class ShowsudentAction extends Action {
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		
		String result= "success";
		
		DataSource dataSource = getDataSource(request, "corpo");
		HttpSession session =request.getSession();
		
		
		
		RootMaster  rootMaster = new RootMaster();
		IndvMaster  indvMaster = new IndvMaster();
		String numCount = (request.getParameter("numCount")!=null)? request.getParameter("numCount") :(String)session.getAttribute("numCount");
		
		String uId = (request.getParameter("uId")!=null)? request.getParameter("uId").trim() : "0";	
		//System.out.println("uId ...Action......"+uId);
		
		String compId =(String)session.getAttribute("compId");
		
		
		//System.out.println("searchFor...**..."+session.getAttribute("searchFor"));
		//System.out.println("userVec...**...."+session.getAttribute("userVec"));
		//System.out.println("searchVec...**...."+session.getAttribute("searchVec"));
		
		String collegeId = (request.getParameter("collegeId")!=null)? request.getParameter("collegeId").trim() : "0";
		//System.out.println("collegeId...@@...."+collegeId);
		
		
		Vector companData = rootMaster.getCompanyDetail(dataSource, Integer.parseInt(collegeId));
		
		String collegeTeamp = collegeId+"~"+"1";
		//System.out.println("collegeTeamp...@@...."+collegeTeamp);
		
		String  searchFor ="0";
		session.setAttribute("searchFor", "0");
		//System.out.println("searchFor...@@...."+searchFor);
		
		Vector userVec = new Vector();
		session.setAttribute("userVec", userVec);
		
		userVec.add("country =");
		userVec.add(companData.elementAt(6).toString());
		userVec.add("state =");
		userVec.add(companData.elementAt(5).toString());
		userVec.add("city =");
		userVec.add(companData.elementAt(4).toString());
		session.setAttribute("userVec", userVec);
		
		// [country =, 94, state =, 20, city =, 120]
		
		
		/*if(session.getAttribute("searchFor")!=null)
		{
			   searchFor = (String)session.getAttribute("searchFor");
			   
		}
		
		Vector userVec = new Vector();
		
		if(session.getAttribute("userVec")!=null)
		{
			userVec = (Vector)session.getAttribute("userVec");
			session.setAttribute("userVec", userVec);
			//System.out.println("userVec...@@...."+userVec);
		}*/
		
		Vector searchVec = new Vector();
		
		searchVec.add("college_id =");
		searchVec.add(collegeTeamp);
		
		//System.out.println("searchVec...aaaa...."+searchVec);
		
		session.setAttribute("searchVec", searchVec);
		//System.out.println("searchVec...bbbb..."+searchVec);
		
		/*if(session.getAttribute("searchVec")!=null)
		{
			searchVec = (Vector)session.getAttribute("searchVec");
		}*/
		
		//[college_id =, 775~0]
		
		return mapping.findForward(result);
	}

}
