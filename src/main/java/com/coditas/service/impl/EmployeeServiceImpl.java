package com.coditas.service.impl;

import com.coditas.constants.CrmsConstants;
import com.coditas.domain.Role;
import com.coditas.repository.RoleRepository;
import com.coditas.service.EmployeeService;
import com.coditas.domain.Employee;
import com.coditas.repository.EmployeeRepository;
import com.coditas.service.dto.EmployeeDTO;
import com.coditas.service.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Employee}.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    @Autowired
    private RoleRepository roleRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    /**
     * Save a employee.
     *
     * @param employeeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        log.debug("Request to save Employee : {}", employeeDTO);
        Employee employee = employeeMapper.toEntity(employeeDTO);
        employee = employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    /**
     * Get all the employees.
     *
     * @return the list of entities.
     */
    @Override
    public List<EmployeeDTO> findAll() {
        log.debug("Request to get all Employees");
        return employeeRepository.findAll().stream()
            .map(employeeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the employees.
     *
     * @return the list of entities.
     */
    @Override
    public List<EmployeeDTO> findAllInfo() {
        log.debug("Request to get all Employees info");
        List<EmployeeDTO> empList = new ArrayList<>();

        employeeRepository.findAll().stream().forEach(e -> {
            EmployeeDTO emp = new EmployeeDTO();
            emp.setName(e.getName());
            emp.setPicture(e.getPicture());
            emp.setRole(e.getRole());
            empList.add(emp);
        });
        return empList;
    }

    @Override
    public List<EmployeeDTO> findAllInfoByLead(EmployeeDTO employeeDTO) {
        log.debug("Request to get all Employees info by lead");
        List<EmployeeDTO> empList = new ArrayList<>();
        boolean isLead = false;
        employeeRepository.findAll().stream().
            forEach(e -> {
                employeeDTO.setRole(e.getRole());
            });

        Optional<Role> byId = roleRepository.findById(employeeDTO.getId());
        EmployeeDTO emp = new EmployeeDTO();
        /*() -> byId.ifPresent(emp.getId()){

        }*/

        return empList;
    }


    /**
     * Get one employee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<EmployeeDTO> findOne(String id) {
        log.debug("Request to get Employee : {}", id);
        return employeeRepository.findById(id)
            .map(employeeMapper::toDto);
    }

    /**
     * Delete the employee by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Employee : {}", id);
        employeeRepository.deleteById(id);
    }
}
