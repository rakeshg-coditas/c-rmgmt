package com.coditas.service;

import com.coditas.service.dto.TeamMembersDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.coditas.domain.TeamMembers}.
 */
public interface TeamMembersService {

    /**
     * Save a teamMembers.
     *
     * @param teamMembersDTO the entity to save.
     * @return the persisted entity.
     */
    TeamMembersDTO save(TeamMembersDTO teamMembersDTO);

    /**
     * Get all the teamMembers.
     *
     * @return the list of entities.
     */
    List<TeamMembersDTO> findAll();


    /**
     * Get the "id" teamMembers.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TeamMembersDTO> findOne(String id);

    /**
     * Delete the "id" teamMembers.
     *
     * @param id the id of the entity.
     */
    void delete(String id);



    List<TeamMembersDTO> getLeadDetails();
}
