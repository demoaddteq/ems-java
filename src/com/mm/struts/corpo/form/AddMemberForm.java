
package com.mm.struts.corpo.form;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
import com.mm.struts.corpo.form.AddMemberForm;

/** 
 * MyEclipse Struts
 * Creation date: 06-27-2007
 * 
 * XDoclet definition:
 * @struts.form name="CommunityselectionForm"
 */
public class AddMemberForm extends ValidatorForm {

	/**
	 * otherCollegeName
	 */
	
	private static final long serialVersionUID = 1L;
	/** companyCity property */	
	
	/**  status */
	private String status;
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	/**  otherCollegeName */
	private String otherCollegeName;
	
	/** 
	 * Returns the otherCollegeName.
	 * @return String
	 */
	public String getOtherCollegeName() {
		return otherCollegeName;
	}
	/** 
	 * Set the otherCollegeName.
	 * @param otherCollegeName The otherCollegeName to set
	 */
	public void setOtherCollegeName(String otherCollegeName) {
		this.otherCollegeName = otherCollegeName;
	}
	
	/** password txtCollege_id */
	private String txtCollege_id;
	
	/** 
	 * Returns the txtCollege_id.
	 * @return String
	 */
	public String getTxtCollege_id() {
		return txtCollege_id;
	}
	/** 
	 * Set the txtCollege_id.
	 * @param txtCollege_id The txtCollege_id to set
	 */
	public void setTxtCollege_id(String txtCollege_id) {
		this.txtCollege_id = txtCollege_id;
	}
	
	/** password txtCourse */
	private String txtCourse;
	
	/** 
	 * Returns the txtCourse.
	 * @return String
	 */
	public String getTxtCourse() {
		return txtCourse;
	}
	/** 
	 * Set the txtCourse.
	 * @param txtCourse The txtCourse to set
	 */
	public void setTxtCourse(String txtCourse) {
		this.txtCourse = txtCourse;
	}
	
	/** password txtOthCourse */
	private String txtOthCourse;
	
	/** 
	 * Returns the txtOthCourse.
	 * @return String
	 */
	public String getTxtOthCourse() {
		return txtOthCourse;
	}
	/** 
	 * Set the txtOthCourse.
	 * @param txtOthCourse The txtOthCourse to set
	 */
	public void setTxtOthCourse(String txtOthCourse) {
		this.txtOthCourse = txtOthCourse;
	}
	
	/** password txtSubject */
	private String txtSubject;
	
	/** 
	 * Returns the txtSubject.
	 * @return String
	 */
	public String getTxtSubject() {
		return txtSubject;
	}
	/** 
	 * Set the txtSubject.
	 * @param txtSubject The txtSubject to set
	 */
	public void setTxtSubject(String txtSubject) {
		this.txtSubject = txtSubject;
	}
	
	/** password txtOthSubject */
	private String txtOthSubject;
	
	/** 
	 * Returns the txtOthSubject.
	 * @return String
	 */
	public String getTxtOthSubject() {
		return txtOthSubject;
	}
	/** 
	 * Set the txtOthSubject.
	 * @param txtOthSubject The txtOthSubject to set
	 */
	public void setTxtOthSubject(String txtOthSubject) {
		this.txtOthSubject = txtOthSubject;
	}
	
	/** password txtAdmission_no */
	private String txtAdmission_no;
	
	/** 
	 * Returns the txtAdmission_no.
	 * @return String
	 */
	public String getTxtAdmission_no() {
		return txtAdmission_no;
	}
	/** 
	 * Set the txtAdmission_no.
	 * @param txtAdmission_no The txtAdmission_no to set
	 */
	public void setTxtAdmission_no(String txtAdmission_no) {
		this.txtAdmission_no = txtAdmission_no;
	}
	
	/** password cons_cate */
	private String cons_cate;
	
	/** 
	 * Returns the cons_cate.
	 * @return String
	 */
	public String getCons_cate() {
		return cons_cate;
	}
	/** 
	 * Set the cons_cate.
	 * @param cons_cate The cons_cate to set
	 */
	public void setCons_cate(String cons_cate) {
		this.cons_cate = cons_cate;
	}
	
	
	
