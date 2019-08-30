package com.coditas.repository;

import com.coditas.domain.Client;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Client entity.
 */
@SuppressWarnings("unused")
@Repository
@JaversSpringDataAuditable
public interface ClientRepository extends MongoRepository<Client, String> {

}
