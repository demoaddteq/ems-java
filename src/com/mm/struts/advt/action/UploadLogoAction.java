package com.mm.struts.advt.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
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
import org.apache.struts.upload.FormFile;

import com.mm.core.master.IndvMaster;
import com.mm.core.resource.Constant;
import com.mm.struts.advt.form.UploadLogoForm;

public class UploadLogoAction extends Action {
	
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
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		UploadLogoForm uploadLogoForm = (UploadLogoForm) form;// TODO Auto-generated method stub
		FormFile  imageFile = uploadLogoForm.getUploadfile();
		IndvMaster indvMaster = new IndvMaster();
		String status = "failure";
		HttpSession session = request.getSession();
		String uName = (String)session.getAttribute("uName");
		String compId =(session.getAttribute("compId") != null) ? (String)session.getAttribute("compId") : "0";
	//	String uid = (String)session.getAttribute("uId");
		String fileName    = imageFile.getFileName();
		String contentType = imageFile.getContentType();             
        int fileSize       = imageFile.getFileSize();        
        
        if(contentType.equalsIgnoreCase("image/pjpeg")|| contentType.equalsIgnoreCase("image/gif"))
        { 
        	String templogoPath = indvMaster.getUserLogoPath(getDataSource(request, "advt"),Integer.parseInt(compId.trim()));
       		
        	InputStream fileInputStream = imageFile.getInputStream(); 
        	
        	//get and set photo path 
        	String logoPath = Constant.Logo_Path;
        	
        	
        	String fileTypearr[] = contentType.split("/");
        	String fileType = "";
        	
        	if(fileTypearr[1].indexOf("jpeg")>=0)
        	{
        		fileType = "jpg";
        	}
        	if(fileTypearr[1].indexOf("gif")>=0)
        	{
        		fileType = "gif";
        	}
        	if((templogoPath.indexOf("."+fileType)<0) && (!templogoPath.equals("sam.gif")))
        	{
	        	templogoPath = logoPath+templogoPath;
	        	File f = new File(templogoPath);
	       		f.delete();
        	}
           	String LogofileName = uName+"_"+compId+"."+fileType;
           	String logoFile = logoPath+LogofileName;
           	
           	FileOutputStream fout = new FileOutputStream(logoFile);
           	int i;
           	boolean result = false;
           	do
           	{           		
           		i = fileInputStream.read();
           		if(i != -1)
           		{
           			fout.write(i);
           			result = true;
           		}
           	}while(i != -1);
           	//BufferedImage bufi = ImageIO.read(fileInputStream); 
           	
           //ystem.out.println("bufi >>>>>>>>"+bufi.toString());
           	
           	//boolean result = ImageIO.write(bufi, fileType, file); 
           	
           	if(result)
           	{
           		Vector<String> dataVec = new Vector<String>();
           		dataVec.add(compId);
           		dataVec.add(LogofileName);
           		status = indvMaster.updateLogoPath(getDataSource(request,"corpo"),dataVec);
           	}
           	ActionErrors errors = new ActionErrors();
          	errors.add("Logo", new ActionError("errors.user.LogoSuccess"));
			saveErrors(request, errors);
			status = "success";
           	
           	
        }
        else
        {
        	ActionErrors errors = new ActionErrors();
          	errors.add("Logo", new ActionError("errors.user.LogoFailure"));
			saveErrors(request, errors);
			status ="failure";
        }
        
		return mapping.findForward(status);
	}
}
