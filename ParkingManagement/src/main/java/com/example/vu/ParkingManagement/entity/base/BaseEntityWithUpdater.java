package com.example.vu.ParkingManagement.entity.base;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@MappedSuperclass
public class BaseEntityWithUpdater extends BaseEntity {
    @LastModifiedBy
    private String lastUpdatedBy;

    @LastModifiedDate
    private Long lastUpdatedAt;

}
