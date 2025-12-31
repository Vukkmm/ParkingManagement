package com.example.vu.ParkingManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMotorbikeSearchResponse {
    private String motorbikeId;
    private String licensePlate;
    private String color;
    private String cardId;
    private String employeeId;
    private String employeeCode;
    private String fullName;
}
