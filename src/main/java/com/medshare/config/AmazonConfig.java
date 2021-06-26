package com.medshare.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Value("${aws.credentials.id}")
    private String awsId;
    @Value("${aws.credentials.secret}")
    private String awsSecret;

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials(this.awsId, this.awsSecret);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion("me-south-1")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }
}