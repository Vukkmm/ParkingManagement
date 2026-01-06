package com.example.vu.ParkingManagement.controller.parking;

import com.example.vu.ParkingManagement.dto.base.ResponseGeneral;
import com.example.vu.ParkingManagement.dto.request.EmployeeRequest;
import com.example.vu.ParkingManagement.dto.response.EmployeeResponse;
import com.example.vu.ParkingManagement.service.message.MessageService;
import com.example.vu.ParkingManagement.service.parking.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.vu.ParkingManagement.constant.CommonConstants.DEFAULT_LANGUAGE;
import static com.example.vu.ParkingManagement.constant.CommonConstants.LANGUAGE;
import static com.example.vu.ParkingManagement.constant.MessageCodeConstant.UPDATE_EMPLOYEE;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final MessageService messageService;

    @PutMapping("{id}")
    public ResponseGeneral<EmployeeResponse> update(
            @PathVariable String id,
            @RequestBody EmployeeRequest request,
            @RequestHeader(name = LANGUAGE, defaultValue = DEFAULT_LANGUAGE) String language
    ) {
        return ResponseGeneral.ofSuccess(
                messageService.getMessage(UPDATE_EMPLOYEE, language),
                employeeService.update(id, request)
        );
    }
}
