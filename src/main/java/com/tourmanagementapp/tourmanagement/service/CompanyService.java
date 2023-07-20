package com.tourmanagementapp.tourmanagement.service;


import com.tourmanagementapp.tourmanagement.controller.TrafficUpdateRequest;
import com.tourmanagementapp.tourmanagement.models.Company;
import com.tourmanagementapp.tourmanagement.models.TrafficDetails;
import com.tourmanagementapp.tourmanagement.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company addCompany(Company company) {
        // Set unique branchId and addedDate here
        // (You can use UUID or any other mechanism to generate a unique branchId)
        company.setBranchId(generateUniqueBranchId());
        company.setAddedDate(LocalDate.now());

        // Save the company with tariff details
        List<TrafficDetails> tariffDetails = company.getTrafficDetails();
        if (tariffDetails != null) {
            for (TrafficDetails tariffDetail : tariffDetails) {
                // Validate and set the tariff details
                validateAndSetTariffDetails(tariffDetail);
                tariffDetail.setCompany(company);
            }
        }

        return companyRepository.save(company);
    }

    private String generateUniqueBranchId() {
        // Implement your logic to generate a unique branchId
        // Example: Use UUID.randomUUID().toString() to generate a unique identifier
        return UUID.randomUUID().toString();
    }

    private void validateAndSetTariffDetails(TrafficDetails tariffDetail) {
        double tariff = tariffDetail.getTariff();
        if (tariff < TrafficDetails.MIN_TARIFF || tariff > TrafficDetails.MAX_TARIFF) {
            throw new Company.CustomException("Tariff details must range between 50,000 - 100,000.");
        }
    }

    public void updateTrafficDetails(String branchId, TrafficUpdateRequest trafficUpdateRequest ){

        Company company = companyRepository.findByBranchId(branchId);
        if(company == null){
            throw new Company.CustomException("Invalid branchId");
        }

        List<TrafficDetails> trafficDetails  = company.getTrafficDetails();
        if(trafficDetails!=null){
            for(TrafficDetails trafficDetail:trafficDetails){
                if(trafficDetail.getPlace().equals(trafficUpdateRequest.getPlace())){
                    trafficDetail.setTariff(trafficUpdateRequest.getTariff());
                    company.setLastUpdateDate(LocalDate.now());
                    companyRepository.save(company);
                    return;
                }
            }
        }

    }


}
