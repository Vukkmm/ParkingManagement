package com.example.vu.ParkingManagement.facade;

import com.example.vu.ParkingManagement.dto.base.PageResponse;
import com.example.vu.ParkingManagement.dto.request.RegisterMotorbikeRequest;
import com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeResponse;

public interface RegisterMotorbikeService {
    RegisterMotorbikeResponse create(RegisterMotorbikeRequest request);

    PageResponse<RegisterMotorbikeResponse> getAllAndSearch(RegisterMotorbikeRequest request , int size, int page, boolean isAll);

    RegisterMotorbikeResponse update(String employeeId, String motorId, RegisterMotorbikeRequest request);

}
