package com.example.carRental.controllers.Response;

import com.example.carRental.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String country;
    private String address;
    private String email;
    private Reservation reservation;
}