	/** password property */
	private String loginType;
	
	/** 
	 * Returns the loginType.
	 * @return String
	 */
	public String getLoginType() {
		return loginType;
	}
	/** 
	 * Set the loginType.
	 * @param loginType The loginType to set
	 */
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
	private String othercompanyCity="";
	
	/** companyState property */	
	private String othercompanyState="";
	
	/** compCountry property */	
	private String othercompCountry="";
	
	/** community property */	
	private String community="";
	
	
	/** userID property */
	private String userID = "";
	
	/** 
	 * Returns the userID.
	 * @return String
	 */
	public String getUserID() {
		return userID;
	}
	/** 
	 * Set the userID.
	 * @param userID The userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	/** userPassword property */
	private String userPassword = "";
	/** 
	 * Returns the userPassword.
	 * @return String
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/** 
	 * Set the userPassword.
	 * @param userPassword The userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	/** userRePassword property */
	private String userRePassword = "";
	/** 
	 * Returns the userRePassword.
	 * @return String
	 */
	public String getUserRePassword() {
		return userRePassword;
	}

	/** 
	 * Set the userRePassword.
	 * @param userRePassword The userRePassword to set
	 */
	public void setUserRePassword(String userRePassword) {
		this.userRePassword = userRePassword;
	}
	
	
	/** userFirstName property */
	private String userFirstName = "";
	/** 
	 * Returns the userFirstName.
	 * @return String
	 */
	public String getUserFirstName() {
		return this.userFirstName;
	}
	/** 
	 * Set the userFirstName.
	 * @param userFirstName The userFirstName to set
	 */
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	/**Resident Address Start**/
	

	private String builderFacility = "";
	/** 
	 * Returns the service id
	 * @return String
	 */
	public String getbuilderFacility() {
		return this.builderFacility;
	}
	/** 
	 * Set the userFirstName.
	 * @param userFirstName The userFirstName to set
	 */
	public void setbuilderFacility(String builderFacility) {
		this.builderFacility = builderFacility;
	}
	
	
	/** flatNo property */
	private String flatNo = "";
	/** 
	 * Returns the flatNo.
	 * @return String
	 */
	public String getFlatNo() {
		return this.flatNo;
	}
	/** 
	 * Set the userFirstName.
	 * @param userFirstName The userFirstName to set
	 */
	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}


	/** flatNo property */
	private String designation = "";
	/** 
	 * Returns the flatNo.
	 * @return String
	 */
	public String getdesignation() {
		return this.designation;
	}
	/** 
	 * Set the userFirstName.
	 * @param userFirstName The userFirstName to set
	 */
	public void setdesignation(String designation) {
		this.designation = designation;
	}


	/** towerName property */
	private String towerName = "";
	/** 
	 * Returns the towerName.
	 * @return String
	 */
	public String getTowerName() {
		return this.towerName;
	}
	/** 
	 * Set the userFirstName.
	 * @param userFirstName The userFirstName to set
	 */
	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}


	private String builder = "";
	/** 
	 * Returns the towerName.
	 * @return String
	 */
	public String getbuilder() {
		return this.builder;
	}
	/** 
	 * Set the userFirstName.
	 * @param userFirstName The userFirstName to set
	 */
	public void setbuilder(String builder) {
		this.builder = builder;
	}


	private String builderCity = "";
	/** 
	 * Returns the towerName.
	 * @return String
	 */
	public String getbuilderCity() {
		return this.builderCity;
	}
	/** 
	 * Set the userFirstName.
	 * @param userFirstName The userFirstName to set
	 */
	public void setbuilderCity(String builderCity) {
		this.builderCity = builderCity;
	}

	/** buildingName property */
	private String buildingName = "";
	/** 
	 * Returns the buildingName.
	 * @return String
	 */
	public String getBuildingName() {
		return this.buildingName;
	}
	/** 
	 * Set the buildingName.
	 * @param buildingName The buildingName to set
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	/**resident Address End**/
	
	
							/** Family Detail Property */
							/** Spouse Name Property */
	private String spouseName="";
	
	public String getSpouseName(){
		return this.spouseName;
	}
	public void setSpouseName(String spouseName){
		this.spouseName=spouseName;
	}
	

	/** cmbDate property */
	private String cmbDateSp = "";
	
	/** 
	 * Returns the cmbDate.
	 * @return String
	 */
	public String getCmbDateSp() {
		return this.cmbDateSp;
	}
	/** 
	 * Set the cmbDate.
	 * @param cmbDate The cmbDate to set
	 */
	public void setCmbDateSp(String cmbDateSp) {
		this.cmbDateSp = cmbDateSp;
	}
	
	
	/** cmbMonth property */
	private String cmbMonthSp = "";
	/** 
	 * Returns the cmbMonth.
	 * @return String
	 */
	public String getCmbMonthSp() {
		return this.cmbMonthSp;
	}

	/** 
	 * Set the cmbMonth.
	 * @param cmbMonth The cmbMonth to set
	 */
	public void setCmbMonthSp(String cmbMonthSp) {
		this.cmbMonthSp = cmbMonthSp;
	}
	
	
	/** cmbYear property */
	private String cmbYearSp = "";
	/** 
	 * Returns the cmbYear.
	 * @return String
	 */
	public String getCmbYearSp() {
		return this.cmbYearSp;
	}
	/** 
	 * Set the cmbYear.
	 * @param cmbYear The cmbYear to set
	 */
	public void setCmbYearSp(String cmbYearSp) {
		this.cmbYearSp = cmbYearSp;
	}
	
				/**Child 1 Detail Start**/
