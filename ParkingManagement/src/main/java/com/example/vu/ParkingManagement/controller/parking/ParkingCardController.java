package com.example.vu.ParkingManagement.controller.parking;

import com.example.vu.ParkingManagement.dto.base.ResponseGeneral;
import com.example.vu.ParkingManagement.dto.request.ParkingCardRequest;
import com.example.vu.ParkingManagement.dto.response.ParkingCarResponse;
import com.example.vu.ParkingManagement.service.message.MessageService;
import com.example.vu.ParkingManagement.service.parking.ParkingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.vu.ParkingManagement.constant.CommonConstants.DEFAULT_LANGUAGE;
import static com.example.vu.ParkingManagement.constant.CommonConstants.LANGUAGE;
import static com.example.vu.ParkingManagement.constant.MessageCodeConstant.CREATE_PARKING_CARD;

@RestController
@RequestMapping("api/v1/parking-cards")
@RequiredArgsConstructor
public class ParkingCardController {
    private  final ParkingCardService parkingCardService;
    private final MessageService messageService;

    @PostMapping
    public ResponseGeneral<ParkingCarResponse> create(
            @RequestBody ParkingCardRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ResponseGeneral.ofCreated(
                messageService.getMessage(CREATE_PARKING_CARD, language),
                parkingCardService.create(request)
        );
    }
}
