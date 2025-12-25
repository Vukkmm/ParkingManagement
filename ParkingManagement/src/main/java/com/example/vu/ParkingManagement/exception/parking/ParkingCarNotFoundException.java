package com.example.vu.ParkingManagement.exception.parking;

import com.example.vu.ParkingManagement.exception.base.NotFoundException;

import static com.example.vu.ParkingManagement.constant.ExceptionCode.PARKING_CARD_NOT_FOUND_EXCEPTION;

public class ParkingCarNotFoundException extends NotFoundException {
    public ParkingCarNotFoundException() {
        setCode(PARKING_CARD_NOT_FOUND_EXCEPTION);
    }

}
