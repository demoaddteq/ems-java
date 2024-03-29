
/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.advt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.struts.advt.form.WriteBlogForm;
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
public class WriteBlogAction extends Action {
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
	DataSource dataSource = getDataSource(request, "advt");
	String qtype=writeBlogForm.getQtype().toString().trim();
	String result = "failure";
	if(qtype.equalsIgnoreCase("blog"))
	{
		result="blogfailure";
	}
	String qcat = writeBlogForm.getQcat().toString().trim();
	
	String category = writeBlogForm.getCategory().toString().trim();
	String otherCat = writeBlogForm.getOtherCategory().toString().trim();
	String brand = writeBlogForm.getBrand().toString().trim();
	String otherBrand = writeBlogForm.getOtherBrand().toString().trim();
	String country = writeBlogForm.getCountry().toString().trim();
	String countryArr[] = country.split("~");
	String otherCountry = writeBlogForm.getOtherCountry().toString().trim();
	String state = writeBlogForm.getState().toString().trim();
	String otherState = writeBlogForm.getOtherState().toString().trim();
	String city = writeBlogForm.getCity().toString().trim();
	String otherCity = writeBlogForm.getOtherCity().toString().trim();
	
	int numCatID = Integer.parseInt(category.trim());
	System.out.println("numCatIDnumCat category,``````````......."+category);
	int numBrandId = (brand.length()>0)?Integer.parseInt(brand):-1;
	int numCountryId = 0;
	int numStateId = (state.length()>0)?Integer.parseInt(state):-1;
	int numCityId = (city.length()>0)?Integer.parseInt(city):-1;
	
	if(numCatID!=-1 && category.length()>0 && numCatID!=0 )
	{
		numCatID = Integer.parseInt(category);			
		Vector tempVec = rootMaster.getCategoryName(dataSource, numCatID);
		categoryName=tempVec.elementAt(0).toString().trim();
		
		if(qcat.equalsIgnoreCase("Advertiser")||qcat.equalsIgnoreCase("student")){
				
			categoryName  = rootMaster.getStrCollegeCategoryName(getDataSource(request, "advt"), numCatID);
		}
		
	}
	else
	{
		categoryName = otherCat;
	}
	if(numBrandId!=-1 && brand.length()>0)
	{
		numBrandId = Integer.parseInt(brand);
		brandName = rootMaster.getBrandName(dataSource, numBrandId);
	}
	else
	{
		brandName = otherBrand;
	}
	
	if(numCountryId!=-1 && country.length()>0)
	{
		numCountryId=Integer.parseInt(countryArr[0]);
		countryName = rootMaster.getCountryName(dataSource, numCountryId);
	}
	else
	{
		countryName = otherCountry;
	}
	if(numStateId!=-1 && state.length()>0)
	{
		numStateId = Integer.parseInt(state);
		stateName = rootMaster.getStateName(dataSource, numStateId);
	}
	else
	{
		stateName = otherState;
	}
	if(numCityId!=-1 && city.length()>0)
	{
		numCityId = Integer.parseInt(city);
		cityName = rootMaster.getPlaceName(dataSource, numCityId);
	}
	else
	{
		cityName = otherCity;
	}
	
