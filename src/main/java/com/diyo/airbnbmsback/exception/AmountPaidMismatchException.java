package com.diyo.airbnbmsback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class AmountPaidMismatchException extends RuntimeException{
    public AmountPaidMismatchException(String message){
        super(message);
    }

}
