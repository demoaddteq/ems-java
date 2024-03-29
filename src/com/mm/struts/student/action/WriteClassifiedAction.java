
/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.student.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.struts.student.form.WriteBlogForm;
import com.mm.core.master.*;
import javax.sql.DataSource;
import java.util.Vector;




/** 
 * MyEclipse Struts
 * Creation date: 06-11-2007
 * 
 * XDoclet definition:
 * @struts.action path="/writeComplaint" name="writeRequestForm" input="/form/writeComplaint.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="writeComplaint.jsp"
 */
public class WriteClassifiedAction extends Action {
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
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		
		WriteBlogForm writeBlogForm = (WriteBlogForm) form;// TODO Auto-generated method stub
	RootMaster rootMaster = new RootMaster();
	
	IndvMaster im = new IndvMaster();
	
	
	Vector <String> dataVec = new Vector<String>();
	String categoryName="",brandName="",countryName="", stateName="",cityName="";
	//set all form data in a vector and transfer this vector to the complaint preview page.
	//and set status success for that action.
	DataSource dataSource = getDataSource(request, "student");
	String qtype=writeBlogForm.getQtype().toString().trim();
	String result = "failure";
	if(qtype.equalsIgnoreCase("blog"))
	{
		result="blogfailure";
	}

	Vector<String> extraVec =  new Vector<String>();
	extraVec.add(categoryName);
	extraVec.add(brandName);
	extraVec.add(countryName);
	extraVec.add(stateName);
	extraVec.add(cityName);
		
	
	dataVec.add(writeBlogForm.getBlogTitle().toString().trim());
	dataVec.add(writeBlogForm.getBlogtext().toString().trim());
	dataVec.add(writeBlogForm.getQtype().toString().trim());
	dataVec.add(writeBlogForm.getCons().toString().trim());
	dataVec.add(writeBlogForm.getStudent().toString().trim());
	
	////System.out.println("datavac in write action"+dataVec.size());
	////System.out.println("datavac in write action"+writeBlogForm.getQtype().toString().trim());
	
	Vector<String> unnamedDataVec = new Vector<String>();
	for(int i=1; i<=35; i++)
	{
		String strSubCatVal = "";
		if(request.getParameterValues("field"+i)!= null)
		{
			String arrTemp[] = request.getParameterValues("field"+i);
			String strTemp="";
			for(int j=0; j<arrTemp.length; j++)
			{
				strTemp=(strTemp.equalsIgnoreCase("")) ? arrTemp[j] : strTemp+", "+arrTemp[j];
				//////System.out.println("Field "+i+" values "+arrTemp[j]);
			}
			unnamedDataVec.add(strTemp);
			
			
			if(strTemp.length()>0)
			{
				char [] chars = strTemp.toCharArray();
				String strFlag="";
				for(int j=0; j<chars.length; j++)
				{
					strFlag +=(Character.isDigit(chars[j])) ? "false" : "true";
				}
				if(strFlag.indexOf("true") <= -1)
				{
					if(i==1)
					{
						strSubCatVal = im.getValueSubCat1(getDataSource(request, "student"), Integer.parseInt(strTemp.trim()));
					}
					if(i==2)
					{
						strSubCatVal = im.getValueSubCat2(getDataSource(request, "student"), Integer.parseInt(strTemp.trim()));
					}
					if(i==3)
					{
						strSubCatVal = im.getValueSubCat3(getDataSource(request, "student"), Integer.parseInt(strTemp.trim()));
					}
					if(i==4)
					{
						strSubCatVal = im.getValueSubCat4(getDataSource(request, "student"), Integer.parseInt(strTemp.trim()));
					}
					if(i==5)
					{
						strSubCatVal = im.getValueSubCat5(getDataSource(request, "student"), Integer.parseInt(strTemp.trim()));
					}
					if(i==6)
					{
						strSubCatVal = im.getValueSubCat6(getDataSource(request, "student"), Integer.parseInt(strTemp.trim()));
					}
					if(i==7)
					{
						strSubCatVal = im.getValueSubCat7(getDataSource(request, "student"), Integer.parseInt(strTemp.trim()));
					}
					if(i==8)
					{
						strSubCatVal = im.getValueSubCat8(getDataSource(request, "student"), Integer.parseInt(strTemp.trim()));
					}
					if(i==9)
					{
						strSubCatVal = im.getValueSubCat9(getDataSource(request, "student"), Integer.parseInt(strTemp.trim()));
					}
					if(i==10)
					{
						strSubCatVal = im.getValueSubCat10(getDataSource(request, "student"), Integer.parseInt(strTemp.trim()));
					}
					System.out.println("Str Sub Cat Val "+strSubCatVal);
					if(strSubCatVal.equalsIgnoreCase("failure"))
					{
						strSubCatVal="";
					}
					
				}
				else
				{
					strSubCatVal = "";
				}
			}
			else
			{
				strSubCatVal = "";
			}
			
		}
		else
		{
			unnamedDataVec.add("");
		}
		if(i<11)
		{
			extraVec.add(strSubCatVal);
		}
	}
	//////System.out.println("unnamedDataVec.size "+unnamedDataVec.size());
	//////System.out.println("unnamedDataVec "+unnamedDataVec);
	
	Vector<String> dataUnnamedVec = new Vector<String>();
	//field1
	
	Vector<String> unnamedNameVec= new Vector<String>();
	request.setAttribute("UnnamedNameData",unnamedNameVec);
	//request.setAttribute("complaintUnnamedData",dataUnnamedVec);
	request.setAttribute("complaintUnnamedData",unnamedDataVec);
	request.setAttribute("complaintData",dataVec);
	request.setAttribute("extraData",extraVec);
	if(request.getAttribute("complaintData")!=null)
	{
		result = "success";
	}
	return mapping.findForward(result);
	
}
}