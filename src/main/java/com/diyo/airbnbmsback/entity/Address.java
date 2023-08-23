package com.diyo.airbnbmsback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "address_id_generator")
    @SequenceGenerator(name="address_id_generator",sequenceName = "address_id_generator")
    private long id;

    private int houseNumber; //optional
    @NotBlank(message = "Street Name cannot be blank")
    private String streetName;
    @NotBlank(message = "City cannot be blank")
    private String city;
    @NotBlank(message = "State cannot be blank")
    private String state;
    @NotBlank(message = "Zip Code cannot be blank")
    private String zipCode;

}
