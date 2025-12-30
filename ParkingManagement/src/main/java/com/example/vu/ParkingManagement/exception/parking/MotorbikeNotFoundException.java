package com.example.vu.ParkingManagement.exception.parking;

import com.example.vu.ParkingManagement.exception.base.NotFoundException;

import static com.example.vu.ParkingManagement.constant.ExceptionCode.MOTORBIKE_NOT_FOUND_EXCEPTION;

public class MotorbikeNotFoundException extends NotFoundException {
    public MotorbikeNotFoundException() {setCode(MOTORBIKE_NOT_FOUND_EXCEPTION);}
}
