package com.tourmanagementapp.tourmanagement.controller;

import com.tourmanagementapp.tourmanagement.models.Company;
import com.tourmanagementapp.tourmanagement.models.TrafficDetails;
import com.tourmanagementapp.tourmanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        Company createdCompany = companyService.addCompany(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
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
