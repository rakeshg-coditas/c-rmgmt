package com.coditas.service;

import com.coditas.service.dto.RoleDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.coditas.domain.Role}.
 */
public interface RoleService {

    /**
     * Save a role.
     *
     * @param roleDTO the entity to save.
     * @return the persisted entity.
     */
    RoleDTO save(RoleDTO roleDTO);

    /**
     * Get all the roles.
     *
     * @return the list of entities.
     */
    List<RoleDTO> findAll();


    /**
     * Get the "id" role.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RoleDTO> findOne(String id);

    /**
     * Delete the "id" role.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
