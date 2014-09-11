package com.mm.struts.corpo.action;

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

import com.mm.core.master.*;
import com.mm.struts.corpo.form.DeletStuResultForm;

public class DeletStuResultAction  extends Action {
	
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
		HttpSession session = request.getSession();
				
		String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
		Vector allRights =(Vector)session.getAttribute("allRights");
		String adminFlag = allRights.elementAt(18).toString();
		DeletStuResultForm deletStuResultForm = (DeletStuResultForm)form;
		
		String taskId = (request.getParameter("strtaskId")!=null) ? request.getParameter("strtaskId") : "5";
		//System.out.println("taskId.......DeletStuResultAction............"+request.getParameter("strtaskId"));
		request.setAttribute("taskId", taskId);
		
		//String taskId1 = deletStuResultForm.getStrtaskId().toString();
		////System.out.println("taskId1.......DeletStuResultAction............"+taskId1);
		
		RootMaster rootMaster = new RootMaster();
		String status = "failure";
		
		String[] studentsIds = deletStuResultForm.getStudentsIds();
		//System.out.println("studentsIds..................."+studentsIds.length);
		String students = "";
		for(int i=0; i<studentsIds.length; i++)
		{
			//////System.out.println("manoj in action "+i+">>>>>"+draftIds[i]);
			if(i!=(studentsIds.length-1))
				students = students+studentsIds[i]+",";
			else
			{
				students = students+studentsIds[i];
			}
		}
		Vector<String> paramVec = new Vector<String>();
		String result = "failure";
		//////System.out.println("drafts>>>>>>>>>>>"+drafts);
		
		if(taskId.equalsIgnoreCase("5")){
			
			if(students.length()>0)
			{
				paramVec.add(students);
				result = rootMaster.deleteStuResult(getDataSource(request, "corpo"), paramVec);
				
				//System.out.println("result......."+result);
			}
			
		}else if(taskId.equalsIgnoreCase("6")){
			
			paramVec.add(students);
			
			//System.out.println("paramVec.in else......"+paramVec);
			
			result = rootMaster.deleteSearchCriteria(getDataSource(request, "corpo"), paramVec);
			
			//System.out.println("result.in else......"+result);
			
		}
		
		
		
		return mapping.findForward(result);
	}
	
	
	

}
