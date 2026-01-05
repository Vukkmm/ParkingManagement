package com.example.vu.ParkingManagement.controller.parking;

import com.example.vu.ParkingManagement.dto.base.ResponseGeneral;
import com.example.vu.ParkingManagement.dto.request.EmployeeRequest;
import com.example.vu.ParkingManagement.dto.request.MotorbikeRequest;
import com.example.vu.ParkingManagement.dto.response.EmployeeResponse;
import com.example.vu.ParkingManagement.dto.response.MotorbikeResponse;
import com.example.vu.ParkingManagement.service.message.MessageService;
import com.example.vu.ParkingManagement.service.parking.MotorbikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.vu.ParkingManagement.constant.CommonConstants.DEFAULT_LANGUAGE;
import static com.example.vu.ParkingManagement.constant.CommonConstants.LANGUAGE;
import static com.example.vu.ParkingManagement.constant.MessageCodeConstant.CREATE_MOTORBIKE;
import static com.example.vu.ParkingManagement.constant.MessageCodeConstant.UPDATE_MOTORBIKE;

@RestController
@RequestMapping("api/v1/motorbikes")
@RequiredArgsConstructor
public class MotorbikeController {
    private final MotorbikeService motorbikeService;
    private final MessageService messageService;

    @PostMapping
    public ResponseGeneral<MotorbikeResponse> create(
            @RequestBody MotorbikeRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ResponseGeneral.ofCreated(
                messageService.getMessage(CREATE_MOTORBIKE, language),
                motorbikeService.create(request)
        );
    }

    @PutMapping("{id}")
    public ResponseGeneral<MotorbikeResponse> update(
            @PathVariable String id,
            @RequestBody MotorbikeRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ResponseGeneral.ofSuccess(
                messageService.getMessage(UPDATE_MOTORBIKE, language),
                motorbikeService.update(id, request)
        );
    }
}
