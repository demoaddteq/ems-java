package com.mm.struts.student.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.core.master.*;


public class PopUpAction extends Action 
{
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		
		RootMaster rootMaster=new RootMaster();
		String uId = request.getParameter("uId");
		String flag = request.getParameter("flag");
		if(flag.equalsIgnoreCase("1"))
		{
			rootMaster.UpdatepopupFlag(getDataSource(request,"student"),uId, flag);
		}
		return mapping.findForward("sucess");
	}
}
