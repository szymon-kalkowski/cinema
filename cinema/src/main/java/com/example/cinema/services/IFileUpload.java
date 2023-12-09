package com.example.cinema.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUpload {
    String uploadFile(MultipartFile multipartFile) throws IOException;
}
