package com.mm.struts.entp.form;
/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */


import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 06-20-2007
 * 
 * XDoclet definition:
 * @struts.form name="ModifyComplaintForm"
 */
public class ModifyComplaintForm extends ValidatorForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** text property */
	
	private String brand= "";
	private String otherCategory= "";
	private String otherBrand= "";
	private String blogTitle = "";
	private String brandName = "";
	private String brUsName[] = null;
	private String designation[] = null;
	private String bemailId[] = null;
	private String dealername = "";
	private String dealeraddress = "";
	private String city = "";
	private String state = "";
	private String country = "";
	private String contactPerson = "";
	private String mobileNo = "";
	private String category = "";
	private String email = "";
	private String blogtext = "";
	private String releventtext = "";
	private String tag = "";
	private String published = "";
	private String branduserChek[]  = null;
	private String dealerchk = "";
	private String otherCountry = "";
	private String otherState = "";
	private String otherCity = "";
	private String dealerId="";
	private String pincode ="";
	private String advtl_id = "";
	
	/** 
	 * Returns the brand.
	 * @return String
	 */
	public String getBrand() {
		return this.brand;
	}
	/** 
	 * Set the brand.
	 * @param brand The brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	/** 
	 * Returns the otherCategory.
	 * @return String
	 */
	public String getOtherCategory() {
		return this.otherCategory;
	}
	/** 
	 * Set the otherCategory.
	 * @param otherCategory The otherCategory to set
	 */
	public void setOtherCategory(String otherCategory) {
		this.otherCategory = otherCategory;
	}
	/** 
	 * Returns the otherBrand.
	 * @return String
	 */
	public String getOtherBrand() {
		return this.otherBrand;
	}
	/** 
	 * Set the otherBrand.
	 * @param otherBrand The otherBrand to set
	 */
	public void setOtherBrand(String otherBrand) {
		this.otherBrand = otherBrand;
	}
	
	/** 
	 * Returns the advtl_id.
	 * @return this.String
	 */
	public String getAdvtl_id() {
		return this.advtl_id;
	}
	/** 
	 * Set the branduserChek.
	 * @param branduserChek The branduserChek to set
	 */
	public void setAdvtl_id(String advtl_id) {
		this.advtl_id = advtl_id;
	}
	
	/** 
	 * Returns the dealerId.
	 * @return this.String
	 */
	public String getPincode() {
		return this.pincode;
	}
	/** 
	 * Set the branduserChek.
	 * @param branduserChek The branduserChek to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	/** 
	 * Returns the dealerId.
	 * @return this.String
	 */
	public String getDealerId() {
		return this.dealerId;
	}
	/** 
	 * Set the branduserChek.
	 * @param branduserChek The branduserChek to set
	 */
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	/** 
	 * Returns the otherCountry.
	 * @return this.String
	 */
	public String getOtherCountry() {
		return this.otherCountry;
	}
	/** 
	 * Set the branduserChek.
	 * @param branduserChek The branduserChek to set
	 */
	public void setOtherCountry(String otherCountry) {
		this.otherCountry = otherCountry;
	}
	/** 
	 * Set the branduserChek.
	 * @param branduserChek The branduserChek to set
	 */
	public void setOtherState(String otherState) {
		this.otherState = otherState;
	}
	/** 
	 * Returns the otherCountry.
	 * @return this.String
	 */
	public String getOtherState() {
		return this.otherState;
	}

	
	/** 
	 * Returns the otherCountry.
	 * @return this.String
	 */
	public String getOtherCity() {
		return this.otherCity;
	}

	/** 
	 * Set the branduserChek.
	 * @param branduserChek The branduserChek to set
	 */
	public void setOtherCity(String otherCity) {
		this.otherCity = otherCity;
	}
	/** 
	 * Returns the branduserChek.
	 * @return this.String
	 */
	public String getDealerchk() {
		return this.dealerchk;
	}

	/** 
	 * Set the branduserChek.
	 * @param branduserChek The branduserChek to set
	 */
	public void setDealerchk(String dealerchk) {
		this.dealerchk = dealerchk;
	}
	
	/** 
	 * Returns the branduserChek.
	 * @return this.String
	 */
	public String[] getBranduserChek() {
		return this.branduserChek;
	}

	/** 
	 * Set the branduserChek.
	 * @param branduserChek The branduserChek to set
	 */
	public void setBranduserChek(String branduserChek[]) {
		this.branduserChek = branduserChek;
	}
	
	/** 
	 * Returns the blogTitle.
	 * @return this.String
	 */
	public String getTag() {
		return this.tag;
	}

	/** 
	 * Set the blogTitle.
	 * @param blogTitle The blogTitle to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
	/** 
	 * Returns the blogTitle.
	 * @return this.String
	 */
	public String getPublished() {
		return this.published;
	}

	/** 
	 * Set the blogTitle.
	 * @param blogTitle The blogTitle to set
	 */
	public void setPublished(String published) {
		this.published = published;
	}
	
	/** 
	 * Returns the blogTitle.
	 * @return this.String
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
	}
	/** 
	 * Returns the brandName.
	 * @return this.String
	 */
	public String getBrandName() {
		return this.brandName;
	}

	/** 
	 * Set the brandName.
	 * @param brandName The brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/** 
	 * Returns the brUsName.
	 * @return this.String
	 */
	public String[] getBrUsName() {
		return this.brUsName;
	}

	/** 
	 * Set the brUsName.
	 * @param brUsName The brUsName to set
	 */
	public void setBrUsName(String brUsName[]) {
		this.brUsName = brUsName;
	}
	/** 
	 * Returns the designation.
	 * @return this.String
	 */
	public String[] getDesignation() {
		return this.designation;
	}

	/** 
	 * Set the designation.
	 * @param designation The designation to set
	 */
	public void setDesignation(String designation[]) {
		this.designation = designation;
	}
	/** 
	 * Returns the bemailId.
	 * @return this.String
	 */
	public String[] getBemailId() {
		return this.bemailId;
	}

	/** 
	 * Set the bemailId.
	 * @param bemailId The bemailId to set
	 */
	public void setBemailId(String bemailId[]) {
		this.bemailId = bemailId;
	}
	/** 
	 * Returns the dealername.
	 * @return this.String
	 */
	public String getDealername() {
		return this.dealername;
	}

	/** 
	 * Set the dealername.
	 * @param dealername The dealername to set
	 */
	public void setDealername(String dealername) {
		this.dealername = dealername;
	}
	/** 
	 * Returns the dealeraddress.
	 * @return this.String
	 */
	public String getDealeraddress() {
		return this.dealeraddress;
	}

	/** 
	 * Set the dealeraddress.
	 * @param dealeraddress The dealeraddress to set
	 */
	public void setDealeraddress(String dealeraddress) {
		this.dealeraddress = dealeraddress;
	}
	/** 
	 * Returns the city.
	 * @return this.String
	 */
	public String getCity() {
		return this.city;
	}

	/** 
	 * Set the city.
	 * @param city The city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/** 
	 * Returns the state.
	 * @return this.String
	 */
	public String getState() {
		return this.state;
	}

	/** 
	 * Set the state.
	 * @param state The state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/** 
	 * Returns the country.
	 * @return this.String
	 */
	public String getCountry() {
		return this.country;
	}

	/** 
	 * Set the country.
	 * @param country The country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/** 
	 * Returns the contactPerson.
	 * @return this.String
	 */
	public String getContactPerson() {
		return this.contactPerson;
	}

	/** 
	 * Set the contactPerson.
	 * @param contactPerson The contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	/** 
	 * Returns the mobileNo.
	 * @return this.String
	 */
	public String getMobileNo() {
		return this.mobileNo;
	}

	/** 
	 * Set the mobileNo.
	 * @param mobileNo The mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	/** 
	 * Returns the category.
	 * @return this.String
	 */
	public String getCategory() {
		return this.category;
	}

	/** 
	 * Set the category.
	 * @param category The category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/** 
	 * Returns the email.
	 * @return this.String
	 */
	public String getEmail() {
		return this.email;
	}

	/** 
	 * Set the email.
	 * @param email The email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/** 
	 * Returns the blogtext.
	 * @return this.String
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
	 * Returns the releventtext.
	 * @return this.String
	 */
	public String getReleventtext() {
		return this.releventtext;
	}

	/** 
	 * Set the releventtext.
	 * @param releventtext The releventtext to set
	 */
	public void setReleventtext(String releventtext) {
		this.releventtext = releventtext;
	}
	
