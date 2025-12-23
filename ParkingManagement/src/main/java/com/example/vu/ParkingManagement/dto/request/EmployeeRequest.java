package com.example.vu.ParkingManagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class EmployeeRequest {
    private String employeeCode;
    private String fullName;
    private Boolean isActive;
    private String createBy;
    private String lastUpdateBy;
}
