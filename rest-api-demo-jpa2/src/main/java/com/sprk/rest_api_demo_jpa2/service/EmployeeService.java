package com.sprk.rest_api_demo_jpa2.service;

import com.sprk.rest_api_demo_jpa2.dto.EmployeeDto;
import java.util.List;

public interface EmployeeService {

      EmployeeDto saveEmployee(EmployeeDto employeeDto);

      List<EmployeeDto> getAllEmployees();

      EmployeeDto getByEmpId(String empId);

      void deleteEmployee(String empId);

      EmployeeDto updateEmployeeByEmpId(EmployeeDto employeeDto, String empId);

      EmployeeDto updateEmployeeByEmpIdV1(EmployeeDto employeeDto, String empId);

}
