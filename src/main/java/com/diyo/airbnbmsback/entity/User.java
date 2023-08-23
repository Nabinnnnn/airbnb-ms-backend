package com.diyo.airbnbmsback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "userId")
    @SequenceGenerator(name="userId", sequenceName = "userId")
    private Long id;
    @NotNull(message="Please enter your name")
    private String name;
    @NotNull(message ="Please enter your phone number")
    private String phoneNumber;
    private LocalDate dob;
    @NotNull(message="Please enter email")
    private String email;
    @NotNull(message="Please enter your password")
    private String password;

}
