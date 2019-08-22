package com.coditas.repository;

import com.coditas.domain.TeamMembers;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the TeamMembers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeamMembersRepository extends MongoRepository<TeamMembers, String> {

}
