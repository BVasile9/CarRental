package com.example.carRental.controllers;

import com.example.carRental.controllers.Request.UserRequest;
import com.example.carRental.controllers.Response.UserResponse;
import com.example.carRental.entity.Reservation;
import com.example.carRental.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final Logger logger= LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponse>> findAll(){
        try {
            return new ResponseEntity<>(userService.findALl(), HttpStatus.OK);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<UserResponse> findById(@RequestParam("id") Integer id){
        try {
            return new ResponseEntity<>(userService.findById(id),HttpStatus.FOUND);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody UserRequest userRequest){
        try {
            return new ResponseEntity<>(userService.saveUser(userRequest),HttpStatus.CREATED);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @PatchMapping("/addReservation")
//    public ResponseEntity<UserResponse> addReservation(@RequestParam("idUser") Integer idUser,@RequestParam("idReservation") Integer idReservation){
//        try {
//            return new ResponseEntity<>(userService.addReservation(idUser,idReservation),HttpStatus.OK);
//        }catch (Exception e){
//            logger.error(e.getMessage());
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
