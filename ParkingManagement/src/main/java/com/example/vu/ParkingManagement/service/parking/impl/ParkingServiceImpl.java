package com.example.vu.ParkingManagement.service.parking.impl;

import com.example.vu.ParkingManagement.constant.EnumStatus;
import com.example.vu.ParkingManagement.dto.request.ParkingCardRequest;
import com.example.vu.ParkingManagement.dto.response.ParkingCarResponse;
import com.example.vu.ParkingManagement.entity.parking.ParkingCard;
import com.example.vu.ParkingManagement.exception.parking.ParkingCarAlreadyExistException;
import com.example.vu.ParkingManagement.exception.parking.ParkingCarNotFoundException;
import com.example.vu.ParkingManagement.exception.parking.ParkingCardBadRequestStatusException;
import com.example.vu.ParkingManagement.repository.parking.ParkingCardRepository;
import com.example.vu.ParkingManagement.service.parking.ParkingCardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingCardService {
    private final ParkingCardRepository repository;

    @Transactional
    @Override
    public ParkingCarResponse create(ParkingCardRequest request) {
        this.checkExistParkingCardCode(request.getCardCode());
        ParkingCard parkingCard = new ParkingCard(
                request.getCardCode(),
                EnumStatus.AVAILABLE.getStatus()
        );
        repository.save(parkingCard);
        return new ParkingCarResponse(
                parkingCard.getId(),
                parkingCard.getCardCode(),
                parkingCard.getStatus()
        );
    }

    @Override
    public ParkingCarResponse update(String id, ParkingCardRequest request) {

        return null;
    }

    @Transactional
    @Override
    public void delete(String id) {
        ParkingCard parkingCard = repository.findById(id).orElseThrow(ParkingCarNotFoundException::new);
        if (parkingCard.getStatus().equals(EnumStatus.USED.getStatus()) ){
            throw  new ParkingCardBadRequestStatusException();
        }
        repository.deleteById(id);
    }

    private void checkExistParkingCardCode(String code) {
        if(repository.checkParkingCardExist(code)){
            throw new ParkingCarAlreadyExistException();
        }
    }
}
