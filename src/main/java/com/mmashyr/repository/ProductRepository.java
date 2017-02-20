package com.mmashyr.repository;

import com.mmashyr.entity.Category;
import com.mmashyr.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Anton on 13.01.2017.
 */
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    @Query("SELECT DISTINCT p FROM Product p INNER JOIN p.categories c WHERE c.id IN (:categories)")
    List<Product> findDistinctByCategoryIds(@Param("categories") List<Long> categories);

    @Query("SELECT DISTINCT p FROM Product p INNER JOIN p.categories c WHERE c.name IN (:categories)")
    Page<Product> findDistinctByCategoryNames(@Param("categories") List<String> categories, Pageable pageable);

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Product> findAll(Pageable pageable);
}
