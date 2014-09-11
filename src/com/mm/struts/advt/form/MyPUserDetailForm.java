package com.mm.struts.advt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
/** 
 * MyEclipse Struts
 * Creation date: 07-20-2007
 * 
 * XDoclet definition:
 * @struts.entp.form name="MyprofileForm"
 */
public class MyPUserDetailForm extends ValidatorForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** text property */
	
	private String loginName = "";	
	private String fname = "";	
	private String lname = "";	
	private String address = "";
	private String city = "";	
	private String state = "";	
	private String country = "";	
	private String zip = "";	
	private String email = "";	
	private String txtPPrefix = "";
	private String txtUACPrefix = "";
	private String txtPhone = "";	
	private String txtMobile = "";
	private String mobile = "";
	
	private String rid = "";
	
	private String mcata="0";
	private String mcatb="0";
	private String mcatc="0";
	private String mcatd="0";
	private String tecquer= "0";
	private String perquer= "0";
private String shortPro = "";
	
private String department= "";
private String otherdepartment= "";
private String designation= "";
private String otherdesignation= "";


/** 
 * Returns the department.
 * @return String
 */
public String getDepartment() {
	return department;
}
/** 
 * Set the department.
 * @param department The department to set
 */
public void setDepartment(String department) {
	this.department = department;
}

/** 
 * Returns the getOtherdepartment.
 * @return String
 */
public String getOtherdepartment() {
	return otherdepartment;
}
/** 
 * Set the getOtherdepartment.
 * @param getOtherdepartment The getOtherdepartment to set
 */
public void setOtherdepartment(String otherdepartment) {
	this.otherdepartment = otherdepartment;
}

/** 
 * Returns the getDesignation.
 * @return String
 */
public String getDesignation() {
	return designation;
}
/** 
 * Set the getDesignation.
 * @param getDesignation The getDesignation to set
 */
public void setDesignation(String designation) {
	this.designation = designation;
}

/** 
 * Returns the getOtherdesignation.
 * @return String
 */
public String getOtherdesignation() {
	return otherdesignation;
}
/** 
 * Set the getOtherdesignation.
 * @param getOtherdesignation The getOtherdesignation to set
 */
public void setOtherdesignation(String otherdesignation) {
	this.otherdesignation = otherdesignation;
}

/** 
 * Returns the mcata.
 * @return String
 */
public String getMcata() {
	return mcata;
}

/** 
 * Set the mcata.
 * @param mcata The mcata to set
 */
public void setMcata(String mcata) {
	this.mcata = mcata;
}

/** 
 * Returns the mcatb.
 * @return String
 */
public String getMcatb() {
	return mcatb;
}

/** 
 * Set the mcatb.
 * @param mcatb The mcatb to set
 */
public void setMcatb(String mcatb) {
	this.mcatb = mcatb;
}

/** 
 * Returns the mcatc.
 * @return String
 */
public String getMcatc() {
	return mcatc;
}

/** 
 * Set the mcatc.
 * @param mcatc The mcatc to set
 */
public void setMcatc(String mcatc) {
	this.mcatc = mcatc;
}

/** 
 * Returns the mcatd.
 * @return String
 */
public String getMcatd() {
	return mcatd;
}

/** 
 * Set the mcatd.
 * @param mcatd The mcatd to set
 */
public void setMcatd(String mcatd) {
	this.mcatd = mcatd;
}





/** 
 * Returns the tecquer.
 * @return String
 */
public String getTecquer() {
	return tecquer;
}
/** 
 * Set the tecquer.
 * @param tecquer The tecquer to set
 */
public void setTecquer(String tecquer) {
	this.tecquer = tecquer;
}

/** 
 * Returns the perquer.
 * @return String
 */
public String getPerquer() {
	return perquer;
}
/** 
 * Set the perquer.
 * @param perquer The perquer to set
 */
