package com.coditas.errors;


import java.lang.reflect.Method;

public class ValidatorInterfaceImpl implements ValidatorInterface {

    //private Class myClass ;
    private Object myClassObject;
    private String className ;

    @Override
    public void validate(Object classObject)  {

        try {
            className = classObject.getClass().getName();
            myClassObject = classObject;

            System.out.println("======="+className);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String checkNullOrEmptyValue(Method getterMethodName) {

        String message = "";
            if(className!=null || !className.trim().equals("")){
                try {
                    Class cl =(Class.forName(className));
                    String value = (String)getterMethodName.invoke(myClassObject);
                    if(value == null || value.toString().trim().isEmpty()){
                        message = getterMethodName.getName()+" is Required";
                    }
                }catch (Exception e){

                }
            }
        return message;
    }

}
