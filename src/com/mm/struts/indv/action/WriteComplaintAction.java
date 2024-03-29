/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.indv.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.mm.struts.indv.form.WriteComplaintForm;
import com.mm.core.master.IndvMaster;
import com.mm.core.master.RootMaster;
import javax.sql.DataSource;
import java.util.Vector;




/** 
 * MyEclipse Struts
 * Creation date: 06-11-2007
 * 
 * XDoclet definition:
 * @struts.action path="/writeComplaint" name="writeComplaintForm" input="/form/writeComplaint.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="writeComplaint.jsp"
 */
public class WriteComplaintAction extends Action {
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
		WriteComplaintForm writeComplaintForm = (WriteComplaintForm) form;// TODO Auto-generated method stub
		RootMaster rootMaster = new RootMaster();
		IndvMaster im = new IndvMaster();
		String result = "failure";
		Vector <String> dataVec = new Vector<String>();
		String categoryName="",brandName="",countryName="", stateName="",cityName="";
		//set all form data in a vector and transfer this vector to the complaint preview page.
		//and set status success for that action.
		DataSource dataSource = getDataSource(request, "indv");
		String category = writeComplaintForm.getCategory().toString().trim();
		String otherCat = writeComplaintForm.getOtherCategory().toString().trim();
		String brand = writeComplaintForm.getBrand().toString().trim();
		String otherBrand = writeComplaintForm.getOtherBrand().toString().trim();
		String country = writeComplaintForm.getCountry().toString().trim();
		String countryArr[] = country.split("~");
		String otherCountry = writeComplaintForm.getOtherCountry().toString().trim();
		String state = writeComplaintForm.getState().toString().trim();
		String otherState = writeComplaintForm.getOtherState().toString().trim();
		String city = writeComplaintForm.getCity().toString().trim();
		String otherCity = writeComplaintForm.getOtherCity().toString().trim();
		
		int numCatID = Integer.parseInt(category);
		int numBrandId = (brand.length()>0)?Integer.parseInt(brand):-1;
		int numCountryId = 0;
		int numStateId = (state.length()>0)?Integer.parseInt(state):-1;
		int numCityId = (city.length()>0)?Integer.parseInt(city):-1;
		
		if(numCatID!=-1 && category.length()>0)
		{
			numCatID = Integer.parseInt(category);
			Vector tempVec = rootMaster.getCategoryName(dataSource, numCatID);
			categoryName=tempVec.elementAt(0).toString().trim();
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
		
		
		dataVec.add(writeComplaintForm.getBlogTitle().toString().trim());
		dataVec.add(category);
		dataVec.add(writeComplaintForm.getOtherCategory().toString().trim());
		dataVec.add(brand);
		dataVec.add(writeComplaintForm.getOtherBrand().toString().trim());
		dataVec.add(writeComplaintForm.getCheckDealer().toString().trim());
		dataVec.add(writeComplaintForm.getDealerName().toString().trim());
		dataVec.add(writeComplaintForm.getAddress().toString().trim());
		dataVec.add(country);
		dataVec.add(writeComplaintForm.getOtherCountry().toString().trim());
		dataVec.add(state);
		dataVec.add(writeComplaintForm.getOtherState().toString().trim());
		dataVec.add(city);
		dataVec.add(writeComplaintForm.getOtherCity().toString().trim());
		dataVec.add(writeComplaintForm.getPincode().toString().trim());
		dataVec.add(writeComplaintForm.getBlogtext().toString().trim());
		dataVec.add(writeComplaintForm.getReleventtext().toString().trim());
		
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
					//System.out.println("Field "+i+" values "+arrTemp[j]);
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
							strSubCatVal = im.getValueSubCat1(getDataSource(request, "indv"), Integer.parseInt(strTemp.trim()));
						}
						if(i==2)
						{
							strSubCatVal = im.getValueSubCat2(getDataSource(request, "indv"), Integer.parseInt(strTemp.trim()));
						}
						if(i==3)
						{
							strSubCatVal = im.getValueSubCat3(getDataSource(request, "indv"), Integer.parseInt(strTemp.trim()));
						}
						if(i==4)
						{
							strSubCatVal = im.getValueSubCat4(getDataSource(request, "indv"), Integer.parseInt(strTemp.trim()));
						}
						if(i==5)
						{
							strSubCatVal = im.getValueSubCat5(getDataSource(request, "indv"), Integer.parseInt(strTemp.trim()));
						}
						if(i==6)
						{
							strSubCatVal = im.getValueSubCat6(getDataSource(request, "indv"), Integer.parseInt(strTemp.trim()));
						}
						if(i==7)
						{
							strSubCatVal = im.getValueSubCat7(getDataSource(request, "indv"), Integer.parseInt(strTemp.trim()));
						}
						if(i==8)
						{
							strSubCatVal = im.getValueSubCat8(getDataSource(request, "indv"), Integer.parseInt(strTemp.trim()));
						}
						if(i==9)
						{
							strSubCatVal = im.getValueSubCat9(getDataSource(request, "indv"), Integer.parseInt(strTemp.trim()));
						}
						if(i==10)
						{
							strSubCatVal = im.getValueSubCat10(getDataSource(request, "indv"), Integer.parseInt(strTemp.trim()));
						}
						//System.out.println("Str Sub Cat Val "+strSubCatVal);
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
		//System.out.println("unnamedDataVec.size "+unnamedDataVec.size());
		//System.out.println("unnamedDataVec "+unnamedDataVec);
		
