package com.example.demo.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FirebaseFileService {
    private Storage storage;
    @Value("${firebase.storage.bucketName}")
    private String bucketName;
    @Value("${firebase.storage.projectId}")
    private String projectId;
    @Value("${firebase.storage.url}")
    private String url;

    @EventListener
    public void init(ApplicationReadyEvent event) {
        try {
            ClassPathResource serviceAccount = new ClassPathResource("serviceAccountKey.json");
            storage = StorageOptions.newBuilder().
                    setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream())).
                    setProjectId(projectId)
                    .build().getService();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String saveFile(MultipartFile file, String extensionFile) throws IOException {
        String imageName = generateFileName(extensionFile);
        BlobId blobId = BlobId.of(bucketName, imageName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();
        storage.create(blobInfo, file.getInputStream());
        // Hacer el archivo público
        storage.createAcl(blobId, Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        return url.concat(bucketName).concat("/").concat(imageName);
    }

    private String generateFileName(String extensionFile) {
        return UUID.randomUUID().toString() + "." + extensionFile;
    }
}
