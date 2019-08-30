package com.coditas.repository;

import com.coditas.domain.Billable;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface BillableRepository extends MongoRepository<Billable,String> {
}
