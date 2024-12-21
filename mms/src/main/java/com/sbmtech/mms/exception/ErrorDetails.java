package com.sbmtech.mms.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private int statusCode;
    private String message;
    private String details;
    private String timestamp;
    
}