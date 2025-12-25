package com.example.vu.ParkingManagement.repository.parking;

import com.example.vu.ParkingManagement.entity.parking.Motorbike;
import com.example.vu.ParkingManagement.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MotorbikeRepository extends BaseRepository<Motorbike> {
    @Query("""
        SELECT CASE WHEN COUNT(r) > 0 
        THEN true ELSE false END FROM Motorbike r
        WHERE r.licensePlate = :licensePlate
        """)
    boolean checkLicensePlateExist(String licensePlate);
}
