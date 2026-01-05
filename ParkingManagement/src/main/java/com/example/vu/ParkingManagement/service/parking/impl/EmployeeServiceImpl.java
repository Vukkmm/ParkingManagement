package com.example.vu.ParkingManagement.service.parking.impl;

import com.example.vu.ParkingManagement.dto.request.EmployeeRequest;
import com.example.vu.ParkingManagement.dto.response.EmployeeResponse;
import com.example.vu.ParkingManagement.entity.parking.Employee;
import com.example.vu.ParkingManagement.exception.parking.EmployeeAlreadyExistException;
import com.example.vu.ParkingManagement.exception.parking.EmployeeNotFoundException;
import com.example.vu.ParkingManagement.repository.parking.EmployeeRepository;
import com.example.vu.ParkingManagement.service.parking.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public EmployeeResponse update(String id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        employee.setFullName(request.getFullName());
        employeeRepository.save(employee);
        return new EmployeeResponse(
                employee.getId(),
                employee.getEmployeeCode(),
                employee.getFullName()
        );
    }

}
