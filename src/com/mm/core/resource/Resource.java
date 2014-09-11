/** * 
 */
package com.mm.core.resource;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;


/**
 * @author Ajay Kumar Jha
 *
 */
/**
 * this class is use for fulfill the basic requirment of whole application.it contains various 
 * methods for performing variour task.
 */
public class Resource 
{

/**
 * 
 * @param text Mail text
 * @param strFrom Mail Id from mail will send.
 * @param strTo mail id where mail will go.
 * @param strSubject Subject line of mail.
 * @return String paramenter for difine status of mail send.
 */
/**
 * A Standard Mailing Procedure for all the pages for sending mail.
 */
	public String sendMail(String text,String strFrom,String strTo,String strSubject)
	{
		String result = "failure";
		try 
		{
		boolean debug = false;
		Properties props = new Properties();
		props.put("mail.smtp.host","mail.campusyogi.com");
		props.put("mail.smtp.auth", "true");
		Authenticator auth = new SMTPAuthenticator(); //SMTPAuthenticator class is created above
		Session s = Session.getInstance(props, auth);
		s.setDebug(debug);
		
		MimeMessage message = new MimeMessage(s);
		InternetAddress from = new InternetAddress(strFrom);
		message.setFrom(from);

		InternetAddress to = new InternetAddress(strTo);
		message.addRecipient(javax.mail.Message.RecipientType.TO, to);
		
		message.setSubject(strSubject);
		message.setText(text);
		javax.mail.Transport.send(message);
		result ="success";
		}
		catch(Exception e)
		{
			result = "failure";
			e.printStackTrace();
		}
		return result;
	}

	
	
/**
	
 * 
	
 * @return vactor object that contains mail id and passowrd
	
 * for authentication of SMTP server.
	
 */
	
/**
	
 * This function is used for getting Email configuration fields.
	
 * It calls getPasswordAuthentication().
	
 */
	public Vector getEmailConf()
	{
		Vector<String> resultVec = new Vector<String>();
		Properties props = new Properties();
        try
        {
           FileInputStream is = new FileInputStream(new File(Constant.FileLoc_DB));
            props.load(is);
            is.close();
        } 
        catch(IOException e) 
        {
			e.printStackTrace();
		}

		String ID = props.getProperty("emailId");
		String Pass = props.getProperty("emailPass");

		resultVec.add(ID);
		resultVec.add(Pass);
		 
		
		return resultVec;
}
	
/**
	
 * 
	
 * @param strValues takes string that will write in the file
	
 * @param strFliePath takes the path where file will created
	
 * @return status result of write a file.
	
 */
	
/**
	
 * This function will use for creation of file .
	
 */
	public boolean writeFile(String strValues, String strFliePath) {
        boolean blnResult = false;
        try 
		{
            //Code to write to file
            String text = strValues;
            String outputFileName = strFliePath;
            FileWriter out = new FileWriter(outputFileName);
            out.write(text);
            out.close();
            blnResult = true;
        } 
		catch(java.io.IOException e) {
            System.out.println("Cannot access file");
            blnResult = false;
        }
        return blnResult;
    }
	
	public int rndCompalintId(int Cidlow, int Cidhi)
	{
		//Random Cidrn = new Random();
		int n = (Cidhi-Cidlow)+1;
		int rndN = Cidhi%n;
		//int i = Cidrn.nextInt()%n;
	
		//if(i<0)
		//{
			//i=-1;
		//}
		//return Cidlow+i;
		return Cidlow+rndN;
	
	} 
	
	public int rndEntpUserId(int EUIdlow, int EUIdhi)
	{
		//Random EUIdrn = new Random();
		int n = (EUIdhi-EUIdlow)+1;
		int rndN = EUIdhi%n;
		//int i = EUIdrn.nextInt()%n;
	
		//if(i<0)
		//{
			//i=-1;
		//}
		//return EUIdlow+i;
		return EUIdlow+rndN;
	} 
	
	public int rndComplaintYear(int Cyearlow, int Cyearhi)
	{
		//Random CyearRn = new Random();
		int n = (Cyearhi-Cyearlow)+1;
		int rndN = Cyearhi%n;
		//int i = CyearRn.nextInt()%n;
	
		//if(i<0)
		//{
		//	i=-1;
		//}
		//return Cyearlow+i;
		return Cyearlow+rndN;
	
	} 

}

class SMTPAuthenticator extends javax.mail.Authenticator
{
	/**
	 * This function is used for authenticate the email. 
	 * This is called by  sendMail().
	 */
	public PasswordAuthentication getPasswordAuthentication()
	{
			Resource resr = new Resource();
			Vector resultVec1 = resr.getEmailConf();
							
		String username = resultVec1.elementAt(0).toString().trim();
		String password = resultVec1.elementAt(1).toString().trim();
							
		return new PasswordAuthentication(username, password);
	}
}