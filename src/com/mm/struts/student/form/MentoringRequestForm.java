package com.mm.struts.student.form;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class MentoringRequestForm extends ValidatorForm {
	private static final long serialVersionUID = 1L;
	
	/** text property */
	private String blogTitle= "";
	private String category= "";
	private String brand= "";
	private String otherCategory= "";
	private String otherBrand= "";
	private String checkDealer = "";
	private String dealerName= "";
	private String address= "";
	private String city= "0";
	private String state= "0";
	private String country= "0~+0";
	private String otherCountry="";
	private String otherState="";
	private String otherCity="";
	private String pincode= "000000";
	private String blogtext= "";
	private String releventtext= "";
	
	
	private String count = "";
	private String qtype = "";
	private String qcat = "";
	private String cons = "";
	private String qcat1 = "";	
	
	

	/** 
	 * Returns the qcat1.
	 * @return String
	 */
	public String getQcat1() {
		return this.qcat1;
	}
	/** 
	 * Set the qcat1.
	 * @param qcat1 The qcat1 to set
	 */
	public void setQcat1(String qcat1) {
		this.qcat1 = qcat1;
	}
	
	/** 
	 * Returns the cons.
	 * @return String
	 */
	public String getCons() {
		return this.cons;
	}
	/** 
	 * Set the cons.
	 * @param cons The cons to set
	 */
	public void setCons(String cons) {
		this.cons = cons;
	}
	
	/** 
	 * Returns the qcat.
	 * @return String
	 */
	public String getQcat() 
	{ /*String Ncat=this.qcat;
		if (Ncat.equalsIgnoreCase("0,Advertiser"))
		{
			Ncat=getQcat() ;
		}*/
		return this.qcat;
	}
	/** 
	 * Set the qcat.
	 * @param qcat The qcat to set
	 */
	public void setQcat(String qcat) {
		this.qcat = qcat;
	}
	
	/** 
	 * Returns the blogTitle.
	 * @return String
	 */
	public String getBlogTitle() {
		return this.blogTitle;
	}
	/** 
	 * Set the blogTitle.
	 * @param blogTitle The blogTitle to set
	 */
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}/** 
	 * Returns the category.
	 * @return String
	 */
	public String getCategory() {
		return this.category;
	}
	/** 
	 * Set the category.
	 * @param category The category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/** 
	 * Returns the brand.
	 * @return String
	 */
	public String getBrand() {
		return this.brand;
	}
	/** 
	 * Set the brand.
	 * @param brand The brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	/** 
	 * Returns the otherCategory.
	 * @return String
	 */
	public String getOtherCategory() {
		return this.otherCategory;
	}
	/** 
	 * Set the otherCategory.
	 * @param otherCategory The otherCategory to set
	 */
	public void setOtherCategory(String otherCategory) {
		this.otherCategory = otherCategory;
	}
	/** 
	 * Returns the otherBrand.
	 * @return String
	 */
	public String getOtherBrand() {
		return this.otherBrand;
	}
	/** 
	 * Set the otherBrand.
	 * @param otherBrand The otherBrand to set
	 */
	public void setOtherBrand(String otherBrand) {
		this.otherBrand = otherBrand;
	}
	
	/** 
	 * Returns the checkDealer.
	 * @return String
	 */
	public String getCheckDealer() {
		return this.checkDealer;
	}
	/** 
	 * Set the dealerName.
	 * @param dealerName The dealerName to set
	 */
	public void setCheckDealer(String checkDealer) {
		this.checkDealer = checkDealer;
	}
	/** 
	 * Returns the dealerName.
	 * @return String
	 */
	public String getDealerName() {
		return this.dealerName;
	}
	/** 
	 * Set the dealerName.
	 * @param dealerName The dealerName to set
	 */
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
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
	 * Returns the city.
	 * @return String
	 */
	public String getOtherCity() {
		return this.otherCity;
	}
	/** 
	 * Set the city.
	 * @param city The city to set
	 */
	
	public void setOtherCity(String otherCity) {
		this.otherCity = otherCity;
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
	 * Returns the otherState.
	 * @return String
	 */
	public String getOtherState() {
		return this.otherState;
	}
	/** 
	 * Set the otherState.
	 * @param otherState The otherState to set
	 */
	
	public void setOtherState(String otherState) {
		this.otherState = otherState;
	}
	/** 
	 * Returns the country.
	 * @return String
	 */
	public String getCountry() {
		return this.country;
	}
	/** 
	 * Set the country.
	 * @param country The country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/** 
	 * Returns the otherCountry.
	 * @return String
	 */
	public String getOtherCountry() {
		return this.otherCountry;
	}
	/** 
	 * Set the otherState.
	 * @param otherState The otherState to set
	 */
	
	public void setOtherCountry(String otherCountry) {
		this.otherCountry = otherCountry;
	}
	/** 
	 * Returns the pincode.
	 * @return String
	 */
	public String getPincode() {
		return this.pincode;
	}
	/** 
	 * Set the pincode.
	 * @param pincode The pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	/** 
	 * Returns the blogtext.
	 * @return String
	 */
	public String getBlogtext() {
		return this.blogtext;
	}
	/** 
	 * Set the blogtext.
	 * @param blogtext The blogtext to set
	 */
	public void setBlogtext(String blogtext) {
		this.blogtext = blogtext;
	}
	/** 
	 * Returns the releventtext.
	 * @return String
	 */
	public String getReleventtext() {
		return this.releventtext;
	}
	/** 
	 * Set the releventtext.
	 * @param releventtext The releventtext to set
	 */
	public void setReleventtext(String releventtext) {
		this.releventtext = releventtext;
	}
	
	
	
	/** 
	 * Returns the count.
	 * @return String
	 */
	public String getCount() {
		return this.count;
	}
	/** 
	 * Set the count.
	 * @param count The count to set
	 */
	public void setCount(String count) {
		this.count = count;
	}
	
	/** 
	 * Returns the qtype.
	 * @return String
	 */
	public String getQtype() {
		return this.qtype;
	}
	/** 
	 * Set the qtype.
	 * @param qtype The qtype to set
	 */
	public void setQtype(String qtype) {
		
		this.qtype = qtype;
	}
	
	private String service = "";
	/** 
	 * Returns the service id
	 * @return String
	 */
	public String getService() {
		return this.service;
	}
	/** 
	 * Set the userFirstName.
	 * @param userFirstName The userFirstName to set
	 */
	public void setService(String service) {
		this.service = service;
	}


	
	/** cmbDate property */
	private String cmbDatestart = "";
	
	/** 
	 * Returns the cmbDate.
	 * @return String
	 */
	public String getCmbDatestart() {
		return this.cmbDatestart;
	}
	/** 
	 * Set the cmbDate.
	 * @param cmbDate The cmbDate to set
	 */
	public void setCmbDatestart(String cmbDatestart) {
		this.cmbDatestart = cmbDatestart;
	}
	
	
	/** cmbMonth property */
	private String cmbMonthstart = "";
	/** 
	 * Returns the cmbMonth.
	 * @return String
	 */
	public String getCmbMonthstart() {
		return this.cmbMonthstart;
	}

	/** 
	 * Set the cmbMonth.
	 * @param cmbMonth The cmbMonth to set
	 */
	public void setCmbMonthstart(String cmbMonthstart) {
		this.cmbMonthstart = cmbMonthstart;
	}
	
	
	/** cmbYear property */
	private String cmbYearstart = "";
	/** 
	 * Returns the cmbYear.
	 * @return String
	 */
	public String getCmbYearstart() {
		return this.cmbYearstart;
	}
	/** 
	 * Set the cmbYear.
	 * @param cmbYear The cmbYear to set
	 */
	public void setCmbYearstart(String cmbYearstart) {
		this.cmbYearstart = cmbYearstart;
	}
	
				/**EndDate Detail Start**/

	/** cmbDate property */
	private String cmbDateend = "";
	
	/** 
	 * Returns the cmbDate.
	 * @return String
	 */
	public String getCmbDateend() {
		return this.cmbDateend;
	}
	/** 
	 * Set the cmbDate.
	 * @param cmbDate The cmbDate to set
	 */
	public void setCmbDateend(String cmbDateend) {
		this.cmbDateend = cmbDateend;
	}
	
	
	/** cmbMonth property */
	private String cmbMonthend = "";
	/** 
	 * Returns the cmbMonth.
	 * @return String
	 */
	public String getCmbMonthend() {
		return this.cmbMonthend;
	}

	/** 
	 * Set the cmbMonth.
	 * @param cmbMonth The cmbMonth to set
	 */
	public void setCmbMonthend(String cmbMonthend) {
		this.cmbMonthend = cmbMonthend;
	}
	
	
	/** cmbYear property */
	private String cmbYearend = "";
	/** 
	 * Returns the cmbYear.
	 * @return String
	 */
	public String getCmbYearend() {
		return this.cmbYearend;
	}
	/** 
	 * Set the cmbYear.
	 * @param cmbYear The cmbYear to set
	 */
	public void setCmbYearend(String cmbYearend) {
		this.cmbYearend = cmbYearend;
	}
					/**enddate Details End**/


//	validatin for the Write Complaint form. it check other category,brand and contact person.
	//and also check for the country ,state ,city others field 
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest req)
	{
		
		ActionErrors errors = super.validate(mapping,req);
		String catid="";
		if(getQcat().toString().equalsIgnoreCase("0,Advertiser"))
		{
			catid = this.qcat1;
		}else
		{
			catid = this.qcat;
		}
		 
		req.setAttribute("catid", catid);
		
		String comid = this.brand;
		req.setAttribute("comid", comid);
		
		String cpid = this.cons;
		req.setAttribute("cpid", cpid);
		
		//System.out.println("category.........  "+catid);
		//System.out.println("compan..........  "+comid);
		//System.out.println("contact pertion...........  "+cpid);
		
		
		
		//end of category check
		if(this.checkDealer.equalsIgnoreCase("checked"))
		{
			if(this.dealerName.length()==0)
			{
				errors.add("stdMentoringRequestForm",new ActionError("errors.required","Dealer Name"));
			}
			if(this.address.length()==0)
			{
				errors.add("stdMentoringRequestForm",new ActionError("errors.required","Dealer Address"));
			}
			if(this.country.equals("0~+0"))
			{
				errors.add("stdMentoringRequestForm",new ActionError("errors.required","Dealer Country"));
			}
			if(this.state.equals("0"))
			{
				errors.add("stdMentoringRequestForm",new ActionError("errors.required","Dealer State"));
			}
			if(this.city.equals("0"))
			{
				errors.add("stdMentoringRequestForm",new ActionError("errors.required","Dealer City"));
			}
			if(this.country.equals("-1~+0") && this.otherCountry.length()<=0)
			{
				errors.add("stdMentoringRequestForm",new ActionError("errors.resource.other","country"));
				if(this.otherState.length()<=0)
				{
					errors.add("stdMentoringRequestForm",new ActionError("errors.resource.other","state"));
					if(this.otherCity.length()<=0)
					{
						errors.add("stdMentoringRequestForm",new ActionError("errors.resource.other","city"));
					}
				}
			}
			else
			{
				if(this.state.equals("-1") && this.otherState.length()<=0)
				{
					errors.add("stdMentoringRequestForm",new ActionError("errors.resource.other","state"));
					if(this.otherCity.length()<=0)
					{
						errors.add("stdMentoringRequestForm",new ActionError("errors.resource.other","city"));
					}
				}
				else
				{
					if(this.city.equals("-1") && this.otherCity.length()<=0)
					{
						errors.add("stdMentoringRequestForm",new ActionError("errors.resource.other","city"));
					}
				}
				
			}
		
		}
		
		////System.out.println("Ajay Kumar Jha "+unnamedDataVec);
		HttpSession session = req.getSession();
		//session.setAttribute("c_type", this.c_type);
		////System.out.println("cptypecptypecptypecptype in form ...."+c_type);
		session.setAttribute("cid", this.country);
		session.setAttribute("sid", this.state);
		session.setAttribute("pid", this.city);
		session.setAttribute("catid", this.category);
		session.setAttribute("otherCatid", (this.otherCategory.equalsIgnoreCase("")) ? "NullNone" : this.otherCategory.toString().trim());
		session.setAttribute("Bid", this.brand);
		session.setAttribute("otherBid", (this.otherBrand.equalsIgnoreCase("")) ? "NullNone" : this.otherBrand.toString());

		return errors;
	}
	
	//end of validatin methd

}
