package com.mmashyr.repository;

import com.mmashyr.entity.SalePosition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Anton on 13.01.2017.
 */
@Repository
public interface SalePositionRepository extends CrudRepository<SalePosition, Long> {
}
