package com.mmashyr.controller.customer;

import com.mmashyr.entity.Account;
import com.mmashyr.entity.Booking;
import com.mmashyr.service.AccountService;
import com.mmashyr.service.BookingService;
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
@Controller
@RequestMapping(value = "/booking")
public class BookingController {

    public final static String CUSTOMER_PAGES = "customerpages/";

    AccountService accountService;
    BookingService bookingService;

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAllBookings(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customer = authentication.getName();
        Account account = accountService.findAccountByUsername(customer);

        List<Booking> bookings = account.getBookings();
        model.addAttribute("bookings", bookings);

        return CUSTOMER_PAGES + "bookings";
    }
}
