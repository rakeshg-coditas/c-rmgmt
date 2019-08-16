package com.coditas.service.employee;

import com.coditas.dto.Employee;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("employeeService")
@Service
public interface EmployeeService {

        public Employee googleSignIn(String jsonIdToken);

        public String registerEmployee(Employee employee);
}
