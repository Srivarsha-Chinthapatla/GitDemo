package com.demo.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
// where ever record not existing in db then our application throws this exception
@ResponseStatus(value= HttpStatus.NOT_FOUND)// we no need to use this annoattion in alla restapi which throws this exception so we are keeping this exception in common place
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){ //constructor
        super(message);
    }

}


