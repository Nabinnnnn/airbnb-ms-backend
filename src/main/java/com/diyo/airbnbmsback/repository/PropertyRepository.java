package com.diyo.airbnbmsback.repository;

import com.diyo.airbnbmsback.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property,Long> {
     List<Property> findByName(String Name);
     List<Property> findAllByPriceLessThanEqual(double maxPrice);
     List<Property> findByAddressCity(String city);
}


