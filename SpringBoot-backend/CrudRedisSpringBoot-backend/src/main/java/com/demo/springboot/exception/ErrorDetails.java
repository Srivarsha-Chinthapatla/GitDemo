package com.demo.springboot.exception;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;


}
