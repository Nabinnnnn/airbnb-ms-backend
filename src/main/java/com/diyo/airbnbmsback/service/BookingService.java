package com.diyo.airbnbmsback.service;

import com.diyo.airbnbmsback.entity.Booking;
import com.diyo.airbnbmsback.entity.Payment;
import com.diyo.airbnbmsback.entity.Property;
import com.diyo.airbnbmsback.exception.AmountPaidMismatchException;
import com.diyo.airbnbmsback.exception.BookingNotFoundException;
import com.diyo.airbnbmsback.exception.MultipleBookingException;
import com.diyo.airbnbmsback.exception.PropertyNotFoundException;
import com.diyo.airbnbmsback.repository.BookingRepository;
import com.diyo.airbnbmsback.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PropertyRepository propertyRepository;
   /* @Autowired
    private   BookingService bookingService;*/ //Relying upon circular references is discouraged

    public  boolean multipleBooking(Long propertyId, LocalDate checkInDate, LocalDate checkOutDate){
        List<Booking> existingBooking = bookingRepository.findByPropertyIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(propertyId,checkInDate,checkOutDate);
        if (existingBooking.isEmpty()){
            return false;
        }
        return true;

    }
    public void bookTheProperty(Booking book,Long propertyId){

       Property property = propertyRepository.findById(propertyId).orElseThrow(()->new PropertyNotFoundException("Property not found with ID:" + propertyId));

       if(checkPayment(property.getPrice(),book.getCheckInDate(),book.getCheckInDate(),book.getPayment()))
       {  if(property!=null){
        bookingRepository.save(book);}
    else {
           throw new MultipleBookingException("This date is already reserved"); }
    }
    else {
        throw new AmountPaidMismatchException("Please pay full amount" );
       }
     }

    public List<Booking> getBookingOfProperty(Long propertyId) {
        //Property property = propertyRepository.findById(propertyId).orElseThrow(()->new PropertyNotFoundException("Property not found with ID:" + propertyId));
List<Booking> propertyBookingList = bookingRepository.findByPropertyId(propertyId);
return propertyBookingList;
    }

    public List<Booking> getAll() {
        List<Booking> bookingList = bookingRepository.findAll();
        return bookingList;
    }

    public void changeBooking(Booking newBooking, Long bookingId) {
        Booking oldBooking = bookingRepository.findById(bookingId).orElseThrow(() -> new PropertyNotFoundException("Please enter correct booking id"));
        oldBooking.setPayment(newBooking.getPayment());
        oldBooking.setCheckInDate(newBooking.getCheckInDate());
        oldBooking.setCheckOutDate(newBooking.getCheckOutDate());
        oldBooking.setGuestCount(newBooking.getGuestCount());

        Property property = propertyRepository.findById(newBooking.getProperty().getId()).orElseThrow(()->new PropertyNotFoundException("Property not found "));

        if(checkPayment(property.getPrice(),newBooking.getCheckInDate(),newBooking.getCheckInDate(),newBooking.getPayment()))
        {  if(property!=null){
            bookingRepository.save(oldBooking);}
        else {
            throw new MultipleBookingException("This date is already reserved"); }
        }
        else {
            throw new AmountPaidMismatchException("Please pay full amount" );
        }
    }






    public Booking getBookingByBookingId(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()->new BookingNotFoundException("Booking with id "+ bookingId+" not found"));
        return booking;
    }

    public void cancelBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    public List<Booking> getBookingByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public boolean checkPayment(double price, LocalDate checkInDate, LocalDate checkOutDate, Payment payment){
       long noOfDaysStay =  ChronoUnit.DAYS.between(checkInDate,checkOutDate);
       double amountToBePaid= price*noOfDaysStay;
       if(amountToBePaid==payment.getAmountPaid()){return true;}
       else{
           return false;
       }

    }
}

     /*   if(multipleBooking(newBooking.getPropertyId(),newBooking.getCheckInDate(),newBooking.getCheckOutDate())!=true){
        bookingRepository.save(oldBooking);
        }
        else{
        throw new MultipleBookingException("This date is already reserved");}}*/
        /*bookingService.bookTheProperty(oldBooking);*/