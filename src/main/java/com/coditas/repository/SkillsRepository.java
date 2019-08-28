package com.coditas.repository;

import com.coditas.domain.Skills;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillsRepository extends MongoRepository<Skills,String> {
}
