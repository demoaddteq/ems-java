package com.mm.struts.entp.action;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.RootMaster;
/** 
 * MyEclipse Struts
 * Creation date: 07-15-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */

public class CityAction extends Action {
	RootMaster rootMaster= new RootMaster();
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
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String codeStr = (request.getParameter("country")!=null) ? (request.getParameter("country").trim()) : "0~0";
		String codeStrArr[]= codeStr.split("~");
		int numCId=Integer.parseInt(codeStrArr[0]);
		int numSId = (request.getParameter("state")!=null) ? Integer.parseInt(request.getParameter("state").trim()) : 0;
		int numPId = (request.getParameter("place")!=null) ? Integer.parseInt(request.getParameter("place").trim()) : 0;
		String strPlace = "";
		if(request.getParameter("state")!=null)
		{
			
			Vector placeVec = rootMaster.getPlace(numCId, numSId, getDataSource(request, "entp"));
			strPlace = getPlaceList(numPId, placeVec);
		}
		else
		{
			strPlace ="<select name=\"city\" class=\"input_multi_txt_box\" tabindex=\"11\" onchange=\"setCity()\">";
			if(numPId == -1){
				strPlace += "<option value=\"0\" >Select</option>";
				strPlace += "<option value=\"-1\" Selected>Others</option>";
			}else{
				strPlace += "<option value=\"0\" Selected>Select</option>";
			strPlace += "<option value=\"-1\">Others</option>";
			}
			
			strPlace += "</select>";
			
		}
	    response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strPlace);
	    out.flush();
		return null;
	}
	
	private String getPlaceList(int numPId, Vector placeVec)
	{
		String strResult ="<select name=\"city\"  tabindex=\"11\" onchange=\"setCity()\">";
		
		for(int i=0; i<placeVec.size(); i++)
		{
			Vector tempVec = (Vector)placeVec.elementAt(i);
			int numPlaceId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			String strPName = tempVec.elementAt(1).toString().trim();
			////System.out.println("numPlaceId "+numPlaceId);
			////System.out.println("strPName "+strPName);
			if(numPId == numPlaceId)
			{
				strResult += "<option value=\"" + numPlaceId + "\" Selected>" + strPName + "</option>";
			}
			else
			{
				strResult += "<option value=\"" + numPlaceId + "\">" + strPName + "</option>";
			}
		}
		strResult += "</select>";
		return strResult;
	}
}
