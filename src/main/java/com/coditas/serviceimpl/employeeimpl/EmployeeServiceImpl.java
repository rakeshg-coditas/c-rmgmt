package com.coditas.serviceimpl.employeeimpl;

import com.coditas.constants.CrmsConstants;
import com.coditas.dto.Employee;
import com.coditas.errors.ValidatorInterface;
import com.coditas.errors.ValidatorInterfaceImpl;
import com.coditas.repository.employee.EmployeeRepository;
import com.coditas.service.employee.EmployeeService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public String googleSignIn(String jsonIdToken){

        Employee emp = null;
        String msg = "";
        try{
                JSONObject jsonObject = new JSONObject(jsonIdToken);
                String idToken = (String)jsonObject.get("idToken");

                HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
                JsonFactory jsonFactory = new JacksonFactory();

                GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                                                        .Builder(httpTransport,jsonFactory)
                                                        .setAudience(Collections.singletonList(CrmsConstants.GOOGLE_CLIENT_ID)).build();

                GoogleIdToken googleIdToken =  verifier.verify(idToken);


                if(googleIdToken!=null) {

                    GoogleIdToken.Payload payload = googleIdToken.getPayload();

                    String userId = payload.getUserId();
                    String email = payload.getEmail();
                    String name = (String) payload.get("name");
                    String picture = (String) payload.get("picture");

                    Employee employee = isUserAvailable(email);
                    if(employee!=null){
                        employee.setPicture(picture);
                        msg = "Employee Logged in Successfully.";
                    }else{
                        msg = "Employee not registered.";
                    }
                    /*Employee employee = new Employee();
                    employee.setId(new ObjectId().toString());
                    employee.setEmail(email);
                    employee.setName(name);
                    employee.setPicture(picture);

                    emp = saveUser(employee);*/
                    System.out.println("successs");
                }
        }catch(Exception e){

        }
        return msg;
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

}
