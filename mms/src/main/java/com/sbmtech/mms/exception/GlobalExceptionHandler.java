package com.sbmtech.mms.exception;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sbmtech.mms.controllers.TestController;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
	private static final Logger logger = LogManager.getLogger(TestController.class);

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
    	LocalDateTime dateTime = LocalDateTime.now();
    	String formattedDateTime = dateTime.format(formatter); 
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false),formattedDateTime);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
    	
    	LocalDateTime dateTime = LocalDateTime.now();
    	String formattedDateTime = dateTime.format(formatter); 
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), 
        		request.getDescription(false),formattedDateTime);
        logger.error("Error Message Logged=", ex);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}