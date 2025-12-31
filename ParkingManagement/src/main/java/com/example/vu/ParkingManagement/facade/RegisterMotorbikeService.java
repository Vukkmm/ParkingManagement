package com.example.vu.ParkingManagement.facade;

import com.example.vu.ParkingManagement.dto.base.PageResponse;
import com.example.vu.ParkingManagement.dto.request.RegisterMotorbikeRequest;
import com.example.vu.ParkingManagement.dto.request.RegisterMotorbikeSearchRequest;
import com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeResponse;
import com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeSearchResponse;

public interface RegisterMotorbikeService {
    RegisterMotorbikeResponse create(RegisterMotorbikeRequest request);

    PageResponse<RegisterMotorbikeSearchResponse> getAllAndSearch(RegisterMotorbikeSearchRequest request , int size, int page, boolean isAll);

    RegisterMotorbikeResponse update(String employeeId, String motorId, RegisterMotorbikeRequest request);

}
