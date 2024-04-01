package com.example.carRental.controllers.Request;

import com.example.carRental.entity.Car;
import com.example.carRental.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private User user;
    private Car car;
}
