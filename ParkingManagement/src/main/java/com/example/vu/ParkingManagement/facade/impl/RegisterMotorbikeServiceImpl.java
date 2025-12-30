package com.example.vu.ParkingManagement.facade.impl;

import com.example.vu.ParkingManagement.constant.EnumStatus;
import com.example.vu.ParkingManagement.dto.base.PageResponse;
import com.example.vu.ParkingManagement.dto.request.MotorbikeRequest;
import com.example.vu.ParkingManagement.dto.request.RegisterMotorbikeRequest;
import com.example.vu.ParkingManagement.dto.response.MotorbikeResponse;
import com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeResponse;
import com.example.vu.ParkingManagement.entity.parking.Employee;
import com.example.vu.ParkingManagement.entity.parking.Motorbike;
import com.example.vu.ParkingManagement.entity.parking.ParkingCard;
import com.example.vu.ParkingManagement.exception.parking.*;
import com.example.vu.ParkingManagement.facade.RegisterMotorbikeService;
import com.example.vu.ParkingManagement.repository.parking.EmployeeRepository;
import com.example.vu.ParkingManagement.repository.parking.MotorbikeRepository;
import com.example.vu.ParkingManagement.repository.parking.ParkingCardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterMotorbikeServiceImpl implements RegisterMotorbikeService {
    private final EmployeeRepository employeeRepository;
    private final MotorbikeRepository motorbikeRepository;
    private final ParkingCardRepository parkingCardRepository;

    @Transactional
    @Override
    public RegisterMotorbikeResponse create(RegisterMotorbikeRequest request) {
        List<MotorbikeResponse> listMotorbike = new ArrayList<>();

        for (MotorbikeRequest r : request.getMotorbikeList()) {
            this.checkLicensePlateExist(r.getLicensePlate());
        }
        this.checkCardIdExist(request.getCardId());
        Employee employee = employeeRepository.findByEmployeeCode(request.getEmployeeCode());
        if (employee == null) {
            employee.setEmployeeCode(request.getEmployeeCode());
            employee.setFullName(request.getFullName());
        }
        employeeRepository.save(employee);

        for (MotorbikeRequest r : request.getMotorbikeList()) {
            Motorbike motorbike = new Motorbike(
                    r.getLicensePlate(),
                    r.getColor(),
                    employee.getId(),
                    r.getCardId()
            );
            MotorbikeResponse response = new MotorbikeResponse(
                    motorbike.getId(),
                    motorbike.getLicensePlate(),
                    motorbike.getColor(),
                    motorbike.getCardId()
            );
            listMotorbike.add(response);
            motorbikeRepository.save(motorbike);
        }
        return new RegisterMotorbikeResponse(
                employee.getId(),
                employee.getEmployeeCode(),
                employee.getFullName(),
                listMotorbike
                );
    }

    @Override
    public PageResponse<RegisterMotorbikeResponse> getAllAndSearch(RegisterMotorbikeRequest request, int size, int page, boolean isAll) {
        Page<RegisterMotorbikeResponse> responses = isAll ? motorbikeRepository.findAllRegisterMotorbike(PageRequest.of(page, size)) :
                motorbikeRepository.search(PageRequest.of(page, size), request);
        return PageResponse.of(responses.getContent(), responses.getNumberOfElements());
    }

    @Transactional
    @Override
    public RegisterMotorbikeResponse update(String employeeId, String motorId, RegisterMotorbikeRequest request) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
        Motorbike motorbike = motorbikeRepository.findById(motorId).orElseThrow(MotorbikeNotFoundException::new);

        return null;
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

//    private Employee findEmployeeById(String id) {
//        return employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
//    }

}
