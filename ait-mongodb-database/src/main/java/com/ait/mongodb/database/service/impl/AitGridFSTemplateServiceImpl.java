package com.ait.mongodb.database.service.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.ait.mongodb.database.constant.AitFileTypeConstant;
import com.ait.mongodb.database.service.AitGridFSTemplateService;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Service
public class AitGridFSTemplateServiceImpl implements AitGridFSTemplateService {

	public static final Logger logger = LoggerFactory.getLogger(AitMongoDBTemplateServiceImpl.class);
	
	@Autowired
	private MongoOperations mog; 
	
	@Value("${gridfs.image.colloction}")
	private String imageCollection;
	
	@Override
	public String uploadImage(File file, String id, AitFileTypeConstant type, AitFileTypeConstant size,double max) {
		String successName = null;
		GridFSInputFile gfsInput = null;
		DB db = mog.getCollection(imageCollection).getDB();
		db.requestStart(); 
		try{
			 String fileName = id + size.getString() + type.getString();
			 weakZipImage(file,max);
			 gfsInput = new GridFS(db, imageCollection).createFile(file);//
			 gfsInput.setFilename(fileName);//
			 gfsInput.setContentType(type.getString());//
			 gfsInput.save(); //save
			 successName = fileName;
		}catch(Exception e){
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
		}
		return successName;
	}

	@Override
	public String uploadImageWithSize(File file, String id, String type,String size, int witdh, int height) {
		String successName = null;
		GridFSInputFile gfsInput = null;
		DB db = mog.getCollection(imageCollection).getDB();
		db.requestStart(); 
		try{
			 strongZipImage(file,witdh,height);
			 gfsInput = new GridFS(db, imageCollection).createFile(file);
			 gfsInput.setFilename(id + size + type);
			 gfsInput.setContentType("png");
			 gfsInput.save(); //save
			 successName = id + size + type;
		}catch(Exception e){
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
		}
		return successName;
	}

	@Override
	public String uploadFile(File file, String id, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeFile(String fileName, String type) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void strongZipImage(File file, int width, int height) throws Exception{
		 BufferedImage image = ImageIO.read(file);
		 BufferedImage bfImage = new BufferedImage(width, height,
                 BufferedImage.TYPE_INT_RGB);
         bfImage.getGraphics().drawImage(image.getScaledInstance(width, height,
                         Image.SCALE_SMOOTH), 0, 0, null);
         FileOutputStream os = new FileOutputStream(file);
         JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
         encoder.encode(bfImage);
         os.close();
	}
	
	private void weakZipImage(File file,double max){
		try{
			BufferedImage image = ImageIO.read(file);
		    int newWidth = 0;
		    int newHeight = 0;
		    double ratio = 0.0; 
		    if(image.getHeight() > max || image.getWidth() > max){
	    	   if (image.getHeight() > image.getWidth()) {
	                ratio = max / image.getHeight();
	              } else {
	                ratio = max / image.getWidth();
	            }
	    	    newWidth = (int) (image.getWidth() * ratio);
	            newHeight = (int) (image.getHeight() * ratio);
		    }else{
		    	newWidth = image.getWidth();
	            newHeight = image.getHeight();
		    }
		    BufferedImage bfImage = new BufferedImage(newWidth, newHeight,
                    BufferedImage.TYPE_INT_RGB);
            bfImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight,
                            Image.SCALE_SMOOTH), 0, 0, null);
            FileOutputStream os = new FileOutputStream(file);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
            encoder.encode(bfImage);
            os.close();
		} catch (Exception e) {
			logger.error("error message {}. error exception {}.",e.getMessage(), e);
			logger.error("error StackTrace ", e);
        }
	}

}
