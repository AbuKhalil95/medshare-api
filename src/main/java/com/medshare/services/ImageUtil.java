package com.medshare.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUtil {
    public String saveImages(MultipartFile image){

        Path currentPath = Paths.get(".");
        Path absolutePath = currentPath.toAbsolutePath();
        String folder=absolutePath+ "/src/main/resources/static/posts/";
        byte[] bytes = new byte[0];
        try {
            bytes = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path path = Paths.get(folder + image.getOriginalFilename());
        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image.getOriginalFilename();
    }
}
