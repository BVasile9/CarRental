package com.example.carRental.helper;

import com.example.carRental.controllers.Request.CarRequest;
import com.example.carRental.controllers.Request.ReservationRequest;
import com.example.carRental.controllers.Request.UserRequest;
import com.example.carRental.controllers.Response.CarResponse;
import com.example.carRental.controllers.Response.ReservationResponse;
import com.example.carRental.controllers.Response.UserResponse;
import com.example.carRental.entity.Car;
import com.example.carRental.entity.Reservation;
import com.example.carRental.entity.User;

public class MapEntities {

    public static Car mapCarRequestToCar(CarRequest carRequest) {
        Car car = new Car();
        car.setBrand(carRequest.getBrand());
        car.setModel(carRequest.getModel());
        car.setColor(carRequest.getColor());
        car.setVin(carRequest.getVin());
        car.setMileage(carRequest.getMileage());
        car.setStatus(carRequest.getStatus());
        car.setPricePerDay(carRequest.getPricePerDay());
        car.setYearOfFabrication(carRequest.getYearOfFabrication());
        car.setBranch(carRequest.getBranch());
        car.setReservation(carRequest.getReservation());
        return car;
    }

    public static CarResponse mapCarToCarResponse(Car car){
        CarResponse carResponse=new CarResponse();
        carResponse.setBrand(car.getBrand());
        carResponse.setModel(car.getModel());
        carResponse.setColor(car.getColor());
        carResponse.setVin(car.getVin());
        carResponse.setMileage(car.getMileage());
        carResponse.setStatus(car.getStatus());
        carResponse.setPricePerDay(car.getPricePerDay());
        carResponse.setYearOfFabrication(car.getYearOfFabrication());
        carResponse.setBranch(car.getBranch());
        carResponse.setReservation(car.getReservation());
        return carResponse;

    }

    public static UserResponse mapUserToUserResponse(User user){
        UserResponse userResponse=new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setCountry(user.getCountry());
        userResponse.setAddress(user.getAddress());
        userResponse.setEmail(user.getEmail());
//        userResponse.setReservation(user.getReservation());
        return userResponse;
    }

    public static User mapUserRequestToUser(UserRequest userRequest){
        User user=new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setCountry(userRequest.getCountry());
        user.setAddress(userRequest.getAddress());
        user.setEmail(userRequest.getEmail());
        return user;
    }
    public static ReservationResponse mapReservationToReservationResponse(Reservation reservation){
        ReservationResponse reservationResponse=new ReservationResponse();

        reservationResponse.setDateTo(reservation.getDateTo());
        reservationResponse.setDateFrom(reservation.getDateFrom());
        reservationResponse.setUser(reservation.getUser());
        reservationResponse.setCar(reservation.getCar());
        return reservationResponse;
    }
    public static Reservation mapReservationRequestToReservation(ReservationRequest reservationRequest){
        Reservation reservation=new Reservation();
        reservation.setDateFrom(reservationRequest.getDateFrom());
        reservation.setDateTo(reservationRequest.getDateTo());
        reservation.setUser(reservationRequest.getUser());
        reservation.setCar(reservationRequest.getCar());
        return reservation;
    }
}
