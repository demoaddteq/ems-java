package com.mm.struts.student.action;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mm.core.master.RootMaster;
import com.mm.core.master.IndvMaster;


public class TestResultAction  extends Action{

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
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception  
	{
		
		
			
			Vector <String> datavec = new Vector<String>();		
			DataSource ds = getDataSource(request,"student");
			IndvMaster indvMaster = new IndvMaster();
			RootMaster rootMaster = new RootMaster();
			////////System.out.println("....llllllllllllll........");
			
			HttpSession session = request.getSession();
			String uId = (String)session.getAttribute("uId");
			String numCount = (String)session.getAttribute("numCount");
	
			int count = Integer.parseInt(numCount);
			int remander = count%2;
			String testId =(request.getParameter("testId")!=null) ? request.getParameter("testId") : "0";
			String pageid = (request.getParameter("pageid") != null) ? request.getParameter("pageid") : "1" ;
			String strCid = (request.getParameter("catid") != null) ? request.getParameter("catid") : "0";
			String strFinalStrCat = "";
			//System.out.println("testId......."+testId);
			//System.out.println("pageid......."+pageid);
			
			Vector<String> paraVec = new Vector<String>();
			
			String testType = "";
			if(pageid.equalsIgnoreCase("1")){
				
				testType = "Personality";
			}else if(pageid.equalsIgnoreCase("2")){
				
				testType = "SoftSkills";
			}else if(pageid.equalsIgnoreCase("3")){
				
				testType = "Aptitude";
			}else if(pageid.equalsIgnoreCase("4")){
				
				testType = "TechnicalSkills";
			}
			
			paraVec.add(testType);
			
			Vector testComListVec = rootMaster.getTestComListVec(ds, paraVec, Integer.parseInt(strCid.trim()));
			System.out.println("testComListVec......."+testComListVec);
			String compIdTeamp = "0";
				
			if(testComListVec.size()>0){
			Vector teampVec = (Vector)testComListVec.elementAt(0);
			 compIdTeamp = teampVec.elementAt(0).toString();
			}
			
			String compId = (request.getParameter("compId") != null) ? request.getParameter("compId") : "0";			
			if(compId.equalsIgnoreCase("0")){
				compId = compIdTeamp;
			}
			
			paraVec.add(compId);
			//System.out.println("compId......."+compId);
			if(pageid.equalsIgnoreCase("4")){
				
				testType = "TechnicalSkills";
				HashMap catList = (HashMap)session.getAttribute("AllTestCategory");
				
				//if(request.getParameter("catname")!=null)
				//{
					strFinalStrCat = getSubCatList(Integer.parseInt(strCid.trim()), testType, catList, Integer.parseInt(compId.trim()), Integer.parseInt(pageid.trim()));
				/*}
				else
				{
					strFinalStrCat ="<select name=\"testsubcat\" class=\"input_combo_box\" tabindex=\"10\"  onchange=\"retrieveTRlistURL('testResult.do?pageid="+pageid+"&compId="+compId+"&catid='+this.value);\">";
					strFinalStrCat += "<option value=\"0\" Selected>Select</option>";
					strFinalStrCat += "<option value=\"-1\">Others</option>";
					strFinalStrCat += "</select>";
				}*/
			}
			Vector testListVec = rootMaster.getTestListVec(ds, paraVec);
			//System.out.println("testListVec......."+testListVec);
			
			int minVal = (request.getParameter("numMin")!=null) ? Integer.parseInt(request.getParameter("numMin")) : 0;
			int maxVal = 20;
		 
			
			Vector<String> dataVec = new Vector<String>();
            datavec.add(String.valueOf(minVal));	
            datavec.add(String.valueOf(maxVal));	
	        datavec.add(uId);
	        datavec.add(testId);	
	        
			
	        Vector resultVec = indvMaster.getTestVecdetail(ds, datavec);
	        //System.out.println("resultVec...resultVec...."+resultVec);
	        
	        String remarkStr = rootMaster.getTestremarks(ds, testId);
	        //System.out.println("remarkStr...resultVec...."+remarkStr);
			
			//int totalRow = indvMaster.getTestVecCount(getDataSource(request, "student"), datavec);
			////System.out.println("resultVec...resultVec...."+resultVec);
			//int totalRow=500;
			String strComp = getCompanyList( testComListVec,  compId , Integer.parseInt(numCount.trim()), pageid);
			//System.out.println("strComp......."+strComp);
			
			
			String testText = (request.getParameter("testText") != null) ? request.getParameter("testText") : "defalt";			
			//System.out.println("testText......."+testText);
			
			
			String strTest = getTestVecList( testComListVec, minVal, strComp, remarkStr, resultVec, testListVec,  remander, uId, pageid ,testId, compId, testText, strFinalStrCat);
			//////////System.out.println("Users "+strUsers);
			/**
		     * setContentType - text/html to write String on page.
		     * PrintWriter - This line use to get writer to write on page.
		     * out.println - use to write string. 
		     * 
		     */
			response.setContentType("text/html;charset=ISO-8859-1");
		    PrintWriter out = response.getWriter();
		    //out.println(strComplaints);
		    out.write(strTest);
		    out.flush();
		    out.close();
			return null;
		}
		
