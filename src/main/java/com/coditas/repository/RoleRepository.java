package com.coditas.repository;

import com.coditas.domain.Role;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data MongoDB repository for the Role entity.
 */
@SuppressWarnings("unused")
@Repository
@JaversSpringDataAuditable
public interface RoleRepository extends MongoRepository<Role, String> {

    @Query("{'isDeleted':false}")
    public List<Role> getAvailableRoles();

    public List<Role> findByIsDeleted(boolean flag);


    @Query(value = "{isDeleted:?0}",fields="{ id : 1 , name : 1 }")
    public List<Role> findIdAndNameAndExcludeIsDeleted(boolean isDeleted);


    @Query(fields="{ id : 1 , name : 1 }")
    public List<Role> findIdAndNameAndByIsDeleted(boolean isDeleted);


}
