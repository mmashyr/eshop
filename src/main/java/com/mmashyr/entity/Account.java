package com.mmashyr.entity;

import com.mmashyr.entity.enums.UserRole;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 12.01.2017.
 */
@Entity
@Table(name = "account")
public class Account extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "username", unique = true)
    @NotNull
    @Size(min = 4, max = 12, message = "Must be between {min} and {max}")
    private String username;

    @Column(name = "password")
    private String password;

    @Transient
    @NotNull
    @Size(min = 6, max = 16, message = "Password must be between {min} and {max}")
    private String passwordDto;

    @Column(name = "first_name")
    @NotNull
    @Size(min = 2, max = 16 , message = "First name must be between {min} and {max}")
    private String firstName;

    @Column(name = "second_name")
    @NotNull
    @Size(min = 2, max = 16, message = "Second name must be between {min} and {max}")
    private String secondName;

    @Embedded
    @Valid
    private Address address;

    @ManyToMany(mappedBy = "accounts")
    private List<Booking> bookings = new ArrayList<>();

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordDto() {
        return passwordDto;
    }

    public void setPasswordDto(String passwordDto) {
        this.passwordDto = passwordDto;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
