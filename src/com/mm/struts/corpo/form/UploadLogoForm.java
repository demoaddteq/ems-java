package com.mm.struts.corpo.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

public class UploadLogoForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;

	/** text property */
	private FormFile uploadfile;
	private String logoPath ="";
	
	
	public String getLogoPath() {
		return this.logoPath;
	}
	/** 
	 * Set the userid.
	 * @param userid The userid to set
	 */
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}
	
	/** 
	 * Returns the uploadfile.
	 * @return String
	 */
	public FormFile getUploadfile() {
		return uploadfile;
	}
	/** 
	 * Set the uploadfile.
	 * @param uploadfile The uploadfile to set
	 */
	public void setUploadfile(FormFile uploadfile) {
		this.uploadfile = uploadfile;
	}


}
