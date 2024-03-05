package com.workmotion.app.document.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("temFileManager")
public class FileManager {
	
	public String fileSave(String path,MultipartFile templeteFile)throws Exception{
		
		File f = new File(path);
		
		if(f.isFile()) {
			throw new Exception();
		}
		
		if(f.exists()) {
			f.mkdirs();
		}
		
		String fileName = UUID.randomUUID().toString()+"_"+templeteFile.getOriginalFilename();
		
		f = new File(f,fileName);
		templeteFile.transferTo(f);
		
		return fileName;		
		
	}

}
