package com.medshare.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface ImageService {
    ArrayList<String> saveImage(MultipartFile file);

    byte[] downloadImage(Long id);

}