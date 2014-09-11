/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.corpo.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.RootMaster;
import com.mm.struts.corpo.form.TestquestionForm;

/** 
 * MyEclipse Struts
 * Creation date: 11-29-2007
 * 
 * XDoclet definition:
 * @struts.action path="/testquestion" name="testquestionForm" input="/corpo/testquestion.jsp" scope="request" validate="true"
 */
public class TestquestionAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {

		String target = "success";
		
		TestquestionForm testquestionForm = (TestquestionForm) form;
		Vector<String> testVec = new Vector<String>();
		HttpSession session = request.getSession();
		String compId =(session.getAttribute("compId") != null) ? (String)session.getAttribute("compId") : "0";
		if(Integer.parseInt(compId.trim())>0)
		{
			RootMaster rm = new RootMaster();
			testVec.add(compId.trim());
			testVec.add(testquestionForm.getTestname());
			testVec.add(testquestionForm.getTotalmarks());
			
			//1
			testVec.add(testquestionForm.getQues1());
			testVec.add(testquestionForm.getQuestype1());
			testVec.add(testquestionForm.getQuescheck1());
			testVec.add(testquestionForm.getQuesvisible1());
			
	//		2
			testVec.add(testquestionForm.getQues2());
			testVec.add(testquestionForm.getQuestype2());
			testVec.add(testquestionForm.getQuescheck2());
			testVec.add(testquestionForm.getQuesvisible2());
			
	//		3
			testVec.add(testquestionForm.getQues3());
			testVec.add(testquestionForm.getQuestype3());
			testVec.add(testquestionForm.getQuescheck3());
			testVec.add(testquestionForm.getQuesvisible3());
			
	//		4
			testVec.add(testquestionForm.getQues4());
			testVec.add(testquestionForm.getQuestype4());
			testVec.add(testquestionForm.getQuescheck4());
			testVec.add(testquestionForm.getQuesvisible4());
			
	//		5
			testVec.add(testquestionForm.getQues5());
			testVec.add(testquestionForm.getQuestype5());
			testVec.add(testquestionForm.getQuescheck5());
			testVec.add(testquestionForm.getQuesvisible5());
			
	//		6
			testVec.add(testquestionForm.getQues6());
			testVec.add(testquestionForm.getQuestype6());
			testVec.add(testquestionForm.getQuescheck6());
			testVec.add(testquestionForm.getQuesvisible6());
			
	//		7
			testVec.add(testquestionForm.getQues7());
			testVec.add(testquestionForm.getQuestype7());
			testVec.add(testquestionForm.getQuescheck7());
			testVec.add(testquestionForm.getQuesvisible7());
			
	//		8
			testVec.add(testquestionForm.getQues8());
			testVec.add(testquestionForm.getQuestype8());
			testVec.add(testquestionForm.getQuescheck8());
			testVec.add(testquestionForm.getQuesvisible8());
			
	//		9
			testVec.add(testquestionForm.getQues9());
			testVec.add(testquestionForm.getQuestype9());
			testVec.add(testquestionForm.getQuescheck9());
			testVec.add(testquestionForm.getQuesvisible9());
			
	//		10
			testVec.add(testquestionForm.getQues10());
			testVec.add(testquestionForm.getQuestype10());
			testVec.add(testquestionForm.getQuescheck10());
			testVec.add(testquestionForm.getQuesvisible10());
			
	//		11
			testVec.add(testquestionForm.getQues11());
			testVec.add(testquestionForm.getQuestype11());
			testVec.add(testquestionForm.getQuescheck11());
			testVec.add(testquestionForm.getQuesvisible11());
			
	//		12
			testVec.add(testquestionForm.getQues12());
			testVec.add(testquestionForm.getQuestype12());
			testVec.add(testquestionForm.getQuescheck12());
			testVec.add(testquestionForm.getQuesvisible12());
			
	//		13
			testVec.add(testquestionForm.getQues13());
			testVec.add(testquestionForm.getQuestype13());
			testVec.add(testquestionForm.getQuescheck13());
			testVec.add(testquestionForm.getQuesvisible13());
			
	//		14
			testVec.add(testquestionForm.getQues14());
			testVec.add(testquestionForm.getQuestype14());
			testVec.add(testquestionForm.getQuescheck14());
			testVec.add(testquestionForm.getQuesvisible14());
			
	//		15
			testVec.add(testquestionForm.getQues15());
			testVec.add(testquestionForm.getQuestype15());
			testVec.add(testquestionForm.getQuescheck15());
			testVec.add(testquestionForm.getQuesvisible15());
			
	//		16
			testVec.add(testquestionForm.getQues16());
			testVec.add(testquestionForm.getQuestype16());
			testVec.add(testquestionForm.getQuescheck16());
			testVec.add(testquestionForm.getQuesvisible16());
			
	//		17
			testVec.add(testquestionForm.getQues17());
			testVec.add(testquestionForm.getQuestype17());
			testVec.add(testquestionForm.getQuescheck17());
			testVec.add(testquestionForm.getQuesvisible17());
			
	//		18
			testVec.add(testquestionForm.getQues18());
			testVec.add(testquestionForm.getQuestype18());
			testVec.add(testquestionForm.getQuescheck18());
			testVec.add(testquestionForm.getQuesvisible18());
			
	//		19
			testVec.add(testquestionForm.getQues19());
			testVec.add(testquestionForm.getQuestype19());
			testVec.add(testquestionForm.getQuescheck19());
			testVec.add(testquestionForm.getQuesvisible19());
			
	//		20
			testVec.add(testquestionForm.getQues20());
			testVec.add(testquestionForm.getQuestype20());
			testVec.add(testquestionForm.getQuescheck20());
			testVec.add(testquestionForm.getQuesvisible20());
			
	//		21
			testVec.add(testquestionForm.getQues21());
			testVec.add(testquestionForm.getQuestype21());
			testVec.add(testquestionForm.getQuescheck21());
			testVec.add(testquestionForm.getQuesvisible21());
			
	//		22
			testVec.add(testquestionForm.getQues22());
			testVec.add(testquestionForm.getQuestype22());
			testVec.add(testquestionForm.getQuescheck22());
			testVec.add(testquestionForm.getQuesvisible22());
			
	//		23
			testVec.add(testquestionForm.getQues23());
			testVec.add(testquestionForm.getQuestype23());
			testVec.add(testquestionForm.getQuescheck23());
			testVec.add(testquestionForm.getQuesvisible23());
			
	//		24
			testVec.add(testquestionForm.getQues24());
			testVec.add(testquestionForm.getQuestype24());
			testVec.add(testquestionForm.getQuescheck24());
			testVec.add(testquestionForm.getQuesvisible24());
			
	//		25
			testVec.add(testquestionForm.getQues25());
			testVec.add(testquestionForm.getQuestype25());
			testVec.add(testquestionForm.getQuescheck25());
			testVec.add(testquestionForm.getQuesvisible25());
			
	//		26
			testVec.add(testquestionForm.getQues26());
			testVec.add(testquestionForm.getQuestype26());
			testVec.add(testquestionForm.getQuescheck26());
			testVec.add(testquestionForm.getQuesvisible26());
			
	//		27
			testVec.add(testquestionForm.getQues27());
			testVec.add(testquestionForm.getQuestype27());
			testVec.add(testquestionForm.getQuescheck27());
			testVec.add(testquestionForm.getQuesvisible27());
			
	//		28
			testVec.add(testquestionForm.getQues28());
			testVec.add(testquestionForm.getQuestype28());
			testVec.add(testquestionForm.getQuescheck28());
			testVec.add(testquestionForm.getQuesvisible28());
			
	//		29
			testVec.add(testquestionForm.getQues29());
			testVec.add(testquestionForm.getQuestype29());
			testVec.add(testquestionForm.getQuescheck29());
			testVec.add(testquestionForm.getQuesvisible29());
			
	//		30
			testVec.add(testquestionForm.getQues30());
			testVec.add(testquestionForm.getQuestype30());
			testVec.add(testquestionForm.getQuescheck30());
			testVec.add(testquestionForm.getQuesvisible30());
			
	//		31
			testVec.add(testquestionForm.getQues31());
			testVec.add(testquestionForm.getQuestype31());
			testVec.add(testquestionForm.getQuescheck31());
			testVec.add(testquestionForm.getQuesvisible31());
			
	//		32
			testVec.add(testquestionForm.getQues32());
			testVec.add(testquestionForm.getQuestype32());
			testVec.add(testquestionForm.getQuescheck32());
			testVec.add(testquestionForm.getQuesvisible32());
			
	//		33
			testVec.add(testquestionForm.getQues33());
			testVec.add(testquestionForm.getQuestype33());
			testVec.add(testquestionForm.getQuescheck33());
			testVec.add(testquestionForm.getQuesvisible33());
			
	//		34
			testVec.add(testquestionForm.getQues34());
			testVec.add(testquestionForm.getQuestype34());
			testVec.add(testquestionForm.getQuescheck34());
			testVec.add(testquestionForm.getQuesvisible34());
			
	//		35
			testVec.add(testquestionForm.getQues35());
			testVec.add(testquestionForm.getQuestype35());
			testVec.add(testquestionForm.getQuescheck35());
			testVec.add(testquestionForm.getQuesvisible35());
			
			testVec.add(testquestionForm.getTesttyp());
			testVec.add(testquestionForm.getTestremark());
			testVec.add(testquestionForm.getTestcategory());
			testVec.add(testquestionForm.getMaxtime());
			testVec.add(testquestionForm.getTestsubcat());
			////////System.out.println("testVec "+testVec.size());
			////////System.out.println("testVec "+testVec);
			String strReqType = testquestionForm.getReqtyp();
			String strTestId = "";
		
			
			
			//////////System.out.println("test Question Action>>>>testquestionForm.getTestname()>>>>>>>>>>>>>>>>"+testquestionForm.getTestname());
			//////////System.out.println("test Question Action>>>>testquestionForm.getTotalmarks()>>>>>>>>>>>>>>>>"+testquestionForm.getTotalmarks());
			//////////System.out.println("test Question Action>>>>testquestionForm.getTestname()>>>>>>>>>>>>>>>>"+testquestionForm.getTestname());
			//////////System.out.println("test Question Action>>>>testquestionForm.getTestid()>>>>>>>>>>>>>>>>"+testquestionForm.getTestid());
			//////////System.out.println("test Question Action>>>>testquestionForm.getTestcategory()>>>>>>>>>>>>>>>>"+testquestionForm.getTestcategory());
			//////////System.out.println("test Question Action>>>>testquestionForm.getTestremark()>>>>>>>>>>>>>>>>"+testquestionForm.getTestremark());
			//////////System.out.println("test Question Action>>>>testquestionForm.getTestname()>>>>>>>>>>>>>>>>"+testquestionForm.getQues1());
			//////////System.out.println("test Question Action>>>>testquestionForm.getTestname()>>>>>>>>>>>>>>>>"+testquestionForm.getQuestype1());
			//////////System.out.println("test Question Action>>>>testquestionForm.getTestname()>>>>>>>>>>>>>>>>"+testquestionForm.getQuescheck1());
			//////////System.out.println("test Question Action>>>>testquestionForm.getTestname()>>>>>>>>>>>>>>>>"+testquestionForm.getQuesvisible1());
			
			if(testquestionForm.getTestname().length()==0)
			{	ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureEnter","Test Name"));
				saveErrors(request, errors);
				return mapping.findForward("failure");		
			}else if(testquestionForm.getTestremark().length()==0)
			{	
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureEnter","Test Remark"));
				saveErrors(request, errors);
				return mapping.findForward("failure");		
			}
			////////////////////////////////////////////////////////////
			
			
			
			if(testquestionForm.getQuesvisible1().equalsIgnoreCase("1"))
			{	
				
					if(testquestionForm.getQues1().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name1"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			}
			if(testquestionForm.getQuesvisible2().equalsIgnoreCase("1"))
			{	
			
				 if(testquestionForm.getQues2().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name2"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible3().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues3().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name3"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible4().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues4().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name4"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible5().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues5().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name5"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible6().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues6().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name6"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible7().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues7().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name7"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible8().equalsIgnoreCase("1"))
			{	
			
				 if(testquestionForm.getQues8().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name8"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible9().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues9().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name9"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible10().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues10().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name10"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible11().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues11().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name11"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible12().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues12().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name12"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible13().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues13().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name13"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible14().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues14().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name14"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible15().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues15().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name15"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible16().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues16().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name16"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible17().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues17().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name17"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible18().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues18().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name18"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible19().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues19().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name19"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible20().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues20().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name20"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible21().equalsIgnoreCase("1"))
			{	
			
				 if(testquestionForm.getQues21().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name21"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible22().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues22().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name22"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible23().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues23().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name23"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible24().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues24().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name24"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible25().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues25().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name25"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible26().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues26().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name26"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible27().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues27().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name27"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible28().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues28().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name28"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible29().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues29().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name29"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible30().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues30().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name30"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible31().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues31().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name31"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible32().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues32().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name32"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible33().equalsIgnoreCase("1"))
			{	
			
				
				if(testquestionForm.getQues33().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name33"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible34().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues34().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name34"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			if(testquestionForm.getQuesvisible35().equalsIgnoreCase("1"))
			{	
			
				
				 if(testquestionForm.getQues35().length()==0){
					
					ActionErrors errors = new ActionErrors();
		           	errors.clear();
		          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailureFill","Question Name35"));
					saveErrors(request, errors);
					return mapping.findForward("failure");				
				}
			
			
			}
			
			
			
			
			int i=Integer.parseInt(testquestionForm.getQuesvisible1().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible2().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible3().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible4().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible5().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible6().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible7().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible8().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible9().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible10().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible11().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible12().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible13().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible14().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible15().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible16().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible17().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible18().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible19().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible20().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible21().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible22().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible23().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible24().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible25().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible26().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible27().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible28().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible29().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible30().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible31().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible32().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible33().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible34().toString());
			i=i+Integer.parseInt(testquestionForm.getQuesvisible35().toString());
			
			if(i<10)
			{	
				
				ActionErrors errors = new ActionErrors();
	           	errors.clear();
	          	errors.add("testquestionFormstr", new ActionError("errors.testquestionForm.modiyFailure","Minimum 10 Question in Test"));
				saveErrors(request, errors);
				return mapping.findForward("failure");				
			}
			///////////////////////////////////////////////////////////////////
			if(strReqType.equalsIgnoreCase("New"))
			{
				target = rm.setTest(getDataSource(request, "corpo"), testVec);
				////////System.out.println("target New Question "+target);
				if(target.equalsIgnoreCase("success"))
				{
					int numId = rm.getTestId(getDataSource(request, "corpo"), Integer.parseInt(compId.trim()), testquestionForm.getTestname(), testquestionForm.getTesttyp(), testquestionForm.getTestcategory());
					strTestId = String.valueOf(numId);
				}
			}
			else if(strReqType.equalsIgnoreCase("Edit"))
			{
				strTestId = testquestionForm.getTestid();
				target = rm.editTest(getDataSource(request, "corpo"), testVec, Integer.parseInt(strTestId.trim()));
				////////System.out.println("target Edit Question "+target);
				
			}
			else
			{
				target="success";
			}
			////////System.out.println("Test Data in Test Question "+testVec);
			////////System.out.println("Request Type in test Question "+strTestId);
			//////System.out.println("Test id in test Question "+strTestId);
			session.setAttribute("testid", strTestId);
			session.setAttribute("reqtyp", strReqType);
			session.setAttribute("TestData", testVec);
		}
		else
		{
			target = "failure";
		}
				
		return (mapping.findForward(target));
	}
}