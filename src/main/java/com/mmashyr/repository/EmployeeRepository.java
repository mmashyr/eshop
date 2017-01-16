package com.mmashyr.repository;

import com.mmashyr.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Anton on 13.01.2017.
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{
}
