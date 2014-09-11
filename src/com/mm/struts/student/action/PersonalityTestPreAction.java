package com.mm.struts.student.action;

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

public class PersonalityTestPreAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String result = "success";
		
		System.out.println(".........PersonalityTestPreAction........");
		
		Vector <String> datavec = new Vector<String>();		
		DataSource ds = getDataSource(request,"student");
		IndvMaster indvMaster = new IndvMaster();
		RootMaster rootMaster = new RootMaster();		
		
		HttpSession session = request.getSession();
		String uId = (String)session.getAttribute("uId");
		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		
		String testId =(request.getParameter("testId")!=null) ? request.getParameter("testId") : "0";
		
		
		Vector testQuestionsVec = rootMaster.getTestQuestions( ds, Integer.parseInt(testId.trim()));
		request.setAttribute("testQuestionsVec", testQuestionsVec);
		//System.out.println("testQuestionsVec........"+testQuestionsVec);
		
		int m = 105;
		for(int j=1;j<36;j++){
			
			String visibleField = testQuestionsVec.elementAt(m++).toString();
			if(visibleField.equalsIgnoreCase("1")){
				
				Vector testAnsVec = rootMaster.getTestAns( ds, j, Integer.parseInt(testId.trim()));
				
				String testStr = "testStr"+j;
				
				request.setAttribute(testStr, testAnsVec);
				
				//System.out.println("j.............."+j);
				//System.out.println("testAnsVec.............."+testAnsVec);
			}
		}
		
		
		//Vector testAnsVec = rootMaster.getTestAns( ds, Integer.parseInt(testId.trim()));
		//request.setAttribute("testAnsVec", testAnsVec);
		////System.out.println("testAnsVec.............."+testAnsVec);
		
		
		
		
		return mapping.findForward(result);
	}

}
