package com.coditas.service;

import com.coditas.domain.Employee;
import com.coditas.service.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.coditas.domain.Employee}.
 */
@Service
public interface EmployeeService {

    /**
     * Save a employee.
     *
     * @param employeeDTO the entity to save.
     * @return the persisted entity.
     */
    EmployeeDTO save(EmployeeDTO employeeDTO);

    /**
     * Get all the employees.
     *
     * @return the list of entities.
     */
    List<EmployeeDTO> findAll();


    /**
     * Get the "id" employee.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmployeeDTO> findOne(String id);

    /**
     * Delete the "id" employee.
     *
     * @param id the id of the entity.
     */
    void delete(String id);

    String googleSignIn(String token);

    Map<String,List<Object>> getMasterData();

    Optional<List<EmployeeDTO>> getBillableEmployees(String id);
}
