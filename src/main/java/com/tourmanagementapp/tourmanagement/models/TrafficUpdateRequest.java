package com.tourmanagementapp.tourmanagement.models;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrafficUpdateRequest {
    private String place;
    private double tariff;
}