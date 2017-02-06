package com.mmashyr.controller.customer;

import com.mmashyr.entity.Account;
import com.mmashyr.entity.Booking;
import com.mmashyr.entity.Product;
import com.mmashyr.entity.enums.OrderStatus;
import com.mmashyr.repository.AccountRepository;
import com.mmashyr.repository.BookingRepository;
import com.mmashyr.service.ProductService;
import com.mmashyr.utils.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Anton on 19.01.2017.
 */
@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    private final static String CART_PAGE = "customerpages/cart";
    @Autowired
    ShoppingCart shoppingCart;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    BookingRepository bookingRepository;
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute
    public void addCart(Model model) {
        model.addAttribute("cart", shoppingCart);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showShoppingCart() {
        return CART_PAGE;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addToShoppingCart(@RequestParam("productId") Long productId, @RequestParam("amount") String amount) {
        Product product = productService.findOne(productId);
        shoppingCart.addProduct(product, Integer.parseInt(amount));
        return "redirect:/cart";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAsBooking() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customer = authentication.getName();
        Account account = accountRepository.findAccountByUsername(customer);

        Booking booking = new Booking();
        booking.setOrderStatus(OrderStatus.NEW);
        booking.setProductsInBooking(shoppingCart.getSalePositions());
        booking.setTotalPrice(shoppingCart.getTotalPrice());
        booking.setAccount(account);

        bookingRepository.save(booking);

        shoppingCart.removeAll();
        return "redirect:/cart"; //TODO add flash attributes about successful purchase
    }
}
