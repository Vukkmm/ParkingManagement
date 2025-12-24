package com.example.vu.ParkingManagement.exception.parking;

import com.example.vu.ParkingManagement.exception.base.BadRequestException;

import static com.example.vu.ParkingManagement.constant.ExceptionCode.EMPLOYEE_ALREADY_EXIST_EXCEPTION;

public class EmployeeAlreadyExistException extends BadRequestException {
    public EmployeeAlreadyExistException() {
        setCode(EMPLOYEE_ALREADY_EXIST_EXCEPTION);
    }
}
