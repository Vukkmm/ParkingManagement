package com.example.vu.ParkingManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotorbikeResponse {
    private String id;
    private String licensePlate;
    private String color;
    private String cardId;
}
