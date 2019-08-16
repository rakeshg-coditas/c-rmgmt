package com.coditas.errors;

import java.lang.reflect.Method;

public interface ValidatorInterface {

    void validate(Object classObject);

    String checkNullOrEmptyValue(Method getterMethodName);


}
