package com.example.s3connect.service;

import com.amazonaws.services.s3.AmazonS3;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class S3ServiceTest {

    @Mock
    private AmazonS3 s3clientMock;

    @InjectMocks
    private S3Service service;

    @Test
    public void testUploadSuccessful() {
        File uploadFile = new File(this.getClass().getClassLoader().getResource("test-upload.json").getFile());

        service.uploadFile(uploadFile);
        verify(s3clientMock).putObject(any(), eq(uploadFile.getName()), eq(uploadFile));
        uploadFile.delete();
    }

}