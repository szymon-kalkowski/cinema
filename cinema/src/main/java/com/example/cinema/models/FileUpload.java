package com.example.cinema.models;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileUpload {
    String uploadFile(MultipartFile multipartFile) throws IOException;
}
