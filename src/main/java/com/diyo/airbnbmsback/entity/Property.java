package com.diyo.airbnbmsback.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="property")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "property_id_generator")
    @SequenceGenerator(name="property_id_generator",sequenceName = "property_id_generator")
    private long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String description;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="address_id")
    @Valid
    private Address address;
    @NotNull(message="Price cannot be null")
    private double price;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="bookingId")
    private List<Booking> booking;

}
