package com.diyo.airbnbmsback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "paymentId")
    @SequenceGenerator(name = "paymentId",sequenceName = "paymentId")
    private long id;
    @NotNull(message = "Please enter the amount")
    private double amountPaid;

    private LocalDate paymentTime;


}
