package com.pulsefeed.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.*;
import java.nio.file.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class FileUploadController {

    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file){
        try {
            File uploadDirectory = new File(UPLOAD_DIR);
            if (!uploadDirectory.exists()){
                uploadDirectory.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR,fileName);

            Files.write(filePath,file.getBytes());

            String fileUrl = "https://localhost:8080" + fileName;
            return ResponseEntity.ok().body(
                    String.format("File Uploaded Successfully! URL %s" ,fileUrl)
            );

        }catch (IOException e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error Uploading a File :"+e.getMessage());
        }
    }
}
