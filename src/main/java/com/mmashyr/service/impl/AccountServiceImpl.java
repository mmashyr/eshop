package com.mmashyr.service.impl;

import com.mmashyr.entity.Account;
import com.mmashyr.entity.enums.OrderStatus;
import com.mmashyr.entity.enums.UserRole;
import com.mmashyr.repository.AccountRepository;
import com.mmashyr.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Anton on 13.01.2017.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        accountRepository.delete(id);
    }

    @Override
    public Account findOne(Long id) {
        return accountRepository.findOne(id);
    }

    @Override
    public List<Account> getAll() {
        return (List<Account>) accountRepository.findAll();
    }

    @Override
    public Account findAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    @Override
    public List<Account> findCustomerHavingBookingsWithOrderStatus(OrderStatus orderStatus) {
        return accountRepository.findAccountsByBookings_orderStatusAndUserRole(orderStatus, UserRole.ROLE_CUSTOMER);
    }
}
