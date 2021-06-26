package com.medshare.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {
    TODO_IMAGE("medhsare-spring-amazon-storage");
    private final String bucketName;
}