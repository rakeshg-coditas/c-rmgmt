package com.coditas.repository;

import com.coditas.domain.Billable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillableRepository extends MongoRepository<Billable,String> {
}
