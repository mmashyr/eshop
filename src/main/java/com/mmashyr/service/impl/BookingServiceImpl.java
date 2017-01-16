package com.mmashyr.service.impl;

import com.mmashyr.entity.Booking;
import com.mmashyr.repository.BookingRepository;
import com.mmashyr.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Anton on 13.01.2017.
 */
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Override
    @Transactional
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookingRepository.delete(id);
    }

    @Override
    public Booking findOne(Long id) {
        return bookingRepository.findOne(id);
    }

    @Override
    public List<Booking> getAll() {
        return (List<Booking>) bookingRepository.findAll();
    }
}
