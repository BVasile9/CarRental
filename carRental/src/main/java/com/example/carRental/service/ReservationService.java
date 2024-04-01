package com.example.carRental.service;

import com.example.carRental.controllers.Request.ReservationRequest;
import com.example.carRental.controllers.Request.ReservationRequestIncomplete;
import com.example.carRental.controllers.Response.ReservationResponse;
import com.example.carRental.entity.Car;
import com.example.carRental.entity.Reservation;
import com.example.carRental.entity.User;
import com.example.carRental.enums.CarStatus;
import com.example.carRental.helper.MapEntities;
import com.example.carRental.repository.CarRepository;
import com.example.carRental.repository.ReservationRepository;
import com.example.carRental.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public ReservationResponse saveReservation(ReservationRequest reservationRequest) {
        Reservation reservation=MapEntities.mapReservationRequestToReservation(reservationRequest);
            reservationRepository.save(reservation);
        return MapEntities.mapReservationToReservationResponse(reservation);
    }
    public List<ReservationResponse> getAllReservations(){
        return reservationRepository.findAll().stream().map(MapEntities::mapReservationToReservationResponse).toList();
    }

    public ReservationResponse addReservation(ReservationRequestIncomplete reservation) {
        ReservationRequest reservationRequest = new ReservationRequest();
        if (carRepository.findByVin(reservation.getCarVin()).isPresent()) {
            if (userRepository.existsByEmail(reservation.getEmail())) {
                reservationRequest.setDateFrom(reservation.getDateFrom());
                reservationRequest.setDateTo(reservation.getDateTo());
                reservationRequest.setCar(carRepository.findByVin(reservation.getCarVin()).get());
                reservationRequest.setUser(userRepository.findByEmail(reservation.getEmail()).get());
            } else throw new RuntimeException("User not found");
        }else throw new RuntimeException("Car not found");
        Reservation reservation1=MapEntities.mapReservationRequestToReservation(reservationRequest);
        reservationRepository.save(reservation1);
        Car car=reservation1.getCar();
        car.setStatus(CarStatus.BOOKED);
        carRepository.save(car);
        return MapEntities.mapReservationToReservationResponse(reservation1);
    }

    public void removeReservation(Integer id) {
        Optional<Reservation> reservation=reservationRepository.findById(id);
        if(reservation.isEmpty()){
            throw new RuntimeException("Reservation not found");
        }
        Reservation reservation1=reservation.get();
        reservation1.setCar(null);
        reservation1.setUser(null);
//        reservationRepository.save(reservation1);
//        reservation1=reservationRepository.findById(id).get();
        reservationRepository.delete(reservation1);
        if(reservationRepository.findById(id).isPresent())throw new RuntimeException("Fail");
    }

    public ReservationResponse getReservationByEmail(String email) {
        User user=userRepository.findByEmail(email).get();
        Reservation reservation=user.getReservation();
        return MapEntities.mapReservationToReservationResponse(reservation);
    }
}
