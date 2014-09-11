package com.mm.struts.advt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class SearchQueriesForm extends ValidatorForm {
	
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** loginId property */
	private String fcom_id= "";
	private String country= "0";
	private String state= "0";
	private String city= "0";
	private String category= "0";
	private String companyTemplate= "0";
	
	private String nameField= "";
	private String txtCourse= "";
	private String collegeList= "0";
	
	private String collegeOth= "";
	
	
	/** 
	 * Returns the collegeOth.
	 * @return this.String
	 */
	public String getCollegeOth() {
		return this.collegeOth;
	}

	/** 
	 * Set the collegeOth.
	 * @param collegeOth The collegeOth to set
	 */
	public void setCollegeOth(String collegeOth) {
		this.collegeOth = collegeOth;
	}
	
	/** 
	 * Returns the collegeList.
	 * @return this.String
	 */
	public String getCollegeList() {
		return this.collegeList;
	}

	/** 
	 * Set the collegeList.
	 * @param txtCourse The collegeList to set
	 */
	public void setCollegeList(String collegeList) {
		this.collegeList = collegeList;
	}
	
	/** 
	 * Returns the txtCourse.
	 * @return this.String
	 */
	public String getTxtCourse() {
		return this.txtCourse;
	}

	/** 
	 * Set the txtCourse.
	 * @param txtCourse The txtCourse to set
	 */
	public void setTxtCourse(String txtCourse) {
		this.txtCourse = txtCourse;
	}


	
	
	
	
	
	/** 
	 * Returns the nameField.
	 * @return this.String
	 */
	public String getNameField() {
		return this.nameField;
	}

	/** 
	 * Set the nameField.
	 * @param nameField The nameField to set
	 */
	public void setNameField(String nameField) {
		this.nameField = nameField;
	}

	/** 
	 * Returns the fcom_id.
	 * @return this.String
	 */
	public String getFcom_id() {
		return this.fcom_id;
	}

	/** 
	 * Set the fcom_id.
	 * @param fcom_id The fcom_id to set
	 */
	public void setFcom_id(String fcom_id) {
		this.fcom_id = fcom_id;
	}

	/** 
	 * Returns the cmbCity.
	 * @return this.String
	 */
	public String getCountry() {
		return this.country;
	}

	/** 
	 * Set the cmbCity.
	 * @param cmbCity The cmbCity to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/** 
	 * Returns the cmbCity.
	 * @return this.String
	 */
	public String getState() {
		return this.state;
	}

	/** 
	 * Set the cmbCity.
	 * @param cmbCity The cmbCity to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/** 
	 * Returns the cmbCity.
	 * @return this.String
	 */
	public String getCity() {
		return this.city;
	}

	/** 
	 * Set the cmbCity.
	 * @param cmbCity The cmbCity to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/** 
	 * Returns the cmbCategory.
	 * @return this.String
	 */
	public String getCategory() {
		return this.category;
	}

	/** 
	 * Set the cmbCategory.
	 * @param cmbCategory The cmbCategory to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/** 
	 * Returns the companyTemplate.
	 * @return this.String
	 */
	public String getCompanyTemplate() {
		return this.companyTemplate;
	}

	/** 
	 * Set the companyTemplate.
	 * @param companyTemplate The colcompanyTemplatelCat to set
	 */
	public void setCompanyTemplate(String companyTemplate) {
		this.companyTemplate = companyTemplate;
	}
//	validatin for the Write Complaint form. it check other category,brand and contact person.
	//and also check for the country ,state ,city others field 
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{
		ActionErrors errors = super.validate(mapping,req);
		//check for other category , brand and contact person
		
		if(this.fcom_id.equals(""))
		{
			if(this.country.equals("0"))
			{
				if(this.state.equals("0"))
				{	
					if(this.city.equals("0"))
					{	
						if(this.category.equals("0"))
						{	
							if(this.companyTemplate.equals("0"))
							{	
								errors.add("advtSearchQueriesForm",new ActionError("errors.resource.search"));
							}
						}
					}
					
				}
			}
		}
		return errors;
	}
	
	//end of validatin methd
	

}
