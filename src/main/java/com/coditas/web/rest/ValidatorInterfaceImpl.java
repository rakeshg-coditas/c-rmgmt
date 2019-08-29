package com.coditas.web.rest;


import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author rakesh.ghonmode
 */
@Component
public class ValidatorInterfaceImpl implements ValidatorInterface {


    private Object myClassObject;
    private String className ;

    public ValidatorInterfaceImpl(){}

    @Override
    public void validate(Object classObject)  {

        try {
            className = classObject.getClass().getName();
            myClassObject = classObject;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String checkNullOrEmptyValue(String field) {
        String message = "";
            if(className!=null || !className.trim().equals("")){
                try {
                    Method getterMethodName = BeanUtils.getPropertyDescriptor(myClassObject.getClass(),field).getReadMethod();
                    String value = (String)getterMethodName.invoke(myClassObject);
                    if(value == null || value.toString().trim().isEmpty()){
                        message = field+" is Required.";
                    }
                }catch (Exception e){

                }
            }
        return message;
    }

}
