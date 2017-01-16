package com.mmashyr.repository;

import com.mmashyr.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Anton on 13.01.2017.
 */
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
