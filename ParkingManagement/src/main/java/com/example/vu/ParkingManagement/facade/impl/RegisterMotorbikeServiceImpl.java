package com.example.vu.ParkingManagement.facade.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.vu.ParkingManagement.constant.EnumStatus;
import com.example.vu.ParkingManagement.dto.request.RegisterMotorbikeRequest;
import com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeResponse;
import com.example.vu.ParkingManagement.entity.parking.Employee;
import com.example.vu.ParkingManagement.entity.parking.Motorbike;
import com.example.vu.ParkingManagement.entity.parking.ParkingCard;
import com.example.vu.ParkingManagement.exception.parking.EmployeeAlreadyExistException;
import com.example.vu.ParkingManagement.exception.parking.MotorbikeAlreadyExistException;
import com.example.vu.ParkingManagement.exception.parking.ParkingCarAlreadyExistException;
import com.example.vu.ParkingManagement.exception.parking.ParkingCarNotFoundException;
import com.example.vu.ParkingManagement.facade.RegisterMotorbikeService;
import com.example.vu.ParkingManagement.repository.parking.EmployeeRepository;
import com.example.vu.ParkingManagement.repository.parking.MotorbikeRepository;
import com.example.vu.ParkingManagement.repository.parking.ParkingCardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterMotorbikeServiceImpl implements RegisterMotorbikeService {
    private final EmployeeRepository employeeRepository;
    private final MotorbikeRepository motorbikeRepository;
    private final ParkingCardRepository parkingCardRepository;

    @Transactional
    @Override
    public RegisterMotorbikeResponse create(RegisterMotorbikeRequest request) {
        this.checkLicensePlateExist(request.getLicensePlate());
        this.checkCardIdExist(request.getCardId());
        Employee employee = new Employee(
                request.getEmployeeCode(),
                request.getFullName()
        );
        employeeRepository.save(employee);
        Motorbike motorbike = new Motorbike(
                request.getLicensePlate(),
                request.getColor(),
                employee.getId(),
                request.getCardId()
        );
        motorbikeRepository.save(motorbike);
        return new RegisterMotorbikeResponse(
                employee.getId(),
                employee.getEmployeeCode(),
                employee.getFullName(),
                motorbike.getId(),
                motorbike.getLicensePlate(),
                motorbike.getColor(),
                motorbike.getCardId()
                );
    }

    private void checkLicensePlateExist(String licensePlate) {
        if(motorbikeRepository.checkLicensePlateExist(licensePlate)){
            throw new MotorbikeAlreadyExistException();
        }
    }

    private void checkCardIdExist(String cardId) {
        ParkingCard parkingCard = parkingCardRepository.findById(cardId)
                .orElseThrow(() -> new ParkingCarNotFoundException());
        if (!EnumStatus.AVAILABLE.getStatus().equals(parkingCard.getStatus())) {
            throw new ParkingCarAlreadyExistException();
        }
        parkingCard.setStatus(EnumStatus.USED.getStatus());
        parkingCardRepository.save(parkingCard);
    }

}
