package com.example.s3connect.controller;

import com.amazonaws.AmazonServiceException;
import com.example.s3connect.service.S3Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Web layer test
 */
@WebMvcTest(S3Controller.class)
@ExtendWith(SpringExtension.class)
class S3ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private S3Service s3ServiceMock;

    @Test
    public void testCreateRetrieveWithMockMVC() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "test-file.txt",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                "test data".getBytes());

        mockMvc.perform(multipart("/store/upload")
                .file(mockMultipartFile))
                .andExpect(status().isCreated());

        verify(s3ServiceMock, times(1)).uploadFile(any(File.class));
    }

    @Test
    public void testCreateRetrieveWithError() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "test-file.txt",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                "test data".getBytes());

        doThrow(new AmazonServiceException("Error occurred"))
                .when(s3ServiceMock)
                .uploadFile(any(File.class));

        mockMvc.perform(multipart("/store/upload")
                .file(mockMultipartFile))
                .andExpect(status().isInternalServerError());

        verify(s3ServiceMock, times(1)).uploadFile(any(File.class));

    }

}