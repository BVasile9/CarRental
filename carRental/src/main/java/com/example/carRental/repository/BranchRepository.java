package com.example.carRental.repository;

import com.example.carRental.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Integer> {

    Branch findByCity(String city);
}
