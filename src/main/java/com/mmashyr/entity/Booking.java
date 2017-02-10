package com.mmashyr.entity;

import com.mmashyr.entity.enums.DeliveryType;
import com.mmashyr.entity.enums.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Anton on 12.01.2017.
 */
@Entity
@Table(name = "booking")
public class Booking extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "product_in_booking")
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> productsInBooking = new HashMap<>();

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "account_booking",
            joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "booking_id")})
    private List<Account> accounts = new ArrayList<>();

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "booking_date")
    private LocalDateTime dateTime = LocalDateTime.now();

    @Column(name = "booking_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "delivery_type")
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Account account) {
        this.accounts = accounts;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Map<Product, Integer> getProductsInBooking() {
        return productsInBooking;
    }

    public void setProductsInBooking(Map<Product, Integer> productsInBooking) {
        this.productsInBooking = productsInBooking;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
