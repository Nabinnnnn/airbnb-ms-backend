package com.diyo.airbnbmsback.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "bookingId")
    @SequenceGenerator(name="bookingId",sequenceName = "bookingId")
    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int guestCount;

   // private Long propertyId;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="paymentId")

    private Payment payment;
    private Long userId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="propertyId")
    private Property property;
}
