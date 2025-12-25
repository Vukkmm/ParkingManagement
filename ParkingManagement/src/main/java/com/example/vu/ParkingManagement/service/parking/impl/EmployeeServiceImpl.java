package com.example.vu.ParkingManagement.service.parking.impl;

import com.example.vu.ParkingManagement.constant.ExceptionCode;
import com.example.vu.ParkingManagement.dto.request.EmployeeRequest;
import com.example.vu.ParkingManagement.dto.response.EmployeeResponse;
import com.example.vu.ParkingManagement.entity.parking.Employee;
import com.example.vu.ParkingManagement.exception.parking.EmployeeAlreadyExistException;
import com.example.vu.ParkingManagement.repository.parking.EmployeeRepository;
import com.example.vu.ParkingManagement.service.parking.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse create(EmployeeRequest request) {
        this.checkExistEmployeeCode(request.getEmployeeCode());
        Employee employee = new Employee(
                request.getEmployeeCode(),
                request.getFullName()
        );
        employeeRepository.save(employee);
        return new EmployeeResponse(
                employee.getId(),
                employee.getEmployeeCode(),
                employee.getFullName()
        );
    }

    private void checkExistEmployeeCode(String code) {
        if(employeeRepository.checkEmployeeCodeExist(code)){
            throw new EmployeeAlreadyExistException();
        }

    }
}
