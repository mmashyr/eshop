package com.mmashyr.controller.security;

import com.mmashyr.entity.Account;
import com.mmashyr.entity.enums.UserRole;
import com.mmashyr.service.AccountService;
import com.mmashyr.service.security.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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
    public String registrationForm(@Valid @ModelAttribute Account account, BindingResult accountBindingResult) {
        if (accountBindingResult.hasErrors()) {
            return "/security/registration";
        }
        account.setUserRole(UserRole.ROLE_CUSTOMER);
        account.setPassword(account.getPasswordDto());
        accountService.save(account);
        return "/security/login";

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "/security/login";
    }

}