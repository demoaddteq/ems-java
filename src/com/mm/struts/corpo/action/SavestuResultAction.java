package com.mm.struts.corpo.action;

import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.RootMaster;
import com.mm.struts.corpo.form.SavestuResultForm;

public class SavestuResultAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		String saveStuResult =(String)session.getAttribute("saveStuResult");
		String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
		
		RootMaster  rootmaster = new  RootMaster();
		
		SavestuResultForm savestuResultForm = (SavestuResultForm)form;
		String result = "failure";
		
		String titelname1 = "";
		String accessType1 = "";
		
		// searchType.
		
		String searchType = (request.getParameter("searchType")!=null)? request.getParameter("searchType") :"Student";
		
		//System.out.println("searchType..........."+searchType);
		
		//System.out.println("titelname1...........in action.........."+savestuResultForm.getTitelname1().trim());
		//System.out.println("accessType1...........in action.........."+savestuResultForm.getAcceess1());
		
		if(!savestuResultForm.getTitelname1().trim().equalsIgnoreCase("")){
			 titelname1 = savestuResultForm.getTitelname1();	
			 accessType1 = savestuResultForm.getAcceess1();
		
			// System.out.println("titelname1...........in action.........."+titelname1);
			// System.out.println("accessType1...........in action.........."+accessType1);
		
			String searchFor="";
			Vector userVec = new Vector();
			Vector searchVec = new Vector();
			Vector dataVec = new Vector();
			
			if(session.getAttribute("searchFor")!=null)
			{
				   searchFor = (String)session.getAttribute("searchFor");
				
			}
			
			if(session.getAttribute("userVec")!=null)
			{
				userVec = (Vector)session.getAttribute("userVec");
			}
			
			if(session.getAttribute("searchVec")!=null)
			{
				searchVec = (Vector)session.getAttribute("searchVec");
			}
			
			//System.out.println("searchFor.....1......SavestuResultAction.........."+searchFor);
			//System.out.println("userVec.......1....in action.........."+userVec	);
			//System.out.println("searchVec......1.....in action.........."+searchVec);
			
			java.util.Date dt = new java.util.Date();
			SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");
			String completeRemDate = sform.format(dt);
			StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
		    String creationDate = sttotal.nextToken();
		    String creationTime = sttotal.nextToken();
			
			
			dataVec.add(uId);
			dataVec.add(compId);
			dataVec.add(titelname1);
			dataVec.add(userVec);
			dataVec.add(searchVec);
			dataVec.add(searchFor);			
			dataVec.add(accessType1);
			dataVec.add(creationDate);
			
			result = rootmaster.insertSearchCriteria(getDataSource(request, "corpo"), dataVec);
			
			request.setAttribute("taskId", "6");
			
			//System.out.println("result......1.....in action.........."+result);
		
		}
		else
		{
		
		
				String[] studentsds = savestuResultForm.getStudentsds();
				String students = "";
				for(int i=0; i<studentsds.length; i++)
				{
					////System.out.println("manoj in action "+i+">>>>>"+draftIds[i]);
					if(i!=(studentsds.length-1))
						students = students+studentsds[i]+",";
					else
					{
						students = students+studentsds[i];
					}
				}
				
				String titelname = "";
				String accessType = "";
				
				//System.out.println("titelname...........in action.........."+savestuResultForm.getTitelname().trim());
				//System.out.println("accessType..........in action.........."+savestuResultForm.getAcceess());
				
				if(!savestuResultForm.getTitelname().trim().equalsIgnoreCase("")){
					 titelname = savestuResultForm.getTitelname();	
					 accessType = savestuResultForm.getAcceess();
				}
				//System.out.println("titelname...........in action.........."+titelname);
				//System.out.println("accessType...........in action.........."+accessType);
				
				
				Vector<String> paramVec = new Vector<String>();
				
				//System.out.println("students...........in action.........."+students);
				
				
				java.util.Date dt = new java.util.Date();
				SimpleDateFormat sform = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");
				String completeRemDate = sform.format(dt);
				StringTokenizer sttotal = new StringTokenizer(completeRemDate, ",");
			    String creationDate = sttotal.nextToken();
			    String creationTime = sttotal.nextToken();
				
				paramVec.add(uId);
				paramVec.add(compId);
				paramVec.add(titelname);
				paramVec.add(creationDate);		
				paramVec.add(accessType);
				paramVec.add(students);
				paramVec.add(searchType);
				
				
				if(titelname.length()<1)
				{
					ActionErrors errors = new ActionErrors();
					errors.add("saveresult", new ActionError("errors.company.saveresult"));
					
					if(!errors.isEmpty())
					{
						saveErrors(request, errors);
					}
					result="failure";
				}
				else if(saveStuResult.equalsIgnoreCase("1"))
				{
					//System.out.println("paramVec...........in action.........."+paramVec);
					
						
						result = rootmaster.insertSearchStuResult(getDataSource(request, "corpo"), paramVec);
						
						session.setAttribute("saveStuResult", "0");
						
						//System.out.println("result...........in action.........."+result);
				}
				else{
					
					result = "success";
					//System.out.println("saveStuResult...................."+saveStuResult);
				}
				
				request.setAttribute("taskId", "5");
		}
		
		return mapping.findForward(result);
	}

}
