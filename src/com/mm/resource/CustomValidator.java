package com.mm.resource; 
import java.io.Serializable;


import javax.servlet.http.HttpServletRequest;
import org.apache.commons.validator.Field;

import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorUtil;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.validator.Resources;
import java.util.regex.*;

public class CustomValidator implements Serializable{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/*
 * 1. No special characters except ( _ and numeric value) for Login Id or User Id
 */
	public static boolean submitcheck1( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
		// check to see if the value is a valid.
		char [] chars = strField.toCharArray();
		int count=0;
		if(chars.length==0)
		{
			return true; 
		}
		for(int i =0 ; i < chars.length; i++)
		{
			char c = chars[i];
			if (Character.isLowerCase(c) || Character.isUpperCase(c)){
				count++;				
			}
			else if (Character.isDigit(c))
			{
				count++;
			}
			else if (c == '_')
			{
				count++;				
			}
			else 
			{
				errors.add(field.getKey(), Resources.getActionError(request, va, field));
				return false; 
			}
		
		}
		if(count > 0){
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
		
	}
	
//	2. No special Character and numeric no. for Category,First Name ,Last Name and City

	public static boolean submitcheck2( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
   
		// check to see if the value is a valid.
		char [] chars = strField.toCharArray();
		int count=0;
		if(chars.length==0)
		{
			return true; 
		}
		for(int i =0 ; i < chars.length; i++)
		{
			char c = chars[i];
			if (Character.isLowerCase(c) || Character.isUpperCase(c)){
				count++;				
			}			
			else 
			{
				errors.add(field.getKey(), Resources.getActionError(request, va, field));
				return false; 
			}
		
		}
		if(count > 0){
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
//	3. No special character except(- , . # ( ) / \ and numeric no. and space) for Address

	public static boolean submitcheck3( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
   
		// check to see if the value is a valid.
		char [] chars = strField.toCharArray();
		int count=0;
		if(chars.length==0)
		{
			return true; 
		}
		for(int i =0 ; i < chars.length; i++) 
		{
			char c = chars[i];
			if (Character.isLowerCase(c) || Character.isUpperCase(c)){
				count++;				
			}
			else if (Character.isDigit(c)){
				count++;
			}
			else if (Character.isWhitespace(c)){
				count++;
			}
			else if (c ==' '|| c =='-' || c ==',' || c =='.' || c =='#' || c =='(' || c ==')' || c =='/' || c == '\\')
			{
				count++;				
			}
			else 
			{
				errors.add(field.getKey(), Resources.getActionError(request, va, field));
				return false; 
			}
		
		}
		if(count > 0){
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
//	4. Numbers Only for Pincode,Mobile,Area Code, Phone No.
	public static boolean submitcheck4( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
   
		// check to see if the value is a valid.
		char [] chars = strField.toCharArray();
		int count=0;
		if(chars.length==0)
		{
			return true; 
		}
		for(int i =0 ; i < chars.length; i++)
		{
			char c = chars[i];
			if (Character.isDigit(c)){
				count++;
			}
			else 
			{
				errors.add(field.getKey(), Resources.getActionError(request, va, field));
				return false; 
			}
		
		}
		if(count > 0){
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
//	5. No  Special Character except( . and _ and numeric No. @) for Email Address
	public static boolean submitcheck5( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String mask = field.getVarValue("mailmask");
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
		if(strField.length()<=0)
			return true;
		Pattern p = Pattern.compile(mask);
		Matcher m = p.matcher(strField);
   		// check to see if the value is a valid.
		if (m.find()){
			 return true;
		}
		else 
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
	
//	6. Some special chatacter < > ! ~ @ % ^ / \ are restricted for Complaint Title
	
	public static boolean submitcheck6( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
   
		// check to see if the value is a valid.
		char [] chars = strField.toCharArray();
		int count=0;
		if(chars.length==0)
		{
			return true; 
		}
		for(int i =0 ; i < chars.length; i++)
		{
			char c = chars[i];
			if(c =='<'|| c =='>' || c =='!' || c =='~' || c =='%' || c =='^'|| c =='/' || c =='\\')
			{
				errors.add(field.getKey(), Resources.getActionError(request, va, field));
				return false; 
			}
			else
			{
				count++;
			}
				
		}
		if(count > 0){
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
//	7. No Special Character except ( . space ( ) and numeric no.) for Brand , brand name and Dealer Name
	public static boolean submitcheck7( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
   
		// check to see if the value is a valid.
		char [] chars = strField.toCharArray();
		int count=0;
		if(chars.length==0)
		{
			return true; 
		}
		for(int i =0 ; i < chars.length; i++)
		{
			char c = chars[i];
			if (Character.isLowerCase(c) || Character.isUpperCase(c)){
				count++;				
			}
			else if (Character.isDigit(c)){
				count++;
			}
			else if (Character.isSpaceChar(c)){
				//White space okay
			}
			else if (c =='.'|| c =='(' || c ==')' || c =='&' || c =='-' || c ==',' || c =='/')
			{
				count++;				
			}
			else 
			{
				errors.add(field.getKey(), Resources.getActionError(request, va, field));
				return false; 
			}
		
		}
		if(count > 0){
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
	
//	8. No special character and numeric no. except (Space) for Contact Person and state
	
	public static boolean submitcheck8( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
   
		// check to see if the value is a valid.
		char [] chars = strField.toCharArray();
		int count=1;
		
		for(int i =0 ; i < chars.length; i++)
		{
			char c = chars[i];
			if (Character.isLowerCase(c) || Character.isUpperCase(c)){
				count++;				
			}
			else if (Character.isSpaceChar(c)){
				//White space okay
			}
			else 
			{
				errors.add(field.getKey(), Resources.getActionError(request, va, field));
				return false; 
			}
		
		}
		if(count > 0){
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
	
//	9. Some special character ~ ` ^ < > are restricted for Complaint Text,Response,Reply
	public static boolean submitcheck9( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
   
		// check to see if the value is a valid.
		char [] chars = strField.toCharArray();
		int count=0;
		if(chars.length==0)
		{
			return true; 
		}
		for(int i =0 ; i < chars.length; i++)
		{
			char c = chars[i];
			if(c =='~'|| c =='`' || c =='<' || c =='>' || c =='^')
			{
				errors.add(field.getKey(), Resources.getActionError(request, va, field));
				return false; 
			}
			else
			{
				count++;
			}
				
		}
		if(count > 0){
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
//	10. No special character except(/ and numeric No.) for Final Complaint Id
	public static boolean submitcheck10( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
   
		// check to see if the value is a valid.
		char [] chars = strField.toCharArray();
		int count=0;
		if(chars.length==0)
		{
			return true; 
		}
		for(int i =0 ; i < chars.length; i++)
		{
			char c = chars[i];
			if (Character.isDigit(c)){
				count++;
			}
			else if(c =='/')
			{
				count++;			
			}
			else
			{
				errors.add(field.getKey(), Resources.getActionError(request, va, field));
				return false; 
			}
				
		}
		if(count > 0){
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
	
//	11. Some special character < % > ~ ` ^ @ , / \  for Password
	public static boolean submitcheck11( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
   
		// check to see if the value is a valid.
		char [] chars = strField.toCharArray();
		int count=0;
		if(chars.length==0)
		{
			return true; 
		}
		for(int i =0 ; i < chars.length; i++)
		{
			char c = chars[i];
			if(c =='@'|| c =='%' || c =='<' || c =='>' || c =='^' || c =='~' || c =='`' || c =='/' || Character.isWhitespace(c))
			{
				errors.add(field.getKey(), Resources.getActionError(request, va, field));
				return false; 
			}
			
			else
			{
				count++;
			}
				
		}
		if(count > 0){
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
//	12. No special character And numeric no. for Brand short Name
	public static boolean submitcheck12( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
   
		// check to see if the value is a valid.
		char [] chars = strField.toCharArray();
		int count=0;
		if(chars.length==0)
		{
			return true; 
		}
		for(int i =0 ; i < chars.length; i++)
		{
			char c = chars[i];
			if (Character.isLowerCase(c) || Character.isUpperCase(c)){
				count++;				
			}
			else if (Character.isDigit(c)){
				count++;
			}else if(c =='.')
			{
				count++;			
			}
			else 
			{
				errors.add(field.getKey(), Resources.getActionError(request, va, field));
				return false; 
			}
				
		}
		if(count > 0){
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
//	13. Numbers Only with + sign for mobile no.
	public static boolean submitcheck13( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
   
		// check to see if the value is a valid.
		char [] chars = strField.toCharArray();
		int count=0;
		if(chars.length==0)
		{
			return true; 
		}
		for(int i =0 ; i < chars.length; i++)
		{
			char c = chars[i];
			if (Character.isDigit(c)){
				count++;
			}
			else if(c =='+')
			{
				count++;			
			}
			else
			{
				errors.add(field.getKey(), Resources.getActionError(request, va, field));
				return false; 
			}
				
		}
		if(count > 0){
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
//	14. No special characters except ( _ and numeric value and @) for Login Id or User Id for core and brand
	public static boolean submitcheck14( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
   
		// check to see if the value is a valid.
		char [] chars = strField.toCharArray();
		int count=0;
		if(chars.length==0)
		{
			return true; 
		}
		for(int i =0 ; i < chars.length; i++)
		{
			char c = chars[i];
			if (Character.isLowerCase(c) || Character.isUpperCase(c)){
				count++;				
			}
			else if (Character.isDigit(c)){
				count++;
			}
			else if(c =='_'|| c =='@')
			{
				count++;
			}
			else
			{
				errors.add(field.getKey(), Resources.getActionError(request, va, field));
				return false; 
			}
				
		}
		if(count > 0){
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
//	15 No  Special Character except( . and _ and , and numeric No. @) for multiple Email Address's
	public static boolean submitcheck15( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String mask = field.getVarValue("mailmask");
		String strFieldFull = ValidatorUtil.getValueAsString(bean, field.getProperty());
		if(strFieldFull.length()<=0)
			return true;
		String strFieldArr[] = strFieldFull.split(",");
		for(int i= 0; i<strFieldArr.length;i++)
		{
			String strField = strFieldArr[i];
			//System.out.println("strField>>>>>>>>>"+strField);
			 Pattern p = Pattern.compile(mask);
			 Matcher m = p.matcher(strField);
	   
			// check to see if the value is a valid.
			
			 if (m.find()){
				
				 return true;
			 }
			else 
			{
				
				errors.add(field.getKey(), Resources.getActionError(request, va, field));
				return false; 
			}
		}
		errors.add(field.getKey(), Resources.getActionError(request, va, field));
		return false;
		
	}
	
	//for country drop down menu
	public static boolean submitcheck16( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
		System.out.println("strField in dropdown>>>"+strField);
		// check to see if the value is a valid.
		if(!strField.startsWith("0"))
		{
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
	
//	for country drop down menu
	public static boolean submitcheck19( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
		//System.out.println("strField in dropdown>>>"+strField);
		// check to see if the value is a valid.
		if(!strField.startsWith("00"))
		{
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
	
	
	//end of country drop down
	
//	17. Some special character ~ ` ^ < > are restricted for Complaint Text,Response,Reply
	public static boolean submitcheck17( Object bean,
			ValidatorAction va, 
			Field field, 
			ActionErrors errors,
			HttpServletRequest request) {

		//Get the string value of the currentfield
		String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());
   
		// check to see if the value is a valid.
		char [] chars = strField.toCharArray();
		int count=0;
		if(chars.length==0)
		{
			return true; 
		}
		for(int i =0 ; i < chars.length; i++)
		{
			char c = chars[i];
			if(c =='~'|| c =='`' || c =='<' || c =='>' || c =='^')
			{
				errors.add(field.getKey(), Resources.getActionError(request, va, field));
				return false; 
			}
			else
			{
				count++;
			}
				
		}
		if(count > 0){
			return true; 
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
	}
	protected static boolean isString(Object o) {
	    return (o == null) ? true : String.class.isInstance(o);
		}


//18. Numbers Only with . sign for mobile no.
public static boolean submitcheck18( Object bean,
		ValidatorAction va, 
		Field field, 
		ActionErrors errors,
		HttpServletRequest request) {

	//Get the string value of the currentfield
	String strField = ValidatorUtil.getValueAsString(bean, field.getProperty());

	// check to see if the value is a valid.
	char [] chars = strField.toCharArray();
	int count=0;
	if(chars.length==0)
	{
		return true; 
	}
	for(int i =0 ; i < chars.length; i++)
	{
		char c = chars[i];
		if (Character.isDigit(c)){
			count++;
		}
		else if(c =='.')
		{
			count++;			
		}
		else
		{
			errors.add(field.getKey(), Resources.getActionError(request, va, field));
			return false; 
		}
			
	}
	if(count > 0){
		return true; 
	}
	else
	{
		errors.add(field.getKey(), Resources.getActionError(request, va, field));
		return false; 
	}
}

}