package com.medshare.services;

import com.medshare.config.BucketName;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ImageServiceImpl implements ImageService {
    private final FileStore fileStore;

    public ImageServiceImpl(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    // TODO: Implement AWS image folder per user to upload, use and delete images related to each user
    @Override
    public ArrayList<String> saveImage(MultipartFile file) {
        //check if the file is empty
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        //Check if the file is an image
        System.out.println("file image upload type" + file.getContentType());
        if (!Arrays.asList("image/png", "image/gif", "image/jpeg").contains(file.getContentType())) {
            throw new IllegalStateException("FIle uploaded is not an image");
        }
        //get file metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        //Save Image in S3
        String path = String.format("%s/%s", BucketName.TODO_IMAGE.getBucketName(), UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());

        try {
            fileStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }

        return new ArrayList<String>(Arrays.asList(fileStore.getUrl(path, fileName).toString(), fileName));
    }

    @Override
    public byte[] downloadImage(Long id) {

        return new byte[0];

    }
}