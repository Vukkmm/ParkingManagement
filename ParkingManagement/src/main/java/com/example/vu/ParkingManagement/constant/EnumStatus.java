package com.example.vu.ParkingManagement.constant;

import lombok.Getter;

@Getter
public enum EnumStatus {
    PARKING("PARKING"),
    COMPLETE("COMPLETE"),
    AVAILABLE("AVAILABLE"),
    LOST("LOST"),
    USED("USED");

    private final String status;
    EnumStatus(String status) {
        this.status = status;
    }
    }