private String childName1="";
	
	public String getChildName1(){
		return this.childName1;
	}
	public void setChildName1(String childName1){
		this.childName1=childName1;
	}
	

	/** cmbDate property */
	private String cmbDateCh1 = "";
	
	/** 
	 * Returns the cmbDate.
	 * @return String
	 */
	public String getCmbDateCh1() {
		return this.cmbDateCh1;
	}
	/** 
	 * Set the cmbDate.
	 * @param cmbDate The cmbDate to set
	 */
	public void setCmbDateCh1(String cmbDateCh1) {
		this.cmbDateCh1 = cmbDateCh1;
	}
	
	
	/** cmbMonth property */
	private String cmbMonthCh1 = "";
	/** 
	 * Returns the cmbMonth.
	 * @return String
	 */
	public String getCmbMonthCh1() {
		return this.cmbMonthCh1;
	}

	/** 
	 * Set the cmbMonth.
	 * @param cmbMonth The cmbMonth to set
	 */
	public void setCmbMonthCh1(String cmbMonthCh1) {
		this.cmbMonthCh1 = cmbMonthCh1;
	}
	
	
	/** cmbYear property */
	private String cmbYearCh1 = "";
	/** 
	 * Returns the cmbYear.
	 * @return String
	 */
	public String getCmbYearCh1() {
		return this.cmbYearCh1;
	}
	/** 
	 * Set the cmbYear.
	 * @param cmbYear The cmbYear to set
	 */
	public void setCmbYearCh1(String cmbYearCh1) {
		this.cmbYearCh1 = cmbYearCh1;
	}
					/**Child1 Details End**/



					/**Child 2 Detail Start**/
private String childName2="";

public String getChildName2(){
return this.childName2;
}
public void setChildName2(String childName2){
this.childName2=childName2;
}


/** cmbDate property */
private String cmbDateCh2 = "";

/** 
* Returns the cmbDate.
* @return String
*/
public String getCmbDateCh2() {
return this.cmbDateCh2;
}
/** 
* Set the cmbDate.
* @param cmbDate The cmbDate to set
*/
public void setCmbDateCh2(String cmbDateCh2) {
this.cmbDateCh2 = cmbDateCh2;
}


/** cmbMonth property */
private String cmbMonthCh2 = "";
/** 
* Returns the cmbMonth.
* @return String
*/
public String getCmbMonthCh2() {
return this.cmbMonthCh2;
}

/** 
* Set the cmbMonth.
* @param cmbMonth The cmbMonth to set
*/
public void setCmbMonthCh2(String cmbMonthCh2) {
this.cmbMonthCh2 = cmbMonthCh2;
}


