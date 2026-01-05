package com.example.vu.ParkingManagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotorbikeRequest {
    private String licensePlate;
    private String color;
    private String cardId;
    private String employeeId;

    public MotorbikeRequest(String licensePlate, String color, String cardId) {
        this.licensePlate = licensePlate;
        this.color = color;
        this.cardId = cardId;
    }
}
