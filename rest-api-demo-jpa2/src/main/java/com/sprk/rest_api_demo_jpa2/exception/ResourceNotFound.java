package com.sprk.rest_api_demo_jpa2.exception;

import lombok.Getter;

@Getter
public class ResourceNotFound extends RuntimeException {

    int statusCode;

    public ResourceNotFound(String mesaage, int statusCode){
        super(mesaage);
        this.statusCode = statusCode;
    }

}
   
