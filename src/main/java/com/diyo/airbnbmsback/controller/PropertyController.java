package com.diyo.airbnbmsback.controller;

import com.diyo.airbnbmsback.entity.Address;
import com.diyo.airbnbmsback.entity.Property;
import com.diyo.airbnbmsback.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("property")
@CrossOrigin("http://localhost:4200")


public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<String> addProperty(@Valid @RequestBody Property property) {
        propertyService.addProperty(property);
        return ResponseEntity.ok("Property added");
    }

    @GetMapping("{id}")
    public ResponseEntity<Property> getProperty(@PathVariable long id){
      Property property =propertyService.getProperty(id);
      return ResponseEntity.ok(property);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Property>> searchPropertyByName(@PathVariable String name){
        List<Property> property = propertyService.searchPropertyByName(name);
        return ResponseEntity.ok(property);
    }
    @GetMapping("/price/{price}")
    public ResponseEntity<List<Property>> findProperty(@PathVariable double price){
        List<Property> property = propertyService.findProperty(price);
        return ResponseEntity.ok(property);
    }
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Property>> findProperty(@PathVariable String city){
        List<Property> property = propertyService.findProperty(city);
        return ResponseEntity.ok(property);
    }
    @GetMapping
    public ResponseEntity<List<Property>> getAll(){
        List<Property> propertyList = propertyService.getAll();
        return ResponseEntity.ok(propertyList);
    }
    @PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody Property property,@PathVariable long id){
        propertyService.update(property,id);
        return ResponseEntity.ok("Property updated successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        propertyService.delete(id);
        return  ResponseEntity.ok("Deleted successfully.");

    }
    @GetMapping("address/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable long id){
        Address address=propertyService.getAddress(id);
        return ResponseEntity.ok(address);
    }



}
