package com.example.s3connect.service;


import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3Service {

    @Autowired
    AmazonS3 s3client;

    @Value("${s3bucket.bucketName}")
    String bucketName;

    private static final Logger logger = LoggerFactory.getLogger(S3Service.class);

    public void uploadFile(File file) {
        logger.trace("Will upload file " + file.getName() + " to " + bucketName);
        s3client.putObject(bucketName, file.getName(), file);
    }
}