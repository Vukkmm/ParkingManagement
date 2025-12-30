package com.example.vu.ParkingManagement.exception.parking;

import com.example.vu.ParkingManagement.exception.base.NotFoundException;

import static com.example.vu.ParkingManagement.constant.ExceptionCode.EMPLOYEE_NOT_FOUND_EXCEPTION;

public class EmployeeNotFoundException extends NotFoundException {
    public EmployeeNotFoundException() {
        setCode(EMPLOYEE_NOT_FOUND_EXCEPTION);
    }

}
