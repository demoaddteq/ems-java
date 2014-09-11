package com.mm.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class CollegeRegisForm extends ValidatorForm {
	
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	
	/** CompanySName property */
	private String companySName="";
	
	/** companyName property */	
	private String companyName="";
	
	/** companyAddress1 property */	
	private String companyAddress1="";
	
	/** companyAddress2 property */	
	private String companyAddress2="";
	
	/** companyCity property */	
	private String companyCity="";
	
	/** companyState property */	
	private String companyState="";
	
	/** compCountry property */	
	private String compCountry="";
	
	/** companyCity property */	
	private String othercompanyCity="";
	
	/** companyState property */	
	private String othercompanyState="";
	
	/** compCountry property */	
	private String othercompCountry="";
	
	/** companyPostalCode property */	
	private String companyPostalCode="";
	
	/** companyEMail property */	
	private String companyEMail="";
	
	/** userCountry property */	
	private String txtCPPrefix="";
	
	/** txtCAreacode property */
	private String txtCAreacode="";
	
	/** txtCPhone property */
	private String txtCPhone="";
	
	/** companyTemplate property */	
	private String[] companyTemplate;
	
	/** companyTemplate property */	
	private String othercompanyTemplate="";
	
	
	private String mcata="0";
	private String mcatb="0";
	private String mcatc="0";
	private String mcatd="0";
	
	
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
	 * Returns the companySName.
	 * @return String
	 */
	public String getCompanySName() {
		return companySName;
	}

	/** 
	 * Set the companySName.
	 * @param companySName The companySName to set
	 */
	public void setCompanySName(String companySName) {
		this.companySName = companySName;
	}
	
	
	
	
	/** 
	 * Returns the companyName.
	 * @return String
	 */
	public String getCompanyName() {
		return companyName;
	}

	/** 
	 * Set the companyName.
	 * @param companyName The companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	/** 
	 * Returns the userPassword.
	 * @return String
	 */
	
	
	
	/** 
	 * Returns the companyAddress1.
	 * @return String
	 */
	public String getCompanyAddress1() {
		return companyAddress1;
	}

	/** 
	 * Set the companySName.
	 * @param companySName The companySName to set
	 */
	public void setCompanyAddress1(String companyAddress1) {
		this.companyAddress1 = companyAddress1;
	}
	
	
	
	
	
	/** 
	 * Returns the companyAddress2.
	 * @return String
	 */
	public String getCompanyAddress2() {
		return companyAddress2;
	}

	/** 
	 * Set the companyAddress2.
	 * @param companyAddress2 The companyAddress2 to set
	 */
	public void setCompanyAddress2(String companyAddress2) {
		this.companyAddress2 = companyAddress2;
	}
	
	
	
	
	
	/** 
	 * Returns the companyCity.
	 * @return String
	 */
	public String getCompanyCity() {
		return companyCity;
	}

	/** 
	 * Set the companyCity.
	 * @param companyCity The companyCity to set
	 */
	public void setCompanyCity(String companyCity) {
		this.companyCity = companyCity;
	}
	
	
	
	
	
	/** 
	 * Returns the companyState.
	 * @return String
	 */
	public String getCompanyState() {
		return companyState;
	}

	/** 
	 * Set the companyState.
	 * @param companyState The companyState to set
	 */
	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}
	
	
	
	
	
	/** 
	 * Returns the compCountry.
	 * @return String
	 */
	public String getCompCountry() {
		return compCountry;
	}

	/** 
	 * Set the compCountry.
	 * @param cCountry The compCountry to set
	 */
	public void setCompCountry(String compCountry) {
		this.compCountry = compCountry;
	}
	
	
	
	/** 
	 * Returns the companyCity.
	 * @return String
	 */
	public String getOthercompanyCity() {
		return othercompanyCity;
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
		return othercompanyState;
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
		return othercompCountry;
	}

	/** 
	 * Set the compCountry.
	 * @param cCountry The compCountry to set
	 */
	public void setOthercompCountry(String othercompCountry) {
		this.othercompCountry = othercompCountry;
	}
	
	
	
	/** 
	 * Returns the companyPostalCode.
	 * @return String
	 */
	public String getCompanyPostalCode() {
		return companyPostalCode;
	}

	/** 
	 * Set the companyPostalCode.
	 * @param companyPostalCode The companyPostalCode to set
	 */
	public void setCompanyPostalCode(String companyPostalCode) {
		this.companyPostalCode = companyPostalCode;
	}
	
	
	
	/** 
	 * Returns the companyEMail.
	 * @return String
	 */
	public String getCompanyEMail() {
		return companyEMail;
	}

	/** 
	 * Set the companyEMail.
	 * @param companyEMail The companyEMail to set
	 */
	public void setCompanyEMail(String companyEMail) {
		this.companyEMail = companyEMail;
	}
	
	
	
	/** 
	 * Returns the txtCPPrefix.
	 * @return String
	 */
	public String getTxtCPPrefix() {
		return txtCPPrefix;
	}

	/** 
	 * Set the txtCPPrefix.
	 * @param txtCPPrefix The txtCPPrefix to set
	 */
	public void setTxtCPPrefix(String txtCPPrefix) {
		this.txtCPPrefix = txtCPPrefix;
	}
	
	/** 
	 * Returns the txtCAreacode.
	 * @return String
	 */
	public String getTxtCAreacode() {
		return txtCAreacode;
	}

	/** 
	 * Set the txtCAreacode.
	 * @param txtCAreacode The txtCAreacode to set
	 */
	public void setTxtCAreacode(String txtCAreacode) {
		this.txtCAreacode = txtCAreacode;
	}
	
	

	/** 
	 * Returns the txtCPhone.
	 * @return String
	 */
	public String getTxtCPhone() {
		return txtCPhone;
	}

	/** 
	 * Set the txtCPhone.
	 * @param txtCPhone The txtCPhone to set
	 */
	public void setTxtCPhone(String txtCPhone) {
		this.txtCPhone = txtCPhone;
	}
	

	
	

	/** 
	 * Returns the companyTemplate.
	 * @return String
	 */
	public String[] getCompanyTemplate() {
		return companyTemplate;
	}

	/** 
	 * Set the companyTemplate.
	 * @param companyTemplate The companyTemplate to set
	 */
	public void setCompanyTemplate(String companyTemplate[]) {
		this.companyTemplate = companyTemplate;
	}
	
	/** 
	 * Returns the companyTemplate.
	 * @return String
	 */
	public String getOthercompanyTemplate() {
		return othercompanyTemplate;
	}

	/** 
	 * Set the companyTemplate.
	 * @param companyTemplate The companyTemplate to set
	 */
	public void setOthercompanyTemplate(String othercompanyTemplate) {
		this.othercompanyTemplate = othercompanyTemplate;
	}
	
	/**
	 * retrieve Corresponding VO object for transfering the form data to utility class.
	 */
	public CollegeRegisVo getCollegeRegisVo()
	{
		
		return new CollegeRegisVo(this.companySName,this.companyName,this.companyAddress1,this.companyAddress2,this.companyCity,this.companyState,this.compCountry,this.companyPostalCode,this.companyEMail,this.txtCPPrefix,this.txtCAreacode,this.txtCPhone,this.companyTemplate,this.othercompanyCity,this.othercompanyState,this.othercompCountry,this.othercompanyTemplate, this.mcata, this.mcatb, this.mcatc, this.mcatd);
	}
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{
		
		String cid = this.compCountry;
		req.setAttribute("cid", cid);
		//System.out.println("cid....collegeRegisForm......"+cid);
		if(cid.equalsIgnoreCase("-1"))
		{
			String othercompCountry = this.othercompCountry;
			req.setAttribute("othercompCountry", othercompCountry);
			//System.out.println("othercompCountry....collegeRegisForm......"+othercompCountry);
		}
		
		String sid = this.companyState;
		req.setAttribute("sid", sid);
		//System.out.println("sid....collegeRegisForm......"+sid);
		if(sid.equalsIgnoreCase("-1"))
		{
			String othercompanyState = this.othercompanyState;
			req.setAttribute("othercompanyState", othercompanyState);
			//System.out.println("othercompanyState....collegeRegisForm......"+othercompanyState);
		}
			
		String pid = this.companyCity;
		req.setAttribute("pid", pid);
		//System.out.println("pid....collegeRegisForm......"+pid);
		if(pid.equalsIgnoreCase("-1"))
		{
			String othercompanyCity = this.othercompanyCity;
			req.setAttribute("othercompanyCity", othercompanyCity);
			//System.out.println("othercompanyCity....collegeRegisForm......"+othercompanyCity);
		}
		
		if(this.companyTemplate!=null)
		{
		
			String ctemplate[] = this.companyTemplate;
			String industryCategory = "";
			for(int i=0; i< ctemplate.length ; i++)
			{
				
				if(i == (ctemplate.length-1)){
					industryCategory = industryCategory + ctemplate[i]  ;
					
				}
				else
				{
					industryCategory = industryCategory + ctemplate[i] + ", ";
				}
			}
			req.setAttribute("industryCategory", industryCategory);
			//System.out.println("ctemplate....collegeRegisForm......"+industryCategory);
			if(ctemplate[ctemplate.length-1].equalsIgnoreCase("-1"))
			{
				String othercompanyTemplate = this.othercompanyTemplate;
				req.setAttribute("othercompanyTemplate", othercompanyTemplate);
				//System.out.println("othercompanyTemplate....collegeRegisForm......"+othercompanyTemplate);
			}
		}
		
		ActionErrors errors = super.validate(mapping,req);
		
		if(this.compCountry.equals("-1~+0") && this.othercompCountry.length()<=0)
		{
			errors.add("collegeRegisForm",new ActionError("errors.resource.other","country"));
			if(this.othercompanyState.length()<=0)
			{
				errors.add("collegeRegisForm",new ActionError("errors.resource.other","state"));
				if(this.othercompanyCity.length()<=0)
				{
					errors.add("collegeRegisForm",new ActionError("errors.resource.other","city"));
				}
			}
		}
		else
		{
			if(this.companyState.equals("-1") && this.othercompanyState.length()<=0)
			{
				errors.add("collegeRegisForm",new ActionError("errors.resource.other","state"));
				if(this.othercompanyCity.length()<=0)
				{
					errors.add("collegeRegisForm",new ActionError("errors.resource.other","city"));
				}
			}
			else
			{
				if(this.companyCity.equals("-1") && this.othercompanyCity.length()<=0)
				{
					errors.add("collegeRegisForm",new ActionError("errors.resource.other","city"));
				}
			}
			
		}
		
		////System.out.println("this.companyTemplate.length>>"+this.companyTemplate.length);
		if(this.companyTemplate!=null)
		{
			
			for(int i=0;i<this.companyTemplate.length;i++)
			{
				if(Integer.parseInt(this.companyTemplate[i])==0)
					errors.add("collegeRegisForm",new ActionError("errors.resource.categorymore","category"));
				else
				{
					if(Integer.parseInt(this.companyTemplate[i])==-1 && this.othercompanyTemplate.length()<=0)
					{
						errors.add("collegeRegisForm",new ActionError("errors.resource.other","category"));
					}
				}
			}
		}
		else
		{
			////System.out.println("in else  section");
			errors.add("collegeRegisForm",new ActionError("errors.resource.categorymore","category"));
		}
		if(this.txtCPPrefix.equals("-1"))
		{
			errors.add("collegeRegisForm",new ActionError("errors.resource.valid","phone prefix"));
		}
			
		return errors;
	}

}
