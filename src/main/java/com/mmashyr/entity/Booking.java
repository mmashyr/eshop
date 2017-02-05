package com.mmashyr.entity;

import com.mmashyr.entity.enums.DeliveryType;
import com.mmashyr.entity.enums.OrderStatus;

import javax.persistence.*;
import java.util.HashMap;
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

//TODO add date and time here  and to the jsp as well
    @ElementCollection
    @CollectionTable(name = "product_in_booking")
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> productsInBooking = new HashMap<>();

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
}
