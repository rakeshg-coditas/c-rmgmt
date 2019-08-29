package com.coditas.web.rest.validations;


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
                    message = e.getMessage();
                }
            }
        return message;
    }

    @Override
    public String checkIfValidInteger(String field){
        String message = "";
        if(className!=null || !className.trim().equals("")){
            try {
                Method getterMethodName = BeanUtils.getPropertyDescriptor(myClassObject.getClass(), field).getReadMethod();
                String value = (String) getterMethodName.invoke(myClassObject);  //illegal access expetion , Invocation target exception
                try {
                    Integer.valueOf(value);
                } catch (NumberFormatException numberFormatException) {
                    message = field + " should be a number.";
                }
            }catch(Exception e){
                message = e.getMessage();
            }
        }
        return message;
    }

}
