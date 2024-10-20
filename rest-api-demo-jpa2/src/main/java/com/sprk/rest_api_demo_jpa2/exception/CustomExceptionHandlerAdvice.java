package com.sprk.rest_api_demo_jpa2.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sprk.rest_api_demo_jpa2.dto.ErrorResponseDto;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    /*@ExceptionHandler(EmployeeWithEmailAlreadyExists.class)
    public ResponseEntity<?> handleEmployeeWithEmailAlreadyExists(EmployeeWithEmailAlreadyExists employeeWithEmailAlreadyExists, HttpServletRequest request){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(request.getContextPath(), "500",employeeWithEmailAlreadyExists.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(500).body(errorResponseDto);
    }


    @ExceptionHandler(EmployeeWithPhoneAlreadyExists.class)
    public ResponseEntity<?> handleEmployeeWithPhoneAlreadyExists(EmployeeWithPhoneAlreadyExists employeeWithPhoneAlreadyExists, HttpServletRequest request){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(request.getContextPath(), "500",employeeWithPhoneAlreadyExists.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(500).body(errorResponseDto); 
    }*/

    @ExceptionHandler(ResourceNotFound.class)
    public  ResponseEntity<ErrorResponseDto> handleResourceNotFound(ResourceNotFound resourceNotFound, HttpServletRequest request){

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(request.getServletPath(), resourceNotFound.getStatusCode(),resourceNotFound.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(resourceNotFound.getStatusCode()).body(errorResponseDto);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){

        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });

        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
}
