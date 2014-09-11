package com.mm.struts.corpo.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.action.ActionForm;

public class ExpenseForm extends ActionForm{
	
	private String expDate;
	private int type;
	private String category;
	private float amount;
	private String description;
	private FormFile theFile;
	
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public FormFile getTheFile() {
		return theFile;
	}
	public void setTheFile(FormFile theFile) {
		this.theFile = theFile;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	

}