//	validatin for the Write Complaint form. it check other category,brand and contact person.
	//and also check for the country ,state ,city others field 
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		ActionErrors errors = super.validate(mapping,request);
		//if(this.branduserChek == null)
		//{
			//errors.add("entpModifyComplaintForm",new ActionError("errors.required","Brand contact person"));
		//}
		//end of category check
		if(this.dealerchk.equalsIgnoreCase("checked"))
		{
			if(this.dealername.length()==0)
			{
				errors.add("entpModifyComplaintForm",new ActionError("errors.required","Dealer Name"));
			}
			if(this.dealeraddress.length()==0)
			{
				errors.add("entpModifyComplaintForm",new ActionError("errors.required","Dealer Address"));
			}
			if(this.country.equals("0~+0"))
			{
				errors.add("entpModifyComplaintForm",new ActionError("errors.required","Dealer Country"));
			}
			if(this.state.equals("0"))
			{
				errors.add("entpModifyComplaintForm",new ActionError("errors.required","Dealer State"));
			}
			if(this.city.equals("0"))
			{
				errors.add("entpModifyComplaintForm",new ActionError("errors.required","Dealer City"));
			}
			if(this.country.equals("-1~+0") && this.otherCountry.length()<=0)
			{
				errors.add("entpModifyComplaintForm",new ActionError("errors.resource.other","country"));
				if(this.otherState.length()<=0)
				{
					errors.add("entpModifyComplaintForm",new ActionError("errors.resource.other","state"));
					if(this.otherCity.length()<=0)
					{
						errors.add("entpModifyComplaintForm",new ActionError("errors.resource.other","city"));
					}
				}
			}
			else
			{
				if(this.state.equals("-1") && this.otherState.length()<=0)
				{
					errors.add("entpModifyComplaintForm",new ActionError("errors.resource.other","state"));
					if(this.otherCity.length()<=0)
					{
						errors.add("entpModifyComplaintForm",new ActionError("errors.resource.other","city"));
					}
				}
				else
				{
					if(this.city.equals("-1") && this.otherCity.length()<=0)
					{
						errors.add("entpModifyComplaintForm",new ActionError("errors.resource.other","city"));
					}
				}
				
			}
				
		}
		if(Integer.parseInt(this.tag)<=1)
		{
			errors.add("entpModifyComplaintForm",new ActionError("errors.resource.status"));
		}
		
		Vector<String> unnamedDataVec = new Vector<String>();
		for(int i=1; i<=35; i++)
		{
			if(request.getParameterValues("field"+i)!= null)
			{
				String arrTemp[] = request.getParameterValues("field"+i);
				String strTemp="";
				for(int j=0; j<arrTemp.length; j++)
				{
					strTemp=(strTemp.equalsIgnoreCase("")) ? arrTemp[j] : strTemp+", "+arrTemp[j];
					////System.out.println("Field "+i+" values "+arrTemp[j]);
				}
				String strValue = request.getParameter("fieldmnd"+i);
				String strFldName = request.getParameter("fldname"+i);
				////System.out.println("strValue "+i+" "+strValue);
				if(strValue.trim().equalsIgnoreCase("1"))
				{
					////System.out.println("strTemp.trim() "+i+" " + strTemp.trim());
					////System.out.println("strTemp.trim().length() "+i+" "+strTemp.trim().length());
					if(i<11)
					{
						if(Integer.parseInt(strTemp.trim())<=0)
						{
							errors.add("entpModifyComplaintForm",new ActionError("errors.required",strFldName));
						}
					}
					else
					{
						if(strTemp.trim().length()<=0)
						{
							errors.add("entpModifyComplaintForm",new ActionError("errors.required",strFldName));
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
		////System.out.println("Ajay Kumar Jha "+unnamedDataVec);
		HttpSession session = request.getSession();
		session.setAttribute("complaintUnnamedData",unnamedDataVec);
		session.setAttribute("catid", this.category);
		session.setAttribute("otherCatid", (this.otherCategory.equalsIgnoreCase("")) ? "NullNone" : this.otherCategory.toString().trim());
		session.setAttribute("Bid", this.brand);
		session.setAttribute("otherBid", (this.otherBrand.equalsIgnoreCase("")) ? "NullNone" : this.otherBrand.toString());
		return errors;
	}
}