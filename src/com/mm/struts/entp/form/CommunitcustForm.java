package com.mm.struts.entp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

public class CommunitcustForm extends ValidatorForm {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String compId = "";

	
	private String field1 = "";
	private String cmbfield1 = "";
	private String valfield1 = "";
	private String mndfield1 = "0";
	private String vsbfield1 = "0";
	private String field2 = "";
	private String cmbfield2 = "";
	private String valfield2 = "";
	private String mndfield2 = "0";
	private String vsbfield2 = "0";
	private String field3 = "";
	private String cmbfield3 = "";
	private String valfield3 = "";
	private String mndfield3 = "0";
	private String vsbfield3 = "0";
	private String field4 = "";
	private String cmbfield4 = "";
	private String valfield4 = "";
	private String mndfield4 = "0";
	private String vsbfield4 = "0";
	private String field5 = "";
	private String cmbfield5 = "";
	private String valfield5 = "";
	private String mndfield5 = "0";
	private String vsbfield5 = "0";
	private String field6 = "";
	private String cmbfield6 = "";
	private String valfield6 = "";
	private String mndfield6 = "0";
	private String vsbfield6 = "0";
	private String field7 = "";
	private String cmbfield7 = "";
	private String valfield7 = "";
	private String mndfield7 = "0";
	private String vsbfield7 = "0";
	private String field8 = "";
	private String cmbfield8 = "";
	private String valfield8 = "";
	private String mndfield8 = "0";
	private String vsbfield8 = "0";
	private String field9 = "";
	private String cmbfield9 = "";
	private String valfield9 = "";
	private String mndfield9 = "0";
	private String vsbfield9 = "0";
	private String field10 = "";
	private String cmbfield10 = "";
	private String valfield10 = "";
	private String mndfield10 = "0";
	private String vsbfield10 = "0";
	private String field11 = "";
	private String cmbfield11 = "";
	private String valfield11 = "";
	private String mndfield11 = "0";
	private String vsbfield11 = "0";
	private String field12 = "";
	private String cmbfield12 = "";
	private String valfield12 = "";
	private String mndfield12 = "0";
	private String vsbfield12 = "0";
	private String field13 = "";
	private String cmbfield13 = "";
	private String valfield13 = "";
	private String mndfield13 = "0";
	private String vsbfield13 = "0";
	private String field14 = "";
	private String cmbfield14 = "";
	private String valfield14 = "";
	private String mndfield14 = "0";
	private String vsbfield14 = "0";
	private String field15 = "";
	private String cmbfield15 = "";
	private String valfield15 = "";
	private String mndfield15 = "0";
	private String vsbfield15 = "0";
	private String field16 = "";
	private String cmbfield16 = "";
	private String valfield16 = "";
	private String mndfield16 = "0";
	private String vsbfield16 = "0";
	private String field17 = "";
	private String cmbfield17 = "";
	private String valfield17 = "";
	private String mndfield17 = "0";
	private String vsbfield17 = "0";
	private String field18 = "";
	private String cmbfield18 = "";
	private String valfield18 = "";
	private String mndfield18 = "0";
	private String vsbfield18 = "0";
	private String field19 = "";
	private String cmbfield19 = "";
	private String valfield19 = "";
	private String mndfield19 = "0";
	private String vsbfield19 = "0";
	private String field20 = "";
	private String cmbfield20 = "";
	private String valfield20 = "";
	private String mndfield20 = "0";
	private String vsbfield20 = "0";
	private String field21 = "";
	private String cmbfield21 = "";
	private String valfield21 = "";
	private String mndfield21 = "0";
	private String vsbfield21 = "0";
	private String field22 = "";
	private String cmbfield22 = "";
	private String valfield22 = "";
	private String mndfield22 = "0";
	private String vsbfield22 = "0";
	private String field23 = "";
	private String cmbfield23 = "";
	private String valfield23 = "";
	private String mndfield23 = "0";
	private String vsbfield23 = "0";
	private String field24 = "";
	private String cmbfield24 = "";
	private String valfield24 = "";
	private String mndfield24 = "0";
	private String vsbfield24 = "0";
	private String field25 = "";
	private String cmbfield25 = "";
	private String valfield25 = "";
	private String mndfield25 = "0";
	private String vsbfield25 = "0";
	private String field26 = "";
	private String cmbfield26 = "";
	private String valfield26 = "";
	private String mndfield26 = "0";
	private String vsbfield26 = "0";
	private String field27 = "";
	private String cmbfield27 = "";
	private String valfield27 = "";
	private String mndfield27 = "0";
	private String vsbfield27 = "0";
	private String field28 = "";
	private String cmbfield28 = "";
	private String valfield28 = "";
	private String mndfield28 = "0";
	private String vsbfield28 = "0";
	private String field29 = "";
	private String cmbfield29 = "";
	private String valfield29 = "";
	private String mndfield29 = "0";
	private String vsbfield29 = "0";
	private String field30 = "";
	private String cmbfield30 = "";
	private String valfield30 = "";
	private String mndfield30 = "0";
	private String vsbfield30 = "0";
	private String field31 = "";
	private String cmbfield31 = "";
	private String valfield31 = "";
	private String mndfield31 = "0";
	private String vsbfield31 = "0";
	private String field32 = "";
	private String cmbfield32 = "";
	private String valfield32 = "";
	private String mndfield32 = "0";
	private String vsbfield32 = "0";
	private String field33 = "";
	private String cmbfield33 = "";
	private String valfield33 = "";
	private String mndfield33 = "0";
	private String vsbfield33 = "0";
	private String field34 = "";
	private String cmbfield34 = "";
	private String valfield34 = "";
	private String mndfield34 = "0";
	private String vsbfield34 = "0";
	private String field35 = "";
	private String cmbfield35 = "";
	private String valfield35 = "";
	private String mndfield35 = "0";
	private String vsbfield35 = "0";
	
