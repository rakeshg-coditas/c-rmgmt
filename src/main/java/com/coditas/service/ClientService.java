package com.coditas.service;

import com.coditas.service.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.coditas.domain.Client}.
 */
public interface ClientService {

    /**
     * Save a client.
     *
     * @param clientDTO the entity to save.
     * @return the persisted entity.
     */
    ClientDTO save(ClientDTO clientDTO,boolean isUpdate);

    /**
     * Get all the clients.
     *
     * @return the list of entities.
     */
    List<ClientDTO> findAll();


    /**
     * Get the "id" client.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClientDTO> findOne(String id);

    /**
     * Delete the "id" client.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
