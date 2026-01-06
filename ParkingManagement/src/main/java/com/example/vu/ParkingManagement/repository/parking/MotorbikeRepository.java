package com.example.vu.ParkingManagement.repository.parking;

import com.example.vu.ParkingManagement.dto.request.RegisterMotorbikeRequest;
import com.example.vu.ParkingManagement.dto.request.RegisterMotorbikeSearchRequest;
import com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeResponse;
import com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeSearchResponse;
import com.example.vu.ParkingManagement.entity.parking.Motorbike;
import com.example.vu.ParkingManagement.repository.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorbikeRepository extends BaseRepository<Motorbike> {

    @Query("""
        SELECT CASE WHEN COUNT(r) > 0 
        THEN true ELSE false END FROM Motorbike r
        WHERE r.licensePlate = :licensePlate
                AND (:id IS NULL OR r.id <> :id)
        """)
    boolean checkLicensePlateExist(String licensePlate, String id);


    @Query("""
        SELECT new com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeSearchResponse(
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
    Page<RegisterMotorbikeSearchResponse> findAllRegisterMotorbike(Pageable pageable);

    @Query("""
    SELECT new com.example.vu.ParkingManagement.dto.response.RegisterMotorbikeSearchResponse(
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
    Page<RegisterMotorbikeSearchResponse> search(
            Pageable pageable,
            RegisterMotorbikeSearchRequest req
    );



    @Modifying
    @Transactional
    @Query("""
    DELETE FROM Motorbike m
    WHERE m.employeeId = :employeeId
""")
    void deleteByEmployeeId( String employeeId);

    @Query("""
    SELECT m.cardId
    FROM Motorbike m
    WHERE m.employeeId = :employeeId
""")
    List<String> findCardIdsByEmployeeId(String employeeId);


}
