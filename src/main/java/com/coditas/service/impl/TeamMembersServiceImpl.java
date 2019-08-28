package com.coditas.service.impl;

import com.coditas.constants.CrmsConstants;
import com.coditas.domain.Employee;
import com.coditas.domain.Role;
import com.coditas.domain.TeamMembers;
import com.coditas.exception.NameNotFoundException;
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
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    @Autowired
    private MongoTemplate mongoTemplate;


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

    @Override
    public Map<String, List<EmployeeDTO>> getMasterLeadsAndMembersData() throws NameNotFoundException {
        Map<String, List<EmployeeDTO>> masterDataMap = new HashMap<>();

        List<EmployeeDTO> leadList=new ArrayList<>();
        List<EmployeeDTO> empList=new ArrayList<>();

        List<String> empRoleList = new ArrayList<>();
        empRoleList.add(CrmsConstants.Roles.CEO);
        empRoleList.add(CrmsConstants.Roles.LEAD);

        Role leadRole=new Role();
        List<Role> empRoles=new ArrayList<>();

        String roleId= "";
        leadRole=roleRepository.findIdByNameInIgnoreCaseAndIsDeleted(CrmsConstants.Roles.LEAD,false);

        if(leadRole!=null && leadRole.getName().equals(CrmsConstants.Roles.LEAD)){
            roleId=leadRole.getId();
        }

        leadList= employeeRepository.findAllByRole(roleId);
        if(leadList!=null && !leadList.isEmpty()) {
            masterDataMap.put(CrmsConstants.Roles.Leads, leadList);
        }/*else{
            throw new NameNotFoundException("Name is empty, lead not found exception!");
        }*/

        empRoles=roleRepository.findIdByNameNotIn(empRoleList);

        if(empRoles!=null && !empRoles.isEmpty()) {
            empList= employeeRepository.findAllByRoleIn(empRoles);
        }

        if(empList!=null && !empList.isEmpty()) {
            masterDataMap.put(CrmsConstants.Roles.TEAM_MEMBERS, empList);
        }/*else {
            throw new NameNotFoundException("Name is empty, member not found exception!");
        }*/
        return masterDataMap;
    }

    @Override
    public List<TeamMembersDTO> findAllName() {
        log.debug("Request to get all TeamMembers");
        Query query=new Query();
        query.fields().include("name").include("id");
        List<TeamMembers> teamMembersList = mongoTemplate.find(query, TeamMembers.class);
        return teamMembersMapper.toDto(teamMembersList);
    }
}
