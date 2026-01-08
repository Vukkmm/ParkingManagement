package com.example.vu.ParkingManagement.controller.parking;

import com.example.vu.ParkingManagement.dto.base.PageResponse;
import com.example.vu.ParkingManagement.dto.base.ResponseGeneral;
import com.example.vu.ParkingManagement.dto.request.ParkingCardRequest;
import com.example.vu.ParkingManagement.dto.request.RegisterMotorbikeSearchRequest;
import com.example.vu.ParkingManagement.dto.response.ParkingCarResponse;
import com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeSearchResponse;
import com.example.vu.ParkingManagement.service.message.MessageService;
import com.example.vu.ParkingManagement.service.parking.ParkingCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import static com.example.vu.ParkingManagement.constant.CommonConstants.*;
import static com.example.vu.ParkingManagement.constant.CommonConstants.ALL;
import static com.example.vu.ParkingManagement.constant.CommonConstants.PAGE;
import static com.example.vu.ParkingManagement.constant.MessageCodeConstant.*;

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

    @DeleteMapping("{id}")
    public ResponseGeneral<Void> delete(
            @PathVariable String id,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        parkingCardService.delete(id);
        return ResponseGeneral.ofSuccess(messageService.getMessage(DELETE_PARKING_CARD, language));
    }

    @PostMapping("/getAllAndSearch")
    public  ResponseGeneral<PageResponse<ParkingCarResponse>> getAllAndSearch(
            @RequestBody ParkingCardRequest request,
            @RequestParam(name = SIZE, defaultValue = "10") int size,
            @RequestParam(name = PAGE, defaultValue = "0") int page,
            @RequestParam(name = ALL, defaultValue = "false", required = false) boolean isAll,
            @RequestParam(name = SORT, defaultValue = "DESC", required = false) Sort sort,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ResponseGeneral.ofSuccess(messageService.getMessage(GET_ALL_AND_SEARCH_PARKING_CARD, language),
                parkingCardService.getAllAndSearch(request, size, page, isAll, sort)
        );
    }
}
