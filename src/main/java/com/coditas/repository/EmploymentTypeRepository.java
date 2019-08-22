package com.coditas.repository;

import com.coditas.domain.EmploymentType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentTypeRepository extends MongoRepository<EmploymentType,String> {
}
