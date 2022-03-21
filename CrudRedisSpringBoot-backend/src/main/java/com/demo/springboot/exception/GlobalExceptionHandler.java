package com.demo.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice // to handle exceptions globally
public class GlobalExceptionHandler {

    // handle specific exceptions  : RESOUSE NOT FOUND
    @ExceptionHandler(ResourceNotFoundException.class)
    // exception handler is used to hanlde specific exception and send the custom response to the client
    public ResponseEntity<?> handleResourceNotFoundException
    (ResourceNotFoundException exception, WebRequest request) {  //webrequest : we retriew url from webreqest and send it to client
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }


}
