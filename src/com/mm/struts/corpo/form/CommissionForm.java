/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.corpo.form;


import org.apache.struts.validator.ValidatorForm;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 03-23-2010
 * 
 * XDoclet definition:
 * @struts.form name="commissionForm"
 */
public class CommissionForm extends ActionForm {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	
		private int table_id;
		private int earner_id;
		private int wid;
		private String start_date;
		private String end_date;
		private float amount;
		private int id;
		
		
		
		
		
		
		
	

	public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

	public float getAmount() {
			return amount;
		}

		public void setAmount(float amount) {
			this.amount = amount;
		}

		public int getEarner_id() {
			return earner_id;
		}

		public void setEarner_id(int earner_id) {
			this.earner_id = earner_id;
		}

		public String getEnd_date() {
			return end_date;
		}

		public void setEnd_date(String end_date) {
			this.end_date = end_date;
		}

		public String getStart_date() {
			return start_date;
		}

		public void setStart_date(String start_date) {
			this.start_date = start_date;
		}

		public int getTable_id() {
			return table_id;
		}

		public void setTable_id(int table_id) {
			this.table_id = table_id;
		}

		public int getWid() {
			return wid;
		}

		public void setWid(int wid) {
			this.wid = wid;
		}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.start_date=null;
		this.end_date=null;
		
	}
}