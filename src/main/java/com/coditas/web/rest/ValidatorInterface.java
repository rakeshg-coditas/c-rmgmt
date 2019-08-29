package com.coditas.web.rest;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author rakesh.ghonmode
 */
@Component
public interface ValidatorInterface {

    void validate(Object classObject);

    String checkNullOrEmptyValue(String field);


}