	Vector<String> extraVec =  new Vector<String>();
	extraVec.add(categoryName);
	extraVec.add(brandName);
	extraVec.add(countryName);
	extraVec.add(stateName);
	extraVec.add(cityName);
	extraVec.add(qcat);
	
	
	dataVec.add(writeBlogForm.getBlogTitle().toString().trim());
	dataVec.add(category);
	dataVec.add(writeBlogForm.getOtherCategory().toString().trim());
	dataVec.add(brand);
	dataVec.add(writeBlogForm.getOtherBrand().toString().trim());
	dataVec.add(writeBlogForm.getCheckDealer().toString().trim());
	dataVec.add(writeBlogForm.getDealerName().toString().trim());
	dataVec.add(writeBlogForm.getAddress().toString().trim());
	dataVec.add(country);
	dataVec.add(writeBlogForm.getOtherCountry().toString().trim());
	dataVec.add(state);
	dataVec.add(writeBlogForm.getOtherState().toString().trim());
	dataVec.add(city);
	dataVec.add(writeBlogForm.getOtherCity().toString().trim());
	dataVec.add(writeBlogForm.getPincode().toString().trim());
	dataVec.add(writeBlogForm.getBlogtext().toString().trim());
	dataVec.add(writeBlogForm.getReleventtext().toString().trim());
	dataVec.add(writeBlogForm.getQtype().toString().trim());
	dataVec.add(writeBlogForm.getCons().toString().trim());
	dataVec.add(writeBlogForm.getCollegeList().toString().trim());
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
						strSubCatVal = im.getValueSubCat1(getDataSource(request, "advt"), Integer.parseInt(strTemp.trim()));
					}
					if(i==2)
					{
						strSubCatVal = im.getValueSubCat2(getDataSource(request, "advt"), Integer.parseInt(strTemp.trim()));
					}
					if(i==3)
					{
						strSubCatVal = im.getValueSubCat3(getDataSource(request, "advt"), Integer.parseInt(strTemp.trim()));
					}
					if(i==4)
					{
						strSubCatVal = im.getValueSubCat4(getDataSource(request, "advt"), Integer.parseInt(strTemp.trim()));
					}
					if(i==5)
					{
						strSubCatVal = im.getValueSubCat5(getDataSource(request, "advt"), Integer.parseInt(strTemp.trim()));
					}
					if(i==6)
					{
						strSubCatVal = im.getValueSubCat6(getDataSource(request, "advt"), Integer.parseInt(strTemp.trim()));
					}
					if(i==7)
					{
						strSubCatVal = im.getValueSubCat7(getDataSource(request, "advt"), Integer.parseInt(strTemp.trim()));
					}
					if(i==8)
					{
						strSubCatVal = im.getValueSubCat8(getDataSource(request, "advt"), Integer.parseInt(strTemp.trim()));
					}
					if(i==9)
					{
						strSubCatVal = im.getValueSubCat9(getDataSource(request, "advt"), Integer.parseInt(strTemp.trim()));
					}
					if(i==10)
					{
						strSubCatVal = im.getValueSubCat10(getDataSource(request, "advt"), Integer.parseInt(strTemp.trim()));
					}
					//////System.out.println("Str Sub Cat Val "+strSubCatVal);
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
	if(writeBlogForm.getField1().equalsIgnoreCase("-1"))
	{
		dataUnnamedVec.add(writeBlogForm.getOtherfield1().toString().trim());
	}
	else
	{
		dataUnnamedVec.add(writeBlogForm.getField1().toString().trim());
	}
//	field2
	if(writeBlogForm.getField2().equalsIgnoreCase("-1"))
	{
		dataUnnamedVec.add(writeBlogForm.getOtherfield2().toString().trim());
	}
	else
	{
		dataUnnamedVec.add(writeBlogForm.getField2().toString().trim());
	}
//	field3
	if(writeBlogForm.getField3().equalsIgnoreCase("-1"))
	{
		dataUnnamedVec.add(writeBlogForm.getOtherfield3().toString().trim());
	}
	else
	{
		dataUnnamedVec.add(writeBlogForm.getField3().toString().trim());
	}
//	field4
	if(writeBlogForm.getField4().equalsIgnoreCase("-1"))
	{
		dataUnnamedVec.add(writeBlogForm.getOtherfield4().toString().trim());
	}
	else
	{
		dataUnnamedVec.add(writeBlogForm.getField4().toString().trim());
	}
//	field5
	if(writeBlogForm.getField5().equalsIgnoreCase("-1"))
	{
		dataUnnamedVec.add(writeBlogForm.getOtherfield5().toString().trim());
	}
	else
	{
		dataUnnamedVec.add(writeBlogForm.getField5().toString().trim());
	}
//	field6
	if(writeBlogForm.getField6().equalsIgnoreCase("-1"))
	{
		dataUnnamedVec.add(writeBlogForm.getOtherfield6().toString().trim());
	}
	else
	{
		dataUnnamedVec.add(writeBlogForm.getField6().toString().trim());
	}
