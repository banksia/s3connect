package com.example.s3connect.controller;

import com.example.s3connect.service.S3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/store")
@Api(value="S3 file store", description="Operations for storing files in AWS S3")
public class S3Controller {
    @Autowired
    S3Service service;

    private static final Logger logger = LoggerFactory.getLogger(S3Controller.class);

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Upload file")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
        logger.trace("Multipart input: " + file.getOriginalFilename());
        File uploadFile = convertMultiPartToFile(file);
        service.uploadFile(uploadFile);
        uploadFile.delete();
        return file.getName();
    }

//    @GetMapping("/retrieve")
//    public String retrieveFile(@RequestPart(value = "url") String fileUrl) {
//        return service.retrieveFile(fileUrl);
//    }
//
//    @DeleteMapping("/delete")
//    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
//        return service.deleteFile(fileUrl);
//    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File uploadFile = new File(generateFileName(file));
        FileOutputStream fos = new FileOutputStream(uploadFile);
        fos.write(file.getBytes());
        fos.close();
        return uploadFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

}