package com.mmashyr.controller.customer;

import com.mmashyr.entity.Account;
import com.mmashyr.entity.Booking;
import com.mmashyr.repository.AccountRepository;
import com.mmashyr.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Anton on 27.01.2017.
 */
@Controller(value = "/booking")
public class BookingController {

    private final static String CUSTOMER_PAGES = "customerpages/";

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BookingRepository bookingRepository;

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void setBookingRepository(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAllBookings(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customer = authentication.getName();
        Account account = accountRepository.findAccountByUsername(customer);

        List<Booking> bookings = account.getBookings();
        model.addAttribute("bookings", bookings);

        return CUSTOMER_PAGES + "bookings";
    }
}
