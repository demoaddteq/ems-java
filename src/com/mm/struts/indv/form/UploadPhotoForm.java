
package com.mm.struts.indv.form;

import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.upload.FormFile;

/** 
 * MyEclipse Struts
 * Creation date: 06-21-2007
 * 
 * XDoclet definition:
 * @struts.entp.form name="UploadPhotoForm"
 */
public class UploadPhotoForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;

	/** text property */
	private FormFile uploadfile;
	private String photoPath ="";
	
	
	public String getPhotoPath() {
		return this.photoPath;
	}
	/** 
	 * Set the userid.
	 * @param userid The userid to set
	 */
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
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
