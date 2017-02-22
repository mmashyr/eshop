package com.mmashyr.controller.courier;

import com.mmashyr.entity.Account;
import com.mmashyr.entity.Booking;
import com.mmashyr.entity.enums.OrderStatus;
import com.mmashyr.service.AccountService;
import com.mmashyr.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mark on 10.02.2017.
 */
@Controller
@RequestMapping(value = "/courier")
public class DeliveryController {

    private final static String COURIER_PAGES = "courierpages/";

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

    @ModelAttribute
    public void addAccount(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String courier = authentication.getName();
        Account couriersAccount = accountService.findAccountByUsername(courier);
        model.addAttribute("couriersAccount", couriersAccount);
    }

    @RequestMapping(value = "/booking")
    public String handleAccessToBookingsWithoutOrderStatus() {
        return "redirect:/courier/booking/new/";
    }

    @RequestMapping(value = "/booking/{orderStatus}", method = RequestMethod.GET)
    public String getBookingsByOrderStatus(Model model, @ModelAttribute("couriersAccount") Account couriersAccount, @PathVariable("orderStatus") String orderStatus) {
        OrderStatus orderStatusEnum = OrderStatus.NEW;
        try {
            orderStatusEnum = OrderStatus.valueOf(orderStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            return "redirect:/courier/booking/new/";
        }
        List<Account> accounts = accountService.findCustomerHavingBookingsWithOrderStatus(orderStatusEnum);
        Map<Account, List<Booking>> accountAndBookings = new HashMap<>();

        for (Account account : accounts) {
            accountAndBookings.put(account,
                    bookingService.findByOrderStatusAndAccountId(orderStatusEnum, account.getId()));
        }
        model.addAttribute("accountAndBookings", accountAndBookings);
        return COURIER_PAGES + "bookings";
    }

    @RequestMapping(value = "/booking/add/{bookingId}", method = RequestMethod.POST)
    public String takeBooking(@ModelAttribute("couriersAccount") Account couriersAccount, @PathVariable("bookingId") Long bookingId) {
        Booking booking = bookingService.findOne(bookingId);
        booking.setOrderStatus(OrderStatus.DELIVERY);
        booking.getAccounts().add(couriersAccount);
        bookingService.save(booking);
        return "redirect:/courier/booking/delivery";
    }

}
