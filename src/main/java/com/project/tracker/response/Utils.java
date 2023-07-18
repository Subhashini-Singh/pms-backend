package com.project.tracker.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Utils {
    public static ResponseEntity<String> getResponseEntity(String response, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+response+"\"}",httpStatus);
    }
}
