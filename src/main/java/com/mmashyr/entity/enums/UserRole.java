package com.mmashyr.entity.enums;

/**
 * Created by Anton on 20.01.2017.
 */
public enum UserRole {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_COURIER("ROLE_COURIER"),
    ROLE_CUSTOMER("ROLE_CUSTOMER");

    private final String role;

    private UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return role;
    }
}