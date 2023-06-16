package com.letter_guide.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirestoreConfig {

   @Value("${app.firebase-configuration-file}")
   private String firebaseConfigPath;

   @Value("${app.project-id}")
   private String projectId;

   @Bean
   public Firestore firestore() throws IOException {
      FileInputStream serviceAccount = new FileInputStream(firebaseConfigPath);
      FirestoreOptions firestoreOptions =
            FirestoreOptions.getDefaultInstance().toBuilder()
                  .setProjectId(projectId)
                  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                  .build();
      return firestoreOptions.getService();
   }
}
