package com.mm.struts.corpo.action;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
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

import sun.awt.image.codec.JPEGImageEncoderImpl;

import com.mm.core.master.IndvMaster;
import com.mm.core.resource.Constant;
import com.mm.struts.corpo.form.UploadPhotoForm;
import com.sun.image.codec.jpeg.JPEGEncodeParam;

public class UploadPhotoAction extends Action {
	
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
		UploadPhotoForm uploadPhotoForm = (UploadPhotoForm) form;// TODO Auto-generated method stub
		FormFile  imageFile = uploadPhotoForm.getUploadfile();
		IndvMaster indvMaster = new IndvMaster();
		System.out.println("line 53 in UploadPhotoAction:");
		String status = "failure";
		HttpSession session = request.getSession();
		String uName = (String)session.getAttribute("uName");
		String uid = (String)session.getAttribute("uId");
		//String fileName    = imageFile.getFileName();
		String contentType = imageFile.getContentType();             
        int fileSize       = imageFile.getFileSize();
        System.out.println("line 61:"+contentType);
        
        if(contentType.equalsIgnoreCase("image/jpeg")|| contentType.equalsIgnoreCase("image/gif"))
        { 
        	System.out.println("in if loop :line65");
        	if(fileSize > 307200)
	        {
        		System.out.println("in if if loop :line68");
        		ActionErrors errors = new ActionErrors();
	        	errors.clear();
	          	errors.add("Photo", new ActionError("errors.user.PhotoLarge"));
				saveErrors(request, errors);
				status ="failure";
	        }
	        else
	        {
	        	System.out.println("in if loop :line77");
	        	String tempphotoPath = indvMaster.getUserPhotoPath(getDataSource(request, "corpo"),Integer.parseInt(uid));
	       		
	        	InputStream fileInputStream = imageFile.getInputStream(); 
	        	System.out.println("line81");
	        	//get and set photo path 
	        	String photPath = Constant.Photo_Path;
	        	String strTmpPPath = Constant.TempPhoto_Path;
	        	String strThmbPPath = Constant.thumPhoto_Path;
	        	System.out.println("line86");
	        	String fileTypearr[] = contentType.split("/");
	        	String fileType = "";
	        	
	        	System.out.println("line90");
	        	if(fileTypearr[1].indexOf("jpeg")>=0)
	        	{
	        		fileType = "jpg";
	        	}
	        	if(fileTypearr[1].indexOf("gif")>=0)
	        	{
	        		fileType = "gif";
	        	}
	        	/* commeted by Renuka 
	        	if((tempphotoPath.indexOf("."+fileType)<0) && (!tempphotoPath.equals("nophoto.jpg")))
	        	{
		        	tempphotoPath = photPath+tempphotoPath;
		        	System.out.println("line102::102::"+tempphotoPath);
		        	File f = new File(tempphotoPath);
		       		f.delete();
	        	}
	        	*/
	        	System.out.println("line 105 ");
	        	String tPhotofileName = uName+"_"+uid+"."+fileType;
	        	String PhotofileName = uName+"_"+uid+".jpg";
	           	String photoFile = photPath+PhotofileName;
	        	String TempphotoFile = strTmpPPath+tPhotofileName;
	        	System.out.println("line 111::"+TempphotoFile);
	        	String ThumbphotoFile = strThmbPPath+PhotofileName;
	           	System.out.println("line 111");
	           	FileOutputStream fout = new FileOutputStream(TempphotoFile);
	           	
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
	           	System.out.println("line 124");
	           	setOriginalPhoto(photoFile, TempphotoFile);
	           	setThumbnil(ThumbphotoFile, TempphotoFile);
	           	setRemoveTempFile(TempphotoFile);
	           	//BufferedImage bufi = ImageIO.read(fileInputStream); 
	           	
	           //ystem.out.println("bufi >>>>>>>>"+bufi.toString());
	           	
	           	//boolean result = ImageIO.write(bufi, fileType, file); 
	           	System.out.println("line 133");
	           	if(result)
	           	{
	           		Vector<String> dataVec = new Vector<String>();
	           		dataVec.add(uid);
	           		dataVec.add(PhotofileName);
	           		status = indvMaster.updatePhotoPath(getDataSource(request,"corpo"),dataVec);
	           	}
	           	ActionErrors errors = new ActionErrors();
	          	errors.add("Photo", new ActionError("errors.user.PhotoSuccess"));
				saveErrors(request, errors);
				status = "success";
	        }
           	
        	System.out.println("line 147");
        }
        else
        {
        	System.out.println("line 151");
        	ActionErrors errors = new ActionErrors();
          	errors.add("Photo", new ActionError("errors.user.PhotoFailure"));
			saveErrors(request, errors);
			status ="failure";
        }
        
