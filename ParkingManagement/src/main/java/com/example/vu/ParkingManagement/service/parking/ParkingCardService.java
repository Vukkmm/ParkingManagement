package com.example.vu.ParkingManagement.service.parking;

import com.example.vu.ParkingManagement.dto.request.ParkingCardRequest;
import com.example.vu.ParkingManagement.dto.response.ParkingCarResponse;

public interface ParkingCardService
{
    ParkingCarResponse create(ParkingCardRequest request);

    ParkingCarResponse update(String id, ParkingCardRequest request);

    void delete(String id);
}