		private String getTestVecList(Vector testComListVec, int minVal, String strComp,String remarkStr, Vector resultVec, Vector testListVec, int remander, String uId,String pageid,String testId, String compId, String testText, String strFinalStrCat)
		{	String strValue="";
			if(testComListVec.size()<2 && Integer.parseInt(pageid.trim())!=4)
			{
				 strValue="<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"300\">";
				  strValue+="<tr valign=\"middle\">";
				    strValue+="<td scope=\"col\" valign=\"middle\"><H2> Coming Soon ...... </H2></td>";
				   
				  strValue+="</tr>";
				  strValue+="</table>";
			}else if(testText.equalsIgnoreCase("evaluation")){
				
				 strValue+="<tr>";
			        strValue+="<TD colspan=\"3\"  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_Narrow_12_black_bold\">Test Evaluation: </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
			      strValue+="</tr>";
				
				strValue+="<tr>";
			    strValue+="<td width=\"5%\" scope=\"col\">&nbsp;</td>";
			    strValue+="<td width=\"90%\" scope=\"col\"><div style = \"height:350px;  overflow-y:scroll;\" ><table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >";
			   
				
				 
        		
        		strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">Every question has 2 possible replies. Each reply is attached to a specific personality type, from 9 personality types of Enneagram System. Depending upon the reply, the score for that personality type would increase by 1. </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">If your highest score is much higher (3-4 points or more) than the rest of your scores, this is most likely your basic personality type. If the descriptions of this type do not fit you, there are several other possibilities to consider: </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      
		      
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td width=\"10%\" ALIGN=\"right\" VALIGN=\"top\"><li> </td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">If you have a number of close ties, you could be a Three, a Six, or a Nine.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</DIV></td><td width=\"10%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td width=\"10%\" ALIGN=\"right\" VALIGN=\"top\"><li> </td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">If you have a scattered distribution of scores across three to five types so that there doesn't seem to be any discernible pattern, you could be a Six. </DIV></td><td width=\"10%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td width=\"10%\" ALIGN=\"right\" VALIGN=\"top\"><li> </td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">If you are a woman and your Two score is highest, look at your next two high scores—women are often taught to play the role of the Two whether it is their basic type or not. </DIV></td><td width=\"10%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="<tr height=\"20\">";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">  </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      strValue+="<tr height=\"20\">";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">  </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      strValue+="<tr height=\"20\">";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">  </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
        		
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">Please note: If you relate very strongly to both the alternatives, or if you do not relate to any of the replies, please skip that question. </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">  </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		     
		      
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">  </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="</table></div>";
		      strValue+="</td>";
		      strValue+="<td width=\"5%\" scope=\"col\">&nbsp;</td>";
		      strValue+="</tr>";
		      
		      strValue+="<tr>";
		        strValue+="<TD colspan=\"3\"  width=\"100%\"  ALIGN=\"center\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"> <input type=\"button\" value=\"Back\" name=\"Cancel\"Class=\"buttonCopy\"  onclick=\"back()\" /> </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
        	}else if(testText.equalsIgnoreCase("type")){
        		
        		  strValue+="<tr>";
			        strValue+="<TD colspan=\"3\"  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_Narrow_12_black_bold\">Enneagram Type:</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
			      strValue+="</tr>";
			      
        		
        		
        		strValue+="<tr>";
			    strValue+="<td width=\"5%\" scope=\"col\">&nbsp;</td>";
			    strValue+="<td width=\"94%\" scope=\"col\"><div style = \"height:350px;  overflow-y:scroll;\" ><table width=\"95%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >";
			   
				
       		
       		strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">Enneagram is a powerful tool for self-awareness, emotional healing and personal transformation. Although Enneagram has been known to spiritual seekers for centuries, the Enneagram has only become known to the general public within the last thirty years or so. It is increasingly being used as a powerful tool for spiritual growth, relationship building, and executive coaching. The Enneagram is a personality system that describes nine basic types. It explains the strengths and limitations of nine specific strategies people use to interact with each other and the world. It describes how those strategies look when people are functioning in a normal manner and when they are under stress. </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">The origins of the Enneagram go back many centuries. Though no one is sure when the first Enneagram figure was drawn, it is thought to have its roots in ancient Greece—thus its name: ennea is Greek for nine; gram means drawing. The drawing itself consists of a triangle and a hexagon enclosed within a circle.. In the early 1970s, the application of this diagram to understanding personality began to grow in popularity, namely through the work of Claudio Naranjo, M.D., a noted Gestalt psychologist. </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      
		      
		      strValue+="<tr height=\"20\">";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">  </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      strValue+="<tr height=\"20\">";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Type One-The Perfectionist:</strong> Strives to be perfect and strives not to be irresponsible or careless. Perfectionists are models of decorum, clear logic and appropriate behavior. They focus on rules, procedures and making sure that they are always doing the \"right thing.\" Under stress they can become critical, judgmental, unwilling to take risks, and fear that having \"too much\" fun is irresponsible.</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      strValue+="<tr height=\"20\">";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Type Two-The Giver: </strong> Strives to be connected to others and strives not to be physically or emotionally isolated. Givers are selfless, caring and nurturing. They focus on helping others meet their needs; they easily build rapport and find connection with others. Under stress they fail to care for their own needs, end up becoming emotionally dependent on others, and fear that if they are not closely connected to others they will become isolated.   </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="</tr>";
		      strValue+="<tr height=\"20\">";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Type Three-The Producer: </strong>Strives to be outstanding and strives not to be mediocre or average. Producers work hard to exceed standards and to be successful in whatever they undertake. They place high value on productivity and presenting an image of being a winner. Under stress they become attention seeking, may value image over substance, and fear that if they are not making great efforts to be excellent they will become mediocre. </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="</tr>";
		      strValue+="<tr height=\"20\">";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Type Four-The Connoisseur: </strong>Strives to be unique and strives not to be plain, typical, or mundane. Connoisseurs are creative and approach their lives in fresh and interesting ways. They gravitate toward things and experiences that are elegant, refined, or unusual. Under stress they feel misunderstood, withdraw from others, and fear that if they don't put their own special touch on their world and their experiences their individuality will become repressed.  </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="</tr>";
		      strValue+="<tr height=\"20\">";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Type Five-The Observer: </strong>Strives to be detached and strives not to be reckless, emotional, and uncontrolled. Observers are observant, logical and generally reserved. They focus on problem solving, innovative ideas, and data gathering. Under stress they can end up being dull, out of touch with their experiences and emotions, and fear that if they do not remain detached and guarded they will become uncontrolled.  </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="</tr>";
		      strValue+="<tr height=\"20\">";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Type Six-The Guardian: </strong>Strives to be secure and strives not to let their guard down and be passive. Guardians find security in being part of something bigger than himself or herself, such as a group or tradition. They are careful, responsible and protective of the welfare of the group. They focus on maintaining consistency, tradition and cohesion. Under stress they may fail to take the risks necessary for high performance and settle for mediocrity and fear that if they relax their guard they will be vulnerable to possible dangers.</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="</tr>";
		      strValue+="<tr height=\"20\">";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Type Seven-The Enthusiast: </strong>Strives to be excited and strives not to be bored or boring. Enthusiasts are upbeat, enthusiastic, optimistic and curious. They focus on possibilities and options and keeping others entertained. Under stress they fail to follow-through, become easily distracted and irresponsible and fear that they may miss out on something and end up feeling empty.  </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="</tr>";
		      strValue+="<tr height=\"20\">";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Type Eight-The Challenger: </strong>Strives to be powerful and strives not to be vulnerable or dependent on others. Challengers are action-oriented self-starters who love to be in charge. They focus on getting things done and overcoming obstacles. Under stress they may not adhere to the rules that others expect them to follow and their behavior can become uncontrolled. They fear that if they become too connected to others or experience their own emotions too deeply they will become dependent on others.  </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="</tr>";
		      strValue+="<tr height=\"20\">";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Type Nine-The Peacemaker: </strong>Strives to be peaceful and strives not to seek attention or express needs. Peacemakers are calm, pleasant, self-effacing, likeable, and charming. They focus on maintaining inner harmony by minimizing their needs and concentrating on the needs of others. Under stress they overlook threats to their security or success, become vulnerable and fear that if they place too much importance on themselves they will be seen as attention seeking.  </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="</tr>";
		      strValue+="<tr height=\"20\">";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>One in Love <br> Living with Ones: </strong><li>Do remember details. Ones are detail conscious. They appreciate small gestures: being on time, remembering names, proper introductions. <li>Speak respectfully. Make sure no one looks foolish. Ask for permission.<li>Admit error immediately. Admission clears the air and prevents resentment.<li>Avoid power struggles. Ones need to be right. There are at least two right ways.<li>Maintain your own interests. Ones work long hours on their own.<li>Ones perfect relationships. \"What are our responsibilities?\" \"What are we learning?\" \"What does right relating mean?\"<li>Scorched-earth policy. If the relationship develops a negative aspect, Ones think about calling the whole thing off. Relationships seem either black or white. </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      
       		
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Two in Love <br> Living with Twos: </strong><li>Wants to be the central figure in your life. \"I don't need you, but you depend on me.\"<li>Learn to recognize the Two's manipulation tactics, complaints and guilt, for example. The Two will try to maneuver you into doing what he or she wants.<li>Their heavy emphasis on relationship makes Twos vulnerable to rejection and loss.  Expect big emotions. Anger and rising hysteria are signals of unmet needs. Twos may not know what they want, but they can get hysterical if they don't get it.<li>Hysterical laughter, hyperactivity, and flirtations cover insecurity about the Two's own needs.<li>Be sensitive to the Two's likely inexperience with real intimacy. Sexual and emotional feelings have been repressed in the interests of altering to attract attention. \"I can please you, but what do I really feel for you?\"<li>Beware: Twos are attracted to relationships with obstacles. Obstacles forestall having to face the confusion that surrounds an available, intimate relationship.<li>Twos like to triangulate, to be attracted to some \"great man\" or inspiring woman (\"muse\") while being involved in a more available romance. That way they can hedge on commitment, not risk rejection so much.    </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Three in Love <br> Living with Threes: </strong><li>Threes feel loved for their achievements, not for who they are.<li>The Three frames the relationship as an \"important task\" that can be built.<li>The Three expects appreciation from a mate for a winning image and style.<li>Be aware of your Three's tendency to \"do\" feelings, for activity to replace affect, and to adopt the role of the perfect lover with a script of endearing things to say.<li>Your Three partner will be intolerant of \"darker\" emotions. Wants to tune out negative feedback. \"Let's stay energetic and happy,\" \"Let's do something together,\" \"Let's have fun.\"<li>Understand that your Three can readily confuse ideas about emotions with the real thing.<li>Three partners need to be assured that they are loved for themselves, not as the prototype of the perfect mate.<li>A Three's heart is in his or her work. The Three will therefore need a strong push from a partner to take time away from work.    </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Four in Love <br> Living with Fours: </strong><li>Remember that Fours feel that something is missing. Others have what's missing. Focused on the quality of feeling in other people's relationships, the Four worries: \"They have it. I don't.\"<li>You can easily be dismayed by your Four's attraction to the distant and the unavailable, positive attention to whoever is missing: the ghostly lover, the distant friend, the unfulfilled dream.<>liCount on complex relating. Nothing is simple. Depth is the goal rather than fun.<li>For Fours, it's the pursuit not the happiness that matters: a refined and bittersweet emotional sensibility. A mood of melancholy. Love is many layered and goes through many phases. The stages of letting go are unusually slow.<li>Fours sweetly reminisce about people from the past and focus on lovers, experiences yet to come. Attention on present opportunities is weak and intermittent.<li>Push-pull habit of attention. The Four's focus turns to your negative aspects when you are present and to positive aspects with the safety of distance. </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Five in Love <br> Living with Fives: </strong><li>Because Fives have delayed reactions, their feelings can surface when they're alone. They find intimacy in private reverie. Great tenderness can develop without the need for words or prolonged personal contact.<li>Fives' cycle of withdrawal can lead to feelings of isolation and the desire to have others draw them out. They are caught between wanting contact and wanting to go.<li>Intimacy can stimulate detachment. Significant may get the message \"I can still do without you,\" or \"I'm committed, but I won't live with you.\"<li>You may be compartmentalized, separated from other aspects of the Five's life.<li>Expect a Five to express intimacy in nonverbal ways. Fives sense that feelings can surface more easily if they need not be spoken.<li>Noninvolvement is the Five's habitual emotional stance. Partners should therefore read \"negative\" feelings such as anger, jealousy, and competition, as well as \"positive\" feelings like sexuality and tenderness, as possible signs of increasing connection.   </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Six  in Love <br> Living with Sixes: </strong><li>Sixes question your intentions: suspecting your positive regard, wondering what you really think, and undervaluing romance.<li>A Six can be a loyal ally, strong in an \"us against the world\" relationship, a devoted supporter.<li>Sixes want reassurance to overcome doubt. \"Will you always love me?\" There's no right answer for this one. A positive response leads to doubt of your sincerity, further assurances are required, and so on.<li>Sixes tend to project personal dissatisfaction, for instance, denying their own wandering eye by \"seeing\" that you are attracted to someone else.<li>Expect a Six to identify with the problem areas of relationship, which become the focal points of attention.<li>· Don't count on Sixes to be able to locate the source of tension in intimacy. \"Am I afraid of showing weakness? Am I sensing a possible betrayal?\" They expect hurt when their guard goes down.<li>A Six searches for clues in your behavior. \"What's going on underneath the surface? How do you act toward other people? What do you really think of me?\" They need reassurance.    </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Seven  in Love <br> Living with Sevens: </strong><li>The main problem is getting a Seven to see the problem.<li>An ideal mate is someone who adores the Seven and will keep the Seven company while he or she has a good time.<li>Sevens want high levels of stimulation, adventure, and multiple options of activity. Because they have great difficulty staying with negative feelings, they'll want to diffuse disagreement and sweeten the situation. \"Shouldn't we go to dinner and a show?\"<li>Sevens want to be with partners who mirror their own high self-image.<li>Sevens are pleasant when you admire them. But they'll ridicule or discount you or the situation when they're challenged or placed in an inferior position. They make nice or make fun of.<li>Acutely sensitive to boredom and repetition in relationships, Sevens can adopt new interests and maintain a charming lifestyle to keep the spark alive.<li>Sevens become acutely aware of the limitations when you call for commitment. They can live in committed relationships for decades and still be uneasy with the concept. Long-term commitments are \"a process\" and an adventure. </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Eight  in Love <br> Living with Eights: </strong><li>Eights like partners who are independent and strong, and they enjoy fighting, sex, and adventure as ways of making contact. Because Eights experience deep joy in sexuality, they are willing to match the partner's intensity.<li>Their lust for life and desire for stimulation mean late hours, heavy entertainment, and binges. Too much, too loud, too many. If something's good then they want more of it.<li>Eights' tendency toward excess, all or nothing, all work and no play, or all play and nothing gets done, may burden the partner with the task of keeping the different areas of life in balance.<li>Eights need control and will therefore want to predict your intentions.<li>Their fear of being controlled displaces into the territorial control of schedules, personal objects, and physical space.<li>Because Eights cannot tolerate ambiguity or lack of information, your small oversights may be perceived as a betrayal of trust. They may feel that you've overlooked their options or left them out of a decision.<li>Eights rarely allow themselves to be hurt by others. If you hurt them emotionally, they will want to manipulate circumstances in order to get back. Thoughts of revenge will forestall their feelings of vulnerability.<li>Partners will find Eights to be rallying points during difficulty, towers of strength in dangerous times.  </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Nine  in Love <br> Living with Nines: </strong><li>Once a Nine merges with you it is hard to separate. Relationships can continue for years beyond the natural stopping point. Nines find it hard to give up memories of old relationships so that new ones can develop.<li>You'll find that Nines divert attention from feelings by becoming preoccupied with nonessentials. They search for alternatives to forestall arguments. They are often laconic and uncommunicative about what they really feel: \"Let the unspoken remain unsaid.\"<li>Nines retreat into habitual patterns and trivial concerns (\"lots of little things to do\") rather than really engaging in the relationship. Energy spreads to the mechanics of living together: the house repairs, the mortgage rate. As a Nine's partner, you will find yourself being the active agent for change.<li>The Nine will say back what you want to hear. This does not imply that the Nine agrees with you. It's hard for Nines to say no because your needs sound louder than their own.<li>Nines fantasize about merging with ideal partners and being swept into a new life. The flip side of merging with the lives of others is that the Nine blames you when things go wrong.   </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		     
		      
		      strValue+="<tr>";
		        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">  </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
		      
		      strValue+="</table></div>";
		      strValue+="</td>";
		      strValue+="<td  >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>";
		      strValue+="</tr>";
		      
		      strValue+="<tr>";
		        strValue+="<TD colspan=\"3\"  width=\"100%\"  ALIGN=\"left\" ><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"> <input type=\"button\" value=\"Back\" name=\"Cancel\"Class=\"buttonCopy\"  onclick=\"back()\" /> </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
		      strValue+="</tr>";
       	}			
			else
			{
				 strValue="<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" >";
				if(resultVec.size()==0)
				{
					//System.out.println("resultVec...In....IF....");
					
					strValue+="<table width=\"100%\" height=\"263\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
					  strValue+="<tr bgcolor=\"#E6E6E6\">";
					    strValue+="<td scope=\"col\">&nbsp;</td>";
					    strValue+="<td scope=\"col\" align=\"left\" class=\"Arial_Narrow_12_black_bold\">&nbsp;&nbsp;&nbsp;</td>";
					    strValue+="<td scope=\"col\">&nbsp;</td>";
					    strValue+="<td scope=\"col\"align=\"left\" class=\"Arial_Narrow_12_black_bold\">&nbsp;&nbsp;Test(s) List&nbsp;</td>";
					    strValue+="<td scope=\"col\">&nbsp;</td>";
					  strValue+="</tr>";
					  strValue+="<tr>";
					    strValue+="<td width=\"1%\" scope=\"col\">&nbsp;</td>";
					    strValue+="<td width=\"59%\" scope=\"col\"><div style = \"height:350px; overflow-y:scroll;\" ><table width=\"100%\"   border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
					   
					    strValue+="<tr>";
				        strValue+="<td width=\"26%\" height=\"15\" valign=\"top\" scope=\"col\" align=\"right\" class=\"Arial_Narrow_12_black_bold\"></td>";
				        strValue+="<td width=\"74%\" valign=\"top\" scope=\"col\" class=\"Arial_12_black_normal\" align=\"left\"></td>";
				        strValue+="</tr>";
				        
				        if((pageid.equalsIgnoreCase("4"))&&(remarkStr.equalsIgnoreCase("failure"))){
				        	
				        strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">To take a test, select a company from the company drop-down list at the right side. All the available tests from the selected company would be displayed as links. You can choose to take a test by clicking on the link.</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					      
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">Answer each question carefully, since you will not be able to review or change your answers, once you submit them.</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">In this assessment, you may encounter questions of the following types: </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><li>Single Choice <li>Multiple Choice</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">For each <strong>Single Choice </strong> question, you must select the one correct answer, in order to score marks.</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">For each <strong>Multiple-choice</strong> question, you must select correct answers from the choices, in order to score marks.</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">To make it more clear to you, here are few examples:</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Single choice question:</strong></DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">Q: You can code any program in java without declaring classes</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"> a) True <br> b) False</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">We have only one correct answer, which is b</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>Q: Multiple Choice question:</strong></DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">The important properties of OOPS are - </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">a) Encapsulation, Inheritance<br>b) Polymorphism	<br>c) Platform Independent	<br>d) Database connectivity</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">We have two correct answers a and b, so marks would be granted when the user selects the any/both right answers. </DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">For each correct answer, scores are given, set by the individual corporate. For wrong answers there is no negative marking, however no marks as well.</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"><strong>It is important that you take the test without any interruptions, or disconnections because it could affect your results.</strong></DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					    
					    strValue+="<tr>";
					    strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\"></DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					    strValue+="</tr>";
					      
				        }				       	
				        else if(remarkStr.equalsIgnoreCase("failure")){
				        					        
					      strValue+="<tr>";
					        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">To take a test, select a company from the company drop-down list at the right side. All the available tests from the selected company would be displayed as links. You can choose to take a test by clicking on the link.</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					      strValue+="</tr>";
					      
					      
					      strValue+="<tr>";
					        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">Personality tests are based on Enneagram system. The Enneagram is a vital link between the psyche and the spirit. It is a conceptual system, a theory of 9 personality types that is complex and sophisticated, and yet is a sensible and easily understood tool for self-discovery. The nine points represent the ways in which the nine different personality personality <a href=\"testResult.jsp?pageid="+pageid+"&testText=type\"> types </a> perceive and defend their realities.</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					      strValue+="</tr>";
					      
					      
					      strValue+="<tr>";
					        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">The <a href=\"testResult.jsp?pageid="+pageid+"&testText=type\"> types </a> are distinguished by unconscious motivations and preoccupations, which produce patterns of perception, feeling, and behavior, which can be gifts or obstacles to the personality.</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					      strValue+="</tr>";
					      
					      strValue+="<tr>";
					        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">To know more about Enneagram System, <a href=\"testResult.jsp?pageid="+pageid+"&testText=type\"> click here.</a></DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					      strValue+="</tr>";
					      
					      strValue+="<tr>";
					        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">To know how Enneagram tests are evaluated, <a href=\"testResult.jsp?pageid="+pageid+"&testText=evaluation\"> click here.</a></DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					      strValue+="</tr>";
				        	
				        	
				        }else{
					      strValue+="<tr>";
					        strValue+="<TD  width=\"100%\"  ALIGN=\"left\" class=\"Arial_12_black_normal\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\" class=\"Arial_12_black_normal\">"+remarkStr+"</DIV></td><td width=\"5%\"></td></tr></table></td>";				       
					      strValue+="</tr>";
					      
					      
					      strValue+="<tr>";
					        strValue+="<td width=\"100%\" height=\"20\"  ></td>";				       
					      strValue+="</tr>";
					      
					      
					      
					      strValue+="<tr>";
					        strValue+="<td width=\"100%\" valign=\"top\"  scope=\"col\" align=\"center\" class=\"Arial_Narrow_16_orange_normal\"> <table><tr><td width=\"35%\"></td><td bgcolor=\"#E6E6E6\" width=\"30%\"><A href=\"personalityTestPre.do?pageid="+pageid+"&testId="+testId+"\"> Take a test..  </A></td><td width=\"35%\"></td></tr></table></td>";
					       
					      strValue+="</tr>";
				        }
					     
						
					      
					    strValue+="</table></div></td>";
					    
					    /////////////////////////////////////////////////////////////
					    strValue+="<td width=\"1%\" scope=\"col\">&nbsp;</td>";
					    /////////////////////////////////////////////////////////////
					    strValue+="<td width=\"38%\" scope=\"col\"><div style = \"height:350px; overflow-y:scroll;\" ><table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
					    strValue+="<tr height=\"15\">";
				        strValue+="<td scope=\"col\"&nbsp;  align=\"left\" class=\"Arial_Narrow_12_black_bold\"></td>";
				        strValue+="</tr>";
				      /**
				       * --------------------------------------------------------------------
				       */
				        if(Integer.parseInt(pageid.trim())==4)
				        {
					        strValue += "<tr >";
							strValue += "<td colspan=\"5\" width=\"38%\" class=\"Arial_12_black_normal\">Category:  "+strFinalStrCat+"</td>";		
							strValue += "</tr>";
				        }
				        strValue += "<tr >";
						
						if(testComListVec.size()<2)
						{
							strValue += "<td colspan=\"5\" width=\"38%\" class=\"Arial_12_black_normal\">No Test Yet.</td>";
						}
						else
						{
							strValue += "<td colspan=\"5\" width=\"38%\" class=\"Arial_12_black_normal\">Company:  "+strComp+"</td>";
						}
						strValue += "</tr>";
						
						strValue += "<tr >";
						strValue += "<td colspan=\"5\" width=\"38%\" class=\"Arial_12_black_normal\"></td>";		
						strValue += "</tr>";
						
						 strValue+="<tr height=\"5\">";
					      strValue+="<td height=\"5\" width=\"38%\"></td>";
					      strValue+="</tr>";
				        
				        for(int i=0; i<testListVec.size(); i++){
					
					Vector teampDataVec = new Vector();
					teampDataVec = (Vector)testListVec.elementAt(i);
					
					String testIdteam = teampDataVec.elementAt(0).toString().trim();
					String testNameteam = teampDataVec.elementAt(2).toString().trim();
					
				      
					if(testIdteam.equalsIgnoreCase(testId)){
			      strValue+="<tr >";
			      strValue+="<td  width=\"38%\"  bgcolor=\"#ee8126\" align=\"left\" class=\"Arial_Narrow_12_black_bold\">&nbsp;<A href=\"testResult.jsp?testId="+testIdteam+"&compId="+compId+"&uId="+uId+"&pageid="+pageid+"&type=Student\">"+testNameteam+" </A>&nbsp;</td>";
			      strValue+="</tr>";
			      
			      strValue+="<tr height=\"5\">";
			      strValue+="<td height=\"5\" width=\"38%\"></td>";
			      strValue+="</tr>";
			      
					}else{
				  strValue+="<tr >";
			      strValue+="<td    width=\"38%\"  bgcolor=\"#a5cdbc\" align=\"left\" class=\"Arial_Narrow_12_black_bold\">&nbsp;<A href=\"testResult.jsp?testId="+testIdteam+"&compId="+compId+"&uId="+uId+"&pageid="+pageid+"&type=Student\">"+testNameteam+" </A>&nbsp;</td>";
			      strValue+="</tr>";	
			      
			      strValue+="<tr height=\"5\">";
			      strValue+="<td height=\"5\" width=\"38%\"></td>";
			      strValue+="</tr>";
					}
					      
		 }
					      
					    strValue+="</table><div></td>";
					    strValue+="<td width=\"1%\" scope=\"col\">&nbsp;</td>";
					  strValue+="</tr>";
					strValue+="</table>";
					
				}
				else
				{
					//System.out.println("resultVec...In....ELSE....");
					
						strValue+="<table width=\"100%\" height=\"263\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
						  strValue+="<tr bgcolor=\"#E6E6E6\">";
						    strValue+="<td scope=\"col\">&nbsp;</td>";
						    strValue+="<td scope=\"col\" align=\"left\" class=\"Arial_Narrow_12_black_bold\">&nbsp;&nbsp;Test Detail&nbsp;</td>";
						    strValue+="<td scope=\"col\">&nbsp;</td>";
						    strValue+="<td scope=\"col\"align=\"left\" class=\"Arial_Narrow_12_black_bold\">&nbsp;&nbsp;Test List&nbsp;</td>";
						    strValue+="<td scope=\"col\">&nbsp;</td>";
						  strValue+="</tr>";
						  strValue+="<tr height=\"15\">";
						    strValue+="<td width=\"1%\"  scope=\"col\">&nbsp;</td>";
						    strValue+="<td width=\"59%\" scope=\"col\"><div style = \"height:350px; overflow-y:scroll;\" ><table width=\"100%\"   border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
						   
						    strValue+="<tr height=\"15\">";
					        strValue+="<td width=\"26%\" valign=\"top\" scope=\"col\" align=\"right\" class=\"Arial_Narrow_12_black_bold\"></td>";
					        strValue+="<td width=\"74%\" valign=\"top\" scope=\"col\" class=\"Arial_12_black_normal\" align=\"left\"></td>";
					        strValue+="</tr>";
					        //System.out.println("resultVec...resultVec...."+resultVec);
								
								 String strTTestName = resultVec.elementAt(4).toString().trim();
							     String strMMaxMarks = resultVec.elementAt(1).toString().trim();
							     String strOObtainedMarks = resultVec.elementAt(2).toString().trim();
							     String strAAnalysis = resultVec.elementAt(3).toString().trim();
							     String dateStr = resultVec.elementAt(5).toString().trim();
							     
							     String flag = resultVec.elementAt(6).toString().trim();
							     System.out.println("On Action......."+flag);
							     
							     
								    strValue+="<tr>";
							        strValue+="<td width=\"26%\" valign=\"top\" scope=\"col\" align=\"right\" class=\"Arial_Narrow_12_black_bold\"></td>";
							        strValue+="<td width=\"74%\" valign=\"top\" scope=\"col\" class=\"Arial_12_black_normal\" align=\"left\"></td>";
							        strValue+="</tr>";
							        
							      
							  String arrDate[] = dateStr.split("-");							  
							  String date1 = arrDate[2]+"-"+arrDate[1]+"-"+arrDate[0];
						      
						      strValue+="<tr>";
						        strValue+="<td width=\"26%\" valign=\"top\"  scope=\"col\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">Date:&nbsp;</td>";
						        strValue+="<td width=\"74%\" valign=\"top\" scope=\"col\" class=\"Arial_12_black_normal\" align=\"left\">"+date1+"</td>";
						      strValue+="</tr>";
						      
						      strValue+="<tr>";
						        strValue+="<td width=\"26%\" valign=\"top\" scope=\"col\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">Test Name:&nbsp;</td>";
						        strValue+="<td width=\"74%\" valign=\"top\" scope=\"col\" class=\"Arial_12_black_normal\" align=\"left\">"+strTTestName+"</td>";
						      strValue+="</tr>";
						      
						      
						      if(!strMMaxMarks.equalsIgnoreCase("N/A"))
						      {
						      strValue+="<tr>";
						        strValue+="<td width=\"26%\"  valign=\"top\" scope=\"col\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">Max Marks:&nbsp;</td>";
						        strValue+="<td width=\"74%\" valign=\"top\" scope=\"col\" class=\"Arial_12_black_normal\" align=\"left\">"+strMMaxMarks+"</td>";
						      strValue+="</tr>";
						      }
						      if(!strOObtainedMarks.equalsIgnoreCase("N/A"))
						      {
						      strValue+="<tr>";
						        strValue+="<td width=\"26%\" valign=\"top\"  scope=\"col\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">Obtained Marks:&nbsp;</td>";
						        strValue+="<td width=\"74%\" valign=\"top\" scope=\"col\" class=\"Arial_12_black_normal\" align=\"left\">"+strOObtainedMarks+"</td>";
						      strValue+="</tr>";
						      }
						     
						      
						      	if(strAAnalysis.length()!=0){					      
						      strValue+="<tr>";
						        strValue+="<td width=\"26%\" valign=\"top\"  scope=\"col\" align=\"right\" class=\"Arial_Narrow_12_black_bold\">Analysis:&nbsp;</td>";
						        strValue+="<td width=\"74%\" ALIGN=\"left\" valign=\"top\" scope=\"col\" class=\"Arial_12_black_normal\" align=\"left\"><table><tr><td ></td><td><DIV ALIGN=\"JUSTIFY\">You are "+strAAnalysis+"</DIV></td><td width=\"5%\"></td></tr></table></td>";
						      strValue+="</tr>";
						      	}
						     
						      	
						      
						      	
						      	strValue+="<tr><td colspan=\"2\"><table align=\"center\" width=\"100%\">";
						    	strValue+="<form name=\"publishtestresult\" method=\"post\" action=\"publishtestresult.do\" >";
						    	
						    	strValue+="<tr align=\"center\">";
						      	strValue+="<td colspan=\"2\"><input type=\"hidden\" name=\"testId\" value=\""+testId+"\" ></input></td>";
						      	strValue+="</tr>";
						      	
						      	strValue+="<tr align=\"left\">";
						      	strValue+="<td colspan=\"2\" class=\"bold\">Make this test result public or private </td>";
						      	strValue+="</tr>";
						      	
						      	
						      	
							
						      	strValue+="<tr align=\"center\">";
						      	strValue+="<td colspan=\"2\">";
						      	
						      	if(flag.equalsIgnoreCase("1")){
						      		
						      		strValue+="<input type=\"radio\" name=\"pflags\" value=\"0\" ></input><span class=\"bold\" >public </span>&nbsp;&nbsp;";
							      	strValue+="<input type=\"radio\" name=\"pflags\" value=\"1\" checked></input><span class=\"bold\" >private </span>";
							      	
						      		
						      	}else{
						      		
						      		strValue+="<input type=\"radio\" name=\"pflags\" value=\"0\" checked></input><span class=\"bold\" >public </span>&nbsp;&nbsp;";
							      	strValue+="<input type=\"radio\" name=\"pflags\" value=\"1\" ></input><span class=\"bold\" >private </span>";
							      	
						      	}
						      	strValue+="</td>";
						      	strValue+="</tr>";
							
						      	strValue+="<tr align=\"center\">";
						      	strValue+="<td colspan=\"2\"><input name=\"submit\" type=\"submit\" value=\"Submit\" class=\"bold\" tabindex=\"14\"></td>";
						      	strValue+="</tr>";
						      	
						      	
						      	// testId
						      	
						      	 strValue+="</form>";
						    	strValue+="</table></td></tr>";
							
						   
						    
						    strValue+="</table></div></td>";
						   
						    
						    /////////////////////////////////////////////////////////////
						    strValue+="<td width=\"1%\" scope=\"col\">&nbsp;</td>";
						    /////////////////////////////////////////////////////////////
						    strValue+="<td width=\"38%\" scope=\"col\"><div style = \"height:350px; overflow-y:scroll;\" ><table width=\"100%\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">";
						    strValue+="<tr>";
					        strValue+="<td scope=\"col\"&nbsp; height=\"15\" align=\"left\" class=\"Arial_Narrow_12_black_bold\"></td>";
					      strValue+="</tr>";  
					      if(Integer.parseInt(pageid.trim())==4)
					        {
						        strValue += "<tr >";
								strValue += "<td colspan=\"5\" width=\"38%\" class=\"Arial_12_black_normal\">Category:  "+strFinalStrCat+"</td>";		
								strValue += "</tr>";
					        }
					      strValue += "<tr >";
							strValue += "<td colspan=\"5\" width=\"38%\" class=\"Arial_12_black_normal\">Company:  "+strComp+"</td>";		
							strValue += "</tr>";
							
							 strValue += "<tr >";
								strValue += "<td colspan=\"5\" width=\"38%\" class=\"Arial_12_black_normal\"></td>";		
								strValue += "</tr>";
								
						 strValue += "<tr >";
							strValue += "<td colspan=\"5\" width=\"38%\" class=\"Arial_12_black_normal\"></td>";		
							strValue += "</tr>";
							
					      for(int i=0; i<testListVec.size(); i++){
								
								Vector teampDataVec = new Vector();
								
								teampDataVec = (Vector)testListVec.elementAt(i);
								
								String testIdteam = teampDataVec.elementAt(0).toString().trim();
								String testNameteam = teampDataVec.elementAt(2).toString().trim();
												
												
								if(testIdteam.equalsIgnoreCase(testId)){
								      strValue+="<tr >";
								      strValue+="<td  width=\"38%\"  bgcolor=\"#ee8126\" align=\"left\" class=\"Arial_Narrow_12_black_bold\">&nbsp;<A href=\"testResult.jsp?testId="+testIdteam+"&compId="+compId+"&uId="+uId+"&pageid="+pageid+"&type=Student\">"+testNameteam+" </A>&nbsp;</td>";
								      strValue+="</tr>";
								      
								      strValue+="<tr height=\"5\">";
								      strValue+="<td height=\"5\" width=\"38%\"></td>";
								      strValue+="</tr>";
								      
										}else{
									  strValue+="<tr >";
								      strValue+="<td    width=\"38%\"  bgcolor=\"#a5cdbc\" align=\"left\" class=\"Arial_Narrow_12_black_bold\">&nbsp;<A href=\"testResult.jsp?testId="+testIdteam+"&compId="+compId+"&uId="+uId+"&pageid="+pageid+"&type=Student\">"+testNameteam+" </A>&nbsp;</td>";
								      strValue+="</tr>";	
								      
								      strValue+="<tr height=\"5\">";
								      strValue+="<td height=\"5\" width=\"38%\"></td>";
								      strValue+="</tr>";
										}    
							 }
						    strValue+="</table><div></td>";
						    strValue+="<td width=\"1%\" scope=\"col\">&nbsp;</td>";
						  strValue+="</tr>";
						strValue+="</table>";

				}
				if(remander==0)
				{
					strValue += "<tr style=\"display:none\" align=\"center\">";
					strValue += "<td height=\"50\" colspan=\"7\"></td>";
					strValue += "</tr>";
				}
				strValue += "</table>";
			}
			
			
			
			return strValue;
		}
		
		private String getCompanyList( Vector compVec, String compidStr ,int numTempCount, String pageid)
		{	
			numTempCount = numTempCount +1;
			String strResult = "";
			
						
					
					 strResult ="<select name=\"companyList\" class=\"input_txt_box\"   onchange=\"retrieveTRlistURL('testResult.do?pageid="+pageid+"&compId='+this.value)\" >";
					 
					
					
					for(int i=0; i<compVec.size(); i++)
					{
						Vector tempVec = (Vector)compVec.elementAt(i);
						String compId = tempVec.elementAt(0).toString().trim();			
							
						String strCName = tempVec.elementAt(1).toString().trim();
			
						if(compidStr.equalsIgnoreCase(compId))
						{
							strResult += "<option value=\"" + compId + "\" Selected>" + strCName + "</option>";
							
						}
						else
						{
							strResult += "<option value=\"" + compId + "\">" + strCName + "</option>";
						}
					}
				
			
			
			strResult += "</select>";
			return strResult;
		}
		private String getSubCatList(int numCatId, String strCat, HashMap catList, int compid, int pageid)
		{
			
			ArrayList AllStates = (ArrayList)catList.get(strCat);
			
			String strResult ="<select name=\"testsubcat\" class=\"input_combo_box\" tabindex=\"10\"  onchange=\"retrieveTRlistURL('testResult.do?pageid="+pageid+"&compId="+compid+"&catid='+this.value);\">";
			
			for(int i=0; i<AllStates.size(); i++)
			{
				ArrayList StatesArr = (ArrayList)AllStates.get(i);
				int numStateId = Integer.parseInt(StatesArr.get(0).toString().trim());
				String strSName = StatesArr.get(1).toString().trim();
					if(numCatId == numStateId)
				{
					strResult += "<option value=\"" + numStateId + "\" Selected>" + strSName + "</option>";
				}
				else
				{
					strResult += "<option value=\"" + numStateId + "\">" + strSName + "</option>";
				}
			}
			
			strResult += "</select>";
			return strResult;
		}
	}