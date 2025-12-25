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
@Table(name = "parking_card")
public class ParkingCard extends BaseEntityWithUpdater {
    @Column(name = "card_code")
    private String cardCode;
    @Column(name = "status")
    private String status;
}
