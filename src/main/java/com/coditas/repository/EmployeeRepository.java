package com.coditas.repository;

import com.coditas.domain.Employee;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data MongoDB repository for the Employee entity.
 */

@Repository
@JaversSpringDataAuditable
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    @Query(fields="{ name : 1 , skills : 1 }")
    Optional<List<Employee>> findAllByBillable(String id);

}
