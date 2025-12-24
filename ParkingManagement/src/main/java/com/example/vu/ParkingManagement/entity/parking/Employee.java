package com.example.vu.ParkingManagement.entity.parking;

import com.example.vu.ParkingManagement.entity.base.BaseEntityWithUpdater;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Table(name="employee")
public class Employee extends BaseEntityWithUpdater {
    @Column(name = "employee_code")
    private String employeeCode;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "is_active")
    private Boolean isActive;

    public Employee(String createdBy, String employeeCode, String fullName, Boolean isActive) {
        super(createdBy);
        this.employeeCode = employeeCode;
        this.fullName = fullName;
        this.isActive = isActive;
    }
}



