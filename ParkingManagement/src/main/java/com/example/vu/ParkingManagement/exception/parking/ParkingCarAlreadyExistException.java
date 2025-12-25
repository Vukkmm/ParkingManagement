package com.example.vu.ParkingManagement.exception.parking;

import com.example.vu.ParkingManagement.exception.base.BadRequestException;

import static com.example.vu.ParkingManagement.constant.ExceptionCode.EMPLOYEE_ALREADY_EXIST_EXCEPTION;
import static com.example.vu.ParkingManagement.constant.ExceptionCode.PARKING_CARD_ALREADY_EXIST_EXCEPTION;

public class ParkingCarAlreadyExistException extends BadRequestException {
    public ParkingCarAlreadyExistException() {
        setCode(PARKING_CARD_ALREADY_EXIST_EXCEPTION);
    }

}
