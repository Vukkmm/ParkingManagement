package com.example.vu.ParkingManagement.repository.parking;

import com.example.vu.ParkingManagement.dto.base.PageResponse;
import com.example.vu.ParkingManagement.dto.request.ParkingCardRequest;
import com.example.vu.ParkingManagement.dto.response.ParkingCarResponse;
import com.example.vu.ParkingManagement.entity.parking.ParkingCard;
import com.example.vu.ParkingManagement.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingCardRepository extends BaseRepository<ParkingCard> {
    @Query("""
        SELECT CASE WHEN COUNT(r) > 0 
        THEN true ELSE false END FROM ParkingCard r
        WHERE r.cardCode = :cardCode
        """)
    boolean checkParkingCardExist(String cardCode);


    @Query("""
    SELECT new com.example.vu.ParkingManagement.dto.response.ParkingCarResponse(
        p.id,
        p.cardCode,
        p.status
        )
        FROM  ParkingCard p
        WHERE 
            (:#{#req.cardCode} IS NULL OR LOWER(p.cardCode) LIKE LOWER(CONCAT('%', :#{#req.cardCode}, '%')))
    AND (:#{#req.status} IS NULL OR LOWER(p.status) LIKE LOWER(CONCAT('%', :#{#req.status}, '%')))
    """)
    Page<ParkingCarResponse> search(
            Pageable pageable,
            ParkingCardRequest req
    );
}
