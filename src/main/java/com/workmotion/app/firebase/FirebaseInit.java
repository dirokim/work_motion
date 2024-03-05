package com.workmotion.app.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;

@Service
public class FirebaseInit {


    @PostConstruct
    public void initialize() throws Exception {
        File file = new ClassPathResource("firebaseKey.json").getFile();

        FileInputStream serviceAccount =
                new FileInputStream(file);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("workmotion-c3939.appspot.com")
                .build();

        FirebaseApp.initializeApp(options);
    }
}
