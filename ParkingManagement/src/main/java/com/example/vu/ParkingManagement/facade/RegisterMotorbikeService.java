package com.example.vu.ParkingManagement.facade;

import com.example.vu.ParkingManagement.dto.request.RegisterMotorbikeRequest;
import com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeResponse;

public interface RegisterMotorbikeService {
    RegisterMotorbikeResponse create(RegisterMotorbikeRequest request);
}
