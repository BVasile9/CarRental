package com.example.carRental.controllers.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestIncomplete {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String carVin;
    private String email;
}
