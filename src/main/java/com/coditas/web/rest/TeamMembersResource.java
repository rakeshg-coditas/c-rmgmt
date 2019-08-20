package com.coditas.web.rest;

import com.coditas.service.TeamMembersService;
import com.coditas.web.rest.errors.BadRequestAlertException;
import com.coditas.service.dto.TeamMembersDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.coditas.domain.TeamMembers}.
 */
@RestController
@RequestMapping("/apiii")
public class TeamMembersResource {

    private final Logger log = LoggerFactory.getLogger(TeamMembersResource.class);

    private static final String ENTITY_NAME = "teamMembers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TeamMembersService teamMembersService;

    public TeamMembersResource(TeamMembersService teamMembersService) {
        this.teamMembersService = teamMembersService;
    }

    /**
     * {@code POST  /team-members} : Create a new teamMembers.
     *
     * @param teamMembersDTO the teamMembersDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new teamMembersDTO, or with status {@code 400 (Bad Request)} if the teamMembers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/team-members")
    public ResponseEntity<TeamMembersDTO> createTeamMembers(@RequestBody TeamMembersDTO teamMembersDTO) throws URISyntaxException {
        log.debug("REST request to save TeamMembers : {}", teamMembersDTO);
        if (teamMembersDTO.getId() != null) {
            throw new BadRequestAlertException("A new teamMembers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TeamMembersDTO result = teamMembersService.save(teamMembersDTO);
        return ResponseEntity.created(new URI("/api/team-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /team-members} : Updates an existing teamMembers.
     *
     * @param teamMembersDTO the teamMembersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated teamMembersDTO,
     * or with status {@code 400 (Bad Request)} if the teamMembersDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the teamMembersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/team-members")
    public ResponseEntity<TeamMembersDTO> updateTeamMembers(@RequestBody TeamMembersDTO teamMembersDTO) throws URISyntaxException {
        log.debug("REST request to update TeamMembers : {}", teamMembersDTO);
        if (teamMembersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TeamMembersDTO result = teamMembersService.save(teamMembersDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, teamMembersDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /team-members} : get all the teamMembers.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of teamMembers in body.
     */
    @GetMapping("/team-members")
    public List<TeamMembersDTO> getAllTeamMembers() {
        log.debug("REST request to get all TeamMembers");

        List<TeamMembersDTO> teamList = teamMembersService.findAll();
        teamMembersService.getLeadDetails();


        return teamMembersService.findAll();
    }

    /**
     * {@code GET  /team-members/:id} : get the "id" teamMembers.
     *
     * @param id the id of the teamMembersDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the teamMembersDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/team-members/{id}")
    public ResponseEntity<TeamMembersDTO> getTeamMembers(@PathVariable String id) {
        log.debug("REST request to get TeamMembers : {}", id);
        Optional<TeamMembersDTO> teamMembersDTO = teamMembersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teamMembersDTO);
    }

    /**
     * {@code DELETE  /team-members/:id} : delete the "id" teamMembers.
     *
     * @param id the id of the teamMembersDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/team-members/{id}")
    public ResponseEntity<Void> deleteTeamMembers(@PathVariable String id) {
        log.debug("REST request to delete TeamMembers : {}", id);
        teamMembersService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id)).build();
    }
}
