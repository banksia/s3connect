package com.example.s3connect.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        String errorMessage = exception.getClass() + ": " + exception.getMessage();
        logger.error(errorMessage);
        return new ResponseEntity(
                new ErrorResponse("SERVER_ERROR", errorMessage, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


//    @ExceptionHandler(value = IOException.class)
//    public ResponseEntity<ErrorResponse> handleIOException(IOException exception) {
//        String errorMessage = exception.getClass() + ": " + exception.getMessage();
//        logger.error(errorMessage);
//        return new ResponseEntity(
//                new ErrorResponse("IO_EXCEPTION", errorMessage, HttpStatus.INTERNAL_SERVER_ERROR.value()),
//                HttpStatus.INTERNAL_SERVER_ERROR
//        );
//    }

}
