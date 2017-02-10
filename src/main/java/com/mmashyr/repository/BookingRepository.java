package com.mmashyr.repository;

import com.mmashyr.entity.Booking;
import com.mmashyr.entity.enums.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Anton on 13.01.2017.
 */
@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findByOrderStatus(OrderStatus orderStatus);

    List<Booking> findByOrderStatusAndAccounts_Id(OrderStatus orderStatus, Long id);
}
