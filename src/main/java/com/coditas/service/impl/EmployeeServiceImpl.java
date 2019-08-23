package com.coditas.service.impl;

import com.coditas.constants.CrmsConstants;
import com.coditas.domain.Skills;
import com.coditas.errors.ValidatorInterface;
import com.coditas.errors.ValidatorInterfaceImpl;
import com.coditas.repository.*;
import com.coditas.security.jwt.TokenProvider;
import com.coditas.service.EmployeeService;
import com.coditas.domain.Employee;
import com.coditas.service.dto.EmployeeDTO;
import com.coditas.service.dto.SkillsDTO;
import com.coditas.service.mapper.EmployeeMapper;
import com.coditas.service.mapper.RoleMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import io.github.jhipster.config.JHipsterProperties;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Employee}.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private SkillsRepository skillsRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private BillableRepository billableRepository;

    @Autowired
    private OfficeLocationRepository officeLocationRepository;

    @Autowired
    EmploymentTypeRepository employmentTypeRepository;

    private final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);



    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl( EmployeeMapper employeeMapper) {

        this.employeeMapper = employeeMapper;
    }

    /**
     * Save a employee.
     *
     * @param employeeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        log.debug("Request to save Employee : {}", employeeDTO);
        Employee emp = isUserAvailable(employeeDTO.getEmail());
        if(emp!=null){
          employeeDTO.setId(emp.getId());

        }
        Employee employee = employeeMapper.toEntity(employeeDTO);
        employee = employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    /**
     * Get all the employees.
     *
     * @return the list of entities.
     */
    @Override
    public List<EmployeeDTO> findAll() {
        log.debug("Request to get all Employees");
        return employeeRepository.findAll().stream()
            .map(employeeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one employee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<EmployeeDTO> findOne(String id) {
        log.debug("Request to get Employee : {}", id);
        return employeeRepository.findById(id)
            .map(employeeMapper::toDto);
    }

    /**
     * Delete the employee by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Employee : {}", id);
        employeeRepository.deleteById(id);
    }

    @Override
    public String googleSignIn(String token) {
        JSONObject jsonObject = new JSONObject(token);
        String idToken = (String) jsonObject.get("idToken");
        String msg = "";
        Employee employee = null;
        String jwtToken = "";
        try {
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = new JacksonFactory();

            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                .Builder(httpTransport, jsonFactory)
                .setAudience(Collections.singletonList(CrmsConstants.GOOGLE_CLIENT_ID)).build();

            GoogleIdToken googleIdToken = verifier.verify(idToken);


            if (googleIdToken != null) {

                GoogleIdToken.Payload payload = googleIdToken.getPayload();

                String userId = payload.getUserId();
                String email = payload.getEmail();
                String name = (String) payload.get("name");
                String picture = (String) payload.get("picture");

                employee = isUserAvailable(email);
                if (employee == null) {
                    Employee emp = new Employee();
                    //emp.setId(new ObjectId().toString());
                    emp.setEmail(email);
                    emp.setName(name);
                    emp.setPicture(picture);

                    employee = saveUser(emp);
                    msg = "Employee Logged in Successfully.";
                }


                StringBuilder tokenBuilder = new StringBuilder();

                tokenBuilder.append("id:"+employee.getId());
                ObjectMapper objectMapper = new ObjectMapper();

                StringBuilder empToken = new StringBuilder();

                    empToken.append(objectMapper.writeValueAsString(employee));
                    empToken.append(",");
                    empToken.append(tokenBuilder.toString());

                /*tokenProvider.getAuthentication(tokenBuilder.toString());*/
                jwtToken = tokenProvider.createToken(tokenProvider.getAuthentication(employee.getId()),false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jwtToken;
    }

    public String registerEmployee(Employee employee){

        // register object  for validation
        ValidatorInterface validatorInterface = new ValidatorInterfaceImpl();
        validatorInterface.validate(employee);

        String errorMsg="";

        if(employee!=null){

            String employeeDesignation ;
            String role;

            errorMsg = validatorInterface.checkNullOrEmptyValue(BeanUtils.getPropertyDescriptor(Employee.class,"role").getReadMethod());
            errorMsg = validatorInterface.checkNullOrEmptyValue(BeanUtils.getPropertyDescriptor(Employee.class,"skills").getReadMethod());
            errorMsg = validatorInterface.checkNullOrEmptyValue(BeanUtils.getPropertyDescriptor(Employee.class,"email").getReadMethod());

            if(!errorMsg.trim().equals(""))
                return errorMsg;

            Employee emp = isUserAvailable(employee.getEmail());

            if(emp!=null) {
                errorMsg = "Employee already registered";
                return errorMsg;
            }
            employee.setId(new ObjectId().toString());
            employeeRepository.save(employee);
            errorMsg = "Employee registered";
        }else{
            errorMsg="Problem with the data";
        }
        return errorMsg;
    }

    private Employee saveUser(Employee employee) {

        Employee emp = isUserAvailable(employee.getEmail());

        if(emp==null){
            employeeRepository.save(employee);
            emp = isUserAvailable(employee.getEmail());
        }
        return emp;
    }

    private Employee isUserAvailable(String email) {

        final Query query = new Query();
        Criteria criteria =  new Criteria();
        query.addCriteria(Criteria.where("email").regex(email));

        List<Employee> employee = mongoTemplate.find(query,Employee.class);

        return employee==null||employee.isEmpty()?null:employee.get(0);
    }

    @Override
    public Map<String, List<Object>> getMasterData() {

        Map<String,List<Object>> masterDataMap = new HashMap<>();

                List<Object> skillsList ;
                skillsList =(List)skillsRepository.findAll();
                masterDataMap.put("skills",skillsList);

                List<Object> rolesList;
                /*List<Object> rolesList_1;
                List<Object> rolesList_2;
                List<Object> rolesList_3;*/

                /*rolesList = roleRepository.findAll().stream().map(roleMapper::toDto).collect(Collectors.toCollection(ArrayList::new));   // all roles
                rolesList_1 = roleRepository.getAvailableRoles().stream().map(roleMapper::toDto).collect(Collectors.toCollection(ArrayList::new));  // valid roles using @Query annotation
                rolesList_2 = roleRepository.findIdAndNameAndExcludeIsDeleted(false).stream().collect(Collectors.toList());//.stream().map(roleMapper::toDto).collect(Collectors.toCollection(ArrayList::new));   // valid roles using
*/
                rolesList = roleRepository.findIdAndNameAndByIsDeleted(false).stream().collect(Collectors.toList());

                masterDataMap.put("designation",rolesList);

                List<Object> billableList;
                billableList = (List)billableRepository.findAll();
                masterDataMap.put("isBillable",billableList);

                List<Object> locationList;
                locationList = (List)officeLocationRepository.findAll();
                masterDataMap.put("locations",locationList);

                List<Object> empTypeList;
                empTypeList = (List)employmentTypeRepository.findAll();
                masterDataMap.put("empTypeList",empTypeList);


        return masterDataMap;
    }


}
