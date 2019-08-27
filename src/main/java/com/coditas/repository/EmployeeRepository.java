package com.coditas.repository;

import com.coditas.domain.Employee;
import com.coditas.domain.Role;
import com.coditas.service.dto.EmployeeDTO;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data MongoDB repository for the Employee entity.
 */

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    @Query(fields="{ id : 1 , name : 1 }")
    public List<EmployeeDTO> findAllByRole(Object role);

    @Query(fields="{ id : 1 , name : 1 }")
    public List<EmployeeDTO> findAllByRoleIn(List<Role> roles);

}
