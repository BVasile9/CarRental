package com.example.carRental.repository;

import com.example.carRental.controllers.Response.CarResponse;
import com.example.carRental.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    Optional<Car> findByVin(String vin);

    List<Car> findByBranchId(Integer id);
}
