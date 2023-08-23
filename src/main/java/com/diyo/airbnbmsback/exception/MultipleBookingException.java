package com.diyo.airbnbmsback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class MultipleBookingException extends RuntimeException{
    public MultipleBookingException(String message){
        super(message);
    }
}
