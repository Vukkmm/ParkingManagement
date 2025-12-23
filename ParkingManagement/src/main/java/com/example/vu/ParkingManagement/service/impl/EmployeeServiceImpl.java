package com.example.vu.ParkingManagement.service.impl;

import com.example.vu.ParkingManagement.dto.request.EmployeeRequest;
import com.example.vu.ParkingManagement.dto.response.EmployeeResponse;
import com.example.vu.ParkingManagement.repository.parking.EmployeeRepository;
import com.example.vu.ParkingManagement.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse create(EmployeeRequest request) {

        return null;
    }
}
