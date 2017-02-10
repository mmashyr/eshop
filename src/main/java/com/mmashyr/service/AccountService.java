package com.mmashyr.service;

import com.mmashyr.entity.Account;
import com.mmashyr.entity.enums.OrderStatus;

import java.util.List;

/**
 * Created by Anton on 13.01.2017.
 */
public interface AccountService extends CRUDService<Account> {

    public Account findAccountByUsername(String username);

    public List<Account> findCustomerHavingBookingsWithOrderStatus(OrderStatus orderStatus);
}
