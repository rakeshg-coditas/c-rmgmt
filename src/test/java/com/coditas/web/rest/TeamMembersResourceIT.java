package com.coditas.web.rest;

import com.coditas.CRmgmtApp;
import com.coditas.domain.TeamMembers;
import com.coditas.repository.TeamMembersRepository;
import com.coditas.service.TeamMembersService;
import com.coditas.service.dto.TeamMembersDTO;
import com.coditas.service.mapper.TeamMembersMapper;
import com.coditas.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.util.List;

import static com.coditas.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TeamMembersResource} REST controller.
 */
@SpringBootTest(classes = CRmgmtApp.class)
public class TeamMembersResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LEAD = "AAAAAAAAAA";
    private static final String UPDATED_LEAD = "BBBBBBBBBB";

    private static final String[] DEFAULT_MEMBERS = {"AAAAAAAAAA","DDDDDDDDDD"};
    private static final String[] UPDATED_MEMBERS = {"BBBBBBBBBB","CCCCCCCCCC","DDDDDDDDDD"};

    @Autowired
    private TeamMembersRepository teamMembersRepository;

    @Autowired
    private TeamMembersMapper teamMembersMapper;

    @Autowired
    private TeamMembersService teamMembersService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restTeamMembersMockMvc;

    private TeamMembers teamMembers;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TeamMembersResource teamMembersResource = new TeamMembersResource(teamMembersService);
        this.restTeamMembersMockMvc = MockMvcBuilders.standaloneSetup(teamMembersResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TeamMembers createEntity() {
        TeamMembers teamMembers = new TeamMembers()
            .name(DEFAULT_NAME)
            .lead(DEFAULT_LEAD)
            .members(DEFAULT_MEMBERS);
        return teamMembers;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TeamMembers createUpdatedEntity() {
        TeamMembers teamMembers = new TeamMembers()
            .name(UPDATED_NAME)
            .lead(UPDATED_LEAD)
            .members(UPDATED_MEMBERS);
        return teamMembers;
    }

    @BeforeEach
    public void initTest() {
        teamMembersRepository.deleteAll();
        teamMembers = createEntity();
    }

    @Test
    public void createTeamMembers() throws Exception {
        int databaseSizeBeforeCreate = teamMembersRepository.findAll().size();

        // Create the TeamMembers
        TeamMembersDTO teamMembersDTO = teamMembersMapper.toDto(teamMembers);
        restTeamMembersMockMvc.perform(post("/api/team-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teamMembersDTO)))
            .andExpect(status().isCreated());

        // Validate the TeamMembers in the database
        List<TeamMembers> teamMembersList = teamMembersRepository.findAll();
        assertThat(teamMembersList).hasSize(databaseSizeBeforeCreate + 1);
        TeamMembers testTeamMembers = teamMembersList.get(teamMembersList.size() - 1);
        assertThat(testTeamMembers.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTeamMembers.getLead()).isEqualTo(DEFAULT_LEAD);
        assertThat(testTeamMembers.getMembers()).isEqualTo(DEFAULT_MEMBERS);
    }

    @Test
    public void createTeamMembersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = teamMembersRepository.findAll().size();

        // Create the TeamMembers with an existing ID
        teamMembers.setId("existing_id");
        TeamMembersDTO teamMembersDTO = teamMembersMapper.toDto(teamMembers);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTeamMembersMockMvc.perform(post("/api/team-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teamMembersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TeamMembers in the database
        List<TeamMembers> teamMembersList = teamMembersRepository.findAll();
        assertThat(teamMembersList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllTeamMembers() throws Exception {
        // Initialize the database
        teamMembersRepository.save(teamMembers);

        // Get all the teamMembersList
        restTeamMembersMockMvc.perform(get("/api/team-members?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(teamMembers.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].lead").value(hasItem(DEFAULT_LEAD.toString())))
            .andExpect(jsonPath("$.[*].members").value(hasItem(DEFAULT_MEMBERS.toString())));
    }

    @Test
    public void getTeamMembers() throws Exception {
        // Initialize the database
        teamMembersRepository.save(teamMembers);

        // Get the teamMembers
        restTeamMembersMockMvc.perform(get("/api/team-members/{id}", teamMembers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(teamMembers.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.lead").value(DEFAULT_LEAD.toString()))
            .andExpect(jsonPath("$.members").value(DEFAULT_MEMBERS.toString()));
    }

    @Test
    public void getNonExistingTeamMembers() throws Exception {
        // Get the teamMembers
        restTeamMembersMockMvc.perform(get("/api/team-members/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTeamMembers() throws Exception {
        // Initialize the database
        teamMembersRepository.save(teamMembers);

        int databaseSizeBeforeUpdate = teamMembersRepository.findAll().size();

        // Update the teamMembers
        TeamMembers updatedTeamMembers = teamMembersRepository.findById(teamMembers.getId()).get();
        updatedTeamMembers
            .name(UPDATED_NAME)
            .lead(UPDATED_LEAD)
            .members(UPDATED_MEMBERS);
        TeamMembersDTO teamMembersDTO = teamMembersMapper.toDto(updatedTeamMembers);

        restTeamMembersMockMvc.perform(put("/api/team-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teamMembersDTO)))
            .andExpect(status().isOk());

        // Validate the TeamMembers in the database
        List<TeamMembers> teamMembersList = teamMembersRepository.findAll();
        assertThat(teamMembersList).hasSize(databaseSizeBeforeUpdate);
        TeamMembers testTeamMembers = teamMembersList.get(teamMembersList.size() - 1);
        assertThat(testTeamMembers.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTeamMembers.getLead()).isEqualTo(UPDATED_LEAD);
        assertThat(testTeamMembers.getMembers()).isEqualTo(UPDATED_MEMBERS);
    }

    @Test
    public void updateNonExistingTeamMembers() throws Exception {
        int databaseSizeBeforeUpdate = teamMembersRepository.findAll().size();

        // Create the TeamMembers
        TeamMembersDTO teamMembersDTO = teamMembersMapper.toDto(teamMembers);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTeamMembersMockMvc.perform(put("/api/team-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(teamMembersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TeamMembers in the database
        List<TeamMembers> teamMembersList = teamMembersRepository.findAll();
        assertThat(teamMembersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteTeamMembers() throws Exception {
        // Initialize the database
        teamMembersRepository.save(teamMembers);

        int databaseSizeBeforeDelete = teamMembersRepository.findAll().size();

        // Delete the teamMembers
        restTeamMembersMockMvc.perform(delete("/api/team-members/{id}", teamMembers.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TeamMembers> teamMembersList = teamMembersRepository.findAll();
        assertThat(teamMembersList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TeamMembers.class);
        TeamMembers teamMembers1 = new TeamMembers();
        teamMembers1.setId("id1");
        TeamMembers teamMembers2 = new TeamMembers();
        teamMembers2.setId(teamMembers1.getId());
        assertThat(teamMembers1).isEqualTo(teamMembers2);
        teamMembers2.setId("id2");
        assertThat(teamMembers1).isNotEqualTo(teamMembers2);
        teamMembers1.setId(null);
        assertThat(teamMembers1).isNotEqualTo(teamMembers2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TeamMembersDTO.class);
        TeamMembersDTO teamMembersDTO1 = new TeamMembersDTO();
        teamMembersDTO1.setId("id1");
        TeamMembersDTO teamMembersDTO2 = new TeamMembersDTO();
        assertThat(teamMembersDTO1).isNotEqualTo(teamMembersDTO2);
        teamMembersDTO2.setId(teamMembersDTO1.getId());
        assertThat(teamMembersDTO1).isEqualTo(teamMembersDTO2);
        teamMembersDTO2.setId("id2");
        assertThat(teamMembersDTO1).isNotEqualTo(teamMembersDTO2);
        teamMembersDTO1.setId(null);
        assertThat(teamMembersDTO1).isNotEqualTo(teamMembersDTO2);
    }
}
