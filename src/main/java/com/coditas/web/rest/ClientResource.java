package com.coditas.web.rest;

import com.coditas.service.ClientService;
import com.coditas.web.rest.errors.BadRequestAlertException;
import com.coditas.service.dto.ClientDTO;

import com.coditas.web.rest.validations.ValidatorInterface;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.coditas.domain.Client}.
 */
@RestController
@RequestMapping("/api")
public class ClientResource {

    private final Logger log = LoggerFactory.getLogger(ClientResource.class);

    private static final String ENTITY_NAME = "client";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    ValidatorInterface validatorInterface;

    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * {@code POST  /clients} : Create a new client.
     *
     * @param clientDTO the clientDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clientDTO, or with status {@code 400 (Bad Request)} if the client has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/clients")
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) throws URISyntaxException {
        log.debug("REST request to save Client : {}", clientDTO);
        if (clientDTO.getId() != null) {
            throw new BadRequestAlertException("A new client cannot already have an ID", ENTITY_NAME, "idexists");
        }
        validateClient(clientDTO);
        ClientDTO result = clientService.save(clientDTO,false);
        return ResponseEntity.created(new URI("/api/clients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /clients} : Updates an existing client.
     *
     * @param clientDTO the clientDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientDTO,
     * or with status {@code 400 (Bad Request)} if the clientDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clientDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/clients")
    public ResponseEntity<ClientDTO> updateClient(@Valid @RequestBody ClientDTO clientDTO) throws URISyntaxException {
        log.debug("REST request to update Client : {}", clientDTO);
        if (clientDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        validateClient(clientDTO);
        ClientDTO result = clientService.save(clientDTO,true);
        String msg = "";
        if(result == null )
            throw new BadRequestAlertException("Client not available", ENTITY_NAME, "clientNotAvailable");
        else if(!result.isIsActive())
            throw new BadRequestAlertException("Not active client", ENTITY_NAME, "notActiveClient");

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, clientDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /clients} : get all the clients.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clients in body.
     */
    @GetMapping("/clients")
    public List<ClientDTO> getAllClients() {
        log.debug("REST request to get all Clients");
        return clientService.findAll();
    }

    /**
     * {@code GET  /clients/:id} : get the "id" client.
     *
     * @param id the id of the clientDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clientDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable String id) {
        log.debug("REST request to get Client : {}", id);
        Optional<ClientDTO> clientDTO = clientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clientDTO);
    }

    /**
     * {@code DELETE  /clients/:id} : delete the "id" client.
     *
     * @param id the id of the clientDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        log.debug("REST request to delete Client : {}", id);
        clientService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }

    public ResponseEntity<ClientDTO> validateClient(ClientDTO clientDTO){

        validatorInterface.validate(clientDTO);

        String errorMsg = "";

        errorMsg = validatorInterface.checkNullOrEmptyValue("name");
        errorMsg = errorMsg + validatorInterface.checkNullOrEmptyValue("email");
        errorMsg = errorMsg + validatorInterface.matchEmailRegEx("email");

        if(!errorMsg.trim().isEmpty())
            throw  new BadRequestAlertException(errorMsg,ENTITY_NAME,"invalid");
        return null;
    }

}
