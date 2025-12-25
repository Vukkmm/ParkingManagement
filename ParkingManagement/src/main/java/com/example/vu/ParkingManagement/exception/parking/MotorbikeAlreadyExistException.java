package com.example.vu.ParkingManagement.exception.parking;

import com.example.vu.ParkingManagement.exception.base.BadRequestException;

import static com.example.vu.ParkingManagement.constant.ExceptionCode.MOTORBIKE_CARD_ALREADY_EXIST_EXCEPTION;
import static com.example.vu.ParkingManagement.constant.ExceptionCode.PARKING_CARD_ALREADY_EXIST_EXCEPTION;

public class MotorbikeAlreadyExistException extends BadRequestException {
    public MotorbikeAlreadyExistException() {
        setCode(MOTORBIKE_CARD_ALREADY_EXIST_EXCEPTION);
    }
}
