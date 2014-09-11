package com.mm.struts.corpo.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.AdvtMaster;
import com.mm.core.master.RootMaster;

public class SearchSaveListAction extends Action {
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
		String numCount = (String)session.getAttribute("numCount");
		int count = Integer.parseInt(numCount);
		int remander = count%2;
		
		String taskId = (request.getParameter("taskId")!=null) ? request.getParameter("taskId") : (String)request.getAttribute("taskId");
		//String taskId = (String)request.getAttribute("taskId");
		////System.out.println("taskId..................."+taskId);
		request.setAttribute("taskId", taskId);
		
		AdvtMaster advtMaster = new AdvtMaster();
		RootMaster rootMaster = new RootMaster();
		
		String uId =(String)session.getAttribute("uId");
		String compId =(String)session.getAttribute("compId");
		int regFlag= rootMaster.getRegisteredFlag(getDataSource(request, "corpo"), compId);		
		////System.out.println("uId....in SearchSaveAction........"+uId);
		Vector<Vector> resultVec = new Vector<Vector>();
		Vector<Vector> resultVecGlobal = new Vector<Vector>();		
		Vector<String> paramVec = new Vector<String>();
		
				
		
		paramVec.add(uId);
		paramVec.add(compId);
		
		if(taskId.equalsIgnoreCase("5")){
			
			resultVec= rootMaster.getSearchStuResultList(getDataSource(request, "corpo"), paramVec);
			////System.out.println("resultVec....in SearchSaveAction........"+resultVec);
			
			resultVecGlobal= rootMaster.getSearchStuCGlobalList(getDataSource(request, "corpo"), paramVec);
			////System.out.println("resultVecGlobal....in SearchSaveAction........"+resultVecGlobal);
			
		}else if(taskId.equalsIgnoreCase("6")){
			
			resultVec= rootMaster.getSearchCriteriaList(getDataSource(request, "corpo"), paramVec);
			////System.out.println("resultVec....in SearchSaveAction........"+resultVec);
			
			resultVecGlobal= rootMaster.getSearchCriteriaGlobalList(getDataSource(request, "corpo"), paramVec);
			////System.out.println("resultVecGlobal....in SearchSaveAction........"+resultVecGlobal);
			
		}
		
		
		
