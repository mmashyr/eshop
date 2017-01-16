package com.mmashyr.entity;

import com.mmashyr.entity.enums.DeliveryType;
import com.mmashyr.entity.enums.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "booking")
    private List<SalePosition> salePositions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "booking_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "delivery_type")
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    public Booking() {
    }

    public List<SalePosition> getSalePositions() {
        return salePositions;
    }

    public void setSalePositions(List<SalePosition> salePositions) {
        this.salePositions = salePositions;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
}
