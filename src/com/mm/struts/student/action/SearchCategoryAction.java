/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.student.action;

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
 * Creation date: 08-02-2007
 * 
 * XDoclet definition:
 * @struts.action
 */
public class SearchCategoryAction extends Action {
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
			HttpServletRequest request, HttpServletResponse response) throws Exception 
		{
				RootMaster rootMaster= new RootMaster();
				int numCid = (request.getParameter("cid")!=null) ? Integer.parseInt(request.getParameter("cid")) : 0;
				
				Vector categoryVec = rootMaster.getCategories(getDataSource(request, "student"));
			    String strCat = getCategoryList(numCid, categoryVec);
			    //////System.out.println("search Category list "+strCat);
			    response.setContentType("text/html;charset=ISO-8859-1");
			    PrintWriter out = response.getWriter();
			    out.println(strCat);
			    out.flush();
				return null;
			}
			
			private String getCategoryList(int numCid, Vector categoryVec)
			{
				String strResult ="<select name=\"category\"   tabindex=\"5\" onchange=\"retrieveBrandURL1('brandForCat.do?category='+this.value), setCategory()\">";
				
				for(int i=0; i<categoryVec.size()-1; i++)
				{
					Vector tempVec = (Vector)categoryVec.elementAt(i);
					int numCatId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
					String strCName = tempVec.elementAt(1).toString().trim();
					
						strResult += "<option value=\"" + numCatId + "\">" + strCName + "</option>";
						
					
				}
				strResult += "</select>";
				return strResult;
			}
		}