package com.mm.struts.corpo.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.IndvMaster;
import com.mm.core.master.RootMaster;

public class DetailAllAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		////////System.out.println(".............DetailAllAction...................");
		
	
	IndvMaster indvMaster = new IndvMaster();
	RootMaster rootMaster = new RootMaster();
	HttpSession session =request.getSession();
	
	String strComplaints= "<tr><td>No Data</td></tr>";
	
	
	
	Vector<String> paramVec = new Vector<String>();
	
	String cuid = (request.getParameter("cuid")!=null) ? request.getParameter("cuid") : "0";
	session.setAttribute(cuid, cuid);
	String detailFor = (request.getParameter("detailFor")!=null) ? request.getParameter("detailFor") : "";
	
	Vector searchResultVec = new Vector();
	Vector dataVec = new Vector();
	paramVec.add(detailFor);
	paramVec.add(cuid);
	//////System.out.println("..paramVec...........DetailAllAction..................."+paramVec);
	
	String strDes = "";
	
	if(paramVec.elementAt(0).equalsIgnoreCase("Student") ){
		
		 searchResultVec = indvMaster.getUserInfo(getDataSource(request, "corpo"),Integer.parseInt(paramVec.elementAt(1)));
		 //////System.out.println("searchResultVec....Student..............."+searchResultVec);
		  strDes = "Student";
		  
		  
		  	
	}
	else if(paramVec.elementAt(0).equalsIgnoreCase("Corporates") ){
		
		 searchResultVec = rootMaster.getCompanyDetail(getDataSource(request, "corpo"),Integer.parseInt(paramVec.elementAt(1)));
		 dataVec.add(cuid);
			Vector tabVec = indvMaster.getTabDetails(getDataSource(request, "corpo"), dataVec);
			session.setAttribute("tabVec",tabVec);
			//////System.out.println("tabVec....Corporates..............."+tabVec);
					
			strDes = "Corporates";	 
		
		 
		 	
	}
	else if( paramVec.elementAt(0).equalsIgnoreCase("Consultants")){
			
		 searchResultVec = indvMaster.getUserInfo(getDataSource(request, "corpo"),Integer.parseInt(paramVec.elementAt(1)));
		  
		  strDes = "Consultants";
		 
	}
	else if( paramVec.elementAt(0).equalsIgnoreCase("Advertiser")){
		
		 searchResultVec = rootMaster.getCompanyDetail(getDataSource(request, "corpo"),Integer.parseInt(paramVec.elementAt(1)));
		 dataVec.add(cuid);
			Vector tabVec = indvMaster.getTabDetails(getDataSource(request, "corpo"), dataVec);
			session.setAttribute("tabVec",tabVec);
			
			strDes = "Advertiser";
		 
				}
	
	//////System.out.println("searchResultVec ......"+searchResultVec);

	
	
	strComplaints = getresultDetail(getDataSource(request, "corpo"), strDes, searchResultVec);
	
	
	
	response.setContentType("text/html;charset=ISO-8859-1");
    PrintWriter out = response.getWriter();
    out.println(strComplaints);
    out.flush();
	return null;	
	
}

