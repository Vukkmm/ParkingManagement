package com.example.vu.ParkingManagement.entity.parking;

import com.example.vu.ParkingManagement.entity.base.BaseEntityWithUpdater;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "motorbike")
public class Motorbike extends BaseEntityWithUpdater {
    @Column(name = "license_plate")
    private String licensePlate;
    @Column(name = "employee_id")
    private String employeeId;
}
