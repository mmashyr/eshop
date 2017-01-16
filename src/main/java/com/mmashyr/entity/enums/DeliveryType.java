package com.mmashyr.entity.enums;

/**
 * Created by Anton on 12.01.2017.
 */
public enum DeliveryType {
    PICKUP("PICKUP"),
    POSTAL("POSTAL"),
    COURIER("COURIER");

    private final String type;

    private DeliveryType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}