private String getresultDetail(DataSource ds, String strDes, Vector searchResultVec)
{
	
	RootMaster rootMaster = new RootMaster();
	
	IndvMaster indvMaster =new IndvMaster();
	String strValue = "<table width=\"100%\"   align=\"center\" cellpadding=\"0\" cellspacing=\"0\"  >";
	if(searchResultVec.size()==0)
	{
		strValue += "<tr align=\"center\">";
		strValue += "<td height=\"400\" valign=\"center\" colspan=\"5\" class=\"bold\"> No Data Found</td>";
		strValue += "</tr>";
	}
	else
	{
		
		
		  
	    if(strDes.equalsIgnoreCase("Student")){
	    	
	    	
	    		
	    		int contryId = Integer.parseInt(searchResultVec.elementAt(11).toString());//dealer contryId id
				String contryStr = rootMaster.getCountryName(ds,contryId);
				int stateId = Integer.parseInt(searchResultVec.elementAt(7).toString());//dealer stateId id
				String stateStr = rootMaster.getStateName(ds,stateId);
				int cityId = Integer.parseInt(searchResultVec.elementAt(6).toString());//dealer cityId id
				String cityStr = rootMaster.getPlaceName(ds,cityId);
				int compnyid=Integer.parseInt(searchResultVec.elementAt(22).toString());
				String Cname="";
				if(compnyid==0)
				{ 
					 compnyid=Integer.parseInt(searchResultVec.elementAt(27).toString());
					 Vector name=indvMaster.getOtherCollege(ds, compnyid);
					 Cname=name.elementAt(0).toString().trim();
				}else{
						Cname=rootMaster.getCompnyName(ds, compnyid);
					 }
				
				String phone = searchResultVec.elementAt(12).toString().replace("~", "-");
				String mobile = searchResultVec.elementAt(10).toString().replace("~", "-");
				
				Vector extraVec = new Vector();
				
				extraVec.add(contryStr);
				extraVec.add(stateStr);
				extraVec.add(cityStr);
				extraVec.add(phone);
				extraVec.add(mobile);
			
	    	
				strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\"></td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;</td>";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\"></td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"><A href=\"otherResumePre.do?userid="+searchResultVec.elementAt(26).toString()+"\">Resume </A> </td>";
				strValue+="<td class=\"bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				
				
	    	strValue += "<tr height=\"25\" align=\"center\">";
			strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Student Name:  </td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(4).toString()+"&nbsp; "+searchResultVec.elementAt(5).toString()+"</td>";
			strValue+="<td width=\"20%\" align=\"left\" height=\"20\"></td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\"></td>";
			strValue+="<td class=\"bold\">";
			strValue+="</td>";
			strValue+="</tr>";
			
			strValue += "<tr height=\"25\" align=\"center\">";
			strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Gender:</td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(14).toString()+" </td>";				
			strValue+="<td width=\"20%\" align=\"left\" height=\"20\"></td>";
			strValue+="<td width=\"30%\" class=\"bold\">";
			strValue+="<td align=\"left\" height=\"20\"></td>";
			strValue+="</td>";
			strValue+="</tr>";
			
			strValue += "<tr height=\"25\" align=\"center\">";
			strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">College/Institue Nane:</td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+Cname+" </td>";				
			strValue+="<td width=\"20%\" align=\"left\" height=\"20\"></td>";
			strValue+="<td width=\"30%\" class=\"bold\">";
			strValue+="<td align=\"left\" height=\"20\"></td>";
			strValue+="</td>";
			strValue+="</tr>";
			
			strValue += "<tr height=\"25\" align=\"center\">";
			strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Course Attending:</td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(23).toString()+" </td>";				
			strValue+="<td width=\"20%\" align=\"left\" height=\"20\"></td>";
			strValue+="<td width=\"30%\" class=\"bold\">";
			strValue+="<td align=\"left\" height=\"20\"></td>";
			strValue+="</td>";
			strValue+="</tr>";
			
			strValue += "<tr height=\"25\" align=\"center\">";
			strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Major Subject :</td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(24).toString()+" </td>";				
			strValue+="<td width=\"20%\" align=\"left\" height=\"20\"></td>";
			strValue+="<td width=\"30%\" class=\"bold\">";
			strValue+="<td align=\"left\" height=\"20\"></td>";
			strValue+="</td>";
			strValue+="</tr>";
			
			strValue += "<tr height=\"25\" align=\"center\">";
			strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">E-mail Id:</td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(8).toString()+"</td>";
			strValue+="<td width=\"20%\" align=\"left\" height=\"20\"></td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\"></td>";
			strValue+="<td class=\"bold\">";
			strValue+="</td>";
			strValue+="</tr>";
			
			strValue += "<tr height=\"25\" align=\"center\">";
			strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Address:</td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(9).toString()+"</td>";
			strValue+="<td width=\"20%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;</td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\"></td>";
			strValue+="<td class=\"bold\">";
			strValue+="</td>";
			strValue+="</tr>";
			
			strValue += "<tr height=\"25\" align=\"center\">";
			strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Country:</td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(0).toString()+"</td>";
			strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"bold\">State:</td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(1).toString()+"</td>";
			strValue+="<td class=\"bold\">";
			strValue+="</td>";
			strValue+="</tr>";
			
			strValue += "<tr height=\"25\" align=\"center\">";
			strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">City:</td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(2).toString()+"</td>";
			strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"bold\">Pin No:</td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(13).toString()+"</td>";
			strValue+="<td class=\"bold\">";
			strValue+="</td>";
			strValue+="</tr>";
			
			strValue += "<tr height=\"25\" align=\"center\">";
			strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Phone No:</td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(3).toString()+"</td>";
			strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"bold\">Mobile No:</td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(4).toString()+"</td>";
			strValue+="<td class=\"bold\">";
			strValue+="</td>";
			strValue+="</tr>";
			
			strValue += "<tr height=\"25\" align=\"center\">";
			strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">&nbsp;&nbsp;&nbsp;&nbsp;</td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;</td>";
			strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"bold\"></td>";
			strValue+="<td width=\"30%\" align=\"left\" height=\"20\"><A href=\"otherResumePre.do?userid="+searchResultVec.elementAt(26).toString()+"\">Resume </A> </td>";
			strValue+="<td class=\"bold\">";
			strValue+="</td>";
			strValue+="</tr>";
			
	    	
	    	
	    }
	    else if(strDes.equalsIgnoreCase("Consultants"))
	    {
	    	int contryId = Integer.parseInt(searchResultVec.elementAt(11).toString());//dealer contryId id
			String contryStr = rootMaster.getCountryName(ds,contryId);
			int stateId = Integer.parseInt(searchResultVec.elementAt(7).toString());//dealer stateId id
			String stateStr = rootMaster.getStateName(ds,stateId);
			int cityId = Integer.parseInt(searchResultVec.elementAt(6).toString());//dealer cityId id
			String cityStr = rootMaster.getPlaceName(ds,cityId);
			
			String phone = searchResultVec.elementAt(12).toString().replace("~", "-");
			String mobile = searchResultVec.elementAt(10).toString().replace("~", "-");
			
			Vector extraVec = new Vector();
			
			extraVec.add(contryStr);
			extraVec.add(stateStr);
			extraVec.add(cityStr);
			extraVec.add(phone);
			extraVec.add(mobile);
			
  /*  		
		strValue += "<tr height=\"25\" align=\"center\" bgcolor=\"#E6E6E6\">";
		strValue+="<td width=\"20%\" align=\"left\" height=\"20\" class=\"bold\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";
		strValue+="<td width=\"30%\" align=\"left\" height=\"20\"></td>";
		strValue+="<td width=\"20%\" align=\"left\" height=\"20\"></td>";
		strValue+="<td width=\"30%\" align=\"left\" height=\"20\"></td>";
		strValue+="<td class=\"bold\">";
		strValue+="</td>";
		strValue+="</tr>";
  */  	
    	strValue += "<tr height=\"25\" align=\"center\">";
		strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Consultants Name:  </td>";
		strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(4).toString()+"&nbsp; "+searchResultVec.elementAt(5).toString()+"</td>";
		strValue+="<td width=\"20%\" align=\"left\" height=\"20\"></td>";
		strValue+="<td width=\"30%\" align=\"left\" height=\"20\"></td>";
		strValue+="<td class=\"bold\">";
		strValue+="</td>";
		strValue+="</tr>";
		
		strValue += "<tr height=\"25\" align=\"center\">";
		strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Gender:</td>";
		strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(14).toString()+" </td>";				
		strValue+="<td width=\"20%\" align=\"left\" height=\"20\"></td>";
		strValue+="<td width=\"30%\" class=\"bold\">";
		strValue+="<td align=\"left\" height=\"20\"></td>";
		strValue+="</td>";
		strValue+="</tr>";
		
		strValue += "<tr height=\"25\" align=\"center\">";
		strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">E-mail Id:</td>";
		strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(8).toString()+"</td>";
		strValue+="<td width=\"20%\" align=\"left\" height=\"20\"></td>";
		strValue+="<td width=\"30%\" align=\"left\" height=\"20\"></td>";
		strValue+="<td class=\"bold\">";
		strValue+="</td>";
		strValue+="</tr>";
		
		strValue += "<tr height=\"25\" align=\"center\">";
		strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Address:</td>";
		strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(9).toString()+"</td>";
		strValue+="<td width=\"20%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;</td>";
		strValue+="<td width=\"30%\" align=\"left\" height=\"20\"></td>";
		strValue+="<td class=\"bold\">";
		strValue+="</td>";
		strValue+="</tr>";
		
		strValue += "<tr height=\"25\" align=\"center\">";
		strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Country:</td>";
		strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(0).toString()+"</td>";
		strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"bold\">State:</td>";
		strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(1).toString()+"</td>";
		strValue+="<td class=\"bold\">";
		strValue+="</td>";
		strValue+="</tr>";
		
		strValue += "<tr height=\"25\" align=\"center\">";
		strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">City:</td>";
		strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(2).toString()+"</td>";
		strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"bold\">Pin No:</td>";
		strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(13).toString()+"</td>";
		strValue+="<td class=\"bold\">";
		strValue+="</td>";
		strValue+="</tr>";
		
		strValue += "<tr height=\"25\" align=\"center\">";
		strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Phone No:</td>";
		strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(3).toString()+"</td>";
		strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"bold\">Mobile No:</td>";
		strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(4).toString()+"</td>";
		strValue+="<td class=\"bold\">";
		strValue+="</td>";
		strValue+="</tr>";
		
	    	
	    	
	    }
	    else if(strDes.equalsIgnoreCase("Corporates"))
	    {
	    	
	    	
	    		
	    		int contryId = Integer.parseInt(searchResultVec.elementAt(6).toString());//dealer contryId id
				String contryStr = rootMaster.getCountryName(ds,contryId);
				int stateId = Integer.parseInt(searchResultVec.elementAt(5).toString());//dealer stateId id
				String stateStr = rootMaster.getStateName(ds,stateId);
				int cityId = Integer.parseInt(searchResultVec.elementAt(4).toString());//dealer cityId id
				String cityStr = rootMaster.getPlaceName(ds,cityId);
				
				String phone = searchResultVec.elementAt(9).toString().replace("~", "-");
				String Category = searchResultVec.elementAt(10).toString();
				//////System.out.println("Category.............."+Category);
				
				String strtemCat[] = Category.split(",");
				int k = strtemCat.length;
				//////System.out.println("industryCategory...length........"+strtemCat.length);
				Vector<String> industryCatName = new Vector<String>();		
				
				for (int m=1; m<k ; m++)
					
				{ 	int a = Integer.parseInt(strtemCat[m].trim());
					
					String catName = rootMaster.getStrCategoryName(ds, a);
					industryCatName.add(catName);
					
				}
				
				//////System.out.println("industryCatName..........."+industryCatName);
				
				Vector extraVec = new Vector();
				
				extraVec.add(contryStr);
				extraVec.add(stateStr);
				extraVec.add(cityStr);
				extraVec.add(phone);
				extraVec.add(industryCatName);
				
				String manterCategory = searchResultVec.elementAt(25).toString();				
				String mCat[] = manterCategory.split(",");
				String men1="[";
				
				if(mCat[0].equalsIgnoreCase("1")){
					men1=men1+"Personality";
				}
				if(mCat[1].equalsIgnoreCase("2")){
					
					if(men1.length()<3){
						men1=men1+"Soft skills";
					}else{
					men1=men1+"  , "+"Soft skills";
					}
				}
				if(mCat[2].equalsIgnoreCase("3")){
					
					if(men1.length()<3){
						men1=men1+"Technical";
					}else{
					men1=men1+"  , "+"Technical";
					}
					
				}
				if(mCat[3].equalsIgnoreCase("4")){
					
					if(men1.length()<3){
						men1=men1+"Aptitude";
					}else{
					men1=men1+"  , "+"Aptitude";
					}
					
				}
				men1=men1+"]";
				
	/*			strValue += "<tr height=\"25\" align=\"center\" bgcolor=\"#E6E6E6\">";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\" class=\"bold\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"></td>";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\"></td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"></td>";
				strValue+="<td class=\"bold\">";
				strValue+="</td>";
				strValue+="</tr>";
	*/	    	
		    	strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Company Name:  </td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(1).toString()+"&nbsp; </td>";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\"></td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"></td>";
				strValue+="<td class=\"bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				
		/*		strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Category:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(4).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"bold\"></td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;</td>";
				strValue+="<td class=\"bold\">";
				strValue+="</td>";
				strValue+="</tr>";
		*/		
				strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Mentoring Category:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+men1+" </td>";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"bold\"></td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;</td>";
				strValue+="<td class=\"bold\">";
				strValue+="</td>";
				strValue+="</tr>";
	/*								
				strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">E-mail Id:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(8).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\"></td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"></td>";
				strValue+="<td class=\"bold\">";
				strValue+="</td>";
				strValue+="</tr>";
	*/			
				strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\">Address:</td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(2).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;</td>";
				if(searchResultVec.elementAt(3).toString()!=null){
					
					strValue += "<tr height=\"25\" align=\"center\">";
					strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\"></td>";
					strValue+="<td width=\"60%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(3).toString()+"&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(0).toString()+",&nbsp;&nbsp;"+extraVec.elementAt(1).toString()+",&nbsp;&nbsp;"+extraVec.elementAt(2).toString()+"-&nbsp;&nbsp;"+searchResultVec.elementAt(7).toString()+"</td>";
					strValue+="<td width=\"20%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;</td>";
					
					
				}
				
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\"></td>";
				strValue+="<td class=\"bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				
		/*		strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\"></td>";   // Country:
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(0).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"bold\"></td>";  // State:
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(1).toString()+"</td>";
				strValue+="<td class=\"bold\">";
				strValue+="</td>";
				strValue+="</tr>";
				
				strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\"></td>";   // City:
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(2).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"bold\"></td>";     // Pin No:
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+searchResultVec.elementAt(7).toString()+"</td>";
				strValue+="<td class=\"bold\">";
				strValue+="</td>";
				strValue+="</tr>";
	*/			
	/*			strValue += "<tr height=\"25\" align=\"center\">";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\" class=\"bold\"></td>";  // Phone No:
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;"+extraVec.elementAt(3).toString()+"</td>";
				strValue+="<td width=\"20%\" align=\"right\" height=\"20\"class=\"bold\"></td>";
				strValue+="<td width=\"30%\" align=\"left\" height=\"20\">&nbsp;&nbsp;&nbsp;&nbsp;</td>";
				strValue+="<td class=\"bold\">";
				strValue+="</td>";
				strValue+="</tr>";
	*/			
				
					
				
				
	    	
	    }
	    else if(strDes.equalsIgnoreCase("Advertiser"))
	    {
	    	
	    	
	    	int contryId = Integer.parseInt(searchResultVec.elementAt(6).toString());//dealer contryId id
			String contryStr = rootMaster.getCountryName(ds,contryId);
			int stateId = Integer.parseInt(searchResultVec.elementAt(5).toString());//dealer stateId id
			String stateStr = rootMaster.getStateName(ds,stateId);
			int cityId = Integer.parseInt(searchResultVec.elementAt(4).toString());//dealer cityId id
			String cityStr = rootMaster.getPlaceName(ds,cityId);
			
			String phone = searchResultVec.elementAt(9).toString().replace("~", "-");
			String Category = searchResultVec.elementAt(10).toString();
			////////System.out.println("Category.............."+Category);
			
			String strtemCat[] = Category.split(",");
			int k = strtemCat.length;
			////////System.out.println("industryCategory...length........"+strtemCat.length);
			Vector<String> industryCatName = new Vector<String>();		
			
			for (int m=1; m<k ; m++)
				
			{ 	int a = Integer.parseInt(strtemCat[m].trim());
				
				String catName = rootMaster.getStrCollegeCategoryName(ds, a);
				industryCatName.add(catName);
				
			}
			
			////////System.out.println("industryCatName..........."+industryCatName);
			
			Vector extraVec = new Vector();
			
			extraVec.add(contryStr);
			extraVec.add(stateStr);
			extraVec.add(cityStr);
			extraVec.add(phone);
			extraVec.add(industryCatName);
			
	
			strValue += "<tr height=\"20\" align=\"center\">";
			strValue+="<td class=\"bold\" colspan=\"5\"><table><tr>";
			strValue+="<td class=\"bold\" width=\"15%\" align=\"right\"> College Name:</td>";
			strValue+="<td class=\"bold\" width=\"1%\"></td>";
			strValue+="<td  colspan=\"4\" align=\"left\">"+searchResultVec.elementAt(1).toString()+"</td>";
			strValue+="</tr>";
			
	  	
	    	strValue += "<tr height=\"20\" align=\"center\">";
	    	strValue+="<td class=\"bold\" width=\"15%\" align=\"right\" > Category:</td>";
			strValue+="<td class=\"bold\" width=\"1%\"></td>";
			strValue+="<td  colspan=\"4\" align=\"left\">"+extraVec.elementAt(4).toString()+"</td>";
			strValue+="</tr>";
			
			strValue += "<tr height=\"25\" align=\"center\">";
			strValue+="<td class=\"bold\" width=\"15%\" align=\"right\"> Address:</td>";
			strValue+="<td class=\"bold\" width=\"1%\"></td>";
			strValue+="<td  colspan=\"4\"  align=\"left\">"+searchResultVec.elementAt(2).toString()+"</td>";
			strValue+="</tr>";
			if(searchResultVec.elementAt(3).toString().length()!=0){
			strValue += "<tr height=\"25\" align=\"center\">";
			strValue+="<td class=\"bold\" width=\"15%\" ></td>";
			strValue+="<td class=\"bold\" width=\"1%\"></td>";
			strValue+="<td  colspan=\"4\" align=\"left\">"+searchResultVec.elementAt(3).toString()+"</td>";
			strValue+="</tr>";
			}				
			
			
		
			strValue += "<tr height=\"25\" align=\"center\">";
			strValue+="<td class=\"bold\" width=\"15%\" ></td>";
			strValue+="<td class=\"bold\" width=\"1%\"></td>";
			strValue+="<td  width=\"21%\" align=\"left\">"+extraVec.elementAt(0).toString()+"</td>";
			strValue+="<td  width=\"21%\" align=\"left\">"+extraVec.elementAt(1).toString()+"</td>";
			strValue+="<td  width=\"21%\" align=\"left\">"+extraVec.elementAt(2).toString()+"</td>";
			strValue+="<td  width=\"21%\" align=\"left\">"+searchResultVec.elementAt(7).toString()+"</td>";
			strValue+="</tr>";
			
			strValue+="</table>";
			strValue+="</td>";
			strValue+="</tr>";
			
			
			
			
			
			
	    }
	    
	   
	}
	
	strValue += "</table>";
	return strValue;
}

}
