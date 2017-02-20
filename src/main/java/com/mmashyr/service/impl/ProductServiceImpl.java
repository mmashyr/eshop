package com.mmashyr.service.impl;

import com.mmashyr.entity.Category;
import com.mmashyr.entity.Product;
import com.mmashyr.repository.ProductRepository;
import com.mmashyr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Anton on 13.01.2017.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Override
    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }


    @Override
    public List<Product> findDistinctByCategoryIds(List<Long> categories) {
       return  productRepository.findDistinctByCategoryIds(categories);
    }

    @Override
    public Page<Product> findDistinctByCategoryNames(List<String> categories, Pageable pageable) {
        return productRepository.findDistinctByCategoryNames(categories, pageable);
    }

    @Override
    public Page<Product> findByName(String name, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
