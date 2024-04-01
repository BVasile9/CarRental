package com.example.carRental.controllers;

import com.example.carRental.controllers.Request.CarRequest;
import com.example.carRental.controllers.Response.CarResponse;
import com.example.carRental.entity.Branch;
import com.example.carRental.entity.Car;
import com.example.carRental.repository.BranchRepository;
import com.example.carRental.repository.CarRepository;
import com.example.carRental.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/cars")
public class CarController {
    private final CarService carService;
    private final CarRepository carRepository;
    private final BranchRepository branchRepository;
    private final Logger logger= LoggerFactory.getLogger(CarController.class);

    @Autowired
    public CarController(CarService carService, CarRepository carRepository, BranchRepository branchRepository) {
        this.carService = carService;
        this.carRepository = carRepository;
        this.branchRepository = branchRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<CarResponse> saveCar(@RequestBody CarRequest carRequest){
        try{
            CarResponse carResponse=carService.saveCar(carRequest);
            return new ResponseEntity<>(carResponse,HttpStatusCode.valueOf(200));
        }
        catch (Exception e){
//            System.out.println(e.getMessage());
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @PostMapping("/saveWithBranch")
    public ResponseEntity<CarResponse> saveCarWithBranch(@RequestBody CarRequest carRequest,@RequestParam("city") String city){
        try {
            if(branchRepository.findByCity(city)!=null){
                carRequest.setBranch(branchRepository.findByCity(city));
            }else throw new RuntimeException("Branch not found");
            return new ResponseEntity<>(carService.saveCarWithBranch(carRequest), HttpStatus.CREATED);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/get")
    public ResponseEntity<CarResponse> findByVin(@RequestParam("vin")String vin){
        try {
            CarResponse carResponse=carService.findByVin(vin);
            return new ResponseEntity<>(carResponse,HttpStatusCode.valueOf(200));
        }
        catch (Exception e){
           // System.out.println(e.getMessage());
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CarResponse>>findAll(){
       return new ResponseEntity<>(carService.findAll(),HttpStatusCode.valueOf(200));
    }

    @GetMapping("/sameBranch")
    public ResponseEntity<List<CarResponse>> sameBranch(@RequestParam("city")String city){
       try {
           Branch branch=branchRepository.findByCity(city);
           Integer id=branch.getId();
           List<CarResponse> cars=carService.sameBranch(id);
           return new ResponseEntity<>(cars,HttpStatusCode.valueOf(200));
       }catch (Exception e){
           logger.error(e.getMessage());
           return new ResponseEntity<>(HttpStatusCode.valueOf(500));
       }
    }




    @PatchMapping("/branchUpdate")
    public ResponseEntity<CarResponse> branchUpdate(@RequestParam("branchid") Integer branchid,@RequestParam("id")Integer id){
        try {
            return new ResponseEntity<>(carService.branchUpdate(id,branchid),HttpStatusCode.valueOf(200));
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }




    @DeleteMapping("/removeByVin")
    public void removeByVin(@RequestParam("vin")String vin){
        if(carRepository.findByVin(vin).isPresent()) {
            removeById(carRepository.findByVin(vin).get().getId());
        }
        else throw new RuntimeException("Car not found");
    }

    @DeleteMapping("/remove")
    public void removeById(@RequestParam("id")Integer id){
        try{
            carService.removeById(id);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }


}
