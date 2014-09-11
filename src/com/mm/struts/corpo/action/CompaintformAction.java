/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.corpo.action;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.Vector;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.AdvtMaster;
import com.mm.struts.corpo.form.ComplaintForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-16-2007
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="failuer" path="/complaintfrm.jsp"
 * @struts.action-forward name="success" path="/complaintfrm.jsp"
 */
public class CompaintformAction extends Action {
	/*
	 * Generated Methods
	 */

	protected ActionForm buildComplaintForm(int numCompId, int numCatId, HttpServletRequest request) throws Exception
	{
		ComplaintForm cf = new ComplaintForm();
		AdvtMaster am = new AdvtMaster();
		Vector resultVec = am.getComplaintUnnamedMap(getDataSource(request, "corpo"), numCompId, numCatId);
		
		if(resultVec.size()>= 175)
		{
//			Field 1 Values
			cf.setField1(resultVec.elementAt(0).toString().trim());
			cf.setCmbfield1(resultVec.elementAt(1).toString().trim());
			cf.setValfield1(resultVec.elementAt(2).toString().trim());
			cf.setMndfield1(resultVec.elementAt(3).toString().trim());
			cf.setVsbfield1(resultVec.elementAt(4).toString().trim());
			
//			Field 2 Values
			cf.setField2(resultVec.elementAt(5).toString().trim());
			cf.setCmbfield2(resultVec.elementAt(6).toString().trim());
			cf.setValfield2(resultVec.elementAt(7).toString().trim());
			cf.setMndfield2(resultVec.elementAt(8).toString().trim());
			cf.setVsbfield2(resultVec.elementAt(9).toString().trim());
			
//			Field 3 Values
			cf.setField3(resultVec.elementAt(10).toString().trim());
			cf.setCmbfield3(resultVec.elementAt(11).toString().trim());
			cf.setValfield3(resultVec.elementAt(12).toString().trim());
			cf.setMndfield3(resultVec.elementAt(13).toString().trim());
			cf.setVsbfield3(resultVec.elementAt(14).toString().trim());
			
//			Field 4 Values
			cf.setField4(resultVec.elementAt(15).toString().trim());
			cf.setCmbfield4(resultVec.elementAt(16).toString().trim());
			cf.setValfield4(resultVec.elementAt(17).toString().trim());
			cf.setMndfield4(resultVec.elementAt(18).toString().trim());
			cf.setVsbfield4(resultVec.elementAt(19).toString().trim());
			
//			Field 5 Values
			cf.setField5(resultVec.elementAt(20).toString().trim());
			cf.setCmbfield5(resultVec.elementAt(21).toString().trim());
			cf.setValfield5(resultVec.elementAt(22).toString().trim());
			cf.setMndfield5(resultVec.elementAt(23).toString().trim());
			cf.setVsbfield5(resultVec.elementAt(24).toString().trim());
			
//			Field 6 Values
			cf.setField6(resultVec.elementAt(25).toString().trim());
			cf.setCmbfield6(resultVec.elementAt(26).toString().trim());
			cf.setValfield6(resultVec.elementAt(27).toString().trim());
			cf.setMndfield6(resultVec.elementAt(28).toString().trim());
			cf.setVsbfield6(resultVec.elementAt(29).toString().trim());
			
//			Field 7 Values
			cf.setField7(resultVec.elementAt(30).toString().trim());
			cf.setCmbfield7(resultVec.elementAt(31).toString().trim());
			cf.setValfield7(resultVec.elementAt(32).toString().trim());
			cf.setMndfield7(resultVec.elementAt(33).toString().trim());
			cf.setVsbfield7(resultVec.elementAt(34).toString().trim());
			
//			Field 8 Values
			cf.setField8(resultVec.elementAt(35).toString().trim());
			cf.setCmbfield8(resultVec.elementAt(36).toString().trim());
			cf.setValfield8(resultVec.elementAt(37).toString().trim());
			cf.setMndfield8(resultVec.elementAt(38).toString().trim());
			cf.setVsbfield8(resultVec.elementAt(39).toString().trim());
			
//			Field 9 Values
			cf.setField9(resultVec.elementAt(40).toString().trim());
			cf.setCmbfield9(resultVec.elementAt(41).toString().trim());
			cf.setValfield9(resultVec.elementAt(42).toString().trim());
			cf.setMndfield9(resultVec.elementAt(43).toString().trim());
			cf.setVsbfield9(resultVec.elementAt(44).toString().trim());
			
//			Field 10 Values
			cf.setField10(resultVec.elementAt(45).toString().trim());
			cf.setCmbfield10(resultVec.elementAt(46).toString().trim());
			cf.setValfield10(resultVec.elementAt(47).toString().trim());
			cf.setMndfield10(resultVec.elementAt(48).toString().trim());
			cf.setVsbfield10(resultVec.elementAt(49).toString().trim());
			
//			Field 11 Values
			cf.setField11(resultVec.elementAt(50).toString().trim());
			cf.setCmbfield11(resultVec.elementAt(51).toString().trim());
			cf.setValfield11(resultVec.elementAt(52).toString().trim());
			cf.setMndfield11(resultVec.elementAt(53).toString().trim());
			cf.setVsbfield11(resultVec.elementAt(54).toString().trim());
			
//			Field 12 Values
			cf.setField12(resultVec.elementAt(55).toString().trim());
			cf.setCmbfield12(resultVec.elementAt(56).toString().trim());
			cf.setValfield12(resultVec.elementAt(57).toString().trim());
			cf.setMndfield12(resultVec.elementAt(58).toString().trim());
			cf.setVsbfield12(resultVec.elementAt(59).toString().trim());
			
//			Field 13 Values
			cf.setField13(resultVec.elementAt(60).toString().trim());
			cf.setCmbfield13(resultVec.elementAt(61).toString().trim());
			cf.setValfield13(resultVec.elementAt(62).toString().trim());
			cf.setMndfield13(resultVec.elementAt(63).toString().trim());
			cf.setVsbfield13(resultVec.elementAt(64).toString().trim());
			
//			Field 14 Values
			cf.setField14(resultVec.elementAt(65).toString().trim());
			cf.setCmbfield14(resultVec.elementAt(66).toString().trim());
			cf.setValfield14(resultVec.elementAt(67).toString().trim());
			cf.setMndfield14(resultVec.elementAt(68).toString().trim());
			cf.setVsbfield14(resultVec.elementAt(69).toString().trim());
			
//			Field 15 Values
			cf.setField15(resultVec.elementAt(70).toString().trim());
			cf.setCmbfield15(resultVec.elementAt(71).toString().trim());
			cf.setValfield15(resultVec.elementAt(72).toString().trim());
			cf.setMndfield15(resultVec.elementAt(73).toString().trim());
			cf.setVsbfield15(resultVec.elementAt(74).toString().trim());
			
//			Field 16 Values
			cf.setField16(resultVec.elementAt(75).toString().trim());
			cf.setCmbfield16(resultVec.elementAt(76).toString().trim());
			cf.setValfield16(resultVec.elementAt(77).toString().trim());
			cf.setMndfield16(resultVec.elementAt(78).toString().trim());
			cf.setVsbfield16(resultVec.elementAt(79).toString().trim());
			
//			Field 17 Values
			cf.setField17(resultVec.elementAt(80).toString().trim());
			cf.setCmbfield17(resultVec.elementAt(81).toString().trim());
			cf.setValfield17(resultVec.elementAt(82).toString().trim());
			cf.setMndfield17(resultVec.elementAt(83).toString().trim());
			cf.setVsbfield17(resultVec.elementAt(84).toString().trim());
			
//			Field 18 Values
			cf.setField18(resultVec.elementAt(85).toString().trim());
			cf.setCmbfield18(resultVec.elementAt(86).toString().trim());
			cf.setValfield18(resultVec.elementAt(87).toString().trim());
			cf.setMndfield18(resultVec.elementAt(88).toString().trim());
			cf.setVsbfield18(resultVec.elementAt(89).toString().trim());
			
//			Field 19 Values
			cf.setField19(resultVec.elementAt(90).toString().trim());
			cf.setCmbfield19(resultVec.elementAt(91).toString().trim());
			cf.setValfield19(resultVec.elementAt(92).toString().trim());
			cf.setMndfield19(resultVec.elementAt(93).toString().trim());
			cf.setVsbfield19(resultVec.elementAt(94).toString().trim());
			
//			Field 20 Values
			cf.setField20(resultVec.elementAt(95).toString().trim());
			cf.setCmbfield20(resultVec.elementAt(96).toString().trim());
			cf.setValfield20(resultVec.elementAt(97).toString().trim());
			cf.setMndfield20(resultVec.elementAt(98).toString().trim());
			cf.setVsbfield20(resultVec.elementAt(99).toString().trim());
			
//			Field 21 Values
			cf.setField21(resultVec.elementAt(100).toString().trim());
			cf.setCmbfield21(resultVec.elementAt(101).toString().trim());
			cf.setValfield21(resultVec.elementAt(102).toString().trim());
			cf.setMndfield21(resultVec.elementAt(103).toString().trim());
			cf.setVsbfield21(resultVec.elementAt(104).toString().trim());
			
//			Field 22 Values
			cf.setField22(resultVec.elementAt(105).toString().trim());
			cf.setCmbfield22(resultVec.elementAt(106).toString().trim());
			cf.setValfield22(resultVec.elementAt(107).toString().trim());
			cf.setMndfield22(resultVec.elementAt(108).toString().trim());
			cf.setVsbfield22(resultVec.elementAt(109).toString().trim());
			
//			Field 23 Values
			cf.setField23(resultVec.elementAt(110).toString().trim());
			cf.setCmbfield23(resultVec.elementAt(111).toString().trim());
			cf.setValfield23(resultVec.elementAt(112).toString().trim());
			cf.setMndfield23(resultVec.elementAt(113).toString().trim());
			cf.setVsbfield23(resultVec.elementAt(114).toString().trim());
			
//			Field 24 Values
			cf.setField24(resultVec.elementAt(115).toString().trim());
			cf.setCmbfield24(resultVec.elementAt(116).toString().trim());
			cf.setValfield24(resultVec.elementAt(117).toString().trim());
			cf.setMndfield24(resultVec.elementAt(118).toString().trim());
			cf.setVsbfield24(resultVec.elementAt(119).toString().trim());
			
//			Field 25 Values
			cf.setField25(resultVec.elementAt(120).toString().trim());
			cf.setCmbfield25(resultVec.elementAt(121).toString().trim());
			cf.setValfield25(resultVec.elementAt(122).toString().trim());
			cf.setMndfield25(resultVec.elementAt(123).toString().trim());
			cf.setVsbfield25(resultVec.elementAt(124).toString().trim());
			
//			Field 26 Values
			cf.setField26(resultVec.elementAt(125).toString().trim());
			cf.setCmbfield26(resultVec.elementAt(126).toString().trim());
			cf.setValfield26(resultVec.elementAt(127).toString().trim());
			cf.setMndfield26(resultVec.elementAt(128).toString().trim());
			cf.setVsbfield26(resultVec.elementAt(129).toString().trim());
			
//			Field 27 Values
			cf.setField27(resultVec.elementAt(130).toString().trim());
			cf.setCmbfield27(resultVec.elementAt(131).toString().trim());
			cf.setValfield27(resultVec.elementAt(132).toString().trim());
			cf.setMndfield27(resultVec.elementAt(133).toString().trim());
			cf.setVsbfield27(resultVec.elementAt(134).toString().trim());
			
//			Field 28 Values
			cf.setField28(resultVec.elementAt(135).toString().trim());
			cf.setCmbfield28(resultVec.elementAt(136).toString().trim());
			cf.setValfield28(resultVec.elementAt(137).toString().trim());
			cf.setMndfield28(resultVec.elementAt(138).toString().trim());
			cf.setVsbfield28(resultVec.elementAt(139).toString().trim());
			
//			Field 29 Values
			cf.setField29(resultVec.elementAt(140).toString().trim());
			cf.setCmbfield29(resultVec.elementAt(141).toString().trim());
			cf.setValfield29(resultVec.elementAt(142).toString().trim());
			cf.setMndfield29(resultVec.elementAt(143).toString().trim());
			cf.setVsbfield29(resultVec.elementAt(144).toString().trim());
			
//			Field 30 Values
			cf.setField30(resultVec.elementAt(145).toString().trim());
			cf.setCmbfield30(resultVec.elementAt(146).toString().trim());
			cf.setValfield30(resultVec.elementAt(147).toString().trim());
			cf.setMndfield30(resultVec.elementAt(148).toString().trim());
			cf.setVsbfield30(resultVec.elementAt(149).toString().trim());
			
//			Field 31 Values
			cf.setField31(resultVec.elementAt(150).toString().trim());
			cf.setCmbfield31(resultVec.elementAt(151).toString().trim());
			cf.setValfield31(resultVec.elementAt(152).toString().trim());
			cf.setMndfield31(resultVec.elementAt(153).toString().trim());
			cf.setVsbfield31(resultVec.elementAt(154).toString().trim());
			
//			Field 32 Values
			cf.setField32(resultVec.elementAt(155).toString().trim());
			cf.setCmbfield32(resultVec.elementAt(156).toString().trim());
			cf.setValfield32(resultVec.elementAt(157).toString().trim());
			cf.setMndfield32(resultVec.elementAt(158).toString().trim());
			cf.setVsbfield32(resultVec.elementAt(159).toString().trim());
			
//			Field 33 Values
			cf.setField33(resultVec.elementAt(160).toString().trim());
			cf.setCmbfield33(resultVec.elementAt(161).toString().trim());
			cf.setValfield33(resultVec.elementAt(162).toString().trim());
			cf.setMndfield33(resultVec.elementAt(163).toString().trim());
			cf.setVsbfield33(resultVec.elementAt(164).toString().trim());
			
//			Field 34 Values
			cf.setField34(resultVec.elementAt(165).toString().trim());
			cf.setCmbfield34(resultVec.elementAt(166).toString().trim());
			cf.setValfield34(resultVec.elementAt(167).toString().trim());
			cf.setMndfield34(resultVec.elementAt(168).toString().trim());
			cf.setVsbfield34(resultVec.elementAt(169).toString().trim());
			
//			Field 35 Values
			cf.setField35(resultVec.elementAt(170).toString().trim());
			cf.setCmbfield35(resultVec.elementAt(171).toString().trim());
			cf.setValfield35(resultVec.elementAt(172).toString().trim());
			cf.setMndfield35(resultVec.elementAt(173).toString().trim());
			cf.setVsbfield35(resultVec.elementAt(174).toString().trim());
		}
		else
		{
//			Category
			//cf.setCmbcat("1");
//			Field 1 Values
			cf.setField1("");
			cf.setCmbfield1("Combo Box");
			cf.setValfield1("");
			cf.setMndfield1("");
			cf.setVsbfield1("");
			
//			Field 2 Values
			cf.setField2("");
			cf.setCmbfield2("Combo Box");
			cf.setValfield2("");
			cf.setMndfield2("");
			cf.setVsbfield2("");
			
//			Field 3 Values
			cf.setField3("");
			cf.setCmbfield3("Combo Box");
			cf.setValfield3("");
			cf.setMndfield3("");
			cf.setVsbfield3("");
			
//			Field 4 Values
			cf.setField4("");
			cf.setCmbfield4("Combo Box");
			cf.setValfield4("");
			cf.setMndfield4("");
			cf.setVsbfield4("");
			
//			Field 5 Values
			cf.setField5("");
			cf.setCmbfield5("Combo Box");
			cf.setValfield5("");
			cf.setMndfield5("");
			cf.setVsbfield5("");
			
//			Field 6 Values
			cf.setField6("");
			cf.setCmbfield6("Combo Box");
			cf.setValfield6("");
			cf.setMndfield6("");
			cf.setVsbfield6("");
			
//			Field 7 Values
			cf.setField7("");
			cf.setCmbfield7("Combo Box");
			cf.setValfield7("");
			cf.setMndfield7("");
			cf.setVsbfield7("");
			
//			Field 8 Values
			cf.setField8("");
			cf.setCmbfield8("Combo Box");
			cf.setValfield8("");
			cf.setMndfield8("");
			cf.setVsbfield8("");
			
//			Field 9 Values
			cf.setField9("");
			cf.setCmbfield9("Combo Box");
			cf.setValfield9("");
			cf.setMndfield9("");
			cf.setVsbfield9("");
			
//			Field 10 Values
			cf.setField10("");
			cf.setCmbfield10("Combo Box");
			cf.setValfield10("");
			cf.setMndfield10("");
			cf.setVsbfield10("");
			
//			Field 11 Values
			cf.setField11("");
			cf.setCmbfield11("Text Box");
			cf.setValfield11("");
			cf.setMndfield11("");
			cf.setVsbfield11("");
			
//			Field 12 Values
			cf.setField12("");
			cf.setCmbfield12("Text Box");
			cf.setValfield12("");
			cf.setMndfield12("");
			cf.setVsbfield12("");
			
//			Field 13 Values
			cf.setField13("");
			cf.setCmbfield13("Text Box");
			cf.setValfield13("");
			cf.setMndfield13("");
			cf.setVsbfield13("");
			
//			Field 14 Values
			cf.setField14("");
			cf.setCmbfield14("Text Box");
			cf.setValfield14("");
			cf.setMndfield14("");
			cf.setVsbfield14("");
			
//			Field 15 Values
			cf.setField15("");
			cf.setCmbfield15("Text Box");
			cf.setValfield15("");
			cf.setMndfield15("");
			cf.setVsbfield15("");
			
//			Field 16 Values
			cf.setField16("");
			cf.setCmbfield16("Text Box");
			cf.setValfield16("");
			cf.setMndfield16("");
			cf.setVsbfield16("");
			
//			Field 17 Values
			cf.setField17("");
			cf.setCmbfield17("Text Box");
			cf.setValfield17("");
			cf.setMndfield17("");
			cf.setVsbfield17("");
			
//			Field 18 Values
			cf.setField18("");
			cf.setCmbfield18("Text Box");
			cf.setValfield18("");
			cf.setMndfield18("");
			cf.setVsbfield18("");
			
//			Field 19 Values
			cf.setField19("");
			cf.setCmbfield19("Text Box");
			cf.setValfield19("");
			cf.setMndfield19("");
			cf.setVsbfield19("");
			
//			Field 20 Values
			cf.setField20("");
			cf.setCmbfield20("Text Box");
			cf.setValfield20("");
			cf.setMndfield20("");
			cf.setVsbfield20("");
			
//			Field 21 Values
			cf.setField21("");
			cf.setCmbfield21("Text Box");
			cf.setValfield21("");
			cf.setMndfield21("");
			cf.setVsbfield21("");
			
//			Field 2 Values
			cf.setField22("");
			cf.setCmbfield22("Text Box");
			cf.setValfield22("");
			cf.setMndfield22("");
			cf.setVsbfield22("");
			
//			Field 23 Values
			cf.setField23("");
			cf.setCmbfield23("Text Box");
			cf.setValfield23("");
			cf.setMndfield23("");
			cf.setVsbfield23("");
			
//			Field 24 Values
			cf.setField24("");
			cf.setCmbfield24("Text Box");
			cf.setValfield24("");
			cf.setMndfield24("");
			cf.setVsbfield24("");
			
//			Field 25 Values
			cf.setField25("");
			cf.setCmbfield25("Text Box");
			cf.setValfield25("");
			cf.setMndfield25("");
			cf.setVsbfield25("");
			
//			Field 26 Values
			cf.setField26("");
			cf.setCmbfield26("Text Box");
			cf.setValfield26("");
			cf.setMndfield26("");
			cf.setVsbfield26("");
			
//			Field 27 Values
			cf.setField27("");
			cf.setCmbfield27("Text Box");
			cf.setValfield27("");
			cf.setMndfield27("");
			cf.setVsbfield27("");
			
//			Field 28 Values
			cf.setField28("");
			cf.setCmbfield28("Text Box");
			cf.setValfield28("");
			cf.setMndfield28("");
			cf.setVsbfield28("");
			
//			Field 29 Values
			cf.setField29("");
			cf.setCmbfield29("Text Box");
			cf.setValfield29("");
			cf.setMndfield29("");
			cf.setVsbfield29("");
			
//			Field 30 Values
			cf.setField30("");
			cf.setCmbfield30("Text Box");
			cf.setValfield30("");
			cf.setMndfield30("");
			cf.setVsbfield30("");
			
//			Field 31 Values
			cf.setField31("");
			cf.setCmbfield31("Text Box");
			cf.setValfield31("");
			cf.setMndfield31("");
			cf.setVsbfield31("");
			
//			Field 32 Values
			cf.setField32("");
			cf.setCmbfield32("Text Box");
			cf.setValfield32("");
			cf.setMndfield32("");
			cf.setVsbfield32("");
			
//			Field 33 Values
			cf.setField33("");
			cf.setCmbfield33("Text Box");
			cf.setValfield33("");
			cf.setMndfield33("");
			cf.setVsbfield33("");
			
//			Field 34 Values
			cf.setField34("");
			cf.setCmbfield34("Text Box");
			cf.setValfield34("");
			cf.setMndfield34("");
			cf.setVsbfield34("");
			
//			Field 35 Values
			cf.setField35("");
			cf.setCmbfield35("Text Box");
			cf.setValfield35("");
			cf.setMndfield35("");
			cf.setVsbfield35("");
		}
		return cf;
	}
	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServerException 
	{
		String target = new String("success");

	    try 
	    {
	    	HttpSession session = request.getSession();
	    	int numCompId = (session.getAttribute("compId") !=null) ? Integer.parseInt(session.getAttribute("compId").toString().trim()): 0;
	    	int numCatId = 0;
	    	
	    	if(request.getParameter("catid")!=null)
	    	{
	    		numCatId = Integer.parseInt(request.getParameter("catid").trim());
	    	}
	    	else
	    	{
	    		numCatId = (session.getAttribute("catid")!=null) ? Integer.parseInt(session.getAttribute("catid").toString().trim()): 0;
	    	}
	    	session.setAttribute("catid", String.valueOf(numCatId));
	    	
	    	form = (ComplaintForm)buildComplaintForm(numCompId, numCatId, request);
	    	
	    	if ("request".equals(mapping.getScope()) ) 
	    	{
	    		request.setAttribute("complaintForm", form);
	    	}
	    	else 
	    	{	    		
	    		session.setAttribute("complaintForm", form);
	    	}
	    }
	    catch ( Exception e ) 
	    {
	    	//System.err.println("Setting target to error");
	    	//System.err.println("---->" + e.getMessage() + "<----");
	    	target = new String("error");
	    	ActionErrors errors = new ActionErrors();
	    	errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.database.error", e.getMessage()));
	    	// Report any errors
	    	if ( !errors.isEmpty() ) 
	    	{
	    		saveErrors(request, errors);
	    	}
	    }
	    // Forward to the appropriate View
	    return (mapping.findForward(target));
	}
}