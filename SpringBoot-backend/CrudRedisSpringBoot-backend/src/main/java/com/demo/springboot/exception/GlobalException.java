package com.demo.springboot.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

@ControllerAdvice
public class GlobalException {
    //for handling specific exceptions
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<?> handleStudentNotFoundException(StudentNotFoundException exception, WebRequest request) {
        ErrorDetails error = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

}
