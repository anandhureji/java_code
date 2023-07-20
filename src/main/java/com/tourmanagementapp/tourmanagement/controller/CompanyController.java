package com.tourmanagementapp.tourmanagement.controller;

import com.tourmanagementapp.tourmanagement.models.Company;
import com.tourmanagementapp.tourmanagement.repository.CompanyRepository;
import com.tourmanagementapp.tourmanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(CompanyService companyService, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCompany(@RequestBody Company company) {
        try {
            Company createdCompany = companyService.addCompany(company);
            return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
        } catch (Company.CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(){
        List<Company> all = companyRepository.findAll();
        return new ResponseEntity<List<Company>>(all,HttpStatus.OK);

    }

    @PutMapping("/{branchId}/tariff")
    public ResponseEntity<String> updateTariffDetails(@PathVariable String branchId,
                                                      @RequestBody TrafficUpdateRequest trafficUpdateRequest) {
        try {
            companyService.updateTrafficDetails(branchId,trafficUpdateRequest);
            return ResponseEntity.ok("Tariff details updated successfully.");
        } catch (Company.CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
