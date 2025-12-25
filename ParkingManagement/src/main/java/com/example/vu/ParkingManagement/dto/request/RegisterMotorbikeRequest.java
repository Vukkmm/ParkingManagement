package com.example.vu.ParkingManagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMotorbikeRequest {
    private String employeeCode;
    private String fullName;
    private String licensePlate;
    private String color;
}
