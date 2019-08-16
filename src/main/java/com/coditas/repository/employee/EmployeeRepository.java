package com.coditas.repository.employee;

import com.coditas.dto.Employee;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {

    @Override
    boolean existsById(ObjectId objectId);

    Employee findByEmail(String email);

    @Override
    List<Employee> findAll();

    @Override
    Iterable<Employee> findAllById(Iterable<ObjectId> objectIds);

    @Override
    Optional<Employee> findById(ObjectId objectId);


}
