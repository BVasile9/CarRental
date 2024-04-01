package com.example.carRental.service;

import com.example.carRental.controllers.Request.CarRequest;
import com.example.carRental.controllers.Response.CarResponse;
import com.example.carRental.entity.Branch;
import com.example.carRental.repository.BranchRepository;
import com.example.carRental.repository.CarRepository;
import com.example.carRental.entity.Car;
import com.example.carRental.helper.MapEntities;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final BranchRepository branchRepository;

    public CarService(CarRepository carRepository, BranchRepository branchRepository) {
        this.carRepository = carRepository;
        this.branchRepository = branchRepository;
    }

    public CarResponse saveCar(CarRequest carRequest) {
//        Car car=new Car(carRequest.getVin(),
//                carRequest.getBrand(),
//                carRequest.getModel(),
//                carRequest.getColor(),
//                carRequest.getYearOfFabrication(),
//                carRequest.getMileage(),
//                carRequest.getPricePerDay(),
//                carRequest.getStatus());
        Car car = MapEntities.mapCarRequestToCar(carRequest);
        carRepository.save(car);
        return MapEntities.mapCarToCarResponse(car);
    }

    public CarResponse findByVin(String vin) {
        Optional<Car> carFound=carRepository.findByVin(vin);
        if(carFound.isEmpty()){
            throw new RuntimeException("Car not found");
        }
        Car car=carFound.get();
        if(car.getReservation()!=null )System.out.println(car.getReservation().toString());
        return MapEntities.mapCarToCarResponse(car);
    }

    public void removeById(Integer id) {
        Optional<Car> car=carRepository.findById(id);
        if(car.isEmpty()){
            throw new RuntimeException("Car not found");
        }
        carRepository.deleteById(id);
    }
    public List<CarResponse> findAll(){
        return carRepository.findAll().stream()
                .map(c ->new CarResponse(c.getVin(),c.getBrand(),c.getModel(),c.getColor()
                ,c.getYearOfFabrication(),c.getMileage(),c.getPricePerDay(),c.getStatus(),c.getBranch(),c.getReservation())).toList();
    }

    public List<CarResponse> sameBranch(Integer id) {
        return carRepository.findByBranchId(id).stream()
                .map(c ->new CarResponse(c.getVin(),c.getBrand(),c.getModel(),c.getColor(),c.getYearOfFabrication()
                ,c.getMileage(),c.getPricePerDay(),c.getStatus(),c.getBranch(),c.getReservation())).toList();
    }

    public CarResponse branchUpdate(Integer id,Integer branchid){
        if(carRepository.findById(id).isPresent()&&branchRepository.findById(branchid).isPresent()){
            Car car=carRepository.findById(id).get();
            Branch branch=branchRepository.findById(branchid).get();
            car.setBranch(branch);
//            List<Car> branchCars=branch.getCars();
//            branchCars.add(car);
//            branch.setCars(branchCars);
//            this.branchRepository.save(branch);
            this.carRepository.save(car);
            return MapEntities.mapCarToCarResponse(car);
        }
        else throw new RuntimeException("Car or branch not found");
    }

    public CarResponse saveCarWithBranch(CarRequest carRequest) {
        Car car=MapEntities.mapCarRequestToCar(carRequest);
        carRepository.save(car);
        return MapEntities.mapCarToCarResponse(car);
    }
}
