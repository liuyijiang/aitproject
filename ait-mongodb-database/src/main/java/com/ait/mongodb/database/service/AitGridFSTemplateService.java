package com.ait.mongodb.database.service;

import java.io.File;

import com.ait.mongodb.database.constant.AitFileTypeConstant;

public interface AitGridFSTemplateService {
   
	public String uploadImage(File file,String id,AitFileTypeConstant type,AitFileTypeConstant size,double max);
	
	public String uploadImageWithSize(File file,String id,String type,String size,int witdh,int height);
	
	public String uploadFile(File file,String id,String type);
	
	public boolean removeFile(String fileName,String type);
	
}
