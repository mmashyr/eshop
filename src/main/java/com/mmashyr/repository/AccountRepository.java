package com.mmashyr.repository;

import com.mmashyr.entity.Account;
import com.mmashyr.entity.enums.OrderStatus;
import com.mmashyr.entity.enums.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Anton on 13.01.2017.
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    public Account findAccountByUsername(String username);

    public List<Account> findAccountsByBookings_orderStatusAndUserRole(OrderStatus orderStatus, UserRole userRole);
}
