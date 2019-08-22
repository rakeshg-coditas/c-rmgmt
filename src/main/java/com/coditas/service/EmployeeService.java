package com.coditas.service;

import com.coditas.service.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.coditas.domain.Employee}.
 */
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
     * Get all the employees info.
     *
     * @return the list of entities.
     */
    List<EmployeeDTO> findAllInfo();

    /**
     * Get all the employees info.
     *
     * @return the list of entities.
     */
    List<EmployeeDTO> findAllInfoByLead(EmployeeDTO employeeDTO);

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
}
