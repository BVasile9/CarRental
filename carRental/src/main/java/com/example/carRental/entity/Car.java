package com.example.carRental.entity;

import com.example.carRental.enums.CarBrand;
import com.example.carRental.enums.CarStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String vin;
    @Enumerated(EnumType.STRING)
    private CarBrand brand;
    private String model;
    private String color;
    @Column(name = "year_of_fabrication")
    private short yearOfFabrication;
    private long mileage;
    @Column(name = "price_per_day")
    private double pricePerDay;
    @Enumerated(EnumType.STRING)
    private CarStatus status;

    @ManyToOne
    @JoinColumn(name="branch_id")
    @JsonBackReference
    private Branch branch;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private Reservation reservation;
}