//	field7
	if(writeBlogForm.getField7().equalsIgnoreCase("-1"))
	{
		dataUnnamedVec.add(writeBlogForm.getOtherfield7().toString().trim());
	}
	else
	{
		dataUnnamedVec.add(writeBlogForm.getField7().toString().trim());
	}
//	field8
	if(writeBlogForm.getField8().equalsIgnoreCase("-1"))
	{
		dataUnnamedVec.add(writeBlogForm.getOtherfield8().toString().trim());
	}
	else
	{
		dataUnnamedVec.add(writeBlogForm.getField8().toString().trim());
	}
//	field9
	if(writeBlogForm.getField9().equalsIgnoreCase("-1"))
	{
		dataUnnamedVec.add(writeBlogForm.getOtherfield9().toString().trim());
	}
	else
	{
		dataUnnamedVec.add(writeBlogForm.getField9().toString().trim());
	}
//	field10
	if(writeBlogForm.getField10().equalsIgnoreCase("-1"))
	{
		dataUnnamedVec.add(writeBlogForm.getOtherfield10().toString().trim());
	}
	else
	{
		dataUnnamedVec.add(writeBlogForm.getField10().toString().trim());
	}
//	field 11		
	dataUnnamedVec.add(writeBlogForm.getField11().toString().trim());
//	field 12		
	dataUnnamedVec.add(writeBlogForm.getField12().toString().trim());
//	field 13		
	dataUnnamedVec.add(writeBlogForm.getField13().toString().trim());
//	field 14		
	dataUnnamedVec.add(writeBlogForm.getField14().toString().trim());
//	field 15		
	dataUnnamedVec.add(writeBlogForm.getField15().toString().trim());
//	field 16		
	dataUnnamedVec.add(writeBlogForm.getField16().toString().trim());
//	field 17		
	dataUnnamedVec.add(writeBlogForm.getField17().toString().trim());
//	field 18		
	dataUnnamedVec.add(writeBlogForm.getField18().toString().trim());
//	field 19		
	dataUnnamedVec.add(writeBlogForm.getField19().toString().trim());
//	field 20		
	dataUnnamedVec.add(writeBlogForm.getField20().toString().trim());
//	field 21		
	dataUnnamedVec.add(writeBlogForm.getField21().toString().trim());
//	field 22		
	dataUnnamedVec.add(writeBlogForm.getField22().toString().trim());
//	field 23		
	dataUnnamedVec.add(writeBlogForm.getField23().toString().trim());
//	field 24		
	dataUnnamedVec.add(writeBlogForm.getField24().toString().trim());
//	field 25		
	dataUnnamedVec.add(writeBlogForm.getField25().toString().trim());
//	field 26		
	dataUnnamedVec.add(writeBlogForm.getField26().toString().trim());
//	field 27		
	dataUnnamedVec.add(writeBlogForm.getField27().toString().trim());
//	field 28		
	dataUnnamedVec.add(writeBlogForm.getField28().toString().trim());
//	field 29		
	dataUnnamedVec.add(writeBlogForm.getField29().toString().trim());
//	field 30		
	dataUnnamedVec.add(writeBlogForm.getField30().toString().trim());
//	field 31		
	dataUnnamedVec.add(writeBlogForm.getField31().toString().trim());
//	field 32		
	dataUnnamedVec.add(writeBlogForm.getField32().toString().trim());
//	field 33		
	dataUnnamedVec.add(writeBlogForm.getField33().toString().trim());
//	field 34		
	dataUnnamedVec.add(writeBlogForm.getField34().toString().trim());
//	field 35		
	dataUnnamedVec.add(writeBlogForm.getField35().toString().trim());
	//////System.out.println("dataUnnamedVec "+dataUnnamedVec);
	
	Vector<String> unnamedNameVec= new Vector<String>();
	unnamedNameVec.add(writeBlogForm.getFldname1().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname2().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname3().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname4().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname5().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname6().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname7().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname8().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname9().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname10().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname11().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname12().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname13().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname14().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname15().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname16().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname17().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname18().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname19().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname20().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname21().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname22().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname23().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname24().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname25().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname26().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname27().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname28().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname29().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname30().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname31().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname32().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname33().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname34().toString().trim());
	unnamedNameVec.add(writeBlogForm.getFldname35().toString().trim());
			
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