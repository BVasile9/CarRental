package com.example.carRental.service;

import com.example.carRental.controllers.CarController;
import com.example.carRental.controllers.Request.BranchRequest;
import com.example.carRental.controllers.Response.BranchResponse;
import com.example.carRental.entity.Branch;
import com.example.carRental.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {
    private final CarController carController;
    private final BranchRepository branchRepository;

    public BranchService(CarController carController, BranchRepository branchRepository) {
        this.carController = carController;
        this.branchRepository = branchRepository;
    }

    public List<BranchResponse> findAll(){
        return branchRepository.findAll().stream().map(
                b->new BranchResponse(b.getCity(),b.getCars())
        ).toList();
    };

    public BranchResponse save(BranchRequest branchRequest) {
        Branch branch = new Branch();
        branch.setCity(branchRequest.getCity());
        branch.setCars(null);
        branchRepository.save(branch);
        BranchResponse branchResponse=new BranchResponse(branch.getCity(),null);
        return branchResponse;
    }

    public void removeBranch(Integer id) {

        branchRepository.deleteById(id);
    }
}
