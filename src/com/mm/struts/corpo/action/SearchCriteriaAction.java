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

public class SearchCriteriaAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		
		String result = "success";
		DataSource dataSource = getDataSource(request, "corpo");
		HttpSession session =request.getSession();
		
		String taskId = (request.getParameter("taskId")!=null)? request.getParameter("taskId") :"0";
		//////System.out.println("taskId ........."+taskId);

		RootMaster  rootMaster = new RootMaster();
		IndvMaster  indvMaster = new IndvMaster();
		String numCount = (request.getParameter("numCount")!=null)? request.getParameter("numCount") :(String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int reminder = count%2;
		
		String uId = (request.getParameter("uId")!=null)? request.getParameter("uId").trim() : "0";	
		////////System.out.println("uId ...Action......"+uId);
		
		String compId =(String)session.getAttribute("compId");
				
		//////////System.out.println("compId..in Action avdt ........."+compId);
		int regFlag= rootMaster.getRegisteredFlag(getDataSource(request, "corpo"), compId);
		//////////System.out.println("regFlag..in Action avdt ........."+regFlag);
		String searchResultId = (request.getParameter("searchResultId")!=null)? request.getParameter("searchResultId") :"0";
		
		////////System.out.println("searchResultId ........."+searchResultId);
		if(taskId.equalsIgnoreCase("6")){
			
			
			Vector SearchCriteriaVec = rootMaster.getSearchCriteria(dataSource, Integer.parseInt(searchResultId.trim()));
				
			//////System.out.println("SearchCriteriaVec ........."+SearchCriteriaVec);
			
			
			
			Vector<String> userVec = new Vector<String>();	
			Vector<String> searchVec = new Vector<String>();
			String searchFor = SearchCriteriaVec.elementAt(6).toString();
			
			String userStr = SearchCriteriaVec.elementAt(7).toString();
			String userStr2= userStr.replace("[", "");
			String userStr3= userStr2.replace("]", "");			
			//////System.out.println("userStr3 ........."+userStr3);			
			String userStr1[]= userStr3.split(",");
			for(int i=0; i<userStr1.length; i++)
			{
				userVec.add(userStr1[i]);
				//////System.out.println("userStr1. ......."+userStr1[i]);
			}
			
			
			String searchStr = SearchCriteriaVec.elementAt(8).toString();
			String searchStr2= searchStr.replace("[", "");
			String searchStr3= searchStr2.replace("]", "");			
			//////System.out.println("searchStr3 ........."+searchStr3);
			
			String searchStr1[]= searchStr3.split(", ");
			
			//////System.out.println("searchStr1.length ......."+searchStr1.length);
			
			for(int i=0; i<searchStr1.length; i++)
			{	
				//////System.out.println("searchStr1. ......."+searchStr1[i]);
				//String searchStrTeam[]= searchStr1[].split(",");
				if(!searchStr1[i].equalsIgnoreCase("%")){
				searchVec.add(searchStr1[i]);
				}else{
					i++;
					String teamStr = "%, "+searchStr1[i];
					searchVec.add(teamStr);
				}
			}
			
			//////System.out.println("...................... .......");
			/*
			for(int i=0; i<searchStr1.length; i++)
			{
				searchVec.add(searchStr1[i]);
			}*/
			
			session.setAttribute("searchFor", searchFor);		
			//session.setAttribute("searchVec", searchVec);		
			session.setAttribute("userVec", userVec);	
			
			session.setAttribute("searchVec", searchVec);	
			
			//////System.out.println("searchFor .....SearchCriteriaAction...."+searchFor);
			//////System.out.println("userVec .......SearchCriteriaAction.."+userVec);
			////System.out.println("searchVec .....SearchCriteriaAction...."+searchVec);
			}
		
		return mapping.findForward(result);
	}

}
