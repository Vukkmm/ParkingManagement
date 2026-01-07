package com.example.vu.ParkingManagement.service.parking.impl;

import com.example.vu.ParkingManagement.constant.EnumStatus;
import com.example.vu.ParkingManagement.dto.request.MotorbikeRequest;
import com.example.vu.ParkingManagement.dto.response.MotorbikeResponse;
import com.example.vu.ParkingManagement.entity.parking.Motorbike;
import com.example.vu.ParkingManagement.entity.parking.ParkingCard;
import com.example.vu.ParkingManagement.exception.parking.*;
import com.example.vu.ParkingManagement.repository.parking.EmployeeRepository;
import com.example.vu.ParkingManagement.repository.parking.MotorbikeRepository;
import com.example.vu.ParkingManagement.repository.parking.ParkingCardRepository;
import com.example.vu.ParkingManagement.service.parking.MotorbikeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MotorbikeServiceImpl implements MotorbikeService {
    private final EmployeeRepository employeeRepository;
    private final MotorbikeRepository motorbikeRepository;
    private final ParkingCardRepository parkingCardRepository;

    @Transactional
    @Override
    public MotorbikeResponse create(MotorbikeRequest request) {
        checkEmployeeId(request.getEmployeeId());
        checkLicensePlateExist(request.getLicensePlate(), null);
        checkCardIdExist(request.getCardId());
        Motorbike motorbike = new Motorbike(
               request.getLicensePlate(),
               request.getColor(),
               request.getEmployeeId(),
               request.getCardId()
        );
        motorbikeRepository.save(motorbike);
        return convertResponse(motorbike);
    }

    @Transactional
    @Override
    public MotorbikeResponse update(String id, MotorbikeRequest request) {
        checkEmployeeId(request.getEmployeeId());
        checkLicensePlateExist(request.getLicensePlate(), request.getEmployeeId());
        Motorbike motorbike = motorbikeRepository.findById(id).orElseThrow(MotorbikeNotFoundException::new);
        motorbike.setLicensePlate(request.getLicensePlate());
        motorbike.setColor(request.getColor());

        if(request.getCardId() != null && !request.getCardId().equals(motorbike.getCardId())) {
            updateStatusCardIdLost(motorbike.getCardId());
            checkCardIdExist(request.getCardId());
            motorbike.setCardId(request.getCardId());
        }
        motorbikeRepository.save(motorbike);
        return convertResponse(motorbike);
    }

    @Transactional
    @Override
    public void delete(String id) {
        Motorbike motorbike = motorbikeRepository.findById(id)
                .orElseThrow(MotorbikeNotFoundException::new);
            updateStatusCardId(motorbike.getCardId());
    }

    private void updateStatusCardId(String cardId) {
        ParkingCard parkingCard = parkingCardRepository.findById(cardId)
                .orElseThrow((ParkingCarNotFoundException::new));
        if (EnumStatus.USED.getStatus().equals(parkingCard.getStatus())) {
            parkingCard.setStatus(EnumStatus.AVAILABLE.getStatus());
        }
        parkingCardRepository.save(parkingCard);
    }

    private void updateStatusCardIdLost(String cardId) {
        ParkingCard parkingCard = parkingCardRepository.findById(cardId)
                .orElseThrow((ParkingCarNotFoundException::new));
        if (EnumStatus.USED.getStatus().equals(parkingCard.getStatus())) {
            parkingCard.setStatus(EnumStatus.LOST.getStatus());
        }
        parkingCardRepository.save(parkingCard);
    }

    private void checkLicensePlateExist(String licensePlate, String employeeId) {
        if(motorbikeRepository.checkLicensePlateExist(licensePlate, employeeId)){
            throw new MotorbikeAlreadyExistException();
        }
    }

    private void checkCardIdExist(String cardId) {
        ParkingCard parkingCard = parkingCardRepository.findById(cardId)
                .orElseThrow(ParkingCarNotFoundException::new);
        if (!EnumStatus.AVAILABLE.getStatus().equals(parkingCard.getStatus())) {
            throw new ParkingCarAlreadyExistException();
        }
        parkingCard.setStatus(EnumStatus.USED.getStatus());
        parkingCardRepository.save(parkingCard);
    }

    private void checkEmployeeId(String employeeId) {
        employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
    }

    private MotorbikeResponse convertResponse(Motorbike motorbike) {
        return new MotorbikeResponse(
                motorbike.getId(),
                motorbike.getLicensePlate(),
                motorbike.getColor(),
                motorbike.getCardId(),
                motorbike.getEmployeeId()
        );
    }

}
