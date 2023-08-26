package com.diyo.airbnbmsback.service;

import com.diyo.airbnbmsback.entity.Address;

import com.diyo.airbnbmsback.entity.Booking;
import com.diyo.airbnbmsback.entity.Property;
import com.diyo.airbnbmsback.exception.BookingNotFoundException;
import com.diyo.airbnbmsback.exception.PropertyNotFoundException;
import com.diyo.airbnbmsback.repository.BookingRepository;
import com.diyo.airbnbmsback.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    public void addProperty(Property property) {
       propertyRepository.save(property);


    }

    public Property getProperty(long id) {
        return propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFoundException("Property with id " +
                id + " not found"));
    }

    public List<Property> getAll() {
        return propertyRepository.findAll();
    }

    public List<Property> searchPropertyByName(String name) {
        return propertyRepository.findByName(name);
    }

    public void update(Property newProperty, long id) {
        Property oldProperty = propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFoundException("Property with id " +
                id + " not found"));
        oldProperty.setName(newProperty.getName());
        oldProperty.setPrice(newProperty.getPrice());
        oldProperty.setAddress(newProperty.getAddress());

        oldProperty.setDescription(newProperty.getDescription());

propertyRepository.save(oldProperty);
    }

    public void delete(long id) {
       List<Booking> book = bookingService.getBookingOfProperty(id);

       if (book==null){

        propertyRepository.deleteById(id);}
       else{
           throw new BookingNotFoundException("Booked property cannot be deleted");
       }

    }

    public List<Property> findProperty(double price) {
        return propertyRepository.findAllByPriceLessThanEqual(price);
    }

    public List<Property> findProperty(String city) {
        return propertyRepository.findByAddressCity(city);
    }

    public Address getAddress(long id) {
         Property property=propertyRepository.findById(id).orElseThrow(()->new PropertyNotFoundException("Not found"));
        return property.getAddress();
    }
}
