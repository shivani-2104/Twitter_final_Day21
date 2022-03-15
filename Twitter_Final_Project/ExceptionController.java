package com.company.Twitter_Final_Project;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        return new ResponseEntity<>("Data not found ", HttpStatus.NOT_FOUND);
    }

}