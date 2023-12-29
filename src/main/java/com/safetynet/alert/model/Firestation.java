package com.safetynet.alert.model;

import lombok.Data;

@Data
public class Firestation {

    private String address;
    private String station;

    public Firestation(String address, String station) {
        this.address = address;
        this.station = station;
    }
}