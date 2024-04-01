package com.example.carRental.controllers;

import com.example.carRental.controllers.Request.ReservationRequest;
import com.example.carRental.controllers.Request.ReservationRequestIncomplete;
import com.example.carRental.controllers.Response.ReservationResponse;
import com.example.carRental.repository.CarRepository;
import com.example.carRental.repository.UserRepository;
import com.example.carRental.service.CarService;
import com.example.carRental.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final CarService carService;
    private final Logger logger= LoggerFactory.getLogger(ReservationController.class);

    public ReservationController(ReservationService reservationService, UserRepository userRepository, CarRepository carRepository, CarService carService) {
        this.reservationService = reservationService;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.carService = carService;
    }

    @PostMapping()
    public ResponseEntity<ReservationResponse> saveReservation(@RequestBody ReservationRequest reservationRequest){
        try {
            return new ResponseEntity<>(reservationService.saveReservation(reservationRequest), HttpStatus.CREATED);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveReservation")
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody ReservationRequestIncomplete reservation) {
        try {
            return new ResponseEntity<>(reservationService.addReservation(reservation),HttpStatus.CREATED);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/allReservations")
    public ResponseEntity<List<ReservationResponse>> getAllReservations(){
        try {
            return new ResponseEntity<>(reservationService.getAllReservations(),HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/reservationByEmail")
    public ResponseEntity<ReservationResponse> getReservationByEmail(@RequestParam("email")String email){
        try {
            return new ResponseEntity<>(reservationService.getReservationByEmail(email),HttpStatus.FOUND);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public void removeReservation(@RequestParam("id")Integer id){
        try {
            reservationService.removeReservation(id);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }





}
