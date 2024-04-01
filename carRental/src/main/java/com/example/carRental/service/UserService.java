package com.example.carRental.service;

import com.example.carRental.controllers.Request.UserRequest;
import com.example.carRental.controllers.Response.UserResponse;
import com.example.carRental.entity.User;
import com.example.carRental.helper.MapEntities;
import com.example.carRental.repository.ReservationRepository;
import com.example.carRental.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public UserService(UserRepository userRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<UserResponse> findALl() {
        return userRepository.findAll().stream()
                .map(MapEntities::mapUserToUserResponse).toList();
    }

    public UserResponse saveUser(UserRequest userRequest) {
        User user=MapEntities.mapUserRequestToUser(userRequest);
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("User already created");
        }
        return MapEntities.mapUserToUserResponse(userRepository.save(user));
    }

    public UserResponse findById(Integer id) {
        if(userRepository.findById(id).isPresent()){
            return MapEntities.mapUserToUserResponse(userRepository.findById(id).get());
        }
        throw new RuntimeException("User not found");
    }

//    public UserResponse addReservation(Integer idUser, Integer idReservation) {
//        if(userRepository.findById(idUser).isPresent()){
//            if(reservationRepository.findById(idReservation).isPresent()){
//                User user=userRepository.findById(idUser).get();
//                user.setReservation(reservationRepository.findById(idReservation).get());
//                return MapEntities.mapUserToUserResponse(user);
//            }else throw new RuntimeException("Reservation not found");
//        }
//        else throw new RuntimeException("User not found");
//    }
}
