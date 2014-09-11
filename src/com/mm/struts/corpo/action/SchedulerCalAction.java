/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.mm.struts.corpo.action;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.AdvtMaster;

/** 
 * MyEclipse Struts
 * Creation date: 10-01-2009
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class SchedulerCalAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		int numMonth = (request.getParameter("usrMonth")!=null) ? Integer.parseInt(request.getParameter("usrMonth").trim()) : 0;
		int numYear = (request.getParameter("usrYear")!=null) ? Integer.parseInt(request.getParameter("usrYear").trim()) : 2009;
		String strView = (request.getParameter("calView")!=null) ? request.getParameter("calView").trim() : "Month";
		System.out.println("numMonth "+numMonth);
		System.out.println("numYear "+numYear);
		//int numYear = 2009;
		HttpSession session = request.getSession();
		int numFid = (session.getAttribute("fId")!=null) ? Integer.parseInt((String)session.getAttribute("fId")) : 0;
		System.out.println("numFid  "+numFid);
		AdvtMaster am = new AdvtMaster();
		Vector resultVec = am.getShiftList(getDataSource(request, "corpo"), numFid);
		System.out.println("resultVec  "+resultVec);
		String strResult ="";
		if(strView.equalsIgnoreCase("Month"))
		{
			strResult = getCalanderMonthView(numMonth, numYear, numFid, resultVec, request);
		}
		else if(strView.equalsIgnoreCase("Week"))
		{
			int numWeek = (request.getParameter("usrWeek")!=null) ? Integer.parseInt(request.getParameter("usrWeek").trim()) : 2;
			strResult= getCalanderWeekView(numMonth, numYear, numWeek, numFid, resultVec, request);
		}
		else
		{
			int numDay = (request.getParameter("usrDay")!=null) ? Integer.parseInt(request.getParameter("usrDay").trim()) : 1;
			strResult= getCalanderDayView(numMonth, numYear, numDay, numFid, resultVec, request);
		}
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strResult);
	    out.flush();
		return null;
	}
	
	/**
	 * 
	 * @param numMonth
	 * @param numYear
	 * @return
	 */
	private String getCalanderMonthView(int numMonth, int numYear, int numFid, Vector resultVec, HttpServletRequest request)
	{
		System.out.println("numMonth1 "+numMonth);
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, numYear);
        //calendar.set(Calendar.DAY_OF_YEAR, 181);
        calendar.set(Calendar.MONTH, numMonth-1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
       // System.out.println("\nThe date of Calendar is: " +calendar.getTime());
        //int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);  
        //System.out.println("The day of month: " +dayOfMonth);  
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        ///System.out.println("The day of week: " + dayOfWeek);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
       // System.out.println("Number of Days: " + days);
		String strValue="<div id=\"blanket\" style=\"display:none;\"></div><table class=\"CalendarTbl\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">";
		strValue += "<thead>";
		strValue += "<tr>";
		strValue += "<th valign=\"middle\"></th>";
		strValue += "<th valign=\"middle\">Sunday</th>";
		strValue += "<th valign=\"middle\">Monday</th>";
		strValue += "<th valign=\"middle\">Tuesday</th>";
		strValue += "<th valign=\"middle\">Wednesday</th>";
		strValue += "<th valign=\"middle\">Thursday</th>";
		strValue += "<th valign=\"middle\">Friday</th>";
		strValue += "<th valign=\"middle\">Saturday</th>";
		strValue += "<th valign=\"middle\"></th>";
		strValue += "</tr>";
		strValue += "</thead>";
		strValue += "<tbody>";
		int numCount=1;
		int numParent = 5;
        if(dayOfWeek>=6 && days==31)
        {
        	numParent = 6;
        }
        if(dayOfWeek>=7 && days==30)
        {
        	numParent = 6;
        }
        int numLastRow = numParent-1;
        AdvtMaster am1 = new AdvtMaster();
        for(int i=0; i<numParent; i++)
        {
        	strValue += "<tr>";
        	strValue += "<td>&nbsp;</td>";
        	if(i==0)
        	{
        		for(int j=1; j<=7; j++)
        		{
        			if(dayOfWeek<=j)
        			{
        				strValue += "<td width=\"14%\"><div class=\"White\">"+numCount+"</div>";
        				if(resultVec.size()>0)
        				{
        					strValue += "<div style=\" margin:2px; -moz-border-radius:5px; background-color:#FAE25F; border: 1px solid #D2B92D; position:relative;  height:210px; float:left;\">";
	        				for(int k=0; k<resultVec.size(); k++)
	        				{
	        					Vector tempVec = (Vector)resultVec.get(k);
	        					int numShiftId = Integer.parseInt(tempVec.get(0).toString().trim());
	        					String strSiftName = tempVec.get(1).toString().trim();
	        					String strStartTime = tempVec.get(2).toString().trim();
	        					String strEndTime = tempVec.get(3).toString().trim();
	        					String strMonth = (String.valueOf(numMonth).length()<=1) ? "0"+numMonth : ""+numMonth;
	        					String strDay = (String.valueOf(numCount).length()<=1) ? "0"+numCount : ""+numCount;
	        					String strDate = strDay+"/"+strMonth+"/"+numYear;
	        					System.out.println("Shift Date "+strDate);
	        					Vector scheduleVec = am1.getShiftSchedule(getDataSource(request, "corpo"), numFid, numShiftId, strDate);
	        					strValue += "<p class=\"frstDate\" style=\" background-color:#FDF2B7;  padding:4px 0px 2px 0px;  margin:4px 0px 0px 0px; padding:2px;\"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">"+strSiftName+"("+strStartTime+" - "+strEndTime+")</span></a></p>";
	        					
	        					if(scheduleVec.size()>0)
	        					{
	        						strValue += "<p class=\"frstDate\" style=\" background-color:#FDF2B7;  padding:4px 0px 2px 0px;  margin:0px 0px 4px 0px;\"><a href=\"#\" onclick=\"popup('popUpDiv"+numCount+""+k+"')\">View Staff</a></p>";
	        						strValue += "<div id=\"popUpDiv"+numCount+""+k+"\" style=\"display:none; position:relative;	background-color:#fff; width:200px;	 border:2px solid #0066FF; padding:10px; z-index: 9002;\">";
	        						strValue += "<div style=\"height:6px; float:right\"><img src=\"../images/close-1.gif\" onclick=\"popup('popUpDiv"+numCount+""+k+"')\"></div>";
		        					for(int l=0; l<scheduleVec.size(); l++)
		        					{
		        						Vector tempVec1 = (Vector)scheduleVec.get(l);
		        						//int numSheduleId = Integer.parseInt(tempVec1.get(0).toString().trim()); 
		        						//String strScheduleDate = tempVec1.get(1).toString().trim();
		        						//int numStaffId = Integer.parseInt(tempVec1.get(2).toString().trim());
		        						String strStaffName = tempVec1.get(3).toString().trim();
		        						strValue += "<p class=\"frstDate\" style=\" background-color:#FDF2B7;  padding:4px 0px 2px 0px;  margin:0px 0px 4px 0px;\"><a href=\"#\"><span>"+strStaffName+"</span></a></p>";
		        					}
		        					strValue += "</div>";
	        					}
	        					else
	        					{
	        						strValue += "<p class=\"frstDate\" style=\" background-color:#FCEEA1; padding:2px 0px 4px 0px;  margin:0px 0px 4px 0px; \"><a href=\"#\"><span>No Staff</span></a></p>";
	        					}
	        					
	        				}
	        				strValue += "</div>";
        				}
        				else
        				{
        					strValue += "<p class=\"frstDate\" style=\" background-color:#FCEEA1; padding:2px 0px 4px 0px;  margin:4px 0px 0px 0px; \"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">No Shift</span></a></p>";
        				}
        				strValue +="</td>";
    	        		numCount++;
        			}
        			else
        			{
        				strValue += "<td width=\"14%\"><div>&nbsp;</div></td>";
        			}
        		}
        	}
        	else if(i==numLastRow)
        	{
        		for(int j=1; j<=7; j++)
        		{
        			if(numCount<=days)
        			{
        				strValue += "<td width=\"14%\"><div class=\"White\">"+numCount+"</div>";
        				if(resultVec.size()>0)
        				{
        					strValue += "<div style=\" margin:2px; -moz-border-radius:5px; background-color:#FAE25F; border: 1px solid #D2B92D; position:relative;  height:210px; float:left;\">";
	        				for(int k=0; k<resultVec.size(); k++)
	        				{
	        					Vector tempVec = (Vector)resultVec.get(k);
	        					int numShiftId = Integer.parseInt(tempVec.get(0).toString().trim());
	        					String strSiftName = tempVec.get(1).toString().trim();
	        					String strStartTime = tempVec.get(2).toString().trim();
	        					String strEndTime = tempVec.get(3).toString().trim();
	        					String strMonth = (String.valueOf(numMonth).length()<=1) ? "0"+numMonth : ""+numMonth;
	        					String strDay = (String.valueOf(numCount).length()<=1) ? "0"+numCount : ""+numCount;
	        					String strDate = strDay+"/"+strMonth+"/"+numYear;
	        					Vector scheduleVec = am1.getShiftSchedule(getDataSource(request, "corpo"), numFid, numShiftId, strDate);
	        					strValue += "<p class=\"frstDate\" style=\" background-color:#FDF2B7;  padding:4px 0px 2px 0px;  margin:4px 0px 0px 0px; padding:2px;\"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">"+strSiftName+"("+strStartTime+" - "+strEndTime+")</span></a></p>";
	        					
	        					if(scheduleVec.size()>0)
	        					{
	        						strValue += "<p class=\"frstDate\" style=\" background-color:#FDF2B7;  padding:4px 0px 2px 0px;  margin:0px 0px 4px 0px;\"><a href=\"#\" onclick=\"popup('popUpDiv"+numCount+""+k+"')\">View Staff</a></p>";
	        						strValue += "<div id=\"popUpDiv"+numCount+""+k+"\" style=\"display:none; position:relative;	background-color:#fff; width:200px;	 border:2px solid #0066FF; padding:10px; z-index: 9002;\">";
	        						strValue += "<div style=\"height:6px; float:right\"><img src=\"../images/close-1.gif\" onclick=\"popup('popUpDiv"+numCount+""+k+"')\"></div>";
		        					for(int l=0; l<scheduleVec.size(); l++)
		        					{
		        						Vector tempVec1 = (Vector)scheduleVec.get(l);
		        						//int numSheduleId = Integer.parseInt(tempVec1.get(0).toString().trim()); 
		        						//String strScheduleDate = tempVec1.get(1).toString().trim();
		        						//int numStaffId = Integer.parseInt(tempVec1.get(2).toString().trim());
		        						String strStaffName = tempVec1.get(3).toString().trim();
		        						strValue += "<p class=\"frstDate\"  style=\" background-color:#FDF2B7;  padding:4px 0px 2px 0px;  margin:0px 0px 4px 0px;\"><a href=\"#\"><span>"+strStaffName+"</span></a></p>";
		        					}
		        					strValue += "</div>";
	        					}
	        					else
	        					{
	        						strValue += "<p class=\"frstDate\"  style=\" background-color:#FDF2B7;  padding:4px 0px 2px 0px;  margin:0px 0px 4px 0px;\"><a href=\"#\"><span>No Staff</span></a></p>";
	        					}
	        					
	        				}
	        				strValue += "</div>";
        				}
        				else
        				{
        					strValue += "<p class=\"frstDate\" style=\" background-color:#FCEEA1; padding:2px 0px 4px 0px;  margin:4px 0px 0px 0px; \"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">No Shift</span></a></p>";
        				}
        				strValue +="</td>";
    	        		numCount++;
        			}
        			else
        			{
        				strValue += "<td width=\"14%\"><div>&nbsp;</div></td>";
        			}
        		}
        	}
        	else
        	{
	        	for(int j=1; j<=7; j++)
	        	{
	        		strValue += "<td width=\"14%\"><div class=\"White\">"+numCount+"</div>";
	        		if(resultVec.size()>0)
    				{
	        			strValue += "<div style=\" margin:2px; -moz-border-radius:5px; background-color:#FAE25F; border: 1px solid #D2B92D; position:relative;  height:210px; float:left;\">";
        				for(int k=0; k<resultVec.size(); k++)
        				{
        					Vector tempVec = (Vector)resultVec.get(k);
        					int numShiftId = Integer.parseInt(tempVec.get(0).toString().trim());
        					String strSiftName = tempVec.get(1).toString().trim();
        					String strStartTime = tempVec.get(2).toString().trim();
        					String strEndTime = tempVec.get(3).toString().trim();
        					String strMonth = (String.valueOf(numMonth).length()<=1) ? "0"+numMonth : ""+numMonth;
        					String strDay = (String.valueOf(numCount).length()<=1) ? "0"+numCount : ""+numCount;
        					String strDate = strDay+"/"+strMonth+"/"+numYear;
        					Vector scheduleVec = am1.getShiftSchedule(getDataSource(request, "corpo"), numFid, numShiftId, strDate);
        					strValue += "<p class=\"frstDate\" style=\" background-color:#FDF2B7;  padding:4px 0px 2px 0px;  margin:4px 0px 0px 0px; padding:2px;\"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">"+strSiftName+"("+strStartTime+" - "+strEndTime+")</span></a></p>";
        					if(scheduleVec.size()>0)
        					{
        						strValue += "<p class=\"frstDate\" style=\" background-color:#FDF2B7;  padding:4px 0px 2px 0px;  margin:0px 0px 4px 0px;\"><a href=\"#\" onclick=\"popup('popUpDiv"+numCount+""+k+"')\">View Staff</a></p>";
        						strValue += "<div id=\"popUpDiv"+numCount+""+k+"\" style=\"display:none; position:relative;	background-color:#fff; width:200px;	 border:2px solid #0066FF; padding:10px; z-index: 9002;\">";
        						strValue += "<div style=\"height:6px; float:right\"><img src=\"../images/close-1.gif\" onclick=\"popup('popUpDiv"+numCount+""+k+"')\"></div>";
	        					for(int l=0; l<scheduleVec.size(); l++)
	        					{
	        						Vector tempVec1 = (Vector)scheduleVec.get(l);
	        						//int numSheduleId = Integer.parseInt(tempVec1.get(0).toString().trim()); 
	        						//String strScheduleDate = tempVec1.get(1).toString().trim();
	        						//int numStaffId = Integer.parseInt(tempVec1.get(2).toString().trim());
	        						String strStaffName = tempVec1.get(3).toString().trim();
	        						strValue += "<p class=\"frstDate\"  style=\" background-color:#FDF2B7;  padding:4px 0px 2px 0px;  margin:0px 0px 4px 0px;\"><a href=\"#\"><span>"+strStaffName+"</span></a></p>";
	        					}
	        					strValue += "</div>";
        					}
        					else
        					{
        						strValue += "<p class=\"frstDate\"  style=\" background-color:#FDF2B7;  padding:4px 0px 2px 0px;  margin:0px 0px 4px 0px;\"><a href=\"#\"><span>No Staff</span></a></p>";
        					}
        					
        				}
        				strValue += "</div>";
    				}
    				else
    				{
    					strValue += "<p class=\"frstDate\" style=\" background-color:#FCEEA1; padding:2px 0px 4px 0px;  margin:4px 0px 0px 0px; \"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">No Shift</span></a></p>";
    				}
	        		strValue +="</td>";
	        		numCount++;
	        	}
        	}
        	strValue += "<td>&nbsp;</td>";
        	strValue += "</tr>";
        }
				
		strValue += "</tbody>";
		strValue += "</table>";
		return strValue;
	}
	
	/**
	 * 
	 * @param numMonth
	 * @param numYear
	 * @return
	 */
	private String getCalanderWeekView(int numMonth, int numYear, int numWeek, int numFid, Vector resultVec, HttpServletRequest request)
	{
		System.out.println("numMonth1 "+numMonth);
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, numYear);
        //calendar.set(Calendar.DAY_OF_YEAR, 181);
        calendar.set(Calendar.MONTH, numMonth-1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
       // System.out.println("\nThe date of Calendar is: " +calendar.getTime());
        //int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);  
        //System.out.println("The day of month: " +dayOfMonth);  
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        ///System.out.println("The day of week: " + dayOfWeek);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
       // System.out.println("Number of Days: " + days);
		String strValue="<table class=\"CalendarTbl\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">";
		strValue += "<thead>";
		strValue += "<tr>";
		strValue += "<th valign=\"middle\"></th>";
		strValue += "<th valign=\"middle\">Sunday</th>";
		strValue += "<th valign=\"middle\">Monday</th>";
		strValue += "<th valign=\"middle\">Tuesday</th>";
		strValue += "<th valign=\"middle\">Wednesday</th>";
		strValue += "<th valign=\"middle\">Thursday</th>";
		strValue += "<th valign=\"middle\">Friday</th>";
		strValue += "<th valign=\"middle\">Saturday</th>";
		strValue += "<th valign=\"middle\"></th>";
		strValue += "</tr>";
		strValue += "</thead>";
		strValue += "<tbody>";
		AdvtMaster am1 = new AdvtMaster();
		int numCount=1;
		int numParent = 5;
        if(dayOfWeek>=6 && days==31)
        {
        	numParent = 6;
        }
        if(dayOfWeek>=7 && days==30)
        {
        	numParent = 6;
        }
        int numLastRow = numParent-1;
        for(int i=1; i<=numParent; i++)
        {
        	if(i==numWeek)
    		{
        	strValue += "<tr height=\"60\">";
        	strValue += "<td>&nbsp;</td>";
        	if(i==1)
        	{
        		
	        		for(int j=1; j<=7; j++)
	        		{
	        			if(dayOfWeek<=j)
	        			{
	        				strValue += "<td width=\"14%\"><div class=\"White\">"+numCount+"</div>";
	        				if(resultVec.size()>0)
	        				{
	        					strValue += "<div style=\" margin:2px 2px; position:relative;  height:210px;   float:left;\">";
		        				for(int k=0; k<resultVec.size(); k++)
		        				{
		        					Vector tempVec = (Vector)resultVec.get(k);
		        					int numShiftId = Integer.parseInt(tempVec.get(0).toString().trim());
		        					String strSiftName = tempVec.get(1).toString().trim();
		        					String strStartTime = tempVec.get(2).toString().trim();
		        					String strEndTime = tempVec.get(3).toString().trim();
		        					String strMonth = (String.valueOf(numMonth).length()<=1) ? "0"+numMonth : ""+numMonth;
		        					String strDay = (String.valueOf(numCount).length()<=1) ? "0"+numCount : ""+numCount;
		        					String strDate = strDay+"/"+strMonth+"/"+numYear;
		        					System.out.println("Shift Date "+strDate);
		        					Vector scheduleVec = am1.getShiftSchedule(getDataSource(request, "corpo"), numFid, numShiftId, strDate);
		        					strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-top:1px solid #ccc;\"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">"+strSiftName+"("+strStartTime+" - "+strEndTime+")</span></a></p>";
		        					if(scheduleVec.size()>0)
		        					{
			        					for(int l=0; l<scheduleVec.size(); l++)
			        					{
			        						Vector tempVec1 = (Vector)scheduleVec.get(l);
			        						//int numSheduleId = Integer.parseInt(tempVec1.get(0).toString().trim()); 
			        						//String strScheduleDate = tempVec1.get(1).toString().trim();
			        						//int numStaffId = Integer.parseInt(tempVec1.get(2).toString().trim());
			        						String strStaffName = tempVec1.get(3).toString().trim();
			        						strValue += "<p class=\"frstDate\"  style=\" margin:2px 0px; border-bottom:1px solid #ccc;\"><a href=\"#\"><span>"+strStaffName+"</span></a></p>";
			        					}
		        					}
		        					else
		        					{
		        						strValue += "<p class=\"frstDate\"  style=\" margin:2px 0px; border-bottom:1px solid #ccc;\"><a href=\"#\"><span>No Staff</span></a></p>";
		        					}
		        					
		        				}
		        				strValue += "</div>";
	        				}
	        				else
	        				{
	        					strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-top:1px solid #ccc;\"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">No Shift</span></a></p>";
	        				}
	        				strValue += "</td>";
	    	        		numCount++;
	        			}
	        			else
	        			{
	        				strValue += "<td width=\"14%\"><div>&nbsp;</div></td>";
	        			}
	        		}
        		
        	}
        	else if(i==numLastRow)
        	{
        		
	        		for(int j=1; j<=7; j++)
	        		{
	        			if(numCount<=days)
	        			{
	        				strValue += "<td width=\"14%\"><div class=\"White\">"+numCount+"</div>";
	        				if(resultVec.size()>0)
	        				{
	        					strValue += "<div style=\" margin:2px 2px; position:relative;  height:210px;   float:left;\">";
		        				for(int k=0; k<resultVec.size(); k++)
		        				{
		        					Vector tempVec = (Vector)resultVec.get(k);
		        					int numShiftId = Integer.parseInt(tempVec.get(0).toString().trim());
		        					String strSiftName = tempVec.get(1).toString().trim();
		        					String strStartTime = tempVec.get(2).toString().trim();
		        					String strEndTime = tempVec.get(3).toString().trim();
		        					String strMonth = (String.valueOf(numMonth).length()<=1) ? "0"+numMonth : ""+numMonth;
		        					String strDay = (String.valueOf(numCount).length()<=1) ? "0"+numCount : ""+numCount;
		        					String strDate = strDay+"/"+strMonth+"/"+numYear;
		        					System.out.println("Shift Date "+strDate);
		        					Vector scheduleVec = am1.getShiftSchedule(getDataSource(request, "corpo"), numFid, numShiftId, strDate);
		        					strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-top:1px solid #ccc;\"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">"+strSiftName+"("+strStartTime+" - "+strEndTime+")</span></a></p>";
		        					if(scheduleVec.size()>0)
		        					{
			        					for(int l=0; l<scheduleVec.size(); l++)
			        					{
			        						Vector tempVec1 = (Vector)scheduleVec.get(l);
			        						//int numSheduleId = Integer.parseInt(tempVec1.get(0).toString().trim()); 
			        						//String strScheduleDate = tempVec1.get(1).toString().trim();
			        						//int numStaffId = Integer.parseInt(tempVec1.get(2).toString().trim());
			        						String strStaffName = tempVec1.get(3).toString().trim();
			        						strValue += "<p class=\"frstDate\"  style=\" margin:2px 0px; border-bottom:1px solid #ccc;\"><a href=\"#\"><span>"+strStaffName+"</span></a></p>";
			        					}
		        					}
		        					else
		        					{
		        						strValue += "<p class=\"frstDate\"  style=\" margin:2px 0px; border-bottom:1px solid #ccc;\"><a href=\"#\"><span>No Staff</span></a></p>";
		        					}
		        					
		        				}
		        				strValue += "</div>";
	        				}
	        				else
	        				{
	        					strValue += "<p class=\"frstDate\"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">No Shift</span></a></p>";
	        				}
	        				strValue += "</td>";
	    	        		numCount++;
	        			}
	        			else
	        			{
	        				strValue += "<td width=\"14%\"><div>&nbsp;</div></td>";
	        			}
	        		}
        		
        	}
        	else
        	{
        		
	        		for(int j=1; j<=7; j++)
		        	{
		        		strValue += "<td width=\"14%\"><div class=\"White\">"+numCount+"</div>";
		        		if(resultVec.size()>0)
        				{
		        			strValue += "<div style=\" margin:2px 2px; position:relative;  height:210px;   float:left;\">";
	        				for(int k=0; k<resultVec.size(); k++)
	        				{
	        					Vector tempVec = (Vector)resultVec.get(k);
	        					int numShiftId = Integer.parseInt(tempVec.get(0).toString().trim());
	        					String strSiftName = tempVec.get(1).toString().trim();
	        					String strStartTime = tempVec.get(2).toString().trim();
	        					String strEndTime = tempVec.get(3).toString().trim();
	        					String strMonth = (String.valueOf(numMonth).length()<=1) ? "0"+numMonth : ""+numMonth;
	        					String strDay = (String.valueOf(numCount).length()<=1) ? "0"+numCount : ""+numCount;
	        					String strDate = strDay+"/"+strMonth+"/"+numYear;
	        					System.out.println("Shift Date "+strDate);
	        					Vector scheduleVec = am1.getShiftSchedule(getDataSource(request, "corpo"), numFid, numShiftId, strDate);
	        					strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-top:1px solid #ccc;\"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">"+strSiftName+"("+strStartTime+" - "+strEndTime+")</span></a></p>";
	        					if(scheduleVec.size()>0)
	        					{
		        					for(int l=0; l<scheduleVec.size(); l++)
		        					{
		        						Vector tempVec1 = (Vector)scheduleVec.get(l);
		        						//int numSheduleId = Integer.parseInt(tempVec1.get(0).toString().trim()); 
		        						//String strScheduleDate = tempVec1.get(1).toString().trim();
		        						//int numStaffId = Integer.parseInt(tempVec1.get(2).toString().trim());
		        						String strStaffName = tempVec1.get(3).toString().trim();
		        						strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-bottom:1px solid #ccc;\"><a href=\"#\"><span>"+strStaffName+"</span></a></p>";
		        					}
	        					}
	        					else
	        					{
	        						strValue += "<p class=\"frstDate\"  style=\" margin:2px 0px; border-bottom:1px solid #ccc;\"><a href=\"#\"><span>No Staff</span></a></p>";
	        					}
	        					
	        				}
	        				strValue += "</div>";
        				}
        				else
        				{
        					strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-top:1px solid #ccc;\"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">No Shift</span></a></p>";
        				}
        				strValue += "</td>";
		        		numCount++;
		        	}
        		
        	}
        	strValue += "<td>&nbsp;</td>";
        	strValue += "</tr>";
    		}
        }
				
		strValue += "</tbody>";
		strValue += "</table>";
		return strValue;
	}
	
	private String getCalanderDayView(int numMonth, int numYear, int numDay, int numFid, Vector resultVec, HttpServletRequest request)
	{
		System.out.println("numMonth1 "+numMonth);
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, numYear);
        //calendar.set(Calendar.DAY_OF_YEAR, 181);
        calendar.set(Calendar.MONTH, numMonth-1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
       // System.out.println("\nThe date of Calendar is: " +calendar.getTime());
        //int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);  
        //System.out.println("The day of month: " +dayOfMonth);  
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        ///System.out.println("The day of week: " + dayOfWeek);
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
       // System.out.println("Number of Days: " + days);
		String strValue="<table class=\"CalendarTbl\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">";
		strValue += "<thead>";
		strValue += "<tr>";
		strValue += "<th valign=\"middle\"></th>";
		strValue += "<th valign=\"middle\">Sunday</th>";
		strValue += "<th valign=\"middle\">Monday</th>";
		strValue += "<th valign=\"middle\">Tuesday</th>";
		strValue += "<th valign=\"middle\">Wednesday</th>";
		strValue += "<th valign=\"middle\">Thursday</th>";
		strValue += "<th valign=\"middle\">Friday</th>";
		strValue += "<th valign=\"middle\">Saturday</th>";
		strValue += "<th valign=\"middle\"></th>";
		strValue += "</tr>";
		strValue += "</thead>";
		strValue += "<tbody>";
		AdvtMaster am1 = new AdvtMaster();
		int numCount=1;
		int numParent = 5;
        if(dayOfWeek>=6 && days==31)
        {
        	numParent = 6;
        }
        if(dayOfWeek>=7 && days==30)
        {
        	numParent = 6;
        }
        int numLastRow = numParent-1;
        for(int i=0; i<numParent; i++)
        {
        	strValue += "<tr>";
        	strValue += "<td>&nbsp;</td>";
        	if(i==0)
        	{
        		for(int j=1; j<=7; j++)
        		{
        			if(dayOfWeek<=j)
        			{
        				strValue += "<td width=\"14%\"><div class=\"White\">"+numCount+"</div>";
        				if(resultVec.size()>0)
        				{
        					strValue += "<div style=\" margin:2px 2px; position:relative;  height:210px;   float:left;\">";
	        				for(int k=0; k<resultVec.size(); k++)
	        				{
	        					Vector tempVec = (Vector)resultVec.get(k);
	        					int numShiftId = Integer.parseInt(tempVec.get(0).toString().trim());
	        					String strSiftName = tempVec.get(1).toString().trim();
	        					String strStartTime = tempVec.get(2).toString().trim();
	        					String strEndTime = tempVec.get(3).toString().trim();
	        					String strMonth = (String.valueOf(numMonth).length()<=1) ? "0"+numMonth : ""+numMonth;
	        					String strDay = (String.valueOf(numCount).length()<=1) ? "0"+numCount : ""+numCount;
	        					String strDate = strDay+"/"+strMonth+"/"+numYear;
	        					System.out.println("Shift Date "+strDate);
	        					Vector scheduleVec = am1.getShiftSchedule(getDataSource(request, "corpo"), numFid, numShiftId, strDate);
	        					strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-top:1px solid #ccc;\"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">"+strSiftName+"("+strStartTime+" - "+strEndTime+")</span></a></p>";
	        					if(scheduleVec.size()>0)
	        					{
		        					for(int l=0; l<scheduleVec.size(); l++)
		        					{
		        						Vector tempVec1 = (Vector)scheduleVec.get(l);
		        						//int numSheduleId = Integer.parseInt(tempVec1.get(0).toString().trim()); 
		        						//String strScheduleDate = tempVec1.get(1).toString().trim();
		        						//int numStaffId = Integer.parseInt(tempVec1.get(2).toString().trim());
		        						String strStaffName = tempVec1.get(3).toString().trim();
		        						strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-bottom:1px solid #ccc;\"><a href=\"#\"><span>"+strStaffName+"</span></a></p>";
		        					}
	        					}
	        					else
	        					{
	        						strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-bottom:1px solid #ccc;\"><a href=\"#\"><span>No Staff</span></a></p>";
	        					}
	        					
	        				}
	        				strValue += "</div>";
        				}
        				else
        				{
        					strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-top:1px solid #ccc;\"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">No Shift</span></a></p>";
        				}
        				strValue +="</td>";
    	        		numCount++;
        			}
        			else
        			{
        				strValue += "<td width=\"14%\"><div>&nbsp;</div></td>";
        			}
        		}
        	}
        	else if(i==numLastRow)
        	{
        		for(int j=1; j<=7; j++)
        		{
        			if(numCount<=days)
        			{
        				strValue += "<td width=\"14%\"><div class=\"White\">"+numCount+"</div>";
        				if(resultVec.size()>0)
        				{
        					strValue += "<div style=\" margin:2px 2px; position:relative;  height:210px;   float:left;\">";
	        				for(int k=0; k<resultVec.size(); k++)
	        				{
	        					Vector tempVec = (Vector)resultVec.get(k);
	        					int numShiftId = Integer.parseInt(tempVec.get(0).toString().trim());
	        					String strSiftName = tempVec.get(1).toString().trim();
	        					String strStartTime = tempVec.get(2).toString().trim();
	        					String strEndTime = tempVec.get(3).toString().trim();
	        					String strMonth = (String.valueOf(numMonth).length()<=1) ? "0"+numMonth : ""+numMonth;
	        					String strDay = (String.valueOf(numCount).length()<=1) ? "0"+numCount : ""+numCount;
	        					String strDate = strDay+"/"+strMonth+"/"+numYear;
	        					System.out.println("Shift Date "+strDate);
	        					Vector scheduleVec = am1.getShiftSchedule(getDataSource(request, "corpo"), numFid, numShiftId, strDate);
	        					strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-top:1px solid #ccc;\"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">"+strSiftName+"("+strStartTime+" - "+strEndTime+")</span></a></p>";
	        					if(scheduleVec.size()>0)
	        					{
		        					for(int l=0; l<scheduleVec.size(); l++)
		        					{
		        						Vector tempVec1 = (Vector)scheduleVec.get(l);
		        						//int numSheduleId = Integer.parseInt(tempVec1.get(0).toString().trim()); 
		        						//String strScheduleDate = tempVec1.get(1).toString().trim();
		        						//int numStaffId = Integer.parseInt(tempVec1.get(2).toString().trim());
		        						String strStaffName = tempVec1.get(3).toString().trim();
		        						strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-bottom:1px solid #ccc;\"><a href=\"#\"><span>"+strStaffName+"</span></a></p>";
		        					}
	        					}
	        					else
	        					{
	        						strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-bottom:1px solid #ccc;\"><a href=\"#\"><span>No Staff</span></a></p>";
	        					}
	        					
	        				}
	        				strValue += "</div>";
        				}
        				else
        				{
        					strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-top:1px solid #ccc;\"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">No Shift</span></a></p>";
        				}
        				strValue +="</td>";
    	        		numCount++;
        			}
        			else
        			{
        				strValue += "<td width=\"14%\"><div>&nbsp;</div></td>";
        			}
        		}
        	}
        	else
        	{
	        	for(int j=1; j<=7; j++)
	        	{
	        		strValue += "<td width=\"14%\"><div class=\"White\">"+numCount+"</div>";
        				if(resultVec.size()>0)
        				{
        					strValue += "<div style=\" margin:2px 2px; position:relative;  height:210px;  height:210px;   float:left;\">";
	        				for(int k=0; k<resultVec.size(); k++)
	        				{
	        					Vector tempVec = (Vector)resultVec.get(k);
	        					int numShiftId = Integer.parseInt(tempVec.get(0).toString().trim());
	        					String strSiftName = tempVec.get(1).toString().trim();
	        					String strStartTime = tempVec.get(2).toString().trim();
	        					String strEndTime = tempVec.get(3).toString().trim();
	        					String strMonth = (String.valueOf(numMonth).length()<=1) ? "0"+numMonth : ""+numMonth;
	        					String strDay = (String.valueOf(numCount).length()<=1) ? "0"+numCount : ""+numCount;
	        					String strDate = strDay+"/"+strMonth+"/"+numYear;
	        					System.out.println("Shift Date "+strDate);
	        					Vector scheduleVec = am1.getShiftSchedule(getDataSource(request, "corpo"), numFid, numShiftId, strDate);
	        					strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-top:1px solid #ccc;\"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">"+strSiftName+"("+strStartTime+" - "+strEndTime+")</span></a></p>";
	        					if(scheduleVec.size()>0)
	        					{
		        					for(int l=0; l<scheduleVec.size(); l++)
		        					{
		        						Vector tempVec1 = (Vector)scheduleVec.get(l);
		        						//int numSheduleId = Integer.parseInt(tempVec1.get(0).toString().trim()); 
		        						//String strScheduleDate = tempVec1.get(1).toString().trim();
		        						//int numStaffId = Integer.parseInt(tempVec1.get(2).toString().trim());
		        						String strStaffName = tempVec1.get(3).toString().trim();
		        						strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-bottom:1px solid #ccc;\"><a href=\"#\"><span>"+strStaffName+"</span></a></p>";
		        					}
	        					}
	        					else
	        					{
	        						strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-bottom:1px solid #ccc;\"><a href=\"#\"><span>No Staff</span></a></p>";
	        					}
	        					
	        				}
	        				strValue += "</div>";
        				}
        				else
        				{
        					strValue += "<p class=\"frstDate\" style=\" background-color:#fff;  margin:2px 0px; border-top:1px solid #ccc;\"><a href=\"#\"><span style=\"color:#000000; font-weight:bold;\">No Shift</span></a></p>";
        				}
        				strValue +="</td>";
	        		numCount++;
	        	}
        	}
        	strValue += "<td>&nbsp;</td>";
        	strValue += "</tr>";
        }
				
		strValue += "</tbody>";
		strValue += "</table>";
		return strValue;
	}
}