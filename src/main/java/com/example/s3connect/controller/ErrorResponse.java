package com.example.s3connect.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
        String errorCode;
        String errorMsg;
        int status;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
        LocalDateTime timestamp;

        public ErrorResponse(String errorCode, String errorMsg, int status) {
            super();
            this.errorCode = errorCode;
            this.errorMsg = errorMsg;
            this.status = status;
        }
}
