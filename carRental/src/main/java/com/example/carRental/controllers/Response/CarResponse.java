package com.example.carRental.controllers.Response;

import com.example.carRental.entity.Branch;
import com.example.carRental.entity.Reservation;
import com.example.carRental.enums.CarBrand;
import com.example.carRental.enums.CarStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {

    private String vin;
    @Enumerated(EnumType.STRING)
    private CarBrand brand;
    private String model;
    private String color;
    private short yearOfFabrication;
    private long mileage;
    private double pricePerDay;
    @Enumerated(EnumType.STRING)
    private CarStatus status;
    private Branch branch;
    private Reservation reservation;



}
