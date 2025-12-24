package com.example.vu.ParkingManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class EmployeeResponse {
    private String id;
    private String employeeCode;
    private String fullName;
    private Boolean isActive;
    private String createBy;
    private Long createAt;
    private String lastUpdateBy;
    private Long lastUpdateAt;

    public EmployeeResponse(String id, String employeeCode, String fullName, Boolean isActive, String createBy, Long createAt) {
        this.id = id;
        this.employeeCode = employeeCode;
        this.fullName = fullName;
        this.isActive = isActive;
        this.createBy = createBy;
        this.createAt = createAt;
    }
}
