package com.mmashyr.entity;

import javax.persistence.*;

/**
 * Created by Anton on 12.01.2017.
 */
@Entity
@Table(name = "sale_position")
public class SalePosition extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_position_id")
    private long id;

    @Column(name = "quantity_of_product")
    private int quantityOfProduct;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public SalePosition() {
    }

    public int getQuantityOfProduct() {
        return quantityOfProduct;
    }

    public void setQuantityOfProduct(int quantityOfProduct) {
        this.quantityOfProduct = quantityOfProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
