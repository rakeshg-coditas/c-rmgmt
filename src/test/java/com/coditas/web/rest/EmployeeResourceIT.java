package com.coditas.web.rest;

import com.coditas.CRmgmtApp;
import com.coditas.domain.Employee;
import com.coditas.repository.EmployeeRepository;
import com.coditas.service.EmployeeService;
import com.coditas.service.dto.EmployeeDTO;
import com.coditas.service.mapper.EmployeeMapper;
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


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.coditas.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link EmployeeResource} REST controller.
 */
@SpringBootTest(classes = CRmgmtApp.class)
public class EmployeeResourceIT {

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SKILLS = "AAAAAAAAAA";
    private static final String UPDATED_SKILLS = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTS = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTS = "BBBBBBBBBB";

    private static final String DEFAULT_PICTURE = "AAAAAAAAAA";
    private static final String UPDATED_PICTURE = "BBBBBBBBBB";

    private static final String DEFAULT_SKILL_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_SKILL_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_REPORT_TO = "AAAAAAAAAA";
    private static final String UPDATED_REPORT_TO = "BBBBBBBBBB";

    private static final String DEFAULT_TEAM = "AAAAAAAAAA";
    private static final String UPDATED_TEAM = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CAREER_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CAREER_START_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_CAREER_START_DATE = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_JOINING_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_JOINING_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_JOINING_DATE = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_ID = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BILLABLE = "AAAAAAAAAA";
    private static final String UPDATED_BILLABLE = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICE_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_OFFICE_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYMENT_TYPE = "BBBBBBBBBB";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restEmployeeMockMvc;

