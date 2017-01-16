package com.mmashyr.entity.enums;

/**
 * Created by Anton on 12.01.2017.
 */
public enum OrderStatus {

    PACKAGED("PACKAGED"),
    DELIVERY("DELIVERY"),
    CLOSED("CLOSED"),
    CANCELED("CANCELED");

    private final String status;

    private OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}