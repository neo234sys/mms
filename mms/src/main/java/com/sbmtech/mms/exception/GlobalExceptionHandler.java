package com.sbmtech.mms.exception;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sbmtech.mms.constant.CommonConstants;
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
    
    @ExceptionHandler(SubscriptionException.class)
    @ResponseBody
    public ResponseEntity<Object> handleSubscriptionException(SubscriptionException ex, WebRequest request) {

    	HttpStatus httpStatus=HttpStatus.OK;
        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("timestamp", new Date());
        objectBody.put("status", 200);
        objectBody.put("responseCode", CommonConstants.FAILURE_CODE);
        objectBody.put("responseMessage", CommonConstants.FAILURE_DESC);
      
        objectBody.put("data", ex.getMessage());
 
        return new ResponseEntity<>(objectBody, httpStatus);
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
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
                    MethodArgumentNotValidException exception,
                    HttpHeaders httpHeaders, HttpStatus httpStatus,
                    WebRequest webRequest){
    	httpStatus=HttpStatus.OK;
        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("timestamp", new Date());
        objectBody.put("status", 200);
        
        objectBody.put("responseCode",  CommonConstants.FAILURE_CODE);
        objectBody.put("responseMessage",  CommonConstants.FAILURE_DESC);
        
 
        // Get all errors
        List<String> exceptionalErrors
                  = exception.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(x -> x.getDefaultMessage())
                        .collect(Collectors.toList());
 
        objectBody.put("data", exceptionalErrors);
 
        return new ResponseEntity<>(objectBody, httpStatus);
    }
}