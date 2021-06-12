package com.medshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class MedShareApplication implements WebMvcConfigurer{
	public static void main(String[] args) {
		SpringApplication.run(MedShareApplication.class, args);
	}

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		exposeDirectory("src/main/resources/allImages", registry);
//	}
//	private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
//		Path uploadDir = Paths.get(dirName);
//		System.out.println(uploadDir);
//		String uploadPath = uploadDir.toFile().getAbsolutePath();
//		System.out.println("upload path: " +uploadPath);
//		if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
//
//		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/"+ uploadPath + "/");
//	}
}