	/*
	 * Generated Methods  
	 */
	
	public String getCompId() {
		return this.compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	
		
	public String getField1() {
		return this.field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getCmbfield1() {
		return this.cmbfield1;
	}
	public void setCmbfield1(String cmbfield1) {
		this.cmbfield1 = cmbfield1;
	}
	public String getValfield1() {
		return this.valfield1;
	}
	public void setValfield1(String valfield1) {
		this.valfield1 = valfield1;
	}
	public String getMndfield1() {
		return this.mndfield1;
	}
	public void setMndfield1(String mndfield1) {
		this.mndfield1 = mndfield1;
	}
	public String getVsbfield1() {
		return this.vsbfield1;
	}
	public void setVsbfield1(String vsbfield1) {
		this.vsbfield1 = vsbfield1;
	}
	
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getCmbfield2() {
		return cmbfield2;
	}
	public void setCmbfield2(String cmbfield2) {
		this.cmbfield2 = cmbfield2;
	}
	public String getValfield2() {
		return valfield2;
	}
	public void setValfield2(String valfield2) {
		this.valfield2 = valfield2;
	}
	public String getMndfield2() {
		return mndfield2;
	}
	public void setMndfield2(String mndfield2) {
		this.mndfield2 = mndfield2;
	}
	public String getVsbfield2() {
		return vsbfield2;
	}
	public void setVsbfield2(String vsbfield2) {
		this.vsbfield2 = vsbfield2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	public String getCmbfield3() {
		return cmbfield3;
	}
	public void setCmbfield3(String cmbfield3) {
		this.cmbfield3 = cmbfield3;
	}
	public String getValfield3() {
		return valfield3;
	}
	public void setValfield3(String valfield3) {
		this.valfield3 = valfield3;
	}
	public String getMndfield3() {
		return mndfield3;
	}
	public void setMndfield3(String mndfield3) {
		this.mndfield3 = mndfield3;
	}
	public String getVsbfield3() {
		return vsbfield3;
	}
	public void setVsbfield3(String vsbfield3) {
		this.vsbfield3 = vsbfield3;
	}
	
	public String getField4() {
		return field4;
	}
	public void setField4(String field4) {
		this.field4 = field4;
	}
	public String getCmbfield4() {
		return cmbfield4;
	}
	public void setCmbfield4(String cmbfield4) {
		this.cmbfield4 = cmbfield4;
	}
	public String getValfield4() {
		return valfield4;
	}
	public void setValfield4(String valfield4) {
		this.valfield4 = valfield4;
	}
	public String getMndfield4() {
		return mndfield4;
	}
	public void setMndfield4(String mndfield4) {
		this.mndfield4 = mndfield4;
	}
	public String getVsbfield4() {
		return vsbfield4;
	}
	public void setVsbfield4(String vsbfield4) {
		this.vsbfield4 = vsbfield4;
	}
	public String getField5() {
		return field5;
	}
	public void setField5(String field5) {
		this.field5 = field5;
	}
	public String getCmbfield5() {
		return cmbfield5;
	}
	public void setCmbfield5(String cmbfield5) {
		this.cmbfield5 = cmbfield5;
	}
	public String getValfield5() {
		return valfield5;
	}
	public void setValfield5(String valfield5) {
		this.valfield5 = valfield5;
	}
	public String getMndfield5() {
		return mndfield5;
	}
	public void setMndfield5(String mndfield5) {
		this.mndfield5 = mndfield5;
	}
	public String getVsbfield5() {
		return vsbfield5;
	}
	public void setVsbfield5(String vsbfield5) {
		this.vsbfield5 = vsbfield5;
	}
	
	public String getField6() {
		return field6;
	}
	public void setField6(String field6) {
		this.field6 = field6;
	}
	public String getCmbfield6() {
		return cmbfield6;
	}
	public void setCmbfield6(String cmbfield6) {
		this.cmbfield6 = cmbfield6;
	}
	public String getValfield6() {
		return valfield6;
	}
	public void setValfield6(String valfield6) {
		this.valfield6 = valfield6;
	}
	public String getMndfield6() {
		return mndfield6;
	}
	public void setMndfield6(String mndfield6) {
		this.mndfield6 = mndfield6;
	}
	public String getVsbfield6() {
		return vsbfield6;
	}
	public void setVsbfield6(String vsbfield6) {
		this.vsbfield6 = vsbfield6;
	}
	
	public String getField7() {
		return field7;
	}
	public void setField7(String field7) {
		this.field7 = field7;
	}
	public String getCmbfield7() {
		return cmbfield7;
	}
	public void setCmbfield7(String cmbfield7) {
		this.cmbfield7 = cmbfield7;
	}
	public String getValfield7() {
		return valfield7;
	}
	public void setValfield7(String valfield7) {
		this.valfield7 = valfield7;
	}
	public String getMndfield7() {
		return mndfield7;
	}
	public void setMndfield7(String mndfield7) {
		this.mndfield7 = mndfield7;
	}
	public String getVsbfield7() {
		return vsbfield7;
	}
	public void setVsbfield7(String vsbfield7) {
		this.vsbfield7 = vsbfield7;
	}
	public String getField8() {
		return field8;
	}
	public void setField8(String field8) {
		this.field8 = field8;
	}
	public String getCmbfield8() {
		return cmbfield8;
	}
	public void setCmbfield8(String cmbfield8) {
		this.cmbfield8 = cmbfield8;
	}
	public String getValfield8() {
		return valfield8;
	}
	public void setValfield8(String valfield8) {
		this.valfield8 = valfield8;
	}
	public String getMndfield8() {
		return mndfield8;
	}
	public void setMndfield8(String mndfield8) {
		this.mndfield8 = mndfield8;
	}
	public String getVsbfield8() {
		return vsbfield8;
	}
	public void setVsbfield8(String vsbfield8) {
		this.vsbfield8 = vsbfield8;
	}
	
	public String getField9() {
		return field9;
	}
	public void setField9(String field9) {
		this.field9 = field9;
	}
	public String getCmbfield9() {
		return cmbfield9;
	}
	public void setCmbfield9(String cmbfield9) {
		this.cmbfield9 = cmbfield9;
	}
	public String getValfield9() {
		return valfield9;
	}
	public void setValfield9(String valfield9) {
		this.valfield9 = valfield9;
	}
	public String getMndfield9() {
		return mndfield9;
	}
	public void setMndfield9(String mndfield9) {
		this.mndfield9 = mndfield9;
	}
	public String getVsbfield9() {
		return vsbfield9;
	}
	public void setVsbfield9(String vsbfield9) {
		this.vsbfield9 = vsbfield9;
	}
	public String getField10() {
		return field10;
	}
	public void setField10(String field10) {
		this.field10 = field10;
	}
	public String getCmbfield10() {
		return cmbfield10;
	}
	public void setCmbfield10(String cmbfield10) {
		this.cmbfield10 = cmbfield10;
	}
	public String getValfield10() {
		return valfield10;
	}
	public void setValfield10(String valfield10) {
		this.valfield10 = valfield10;
	}
	public String getMndfield10() {
		return mndfield10;
	}
	public void setMndfield10(String mndfield10) {
		this.mndfield10 = mndfield10;
	}
	public String getVsbfield10() {
		return vsbfield10;
	}
	public void setVsbfield10(String vsbfield10) {
		this.vsbfield10 = vsbfield10;
	}
	
	public String getField11() {
		return field11;
	}
	public void setField11(String field11) {
		this.field11 = field11;
	}
	public String getCmbfield11() {
		return cmbfield11;
	}
	public void setCmbfield11(String cmbfield11) {
		this.cmbfield11 = cmbfield11;
	}
	public String getValfield11() {
		return valfield11;
	}
	public void setValfield11(String valfield11) {
		this.valfield11 = valfield11;
	}
	public String getMndfield11() {
		return mndfield11;
	}
	public void setMndfield11(String mndfield11) {
		this.mndfield11 = mndfield11;
	}
	public String getVsbfield11() {
		return vsbfield11;
	}
	public void setVsbfield11(String vsbfield11) {
		this.vsbfield11 = vsbfield11;
	}
	
	public String getField12() {
		return field12;
	}
	public void setField12(String field12) {
		this.field12 = field12;
	}
	public String getCmbfield12() {
		return cmbfield12;
	}
	public void setCmbfield12(String cmbfield12) {
		this.cmbfield12 = cmbfield12;
	}
	public String getValfield12() {
		return valfield12;
	}
	public void setValfield12(String valfield12) {
		this.valfield12 = valfield12;
	}
	public String getMndfield12() {
		return mndfield12;
	}
	public void setMndfield12(String mndfield12) {
		this.mndfield12 = mndfield12;
	}
	public String getVsbfield12() {
		return vsbfield12;
	}
	public void setVsbfield12(String vsbfield12) {
		this.vsbfield12 = vsbfield12;
	}
	public String getField13() {
		return field13;
	}
	public void setField13(String field13) {
		this.field13 = field13;
	}
	public String getCmbfield13() {
		return cmbfield13;
	}
	public void setCmbfield13(String cmbfield13) {
		this.cmbfield13 = cmbfield13;
	}
	public String getValfield13() {
		return valfield13;
	}
	public void setValfield13(String valfield13) {
		this.valfield13 = valfield13;
	}
	public String getMndfield13() {
		return mndfield13;
	}
	public void setMndfield13(String mndfield13) {
		this.mndfield13 = mndfield13;
	}
	public String getVsbfield13() {
		return vsbfield13;
	}
	public void setVsbfield13(String vsbfield13) {
		this.vsbfield13 = vsbfield13;
	}
	
	public String getField14() {
		return field14;
	}
	public void setField14(String field14) {
		this.field14 = field14;
	}
	public String getCmbfield14() {
		return cmbfield14;
	}
	public void setCmbfield14(String cmbfield14) {
		this.cmbfield14 = cmbfield14;
	}
	public String getValfield14() {
		return valfield14;
	}
	public void setValfield14(String valfield14) {
		this.valfield14 = valfield14;
	}
	public String getMndfield14() {
		return mndfield14;
	}
	public void setMndfield14(String mndfield14) {
		this.mndfield14 = mndfield14;
	}
	public String getVsbfield14() {
		return vsbfield14;
	}
	public void setVsbfield14(String vsbfield14) {
		this.vsbfield14 = vsbfield14;
	}
	public String getField15() {
		return field15;
	}
	public void setField15(String field15) {
		this.field15 = field15;
	}
	public String getCmbfield15() {
		return cmbfield15;
	}
	public void setCmbfield15(String cmbfield15) {
		this.cmbfield15 = cmbfield15;
	}
	public String getValfield15() {
		return valfield15;
	}
	public void setValfield15(String valfield15) {
		this.valfield15 = valfield15;
	}
	public String getMndfield15() {
		return mndfield15;
	}
	public void setMndfield15(String mndfield15) {
		this.mndfield15 = mndfield15;
	}
	public String getVsbfield15() {
		return vsbfield15;
	}
	public void setVsbfield15(String vsbfield15) {
		this.vsbfield15 = vsbfield15;
	}
	
	public String getField16() {
		return field16;
	}
	public void setField16(String field16) {
		this.field16 = field16;
	}
	public String getCmbfield16() {
		return cmbfield16;
	}
	public void setCmbfield16(String cmbfield16) {
		this.cmbfield16 = cmbfield16;
	}
	public String getValfield16() {
		return valfield16;
	}
	public void setValfield16(String valfield16) {
		this.valfield16 = valfield16;
	}
	public String getMndfield16() {
		return mndfield16;
	}
	public void setMndfield16(String mndfield16) {
		this.mndfield16 = mndfield16;
	}
	public String getVsbfield16() {
		return vsbfield16;
	}
	public void setVsbfield16(String vsbfield16) {
		this.vsbfield16 = vsbfield16;
	}
	
	public String getField17() {
		return field17;
	}
	public void setField17(String field17) {
		this.field17 = field17;
	}
	public String getCmbfield17() {
		return cmbfield17;
	}
	public void setCmbfield17(String cmbfield17) {
		this.cmbfield17 = cmbfield17;
	}
	public String getValfield17() {
		return valfield17;
	}
	public void setValfield17(String valfield17) {
		this.valfield17 = valfield17;
	}
	public String getMndfield17() {
		return mndfield17;
	}
	public void setMndfield17(String mndfield17) {
		this.mndfield17 = mndfield17;
	}
	public String getVsbfield17() {
		return vsbfield17;
	}
	public void setVsbfield17(String vsbfield17) {
		this.vsbfield17 = vsbfield17;
	}
	public String getField18() {
		return field18;
	}
	public void setField18(String field18) {
		this.field18 = field18;
	}
	public String getCmbfield18() {
		return cmbfield18;
	}
	public void setCmbfield18(String cmbfield18) {
		this.cmbfield18 = cmbfield18;
	}
	public String getValfield18() {
		return valfield18;
	}
	public void setValfield18(String valfield18) {
		this.valfield18 = valfield18;
	}
	public String getMndfield18() {
		return mndfield18;
	}
	public void setMndfield18(String mndfield18) {
		this.mndfield18 = mndfield18;
	}
	public String getVsbfield18() {
		return vsbfield18;
	}
	public void setVsbfield18(String vsbfield18) {
		this.vsbfield18 = vsbfield18;
	}
	
	public String getField19() {
		return field19;
	}
	public void setField19(String field19) {
		this.field19 = field19;
	}
	public String getCmbfield19() {
		return cmbfield19;
	}
	public void setCmbfield19(String cmbfield19) {
		this.cmbfield19 = cmbfield19;
	}
	public String getValfield19() {
		return valfield19;
	}
	public void setValfield19(String valfield19) {
		this.valfield19 = valfield19;
	}
	public String getMndfield19() {
		return mndfield19;
	}
	public void setMndfield19(String mndfield19) {
		this.mndfield19 = mndfield19;
	}
	public String getVsbfield19() {
		return vsbfield19;
	}
	public void setVsbfield19(String vsbfield19) {
		this.vsbfield19 = vsbfield19;
	}
	public String getField20() {
		return field20;
	}
	public void setField20(String field20) {
		this.field20 = field20;
	}
	public String getCmbfield20() {
		return cmbfield20;
	}
	public void setCmbfield20(String cmbfield20) {
		this.cmbfield20 = cmbfield20;
	}
	public String getValfield20() {
		return valfield20;
	}
	public void setValfield20(String valfield20) {
		this.valfield20 = valfield20;
	}
	public String getMndfield20() {
		return mndfield20;
	}
	public void setMndfield20(String mndfield20) {
		this.mndfield20 = mndfield20;
	}
	public String getVsbfield20() {
		return vsbfield20;
	}
	public void setVsbfield20(String vsbfield20) {
		this.vsbfield20 = vsbfield20;
	}
	public String getField21() {
		return field21;
	}
	public void setField21(String field21) {
		this.field21 = field21;
	}
	public String getCmbfield21() {
		return cmbfield21;
	}
	public void setCmbfield21(String cmbfield21) {
		this.cmbfield21 = cmbfield21;
	}
	public String getValfield21() {
		return valfield21;
	}
	public void setValfield21(String valfield21) {
		this.valfield21 = valfield21;
	}
	public String getMndfield21() {
		return mndfield21;
	}
	public void setMndfield21(String mndfield21) {
		this.mndfield21 = mndfield21;
	}
	public String getVsbfield21() {
		return vsbfield21;
	}
	public void setVsbfield21(String vsbfield21) {
		this.vsbfield21 = vsbfield21;
	}
	
	public String getField22() {
		return field22;
	}
	public void setField22(String field22) {
		this.field22 = field22;
	}
	public String getCmbfield22() {
		return cmbfield22;
	}
	public void setCmbfield22(String cmbfield22) {
		this.cmbfield22 = cmbfield22;
	}
	public String getValfield22() {
		return valfield22;
	}
	public void setValfield22(String valfield22) {
		this.valfield22 = valfield22;
	}
	public String getMndfield22() {
		return mndfield22;
	}
	public void setMndfield22(String mndfield22) {
		this.mndfield22 = mndfield22;
	}
	public String getVsbfield22() {
		return vsbfield22;
	}
	public void setVsbfield22(String vsbfield22) {
		this.vsbfield22 = vsbfield22;
	}
	public String getField23() {
		return field23;
	}
	public void setField23(String field23) {
		this.field23 = field23;
	}
	public String getCmbfield23() {
		return cmbfield23;
	}
	public void setCmbfield23(String cmbfield23) {
		this.cmbfield23 = cmbfield23;
	}
	public String getValfield23() {
		return valfield23;
	}
	public void setValfield23(String valfield23) {
		this.valfield23 = valfield23;
	}
	public String getMndfield23() {
		return mndfield23;
	}
	public void setMndfield23(String mndfield23) {
		this.mndfield23 = mndfield23;
	}
	public String getVsbfield23() {
		return vsbfield23;
	}
	public void setVsbfield23(String vsbfield23) {
		this.vsbfield23 = vsbfield23;
	}
	
	public String getField24() {
		return field24;
	}
	public void setField24(String field24) {
		this.field24 = field24;
	}
	public String getCmbfield24() {
		return cmbfield24;
	}
	public void setCmbfield24(String cmbfield24) {
		this.cmbfield24 = cmbfield24;
	}
	public String getValfield24() {
		return valfield24;
	}
	public void setValfield24(String valfield24) {
		this.valfield24 = valfield24;
	}
	public String getMndfield24() {
		return mndfield24;
	}
	public void setMndfield24(String mndfield24) {
		this.mndfield24 = mndfield24;
	}
	public String getVsbfield24() {
		return vsbfield24;
	}
	public void setVsbfield24(String vsbfield24) {
		this.vsbfield24 = vsbfield24;
	}
	public String getField25() {
		return field25;
	}
	public void setField25(String field25) {
		this.field25 = field25;
	}
	public String getCmbfield25() {
		return cmbfield25;
	}
	public void setCmbfield25(String cmbfield25) {
		this.cmbfield25 = cmbfield25;
	}
	public String getValfield25() {
		return valfield25;
	}
	public void setValfield25(String valfield25) {
		this.valfield25 = valfield25;
	}
	public String getMndfield25() {
		return mndfield25;
	}
	public void setMndfield25(String mndfield25) {
		this.mndfield25 = mndfield25;
	}
	public String getVsbfield25() {
		return vsbfield25;
	}
	public void setVsbfield25(String vsbfield25) {
		this.vsbfield25 = vsbfield25;
	}
	
	public String getField26() {
		return field26;
	}
	public void setField26(String field26) {
		this.field26 = field26;
	}
	public String getCmbfield26() {
		return cmbfield26;
	}
	public void setCmbfield26(String cmbfield26) {
		this.cmbfield26 = cmbfield26;
	}
	public String getValfield26() {
		return valfield26;
	}
	public void setValfield26(String valfield26) {
		this.valfield26 = valfield26;
	}
	public String getMndfield26() {
		return mndfield26;
	}
	public void setMndfield26(String mndfield26) {
		this.mndfield26 = mndfield26;
	}
	public String getVsbfield26() {
		return vsbfield26;
	}
	public void setVsbfield26(String vsbfield26) {
		this.vsbfield26 = vsbfield26;
	}
	
	public String getField27() {
		return field27;
	}
	public void setField27(String field27) {
		this.field27 = field27;
	}
	public String getCmbfield27() {
		return cmbfield27;
	}
	public void setCmbfield27(String cmbfield27) {
		this.cmbfield27 = cmbfield27;
	}
	public String getValfield27() {
		return valfield27;
	}
	public void setValfield27(String valfield27) {
		this.valfield27 = valfield27;
	}
	public String getMndfield27() {
		return mndfield27;
	}
	public void setMndfield27(String mndfield27) {
		this.mndfield27 = mndfield27;
	}
	public String getVsbfield27() {
		return vsbfield27;
	}
	public void setVsbfield27(String vsbfield27) {
		this.vsbfield27 = vsbfield27;
	}
	public String getField28() {
		return field28;
	}
	public void setField28(String field28) {
		this.field28 = field28;
	}
	public String getCmbfield28() {
		return cmbfield28;
	}
	public void setCmbfield28(String cmbfield28) {
		this.cmbfield28 = cmbfield28;
	}
	public String getValfield28() {
		return valfield28;
	}
	public void setValfield28(String valfield28) {
		this.valfield28 = valfield28;
	}
	public String getMndfield28() {
		return mndfield28;
	}
	public void setMndfield28(String mndfield28) {
		this.mndfield28 = mndfield28;
	}
	public String getVsbfield28() {
		return vsbfield28;
	}
	public void setVsbfield28(String vsbfield28) {
		this.vsbfield28 = vsbfield28;
	}
	
	public String getField29() {
		return field29;
	}
	public void setField29(String field29) {
		this.field29 = field29;
	}
	public String getCmbfield29() {
		return cmbfield29;
	}
	public void setCmbfield29(String cmbfield29) {
		this.cmbfield29 = cmbfield29;
	}
	public String getValfield29() {
		return valfield29;
	}
	public void setValfield29(String valfield29) {
		this.valfield29 = valfield29;
	}
	public String getMndfield29() {
		return mndfield29;
	}
	public void setMndfield29(String mndfield29) {
		this.mndfield29 = mndfield29;
	}
	public String getVsbfield29() {
		return vsbfield29;
	}
	public void setVsbfield29(String vsbfield29) {
		this.vsbfield29 = vsbfield29;
	}
	public String getField30() {
		return field30;
	}
	public void setField30(String field30) {
		this.field30 = field30;
	}
	public String getCmbfield30() {
		return cmbfield30;
	}
	public void setCmbfield30(String cmbfield30) {
		this.cmbfield30 = cmbfield30;
	}
	public String getValfield30() {
		return valfield30;
	}
	public void setValfield30(String valfield30) {
		this.valfield30 = valfield30;
	}
	public String getMndfield30() {
		return mndfield30;
	}
	public void setMndfield30(String mndfield30) {
		this.mndfield30 = mndfield30;
	}
	public String getVsbfield30() {
		return vsbfield30;
	}
	public void setVsbfield30(String vsbfield30) {
		this.vsbfield30 = vsbfield30;
	}
	
	public String getField31() {
		return field31;
	}
	public void setField31(String field31) {
		this.field31 = field31;
	}
	public String getCmbfield31() {
		return cmbfield31;
	}
	public void setCmbfield31(String cmbfield31) {
		this.cmbfield31 = cmbfield31;
	}
	public String getValfield31() {
		return valfield31;
	}
	public void setValfield31(String valfield31) {
		this.valfield31 = valfield31;
	}
	public String getMndfield31() {
		return mndfield31;
	}
	public void setMndfield31(String mndfield31) {
		this.mndfield31 = mndfield31;
	}
	public String getVsbfield31() {
		return vsbfield31;
	}
	public void setVsbfield31(String vsbfield31) {
		this.vsbfield31 = vsbfield31;
	}
	
	public String getField32() {
		return field32;
	}
	public void setField32(String field32) {
		this.field32 = field32;
	}
	public String getCmbfield32() {
		return cmbfield32;
	}
	public void setCmbfield32(String cmbfield32) {
		this.cmbfield32 = cmbfield32;
	}
	public String getValfield32() {
		return valfield32;
	}
	public void setValfield32(String valfield32) {
		this.valfield32 = valfield32;
	}
	public String getMndfield32() {
		return mndfield32;
	}
	public void setMndfield32(String mndfield32) {
		this.mndfield32 = mndfield32;
	}
	public String getVsbfield32() {
		return vsbfield32;
	}
	public void setVsbfield32(String vsbfield32) {
		this.vsbfield32 = vsbfield32;
	}
	public String getField33() {
		return field33;
	}
	public void setField33(String field33) {
		this.field33 = field33;
	}
	public String getCmbfield33() {
		return cmbfield33;
	}
	public void setCmbfield33(String cmbfield33) {
		this.cmbfield33 = cmbfield33;
	}
	public String getValfield33() {
		return valfield33;
	}
	public void setValfield33(String valfield33) {
		this.valfield33 = valfield33;
	}
	public String getMndfield33() {
		return mndfield33;
	}
	public void setMndfield33(String mndfield33) {
		this.mndfield33 = mndfield33;
	}
	public String getVsbfield33() {
		return vsbfield33;
	}
	public void setVsbfield33(String vsbfield33) {
		this.vsbfield33 = vsbfield33;
	}
	
	public String getField34() {
		return field34;
	}
	public void setField34(String field34) {
		this.field34 = field34;
	}
	public String getCmbfield34() {
		return cmbfield34;
	}
	public void setCmbfield34(String cmbfield34) {
		this.cmbfield34 = cmbfield34;
	}
	public String getValfield34() {
		return valfield34;
	}
	public void setValfield34(String valfield34) {
		this.valfield34 = valfield34;
	}
	public String getMndfield34() {
		return mndfield34;
	}
	public void setMndfield34(String mndfield34) {
		this.mndfield34 = mndfield34;
	}
	public String getVsbfield34() {
		return vsbfield34;
	}
	public void setVsbfield34(String vsbfield34) {
		this.vsbfield34 = vsbfield34;
	}
	public String getField35() {
		return field35;
	}
	public void setField35(String field35) {
		this.field35 = field35;
	}
	public String getCmbfield35() {
		return cmbfield35;
	}
	public void setCmbfield35(String cmbfield35) {
		this.cmbfield35 = cmbfield35;
	}
	public String getValfield35() {
		return valfield35;
	}
	public void setValfield35(String valfield35) {
		this.valfield35 = valfield35;
	}
	public String getMndfield35() {
		return mndfield35;
	}
	public void setMndfield35(String mndfield35) {
		this.mndfield35 = mndfield35;
	}
	public String getVsbfield35() {
		return vsbfield35;
	}
	public void setVsbfield35(String vsbfield35) {
		this.vsbfield35 = vsbfield35;
	}
	
	private String hcmbfield1 = "";
	private String hcmbfield2 = "";
	private String hcmbfield3 = "";
	private String hcmbfield4 = "";
	private String hcmbfield5 = "";
	private String hcmbfield6 = "";
	private String hcmbfield7 = "";
	private String hcmbfield8 = "";
	private String hcmbfield9 = "";
	private String hcmbfield10 = "";
	private String hcmbfield11 = "";
	private String hcmbfield12 = "";
	private String hcmbfield13 = "";
	private String hcmbfield14 = "";
	private String hcmbfield15 = "";
	private String hcmbfield16 = "";
	private String hcmbfield17 = "";
	private String hcmbfield18 = "";
	private String hcmbfield19 = "";
	private String hcmbfield20 = "";
	private String hcmbfield21 = "";
	private String hcmbfield22 = "";
	private String hcmbfield23 = "";
	private String hcmbfield24 = "";
	private String hcmbfield25 = "";
	private String hcmbfield26 = "";
	private String hcmbfield27 = "";
	private String hcmbfield28 = "";
	private String hcmbfield29 = "";
	private String hcmbfield30 = "";
	private String hcmbfield31 = "";
	private String hcmbfield32 = "";
	private String hcmbfield33 = "";
	private String hcmbfield34 = "";
	private String hcmbfield35 = "";
	
	public String getHcmbfield1() {
		return hcmbfield1;
	}
	public void setHcmbfield1(String hcmbfield1) {
		this.hcmbfield1 = hcmbfield1;
	}
	public String getHcmbfield2() {
		return hcmbfield2;
	}
	public void setHcmbfield2(String hcmbfield2) {
		this.hcmbfield2 = hcmbfield2;
	}
	public String getHcmbfield3() {
		return hcmbfield3;
	}
	public void setHcmbfield3(String hcmbfield3) {
		this.hcmbfield3 = hcmbfield3;
	}
	public String getHcmbfield4() {
		return hcmbfield4;
	}
	public void setHcmbfield4(String hcmbfield4) {
		this.hcmbfield4 = hcmbfield4;
	}
	public String getHcmbfield5() {
		return hcmbfield5;
	}
	public void setHcmbfield5(String hcmbfield5) {
		this.hcmbfield5 = hcmbfield5;
	}
	public String getHcmbfield6() {
		return hcmbfield6;
	}
	public void setHcmbfield6(String hcmbfield6) {
		this.hcmbfield6 = hcmbfield6;
	}
	public String getHcmbfield7() {
		return hcmbfield7;
	}
	public void setHcmbfield7(String hcmbfield7) {
		this.hcmbfield7 = hcmbfield7;
	}
	public String getHcmbfield8() {
		return hcmbfield8;
	}
	public void setHcmbfield8(String hcmbfield8) {
		this.hcmbfield8 = hcmbfield8;
	}
	public String getHcmbfield9() {
		return hcmbfield9;
	}
	public void setHcmbfield9(String hcmbfield9) {
		this.hcmbfield9 = hcmbfield9;
	}
	public String getHcmbfield10() {
		return hcmbfield10;
	}
	public void setHcmbfield10(String hcmbfield10) {
		this.hcmbfield10 = hcmbfield10;
	}
	public String getHcmbfield11() {
		return hcmbfield11;
	}
	public void setHcmbfield11(String hcmbfield11) {
		this.hcmbfield11 = hcmbfield11;
	}
	public String getHcmbfield12() {
		return hcmbfield12;
	}
	public void setHcmbfield12(String hcmbfield12) {
		this.hcmbfield12 = hcmbfield12;
	}
	public String getHcmbfield13() {
		return hcmbfield13;
	}
	public void setHcmbfield13(String hcmbfield13) {
		this.hcmbfield13 = hcmbfield13;
	}
	public String getHcmbfield14() {
		return hcmbfield14;
	}
	public void setHcmbfield14(String hcmbfield14) {
		this.hcmbfield14 = hcmbfield14;
	}
	public String getHcmbfield15() {
		return hcmbfield15;
	}
	public void setHcmbfield15(String hcmbfield15) {
		this.hcmbfield15 = hcmbfield15;
	}
	public String getHcmbfield16() {
		return hcmbfield16;
	}
	public void setHcmbfield16(String hcmbfield16) {
		this.hcmbfield16 = hcmbfield16;
	}
	public String getHcmbfield17() {
		return hcmbfield17;
	}
	public void setHcmbfield17(String hcmbfield17) {
		this.hcmbfield17 = hcmbfield17;
	}
	public String getHcmbfield18() {
		return hcmbfield18;
	}
	public void setHcmbfield18(String hcmbfield18) {
		this.hcmbfield18 = hcmbfield18;
	}
	public String getHcmbfield19() {
		return hcmbfield19;
	}
	public void setHcmbfield19(String hcmbfield19) {
		this.hcmbfield19 = hcmbfield19;
	}
	public String getHcmbfield20() {
		return hcmbfield20;
	}
	public void setHcmbfield20(String hcmbfield20) {
		this.hcmbfield20 = hcmbfield20;
	}
	public String getHcmbfield21() {
		return hcmbfield21;
	}
	public void setHcmbfield21(String hcmbfield21) {
		this.hcmbfield21 = hcmbfield21;
	}
	public String getHcmbfield22() {
		return hcmbfield22;
	}
	public void setHcmbfield22(String hcmbfield22) {
		this.hcmbfield22 = hcmbfield22;
	}
	public String getHcmbfield23() {
		return hcmbfield23;
	}
	public void setHcmbfield23(String hcmbfield23) {
		this.hcmbfield23 = hcmbfield23;
	}
	public String getHcmbfield24() {
		return hcmbfield24;
	}
	public void setHcmbfield24(String hcmbfield24) {
		this.hcmbfield24 = hcmbfield24;
	}
	public String getHcmbfield25() {
		return hcmbfield25;
	}
	public void setHcmbfield25(String hcmbfield25) {
		this.hcmbfield25 = hcmbfield25;
	}
	public String getHcmbfield26() {
		return hcmbfield26;
	}
	public void setHcmbfield26(String hcmbfield26) {
		this.hcmbfield26 = hcmbfield26;
	}
	public String getHcmbfield27() {
		return hcmbfield27;
	}
	public void setHcmbfield27(String hcmbfield27) {
		this.hcmbfield27 = hcmbfield27;
	}
	public String getHcmbfield28() {
		return hcmbfield28;
	}
	public void setHcmbfield28(String hcmbfield28) {
		this.hcmbfield28 = hcmbfield28;
	}
	public String getHcmbfield29() {
		return hcmbfield29;
	}
	public void setHcmbfield29(String hcmbfield29) {
		this.hcmbfield29 = hcmbfield29;
	}
	public String getHcmbfield30() {
		return hcmbfield30;
	}
	public void setHcmbfield30(String hcmbfield30) {
		this.hcmbfield30 = hcmbfield30;
	}
	public String getHcmbfield31() {
		return hcmbfield31;
	}
	public void setHcmbfield31(String hcmbfield31) {
		this.hcmbfield31 = hcmbfield31;
	}
	public String getHcmbfield32() {
		return hcmbfield32;
	}
	public void setHcmbfield32(String hcmbfield32) {
		this.hcmbfield32 = hcmbfield32;
	}
	public String getHcmbfield33() {
		return hcmbfield33;
	}
	public void setHcmbfield33(String hcmbfield33) {
		this.hcmbfield33 = hcmbfield33;
	}
	public String getHcmbfield34() {
		return hcmbfield34;
	}
	public void setHcmbfield34(String hcmbfield34) {
		this.hcmbfield34 = hcmbfield34;
	}
	public String getHcmbfield35() {
		return hcmbfield35;
	}
	public void setHcmbfield35(String hcmbfield35) {
		this.hcmbfield35 = hcmbfield35;
	}
	
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		ActionErrors errors = super.validate(mapping,request);
		String CompId = (request.getAttribute("CompId")!=null) ? (String)request.getAttribute("CompId") : "0";
		request.setAttribute("CompId", CompId);
		
		String [] fieldname = {this.field1,this.field2,this.field3,this.field4,this.field5,
								this.field6,this.field7,this.field8,this.field9,this.field10,
								this.field11,this.field12,this.field13,this.field14,this.field15,
								this.field16,this.field17,this.field18,this.field19,this.field20,
								this.field21,this.field22,this.field23,this.field24,this.field25,
								this.field26,this.field27,this.field28,this.field29,this.field30,
								this.field31,this.field32,this.field33,this.field34,this.field35 } ;
		
		String [] fieldtype = {this.cmbfield1,this.cmbfield2,this.cmbfield3,this.cmbfield4,this.cmbfield5,
								this.cmbfield6,this.cmbfield7,this.cmbfield8,this.cmbfield9,this.cmbfield10,
								this.cmbfield11,this.cmbfield12,this.cmbfield13,this.cmbfield14,this.cmbfield15,
								this.cmbfield16,this.cmbfield17,this.cmbfield18,this.cmbfield19,this.cmbfield20,
								this.cmbfield21,this.cmbfield22,this.cmbfield23,this.cmbfield24,this.cmbfield25,
								this.cmbfield26,this.cmbfield27,this.cmbfield28,this.cmbfield29,this.cmbfield30,
								this.cmbfield31,this.cmbfield32,this.cmbfield33,this.cmbfield34,this.cmbfield35 } ;
		
		String [] fieldtval = {this.valfield1,this.valfield2,this.valfield3,this.valfield4,this.valfield5,
								this.valfield6,this.valfield7,this.valfield8,this.valfield9,this.valfield10,
								this.valfield11,this.valfield12,this.valfield13,this.valfield14,this.valfield15,
								this.valfield16,this.valfield17,this.valfield18,this.valfield19,this.valfield20,
								this.valfield21,this.valfield22,this.valfield23,this.valfield24,this.valfield25,
								this.valfield26,this.valfield27,this.valfield28,this.valfield29,this.valfield30,
								this.valfield31,this.valfield32,this.valfield33,this.valfield34,this.valfield35 } ;
		
		String [] fieldvis = {this.vsbfield1,this.vsbfield2,this.vsbfield3,this.vsbfield4,this.vsbfield5,
								this.vsbfield6,this.vsbfield7,this.vsbfield8,this.vsbfield9,this.vsbfield10,
								this.vsbfield11,this.vsbfield12,this.vsbfield13,this.vsbfield14,this.vsbfield15,
								this.vsbfield16,this.vsbfield17,this.vsbfield18,this.vsbfield19,this.vsbfield20,
								this.vsbfield21,this.vsbfield22,this.vsbfield23,this.vsbfield24,this.vsbfield25,
								this.vsbfield26,this.vsbfield27,this.vsbfield28,this.vsbfield29,this.vsbfield30,
								this.vsbfield31,this.vsbfield32,this.vsbfield33,this.vsbfield34,this.vsbfield35 } ;
		
		for(int i=0;i<35;i++)
		{
		
				if(fieldname[i].length()>0)
				{
					if(fieldtype[i].equals("Select"))
					{
						errors.add("entpCommunitcustForm",new ActionError("errors.comcustomize.type","Field Type",String.valueOf(i+1)));
					}
					else if(fieldtype[i].equals("Check Box") || fieldtype[i].equals("Combo Box") || fieldtype[i].equals("Radio Button"))
					{
						if(fieldtval[i].length() == 0)
						{
							errors.add("entpCommunitcustForm",new ActionError("errors.comcustomize.type","Field Value",String.valueOf(i+1)));
						}
					}
				}
				else if(fieldvis[i].equals("1"))
				{
					if(fieldname[i].length()==0)
					{
						errors.add("entpCommunitcustForm",new ActionError("errors.comcustomize.type","Field Name",String.valueOf(i+1)));	
					}
					else
					{
						
						if(fieldtype[i].equals("Select"))
						{
							errors.add("entpCommunitcustForm",new ActionError("errors.comcustomize.type","Field Type",String.valueOf(i+1)));
						}
						else if(fieldtype[i].equals("Check Box") || fieldtype[i].equals("Combo Box") || fieldtype[i].equals("Radio Button"))
						{
							if(fieldtval[i].length() == 0)
							{
								errors.add("entpCommunitcustForm",new ActionError("errors.comcustomize.type","Field Value",String.valueOf(i+1)));
							}
						}
					}
				}
			
		}
		
		return errors;
	}
}
