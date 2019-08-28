package com.coditas.service.impl;

import com.coditas.service.ClientService;
import com.coditas.domain.Client;
import com.coditas.repository.ClientRepository;
import com.coditas.service.dto.ClientDTO;
import com.coditas.service.mapper.ClientMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Client}.
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    /**
     * Save a client.
     *
     * @param clientDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClientDTO save(ClientDTO clientDTO,boolean isUpdate) {
        log.debug("Request to save Client : {}", clientDTO);
        Client client = clientMapper.toEntity(clientDTO);
        if(isUpdate) {
            Optional<Client> availableClient = clientRepository.findById(clientDTO.getId());
                if(availableClient.isPresent())
                    if(availableClient.get().isIsActive()){
                        client = clientRepository.save(client);
                        return clientMapper.toDto(client);
                    }else{
                        return clientMapper.toDto(availableClient.get());
                    }
                else
                    return null;
        }else{
            client.setIsActive(true);
        }
        client = clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    /**
     * Get all the clients.
     *
     * @return the list of entities.
     */
    @Override
    public List<ClientDTO> findAll() {
        log.debug("Request to get all Clients");
        return clientRepository.findAll().stream()
            .map(clientMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one client by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<ClientDTO> findOne(String id) {
        log.debug("Request to get Client : {}", id);
        return clientRepository.findById(id)
            .map(clientMapper::toDto);
    }

    /**
     * Delete the client by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Client : {}", id);
        /*
        * //check if the client id is mapped with any project
        *
        * projectRepository.getIdByClient(id);
        *
        * if there is project mapped then dont allow to delete
        *
        *
        * */
        clientRepository.deleteById(id);
    }
}
