package com.example.vu.ParkingManagement.repository.parking;

import com.example.vu.ParkingManagement.entity.parking.ParkingCard;
import com.example.vu.ParkingManagement.repository.BaseRepository;
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
}