/** cmbYear property */
private String cmbYearCh2 = "";
/** 
* Returns the cmbYear.
* @return String
*/
public String getCmbYearCh2() {
return this.cmbYearCh2;
}
/** 
* Set the cmbYear.
* @param cmbYear The cmbYear to set
*/
public void setCmbYearCh2(String cmbYearCh2) {
this.cmbYearCh2 = cmbYearCh2;
}
		/**Child2 Details End**/





		/**Child 3 Detail Start**/

private String childName3="";

public String getChildName3(){
return this.childName3;
}
public void setChildName3(String childName3){
this.childName3=childName3;
}


/** cmbDate property */
private String cmbDateCh3 = "";

/** 
* Returns the cmbDate.
* @return String
*/
public String getCmbDateCh3() {
return this.cmbDateCh3;
}
/** 
* Set the cmbDate.
* @param cmbDate The cmbDate to set
*/
public void setCmbDateCh3(String cmbDateCh3) {
this.cmbDateCh3 = cmbDateCh3;
}


/** cmbMonth property */
private String cmbMonthCh3 = "";
/** 
* Returns the cmbMonth.
* @return String
*/
public String getCmbMonthCh3() {
return this.cmbMonthCh3;
}

/** 
* Set the cmbMonth.
* @param cmbMonth The cmbMonth to set
*/
public void setCmbMonthCh3(String cmbMonthCh3) {
this.cmbMonthCh3 = cmbMonthCh3;
}


/** cmbYear property */
private String cmbYearCh3 = "";
/** 
* Returns the cmbYear.
* @return String
*/
public String getCmbYearCh3() {
return this.cmbYearCh3;
}
/** 
* Set the cmbYear.
* @param cmbYear The cmbYear to set
*/
public void setCmbYearCh3(String cmbYearCh3) {
this.cmbYearCh3 = cmbYearCh3;
}
/**Child3 Details End**/

		/**Vehicle Information Start Here**/
	
/** Vehicle property */

private String vehicleType = "";
/** 
* Returns the vehicleType.
* @return String
*/
public String getVehicleType() {
return this.vehicleType;
}
/** 
* Set the vehicleType.
* @param vehicleType The vehicleType to set
*/
public void setVehicleType(String vehicleType) {
this.vehicleType = vehicleType;
}

/** Vehicle Name property */

private String vehicleName = "";
/** 
* Returns the vehicleName.
* @return String
*/
public String getVehicleName() {
return this.vehicleName;
}
/** 
* Set the vehicleName.
* @param vehicleName The vehicleName to set
*/
public void setVehicleName(String vehicleName) {
this.vehicleName = vehicleName;
}



/** Vehicle Model property */

private String modelYear = "";
/** 
* Returns the modelYear.
* @return String
*/
public String getModelYear() {
return this.modelYear;
}
/** 
* Set the modelYear.
* @param modelYear The modelYear to set
*/
public void setModelYear(String modelYear) {
this.modelYear = modelYear;
}


/** Vehicle Model property */

