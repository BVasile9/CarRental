package com.example.carRental.controllers.Response;

import com.example.carRental.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchResponse {
    private String city;
    private List<Car> cars;
}
