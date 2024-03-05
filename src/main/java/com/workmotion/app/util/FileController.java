package com.workmotion.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FileController {
    @Autowired
    private FileManager fileManager;

    @PostMapping("/v1/files")
    public ResponseEntity<?> uploadFile(MultipartFile[] attach) throws Exception {
        List<String> paths = new ArrayList<>();
        for (MultipartFile multipartFile : attach) {
            if (!multipartFile.isEmpty()) {
                paths.add(fileManager.fileSave("", multipartFile));
            }
        }
        return ResponseEntity.ok().body(paths);
    }
}
