package com.mm.struts.advt.action;

import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.EntpMaster;
import com.mm.core.master.IndvMaster;
import com.mm.core.master.RootMaster;

public class OtherResumePreAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {

HttpSession session =request.getSession();
String numCount = (String)session.getAttribute("numCount");	

String uId = (request.getParameter("userid")!=null) ? request.getParameter("userid") : "";


String qtype1 = (request.getParameter("qtype1")!=null) ? request.getParameter("qtype1") : "";
int numUId= Integer.parseInt(uId);
DataSource ds = getDataSource(request,"advt");
EntpMaster entpMaster= new EntpMaster();
RootMaster rootMaster= new RootMaster();
IndvMaster indvMaster= new IndvMaster();

Vector detailVec = new Vector();
Vector commentDetailVec = new Vector();
Vector tempComment=new Vector();
Vector detail1=new Vector();
Vector responseVec=new Vector();

if(qtype1.equalsIgnoreCase("detail"))
{
	request.setAttribute("qtype1", "detail");
	String complainId = (request.getParameter("complainId")!=null) ? request.getParameter("complainId") : "0";
	int compId2=Integer.parseInt(complainId);
	//Systemout.println("compId2... .Pre........." + compId2);
	detailVec=rootMaster.getComplaintDetails(ds, compId2);
	
	Vector comment = rootMaster.getCommentsList(ds, compId2);
	//Systemout.println("comment... comment.comment....befor........." + comment.size());
	if(comment.size()!=0)
	{ 			
		for(int i=0;i<comment.size();i++)
		{ 
			//Systemout.println("Comp..if........" + tempComment.size());
			
            Vector temp = (Vector)comment.elementAt(i);
			String comtext=temp.elementAt(0).toString().trim();
			String comdate=temp.elementAt(1).toString().trim();
			String first_name=temp.elementAt(6).toString().trim();
			String last_name=temp.elementAt(7).toString().trim();

			tempComment.add(comtext);
			tempComment.add(comdate);
			tempComment.add(first_name);
			tempComment.add(last_name);
			commentDetailVec.add(tempComment);
			//Systemout.println("Comp... if............." + tempComment);
			
			}
		}
	
	
			
	int Compid1=Integer.parseInt(detailVec.elementAt(18).toString().trim());
	Vector Comp=rootMaster.getCompanyDetail(ds, Compid1);
	//Systemout.println("Comp... Comp.Comp............." + Comp.size());
	//Systemout.println("Comp... Comp.Comp............." + Comp);
	//Systemout.println("Comp... Comp.Comp............." + Comp.elementAt(1));
	int cid=Integer.parseInt(detailVec.elementAt(4).toString().trim());
	String cName=rootMaster.getStrCategoryName(ds, cid);
	//Systemout.println("Comp... cNamecNamecName............." + cName);
	
	detail1.add(Comp.elementAt(1).toString().trim());//Company Name
	int cityId=Integer.parseInt(Comp.elementAt(4).toString().trim());
	String City=rootMaster.getPlaceName(ds, cityId);
	detail1.add(cName);//Category Name
	detail1.add(City);
	
	
	Vector<String> respDataVec = new Vector<String>();
	respDataVec.add(complainId);// complaint Id
	respDataVec.add("advt");
	////Systemout.println("respDataVec>>>"+respDataVec);
	 responseVec = rootMaster.getResponseList(getDataSource(request, "advt"), respDataVec);
		
	
    
	
	
}
 

//Systemout.println("userid...userid............." +uId);

int numCompId  = indvMaster.getCompanyId(getDataSource(request, "advt"), numUId);		
String compId= String.valueOf(numCompId);
String qtype= (request.getParameter("qtype") != null) ? request.getParameter("qtype") : "Resume";
Vector dataVec = new Vector();		
dataVec.add(uId);
dataVec.add(compId);		
dataVec.add(qtype);	

Vector countVec = indvMaster.complaintCount(getDataSource(request, "advt"), dataVec);		
session.setAttribute("countVec", countVec);		
String strShortBy = (request.getParameter("sby")!=null) ? request.getParameter("sby") : "creation_date";
String strSOrder = "";
String strComplaintFId = "asc";
String strComplaintTitle = "asc";
String strComplaintDate = "asc";
if(request.getParameter("sby")!=null)
{
	if(request.getParameter("cidorder")!=null)
	{
		if(request.getParameter("cidorder").trim().equalsIgnoreCase("asc"))
		{
			strSOrder = "desc";
			strComplaintFId = "desc";
		}
		else if (request.getParameter("cidorder").trim().equalsIgnoreCase("desc"))
		{
			strSOrder = "asc";
			strComplaintFId = "asc";
		}
	}
	
	if(request.getParameter("ctorder")!=null)
	{
		if(request.getParameter("ctorder").trim().equalsIgnoreCase("asc"))
		{
			strSOrder = "desc";
			strComplaintTitle = "desc";
		}
		else if (request.getParameter("ctorder").trim().equalsIgnoreCase("desc"))
		{
			strSOrder = "asc";
			strComplaintTitle = "asc";
		}
	}
	if(request.getParameter("cdorder")!=null)
	{
		if(request.getParameter("cdorder").trim().equalsIgnoreCase("asc"))
		{
			strSOrder = "desc";
			strComplaintDate = "desc";
		}
		else if (request.getParameter("cdorder").trim().equalsIgnoreCase("desc"))
		{
			strSOrder = "asc";
			strComplaintDate = "asc";
		}
	}
	
	
	
}
String strFShortBy = strShortBy+" "+strSOrder;
int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
int maxVal = 20;


Vector<String> paramVec1 = new Vector<String>();
paramVec1.add(uId);
paramVec1.add(compId);		
paramVec1.add(qtype);	
	

int totalRow = indvMaster.getComplaintCount1(getDataSource(request, "advt"), paramVec1);

//int totalRow=200;
String strPageHtml = getPages(minVal, maxVal, totalRow,uId,compId,numCount);
Vector<String> paramVec = new Vector<String>();
paramVec.add(uId);
paramVec.add(compId);		
paramVec.add(String.valueOf(minVal));
paramVec.add(String.valueOf(maxVal));		
paramVec.add(strFShortBy);
paramVec.add(qtype);

Vector<String> datavec = new Vector<String>();
datavec.add(String.valueOf(minVal));
datavec.add(String.valueOf(maxVal));		
datavec.add(uId);


Vector userMTlist=new Vector();
Vector userVeclist=new Vector();
 Vector educationVec =new Vector();
 Vector TresultVec =new Vector();
 
