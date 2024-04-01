package com.example.carRental.controllers;

import com.example.carRental.controllers.Request.BranchRequest;
import com.example.carRental.controllers.Response.BranchResponse;
import com.example.carRental.repository.BranchRepository;
import com.example.carRental.service.BranchService;
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
@RequestMapping("/api/v1/branch")
public class BranchController {
    private final BranchService branchService;
    private final BranchRepository branchRepository;
    private final Logger logger= LoggerFactory.getLogger(BranchController.class);

    @Autowired
    public BranchController(BranchService branchService, BranchRepository branchRepository) {
        this.branchService = branchService;
        this.branchRepository = branchRepository;
    }

    @GetMapping("/get")
    public ResponseEntity<List<BranchResponse>> findAll(){
        try {
           return new ResponseEntity<>(branchService.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }



    @PostMapping("/save")
    public ResponseEntity<BranchResponse> saveBranch(@RequestBody BranchRequest branchRequest){
        try{
            return new ResponseEntity<>(branchService.save(branchRequest),HttpStatusCode.valueOf(200));
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }



    @DeleteMapping("/remove")
    public void removeBranch(@RequestParam("id")Integer id){
        branchService.removeBranch(id);
    }

    @DeleteMapping("/removeByCity")
    public void removeBranchByCity(@RequestParam("city")String city){
        branchService.removeBranch(
                branchRepository.findByCity(city).getId()
        );
    }


}
