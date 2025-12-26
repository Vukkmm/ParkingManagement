package com.example.vu.ParkingManagement.repository.parking;

import com.example.vu.ParkingManagement.dto.request.RegisterMotorbikeRequest;
import com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeResponse;
import com.example.vu.ParkingManagement.entity.parking.Motorbike;
import com.example.vu.ParkingManagement.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    @Query("""
        SELECT new com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeResponse(
            m.id,
            m.licensePlate,
            m.color,
            m.cardId,
            e.id,
            e.employeeCode,
            e.fullName
        )
        FROM Motorbike m, Employee e
        WHERE m.employeeId = e.id
    """)
    Page<RegisterMotorbikeResponse> findAllRegisterMotorbike(Pageable pageable);

    @Query("""
    SELECT new com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeResponse(
        m.id,
        m.licensePlate,
        m.color,
        m.cardId,
        e.id,
        e.employeeCode,
        e.fullName
    )
    FROM Motorbike m
    JOIN Employee e ON m.employeeId = e.id
    WHERE
        (:#{#req.employeeCode} IS NULL OR LOWER(e.employeeCode) LIKE LOWER(CONCAT('%', :#{#req.employeeCode}, '%')))
    AND (:#{#req.fullName} IS NULL OR LOWER(e.fullName) LIKE LOWER(CONCAT('%', :#{#req.fullName}, '%')))
    AND (:#{#req.licensePlate} IS NULL OR LOWER(m.licensePlate) LIKE LOWER(CONCAT('%', :#{#req.licensePlate}, '%')))
    AND (:#{#req.color} IS NULL OR LOWER(m.color) LIKE LOWER(CONCAT('%', :#{#req.color}, '%')))
    AND (:#{#req.cardId} IS NULL OR LOWER(m.cardId) LIKE LOWER(CONCAT('%', :#{#req.cardId}, '%')))
    """)
    Page<RegisterMotorbikeResponse> search(
            Pageable pageable,
            RegisterMotorbikeRequest req
    );

}
