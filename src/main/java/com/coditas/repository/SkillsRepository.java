package com.coditas.repository;

import com.coditas.domain.Skills;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface SkillsRepository extends MongoRepository<Skills,String> {
}
