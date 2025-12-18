package com.example.vu.ParkingManagement.exception.base;

import static com.example.vu.ParkingManagement.constant.ExceptionCode.NOT_FOUND_CODE;
import static com.example.vu.ParkingManagement.constant.ExceptionCode.NOT_FOUND_CODE;

public class NotFoundException extends BaseException{
    public NotFoundException() {
        setCode(NOT_FOUND_CODE);
        setStatus(StatusConstants.NOT_FOUND);
    }

}
