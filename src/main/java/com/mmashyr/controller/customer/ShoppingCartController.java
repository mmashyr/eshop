package com.mmashyr.controller.customer;

import com.mmashyr.entity.Account;
import com.mmashyr.entity.Booking;
import com.mmashyr.entity.Product;
import com.mmashyr.entity.enums.DeliveryType;
import com.mmashyr.entity.enums.OrderStatus;
import com.mmashyr.service.AccountService;
import com.mmashyr.service.BookingService;
import com.mmashyr.service.ProductService;
import com.mmashyr.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * Created by Anton on 19.01.2017.
 */
@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    public final static String CART_PAGE = "customerpages/cart";

    private BookingService bookingService;
    private ShoppingCart shoppingCart;
    private AccountService accountService;
    private ProductService productService;

    @Autowired
    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Autowired
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute
    public void addCart(Model model) {
        model.addAttribute("cart", shoppingCart);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showShoppingCart(Model model) {
        model.addAttribute("deliveryTypes", DeliveryType.values());
        return CART_PAGE;
    }

    @RequestMapping(value = "/remove/{productId}/", method = RequestMethod.DELETE)
    public String removeProductFromCart(@PathVariable Long productId) {
        Product product = productService.findOne(productId);
        shoppingCart.removeProduct(product, shoppingCart.getSalePositions().get(product));
        return "redirect:/cart";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addToShoppingCart(@RequestParam("productId") Long productId, @RequestParam("amount") String amount, Model model) {
        if(Integer.parseInt(amount) < 1){
            return "redirect:/product/" + productId;
        }
        Product product = productService.findOne(productId);
        shoppingCart.addProduct(product, Integer.parseInt(amount));
        return "redirect:/cart";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAsBooking(@RequestParam String deliveryType) {
        if(shoppingCart.getSalePositions().size() == 0){
            return "redirect:/cart";
        }
        DeliveryType deliveryTypeEnum = DeliveryType.COURIER;
        try {
            deliveryTypeEnum = DeliveryType.valueOf(deliveryType.toUpperCase());
        } catch (IllegalArgumentException e) {
            //TODO do smth with the exception
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customer = authentication.getName();
        Account account = accountService.findAccountByUsername(customer);

        Booking booking = new Booking();
        booking.setOrderStatus(OrderStatus.NEW);
        booking.setProductsInBooking(shoppingCart.getSalePositions());
        booking.setTotalPrice(shoppingCart.getTotalPrice());
        booking.setDeliveryType(deliveryTypeEnum);
        booking.getAccounts().add(account);

        bookingService.save(booking);

        shoppingCart.removeAll();
        return "redirect:/cart"; //TODO add flash attributes about successful purchase
    }
}
