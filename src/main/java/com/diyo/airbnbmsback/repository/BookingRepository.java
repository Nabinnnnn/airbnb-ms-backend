package com.diyo.airbnbmsback.repository;


import com.diyo.airbnbmsback.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findByPropertyId(Long propertyId);
    List<Booking> findByPropertyIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(Long propertyId, LocalDate checkInDate, LocalDate checkOutDate);

    List<Booking> findByUserId(Long userId);
}

