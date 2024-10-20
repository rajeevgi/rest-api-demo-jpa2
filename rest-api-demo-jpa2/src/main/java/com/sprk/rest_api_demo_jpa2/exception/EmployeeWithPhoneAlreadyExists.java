package com.sprk.rest_api_demo_jpa2.exception;

public class EmployeeWithPhoneAlreadyExists extends ResourceNotFound {

    public EmployeeWithPhoneAlreadyExists(String message, int statusCode){
        super(message, statusCode);
    }
}