    private Employee employee;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EmployeeResource employeeResource = new EmployeeResource(employeeService);
        this.restEmployeeMockMvc = MockMvcBuilders.standaloneSetup(employeeResource)
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
    public static Employee createEntity() {
        Employee employee = new Employee()
            .email(DEFAULT_EMAIL)
            .name(DEFAULT_NAME)
            .skills(DEFAULT_SKILLS)
            .projects(DEFAULT_PROJECTS)
            .picture(DEFAULT_PICTURE)
            .skill_category(DEFAULT_SKILL_CATEGORY)
            .report_to(DEFAULT_REPORT_TO)
            .team(DEFAULT_TEAM)
            .career_start_date(DEFAULT_CAREER_START_DATE)
            .joining_date(DEFAULT_JOINING_DATE)
            .role(DEFAULT_ROLE)
            .employeeId(DEFAULT_EMPLOYEE_ID)
            .billable(DEFAULT_BILLABLE)
            .officeLocation(DEFAULT_OFFICE_LOCATION)
            .employmentType(DEFAULT_EMPLOYMENT_TYPE);
        return employee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Employee createUpdatedEntity() {
        Employee employee = new Employee()
            .email(UPDATED_EMAIL)
            .name(UPDATED_NAME)
            .skills(UPDATED_SKILLS)
            .projects(UPDATED_PROJECTS)
            .picture(UPDATED_PICTURE)
            .skill_category(UPDATED_SKILL_CATEGORY)
            .report_to(UPDATED_REPORT_TO)
            .team(UPDATED_TEAM)
            .career_start_date(UPDATED_CAREER_START_DATE)
            .joining_date(UPDATED_JOINING_DATE)
            .role(UPDATED_ROLE)
            .employeeId(UPDATED_EMPLOYEE_ID)
            .billable(UPDATED_BILLABLE)
            .officeLocation(UPDATED_OFFICE_LOCATION)
            .employmentType(UPDATED_EMPLOYMENT_TYPE);
        return employee;
    }

    @BeforeEach
    public void initTest() {
        employeeRepository.deleteAll();
        employee = createEntity();
    }

    @Test
    public void createEmployee() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isCreated());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate + 1);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEmployee.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testEmployee.getSkills()).isEqualTo(DEFAULT_SKILLS);
        assertThat(testEmployee.getProjects()).isEqualTo(DEFAULT_PROJECTS);
        assertThat(testEmployee.getPicture()).isEqualTo(DEFAULT_PICTURE);
        assertThat(testEmployee.getSkill_category()).isEqualTo(DEFAULT_SKILL_CATEGORY);
        assertThat(testEmployee.getReport_to()).isEqualTo(DEFAULT_REPORT_TO);
        assertThat(testEmployee.getTeam()).isEqualTo(DEFAULT_TEAM);
        assertThat(testEmployee.getCareer_start_date()).isEqualTo(DEFAULT_CAREER_START_DATE);
        assertThat(testEmployee.getJoining_date()).isEqualTo(DEFAULT_JOINING_DATE);
        assertThat(testEmployee.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testEmployee.getEmployeeId()).isEqualTo(DEFAULT_EMPLOYEE_ID);
        assertThat(testEmployee.getBillable()).isEqualTo(DEFAULT_BILLABLE);
        assertThat(testEmployee.getOfficeLocation()).isEqualTo(DEFAULT_OFFICE_LOCATION);
        assertThat(testEmployee.getEmploymentType()).isEqualTo(DEFAULT_EMPLOYMENT_TYPE);
    }

    @Test
    public void createEmployeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeeRepository.findAll().size();

        // Create the Employee with an existing ID
        employee.setId("existing_id");
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeeMockMvc.perform(post("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllEmployees() throws Exception {
        // Initialize the database
        employeeRepository.save(employee);

        // Get all the employeeList
        restEmployeeMockMvc.perform(get("/api/employees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].skills").value(hasItem(DEFAULT_SKILLS.toString())))
            .andExpect(jsonPath("$.[*].projects").value(hasItem(DEFAULT_PROJECTS.toString())))
            .andExpect(jsonPath("$.[*].picture").value(hasItem(DEFAULT_PICTURE.toString())))
            .andExpect(jsonPath("$.[*].skill_category").value(hasItem(DEFAULT_SKILL_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].report_to").value(hasItem(DEFAULT_REPORT_TO.toString())))
            .andExpect(jsonPath("$.[*].team").value(hasItem(DEFAULT_TEAM.toString())))
            .andExpect(jsonPath("$.[*].career_start_date").value(hasItem(DEFAULT_CAREER_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].joining_date").value(hasItem(DEFAULT_JOINING_DATE.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())))
            .andExpect(jsonPath("$.[*].employeeId").value(hasItem(DEFAULT_EMPLOYEE_ID.toString())))
            .andExpect(jsonPath("$.[*].billable").value(hasItem(DEFAULT_BILLABLE.toString())))
            .andExpect(jsonPath("$.[*].officeLocation").value(hasItem(DEFAULT_OFFICE_LOCATION.toString())))
            .andExpect(jsonPath("$.[*].employmentType").value(hasItem(DEFAULT_EMPLOYMENT_TYPE.toString())));
    }
    
    @Test
    public void getEmployee() throws Exception {
        // Initialize the database
        employeeRepository.save(employee);

        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", employee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(employee.getId()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.skills").value(DEFAULT_SKILLS.toString()))
            .andExpect(jsonPath("$.projects").value(DEFAULT_PROJECTS.toString()))
            .andExpect(jsonPath("$.picture").value(DEFAULT_PICTURE.toString()))
            .andExpect(jsonPath("$.skill_category").value(DEFAULT_SKILL_CATEGORY.toString()))
            .andExpect(jsonPath("$.report_to").value(DEFAULT_REPORT_TO.toString()))
            .andExpect(jsonPath("$.team").value(DEFAULT_TEAM.toString()))
            .andExpect(jsonPath("$.career_start_date").value(DEFAULT_CAREER_START_DATE.toString()))
            .andExpect(jsonPath("$.joining_date").value(DEFAULT_JOINING_DATE.toString()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()))
            .andExpect(jsonPath("$.employeeId").value(DEFAULT_EMPLOYEE_ID.toString()))
            .andExpect(jsonPath("$.billable").value(DEFAULT_BILLABLE.toString()))
            .andExpect(jsonPath("$.officeLocation").value(DEFAULT_OFFICE_LOCATION.toString()))
            .andExpect(jsonPath("$.employmentType").value(DEFAULT_EMPLOYMENT_TYPE.toString()));
    }

    @Test
    public void getNonExistingEmployee() throws Exception {
        // Get the employee
        restEmployeeMockMvc.perform(get("/api/employees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateEmployee() throws Exception {
        // Initialize the database
        employeeRepository.save(employee);

        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Update the employee
        Employee updatedEmployee = employeeRepository.findById(employee.getId()).get();
        updatedEmployee
            .email(UPDATED_EMAIL)
            .name(UPDATED_NAME)
            .skills(UPDATED_SKILLS)
            .projects(UPDATED_PROJECTS)
            .picture(UPDATED_PICTURE)
            .skill_category(UPDATED_SKILL_CATEGORY)
            .report_to(UPDATED_REPORT_TO)
            .team(UPDATED_TEAM)
            .career_start_date(UPDATED_CAREER_START_DATE)
            .joining_date(UPDATED_JOINING_DATE)
            .role(UPDATED_ROLE)
            .employeeId(UPDATED_EMPLOYEE_ID)
            .billable(UPDATED_BILLABLE)
            .officeLocation(UPDATED_OFFICE_LOCATION)
            .employmentType(UPDATED_EMPLOYMENT_TYPE);
        EmployeeDTO employeeDTO = employeeMapper.toDto(updatedEmployee);

        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isOk());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
        Employee testEmployee = employeeList.get(employeeList.size() - 1);
        assertThat(testEmployee.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEmployee.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testEmployee.getSkills()).isEqualTo(UPDATED_SKILLS);
        assertThat(testEmployee.getProjects()).isEqualTo(UPDATED_PROJECTS);
        assertThat(testEmployee.getPicture()).isEqualTo(UPDATED_PICTURE);
        assertThat(testEmployee.getSkill_category()).isEqualTo(UPDATED_SKILL_CATEGORY);
        assertThat(testEmployee.getReport_to()).isEqualTo(UPDATED_REPORT_TO);
        assertThat(testEmployee.getTeam()).isEqualTo(UPDATED_TEAM);
        assertThat(testEmployee.getCareer_start_date()).isEqualTo(UPDATED_CAREER_START_DATE);
        assertThat(testEmployee.getJoining_date()).isEqualTo(UPDATED_JOINING_DATE);
        assertThat(testEmployee.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testEmployee.getEmployeeId()).isEqualTo(UPDATED_EMPLOYEE_ID);
        assertThat(testEmployee.getBillable()).isEqualTo(UPDATED_BILLABLE);
        assertThat(testEmployee.getOfficeLocation()).isEqualTo(UPDATED_OFFICE_LOCATION);
        assertThat(testEmployee.getEmploymentType()).isEqualTo(UPDATED_EMPLOYMENT_TYPE);
    }

    @Test
    public void updateNonExistingEmployee() throws Exception {
        int databaseSizeBeforeUpdate = employeeRepository.findAll().size();

        // Create the Employee
        EmployeeDTO employeeDTO = employeeMapper.toDto(employee);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmployeeMockMvc.perform(put("/api/employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(employeeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employee in the database
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteEmployee() throws Exception {
        // Initialize the database
        employeeRepository.save(employee);

        int databaseSizeBeforeDelete = employeeRepository.findAll().size();

        // Delete the employee
        restEmployeeMockMvc.perform(delete("/api/employees/{id}", employee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Employee.class);
        Employee employee1 = new Employee();
        employee1.setId("id1");
        Employee employee2 = new Employee();
        employee2.setId(employee1.getId());
        assertThat(employee1).isEqualTo(employee2);
        employee2.setId("id2");
        assertThat(employee1).isNotEqualTo(employee2);
        employee1.setId(null);
        assertThat(employee1).isNotEqualTo(employee2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmployeeDTO.class);
        EmployeeDTO employeeDTO1 = new EmployeeDTO();
        employeeDTO1.setId("id1");
        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
        employeeDTO2.setId(employeeDTO1.getId());
        assertThat(employeeDTO1).isEqualTo(employeeDTO2);
        employeeDTO2.setId("id2");
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
        employeeDTO1.setId(null);
        assertThat(employeeDTO1).isNotEqualTo(employeeDTO2);
    }
}