		Vector<String> dataUnnamedVec = new Vector<String>();
		//field1
		if(writeComplaintForm.getField1().equalsIgnoreCase("-1"))
		{
			dataUnnamedVec.add(writeComplaintForm.getOtherfield1().toString().trim());
		}
		else
		{
			dataUnnamedVec.add(writeComplaintForm.getField1().toString().trim());
		}
//		field2
		if(writeComplaintForm.getField2().equalsIgnoreCase("-1"))
		{
			dataUnnamedVec.add(writeComplaintForm.getOtherfield2().toString().trim());
		}
		else
		{
			dataUnnamedVec.add(writeComplaintForm.getField2().toString().trim());
		}
//		field3
		if(writeComplaintForm.getField3().equalsIgnoreCase("-1"))
		{
			dataUnnamedVec.add(writeComplaintForm.getOtherfield3().toString().trim());
		}
		else
		{
			dataUnnamedVec.add(writeComplaintForm.getField3().toString().trim());
		}
//		field4
		if(writeComplaintForm.getField4().equalsIgnoreCase("-1"))
		{
			dataUnnamedVec.add(writeComplaintForm.getOtherfield4().toString().trim());
		}
		else
		{
			dataUnnamedVec.add(writeComplaintForm.getField4().toString().trim());
		}
//		field5
		if(writeComplaintForm.getField5().equalsIgnoreCase("-1"))
		{
			dataUnnamedVec.add(writeComplaintForm.getOtherfield5().toString().trim());
		}
		else
		{
			dataUnnamedVec.add(writeComplaintForm.getField5().toString().trim());
		}
//		field6
		if(writeComplaintForm.getField6().equalsIgnoreCase("-1"))
		{
			dataUnnamedVec.add(writeComplaintForm.getOtherfield6().toString().trim());
		}
		else
		{
			dataUnnamedVec.add(writeComplaintForm.getField6().toString().trim());
		}
//		field7
		if(writeComplaintForm.getField7().equalsIgnoreCase("-1"))
		{
			dataUnnamedVec.add(writeComplaintForm.getOtherfield7().toString().trim());
		}
		else
		{
			dataUnnamedVec.add(writeComplaintForm.getField7().toString().trim());
		}
//		field8
		if(writeComplaintForm.getField8().equalsIgnoreCase("-1"))
		{
			dataUnnamedVec.add(writeComplaintForm.getOtherfield8().toString().trim());
		}
		else
		{
			dataUnnamedVec.add(writeComplaintForm.getField8().toString().trim());
		}
//		field9
		if(writeComplaintForm.getField9().equalsIgnoreCase("-1"))
		{
			dataUnnamedVec.add(writeComplaintForm.getOtherfield9().toString().trim());
		}
		else
		{
			dataUnnamedVec.add(writeComplaintForm.getField9().toString().trim());
		}
//		field10
		if(writeComplaintForm.getField10().equalsIgnoreCase("-1"))
		{
			dataUnnamedVec.add(writeComplaintForm.getOtherfield10().toString().trim());
		}
		else
		{
			dataUnnamedVec.add(writeComplaintForm.getField10().toString().trim());
		}
//		field 11		
		dataUnnamedVec.add(writeComplaintForm.getField11().toString().trim());
//		field 12		
		dataUnnamedVec.add(writeComplaintForm.getField12().toString().trim());
//		field 13		
		dataUnnamedVec.add(writeComplaintForm.getField13().toString().trim());
//		field 14		
		dataUnnamedVec.add(writeComplaintForm.getField14().toString().trim());
//		field 15		
		dataUnnamedVec.add(writeComplaintForm.getField15().toString().trim());
//		field 16		
		dataUnnamedVec.add(writeComplaintForm.getField16().toString().trim());
//		field 17		
		dataUnnamedVec.add(writeComplaintForm.getField17().toString().trim());
//		field 18		
		dataUnnamedVec.add(writeComplaintForm.getField18().toString().trim());
//		field 19		
		dataUnnamedVec.add(writeComplaintForm.getField19().toString().trim());
//		field 20		
		dataUnnamedVec.add(writeComplaintForm.getField20().toString().trim());
//		field 21		
		dataUnnamedVec.add(writeComplaintForm.getField21().toString().trim());
//		field 22		
		dataUnnamedVec.add(writeComplaintForm.getField22().toString().trim());
//		field 23		
		dataUnnamedVec.add(writeComplaintForm.getField23().toString().trim());
//		field 24		
		dataUnnamedVec.add(writeComplaintForm.getField24().toString().trim());
//		field 25		
		dataUnnamedVec.add(writeComplaintForm.getField25().toString().trim());
//		field 26		
		dataUnnamedVec.add(writeComplaintForm.getField26().toString().trim());
//		field 27		
		dataUnnamedVec.add(writeComplaintForm.getField27().toString().trim());
//		field 28		
		dataUnnamedVec.add(writeComplaintForm.getField28().toString().trim());
//		field 29		
		dataUnnamedVec.add(writeComplaintForm.getField29().toString().trim());
//		field 30		
		dataUnnamedVec.add(writeComplaintForm.getField30().toString().trim());
//		field 31		
		dataUnnamedVec.add(writeComplaintForm.getField31().toString().trim());
//		field 32		
		dataUnnamedVec.add(writeComplaintForm.getField32().toString().trim());
//		field 33		
		dataUnnamedVec.add(writeComplaintForm.getField33().toString().trim());
//		field 34		
		dataUnnamedVec.add(writeComplaintForm.getField34().toString().trim());
//		field 35		
		dataUnnamedVec.add(writeComplaintForm.getField35().toString().trim());
		//System.out.println("dataUnnamedVec "+dataUnnamedVec);
		
		Vector<String> unnamedNameVec= new Vector<String>();
		unnamedNameVec.add(writeComplaintForm.getFldname1().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname2().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname3().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname4().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname5().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname6().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname7().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname8().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname9().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname10().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname11().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname12().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname13().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname14().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname15().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname16().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname17().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname18().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname19().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname20().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname21().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname22().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname23().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname24().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname25().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname26().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname27().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname28().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname29().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname30().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname31().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname32().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname33().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname34().toString().trim());
		unnamedNameVec.add(writeComplaintForm.getFldname35().toString().trim());
				
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