package com.diyo.airbnbmsback.exception;

import com.diyo.airbnbmsback.model.ExceptionMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PropertyNotFoundException.class)
    public String handleNotFoundException(PropertyNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionMessage>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ExceptionMessage> errorList = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            ExceptionMessage exceptionMessage = new ExceptionMessage(fieldError.getField(), fieldError.getDefaultMessage());
            errorList.add(exceptionMessage);

        });
        return ResponseEntity.ok(errorList);
    }

    @ExceptionHandler(MultipleBookingException.class)
    public String handleMultipleBookingException(MultipleBookingException e) {
        return e.getMessage();

    }

    @ExceptionHandler(BookingNotFoundException.class)
    public String handleBookingNotFoundException(BookingNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(AmountPaidMismatchException.class)
    public String handleAmountPaidMismatchException(AmountPaidMismatchException e) {
        return e.getMessage();
    }
}
