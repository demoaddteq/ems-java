package com.mm.struts.form;

import org.apache.struts.validator.ValidatorForm;

public class FeedbackForm extends ValidatorForm {
	
	private static final long serialVersionUID = 1L;
	
	
	private String namec="";
	private String userid="";
	private String emailid="";
	private String feedbacktype="";
	private String subject="";
	private String text="";
	
	/** 
	 * Returns the namec.
	 * @return String
	 */
	public String getNamec() {
		return namec;
	}

	/** 
	 * Set the namec.
	 * @param mcata The namec to set
	 */
	public void setNamec(String namec) {
		this.namec = namec;
	}
	
	/** 
	 * Returns the userid.
	 * @return String
	 */
	public String getUserid() {
		return userid;
	}

	/** 
	 * Set the userid.
	 * @param userid The userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	

	/** 
	 * Returns the emailid.
	 * @return String
	 */
	public String getEmailid() {
		return emailid;
	}

	/** 
	 * Set the emailid.
	 * @param emailid The emailid to set
	 */
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	
	/** 
	 * Returns the feedbacktype.
	 * @return String
	 */
	public String getFeedbacktype() {
		return feedbacktype;
	}

	/** 
	 * Set the feedbacktype.
	 * @param feedbacktype The feedbacktype to set
	 */
	public void setFeedbacktype(String feedbacktype) {
		this.feedbacktype = feedbacktype;
	}
	
	/** 
	 * Returns the subject.
	 * @return String
	 */
	public String getSubject() {
		return subject;
	}

	/** 
	 * Set the subject.
	 * @param subject The subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/** 
	 * Returns the text.
	 * @return String
	 */
	public String getText() {
		return text;
	}

	/** 
	 * Set the text.
	 * @param text The text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	

}
