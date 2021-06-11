package com.medshare.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public
class UploadFileService {

        @Value("./src/main/resources/allImages/")
        public String uploadDir;

        public String uploadFile(MultipartFile file) {
            try {
                Path copyLocation = Paths
                        .get( uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));

                if (!Files.exists(copyLocation)) {
                    Files.createDirectories(copyLocation);
                }

                Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
//
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Could not store file " + file.getOriginalFilename()
                        + ". Please try again!");
                return null;
            }

            return StringUtils.cleanPath(file.getOriginalFilename());
        }


}
