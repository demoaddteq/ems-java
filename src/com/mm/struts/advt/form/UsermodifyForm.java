package com.mm.struts.advt.form;



import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class UsermodifyForm  extends ValidatorForm
{

	private static final long serialVersionUID = 1L;
	/** text property */
	
	private String loginName= "";
	private String state= "";
	private String otherstate= "";
	private String password= "";
	private String repassword= "";
	private String country= "";
	private String othercountry= "";
	private String zip= "";
	private String fname= "";
	private String email= "";
	private String lname= "";
	private String txtPPrefix= "";
	private String txtAreacode= "";
	private String txtPhone= "";
	private String txtPrefix= "";
	private String txtMobile= "";
	private String address= "";
	private String city= "";
	private String othercity= "";
	private String allrights= "";
	private String userdetails= "";
	private String changepassword= "";
	private String userdeletion = "";
	private String usercreation= "";
	private String usermodification= "";
	private String companyprofilemodify= "";
	private String userid= "";//hidden
	private String task = ""; //hidden
	
	private String cmbcat="0";
	private String chkGroup1="0";
	private String chkGroup2="0";
	private String chkGroup3="0";
	private String chkGroup4="0";
	private String chkGroup5="0";
	private String chkGroup6="0";
	private String chkGroup7="0";
	private String chkGroup8="0";
	private String chkGroup9="0";
	private String chkGroup10="0";
	private String rdbgroup="0";
	
	private String mcata= "0";
	private String mcatb= "0";
	private String mcatc= "0";
	private String mcatd= "0";
	
	private String tecquer= "0";
	private String perquer= "0";
	
	
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
	 * Returns the loginName.
	 * @return this. String
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
	 * Returns the state.
	 * @return this. String
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
	 * Returns the otherState.
	 * @return this. String
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
	/** 
	 * Returns the password.
	 * @return this. String
	 */
	public String getPassword() {
		return this.password;
	}
	/** 
	 * Set the password.
	 * @param password The password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/** 
	 * Returns the repassword.
	 * @return this. String
	 */
	public String getRepassword() {
		return this.repassword;
	}
	/** 
	 * Set the repassword.
	 * @param repassword The repassword to set
	 */
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	/** 
	 * Returns the ucountry.
	 * @return this. String
	 */
	public String getCountry() {
		return this.country;
	}
	/** 
	 * Set the ucountry.
	 * @param ucountry The ucountry to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

/** 
 * Returns the otherCountry.
 * @return this. String
 */
public String getOthercountry() {
	return this.othercountry;
}
/** 
 * Set the otherState.
 * @param otherState The otherState to set
 */

public void setOthercountry(String othercountry) {
	this.othercountry = othercountry;
}
	/** 
	 * Returns the zip.
	 * @return this. String
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
	 * Returns the fname.
	 * @return this. String
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
	 * Returns the email.
	 * @return this. String
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
	 * Returns the lname.
	 * @return this. String
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
	 * Returns the txtPPrefix.
	 * @return this. String
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
	 * Returns the txtAreacode.
	 * @return this. String
	 */
	public String getTxtAreacode() {
		return this.txtAreacode;
	}
	/** 
	 * Set the txtAreacode.
	 * @param txtAreacode The txtAreacode to set
	 */
	public void setTxtAreacode(String txtAreacode) {
		this.txtAreacode = txtAreacode;
	}
	/** 
	 * Returns the txtPhone.
	 * @return this. String
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
	 * Returns the txtPrefix.
	 * @return this. String
	 */
	public String getTxtPrefix() {
		return this.txtPrefix;
	}
	/** 
	 * Set the txtPrefix.
	 * @param txtPrefix The txtPrefix to set
	 */
	public void setTxtPrefix(String txtPrefix) {
		this.txtPrefix = txtPrefix;
	}
	/** 
	 * Returns the txtMobile.
	 * @return this. String
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
	 * Returns the address.
	 * @return this. String
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
	 * @return this. String
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
	 * Returns the city.
	 * @return this. String
	 */
	public String getOthercity() {
		return this.othercity;
	}
	/** 
	 * Set the city.
	 * @param city The city to set
	 */
	
	public void setOthercity(String othercity) {
		this.othercity = othercity;
	}
	
	/** 
	 * Returns the allrights.
	 * @return String
	 */
	public String getAllrights() {
		return allrights;
	}
	/** 
	 * Set the allrights.
	 * @param allrights The allrights to set
	 */
	public void setAllrights(String allrights) {
		this.allrights = allrights;
	}
	/** 
	 * Returns the userdetails.
	 * @return String
	 */
	public String getUserdetails() {
		return userdetails;
	}
	/** 
	 * Set the userdetails.
	 * @param userdetails The userdetails to set
	 */
	public void setUserdetails(String userdetails) {
		this.userdetails = userdetails;
	}
	/** 
	 * Returns the usercreation.
	 * @return String
	 */
	public String getUsercreation() {
		return usercreation;
	}
	/** 
	 * Set the usercreation.
	 * @param usercreation The usercreation to set
	 */
	public void setUsercreation(String usercreation) {
		this.usercreation = usercreation;
	}
	/** 
	 * Returns the usermodification.
	 * @return String
	 */
	public String getUsermodification() {
		return usermodification;
	}
	/** 
	 * Set the usermodification.
	 * @param usermodification The usermodification to set
	 */
	public void setUsermodification(String usermodification) {
		this.usermodification = usermodification;
	}	
	/** 
	 * Returns the companyprofilemodify.
	 * @return String
	 */
	public String getCompanyprofilemodify() {
		return companyprofilemodify;
	}
	/** 
	 * Set the companyprofilemodify.
	 * @param companyprofilemodify The companyprofilemodify to set
	 */
	public void setCompanyprofilemodify(String companyprofilemodify) {
		this.companyprofilemodify = companyprofilemodify;
	}
	/** 
	 * Returns the changepassword.
	 * @return String
	 */	
	public String getChangepassword() {
		return changepassword;
	}
	/** 
	 * Set the changepassword.
	 * @param changepassword The changepassword to set
	 */
	public void setChangepassword(String changepassword) {
		this.changepassword = changepassword;
	}
	/** 
	 * Returns the userdeletion.
	 * @return String
	 */
	public String getUserdeletion() {
		return userdeletion;
	}
	/** 
	 * Set the userdeletion.
	 * @param userdeletion The userdeletion to set
	 */
	public void setUserdeletion(String userdeletion) {
		this.userdeletion = userdeletion;

	}
	
	/** 
	 * Returns the userid.
	 * @return String
	 */
	public String getUserid() {
		return this.userid;
	}
	/** 
	 * Set the userid.
	 * @param userid The userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	/** 
	 * Returns the task.
	 * @return String
	 */
	public String getTask() {
		return this.task;
	}
	/** 
	 * Set the task.
	 * @param task The task to set
	 */
	public void setTask(String task) {
		this.task = task;
	}
//	private String rdbgroup;
	/** 
	 * Returns the rdbgroup.
	 * @return String
	 */
	public String getRdbgroup() {
		return rdbgroup;
	}
	/** 
	 * Set the rdbgroup.
	 * @param rdbgroup The rdbgroup to set
	 */
	public void setRdbgroup(String rdbgroup) {
		this.rdbgroup = rdbgroup;

	}
	
	//private String cmbcat="0";
	/** 
	 * Returns the cmbcat.
	 * @return String
	 */
	public String getCmbcat() {
		return cmbcat;
	}
	/** 
	 * Set the cmbcat.
	 * @param cmbcat The cmbcat to set
	 */
	public void setCmbcat(String cmbcat) {
		this.cmbcat = cmbcat;

	}
	//private String chkGroup1="0";
	/** 
	 * Returns the chkGroup1.
	 * @return String
	 */
	public String getChkGroup1() {
		return chkGroup1;
	}
	/** 
	 * Set the chkGroup1.
	 * @param chkGroup1 The chkGroup1 to set
	 */
	public void setChkGroup1(String chkGroup1) {
		this.chkGroup1 = chkGroup1;

	}
	//private String chkGroup2="0";
	/** 
	 * Returns the chkGroup2.
	 * @return String
	 */
	public String getChkGroup2() {
		return chkGroup2;
	}
	/** 
	 * Set the chkGroup2.
	 * @param chkGroup2 The chkGroup2 to set
	 */
	public void setChkGroup2(String chkGroup2) {
		this.chkGroup2 = chkGroup2;

	}
	//private String chkGroup3="0";
	/** 
	 * Returns the chkGroup3.
	 * @return String
	 */
	public String getChkGroup3() {
		return chkGroup3;
	}
	/** 
	 * Set the chkGroup3.
	 * @param chkGroup3 The chkGroup3 to set
	 */
	public void setChkGroup3(String chkGroup3) {
		this.chkGroup3 = chkGroup3;

	}
	//private String chkGroup4="0";
	/** 
	 * Returns the chkGroup4.
	 * @return String
	 */
	public String getChkGroup4() {
		return chkGroup4;
	}
	/** 
	 * Set the chkGroup4.
	 * @param chkGroup4 The chkGroup4 to set
	 */
	public void setChkGroup4(String chkGroup4) {
		this.chkGroup4 = chkGroup4;

	}
	//private String chkGroup5="0";
	/** 
	 * Returns the chkGroup5.
	 * @return String
	 */
	public String getChkGroup5() {
		return chkGroup5;
	}
	/** 
	 * Set the chkGroup5.
	 * @param chkGroup5 The chkGroup5 to set
	 */
	public void setChkGroup5(String chkGroup5) {
		this.chkGroup5 = chkGroup5;

	}
	//private String chkGroup6="0";
	/** 
	 * Returns the chkGroup6.
	 * @return String
	 */
	public String getChkGroup6() {
		return chkGroup6;
	}
	/** 
	 * Set the chkGroup6.
	 * @param chkGroup6 The chkGroup6 to set
	 */
	public void setChkGroup6(String chkGroup6) {
		this.chkGroup6 = chkGroup6;

	}
	//private String chkGroup7="0";
	/** 
	 * Returns the chkGroup7.
	 * @return String
	 */
	public String getChkGroup7() {
		return chkGroup7;
	}
	/** 
	 * Set the chkGroup7.
	 * @param chkGroup7 The chkGroup7 to set
	 */
	public void setChkGroup7(String chkGroup7) {
		this.chkGroup7 = chkGroup7;

	}
	//private String chkGroup8="0";
	/** 
	 * Returns the chkGroup8.
	 * @return String
	 */
	public String getChkGroup8() {
		return chkGroup8;
	}
	/** 
	 * Set the chkGroup8.
	 * @param chkGroup8 The chkGroup8 to set
	 */
	public void setChkGroup8(String chkGroup8) {
		this.chkGroup8 = chkGroup8;

	}
	//private String chkGroup9="0";
	/** 
	 * Returns the chkGroup9.
	 * @return String
	 */
	public String getChkGroup9() {
		return chkGroup9;
	}
	/** 
	 * Set the chkgroupval.
	 * @param chkgroupval The chkgroupval to set
	 */
	public void setChkGroup9(String chkGroup9) {
		this.chkGroup9 = chkGroup9;

	}
	//private String chkGroup10="0";
	/** 
	 * Returns the chkGroup10.
	 * @return String
	 */
	public String getChkGroup10() {
		return chkGroup10;
	}
	/** 
	 * Set the chkGroup10.
	 * @param chkGroup10 The chkGroup10 to set
	 */
	public void setChkGroup10(String chkGroup10) {
		this.chkGroup10 = chkGroup10;

	}
//	validatin for the Usermodify form. it check other category,brand and contact person.
	//and also check for the country ,state ,city others field 
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{
		ActionErrors errors = super.validate(mapping,req);
	
	if(this.country.equals("-1~+0") && this.othercountry.length()<=0)
	{
		errors.add("advtUsermodifyForm",new ActionError("errors.resource.other","country"));
		if(this.otherstate.length()<=0)
		{
			errors.add("advtUsermodifyForm",new ActionError("errors.resource.other","state"));
			if(this.othercity.length()<=0)
			{
				errors.add("advtUsermodifyForm",new ActionError("errors.resource.other","city"));
			}
		}
	}
	else
	{
		if(this.state.equals("-1") && this.otherstate.length()<=0)
		{
			errors.add("advtUsermodifyForm",new ActionError("errors.resource.other","state"));
			if(this.othercity.length()<=0)
			{
				errors.add("advtUsermodifyForm",new ActionError("errors.resource.other","city"));
			}
		}
		else
		{
			if(this.city.equals("-1") && this.othercity.length()<=0)
			{
				errors.add("advtUsermodifyForm",new ActionError("errors.resource.other","city"));
			}
		}
		
	}
	return  errors;

}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
