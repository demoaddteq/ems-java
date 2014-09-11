package com.mm.struts.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.*;
import com.mm.struts.form.MentormeForm;

public class MentormeAction extends Action {
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session= request.getSession();
		String type = "";
		String subType = "";
		
		
		
			/*type = (String)session.getAttribute("type");
			
			if(session.getAttribute("subType")!=null)
			{
				subType= (String)session.getAttribute("subType");
				
				////System.out.println("subType.......on LoginAction.jsp........"+subType);
			}*/
		
		RootMaster rootMaster= new RootMaster();
		IndvMaster indvMaster= new IndvMaster();
		
		MentormeForm mentormeForm = (MentormeForm) form;// TODO Auto-generated method stub
		DataSource dataSource = getDataSource(request,"main");	
		Vector <String> dataVec = new Vector<String>();	
		
		String result = "failure";
		
		dataVec.add(mentormeForm.getLoginId().trim());
		dataVec.add(mentormeForm.getPasword().trim());
		dataVec.add("indv");
		dataVec.add("indv");
		
		////System.out.println("dataVec.......Action ........"+dataVec);
		
		
		Vector userVec = rootMaster.verifyUser(dataSource, dataVec);
		
		////System.out.println("userVec.......Action ........"+userVec);
		
		if(userVec.size()==1)
		{
			ActionErrors errors = new ActionErrors();
			if(userVec.elementAt(0).toString().equalsIgnoreCase("loginFailure"))
			{
				errors.add("notFound", new ActionError("errors.user.notFound"));
				if(!errors.isEmpty())
				{
					saveErrors(request, errors);
				}
				return mapping.findForward("failure");
			}
			if(userVec.elementAt(0).toString().equalsIgnoreCase("companyFailure"))
			{
				errors.add("notFound", new ActionError("errors.company.notExist"));
				if(!errors.isEmpty())
				{
					saveErrors(request, errors);
				}
				return mapping.findForward("failure");
			}
			if(userVec.elementAt(0).toString().equalsIgnoreCase("activeFailure"))
			{
				if(type.equalsIgnoreCase("indv"))
				{
					
					return mapping.findForward("activeFailure");
				}
				else
				{
					errors.add("notFound", new ActionError("errors.user.acivate"));
					if(!errors.isEmpty())
					{
						saveErrors(request, errors);
					}
					return mapping.findForward("failure");
				}
			}
			
		}
		else
		{
			String uId = userVec.elementAt(1).toString();
			
			Vector <String> dataVec1 = new Vector<String>();
			dataVec1.add(uId);
			dataVec1.add("Mrequest");
			
			
			int totalReq = indvMaster.getMentorTotalCount(dataSource,Integer.parseInt(userVec.elementAt(1).toString()) );
			
			////System.out.println("totalReq..................."+totalReq);
			
			Vector <String> dataVec2 = new Vector<String>();
			dataVec2.add(uId);
			dataVec2.add(mentormeForm.getLoginId().trim());
			dataVec2.add(mentormeForm.getPasword().trim());
			
			if(totalReq>=3){
				
				dataVec2.add("noMore");
			}
			else
			{
				dataVec2.add("More");
			}
			
			
			request.setAttribute("userdata", dataVec2);
			
			////System.out.println("userlogindata..................."+dataVec2);			
			session.setAttribute("userlogindata", dataVec2);
			
			result = "success"; 
			
		}
		
		////System.out.println("result..................."+result);
		return mapping.findForward(result);

		
		
	}
	
}