private String manuFacturer = "";
/** 
* Returns the manuFacturer.
* @return String
*/
public String getManuFacturer() {
return this.manuFacturer;
}
/** 
* Set the manuFacturer.
* @param manuFacturer The manuFacturer to set
*/
public void setManuFacturer(String manuFacturer) {
this.manuFacturer = manuFacturer;
}

		/**Vehicle Information Here Here**/
	
	/** userLastName property */
	private String userLastName = "";
	/** 
	 * Returns the userLastName.
	 * @return String
	 */
	public String getUserLastName() {
		return this.userLastName ;
	}
	/** 
	 * Set the userLastName.
	 * @param userLastName The userLastName to set
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	
	
	/** cmbGender property */
	private String cmbGender = "";
	/** 
	 * Returns the cmbGender.
	 * @return String
	 */
	public String getCmbGender() {
		return this.cmbGender;
	}	
	/** 
	 * Set the cmbGender.
	 * @param cmbGender The cmbGender to set
	 */
	public void setCmbGender(String cmbGender) {
		this.cmbGender = cmbGender;
	}
	
	
	/** cmbDate property */
	private String cmbDate = "";
	
	/** 
	 * Returns the cmbDate.
	 * @return String
	 */
	public String getCmbDate() {
		return this.cmbDate;
	}
	/** 
	 * Set the cmbDate.
	 * @param cmbDate The cmbDate to set
	 */
	public void setCmbDate(String cmbDate) {
		this.cmbDate = cmbDate;
	}
	
	
	/** cmbMonth property */
	private String cmbMonth = "";
	/** 
	 * Returns the cmbMonth.
	 * @return String
	 */
	public String getCmbMonth() {
		return this.cmbMonth;
	}

	/** 
	 * Set the cmbMonth.
	 * @param cmbMonth The cmbMonth to set
	 */
	public void setCmbMonth(String cmbMonth) {
		this.cmbMonth = cmbMonth;
	}
	
	
	/** cmbYear property */
	private String cmbYear = "";
	/** 
	 * Returns the cmbYear.
	 * @return String
	 */
	public String getCmbYear() {
		return this.cmbYear;
	}
	/** 
	 * Set the cmbYear.
	 * @param cmbYear The cmbYear to set
	 */
	public void setCmbYear(String cmbYear) {
		this.cmbYear = cmbYear;
	}
	
	
	/** maritalCombo property */
	private String maritalCombo = "";	
	/** 
	 * Returns the maritalCombo.
	 * @return String
	 */
	public String getMaritalCombo() {
		return this.maritalCombo;
	}
	/** 
	 * Set the maritalCombo.
	 * @param maritalCombo The maritalCombo to set
	 */
	public void setMaritalCombo(String maritalCombo) {
		this.maritalCombo = maritalCombo;
	}
	
	/** txtAddress property */
	private String txtAddress = "";
	/** 
	 * Returns the txtAddress.
	 * @return String
	 */
	public String getTxtAddress() {
		return this.txtAddress;
	}
	/** 
	 * Set the txtAddress.
	 * @param txtAddress The txtAddress to set
	 */
	public void setTxtAddress(String txtAddress) {
		this.txtAddress = txtAddress;
	}
	
	/** userCity property */
	private String companyCity = "";
	/** 
	 * Returns the userCity.
	 * @return String
	 */
	public String getCompanyCity() {
		return this.companyCity;
	}

	/** 
	 * Set the companyCity.
	 * @param companyCity The companyCity to set
	 */
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}
	
	/** userState property */
	private String companyState = "";
	/** 
	 * Returns the userState.
	 * @return String
	 */
	public String getCompanyState() {
		return this.companyState;
	}

	/** 
	 * Set the companyState.
	 * @param companyState The companyState to set
	 */
	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}
	
	
	/** userCountry property */
	private String compCountry = "";
	/** 
	 * Returns the userCountry.
	 * @return String
	 */
	public String getCompCountry() {
		return this.compCountry;
	}

	/** 
	 * Set the compCountry.
	 * @param cCountry The compCountry to set
	 */
	public void setCompCountry(String compCountry) {
		this.compCountry = compCountry;
	}
	public String getOthercompanyCity() {
		return this.othercompanyCity;
	}

	/** 
	 * Set the companyCity.
	 * @param companyCity The companyCity to set
	 */
	public void setOthercompanyCity(String othercompanyCity) {
		this.othercompanyCity = othercompanyCity;
	}
	
	
	
	
	
	/** 
	 * Returns the companyState.
	 * @return String
	 */
	public String getOthercompanyState() {
		return this.othercompanyState;
	}

	/** 
	 * Set the companyState.
	 * @param companyState The companyState to set
	 */
	public void setOthercompanyState(String othercompanyState) {
		this.othercompanyState = othercompanyState;
	}
	
	
	/** 
	 * Returns the compCountry.
	 * @return String
	 */
	public String getOthercompCountry() {
		return this.othercompCountry;
	}

	/** 
	 * Set the compCountry.
	 * @param cCountry The compCountry to set
	 */
	public void setOthercompCountry(String othercompCountry) {
		this.othercompCountry = othercompCountry;
	}
	/**community*/
	public String getCommunity() {
		return this.community;
	}

	/** 
	 * Set the txtPincode.
	 * @param txtPincode The txtPincode to set
	 */
	public void setCommunity(String community) {
		this.community = community;
	}
	/** txtPincode property */
	
	private String txtPincode = "";
	/** 
	 * Returns the txtPincode.
	 * @return String
	 */
	public String getTxtPincode() {
		return this.txtPincode;
	}

	/** 
	 * Set the txtPincode.
	 * @param txtPincode The txtPincode to set
	 */
	public void setTxtPincode(String txtPincode) {
		this.txtPincode = txtPincode;
	}
	
	/** txtPrefix property */
	private String txtPrefix = "";
	/** 
	 * Returns the txtPrefix.
	 * @return String
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
	
	/** txtMobile property */
	private String txtMobile = "";
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
	
	/** userEMail property */
	private String userEMail = "";
	/** 
	 * Returns the userEMail.
	 * @return String
	 */
	public String getUserEMail() {
		return this.userEMail;
	}
	/** 
	 * Set the userEMail.
	 * @param userEMail The userEMail to set
	 */
	public void setUserEMail(String userEMail) {
		this.userEMail = userEMail;
	}
	
	/** educationCombo property */
	private String educationCombo = "";
	/** 
	 * Returns the educationCombo.
	 * @return String
	 */
	public String getEducationCombo() {
		return this.educationCombo;
	}
	/** 
	 * Set the educationCombo.
	 * @param educationCombo The educationCombo to set
	 */
	public void setEducationCombo(String educationCombo) {
		this.educationCombo = educationCombo;
	}	
	/** professionCombo property */
	private String professionCombo = "";
	/** 
	 * Returns the professionCombo.
	 * @return String
	 */
	public String getProfessionCombo() {
		return this.professionCombo;
	}
	/** 
	 * Set the professionCombo.
	 * @param professionCombo The professionCombo to set
	 */
	public void setProfessionCombo(String professionCombo) {
		this.professionCombo = professionCombo;
	}
	
	/** educationCombo property */
	private String annualincomeCombo = "";
	/** 
	 * Returns the annualincomeCombo.
	 * @return String
	 */
	public String getAnnualincomeCombo() {
		return this.annualincomeCombo;
	}
	/** 
	 * Set the annualincomeCombo.
	 * @param annualincomeCombo The annualincomeCombo to set
	 */
	public void setAnnualincomeCombo(String annualincomeCombo) {
		this.annualincomeCombo = annualincomeCombo;
	}
	
	/** aboutUsCombo property */
	private String aboutUsCombo = "";	
	/** 
	 * Returns the aboutUsCombo.
	 * @return String
	 */
	public String getAboutUsCombo() {
		return this.aboutUsCombo;
	}
	/** 
	 * Set the aboutUsCombo.
	 * @param aboutUsCombo The aboutUsCombo to set
	 */
	public void setAboutUsCombo(String aboutUsCombo) {
		this.aboutUsCombo = aboutUsCombo;
	}
	
	/** agreement property */
	private String agreement = "";
	/** 
	 * Returns the agreement.
	 * @return String
	 */
	public String getAgreement() {
		return this.agreement;
	}
	/** 
	 * Set the agreement.
	 * @param agreement The agreement to set
	 */
	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}
	
	/**  collegeCountry */
	private String collegeCountry;
	
	/** 
	 * Returns the collegeCountry.
	 * @return String
	 */
	public String getCollegeCountry() {
		return "90~94";
	}
	/** 
	 * Set the collegeCountry.
	 * @param collegeCountry The collegeCountry to set
	 */
	public void setCollegeCountry(String collegeCountry) {
		this.collegeCountry = collegeCountry;
	}
	
	/**  collegeState */
	private String collegeState;
	
	/** 
	 * Returns the collegeState.
	 * @return String
	 */
	public String getCollegeState() {
		return collegeState;
	}
	/** 
	 * Set the collegeState.
	 * @param collegeState The collegeState to set
	 */
	public void setCollegeState(String collegeState) {
		this.collegeState = collegeState;
	}
	
	/**  collegeCity */
	private String collegeCity;
	
	/** 
	 * Returns the collegeCity.
	 * @return String
	 */
	public String getCollegeCity() {
		return collegeCity;
	}
	
	
	private String otherCollegeCity="";
	
	/** companyState property */	
	private String otherCollegeState="";
	
	/** compCountry property */	
	private String otherCollegeCountry="";
	
	
	public String getOtherCollegeCountry() {
		return this.otherCollegeCountry;
	}
	
	public void setOtherCollegeCountry(String otherCollegeCountry) {
		this.otherCollegeCountry = otherCollegeCountry;
	}
	
	public String getOtherCollegeState() {
		return this.otherCollegeState;
	}
	
	public void setOtherCollegeState(String otherCollegeState) {
		this.otherCollegeState = otherCollegeState;
	}
	
	public String getOtherCollegeCity() {
		return this.otherCollegeCity;
	}
	
	public void setOtherCollegeCity(String otherCollegeCity) {
		this.otherCollegeCity = otherCollegeCity;
	}
	
	
	/** 
	 * Set the collegeCity.
	 * @param collegeCity The collegeCity to set
	 */
	public void setCollegeCity(String collegeCity) {
		this.collegeCity = collegeCity;
	}
	//validatin for the user creatin form. it chaek password and retype password will be same.
	//and also check for the country ,state ,city others field 
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{	
		String cid = this.compCountry;
		req.setAttribute("cid", cid);
		
		System.out.println("cid....CommunityselectionForm......"+cid);
		if(cid.equalsIgnoreCase("-1~+0"))
		{
			String othercompCountry = this.othercompCountry;
			req.setAttribute("othercompCountry", othercompCountry);
			////System.out.println("othercompCountry....CommunityselectionForm......"+othercompCountry);
		}
		
		String sid = this.companyState;
		req.setAttribute("sid", sid);
		////System.out.println("sid....CommunityselectionForm......"+sid);
		if(sid.equalsIgnoreCase("-1"))
		{
			String othercompanyState = this.othercompanyState;
			req.setAttribute("othercompanyState", othercompanyState);
			////System.out.println("othercompanyState....CommunityselectionForm......"+othercompanyState);
		}
			
		String pid = this.companyCity;
		req.setAttribute("pid", pid);
		////System.out.println("pid....CommunityselectionForm......"+pid);
		if(pid.equalsIgnoreCase("-1"))
		{
			String othercompanyCity = this.othercompanyCity;
			req.setAttribute("othercompanyCity", othercompanyCity);
			////System.out.println("othercompanyCity....CommunityselectionForm......"+othercompanyCity);
		}
		
		
		
		String cid2 = "-1~+0";
		//this.collegeCountry;
		req.setAttribute("cid2", cid2);
		System.out.println("cid2....CommunityselectionForm...Ajay..."+cid2);
		if(cid2.equalsIgnoreCase("-1~+0"))
		{
			String othercompCountry2 = this.otherCollegeCountry;
			req.setAttribute("othercompCountry2", othercompCountry2);
			////System.out.println("othercompCountry2....CommunityselectionForm......"+othercompCountry2);
		}
		
		String sid2 = "1";
		req.setAttribute("sid2", sid2);
		////System.out.println("sid2....CommunityselectionForm......"+sid2);
		if(sid2.equalsIgnoreCase("-1"))
		{
			String othercompanyState2 = this.otherCollegeState;
			req.setAttribute("othercompanyState2", othercompanyState2);
			////System.out.println("othercompanyState2....CommunityselectionForm......"+othercompanyState2);
		}
			
		String pid2 = "-1";
		req.setAttribute("pid2", pid2);
		System.out.println("pid2....CommunityselectionForm......"+pid2);
		if(pid2.equalsIgnoreCase("-1"))
		{
			String othercompanyCity2 = this.otherCollegeCity;
			req.setAttribute("othercompanyCity2", othercompanyCity2);
			////System.out.println("othercompanyCity2....CommunityselectionForm......"+othercompanyCity2);
		}
		
		
		String college = "JIIT Noida";
		req.setAttribute("college", college);
		System.out.println("24aug....CommunityselectionForm......"+college);
		if(college.equalsIgnoreCase("-1"))
		{
			String othercollege = this.otherCollegeName;
			req.setAttribute("othercollege", othercollege);
			////System.out.println("othercollege....CommunityselectionForm......"+othercollege);
		}
		
		
		String course = "MCA";
		req.setAttribute("course", course);
		System.out.println("course....CommunityselectionForm......"+course);
		if(course.equalsIgnoreCase("-1"))
		{
			String othercourse = this.txtOthCourse;
			req.setAttribute("othercourse", othercourse);
			System.out.println("othercourse....CommunityselectionForm......"+othercourse);
		}
		
		String stream = "Computers";
		req.setAttribute("stream", stream);
		System.out.println("stream....CommunityselectionForm......"+stream);
		if(stream.equalsIgnoreCase("-1"))
		{
			String otherstream = this.txtOthSubject;
			req.setAttribute("otherstream", otherstream);
			System.out.println("otherstream....CommunityselectionForm......"+otherstream);
		}
		
		
		
		
		String commuId = this.community;
		req.setAttribute("commuId", commuId);
		System.out.println("commuId....CommunityselectionForm......"+commuId);
		//System.out.println("compCountry....CommunityselectionForm......"+this.compCountry);		
		//System.out.println("companyState....CommunityselectionForm......"+this.companyState);
		//System.out.println("companyCity....CommunityselectionForm......"+this.companyCity);
		//System.out.println("othercompCountry....CommunityselectionForm......"+this.othercompCountry);		
		//System.out.println("othercompanyState....CommunityselectionForm......"+this.othercompanyState);
		//System.out.println("othercompanyCity....CommunityselectionForm......"+this.othercompanyCity);
		System.out.println("line 1364::::");
		ActionErrors errors = super.validate(mapping,req);
		System.out.println("line 1366::::");
		if(!(this.userPassword.equals(this.userRePassword)))
		{
			errors.add("addMemberForm",new ActionError("errors.resource.match","password" , "retype password"));
		}
		System.out.println("line 1371");
		if(this.compCountry.equals("-1~+0") && this.othercompCountry.length()<=0)
		{
			errors.add("addMemberForm",new ActionError("errors.resource.other","country"));
			if(this.othercompanyState.length()<=0)
			{
				errors.add("addMemberForm",new ActionError("errors.resource.other","state"));
				if(this.othercompanyCity.length()<=0)
				{
					errors.add("addMemberForm",new ActionError("errors.resource.other","city"));
				}
			}
		}
		else
		{
			if(this.companyState.equals("-1") && this.othercompanyState.length()<=0)
			{
				errors.add("addMemberForm",new ActionError("errors.resource.other","state"));
				if(this.othercompanyCity.length()<=0)
				{
					errors.add("addMemberForm",new ActionError("errors.resource.other","city"));
				}
			}
			else
			{
				if(this.companyCity.equals("-1") && this.othercompanyCity.length()<=0)
				{
					errors.add("addMemberForm",new ActionError("errors.resource.other","city"));
				}
			}
			
		}
		
		
		if(this.txtPrefix.equals("-1"))
		{
			errors.add("addMemberForm",new ActionError("errors.resource.valid","mobile prefix"));
		}
		
		
		
		
		
		/*
		if(this.collegeCountry.equals("-1~+0") && this.otherCollegeCountry.length()<=0)
		{
			errors.add("communityselectionForm",new ActionError("errors.resource.other","country"));
			if(this.otherCollegeState.length()<=0)
			{
				errors.add("communityselectionForm",new ActionError("errors.resource.other","state"));
				if(this.otherCollegeCity.length()<=0)
				{
					errors.add("communityselectionForm",new ActionError("errors.resource.other","city"));
				}
			}
		}
		else
		{
			if(this.collegeState.equals("-1") && this.otherCollegeState.length()<=0)
			{
				errors.add("communityselectionForm",new ActionError("errors.resource.other","state"));
				if(this.otherCollegeCity.length()<=0)
				{
					errors.add("communityselectionForm",new ActionError("errors.resource.other","city"));
				}
			}
			else
			{
				if(this.collegeCity.equals("-1") && this.otherCollegeCity.length()<=0)
				{
					errors.add("communityselectionForm",new ActionError("errors.resource.other","city"));
				}
			}
			
		}
		
		*/
		
		
		
			
		return errors;
	}
	
	//end of validatin methd  this.community
	
}