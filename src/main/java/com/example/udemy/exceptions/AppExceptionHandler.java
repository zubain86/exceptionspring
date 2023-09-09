package com.example.udemy.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
// Single class that handles all the exceptions
@ControllerAdvice
public class AppExceptionHandler {
    //Following annotation declares the method to be handling exceptions....Here this method is handling UserException class
    @ExceptionHandler(value = {UserException.class})
    public ResponseEntity<Object> handleUserServiceException(UserException userException, WebRequest web){
        ErrorMessage errorMessage = new ErrorMessage(userException.getMessage(), new Date());
        return new ResponseEntity<>(errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleUserServiceException(Exception userException, WebRequest web){
        ErrorMessage errorMessage = new ErrorMessage(userException.getMessage(), new Date());
        return new ResponseEntity<>(errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
