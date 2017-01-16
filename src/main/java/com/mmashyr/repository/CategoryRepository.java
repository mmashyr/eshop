package com.mmashyr.repository;

import com.mmashyr.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Anton on 13.01.2017.
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
}
