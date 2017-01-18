package com.mmashyr.service;

import com.mmashyr.entity.Category;
import com.mmashyr.entity.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Anton on 13.01.2017.
 */
public interface ProductService extends CRUDService<Product> {

    List<Product> findDistinctByCategoryIds(List<Long> categories);
}
