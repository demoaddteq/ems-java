
package com.mm.struts.form;

/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */


import org.apache.struts.validator.ValidatorForm;
/** 
 * MyEclipse Struts
 * Creation date: 06-06-2007
 * 
 * XDoclet definition:
 * @struts.form name="loginForm"
 */
public class ComplaintsDetailForm extends ValidatorForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** loginId property */
	private String fcom_id= "";
	private String cmbCity= "";
	private String cmbCategory= "";
	private String cmbBrand= "";
	

	/** 
	 * Returns the fcom_id.
	 * @return String
	 */
	public String getFcom_id() {
		return fcom_id;
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
	 * @return String
	 */
	public String getCmbCity() {
		return cmbCity;
	}

	/** 
	 * Set the cmbCity.
	 * @param cmbCity The cmbCity to set
	 */
	public void setCmbCity(String cmbCity) {
		this.cmbCity = cmbCity;
	}
	/** 
	 * Returns the cmbCategory.
	 * @return String
	 */
	public String getCmbCategory() {
		return cmbCategory;
	}

	/** 
	 * Set the cmbCategory.
	 * @param cmbCategory The cmbCategory to set
	 */
	public void setCmbCategory(String cmbCategory) {
		this.cmbCategory = cmbCategory;
	}
	/** 
	 * Returns the cmbBrand.
	 * @return String
	 */
	public String getCmbBrand() {
		return cmbBrand;
	}

	/** 
	 * Set the cmbBrand.
	 * @param cmbBrand The cmbBrand to set
	 */
	public void setCmbBrand(String cmbBrand) {
		this.cmbBrand = cmbBrand;
	}
	
	
}
