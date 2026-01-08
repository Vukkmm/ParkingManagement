package com.example.vu.ParkingManagement.service.parking;

import com.example.vu.ParkingManagement.dto.base.PageResponse;
import com.example.vu.ParkingManagement.dto.request.ParkingCardRequest;
import com.example.vu.ParkingManagement.dto.response.ParkingCarResponse;
import org.springframework.data.domain.Sort;

public interface ParkingCardService
{
    ParkingCarResponse create(ParkingCardRequest request);

    ParkingCarResponse update(String id, ParkingCardRequest request);

    void delete(String id);

    PageResponse<ParkingCarResponse> getAllAndSearch(ParkingCardRequest request, int size, int page, boolean isAll, Sort sort);
}
