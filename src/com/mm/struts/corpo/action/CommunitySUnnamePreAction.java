package com.mm.struts.corpo.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.EntpMaster;
import com.mm.struts.form.CommunitySUnnameForm;

public class CommunitySUnnamePreAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CommunitySUnnameForm communityUnname= new CommunitySUnnameForm();
		
		
		String communitId = "1";//(String)request.getAttribute("communitId");
		
		request.setAttribute("communitId", communitId);	
		System.out.println("Forwarded==>"+communitId);	
		
		
		Vector datavec = (Vector)request.getAttribute("datavec");
		request.setAttribute("datavec", datavec);
		////System.out.println("datavec//////CommunitySUnnamePreAction///////"+datavec);	
		////System.out.println("datavec///size///CommunitySUnnamePreAction///////"+datavec.size());	
		
		
		
		int numCompId= Integer.parseInt(communitId);
		EntpMaster entpMaster= new EntpMaster();
		
		Vector resultVec = entpMaster.getCommunityUnnamedMap( getDataSource(request, "corpo") ,numCompId);	
		request.setAttribute("resultVec", resultVec);
		for(int k=0;k<=40;k++){
			System.out.println("CampusYog1 ---"+ k +"Th Posion==>"+datavec.elementAt(k));
		}
		
		communityUnname.setCompId(communitId);
		communityUnname.setValue1(datavec.elementAt(1).toString());
		communityUnname.setValue2(datavec.elementAt(2).toString());
		communityUnname.setValue3(datavec.elementAt(3).toString());
		communityUnname.setValue4(datavec.elementAt(4).toString());
		communityUnname.setValue5(datavec.elementAt(5).toString());
		communityUnname.setValue6(datavec.elementAt(6).toString());
		communityUnname.setValue7(datavec.elementAt(7).toString());
		communityUnname.setValue8(datavec.elementAt(8).toString());
		communityUnname.setValue9(datavec.elementAt(9).toString());
		communityUnname.setValue10(datavec.elementAt(10).toString());
		communityUnname.setValue11(datavec.elementAt(11).toString());//State
		communityUnname.setValue12(datavec.elementAt(12).toString());
		communityUnname.setValue13(datavec.elementAt(13).toString());
		communityUnname.setValue14(datavec.elementAt(14).toString());
		communityUnname.setValue15(datavec.elementAt(15).toString());
		communityUnname.setValue16(datavec.elementAt(16).toString());
		communityUnname.setValue17(datavec.elementAt(17).toString());
		communityUnname.setValue18(datavec.elementAt(18).toString());
		communityUnname.setValue19(datavec.elementAt(19).toString());
		communityUnname.setValue20(datavec.elementAt(20).toString());
		communityUnname.setValue21(datavec.elementAt(21).toString());
		communityUnname.setValue23(datavec.elementAt(23).toString());
		communityUnname.setValue24(datavec.elementAt(24).toString());
		communityUnname.setValue25(datavec.elementAt(25).toString());
		communityUnname.setValue26(datavec.elementAt(26).toString());
		communityUnname.setValue26(datavec.elementAt(26).toString());
		communityUnname.setValue27(datavec.elementAt(27).toString());
		communityUnname.setValue28(datavec.elementAt(28).toString());
		communityUnname.setValue29(datavec.elementAt(29).toString());
		communityUnname.setValue30(datavec.elementAt(30).toString());
		communityUnname.setValue31(datavec.elementAt(31).toString());
		communityUnname.setValue32(datavec.elementAt(32).toString());
		communityUnname.setValue33(datavec.elementAt(33).toString());
		communityUnname.setValue34(datavec.elementAt(34).toString());
		communityUnname.setValue35(datavec.elementAt(35).toString());
		communityUnname.setValue36(datavec.elementAt(36).toString());
		communityUnname.setValue37(datavec.elementAt(37).toString());
		communityUnname.setValue38(datavec.elementAt(38).toString());
		communityUnname.setValue39(datavec.elementAt(39).toString());
		communityUnname.setValue40(datavec.elementAt(40).toString());
		communityUnname.setValue41(datavec.elementAt(41).toString());
		communityUnname.setValue42(datavec.elementAt(42).toString());
		communityUnname.setValue43(datavec.elementAt(43).toString());
		communityUnname.setValue44(datavec.elementAt(44).toString());
		communityUnname.setValue45(datavec.elementAt(45).toString());
		communityUnname.setValue46(datavec.elementAt(46).toString());
		communityUnname.setValue47(datavec.elementAt(47).toString());
		communityUnname.setValue48(datavec.elementAt(48).toString());
		communityUnname.setValue49(datavec.elementAt(49).toString());
		communityUnname.setValue50(datavec.elementAt(50).toString());
		communityUnname.setValue51(datavec.elementAt(51).toString());
		communityUnname.setValue52(datavec.elementAt(52).toString());
		communityUnname.setValue53(datavec.elementAt(53).toString());
		communityUnname.setValue54(datavec.elementAt(54).toString());
		
		/*
		 * 
		 */
		
		
		
		
		communityUnname.setCoures(datavec.elementAt(35).toString());		
		communityUnname.setMsub(datavec.elementAt(36).toString());		
		communityUnname.setAdmno(datavec.elementAt(37).toString());

		
		request.setAttribute("communitySUnnameForm", communityUnname);
		return mapping.findForward("success");
	}

}
