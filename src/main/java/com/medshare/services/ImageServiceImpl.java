package com.medshare.services;

import com.medshare.config.BucketName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final FileStore fileStore;

    @Override
    public ArrayList<String> saveImage(MultipartFile file) {
        //check if the file is empty
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        //Check if the file is an image
        if (!Arrays.asList("image/png", "image/gif", "image/jpeg").contains(file.getContentType())) {
            throw new IllegalStateException("FIle uploaded is not an image");
        }
        //get file metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        //Save Image in S3 and then save Todo in the database
        String path = String.format("%s/%s", BucketName.TODO_IMAGE.getBucketName(), UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            fileStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }

        return new ArrayList<String>(Arrays.asList(path, fileName));
    }

    @Override
    public byte[] downloadImage(Long id) {
//        Todo todo = repository.findById(id).get();
//        return fileStore.download(todo.getImagePath(), todo.getImageFileName());
        return new byte[0];

    }
}