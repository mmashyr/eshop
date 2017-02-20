package com.mmashyr.service;

import com.mmashyr.entity.Category;
import com.mmashyr.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Anton on 13.01.2017.
 */
public interface ProductService extends CRUDService<Product> {

    List<Product> findDistinctByCategoryIds(List<Long> categories);

    Page<Product> findDistinctByCategoryNames(List<String> categories, Pageable pageable);

    Page<Product> findByName(String name, Pageable pageable);

    Page<Product> findAll(Pageable pageable);
}