public void setPerquer(String perquer) {
	this.perquer = perquer;
}
	
	public String getShortPro() {
		return this.shortPro;
	}
	/** 
	 * Set the shortPro.
	 * @param shortPro The shortPro to set
	 */
	public void setShortPro(String shortPro) {
		this.shortPro = shortPro;
	}
	
	public String getRid() {
		return this.rid;
	}
	/** 
	 * Set the rid.
	 * @param rid The rid to set
	 */
	public void setRid(String rid) {
		this.rid = rid;
	}
	
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
	
	
	
	/** text property */
	private String othercountry = "";
	/** 
	 * Returns the otherCountry.
	 * @return String
	 */
	public String getOthercountry() {
		return this.othercountry;
	}
	/** 
	 * Set the otherCountry.
	 * @param otherCountry The otherCountry to set
	 */
	public void setOthercountry(String othercountry) {
		this.othercountry = othercountry;
	}
	
	
	/** text property */
	private String otherstate = "";
	
	/** 
	 * Returns the otherState.
	 * @return String
	 */
	public String getOtherstate() {
		return this.otherstate;
	}
	/** 
	 * Set the otherState.
	 * @param otherState The otherState to set
	 */
	public void setOtherstate(String otherstate) {
		this.otherstate = otherstate;
	}
	
	
	
	/** text property */
	private String othercity = "";
	
	/** 
	 * Returns the otherCity.
	 * @return String
	 */
	public String getOthercity() {
		return this.othercity;
	}
	/** 
	 * Set the otherCity.
	 * @param otherCity The otherCity to set
	 */
	public void setOthercity(String othercity) {
		this.othercity = othercity;
	}
	
	
	/** 
	 * Returns the loginName.
	 * @return String
	 */
	public String getLoginName() {
		return this.loginName;
	}
	/** 
	 * Set the loginName.
	 * @param loginName The loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	/** 
	 * Returns the fname.
	 * @return String
	 */
	public String getFname() {
		return this.fname;
	}
	/** 
	 * Set the fname.
	 * @param fname The fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	/** 
	 * Returns the lname.
	 * @return String
	 */
	public String getLname() {
		return this.lname;
	}
	/** 
	 * Set the lname.
	 * @param lname The lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	/** 
	 * Returns the address.
	 * @return String
	 */
	public String getAddress() {
		return this.address;
	}
	/** 
	 * Set the address.
	 * @param address The address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/** 
	 * Returns the city.
	 * @return String
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
	 * @return String
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
	 * Returns the ucountry.
	 * @return String
	 */
	public String getCountry() {
		return this.country;
	}
	/** 
	 * Set the ucountry.
	 * @param ucountry The ucountry to set
	 */
	public void setCountry(String ucountry) {
		this.country = ucountry;
	}
	
	/** 
	 * Returns the zip.
	 * @return String
	 */
	public String getZip() {
		return this.zip;
	}
	/** 
	 * Set the zip.
	 * @param zip The zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	/** 
	 * Returns the email.
	 * @return String
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
	 * Returns the txtPPrefix.
	 * @return String
	 */
	public String getTxtPPrefix() {
		return this.txtPPrefix;
	}
	/** 
	 * Set the txtPPrefix.
	 * @param txtPPrefix The txtPPrefix to set
	 */
	public void setTxtPPrefix(String txtPPrefix) {
		this.txtPPrefix = txtPPrefix;
	}
	/** 
	 * Returns the txtUACPrefix.
	 * @return String
	 */
	public String getTxtUACPrefix() {
		return this.txtUACPrefix;
	}
	/** 
	 * Set the txtUACPrefix.
	 * @param txtUACPrefix The txtUACPrefix to set
	 */
	public void setTxtUACPrefix(String txtUACPrefix) {
		this.txtUACPrefix = txtUACPrefix;
	}
	/** 
	 * Returns the txtPhone.
	 * @return String
	 */
	public String getTxtPhone() {
		return this.txtPhone;
	}
	/** 
	 * Set the txtPhone.
	 * @param txtPhone The txtPhone to set
	 */
	public void setTxtPhone(String txtPhone) {
		this.txtPhone = txtPhone;
	}
	
	/** 
	 * Returns the txtMobile.
	 * @return String
	 */
	public String getTxtMobile() {
		return this.txtMobile;
	}
	/** 
	 * Set the txtMobile.
	 * @param txtMobile The txtMobile to set
	 */
	public void setTxtMobile(String txtMobile) {
		this.txtMobile = txtMobile;
	}
	/** 
	 * Returns the mobile.
	 * @return String
	 */
	public String getMobile() {
		return this.mobile;
	}
	/** 
	 * Set the mobile.
	 * @param mobile The mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{
		ActionErrors errors = super.validate(mapping,req);
		
		
		if(this.country.equals("-1~+0") && this.othercountry.length()<=0)
		{
			errors.add("advtUserdetailsForm",new ActionError("errors.resource.other","country"));
			if(this.otherstate.length()<=0)
			{
				errors.add("advtUserdetailsForm",new ActionError("errors.resource.other","state"));
				if(this.othercity.length()<=0)
				{
					errors.add("advtUserdetailsForm",new ActionError("errors.resource.other","city"));
				}
			}
		}
		else
		{
			if(this.state.equals("-1") && this.otherstate.length()<=0)
			{
				errors.add("advtUserdetailsForm",new ActionError("errors.resource.other","state"));
				if(this.othercity.length()<=0)
				{
					errors.add("advtUserdetailsForm",new ActionError("errors.resource.other","city"));
				}
			}
			else
			{
				if(this.city.equals("-1") && this.othercity.length()<=0)
				{
					errors.add("advtUserdetailsForm",new ActionError("errors.resource.other","city"));
				}
			}
			
		}
		if(this.txtPPrefix.length()>0)
		{
			if(!(this.txtPPrefix.equalsIgnoreCase(this.txtMobile)))
			{
				errors.add("advtUserdetailsForm",new ActionError("errors.resource.match","phone prefix" , "mobile prefix"));
			}
		}
		if(this.txtPPrefix.equals("-1") || this.txtMobile.equals("-1"))
		{
			errors.add("advtUserdetailsForm",new ActionError("errors.resource.valid","phone or mobile prefix"));
		}
		return errors;
	}
	
}
