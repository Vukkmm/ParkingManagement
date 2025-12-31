package com.example.vu.ParkingManagement.controller.parking;

import com.example.vu.ParkingManagement.dto.base.PageResponse;
import com.example.vu.ParkingManagement.dto.base.ResponseGeneral;
import com.example.vu.ParkingManagement.dto.request.ParkingCardRequest;
import com.example.vu.ParkingManagement.dto.request.RegisterMotorbikeRequest;
import com.example.vu.ParkingManagement.dto.request.RegisterMotorbikeSearchRequest;
import com.example.vu.ParkingManagement.dto.response.ParkingCarResponse;
import com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeResponse;
import com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeSearchResponse;
import com.example.vu.ParkingManagement.facade.RegisterMotorbikeService;
import com.example.vu.ParkingManagement.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.vu.ParkingManagement.constant.CommonConstants.*;
import static com.example.vu.ParkingManagement.constant.MessageCodeConstant.*;

@RestController
@RequestMapping("api/v1/register-motorbikes")
@RequiredArgsConstructor
public class RegisterMotorbikeController {
    private final RegisterMotorbikeService registerMotorbikeService;
    private final MessageService messageService;

    @PostMapping
    public ResponseGeneral<RegisterMotorbikeResponse> create(
            @RequestBody RegisterMotorbikeRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ResponseGeneral.ofCreated(
                messageService.getMessage(CREATE_EMPLOYEE_AND_MOTORBIKE, language),
                registerMotorbikeService.create(request)
        );
    }

    @PostMapping("/getAllAndSearch")
    public  ResponseGeneral<PageResponse<RegisterMotorbikeSearchResponse>> getAllAndSearch(
            @RequestBody RegisterMotorbikeSearchRequest request,
            @RequestParam(name = SIZE, defaultValue = "10") int size,
            @RequestParam(name = PAGE, defaultValue = "0") int page,
            @RequestParam(name = ALL, defaultValue = "false", required = false) boolean isAll,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ResponseGeneral.ofSuccess(messageService.getMessage(GET_ALL_AND_SEARCH_EMPLOYEE_AND_MOTORBIKE, language),
                registerMotorbikeService.getAllAndSearch(request, size, page, isAll)
        );
    }



}
