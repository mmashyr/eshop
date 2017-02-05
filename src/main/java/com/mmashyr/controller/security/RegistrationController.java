package com.mmashyr.controller.security;

import com.mmashyr.entity.Account;
import com.mmashyr.entity.Address;
import com.mmashyr.entity.enums.UserRole;
import com.mmashyr.service.AccountService;
import com.mmashyr.service.security.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Anton on 20.01.2017.
 */
@Controller
public class RegistrationController {

    @Autowired
    CustomerUserDetailsService userDetailsService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);

        return "/security/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationForm(Account account) {
        account.setUserRole(UserRole.ROLE_CUSTOMER);
        accountService.save(account);
        return "/security/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "/security/login";
    }

}