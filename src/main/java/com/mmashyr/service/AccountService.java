package com.mmashyr.service;

import com.mmashyr.entity.Account;

/**
 * Created by Anton on 13.01.2017.
 */
public interface AccountService extends CRUDService<Account> {

    public Account findAccountByUsername(String username);
}
