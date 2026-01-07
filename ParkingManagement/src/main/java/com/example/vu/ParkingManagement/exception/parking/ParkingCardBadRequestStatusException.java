package com.example.vu.ParkingManagement.exception.parking;

import com.example.vu.ParkingManagement.exception.base.BadRequestException;

import static com.example.vu.ParkingManagement.constant.ExceptionCode.PARKING_CARD_BAD_REQUEST_STATUS_EXCEPTION;

public class ParkingCardBadRequestStatusException extends BadRequestException {
    public ParkingCardBadRequestStatusException() {
        setCode(PARKING_CARD_BAD_REQUEST_STATUS_EXCEPTION);
    }

}
