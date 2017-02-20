package com.mmashyr.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Anton on 12.01.2017.
 */

@Embeddable
public class Address extends BasicEntity {

    @Column(name = "city")
    @NotNull
    @Size(min = 3, max = 20, message = "City must be between {min} and {max}")
    private String city;

    @Column(name = "street")
    @NotNull
    @Size(min = 3, max = 30, message = "Street must be between {min} and {max}")
    private String street;

    @Column(name = "house_number")
    @NotNull
    @Digits(integer = 3, fraction = 0)
    private int houseNumber;

    @Column(name = "apartment_number")
    @NotNull
    @Digits(integer = 3, fraction = 0)
    private int apartmentNumber;

    @Column(name = "phone_number")
    @NotNull
    private String phoneNumber;

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
