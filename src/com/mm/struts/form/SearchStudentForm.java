package com.mm.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class SearchStudentForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;
	/** text property */
	
	/** loginId property */
	private String fcom_id= "";
	private String country= "0";
	private String state= "0";
	private String city= "0";
	private String category= "0";
	private String brand= "0";
	private String nameField= "";
	
	
	
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
	 * Returns the cmbBrand.
	 * @return this.String
	 */
	public String getBrand() {
		return this.brand;
	}

	/** 
	 * Set the cmbBrand.
	 * @param cmbBrand The cmbBrand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
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
							if(this.brand.equals("0"))
							{	
								errors.add("SearchStudentForm",new ActionError("errors.resource.search"));
							}
						}
					}
					
				}
			}
		}
		return errors;
	}
	

}
