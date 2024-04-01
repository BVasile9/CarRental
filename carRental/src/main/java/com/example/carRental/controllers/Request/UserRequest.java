package com.example.carRental.controllers.Request;

import com.example.carRental.entity.Reservation;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String Country;
    private String address;
    @Email
    private String email;
    private Reservation reservation;
}
