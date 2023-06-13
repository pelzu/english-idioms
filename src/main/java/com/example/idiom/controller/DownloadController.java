package com.example.idiom.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

@Controller
public class DownloadController {



    @GetMapping("/download")
    public ResponseEntity<FileSystemResource> downloadFile() {
        String filePath = "src/main/resources/static/csv/idiom.csv";
        File file = new File(filePath);


        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        // Ustaw nagłówki odpowiedzi HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
        
        FileSystemResource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
