package com.sprk.rest_api_demo_jpa2.exception;

public class EmployeeWithEmailAlreadyExists extends ResourceNotFound {

    public EmployeeWithEmailAlreadyExists(String message, int statusCode){
        super(message, statusCode);
    }
}
