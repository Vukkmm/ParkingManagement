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
@AllArgsConstructor
@Entity
@Table(name = "motorbike")
public class Motorbike extends BaseEntityWithUpdater {
    @Column(name = "license_plate")
    private String licensePlate;
    @Column(name = "color")
    private String color;
    @Column(name = "employee_id")
    private String employeeId;
    @Column(name = "card_id")
    private String cardId;
}
