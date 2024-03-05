package com.workmotion.app.firebase;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class FirebaseService {
    public String uploadFile(MultipartFile file, String fileName) throws Exception {
        Bucket bucket = StorageClient.getInstance().bucket();
        InputStream content = new ByteArrayInputStream(file.getBytes());
        Blob blob = bucket.create(fileName, content, file.getContentType());
        return blob.getMediaLink();
    }
}
