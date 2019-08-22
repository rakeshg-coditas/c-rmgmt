package com.coditas.repository;

import com.coditas.domain.OfficeLocation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeLocationRepository extends MongoRepository<OfficeLocation,String> {
}