		return mapping.findForward(status);
	}
	private void setRemoveTempFile(String strTmpphotoFile) throws Exception
	{
		File f = new File(strTmpphotoFile);
   		boolean blnResult = f.delete();
   		if(blnResult==true){}
   		//////System.out.println("tempphoto File Deletion ......"+blnResult);
	}
	private void setOriginalPhoto(String strfphotoFile, String strTmpphotoFile) throws Exception
	{
		//////System.out.println("Original Called ");
		//////System.out.println("Final File "+strfphotoFile);
		//////System.out.println("Temp File "+strTmpphotoFile);
		Image image = Toolkit.getDefaultToolkit().getImage(strTmpphotoFile);
	    MediaTracker mediaTracker = new MediaTracker(new Container());
	    mediaTracker.addImage(image, 0);
	    mediaTracker.waitForID(0);
	    // determine thumbnail size from WIDTH and HEIGHT
	    int thumbWidth = Integer.parseInt("150");
	    int thumbHeight = Integer.parseInt("150");
	    double thumbRatio = (double)thumbWidth / (double)thumbHeight;
	    int imageWidth = image.getWidth(null);
	    int imageHeight = image.getHeight(null);
	    double imageRatio = (double)imageWidth / (double)imageHeight;
	    if (thumbRatio < imageRatio) {
	      thumbHeight = (int)(thumbWidth / imageRatio);
	    } else {
	      thumbWidth = (int)(thumbHeight * imageRatio);
	    }
	    // draw original image to thumbnail image object and
	    // scale it to the new size on-the-fly
	    BufferedImage thumbImage = new BufferedImage(thumbWidth, 
	      thumbHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D = thumbImage.createGraphics();
	    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	      RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
	    // save thumbnail image to OUTFILE
	    BufferedOutputStream out = new BufferedOutputStream(new
	      FileOutputStream(strfphotoFile));
	    JPEGImageEncoderImpl encoder = new JPEGImageEncoderImpl(out);
	    JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
	    int quality = Integer.parseInt("100");
	    quality = Math.max(0, Math.min(quality, 100));
	    param.setQuality((float)quality / 100.0f, false);
	    encoder.setJPEGEncodeParam(param);
	    encoder.encode(thumbImage);
	    out.close(); 
	}
	
	private void setThumbnil(String strfphotoFile, String strTmpphotoFile) throws Exception
	{
		//////System.out.println("Thumbnil Called ");
		//////System.out.println("Final File "+strfphotoFile);
		////System.out.println("Temp File "+strTmpphotoFile);
		Image image = Toolkit.getDefaultToolkit().getImage(strTmpphotoFile);
	    MediaTracker mediaTracker = new MediaTracker(new Container());
	    mediaTracker.addImage(image, 0);
	    mediaTracker.waitForID(0);
	    // determine thumbnail size from WIDTH and HEIGHT
	    int thumbWidth = Integer.parseInt("50");
	    int thumbHeight = Integer.parseInt("50");
	    double thumbRatio = (double)thumbWidth / (double)thumbHeight;
	    int imageWidth = image.getWidth(null);
	    int imageHeight = image.getHeight(null);
	    double imageRatio = (double)imageWidth / (double)imageHeight;
	    if (thumbRatio < imageRatio) {
	      thumbHeight = (int)(thumbWidth / imageRatio);
	    } else {
	      thumbWidth = (int)(thumbHeight * imageRatio);
	    }
	    // draw original image to thumbnail image object and
	    // scale it to the new size on-the-fly
	    BufferedImage thumbImage = new BufferedImage(thumbWidth, 
	      thumbHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D = thumbImage.createGraphics();
	    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	      RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
	    // save thumbnail image to OUTFILE
	    BufferedOutputStream out = new BufferedOutputStream(new
	      FileOutputStream(strfphotoFile));
	    JPEGImageEncoderImpl encoder = new JPEGImageEncoderImpl(out);
	    JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
	    int quality = Integer.parseInt("100");
	    quality = Math.max(0, Math.min(quality, 100));
	    param.setQuality((float)quality / 100.0f, false);
	    encoder.setJPEGEncodeParam(param);
	    encoder.encode(thumbImage);
	    out.close(); 
	}
}
