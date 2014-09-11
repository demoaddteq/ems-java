package com.mm.struts.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.resource.MasterUtility;

public class PreCountryStateCityAction  extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {

		String result="sucess";
		HttpSession session= request.getSession();
		
		DataSource dataSource = getDataSource(request,"main");
		
		MasterUtility mu = new MasterUtility();
		ArrayList countriesArr = mu.getListCountries(dataSource);
		HashMap stateMap = mu.getListState(dataSource);
		HashMap citiesMap = mu.getListCities(dataSource);
		session.setAttribute("AllCountry", countriesArr);
		session.setAttribute("AllStates", stateMap);
		session.setAttribute("AllCities", citiesMap);
		System.out.println("AllCountryAllCountryAllCountry>>>>>>>>"+countriesArr);
		return mapping.findForward(result);
	}

}