 Vector tempEXP = new Vector();
 Vector tempEXP1 = new Vector();

 String Catname="";
 
 Vector expVec=indvMaster.getExpDetails( getDataSource(request, "advt") ,Integer.parseInt(uId));
 for(int i=0; i<expVec.size(); i++){
		 tempEXP = (Vector)expVec.elementAt(i);
		int cid=Integer.parseInt(tempEXP.elementAt(4).toString());
		 Catname=indvMaster.getIcategoryName(ds, cid);
		 tempEXP.add(Catname);
		 tempEXP1.add(tempEXP);
 }
 
 educationVec = rootMaster.getEducationInfo(uId, ds);
	Vector educationVec1=new Vector();
	 for(int i=0; i<educationVec.size(); i++){
		Vector teampVec = (Vector)educationVec.elementAt(i);
		String coursName = "";
		////System.out.println("coursName>>>>>>>>>>"+coursName);
         ////System.out.println("coursName>>>>>>>>>>"+teampVec.elementAt(3).toString());
         ////System.out.println("coursName>>>>>>>>>>"+teampVec.elementAt(4).toString());
         ////System.out.println("coursName>>>>>>>>>>"+teampVec.elementAt(5).toString());
         ////System.out.println("coursName>>>>>>>>>>"+teampVec.elementAt(6).toString());
         ////System.out.println("coursName>>>>>>>>>>"+teampVec.elementAt(7).toString().length());
         String Cname="";
         String Csubname="";
         if(!teampVec.elementAt(2).toString().equalsIgnoreCase("0"))
         {
         	int CurId=Integer.parseInt(teampVec.elementAt(2).toString());
	    		////System.out.println("CurId....mmmmmmmmmm............" +CurId);
	    		Vector CourVec=indvMaster.getCourseName(ds, CurId);
	    		 Cname=CourVec.elementAt(0).toString().trim();
	    		 ////System.out.println("Cname....mmmmmmmmmm............" +Cname);
         }
         
         if(!teampVec.elementAt(4).toString().equalsIgnoreCase("0"))
         {
         
 		int CurStr=Integer.parseInt(teampVec.elementAt(4).toString());
 		////System.out.println("CurStr....mmmmmmmmmm............" +CurStr);
 		Vector CurStrVec=indvMaster.getStreamName(ds, CurStr);
 		Csubname=CurStrVec.elementAt(0).toString().trim();
 		////System.out.println("Csubname....mmmmmmmmmm............" +Csubname);
         }
 		teampVec.add(Cname);
 		teampVec.add(Csubname);
 		educationVec1.add(teampVec);
			}

 
 
if(qtype.equalsIgnoreCase("Mrequest"))
{
	 userMTlist = indvMaster.getMTList(getDataSource(request, "advt"), paramVec);
	 //Systemout.println("qtype...qtype............." +qtype);
	 //Systemout.println("userMTlist...totalRow............." +userMTlist);
}
else if((qtype.equalsIgnoreCase("Personal"))||(qtype.equalsIgnoreCase("Technical")))
{
	 userVeclist = indvMaster.getComplaintList1(getDataSource(request, "advt"), paramVec);
	 //Systemout.println("totalRow...totalRow.....getComplaintList1........" +totalRow);
}
else if(qtype.equalsIgnoreCase("Resume")){
	 userVeclist = indvMaster.getComplaintList1(getDataSource(request, "advt"), paramVec);
	 //Systemout.println("totalRow...uId.. in resume...uId........" +uId);
	  
	  //Systemout.println("totalRow...educationVec.....educationVec........" +educationVec);
}
else if(qtype.equalsIgnoreCase("Ptest")){
	TresultVec = indvMaster.getTestVecList(ds, datavec);	
	userVeclist = indvMaster.getComplaintList1(getDataSource(request, "advt"), paramVec);
	 
}

String txtPrefix="",txtMobile="";   
String date="0",month="0",year="0";


// Data from loginmaster table.

Vector userVec = indvMaster.getUserInfo(getDataSource(request, "advt") ,Integer.parseInt(uId));


if(userVec.elementAt(10).toString().length()>0)
{
	String mobile = (userVec.elementAt(10).toString());
	StringTokenizer stMobile = new StringTokenizer(mobile,"~");	
	txtPrefix = stMobile.nextToken();
	txtMobile = stMobile.nextToken();
}
if(userVec.elementAt(15).toString().length()>0)
{
	String borthDate = (userVec.elementAt(15).toString());
	StringTokenizer stBirth = new StringTokenizer(borthDate,"-");
	year = stBirth.nextToken();
	month = stBirth.nextToken();
	date = stBirth.nextToken();
}


int contryId = Integer.parseInt(userVec.elementAt(11).toString());//dealer contryId id
String contryStr = rootMaster.getCountryName(getDataSource(request, "advt"),contryId);
int stateId = Integer.parseInt(userVec.elementAt(7).toString());//dealer stateId id
String stateStr = rootMaster.getStateName(getDataSource(request, "advt"),stateId);
int cityId = Integer.parseInt(userVec.elementAt(6).toString());//dealer cityId id
String cityStr = rootMaster.getPlaceName(getDataSource(request, "advt"),cityId);

String CId =userVec.elementAt(22).toString();
String StrArr[]= CId.split("~");
int compnyid=Integer.parseInt(StrArr[0]);


String Cname="";
if(compnyid==0)
{
	 compnyid=Integer.parseInt(userVec.elementAt(27).toString());
	 Vector name=indvMaster.getOtherCollege(ds, compnyid);
	 Cname=name.elementAt(0).toString().trim();
}else if(StrArr[1].equalsIgnoreCase("1"))
{
		Cname=rootMaster.getCompnyName(ds, compnyid);
 }
else
{
	Cname = rootMaster.getCollegeName(ds, compnyid);
}
int CurId=Integer.parseInt(userVec.elementAt(23).toString());
Vector CourVec=indvMaster.getCourseName(ds, CurId);
String CourStr=CourVec.elementAt(0).toString();

int CurStr=Integer.parseInt(userVec.elementAt(24).toString());
Vector CurStrVec=indvMaster.getStreamName(ds, CurStr);
String MajorStr=CurStrVec.elementAt(0).toString();
//Systemout.println("CnameCnameCname>in Action>>>outer>>>>>>"+Cname);
Vector extraVec = new Vector();

extraVec.add(txtPrefix);
extraVec.add(txtMobile);
extraVec.add(year);
extraVec.add(month);
extraVec.add(date);
extraVec.add(contryStr);
extraVec.add(stateStr);
extraVec.add(cityStr);
extraVec.add(Cname);
extraVec.add(CourStr);
extraVec.add(MajorStr);

request.setAttribute("userVec", userVec);
request.setAttribute("extraVec", extraVec);
request.setAttribute("userVeclist", userVeclist);
request.setAttribute("userMTlist", userMTlist);
request.setAttribute("educationVec", educationVec1);
request.setAttribute("TresultVec", TresultVec);
request.setAttribute("detailVec", detailVec);
request.setAttribute("commentVec", commentDetailVec);
request.setAttribute("detailVec1", detail1);
request.setAttribute("responseVec", responseVec);
request.setAttribute("tempEXP", tempEXP1);


// end  Data from loginmaster table.

//Data from `consumer_data` and `consumer_mapping` table.

Vector resultVec = entpMaster.getCommunityUnnamedMap( getDataSource(request, "advt") ,numCompId);	
request.setAttribute("resultVec", resultVec);


Vector consumerUnName = new Vector();
if(resultVec.size()!=0){

consumerUnName = rootMaster.getUserUnNameInfo(getDataSource(request, "advt"), numUId, numCompId );
request.setAttribute("consumerUnName", consumerUnName);


}



//end Data from `consumer_data` and `consumer_mapping` table.

//Systemout.println("userVec......**........"+userVec);
//Systemout.println("extraVec....**......."+extraVec);
//Systemout.println("resultVec......**........"+resultVec);
//Systemout.println("consumerUnName....**......."+consumerUnName);

return mapping.findForward("success");

}
private String getPages(int minVal, int maxVal, int numSize ,String uId,String compId,String numCount)
{
String strResult ="<select name=\"paging\" onchange=\"retrieveIndvEComplistURL('existingComplaint.do?uId="+uId+"&compId="+compId+"&numCount="+numCount+"&'+this.value);\">";
int numMin = 0;
int numPage = 1;
for(int i=0; i<numSize; i=i+maxVal)
{
	numMin = i;
	
	if(minVal == numMin)
	{
		strResult += "<option value=\"numMin=" + numMin+"\" Selected>Page" + numPage + "</option>";
	}
	else
	{
		strResult += "<option value=\"numMin=" + numMin+"\">Page" + numPage + "</option>";
	}
		numPage++;
}
strResult += "</select>";
return strResult;
}


}
