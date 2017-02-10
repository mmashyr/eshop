package com.mmashyr.service;

import com.mmashyr.entity.Booking;
import com.mmashyr.entity.enums.OrderStatus;

import java.util.List;

/**
 * Created by Anton on 13.01.2017.
 */
public interface BookingService extends CRUDService<Booking> {
    List<Booking> findByOrderStatus(OrderStatus orderStatus);

    List<Booking> findByOrderStatusAndAccountId(OrderStatus orderStatus, Long id);

}
