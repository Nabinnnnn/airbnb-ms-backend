package com.diyo.airbnbmsback.controller;

import com.diyo.airbnbmsback.entity.Booking;
import com.diyo.airbnbmsback.entity.Property;
import com.diyo.airbnbmsback.exception.MultipleBookingException;
import com.diyo.airbnbmsback.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("booking")
@CrossOrigin("http://localhost:4200")

public class BookingController {
    @Autowired
    private BookingService bookingService;


    @PostMapping
    public ResponseEntity<String> bookTheProperty(@RequestBody Booking book){


       boolean isBooked = bookingService.multipleBooking(book.getProperty().getId(), book.getCheckInDate(),book.getCheckOutDate());
      if(isBooked){
           throw new MultipleBookingException("This date is already reserved");
       }
       else{
           return ResponseEntity.ok("Booking successful");
       }
      /* if(isBooked==true){
           return ResponseEntity.ok("Booking cannot be done because of multiple booking");
       } else{
        bookingService.bookTheProperty(book);
        return ResponseEntity.ok("Booking successful with Id " + book.getId());}*/

    }
    @GetMapping("/propertyId/{propertyId}")
    public ResponseEntity<List<Booking>> getTheBookingOfTheProperty(@PathVariable Long propertyId){
        List<Booking> bookingList=bookingService.getBookingOfProperty(propertyId);
        return ResponseEntity.ok(bookingList);

    }
    @GetMapping("/bookingId/{bookingId}")
    public ResponseEntity<Booking> getTheBookingByBookingId(@PathVariable Long bookingId){
        Booking booking=bookingService.getBookingByBookingId(bookingId);
        return ResponseEntity.ok(booking);

    }
    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Booking>>getTheBookingBookedByUser(@PathVariable Long userId){
        List<Booking> boookingList = bookingService.getBookingByUserId(userId);
        return ResponseEntity.ok(boookingList);

    }
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBooking(){
        List<Booking> bookingList =bookingService.getAll();
        return ResponseEntity.ok(bookingList);
    }

    @PutMapping("{bookingId}")
    public ResponseEntity<String> changeBooking(@RequestBody Booking newBooking,@PathVariable Long bookingId){
       bookingService.changeBooking(newBooking,bookingId);
       return ResponseEntity.ok("Booking Changed");

        }
        @DeleteMapping("{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId){
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok("Canceled successfully");
        }

        }





