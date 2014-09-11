package com.mm.struts.corpo.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.core.master.RootMaster;

public class TagAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		RootMaster rootMaster=new RootMaster();
		
		String tagID = (request.getParameter("tagId")!=null) ? (request.getParameter("tagId").trim()) : "0";
		System.out.println("tag "+tagID);		
		
		int numCId=Integer.parseInt(tagID);
		int numdesId = (request.getParameter("desId")!=null) ? Integer.parseInt(request.getParameter("desId").trim()) : 0;
		
		
		
		Vector streamVec = rootMaster.getTagList(getDataSource(request, "corpo"));
		System.out.println("Stream Vec"+streamVec);
	    String strStream = getCourseStreamList(tagID, streamVec);
	    
	    //////System.out.println("States "+strState);
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strStream);
	    out.flush();
		return null;
	}
	private String getCourseStreamList(String numTid,  Vector streamVec)
	{
		String strTemCat[] = numTid.split(", ");
		String strResult ="<select name=\"tag\" onchange=\"outputSelected(this.form.tag)\">";
		for(int i=0; i<streamVec.size(); i++)
		{	
			int flag = 0;
			Vector tempVec = (Vector)streamVec.elementAt(i);
			String numCatId = tempVec.elementAt(0).toString().trim();
			String strCName = tempVec.elementAt(1).toString().trim();
			
			for(int j =0; j<strTemCat.length; j++){
				
				if(numCatId.equalsIgnoreCase(strTemCat[j])){
					flag = 1;	
					
				}
				
			}
			
			if(flag==1)
			{
				strResult += "<option value=\"" + numCatId + "\" Selected>" + strCName + "</option>";
				
			}
			
			else
			{
				strResult += "<option value=\"" + numCatId + "\">" + strCName + "</option>";
				
			}
			
		}
		strResult += "</select>";
		return strResult;
	}

}
