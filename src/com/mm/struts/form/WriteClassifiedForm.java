

package com.mm.struts.form;
/**
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
/** 
 * MyEclipse Struts
 * Creation date: 06-11-2007
 * 
 * XDoclet definition:
 * @struts.form name="writeClassifiedForm"
 */
public class WriteClassifiedForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;

	/** text property */
	private String blogTitle= "";
	private String blogtext= "";
	private String count = "";
	private String student = "";
	
	/** 
	 * Returns the student.
	 * @return String
	 */
	public String getStudent() {
		return this.student;
	}
	/** 
	 * Set the student.
	 * @param student The student to set
	 */
	public void setStudent(String student) {
		this.student = student;
	}
	

	/** 
	 * Returns the blogTitle.
	 * @return String
	 */
	public String getBlogTitle() {
		return this.blogTitle;
	}
	/** 
	 * Set the blogTitle.
	 * @param blogTitle The blogTitle to set
	 */
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}/** 
	 * Returns the category.
	 * @return String
	 */
	/** 
	 * Returns the blogtext.
	 * @return String
	 */
	public String getBlogtext() {
		return this.blogtext;
	}
	/** 
	 * Set the blogtext.
	 * @param blogtext The blogtext to set
	 */
	public void setBlogtext(String blogtext) {
		this.blogtext = blogtext;
	}
	
	/** 
	 * Returns the count.
	 * @return String
	 */
	public String getCount() {
		return this.count;
	}
	/** 
	 * Set the count.
	 * @param count The count to set
	 */
	public void setCount(String count) {
		this.count = count;
	}
	
//	validatin for the Write Complaint form. it check other category,brand and contact person.
	//and also check for the country ,state ,city others field 
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{
		ActionErrors errors = super.validate(mapping,req);
		//check for other category , brand and contact person
		
		/*if(this.category.equals("-1") && this.otherCategory.length()<=0)
		{
			errors.add("stdWriteComplaintForm",new ActionError("errors.resource.other","Category"));
			if(this.otherBrand.length()<=0)
			{
				errors.add("stdWriteComplaintForm",new ActionError("errors.resource.other","Brand"));
			}
		}
		else
		{
			if(this.brand.equals("-1") && this.otherBrand.length()<=0)
			{
				errors.add("stdWriteComplaintForm",new ActionError("errors.resource.other","Brand"));
			}
			
		}*/
		//end of category check
		Vector<String> unnamedDataVec = new Vector<String>();
		for(int i=1; i<=35; i++)
		{
			if(req.getParameterValues("field"+i)!= null)
			{
				String arrTemp[] = req.getParameterValues("field"+i);
				String strTemp="";
				for(int j=0; j<arrTemp.length; j++)
				{
					strTemp=(strTemp.equalsIgnoreCase("")) ? arrTemp[j] : strTemp+", "+arrTemp[j];
					////System.out.println("Field "+i+" values "+arrTemp[j]);
				}
				String strValue = req.getParameter("fieldmnd"+i);
				String strFldName = req.getParameter("fldname"+i);
				////System.out.println("strValue "+i+" "+strValue);
				if(strValue.trim().equalsIgnoreCase("1"))
				{
					////System.out.println("strTemp.trim() "+i+" " + strTemp.trim());
					////System.out.println("strTemp.trim().length() "+i+" "+strTemp.trim().length());
					if(i<11)
					{
						if(Integer.parseInt(strTemp.trim())<=0)
						{
							errors.add("stdWriteComplaintForm",new ActionError("errors.required",strFldName));
						}
					}
					else
					{
						if(strTemp.trim().length()<=0)
						{
							errors.add("stdWriteComplaintForm",new ActionError("errors.required",strFldName));
						}
					}
				}
				unnamedDataVec.add(strTemp);
			}
			else
			{
				unnamedDataVec.add("");
			}
		}
		System.out.println("Ajay Kumar Jha "+unnamedDataVec);
		HttpSession session = req.getSession();
		//session.setAttribute("c_type", this.c_type);
	//	System.out.println("cptypecptypecptypecptype in form ...."+c_type);
		session.setAttribute("complaintUnnamedData",unnamedDataVec);

		return errors;
	}
	
	//end of validatin methd

}