		String strComplaintList = getSearchResultList(resultVec, resultVecGlobal, taskId, regFlag, remander);
		////////System.out.println("Users "+strComplaintList);
		/**
	     * setContentType - text/html to write String on page.
	     * PrintWriter - This line use to get writer to write on page.
	     * out.println - use to write string. 
	     * 
	     */
		response.setContentType("text/html;charset=ISO-8859-1");
	    PrintWriter out = response.getWriter();
	    out.println(strComplaintList);
	    out.flush();
		
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getSearchResultList(Vector resultVec, Vector resultVecGlobal, String taskId, int regFlag, int remander)
	{	
		String strValue = "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
		
		
	
		
		if((resultVec.size()==0)&&(resultVecGlobal.size()==0)){
		strValue= "<tr align=\"center\">";
		strValue += "<td height=\"400\" valign=\"center\" class=\"bold\"> No Data Found</td>";
		strValue += "</tr>";
		}else if(resultVec.size()!=0){
			
			strValue += "<tr height=\"20\">";
	        strValue += "<td width=\"100%\">";
	        strValue += "</td>";
	        strValue += "</tr>";
	        
	        if(taskId.equalsIgnoreCase("5")){
	        
	        strValue += "<tr height=\"20\">";
	        strValue += "<td align=\"left\" width=\"100%\"class=\"bold\">&nbsp;Your Saved Search Result(s)";
	        strValue += "</td>";
	        strValue += "</tr>";
	        }else{
	        	strValue += "<tr height=\"20\">";
		        strValue += "<td align=\"left\" width=\"100%\"class=\"bold\">&nbsp;Your Saved Search Criteria(s)";
		        strValue += "</td>";
		        strValue += "</tr>";
	        }
	        
	        strValue += "<tr height=\"20\">";
	        strValue += "<td width=\"100%\"><hr color=\"#C1C1C1\">";
	        strValue += "</td>";
	        strValue += "</tr>";
			
			strValue += "<tr>";
	        strValue += "<td width=\"100%\">";
	           
		    strValue += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
		    strValue += "<form  name=\"frmdeletStudentId\" method=\"post\"  action=\"deletStuResult.do\">";
		    
		    strValue += "<tr height=\"20\" >";	
		    strValue += "<th align=\"center\" class=\"bold\">&nbsp;S. No.</th>";	
		    strValue += "<th class=\"bold\">&nbsp;</th>";
		    strValue += "<th class=\"bold\">Search Title&nbsp;</th>";	
		    strValue += "<th class=\"bold\">Date&nbsp;</th>";
		   
		    strValue += "</tr>";	
		    
		   
		    strValue += "<tr>";	
		    strValue += "<td height=\"5\" colspan=\"4\" align=\"right\"><hr color=\"#C1C1C1\"><input type=\"hidden\" name=\"strtaskId\" id=\"strtaskId\" value=\""+taskId+"\"></input></td>";	
		    strValue += "</tr>";
		    
		    
		    for(int i=0; i<resultVec.size(); i++)
			{
		    	int j = i+1;
		    	int numTotal = j%2;
		    	String strColor = (numTotal==0) ? "#FFFFFF" : "#EDF3F8";
		    	String strImg = (numTotal==0) ? "detail_gray.gif" : "detail_white.gif";
			 Vector tempVec = (Vector)resultVec.elementAt(i);
			 
			 int srId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			 
			 String strFid = tempVec.elementAt(1).toString().trim();
			 
//			 	for check length of Complaint text
			 String strText = "";  
			 String strFMsg = "";
			 
				String strMessage =  tempVec.elementAt(3).toString().trim();
				//////////System.out.println("complaintMessage........."+strMessage.length());
				//////////System.out.println("complaintMessage........."+strMessage);
				int numChar = (strMessage.length() > 45) ? 45 : strMessage.length();  
				for(int k=0; k<numChar; k++)
				{ 
					
						   strFMsg = strFMsg+strMessage.charAt(k);
						   
						
				}
				if(strFMsg.lastIndexOf(strFMsg)!=-1)
				{
					 strText= strFMsg;
					////////System.out.println("...strFMsgTeam......"+strFMsg.lastIndexOf(strFMsg));
					
		         
		        }else{
		        	strText= strFMsg.substring(0, strFMsg.lastIndexOf(" "));
		        	////////System.out.println("...strText......");
		        }
		     String strDate = tempVec.elementAt(4).toString().trim();
		     strValue += "<tr height=\"25\" align=\"center\" bgcolor=\""+strColor+"\">";
		     strValue += "<td width=\"10%\" align=\"center\" class=\"bold\">&nbsp;"+j+"</td>";
		    strValue += "<td width=\"5%\" align=\"left\" >";	
		    strValue +="&nbsp;<input name=\"studentsIds\" type=\"checkbox\"  id=\"studentsIds\" value=\""+srId+"\" onClick=\"checkSelection()\"></input>&nbsp;</td>";
			if(taskId.equalsIgnoreCase("6")){
				strValue += "<td align=\"center\" ><a href=\"searchCriteria.do?taskId="+taskId+"&searchResultId="+srId+"\"> "+strText+"..</a></td>";	
				
			}else{
		    strValue += "<td align=\"center\" ><a href=\"searchResultDetail.jsp?searchResultId="+srId+"\"> "+strText+"..</a></td>";	
			}
		    strValue += "<td width=\"20%\" align=\"center\" > "+strDate+"</td>";
		   
		    strValue += "</tr>";	
		   	   
		    strValue += "</td>";	
		    strValue += "</tr>";
		    
		    
			
			}
		    
		    strValue += "<tr>";	
		    strValue += "<td height=\"20\" colspan=\"4\" align=\"right\"><hr color=\"#C1C1C1\"></td>";	
		    strValue += "</tr>";
		    
		    strValue +=" <tr  align=\"right\">";
			strValue +="  <td colspan=\"4\" valign=\"middle\" align=\"left\" bgcolor=\"#FFFFFF\"><input type=\"submit\" id=\"deletstudent\" name=\"deletstudent\"  value=\"Delete\" disabled=\"true\" ></input></td>";
			strValue +="  </tr>";
		    
		    strValue = strValue +"</form>";
		    strValue += "</table>"; 
		    strValue += "</td>"; 
		    strValue += "</tr>"; 		    
		    
		    strValue += "<tr>";	
		    strValue += "<td height=\"5\" align=\"right\"><hr color=\"#C1C1C1\"></td>";	
		    strValue += "</tr>";
		    // end parsnal.
		}
		if(resultVecGlobal.size()!=0){
		    strValue += "<tr height=\"40\">";
	        strValue += "<td width=\"100%\">";
	        strValue += "</td>";
	        strValue += "</tr>";
	        
	       
		    
		    // for global.
		    
		    if(taskId.equalsIgnoreCase("5")){
		        
		        strValue += "<tr height=\"20\">";
		        strValue += "<td align=\"left\" width=\"100%\"class=\"bold\">&nbsp;Company Saved Search Result(s)";
		        strValue += "</td>";
		        strValue += "</tr>";
		        }else{
		        	strValue += "<tr height=\"20\">";
			        strValue += "<td align=\"left\" width=\"100%\"class=\"bold\">&nbsp;Company Saved Search Criteria(s)";
			        strValue += "</td>";
			        strValue += "</tr>";
		        }
		    
		    strValue += "<tr>";	
		    strValue += "<td height=\"5\" align=\"right\"><hr color=\"#C1C1C1\"></td>";	
		    strValue += "</tr>";
		    
		    strValue += "<tr >";
	        strValue += "<td width=\"100%\">";
	           
		    strValue += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >"; 
		    
		    strValue += "<tr height=\"20\" >";	
		    strValue += "<th class=\"bold\">&nbsp;S. No.</th>";		  	    
		    strValue += "<th class=\"bold\">Search Title&nbsp;</th>";	
		    strValue += "<th class=\"bold\">Date&nbsp;</th>";
		    strValue += "<th class=\"bold\">&nbsp;</th>";
		    strValue += "</tr>";	
		    
		    strValue += "<tr>";	
		    strValue += "<td height=\"5\" colspan=\"4\" align=\"right\"><hr color=\"#C1C1C1\"></td>";	
		    strValue += "</tr>";
		    
		    
		    for(int i=0; i<resultVecGlobal.size(); i++)
			{
		    	int j = i+1;
		    	int numTotal = j%2;
		    	String strColor = (numTotal==0) ? "#FFFFFF" : "#EDF3F8";
		    	String strImg = (numTotal==0) ? "detail_gray.gif" : "detail_white.gif";
			 Vector tempVec = (Vector)resultVecGlobal.elementAt(i);
			 
			 int srId = Integer.parseInt(tempVec.elementAt(0).toString().trim());
			 
			 String strFid = tempVec.elementAt(1).toString().trim();
			 
//			 	for check length of Complaint text
			 String strText = "";  
			 String strFMsg = "";
			 
				String strMessage =  tempVec.elementAt(3).toString().trim();
				//////////System.out.println("complaintMessage........."+strMessage.length());
				//////////System.out.println("complaintMessage........."+strMessage);
				int numChar = (strMessage.length() > 45) ? 45 : strMessage.length();  
				for(int k=0; k<numChar; k++)
				{ 
					
						   strFMsg = strFMsg+strMessage.charAt(k);
						   
						
				}
				if(strFMsg.lastIndexOf(strFMsg)!=-1)
				{
					 strText= strFMsg;
					////////System.out.println("...strFMsgTeam......"+strFMsg.lastIndexOf(strFMsg));
					
		         
		        }else{
		        	strText= strFMsg.substring(0, strFMsg.lastIndexOf(" "));
		        	////////System.out.println("...strText......");
		        }
		     String strDate = tempVec.elementAt(4).toString().trim();
		     strValue += "<tr height=\"25\" align=\"center\" bgcolor=\""+strColor+"\">";
		    strValue += "<td height=\"20\" >&nbsp;"+j+"</td>";	
		    
		    if(taskId.equalsIgnoreCase("6")){
				strValue += "<td align=\"center\" ><a href=\"searchCriteria.do?taskId="+taskId+"&searchResultId="+srId+"\"> "+strText+"..</a></td>";	
				
			}else{
		    strValue += "<td align=\"center\" ><a href=\"searchResultDetail.jsp?taskId="+taskId+"&searchResultId="+srId+"\"> "+strText+"..</a></td>";	
			}
		    strValue += "<td align=\"center\" class=\"bold\"> "+strDate+"</td>";
		    strValue += "<td class=\"bold\">&nbsp;</td>";
		    strValue += "</tr>";	
		   	   
		    strValue += "</td>";	
		    strValue += "</tr>";
			}
		    
		    
		    strValue += "</table>"; 
		    strValue += "</td>"; 
		    strValue += "</tr>"; 
		    
		    strValue += "<tr>";	
		    strValue += "<td height=\"5\"  align=\"right\"><hr color=\"#C1C1C1\"></td>";	
		    strValue += "</tr>";
		   
		    
		    strValue += "<tr height=\"40\">";
	        strValue += "<td width=\"100%\">";
	        strValue += "</td>";
	        strValue += "</tr>";
		
		    
			
		}
		
		if(remander==0)
		{
			strValue += "<tr style=\"display:none\" align=\"center\">";
			strValue += "<td height=\"50\"></td>";
			strValue += "</tr>";
		}
	    strValue += "</table>";	
		
		return strValue;
	}

}
