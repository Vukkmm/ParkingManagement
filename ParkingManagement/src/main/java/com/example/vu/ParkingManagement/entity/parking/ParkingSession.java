package com.example.vu.ParkingManagement.entity.parking;

import com.example.vu.ParkingManagement.entity.base.BaseEntityWithUpdater;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "parking_session")
public class ParkingSession extends BaseEntityWithUpdater {
    @Column(name = "time_in")
    private Timestamp timeIn;
    @Column(name = "time_out")
    private Timestamp timeOut;
    @Column(name = "gate_in")
    private String gateIn;
    @Column(name = "gate_out")
    private String gateOut;
    @Column(name = "status")
    private String status;
    @Column(name = "card_id")
    private String cardId;
    @Column(name = "motorbike_id")
    private String motorbikeId;
    @Column(name = "employee_id")
    private String employeeId;
}
