package com.tourmanagementapp.tourmanagement.repository;

import com.tourmanagementapp.tourmanagement.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends MongoRepository<Company,Integer> {
    Company findByBranchId(String branchId);
}
