package com.example.vu.ParkingManagement.service.parking;

import com.example.vu.ParkingManagement.dto.request.EmployeeRequest;
import com.example.vu.ParkingManagement.dto.response.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse update(String id, EmployeeRequest request);
}
