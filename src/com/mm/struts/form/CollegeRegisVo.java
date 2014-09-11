package com.mm.struts.form;

public class CollegeRegisVo {
	
	/** CompanySName property */
	private String companySName;
	
	/** companyName property */	
	private String companyName;
	
	/** companyAddress1 property */	
	private String companyAddress1;
	
	/** companyAddress2 property */	
	private String companyAddress2;
	
	/** companyCity property */	
	private String companyCity;
	
	/** companyState property */	
	private String companyState;
	
	/** compCountry property */	
	private String compCountry;
	
	/** companyPostalCode property */	
	private String companyPostalCode;
	
	/** companyEMail property */	
	private String companyEMail;
	
	/** userCountry property */	
	private String txtCPPrefix;
	
	/** txtCAreacode property */
	private String txtCAreacode;
	
	/** txtCPhone property */
	private String txtCPhone;
	
	/** companyTemplate property */	
	private String companyTemplate[];
	
	private String othercompanyCity;
	
	/** companyState property */	
	private String othercompanyState;
	
	/** compCountry property */	
	private String othercompCountry;
	/** companyTemplate property */	
	private String othercompanyTemplate;
	
	/** mcata property */	
	private String mcata;
	
	/** mcatb property */	
	private String mcatb;
	
	/** mcatc property */	
	private String mcatc;
	
	/** mcatd property */	
	private String mcatd;
	
	
	
	public CollegeRegisVo(String companySName,String companyName,String companyAddress1,String companyAddress2,String companyCity,String companyState,String compCountry,String companyPostalCode,String companyEMail,String txtCPPrefix,String txtCAreacode,String txtCPhone,String companyTemplate[],String othercompanyCity,String othercompanyState,String othercompCountry,String othercompanyTemplate,String mcata,String mcatb,String mcatc,String mcatd)
	{
		this.companySName=companySName;
		this.companyName=companyName;
		this.companyAddress1=companyAddress1;
		this.companyAddress2=companyAddress2;
		this.companyCity=companyCity;
		this.companyState=companyState;
		this.compCountry=compCountry;
		this.companyPostalCode=companyPostalCode;
		this.companyEMail=companyEMail;
		this.txtCPPrefix=txtCPPrefix;
		this.txtCAreacode=txtCAreacode;
		this.txtCPhone=txtCPhone;
		this.companyTemplate=companyTemplate;
		this.othercompanyCity=othercompanyCity;
		this.othercompanyState=othercompanyState;
		this.othercompCountry=othercompCountry;
		this.othercompanyTemplate=othercompanyTemplate;
		this.mcata=mcata;
		this.mcatb=mcatb;
		this.mcatc=mcatc;
		this.mcatd=mcatd;
		
	}
	public String getCompanySName() {
		return companySName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getCompanyAddress1() {
		return companyAddress1;
	}
	public String getCompanyAddress2() {
		return companyAddress2;
	}
	public String getCompanyCity() {
		return companyCity;
	}
	public String getCompanyState() {
		return companyState;
	}
	public String getCompCountry() {
		return compCountry;
	}
	public String getCompanyPostalCode() {
		return companyPostalCode;
	}
	public String getCompanyEMail() {
		return companyEMail;
	}
	public String getTxtCPPrefix() {
		return txtCPPrefix;
	}
	public String getTxtCAreacode() {
		return txtCAreacode;
	}
	public String getTxtCPhone() {
		return txtCPhone;
	}
	public String[] getCompanyTemplate() {
		return companyTemplate;
	}
	public String getOthercompanyCity() {
		return othercompanyCity;
	}
	public String getOthercompanyState() {
		return othercompanyState;
	}
	public String getOthercompCountry() {
		return othercompCountry;
	}
	public String getOthercompanyTemplate() {
		return othercompanyTemplate;
	}
	public String getMcata() {
		return mcata;
	}
	public String getMcatb() {
		return mcatb;
	}
	public String getMcatc() {
		return mcatc;
	}
	public String getMcatd() {
		return mcatd;
	}

}
