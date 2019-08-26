package com.coditas.service.impl;

import com.coditas.domain.Role;
import com.coditas.domain.TeamMembers;
import com.coditas.repository.EmployeeRepository;
import com.coditas.repository.RoleRepository;
import com.coditas.repository.TeamMembersRepository;
import com.coditas.service.EmployeeService;
import com.coditas.service.TeamMembersService;
import com.coditas.service.dto.EmployeeDTO;
import com.coditas.service.dto.TeamMembersDTO;
import com.coditas.service.mapper.TeamMembersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TeamMembers}.
 */
@Service
public class TeamMembersServiceImpl implements TeamMembersService {

    private final Logger log = LoggerFactory.getLogger(TeamMembersServiceImpl.class);

    private final TeamMembersRepository teamMembersRepository;

    private final TeamMembersMapper teamMembersMapper;

    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public TeamMembersServiceImpl(TeamMembersRepository teamMembersRepository, TeamMembersMapper teamMembersMapper) {
        this.teamMembersRepository = teamMembersRepository;
        this.teamMembersMapper = teamMembersMapper;
    }

    /**
     * Save a teamMembers.
     *
     * @param teamMembersDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TeamMembersDTO save(TeamMembersDTO teamMembersDTO) {
        log.debug("Request to save TeamMembers : {}", teamMembersDTO);
        TeamMembers teamMembers = teamMembersMapper.toEntity(teamMembersDTO);
        teamMembers = teamMembersRepository.save(teamMembers);
        return teamMembersMapper.toDto(teamMembers);
    }

    /**
     * Get all the teamMembers.
     *
     * @return the list of entities.
     */
    @Override
    public List<TeamMembersDTO> findAll() {
        log.debug("Request to get all TeamMembers");
        return teamMembersRepository.findAll().stream()
            .map(teamMembersMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one teamMembers by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<TeamMembersDTO> findOne(String id) {
        log.debug("Request to get TeamMembers : {}", id);
        return teamMembersRepository.findById(id)
            .map(teamMembersMapper::toDto);
    }

    /**
     * Delete the teamMembers by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete TeamMembers : {}", id);
        teamMembersRepository.deleteById(id);
    }

    public List<TeamMembersDTO> getLeadDetails() {
        //employeeService.getLeadDetails();
        return null;
    }

    @Override
    public Map<String, List<EmployeeDTO>> getMasterLeadsAndMembersData() {
        Map<String, List<EmployeeDTO>> masterDataMap = new HashMap<>();
        Role role=new Role();
        String roleId= "";
        role=roleRepository.findIdByNameAndIsDeleted("LEAD",false);

        if(role!=null && role.getName().equals("LEAD")){
            roleId=role.getId();
        }

        List<EmployeeDTO> leadEmployees= employeeRepository.findIdByRole(roleId);
        if(leadEmployees!=null && !leadEmployees.isEmpty()) {
            masterDataMap.put("Leads", leadEmployees);
        }else{
            //throws custom exception lead not found
        }

        return masterDataMap;
    }

}
