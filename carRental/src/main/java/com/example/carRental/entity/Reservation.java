package com.example.carRental.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id ;

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Bucharest")
    private LocalDate dateFrom;

//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Bucharest")
    private LocalDate dateTo;

    @OneToOne
    @JoinColumn(name = "user_id")
//    @JsonBackReference
    private User user;

    @OneToOne
    @JoinColumn(name="car_id")
//    @JsonBackReference
    private Car car;
}
