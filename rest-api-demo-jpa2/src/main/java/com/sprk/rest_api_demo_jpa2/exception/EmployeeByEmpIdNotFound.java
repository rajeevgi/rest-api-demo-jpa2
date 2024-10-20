package com.sprk.rest_api_demo_jpa2.exception;

public class EmployeeByEmpIdNotFound extends ResourceNotFound {

    public EmployeeByEmpIdNotFound(String message, int statusCode) {
        super(message, statusCode);
    }
}
