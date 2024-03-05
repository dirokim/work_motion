package com.workmotion.app.util;

import com.workmotion.app.firebase.FirebaseService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Calendar;

@Component
public class FileManager {


    public String fileSave(String path, MultipartFile file) throws Exception {
//		File f = new File(path);
//		if(f.exists()) {
//
//		}else {
//			f.mkdir();
//		}
        Calendar ca = Calendar.getInstance();
        String fileName = ca.getTimeInMillis() + "_" + file.getOriginalFilename();
//		f = new File(f,fileName);
//		file.transferTo(f);
        FirebaseService firebaseService = new FirebaseService();
        return firebaseService.uploadFile(file, fileName);
        //return fileName;
    }

    public boolean fileDelete(String path, String fileName) throws Exception {

        File f = new File(path, fileName);
        return f.delete();

    }
}
