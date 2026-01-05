package com.example.vu.ParkingManagement.service.parking;

import com.example.vu.ParkingManagement.dto.request.MotorbikeRequest;
import com.example.vu.ParkingManagement.dto.response.MotorbikeResponse;

public interface MotorbikeService {
    MotorbikeResponse create(MotorbikeRequest request);

    MotorbikeResponse update(String id, MotorbikeRequest request);
}
